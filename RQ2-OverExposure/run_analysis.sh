#!/bin/bash

debug_mode=0


#Step 1: extractFromNVD Feeds
time python3 toolsDir/extractCVEpoerVulnerable.py openjdk

#Step 2: get
time python3 toolsDir/uniqueVulnerableVersionInAndroid.py

#Step 3: Fetch all URLs of commits web scrapping Bugzilla and CVE Mitre description for BugId
#inputs: 
#outputs:
#debug modes: 1: Debug in specific cve for Bugzilla scrapping || 2: Debug in specific CVE for Mitre scrapping || 3:Full verbose 
time python3 toolsDir/scrapPatchURL.py openjdk ${debug_mode}

#Step 4: Search for files in commit + saves them if present in (so far) the last version of libcore + TBD:guesses if patch has been applied to libcore file through git history
#inputs: 3_cve_to_upstream_commits.json | (Internet|webpagesDir(default)
#outputs:
#debug_modes: 1: discovery | 2: | 3: verbose on + lines in commit | 4: no + lines found in commit | 5: Main table not found | 6:URL accesible but code unable to parse it | 7: No answer from url request |
#8: debug Step 2 | 9: debug step 3
#executed_step:  fetch,match,window  can be 1,2 or 3| if none the equivalent to three | needs $debug_mode
time python3 toolsDir/fileExposure.py openjdk ${debug_mode} ${executed_step}
