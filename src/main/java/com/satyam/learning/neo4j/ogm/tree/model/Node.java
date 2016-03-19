package com.satyam.learning.neo4j.ogm.tree.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Satyam on 3/19/2016.
 */
@NodeEntity
public class Node implements Comparable{

    private Long id;

    private String name;

    @Relationship(type = "REL", direction = "OUTGOING")
    private Node parent;

    @Relationship(type = "REL", direction = "INCOMING")
    private SortedSet<Node> children = new TreeSet<Node>();

    public Node() {}

    public Node(String name) {
        this.name = name;
    }

    public Node setParent(Node parent) {
        parent.children.add(this);
        this.parent = parent;
        return this;
    }

    public Long getId() {
        return id;
    }

    @Relationship(type = "REL", direction = "INCOMING")
    public Set<Node> getChildren() {
        return children;
    }

    @Relationship(type = "REL", direction = "INCOMING")
    public void setChildren(SortedSet<Node> children) {
        this.children = children;
    }

    public Node getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        Node that = (Node)o;
        if (this.name != null) {
            if (that.name != null) {
                return this.name.compareTo(that.name);
            }
            else {
                return 1;
            }
        }
        return -1;
    }

}
