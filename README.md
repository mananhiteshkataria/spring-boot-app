# spring-boot-app

## Spring ORM ##

ORM stands for Object Relational Mapping , which helps you to directly map java objects to the database table , it gives you the feature where you can directly store Java Objects and retrieve java objects without writing any SQL query.

## Features ##

- Provides inbuilt methods to store , update , delete & retrieve Java objects without SQL query
- Database table & Column names you don't have to use while perform crud operations
- Entity class are used to represent the table information for the java object , this helps to recognize the table names & columns while performing CRUD operations
- You will perform CRUD operations on entity class object

## Hibernate Template ##
 It is an inbuilt class that has methods to perform CRUD operations 

## Spring JDBC vs Spring ORM 

Both are used to interact with the database but Spring ORM has some advantages

<table>
<thead><th>Spring JDBC</th><th>Spring ORM</th></thead>
<tbody><tr>
<td>JDBC Template is used to perform operations</td>
<td>Hibernate Template is used to perform operations</td>
</tr>
<tr><td> JDBC Template uses sql query which are database dependent </td>
<td> Hibernate Template uses inbuilt methods that takes care of generating sql query based on underlying database</td></tr>
<tr><td>If DB changes you need to change query written in code</td>
<td> You don't need to change query because SQL is not used</td>
</tr>
<tr>
<td> Table and column names are used in query</td>
<td> Table and column names are used in entity class , the entity class object & its properties recognize the table and column details</td>
</tr>
<tr>
<td> You need to take care of converting SQL records to JAVA objects & vice versa i.e. row Mapper we used to convert  SQL records to objects</td>
<td>Hibernate Template methods will take care of converting JAva to Sql records and vice versa , you don't need any RowMapper here</td>
</tr>
</tbody>
</table>

## Spring Boot ##


It simplifies spring configurations in the application automatically with the inbuilt spring boot starter libraries, you don't need to have XML file at all any application related configuration you need can be configured in a property file in simple key value configuration spring boot takes care of configuring the application based on inbuilt spring boot starter libraries

Some of the starter libraries:
- Web :
 This does all the configuration required for web & rest applications like server configuration, front controller configuration, scanning all the @component, @service , @repository, @restcontroller classes to create their objects in the spring container.

- DataJPA : 
  This does all the configuration required for database beans like DriverManagerDataSource injecting to JdbcTemplate,HibernateTemplate & so on,it automatically reads application.properties for datasource informations like username , password , url, driverClassname etc.

- Spring Cloud : 
  This does configurations required for cloud environments like service discovery , load balancer and etc. 

## How Spring boot automates the configuration ##

It uses @SpringBootApplication annotation which takes care of auto configuring the application based on the library you have in project , a class must have @SpringBootApplication and this class must be loaded to trigger the automation

``` java
	@SpringBootAutomation
	public class AppStarter
	{
		main()
		{
			SpringApplication.run(AppStarter,class);//triggers Automation
		}
		
	}
```

The moment **AppStarter** is loaded it looks for all the libraries in the project & also **application.properties** to perform the configuration required.

Note : By default all the classes are scanned by looking the sub-package of the class @SpringBootApplication , hence you must have all the classes in the sub-package of the class having @SpringBootApplication.

@AutoWired : It is used to supply dependency to an object

@Component , @Service,@RestController,@Repository : These annotations are written on top of the class which lets spring to create the object and maintain in the spring container
``` java
			@Component
			class A{}
			@Component
			class B
			{
				@AutoWired
				private A obj;
			}
			@Service
			class C
			{
				@AutoWired
				private B objB;
				//object of B is injected to C object
			}
```
All the objects like A,B & C are created in spring container.

## RESTfull Web Services ##

ReST stands for Representational State Transfer which is used to make heterogeneous applications to exchange the data regardless of the technology they are using, ReST webservices uses Http methods to specify the operations each Http methods must have some URL's which is used by the applications who wants to communincate.

Http Methods

1. HTTP Post: To perform store/create operation
1. HTTP Get: To perform retrieve/fetch operation
1. HTTP Put: To perform update/modify operation
1. HTTP Delete: To perform remove/delete operation
   
## URL ##

Every Operation you must perform must have an URL which is used by another application to communicate.

## Creating Rest Web Services using SpringBoot ##

We need to use spring boot starter web library to create ReST web services because it gives you all the configurations required for web applications & ReST web services like

- Server Configuration
- Component scanning - @Component , @Service , @RestController and etc
- Dependency Injection using annotations like @AutoWired
- application.properties - for some configurations
## Spring Initializer: ##
  It is a website by spring that helps you quickly get a spring boot project template

## Change Port Of Server ##

You must use application.properties in resources

``` application.properties
server.port=9090
```