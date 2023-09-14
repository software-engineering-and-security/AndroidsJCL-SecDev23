#!/bin/python3

import json
#____________________________________________________rejection_list
def rejection_list(key1, key2):
    with open('rejection_list', 'a+') as rej_file:
        rej_file.write ("rejection between: |"+str(key1)+"| & |"+str(key2)+"|\n")

#____________________________________________________open_json_into_dict
def open_json_into_dict(filepath ):

    with open(filepath, 'r') as input_jsonfile:
        data = json.load(input_jsonfile)

    return data
#___________________________________________________save_json
def save_json(tosave_dict, namefile):

    with open("outputDir/"+namefile+".json",'w')as output_json:
        json.dump(tosave_dict, output_json)
#____________________________________________________save_list
def save_list(list_to_save, file_name):
    with open("outputDir/"+file_name, 'w') as output_file:
        output_file.write( str(list_to_save))

#____________________________________________________open_clean_libcore_jdk_versions
def open_clean_libcore_jdk_versions(filepath):
    with open(filepath, 'r') as input_file:
        raw_lines = input_file.readlines()

    cleared_lines_list = []
    splited_selected_lines = []
    #select the lines
    for indiv_raw_line in raw_lines:
        if not( '#' in indiv_raw_line) and ('jdk' in indiv_raw_line)  and ('/' in indiv_raw_line):
            #print ("-->Selected:"+str(indiv_raw_line.replace('\n','').split(' ')))
            #splited_selected_lines.append(indiv_raw_line.replace('\n',''))
            for element in indiv_raw_line.replace('\n','').split(' '):
                if 'jdk' in element:
                    cleared_lines_list.append(element)
        ##Debug
        #else:
            #print ("==EXCLUDED=="+indiv_raw_line)
    
    #clean the lines
    #for indiv_line in selected_lines:    
    #    cl    

    #print (str(cleared_lines_list))

    return cleared_lines_list
#____________________________________________________format_key
def format_version_key(raw_version_key):
    #goal is to have something like jdkXuY
    
    version_key_split=raw_version_key.split(':')

    major=version_key_split[2]
    minor=version_key_split[3]

    if '*' in minor or '-' in minor:
    #if '*' in minor:# or '-' in minor:
        minor=''
    elif 'update' in minor:    
        minor=minor.split('update')[1]

    if '*' in major:
        major=''

    formated_key_version="jdk"+str(major)+"u"+minor    
    #print("--DEBUG-->"+str(raw_version_key)+" | "+ formated_key_version)
    formated_key_version_list=["jdk"+str(major), str(minor)]
    return formated_key_version_list


#____________________________________________________main

#get list of CVE per vulnerable config
vulnerable_to_cve_path="outputDir/vulnerable_config_to_cves.json"
vulnerable_to_cve_dict=open_json_into_dict(vulnerable_to_cve_path)

#get the list of of openjdk version in libcore
# located in ~/javalangAnalysis/EXPECTED_UPSTREAM_analysis/counted_origines_android-13.0.0_r37
#will need some cleaning thoughi

libcore_jdks_path="/home/triom/javalangAnalysis/EXPECTED_UPSTREAM_analysis/counted_origines_android-13.0.0_r37"
expected_versions_list = open_clean_libcore_jdk_versions(libcore_jdks_path)

vulnconfig_to_cve_dict=dict()
strict_equals_vulnconfig_to_cve_dict=dict()
for indiv_key in vulnerable_to_cve_dict.keys():
    for indiv_expected_version in expected_versions_list:
        #reompose indiv_key into jdkXuY
        formated_key_version_list=format_version_key(indiv_key)
        #print ("--DEBUG-->"+str(formated_key_version_list)+" compared to "+str(indiv_version))
        if (formated_key_version_list[0] in indiv_expected_version) and ((("u"+formated_key_version_list[1]) in indiv_expected_version ) or (("+"+formated_key_version_list[1]) in indiv_expected_version )):
            #print ("--DEBUG-->Match on: |"+str(indiv_key)+"| "+str(formated_key_version_list)+"| |and| |"+str(indiv_expected_version)+"|")
            if not(indiv_expected_version in vulnconfig_to_cve_dict.keys()):
                vulnconfig_to_cve_dict[indiv_expected_version]=[]
                
            vulnconfig_to_cve_dict[indiv_expected_version].extend(vulnerable_to_cve_dict[indiv_key])
            #exclusive_list
            if not('*' in indiv_key or '-' in indiv_key):
                print ("--DEBUG-->StrictInclude: |"+str(indiv_key)+"| and |"+str(indiv_expected_version)+"|")
                if not(indiv_expected_version in strict_equals_vulnconfig_to_cve_dict.keys()):
                    strict_equals_vulnconfig_to_cve_dict[indiv_expected_version]=[]
                strict_equals_vulnconfig_to_cve_dict[indiv_expected_version].extend(vulnerable_to_cve_dict[indiv_key])
        else:
            rejection_list(indiv_key, indiv_expected_version)    






            
#print (str(vulnerable_to_cve_dict.keys()))
print (str(vulnconfig_to_cve_dict.keys()))
#print (str(vulnconfig_to_cve_dict))           

save_json(vulnconfig_to_cve_dict, "2_cleaned_vulnConf_to_cve")
save_json(strict_equals_vulnconfig_to_cve_dict, "2_equals_cleaned_vulnConf_to_cve")

#new dict with , first layer exclusives CVES, and second , shared
vulnerable_config_to_cve_dict=dict()
vulnerable_config_to_cve_dict["exclusive"]=dict()
vulnerable_config_to_cve_dict["shared"]=dict()
#control elements
shared_list=[]
exclusive_list=[]

exclusive_cve2config=dict()
shared_cve2config=dict()

for vuln_key_1 in vulnconfig_to_cve_dict.keys():
    vulnerable_config_to_cve_dict['exclusive'][vuln_key_1]=[]
    vulnerable_config_to_cve_dict['shared'][vuln_key_1]=[]

    for cve_1 in vulnconfig_to_cve_dict[vuln_key_1]:
        DEBUG=0
        if "2021-2161" in cve_1:
            DEBUG=1
        #shared    
        if cve_1 in shared_list:
            if not(cve_1 in vulnerable_config_to_cve_dict['shared'][vuln_key_1]):
                if DEBUG:
                    print ("--DEBUG-->"+str(cve_1)+"| already in shared |"+"|added to: "+str(vuln_key_1))
                vulnerable_config_to_cve_dict['shared'][vuln_key_1].append(cve_1)
                shared_cve2config[cve_1].append(vuln_key_1)
            elif DEBUG:
                print ("--DEBUG--> Was already in")
        elif cve_1 in exclusive_list:
            
            vuln_key_2 =  exclusive_cve2config[cve_1]
            if not(vuln_key_2==vuln_key_1):
                #add to control elements
                if cve_1 in shared_list:
                    print ("===ERROR=== |Algorithm issue|")
                exclusive_list.remove(cve_1)
                shared_list.append(cve_1)
                #save approprietly
                vulnerable_config_to_cve_dict["shared"][vuln_key_1].append(cve_1)
                vuln_key_2 = exclusive_cve2config[cve_1]
                if DEBUG:
                    print("--DEBUG-->Was exclusive at :"+str(vuln_key_2))
                #create the field
                shared_cve2config[cve_1]=[vuln_key_2, vuln_key_1]
                #save it result dicts
                vulnerable_config_to_cve_dict["shared"][vuln_key_2].append(cve_1)
                vulnerable_config_to_cve_dict["exclusive"][vuln_key_2].remove(cve_1)
                # remove from exclusive_cve2config
                exclusive_cve2config.pop(cve_1, None)
            elif DEBUG:
                print ("--DEBUG--> From exclusive to the same vulnerable")
        else:
            if DEBUG:
                print ("--DEBUG-->New CVE")
            exclusive_list.append(cve_1)
            exclusive_cve2config[cve_1]=vuln_key_1
            vulnerable_config_to_cve_dict["exclusive"][vuln_key_1].append(cve_1)


save_json(vulnerable_config_to_cve_dict, "2_sorted_shared_and_exclusives")
save_json(shared_cve2config, "2_shared_cve2config")
print ("--Length of shared list: "+str(len(shared_list)))
print ("--Length of exclusive cves: "+str(len(exclusive_cve2config.keys())))
save_list(shared_list, "2_shared_cves_list")
