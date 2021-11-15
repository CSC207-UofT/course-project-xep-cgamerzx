# Phase 1 Design Document - Xep!cgamerzX
## Updated Specification
## Major Design Decisions
### Room Persistence Library/Data Persistence Overhaul

A significant portion of the time spent working on the backend was to implement the Room persistence library. This is a native Android library that provides an abstraction layer over SQLite which is also natively implemented in Android. Our previous method for data persistence involved a a generic file read write class which took any object and saved it as a file. This made things very simple and easy code wise, but it was horrible for performance because every time a new object was added, updated, or deleted, the entire database file had to be remade. Furthermore, everything in the file had to be loaded into memory in order to access data.

Using the Room library fixes all of this, and allows for the data to be handled in a way such that insertions, entry updates and deletions as well as complex queries are able to be handled without recreating the database every time. Each entity type has its own individual table allowing for easy organization.

Moving to this library did add code complexity. For one, the type of data that can be stored in the SQLite database is limited to basic types like strings, floats and integers. You can't nest objects or put a list of objects directly into a cell either. For more basic types such as BigDecimal, which we used to represent pricing in a way that would avoid float operation errors, it is simple to create a converter for saving and loading data. Some objects like Address can feasibly be flattened into their own columns using the embedded annotation. However, relations between more complex entities such as HotelRooms or even just lists of strings can't be flattened like this without compromising on the database's ability to index, which would negatively affect query speed.

This is solved in a number of different ways. For one to many relationships, such as the relationship between Hotel and HotelRoom where a HotelRoom belongs to only one Hotel but a Hotel may have many HotelRooms associated with it, the entity is only associated with one other entity saves that entity's identifier in one of the database's columns. 

For many to many relationships such as the relationship between Hotels and HotelAmenities, it gets slightly more complicated. An entire new cross relationship entity, and thus table, is needed to save the identifier of both entities in the relationship. There is one entity/entry in the table per relationship between two entities, with the entity identifiers in their own individual columns such that one can query for them separately.

This whole system was implemented using a data access object (DAO) pattern. See the [design patterns section](#Data-Access-Object-Pattern) for more information.

We explored using Firestore, a cloud hosted NoSQL database but cloud based APIs are usually asynchronous in nature and we found it difficult to adhere to clean architecture due to how async methods are implemented in Java. We identified callbacks as a potential solution, but determined that it would take too much time to explore this avenue though we may revisit in Phase 2, time permitting.

### User Interface Update

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


