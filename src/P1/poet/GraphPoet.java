/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>
 * GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph. Vertices in the graph are words. Words are defined as
 * non-empty case-insensitive strings of non-space non-newline characters. They
 * are delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     Hello, HELLO, hello, goodbye!
 * </pre>
 * <p>
 * the graph would contain two edges:
 * <ul>
 * <li>("hello,") -> ("hello,") with weight 2
 * <li>("hello,") -> ("goodbye!") with weight 1
 * </ul>
 * <p>
 * where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>
 * Given an input string, GraphPoet generates a poem by attempting to insert a
 * bridge word between every adjacent pair of words in the input. The bridge
 * word between input words "w1" and "w2" will be some "b" such that w1 -> b ->
 * w2 is a two-edge-long path with maximum-weight weight among all the
 * two-edge-long paths from w1 to w2 in the affinity graph. If there are no such
 * paths, no bridge word is inserted. In the output poem, input words retain
 * their original case, while bridge words are lower case. The whitespace
 * between every word in the poem is a single space.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     This is a test of the Mugar Omni Theater sound system.
 * </pre>
 * <p>
 * on this input:
 * 
 * <pre>
 *     Test the system.
 * </pre>
 * <p>
 * the output poem would be:
 * 
 * <pre>
 *     Test of the system.
 * </pre>
 * 
 * <p>
 * PS2 instructions: this is a required ADT class, and you MUST NOT weaken the
 * required specifications. However, you MAY strengthen the specifications and
 * you MAY add additional methods. You MUST use Graph in your rep, but otherwise
 * the implementation of this class is up to you.
 */
public class GraphPoet {

    private final Graph<String> graph = Graph.empty();
    private final List<String> corpuswords = new ArrayList<>();
    // Abstraction function:
    // represents a
    // Representation invariant:
    // graph is a non-null Graph
    // Safety from rep exposure:
    // all fields are private and final,
    // getWordsFromFile() is private,
    // client can use getCorpusWords() to get corpuswords,
    // poem() is the only method client can use to get a result

    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus
     *            text file from which to derive the poet's affinity graph
     * @throws IOException
     *             if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        getWordsFromFile(corpus);
        connectWords();
    }

    private void connectWords() {
        String source, target;
        Iterator<String> it = corpuswords.iterator();
        it.hasNext();
        target = it.next().toLowerCase();
        while (it.hasNext()) {
            source = target;
            target = it.next().toLowerCase();
            graph.set(source, target, 1);
        }
    }

    public void checkRep() {
        assert graph != null;
    }

    private void getWordsFromFile(File corpus) throws IOException {
        checkRep();
        Scanner s = new Scanner(new BufferedReader(new FileReader(corpus)));
        while (s.hasNext())
            corpuswords.add(s.next());
        s.close();
        checkRep();
    }

    public List<String> getCorpusWords() {
        return corpuswords;
    }

    /**
     * Generate a poem.
     * 
     * @param input
     *            string from which to create the poem
     * @return poem (as described above)
     * 
     * 
     */
    public String poem(String input) {
        checkRep();
        List<String> poem = new ArrayList<>();
        String[] separatedwords;
        String temp;
        int index = 0;
        separatedwords = input.split(" ");
        while (index < separatedwords.length - 1) {
            poem.add(separatedwords[index]);
            if (!graph.targets(separatedwords[index]).containsKey(separatedwords[index + 1])) {
                Iterator<Map.Entry<String, Integer>> it = graph.targets(separatedwords[index]).entrySet().iterator();
                while (it.hasNext()) {
                    if (graph.targets(temp = it.next().getKey()).containsKey(separatedwords[index + 1])) {
                        poem.add(temp);
                        System.out.println(temp);
                    }
                }
            }
            index++;
        }
        poem.add(separatedwords[index]);
        checkRep();
        return poem.stream().collect(Collectors.joining(" "));
    }

    public String toString() {
        checkRep();
        return graph.toString();
    }

}
