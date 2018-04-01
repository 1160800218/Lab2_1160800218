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

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph implements Graph<String> {

    private final List<Vertex> vertices = new ArrayList<>();

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
            preweight = vertexsrc.setweight(target, weight);
        }
        else if (weight <= 0 && vertexsrc.hastarget(target)) {
            preweight = vertexsrc.targets.get(target);
            vertexsrc.targets.remove(target);
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
            temp = vertices.get(i).vertexname;
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
            if(temptarg.hastarget(target))
                newsources.put(temptarg.vertexname, temptarg.targets.get(target));
            i++;
        }  
        return newsources;
    }

    @Override
    public Map<String, Integer> targets(String source) {
        return vertices.get(hasVertex(source)).targets;
    }
    
    //判断顶点是否存在
    public int hasVertex(String vertex) {
        Vertex temp;
        for (int i = 0; (temp = vertices.get(i)) != null; i++) {
            if (temp.vertexname == vertex)
                return i;
        }
        return -1;// 不存在则返回-1
    }
    // TODO toString()

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

    public Vertex(final String vertex) {
        this.vertexname = vertex;
    }

    public String vertexname;
    public Map<String, Integer> targets = new HashMap<>();

    public int setweight(String target, int weight) {
        int preweight = targets.get(target);
        targets.put(target, weight);
        return preweight;
    } 
    
    public boolean hastarget(String target) {
        if(targets.containsKey(target))
            return true;
        else return false;
    }
    
    
}
