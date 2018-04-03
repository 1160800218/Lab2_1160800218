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
public class ConcreteVerticesGraph implements Graph<String> {

    private final List<Vertex> vertices = new ArrayList<>();

    // Abstraction function:
    // a set of source vertices of the graph 
    // Representation invariant:
    // 
    // Safety from rep exposure:
    // vertices is private and final
    

    // TODO constructor
    public ConcreteVerticesGraph() {
        
    }
    
    // TODO checkRep

    @Override
    public boolean add(String vertex) {
        if (hasVertex(vertex) != -1)
            return false;
        Vertex vertexadd = new Vertex(vertex);
        boolean added = vertices.add(vertexadd);
        return added;
    }

    @Override
    public int set(String source, String target, int weight) {
        add(source);
        add(target);
        Vertex vertexsrc = vertices.get(hasVertex(source));
        int preweight;
        if (weight > 0) {
            preweight = vertexsrc.setWeight(target, weight);
        }
        else if (weight <= 0 && vertexsrc.hasTarget(target)) {
            preweight = vertexsrc.getTargets().get(target);
            vertexsrc.getTargets().remove(target);
        }
        else preweight = 0;
        return preweight;
    }

    @Override
    public boolean remove(String vertex) {
        int index;
        if ((index = hasVertex(vertex)) != -1) {
            vertices.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public Set<String> vertices() {
        Set<String> newvertices = new HashSet<>();
        String temp;
        Iterator<Vertex> it = vertices.iterator();
        int i = 0;
        while (it.hasNext()) {
            temp = vertices.get(i).getName();
            newvertices.add(temp);
            i++;
        }
        return newvertices;
    }

    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> newsources = new HashMap<>();
        Iterator<Vertex> it = vertices.iterator();
        int i = 0;
        Vertex temptarg;
        while (it.hasNext()) {
            temptarg = vertices.get(i);
            if(temptarg.hasTarget(target))
                newsources.put(temptarg.getName(), temptarg.getTargets().get(target));
            i++;
        }  
        return newsources;
    }

    @Override
    public Map<String, Integer> targets(String source) {
        return vertices.get(hasVertex(source)).getTargets();
    }
    
    //判断顶点是否存在
    public int hasVertex(String vertex) {
        Iterator<Vertex> it = vertices.iterator();
        int index = 0;
        while(it.hasNext()) {
            if (it.next().getName() == vertex)
                return index;
            index++;
        }
        return -1;// 不存在则返回-1
    }
    // TODO toString()
    public String toString() {
        return vertices.stream()
                .filter(vertice -> !vertice.getTargets().isEmpty())
                .map(vertice -> vertice.toString())
                .collect(Collectors.joining("\n"));
    }
}

/**
 * TODO specification Mutable. This class is internal to the rep of
 * ConcreteVerticesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Vertex {

    // TODO fields
    private final String vertexname;
    private final Map<String, Integer> targets = new HashMap<>();
    
    // Abstraction function:
    // represents the name of source vertex and all of vertices and distances connecting to it  
    // Representation invariant:
    // vertexname should be non-null
    // target vertex shouldn't be itself
    // the key should be String type
    // the value >= 0
    // Safety from rep exposure:
    // all the fields are private
    // vertexname are final
    

    // TODO constructor
    public Vertex(final String vertex) {
        this.vertexname = vertex;
    }
    
    // TODO checkRep
    public void checkRep() {
        assert vertexname != null;
        Iterator<Map.Entry<String, Integer>> it = targets.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            assert entry.getKey() != null;
            assert entry.getKey() != vertexname;
            assert entry.getValue() >= 0;
        }
    }
    
    // TODO methods
    public String getName() {
        return vertexname;
    }
    
    public int setWeight(String target, int weight) {
        int preweight = 0;
        if(hasTarget(target)) {
            preweight = targets.get(target);
            targets.put(target, weight);
        }
        else targets.put(target, weight);
        return preweight;
    } 
    
    public boolean hasTarget(String target) {
        if(targets.containsKey(target))
            return true;
        else return false;
    }
    
    public Map<String, Integer> getTargets(){
        return targets;
    }
    // TODO toString()
    public String toString() {
        List<String> ret = new ArrayList<>();
        Iterator<Map.Entry<String, Integer>> it = targets.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            ret.add(vertexname + "->" + entry.getKey());
        }
        return ret.stream()
                .collect(Collectors.joining("\n"));
    }
}
