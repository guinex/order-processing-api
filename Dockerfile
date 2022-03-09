FROM postgres:latest
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 12345678
ADD create.sql /docker-entrypoint-initdb.d/


