Software Name: College Management System

Created By:
Tamir Eliasy 216430298
Ohad Gips 215426883

About The Software:
This code suppose to help colleges the manage their system including adding new lecturers, committees and department.
Also managing data like wages of lecturers, articles that the lecturer published, their degree kind and name.

Features:
1. Adding a lecturer (name, ID, kind of degree, name of degree, wage). For a Doctoral/Professional
   lecturer the articles are entered as a single comma-separated line; a Professional lecturer also
   provides the institution that granted the professorship.
2. Adding a committee (name, chairperson, degree Type, lecturers in the committee)
3. Assigning a lecturer to a committee
4. Assigning a New Chairperson to a committee
5. Removing a lecturer from a committee
6. Adding a study department (name, number of students, lecturers)
7. Adding a lecturer to a study department
8. Removing a lecturer from a study department  [NEW]
9. Showing the average salary of all lecturers in college and in a certain department
10. Displaying all lecturers information and Seeing all committees information (with customizable sorting options)
11. Comparing between doctors and comparing between committees
12. Duplicating an existing committee
13. All college data is automatically saved and loaded on the next run
14. New in this version (interactive input wizards):  [NEW]
    - Every multi-step input flow (add lecturer / committee / department, assign or remove from a
      committee, add or remove from a department, compare doctors/committees) now runs through an
      interactive, step-by-step "wizard".
    - During any step you can type 'back' to return to the previous step and re-enter it, or 'cancel'
      to abort the whole action without changing anything.
    - Each value is validated the moment it is entered, so mistakes are caught early and you are asked
      to re-enter only the incorrect field.

How To Use?
1. Ensure you have a Java IDE or Java on your PC (Java 21 or newer is required).
2. Place all the project files into the same directory
3. To run the project, compile and execute the Main.java file using any Java IDE or via the command line on any system with Java installed
*Note: Upon running the system, you will be asked to enter a college name. Entering a new name will start a fresh system. Entering a name of a previously saved college will automatically load its data from the local backup file.
