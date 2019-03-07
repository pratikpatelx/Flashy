package comp3350.flashy;

import java.io.*;

//import junit.framework.*;
//import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
//import static org.junit.Assert.*;



//import DomainLogic.Flashcard;
//import Logic.LogicManager;
//import Persistence.DatabaseManager;



public class AllTests {
    
    /**
     * This program uses JUnit4.12
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        try{
            
            BufferedReader reader;
            String line = "";

            

            boolean exit = false;
            boolean cmdLine = (args.length == 0);//should the user interface be used?
            String token;
            while(!exit){
                
                
                if(cmdLine){
                    reader = new BufferedReader(new InputStreamReader(System.in));
                    
                    
                    System.out.println("What test would you like to run? ");
                    System.out.println("Select a test below,lternativly type 'exit' to quit.");




                    //Content beyond this point is the test list
                    System.out.println("All");
                    System.out.println("Example");
                    System.out.println("PutCardIntoDeck");
                    System.out.println("EditCard");
                    System.out.println("GetCard");
                    System.out.println("RemoveCard");
                    System.out.println("PrintDeck");
                    System.out.println("ShuffleDeck");
                    //Content beyond this point is NOT the test list
                    
                    
                    line = reader.readLine();
                }
                else{
                    if(args.length == 1){
                        line = args[0];
                    }
                    else{
                        if(args.length > 1){
                            System.out.println("Error Too many parameters");
                        }
                    }
                    exit = true;
                }
                

                
                line = line.toLowerCase();
                token = line.trim();

                /**
                 * About adding a test:
                 * 
                 * Write your test as a private method in this class see the example
                 * below for details
                 * 
                 * add a case to the logic tree below that will make a call to that 
                 * method, make sure to add that call to the "all" section at the 
                 * end
                 * 
                 * add the word you wish to trigger your test to the test list above
                 * 
                 * RULES
                 * 
                 * - The "word" that runs your test must be unique
                 * - All letters in the "word" must be lowercase
                 * - The word cannot have any whitespace
                 * 
                 */

                if(token.equals("example")){
                    Result result = JUnitCore.runClasses(ExampleTest.class);
                    for (Failure failure : result.getFailures()) {
                        System.out.println(failure.toString());
                    }
                    System.out.println("Test encountered "+ result.getFailureCount() + " failures");
                }
                
                else if(token.equals("printdeck")){
                    Result result = JUnitCore.runClasses(PrintDeckTest.class);
                    for (Failure failure : result.getFailures()) {
                        System.out.println(failure.toString());
                    }
                    System.out.println("Test encountered "+ result.getFailureCount() + " failures");
                }
                
                else if(token.equals("removecard")){
                    Result result = JUnitCore.runClasses(RemoveCardTest.class);
                    for (Failure failure : result.getFailures()) {
                        System.out.println(failure.toString());
                    }
                    System.out.println("Test encountered "+ result.getFailureCount() + " failures");
                }
                /*
                else if(token.equals("editcard")){
                    Result result = JUnitCore.runClasses(EditCardTest.class);
                    for (Failure failure : result.getFailures()) {
                        System.out.println(failure.toString());
                    }
                    System.out.println("Test encountered "+ result.getFailureCount() + " failures");
                }
                */
                
                else if(token.equals("shuffledeck")){
                    Result result = JUnitCore.runClasses(ShuffleDeckTest.class);
                    for (Failure failure : result.getFailures()) {
                        System.out.println(failure.toString());
                    }
                    System.out.println("Test encountered "+ result.getFailureCount() + " failures");
                }
                
                else if(token.equals("putcardintodeck")){
                    Result result = JUnitCore.runClasses(PutCardIntoDeckTest.class);
                    for (Failure failure : result.getFailures()) {
                        System.out.println(failure.toString());
                    }
                    System.out.println("Test encountered "+ result.getFailureCount() + " failures");
                }
                
                else if(token.equals("getcard")){
                    Result result = JUnitCore.runClasses(PutCardIntoDeckTest.class);
                    for (Failure failure : result.getFailures()) {
                        System.out.println(failure.toString());
                    }
                    System.out.println("Test encountered "+ result.getFailureCount() + " failures");
                }
                
                else if(token.equals("all")){
                    System.out.println("Running All tests\n");
                    Result result = JUnitCore.runClasses(PrintDeckTest.class, 
                            PutCardIntoDeckTest.class, 
                            PrintDeckTest.class,
                            //EditCardTest.class,
                            GetCardTest.class,
                            RemoveCardTest.class,
                            ShuffleDeckTest.class);
                    for (Failure failure : result.getFailures()) {
                        System.out.println(failure.toString());
                    }
                    System.out.println("Tests encountered "+ result.getFailureCount() + " failures");
                    
                }
                else if(token.equals("exit")){
                    exit = true;
                    System.out.println("Farewell");
                }
                else{//The test isn't recognized
                    if(!cmdLine && args.length == 1){
                        System.out.println("Sorry I don't understand what you mean by '" + line + "' Please try again.");
                        
                        exit = false;
                        cmdLine = true;
                    }
                }



            }
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

        
    }
    
    
    
    
    
    
    
    
    
    
}
