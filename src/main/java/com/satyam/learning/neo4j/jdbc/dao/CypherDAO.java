package com.satyam.learning.neo4j.jdbc.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Satyam on 3/19/2016.
 */
public interface CypherDAO {
    void createNodesAndRelationships(List<String> cypherList);
    Iterator<Map<String,Object>> query(String statement, Map<String,Object> params);
    void executeCypher(String cypher);
}
