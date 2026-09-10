# Hotel Management System - Theoretical Overview

## Overview
This document explains how the restaurant management system works from three different perspectives: Customer, Restaurant Owner, and Staff.

---

## Restaurant Customer Side

### Menu Browsing & Ordering
- **View Restaurants**: Customers can browse all available restaurants in the system via `GET /api/restaurants`
- **View Menu**: For a specific restaurant, customers can view the complete menu via `GET /api/items/restaurant/{restaurantId}`
- **Item Details**: Each menu item displays:
  - Name
  - Price
  - Description
  - Category (e.g., appetizers, main course, beverages)
  - Image

### Customer User Flow
1. Customer selects a restaurant from the restaurant list
2. Views the restaurant's menu items with prices and descriptions
3. Theoretically places orders (order functionality to be implemented)

### Data Access
- Customers can only view public restaurant and menu information
- Items are scoped to specific restaurants, ensuring customers see only that restaurant's menu
- No authentication required for browsing

---

## Restaurant Owner Side

### Restaurant Management
- **Create Restaurant**: Owners register their restaurant via `POST /api/restaurants`
  - Required fields:
    - Restaurant name
    - Address
    - Phone number
- **Update Restaurant**: Modify restaurant details via `PUT /api/restaurants/{id}`
- **Delete Restaurant**: Remove restaurant from the system via `DELETE /api/restaurants/{id}`

### Menu Management
- **Add Menu Items**: Owners add items to their menu via `POST /api/items`
  - Required fields:
    - Item name
    - Price
    - Restaurant ID (links item to their restaurant)
  - Optional fields:
    - Description
    - Category
    - Image URL
- **Update Items**: Modify item details, prices, or availability
- **Delete Items**: Remove items from the menu
- **Scope**: All items are automatically linked to the owner's restaurant

### Staff Management
- **Create Staff Accounts**: Owners can add staff members with the following attributes:
  - Name
  - Email ID
  - Password (hashed for security)
  - Role (determines permissions)
- **Staff Roles**:
  - **WAITER**: Takes orders, serves customers
  - **CASHIER**: Handles payments and billing
  - **MANAGER**: Has broader operational control
- **Restaurant Assignment**: Each staff member is linked to the owner's restaurant via `restaurant_id`

---

## Staff Side

### Authentication & Access
- Staff members log in with their email and password
- Role-based access control determines their permissions
- Staff are scoped to a specific restaurant (can only access their restaurant's data)
- Data isolation prevents cross-restaurant access

### Role-Based Operations

#### WAITER
- Views the restaurant's menu items
- Takes customer orders (theoretically)
- Manages table assignments (to be implemented)
- Updates order status

#### CASHIER
- Accesses billing information
- Processes payments
- Generates receipts
- Views transaction history

#### MANAGER
- Combined access to waiter and cashier functions
- Manages staff assignments
- Oversees restaurant operations
- Accesses sales reports and analytics (to be implemented)

### Data Isolation
- Each staff member only sees data for their assigned restaurant
- Items, orders, and operations are scoped by `restaurant_id`
- Prevents unauthorized access to other restaurants' data

---

## System Architecture

### Technology Stack
- **Backend Framework**: Spring Boot with REST APIs
- **ORM**: JPA/Hibernate for database operations
- **Database**: MySQL/PostgreSQL (implied)
- **Language**: Java

### Entity Relationships
- **Restaurant → Items**: One-to-Many relationship
  - One restaurant can have multiple menu items
  - Each item belongs to exactly one restaurant
- **Restaurant → Staff**: One-to-Many relationship
  - One restaurant can have multiple staff members
  - Each staff member belongs to exactly one restaurant

### Key Design Patterns
- **Repository Pattern**: For data access (`RestaurantRepo`, `ItemRepo`)
- **Service Layer**: For business logic (`RestaurantService`, `ItemService`)
- **DTO Pattern**: For API requests (`RestaurantRequestDTO`, `ItemRequestDTO`)
- **Role-Based Access Control**: Via `StaffRole` enum

### API Endpoints

#### Restaurant Management
- `POST /api/restaurants` - Create new restaurant
- `GET /api/restaurants` - Get all restaurants
- `GET /api/restaurants/{id}` - Get restaurant by ID
- `PUT /api/restaurants/{id}` - Update restaurant
- `DELETE /api/restaurants/{id}` - Delete restaurant

#### Item Management
- `POST /api/items` - Create new menu item
- `GET /api/items` - Get all items
- `GET /api/items/restaurant/{restaurantId}` - Get items by restaurant
- `GET /api/items/{id}` - Get item by ID
- `PUT /api/items/{id}` - Update item
- `DELETE /api/items/{id}` - Delete item

---

## Current Implementation Status

### ✅ Fully Implemented
- Restaurant CRUD operations
- Item CRUD operations
- Restaurant-Item relationship
- Staff model with role-based structure
- Data scoping by restaurant

### 🚧 To Be Implemented
- Order management system
- Customer ordering functionality
- Billing and payment processing
- Table management
- Sales reports and analytics
- Authentication and authorization
- Customer accounts and profiles

---

## Data Models

### Restaurant
```java
- id: Long (Primary Key)
- restaurantName: String
- restaurantAddress: String
- restaurantPhone: String
```

### Item
```java
- id: Long (Primary Key)
- name: String
- price: BigDecimal
- description: String
- category: String
- image: String
- restaurant: Restaurant (Foreign Key)
```

### Staff
```java
- id: Long (Primary Key)
- name: String
- emailId: String
- password: String (hashed)
- role: StaffRole (WAITER, CASHIER, MANAGER)
- restaurant: Restaurant (Foreign Key)
```

---

## Security Considerations
- Passwords should be hashed (not stored as plain text)
- Role-based access control needed for API endpoints
- Data isolation by restaurant ID prevents cross-tenant access
- Authentication middleware to be implemented

---

## Future Enhancements
- Real-time order tracking
- Payment gateway integration
- Customer reviews and ratings
- Inventory management
- Table reservation system
- Loyalty program
- Multi-restaurant support for chains
