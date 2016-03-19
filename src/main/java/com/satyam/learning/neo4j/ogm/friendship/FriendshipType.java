package com.satyam.learning.neo4j.ogm.friendship;

/**
 * Created by Satyam on 3/19/2016.
 */
public enum FriendshipType {
    ACQUAINTANCE(2),
    CASUAL(5),
    GOOD(7),
    CHILDHOOD(10);

    private int friendshipStrength;
    FriendshipType(int friendshipStrength){
        this.friendshipStrength = friendshipStrength;
    }

    public int getFriendshipStrength() {
        return friendshipStrength;
    }
}
