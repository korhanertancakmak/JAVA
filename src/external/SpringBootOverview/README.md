# [Spring Boot - Quick Start]()

In this course, you will learn how to:

* develop Spring Boot applications
* leverage Hibernate/JPA for database access
* develop a REST API using Spring Boot
* create a Spring MVC app with Spring Boot
* connect Spring Boot apps to a Database for CRUD development
* apply Spring Security to control application access
* leverage all Java configuration (no xml) and Maven

## [Spring Boot - Qverview]()
<div align="justify">

Spring's a very popular framework for building Java applications.
It provides a large number of helper classes and annotations.
But now, the problem is that building a traditional Spring 
application is really hard, because you have a couple of questions.

* Which JAR dependencies do I need for this Spring project?
* How do I set up configuration?
* Should I use XML configuration or Java configuration?
* How do I install the server?
Tomcat, JBoss, WebSphere and so on.

And that's just the basics for getting started.
You haven't even really started building your real application yet.
So this is where the **Spring Boot** solution comes into play.

* Spring Boot makes it easier to get started with Spring development.
* It minimizes the amount of manual configuration you have to do.
* Spring Boot will perform the auto-configuration based on your properties file 
and the JAR classpath.
* It also helps to resolve dependency conflicts (Maven or Gradle).
* Spring Boot also provides an embedded HTTP server, 
so you can get started quickly.

So out of the box, it has support for embedded Tomcat, Jetty or Undertow, 
and those are simply HTTP servers 
that you can embed in your Spring Boot application.

Now what's the relationship between **Spring Boot** and **Spring**?
Well, Spring Boot uses Spring behind the scenes.
Spring Boot simply makes it easier to use Spring.
So at a very high level, you're using Spring Boot, 
but behind the scenes, there's still Spring code running.
So you'll need to learn Spring Boot and also learn Spring, 
and we'll cover all of that during this course.

Now Spring Boot provides the **Spring initializer**.
So this is a website for quickly creating a starter spring project.

![image01]()

Basically, you go to this website at [start.spring.io](https://start.spring.io/).
You select your dependencies, 
and then it'll actually create a **Maven** or **Gradle** project for you 
that you can download and import it into your IDE.
So you can import it into Eclipse, IntelliJ, NetBeans or so on.
Or you could use a plain text editor 
and use **Maven** at the command line.
And then you're okay and ready to go.
And we'll actually use Spring Initializer a lot 
in some of the following sections with setting up our **Spring Boot** projects.
So you'll get a lot of hands on practice with this.

So Spring Boot provides an embedded HTTP server,
so you can get started quickly.
So it has a port for Tomcat, Jetty, and Undertow.
So again, like I mentioned earlier, 
there's no need to install a server separately.
So you'll have your application like `mycoolap.jar`
so this JAR file will include your application code,
and it'll also include the embedded server.
So we'll have Tomcat embedded as part of your JAR file.
So the nice thing about this is that it's a self-contained unit.
There's nothing else that you have to install.
So there's no separate Tomcat installation you need
or JBoss installation or whatever.
You actually have the application server as part of your code, 
and you can run it independently, 
or you can run your application standalone.

So basically we can run our app standalone
because our apps include the embedded server.
So if I wanted to run my **Spring Boot** app, 
I could run it from the IDE 
or I could run it from the command line.
So again, we have this `mycoolapp.jar` 
that includes my code and also the embedded server, in this case Tomcat.
So at my command line, I could simply type `> java -jar mycoolapp.jar`.
In this case, `mycoolapp.jar` will actually run my application,
and it'll also spin up the server.
And my app is up and running in a standalone fashion.
Okay, so then you're probably wondering, 
all right well that's cool to kinda run it as just a Jar file,
but you know, my manager wants me to deploy our applications in a traditional way,
like my manager wants me to deploy a WAR file.
Well, no problem.

Spring Boot apps can also be deployed in the traditional fashion.
So you can deploy a WAR file to an external server like Tomcat, JBoss, 
or WebSphere, and it can work just like you would use it in the past.
All right, so here we have this Tomcat server.

![image02]()

Let's say it's deployed somewhere on your corporate network.
Then you could take your Spring Boot app, and then 
you can deploy that Spring Boot app as a WAR file with `.war` extension.
So now as a war file, you only have your code included.
There's no need to have the embedded server
because now you're deploying it in a traditional sense.
There's already a Tomcat server installed running elsewhere,
and we're simply deploying our WAR file to that server.
So that piece is fine.
And then also on that Tomcat server, 
there may be other project teams 
that are deploying their apps in the traditional way too.
So you can have the travel team deploying our travel WAR,
the e-commerce group, the shopping group deploying that WAR.
So you can take your Spring Boot app,
and you can also deploy it as a WAR file
to a traditional server like you did in the past.
So there are always some frequently asked questions
that I get regarding Spring Boot.

* Does Spring Boot replace Spring MVC, Spring REST, etc ...?
The answer is no.
So there's no competition here.
Instead, Spring Boot actually uses those technologies in the background.
So you have your Spring Core, you have your Spring AOP Spring MVC, Spring REST.
Spring Boot can use all of those technologies in the background.
There's no competition, there's no replacement.
Spring Boot is mainly about configuration.
So once you do your Spring Boot configs,
then you can make use of the regular Spring coding.
So there's no competition, there's no replacement.
Again, Spring Boot is really about helping 
you get started quickly with minimal configuration.

* Does the Spring Boot code run faster than regular Spring code?
And again, the answer's no because behind the scenes 
Spring Boot uses the same code of the Spring framework.
And like I stated, earlier Spring Boot is all about making it easier 
to get started by minimizing the configuration.
But behind the scenes, you have your normal Spring Core, Spring REST, 
Spring MVC and so on.

* Do I need a special IDE for Spring Boot?
And again, the answer is no.
You can use any IDE for Spring Boot apps.
You can even use a plain text editor.
The Spring team actually provides a free Spring Tool Suite, STS, 
which is basically a collection of IDE plugins,
and some IDEs provide fancy Spring tooling support.
So it's not a requirement.
So feel free to use the IDE that works best for you,
or you use Maven, the command line and a plain text editor, totally up to you.
</div>

### Spring Boot Initializr Demo
<div align="justify">

In this section, we'll get a demo of the **Spring Boot Initializr**.
Earlier we discussed the Spring Initializr.
It's a website where you can quickly create a starter Spring project.
So, it's at `start.spring.io`.
We basically go here and select our dependencies.
It'll actually create a Maven/Gradle project for us,
and then we can actually import that project into our IDE, 
like Eclipse, IntelliJ, NetBeans, and so on.
That's the basic idea of the Spring Initializr,
and we'll actually use this in this demo coming up here.

Now let's have a quick word on Maven.
When building your Java project, you may need additional JAR files.
For example, Spring, Hibernate, Commons Logging JSON and so forth.
And one approaches to simply download the JAR files 
from each project website and then, 
manually add those JAR files to your build classpath.

However, Maven provides a nice solution.
We simply tell Maven the projects that you're working on the dependencies, 
like Spring, Hibernate, etc.
Maven will go out and download the JAR files for those projects for you.
And Maven will automatically make those JAR files available 
during compile and run.
And you can kind of think of Maven as like your friendly helper or your personal shopper.
You give Maven a shopping list, say, 
"hey, I need dependencies A, B, C, and D" 
and Maven will go out, grab those jar files, add them to your classpath
and make them available during compile and run.
A really nice feature, and we'll use Maven in this course.
Now, we'll just kind of cover the basics at the moment.
I have more information on Maven,
I'll give you later in the course, 
but for now just think of Maven as a nice utility
that'll actually download the JAR files,
and make them available to your project,
like your friendly helper or your personal shopper.

So for an actual development process,
the first thing we'll do is configure our project 
at the Spring Initializr website, `start.spring.io`.
We'll download the actual zip file that it creates for us,
and then we'll actually unzip that file.
And then finally, we'll import that Maven project into our IDE.
All right, so let's go ahead and dive in and let's get started.

![image03]()

Okay, so let's go ahead and open up a web browser.
And so, the first step is configuring a project
at the Spring Initializr website.
So let's go ahead and visit `start.spring.io` in our web browser.
All right, so we're here at the Spring Initializr website.
We can generate different types of projects.
Here, we'll select Maven, different languages here.
I'll choose Java.
And then also, as far as the Spring Boot version.
I'll choose the most recent version that they have here.

![image04]()

We can move into our project metadata where we can set up our coordinates here.
So I'll set up the group ID, `com.luv2code.springboot.demo`.
And as far as the artifact ID, this is the actual name of my application,
so I'll just call it `mycoolapp`.
And for dependencies here, this is where we go through 
and basically just choose the Spring Boot starters that we want
or the actual dependencies that we want
for our applications.
So here, I'll just keep it simple.
I'll just choose web.
So this will give us the full stack web development
with Tomcat and Spring MVC.
So be sure to click that option
and make sure it's selected and it appears here
as far as selected dependency.
And then from there, just go down to the bottom
and download the zip file by clicking on generate project.
So in the bottom corner there of your browser
you'll see that the file was downloaded.
So that's mycoolapp.zip.
I can swing over to my file system
and the next step is unzipping that file.
So I'll just move into my downloads directory
on my computer.
And here's that mycoolapp.zip that was just created
by the Spring Initializr website.
I'll simply unzip it or uncompress it.
And then, I'll basically just take this folder here
and just copy it.
So in my file system
I have a directory here called Dev Spring Boot.
This could be any directory on your computer.
I'd just like to move things to a different directory
because the downloads directory tends to get mangled.
So I'll just paste this item here into Dev Spring Boot.
Again, this could be any location on your file system.
And so inside of here, we have this Pom file
and some other files and source and so on.
And we'll talk more about this in a bit.
Okay, so let's go ahead and swing over
to your favorite IDE.
So I'll just import this Maven project.
And now I just need to browse over
to where the Maven Project is located.
So, I know it's in this Dev Spring Boot directory
that I'm using on my file system
and it's in the my cool app directory.
And I just hit open.
So give it some time.
It's gonna import the Maven Project and actually
download all the appropriate Maven dependencies
that's needed for this given item.
So I'll go ahead and speed the video up a bit
and make it past all of this wait time.
All right, so there we go.
So Maven is finished downloading the internet.
And so now I have this my cool app as my Maven project here
and there are a number of files that are in this project.
So we have the Palm xml, that's the Maven file.
We have some Maven W files.
I'll talk more about those later.
Then we also have this mycoolapplication.java.
There's a resources directory
and there's also a test directory.
So in later videos
we'll actually cover every file on this project.
So don't worry.
So we'll cover everything, so don't worry
we'll go through this complete.
But for right now
let's just take a look at this Spring Boot application.
It's called my cool application.
We make use of a little fancy spring annotation up here
for Spring Boot application.
I'll talk more about that later.
But basically says, hey
we have this Spring Boot application
and then we use this piece here to actually
bootstrap the Spring Boot application.
So we say springapplication.run
and we give the actual class name that we're gonna run.



So that's our my cool application.
And again, I'll get into all the gory details
on how this Spring Boot application annotation works
and also, how the main method works.
But for now, let's just try and run it



just really quickly here.
So it's important here when you run this



that you run it as a Java application, not server



because Spring Boot actually includes its own server
so there's no need to run it on the server.



Just run it as a Java application.



And then if we review the logs here
we'll see some interesting pieces of information.
So it's starting our my cool application.
And remember, Spring Boot includes an embedded server.
So it says, ooh, Tomcat is initialized on port 8080, nice.
I like that.
That's really cool.
And then at the bottom it'll say, hey
Tomcat has been started on port 8080, so this is great.
So our application is started up and running.



So that means we have an application that's running



at, that has an embedded Tomcat server with it.
So this is really awesome.
So if I swing over to my web browser here
and I just open up a new tab
and I simply go to local host 8080
and oh, error page.
Okay, well, this is okay for now.
We haven't added any real code to our project yet
so we haven't added any controllers
no view pages and so on.
We'll do that in later videos, but at least at this point
we know that a server is up and running



and we simply need to do some more coding
or configuration for it.
But again, we'll get into all those glory details in a bit.
Okay, so we have the basics going
but we still need to do a bit more work.
And so we'll cover that in the next video.


</div>



<div align="justify">


</div>



<div align="justify">


</div>



<div align="justify">


</div>



<div align="justify">


</div>



<div align="justify">


</div>



<div align="justify">


</div>