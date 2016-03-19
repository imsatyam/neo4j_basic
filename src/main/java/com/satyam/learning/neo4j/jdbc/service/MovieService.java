package com.satyam.learning.neo4j.jdbc.service;

import com.satyam.learning.neo4j.jdbc.dao.CypherDAO;
import com.satyam.learning.neo4j.jdbc.dao.CypherDAOImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Satyam on 3/19/2016.
 */
public class MovieService {

    private CypherDAO cypherDAO;

    public void init(){
        cypherDAO = new CypherDAOImpl();
        try{
            System.out.println("1. Creating Nodes and Relationships...");
            createNodes();
            System.out.println("2. Manipulating property...");
            maniputeProperty();
            System.out.println("3. Querying...");
            query();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void createNodes() {
        List<String> cypherList = new ArrayList<String>();
        cypherList.add("CREATE (TheMatrix:Movie {title:'The Matrix', released:1999, tagline:'Welcome to the Real World'})");
        cypherList.add("CREATE (Keanu:Person {name:'Keanu Reeves', born:1964})");
        cypherList.add("CREATE (Carrie:Person {name:'Carrie-Anne Moss', born:1967})");
        cypherList.add("CREATE (Laurence:Person {name:'Laurence Fishburne', born:1961})");
        cypherList.add("CREATE (Hugo:Person {name:'Hugo Weaving', born:1960})");
        cypherList.add("CREATE (AndyW:Person {name:'Andy Wachowski', born:1967})");
        cypherList.add("CREATE (LanaW:Person {name:'Lana Wachowski', born:1965})");
        cypherList.add("CREATE (JoelS:Person {name:'Joel Silver', born:1952})");
        cypherList.add("CREATE\n" +
                "  (Keanu)-[:ACTED_IN {roles:['Neo']}]->(TheMatrix),\n" +
                "  (Carrie)-[:ACTED_IN {roles:['Trinity']}]->(TheMatrix),\n" +
                "  (Laurence)-[:ACTED_IN {roles:['Morpheus']}]->(TheMatrix),\n" +
                "  (Hugo)-[:ACTED_IN {roles:['Agent Smith']}]->(TheMatrix),\n" +
                "  (AndyW)-[:DIRECTED]->(TheMatrix),\n" +
                "  (LanaW)-[:DIRECTED]->(TheMatrix),\n" +
                "  (JoelS)-[:PRODUCED]->(TheMatrix)");

        cypherDAO.createNodesAndRelationships(cypherList);
    }

    private void maniputeProperty() {
        String cypher = "MATCH (p:Person {name: 'Carrie-Anne Moss'}) SET p.favourite = 'YES'";
        cypherDAO.executeCypher(cypher);
    }

    private void query() {
        String cypher = "match(p:Person)-[r:ACTED_IN]->(m:Movie) return p.name as Actor, m.title as Movie, m.released as Year, r.roles[0] as Role ORDER By Actor";
        System.out.println(cypherDAO.query(cypher, new LinkedHashMap<String, Object>()));
    }

}

