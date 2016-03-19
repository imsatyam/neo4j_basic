package com.satyam.learning.neo4j.ogm.friendship.model;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Satyam on 3/19/2016.
 * This class represents an friendship between two persons.
 */
@RelationshipEntity(type="FRIEND_OF")
public class Friendship {

    private Long id;

    @StartNode
    private Person person;

    @EndNode
    private Person friend;

    private int strength;

    private Set<String> commonHobbies;

    public Friendship() {}

    public Friendship(Person from, Person to, int strength) {
        this.person = from;
        this.friend = to;
        this.strength = strength;
    }

    public Friendship(Person person, Person friend, int strength, Set<String> commonHobbies) {
        this.person = person;
        this.friend = friend;
        this.strength = strength;
        this.commonHobbies = commonHobbies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getFriend() {
        return friend;
    }

    public void setFriend(Person friend) {
        this.friend = friend;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setCommonHobbies(Set<String> commonHobbies) {
        this.commonHobbies = commonHobbies;
    }

    public Set<String> getCommonHobbies() {
        return  null != commonHobbies
                ? Collections.unmodifiableSet(commonHobbies)
                : null;
    }

    public void addCommonHobby(String hobby){
        if(null == commonHobbies){
            commonHobbies =  new HashSet<String>();
        }
        commonHobbies.add(hobby);
    }
}
