import static org.junit.Assert.*;

import java.security.AccessControlException;
import java.security.Permission;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for FriendshipGraph.
 */
public class FriendshipGraphTest {
    
    // Testing strategy
    //   TODO
    
    @Before
    public void setUp() throws Exception {
        final SecurityManager securityManager = new SecurityManager() {
            public void checkPermission(Permission permission) {
                /**
                 * 这里permission.getName()会返回exitVM. + status的形式
                 * 例如System.exit(0) 这里会是exitVM.0
                 * System.exit(1) 这里会是exitVM.1
                 * 这里不关系怎么退出的，只需要捕捉到，并不让他退出，继续运行我的测试代码就好了
                 */
                if (permission.getName().startsWith("exitVM")) {
                    throw new AccessControlException("");
                }
            }
        };
        System.setSecurityManager(securityManager);
    }

    FriendshipGraph graph = new FriendshipGraph();
    Person rachel = new Person("Rachel");
    Person ross = new Person("Ross");
    Person ben = new Person("Ben");
    Person kramer = new Person("Kramer");

    @Test
    public void testSetIsVisitFalse() {
        Person jeff = new Person("jeff");
        jeff.isVisit = true;
        graph.setIsVisitFalse(jeff);
        assertTrue(!jeff.isVisit);
    }

    @Test
    public void testSetListFalse() {
        Set<Person> set = new HashSet<>();
        set.add(rachel);
        set.add(ross);
        set.add(ben);
        set.add(kramer);
        Iterator<Person> it1 = set.iterator();
        while(it1.hasNext()) {
            it1.next().isVisit = true;
        }
        graph.setListFalse(set);
        Iterator<Person> it2 = set.iterator();
        while(it2.hasNext()) {
            assertTrue(!it2.next().isVisit);
        }
    }
    
    @Test
    public void testAddVertex() {
        assertEquals(Collections.EMPTY_SET,graph.getPersons());
        Set<Person> set = new HashSet<>();
        set.add(rachel);
        graph.addVertex(rachel);
        Person newone = new Person("Rachel");
        try {
            graph.addVertex(newone);
        } catch (RuntimeException e) {
        } finally {
            // 屏蔽好之后，重新设置setSecurityManager为Null,防止junit退出的时候报错
            System.setSecurityManager(null);
        }  
        assertEquals(set, graph.getPersons());
    }
    
    @Test
    public void testAddEdge() {
        graph.addEdge(rachel, ross);
        assertTrue(graph.getPersons().contains(rachel));
        assertTrue(graph.getPersons().contains(rachel));
        assertEquals(1,graph.getDistance(rachel, ross));
    }

    @Test
    public void testGetDistance() {
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
        graph.addEdge(ben, rachel);
        assertEquals(1, graph.getDistance(rachel, ross));
        assertEquals(2, graph.getDistance(rachel, ben));
        assertEquals(0, graph.getDistance(rachel, rachel));
        assertEquals(-1, graph.getDistance(rachel, kramer));
    }

}
