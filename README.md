# Simplified PicPay - API

I came across this project online and chose to implement it myself as a way to study and improve my skills.

## Technologies used:

- Java 21
- SpringBoot 3
- Spring Data JPA
- Hibernate Validator
- OpenFeign
- PostgreSQL
- Docker

## Features:

- The core feature of this project is implementing money transfers between accounts, following specific business rules: *"common"* users can send and receive funds, while *"merchants"* are only allowed to receive them. Each transfer must go through an external authorization service before being completed. If the authorization fails, the transfer is canceled and all related operations are rolled back.
- In addition to the core implementation, several error and exception handling mechanisms were added to ensure the API's stability and proper operation, as demonstrated in the examples below.

### Example 1 - Successful transfer
```bash
HTTP 200 OK

{
    "amount": 5,
    "sender": {
        "id": 3,
        "fullName": "Leoncio Martins",
        "email": "leoncionmartins@gmail.com",
        "balance": 15.00,
        "userType": "COMMON",
        "creationTimestamp": "2025-04-29T14:20:03.779408"
    },
    "receiver": {
        "id": 2,
        "fullName": "Fernando Silva",
        "email": "fernandosilva@gmail.com",
        "balance": 200.00,
        "userType": "MERCHANT",
        "creationTimestamp": "2025-04-29T11:49:39.687019"
    }
}
```
### Example 2 - When the authorization service fails
```bash
HTTP 503 SERVICE UNAVAILABLE

{
    "title": "Authorization failed, try again later",
    "status": 503,
    "instance": "/transfer"
}
```

### Example 3 - When the merchant try to tranfer money
```bash
HTTP 400 BAD REQUEST

{
    "title": "Unauthorized",
    "status": 400,
    "detail": "Merchants are not allowed to transfer money",
    "instance": "/transfer"
}
```
### Example 4 - When the user tries to transfer an amount greater than his balance
```bash
HTTP 400 BAD REQUEST

{
    "title": "Insufficient balance",
    "status": 400,
    "detail": "Your balance must be greater than or equal to the transfer amount",
    "instance": "/transfer"
}
```

### And more handled exceptions like:

- User not found
- Credentials already registered
- Input parameter validation
- Malformed JSON

## Closing Remarks

This project is still a work in progress and has great potential for further improvement. The main goal so far has been achieved, but there are still important features and enhancements to be implemented. One of the next planned steps is the implementation of unit tests to ensure code quality and reliability. Additional improvements may include better exception handling coverage, more robust validation, and integration with external services or databases.

Contributions and suggestions are always welcome!

## Credits:

The idea for this project was based on a challenge found in the following GitHub repository:
- https://github.com/PicPay/picpay-desafio-backend
