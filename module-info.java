module Gereja {
    requires java.sql;
    requires spring.context;
    requires spring.beans;

    opens Gereja;
    opens Gereja.entity;
    opens Gereja.repository;
    opens Gereja.view;
    opens Gereja.service;
    opens Gereja.config;
}