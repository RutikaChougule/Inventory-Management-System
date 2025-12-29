# Inventory Management System (Design + Code)

## Overview
This repository contains backend design, database schema, and
Spring Boot code snippets for an Inventory Management System.

The focus is on:
- Transaction handling
- Data consistency
- Proper database modeling
- Production-ready backend practices

## Tech Stack (Proposed)
- Java
- Spring Boot
- Spring Data JPA
- MySQL / PostgreSQL

## Code Structure (Current State)
This repository currently contains:
- Controller & Service layer code snippets
- SQL schema design
- API logic explanation

The project can be refactored into a full Spring Boot application.

## Key Design Decisions
- `@Transactional` used for atomic operations
- Composite unique keys for inventory
- Inventory history tracking
- Many-to-many supplier-product mapping
- Separate service and controller layers

## API Example
GET /api/companies/{companyId}/low-stock-alerts

Returns low stock inventory alerts with supplier info.

## Database Tables
- Company
- Warehouse
- Product
- Inventory
- InventoryHistory
- Supplier
- Supplier_Product
- Product_Bundle

## Future Enhancements
- Full Spring Boot project setup
- Swagger documentation
- Authentication & authorization
- Caching for inventory lookups

## Author
Rutika Chougule

