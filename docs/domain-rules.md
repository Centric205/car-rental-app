# Domain Rules — Car Rental API

## User Rules
- A user must have a unique email address
- A user cannot create a booking without authentication

---

## Booking Rules
- A booking must belong to exactly one user
- A booking must reference exactly one car
- A booking cannot exist without a valid start and end date
- Booking end date must be after start date

---

## Car Rules
- A car can exist without images
- A car cannot be booked if marked unavailable

---

## Car Image Rules
- An image belongs to exactly one car
- An image cannot exist without a car
