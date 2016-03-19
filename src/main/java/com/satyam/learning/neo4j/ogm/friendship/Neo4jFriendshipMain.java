package com.satyam.learning.neo4j.ogm.friendship;

import com.satyam.learning.neo4j.ogm.common.Neo4jSessionFactory;
import com.satyam.learning.neo4j.ogm.friendship.model.Person;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;

import java.util.Collection;
import java.util.List;

/**
 * Created by Satyam on 3/19/2016.
 */
public class Neo4jFriendshipMain {
    public static void main (String[] args) {

        Person a = new Person("A");
        Person b = new Person("B");
        Person c = new Person("C");
        Person d = new Person("D");

        //Example of transaction
        Transaction tx = null;
        try {
            Session session = Neo4jSessionFactory.INSTANCE.getNeo4jSession();
            tx = session.beginTransaction();

            session.save(b);
            a.addFriend(b, FriendshipType.GOOD);
            session.save(a);

            b = session.load(Person.class, b.getId());
            b.addFriend(c, FriendshipType.ACQUAINTANCE);
            b.addFriend(d, FriendshipType.CHILDHOOD);
            session.save(b);
            tx.commit();
        }catch (Exception exception){
            exception.printStackTrace();
            tx.rollback();
        }
        tx.close();

        //Example of Load ALL
        Session session = Neo4jSessionFactory.INSTANCE.getNeo4jSession();
        Collection<Person> persons = session.loadAll(Person.class);
        for(Person person : persons){
            System.out.println(person);
        }

    }
}
