# AndroidsJCL-SecDev23 
In this repository, we provide the artefacts related to our paper analysing the Java Class Library in Android.

This paper, entitled *An In-Depth Analysis of Android’s Java Class Library: its Evolution and Security Impact.* is accepted at the  IEEE Secure Development Conference 2023. 

[Conference-Webpage](https://secdev.ieee.org/2023/accepted/)

[BibTeX](https://www.abartel.net/static/p/secdev2023-AndroidJCL.bib.txt)

```bash
# clone this repository
git clone https://github.com/software-engineering-and-security/AndroidsJCL-SecDev23.git
cd AndroidsJCL-SecDev23
tar xzvf timdb-openjdk_classes_tables.tar.gz

```

# Buidling the database
Mariadb has been used all alog the process.

DB can be rebuild auromatically by:

1. Updating the RQ1-OriginalVersion/toolsDir/db\_tools file
1. Create mariadb user accordingly
1. cd in RQ1-OriginalVersion/toolsDir/
1. Execute build\_mariadb\_db.sh
	


--------------------------------------------------------------------------------------

# RQ1-Origin of Libcore Java classes:

## Reproduce Figure 3- Evolution of Java Classes:
```bash
cd RQ1-OriinalVersion
bash toolsDir/one_tables_nb_ojclass_figure.sf
cd ..
```
## Reproduce Figure 4- OpenJDK profile of each Android version:
For each version X
```bash
cd RQ1-OriginalVersion
bash toolsDir/compare_one_tables_sh_X.sh
cd ..
```

## Reproduce figure 5- Proximity of Android Java Classes to Original OpenJDK :
```bash
cd RQ1-OriginalVersion/ONE_TABLES/PROXIMITY
bash toolsDir/stats.sh
```


--------------------------------------------------------------------------------------

# RQ2-Management of OpenJDK CVEs and Potential Over-Exposures :
```bash 
cd RQ2-OverExposure
bash run_analysis.sh
cd ..
```

--------------------------------------------------------------------------------------


# RQ3- Exploit of CVE-2022-21340, both on OpenJDK and Android-13:

Video demonstrating available at ./RQ3-Exploit/cve-2022-21340/tim\_android\_app/device-2023-05-24-101133.mp4

App available at ./RQ3-Exploit/cve-2022-21340/tim\_android\_app/CVE20221340

Device fingerprint: google/sdk\_gphone\_x86\_64/emu64xa:13/TE1A.220922.025/9795748:userdebug/dev-keys

## OpenJDK


```bash 
cd RQ3-Exploit/openjdk-vulnerable
#Download Vulnerable version of OpenJDK
wget https://download.java.net/java/GA/jdk11/13/GPL/openjdk-11.0.1_linux-x64_bin.tar.gz
#untar openjdk vulnerable version
tar xzvf openjdk-11.0.1.tar.gz
#Generate the tar file
cd source_jar
bash create_jar.sh
cd ..

```

## Android-13




# Paper figures

|  Ref |  Location (relative to `$DOCKER_CONTAINER_BASE/home/{user_name}/`) | 
| --------- |:---------|
| Figure 3 | RQ1-OriginalVersion/ONE\_TABLES/GRAPHDIR/nb\_ojluni\_classes.pdf |
| Figure 4 | RQ1-OriginalVersion/ONE\_TABLES/GRAPHDIR/* |
| Figure 5 |  RQ1-OriginalVersion/ONE\_TABLES/PROXIMITY/graphDir/distances_area.pdf|

