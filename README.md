# Employee Management System
JavaFX Desktop Application

---

## What is this?

A desktop app built with JavaFX that manages employee records. You can sign in, add employees, view them in a table, and delete them. I chose Employees as the business entity because it covers a good range of data types and JavaFX controls.

---

## How to run

You need JDK 17+ and Maven installed. Once you have those, open a terminal in the project folder and run:

mvn clean javafx:run

The first run will take about a minute while Maven downloads JavaFX automatically. After that it launches straight away.

Demo accounts: admin / admin123 or user / 1234

---

## What it does

- Sign-in screen with validation
- Dashboard with a welcome message and employee count
- Form to add a new employee (9 fields, various input types)
- Table showing all employees in a separate window
- Delete employees from the table
- Scene switching within the same window throughout the app

---

## Project structure

Main.java is the entry point. The project is split into five packages:

- model: Employee class and Department enum
- data: EmployeeRepository, which stores all employee records
- service: AuthService, which handles login logic
- ui: SceneManager for scene switching, and Styles for all the CSS
- views: LoginView, DashboardView, EmployeeFormView, EmployeeTableView

---

## A few design decisions

I used a Singleton for the repository so every part of the app shares the same list of employees. I used an ObservableList so the table updates automatically when a new employee is added without needing a refresh. The Department field is an enum instead of a plain String to prevent invalid values. Employee fields are all final with no setters to keep the data safe once created.


---

## Version control

This project is tracked with Git and hosted on GitHub as part of CSC265 Mini Project 2.
The `target/` build folder is excluded via `.gitignore`; only source files and `pom.xml` are committed.