package com.satyam.learning.neo4j.jdbc.util;

/**
 * Created by Satyam on 3/19/2016.
 */
public class ApplicationUtil {

    public static String getNeo4jUrl(){
        return "jdbc:neo4j://localhost:7474";
    }

    public static String getNeo4jUsername(){
        return "neo4j";
    }

    public static String getNeo4jPassword(){
        return "satyam";
    }

}
