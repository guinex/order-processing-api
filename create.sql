CREATE DATABASE ecommerce;
CREATE USER postgres WITH PASSWORD '12345678';
GRANT ALL PRIVILEGES ON DATABASE ecommerce TO postgres;

\connect ecommerce

ALTER TABLE public.users
    OWNER to postgres;
