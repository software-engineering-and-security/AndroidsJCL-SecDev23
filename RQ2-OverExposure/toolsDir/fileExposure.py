#!/bin/python3

#########################################################################
#
#
#
#
#
#
#
#
#######################################################################
import re
import sys
import git
import time
import json
import glob
import random
import requests
import subprocess
from os import makedirs, remove
from os.path import join, isfile, dirname
from bs4 import BeautifulSoup
from datetime import datetime

#_________________________________________________________open_json
def open_json(file_name, debug_mode):
    read_dict=dict()
    with open(file_name, 'r') as json_file:
        read_dict=json.load(json_file)
    return read_dict   
#_________________________________________________________get_parsed_soup_from_url
def get_parsed_soup_from_url(url, requets_headers, debug_mode):
    
    #verify if page not locally saved
    page_present=0

    if ("https" in url):
        path_to_file=url.split("https://")[1]
    elif ("http" in url):
        path_to_file=url.split("http://")[1]

    path_branch=""
    if "/file/" in url:
        path_branch="file"
    elif "/rev/" in url or "/commit/" in url:
        path_branch="rev"


    if isfile("webpagesDir/"+path_branch+"/"+path_to_file):
        page_present=1
    if page_present:
        if debug_mode in [5,6,7,9]:
            print ("_______________________  Already present")
        with open("webpagesDir/"+path_branch+"/"+path_to_file,'rb')  as saved_file:
            page_soup=BeautifulSoup(saved_file.read(), 'html.parser')
    else:
        if debug_mode in [5,6,7,9]:
            print ("-_-_-_-_-_-_-_-_-_-_-_-_ Need download")
        #if debug_mode==9:
        #    print ("path_to_patch not correctly generated in 4.3")
        #    quit()
        #delay if downloading    
        time.sleep(random.uniform(1,3))
        #response=requests.get(url)
        response=requests.get(url, headers=requests_headers)
        if debug_mode:
            print (response)
            #print (response.content)
        #download it first
        makedirs(dirname("webpagesDir/"+path_branch+"/"+path_to_file), exist_ok=True)
        with open("webpagesDir/"+path_branch+"/"+path_to_file,'wb+') as webpage:
            webpage.write(response.content)
        
        page_soup=BeautifulSoup(response.text,'html.parser')
    return page_soup
#_________________________________________________________get_patch_markers
def get_patch_markers(patch_short_hexsha, patch_soup, commit_link, cve, target_tag, debug_mode):
    markers_dict=dict()
 
    cve_info =dict()
    month_list=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"]

    patch_line_list=[]
    patch_file_list=[]
    if not (type(patch_soup)==type(None)):
        #differentiate if hg or github
        if ("hg." in commit_link):
            #get main table
            commit_main_finder['tag']='class_'
            commit_main_finder['value']="stripes2 diffblocks"
            #identify lines 
            commit_file_finder['tag']='class_'
            commit_file_finder['value']="plusline"
            commit_file_finder['minus']="minusline"
            #find the main section
            patch_main_section = patch_soup.find(class_=commit_main_finder['value'])
            if not (type(None)==type(patch_main_section)):
                print (patch_main_section.text)
                print ("\n"+str(type(patch_main_section.text)))
                patch_lines_list = patch_main_section.text.split('\n')
            else:
                error_list.append("xXx patch_markers| Cannot retrieve patch from 'hg.' soup ")
                print (error_list[-1])
                quit()
        elif ("github" in commit_link):    
            #extract root dir
            commit_split = commit_link.split("/commit/")
            repo_base_url = commit_split[0]
            commit_full_hexsha = commit_split[1]
            project_name = repo_base_url.split("github.com/")[1]
            #add .git
            project_url = repo_base_url+".git"
            #is repo needded?
            path_to_create=""
            makedirs("patch_repos", exist_ok=True)
            patch_repo=None
            for path_part in project_name.split("/"):
                path_to_create+="/"+path_part 
                makedirs("patch_repos/"+path_to_create, exist_ok=True)
            #create repo dir if needed
            try:
                patch_repo = git.Repo("patch_repos/"+path_to_create)
            except:
            #clone repo if needed
                if debug_mode==9:
                    print ("-_-_-_-_-_-_-_-_-_-Cloning repo")
                patch_repo = git.Repo.clone_from( project_url, "./patch_repos/"+path_to_create )
            
            #get repo access
            if not(type(patch_repo) == type(None)):              
                #get access to patch diff
                diff_lines = (patch_repo.git.diff(commit_full_hexsha+"^", commit_full_hexsha))
                #transform patch diff into list of lines
                if (type(diff_lines)==type("String to be splitted")):
                    patch_lines_list = diff_lines.split("\n")
                elif (type(diff_lines)==type(["we have","a diff list"])):
                    patch_lines_list = diff_lines
                #patch_lines_list = patch_main_section.text.split('\n')
        if (len(patch_lines_list)>0):    
            #print (str(patch_lines_list))
            #print (str(type(patch_lines_list)))
            #for patch_line in patch_main_section.text:
            minus_file_name=""
            file_name=""
            previous_line=""
            previous_status=""
            last_normal_line=""
            patch_chunk_counter=0
            chunk_id="chunk_0"
            for patch_line in patch_lines_list:
            ###markers_dict has a structure 
            ##markers_dict {chunk_id: {'minus_lines':[], 'plus_line':[]}
            ## where minus_line [0] == plus_line[0] the line before chunk to give some context
            ## same for last line of each [-1]
            ## and chunk id is the first line of the chunk
            ## --> may not work because: which line [1] shall it be: from plus line or minus_line?
            ##     ou alors les deux (1#minus_line|plus_line)
            ##apres pas derangeant: si je compare libcore_file_line[1] avec patch[chunk][(+|-)_line]
            ## cela marche aussi pour commencer la comparaison...
            ##--> therefore MAY WORK :-)
                if re.search(r"^---", patch_line):
                    if debug_mode==9:
                        print ("patch_line: "+str(patch_line))
                    if not("/dev/null" in patch_line):
                        minus_file_name=(re.search(file_name_regex, patch_line)).group(0)[1:]
                    else:
                        minus_file_name="/dev/null"
                elif re.search(r"^\+\+\+", patch_line):
                    #new_file removing the starting '/'
                    file_name = (re.search(file_name_regex, patch_line)).group(0)[1:]
                    #new entry in markers_dict
                    markers_dict[file_name]=dict()
                    previous_line=patch_line
                    previous_status="newfile"
                    patch_file_list.append(file_name)
                elif previous_status == "normal" or previous_status == "newfile":
                    if re.search(r"^(-|\+)", patch_line):
                    #NF    
                        #new chunki
                        chunk_id ="chunk_"+str(patch_chunk_counter)
                        markers_dict[file_name][chunk_id]= {"plus_lines":[], "minus_lines":[]}
                        patch_chunk_counter+=1
                        if re.search("^-", patch_line):
                        #NFM
                            previous_status="minus"
                            markers_dict[file_name][chunk_id]["minus_lines"].append(last_normal_line)
                            markers_dict[file_name][chunk_id]["minus_lines"].append(patch_line[1:])
                        elif re.search("^\+", patch_line):  
                        #NFP
                            previous_status="plus"
                            markers_dict[file_name][chunk_id]["plus_lines"].append(last_normal_line)
                            markers_dict[file_name][chunk_id]["plus_lines"].append(patch_line[1:])
                        #else:

                         #   print("Not normal behaviour: should match + or - starting char")
                         #   quit()
                    else:
                    #NFN    
                        #nothing to declare -  still uniteresting code
                        last_normal_line=patch_line
                elif previous_status=="minus":
                #M
                    if re.search(r"^-",patch_line):
                    #MM
                        markers_dict[file_name][chunk_id]["minus_lines"].append(patch_line[1:])
                    elif re.search(r"\+", patch_line):     
                    #MP
                        previous_status="plus"
                        #do not forget to add context before
                        markers_dict[file_name][chunk_id]["plus_lines"].append(last_normal_line)
                        markers_dict[file_name][chunk_id]["plus_lines"].append(patch_line[1:])
                    else:
                    #MN
                        #add context to terminating isolated minus chunk
                        markers_dict[file_name][chunk_id]["minus_lines"].append(patch_line)
                        #normal line
                        previous_status="normal"
                        last_normal_line=patch_line
                elif previous_status=="plus":
                #P
                    if re.search(r"^-", patch_line):
                        #SHOULD NOT HAPPEN OR CHUNK RULES BREACHED: - before + and -+- makes no sense: shall be --+
                        print ("chunk rules not respected")
                        quit()
                        #new chunk!
                        chunk_id="chunk_"+str(patch_chunk_counter)
                        patch_chunk_counter+=1
                        markers_dict[file_name][chunk_id]={"plus_lines": [],"minus_lines":[last_normal_line]}
                        previous_status="minus"
                        #tbd error context line
                        #markers_dict[file_name][chunk_id]["minus_lines"].append[previous_line]
                        markers_dict[file_name][chunk_id]["minus_lines"].append[patch_line]
                        previous_line=patch_line
                    elif re.search(r"^\+", patch_line):
                    #PP
                        #
                        markers_dict[file_name][chunk_id]["plus_lines"].append(patch_line[1:])
                        previous_line=patch_line
                    else:
                    #PN
                        #add context to terminating plus chunk
                        markers_dict[file_name][chunk_id]["plus_lines"].append(patch_line) 
                        #if minus_lines then add context line
                        if len(markers_dict[file_name][chunk_id]["minus_lines"])>0:
                            markers_dict[file_name][chunk_id]["minus_lines"].append(patch_line)

                        previous_status="normal"
                        previous_line=patch_line
                        last_normal_line=patch_line
                previous_line=patch_line
            #if debug_mode==9:
            #    print(str(markers_dict))
            save_json(markers_dict, "testDir/patch_markers/"+cve+"_"+str(patch_short_hexsha)+".json")    

            #save for manual analysis
            m_a_folder="testDir/manual_analysis"
            makedirs(m_a_folder, exist_ok=True)
            makedirs(m_a_folder+"/"+str(cve), exist_ok=True)
            # patch_main_section.text
            with open(m_a_folder+"/"+cve+"/diff",'w')as diff_file:
                for diff_line in patch_lines_list:
                    diff_file.write(diff_line+"\n")
            ####full patched file
            #get the href in the soup page
            if ("hg." in commit_link):
                patched_changeset_soup=patch_soup.find(id="changesetEntry")
                #print (patched_changeset_soup.text)
                #get the date of the patch
                date_soup = patched_changeset_soup.find(class_="date age")
                if not(type(date_soup) ==type(None)):
                    date_string = date_soup.text
                    date_dmy = re.search(r"[0-9]{1,2}\s[a-zA-Z]{3}\s[0-9]{4}", date_string).group(0)
                    date_month_string=re.search(r"[A-Za-z]+",date_dmy).group(0)
                    date_month_index = month_list.index(date_month_string)+1
                    date_year=re.search(r"\s[0-9]{4}$", date_dmy).group(0)[1:]
                    date_day=re.search(r"^[0-9]{2}", date_dmy).group(0)
                    cve_info[cve]={target_tag: str(date_year)+"_"+str(date_month_index)+"_"+str(date_day)}
                    

                root_url="https://"+re.search(r"hg\.[^/]+", commit_link).group(0)

                files_list_soup=patched_changeset_soup.find_all(class_="files")[1]
                url_list_soup=files_list_soup.find_all('a', href=True)
                for url_soup in url_list_soup:
                    patched_file_url=(root_url+"/"+url_soup['href'])
                    print (str(patched_file_url))
                    patched_file_soup=get_parsed_soup_from_url(patched_file_url, firefox_headers, debug_mode)
                    patched_file_text=patched_file_soup.find(class_="sourcelines stripes4 wrap").text
                    file_name_split = url_soup['href'].split("/")
                    file_endpath= file_name_split[-2]+"_"+file_name_split[-1]
                    makedirs(m_a_folder+"/"+cve+"/patched", exist_ok=True)
                    with open(m_a_folder+"/"+cve+"/patched/"+str(file_endpath), 'w') as patched_file_output:
                        for patched_file_line in patched_file_text.split("\n"):
                            patched_file_output.write(patched_file_line+"\n")
            elif "github" in commit_link:
                #error_list.append("xXx 4.3|  Github not supported for getting diif in manual analysis: "+str(cve))
                #we need to save the diff, the unpatched file and the patched file in testDir/manual_analysis
                #patch repo is normaly set
                #diff text is in patch_lines_list
                # commit is in commit_full_hexsha
                #list of files is in patch_file_list

                #folder_path
                m_a_folder = "./testDir/manual_analysis"
                makedirs(m_a_folder, exist_ok=True)
                cve_ma_folder= m_a_folder+"/"+cve
                makedirs(cve_ma_folder, exist_ok=True)
                #write diff
                with open(m_a_folder+"/"+cve+"/diff", 'w') as diff_file:
                    for d_line in patch_lines_list:
                        diff_file.write(d_line+"\n")
                #write patched files
                makedirs(m_a_folder+"/"+cve+"/patched/",exist_ok=True)
                for patched_file_path in patch_file_list:
                    patched_file_name=(("/"+patched_file_path).split("/"))[-1]
                    try:
                        patched_file = patch_repo.git.show(commit_full_hexsha+":"+patched_file_name)
                    except:
                        patched_file = ""
                    with open(m_a_folder+"/"+cve+"/patched/"+patched_file_name, 'w') as p_f:
                        p_f.write(patched_file)
                #write files unpatched version
                makedirs(m_a_folder+"/"+cve+"/unpatched/",exist_ok=True)
                for patched_file_path in patch_file_list:
                    unpatched_file_name=(("/"+patched_file_path).split("/"))[-1]
                    try:
                        unpatched_file = patch_repo.git.show(commit_full_hexsha+":"+patched_file_name)
                    except:
                        unpatched_file = ""
                    with open(m_a_folder+"/"+cve+"/unpatched/"+unpatched_file_name, 'w') as up_f:
                        up_f.write(unpatched_file)
                #save _date of patch in cve_info={cve: YYYY_MM_DD}
                #commit is 
                patch_date = patch_repo.git.show("--no-patch","--no-notes","--date=format:'%Y_%m_%d'","--format=%cI" , commit_full_hexsha)
                patch_date = (str(patch_date).split("T"))[0].replace("-","_")
                cve_info[cve]={target_tag: patch_date}
                print ("github_date: "+(str(patch_date).split("T"))[0].replace("-","_"))
                
            else:
                error_list.append("xXx 4.3| Not supported commit_link: "+str(commit_link)+" for "+str(CVE))
                
        else:
            error_list.append ("xXx 4.3| Anormal Behaviour: parsing cannot access main in previously accessed from this method in 4.1: "+commit_link)
            print(error_list[-1])
            quit()     
    else:
        markers_dict=0

    update_json(cve_info, "outputDir/4_3_upstream_patch_date.json")

    return markers_dict
#_________________________________________________________verify_sha_modifies_target_file
def verify_sha_modifies_target_file(target_repo, sha, target_file, debug_mode):
    modified_target=0

    files_modified_by_sha = target_repo.git.show("--pretty=", "--name-only", sha)
    if debug_mode==9:
        print ("-------new_sha tested "+str(sha)+"|for|"+str(target_file))
        #print (str(files_modified_by_sha))
    files_modified_by_sha_list=files_modified_by_sha.split('\n')    
    for file_modified in files_modified_by_sha_list:    
        #if debug_mode == 9:
        #    print ("Compare|"+file_modified+"|vs|"+target_file+"|")
        if target_file in file_modified:
            modified_target+= 1
    return modified_target
#_________________________________________________________clean_sha_list
def clean_sha_list(target_repo, sha_list, error_list, debug_mode):
    #remove commits before the original introduction of openJDK in android
    #not maintained by google
    #and if patched already, then score is not interesting: should stay positive
    to_remove_list=[]
    for sha_ut in sha_list:
        #get the year
        commit_year=int((target_repo.git.show("-s", "--format=%ci",sha_ut))[0:4])
        if debug_mode==9:
            print ("Commit_year: "+str(commit_year))
        if commit_year < 2015:
            to_remove_list.append(sha_ut)
    if debug_mode ==9:
        print("\t-->removing: "+str(len(to_remove_list)))
    for t_r_sha in to_remove_list:
        sha_list.remove(t_r_sha)
    return sha_list    
#_________________________________________________________list_commits_changing_file
def list_commit_changing_file(target_repo, target_file_path):
    sha_list=[]
    hex_sha_line_regex=re.compile(r"^commit\s+[a-f0-9]+")

    all_commits_text=target_repo.git.log( target_file_path, follow=True)
    for commit_text_line in all_commits_text.split('\n'):
        if re.search(hex_sha_line_regex, commit_text_line):
            hex_sha=(re.search("[0-9a-f]+$", commit_text_line)).group(0)
            sha_list.append(hex_sha)
    sha_list=clean_sha_list(target_repo, sha_list, error_list, debug_mode)        
    if debug_mode==9:
        print ("Nb sha to test: "+str(len(sha_list)))
    return sha_list
#_________________________________________________________git_diff_old_path
def git_diff_old_path(target_repo, target_path, sha_ut,summary_status, error_list, debug_mode):
    file_lines=[]
    init_loop=1
    splitted_target_path=target_path.split('/')
    if debug_mode==9:
        print(str(splitted_target_path))
    x_subfolders=0
    x_part_path=""
    while init_loop==1 or (len(file_lines)>1):
        file_lines=[]
        init_loop=0
        x_subfolders-=1
        x_part_path=splitted_target_path[-1]
        for path_part_id in range(x_subfolders,-1):
             x_part_path=splitted_target_path[path_part_id]+"/"+x_part_path
        if debug_mode==9:
             print("\t| grep "+str(x_part_path))
        if summary_status:     
            commit_summary=target_repo.git.diff("--summary", str(sha_ut)+"^", sha_ut)
        else:
            commit_summary=target_repo.git.diff( str(sha_ut)+"^", sha_ut)
            
        for commit_summary_line in commit_summary.split('\n'):
            #if debug_mode==9:
            #    print (commit_summary_line)
            if x_part_path in commit_summary_line:
                file_lines.append(commit_summary_line)
                if debug_mode:
                    print ("Adding \t"+str(commit_summary_line))
        if not (summary_status):
            old_pathes_found=[]
            for f_line in file_lines:
                if re.search(r"^--- a/([a-zA-Z0-9]+/)+[\S]+", f_line):
                    found_path=(re.search(r"a/([\S]+/)+[\S]+", f_line).group(0))[2:]
                    if not(found_path in  old_pathes_found):
                        old_pathes_found.append(found_path)
            file_lines=old_pathes_found
            if debug_mode==9:
                print(file_lines)
        if debug_mode==9:
            print (str(file_lines))
            print ("Size of lines selectes: "+str(len(file_lines)))
    #if len ==0 pb!!!!!
    return file_lines
#_________________________________________________________get_old_path_before_sha
def get_old_path_before_rename(target_repo, target_path, sha_ut, error_list, debug_mode):
    if debug_mode==9:
        print ("-->get_old_path_before_rename")
    file_lines = []

    file_lines=git_diff_old_path(target_repo, target_path, sha_ut, 1, error_list, debug_mode)

    if len(file_lines)==0:
        #second chance by removing --summary
        file_lines=git_diff_old_path(target_repo, target_path, sha_ut, 0, error_list, debug_mode)

        if len(file_lines)==0:
            target_path_split=target_path.split("/")
            target_end ="/"+target_path_split[-1]
            #does file exist in this version?
            test_existence = target_repo.git.diff(sha_ut+"^", sha_ut)
            if not (target_end in test_existence):
                former_path="--absent--"
            else:
                error_list.append("xXx 4.3 Too strict path for former name| Not possible to retrieve")
                save_errors(error_list)
                print (str(error_list[-1]))
                quit()
        else:
            print (file_lines)
            former_path=file_lines[0]
            #quit() #debug quit
    else:# len==1 continue
        if debug_mode==9:
            print(str(file_lines))
        former_name_line_list=file_lines[0].split(" ")    
    #clean
    
        former_path=former_name_line_list[-1]
        #quit()  #debug_quit   
    #else:
    #    error_list.append("xXx 4.3 Could not find old path before rename")
    #    save_errors(error_list)
    #    quit()

    return former_path    
#_________________________________________________________remove_deleting_commits
def remove_deleting_commits(target_repo, sha_dicts_list, error_list,debug_mode):
    #sha_dict_list has the shape {"sha":,"file_path":}
    removing_sha_list=[]
    
    for sha_position,sha_dict in enumerate(sha_dicts_list):
        # sha_dict is {"sha":,"file_path":}
        if debug_mode==9:
            print ("-->Wondering is "+sha_dict["sha"]+" removes "+sha_dict["file_path"])
        #issue arises if file has a different name as previous version, no problem
        if (sha_position+1)<len(sha_dicts_list):
            #can only check if there is an older version
            if not(sha_dicts_list[sha_position]["file_path"]==sha_dicts_list[sha_position+1]["file_path"]):
                #check with the name of the next one
                if debug_mode==9:
                    print("---->Does "+str(sha_dict["sha"])+" remove "+str(sha_dicts_list[sha_position+1]["file_path"])+" ?")
                #getting the lines of that commit? and looking for the file?
                diff_lines =((target_repo.git.diff(sha_dict["sha"]+"^", sha_dict["sha"],"--", sha_dicts_list[sha_position+1]["file_path"] ))).split("\n")
                print (diff_lines[4])

                if len(diff_lines)>0:
                    diff_line_counter=0
                    while (diff_line_counter< len(diff_lines)) and not( re.search(r"^--- a", diff_lines[diff_line_counter])):
                        diff_line_counter+=1
                    if diff_line_counter+1< len(diff_lines):
                        if "/dev/null" in diff_lines[diff_line_counter+1]:
                            removing_sha_list.append(sha_position)
        #else:
            #still check for removal
            #if file does not exist, then it has just been removed
    
    if debug_mode==9:
        print ("removing"+" in positions:"+str(removing_sha_list))
    for sha_position in reversed(removing_sha_list):
        del sha_dicts_list[sha_position]

    quit()
    return
#_________________________________________________________is_file_deleted
def is_file_deleted(t_repo, file_path, sha, error_list, debug_mode):
    if debug_mode==9:
        print ("--is_file_deleted")
    deleted_status=0

    former_file_name=get_old_path_before_rename(t_repo, file_path, sha, error_list, debug_mode)

    lines = (t_repo.git.diff(sha+"^", sha, "--", former_file_name))
    if re.search(r"\+\+\+ /dev/null", lines):
        deleted_status=1

    return deleted_status
#_________________________________________________________get_file_history
def get_sha_list_modifying_file(target_repo, target_file_path,error_list, debug_mode):
    if debug_mode==9:
        print ("--get_sha_list_modifying_file")
    sha_counter=0
    sha_dicts_list=list()
    turning_target_file_path = target_file_path
    file_renaming_status=0
    newest_target_file_path=target_file_path#
    sha_list=list_commit_changing_file(target_repo, turning_target_file_path)
    if debug_mode==9:
        print ("Hexsha_list:\t"+str(sha_list))
    #verify that all commits modify the file
    for sha_under_test in sha_list:
        verification_status = verify_sha_modifies_target_file(target_repo,sha_under_test, turning_target_file_path, debug_mode)
        sha_counter+=1
        if debug_mode==9:
            print ("sha "+str(sha_counter)+"/"+str(len(sha_list))+"\tVerif_status: "+str(verification_status))
        if verification_status==0:
            ##retry taking the renaming into account for sha_under_test
            #get old new-----------------------------------
            file_renaming_status=1
            newest_target_file_path=turning_target_file_path
            turning_target_file_path=get_old_path_before_rename(target_repo, turning_target_file_path, sha_under_test, error_list, debug_mode)
            if not(turning_target_file_path=="--absent--"):
                #retry for old_name
                verification_status=verify_sha_modifies_target_file(target_repo, sha_under_test, turning_target_file_path, debug_mode)
                if verification_status==0:
                    print ("xXx 4.3 Fatal Error: Commit tested does not modify the file")
                    error_list.append("xXx File:"+str(turning_target_file_path)+"|not in sha:|"+str(sha_under_test))
                    save_errors(error_list)
                    quit()
            else:
                verification_status=2
        elif verification_status >1:
            error_list.append("xXx 4.3 Fatal Error: file appears more than once in commit")
            save_errors(error_list)
            print (error_list[-1])
            quit()
        if verification_status == 0:
            error_list.append("xXx 4.3 File history: should have matched with either this name or former one")
            print (error_list[-1])
            save_errors(error_list)
            quit()
        elif(verification_status==1) :
            if file_renaming_status:
                #after a renaming: the file holds the newest name
                sha_dicts_list.append({"sha":sha_under_test, "file_path":newest_target_file_path})
                file_renaming_status=0
            else:
                sha_dicts_list.append({"sha":sha_under_test, "file_path":turning_target_file_path})
        elif verification_status==2:
            error_list.append("File does not exist in sha returned by list_commit_changing_file because absent before and after")

    #All_sha returned have been verified to modify the target file
#------------------------------------------------------------------------- 
    #Now we need to verify it is not deleting the file: corner case
    #sha_dicts_list = remove_deleting_commits(target_repo, sha_dicts_list, error_list, debug_mode)   
#-------------------------------------------------------------------------
    if debug_mode==9:
        print ("RETURNING: "+str(len(sha_dicts_list)))
        for sha in sha_dicts_list:
            print (sha["sha"]+"\t|"+sha["file_path"])
    #quit()        

    return sha_dicts_list
#_________________________________________________________get_file_lines
def get_file_lines(target_repo, sha_dict, cve_key, error_list, debug_mode):
    if debug_mode==9:
        print ("--get_file_lines")
    file_lines=[]
    #return the lines ifthe file exists after the commit
    #return empty ["rm","rm","rm"] otherwise and score stays 0
    if debug_mode==9:
        print ("Searching for lines of:" +str(sha_dict))
    try:
        file_text=target_repo.git.show(sha_dict["sha"]+":"+sha_dict["file_path"])
    except:
        if is_file_deleted(target_repo, sha_dict['file_path'], sha_dict['sha'], error_list, debug_mode):
            #empty file after commit: no line, score is zero
            file_text="rm\nrm\nrm"
        else:
            print ("Searching for lines of:" +str(sha_dict))
            former_path=get_old_path_before_rename(target_repo, sha_dict["file_path"], sha_dict["sha"], error_list, debug_mode)
            try:
                file_text=target_repo.git.show(sha_dict["sha"]+":"+former_path)
            except:
                error_list.append("xXx 4.3 get file lines: back up method with former name doesnt work")
                print (error_list[-1])
                save_errors(error_list)
                quit()
    date_tag =  (target_repo.git.show("--no-patch","--no-notes","--pretty='%cI'",sha_dict["sha"]) ).split("T")[0][1:]
    print (date_tag)        
    

    file_lines=file_text.split('\n')

    if debug_mode==9:
        print ("returning "+str(len(file_lines))+" lines")
        makedirs("testDir/filesDir", exist_ok=True)
        with open("testDir/filesDir/"+str(sha_dict["sha"])+"_"+(sha_dict["file_path"].split('/'))[-1],'w') as test_file:
            test_file.write(file_text)
    makedirs("testDir/manual_analysis/"+str(cve_key)+"/files_versions",exist_ok=True)
    file_to_path = "testDir/manual_analysis/"+str(cve_key)+"/files_versions"
    target_name_split=sha_dict["file_path"].split("/")
    target_name=target_name_split[-2]+"_"+target_name_split[-1]
    with open(file_to_path+"/"+date_tag+"_"+sha_dict["sha"]+"_"+target_name, 'w') as file_version_output:
        file_version_output.write(file_text)

    return file_lines
#_________________________________________________________get_libcore_v_file_lines
def get_libcore_v_file_lines(target_repo, cve_key, libcore_tag, file_path, error_list, debug_mode):
    short_libcore_tag=""
    if "android" in libcore_tag:
        short_libcore_tag=((libcore_tag.split("."))[0].split("tags/"))[1]
    else:
        #normally only master
        short_libcore_tag = libcore_tag 
    
    #get the commit fot the tag
    commit_hexsha = target_repo.git.log(libcore_tag,"-1","--pretty=%H")

    #get the file version for this libcore commit
    try:
        file_text = target_repo.git.show(commit_hexsha+":"+file_path)
    except:
        #check for old _name
        former_path=get_old_path_before_rename(target_repo, sha_dict["file_path"], sha_dict["sha"], error_list, debug_mode)
        try:
            file_text = target_repo.git.show(commit_hexsha+":"+former_path)
        except:    
        #if still does not exist then 
            file_text = ""


    makedirs("testDir/manual_analysis/"+str(cve_key)+"/libcore_versions",exist_ok=True)
    makedirs("testDir/manual_analysis/"+str(cve_key)+"/libcore_versions/"+short_libcore_tag,exist_ok=True)
    file_to_path = "testDir/manual_analysis/"+str(cve_key)+"/libcore_versions/"+short_libcore_tag
    target_name_split=file_path.split("/")
    target_name=target_name_split[-2]+"_"+target_name_split[-1]
    with open(file_to_path+"/"+target_name, 'w') as file_version_output:
        file_version_output.write(file_text)


    return
#_________________________________________________________line_is_comment_line
def is_comment_line(line):
    comment_line_status=False

    if re.search(r"^(//|/\*|\*|\*/)", line.lstrip()):
        comment_line_status=True
    return comment_line_status    

#_________________________________________________________chunk_comparison
def chunk_comparison(file_name,commit_hash,target_lines, match_line_nb_in_target, chunk_id, patch_chunk_dict, original_match_status, erro_list, debug_mode):
    match_status=original_match_status
    
    no_match_list=[]


    if debug_mode==9:
        print("--\t--\t--\t--\t--\n\t\tStarting_chunk_comparison")

    debug_lines=["\tComparing:"]
    debug_lines.append("---------Patch Chunk Lines-----------------")
    for dl_line,patch_chunk_line in enumerate(patch_chunk_dict[original_match_status]):
        debug_lines.append(str(dl_line)+patch_chunk_line)
    debug_lines.append("-------------------------------------------")
    debug_lines.append("\tis "+str(original_match_status)+"  with")
    debug_lines.append("----------------Target Code--------------------")
    shortest_to_end=min( len(patch_chunk_dict[original_match_status])+(match_line_nb_in_target-1)  , len(target_lines) )
    for nb_line in range(match_line_nb_in_target-1, shortest_to_end): #match_line_nb_in_target-1+len(patch_chunk_dict[original_match_status])):
        dl_line=nb_line-(match_line_nb_in_target-1)
        debug_lines.append(str(dl_line)+target_lines[nb_line])
    debug_lines.append("---------------------------------------------")    

    
    for chunk_line_number, chunk_line in enumerate(patch_chunk_dict[match_status]):
        #compare given the enumeration
        #match is on repo_file_lines[target_code_line_number] and chunk_dict[match_status][1]
        if (match_line_nb_in_target-1+chunk_line_number) < shortest_to_end:
        
            #TBD: no comparison needed if both are a comment line
            if not (is_comment_line(chunk_line)) and not ( is_comment_line(target_lines[match_line_nb_in_target-1+chunk_line_number])):
                if target_lines[match_line_nb_in_target-1+chunk_line_number].lstrip().rstrip()   == chunk_line.lstrip().rstrip():
                    #first test on matchingline_in_targetcode-1 with chunk_line[0]
                    #then should match
                    
                    #as long as it matches then
                    match_status=match_status
                else:
                    #does not look like the chunk anymore
                    match_status="None"
                    if debug_mode==9:
                        no_match_list.append(str(chunk_line_number))
                        #print ("Stopped matching on line: "+str(chunk_line_number))
                    if ((chunk_line_number==1)):
                        error_list.append ("xXx 4.3: error shall match on line 1 as entrance condition")
                        save_errors(error_list)
                        print (error_list[-1])
                        quit()
            else:
                #business as usual, no need to compare comment_lines
                match_status=match_status

    debug_lines.append("Differences:\t"+str(no_match_list))
    debug_lines.append("--\t--\t--\t--\t--")
    debug_lines.append("Match Status: "+str(match_status) )
    debug_lines.append("")
    debug_lines.append("--------------for ref---------------------")
    if original_match_status=="plus_lines":
        ref_match_status="minus_lines"
    else:
        ref_match_status="plus_lines"
    for nb_line,patch_chunk_line in enumerate(patch_chunk_dict[ref_match_status]):
        debug_lines.append(str(nb_line)+patch_chunk_line)

    debug_lines.append("--------------------------------------------")
    if debug_mode==9:
        makedirs("testDir/chunk_comparison/", exist_ok=True)
        with open("testDir/chunk_comparison/"+str(file_name)+"_"+str(commit_hash)+"_"+original_match_status+"_"+str(chunk_id),'w')as test_file:
            for d_l in debug_lines:
                test_file.write(d_l+"\n")
        if match_status=="None":
            print ("Lines "+str(no_match_list)+" are different")
    return match_status
#_________________________________________________________compute_commit_score
def compute_commit_score(file_name, commit_hash, repo_file_lines, patch_markers_dict_of_file, debug_mode):
    score=0
    #repo_file_lines is a list()
    #patch_markers_dict_of_file=patch_markers_dict[target_file] is a dict of {chunk_id}:{"minus_lines":[lines],"plus_lines":[lines]}} 
    #each "plus_lines" and "minus_lines" has one line of context before, and one line of context after

    #the goal is, for each chunk, decide if the unpatched version(minus_lines) or patched version("plus line") is in the code
    #we compare the lines with the elements in position [1] of each to avoid the noise
    #and then if a march, we compare the full chunk
    # if looks like minus, then add -1 to score, if looks like plus add 1, if none, idle
    nb_tot_minus_chunk = 0
    nb_pres_minus_chunk = 0
    nb_tot_plus_chunk = 0
    nb_pres_plus_chunk = 0
    count_chunks = 1

    if debug_mode==9:
        print ("--\t--\t--")
        print ("Starting to compute the commit score")
    

    for target_code_line_number,target_code_line in enumerate(repo_file_lines):
        if target_code_line_number>=1:
            for chunk_id in patch_markers_dict_of_file.keys():
                #if debug_mode==9:
                #    print("\t----"+str(chunk_id))
                chunk_dict=patch_markers_dict_of_file[chunk_id]
                match_status = []
                #count if minus chunks, plus chunk or both chunk
                if len(chunk_dict["minus_lines"])>0:
                    if count_chunks:
                        nb_tot_minus_chunk+=1

                    #need to clean the lines ? rstrip or lstrip ?
                    if target_code_line == chunk_dict["minus_lines"][1]:
                        #now  compare from 0 thanks to the enumeration for the full chunk
                        if debug_mode==9:
                            print (str(target_code_line)+"\n\t-matched with-\n"+chunk_dict["minus_lines"][1])
                        match_status.append("minus_lines")   
                
                if len(chunk_dict["plus_lines"])>0:
                    if count_chunks:
                        nb_tot_plus_chunk+=1
                    
                    if target_code_line == chunk_dict["plus_lines"][1]:
                        #now compare from 0 thanks the enumar
                        match_status.append("plus_lines")
                        if debug_mode==9:
                            print (str(target_code_line)+"\n\t+matched with+\n"+chunk_dict["plus_lines"][1])
                #join the two above int one pocedure with a parameter either "plus_lines" or "minus_lines"    
                for match_st in match_status:
                    if debug_mode==9:
                        print("------------"+str(chunk_id))

                    match_confirmation=chunk_comparison(file_name, commit_hash, repo_file_lines, target_code_line_number, chunk_id, chunk_dict, match_st, error_list, debug_mode)
                    if match_confirmation=="plus_lines":
                        nb_pres_plus_chunk += 1
                        score+=1
                    elif match_confirmation=="minus_lines":
                        nb_pres_minus_chunk +=1
                        score-=1

                    if debug_mode==9:
                        print ("Chunk analysis status:\t"+str(match_status))
                        print ("--------------------")
            count_chunks=0

    #In case abnormal        
    if score==0 and len(repo_file_lines)==0:
        error_list.append("xXx 4.3 | Issue: no lines provided as target_lines for "+str(file_name)+" in "+str(commit_hash))
        save_errors(error_list)
        print(error_list[-1])
        quit()
    if debug_mode==9:
        print("Target code score: "+str(score)+" over range|-"+str(nb_tot_minus_chunk)+" -> "+str(nb_tot_plus_chunk)+"|")
    
    


    return [score, -nb_pres_minus_chunk, -nb_tot_minus_chunk, nb_pres_plus_chunk, nb_tot_plus_chunk]
#_________________________________________________________sha_score_dict
def is_target_patched(cve_key, libcore_tag, upstream_program_version,patch_short_hexa, target_file, sha_score_dict, error_list, debug_mode):
    #sha score dict "sha": score
    #!!!!! score can be == "rm" if commit removes file
    detection_status=""
    detected=0
    #decision_trace
    d_t=[]
    d_t.append("CVE: "+str(cve_key))
    d_t.append("__________________________")

    working_list=[]
    sha_list=[]
    score_list=[]
    nb_pres_m_chunk=[]
    nb_pres_p_chunk=[]
    diff_list=[]
  
    nb_minus_chunks = 0
    nb_plus_chunks = 0

    #reversed as the patches are list by git show from youngest to oldest
    sha_list=(list(sha_score_dict.keys()))
    d_t.append("list of sha:\n"+str(sha_list))
    for commit_sha in sha_score_dict.keys():
      score_list.append(sha_score_dict[commit_sha][0])
      nb_pres_m_chunk.append( int( sha_score_dict[commit_sha][1] ) )
      nb_minus_chunks = ( int(sha_score_dict[commit_sha][2])) #ok because in the openjdk patch so always the same
      nb_pres_p_chunk.append( int(  sha_score_dict[commit_sha][3]) )
      nb_plus_chunks = (int(sha_score_dict[commit_sha][4])) #ok because in the openjdk patch so always the same


    #-------------------Rule 1----------------------------------
    #Rule1|Tight rule| if the minimum is followed by a max then max has patch
    r1_min_score=score_list[0]
    r1_min_sha=sha_list[0]
    r1_max_score=score_list[0]
    r1_max_sha=sha_list[0]
    r1_min_sha_nb=0
    r1_max_sha_nb=0
    #revesed as git show returned from youngest to oldest
    for sha_nb, sha in enumerate(reversed(sha_list)):
        d_t.append("sha:"+str(sha)+" | score:"+str(score_list[sha_nb]))
        if not (score_list[sha_nb]=="rm"):
            
            if score_list[sha_nb] <= r1_min_score:
                r1_min_sha=sha_list[sha_nb]
                r1_min_score=score_list[sha_nb]
                r1_min_sha_nb=sha_nb
            if score_list[sha_nb] > r1_max_score:
                r1_max_sha=sha_list[sha_nb]
                r1_max_score=score_list[sha_nb]
                r1_max_sha_nb=sha_nb
        else:
             d_t.append("\t seen removed")
    
    
    d_t.append("min is: "+str(r1_min_score))
    d_t.append("max is: "+str(r1_max_score))
    max_to_min_distance=(r1_max_sha_nb - r1_min_sha_nb)
    d_t.append("distance is: "+str(max_to_min_distance))
    # - Change of value from a file that has mostly unpatched to file that has mostly patched lines
    #can be addapted as there might not have has much negative_chunk as positive_chunk
    if r1_max_score >0 and r1_min_score<0:
        d_t.append("Rule1:condition fulfilled for patch detection")
        #if max followa min then detected
        if max_to_min_distance==1:
          d_t.append("Rule 1.a")  
          #then patch is applied at max_score
          detection_status=r1_max_sha
          detected=1
        elif max_to_min_distance==2 and score_list[r1_max_max_nb-1]=="rm":
            #then the distance is just because the file was removed
            detectio_status=r1_max_sha
            d_t.append("Rule 1.b")
            detected=1
    if r1_max_score == r1_min_score:
        if r1_min_score > 0:
            detection_status="always_patched"
        elif r1_max_score < 0 :
            detection_status="never_patched"
            detected=1
        d_t.append("Rule 1.c: |"+str( detection_status)+"|" )
    #.................ending rule 1...............................    
    

    #......................Rule 2.......................
    ### including min and max
    if not detected:
        if debug_mode==9:
            print (".....................Testing Rule 2")
        # no removal of chunk in patch, and added lines never present in code
        #case of CVE-2021-3561
        if debug_mode==9:
            d_t.append("nb_minus_chunks "+str(type(nb_minus_chunks))+" _ "+str(nb_minus_chunks)+" |Max score: "+str(r1_max_score))
            print (d_t[-1])
        if (nb_minus_chunks == 0 and r1_max_score==0):
            detection_status = "never_patched"
            detected=1
            d_t.append("Rule 2.a: |No plus minus chunk in patch and plus_lines absent from libcore|" )
            print (d_t[-1])

        #patching takes several implementation
        #case of CVE-2022-21283
        if r1_min_score == nb_minus_chunks and r1_max_score == nb_plus_chunks and max_to_min_distance>0:
            detected=1
            detection_status = r1_max_sha
            d_t.append("Rule 2.b: |A state with all unpatched chunks precedes a states with all patch chunks, hence the patch has been applied on a transition disatnce bigger than 1 as rule 1 states|" )
        
        #if no plus_chunk but r1_max==0 then: patched
    #...................ending rule 2..........................

    
    #......................Rule 3........................
    # counting the number of patch present, negatively and positively in columns 1 and 3 of result dict
    if not detected:
        d_t.append(".....................Testing Rule 3")
        if debug_mode==9:
            print(d_t[-1])

        max_nb_pres_p_chunk = nb_pres_p_chunk[0]
        max_nb_pres_m_chunk = nb_pres_m_chunk[0]
        min_nb_pres_p_chunk = nb_pres_p_chunk[0]
        min_nb_pres_m_chunk = nb_pres_m_chunk[0]

        max_p_chunk_sha = sha_list[0]
        max_m_chunk_sha = sha_list[0]
        min_p_chunk_sha = sha_list[0]
        min_m_chunk_sha = sha_list[0]


        for elt_index in reversed(range(0, len(nb_pres_p_chunk))):
            if nb_pres_p_chunk[elt_index] > max_nb_pres_p_chunk:
                max_nb_pres_p_chunk = nb_pres_p_chunk[elt_index]
                max_p_chunk_sha = sha_list[elt_index]
            elif  nb_pres_p_chunk[elt_index] < min_nb_pres_p_chunk:
                min_nb_pres_p_chunk = nb_pres_p_chunk[elt_index]
                min_p_chunk_sha = sha_list[elt_index]

            if nb_pres_m_chunk[elt_index] < max_nb_pres_m_chunk:
                max_nb_pres_m_chunk = nb_pres_m_chunk[elt_index]
                max_m_chunk_sha = sha_list[elt_index]
            elif nb_pres_m_chunk[elt_index] > min_nb_pres_m_chunk:
                min_nb_pres_m_chunk = nb_pres_m_chunk[elt_index]
                min_m_chunk_sha = sha_list[elt_index]

        if max_nb_pres_m_chunk  == 0 and max_nb_pres_p_chunk == 0 and nb_plus_chunks >0:
            detected = 1
            detection_status = "never_patched"
            d_t.append("Rule 3.a: |No patching chunk is ever detected")
            
        if max_nb_pres_m_chunk < 0 and min_nb_pres_m_chunk == 0  and nb_plus_chunks == 0:
            detected = 1
            detection_status = max_p_chunk_sha 
            d_t.append("Rule 3.b: |All affected chunks have been removed")

    #..................ending Rule 3..............................

    #.................saving the decision process in testDir........
    makedirs("testDir/decision_trace", exist_ok=True)
    file_id=str(cve_key)+"_"+str(libcore_tag).replace("/","_")+"_"+str(upstream_program_version)+"_"+str(patch_short_hexa)+"_"+str(target_file).replace("/","_")
    with open("testDir/decision_trace/"+file_id,'w') as decision_trace_file:
        for decision_line in d_t:
            decision_trace_file.write(decision_line+"\n")


    # patch sha | always_patched | never_patched
    return detection_status    
#_________________________________________________________run_tests
def run_test_suite(cve_to_when_patched_files_dict, test_suite_dict, error_list, debug_mode):
    result_lines=[]

    #cve_to_when_patched_files_dict
    #[cve][libcore_tag][upstreamp_rogram_version][hash][file]

    #test_suite_dict
    #if unpatched: [cve][file]
    #if master_patched: [cve][file]["short_sha":,"full_sha": ]

    patched_test_success_counter=0
    patched_test_counter=0
    master_patched_error_test_list=[]
    #master patched
    for cve_to_test_patched in test_suite_dict["master_patched"]:
        print ("CVE: "+str(cve_to_test_patched))
        cve_in_result=cve_to_when_patched_files_dict[cve_to_test_patched]["master"]
        for cve_upstream_version_res_key in cve_in_result.keys():
            print ("Upstream version key: "+str((cve_upstream_version_res_key) ))
            for cve_upstream_patch_res_key in cve_in_result[cve_upstream_version_res_key].keys():
                print ("Upstream Patch sha: "+str(cve_upstream_patch_res_key))
                cve_upstream_patch_dict = cve_in_result[cve_upstream_version_res_key][cve_upstream_patch_res_key]
                for tested_file in cve_upstream_patch_dict.keys():
                    print ("Testing path:"+str(tested_file))
                    file_name=tested_file.split("/")[-1].split('.')[0]
                    cve_tested_file_patch_result= cve_upstream_patch_dict[tested_file]
                    print("\t"+str(cve_tested_file_patch_result))
                    patched_test_counter+=1
                    if cve_tested_file_patch_result == "always_patched" or (not ("{" in cve_tested_file_patch_result) and not( cve_tested_file_patch_result == "never_patched")):
                        #shall be provided with a sha
                        if debug_mode==9:
                            print ("Expected sha or always patched:\t"+str(test_suite_dict["master_patched"][cve_to_test_patched]) )
                        
                        patched_test_success_counter+=1
                    else:
                        master_patched_error_test_list.append(cve_to_test_patched)
    

    #Never patched
    unpatched_test_success_counter=0
    unpatched_test_counter=0
    error_unpatched_list=[]
    for cve_to_test_unpatched in test_suite_dict["master_unpatched"]:
        print ("CVE: "+str(cve_to_test_unpatched))
        if cve_to_test_unpatched in cve_to_when_patched_files_dict.keys():
            cve_in_result=cve_to_when_patched_files_dict[cve_to_test_unpatched]["master"]
            for cve_upstream_version_res_key in cve_in_result.keys():
                print ("Upstream version key: "+str((cve_upstream_version_res_key) ))
                for cve_upstream_patch_res_key in cve_in_result[cve_upstream_version_res_key].keys():
                    print ("Upstream Patch sha: "+str(cve_upstream_patch_res_key))
                    cve_upstream_patch_dict = cve_in_result[cve_upstream_version_res_key][cve_upstream_patch_res_key]
                    for tested_file in cve_upstream_patch_dict.keys():
                        print ("Testing path:"+str(tested_file))
                        file_name=tested_file.split("/")[-1].split('.')[0]
                        cve_tested_file_patch_result= cve_upstream_patch_dict[tested_file]
                        print("\t"+str(cve_tested_file_patch_result))
                        unpatched_test_counter+=1
                        if cve_tested_file_patch_result == "never_patched":
                           unpatched_test_success_counter+=1
                        else:
                            error_unpatched_list.append(cve_to_test_unpatched)
        else:
            error_unpatched_list.append(cve_to_test_unpatched)

           
                  

    print ("##########TEST SUITE#########################")
    print ("MASTER_PATCHED:\t"+str(patched_test_success_counter)+"  /  "+str(patched_test_counter))
    print ("Error_list:"+str(master_patched_error_test_list))
    print ("-------------------------------------")
    print ("MASTER_UNPATCHED:\t"+str(unpatched_test_success_counter)+"  /  "+str(unpatched_test_counter))
    print ("Unpatched_error_list:"+str(error_unpatched_list))

    makedirs("testDir/testSuiteResultsDir", exist_ok=True)
    time_now=datetime.now()
    date_tag=time_now.strftime("%Y%m%d_%H%M")
    with open("testDir/testSuiteResultsDir/test_"+str(date_tag),'w') as res_file:
        for res_line in result_lines:
            res_file.write(res_line)
#_________________________________________________________get_commitYMD_date
def get_commit_ymd_date(target_repo, patch_full_hexsha, error_list, debug_mode):
    date = ""

    date = target_repo.git.show("--no-patch", "--no-notes", "--pretty=%ci", patch_full_hexsha)

    date = (date.split(" ")[0]).replace("-","_")

    return date

#_________________________________________________________compute_window
def compute_window(target_repo, cve, target_tag, patch_full_hexsha, target_file, error_list, debug_mode):
    window=dict()
    window[cve]=dict()
    window[cve][target_tag]=dict()
    window[cve][target_tag][target_file]=dict()
    #open patch date file
    with open("outputDir/4_3_upstream_patch_date.json", 'r') as up_date_file:
        up_patch_date_dict = json.load(up_date_file)

    #if debug_mode==9:
    #    print ()    

    try:    
         up_date = up_patch_date_dict[cve][target_tag]
    except:
        #take the latest date
        latest_date = up_patch_date_dict[cve][ list(up_patch_date_dict[cve].keys())[0] ] 
        for lib_key in up_patch_date_dict[cve].keys():
            u_d_split = up_patch_date_dict[cve][lib_key].split('_')
            l_d_split = latest_date.split('_')
            if int(u_d_split[0]) > int(l_d_split[0]):
                latest_date = up_patch_date_dict[cve][lib_key]
            elif int(u_d_split[0]) == int(l_d_split[0]) and int(u_d_split[1]) > int(l_d_split[1]):
                latest_date = up_patch_date_dict[cve][lib_key]
            elif int(u_d_split[0]) == int(l_d_split[0]) and int(u_d_split[1]) == int(l_d_split[1]) and int(u_d_split[2]) > int(l_d_split[2]):  
                latest_date = up_patch_date_dict[cve][lib_key] 

        up_date = latest_date

    up_date_split = up_date.split("_")
    up_date_y = int(up_date_split[0])
    up_date_m = int(up_date_split[1])
    up_date_d = int(up_date_split[2])
    #get the date of the patch
    target_patch_date = get_commit_ymd_date(target_repo, patch_full_hexsha, error_list, debug_mode)

    target_patch_date_split = target_patch_date.split("_")
    t_p_d_y = int(target_patch_date_split[0])
    t_p_d_m = int(target_patch_date_split[1])
    t_p_d_d = int(target_patch_date_split[2])

    overhead = 365*(t_p_d_y - up_date_y)+30*(t_p_d_m - up_date_m )+ (t_p_d_d - up_date_d)

    window[cve][target_tag][target_file]={"upstream": up_date, "target": target_patch_date}
    window[cve][target_tag][target_file]["overhead"] = overhead 

    return window
#_________________________________________________________save_errors
def save_errors(error_list):
    time_now=datetime.now()
    date_tag=time_now.strftime("%Y%m%d_%H%M")
    with open("errorDir/fileExposure_"+date_tag,'w') as error_file:
        for line in error_list:
            error_file.write(line+"\n")
    print ("Erros written in: "+"errorDir/fileExposure_"+str(date_tag)) 
#_________________________________________________________update_json
def update_json(to_save_dict, file_path):
    full_dict=dict()
    
    
    #open if exist
    if isfile(file_path):
        with open(file_path, 'r') as file_to_load:
            full_dict = json.load(file_to_load)
        
    for tsd_key in to_save_dict.keys():
        if not (tsd_key in full_dict.keys()):
            full_dict[tsd_key] = to_save_dict[tsd_key]
    #save anew
    with open(file_path, 'w') as saveback_file:
        json.dump(full_dict, saveback_file)

    with open(file_path+"_hr", 'w') as saveback_file:
        lines = json.dumps(full_dict, indent = 4).splitlines()
        for line in lines:
            saveback_file.write(line+"\n")

    return 0    

#_________________________________________________________save_json
def save_json(file_dict, file_name):
    with open(file_name, 'w') as json_file:
        json.dump(file_dict,json_file)
    
    with open(file_name+"_hr", 'w') as json_file:
        lines = json.dumps(file_dict, indent=4).splitlines()
        for line in lines:
            json_file.write(line+"\n")

#_________________________________________________________main
#-------------------------------set headers for request scraping
requests_headers={'Content-Type': 'text/html'}
firefox_headers={
        'User-Agent': 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/111.0', 
        'Connection': 'keep-alive',
        'Accept': 'text/html,application/xhtml+xml;q=0.9,image/avif,image/webp;*/*,q=0.8',
        'Accept-Language': 'en-US,en;q=0.5',
        'Accept-Encoding': 'gzip,deflate,br',
        'Sec-Fetch-Dest': 'document',
        'Sec-Fetch-Mode': 'navigate',
        'Sec-Fetch-Site': 'cross-site',
        'Sec-GPC': '1',
        'TE': 'trailers',
        'Host': 'hg.openjdk.org'}

if len(sys.argv)<=1:
    print("\t\t\t------No Seeked config------")
    quit()
else:
    seeked_config=sys.argv[1]

#-----------------------set debug mode
debug_mode=0
if len(sys.argv)>2:
    print ("\t--Debug Mode ON--")
    debug_mode=int(sys.argv[2])

cve_for_debug_4_3_list = ["CVE-2020-2781"]

###---------------------Decide which step to produce
##Step1: Fetch Files
##Step2: Match in libcore
##Step3: Compute exposure window for patched files
##Step4: testResults
fetch_files_status=0
libcore_match_status=0
window_computation_status=0
test_results_status=0
if len(sys.argv)>3:
    #fourth element will be
    input_status=str(sys.argv[3])
    if "fetch" in input_status:
        fetch_files_status=1
    if "match" in input_status:
        libcore_match_status=1
    if "window" in input_status:
        window_computation_status=1
    if "window" in input_status or "test" in input_status:
        test_results_status=1
else:    
    fetch_files_status=1
    libcore_match_status=1
    window_computation_status=1
    test_results_status=1


#---------------------------init-paths
home_aosp="/home/triom/android_source_code"
home_libcore=home_aosp+"/libcore"
#---------------------------regex
former_file_line_regex=re.compile(r"^---")
file_line_regex=re.compile(r"\+\+\+")
file_name_regex=re.compile(r"/[^/]+/[^/]+\.[\S]+")
program_version_regex_in_url=re.compile(r"jdk[0-9]+")
#---------------------------error_structure
error_list=[]

##-------------------------intermediary_result_structure
#d[cve]=[files]
cve2files_dict=dict()
#dict[cve]=[files]
cve2presentfiles_dict=dict()
#more complete:  dict[cve][jdk][commit]=[files]
complete_cve2files_dict=dict()
#
complete_cve2presfiles_dict=dict()

##---------------------------output_result_structure

#----------------------------counters
main_table_ct=0

#----------------------------soup_scraping_utility
commit_main_finder=dict()
commit_main_finder['tag']=''
commit_file_finder=dict()
commit_file_finder['tag']=''

#--------------------------------tags_under_test_list for libcore
versions=[7,8,9,10,11,12,13]
tags_under_test_list=[]
for ver in versions:
    tags_under_test_list.append("tags/android-"+str(ver)+".0.0_r1") #android specific
tags_under_test_list.append("master")

#-------------------------------------test driven approach
cve_2020_2659={""}
test_suite_dict = dict()
test_suite_dict["master_unpatched"]=["CVE-2022-21293", "CVE-2020-2756", "CVE-2020-2757", "CVE-2020-2805", "CVE-2020-2654"]
test_suite_dict["master_patched"]={"CVE-2022-21340": {"Attributes.java": {"short_sha":"4c551db", "full_sha":"4c551dbec6cf5f10f6bd269a2f5451ada352e1cc"}}, "CVE-2020-2830":{"Scanner.java":{"short_sha":"0a0d058", "full_sha":"0a0d05866e935ea8b2bf68b3f898e0be9a0c536d"}}, "CVE-2009-3881":{"ClassLoader.java":{"short_sha":"51b1b69","full_sha":"51b1b6997fd3f980076b8081f7f1165ccc2a4008"}}}
test_suite_dict["droid_implem"]=["CVE-2021-2161", "CVE-2022-2148", "CVE-2022-21283", "CVE-2020-2593"]
test_suite_dict["partial_patch"]=[]

#test_suite_list=[master_unaptched, masteir_patched, master_droid_implem]




###open input file with all_cves
cve2patch_dict=open_json("outputDir/3_cve_to_upstream_commits.json", debug_mode)
###########################################################################################################################################
####STEP 1: COLLECT FILES MODIFIED IN CVE RELATED PATCHES
###for each cves, each commit link: collect  the paths to file
if fetch_files_status:
    print("#####---STEP 1")
    for cve_key in cve2patch_dict.keys():
        #no need to delay if local fetch
        #time.sleep(random.uniform(0, 1))
        if debug_mode>=1:
            print ("\t ----CVE: "+str(cve_key))
        nb_commit_link=len(cve2patch_dict[cve_key])    
        main_table_ct=0

        for commit_link in cve2patch_dict[cve_key]:
            #no need to limit if local fetch
            #time.sleep(random.uniform(0.3,3))
            
            #keep track of jdk version
            program_version=re.search(program_version_regex_in_url,commit_link).group(0)
            #commit_hash
            commit_hash=commit_link.split('/')[-1]
            if debug_mode in [5,6,7]:
                print (str(commit_link))
            
            commit_soup = get_parsed_soup_from_url(commit_link, firefox_headers, debug_mode)
            if not (type(commit_soup)==type(None)):
                #differentiate if hg or github
                if ("hg." in commit_link):
                    #get main table
                    commit_main_finder['tag']='class_'
                    commit_main_finder['value']="stripes2 diffblocks"
                    #identify lines 
                    commit_file_finder['tag']='class_'
                    commit_file_finder['value']="plusline"
                
                    #for either case if the values are set:
                    if (len(commit_main_finder['tag'])>0) and (len(commit_file_finder['tag'])>0):
                    #get main table
                        commit_main_soup= commit_soup.find(class_=commit_main_finder['value'])
                        if not (type(None)==type(commit_main_soup)):
                            main_table_ct+=1 
                    #get the lines with file names
                            if debug_mode==1:
                                print ("Commit_link: "+str(commit_link))
                                print ("--found main table #"+str(main_table_ct)+"|"+str(nb_commit_link))
                            #file by file
                            all_file_sections_soup = commit_main_soup.find_all(class_="sourcelines wrap")
                            if debug_mode==1:
                                print ("----Found "+str(len(all_file_sections_soup))+" file sections")
                            for file_section_soup in all_file_sections_soup:
                                first_minus_line_soup = file_section_soup.find(class_="minusline")
                                plus_lines_soup = file_section_soup.find_all(class_=commit_file_finder['value'])  
                                former_name_in_line=""
                                if not (type(None)==type(first_minus_line_soup)):
                                    #for line_soup in plus_lines_soup:
                                    if (re.search(r"^---", first_minus_line_soup.text)):
                                                print ("--former_name_line------"+str(first_minus_line_soup.text))
                                                former_name_in_line = (re.search(r"(/[\S]+)+",first_minus_line_soup.text)).group(0)[1:]
                                                #print (former_name_in_line)
                                                former_file_group=former_name_in_line.split('/')
                                                former_file_to_save=former_file_group[-2]+"/"+former_file_group[-1]
                                                
                            

                                if not (type(None)==type(plus_lines_soup)):
                                    if debug_mode==1:
                                        print ("------Found some plus lines")
                                    for line_soup in plus_lines_soup:
                                        if debug_mode==3:
                                           print ("--plusline----"+str(line_soup.text))
                                        

                                # get only those starting with +++ and get the file name
                                        if (re.search(file_line_regex, line_soup.text)):
                                            #if debug_mode==1:
                                            #    print ("-------- file found\t"+str(line_soup.text))
                                            #file_line=(re.search(file_line_regex, line_soup.text)).group(0)
                                            if (re.search(file_name_regex, line_soup.text)):
                                                file_name_in_line = (re.search(file_name_regex,line_soup.text)).group(0)[1:]
                                                file_group=file_name_in_line.split('/')
                                                file_to_save=file_group[-2]+"/"+file_group[-1]
                                            elif re.search(r"/dev/null", line_soup.text)  :
                                                #print ("+++"+line_soup.text)
                                                file_to_save = former_file_to_save
                                                print ("Former_file name found is:"+str(former_file_to_save))
                                            #tbd: note in patch markers that the file is actually deleted by this patch    
                                            #filling cve2files_dict
                                            if not(len(file_to_save) == 0):
                                                if debug_mode==1:
                                                    print ("--------\t"+str(file_name_in_line)+"\t||"+file_to_save)
                                                if cve_key in cve2files_dict.keys():
                                                    cve2files_dict[cve_key].append(file_name_in_line)
                                                else:
                                                    cve2files_dict[cve_key]=[file_name_in_line]
                                                #complete_cve2files_dict
                                                if not( cve_key in complete_cve2files_dict.keys()):
                                                    complete_cve2files_dict[cve_key]=dict()
                                                if not( program_version in complete_cve2files_dict[cve_key].keys()):
                                                    complete_cve2files_dict[cve_key][program_version]=dict()
                                                if not( commit_hash in complete_cve2files_dict[cve_key][program_version].keys()):
                                                    complete_cve2files_dict[cve_key][program_version][commit_hash] = []
                                                print (file_name_in_line)
                                                complete_cve2files_dict[cve_key][program_version][commit_hash].append(file_name_in_line)
                                                #elif re.search(r"/dev/null", line_soup.text):
                                                #    #A file is deleted by the patching commit
                                                #    file_name_in_line = former_file_name
                                                #    file_group=file_name_in_line.split('/')
                                                #    file_to_save=file_group[-2]+"/"+file_group[-1]
                                                #    
                                                #    error_list.apprend("from /dev/null to |"+str(file_to_save))


                                                                                            
                                            else:
                                                #if debug_mode==3:
                                                error_list.append("xxxxxxxxxx Bad file regex in\t"+str(line_soup.text)+" xXxXxXxXxX "+str(re.search(file_name_regex, line_soup.text)))
                                                #error_list.append("xxxxxxxxxxxx Bad file regex")
                                                if debug_mode==3:
                                                   print (error_list[-1:])
                                                quit()   
                                else:
                                    if debug_mode==4:
                                        print("xxxx No plus line found:\t"+str(commit_link))
                                    error_list.append("xxxxxxxx No + line found:\t"+str(cve_key)+"\t|\t"+str(commit_link) )    
                        else:
                            if debug_mode==5:
                                print("No main table access\t"+str(commit_link))
                            error_list.append("xXxXx No access to main_table:\t"+str(cve_key)+": "+str(commit_link))
                            #if file present then erase because useless
                            path_to_file=commit_link.split("https://")[1]
                            ##no more need of the code removing in the form that a turstable version has been uploaded from a client version of the cod
                            ##better have this code not erasing trustable data: if this part fails, ten the matching is not good
                            #if isfile("webpagesDir/"+path_to_file):
                            #    print ("_______________file_removed_from_webpages")
                            #    remove("webpagesDir/"+path_to_file)
                

                elif ("github" in commit_link):    
                    #access page: commit_soup
                    print ("Access to: "+str(commit_link))
                    #get main area
                    main_area_class_tag="js-diff-progressive-container"
                    github_main_area_soup=commit_soup.find(class_=main_area_class_tag)
                    if not(type(None) == type(github_main_area_soup) ):
                    #set section containing file-header and file content for each line
                        file_sections_class_tag="Truncate"
                        #file_sections_class_tag="file js-file js-details-container js-targetable-element show-inline-notes Details Details--on open js-tagsearch-file "
                        file_sections_soup_list = github_main_area_soup.find_all(class_=file_sections_class_tag)
                        #print("a")
                        if not(type(None) == type(file_sections_soup_list) ):
                            #print("b: "+str(len(file_section_soup)))
                            for file_section_soup in file_sections_soup_list:
                    #get file name
                                #print ("d: "+str(file_section_soup))
                                file_name_class_tag="Link--primary Truncate-text"
                                file_name_soup=file_section_soup.find(class_=file_name_class_tag)
                                if not(type(None) == type(file_name_soup) ):
                                    file_name_split = file_name_soup['title'].split('/')  
                                    file_name_in_line = file_name_split[-2]+"/"+file_name_split[-1]
                                    #print("c")
                                    if debug_mode==1:
                                        print(file_name_in_line)
                                    #quit()
                    #build up the file content taking the data-code-marker   #not required: we are not collecting the patch here but the file names
                                    #all_lines_sections=""
                                    #
                                    #
                    #file up cve2files_dict[cve_key] and complete_cve2files_dict[cve_key][program_version][commit_hash]
                                    if cve_key in cve2files_dict.keys():
                                        cve2files_dict[cve_key].append(file_name_in_line)
                                    else:
                                        cve2files_dict[cve_key]=[file_name_in_line]
                                    #complete_cve2files_dict
                                    if not( cve_key in complete_cve2files_dict.keys()):
                                        complete_cve2files_dict[cve_key]=dict()
                                    if not( program_version in complete_cve2files_dict[cve_key].keys()):
                                        complete_cve2files_dict[cve_key][program_version]=dict()
                                    if not( commit_hash in complete_cve2files_dict[cve_key][program_version].keys()):
                                        complete_cve2files_dict[cve_key][program_version][commit_hash] = []
                                    print (file_name_in_line)
                                    complete_cve2files_dict[cve_key][program_version][commit_hash].append(file_name_in_line)
                                else:
                                    
                                    error_list.append("xXx 4.1| Github |Filename not found")
                                    print(error_list[-1])
                        else:
                            error_list.append("xXx 4.1| Github | no access to file sections")
                            print(error_list[-1])
                            #quit()
                    else:
                        error_list.append("xXx 4.1| Github | No access to main area")
                        print(error_list[-1])
                        #quit()
                else:
                    if debug_mode==6:
                        print("Repo access not set upi\t"+str(commit_link))
                    error_list.append("xXx 4.1 Repo access not set up:\t"+str(cve_key)+": "+str(commit_link))
                #save file in intermediary result dict
                #save_json(cve2files_dict,"outputDir/4_1_cve_patched_files.json")
               
            else:
                if debug_mode==7 or debug_mode==6:
                    print ("No access to page\t"+str(commit_link))
                error_list.append("xx 4.1 No soup | No access to page:\t"+str(cve_key)+": "+str(commit_link))

    #save_json in intermediary result dict
    save_json(cve2files_dict, "outputDir/4_1_cve_patched_files.json")
    save_json(complete_cve2files_dict, "outputDir/4_1_complete_cve_to_patch_files.json")
    print ("-----STEP1 finished#####")
##################################################################################################################################################
####STEP 2: CHECK IF FILE WAS EVER IN LIBCORE
###for each file, check if match with any file in libcore (possible through EXPECTED_UPSTREAM)
if libcore_match_status:
    print ("#####---STEP 2")
    cve_with_all_files_found_in_libcore = []
    if not (fetch_files_status):
        error_list.append("XXXXXXXXXXXXXXXXXXXXXXXOnly Matching ErrorsXXXXXXXXXXXXXXXXXXXXXXXXXXX")
        cve2files_dict=open_json("outputDir/4_1_cve_patched_files.json", debug_mode)
        complete_cve2files_dict=open_json("outputDir/4_1_complete_cve_to_patch_files.json", debug_mode)
    for cve_indix,cve_key in enumerate(cve2files_dict.keys()):
        
        print ("-CVE# "+str(cve_indix+1)+" / "+str(len(cve2files_dict.keys()))+"\t|"+str(cve_key))
        nb_file_searched=0
        nb_file_found = 0
        nb_file_versions_found=0
    ###is matches with at least one, then save
        #for file_in_commit in cve2files_dict[cve_key]:
        for program_version in complete_cve2files_dict[cve_key]:
            files_searched_from_upstream = list()
            files_found_from_upstream = list()
            nb_files_patching_upstream_cve = 0
            for commit_hash in complete_cve2files_dict[cve_key][program_version]:
                nb_files_in_patch = len(complete_cve2files_dict[cve_key][program_version][commit_hash])
                nb_files_patching_upstream_cve += nb_files_in_patch
                for file_in_commit in complete_cve2files_dict[cve_key][program_version][commit_hash]:
    ###for several version of android's libcore: navigation through tags
                    file_found_status = 0
                    nb_file_searched+=1
                    #
                    if not file_in_commit in files_searched_from_upstream:
                        files_searched_from_upstream.append(file_in_commit)

                    #
                    libcore_repo = git.Repo(home_libcore)
                    libcore_repo.git.checkout("master", force=True)
                    libcore_init_commit=libcore_repo.head.commit.hexsha
                    if debug_mode==8:
                        print ("libcore_init_branch: "+str(libcore_init_commit) )
                    #    quit()
                    for libcore_tut in tags_under_test_list:
                        if debug_mode==8:
                            print ("----Checkout to "+str(libcore_tut))

                        libcore_repo.git.checkout(libcore_tut, force=True)
                        if debug_mode==8:
                            print ("----Last_commit is: "+str(libcore_repo.head.commit.hexsha))
           
                    
                        #jdk_version=cve2patch_dict[]
                        #fetch if file in libcore
                        match_file_list = glob.glob(home_libcore+"/**/"+file_in_commit, recursive=True )
                        if debug_mode==8:
                            print ("------------------------")
                            print (str(file_in_commit))
                            print(str(match_file_list))
                            print ("------------------------")
                        if len(match_file_list)>0:
                            for libcore_file in match_file_list:
                                if not("annotation" in libcore_file):
                                    file_found_status = 1
                                    short_path=(libcore_file.split('libcore/'))[-1]
                                    if cve_key in cve2presentfiles_dict.keys():
                                        #short_path = libcore_file.split('libcore/')[-1]
                                        if not (short_path in cve2presentfiles_dict[cve_key]):
                                            cve2presentfiles_dict[cve_key].append(short_path)
                                        #not need with complete_cve2presfiles_dict structure
                                        #else:
                                        #    error_list.append("xXx In 4.2, the collect of the openjdk version is not managed")
                                    else:
                                        cve2presentfiles_dict[cve_key]=[short_path] 
                                    if not( cve_key in complete_cve2presfiles_dict.keys()):
                                        complete_cve2presfiles_dict[cve_key]=dict()
                                    
                                    if not( libcore_tut in complete_cve2presfiles_dict[cve_key].keys()):
                                        complete_cve2presfiles_dict[cve_key][libcore_tut]=dict()

                                    if not( program_version in complete_cve2presfiles_dict[cve_key][libcore_tut].keys()):    
                                        complete_cve2presfiles_dict[cve_key][libcore_tut][program_version]=dict()
                                    
                                    if not( commit_hash in complete_cve2presfiles_dict[cve_key][libcore_tut][program_version].keys()):    
                                        complete_cve2presfiles_dict[cve_key][libcore_tut][program_version][commit_hash]= []
                                        #print (str(commit_hash)+"  added")
                                    
                                    if not (short_path in complete_cve2presfiles_dict[cve_key][libcore_tut][program_version][commit_hash]):
                                        if not file_in_commit in files_found_from_upstream:
                                            files_found_from_upstream.append(file_in_commit)
                                        nb_file_versions_found+=1
                                        complete_cve2presfiles_dict[cve_key][libcore_tut][program_version][commit_hash].append(short_path)
                                    else:
                                        error_list.append("xXx In 4.2, a file requires to be added several time to the same complete structure. With complete_cve2presfiles_dict it shall not happen")
                    #
                    if file_found_status:
                        nb_file_found+=1

                    #move back to the initial commit before research   
                    libcore_repo.git.checkout(libcore_init_commit)
                    #check
                    libcore_returnedto_commit=libcore_repo.head.commit.hexsha
                    if not(libcore_returnedto_commit==libcore_init_commit):
                        error_list.append("not returning to the appropriate commit")
                        print ("XXXX FATAL GIT ERROR: Program stopped XXXX")
                        quit()
                    libcore_repo.git.checkout("master", force=True)    
                    if debug_mode==8:
                        print("moved back to initial commit:\t"+str(libcore_init_commit))
            #exit of over patch file loop
            #check if all files have been found in libcore
            # if nb file found == nb_files_in_patch
            #if nb_file_found == nb_files_in_patch:
            all_in = 1
            for searched_file in files_searched_from_upstream:
                if not(searched_file in files_found_from_upstream):
                    all_in = 0

            if all_in ==1:            
                cve_with_all_files_found_in_libcore.append(cve_key)
        print ("|_"+str(cve_key)+":  "+str(nb_file_versions_found)+" file versions matched in libcore over "+str(nb_file_searched))

    with open("outputDir/4_2_CVE_with_all_files_found",'w') as out_file:
        for cve_found in cve_with_all_files_found_in_libcore: 
            out_file.write(cve_found+"\n")
    #error_list.append("x In 4.2, several versions of android are not checked for file matching")                        
    save_json(cve2presentfiles_dict, "outputDir/4_2_cve_to_present_files.json")
    save_json(complete_cve2presfiles_dict, "outputDir/4_2_complete_cve_to_libcorematched_present_files.json")
    print ("----STEP 2 OVER#####")
######################################################################################################################################################################
####STEP 3: CHECK IF PATCH HAS EVER BEEN ALREADY APPLIED                         
###for each cve left, for each file, look in the history if anything looks like the patching commit from 3_
#case where it works is CVE-2022-21340... We shall find the patch appear in libcores' java/util/jar/Attributes.java
#in openJDK patched date is 26 Nov 2021 |  in libcore
if window_computation_status:
    print ("#####---STEP 3")
    cve_to_when_patched_files_dict=dict()
    if not(fetch_files_status) or  not(libcore_match_status):
        error_list.append("XXXXXXXXXXXXXXXXXXXXXXXXXXWINDOW EXPOSURE COMPUTATION ERRORSXXXXXXXXXXXXXXXX")
        cve2presentfiles_dict=open_json("outputDir/4_2_cve_to_present_files.json", debug_mode )
        complete_cve2presfiles_dict= open_json("outputDir/4_2_complete_cve_to_libcorematched_present_files.json", debug_mode)
#set git repo
    libcore_repo = git.Repo(home_libcore)
    libcore_repo.git.checkout("master", force=True)
    libcore_init_commit=libcore_repo.head.commit.hexsha
#for each cve
    for cve_key in complete_cve2presfiles_dict.keys():
        print ("["+str(cve_key)+"]")

        #initialise result dir
        cve_to_when_patched_files_dict[cve_key]=dict()
        debug_this_cve=0
        if debug_mode==9:
            if cve_key in cve_for_debug_4_3_list:
                debug_this_cve=1
#for each file
        for libcore_tag in complete_cve2presfiles_dict[cve_key]:
            print ("["+str(cve_key)+"][libcore: "+str(libcore_tag)+"]")
            cve_to_when_patched_files_dict[cve_key][libcore_tag]=dict()


            libcore_repo.git.checkout(libcore_tag, force=True)
            libcore_tag_commit_hexsha=libcore_repo.head.commit.hexsha
            #we are in files repo versions were the file exists

            for upstream_program_version in complete_cve2presfiles_dict[cve_key][libcore_tag]:
                    print ("["+str(cve_key)+"][libcore: "+str(libcore_tag)+"][upstream:"+str(upstream_program_version)+"]")
                    cve_to_when_patched_files_dict[cve_key][libcore_tag][upstream_program_version]=dict()

                #for upstream_program_version in complete_cve2presfiels_dict[cve_key][libcore_tag][taget_program_version]:
                    sha_score_dict=dict()
                    for patch_short_hexsha in complete_cve2presfiles_dict[cve_key][libcore_tag][upstream_program_version]:
    #get files' patch specificities: plus lines and minus lines for that filei
                    # reconstruct path to file in webpagesDir and access it through soup
                        print ("["+str(cve_key)+"][libcore: "+str(libcore_tag)+"][upstream:"+str(upstream_program_version)+"]["+str(patch_short_hexsha)+"]")
                        cve_to_when_patched_files_dict[cve_key][libcore_tag][upstream_program_version][patch_short_hexsha]=dict()


                        patch_path_list= glob.glob("./webpagesDir/rev"+"/**/"+patch_short_hexsha, recursive=True)
                        if not (len(patch_path_list)==1):
                            
                            error_list.append("xxx4.3xxx\t"+"Seveal short_hexsha found "+str(patch_path_list))
                            print (error_list[-1])
                            print ("4.3 Fatal Error: l968, more than one patch returned")
                            quit()
                        patch_url = ("https://"+((patch_path_list[0].split("webpagesDir/rev/"))[-1]))
                        if debug_mode==9:
                            print (str(patch_url))
                        #Access file to soup
                        patch_soup = get_parsed_soup_from_url(patch_url, firefox_headers, debug_mode)
                        #TBD: function returning a dict commit_file_modif_dict{file_name: {'plus_lines': [], 'minus_lines': []}} for only that file
                        patch_markers_dict=get_patch_markers(patch_short_hexsha, patch_soup, patch_url, cve_key, libcore_tag, debug_mode)
                        if patch_markers_dict ==0:
                            error_list.append("xXx 4.3| no patch markers for: "+ str(cve_key)+" | "+str(patch_short_hexsha))
                            save_errors(error_list)
                            print (error_list[-1])
                            quit()
#get the historicity of that file
                        for target_file in complete_cve2presfiles_dict[cve_key][libcore_tag][upstream_program_version][patch_short_hexsha]:
                            print ("["+str(cve_key)+"][libcore: "+str(libcore_tag)+"][upstream:"+str(upstream_program_version)+"]["+str(patch_short_hexsha)+"]["+str(target_file)+"]")
                            #initialise result_dir
                            cve_to_when_patched_files_dict[cve_key][libcore_tag][upstream_program_version][patch_short_hexsha][target_file]=""
                            if debug_mode==9:
                                print("--------->"+str(target_file))
                            #get the list of sha that modified that file in repo: target_file
                            # the elements of the list are dicts {"sha":,"file_path":} because the file might be renamed/moved during its history
                            sha_dicts_list=get_sha_list_modifying_file(libcore_repo, target_file,error_list, debug_mode)

                            for modifying_sha_dict in reversed(sha_dicts_list):
                                print ("\t\t"+str(modifying_sha_dict["sha"]))
                                modifying_sha_sha=modifying_sha_dict["sha"]
                                modifying_sha_path=modifying_sha_dict["file_path"]
                                #get the text of the file as string
                                file_lines=get_file_lines(libcore_repo, modifying_sha_dict, cve_key, error_list, debug_mode)
                                if len(file_lines)==0:
                                    #something went wrong
                                    error_list.append("xXx 4.3 error in return file|"+modifying_sha_path+"| for commit |"+str(modifying_sha_sha)+"|-> no line returned !!!")
                                    save_errors(error_list)
                                    print (error_list[-1])
                                    quit()
                                #case file removed by commit    
                                if len(file_lines)==3 and file_lines[0]=="rm":
                                    #to acknowledge errors later
                                    #the value is dynamically provided
                                    #to secure the state: file deleted by commit
                                    score = file_lines[1]
                                    
                                else:
                                    #
                                    #patch_markers on the shape {"file_name": {"plus_line":[],"minus_line":[]}}
                                    #but file_name only takes the last 2 parts of the file_name
                                    splitted_target=target_file.split('/')
                                    short_file_path=splitted_target[-2]+"/"+splitted_target[-1]
                                    if debug_mode==9:
                                        print ("4.3|File:\t"+str(short_file_path)+"\t| nb_lines"+str(len(file_lines)))
                                    if str(short_file_path) in patch_markers_dict.keys():
                                
                                        score_struct=compute_commit_score(short_file_path.replace("/","_"), modifying_sha_sha, file_lines,patch_markers_dict[short_file_path], debug_mode)
                                        score=score_struct[0]
                                    else:
                                        error_list.append("xXx 4.3| For "+str(cve_key)+" could not find patch to file "+str(short_file_path)+" in patch_markers_dict")
                                    #store score in structure
                                    sha_score_dict[modifying_sha_sha]=score_struct
                        
                            #
                            get_libcore_v_file_lines(libcore_repo, cve_key, libcore_tag, target_file, error_list, debug_mode)

                            patch_status_score_history=[]
                            p_d_folder="testDir/patch_detection"
                            makedirs(p_d_folder, exist_ok=True)

                            #if not( len ((sha_dicts_list))==len(sha_score_dict.keys()) ):
                            #    error_list.append("xXx 4.3| the number of element save in test dir is not the same as the number of element tested: why ?")
                            #    print ("tobesaved:\n"+str(sha_score_dict.keys())+"|\n|"+str(sha_dicts_list))
                            #    print (error_list[-1])
                            #    save_errors(error_list)
                            #    quit()
                            with open(p_d_folder+"/"+str(cve_key)+"_"+str(libcore_tag).replace("/", "-")+"_"+str(upstream_program_version)+"_"+str(patch_short_hexsha)+"_"+str(target_file).replace("/","_"),'w') as p_t_file:
                                print ("CVE-results: "+str(cve_key))
                                for ss_key in sha_score_dict.keys():    
                                    patch_status_score_history.append( (str(ss_key)+"\t:\t"+str(sha_score_dict[ss_key])))
                                    p_t_file.write(patch_status_score_history[-1]+"\n")
                                    if debug_mode==9:
                                        print(patch_status_score_history[-1])

                            #Here we can deduce if the patch is applied
                            #if score always positive: always present in libcore
                            #if always negative: never applived
                            #if varies from negative to positive: patch is applied
                            #function shall return the hash of the patch application if found
                            #else it can return "always_patched"
                            #or "never_patched"
                            when_patched=is_target_patched(cve_key, libcore_tag, upstream_program_version, patch_short_hexsha, target_file, sha_score_dict, error_list, debug_mode)
                            if debug_mode==9:
                                print("4.3|When patched:\t\t"+str(when_patched))
                                
                                if when_patched=="always_patched":
                                    print ("\tAlways_patched")
                                elif when_patched=="never_patched":
                                    print ("\tNever_patched")
                                elif len(when_patched)>0:
                                    print("\tPatch_detected: "+str(when_patched))
                                    window_dict = compute_window (libcore_repo, cve_key, libcore_tag, when_patched, target_file, error_list, debug_mode)
                                    update_json(window_dict, "outputDir/4_3_window_exposure_overhead.json")
                                   #quit()
                                else:
                                    when_patched=str(sha_score_dict)
                                    print ("\t--Undecided situation--")
                                    error_list.append("xXx 4.3 | Could not determined the when_patched status for "+str(cve_key)+" in file "+str(target_file)+"|\t"+str(sha_score_dict))    
                            cve_to_when_patched_files_dict[cve_key][libcore_tag][upstream_program_version][patch_short_hexsha][target_file]=when_patched               
                            #save for every new entry 
                            save_json(cve_to_when_patched_files_dict, "outputDir/4_3_cve_to_when_patched.json")
#if so take the date in openjdk, the date of commit in libcore, compute a temporal exposure overhead

#else report in errors and in specific missing_patch_dict structure
        #iterative save
        #save_json(cve_to_when_patched_files, "outputDir/4_3_cve_to_when_patched.json") 
        #checkout back to master
        libcore_repo.git.checkout("master", force=True)
        #verify checkout
        to_check_hexsha=libcore_repo.head.commit.hexsha
        if not (to_check_hexsha == libcore_init_commit):
            #FATAl ERROR
            error_list.append("Fata Error on return to master branch")
            put ("XXXXXXXXXX FATAL ERROR: Not Returning to master XXXXXXXXX")
            quit()
          
    #save one last time        
    save_json(cve_to_when_patched_files_dict, "outputDir/4_3_cve_to_when_patched.json")         
    print("-----STEP3 OVER#####")
########################################################
#######
if test_results_status:
    print ("#####-----STEP 4:\tTest Results")
    if not(window_computation_status):
        cve_to_when_patched_files_dict=open_json( "outputDir/4_3_cve_to_when_patched.json" , debug_mode)

    run_test_suite(cve_to_when_patched_files_dict, test_suite_dict, error_list, debug_mode)  

    print ("\t\tSTEP 4 OVER-----#####")
#################################################
save_errors(error_list)

#Results
if len(error_list)-1==0:
    print("=================== SUCCESS ================\n============== NO ERROR FOUND ==============")
else:
    print ("XXXXXXXXXXXXXXXXX "+str(len(error_list))+" ERRORS FOUND XXXXXXXXXXXXXXXXXX")
