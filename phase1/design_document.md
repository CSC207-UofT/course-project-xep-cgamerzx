# Phase 1 Design Document - Xep!cgamerzX
## Updated Specification
## Major Design Decisions
### Room Persistence Library/Data Persistence Overhaul

A significant portion of the time spent working on the backend was to implement the Room persistence library. This is a native Android library that provides an abstraction layer over SQLite which is also natively implemented in Android. Our previous method for data persistence involved a a generic file read write class which took any object and saved it as a file. This made things very simple and easy code wise, but it was horrible for performance because every time a new object was added, updated, or deleted, the entire database file had to be remade. Furthermore, everything in the file had to be loaded into memory in order to access data.

Using the Room library fixes all of this, and allows for the data to be handled in a way such that insertions, entry updates and deletions as well as complex queries are able to be handled without recreating the database every time. Each entity type has its own individual table allowing for easy organization.

Moving to this library did add code complexity. For one, the type of data that can be stored in the SQLite database is limited to basic types like strings, floats and integers. You can't nest objects or put a list of objects directly into a cell either. For more basic types such as BigDecimal, which we used to represent pricing in a way that would avoid float operation errors, it is simple to create a converter for saving and loading data. Some objects like Address can feasibly be flattened into their own columns using the embedded annotation. However, relations between more complex entities such as HotelRooms or even just lists of strings can't be flattened like this without compromising on the database's ability to index, which would negatively affect query speed.

This is solved in a number of different ways. For one to many relationships, such as the relationship between Hotel and HotelRoom where a HotelRoom belongs to only one Hotel but a Hotel may have many HotelRooms associated with it, the entity is only associated with one other entity saves that entity's identifier in one of the database's columns. 

For many to many relationships such as the relationship between Hotels and HotelAmenities, it gets slightly more complicated. An entire new cross relationship entity, and thus table, is needed to save the identifier of both entities in the relationship. There is one entity/entry in the table per relationship between two entities, with the entity identifiers in their own individual columns such that one can query for them separately.

This whole system was implemented using a data access object (DAO) pattern. See the [design patterns section](#Design_Patterns) for more information.

### User Interface Update

## SOLID
## Clean Architecture
## Packaging Strategies/ Code Organization
## Design Patterns
- Data Access Object Pattern
## Progress Report
### Open Questions
### Things that have worked well
- Use of github feature?
### Group Member Current and Future Responsibilities


