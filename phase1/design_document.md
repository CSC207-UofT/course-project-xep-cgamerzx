# Phase 1 Design Document - Xep!cgamerzX
## Updated Specification
Our original specification involved users being able to create hotels, and customer users being able to view the hotel information, available rooms, location, and a possibility to book the room. 

// Rafee - I wanted to have a more concrete problem for our scope, is this ok?

In a more general sense, because every hotel won't use our app and list their own data, we want to specify a more concrete problem. This problem is hotels being cancelled upon last minute, or unpopular hotels that don't get many visitors. Both of these problems can lead to rooms being unused and less money being made. Our app solves this problem as it provides a quick way for a hotel to list their details, the rooms available, the price per night, and other information. This gives hotels an outlet to list their rooms for a cheaper price, for last minute or general bookings. Customers can then view all of these hotels, their location, the rooms available, and get data based on their schedule and city. A booking feature could also be implemented that allows a user to book these rooms that a hotel manager might have listed.

## Major Design Decisions
### Room Persistence Library/Data Persistence Overhaul {[#23](https://github.com/CSC207-UofT/course-project-xep-cgamerzx/issues/23)} {[#17](https://github.com/CSC207-UofT/course-project-xep-cgamerzx/pull/17)}

A significant portion of the time spent working on the backend was to implement the Room persistence library. This is a native Android library that provides an abstraction layer over SQLite which is also natively implemented in Android. Our previous method for data persistence involved a a generic file read write class which took any object and saved it as a file. This made things very simple and easy code wise, but it was horrible for performance because every time a new object was added, updated, or deleted, the entire database file had to be remade. Furthermore, everything in the file had to be loaded into memory in order to access data.

Using the Room library fixes all of this, and allows for the data to be handled in a way such that insertions, entry updates and deletions as well as complex queries are able to be handled without recreating the database every time. Each entity type has its own individual table allowing for easy organization.

Moving to this library did add code complexity. For one, the type of data that can be stored in the SQLite database is limited to basic types like strings, floats and integers. You can't nest objects or put a list of objects directly into a cell either. For more basic types such as BigDecimal, which we used to represent pricing in a way that would avoid float operation errors, it is simple to create a converter for saving and loading data. Some objects like Address can feasibly be flattened into their own columns using the embedded annotation. However, relations between more complex entities such as HotelRooms or even just lists of strings can't be flattened like this without compromising on the database's ability to index, which would negatively affect query speed.

This is solved in a number of different ways. For one to many relationships, such as the relationship between Hotel and HotelRoom where a HotelRoom belongs to only one Hotel but a Hotel may have many HotelRooms associated with it, the entity is only associated with one other entity saves that entity's identifier in one of the database's columns. 

For many to many relationships such as the relationship between Hotels and HotelAmenities, it gets slightly more complicated. An entire new cross relationship entity, and thus table, is needed to save the identifier of both entities in the relationship. There is one entity/entry in the table per relationship between two entities, with the entity identifiers in their own individual columns such that one can query for them separately.

This whole system was implemented using a data access object (DAO) pattern. See the [design patterns section](#Data-Access-Object-Pattern) for more information.

We explored using Firestore, a cloud hosted NoSQL database but cloud based APIs are usually asynchronous in nature and we found it difficult to adhere to clean architecture due to how async methods are implemented in Java. We identified callbacks as a potential solution, but determined that it would take too much time to explore this avenue though we may [revisit in Phase 2](#Firestore-Implementation), time permitting.

### User Interface Update/Explanations & Backend Methods, Along With Ideas For the Future.
A lot of time was spent updating the UI. 

There are two parts to the UI at the moment, the customer searching, and the management hotel creation. When a user registers, and signs in, in the profile fragment a new view will show up with an option to list a hotel. 

Listing a hotel consists of 3 types of inputs, the hotel name, the hotel address, and the rooms in the hotel. With an update on the UI, we also focused on handling input errors. If the user ever clicks save with no inputs or if the input is the wrong type, a message will pop up at the bottom of their screen telling them their error.

**Note about hotel creation:**
One downside to this, is that there are a lot of inputs, so the user has to be careful that they are typing in the right stuff. For example, it is important they give the right longitude and latitude. However, in the future, this input won't be necessary because using the PlacesAPI class, we can get longitude and latitude of a destination they submit.
Because creating these hotels might take a while, we created some dummy data in a json file under the assets folder. The json file contains some hotels in different cities, and also has dummy room data. In the ReadDummyData class, we parse this json file, create hotels along with their relationships within the database. The JSON file structure, and the reader, may also be good for loading Hotel API data in the future.

When hotels are listed, users can go back to the main page, and click search. With the new search feature, using google places api, typing in any location autogenerates suggestions for the users destination. When the user clicks on the location, in the backend, the longitude and latitude is saved. In addition, the user can set a schedule and increase their number of guests. The location, schedule, and number of guests are sent to the HotelViewActivity, where the respective hotels are displayed given the user inputs. One example of how we did this is getting hotels within a 50km radius of the users destination. This method is within HotelManager's getHotelsByLatLong().

**Note about searching:**
If a user does not input a schedule or destination, this is taken care of as all the hotels will show up instead. If a user enters a schedule, but not a destination, only hotels with rooms given their schedule show up. If a user enters a destination, but no schedule, all hotels within 50km of the destination show up.

After searching for a hotel, a recycler view shows up, with the hotels in the database that are within a 50km radius of the user's destination, filtered by their schedule. As of now, each hotel has a default picture attached to them, but in the future, we can let hotels upload an image instead. Upon viewing the new page with hotels, a user can click on a hotel and a new page will show up, with a detailed view.

This detailed view is the CustomerRoomsActivity class. In this page, we used google maps api to display a google map in a fragment of the hotels longitude and latitude. We also display the hotel name, address, and the respective rooms based on the users schedule. Each room displays their respective number of beds, bed size, capacity, price per night, and schedule that it is available. In the future, we plan to make this page nicer, add hotel amenities, and possibly add a "Book" feature to each hotel room.

We also made a sign in and register page, which uses the rooms library to save a user locally. At the moment, the system has no security, and user's can make their passwords and usernames anything they want. In the future, we probably want to make this more secure.

## SOLID
## Clean Architecture
## Packaging Strategies/Code Organization
## Design Patterns
### Data Access Object Pattern
Our persistence system done using the [room persistence library](#room-persistence-librarydata-persistence-overhaul) was implemented using a data access object (DAO) design pattern. The goal of this pattern is to separate low level data accessing operations such as Room library queries from business services. 

Data access objects which are implemented as interfaces define the actual methods that can be used by the rest of the app to manipulate data in the database. These act as use cases. In our implementation, the DAOs also implement a base DAO interface which removes the boilerplate for insert, update and delete functionality.

The database class holds the database instance and acts as an access point for the actual persisted data. This class also implements the singleton design pattern. These act as an interface adaptor.

Entities represent the structure of the individual tables within the database.

Additional use cases called managers deal with more complex actions that can't be implemented in the DAOs directly such as overloading methods for more forgiving type implementations.

#TODO Add picture

### Singleton
## Progress Report
### Open Questions
#### Firestore Implementation
As discussed in [room persistence library](#Room-Persistence-Library/Data-Persistence-Overhaul), we explored using Firestore, a cloud NoSQL database implementation, in order to deal with data persistence. However, cloud based solutions such as Firestore are asynchronous in nature and we found it difficult to implement them while adhering to Clean Architecture, as any use cases done using data accessed would have to be implemented within that same data access method. We identified callbacks as a potential solution but decided that it would be too time costly to explore for now. 

#### Fireauth Implementation
### Things that have worked well
#### Use of Github Features
In order to track issues as well as feature implementations that were being handled, and or identified but still needed to be dealt with, we used Github issues. The label system made it easy to discern at a glance what tasks needed to be done, and what tasks could be done relatively easily. For example, we used the "good first issue" label in order to label issues that can be done by anyone looking to contribute, but were not very sure where to start and or felt lost. The forum like discussion in issues allowed for detailed descriptions of instructions to be added.

We used pull requests and branches tightly with Github issues as they can be linked together. We generally adhered to the idea of one branch per feature and thus issue in order to minimize merge conflicts. However, if a lot of things have changed in an active branch but it wasn't ready to be merged with main, we would merge the active branches together to prevent future merge issues. All merges even those done between non-main branches were done using pull requests as the structure of pull requests allow for a good overview of what commits have been made, the build status of the latest push, and whom to go to if something goes wrong. 

In order to check if a commit had any issues, we utilized Github actions by creating a custom Android orientated workflow which built the app and ran all the tests. This includes the tests which require an android instance such as those testing data persistence. Our workflow action triggered for every push, and would indicate whenever or not a build or test failed or if everything ran correctly. This helped determine the status of a branch at a glance and is an obvious indicator for any issues that may have arisen because either the branch is actively being worked on, or if someone may have forgotten to run the tests themselves before pushing.

### Group Member Current and Future Responsibilities
Howard: 
Rafee: 
Megan: 
Veronica: 
Wei: 
Thomas: 

