DROP DATABASE IF EXISTS order_service_db;
DROP SCHEMA IF EXISTS order_service_user;
DROP USER IF EXISTS order_service_user;
DROP USER IF EXISTS order_service_apps;
CREATE USER order_service_user WITH PASSWORD 'order_service_user' CREATEDB;
CREATE USER order_service_apps WITH PASSWORD 'order_service_apps';
CREATE DATABASE order_service_db WITH OWNER = order_service_user;

--- execute using order_service_user
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE SCHEMA order_service_user AUTHORIZATION order_service_user;
ALTER USER order_service_user SET search_path to 'order_service_user';
GRANT ALL ON SCHEMA "order_service_user" TO order_service_user;
GRANT ALL ON SCHEMA "order_service_user" TO order_service_apps;