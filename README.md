# Softwarehuset A/S - project management system

An implementation of a time management system for the company Softwarehuset A/S. It implements the ability to create projects with activities, assign employees, and register/view time for activities.

## Installation
You can either do

1. Clone the repository

```git clone [repository link]```

```cd [repository directory]```

Compile the project (if using Maven)

```mvn compile```


2. Use the provided zip folder

Download the zip file from the given link and extract it

```unzip path_to_zip_file.zip -d destination_folder```

Navigate to the project directory

```cd destination_folder```

If using Maven follow the steps above.

## Usage
Write about using gui
1. Run GUI through executing App.java
2. Login with one of the Users such as JD01

### Test cases
We have defined a user database which can be seen in the users.txt file. For login in the gui one of these users will work.

An example is the user with ID: JD01. 

To login you only need the ID of the user.

### Running the tests
You can either run tests using Maven

```mvn test```

Or manually with JUnit in your editor.

The tests are divided into cucumber tests found in src/test/cucumber and systematic tests found in src/test/whitebox. They can be run separately with JUnit or as part of the whole project.
