# Retrospective

## Unsuccessful Component

A part of the project in the last iteration that was not as successful as we would’ve like was the state of the managing a deck feature. In this feature, there are 4 user stories associated with it which are creating, deleting, viewing and editing a deck. This feature serves as the foundation of our application since the purpose is to be manipulating decks of flashcards.  In the previous iteration, we had a problem figuring the exact details of how decks were going to be created and stored into a persistent database versus storing it in a stub database. This problem of figuring out specific details took too much time out of implementing the feature and resulted in a bunch of messy code that wasn’t implemented cleanly. Also, since we could not figure out the HSQLDB implementation we were not able to implement our database persistently. 

## Plans to Improve

For this current iteration, we can improve the state of our Manage Deck feature by agreeing to an idea of how this feature should be implemented very early into the iteration. This will allow us to spend more time implementing the actual feature and using tests to see what works and what doesn’t work during the coding process. Also, we can improve the feature by following the sample project and completely understand how a working persistent database can be implemented. By following these two plans for improvement we should be able to implement the Manage Deck feature successfully which will create a solid foundation for the rest of our features.

## Measuring success

 We can evaluate the success of the improving this feature by completing successful tests that test the code for the feature thoroughly. We can implement unit tests that ensure that a deck can be created, deleted, edit and viewed. Once these tests pass, we can work on integration tests that would ensure that the database is storing and retrieving the appropriate data. In addition, this would test if the HSQLDB is storing data persistently and functioning as expected. Lastly, we would implement an acceptance test that would the entire feature of managing a deck. This test would act as the user using the presentation layer to manipulate the other two layers and confirm that we got expected results. After these three levels of testing can be successfully passed, the problem we have with the Manage Deck feature can be deemed properly fixed. 
 
## Project Velocity

![](graph.jpg)