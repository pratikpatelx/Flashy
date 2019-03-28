package comp3350.tests.integration;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExampleTest {
    
    /**
     * exampleTest
     * 
     * test run with word "Example"
     * @Test
     * This method is just an example test, it does not do anything, but it serves
     * to help communicate how to add your own tests.
     */
    @Test
    public void exampleTest(){
        System.out.println("Running example test");
        System.out.println();
        
        System.out.println("This is just an example test it doesn't actually do anything.");
        System.out.println("But I won't leave you empty handed, so here's a nugget a wisdom;");
        System.out.println("Puppies are adorable, don't you dare ever forget that!");
        System.out.println("Afterall, everyone needs to remember that there is good in the world.");
        System.out.println();
        
        String puppies = "Adorable";
        String gremlins = "Ugly";
        String cute = "Adorable";
        
        assertEquals(puppies, cute);
        
        assertFalse(gremlins.equals(cute));
        
        System.out.println("Example test complete");
    }
    
}
