Customer Query Tracker (CQT)
Table of Contents
About the Project
Features
Technologies Used
Getting Started
Prerequisites
Installation
Usage
Database Schema
Contributing
License
Contact.
About the Project
Customer Query Tracker (CQT) is a web-based application designed to streamline the management of customer queries, ensuring efficient communication between customers and employees. The application supports role-based access control, providing different features for administrators, employees, and customers.
eatures
User Registration: Allows new users to register with specific roles (Admin, Employee, Customer).
Role-Based Authentication & Authorization: Secure login with different access levels for each role.
Query Management: Employees can view and respond to customer queries.
User Management: Admins can manage all users, assign roles, and monitor system activity.
Technologies Used

Getting Started
Prerequisites
Make sure you have the following installed:

Java 17 (Amazon Corretto preferred)
Maven
MySQL
Git
Installation
Clone the repository:

bash
Copy code
git clone https://github.com/your-username/customer-query-tracker.git
Navigate to the project directory:

bash
Copy code
cd customer-query-tracker
Install dependencies:

bash
Copy code
mvn clean install
Set up the MySQL database:

Create a new database (e.g., cqt_database).
Update the database URL, username, and password in application.properties.
Run the application:

bash
Copy code
mvn spring-boot:run
Usage
Open your web browser and navigate to http://localhost:8080.
Register a new account or log in with existing credentials.
Based on your role (Admin, Employee, or Customer), access the appropriate features:
Admin: Manage users, view system reports.
Employee: Respond to customer queries.
Customer: Submit queries, view responses.
Database Schema
[Include a visual diagram of your database schema or a description of the tables and their relationships.]

Backend: Java, Spring Boot, Spring Security, Hibernate
Frontend: HTML, CSS, JavaScript, Thymeleaf
Database: MySQL
Build Tool: Maven
Version Control: Git
Other Tools: Lombok, Amazon Corretto JDK 17
