<h1 align="Center">Retail Billing Software</h1>
<p>
    A Spring Boot Backend side project, using a React Frontend provided by the original author for interface purposes. The system simulates a full-stack retail billing experience, including product management, secure login, order tracking, and online payments.
</p>

<a name="table-of-contents"></a>

## Table of contents

-   [Table of contents](#table-of-contents)
-   [Description](#description)
-   [Features](#features)
-   [Installation](#installation)
    -   [Requirements](#requirements)
    -   [Clone the project](#clone-the-project)
    -   [Build from source](#build-from-source)
    -   [Generate code](#generate-code)
    -   [Linting](#linting)
-   [Usage](#usage)
    -   [Running in local mode](#running-in-local-mode)
    -   [Running in distributed mode](#running-in-distributed-mode)
    -   [Editing problem statements](#editing-problem-statements)
    -   [Custom client](#custom-client)
-   [Config](#config)
    -   [CLI Arguments](#cli-arguments)
    -   [Config files](#config-files)
-   [TODO](#todo)
-   [License](#license)

<a name="features"></a>

## Features

### Authentication & Security System
- Secure login/logout with JWT 
- Role-based access control (Admin/User)

### Category Management
- Add/View/Delete categories

### Item Management
- Add/View/Delete and manage products under each category
- Add product images

### Order Management System
- Order creation and processing workflow
- Checkout via Stripe
- Save customer & order data

### Payment Integration
- Stripe integrated for payment handling
- Orders saved post successful transaction (tested in Stripe sandbox)

### Image Handling
- Product images uploaded and stored **locally**

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

## Tech Stack

### Backend Technologies
- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA (Hibernate)
- MySQL
- Stripe Payment Gateway
- Maven

### Architecture Patterns
- **Model-View-Controller (MVC)** - Separation of concerns in web layer
- **Dependency Injection** - Spring container for bean management
- **Role-Based Access Control (RBAC)** - Hierarchical permission system
- **Custom Security Handlers** - Tailored authentication and authorization flows

## Getting Started

1. Create `application.properties`, `constants.js` by copying the example file:
   ```bash
   cp billingsoftware/src/main/resources/application.properties.example billingsoftware/src/main/resources/application.properties
   cp src/utils/constants.js.example src/utils/constants.js
2. Fill in the required values in application.properties and constants.js.

### Backend (Spring Boot)

```bash
cd backend
./mvnw spring-boot:run

cd frontend
npm install
npm run dev
