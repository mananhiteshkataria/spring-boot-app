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
 This does all the configuration required for web & rest applications like server configuration, front controller configuration, scanning all the @component, @service , @repository, @RestController classes to create their objects in the spring container.

- DataJPA : 
  This does all the configuration required for database beans like DriverManagerDataSource injecting toJDBCTemplate,HibernateTemplate & so on,it automatically reads application.properties for data source information like username , password , url, driverclassname etc.

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

ReST stands for Representational State Transfer which is used to make heterogeneous applications to exchange the data regardless of the technology they are using, ReST web-services uses Http methods to specify the operations each Http methods must have some URL's which is used by the applications who wants to communicate.

Http Methods

1. HTTP Post- To perform store/create operation
1. HTTP Get- To perform retrieve/fetch operation
1. HTTP Put- To perform update/modify operation
1. HTTP Delete- To perform remove/delete operation
   
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


## If you face issues ##

```

stop project

project clean

project maven clean

project maven build (goals as package) if success run

if fails 

build path change jre library

maven update project

rebuild with goals as package

and you should have success and run again

```
## Spring Data Jpa ##

- Automate database configurations

- Uses Interface 

- Automate Crud Operations on any entity class , it provides some interfaces like 

- CrudRepository<T>
- JpaRepository<T>

- These interfaces you must extend with the interface you want in DAO layer and spring boot will automatically implement the interface based on the equity 

- Interface EmployeeRepository extends JpaRepository <orm,integer>{}

- Here Employee is an Entity class mapped to employee table, EmployeeRepository gets all the methods of JpaRepository like save(object),deleteById(id),findAll(),getById(id)and so on
- Note: Spring boot will take care providing a proxy for implementation for the EmployeeRepository so that all the methods would work on table without any problem.
- We need to only inject the EmployeeRepository to the @Service class.


  Methods are

1. save(T obj)-T
2. deleteById(T primaryKey):void
3. findAll() -List<T,>

``` java
@Entity
@Table("employee")
public class Employee {@Id,empId,name,salary}
>> DAO Layer
interface EmployeeRepository extends JpaRepository <Employee,Integer>
{
	
}
```
>> No Implementation required for EmployeeRepository because 
>> Spring boot does the implementation of all the methods your job
>> is to only call save(),delete(),findAll(),findById() methods


## Service Layer ##
``` java
@Service
public class EmployeeService
{
 @AutoWired
 EmployeeRepository dao;
 //call crud methods in service layer as below
 dao.save(obj), dao.findAll(), dao.findById(value)
}
```

## Setup for Spring boot & Spring data jpa ##

1. Add Spring Data Jpa & Derby library
      1. spring starter data jpa
      2. derby client
2. Create an entity class that maps to employee table
   1. Start the database server(startNetworkServer)
3. create an interface in dao layer that extends JpaRepository <T,ID>
4. Create a class in service layer that injects dao layer object
5. Configure application.properties with data source like username,password,url,driverClassName
6. Update the Rest Controller class to perform the operations defined in service layer class
   
  ```java 
	//To perform delete by id
   	//Service
   	@Transactional
   	public void deleteEmployee(int id){
   	dao.deleteById(id);
   }
   //Controller
   
   	ping("/delete/{id}")
   	public ResponseEntity<Object>delete(@PathVariable("id")	int id)
   	{
   		service.deleteEmployee(id);
   	}
   
   //to perform update salary by id in service & controller
   
   	@Transactional
   	public employee updateEmployee(int id,double salary){
   	Employee e = getEmployee(id);
   	e.setSalary(salary);
   	dao.save(e);// this is optional
   	}
   
   // Creating Custom Exception
   public class EmployeeNotFOundException extends Exception{
	public EmployeeNotFOundException(String err){
		super (err)
	}

	throw new Employee
   }
   ```
   
   ## CORS ##
   
   Cross Origin Resource Sharing is disabled by default at the back end if any front-end needs to be access hence you must enable which front-end can access back end by configuring CORS at the back end application.
   
   Spring uses an annotation called @CrossOrigin(origin={}) which allows you to configure all the origins who can access the application.
   
   If angular which is running in http://localhost:4200 then we must add this origin in @CrossOrigin as below
   
   @CrossOrigin(origin={"http://localhost:4200"} on top of the controller

   ## Integrating Angular with Spring Boot Services

   Steps:

   1. Add @CrossOrigin in spring boot web-service
   2. Create angular project & select yes for routing , select css for styling (name of the project: employee-ui)
   3. Ensure Database, Spring Boot application is running
   4. Add Bootstrap in the angular
   5. Add FormsModule , ReactiveFormsModule,HttpClientModule in AppModule of app.module.ts
   6. Create components for storing,fetchALL,update
   7. All the components you create can be shown in root component or using routers.
