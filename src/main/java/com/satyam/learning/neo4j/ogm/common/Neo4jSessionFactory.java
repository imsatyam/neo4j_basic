package com.satyam.learning.neo4j.ogm.common;

import com.satyam.learning.neo4j.jdbc.util.ApplicationUtil;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

/**
 * Created by Satyam on 3/19/2016.
 * Represents a Neo4j Session Factory
 */
public enum Neo4jSessionFactory {

    INSTANCE;

    private static final SessionFactory SESSION_FACTORY = new SessionFactory(
                                                            "com.satyam.learning.neo4j.ogm.friendship.model",
                                                            "com.satyam.learning.neo4j.ogm.tree.model");

    public Session getNeo4jSession() {
        return SESSION_FACTORY.openSession();
    }

}
