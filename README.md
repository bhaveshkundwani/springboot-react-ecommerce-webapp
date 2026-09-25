# Spring Boot + React E-Commerce Web App

A full-stack e-commerce application with a **Spring Boot** REST API backend and a **React (Vite)** frontend. Supports product management with image uploads, product search, and an order placement/checkout flow.

## Tech Stack

**Backend**
- Java 17
- Spring Boot 4.1.1 (Spring Web, Spring Data JPA)
- PostgreSQL
- Lombok
- Maven

**Frontend**
- React 19
- Vite 7
- React Router
- Axios
- React Bootstrap
- React Toastify

## Features

- Browse products with images, price, category, and stock availability
- Add, update, and delete products (with image upload)
- Search products by name, description, brand, or category
- Add products to cart and checkout
- Place orders and view order history
- Automatic stock quantity updates on order placement

## Project Structure

```
├── Backend/            # Spring Boot REST API
│   └── src/main/java/com/ecom/
│       ├── controller/  # REST endpoints
│       ├── service/     # Business logic
│       ├── repository/  # Spring Data JPA repositories
│       ├── model/       # JPA entities
│       └── dto/         # Request/response DTOs
└── Frontend-Main/       # React (Vite) frontend
    └── src/
        ├── components/  # UI components
        └── Context/     # Global app state (cart, etc.)
```

## Getting Started

### Prerequisites

- Java 17+
- Maven
- Node.js 18+
- PostgreSQL

### Backend Setup

1. Create a PostgreSQL database:
   ```sql
   CREATE DATABASE ecommdb;
   ```
2. Set your database credentials as environment variables (or update `Backend/src/main/resources/application.yaml`):
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/ecommdb
       username: ${DB_USERNAME}
       password: ${DB_PASSWORD}
   ```
3. Run the backend:
   ```bash
   cd Backend
   ./mvnw spring-boot:run
   ```
   The API will start on `http://localhost:8080`.

### Frontend Setup

```bash
cd Frontend-Main
npm install
npm run dev
```

The app will start on `http://localhost:5173`.

## API Endpoints

| Method | Endpoint                     | Description                  |
|--------|-------------------------------|-------------------------------|
| GET    | `/api/products`               | Get all products             |
| GET    | `/api/product/{id}`           | Get product by ID            |
| POST   | `/api/product`                | Add a new product             |
| PUT    | `/api/product/{id}`           | Update a product             |
| DELETE | `/api/product/{id}`           | Delete a product             |
| GET    | `/api/product/{id}/image`     | Get product image            |
| GET    | `/api/products/search?keyword=` | Search products             |
| POST   | `/api/orders/place`           | Place a new order            |
| GET    | `/api/orders`                 | Get all orders               |

## Author

**Bhavesh Kundwani**
