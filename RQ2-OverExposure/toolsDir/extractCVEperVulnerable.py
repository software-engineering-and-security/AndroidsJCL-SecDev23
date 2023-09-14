#!/bin/python3
import sys
import json
from os import listdir
from os.path import isfile, join

#__________________________________________________________log_error
def log_error(error_message ,log_folder):
  #filename = File.open(log_folder+"/error_log",'a+')
  with open(log_folder+"/error_log", 'a+') as f:
      f.write( "\n----->"+error_message+"\n")

#__________________________________________________________open_json_to_hash
def open_json_to_hash(path_to_file):
    print (path_to_file)
    with open(path_to_file, 'r') as json_file:
        data = json.load(json_file)

    #file.close()
    return data

#_________________________________________________________save_json
def save_json(cve_version_hash ,filepath):
  #save json dict as json file
  with open(filepath, 'w') as output_json:
      json.dump(cve_version_hash, output_json)
#__________________________________________________________save_dict
def save_cve_list(cve_list, file_path):
    #save list in txt file
    sorted_cve_list=list(cve_list)
    sorted_cve_list.sort()
    print (str(sorted_cve_list))
    with open(file_path,'w') as output_file:
        for cve in sorted_cve_list:
            output_file.write (cve+"\n")
#__________________________________________________________extract_cve_id
def extract_cve_id(input_hash):
  cve_id = input_hash["cve"]["CVE_data_meta"]["ID"]

  return cve_id

#__________________________________________________________extract_array_of_vulnerable_configurations
def extract_vulnerable_configurations(cve_hash):
    #print (str(cve_hash.keys()))
    configurations_list = cve_hash["configurations"]["nodes"]

    vulnerable_configurations_list=list()

    for uniq_node in configurations_list:
      if "cpe_match" in uniq_node.keys():
        cpe_match_list = uniq_node["cpe_match"]
        for vulnerable_cpe in cpe_match_list:
          if "cpe23Uri" in vulnerable_cpe.keys():
            vulnerable_line = vulnerable_cpe["cpe23Uri"]
            cpe23_list  = vulnerable_line.split(":")
            vulnerable_configurations_list.append(str(cpe23_list[3]+":"+cpe23_list[4]+":"+cpe23_list[5]+":"+cpe23_list[6]))
    return vulnerable_configurations_list
  
#__________________________________________________________check_for_vulnerable_element
def check_vulnerable_config(vulnerable_config_array, seeked_config):
    status = False

    for one_config in vulnerable_config_array:
        #print (str(one_config)+"  |"+str(seeked_config)+"->"+str(seeked_config in  one_config))
        if (str(seeked_config)) in one_config:
            status = True

    return status
  
#__________________________________________________________main



#reboot log

seeked_config=sys.argv[1]

print ( "-->Searching for:"+str(seeked_config))


path_to_nvd_feeds_folder="/home/triom/nvd_feeds"


#CVEs Hash with vulnerable version
cve_to_vulnerable_hash = dict()
#per_version_CVEs
per_vulnerable_cves_hash = dict()


path_to_array_of_CVEs="CVE_Items"
path_to_each_cveID="[\"cve\"][\"CVE_data_meta\"][\"ID\"]"
path_to_vulnerable_s_array="[\"cve\"][\"configurations\"][\"nodes\"]"


all_feeds_array = listdir(path_to_nvd_feeds_folder)

#list all nvd feeds available
for filename in all_feeds_array:

    nvd_feeds_hash =  open_json_to_hash( path_to_nvd_feeds_folder+"/"+filename  )   
    #list of cves
    #all_cves = cve_to_commit_hash.keys()
  
    all_cves_array = nvd_feeds_hash[path_to_array_of_CVEs]

    for cve_hash in all_cves_array: 
       cve_ID=extract_cve_id(cve_hash)
       #print (cve_ID)
       cve_vulnerable_configurations = extract_vulnerable_configurations(cve_hash)
       #print (cve_vulnerable_configurations)
       
       if  check_vulnerable_config(cve_vulnerable_configurations, seeked_config):
           print (cve_ID)
           cve_to_vulnerable_hash[cve_ID]=list()
           for vulnerable_config in cve_vulnerable_configurations:
               #check if openjdk is included
               if seeked_config in vulnerable_config:
                   cve_to_vulnerable_hash[cve_ID].append(vulnerable_config)
                   if not (vulnerable_config in  per_vulnerable_cves_hash.keys() ):
                       per_vulnerable_cves_hash[vulnerable_config]=list()        
                   per_vulnerable_cves_hash[vulnerable_config].append(cve_ID)
               
             
    
#print (cve_to_vulnerable_hash)


save_json(cve_to_vulnerable_hash, "outputDir/1_cve_to_config.json")
save_json(per_vulnerable_cves_hash, "outputDir/1_vulnerable_config_to_cves.json")
save_cve_list(cve_to_vulnerable_hash.keys(), "outputDir/1_cve_list_"+seeked_config)
