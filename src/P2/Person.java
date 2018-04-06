
public class Person {
    private final String personname;
    public boolean isVisit = false;
    
    // Abstraction function:
    // represents a person in the SocialNetwork,
    // isVisit represents whether this person being touched or not
    // when searching the relation from one person to another
    // Representation invariant:
    // personname != null
    // Safety from rep exposure:
    // personname is private and final,
    // use getName() to get personname
    
    public Person(String name) {  
        personname = name;
    }
    
    public void checkRep() {
        assert personname != null;
    }
    
    public String getName() {
        checkRep();
        return personname;
    }
}
