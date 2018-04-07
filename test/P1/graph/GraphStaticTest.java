/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }
    
    // test Integer vertex label types in Problem 3.2
    @Test
    public void testIntegerVertex() {
        Graph<Integer> graph = Graph.empty();
        int vertex_1 = 1;
        int vertex_2 = 2;
        int vertex_3 = 3;
        boolean add_1 = graph.add(vertex_1);
        boolean add_2 = graph.add(vertex_1);
        boolean add_3 = graph.add(vertex_3);
        int set_1 = graph.set(vertex_1, vertex_2, 1);
        int set_2 = graph.set(vertex_1, vertex_2, 2);
        boolean remove = graph.remove(vertex_3);
        Set<Integer> set = graph.vertices();
        Map<Integer, Integer> sources = graph.sources(vertex_2);
        Map<Integer, Integer> targets = graph.targets(vertex_1);
        Set<Integer> exp_set = new HashSet<>();
        Map<Integer, Integer> exp_sources = new HashMap<>();
        Map<Integer, Integer> exp_targets = new HashMap<>();
        exp_set.add(1);
        exp_set.add(2);
        exp_sources.put(1, 2);
        exp_targets.put(2, 2);
        assertTrue("expected add vertex_1 to graph", add_1);
        assertTrue("expected fail to add vertex_1 to graph twice", !add_2);
        assertTrue("expected add vertex_3 to graph", add_3);
        assertEquals("expected set an edge between vertex_1 and vertex_2", 0, set_1);
        assertEquals("expected update the edge between vertex_1 and vertex_2", 1, set_2);
        assertTrue("expected remove vertex_3 from graph", remove);
        assertEquals("expected get a set including vertex_1 and vertex_2", exp_set, set);
        assertEquals("expected get a map including source vertices of vertex_2", exp_sources, sources);
        assertEquals("expected get a map including target vertices of vertex_1", exp_targets, targets);

        int set_3 = graph.set(vertex_1, vertex_2, 0);
        assertEquals("expected remove an existent edge and return preweight", 2, set_3);
    }
}
