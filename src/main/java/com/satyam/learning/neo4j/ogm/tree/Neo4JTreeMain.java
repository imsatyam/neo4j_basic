package com.satyam.learning.neo4j.ogm.tree;

import com.satyam.learning.neo4j.ogm.common.Neo4jSessionFactory;
import com.satyam.learning.neo4j.ogm.tree.model.Node;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;

import java.util.Collection;

/**
 * Created by Satyam on 3/19/2016.
 */
public class Neo4JTreeMain {
    public static void main(String[] args) {
        Node parent1 = new Node("Parent 1");
        Node parent2 = new Node("Parent 3");

        Node child1 = new Node("Child 1");
        Node child2 = new Node("Child 2");
        Node child3 = new Node("Child 3");

        Transaction tx = null;
        try {
            Session session = Neo4jSessionFactory.INSTANCE.getNeo4jSession();
            tx = session.beginTransaction();

            child1.setParent(parent1);
            child2.setParent(parent2);
            child3.setParent(parent1);
            session.save(parent1);
            session.save(child2);
            tx.commit();
        }catch (Exception exception){
            exception.printStackTrace();
            tx.rollback();
        }
        tx.close();

        Session session = Neo4jSessionFactory.INSTANCE.getNeo4jSession();
        Collection<Node> nodes = session.loadAll(Node.class);
        for(Node node : nodes){

            StringBuilder builder = new StringBuilder();
            builder.append("{");
            builder.append("Name=").append(node.getName()).append(", ");
            builder.append("Parent=").append(null != node.getParent() ? node.getParent().getName() : "NONE").append(", ");
            builder.append("Children=");
            if(null != node.getChildren()) {
                builder.append("[");
                for (Node nd : node.getChildren()) {
                    builder.append(nd.getName()).append(", ");
                }
                builder.append("]");
            }
            else {
                builder.append("NONE");
            }
            System.out.println(builder.toString());
        }
    }
}
