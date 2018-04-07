/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    // Create a new empty graph
    // add 4 different vertices and 4 different edges to the graph
    // add a repeated vertex to test rechecking function
    // expected: return a String consist of 4 different "x->y" separated by "\n" 
    
    // tests for ConcreteEdgesGraph.toString()
    Graph<String> graph = emptyInstance();
    
    @Test 
    public void testToString() {
        graph.add("a");
        graph.add("a");
        graph.add("b");
        graph.add("c");
        graph.add("d");
        graph.set("a", "b", 1);
        graph.set("a", "c", 2);
        graph.set("b", "c", 1);
        graph.set("d", "a", 3);
        assertEquals("a->b" + "\n" + "a->c" + "\n" + "b->c" + "\n" + "d->a", graph.toString());
    }
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   TODO
    
    // tests for operations of Edge
    @Test
    public void testGetSource() {
        Edge<String> test = new Edge<>("a","b",1);
        assertEquals("a",test.getsource());
    }
    @Test
    public void testGetTarget() {
        Edge<String> test = new Edge<>("a","b",1);
        assertEquals("b",test.gettarget());
    }
    @Test
    public void testGetWeight() {
        Edge<String> test = new Edge<>("a","b",1);
        assertEquals(1,test.getweight());
    }
}
