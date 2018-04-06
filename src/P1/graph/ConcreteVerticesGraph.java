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
public class ConcreteVerticesGraph<L> implements Graph<L> {

    private final List<Vertex<L>> vertices = new ArrayList<>();

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
    public boolean add(L vertex) {
        if (hasVertex(vertex) != -1)
            return false;
        Vertex<L> vertexadd = new Vertex<>(vertex);
        boolean added = vertices.add(vertexadd);
        return added;
    }

    @Override
    public int set(L source, L target, int weight) {
        add(source);
        add(target);
        Vertex<L> vertexsrc = vertices.get(hasVertex(source));
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
    public boolean remove(L vertex) {
        int index;
        if ((index = hasVertex(vertex)) != -1) {
            vertices.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public Set<L> vertices() {
        Set<L> newvertices = new HashSet<>();
        L temp;
        Iterator<Vertex<L>> it = vertices.iterator();
        int i = 0;
        while (it.hasNext()) {
            temp = vertices.get(i).getName();
            newvertices.add(temp);
            i++;
        }
        return newvertices;
    }

    @Override
    public Map<L, Integer> sources(L target) {
        Map<L, Integer> newsources = new HashMap<>();
        Iterator<Vertex<L>> it = vertices.iterator();
        Vertex<L> temptarg;
        while (it.hasNext()) {
            temptarg = it.next();
            if(temptarg.hasTarget(target))
                newsources.put(temptarg.getName(), temptarg.getTargets().get(target));
        }  
        return newsources;
    }

    @Override
    public Map<L, Integer> targets(L source) {
        return vertices.get(hasVertex(source)).getTargets();
    }
    
    //判断顶点是否存在
    public int hasVertex(L vertex) {
        Iterator<Vertex<L>> it = vertices.iterator();
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
class Vertex<L> {

    // TODO fields
    private final L vertexname;
    private final Map<L, Integer> targets = new HashMap<>();
    
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
    public Vertex(final L vertex) {
        this.vertexname = vertex;
    }
    
    // TODO checkRep
    public void checkRep() {
        assert vertexname != null;
        Iterator<Map.Entry<L, Integer>> it = targets.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<L, Integer> entry = it.next();
            assert entry.getKey() != null;
            assert entry.getKey() != vertexname;
            assert entry.getValue() >= 0;
        }
    }
    
    // TODO methods
    public L getName() {
        checkRep();
        return vertexname;
    }
    
    public boolean hasTarget(L target) {
        checkRep();
        if(targets.containsKey(target)) {
            checkRep();
            return true;
        }
        else {
            checkRep();
            return false;
        }
    }
    
    public int setWeight(L target, int weight) {
        checkRep();
        int preweight = 0;
        if(hasTarget(target)) {
            preweight = targets.get(target);
            targets.put(target, weight);
        }
        else targets.put(target, weight);
        checkRep();
        return preweight;
    } 
    
    public Map<L, Integer> getTargets(){
        checkRep();
        return targets;
    }
    // TODO toString()
    public String toString() {
        checkRep();
        List<String> ret = new ArrayList<>();
        Iterator<Map.Entry<L, Integer>> it = targets.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<L, Integer> entry = it.next();
            ret.add(getName().toString() + "->" + entry.getKey().toString());
        }
        checkRep();
        return ret.stream()
                .collect(Collectors.joining("\n"));
    }
}
