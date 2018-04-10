# userapp - this application demostrates how to use angular.js 1.5, SpringBoot and MySql to build a user management feature.

# Script to create the database

  This application requires uses a mysql database called userdb. You can use the "createdatabase.sql" to create the database.
 
# To compile this application you can simply run the following command from the top level folder :
	
  Note 1: This application uses Java 1.8
  
  Note 2: The database connection details are configured in userapp\src\main\resources\application.properties file.
	Update the following parameters before building the application :	
		spring.datasource.url=jdbc:mysql://localhost:3306/userdb
		spring.datasource.username=myuser
		spring.datasource.password=Password123
  
  Note 3: Changing the application port
  
	Application will be running on port 8080 which is configured in userapp\src\main\resources\application.yml file.
	Also there is a reference to this port in C:\dev\git\userapp\src\main\resources\static\js\app\app.js file
	You may have to change in both places.
  
  The following command will build the fat jar that include the appliction code and all the dependencies :
	mvn install

	
# To bring up the application that hosts both the UI application and the REST APIs used by this application, run the following command:
  
  java -jar target\LeeDemoUserApp-1.0.0.jar

# Using the application
	In the browser open the following url : http://localhost:8080/LeeDemoUserApp/
	
	This app has only one page which includes two panels. The top panel shows the list of all existing users and the bottom panel has a dialog for adding and updating user entries. 
	
	To add a new user just add the user name and email in the dialog and click Add.
	To update an existing user click on the 'Edit' against the corresponding entry in the list and the make the changes in the dialog below and click on 'Update' to save the changes.
	To delete just click on the 'Delete' button agains the the corresponding entry in the list.
	
