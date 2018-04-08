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
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   add()
    //     increase the number of vertices, add repeated vertex will fail
    //   set()
    //     if the edge is not existent, input vertices and weight, output 0, 
    //     if the edge is existent, input vertices and weight, output preweight,
    //     specially, input 0 weight will delete a edge and output 0
    //   remove()
    //     decrease the number of vertices, remove existent vertex will fail
    //   vertices()
    //     no vertices, only output a empty list,
    //     if using add() or remove(), vertices number changes
    //   sources()
    //     no edges, only output a empty map,
    //     construct a excepted map to compare the output
    //   targets()
    //     no edges, only output a empty map,
    //     construct a excepted map to compare the output
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // other tests for instance methods of Graph
    @Test
    public void testAdd() {
        Graph<String> graph = emptyInstance();
        assertEquals("expected graph to have no vertices", 0, graph.vertices().size());
        assertTrue("expected success to add a vertex to graph", graph.add("add_1"));
        assertTrue("expected fail to add a repeated vertex", !graph.add("add_1"));
        assertEquals("expected graph has a vertex", 1, graph.vertices().size());
    }
    @Test
    public void testSet() {
        Graph<String> graph = emptyInstance();
        assertTrue("expected success to add a vertex to graph", graph.add("add_1"));
        assertTrue("expected success to add a vertex to graph", graph.add("add_2"));
        assertEquals("expected success to add a new edge and return 0", 0, graph.set("add_1", "add_2", 1));
        assertEquals("expected success to update an existent edge and return preweight", 1, graph.set("add_1", "add_2", 2));
        assertEquals("expected remove an existent edge and return preweight", 2, graph.set("add_1", "add_2", 0));
        assertEquals("expected no edge added but just add vertices", 0, graph.set("add_2", "add_3", 0));
        assertEquals("expected three vertices exist", 3, graph.vertices().size());
    }
    @Test
    public void testRemove() {
        Graph<String> graph = emptyInstance();
        assertTrue("expected success to add a vertex to graph", graph.add("add_1"));
        assertEquals("expected graph has a vertex", 1, graph.vertices().size());
        assertTrue("expected success to remove a vertex from graph", graph.remove("add_1"));
        assertTrue("expected fail to remove a non-existent vertex", !graph.remove("add_1"));
        assertEquals("expected graph to have no vertices", 0, graph.vertices().size());
    }
    @Test
    public void testExistVertices() {
        Graph<String> graph = emptyInstance();
        Set<String> vertices = new HashSet<>();
        vertices.add("add_1");
        assertTrue("expected success to add a vertex to graph", graph.add("add_1"));
        assertEquals("expected a non-empty set of vertices", vertices, graph.vertices());
    }
    @Test
    public void testGetSources() {
        Graph<String> graph = emptyInstance();
        Map<String, Integer> sources = new HashMap<>();
        assertEquals("expected get a empty map", sources, graph.sources("add_3"));
        sources.put("add_2", 1);
        sources.put("add_1", 2);
        assertEquals("expected success to add a new edge and return 0", 0, graph.set("add_2", "add_3", 1));
        assertEquals("expected success to add a new edge and return 0", 0, graph.set("add_1", "add_3", 2));
        assertEquals("expected get a map including the source vertices of 'add_3'", sources, graph.sources("add_3"));
    }
    @Test
    public void testGetTargets() {
        Graph<String> graph = emptyInstance();
        Map<String, Integer> targets = new HashMap<>();
        graph.add("add_1");
        assertEquals("expected get a empty map", targets, graph.targets("add_1"));
        targets.put("add_2", 1);
        targets.put("add_3", 2);
        assertEquals("expected success to add a new edge and return 0", 0, graph.set("add_1", "add_2", 1));
        assertEquals("expected success to add a new edge and return 0", 0, graph.set("add_1", "add_3", 2));
        assertEquals("expected get a map including the target vertices of 'add_1'", targets, graph.targets("add_1"));
    }
    
}
