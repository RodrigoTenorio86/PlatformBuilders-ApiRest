FROM mysql:8.0.18
MAINTAINER RODRIGO TENORIO DE BOA VENTURA<boaventura19@yahoo.com.br>
ENV MYSQL_ROOT_PASSWORD=Rr123
ADD ./banco.sql /docker-entrypoint-initdb.d
COPY banco.sql /docker-entrypoint-initdb.d/
EXPOSE 3306
