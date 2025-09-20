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
- Secure login/logout with JWT and remember-me functionality
- Role-based access control with hierarchical permissions
- Password reset and recovery via email verification
- Custom authentication success and access denied handlers
- Session management with configurable timeout
- Automated security key rotation with scheduled tasks

### Product & Category Management
- Complete CRUD operations for products and categories
- Product search and filtering capabilities
- Category hierarchy management
- Product detail management with size and comment support

### Order Management System
- Order creation and processing workflow
- Multiple payment options (Cash and PayPal integration)
- PayPal QR code generation for mobile payments
- Order status tracking and history
- Order detail management with line items
- Order search and filtering capabilities

### User Management
- User registration and profile management
- Role and permission assignment
- User status management (active/inactive)
- Hierarchical role system (Admin, Manager, User)
- Gmail credential management for email services

### Business Reporting & Analytics
- Sales reports by product with visual charts
- Sales reports by user performance
- Order status analytics and tracking
- Dashboard with key business metrics
- Custom reporting with date range filtering
- Export capabilities for business intelligence

### Email Integration
- Gmail SMTP integration for notifications
- Password reset email functionality
- Order confirmation emails
- Custom email templates and messaging

## Project Structure

```
src/main/java/in/tiepdhm/billingsoftware
├── BillingsoftwareApplication.java
├── config
│   ├── SecurityConfig.java
│   └── StaticResourceConfig.java
├── controller
│   ├── AuthController.java
│   ├── CategoryController.java
│   ├── DashboardController.java
│   ├── ItemController.java
│   ├── OrderController.java
│   ├── PaymentController.java
│   └── UserController.java
├── entity
│   ├── CategoryEntity.java
│   ├── ItemEntity.java
│   ├── OrderEntity.java
│   ├── OrderItemEntity.java
│   └── UserEntity.java
├── filter
│   └── JwtRequestFilter.java
├── io
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
├── repository
│   ├── CategoryRepository.java
│   ├── ItemRepository.java
│   ├── OrderEntityRepository.java
│   ├── OrderItemEntityRepository.java
│   └── UserRepository.java
├── service
│   ├── CategoryService.java
│   ├── ItemService.java
│   ├── OrderService.java
│   ├── StripeService.java
│   ├── UserService.java
│   └── impl
│       ├── AppUserDetailsService.java
│       ├── CategoryServiceImpl.java
│       ├── ItemServiceImpl.java
│       ├── OrderServiceImpl.java
│       ├── StripeServiceImpl.java
│       └── UserServiceImpl.java
└── util
    └── JwtUtil.java

src/main/resources
└── application.properties

uploads

```

## Tech Stack

### Backend Technologies
- **Spring Boot** - Application framework and auto-configuration
- **Spring MVC** - Web framework with DispatcherServlet
- **Spring Security** - Authentication and authorization framework
- **Spring Data JPA** - Data access and ORM layer
- **JSP (JavaServer Pages)** - Server-side view templating
- **MySQL** - Relational database for data persistence

### Architecture Patterns
- **Model-View-Controller (MVC)** - Separation of concerns in web layer
- **Dependency Injection** - Spring container for bean management
- **Role-Based Access Control (RBAC)** - Hierarchical permission system
- **Custom Security Handlers** - Tailored authentication and authorization flows
