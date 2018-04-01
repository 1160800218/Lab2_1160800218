/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph implements Graph<String> {

    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();

    // Abstraction function:
    // TODO
    // Representation invariant:
    // TODO
    // Safety from rep exposure:
    // TODO

    // TODO constructor

    // TODO checkRep
    
    @Override
    public boolean add(String vertex) {
        if (vertices.contains(vertex))
            return false;
        else {
            vertices.add(vertex);
            return true;
        }
    }

    @Override
    public int set(String source, String target, int weight) {
        if (!vertices.contains(source))
            add(source);
        if (!vertices.contains(target))
            add(target);
        int i = 0, hasedge = 0, preweight;
        Edge temp;

        // test whether edge exist or not
        while ((temp = edges.get(i)) != null) {
            if (temp.toString() == source + "->" + target) {
                hasedge = 1;
                break;
            }
            i++;
        }
        if (hasedge == 0 && weight > 0) {
            Edge newedge = new Edge(source, target, weight);
            edges.add(newedge);
            preweight = 0;
        } else if (hasedge == 1 && weight > 0) {
            preweight = temp.getweight();
            edges.remove(i);
            Edge newedge = new Edge(source, target, weight);
            edges.add(newedge);
        }
        else if (hasedge == 1 && weight == 0) {
            edges.remove(i);
            preweight = 0;
        } else
            preweight = 0;
        return preweight;
    }

    @Override
    public boolean remove(String vertex) {
        if (vertices.contains(vertex)) {
            vertices.remove(vertex);
            return true;
        } else
            return false;
    }

    @Override
    public Set<String> vertices() {
        return vertices;
    }

    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> sources = new HashMap<>();
        int i = 0;
        Edge temp;
        while ((temp = edges.get(i)) != null) {
            if (temp.gettarget() == target) {
                sources.put(temp.getsource(), temp.getweight());
            }
            i++;
        }
        return sources;
    }

    @Override
    public Map<String, Integer> targets(String source) {
        Map<String, Integer> targets = new HashMap<>();
        int i = 0;
        Edge temp;
        while ((temp = edges.get(i)) != null) {
            if (temp.getsource() == source) {
                targets.put(temp.gettarget(), temp.getweight());
            }
            i++;
        }
        return targets;
    }

    // TODO toString()
    
    

}

/**
 * TODO specification Immutable. This class is internal to the rep of
 * ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Edge {

    // TODO fields

    // Abstraction function:
    // TODO
    // Representation invariant:
    // TODO
    // Safety from rep exposure:
    // TODO

    // TODO constructor

    // TODO checkRep

    // TODO methods

    // TODO toString()

    public Edge(String getsource, String gettarget, int getweight) {
        source = getsource;
        target = gettarget;
        weight = getweight;
    }

    private final String source, target;
    private final int weight;

    public String getsource() {
        return source;
    }

    public String gettarget() {
        return target;
    }
    
    public int getweight() {
        return weight;
    }

    public String toString() {
        System.out.println(source + "->" + target);
        return source + "->" + target;
    }

}
