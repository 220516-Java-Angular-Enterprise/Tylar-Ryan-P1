# Tylar-Ryan-P1
Project 1
Java Enterprise Foundations Project Requirements
Project Description

Our P1 reimbursement project aims to automate reimbursement processes of a company.
it will allow an Admin to enter new employees of the company and assign their role or position with the company.

Project Design Specifications and Documents
Relational Data Model
Relational Model

![image](https://user-images.githubusercontent.com/79468669/173850810-a2584a45-7af8-4a2c-8e2e-8b8350c4318c.png)


##### Reimbursement Types
Reimbursements are to be one of the following types:
- LODGING
- TRAVEL
- FOOD
- OTHER


### Technologies

**Persistence Tier**
- PostGreSQL (running on Docker)

**Application Tier**
- Java 8
- Apache Maven
- Postman
- Tomcat
- AWS
- Dbeaver
- Intellij


### Functional Requirements

An new employee or new finance manager can request registration with the system
An admin user can approve or deny new registration requests
The system will register the user's information for payment processing
A registered employee can authenticate with the system by providing valid credentials
An authenticated employee can view and manage their pending reimbursement requests
An authenticated employee can view their reimbursement request history (sortable and filterable)
An authenticated employee can submit a new reimbursement request
An authenticated finance manager can view a list of all pending reimbursement requests
An authenticated finance manager can view a history of requests that they have approved/denied
An authenticated finance manager can approve/deny reimbursement requests
The system will send a payment request when a reimbursement request is approved
An admin user can deactivate user accounts, making them unable to log into the system
An admin user can reset a registered user's password
### Non-Functional Requirements

- Basic validation is enforced to ensure that invalid/improper data is not persisted
- User passwords will be encrypted by the system before persisting them to the data source
- Users are unable to recall reimbursement requests once they have been processed (only pending allowed)
- Sensitive endpoints are protected from unauthenticated and unauthorized requesters using JWTs
- Errors and exceptions are handled properly and their details are obfuscated from the user
- The system conforms to RESTful architecture constraints
- The system's is tested with at least 80% line coverage in all service and utility classes
- The system's data schema and component design is documented and diagrammed
- The system keeps detailed logs on info, error, and fatal events that occur

### Suggested Bonus Features
- Authenticated employees are able to upload an receipt image along with their reimbursement request
- The system notifies the user of changes to their account registration status by email
- The system notifies the user of changes to their reimbursement request status by email
- Document your API using a tool like OpenAPI/Swagger
- Run your application within a Docker container
- Automate builds using GitHub Actions
