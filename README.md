# AndroidsJCL-SecDev23 
In this repository, we provide the artefacts related to our paper analysing the Java Class Library in Android.

```bash
# clone this repository
git clone https://github.com/software-engineering-and-security/AndroidsJCL-SecDev23.git
cd AndroidsJCL-SecDev23
tar xzvf timdb-openjdk_classes_tables.tar.gz

```

# Buidling the database
Mariadb has been used all alog the process.

DB can be rebuild auromatically by:

	1. Updating the RQ1-/toolsDir/db\_tools file
	2. Executing ....


# RQ1:

## Reproduce Figure 3:
```bash
cd RQ1-OriinalVersion
bash toolsDir/one_tables_nb_ojclass_figure.sf
cd ..
```
## Reproduce Figure 4:
For each version X
```bash
cd RQ1-OriginalVersion
bash toolsDir/compare_one_tables_sh_X.sh
cd ..
```

## Reproduce figure 5:
```bash
cd RQ1-OriginalVersion/ONE_TABLES/PROXIMITY
bash toolsDir/stats.sh
```

# RQ2:
```bash 
cd RQ2-OverExposure
bash run_analysis.sh
cd ..
```


# RQ3:

Video demonstrating available at ./RQ3-Exploit/cve-2022-21340/tim\_android\_app/device-2023-05-24-101133.mp4

App available at ./RQ3-Exploit/cve-2022-21340/tim\_android\_app/CVE20221340

Device fingerprint: google/sdk\_gphone\_x86\_64/emu64xa:13/TE1A.220922.025/9795748:userdebug/dev-keys

## OpenJDK


```bash 
cd RQ3-Exploit/openjdk-vulnerable
#untar openjdk vulnerable version
tar xzvf openjdk-11.0.1.tar.gz

```

## Android-13

# Example:
Make sure you have already reconstructed the database.

```bash
# Now we are inside the docker container

```

The wole process will take ~YY hours. It runs on a machine with at least 32GB of RAM.

# Paper figures

|  Ref |  Location (relative to `$DOCKER_CONTAINER_BASE/home/{user_name}/`) | 
| --------- |:---------|
| Figure 3 | RQ1-OriginalVersion/ONE\_TABLES/GRAPHDIR/nb\_ojluni\_classes.pdf |
| Figure 4 | RQ1-OriginalVersion/ONE\_TABLES/GRAPHDIR/* |
| Figure 5 |  RQ1-OriginalVersion/ONE\_TABLES/PROXIMITY/graphDir/distances_area.pdf|

