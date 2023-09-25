#!/bin/bash

WORKDIR="/home/triom/android_java_sha256/"
ONE_COLUMN="${WORKDIR}/ONE_TABLES/ONE_COLUMN"
GNUPLOTREADYDIR="${WORKDIR}/ONE_TABLES/GNUPLOTREADYDIR"


DATA_FILE="${GNUPLOTREADYDIR}/ojluni_class_per_libcore.dat"
echo "${DATAFILE}"
echo "#version nb_ojluni"> ${DATA_FILE}

ls ${ONE_COLUMN}
ls ${ONE_COLUMN} > "/tmp/tim_list_ojluni_class_per_libcore"

sort -V -o "/tmp/tim_list_ojluni_class_per_libcore" "/tmp/tim_list_ojluni_class_per_libcore"

while read one_column_name_file;do
     echo "${one_column_name_file}"	
     nb_oj_class=`wc -l "${ONE_COLUMN}/${one_column_name_file}" | awk '{print $1}'`
     if [[ ${one_column_name_file} =~ *"android"* ]];then
	     version=`echo ${one_column_name_file}|sed -r 's/(-|\.)/_/'g | cut -d'_' -f2`
     else
         version=`echo ${one_column_name_file}| cut -d'_' -f1`	     
     fi

     echo "${version} ${nb_oj_class}" >> ${DATA_FILE}


done < "/tmp/tim_list_ojluni_class_per_libcore"

gnuplot toolsDir/ojluni_class_per_libcore.gnu

