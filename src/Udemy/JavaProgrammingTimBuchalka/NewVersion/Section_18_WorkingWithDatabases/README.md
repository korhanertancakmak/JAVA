# [Section-18. Working with Databases]()
<div align="justify">

In this section of the course,
I'll be covering how to interact with databases from a Java application.
I'll be primarily focusing on connecting to,
and communicating with a relational database.
I'll show you how to connect to a database,
using a connection string, and a special object,
called a _Java Database Driver_.
We'll get information from the database,
through a database language called **Structured Query Language** or **SQL**.
I'll demonstrate how to execute _SQL_ statements
as strings against tables in the database,
using Java's support, and how to process the results
we get back from these types of queries.
I'll also cover executing parameterized statements,
using Java's **PreparedStatement**,
which allow _SQL_ statements to be reusable,
more flexible, and less prone to some well-known security issues.
Many _Database Management Systems_ support a built-in procedural language as well.
This means there may be functions stored with the database
that you can use to get data, or perform some action,
rather than actually writing _SQL_ code to do it.
I'll be showing you how to make calls to these database stored procedures,
with Java's **CallableStatement**.
Finally, we'll examine different **object relational mapping or ORM** technologies,
used by popular Java frameworks,
to handle basic boilerplate interactions with databases.

To do all of this, we'll need a database to test against.
I originally was using _SQLite_ for this section of the course,
but have decided to switch to _MySQL_ for several reasons.
There were other options,
like selecting one in a memory database like _H2_.
Or I could have chosen alternative relational databases like _PostGreSQL_
which is free and open source, or _Microsoft's SQL Server,_
which has a free version.
I decided on _MySQL_ for several reasons.

* First, It's free, and downloaded from Oracle,
  and the installation isn't too difficult.
* Second, I think it's important to learn about
  _CallableStatements_, and for that,
  we needed a database that supports stored procedures.
  There are workarounds for _H2_ and _SQLite_,
  but I felt the workarounds might have been more confusing to students.
* Third, I offer a beginner's course in _SQL,_ which also uses MySQL,
  so if you want to learn more,
  you'll be in a perfect position to take that course.
* Finally, _MySQL_ is a popular industry standard.

![image01](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image01.png?raw=true)

On this image, I'm showing you several
surveys of the popularity of marketplace databases.
The first is stackoverflow's annual survey,
which solicits feedback from developers on what they use professionally.
This survey is limited to respondents,
so it could be argued it's skewed towards professionals
who are just more likely to take such a survey.
The second is from Statista, and the third from DB-Engines,
which are similar and have similar results.
The last survey polls the internet for monthly statistics,
using the number of mentions of the system on websites,
the frequency of technical discussions,
and the number of job offers as part of their scoring system.
Make sure to check out these surveys, and others if you're interested,
or if you're trying to make this decision for yourself.
However, you look at this feedback _MySQL_ is squarely near the top,
so that's another reason I chose it.
Finally, just because the course will be taught using _MySQL_,
this doesn't mean that you have to use it.
You're welcome to use whichever database you want,
especially if you have another already installed,
and have experience there.
I'll be providing scripts to create a specific database schema
for _MySQL_, which could be tweaked for your database.
In addition, I'll show you several different connection strings
to connect to different databases.
I'll be making an effort to use _ANSI SQL_,
a standard set of commands, which most databases should support.
I'll try to avoid vendor-specific _SQL_ statements,
or point them out when I can.
There are good reasons to use _ANSI SQL_ in your java code.
Doing so lets you swap the database engine
by simply changing your connection string.
This does sometimes happen
as corporations decide the best way to house their data,
based on their own unique situation,
and as technologies change and compete with one another.
I'll explain all of this in more detail as we proceed.
Ok, so let's move forward.
</div>

## [a. Installation For Windows]()
<div align="justify">

In this section, we're going to go ahead 
and install the _MySQL_ database server on a Windows machine.
The first thing we want to do is go to the website _dev.MySQL.com_. 
You may get a pop-up about accepting cookies, 
so do whatever you normally do when you get that pop-up. 

![image02](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image02.png?raw=true)

The download will work fine if you decide to decline all cookies.
Ok, once we get here, I'm going to click the download link.

![image03](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image03.png?raw=true)

I'll scroll down to towards the bottom and click
_MySQL Community GPL Downloads_ and click that.

![image04](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image04.png?raw=true)

We want the _MySQL Installer for Windows link_, so click that.

![image05](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image05.png?raw=truehttps://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image05.png?raw=true)

On the next screen, _Microsoft Windows_ should already be selected.
Below that, we have two choices. 
Both choices will install the same version,
and if you have a connection to the internet, 
then it's not really important which one you choose.
The first one, the _mysql-installer-web-community_ one 
will start the installation 
and then download most of the files it needs, while it's installing.
You'd use that if you want to install _MySQL_ on the computer 
that you're using to download, 
which is what we're doing at the moment.
But you might want to install the program on several computers, 
or on a computer that doesn't have internet access. 
In that case, you can choose the second option,
and download the entire installer. 
As you can see, that's a larger initial download.
Before we decide which one to use, 
notice that there's an Information tab on that screen, 
the blue **Information** icon.
Click that, to get a bit more information 
about the installer choices we have.

![image06](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image06.png?raw=true)

There's a brief description of what the installer includes. 
The latest version of the _MySQL Server_ itself, 
_MySQL Workbench_, which we'll use to interact with the Server, 
and some sample databases and documentation.
Below that, there's a note about choosing the right file. 
That pretty much echoes what I've just said about the two options.
The note is interesting, 
"_MySQL Installer is 32-bit, 
but will install both 32-bit and 64-bit binaries_".
In a minute, I'm going to show you how to select that, 
when we go through the installation process.
So you'll need to know whether your Windows computer 
is running a 32 or 64-bit version of Windows.
If you're running an older version of windows, 
you can generally find that in the system part of a control panel.
Ok, back in my browser, 
click the _General Availability (GA) Releases_tab,
to get back to the choice of installers.
I suggest you grab the full product, the second option.
Because you can re-install it if you need to, 
without having to download again.
I'm going to choose the second one,
so click its _Download_ link.

![image07](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image07.png?raw=true)

Now it's asking whether we want to set up an _Oracle Web Account_, 
because _Oracle_ now owns the _MySQL product_. 
If you already have an account, you can log in. 
We're going to click on _No, thanks, just start my download_,
then click _Save_ file if it doesn't start downloading automatically.

![image08](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image08.png?raw=true)

Now we need to open the file that we've downloaded, 
so I'm just going to select _Open file_ here.
If you're using a different browser, do whatever you'd normally do,
to open a file that you've downloaded.
The installer starts, and I'll give that a moment.
Click on "_Yes_", If it pops up,
to allow this app to make changes to your device,
and the installation should proceed.
It may pop up again and ask you to allow this app
to make changes again.
In my case, it did pop up that twice, so click _Yes_ again.
If you get an option to apply an upgrade,
click _Yes_ to allow the upgrade to be applied as well.

![image09](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image09.png?raw=true)

And now we get this screen, 
where we can choose the setup type.
There are various radio buttons here to the left,
and to the right, it gives you a description of 
what it's actually going to install.
So you can see we have a number of options.
You've got a _Server_ only. 
This installs only the _MySQL server_, 
which would be useful if you want to run 
the server on a different computer 
rather than on your development machine.
We've got a _Client only_ option, 
which is a sort of compliment to the _Server only_ option. 
This is the one we'd installation on our development
and user computers if the server was running on another computer.
We've got a _Full_ option, which installs everything.
But the option we're going to select is the _Custom_ option, 
so that we're installing only the things that we need for this course.
I'm going to click on _Custom_, and I'm going to click on _Next_.

![image10](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image10.png?raw=true)

Once we've done that, we get this screen here. 
On the left-hand side, there's a list of available products, 
and the right-hand side shows a list of the products 
that are to be installed.
Currently, that's empty, because we haven't selected anything yet.
So I'm going to come over here first, 
and expand _MySQL Servers_ by clicking on the plus. 
Do the same to expand _MySQL Server_, 
then again to expand the latest version, 
which for me is version 8.0.

![image11](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image11.png?raw=true)

You may have a later version there, and that's fine. 
Install whatever is the latest version available. 
For me, at the time, that's version 8.0. 
You can see there are older versions I can choose, 
but don't do that. 
Go with the latest version.
In fact, after expanding that, 
we can see version 8.0.36. 
You can see a lot of other versions like 8.0.35. 
Choose the latest version you see.
The X64, at the end, indicates that this is a 64-bit version. 
If you see X86 there, then you'll be installing the 32-bit version. 
You've already checked whether your operating system is 32-bit or 64-bit, 
so make sure that you choose the appropriate version for your operating system.
I mention that because earlier versions of the installer used to 
show both the 32-bit and the 64-bit versions here.
The installer changes whenever there's a new version, 
and Oracle might decide to show both versions again in the future.
So if they do, choose the X64 version on a 64-bit version of Windows, 
and the X86 version on a 32-bit version of Windows.
I'm running a 64-bit version of Windows, 
so I'll select the X64 version, the only one that's showing here, 
then click on the arrow, to move it across to the right-hand side.
So that's now going to be installed.

![image13](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image13.png?raw=true)

The next thing we want to do is come over here, 
to applications, and click on the plus again to expand it. 
_Expand MySQL Workbench_ by clicking on the plus, 
then again to expand _MySQL Workbench 8.0.34.X64_.
Once again, you may have to choose _X64_ or _X86_ 
if those options appear for you.
So I'm going to select the _MySQL Workbench 8.0.34.X64_, 
and click the arrow to move that over to the right-hand side.
I'll ignore all the older versions.
Once again, the version numbers may change in the future. 
In fact, the _MySQL Server_ and _Workbench_ programs might 
even have different version numbers.
That's fine, they are often the same, 
but one program might be updated separately to the other.
Depending on when you're seeing this page, 
they might be a later version,
but the principle is still the same. 
Select the relevant version, 
and select the 64-bit or 32-bit version 
if that option's available.
At this point, we've selected the only two
products we need, so I'm going to click on _Next_, 
and then I'm going to click on _Execute_. 

![image14](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image14.png?raw=true)

This is going to start out downloading the _MySQL_ software first.

![image15](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image15.png?raw=true)

And after that, we will start the installation of both those products
onto this computer.
And once that's finished, 
we need to go through a configuration step,
to set up usernames and so on and so forth.
So it's now finished downloading. 
I'm going to click on Next and Execute to install.

![image16](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image16.png?raw=true)

Now the installation is complete, it's time to configure. 
I'll click Next again. 
That will bring up the configuration options.

![image18](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image18.png?raw=true)

And we've got a _Config Type_ dropdown here,
where we can select various different options.
We're going to leave the default of _Development Computer;_ 
since that's exactly what we're doing here, 
we're using _MySQL_ as developers.
It shouldn't normally be necessary to change any other options here, 
the defaults will work for most computers.
Now in terms of the port, 
it needs to be a unique port number not used anywhere else, 
or it's not used for another application on this computer.
So 3306 will be fine for most computer.
But I'll change it 3335, since it's been used before.

![image19](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image19.png?raw=true)

Normally, 3306 will work for your computer.
The rest of the defaults are okay. 
You don't need to change anything else there, click on Next.

![image20](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image20.png?raw=true)

Now we get to specify the Authentication Method.
You shouldn't normally change this,
we want strong password encryption
that keeps our installation secure.
If you've already written _MySQL_ applications, and they can't be updated, 
then you can specify the old-style authentication here.
But we're starting from scratch, 
and don't have any existing applications to support. 
So we won't change anything here, just click _Next_.

![image21](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image21.png?raw=true)

Now it's asking for what the root password is.
Now the root account is created automatically by _MySQL_, 
and is a logon that is the most powerful user in the system.
And generally speaking, it's good to use that username and password very sparingly.
Only when you really need to do some sort of super administrative tasks.
So what we're going to do is assign a password for it, 
which we need to do, but then we're going to create a new user 
that we're going to be using for our day-to-day use of _MySQL_.
So you want to enter a password that's hard to guess, and is quite secure.
So I'm going to type in a password, 
then confirm it by typing it in again. 

![image22](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image22.png?raw=true)

The password strength is showing as **Strong**, so that's good.
Ok, That's the root user. 
As I said, you'd only log in as this user 
when you need to do administrative tasks.
It's not a good idea to use such a powerful login for normal use.
So the next thing we need to do is click on _Add User_.

![image23](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image23.png?raw=true)

We're going to create another user that we're going to be using 
for the normal tasks in this course.
Type in the name of the user. 
I'm going to call this one _devUser_. 
But you can call it literally whatever you like.
This is the user that we'll log in as
when we're working with our databases.
For the host, I'm going to change that to _localhost_, 
using the dropdown arrow.
_Localhost_ means this computer, so that ensures that 
it can only be used locally. 
That's more secure because we only want to be using this username locally.
We don't want access from any external source.
Some students have reported that they don't get _localhost_ here, 
instead they see the IP address _127.0.0.1_. 
That's fine, that's the IP address that localhost resolves to, 
so choose that address if that's what you see here.
We're going to leave the _Role_ set to _DB admin_. 
There are other options you can choose here, 
but I'm not going to change any of that. 
I'm going to leave it on admin for now.
A database administrator can do things like creating databases and tables, 
which is what we'll want to do in this course.
And we also need to enter a password for this.
I suggest you make this password different to the _root user_.
And make sure you remember both passwords.
I'm going to enter my devUser password, then confirm it.
In this case, you can see again I've got a strong password.
So now we're done with that, click _OK_, then _Next_.

![image25](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image25.png?raw=true)

The next step is to configure the _Windows service_.
I'm not going to go into a lot of details about this, 
you can generally accept the defaults on this screen.
I will mention that _Start the MySQL Server at System Startup_ checkbox.
If you'll be doing a lot of work with _MySQL_,
then you'll probably want to leave that ticked.
But if your computer is low on memory, 
or is quite old and slow, then you should untick that box. 
In a moment, I'll show you what it does, 
and how you can start and stop the service manually.
So we're going to accept all the defaults, and click on _Next_.
Leave everything unchanged on the Server File Permissions dialogue, 
and click _Next_ again.

![image26](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image26.png?raw=true)

We've now configured everything, and this screen just gives us a chance 
to go back if we decide we want to change something.
We don't, so click _Execute_.

![image27](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image27.png?raw=true)

The installer runs through the process of configuring _MySQL_ on this computer.
When it's finished, click on _Finish_.
Click on _Next_, and you can see we're now done 
the installation procedure has been completed.
Leave the checkbox to _Start MySQL workbench_ after setup ticked. 
And that's because we want to run the _Workbench_, 
just to confirm the installation is okay. 
So click on _Finish_.

![image28](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image28.png?raw=true)

I want to start by clicking on this local instance, 
so click on that, and it brings up this little screen. 

![image29](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image29.png?raw=true)

It's asking for the root user password.
So I'm going to type in that root password that I set earlier.
And I'm going to choose not to save it. 
You could choose to save the password if you like,
but for security reasons I prefer not to do that.
Click on _OK_.

![image30](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image30.png?raw=true)

And this has opened up, which is a good sign 
that we got the password correct.
If we come over here, on the left,
and click on _Server Status_, 
that gives us a bit of an overview of our MySQL service.

![image31](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image31.png?raw=true)

That right-hand pane hides a lot of the information,
so click the rightmost button above it.
You can see we've got three panes here,
and each of those buttons corresponds to one of the panes. 
The rightmost one has a blue bar on its right-hand side.
That shows that it represents the right-hand pane, 
and clicking it will toggle that right-hand pane on and off.
When we remove that pane, we can see the graphical stats about our server.
It's Running, as you can see here, so that's good.
We can also see the number of connections and other statistics.
But there won't be a lot of happening at the moment, 
and that's because we haven't really started using it yet.
But bear in mind that we have actually connected to the server, 
because we've used _MySQL Workbench_ to do that, 
in case you're wondering where the connections are coming from.
Ok, come over here, on the left, and click on _Users and Privileges_.

![image32](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image32.png?raw=true)

We can see the list of users, 
so you can see there that we had the root account,
which was automatically created for us, 
and also the devUser account that we created.
You might see a few other users, 
which are used internally by MySQL. 
If you don't see them, don't worry, 
sometimes the default settings are changed, 
and these accounts don't show up. 
That's fine, they're not accounts that we need to do anything with, 
and it's best that we don't make any changes to them.
The account that we'll be using, most of the time, is this devUser account.
We want to be able to create new databases 
and perform all the other database-related tasks 
that we're going to learn about.
That means we have to give this account the necessary access.
Select devUser on the left, then click the _Administrative Roles_ tab.

![image33](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image33.png?raw=true)

You have full control over the level of access
that you can grant to users, 
but it's often easier to use these roles that have already been created.
For example, there's a _UserAdmin_ role 
that can create users, but can't alter databases,
and there's a _DBManager_ role that can manage databases, 
but doesn't have the access needed to create or modify users.
We will want to perform all tasks, 
so click the top option, _DBA_, to make devUser a _Database Administrator_.
We'll be able to perform all tasks when we log in as devUser.
If you make any changes here, 
make sure you click _Apply_ to apply the change; 
otherwise the access rights won't be granted.
All right. 
So click on _View_ now, and from the _View_ menu select _Home_.

![image34](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image34.png?raw=true)

We go back to where we were. 
Now we're going to come over here, 
and click on this little wrench, 
to the right of the plus sign, 
which is for managing connections.

![image35](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image35.png?raw=true)

That should open up this _Manage Server Connections_ screen here, as you can see.
So I'm going to click on this _Local Instance MySQL80_, 
and we're going to click the _Test Connection_ button, at the bottom right.
If it wants a password, that root password, again, then type it in.

![image36](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image36.png?raw=true)

And you can see that we've _Successfully made the MySQL connection_.
So that tells us that MySQL is working correctly,
and we're able to connect to the database.
So that's okay. 
I'm going to click on _OK_.
All right. 
Now let's click on the New button, over on the left. 
This time we're going to add a new connection.
We're going to add a new connection for
what we're going to be using in this course.

![image37](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image37.png?raw=true)

For the _Connection Name_, I'm just going to call it development,
but you can call it whatever you like.
And we're going to change the _Username_.
Instead of root, we're going to change that to the username we created, 
which was devUser.
Then I'm going to click on test connection, 
to make sure we can connect as our development user.
We'll be asked for the password, 
so enter your devUser password this time.
Remembering that this isn't the root user,
this is the other user.
Click on _OK_, 
and you can see we made a successful connection. 

![image38](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image38.png?raw=true)

So that tells us that the username and password are valid,
and we're good to go. 
So click on _OK_.
Close this screen, 

![image39](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image39.png?raw=true)

And we've now got this second connection configured under the _MySQL Connections_.
We're going to be able to use that in the course.
</div>

## [b. Database Basics]()
<div align="justify">

This section is for those of you who are new 
to databases and database terminology.
Here, I'll give you a quick tour of a 
_Relational Database Management System_, or _RDBMS_, 
and the objects we'll be working with, 
in case these might be unfamiliar to you.
At the highest level, data is organized into a schema.
This is used to group related database objects together.
A schema represents a logical grouping, or namespace, 
for database objects such as tables, views, and procedures.

![image40](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image40.png?raw=true)

You might remember when we set up our development connection
in _SQL Workbench_, in the installation section, 
we could have picked a default schema, but we left it blank.
In some database systems, like _PostgreSQL_ and _Oracle_,
the term **schema** is used to represent the namespace 
within a database where objects are organized.
These systems can have multiple schemas within a single database.
In other database systems, like _MySQL_,
the term schema is often used interchangeably with the database.
In _MySQL_, you typically create databases, 
and refer to them as such, 
but the _SQL Workbench_ refers to them as schemas.
I'll be covering the _SQL Workbench User_ interface shortly,
but just enough to get you familiar with it, 
to debug the Java results we get.
Within a schema or database, 
data will be next be organized into additional database objects,
the most common of these is the table.

| Database Object | Description                                                                                                                                                            |
|-----------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Table           | A table stores rows or records of data, in attribute fields, with values specific to that record.                                                                      |
| Index           | An index consists of a table name, a key value and a record locator field, to quickly access a record from a table. A primary key is a unique identifier for a record. |
| View            | A view is a stored query, which can be accessed like a table, but hides the details of the table implementation from the client.                                       |
| User            | A user represents a package a privileges to database artifacts given to an account                                                                                     |

A _table_ is a lot like a spreadsheet, so you have the concept of columns,
which are attribute data you'll be storing.
A row is a record in the table.
Usually a row is indexed in some way,
which allows fast access to that record.
A record will often have one column 
that represents the primary key in the index.
But tables can have many indices, 
and an index can be made up of more than one column.
For example, if you had a student database,
you might have a primary key on a student id,
and a secondary key on last name and first name.

An _Index_ is just another database object, 
which contains information about the table 
its indexing a key value, with a locator to the record.
You'll remember how we used indexes in binary files.
This is the same concept here.

A _view_ is really a stored query, or a special type of stored _SQL_ statement, 
that clients can use, just like a table.
This simplifies queries for the clients, 
and hides implementation details of the tables.

A _User_ is also just a database object, and comes with a set of privileges, 
which control and limit what they can see and modify.
Again, this is all super high level.
You don't really need to be an expert in how databases work,
to access and take advantage of using one.

Like any software, databases also have a language 
you need to learn to use to work with that software.
In databases, the language lets us create objects, 
populate them with information, create relationships, and query data.
This language is called the _Structured Query Language or SQL_.
For Relational Databases, 
the language has become standardized with _ANSI SQL_, 
although each database may still retain 
some of its own unique clauses or commands.
SQL is divided into two categories, 
_Data Definition Language or DDL_, 
and _Data Manipulation Language or DML_,
which serve two very distinct purposes.

**The Data Definition Language** is used to define,
create, manage, and modify the **database objects**.
_DDL_ statements don't manipulate the data in the object,
instead they manipulate data structures 
that store and organize the data.
Some of the most common _DDL_ statements are shown on this table.

| SQL Command | Description                                                                    |
|-------------|--------------------------------------------------------------------------------|
| CREATE      | Used to create database objects like tables, indexes, views, and schemas.      |
| ALTER       | Used to modify the structure of existing database objects.                     |
| DROP        | Used to delete or remove database object.                                      |
| TRUNCATE    | Used to remove all rows from a table while keeping the table structure intact. |
| RENAME      | Used to rename database objects.                                               |

These are _create_, _alter_, _drop_, _truncate_, and _rename_.
It's very common for database objects to be managed by a _DBA_, 
or _Database Administrator_.
I'll be covering a couple of simple _DDL_ statements 
in some examples ahead, but for the most part, 
we'll be focusing on _DML_.

**The Data Manipulation Language or DML** is used to interact with, 
and manipulate, the **data** stored within the database objects or artifacts.
_DML_ statements perform operations 
like _inserting_, _updating_, _retrieving_, 
and _deleting_ data in the database.
Some of the most common _DML_ statements are shown on this table.

| SQL Command | Description                                    |
|-------------|------------------------------------------------|
| SELECT      | Used to retrieve data from one or more tables. |
| INSERT      | Used to add new rows of data into a table.     |
| UPDATE      | Used to modify existing data in a table.       |
| DELETE      | Used to remove rows from a table               |

These commands are often represented with another acronym, 
you might be familiar with.
This acronym is **CRUD**, and it stands for:

* **C**reate: This operation involves creating new records or entries in a database.
This is equal to an INSERT command.
* **R**ead: The read operation involves retrieving or querying data from a database.
This is equal to a SELECT command.
* **U**pdate: Updating refers to modifying existing data in a database.
This is equal to an UPDATE command.
* **D**elete: The delete operation involves removing records or data from a database. 
* This is equal to a DELETE command.

**CRUD** represents the fundamental operations 
for managing data in a database system.
Later we'll cover different Java frameworks 
that all aim to simplify database interactions,
making it easier to perform **CRUD** operations in your Java projects.

So what makes _MySQL_, and many of the other large database management systems, 
like _Oracle_, _SQL server_, and _Postgres_, relational?
Unlike a simple spreadsheet, 
data stored in an _RDBMS_ can be stored in many different tables.
The tables can be associated with one another,
through different kinds of relationships.

| Relationship | Description                                                              |
|--------------|--------------------------------------------------------------------------|
| One to One   | One row in the first table is related to only one row in a second table. |
| One to Many  | One row in the first table is related to many rows in a second table.    |
| Many to Many | Many rows in the first table are related to many rows in a second table. |

The first relationship, _one-to-one_, is not as common as the other two.
There are specific use cases where this makes sense, 
like storing large binary objects, images, for example, in their own table.
Another use case might be to separate out sensitive data, 
like salaries, from general data about an employee.
A _one-to-many_ relationship is much more common, 
and would be cases like an order table with a relationship 
to its order line item table.
A _many-to-many_ relationship is sometimes called a cross-reference.
Imagine a table of students, and a table of courses.
A course will have many students,
and a student may take many courses.
This is a _many-to-many_ relationship.
Designing a relational database a special learned skill,
and usually ends up in a delicate balance between theory 
and performance concerns.
I'm not going to cover this, 
except to introduce you to the term normalization.

**Database normalization** is the process of organizing data in a database, 
mainly to reduce data redundancy, and improve data integrity.
It's a design process that involves breaking down a large table, 
into smaller related tables, and defining relationships between them.
This process is guided by a set of rules, often called normal forms.
The goal of each of these forms is to progressively eliminate redundancy,
meaning a data attribute should be represented in only one table, 
as well as dependency issues.
Once the design process is complete, you'll have a schema full of tables,
with implicit or explicit relationships.
Having data in these separate silos, where no data is repeated,
is great for maintenance and storage, but it complicates data retrieval.

So how do you get information out of these related tables,
without writing many multiple queries?
You do this with something called a **join**.
A **join** is a _SQL_ clause that combines rows from two or more tables, 
based on a common field.
_Joins_ are used to create more complex queries,
and to retrieve data from multiple related tables simultaneously.

| Join Type  | Result                                                                                               |
|------------|------------------------------------------------------------------------------------------------------|
| Inner join | Returns all rows form both tables where the join condition is met                                    |
| Left join  | Returns all rows from the left table, even if there is no matching row in the right table.           |
| Right join | Returns all rows from the right table, even if there is no matching row in the left table.           |
| Full join  | Returns all rows from both tables, regardless of whether there is a matching row in the other table. |
| Cross join | Returns all possible combinations of rows from the two tables.                                       |

An _inner join_ is one of the most common kinds of _joins_.
You'd use this kind of join to get an order and its associated line items.
This would return all the order data, and each line item's columns as well, 
on each record returned.
If there are no order detail line items, an order wouldn't get returned.
There has to be data in both tables in other words.
The left and right join sometimes called outer joins, 
return the full set of records described in the one table 
(either on the left or right in the clause),
and any matching records found in the other.
This means data doesn't have to be joined in both tables 
for records to get returned.
A full join is similar to an outer join, 
except the records from both tables are returned, 
joined where there is a match.
A Cross join is also known as a Cartesian Join, 
which is usually something to be avoided,
except in very specific use cases.
This returns all possible combinations of rows from both tables, 
which can blow up very quickly, to be a huge number of records.
</div>

## [c. Using MySQL WorkBench for a Sample Data]()
<div align="justify">

You can use the command line, 
usually through a special shell provided by the database vendor.
You can use software applications, 
and that's our objective, to build a Java application
that will interact with our database.
But using a special integrated development environment built for databases,
does make life easier, especially when trying to debug issues.
_MySQL Workbench_ is designed for the _MySQL_ database system.
This means that it's a visual tool for database design 
and administration, and _SQL_ development.
Let's take a quick tour.

![image39](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image39.png?raw=true)

You should have two connections on this screen 
if you followed the steps I showed in the installation section.
You'll want to use the development connection, so pick that.
When you log in with this connection, 
your left pane will most likely be displaying Schemas.

![image40](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image40.png?raw=true)

Notice the tabs at the bottom, or the top of the left pane, 
which toggle between the **administration** pane, and the **schemas**.
In the installation section, I already showed you 
how to use a couple of the _Administration Tasks_.
We looked at the server status 
and made changes to the dev user account,
assigning the DBA role to this user.
For most of your work though, you'll be working with schemas.
You'll remember a schema, in MySQL, is another name for a database.
You may see some system databases, _sys_, for example, 
because dev user has the DBA role, 
and therefore has access to elevated privileges, and critical schemas.
You'll want to ignore these, 
they're for use by the system, 
and you can cause serious problems if you mess about with them,
without first understanding them.

First, let's step back, and see what this IDE provides.
As you can see, there are a lot of features in _MySQL Workbench_.
In the main window, a tab is open that says Query 1.
This is where you'd type SQL commands, and it works like a text editor.
This pane has its own ribbon of buttons, along the top here, 
with some functions we'll be using.
We'll have a look at a couple of these, in a moment.
What I want you to do next is select the _Administration_ tab of the left panel.
Before we continue, go to the resources folder of this package _music_database.sql_.
This file was exported, using MySQL, 
and continues all the SQL code needed to create a music schema,
and several tables, as well as a view.
If you want to examine some _Data Definition Language or DDL_, 
this script contains a series of it, 
that will get executed as part of the import process.
In other words, the file will get loaded,
and these SQL queries executed, in the order they're defined in this file.
Let's import this.
Under the Administration tab, you'll see three categories,
_Management_, _Instance_, and _Performance_.
Under Management, the last option is Data Import Restore.
Select that.

![image41](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image41.png?raw=true)

If you have the bottom panel or right panel open, 
you'll want to close these, so you can see the form in its entirety.
You do this by toggling the buttons in the right corner of this IDE.
Optionally, you could turn off the left pane as well.
I'll do this, so I have more screen space.
By default, the _Import from Dump Project Folder_ is selected,
but we want to select Import from self-contained file 
the next radio button has shown there.
And you'll want to use the ellipse button to find, 
and select, the _music_database.sql_ file,
you downloaded from the resources' folder.
You have to know what's in your file, 
and this file includes the creation of the schema, 
so we don't have to specify a default target schema.
In fact, we don't have to do anything else on this screen, 
except click the _Start Import_ button in the lower right-hand corner.

![image42](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image42.png?raw=true)

Next, you'll see the output of the log shown in a second tab.
The final line should say _music_database.sql_ has finished.
Close this tab, and if you did close the left pane, reopen it now.
Select the _schema_ tab in the left pane.
It doesn't look like anything was added, 
and that's because we need to refresh the UI.

![image43](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image43.png?raw=true)

That's the little icon to the right of the text, schemas.
Clicking that should now display music as one of the schemas.
Open the music folder.
Here you can see _Data Objects_ shown.

![image44](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image44.png?raw=true)

These folders are shown whether these objects exist or not, 
allowing you to create new ones.
Open the _Tables_ node next.
Now you can see this database has three tables,
_albums_, _artists_ and _songs_.
Let me show you what's called an _Entity Relationship Diagram_ on below.

![image45](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image45.png?raw=true)

An _Entity Relationship Diagram_ is similar to a _Class_ diagram.
An _Entity_ is a table in this case, so all three tables are shown here.
The column marked as a primary key is identified by the key icon, and _PK_ letters.
The primary key is separated from the other columns as you can see.
The additional columns are listed. 
Relationships are shown with connector lines, which describe how two tables,
or entities relate, to one another.
Both of the relationships shown on this diagram are one to many, 
or many to one, depending on how you look at it.
If I read this document, I have **songs**,
and many songs may be on a single **album**,
a record in the **albums** table.
The _album_id_ is included in the **songs** table, 
as what's called a **foreign key** column.
This column's purpose, in the **songs** table is 
to specify the related record, 
which makes joining the tables much easier.
**Albums** may have many **songs**, 
but in this model, an **album** has only one **artist**, 
so the _artist_id_ is a _foreign key_ column on the **albums** table.
An **artist** however might be on many **albums**,
so it has a _one-to-many_ relationship with **albums**.
Let's now see what this looks like in the _MySQL WorkBench_.

We're going to use this database for many of the upcoming sections,
so it's kind of important that you understand it.
The easiest way to do this is, 
click on the **music** schema in the left pane.
We'll use the schema inspector 
to learn more about the **music** database.
You can right-click on the **music** schema, 
and select schema inspector.
Alternately, you can select the third icon on the toolbar menu,
third from the left, that has an _i_ on it.
This opens a new tab, with a tabbed panel, 
and shows a few details about this schema,
such as _table count_, and an estimated _database size_.
You might wonder why this has a table count of 4, 
and we've only seen three tables.
This count includes views, 
and I'll talk more about the view in a minute.
The tabs are labeled, tables, columns, 
indexes, triggers, views, stored procedures,
functions, grants and events.
Let's click on tables first.
The main thing of interest here is the column labeled rows, 
which tells us how many records are in each table.
Now let's click columns.
This shows the columns for all the tables, 
as well as the view, which is named album view.
You can see the column name, and types, 
and whether nulls are supported in each, for example.
In all cases, except the id fields, the values can be null, 
meaning not every column is required to have data.
Let's look at the _indexes_ tab next.
In this case, we can see each table has a primary index, 
which is unique, and the column used is shown here.
In addition, there's an index for each foreign key, 
and this index isn't unique.
Let's now take a look at some data.
I'll open the _tables_ node, and click on **albums**.
Notice the three icons that appear when this table is selected.
The icon we want is the last, the spreadsheet like one.
Clicking on that opens another tab, with two panels.

![image46](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image46.png?raw=true)

The top bottom shows the SQL statement that was executed.
The bottom panel lists the data in the table.
If this is your first look at an SQL statement, 
this is a widespread statement.
The `SELECT` command will get the data.
A list of columns would follow this command,
but if you enter an **asterisk or star**, 
you're specifying you want the data in every column.
The `FROM` clause will specify what table 
or view the data should come from,
so here it's coming from the **albums** table.
Notice though that the **albums** table includes the schema 
or database name.
Ok, so that's a super quick look at this work bench, 
and the music database.
I want to encourage you to use this database,
to get familiar with workbench.
You can always delete the music database and reimport it, 
so try to spend some time here, if you have it.
Before I end this section, I want to look at the **view**.
A **view** is sort of a _virtual table_.
Let's open the _Views_ node, and select album view, 
and again select that last icon.

![image47](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image47.png?raw=true)

This will do the same thing as we saw with albums,
so the query will be on top, and the data on the bottom panel.
Here, the data doesn't have any IDs,
and has data from all three tables.
This is a full look or view of all the pertinent data in all the tables.
Let's look at data for just a single album.
To do this, I need to add a `where` clause to my `select` statement.

```MySQL  
SELECT * FROM music.albumview where album_name = 'Argus';
```

A `where` clause takes a column name,
so in this case I want to search by an _album_name_,
and I want that album name to be equal to _Argus_.
Strings are always enclosed in single quotes.
To execute this query, I can choose the lightning bolt icon.

![image48](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image48.png?raw=true)

There are two lightning bolts and either would execute this statement.
The first is a bit more flexible 
because it lets you select a portion of a complex statement,
and execute only that selected portion.
If you have no selection, you can execute too.
Now, the data in the bottom panel, the result grid, 
is showing the _songs_ for only the _Argus_ album,
which is an album by the artist, _Wishbone Ash_.
Remember, _album_name_ is stored on the **album** table,
_artist_name_ is stored in the **artists** table,
and _song_title_ is in the **songs** table.
A _view_ is a SQL `SELECT` statement,
that's saved, and given a name.
The data's not saved in the view, 
only the SQL statement is saved that will return that data.
We can use this view's name to easily query data.
You can think of the view as a type of encapsulation.
It's hiding information about the table structures and relationships, 
and displaying only the columns deemed necessary for the client.
In fact, the client might never have to know 
that this data is stored in three tables.
When a table's structure changes, 
the view may not have to change, 
so any software reliant on these structures can remain unaffected.
I'll click on album view again in the left pane,
but this time I'll select the second icon, the tool icon.

![image49](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image49.png?raw=true)

This will display the stored query in a tab.
This statement consists of _DDL_ and _DML_ statements.
It's creating a database object, a view, 
named album view with _Database Definition language_, the command `Create`.
And it's storing a _DML_ `SELECT` statement.
This select statement is executed when we select data from the view.
Notice that the `FROM` clause is selecting data from all three of our _tables_, 
using a series of `JOIN`s.
I'm not going to get into the details of this statement.
The main thing is that this view has all the information 
we'd want to query about the _albums_, _artists_ and _songs_, 
and our java code doesn't have to understand too many details of the schema itself.
We'll be using this view in a lot of our java code, 
which means we don't have to write code, 
with these `join`s in our java code.
</div>

## [d. JDBC]()
<div align="justify">

In the last section, we connected to the MySQL database server, 
using _MySQL WorkBench_.
We already had a connection set up with the username root, 
but we also created a second connection for the dev user.
We used this connection to import the music database import file.

In Java, you connect and communicate with the database 
through something called _JDBC_.
_JDBC_ stands for Java database connectivity.
It's Java's way of providing a consistent way 
to connect to a wide variety of databases, 
including _relational_, _NoSQL_, and _object-oriented_ databases.
It abstracts the complexities of connecting to different databases 
through a common interface.
This means we can use the same code to interact with different databases,
without needing to rewrite, 
or have a separate version of the application logic for each one.
You can think of _JDBC_ as a middleman, 
that sits between a Java application and a data source.
You can also use _JDBC_ with spreadsheets
and flat files, meaning you can use structured query language 
to interact with these files.
To use a particular data source from an application, 
you'll need a JDBC driver for that data source.
A driver is simply a Java library, 
containing classes that implement the JDBC API.
Drivers are usually provided by the database vendor, 
either as a library jar, or a java module, 
which we can import into our application.
The current version of the JDBC API, 
at the time of shooting this section, is JDBC 4.3.
For backwards compatibility, 
it contains all the methods in previous JDBC versions.
You can find this information in the `java.sql` package 
API documentation, which I'll pull up next.

![image50](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image50.png?raw=true)

First, notice, the description that this package provides tools 
for accessing and processing data, usually a relational database,
but can also be used for any data source with a tabular format, 
which includes a spreadsheet or flat file, for example.
Moving down a bit, you'll see all the versions listed.
Below that, you can see the JDBC version and its relationship 
to the JDK version, which is based on the since tag in the documentation.
So for example, if you're using a JDBC 4.2 driver,
you can't expect to have access to features introduced since version 9.
The first note that comes after this list is important.
Many of the new features are optional.
This means there's some variation in drivers and supported features.
It's important to check the driver's documentation
to review what's actually supported.
So why do we need a JDBC Driver?
At the most basic level, these drivers allow us to:

* **Connect** to the database. 
Each database may have a different mechanism to establish a connection
to it.
* The driver lets us **Execute** SQL statements.
These statements can be _DML_ statements, any of the _CRUD_ statements, 
for example, or they can be _DDL_ statements, where you create tables, 
or data artifacts from the application.
* The driver also gives us a way to **Execute Stored** procedures.
This sends a request to the database to execute a procedure 
or function stored in the database.
* We can **Retrieve** and process results.
This could be a set of data from a select statement, 
or a count of rows updated or inserted.
* Finally, the driver provides a uniform method of **Handling Database Exceptions**.
Since there have been changes to the way that JDBC works over time,
you want to be sure you're working with a driver that supports at least JDBC 4.0.
Some Features in JDBC 4.0 and later include:

* Auto-loading of JDBC drivers.
This was a significant improvement, but you might see legacy code 
that uses explicit _Driver Registration_, which I'll demonstrate in a future section.
* Other features are Built-in Connection Pooling.
* Distributed Transactions.
* Row streaming.
* _SQLXML_ support.
* And support for _Try-With-Resources_.

I'll talk about most of these features in the sections ahead.
JDBC consists of two packages, `java.sql` which is core JDBC,
and `javax.sql`, which provides the API for server side data source access.
The `java.sql` package contains most of the types you'll need 
to communicate with your data source.
But `javax.sql` provides two alternative types, for several of the most significant types.

| Purpose            | `java.sql`     | `javax.sql` |
|--------------------|----------------|-------------|
| Makes a connection | DriverManager  | DataSource  |
| Query Results      | ResultSet      | RowSet      |

To make a connection with a driver, you can use either **Driver Manager** or **DataSource**.
**DriverManager** should be replaced with **DataSource** in most cases, 
because it's newer and supports a lot more functionality.
For querying results, there's the standard **ResultSet** type, 
but the **RowSet** interface provides many advantages.
I'll review both of these in the next couple of sections.
Before we move onto those sections,
and get back into Java, 
I'll first walk you through downloading a jdbc driver, for the _MySQL_ database.
I'll google _MySQL connectors_. 
You should see the MySQL connectors, hosted by `MySQL.com` 
displayed as the first option. 
I'll click that.
On this page, you can see a list of drivers for different programming languages.
We want the JDBC Driver, which is `Connector/J`,
so I'll select the download link there.
On this page, you'll need to select your operating system.
And actually, regardless of your operating system,
you can download the **platform independent version**.
I'll select the zip archive, and download that.
There's also a tar archive for non-Windows environments, 
or if you've got a zip utility that supports tar files.
I'll then extract that, after it's downloaded,
to a directory I'll be using in the next section.
Opening that extracted folder, 
the important thing we're after, is the jar file, 
which contains the classes and types needed for this driver.
Make sure you remember where you've extracted this.






In the last video, we talked about what
JDBC is, and downloaded the My SQL's
driver for Java, called Connector slash J.
This is a jar file, which contains all the
packaged classes needed to connect and
execute queries, on a MySQL database.
I've created a new project, calling it
MySQLMusic, with the usual main class
and method, in the dev lpa package.
Before I do anything else, I need to
include the code in that jar file in my project.
So with the project panel open, I can select my
project root, and from the menu,
I'll select Open Module Settings, or F 4 if you're on windows.
On the left pane of this dialog,I'll select Libraries.
I'll click the + sign at the top of the panel,
that says Nothing to show.
Here, you have two choices.
You can get a Java library by selecting
a jar file from an accessible drive,
or if you've got Maven installed, you can
download this from the Maven Repository.
I'll show you this first, then we'll
remove it, and add the jar we downloaded.
To find this driver, I can type in com.mysql
in the dialog there,
and pick the search icon to the right of that.
This displays the different versions of the drivers available on Maven.
You can just pick the latest version there.
Here you can see it's giving me the information
that the mysql connector.j, will be added to
the MySQLMusic module.
So I'll hit Ok there.
And you can see that a couple of jars
are now in the Classes folder here.
If you're already comfortable using maven,
and modules, feel free to go with this.
I'll be talking about Maven
in an upcoming section.
Now I'll remove this, and simply add the jar.
So I'll click on that module in the second pane.
And I'll click the minus sign.
And select OK to the prompt that asks
if I want to remove that library.
I'll pick the plus sign again,
and this time I'll choose Java.
This brings up the file dialog,
so I'll go to the jar, where I extracted
it in the last video, and select it.
And now I've got my jar as a library.
Selecting Ok, I can close this window, and
we're now ready to connect to our music database.
But even before we do that, I think I should talk
a little about usernames and passwords.
One thing you really never want to do,
is put this information in your source code.
This leaves you with a few other options,
such as putting it in a properties
file, or a configuration file,
or soliciting the data from the user.
In a server environment, the connection
and connection details, would be configured
as part of the Datasource, but we won't
be testing in that kind of environment.
So rather than encourage bad habits, for now,
I think we'll prompt a user for the data.
If we use a Scanner with System.in,
to get the information from the console,
we don't have a way to mask the password.
That leaves us with Console, which
we learned early on in the course, doesn't work from the IDE.
Since we haven't covered a lot ofUser Interface options yet,
except for a Swing dialog, I'll just go with two swing prompts.
I'll set these up in the main method.
I'll create a local variable that'll
get populated by calling showInputDialog on a
JOptionPane. This takes a parent component which
would normally be the parent page for example.
Here, I'll just pass null, since we're using this
outside of a true user interface application.
And the prompt will say Enter DB Username.
I'll use a different method on
the JOptionPane for the password.
In this case, I'll first create
a special type of variable.
This is a swing class, JPasswordField, that will
mask the input. Instead of showInputDialog,
I'll call showConfirmDialog, which is similar, but in
this case, I can pass my password field instance,
as the second argument. The prompt will be Enter
DB Password, and the user can choose OK or the
cancel option in this case. I want my password to
be an array of characters, which I'll explain in
a minute, and I'll make that final. If the user
entered OK, then I'll get the password, from the
pf variable, otherwise I'll set it to null;
The getPassword method,
returns a character array, and not a string.
This is because a string might get interned,
and this password, if it were a string, could
inadvertently be stored on the String pool.
A memory dump in any case, could
reveal the user's password.
It's best practice to encrypt or hash the
password, but I won't include that code here.
In most cases, as I've said, this information
will hopefully be securely stored on a server.
I just wanted you to be aware
of a few of these issues.
Ok, so now it's time to
actually make a connection.
First, I'll set up a static string
for what's called the connection url.
This is a string which uniquely describes
how, and what you're connecting to,
and it's determined by the database vendor.
I'll make this private final static, a string
called CONN_String, short for connection string.
For my sql, this will always start with jdbc
colon, my sql colon, then it will be followed
by colon, two slashes, and hostname,
where we can reach the database.
Next, we have another
slash and then the database name. So music.
Depending on what database vendor you're using,
your connection string may be a little different.
On this slide, I'm showing you a
few of the more common databases,
and what a basic connection string might
look like if the database was named music.
You can see that some of the details,
after the jdbc: vendor part, may vary.
Postgres and MySQL are similar, but Oracle,
in this example, includes anat sign,
before the host, and Microsoft SqlServer,
has a semi colon after it's port, so be
sure you understand the right connection
string you need to use for your vendor.
So we use the connection string
as input, to get a connection.
As I mentioned in the previous video,
there's two methods to do this.
The first is with the DriverManager class.
Like opening a file, you'll want to open a
connection in a try with resources block.
Inside the try parentheses, I'll set up a
Connection variable, calling it simply connection,
and calling a static method on the DriverManager
class, named getConnection.
This method takes the connection string,
and optionally the username and password.
Here, I'll turn my character array into
a string, within the call to the method.
If we get here, then we've successfully connected to the
database, and I'll print that message.
Another best practice, once you've used the password for
whatever you need it for, you should reset the
characters, so the password is only in memory
for the shortest time possible.
Here, I'll set the password to all spaces. I'll catch any
SQLException that's thrown. If there's a problem,
I'll re-throw a runtime exception.
I don't have to close the connection,
because I've created it (and implicitly
opened it) in this try with resources code.
The connection will get automatically
closed when this code completes.
Ok, so let's run this.



I'll enter dev user in the first prompt,
as you'll want to do, if you followed
along with the MySQL WorkBench video.
Next, I'll get prompted for the password.
You'll want to enter your own password,
that you set up for this user, in this field.
Notice that I can't see the password, because
I used the swing JPasswordField.
My application will print Success,
Connection made to the music database.
So now, we're successfully connecting to
the MySQL database from a Java application,
using the DriverManager class to do it.
Before we do anything else, I'll show
you how to connect using a Datasource.
Before the try with resources statement,
I'll add a couple of lines of code.
The first statement will create a variable,
using type inference, called dataSource,
and I'll set that to a new mySQLDataSource,
which is My SQL's implementation of this type.
This constructor doesn't have any arguments.
SoI set the connection string by called setURL on that object.
In this case,I've created a basic DataSourceimplementation.
This will produce a standard connection object,
much like I'd get with the Driver
Manager, so it won't be pooled,
or used in a distributed transaction.
If you're working in a multi-tiered
production environment, your client would
get a datasource instance a different way.
You'd use something called a JNDI naming service.
I'll be talking about this service, when I cover
client server applications
a bit later in the course.
When you get a datasource through
JNDI, you wouldn't need to specify
the URL or the username and password.
Let's now get a connection using this datasource.
First I'll comment out the first part of the
try with resources, so the first two lines.
After this I'll add a try with resources,
but get the connection with the data source.
This starts out the same as
before, but this time I'll call
getConnection on the datasource variable.
This code compiles, but if we run it,



I get an error, because I didn't pass the
username or password that was supplied by
the user, to the datasource's get
connection method, so this fails.
Again, if I had gotten the datasource instance
from the naming context or JNDI, I could use this
getConnection method with no arguments.
But since I'm using a basic datasource,
I do have to pass the user name and password I
get, from the swing inputs, so I'll do that now.
I'll pass the username, and
the password as a string.
And rerunning this code,
I've now got my success message.
I could also have used the setUser and
setPassword methods, as I did with setURL.
In fact, I can use datasource without a
connection string at all, by using the set
methods on this class.
Let's do this.
I'll comment out the setURL method.
Now, I'll start typing dataSource.set
Take a minute to scroll through the
set methods available on this class.
It wouldn't be an understatement to
say, there's a lot of options here.
We'll just focus on the basics.
I'll pick setServerName from
the list, and pass that localhost.
I'll next setPort, to 3 3 0 6.
And I'll set the database name to music.
I'll run this code, and enter the user and password,



and I get the same result.
I'm not going to get into all the
complexities of the datasource type,
in this introduction to the subject.
In this case, there's not much difference
between these two methods, since both
connection types are basic connections.
But Datasource is newer, and generally
preferred over DriverManager.
Datasource should definitely be used in
a multi tiered environment, that requires
connection pooling or distributed transactions.
Ok, so now that we can connect, it's time to do
some of the fun work, which is getting data from
the database, so let's move on to the next video.



</div>



<div align="justify">

```java  

```

```html  

```

</div>



<div align="justify">

```java  

```

```html  

```

</div>



<div align="justify">

```java  

```

```html  

```

</div>



<div align="justify">

```java  

```

```html  

```

</div>



<div align="justify">

```java  

```

```html  

```

</div>



<div align="justify">

```java  

```

```html  

```

</div>