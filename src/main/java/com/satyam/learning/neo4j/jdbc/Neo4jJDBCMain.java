package com.satyam.learning.neo4j.jdbc;

import com.satyam.learning.neo4j.jdbc.service.MovieService;

/**
 * Created by Satyam on 3/19/2016.
 */
public class Neo4jJDBCMain {
    public static void main(String[] args) {
        new MovieService().init();
    }
}
