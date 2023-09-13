#!/bin/bash


WORKDIR="/home/triom/android_java_sha256/ONE_TABLES/PROXIMITY"
STATS_DIR="${WORKDIR}/STATS"
mkdir -p "${STATS_DIR}"


while read version;do
    declare -A path_to_distances
    declare -A nb_elements

    > "${STATS_DIR}/${version}"

    while read distance_line;do
        dist=`echo ${distance_line} | awk '{print $1}'`
	path=`echo ${distance_line} | awk '{print $4}'`

	dir_0=`echo ${path} |cut -d'_' -f1`
	dir_1=`echo ${path} |cut -d'_' -f2`
        key="${dir_0}_${dir_1}"
        #echo "key: ${key}"
	#echo "dis: ${dist}"
	if [[ -v path_to_distances["${key}"] ]];then
	   path_to_distances["${key}"]=$((path_to_distances["${key}"]+dist))
	   ((nb_elements["${key}"]++)) 
	    #echo "dist_agg:${path_to_distances[${key}]}" 
	    #echo "#_agg:   ${nb_elements[${key}]}"
            #exit 1
       else
	    path_to_distances["${key}"]=${dist}
	    nb_elements["${key}"]=1
	    #echo "dist:${path_to_distances[${key}]}" 
	    #echo "#:   ${path_to_distances[${key}]}"
	    
	fi	
	#path_to_distances["${key}"]=$((path_to_distances["${key}"]+dist))
	#((nb_elements["${key}"]++))

    done < "${WORKDIR}/inputDir/${version}"	   

    

    for key in "${!path_to_distances[@]}";do
    #    total=0
        echo "key: ${key}"
    #	local_array=path_to_distances["${key}"]
    #    for local_dist in ${local_array[@]}; do
    #	    total=$((total+local_dist))       	
    #    done
        total=${path_to_distances["${key}"]}
	elem=${nb_elements["${key}"]}
        echo "tot: ${total}"
	echo "elem: ${elem}"
	
        average=`bc -l <<< ${total}/${elem}`
	echo "avg: ${average}"
        
        echo "${average} ${key}" >> "${STATS_DIR}/${version}"
        sort -V -o  "${STATS_DIR}/${version}" "${STATS_DIR}/${version}"
	#exit 1
    done

done < "${WORKDIR}/inputDir/list_versions"	
