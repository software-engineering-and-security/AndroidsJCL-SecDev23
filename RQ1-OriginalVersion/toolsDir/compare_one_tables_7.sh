#!/bin/bash

source "db_tools"

WORKDIR=`pwd`
ONETABLES="${WORKDIR}/ONE_TABLES"
mkdir -p "${ONETABLES}"
DISTANCES="${ONETABLES}/DISTANCES"
mkdir -p ${DISTANCES}
CLOSEST="${ONETABLES}/CLOSEST"
mkdir -p ${CLOSEST}


mariadb_command="mariadb -u${db_user} -p${db_user_pwd} ${db_name} -e"

get_version_sqlcommand="SELECT DISTINCT(system) FROM libcore_classes;"

tmp_libcore_systems_list="/tmp/temporary_libcore_system_list"
$mariadb_command "${get_version_sqlcommand}" > "${tmp_libcore_systems_list}"
sed -i~ 1d "${tmp_libcore_systems_list}"


while read libcore_system; do 
    echo "______[${libcore_system}]"
    if [[ "${libcore_system}" =~ "7" ]]; then  
    	    #echo "______[${libcore_system}]"

	    DISTANCE_FOLDER="${DISTANCES}/${libcore_system}"
	    mkdir -p "${DISTANCE_FOLDER}"

	    LIBCORE_CLOSESTS_FILE="${CLOSEST}/${libcore_system}"
	    > "${LIBCORE_CLOSESTS_FILE}"

	    #list all java classes
	    java_classes_sqlcommand="SELECT * FROM libcore_classes WHERE system='${libcore_system}'"
	    ${mariadb_command} "${java_classes_sqlcommand}" > "/tmp/tim_classes_list_${libcore_system}"
	    sed -i~ 1d "/tmp/tim_classes_list_${libcore_system}"
	    wc -l "/tmp/tim_classes_list_${libcore_system}" 

	    while read libcore_class_line;do
		#echo "${libcore_class_line}"
		echo "${libcore_class_line}" | awk '{print $3}'
		filename=`echo ${libcore_class_line}| awk '{print $3}'|rev |cut -d'/' -f1|rev`
		subfolder=`echo ${libcore_class_line}| awk '{print $3}'|rev |cut -d'/' -f2|rev`
		subsubfolder=`echo ${libcore_class_line}| awk '{print $3}'|rev |cut -d'/' -f3|rev`
		path_3="${subsubfolder}_${subfolder}_${filename}"
		echo "${path_3}" 
		DISTANCE_FILE="${DISTANCE_FOLDER}/${path_3}"
		#former_nb_found=`wc -l ${DISTANCE_FILE} |awk '{print $1}'`
		
		#bash "${WORKDIR}/toolsDir/one_tables_tlsh_compare.sh" ${libcore_class_line} ${DISTANCE_FILE}
		
                bash "${WORKDIR}/toolsDir/one_tables_tlsh_compare_no_date.sh" ${libcore_class_line} ${DISTANCE_FILE}


		nb_found=`wc -l ${DISTANCE_FILE} |awk '{print $1}'`
		#diff_nb_found=${nb_found}-${former_nb_found}
		if [[ ${nb_found} -eq 0 ]];then
		    echo "--not found"
		    lc_system=`echo "${libcore_class_line}" | awk '{print $1}'`
		    lc_release=`echo "${libcore_class_line}" | awk '{print $2}'`
		    lc_path=`echo "${libcore_class_line}" | awk '{print $3}'`
		    lc_digest=`echo "${libcore_class_line}" | awk '{print $4}'`
		    #delete row if not found assuming the matching request is good to fasten next runs
		    delete_libcore_row_sqlcommand="DELETE FROM libcore_classes WHERE system='${lc_system}' AND release_date='${lc_release}' AND libcore_path='${lc_path}' AND fuzzy_hash='${lc_digest}';"
		    ${mariadb_command} "${delete_libcore_row_sqlcommand}"
		    rm ${DISTANCE_FILE} 
		else
		    sort -V -o ${DISTANCE_FILE} ${DISTANCE_FILE}
		    cat "${DISTANCE_FILE}"| head -n 1 >> ${LIBCORE_CLOSESTS_FILE}
		    #exit 1
		fi
		
		 


	    done < "/tmp/tim_classes_list_${libcore_system}"
	    sort -V -o ${LIBCORE_CLOSESTS_FILE}  ${LIBCORE_CLOSESTS_FILE}
            bash toolsDir/one_table_sort_and_draw.sh ${LIBCORE_CLOSESTS_FILE} ${libcore_system}


    fi
done < "${tmp_libcore_systems_list}"	
