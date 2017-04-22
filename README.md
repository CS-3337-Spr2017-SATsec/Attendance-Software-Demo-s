# Attendance-Software-Demo-s
cs3337 project

1.Clone repository to your machine

2.Use that folder as your workspace when using eclipse

NOTE: the servlet files will not be recognized as servlet but rather regular java files, for these files you must create the file manually in eclipse and then copy and paste the content

To recreate the database, download the stars.sql file and import it into the schools phpmydmin(Leo and Jaime)

To connect to the database you need to change the database name, username, and password in the servlet files.

Example:

String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stuXX";
String username = "cs3220stuXX";
String password = "";

XX is your student number for cs3220 course
