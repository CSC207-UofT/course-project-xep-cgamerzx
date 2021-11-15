# Phase 1 Design Document - Xep!cgamerzX

## Updated Specification
Our original specification involved users being able to create hotels, and customer users being able to view the hotel information, available rooms, location, and a possibility to book the room. 

In a more general sense, because every hotel won't use our app and list their own data, we want to specify a more concrete problem. This problem is hotels being cancelled upon last minute, or unpopular hotels that don't get many visitors. Both of these problems can lead to rooms being unused and less money being made. Our app solves this problem as it provides a quick way for a hotel to list their details, the rooms available, the price per night, and other information. This gives hotels an outlet to list their rooms for a cheaper price, for last minute or general bookings. Customers can then view all of these hotels, their location, the rooms available, and get data based on their schedule and city. A booking feature could also be implemented that allows a user to book these rooms that a hotel manager might have listed.

## Major Design Decisions
### Room Persistence Library/Data Persistence Overhaul {[#23](https://github.com/CSC207-UofT/course-project-xep-cgamerzx/issues/23)} {[#17](https://github.com/CSC207-UofT/course-project-xep-cgamerzx/pull/17)}

A significant portion of the time spent working on the backend was to implement the Room persistence library. This is a native Android library that provides an abstraction layer over SQLite which is also natively implemented in Android. Our previous method for data persistence involved a a generic file read write class which took any object and saved it as a file. This made things very simple and easy code wise, but it was horrible for performance because every time a new object was added, updated, or deleted, the entire database file had to be remade. Furthermore, everything in the file had to be loaded into memory in order to access data.

Using the Room library fixes all of this, and allows for the data to be handled in a way such that insertions, entry updates and deletions as well as complex queries are able to be handled without recreating the database every time. Each entity type has its own individual table allowing for easy organization. Furthermore, Room is an Object Relational Mapping library (ORM). This means that database objects are mapped to Java objects, meaning data access and insertion will utilize Java objects.

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
Overall, the project adheres to SOLID design principles.

All classes have a single responsibility. For example, each manager class only handles one type of entity. Each data access object only handles one table/entity or relationship. 

We follow the open-closed principle as our code is structured in a way such that it is very easy to add new features. A new entity or manager can or even application page can be added easily in new classes without modification to other classes. Use case functionality for a manager can be easily added by simply extending that manager. Even adding instance variables to existing entities is a simple affair that doesn't require modification of other classes because our data persistence system is object relational mapping.

We made sure to use class inheritance and interfaces in a way that adheres to the Liskov substitution principle. For example, Bed, HotelAmenity, and RoomAmenity all implement the UniqueEntity interface in a manner such that if they can all generally substitute each other throughout the entire program without adverse effects.

All of our interfaces are small and specific. In situations where some parts of an interface can be shared but others can not be shared by different classes, but the classes should still be generally classified as similar things, we created intermediate interfaces which extend more general interfaces. For example, we can expect all managers to have basic functions in terms of insert, delete and update in regards to the entity they manage. However, managers which handle cross references can be expected to have those methods as well as additional methods specific to handling cross references. Thus, the interface for cross managers extend a more general interface for managers.

We adhere to the dependency inversion principle by ensuring that all modules depend on abstractions. For example, unique entities are expected to have string identifiers that are non-autogenerated and this is enforced using an interface. Our managers all have abstractions in the form of interfaces which define the basic methods that all managers should have.

## Clean Architecture
We followed clean archictecture by using the same SOLID design principle structure from phase 0. We have entities for hotel objects, use case managers for to manage things like rooms, hotels, beds, amentities, and more. Within our activities, we use several usecase classes to fetch and output data. In phase 1, we also implemented a new database structure that used android rooms library to save data and query with SQLite. We followed clean architecture here by using a database access object interfaces, which required certain methods the use case managers. This structure allowed us to use the database methods within the usecase classes by and not break SOLID structure because we used interfaces instead of calling the database directly.

## Packaging Strategies/Code Organization
We packaged our code using the "by component method". We packaged by what made sense to us, and structured it in a way that controllers (activities) have their own packages, and within them, adapters and models are there as well. We also have a storage package, that holds anything saves and creates data. For example, we have our managers here, data access objects, and file read write classes in their own respective packages. 

## Design Patterns
### Data Access Object Pattern
Our persistence system done using the [room persistence library](#room-persistence-librarydata-persistence-overhaul-23-17) which has an implementation architecture that uses the data access object (DAO) design pattern. The goal of this pattern is to separate low level data accessing operations such as Room library queries from business services. 

Data access objects which are implemented as interfaces define the actual methods that can be used by the rest of the app to manipulate data in the database. They also act as abstractions over SQLite. These act as use cases. In our implementation, the DAOs also implement a base DAO interface which removes the boilerplate for insert, update and delete functionality.

The database class holds the database instance and acts as an access point for the actual persisted data. This class also implements the singleton design pattern. These act as an interface adaptor.

Entities represent the structure of the individual tables within the database.

Additional use cases called managers deal with more complex actions that can't be implemented in the DAOs directly such as overloading methods for more forgiving type implementations.

![](https://developer.android.com/images/training/data-storage/room_architecture.png)

Image Credit: https://developer.android.com/training/data-storage/room

### Frontend MVC Design Pattern (Model View Controller)

![](https://miro.medium.com/max/875/1*wumdzeLMAL0xvaIhMyMZPQ.png)

Image Credit:https://medium.com/@jeyahariprakash/android-design-patterns-3b69e649aad6

When a user clicks search, a list of hotels is displayed to this. Similarly, when the user clicks a hotel, the hotel details are displayed to this. The way we are displaying this data is through the model view controller design pattern. Using data models, we initialize the data that we need for the respective views, and set getters for them. Then, in the controllers (activities, adapters etc), we can create the model objects and get the data we want from them. This makes it easy to set text fields to the data we want, without having to rely on many managers and use case classes. The "View" part of this pattern is the actual UI the user see's, which are the xml files that interact with the Java activity controllers.

### Singleton

The signleton design pattern restricts a class such that only one instance of the class may exist at a time. Singletons are used rather extensively due to how our persistent data system works. In our DAO pattern, the database class is a singleton. This is required because the database should be acting as a single point of access for the persisted data. Multiple instances of the database class can result in duplicate tables and SQL databases, cascading to bugs where one part of the app saves an entity, but the other part of the app trying to load said data has a different database instance, such that it can't find the data that was just saved. Similarly, because managers utilize the databases directly, each manager acts as a singleton to ensure they are always accessing the same database and data access objects.

The implementation of singleton for all managers is the same where each manager has a private constructor and the class has saves a private volatile instance of itself. To access the instance, one must use the public method getManager which either returns the instance already created, or creates a new instance using a passed app context or database instance. The database class works similarly, except it is an abstract class and thus has no constructor. It uses a database builder in order to create a database instance if one does not exist.


## Progress Report
### Open Questions
#### Firestore Implementation
As discussed in [room persistence library](#room-persistence-librarydata-persistence-overhaul-23-17), we explored using Firestore, a cloud NoSQL database implementation, in order to deal with data persistence. However, cloud based solutions such as Firestore are asynchronous in nature and we found it difficult to implement them while adhering to Clean Architecture, as any use cases done using data accessed would have to be implemented within that same data access method. This means that if we want to get hotel data in useCase classes, we have to directly use the database class, which violates clean architecture. We identified callbacks as a potential solution but decided that it would be too time costly to explore for now. 

#### Fireauth Implementation
Fireauth is a library that handles user authentication services. This includes custom user accounts as well as accounts linked to services such as Google accounts. It also syncs authentication details with the cloud. Currently our users systems store user credentials locally in an insecure manner. By potentially implementing Fireauth in the future, we can circumvent this and simply use user tokens as user identifiers for the app, and separate the authentication process to be handled solely by Fireauth for a more secure and easy to use process. 
### Things that have worked well
#### Use of Github Features
In order to track issues as well as feature implementations that were being handled, and or identified but still needed to be dealt with, we used Github issues. The label system made it easy to discern at a glance what tasks needed to be done, and what tasks could be done relatively easily. For example, we used the "good first issue" label in order to label issues that can be done by anyone looking to contribute, but were not very sure where to start and or felt lost. The forum like discussion in issues allowed for detailed descriptions of instructions to be added.

We used pull requests and branches tightly with Github issues as they can be linked together. We generally adhered to the idea of one branch per feature and thus issue in order to minimize merge conflicts. However, if a lot of things have changed in an active branch but it wasn't ready to be merged with main, we would merge the active branches together to prevent future merge issues. All merges even those done between non-main branches were done using pull requests as the structure of pull requests allow for a good overview of what commits have been made, the build status of the latest push, and whom to go to if something goes wrong. 

In order to check if a commit had any issues, we utilized Github actions by creating a custom Android orientated workflow which built the app and ran all the tests. This includes the tests which require an android instance such as those testing data persistence. Our workflow action triggered for every push, and would indicate whenever or not a build or test failed or if everything ran correctly. This helped determine the status of a branch at a glance and is an obvious indicator for any issues that may have arisen because either the branch is actively being worked on, or if someone may have forgotten to run the tests themselves before pushing.

#### Unit Tests
We used unit tests extensively throughout our project, including for portions that require an application instance such as for database related methods. Using unit tests and test-driven development in general was great for helping with identifying if changes resulted in something breaking. If something did break, this often indicated potential violations of SOLID design. Other times, tests not acting as expected acted as indication that we needed to implement equals and hashcode functions manually. In conjunction with Github actions, they were also a good indicator of the status of a branch in terms of what needs to be worked on and whenever or not it would be safe to merge the branch.

### Group Member Current and Future Responsibilities
Howard: Worked on room library implementation, managers and the backend in general. Also worked on the design document. Will explore cloud syncing of persistent data for phase 2 as well as fireauth implementations.

Rafee: Worked on frontend and backend. Created some methods in managers, and validated input for listing a hotel. Worked on the searching logic and hotel views as well. In the future, might work on some sort of online storage, enhancing the user system, and a booking system.

Megan: I implemented HotelAmenitiesCrossManager and RomAmenitiesCrossManager. I made boiler data and tests in HotelAmenityManagerTest, RoomAmenityManagerTest, UserTest, HotelManagerTest, RoomManagerTest and BedTest. I also added documentation and fixed warnings. I plan on working on implementing a better design pattern for our object managers.

Veronica: Contributed to the making of the hotels dummy data. Worked on UI for rooms listing when a hotel has been selected. Will work on possibly adding a booking feature for hotel rooms so the user can book a room according to their schedule.

Wei: 

Thomas: Will work on implementing the favourite feature, as well as an autocomplete for searches and UX on the frontend.

