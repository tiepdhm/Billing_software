<h1 align="Center">Retail Billing Software</h1>
<p>
    A Spring Boot Backend side project, using a React Frontend provided by the original author for interface purposes. The system simulates a full-stack retail billing experience, including product management, secure login, order tracking, and online payments.

    [![Watch the video demo of my project](https://img.youtube.com/vi/3AOAeSWZQyI/maxresdefault.jpg)](https://youtu.be/3AOAeSWZQyI)

</p>

<a name="table-of-contents"></a>

## Table of contents

-   [Table of contents](#table-of-contents)
-   [Features](#features)
    -   [Authentication & Security System](#authentication)
    -   [Category Management](#category)
    -   [Item Management](#item)
    -   [Order Management System](#order)
    -   [Payment Integration](#payment)
    -   [Image Handling](#image)
-   [Project Structure](#project-structure)
-   [Tech Stack](#tech-stack)
    -   [Backend Technologies](#backend)
    -   [Architecture Patterns](#pattern)
-   [Getting Started](#started)
-   [Acknowledgments](#acknowledgement)

<a name="features"></a>

## Features

<a name="authentication"></a>

### Authentication & Security System
- Secure login/logout with JWT using Spring Security
- Role-based access control (Admin/User)

<a name="category"></a>

### Category Management
- Add/View/Delete categories

<a name="item"></a>

### Item Management
- Add/View/Delete and manage products under each category
- Add product images

<a name="order"></a>

### Order Management System
- Order creation and processing workflow
- Checkout via Stripe
- Save customer & order data

<a name="payment"></a>

### Payment Integration
- Stripe integrated for payment handling
- Orders saved post successful transaction (tested in Stripe sandbox)

<a name="image"></a>

### Image Handling
- Product images uploaded and stored **locally**
  
<a name="project-structure"></a>

## Project Structure

```
billingsoftware                                           # Backend files
├── src
└── uploads                                               # Product images uploaded and stored locally

client                                                    # Frontend files

billingsoftware/src/main/resources/application.properties # Configuration properties file

billingsoftware/src/main/java/in/tiepdhm/billingsoftware
├── BillingsoftwareApplication.java
├── config                                                # Spring Configuration
│   ├── SecurityConfig.java                               # Security filter chain
│   └── StaticResourceConfig.java                         # Local storage config
├── controller                                            # MVC Controllers
│   ├── AuthController.java                               # Authentication flows
│   ├── CategoryController.java                           # Category operations
│   ├── DashboardController.java                          # Order report dashboard
│   ├── ItemController.java                               # Item operations
│   ├── OrderController.java                              # Order processing
│   ├── PaymentController.java                            # Payment processing
│   └── UserController.java                               # User management
├── entity                                                # Domain Entities
│   ├── CategoryEntity.java
│   ├── ItemEntity.java
│   ├── OrderEntity.java
│   ├── OrderItemEntity.java
│   └── UserEntity.java
├── filter
│   └── JwtRequestFilter.java                             # JWT authentication filter
├── io                                                    # Data Transfer Objects
│   ├── AuthRequest.java
│   ├── AuthResponse.java
│   ├── CategoryRequest.java
│   ├── CategoryResponse.java
│   ├── DashboardResponse.java
│   ├── ItemRequest.java
│   ├── ItemResponse.java
│   ├── OrderRequest.java
│   ├── OrderResponse.java
│   ├── PaymentDetails.java
│   ├── PaymentMethod.java
│   ├── PaymentRequest.java
│   ├── PaymentVerificationRequest.java
│   ├── StripeOrderResponse.java
│   ├── UserRequest.java
│   └── UserResponse.java
├── repository                                            # Data Access Layer
│   ├── CategoryRepository.java                           # Standard JPA repositories
│   ├── ItemRepository.java
│   ├── OrderEntityRepository.java
│   ├── OrderItemEntityRepository.java
│   └── UserRepository.java
├── service                                               # Business Logic Layer
│   ├── CategoryService.java                              # Service interfaces
│   ├── ItemService.java
│   ├── OrderService.java
│   ├── StripeService.java
│   ├── UserService.java
│   └── impl                                              # Service interface implementations
│       ├── AppUserDetailsService.java
│       ├── CategoryServiceImpl.java
│       ├── ItemServiceImpl.java
│       ├── OrderServiceImpl.java
│       ├── StripeServiceImpl.java
│       └── UserServiceImpl.java
└── util
    └── JwtUtil.java                                      # JWT helper utility
```

<a name="tech-stack"></a>

## Tech Stack

<a name="backend"></a>

### Backend Technologies
- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA (Hibernate)
- MySQL
- Stripe Payment Gateway
- Maven

<a name="pattern"></a>

### Architecture Patterns
- **Model-View-Controller (MVC)** - Separation of concerns in web layer
- **Dependency Injection** - Spring container for bean management
- **Role-Based Access Control (RBAC)** - Hierarchical permission system
- **Custom Security Handlers** - Tailored authentication and authorization flows

<a name="started"></a>

## Getting Started

1. Create `application.properties`, `constants.js` by copying the example file:
   
   ```bash
   cp billingsoftware/src/main/resources/application.properties.example billingsoftware/src/main/resources/application.properties
   
   cp client/src/utils/constants.js.example client/src/utils/constants.js
3. Fill in the required values in `application.properties` and `constants.js`.

### Backend (Spring Boot)

```bash
cd backend
./mvnw spring-boot:run

cd frontend
npm install
npm run dev
```
<a name="acknowledgement"></a>

## Acknowledgments

This project was implemented based on a tutorial video that helped me understand the basics of building a retail billing system.

[Build your own Retail Billing Software tutorial](https://www.youtube.com/watch?v=_UNE39gZrV4)

