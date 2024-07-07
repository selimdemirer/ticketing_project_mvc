Ticketing Project (Project Management Tool) - MVC

• Servlet Container is a translator of the HTTP messages for our Java app. And this HTTP is gonna communicate between UI and DB.

• Tomcat (which is one of the famous server) is called a ‘Servlet Container’. Tomcat is already coming embeded and giving us already one servlet object when we create a spring boot application. (We don't need to create object also) This servlet object is doing all these translation job for us, HTTP to Java and Java to HTTP. 

• MVC Process: 

1. The client makes an HTTP request

2. Tomcat gets the client’s HTTP request. Tomcat has to call a servlet component for the HTTP request. Tomcat calls a servlet Spring Boot configured. This servlet is called as Dispatcher Servlet or Front Controller.

3. Dispatcher Servlet’s responsibility is to manage the request further inside the Spring App. It has to find what controller action to call for the request and what to send back in response to the client.

4. Dispatcher Servlet need to find the controller action to call for the request. To find out which controller action to call, the Dispatcher Servlet delegates to a component named Handler Mapping.

5. After finding out which controller action to call, the Dispatcher Servlet calls that specific controller action. The controller returns to the Dispatcher servlet the page name it needs to render for the response. We refer to this HTML page also as “view”

6. Dispatcher servlet delegates the responsibility of getting the view content to a component named View Resolver.

7. Dispatcher Servlet returns in the HTTP response the rendered view.

![img_3.png](img_3.png)

![img_1.png](img_1.png)

![img.png](img.png)

![img_2.png](img_2.png)

• All of them is done by Java except for Controller classes. Whenever we put @Controller annotation, everything all the methods inside is registering in the Handler Mapping. We put @RequestMapping annotation on top of the methods with end points. Those methods return View Resolver.

• We cannot use same "endpoint" (@RequestMapping("/...") in different classes!

• Most famous template engine is Thymeleaf. Because it is built top of the html. So texts are not changing when you use it. You can use html tags with Thymeleaf because its extension is .html (But you cannot do that with JSP, its extension is .jsp).

• Thymeleaf is a Java-based library used to create a web application. It provides a good support serving an HTML5 in web application.

• To be able to integrate Thymeleaf with Spring Boot, we need to add the "spring-boot-starter-thymeleaf" dependency.

• We need to add the attribute ' xmlns:th=“http://www.thymeleaf.org" ' to <html> tag.

• This definition is equivalent to an import in Java. It allows us further to use the prefix “th” to refer to specific features provided by Thymeleaf in the view.

• We always put the thymeleaf tag inside the html tag!

• Whenever we need to move data from your method to thymeleaf, use model.addAttribute!

• Use "attributeName" in the Thymeleaf, not right away the data!

• Use @RequestParam when:

○ You need to pass multiple optional parameters.

○ You want to filter or sort results (e.g., search queries, filter options).

• Use @PathVariable when:

○ You need to specify a specific resource by ID.

○ The value is required and is a fundamental part of the URL structure (e.g., resource identification).

• CrudService: We created this service by using generics. Other services extend this service for common methods.

• AbstractMapService: We created this service as an abstract class  by using generics so that other service implementation classes implement common methods. 

• ?ServiceImpl: All service implementation classes will use @Override to implement the methods and will use super keyword to call the parent class's (AbstractMapService) method implementation. 

• Since we haven't any database yet. We created a map to keep the data and serve as a database. And we put it into the AbstractMapService class. This is a temporary solution until Spring Data.

• DataGenerator in the bootstrap package just for visualization purpose when we run the app. We are saving the data into our "map" for now but we don't need to use that data. Because we didn't create repository/data layer yet.

![img_2.png](img_2.png)

• We haven't got any data yet. But we need to put some data in our map. No role, no user, nothing.. Therefore we created a new package called "bootstrap". We created DataGenerator class which is implementing "CommanLineRunner" interface that is giving by Spring. This interface's job is, whenever we run the app in the main runner, first this "run" method will execute, before doing anything. So, basically we are gonna put anything inside the run method whatever I want to Spring to do for me in the beginning. Because when we start the app, we want to see some uploaded data (like roles) over there.

• If one class has a dependency or if this class is gonna be used as a dependency some other class (basically if we make DI), we need to use @Component (@Controller, @Service)

• If you have an object in the UI, and if you have another object inside it, you need "Converter". (For example: User Create form - Choose A Role dropdown menu)

• @PathVariable ("username") String username: It is used to catch the data from browser to our code. (or we can use query parameter with @RequestParam)

• redirect: It returns method through end point, it is not using html files (view)! It's typically used to avoid duplicate form submissions.   
   return "mentor/mentor-register";    //view   
   return "redirect:/mentor/register";    //method!

• The Post/Redirect/Get (PRG) pattern is a web development design pattern used to prevent certain problems that can occur when handling form submissions. Here's how it typically works:

   Post: When a user submits a form (e.g., a registration form or a search form) using the HTTP POST method, the data is sent to the server. This data could include user inputs like text fields, checkboxes, etc.

   Redirect: After processing the form data on the server side (like saving it to a database), instead of returning a traditional HTML response directly back to the user, the server issues an HTTP redirect response to the client. This redirect sends the client to a new URL, typically the same page that generated the form.

   Get: The client (browser) then makes a new HTTP GET request to the redirected URL. This GET request fetches a fresh copy of the page. This effectively prevents the problem of duplicate form submissions caused by users refreshing the page that was a result of a POST request.

   Why use PRG pattern?

   Prevents Duplicate Form Submissions: Without PRG, if a user refreshes a page that was a result of a POST request, most browsers will re-submit the form data, potentially causing duplicate submissions.

   Better User Experience: By redirecting after a POST request, users are less likely to accidentally resubmit form data when refreshing the page.

   Maintains Clean URLs: Using PRG helps in keeping the URL of the page clean and user-friendly, as it directs back to the original form page after processing.

• If you don't know the attribute name, you need to go .html and find it there (for example: go to table in the task/create.html -> ${tasks})

• If you don't know which attributes the controller method needs to have, you need to go .html and check the "${attributeName}"!

• Summary

• A dynamic page might display different content for different requests.

• To know what to display, a dynamic view gets the variable data from the controller.

• An easy way to implement dynamic pages in Spring apps is using template engine such as Thymeleaf.

• The client can send data to server through request parameters or path variables.

• A controller’s action gets the details sent by the client in parameters annotated with @RequestParam or @PathVariable.

General Project Definition

• A ticketing project is an application that organizes management processes.

• The application will allow client to create and manage users, projects and tasks.

![img.png](img.png)

Requirements

Features that are developing on the project are:

• User management:

• CRUD operations for users.

• Authentication of users. After we login, we will redirect users to the welcome page.

• The user will have a role assigned for it. Provided roles will be ADMIN, MANAGER,
EMPLOYEE.

1. ADMIN will be able to create and manage users.
2. MANAGER will be able to use all the parts related to projects such as creating a
   project, assigning a task to an employee, etc.
3. EMPLOYEE will be able to see all tasks related to him, and change the statuses of
   their assigned tasks.

![img_1.png](img_1.png)

![img_2.png](img_2.png)

![img_3.png](img_3.png)

Project

• CRUD operations for the project.

• Every project created will have a manager responsible for it. When creating a project you must assign a manager.

• The project will be able to have status. Provided statuses for project will be OPEN,
IN_PROGRESS, UAT_TEST, COMPLETE.

• The manager of the project will be able to create tasks for employees.

• The manager will be able to complete the project.

![img_4.png](img_4.png)

![img_5.png](img_5.png)

![img_6.png](img_6.png)

Task

• CRUD operations for the task.

• Every task created will have an employee responsible for it.

• When a task is created by the manager, it will be assigned to an employee.

• The task will be able to have status. Provided statuses for task will be OPEN,
IN_PROGRESS, UAT_TEST, COMPLETE.

• Employees will be able to change the status of the task depending on which phase it is.

• Employees will be able to see their own tasks and to start working on it.

• Employees will be able to see all of their archived tasks.

![img_7.png](img_7.png)

![img_8.png](img_8.png)

![img_9.png](img_9.png)