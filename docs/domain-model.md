# Domain Model — Car Rental API

## Overview
This document describes the core domain entities and their relationships
for the Car Rental system.

---

## Core Entities

### User
Represents a customer using the car rental platform.

**Attributes**
- id
- fullName
- email
- passwordHash
- createdAt

---

### Car
Represents a vehicle available for rental.

**Attributes**
- id
- brand
- model
- year
- pricePerDay
- availabilityStatus

---

### Booking
Represents a reservation made by a user for a car.

**Attributes**
- id
- startDate
- endDate
- status
- totalPrice

---

### CarImage
Represents images associated with a car.

**Attributes**
- id
- imageUrl

---

## Entity Relationships

- A User can have many Bookings
- A Booking belongs to exactly one User
- A Booking belongs to exactly one Car
- A Car can have many CarImages
- A CarImage belongs to exactly one Car
