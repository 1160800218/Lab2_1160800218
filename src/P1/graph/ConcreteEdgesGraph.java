/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {

    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();

    // Abstraction function:
    // a set of vertices of the graph and a set of edges of the graph with weight
    // Representation invariant:
    // vertices should be a String set
    // edges should be a Edge list, the number of edges should be no more than possible number
    // Safety from rep exposure:
    // vertices and edges are private and final
    // edges is mutable, set() will create a new edge to keep the Edge immutable

    public ConcreteEdgesGraph() {
        
    }
    
    public void checkRep() {}
    @Override
    public boolean add(L vertex) {
        if (vertices.contains(vertex))
            return false;
        else {
            vertices.add(vertex);
            return true;
        }
    }

    @Override
    public int set(L source, L target, int weight) {
        if (!vertices.contains(source))
            add(source);
        if (!vertices.contains(target))
            add(target);
        int index = 0, hasedge = 0, preweight;
        Edge<L> temp;

        // test whether edge exist or not
        Iterator<Edge<L>> it = edges.iterator();
        while (it.hasNext()) {
            temp = it.next();
            if (temp.getsource().equals(source) && temp.gettarget().equals(target)) {
                hasedge = 1;
                break;
            }
            index++;
        }
        if (hasedge == 0 && weight > 0) {
            Edge<L> newedge = new Edge<>(source, target, weight);
            edges.add(newedge);
            preweight = 0;
        } else if (hasedge == 1 && weight > 0) {
            preweight = edges.get(index).getweight();
            edges.remove(index);
            Edge<L> newedge = new Edge<>(source, target, weight);
            edges.add(newedge);
        }
        else if (hasedge == 1 && weight == 0) {
            preweight = edges.get(index).getweight();
            edges.remove(index);
        } else
            preweight = 0;
        return preweight;
    }

    @Override
    public boolean remove(L vertex) {
        if (vertices.contains(vertex)) {
            vertices.remove(vertex);
            return true;
        } else
            return false;
    }

    @Override
    public Set<L> vertices() {
        return vertices;
    }

    @Override
    public Map<L, Integer> sources(L target) {
        Map<L, Integer> sources = new HashMap<>();
        Iterator<Edge<L>> it = edges.iterator();
        while (it.hasNext()) {
            Edge<L> temp = it.next();
            if (temp.gettarget().equals(target)) {
                sources.put(temp.getsource(), temp.getweight());
            }
        }
        return sources;
    }

    @Override
    public Map<L, Integer> targets(L source) {
        Map<L, Integer> targets = new HashMap<>();
        Iterator<Edge<L>> it = edges.iterator();
        while (it.hasNext()) {
            Edge<L> temp = it.next();
            if (temp.getsource().equals(source)) {
                targets.put(temp.gettarget(), temp.getweight());
            }
        }
        return targets;
    }

    public String toString() {
        return edges.stream()
                .map(edge -> edge.toString())
                .collect(Collectors.joining("\n"));
    }
    

}

/**
 * TODO specification Immutable. This class is internal to the rep of
 * ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Edge<L> {

    // TODO fields
    private final L source, target;
    private final int weight;
    
    // Abstraction function:
    // represents the start and the end of the edge, and the distance between they two
    // Representation invariant:
    // source and target should be non-null String type
    // source and target should be different
    // weight >= 0
    // Safety from rep exposure:
    // all fields are private and final
    // Edge is immutable, use getXXX methods to get the fields' info  

    public Edge(L newsource, L newtarget, int newweight) {
        source = newsource;
        target = newtarget;
        weight = newweight;
    }
    
    public void checkRep() {
        assert source != null;
        assert target != null;
        assert source != target;
        assert weight >= 0;
    }
    
    public L getsource() {
        checkRep();
        return source;
    }

    public L gettarget() {
        checkRep();
        return target;
    }
    
    public int getweight() {
        checkRep();
        return weight;
    }

    // TODO toString()
    public String toString() {
        checkRep();
        return getsource().toString() + "->" + gettarget().toString();
    }

}
