# AndroidsJCL-SecDev23 
In this repository, we provide the artefacts related to our paper Revisiting the VCCFinder approach for the identification of vulnerability-contributing commits to appear in the

```bash
# clone this repository
git clone https://github.com/riomtimothe/AndroidsJCL-SecDev23.git
cd AndroidsJCL-SecDev23
#db is AndroidsJCL-database.dump.xz
```

# Dockerfile
*Building* this docker image will only set up the database. 
If you want the experiments to be done at build time, uncomment the last two lines of `Dockerfile`.
Otherwise, you have to first build it, and then run it and call *******.sh from the docker image.


### Example:
Make sure you have already reconstructed the `androidsjava-database.dump.xz` file.
```bash
docker build -f Dockerfile -t andjcl .
docker run -u root -it andjcl:latest
# Now we are inside the docker container
service postgresql start
su - andjcl
./*******.sh
```

The wole process will take ~YY hours. It runs on a machine with at least 32GB of RAM.

# Paper figures

|  Ref |  Location (relative to `$DOCKER_CONTAINER_BASE/home/{user_name}/`) | 
| --------- |:---------|
| Figure X | X/path/to/figure.pdf |

