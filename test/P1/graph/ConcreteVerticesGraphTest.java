/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    // Create a new empty graph
    // add 4 different vertices and 6 different edges to the graph
    // add a repeated vertex to test rechecking function
    // expected: return a String consist of 6 different "x->y" separated by "\n" 
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test public void testToString() {
        Graph<String> graph = emptyInstance();
        graph.add("a");
        graph.add("a");
        graph.add("b");
        graph.add("c");
        graph.add("d");
        graph.set("a", "b", 1);
        graph.set("a", "c", 2);
        graph.set("b", "c", 1);
        graph.set("b", "d", 2);
        graph.set("d", "a", 3);
        graph.set("d", "b", 2);
        assertEquals("a->b" + "\n" + "a->c" + "\n" + "b->c" + "\n" + "b->d" + "\n" + "d->a" + "\n" + "d->b", graph.toString());
    }
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    // TODO
    
    // TODO tests for operations of Vertex
    @Test
    public void testGetName() {
        Vertex<String> test = new Vertex<>("jeff");
        assertEquals("jeff", test.getName());
    }
    @Test
    public void testHasTarget() {
        Vertex<String> test = new Vertex<>("jeff");
        assertEquals(false, test.hasTarget("Harbin"));
    }
    @Test
    public void testSetTarget() {
        Vertex<String> test1 = new Vertex<>("jeff");
        Vertex<String> test2 = new Vertex<>("Harbin");
        test1.setWeight("Harbin", 2016);
        assertEquals(2016, test1.setWeight("Harbin", 2020));
        assertEquals(0, test2.setWeight("jeff", 2018));
    }
    @Test
    public void testGetTargets() {
        Vertex<String> test1 = new Vertex<>("jeff");
        Map<String, Integer> cities = new HashMap<>();
        test1.setWeight("Harbin", 2016);
        test1.setWeight("Amoy", 2013);
        test1.setWeight("Lanzhou", 2017);
        cities.put("Harbin", 2016);
        cities.put("Amoy", 2013);
        cities.put("Lanzhou", 2017);
        assertEquals(cities, test1.getTargets());
    }
}
