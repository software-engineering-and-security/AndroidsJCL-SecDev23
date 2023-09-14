#!/bin/python3


#########################################################################################################
#                                                                                                       #
#    The objective of this program is to scrapp webpages                                                #
#   so to find the patch given a certain list of CVEs given in input                                    #
#                       as a list.                                                                      #
#                                                                                                       #
#    The program will first aim for openjdk but might teh deviate                                       #
#                                                                                                       #
#       It will focus on Bugzilla:                                                                      #
#           1. Bugzilla: finding the page |getting the url after "upstream cmommit"                     #
#           2. CVE description | getting the bugID | opening the right page in mercurial openJDK repo   #
#                                                                                                       #
#       Outputs: dict of CVE->patch                                                                     #
#
#       !!!! Only matches with links in gitlab, github or hg
#
#                                                                                                       #
#########################################################################################################
import re
import sys
import time
import json
import random
import requests
from os import listdir
from bs4 import BeautifulSoup


#____________________________________________________________get_cve_list
def get_cve_list(seeked_config):
    with open("outputDir/1_cve_list_"+seeked_config,'r') as cve_list_file:
        cve_list=cve_list_file.readlines()
    for cve in cve_list:
        cve = cve.replace('\n','')

    return cve_list    


#___________________________________________________________get_parsed_soup_from_url
def get_parsed_soup_from_url(url):
    response=requests.get(url)
    parsed_soup=BeautifulSoup(response.text, 'html.parser')
    return parsed_soup
#____________________________________________________________save_to_json
def save_to_json(dict_to_save, file_name):
    if file_name.split('/')[-1] in listdir("./outputDir"):
        print ("--output update")
        
        with open(file_name, 'r') as out_json:
            out_dict=json.load(out_json)
        for cve_key in dict_to_save.keys():
            if cve_key in out_dict.keys():
                for commit in dict_to_save[cve_key]:
                    if not(commit in out_dict[cve_key]):
                        out_dict[cve_key].append(commit)
            else:
                out_dict[cve_key]=dict_to_save[cve_key]
        with open(file_name, 'w') as output_json:
            json.dump(out_dict, output_json)
    else:
        print ("--output creation")
        with open(file_name, 'w') as output_json:
            json.dump(dict_to_save, output_json)
#____________________________________________________________test_regex
def test_regex(bug_id_regex, debug_mode):
    return_status=1
    test_case_list=["Bug Id 6767", "bshjabcsh bug Id. 67843254 dbjwbddb"]
    for tc in test_case_list:
        if debug_mode:
            print ("bug id in "+str(tc)+": "+str(re.search(bug_id_regex, tc)))
        if not( re.search(bug_id_regex, tc)):
            return_status=0
    return return_status    

#____________________________________________________________main

#initialised_varables
bugzilla_root_url="https://bugzilla.redhat.com/show_bug.cgi?id="
cve_mitre_root_url="https://cve.mitre.org/cgi-bin/cvename.cgi?name="

#to be imported from source with a json with specific fields
seeked_config_root_url="https://hg.openjdk.org/"
seeked_config_repo_root_url="https://hg.openjdk.org/jdk[version]/jdk[version]/jdk/"
seeked_config_search_addition="log?rev="
seeked_config_commit_addition="rev/"

#counters
bugzilla_page_counter=0
comment_table_counter=0
upstream_commit_link_counter=0
bug_id_found_mitre_counter=0

#get CVE_list from file
seeked_config=sys.argv[1]
cve_list=get_cve_list(seeked_config)

#set up debug mode
debug_mode=0
cve_for_debug="CVE-2020-2803"
if (len(sys.argv)>=3):
    debug_mode=int(sys.argv[2])
    print ("-DEBUG MODE- "+str(debug_mode))

debug_mitre_list=["CVE-2009-3884"]
debug_bz_list=["CVE-2020-2803"]
debug_cve_list=["CVE-2020-2803","CVE-2009-3884"]

#result structure
cve2patch_dict=dict()

#error structure
mitre_error_list=[]


#regex
bug_id_regex=re.compile(r"(B|b)ug\s*(I|i)d\.?\s*[0-9]+")#(b|B)ug\w+(I|i)d\.?\w+[0-9]+")
if not(test_regex(bug_id_regex, debug_mode)):
    quit()


for indiv_cve in cve_list:
    cve = indiv_cve.rstrip()

    cve2patch_dict[cve]=[]

    #debug status
    debug_cve=0
    if debug_cve:
        print ("|"+cve+"|"+cve_for_debug+"|")
    if (debug_mode>=1):
        #debug_mode ==3 then full verbose, if ==1, triggered debug
        
        if (cve in debug_bz_list ) and debug_mode==1:
            #bz
            debug_cve=1
            #print ("bz debug")
        elif (cve in debug_mitre_list) and debug_mode==2:    
            #mitre
            debug_cve=1
            #print ("mitre debug")
        elif debug_mode==3:
            debug_cve=1
            print ("debug:"+str(debug_cve))
            #print ("full debug")
    #debug_cve=1

    found_commit_url_status=0
    
    #Try to get redhat page
    bugzilla_cve_soup=get_parsed_soup_from_url(bugzilla_root_url+cve)

    if ( bugzilla_cve_soup.find(class_='bz_short_desc_container edit_form')):
        page_read_cve = (bugzilla_cve_soup.find(id='alias_nonedit_display')).contents[0]
        if debug_cve:
            print ("_______________________________________________|"+str(cve)+"|"+str(page_read_cve)+"|")
        if (str(cve) in (page_read_cve)):
        #soup=BeautifulSoup(page.text, 'html.parser')
            if debug_cve:
                print ("--bz-Works for: "+str(cve)) 
            bugzilla_page_counter+=1
            #From here try find the upstream commits
            #First get the table
            if (bugzilla_cve_soup.find(class_='bz_comment_table')):
                comment_table=(bugzilla_cve_soup.find(class_='bz_comment_table'))#.contents[0]
                comment_table_counter+=1
                if debug_cve:
                    print (type(comment_table))
                    print (comment_table.name)
                    #print (comment_table)
                #Second get all the comments    
                comment_table_td=comment_table.find('td')    
                if debug_cve:
                    print ("<<<td>>>>\n")
                    #print (comment_table_td)
                comment_list=comment_table_td.find_all('div')#class_='bz_comment_text')
                #DEBUG
                if debug_cve:
                    comment_list_size=len(comment_list)
                    print ("Size of commit_list: "+str(len(comment_list)))
                    if (comment_list_size==0): 
                        quit()
                if(len(comment_list)>0) :
                    if debug_cve:
                        print("--bz--comments_found------------Yes")
                    for comment_elt in comment_list:
                        #Full comments, we know need to  focus on bz_comment_text
                        if debug_cve:
                            print("--bz----Full Comment\n")
                            #print(comment_elt)#.text.rstrip())
                        comment_elt_text=comment_elt.find(class_='bz_comment_text')
                        if debug_cve:
                            if not(type(comment_elt_text)==type(None)):
                                print("--bz----Comment text--------\n")
                                print(comment_elt_text.text)
                            else:
                                print ("xXx Error xXx\n"+str(comment_elt_text))
                        if ("upstream commit" in comment_elt.text):
                            upstream_commit_link_counter+=1
                            found_commit_url_status=1
                            urls_list= comment_elt.find_all('a', href=True)
                            for commit_url in urls_list:
                                if debug_cve:
                                    print ("--bz----href--"+commit_url['href'])
                                #so far we do nothing with all those urls
                                if ("github" in commit_url['href']) or ('gitlab' in commit_url['href']) or ('hg.' in commit_url['href']):
                                    if debug_cve:
                                        print ("--bz----potential upstream patch----"+commit_url['href'])
                                    cve2patch_dict[cve].append(str(commit_url['href']))

                    if found_commit_url_status==0:
                        if debug_cve:
                            print ("--bz--no_commit_url_found------No")


                else:
                    print ("No comment found in table")

    #else and/or did not work: try with the bug-ID
    if len(cve2patch_dict[cve])==0:
        cve_mitre_webpage=cve_mitre_root_url+cve
        if debug_cve:
            print (str(cve)+": bugzilla doesnt work")
            print ("Trying with mitre: |"+str(cve_mitre_webpage))
        #try access the cve description
        cve_mitre_soup = get_parsed_soup_from_url(cve_mitre_webpage)
        if (cve_mitre_soup.find(id="GeneratedTable")):
            mitre_table_soup = cve_mitre_soup.find(id="GeneratedTable")
            if debug_cve:
                if not(type(mitre_table_soup)==type(None)):
                    print (str(mitre_table_soup['id']))
            cve_mitre_table_rows= mitre_table_soup.find_all('tr')
            #if found:
            if (len(cve_mitre_table_rows)>4):
                if debug_cve:
                    print ("--mitre----"+str(cve_mitre_table_rows[2].text))
                if ("Description" in  cve_mitre_table_rows[2].text):
                    if debug_cve:
                        print ("--mitre----desc--"+str(cve_mitre_table_rows[3].text))
                    cve_description = cve_mitre_table_rows[3].text    
                    #search for a bug ID
                    if ( re.search(bug_id_regex, cve_description)):
                    #if found:
                        #get the actual bug Id
                        bug_id = re.search(bug_id_regex, cve_description).group(0)
                        bug_id_number= re.search(r"[0-9]+",bug_id).group(0)
                        if debug_cve:
                            print("bug id: |"+str(bug_id)+"|")
                            #print (bug_id.group(0))
                        bug_id_found_mitre_counter+=1    
                        #enter bug ID page
                        generic_search_page_url=seeked_config_repo_root_url+seeked_config_search_addition+bug_id_number#adapt to format
                        search_page_url=generic_search_page_url.replace('[version]', '8')
                        if debug_cve:
                            print ("--mitre----Search Page--------")
                            print (search_page_url)
                        search_page_soup=get_parsed_soup_from_url(search_page_url)
                        if not(type(None)==type(search_page_soup)):
                            #access commit hash for bug ID
                            main_area_soup= search_page_soup.find(class_="main")#proper to openjdk
                            main_table_soup= main_area_soup.find(class_="bigtable")#proper to openJdk
                            if debug_cve:
                                print("--mitre----Repo----Search Page----Main Table")
                                #print(str(main_table_soup.text))
                            rows_soup = main_table_soup.find_all('tr')#tbd: get actual table row
                            for row_soup in rows_soup:
                                if debug_cve:
                                    print("--mitre----Repo----Search Page----Row")
                                    #print (row_soup)
                                if (row_soup.find('a', href=True)):
                                     if debug_cve:
                                         print ("---------------Found href")
                                         #quit()
                                     description_row_soup=row_soup.find(class_="description")    #proper to openjdk
                                     if (bug_id_number in description_row_soup.text):
                                         matched_urls=description_row_soup.find_all('a', href=True)
                                         for matched_url in matched_urls:
                                             commit_url=matched_url['href']
                                             if debug_cve:
                                                 print (seeked_config_root_url+str(commit_url))
                                                 #quit()
                                             if not(seeked_config_root_url in commit_url):
                                                 complete_url=seeked_config_root_url+commit_url
                                             else:
                                                 complete_url=commit_url
                                             cve2patch_dict[cve].append(complete_url)    
#                                #get the href
#                                                #tbd: find href='True'
#                                if (X.text == bug_id_number):#tbd: X
#                                    commit_hash= #tbd: find it
                            #access page with commit hash

                            #save to cve2path_dict


                    else:
                        if debug_cve:
                            print ("Bug_Id not in description")
                        #tbd verify by hand with table i
                        mitre_error_list.append(cve)
    if (len(cve2patch_dict[cve])==0):
        if debug_cve:
            print ("Attempting patch or bug id in nist cve webpage")



    time.sleep(random.uniform(0, 0.1))

save_to_json(cve2patch_dict, "outputDir/3_cve_to_upstream_commits.json")

upstream_verif_counter=0
for cve in cve2patch_dict.keys():
    if len(cve2patch_dict[cve])>0:
        upstream_verif_counter+=1


print ("BugzillaCounter: "+str(bugzilla_page_counter)+"/"+str(len(cve_list)) )
print ("CommentTableCounter: "+str(comment_table_counter)+"/"+str((bugzilla_page_counter)))
print ("Upstream_commit comment link found: "+str(upstream_commit_link_counter)+"/"+str(comment_table_counter))
print ("Upstream commit for cve in saved file: "+str(upstream_verif_counter)+"/"+str(len(cve2patch_dict.keys())))
print ("Bug id found on mitre cve descriptionsi: "+str(bug_id_found_mitre_counter))
print ("Mite Error List: "+str(mitre_error_list))
