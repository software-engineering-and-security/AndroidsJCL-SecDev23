# AndroidsJCL-SecDev23 
In this repository, we provide the artefacts related to our paper Revisiting the VCCFinder approach for the identification of vulnerability-contributing commits to appear in the

```bash
# clone this repository
git clone https://github.com/riomtimothe/AndroidsJCL-SecDev23.git
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

## Reproduce Figure 4:

## Reproduce figure 5:

# RQ2:

# RQ3:

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

