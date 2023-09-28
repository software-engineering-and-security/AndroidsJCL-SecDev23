#!/bin/bash

source "dbtools"

mariadb_command="mariadb -u${db_user} -p${db_user_pwd} "

#Create db
${mariadb_command} -e "CREATE DATABASE ${db_name};"

#Import libcore table of tlsh hashes 
${mariadb_command} ${db_name} -e < timdb-libcore_classes_table.sql

#Import openJDK tables of tlsh hashes
${mariadb_command} ${db_name} -e < timdb-openjdk_classes_tables.sql
