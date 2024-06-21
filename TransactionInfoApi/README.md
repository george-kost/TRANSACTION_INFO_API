# Demo Project for Spring Boot

This project is a Spring Boot application for managing beneficiaries, accounts, and transactions. It supports retrieving beneficiary details, associated accounts, transactions, calculating account balances, finding the largest withdrawal in the last month, and refreshing the database with data from CSV files.

## Table of Contents

- [Endpoints](#endpoints)
- [Database](#database)



### Prerequisites

- Java 17 or higher
- Maven


Endpoints
1. Retrieve Beneficiary Details
   Endpoint: /api/beneficiaries/{id}
   Method: GET
   Example: http://localhost:8080/api/beneficiaries/1
2. Retrieve Accounts of a Beneficiary
   Endpoint: /api/beneficiaries/{id}/accounts
   Method: GET
   Example: http://localhost:8080/api/beneficiaries/1/accounts
3. Retrieve Transactions of a Beneficiary
   Endpoint: /api/beneficiaries/{id}/transactions
   Method: GET
   Example: http://localhost:8080/api/beneficiaries/1/transactions
4. Retrieve Balance of Accounts of a Beneficiary
   Endpoint: /api/beneficiaries/{id}/balance
   Method: GET
   Example: http://localhost:8080/api/beneficiaries/1/balance
5. Retrieve the Largest Withdrawal for a Beneficiary in the Last Month
   Endpoint: /api/beneficiaries/{id}/largest-withdrawal
   Method: GET
   Example: http://localhost:8080/api/beneficiaries/1/largest-withdrawal
6. Refresh Database with CSV Data
   Endpoint: /api/data/refresh
   Method: POST
   Example: http://localhost:8080/api/data/refresh

Database
The application uses an in-memory H2 database. You can access the H2 console to browse the data:

Open your browser and go to http://localhost:8080/h2-console.
Enter the following details:
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password: password
Click Connect.
Initial Data
The database is populated with data from the following CSV files located in src/main/resources:

beneficiaries.csv
accounts.csv
transactions.csv