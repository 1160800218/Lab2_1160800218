/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // tests   
    File f = new File("C:\\Users\\jeffery\\Desktop\\SC\\workspace\\Lab2_1160800218\\test\\P1\\poet\\testGraphPoet.txt");
    public GraphPoet createGraph() throws IOException{
        GraphPoet graphpoet = new GraphPoet(f);
        return graphpoet;
    }
    
    @Test
    public void testGetWords() throws IOException {
        GraphPoet graphpoet = createGraph();
        List<String> corpuswords = new ArrayList<>();
        corpuswords = graphpoet.getCorpusWords();
        assertEquals(22, corpuswords.size());
        assertTrue(corpuswords.contains("learning"));
    }
    @Test
    public void testPoem() throws IOException {
        GraphPoet graphpoet = createGraph();
        String poem;
        poem = graphpoet.poem("i learning sc,");
        assertEquals("i am learning the sc,", poem);
    }
    @Test
    public void testToString() throws IOException {
        GraphPoet graphpoet = createGraph();
        assertEquals("i->am\n" + 
                "am->learning\n" + 
                "learning->the\n" + 
                "the->sc,\n" +
                "sc,->it's\n" +
                "it's->interesting\n" +
                "interesting->but\n" +
                "but->a\n" +
                "a->little\n" +
                "little->hard,\n" +
                "hard,->i\n" +
                "i->need\n" +
                "need->spend\n" + 
                "spend->a\n" + 
                "a->lot\n" + 
                "lot->of\n" + 
                "of->time\n" + 
                "time->to\n" + 
                "to->finish\n" + 
                "finish->the\n" + 
                "the->lab.", graphpoet.toString());
    }
}
