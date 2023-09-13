#!/bin/bash

source "db_tools"

libcore_class_system=$1
libcore_class_release=$2
libcore_class_path=$3
libcore_class_fuzzy_hash=$4
DISTANCE_FILE=$5

> "${DISTANCE_FILE}"

echo ${DISTANCE_FILE}

path_3=`echo ${DISTANCE_FILE}|rev |cut -d'/' -f1|rev`

mariadb_command="mariadb -u${db_user} -p${db_user_pwd} ${db_name} -e"

select_compatible_openjdk_classes="SELECT * FROM openjdk_classes ocs WHERE ocs.release_date<'${libcore_class_release}' AND '${libcore_class_path}' LIKE CONCAT('%', ocs.openjdk_path);"
echo ">_ ${select_compatible_openjdk_classes}"


temporay_list_of_matches_per_libcore_class="/tmp/temporary_list_of_matches_per_libcore_class_${libcore_class_system}_${path_3}"
${mariadb_command} "${select_compatible_openjdk_classes}" > "${temporary_list_of_matches_per_libcore_class}"
sed -i~ 1d "${temporary_list_of_matches_per_libcore_class}"

while read openjdk_match;do
    echo "______________"
    #echo "${openjdk_match}" | awk '(print $3}'
    openjdk_class_version=`echo "${openjdk_match}" | awk '{print $1}'`
    openjdk_path=`echo "${openjdk_match}" | awk '{print $3}'`
    openjdk_class_digest=`echo "${openjdk_match}" | awk '{print $4}'`
    echo "${openjdk_class_version} ${openjdk_path}"

    tlsh_distance=`tlsh_unittest -c ${libcore_class_fuzzy_hash} -d ${openjdk_class_digest}| awk '{print $1}'`
    echo "Distance: ${tlsh_distance}"
    #echo "${openjdk_class_version}">> ${DISTANCE_FILE} 
    #exit 1
    echo "${tlsh_distance} ${openjdk_class_version} ${libcore_class_system} ${path_3}">> ${DISTANCE_FILE}

done < "${temporary_list_of_matches_per_libcore_class}"	

