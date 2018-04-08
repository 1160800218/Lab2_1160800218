
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import graph.Graph;

public class FriendshipGraph {
    private final Graph<Person> graph = Graph.empty();
    
    // Abstraction function:
    // represents the social network which includes persons(vertices) and their relation(edges) 
    // Representation invariant:
    // graph is a non-null Graph
    // Safety from rep exposure:
    // graph is private and final,
    // use getPersons() to get vertices of graph
    
    public void checkRep() {
        assert graph != null;
    }
    
    /**
     * ���÷���ֵΪfalse
     * @param name
     */
    public void setIsVisitFalse(Person name) {
        name.isVisit = false;
    }
    
    /**
     * ���ö���������ж�����ķ���ֵΪfalse
     * @param list
     */
    public void setListFalse(Set<Person> list) {
        Iterator<Person> it = list.iterator();
        while (it.hasNext()) {
            setIsVisitFalse(it.next());
        }
    }
    
    /**
     * ���ز���Ӷ���
     * @param name
     * @return
     */
    public boolean addVertex(Person name) {
        checkRep();
        Iterator<Person> it = graph.vertices().iterator();
        while (it.hasNext()) {
            if (it.next().getName() == name.getName()) {
                System.out.println("error: repeated name.");
                System.exit(0);
                return false;
            }
        }
        graph.add(name);
        checkRep();
        return true;
    }
    
    /**
     * ���ز���ӱ�
     * @param start
     * @param end
     */
    public void addEdge(Person start, Person end) {
        checkRep();
        graph.set(start, end, 1);
        checkRep();
    }
    
    /**
     * �ö���ʵ��bfs��̾�������
     * @param start
     * @param end
     * @return
     */
    public int getDistance(Person start, Person end) {
        checkRep();
        if (start.equals(end))
            return 0;
        Queue<Person> queue = new LinkedList<Person>();
        queue.offer(start);
        int distance = 0;
        Person temp, pop;
        while (!queue.isEmpty()) {
            pop = queue.remove();
            pop.isVisit = true;
            Iterator<Map.Entry<Person, Integer>> it = graph.targets(pop).entrySet().iterator();
            while (it.hasNext()) {
                if ((temp = it.next().getKey()).isVisit == false) {
                    temp.isVisit = true;
                    if (temp == end) {
                        // ÿ�ε���getDistance(��������isVisitֵΪfalse���Ա���һ�ε���ʱ��·���ж�
                        setListFalse(graph.vertices());
                        checkRep();
                        return distance + 1;
                    }
                    queue.offer(temp);
                }
            }
            distance++;
        }
        setListFalse(graph.vertices());
        checkRep();
        return -1;
    }
    
    /**
     * ��ȡ�����
     * @return
     */
    public Set<Person> getPersons(){
        checkRep();
        return graph.vertices();
    }
    
    public static void main(String args[]) {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
        graph.addEdge(ben, rachel);
        System.out.println("From rachel to ross = " + graph.getDistance(rachel, ross));
        System.out.println("From ross to rachel = " + graph.getDistance(ross, rachel));
        System.out.println("From rachel to ben = " + graph.getDistance(rachel, ben));
        System.out.println("From rachel to rachel = " + graph.getDistance(rachel, rachel));
        System.out.println("From rachel to kramer = " + graph.getDistance(rachel, kramer));
    }
}
