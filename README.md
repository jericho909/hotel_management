# Tourism Agency Management System

## Project Overview

This project aims to create a Tourism Agency Management System as a project for Patika+ Full Stack Web Developer Bootcamp. The system is designed to facilitate daily operations, enhance efficiency in managing hotel partnerships, and optimize customer reservation processes.

## Features

### User Roles

#### Admin

-   User Management:
    -   List, add, delete, update agency employees
    -   Filter employees based on roles (admin, staff)

#### Agency Employee (Staff)

-   Hotel Management:
    -   List, add hotels
-   Room Management:
    -   List, add rooms
-   Season Management:
    -   List, add periods
-   Pricing Management
-   Room Search
-   Reservation Management:
    -   List, add, delete, update reservations

### User Management

-   Admin can add, remove, and edit users who access the system.
-   Admin assigns roles (admin, staff) when adding a new user.
-   Admin can edit user information such as name, surname, password, etc.
-   Admin can delete user accounts.
-   Admin can filter users based on roles (admin, staff).

### Hotel Management

-   Agency manages partner hotels, including location details and features.
-   When adding a hotel, information like Hotel Name, Address, Email, Phone, Star Rating, and Facility Types is recorded.
-   The system includes a screen to list hotels and add new ones.
-   The system records the boarding type, facility features, and period information for existing hotels.

### Season Management

-   Seasons related to hotels are added for pricing considerations.
-   The aim is to provide variable pricing based on different seasons.
-   Seasons are defined as date ranges, and agency staff adds them to the system.

### Room Management

-   Agency staff adds rooms to the system and provides pricing.
-   The system uses a stock logic to manage room types, avoiding repetitive entries.
-   Room features such as the number of beds, square footage, and additional amenities are recorded.
-   The system lists all room details.

### Pricing

-   Room prices are calculated per night.
-   Prices vary based on hotel-defined seasons and boarding types.
-   Different prices are set for adults and children.
-   The system automatically calculates the total price based on user-inputted information.

### Room Search and Reservation

-   Agency staff can search for rooms based on:
    -   Date range
    -   City
    -   Hotel name
-   Dynamic SQL queries are implemented to allow searches with any combination of the three criteria.
-   The system ensures that rooms displayed in search results meet specific criteria.

### Reservation Completion

-   After listing suitable rooms, the agency staff can proceed with the reservation.
-   Total price is automatically calculated based on user-inputted information.
-   Guest information and contact details are collected.
-   When a reservation is completed, the stock of the respective room decreases.

## Usage

1. Clone into this repo.
2. Install the Java dependencies.
3. Create a database in PostgreSQL and restore the database with the provided file in the DB folder.
4. Open up your favorite IDE and run App.java (Or use javac in your terminal and type java App).

## Technologies Used

-   Frontend: Swing UI (Java)
-   Backend: Java
-   Database: PostgreSQL

## Video Demo



https://github.com/jericho909/hotel_management/assets/101925116/d959312c-5b7f-4dac-8d45-164c8fede57c



## License

This project is licensed under the MIT License.
