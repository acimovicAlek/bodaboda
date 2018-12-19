FROM mysql

# Environment variables
ENV MYSQL_ROOT_PASSWORD password

# Allows you to change the value of "max_allowed_packet"
ADD ["mysqlconf/gatewaymy.cnf", "/etc/mysql/conf.d/conf_mysql.cnf"]

# Create Database
RUN	mkdir /usr/sql
RUN	chmod 644 /usr/sql


RUN /etc/init.d/mysql start && \
        mysql -u root -p${MYSQL_ROOT_PASSWORD} -e "CREATE DATABASE db" && \
    	mysql -u root -p${MYSQL_ROOT_PASSWORD} -e "GRANT ALL PRIVILEGES ON *.* TO 'user'@'%' IDENTIFIED BY 'password'"  && \
    	mysql -u root -p${MYSQL_ROOT_PASSWORD} -e "CREATE TABLE 'db'.'__EFMigrationsHistory' ( 'MigrationId' nvarchar(150) NOT NULL, 'ProductVersion' nvarchar(32) NOT NULL, PRIMARY KEY ('MigrationId') )"
