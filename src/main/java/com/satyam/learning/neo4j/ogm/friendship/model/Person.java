package com.satyam.learning.neo4j.ogm.friendship.model;

import com.satyam.learning.neo4j.ogm.friendship.FriendshipType;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Satyam on 3/19/2016.
 */
@NodeEntity
public class Person {
    private Long id;

    private String name;

    @Relationship(type="FRIEND_OF")
    private List<Friendship> friends;

    public Person(){}
    public Person (String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<Friendship> getFriends() {
        return null != friends ? Collections.unmodifiableList(friends) : null;
    }

    public void addFriend(Person friend, FriendshipType friendshipType) {
        if(null == friends){
            friends = new ArrayList<Friendship>();
        }
        friends.add(new Friendship(this, friend, friendshipType.getFriendshipStrength()));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFriends(List<Friendship> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        String personString =  "Person{name= " + name + ", friends=[";
        if(null != friends) {
            for (Friendship f : friends) {
                personString = personString + f.getFriend().getName() + ", ";
            }
        }
        personString = personString + "]}";
        return personString;
    }
}
