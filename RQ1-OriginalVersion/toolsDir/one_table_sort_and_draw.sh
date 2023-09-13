#!/bin/bash
set -e -x

LAUNCH_DIR=`pwd`
source "${LAUNCHDIR}/toolsDir/db_tools"
source "${LAUNCHDIR}/toolsDir/PATHES"


WORKDIR="${LAUNCH_DIR}/ONE_TABLES"
UNIQDIR="${WORKDIR}/UNIQ"
mkdir -p "${UNIQDIR}"
GNUPLOTREADYDIR="${WORKDIR}/GNUPLOTREADYDIR"
mkdir -p "${GNUPLOTREADYDIR}"
GRAPHDIR="${WORKDIR}/GRAPHDIR"
mkdir -p "${GRAPHDIR}"


CLOSEST_OPENJDK_FILE=$1
libcore_system=$2


declare -A gnuplotColours
#gnuplotColours["jdk"]=""
#colours
#gnuplotColours["jdk1.5"]="red"
#gnuplotColours["jdk1.6"]="orange"
#gnuplotColours["jdk1.7"]="yellow"
#gnuplotColours["jdk1.8"]="greenyellow"
#gnuplotColours["jdk-9"]="green"
#gnuplotColours["jdk-10"]="aquamarine"
#gnuplotColours["jdk-11"]="web-blue"
#gnuplotColours["jdk-12"]="skyblue"
#gnuplotColours["jdk-13"]="steelblue"
#gnuplotColours["jdk-14"]="navy"
#gnuplotColours["jdk-17"]="dark-magenta"
#gnuplotColours["jdk-18"]="purple"
#gnuplotColours["jdk-19"]="light-magenta"
#gnuplotColours["jdk-20"]="orchid"

#B-W
gnuplotColours["jdk1.5"]="#000000"
gnuplotColours["jdk1.6"]="#ffffff"
gnuplotColours["jdk1.7"]="#000000"
gnuplotColours["jdk1.8"]="#ffffff"
gnuplotColours["jdk-9"]="#bbbbbb"
gnuplotColours["jdk-10"]="#444444"
gnuplotColours["jdk-11"]="#ffffff"
gnuplotColours["jdk-12"]="#bbbbbb"
gnuplotColours["jdk-13"]="#777777"
gnuplotColours["jdk-14"]="#333333"
gnuplotColours["jdk-17"]="#ffffff"
gnuplotColours["jdk-18"]="#bbbbbb"
gnuplotColours["jdk-19"]="#777777"
gnuplotColours["jdk-20"]="#333333"


	#sort
	sort -V -o "${CLOSEST_OPENJDK_FILE}" "${CLOSEST_OPENJDK_FILE}"
	#clean CLOSEST_openjdk: if two lines are the same: delete one
	wc -l "${CLOSEST_OPENJDK_FILE}"
	uniq ${CLOSEST_OPENJDK_FILE} |wc -l #> "${CLOSEST_OPENJDK}"
        wc -l "${CLOSEST_OPENJDK_FILE}"

        
        #extract first column and prepare merge in major versions
        mkdir -p "${WORKDIR}/ONE_COLUMN"
	ONECOLUMN_FILE="${WORKDIR}/ONE_COLUMN/${libcore_system}_onecolumn"
	> ${ONECOLUMN_FILE}
        while read closest_openjdk_line; do
            openjdk_tag=`echo "${closest_openjdk_line}"|awk '{print $2}'`
	    openjdk_major_tag=`echo ${openjdk_tag}|sed -r 's/(-|\.)/_/g'|cut -d'_' -f 2`
	    #clean
	    if [[ "${openjdk_tag}" != *"jdk1"* ]]; then
                clean_major_tag=`echo "${openjdk_tag::6}" |sed -r 's/(-|\.)/_/g' `
		if [[ ${openjdk_tag} = *"_9"* ]];then
                    clean_major_tag=`echo "${openjdk_tag::5}" | sed -r 's/(-|\.)/_/g'`
		fi	
	    else
                clean_major_tag=`echo "${openjdk_tag::6}"| sed -r 's/(-|\.)/_/g'`		    
	    fi	    
	    #store
	    echo "${clean_major_tag}">> "${ONECOLUMN_FILE}"
	done < "${CLOSEST_OPENJDK_FILE}"	
	sort -V -o "${ONECOLUMN_FILE}" "${ONECOLUMN_FILE}"
        #uniq
	uniq -c "${ONECOLUMN_FILE}" > "${UNIQDIR}/${libcore_system}"
	#add third column with comuter rgb color
	echo "#nb  version  colour"> "${GNUPLOTREADYDIR}/${libcore_system}"
        while read stat_line ; do
	        echo "______ ${stat_line} ____"
	        jdk_count=`echo ${stat_line}| awk '{print $1}'`
		major_version=`echo ${stat_line} | awk '{print $2}'`
		echo ${major_version}
		major_version_number=`echo ${major_version}|cut -d'_' -f2`
		echo ${major_version_number}
		if [ ${major_version_number} -ge 9 ];then
		    openjdk_major=`echo ${major_version} | sed -r 's/_/-/g'`
		else
		    openjdk_major=`echo ${stat_line}|sed -r 's/_/\./g' | awk '{print $2}'`
		fi
		echo ${openjdk_major}
	        echo  ${gnuplotColours[${openjdk_major}]}
                echo "${jdk_count} ${openjdk_major} ${gnuplotColours[${openjdk_major}]}" >> "${GNUPLOTREADYDIR}/${libcore_system}"	       
 
	done < 	"${UNIQDIR}/${libcore_system}"
	#save as gnuplot ready
        cp  "${GNUPLOTREADYDIR}/${libcore_system}" "${GNUPLOTWORKDIR}/android_tag.dat"
	#launch gnuplot script
        gnuplot toolsDir/java_pie_plot.gnu > log/java_pie_gnuplot
	mv "graphDir/circle.pdf" "${GRAPHDIR}/${libcore_system}.pdf" 
	rm "${GNUPLOTWORKDIR}/android_tag.dat"
