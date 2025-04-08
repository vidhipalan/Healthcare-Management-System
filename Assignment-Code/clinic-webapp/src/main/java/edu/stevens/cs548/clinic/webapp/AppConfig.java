package edu.stevens.cs548.clinic.webapp;


import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@DataSourceDefinition( name="java:global/jdbc/cs548",
        className="org.postgresql.ds.PGSimpleDataSource",
        user="cs548user",
        password="dbuserpw",
        databaseName="cs548",
        serverName="cs548db", portNumber=5432)
public class AppConfig {
}
