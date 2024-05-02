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

First, let's step back and see what this IDE provides.
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

## [d. Connecting to a database with JDBC]()
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
This is a jar file, which contains all the packaged classes 
needed to connect and execute queries, on a _MySQL_ database.

```java  
public class Main {
    
    public static void main(String[] args) {

    }
}
```

I've created the usual main class and method.
Before I do anything else, 
I need to include the code in that jar file in my project.
So with the project panel open, 
I can select my project root, and from the menu,
I'll select _Open Module Settings_, or _F4_ if you're on windows.
On the left pane of this dialog, I'll select _Libraries_.
I'll click the _+_ sign at the top of the panel, that says _Nothing to show_.
Here, you have two choices.
You can get a _Java_ library by selecting a jar file 
from an accessible drive, or if you've got _Maven_ installed, 
you can download this from the _Maven Repository_.
I'll show you this first, then we'll remove it, 
and add the jar we downloaded.
To find this driver, I can type in _com.mysql_ in the dialog there,
and pick the search icon to the right of that.
This displays the different versions of the drivers available on _Maven_.
You can pick the latest version there.
Here you can see it's giving me the information that the mysql `connector.j` 
will be added to my module.
So I'll hit _Ok_ there.
And you can see that a couple of jars are now in the _Classes_ folder here.
If you're already comfortable using maven and modules, 
feel free to go with this.
I'll be talking about _Maven_ in an upcoming section.
Now I'll remove this, and simply add the jar.
So I'll click on that module in the second pane.
And I'll click the minus sign.
And select _OK_ to the prompt that asks if I want to remove that library.
I'll pick the plus sign again, and this time I'll choose _Java_.
This brings up the file dialog, so I'll go to the jar, 
where I extracted it in the last section, and select it.
And now I've got my jar as a library.
Selecting _Ok_, I can close this window, 
and we're now ready to connect to our music database.
But even before we do that, 
I think I should talk a little about usernames and passwords.
One thing you really never want to do is put this information in your source code.
This leaves you with a few other options,
such as putting it in a properties file, or a configuration file,
or soliciting the data from the user.
In a server environment, the connection and connection details would be 
configured as part of the _Datasource_, 
but we won't be testing in that kind of environment.
So rather than encourage bad habits, for now,
I think we'll prompt a user for the data.
If we use a **Scanner** with `System.in`,
to get the information from the console,
we don't have a way to mask the password.
That leaves us with **Console**, 
which we learned early on in the course, doesn't work from the IDE.
Since we haven't covered a lot of User Interface options yet,
except for a **Swing** dialog, 
I'll just go with two **swing** prompts.
I'll set these up in the _main_ method.

```java  
public class Main {
    
    public static void main(String[] args) {

      String username = JOptionPane.showInputDialog(null, "Enter DB Username");

      JPasswordField pf = new JPasswordField();
      int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter DB Password", JOptionPane.OK_CANCEL_OPTION);
      final char[] password = (okCxl == JOptionPane.OK_OPTION) ? pf.getPassword() : null;
    }
}
```

I'll create a local variable that'll get populated by calling _showInputDialog_ 
on a **JOptionPane**. 
This takes a parent component which would normally be the parent page, for example.
Here, I'll just pass **null**, 
since we're using this outside a true user interface application.
And the prompt will say _Enter DB Username_.
I'll use a different method on the **JOptionPane** for the password.
In this case, I'll first create a special type of variable.
This is a **swing** class, **JPasswordField**, 
that will mask the input. 
Instead of _showInputDialog_, I'll call _showConfirmDialog_, 
which is similar, but in this case, 
I can pass my password field instance,
as the second argument. 
The prompt will be _Enter DB Password_, 
and the user can choose _OK_ or the_cancel_ option in this case. 
I want my password to be an array of characters, 
which I'll explain in a minute, 
and I'll make that final.
If the user entered _OK_, then I'll get the _password_, 
from the _pf_ variable, otherwise I'll set it to **null**;
The _getPassword_ method, returns a character array, and not a string.

This is because a string might get interned,
and this password, if it were a string, 
could inadvertently be stored on the **String** pool.
A memory dump, in any case, could reveal the user's password.
It's best practice to encrypt or hash the password, 
but I won't include that code here.
In most cases, as I've said, 
this information will hopefully be securely stored on a server.
I just wanted you to be aware of a few of these issues.

```java  
public class Main {

    private final static String CONN_STRING = "jdbc:mysql://localhost:3335/music";

    public static void main(String[] args) {

      String username = JOptionPane.showInputDialog(null, "Enter DB Username");

      JPasswordField pf = new JPasswordField();
      int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter DB Password", JOptionPane.OK_CANCEL_OPTION);
      final char[] password = (okCxl == JOptionPane.OK_OPTION) ? pf.getPassword() : null;
    }
}
```

Ok, so now it's time to actually make a connection.
First, I'll set up a static string for what's called the connection url.
This is a string that uniquely describes how, 
and what you're connecting to,
and it's determined by the database vendor.
I'll make this private final static, 
a string called _CONN_String_, short for connection string.
For _mysql_, this will always start with jdbc colon, mysql colon, 
then it will be followed by colon, two slashes, and hostname,
where we can reach the database.
Next, we have another slash and then the database name. 
So music.
Depending on what database vendor you're using,
your connection string may be a little different.

| Vendor               | JDBC Connection URL                                                                                                               |
|----------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| MySQL                | `jdbc:mysql://[hostname]:[port]/[database]`<br/> example: "`jdbc:mysql://localhost:3335/music`"                                   |
| PostgreSQL           | `jdbc:postgresql://[hostname]:[port]/[database]`<br/> example: "`jdbc:postgresql://localhost:5432/music`"                         |
| Oracle               | `jdbc:oracle:thin:[@[hostname]:[port]/[database]]`<br/> example: "`jdbc:oracle:thin:@localhost:1521/music`"                       |
| Microsoft SQL Server | `jdbc:sqlserver://[hostname]:[port];databaseName=[database]`<br/> example: "`jdbc:sqlserver://localhost:1433;databaseName=music`" |
| SQLite               | `jdbc:sqlite:database.db`<br/> example: "`jdbc:sqlite:music.db`"                                                                  |

On this table, I'm showing you a few of the more common databases,
and what a basic connection string might look like 
if the database was named _music_.
You can see that some details, after the jdbc **vendor** part may vary.
_Postgres_ and _MySQL_ are similar. 
However, _Oracle_, in this example, includes an `@` sign,
before the host, and _Microsoft Sql Server_,
has a semicolon after it's port.
So be sure you understand the right connection string
you need to use for your **vendor**.

```java  
public class Main {
    
    private final static String CONN_STRING = "jdbc:mysql://localhost:3335/music";
    
    public static void main(String[] args) {
        
        String username = JOptionPane.showInputDialog(null, "Enter DB Username");

        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter DB Password", JOptionPane.OK_CANCEL_OPTION);
        final char[] password = (okCxl == JOptionPane.OK_OPTION) ? pf.getPassword() : null;
      
        try (Connection connection = DriverManager.getConnection(CONN_STRING, username, String.valueOf(password))) {
            System.out.println("Success!! Connection made to the music database");
            Arrays.fill(password, ' ');
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

So we use the connection string as input, to get a connection.
As I mentioned before, there are two methods to do this.
The first is with the **DriverManager** class.
Like opening a file, you'll want to open a connection 
in a _try-with-resources_ block.
Inside the _try_ parentheses, 
I'll set up a _Connection_ variable, calling it simply _connection_, 
and calling a static method on the **DriverManager** class, 
named _getConnection_.
This method takes the _connection_ string,
and optionally the _username_ and _password_.
Here, I'll turn my character array into a string, 
within the call to the method.
If we get here, then we've successfully connected to the database, 
and I'll print that message.
Another best practice, once you've used the password for whatever you need it for, 
you should reset the characters, 
so the password is only in memory for the shortest time possible.
Here, I'll set the password to all spaces. 
I'll catch any _SQLException_ that's thrown. 
If there's a problem, I'll re-throw a _RuntimeException_.
I don't have to close the connection
because I've created it (and implicitly opened it) in this _try-with-resources_ code.
The connection will get automatically closed when this code completes.
Ok, so let's run this.

```html  
Success!! Connection made to the music database
```

I'll enter _devUser_ in the first prompt,
as you'll want to do, if you follow along with the _MySQL WorkBench_ section.
Next, I'll get prompted for the password.
You'll want to enter your own password, that you set up for this user, in this field.
Notice that I can't see the password because I used the **swing** _JPasswordField_.
My application will print _Success, Connection made to the music database_.
So now, we're successfully connecting to the _MySQL_ database 
from a Java application, using the **DriverManager** class to do it.
Before we do anything else, I'll show you how to connect using a _Datasource_.
Before the _try-with-resources_ statement, I'll add a couple of lines of code.

```java  
public class Main {

    private final static String CONN_STRING = "jdbc:mysql://localhost:3335/music";

    public static void main(String[] args) {
        
        String username = JOptionPane.showInputDialog(null, "Enter DB Username");
        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter DB Password", JOptionPane.OK_CANCEL_OPTION);
        final char[] password = (okCxl == JOptionPane.OK_OPTION) ? pf.getPassword() : null;

        var dataSource = new MysqlDataSource();
        dataSource.setURL(CONN_STRING);
        try (Connection connection = DriverManager.getConnection(CONN_STRING, username, String.valueOf(password))) {
            System.out.println("Success!! Connection made to the music database");
            Arrays.fill(password, ' ');
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

The first statement will create a variable using type inference, 
called _dataSource_, and I'll set that to a new **mySQLDataSource**,
which is _MySQL_'s implementation of this type.
This constructor doesn't have any arguments.
I set the connection string by called _setURL_ on that object.
In this case, I've created a basic **DataSource** implementation.
This will produce a standard connection object 
much like I'd get with the **DriverManager**, 
so it won't be pooled or used in a distributed transaction.
If you're working in a multi-tiered production environment, 
your client would get a **datasource** instance a different way.
You'd use something called a _JNDI_ (Java Naming and Directory Interface) naming service.
I'll be talking about this service when I cover client server applications
a bit later in the course.
When you get a _datasource_ through _JNDI_, 
you wouldn't need to specify the _URL_ 
or the _username_ and _password_.
Let's now get a connection using this _datasource_.

```java  
public class Main {
    
    private final static String CONN_STRING = "jdbc:mysql://localhost:3335/music";

    public static void main(String[] args) {
        
        String username = JOptionPane.showInputDialog(null, "Enter DB Username");
        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter DB Password", JOptionPane.OK_CANCEL_OPTION);
        final char[] password = (okCxl == JOptionPane.OK_OPTION) ? pf.getPassword() : null;
        
        var dataSource = new MysqlDataSource();
        dataSource.setURL(CONN_STRING);

        //try (Connection connection = DriverManager.getConnection(CONN_STRING, username, String.valueOf(password))) {
        try (Connection connection = dataSource.getConnection() {
            System.out.println("Success!! Connection made to the music database");
            Arrays.fill(password, ' ');
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

First, I'll comment out the first part of the _try-with-resources_.
After this, I'll add a try with resources,
but get the connection with the data source.
This starts out the same as before, but this time 
I'll call _getConnection_ on the _datasource_ variable.
This code compiles, but if we run it:

```html  
Exception in thread "main" java.lang.RuntimeException : java.sql.SQLException: Access denied for user 'korha'@'localhost' (using password: NO)
    at .Main.main()
Caused by: java.sql.SQLException : Access denied for user 'korha '@'localhost' (using password: NO)
    at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException()
    at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException()
    at com.mysql.cj.jdbc.ConnectionImpl.createNewIO()
    at com.mysql.cj.jdbc.ConnectionImpl.<init>()
    at com.mysql.cj.jdbc.ConnectionImpl.getInstance()
    at com.mysql.cj.jdbc.NonRegisteringDriver.connect()
    at com.mysql.cj.jdbc.MysqlDataSource.getConnection()
    at com.mysql.cj.jdbc.MysqlDataSource.getConnection()
    at com.mysql.cj.jdbc.MysqlDataSource.getConnection()
    at .Main.main()
```

I get an error because I didn't pass the _username_ 
or _password_ that was supplied by the user, 
to the _datasource_'s _getConnection_ method, so this fails.
Again, if I had gotten the _datasource_ instance from the naming context or JNDI, 
I could use this _getConnection_ method with no arguments.
But since I'm using a basic datasource,
I do have to pass the _username_ and _password_ 
I get from the **swing** inputs, so I'll do that now.

```java  
public class Main {
    
    private final static String CONN_STRING = "jdbc:mysql://localhost:3335/music";

    public static void main(String[] args) {
        
        String username = JOptionPane.showInputDialog(null, "Enter DB Username");
        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter DB Password", JOptionPane.OK_CANCEL_OPTION);
        final char[] password = (okCxl == JOptionPane.OK_OPTION) ? pf.getPassword() : null;
        
        var dataSource = new MysqlDataSource();
        dataSource.setURL(CONN_STRING);

        //try (Connection connection = dataSource.getConnection() {
        try (Connection connection = dataSource.getConnection(username, String.valueOf(password))) {
            System.out.println("Success!! Connection made to the music database");
            Arrays.fill(password, ' ');
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll pass the _username_, and the _password_ as a string.
And rerunning this code:

```html  
Success!! Connection made to the music database
```

I've now got my success message.
I could also have used the _setUser_ and _setPassword_ methods, 
as I did with _setURL_.
In fact, I can completely use _datasource_ 
without a connection string, by using the _set_ methods on this class.
Let's do this.

```java  
public class Main {
    
    private final static String CONN_STRING = "jdbc:mysql://localhost:3335/music";

    public static void main(String[] args) {
        
        String username = JOptionPane.showInputDialog(null, "Enter DB Username");
        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter DB Password", JOptionPane.OK_CANCEL_OPTION);
        final char[] password = (okCxl == JOptionPane.OK_OPTION) ? pf.getPassword() : null;
        
        var dataSource = new MysqlDataSource();
        
        //dataSource.setURL(CONN_STRING);
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setDatabaseName("music");
        
        try (Connection connection = dataSource.getConnection(username, String.valueOf(password))) {
            System.out.println("Success!! Connection made to the music database");
            Arrays.fill(password, ' ');
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll comment out the _setURL_ method.
Now, I'll start typing `dataSource.set`. 
Take a minute to scroll through the _set_ methods available in this class.
It wouldn't be an understatement to say 
there are a lot of options here.
We'll just focus on the basics.
I'll pick _setServerName_ from the list, and pass that _localhost_.
I'll next _setPort_ to `3335`.
And I'll set the database name to _music_.
I'll run this code, and enter the user and password:

```html  
Success!! Connection made to the music database
```

And I get the same result.
I'm not going to get into all the complexities of the _datasource_ type,
in this introduction to the subject.
In this case, there's not much difference between these two methods, 
since both connection types are basic connections.
But **Datasource** is newer, and generally preferred over **DriverManager**.
**Datasource** should definitely be used in a multi tiered environment, 
that requires connection pooling or distributed transactions.
Ok, so now that we can connect, 
it's time to do some of the fun work, 
which is getting data from the database.
</div>

## [e. Querying Data by MySQLDataSource Class]()
<div align="justify">

In the last section, we connected to the _music_ database, 
first using a **DriverManager**, then using a **DataSource**.
In this section, I'll start with a new **Main** class.
Rather than getting username and password,
with swing UI components, which gets old fast,
this time, I'll use a combination of a properties file, 
and an environment variable.
Again, it's important to research best practices 
for your own environment, and I'm not recommending
this approach, for a production application.
To start, I'll open the project panel,
and create a new file, at the package folder, 
called _music.properties_.
This file will just contain plain text key value pairs.
In the editor window, I'll enter the data 
I need to connect to a _datasource_,
without using a _connection_ string.

```html  
serverName=localhost
port=3335
databaseName=music
user=devUser
password=*******
```

This includes the server name or host,
and I'll make that _localhost_, 
the port, the database name, so _music_ there.
I'll enter _devUser_ as the _username_.
For now, I'm just going to put asterisks here 
in the password value.
You're welcome to enter your password there for your own testing, 
but I'll instead use an environment variable,
to add another level of security.
I could set this up on my operating system, 
but I can also just make it part of my build configuration.
So I'll go to the _Run_ menu Item,
then select _Edit Configurations_.
As you can see, the configurations pane is empty. 

```java  
public class Main { 
    public static void main(String[] args) {
    
    }
}
```

So, I'll exit this and run the program,
which creates a configuration, 
and then come back to this screen again.
You can see there's a text field 
where you can enter a list of additional _environment variables_,
so I'll enter `MYSQL_PASS=` there, and set that to my password.
Getting back to the code, in the _main_ method, 
I'll set up a _properties_ variable.
I'll do this at the start of this main method.

```java  
public class Main { 
    public static void main(String[] args) {
        
        Properties props = new Properties();
        try {
            props.load(Files.newInputStream(Path.of("music.properties"), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

Because I'm reading data from a file, I need a _try-catch_. 
The _properties_ object has a _load_ method, that takes an input stream, 
so I'll set that up to use the _music.properties_ file at the package. 
This file will be set to _READ_ only. 
I'll catch the _IOException_. 
And throw an unchecked exception if I do get an error.
I won't retrieve the password until I need it, at the time I connect.
This eliminates any concerns about it being part of any memory dump, 
or string pool.
Where I had values hard-coded in the _set_ methods 
on data source in the previous section, 
I'll use the values in the _props_ variable.

```java  
public class Main { 
    public static void main(String[] args) {
        
        Properties props = new Properties();
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course03_QueryingData/music.properties";
        try {
            props.load(Files.newInputStream(Path.of(pathName), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setDatabaseName(props.getProperty("databaseName"));

        try (var connection = dataSource.getConnection(props.getProperty("user"), System.getenv("MYSQL_PASS"))) {
            
            System.out.println("Success!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll create a variable for the _datasource_.
This is of type _MySQL Datasource_. 
Instead of passing hard-coded values, 
I'll pass the values that are in the _properties_ object. 
I can get these, using the _getProperty_ method, 
with the key name, so here I'll get server name. 
For the _port_, I'll parse the value to an **Integer**. 
And I'll pass the _databaseName_ in. 
This is actually optional, and I'll show you that in a bit.
Ok, I've got the _datasource_ initially set up, 
now I want to establish a connection with my user and password.
I'll use a try-with-resources block because it's important 
to close database resources, just like file resources.
In the parentheses, I'll use type inference,
and call _getConnection_ on my _datasource_.
I'll pass it the user property.
And I'll pass it the password.
This I'll get from the environment variable directly here,
by calling `system.getENV`, 
and pass the name I used, so _mySQL_PASS_. 
If you want to use the password from the properties file, use _getProperty_. 
I'll end the _try_ clause on its own line, 
because I'll be adding to the _try_ clause statements shortly. 
I'll print _Success_ if this all works.
Otherwise, I'll catch the checked **SQLException**, 
re-throwing it as a _RuntimeException_.
I'll run this:

```html  
Success!
```

And success.
I'm connecting to the music database,
without having to enter the username and password,
which was getting tedious quickly.
This was a quick review of connecting again,
but I did want to show you this alternative.
It's time to actually get some data now.
To do this, I can use a statement,
which is an interface type in Java.
It's implemented by the database vendor in the JDBC driver, 
and represents an SQL statement.
This can be either DML or DDL.
A statement, once its executed, 
will return the results of the query in a **ResultSet** object.
I'll start with a basic select statement, to get all the artists.

```java  
public class Main { 
    public static void main(String[] args) {
        
        Properties props = new Properties();
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course03_QueryingData/music.properties";
        try {
            props.load(Files.newInputStream(Path.of(pathName), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String query = "SELECT * FROM music.artists;
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setDatabaseName(props.getProperty("databaseName"));

        try (var connection = dataSource.getConnection(props.getProperty("user"), System.getenv("MYSQL_PASS"))) {
            
            System.out.println("Success!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll set this up as a variable before the _try-with-resources_ statement.
I'll make this a **String**, named _query_, and type in the SQL statement 
I want executed, as a **String** literal.
Here, I'll do `SELECT * FROM music.artists`.
This statement will select all columns and all rows or records 
from the artist's table in the music database or schema.
Now, there are a couple of things here I just want to point out.

First, the SQL commands and clauses in this statement are 
all specified in uppercase, which is common practice.
In contrast, the schema and table name are in lowercase.
Although MySQL is a case-insensitive language,
some databases are case-sensitive, 
so again it's best practice to use lowercase for data objects,
both in naming them and querying them.
Specifying the music schema here isn't necessary either, 
because we've specified it in the data resource.
Even so, you should still make a habit of including it 
in your SQL statement like this.
It helps with clarity, and may actually be better performant in some databases.
To actually run this, I first have to create a statement object.
I'll include this in my _try-with-resources_ clause.

```java  
public class Main { 
    public static void main(String[] args) {
        
        Properties props = new Properties();
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course03_QueryingData/music.properties";
        try {
            props.load(Files.newInputStream(Path.of(pathName), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        String query = "SELECT * FROM music.artists";
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setDatabaseName(props.getProperty("databaseName"));

        try (var connection = dataSource.getConnection(props.getProperty("user"), 
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            //System.out.println("Success!");
          
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.printf("%d %s %n", 
                        resultSet.getInt(1),
                        resultSet.getString("artist_name")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

Hopefully you'll remember that this clause 
can have multiple declarations, separated by a semicolon.
So I'll start by adding that semicolon to the end of the _connection_ statement.
Then I'll add the next declaration, after that.
I can get a statement instance 
by calling _createStatement_ on the _connection_ instance.
For a select SQL statement,
you'll use the _executeQuery_ method,
to actually execute this query against your database, 
which in turn returns a _ResultSet_.
First, I'll remove the success statement I had there.
Instead of that, I'll set up a _resultSet_ variable, 
then assign it `statement.executeQuery`, 
passing it my _query_ string.
All three objects, the connection, the statement, 
and the result set are all resources that need to be closed.
I could've included the _ResultSet_ in the _try_ clause, 
along with the _connection_ and _statement_, 
but it's actually not necessary here.
A **ResultSet** object is automatically closed, 
when the **Statement** object that generated it is closed.
It's also closed if the statement is re-executed,
or used to retrieve the next result,
from a sequence of multiple results.
The **ResultSet** in this case will contain
all the records in the **artists** table.
To use it, I loop through its contents, with a _while_ loop.
Like an iterator, I can check if there's a next value in the **ResultSet**.
I'll print each record, first the artist id, then the artist name.
There are different ways to retrieve the column or field data. 
I can do it by index, as I'm doing for the artist id,
so I'll call `resultSet.getInt`, and pass it 1. 
Unlike most software languages, SQL starts with 1, as the first index, 
and not 0, so 1 will get the first column's data,
which is an integer value, the artist id here.
Alternately, I can use the field name, so here,
I'll call _getString_, and pass _artist_name_ here.
I'll put the closing parentheses on its own line to support changes later on.
Ok, that's it.
Let's give this a try.
I'll run this now:

```html  
1 Mahogany Rush
2 Elf
3 Mehitabel
4 Big Brother & The Holding Company
5 Roy Harper
6 Pat Benatar
7 Rory Gallagher
8 Iron Maiden
9 Blaster Bates
10 Procol Harum
.
.
.
.
190 Patti Smith
191 Black Crowes
192 Metallica
193 U2
194 Garbage
195 Pink Fairies
196 Deep Purple
197 T.Rex
198 Tom Petty & The Heartbreakers
199 Thomas Tallis
200 Stevie Ray Vaughan
201 Chemical Brothers 
```

This will list all the artists in the table,
in the order of the artist id, as you can see.
Now this is a real success!
I don't know about you, 
but I always feel a sense of accomplishment, 
getting that first query to work, 
in a new database connection.
I'm communicating with my database, via java code, 
and that's pretty empowering really.
In fact, there are very popular database management tools, 
such as _Toad_, _DBeaver_, and _Oracle's SQL Developer_,
that have been written in Java.
These are similar to the _MySQL Workbench_,
which is actually written in _C++_.
You can use any of these tools to connect to a variety of databases.
Ok, now let's change this SQL statement 
to get a few records from the view, 
which was named _albumview_, in the **music** database.
This time I'll limit the data coming back
to just a single album _namedTapestry_,
which I can do with a _where_ clause.
You can think of a _where_ clause as a filter,
to get a subset of data, based on a particular condition, 
as defined in the clause.
Before the statement that sets the query variable,
I'll add another variable for the album name.

```java  
public class Main { 
    public static void main(String[] args) {
        
        Properties props = new Properties();
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course03_QueryingData/music.properties";
        try {
            props.load(Files.newInputStream(Path.of(pathName), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        String albumName = "Tapestry";
        String query = "SELECT * FROM music.albumview WHERE album_name='%s'".formatted(albumName);
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setDatabaseName(props.getProperty("databaseName"));

        try (var connection = dataSource.getConnection(props.getProperty("user"), 
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ///System.out.printf("%d %s %n", 
                System.out.printf("%d %s %s %n", 
                        resultSet.getInt("track_number"),
                        resultSet.getString("artist_name"),
                        resultSet.getString("song_title")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

The album name will be _Tapestry_, as I said.
I'll change the query below that,
and use a formatted string to include album name in the query.
So first, I'll change the table from _artists_ to _albumview_.
You can query a view just like you would a table.
I'll add a where clause, so _where album_name_ equals.
I'll set that to a `'%s'`.
In _ANSI SQL_, string literals should be enclosed in single quotes 
when they're used in _where_ clauses like this.
I'll be talking about using _ANSI SQL_ in a little more detail in a bit.
Double quotes are used to delimit identifiers,
such as _table_ and _column names_, which means these could contain spaces, 
or other characters that aren't allowed in text literals.
Some SQL implementations, including MySQL,
allow you to use double quotes around text literals,
but I'm going to stick with single quotes.
I'll use the _formatted_ method on the string 
to pass on the _album_name_ variable.
Because the data coming back will be different for this view 
from the **artists** table,
I need to change my code in the _while_ loop, 
where I'm printing data out.
I'll add an extra `%s`, as a third element in the string being printed.
I'll change the code, the _getInt_ argument from `1`, 
or the first column, and use the column name,
which is _track_number_ in this case.
After the _artist_name_ get string,
I'll add a comma, and a new line.
Finally, I'll include the _song_title_ as well.
If I run this:

```html  
1 Carole King I Feel The Earth Move 
2 Carole King Carole King - So Far Away 
3 Carole King It's Too Late 
4 Carole King Home Again 
5 Carole King Beautiful 
6 Carole King Way Over Yonder 
7 Carole King You've Got A Friend 
8 Carole King Where You Lead 
9 Carole King Will You Love Me Tomorrow 
10 Carole King Smackwater Jack 
11 Carole King Tapestry 
12 Carole King (You Make Me Feel Like) A Natural Woman 
13 Carole King Out In The Cold (Previously unreleased) 
14 Carole King Smackwater Jack (Live) 
```

I'll see that _Carole King_ was the artist for the entire album, 
and each of the songs is listed by the _track_number_ order.
The view itself is ordered by _track_number_,
which is why I didn't need an order by clause in my SQL statement.
If you don't know what the data might be, 
that's returned from your query,
you can use the **ResultSetMetaData** object,
to get information about the **ResultSet**.
Let me show you how to do this.

```java  
public class Main { 
    public static void main(String[] args) {
        
        Properties props = new Properties();
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course03_QueryingData/music.properties";
        try {
            props.load(Files.newInputStream(Path.of(pathName), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        String albumName = "Tapestry";
        String query = "SELECT * FROM music.albumview WHERE album_name='%s'".formatted(albumName);
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setDatabaseName(props.getProperty("databaseName"));

        try (var connection = dataSource.getConnection(props.getProperty("user"), 
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            
            ResultSet resultSet = statement.executeQuery(query);

            var meta = resultSet.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%d %s %s%n", 
                        i, 
                        meta.getColumnName(i),
                        meta.getColumnTypeName(i)
                );
            }
            System.out.println("===================");
            
            while (resultSet.next()) {
                System.out.printf("%d %s %s %n", 
                        resultSet.getInt("track_number"),
                        resultSet.getString("artist_name"),
                        resultSet.getString("song_title")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll add this code before I execute the _resultSet_.
There's a method on the _resultSet_, called _getMetaData_. 
On that object, I can get the number of columns that were returned,
so I'll loop from 1 to this count. 
For each column, I'll print the column index, remember this starts at 1. 
I'll print the column name and its type. 
I'll pass the appropriate data to that _printf_ statement. 
There are multiple methods on the _resultSet_ metadata, 
and two of these are _getColumnName_, and _getColumnTypeName_, 
each which takes a column index.
I'll include a separator line after this code runs.
Running this:

```html  
1 album_name TEXT
2 artist_name TEXT
3 track_number INT
4 song_title TEXT
===================
1 Carole King I Feel The Earth Move
2 Carole King Carole King - So Far Away
3 Carole King It's Too Late
4 Carole King Home Again
5 Carole King Beautiful
6 Carole King Way Over Yonder
7 Carole King You've Got A Friend
8 Carole King Where You Lead
9 Carole King Will You Love Me Tomorrow
10 Carole King Smackwater Jack
11 Carole King Tapestry
12 Carole King (You Make Me Feel Like) A Natural Woman
13 Carole King Out In The Cold (Previously unreleased)
14 Carole King Smackwater Jack (Live) 
```

You can see information about the columns, printed out first.
I can use this information to help facilitate handling data in a generic fashion.
I'll show you an example of this next.

```java  
public class Main { 
    public static void main(String[] args) {
        
        Properties props = new Properties();
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course03_QueryingData/music.properties";
        try {
            props.load(Files.newInputStream(Path.of(pathName), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        String albumName = "Tapestry";
        String query = "SELECT * FROM music.albumview WHERE album_name='%s'".formatted(albumName);
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setDatabaseName(props.getProperty("databaseName"));

        try (var connection = dataSource.getConnection(props.getProperty("user"), 
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            
            ResultSet resultSet = statement.executeQuery(query);

            var meta = resultSet.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%d %s %s%n", 
                        i, 
                        meta.getColumnName(i),
                        meta.getColumnTypeName(i)
                );
            }
            System.out.println("===================");

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
            }
            System.out.println();
            
            while (resultSet.next()) {
                /*System.out.printf("%d %s %s %n", 
                        resultSet.getInt("track_number"),
                        resultSet.getString("artist_name"),
                        resultSet.getString("song_title")
                );*/
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    System.out.printf("%-15s", resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll remove the code I have in the while loop.
Before this loop, I'll add a column of headers, 
using the _resultSet_ metadata.
Again, I'll loop from 1 to the column count, incrementing by one.
This time, I'll print each column name, 
making it left justified, and allowing for 15 characters.
I'll also make the column names upper case.
After the column headers are printed, I'll print a new line here.
Now, in the while loop, I'll do something similar.
Again, I'll loop through the metadata index.
Here, I'll print the _resultSet_'s value,
using _getString_, with the metadata index.
After each record, I want a new line.
Running this:

```html  
1 album_name TEXT
2 artist_name TEXT
3 track_number INT
4 song_title TEXT
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Tapestry       Carole King    1              I Feel The Earth Move
Tapestry       Carole King    2              Carole King - So Far Away
Tapestry       Carole King    3              It's Too Late  
Tapestry       Carole King    4              Home Again     
Tapestry       Carole King    5              Beautiful      
Tapestry       Carole King    6              Way Over Yonder
Tapestry       Carole King    7              You've Got A Friend
Tapestry       Carole King    8              Where You Lead 
Tapestry       Carole King    9              Will You Love Me Tomorrow
Tapestry       Carole King    10             Smackwater Jack
Tapestry       Carole King    11             Tapestry       
Tapestry       Carole King    12             (You Make Me Feel Like) A Natural Woman
Tapestry       Carole King    13             Out In The Cold (Previously unreleased)
Tapestry       Carole King    14             Smackwater Jack (Live)
```

I get all the data in a grid, 
much like I'd see if I ran that query in the _MySQL WorkBench_.
I can use this method for any type of select query, from any table or view.
Fifteen characters might not be enough
or the right way to format in every case,
but you get the idea here.
The result set metadata object, has other methods, 
such as column type and width,
which you can use to make this more flexible.
Ok, so that's a quick introduction to the
**Statement** type, using the _executeQuery_ method.
This method returns the results of the query in a **ResultSet** object.
And you can use the _ResultSet_ metadata object,
to query information about the _ResultSet_.

Before I do anything more with this _query_, 
I'll first remove the code that prints out the column names
and column types from the **ResultSet MetaData**.

```java  
public class Main { 
    public static void main(String[] args) {
        
        Properties props = new Properties();
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course03_QueryingData/music.properties";
        try {
            props.load(Files.newInputStream(Path.of(pathName), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setDatabaseName(props.getProperty("databaseName"));

        String albumName = "Tapestry";
        String query = "SELECT * FROM music.albumview WHERE album_name='%s'".formatted(albumName);
        
        try (var connection = dataSource.getConnection(props.getProperty("user"), 
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            
            ResultSet resultSet = statement.executeQuery(query);

            var meta = resultSet.getMetaData();
/*            
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%d %s %s%n", 
                        i, 
                        meta.getColumnName(i),
                        meta.getColumnTypeName(i)
                );
            }
*/
            System.out.println("===================");

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
            }
            System.out.println();
            
            while (resultSet.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    System.out.printf("%-15s", resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

This will make the output easier to look at, moving forward.
I'll run this code:

```html  
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Tapestry       Carole King    1              I Feel The Earth Move
Tapestry       Carole King    2              Carole King - So Far Away
Tapestry       Carole King    3              It's Too Late  
Tapestry       Carole King    4              Home Again     
Tapestry       Carole King    5              Beautiful      
Tapestry       Carole King    6              Way Over Yonder
Tapestry       Carole King    7              You've Got A Friend
Tapestry       Carole King    8              Where You Lead 
Tapestry       Carole King    9              Will You Love Me Tomorrow
Tapestry       Carole King    10             Smackwater Jack
Tapestry       Carole King    11             Tapestry       
Tapestry       Carole King    12             (You Make Me Feel Like) A Natural Woman
Tapestry       Carole King    13             Out In The Cold (Previously unreleased)
Tapestry       Carole King    14             Smackwater Jack (Live)
```

And again you'll see I get album name, 
the artist name, the track number and the song title, 
for the _Tapestry_ album, provided by the album view.
Instead of hardcoding the album name,
I'll prompt the user to enter the album name, 
using the console this time, to get data from the user.

```java  
public class Main { 
    public static void main(String[] args) {
        
        Properties props = new Properties();
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course03_QueryingData/music.properties";
        try {
            props.load(Files.newInputStream(Path.of(pathName), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setDatabaseName(props.getProperty("databaseName"));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an Album Name: ");
        //String albumName = "Tapestry";
        String albumName = scanner.nextLine();
        String query = "SELECT * FROM music.albumview WHERE album_name='%s'".formatted(albumName);
        
        try (var connection = dataSource.getConnection(props.getProperty("user"), 
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            
            ResultSet resultSet = statement.executeQuery(query);

            var meta = resultSet.getMetaData();
            System.out.println("===================");

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
            }
            System.out.println();
            
            while (resultSet.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    System.out.printf("%-15s", resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll set up a **scanner** instance, using `System.in`.
I'll prompt the user, to enter an album name.
Next, I'll get the information from the scanner,
with the next line statement,
and assign that to the albumName variable.
I'll run this:

```html  
Enter an Album Name:
```

and I'll enter _Tapestry_ at the prompt.

```html  
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Tapestry       Carole King    1              I Feel The Earth Move
Tapestry       Carole King    2              Carole King - So Far Away
Tapestry       Carole King    3              It's Too Late  
Tapestry       Carole King    4              Home Again     
Tapestry       Carole King    5              Beautiful      
Tapestry       Carole King    6              Way Over Yonder
Tapestry       Carole King    7              You've Got A Friend
Tapestry       Carole King    8              Where You Lead 
Tapestry       Carole King    9              Will You Love Me Tomorrow
Tapestry       Carole King    10             Smackwater Jack
Tapestry       Carole King    11             Tapestry       
Tapestry       Carole King    12             (You Make Me Feel Like) A Natural Woman
Tapestry       Carole King    13             Out In The Cold (Previously unreleased)
Tapestry       Carole King    14             Smackwater Jack (Live)
```

I get the same data I got when I had _Tapestry_ hard-coded there, 
so that's good.
I'll run this again, but now I'll enter a different album name.
This time I entered _Bad Company_.

```html  
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE
Bad Company    Bad Company    1              Can't Get Enough
Bad Company    Bad Company    2              Rock Steady
Bad Company    Bad Company    3              Ready For Love
Bad Company    Bad Company    4              Don't Let Me Down
Bad Company    Bad Company    5              Bad Company
Bad Company    Bad Company    6              The Way I Choose
Bad Company    Bad Company    7              Movin' On
Bad Company    Bad Company    8              Seagull    
```

And I get the songs on that album printed out there.
With this code, I'm now providing a user with a way 
to directly interact with the database.
Now, this code isn't very error proof or user-friendly.
The album name entered by the user has to match the case exactly, 
for example, and there's no validation on the user input.
Next, I'll change my select statement to get data from the artist table 
by the artist id, in a similar way.

```java  
public class Main { 
    public static void main(String[] args) {
        
        Properties props = new Properties();
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course03_QueryingData/music.properties";
        try {
            props.load(Files.newInputStream(Path.of(pathName), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setDatabaseName(props.getProperty("databaseName"));

        Scanner scanner = new Scanner(System.in);
        /*System.out.println("Enter an Album Name: ");
        String albumName = scanner.nextLine();
        String query = "SELECT * FROM music.albumview WHERE album_name='%s'".formatted(albumName);*/
        
        System.out.println("Enter an Artist Id: ");
        String artistId = scanner.nextLine();
        String query = "SELECT * FROM music.artists WHERE artist_id=%s".formatted(artistId);
        
        try (var connection = dataSource.getConnection(props.getProperty("user"), 
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            
            ResultSet resultSet = statement.executeQuery(query);

            var meta = resultSet.getMetaData();
            System.out.println("===================");

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
            }
            System.out.println();
            
            while (resultSet.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    System.out.printf("%-15s", resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

So First, I'll change the prompt to enter an artist id.
I'll change the variable name from _albumName_, to _artistId_.
On the next line, I'll change the select statement, 
first to get data from the **artists** table, instead of **albumview**.
And I'll change the _where_ clause to `artist_id=%s`.
Notice here that I'm not enclosing this in quotes.
Finally, I'll pass _artistId_ to the formatted method.
I'll run this:

```html  
Enter an Artist Id: 
```

I'll enter 7:

```html  
===================
ARTIST_ID      ARTIST_NAME    
7              Rory Gallagher 
```

And I get one artist back, Rory Gallagher.
So that works, which seems good.
I'll run this again.
This time I'll enter an id, as expected, so 7 again, 
but here, I'm going to include some additional input, 
the text, `or artist id=8`.

```html  
===================
ARTIST_ID      ARTIST_NAME
7              Rory Gallagher
8              Iron Maiden
```

If I press enter on that, this actually works,
and I get the data for artist id 7 and 8.
Imagine if this had been code, 
getting personally identifiable information, based on some id.
This would mean a user could now get back,
not only the information for its known specific id, 
but a second one as well.
This is a somewhat staged and 
imperfect example of something called **SQL injection**,
but hopefully gives you and idea of how this problem could occur.
_SQL Injection_ is when a user maliciously alters the input, 
attempting to change the intent of the _SQL statement_.
This is a means to get more information back
or perform unintended operations.

**SQL injection** is a serious security vulnerability.
It occurs when an attacker attempts to manipulate the input data,
sent to an application's database query.
When you patch together an _SQL statement_, 
using user input as we just did, 
and you use Java's statement to execute it, 
you could be putting your application at risk, to this type of problem.
_SQL injection vulnerabilities_ usually exist at points 
where user input is used to construct _SQL queries_.
Common injection points include:

* User login forms, where input is checked against stored credentials. 
* Search forms, where user input is used to filter database records.
* URL parameters, used in dynamic SQL queries. 
* Any input field in a web application that interacts with a database.

There are methods to minimize and prevent this type of attack.
You'll want to: 

* Validate and sanitize user input before using it. 
We could have parsed our input to an integer, for example. 
* Practice the _Least Privilege Principle_ on the database side.
This means ensuring user accounts have the least privilege necessary 
to perform their tasks.
In this case, our dev user has very broad privileges, 
and the opportunity to do more damage because of it. 
* Implement proper error handling and logging to avoid exposing any database 
or table name specifics in messages the user will see. 
* Use prepared statements or parameterized queries, whenever possible.

I'll be covering prepared statements in a later section in this course.
Getting back to my code, I'll implement some simple validation here,
by parsing the data I get from the user.

```java  
public class Main { 
    public static void main(String[] args) {
        
        Properties props = new Properties();
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course03_QueryingData/music.properties";
        try {
            props.load(Files.newInputStream(Path.of(pathName), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setDatabaseName(props.getProperty("databaseName"));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an Artist Id: ");
        String artistId = scanner.nextLine();
        
        int artistid = Integer.parseInt(artistId);
        //String query = "SELECT * FROM music.artists WHERE artist_id=%s".formatted(artistId);
        String query = "SELECT * FROM music.artists WHERE artist_id=%d".formatted(artistid);
        
        try (var connection = dataSource.getConnection(props.getProperty("user"), 
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            
            ResultSet resultSet = statement.executeQuery(query);

            var meta = resultSet.getMetaData();
            System.out.println("===================");

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
            }
            System.out.println();
            
            while (resultSet.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    System.out.printf("%-15s", resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll set up an integer variable, artist ID, with id in lower case.
I'll pass the input to `Integer.parseInt`.
I'll change my format string, to only accept a number there, 
so `%d` instead of `%s`.
And I'll pass my integer variable to the _formatted_ method.
I'll run this.
Again I'll enter `7 or artist_id=8`.

```html  
Exception in thread "main" java.lang.NumberFormatException: For input string: "7 or artist_id=8"
	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
	at java.base/java.lang.Integer.parseInt(Integer.java:662)
	at java.base/java.lang.Integer.parseInt(Integer.java:778)
	at .Main.main()
```


Now, this input gives me a number format exception, so that's an improvement.
I'll run it again, and make sure I can enter a valid number.
So I'll enter `77` there:

```html  
===================
ARTIST_ID      ARTIST_NAME    
77             Jefferson Starship
```

And now I can see that that gives me _Jefferson Starship_ as the artist.
Ok, so that was a brief introduction to a problem 
you're sure to hear about, because it's crucial to avoid it.
Now, I want to move on, so I'll remove the scanner code.

```java  
public class Main { 
    public static void main(String[] args) {
        
        Properties props = new Properties();
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course03_QueryingData/music.properties";
        try {
            props.load(Files.newInputStream(Path.of(pathName), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setDatabaseName(props.getProperty("databaseName"));

        //Scanner scanner = new Scanner(System.in);
        //System.out.println("Enter an Artist Id: ");
        //String artistId = scanner.nextLine();
        //int artistid = Integer.parseInt(artistId);
        
        //String query = "SELECT * FROM music.artists WHERE artist_id=%d".formatted(artistid);
        String query = "SELECT * FROM music.artists limit 10";
        
        try (var connection = dataSource.getConnection(props.getProperty("user"), 
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            
            ResultSet resultSet = statement.executeQuery(query);

            var meta = resultSet.getMetaData();
            System.out.println("===================");

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
            }
            System.out.println();
            
            while (resultSet.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    System.out.printf("%-15s", resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll change this query to just return a subset
of the artists, the first `10` for example.
In My SQL, I can do this with
the limit clause, passing it 10.
I'll run this.

```html  
===================
ARTIST_ID      ARTIST_NAME    
1              Mahogany Rush  
2              Elf            
3              Mehitabel      
4              Big Brother & The Holding Company
5              Roy Harper     
6              Pat Benatar    
7              Rory Gallagher 
8              Iron Maiden    
9              Blaster Bates  
10             Procol Harum   
```

Now you can see the results only have the first 10 records.
That's what the _limit_ clause does.
Unfortunately, using the _limit_ clause in this statement, 
now means this jdbc code isn't portable to other databases.
This is a clause that only a couple of vendors support, 
and it's not _ANSI SQL_.

_ANSI SQL_ stands for 
the **American National Standards Institute Structured Query Language**.
Each database vendor provides documentation
that covers its own SQL language statements.
This documentation often includes information 
about their compliance with the _ANSI SQL_ standards.
_ISO SQL_ is the **International Organization for Standardization**'s version.
Both are based on the same core _SQL_ syntax and semantics, 
and both are maintained by the same technical committee.
For simplicity, I'll continue to call it just _ANSI SQL_.

There are some good reasons to strive 
to use _ANSI SQL_ with your JDBC code:

* **Portability and Database Independence**. 
Because _ANSI SQL_ is supported by all major database vendors to some degree, 
you can write JDBC statements using it.
This should give you confidence that the code will work
on many databases. 
* Using ANSI SQL helps with **Readability and Collaboration**. 
It's well-defined and easy-to-read. 
This makes it easier to write uniform code, as well as understand JDBC statements. 
This is especially true if you're working with a team of developers.
* **Maintainability and Future-Proofing** of your code. 
_ANSI SQL_ is a stable language, which means it's unlikely 
to change in the future.
This makes it easier to maintain your code over time.
* Finally, it helps with **Compliance Requirements**. 
In some industries, compliance with standards and regulations is mandatory.
Using _ANSI SQL_ will help address these requirements.

While using _ANSI SQL_ is a good practice,
different databases implement _ANSI SQL_ standards to varying degrees.
Some advanced features and optimizations will remain vendor-specific, 
and some vendors may only implement the standards minimally.
I recommend trying to start with _ANSI SQL_,
which can help you maintain flexibility,
and minimize what's called vendor lock-in.
Vendor lock-in occurs when you develop code,
that relies heavily on vendor-specific features, syntax, or functionalities.
This makes it challenging to migrate to a different DBMS vendor.
This is good for the vendor, but may limit options for your organization.

| Database Vendor      | TOP | LIMIT | FETCH FIRST |
|----------------------|-----|-------|-------------|
| Microsoft SQL Server | Yes | No    | No          |
| MySQL                | No  | Yes   | No          |
| PostgreSQL           | No  | Yes   | Yes         |
| Oracle               | No  | No    | Yes         |
| IBM Db2              | No  | No    | Yes         |
| Apache Derby         | No  | No    | Yes         |
| H2 Database          | No  | No    | Yes         |

The **limit** clause is a good example of 
how each database might implement this functionality.
The _ANSI SQL_ standard specifies a **FETCH FIRST** clause.
But _MySQL_ doesn't support that, and instead has the **LIMIT** clause.
The table demonstrates which vendors support **FETCH FIRST**, and which don't.
Notice that _SQL Server_ uses a **TOP** clause, instead of **LIMIT**, 
and that _PostgreSQL_ supports both **FETCH FIRST** as well as **LIMIT**.
So if MySQL doesn't support **FETCH FIRST**, 
what options do we have to write database agnostic code in this example?
Well, let me show you some code that should work in any database.
I'll comment out the query statement there.

```java  
public class Main { 
    public static void main(String[] args) {
        
        Properties props = new Properties();
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course03_QueryingData/music.properties";
        try {
            props.load(Files.newInputStream(Path.of(pathName), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setDatabaseName(props.getProperty("databaseName"));

        //String query = "SELECT * FROM music.artists limit 10";
        String query = """
              WITH RankedRows AS (
                                  SELECT *,
                                  ROW_NUMBER() OVER (ORDER BY artist_id) AS row_num
                                  FROM music.artists
                              )
                              SELECT *
                                  FROM RankedRows
                              WHERE row_num <= 10""";
        
        try (var connection = dataSource.getConnection(props.getProperty("user"), 
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            
            ResultSet resultSet = statement.executeQuery(query);

            var meta = resultSet.getMetaData();
            System.out.println("===================");

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
            }
            System.out.println();
            
            while (resultSet.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    System.out.printf("%-15s", resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

And I'll start a new one, with a text block.
I'm going to paste this SQL statement in here rather than type it out.
Ok, this is SQL, that may not make sense to you, 
if you're pretty new to the subject.
I'm not here to teach you SQL, but I will say this code is _ANSI SQL_,
and does the same thing as **limit**.
It's just a lot uglier and harder to understand.
The **with** clause specifies a subquery or **Common Table Expression**, 
called a **CTE**.
Inside there, a sequential number called _row_num_ gets assigned 
to every record in the **artists** table, 
which is first sorted by _artist_id_.
Using this subquery or CTE, the code then gets 
the records assigned a _row_num_, less than or equal to 10.
I'll run this:

```html  
===================
ARTIST_ID      ARTIST_NAME    ROW_NUM        
1              Mahogany Rush  1              
2              Elf            2              
3              Mehitabel      3              
4              Big Brother & The Holding Company4              
5              Roy Harper     5              
6              Pat Benatar    6              
7              Rory Gallagher 7              
8              Iron Maiden    8              
9              Blaster Bates  9              
10             Procol Harum   10
```

I get the same results as before.
It has the benefit of being database agnostic.
But it has the cost of a complex statement,
which may not be as efficient as using the **Limit** clause.
This code is probably going to be challenging for some team members 
to understand and maintain.
So another option for this specific problem is 
to use a feature on the datasource,
which limits the records returned.
I'll comment out this text block,
and uncomment the original SQL statement.

```java  
public class Main { 
    public static void main(String[] args) {
        
        Properties props = new Properties();
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course03_QueryingData/music.properties";
        try {
            props.load(Files.newInputStream(Path.of(pathName), StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setDatabaseName(props.getProperty("databaseName"));

        try {
            dataSource.setMaxRows(10);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String query = "SELECT * FROM music.artists limit 10";
/*       
        String query = """
              WITH RankedRows AS (
                                  SELECT *,
                                  ROW_NUMBER() OVER (ORDER BY artist_id) AS row_num
                                  FROM music.artists
                              )
                              SELECT *
                                  FROM RankedRows
                              WHERE row_num <= 10""";
*/        
        
        try (var connection = dataSource.getConnection(props.getProperty("user"), 
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            
            ResultSet resultSet = statement.executeQuery(query);

            var meta = resultSet.getMetaData();
            System.out.println("===================");

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
            }
            System.out.println();
            
            while (resultSet.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    System.out.printf("%-15s", resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

Above that query declaration, I'll call setMaxRows on the datasource, 
passing it 10, meaning I only ever want 10 records back.
This statement needs to be wrapped in a _try-catch_,
so I'll have IntelliJ generate that for me.
I'll run this:

```html  
===================
ARTIST_ID      ARTIST_NAME    
1              Mahogany Rush  
2              Elf            
3              Mehitabel      
4              Big Brother & The Holding Company
5              Roy Harper     
6              Pat Benatar    
7              Rory Gallagher 
8              Iron Maiden    
9              Blaster Bates  
10             Procol Harum  
```

This gives me the same result as the **limit** command.
In MySQL, the server's execution plan is equivalent to using **LIMIT**, 
meaning the server is optimized similarly as if it were using **limit**.
This example is just one small demonstration of the complexity of trying 
to write database agnostic code.
Fortunately, the database vendors continue to improve and standardize.
So it's possible that MySQL will eventually include the standard
**FETCH FIRST** clause at some point.

Trying to learn what the standards are,
and how well each vendor supports each standard is a challenging exercise.
Oracle provides a good guide, not for the MySQL database, 
but for their Oracle database 
that at least lists quite a few of the standards in one document.
It then describes how well that database conforms to the standards.
</div>

## [f. Querying Data by DriverManager Class]()
<div align="justify">

In all the examples so far, I've used the _executeQuery_ method,
which returns a **ResultSet** instance.
In this section, I'll switch to using the _execute_ method, 
first to execute the same select query we used in the previous section,
and then to execute additional CRUD statements.
I'll create a new class with the _main_ method,
and I'll call this **MusicDML**.
As promised, I'll first demonstrate how you'd get a connection, 
if you're using a JDBC driver, that's earlier than JDBC 4.0.
Prior to that version, a driver had to be loaded explicitly.
To load a class explicitly or manually,
you have to call `class.forName`, 
and pass the fully qualified class name as a string.

```java  
public class MusicDML {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
```

This method will actually return a class instance,
and can be used for other purposes, like reflection.
You'll remember, we can call _getClass_ on any object, 
to get a class instance.
In this case, we're getting a class instance based on 
a fully qualified class name in a string literal, 
for a class we haven't included in any of our import statements.
This won't compile without a _try-catch_,
so I'll use IntelliJ to generate that code.
`TheClass.forName` method was necessary in older JDBC versions, 
and allowed the database driver to be registered dynamically.
This gave programmers the flexibility to swap in different drivers 
at runtime, or in different environments.
This mechanism has since been replaced with something called 
the _Service Provider Interface_.
I'll talk about _Service Providers_ and this interface later in the course.
Even though it's no longer necessary to use this _Class.forName_ call,
let's see what happens if we do.
Legacy code or older applications may still use this approach, 
and you should at least recognize it.

```java  
public class MusicDML {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3335/music",
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            
            String artist = "Elf";
            String query = "SELECT * FROM artists WHERE artist_name='%s'"
                    .formatted(artist);
            
            boolean result = statement.execute(query);
            System.out.println("result = " + result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

Once the driver was registered, the process was the same to get a connection.
I'll set up a _try-with-resources_, and in that, 
I'll set up a new connection, using **DriverManager** this time. 
I'll pass my connection string for the MySQL database.
I'll pass the username, stored in an environment variable this time. 
I'll do the same for the password. 
I'll be setting these up in the run configuration in a minute.
I'll also set up a Statement in my _try-with-resources_.
And as always, I'll deal with the _SQLException_.
Within the _try_ clause, I'll be querying the **artist** table, 
so I'll start with a variable for an artists' name.
I'll set that to _Elf_.
My query variable will be set to a _Select_ statement,
so `SELECT * FROM artists WHERE artist_name='%s'`.
Notice here that I'm using **artists** without specifying the **music** schema.
I had mentioned previously that we don't have to use the schema name
if we have the database name in the connection, 
so I'll show you this, in this example.
This string will be _formatted_ using the _artist_ variable. 
I'll now call the _execute_ method on the **statement**, instead of _executeQuery_. 
This method returns a **boolean**. 
So I'll print that _result_.
The _execute_ method can be used for different kinds of statements, 
like _insert_, _update_ and _delete_, in addition to the _select_ statement,
and I'll cover all of these shortly.
Before I run this, I'll again set up some environment variables,
on the run configuration, for this class
Because I've only created _MusicDML_ in this section, 
and not yet run it, there is no configuration set. 
So I'll create a new one by coming over here to the plus button
to add a new one and selecting _Application_.
I'll call this configuration _MusicDML_.
I need to select the right class name, 
which is the code we have been working on in this section.
This time I'll set up both the username and password here. 
I'll click _OK_ to save the configuration.
Now, I'll run this code:

```html  
Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.
result = true
```

Notice first that I get a message, that loading the class, 
the way I was in this code, has been deprecated.
It then tells me what the new driver class is,
and that it's automatically registered, etc.
Lastly, I get that the _result_ is **true**.
Ok, so now I'll remove the code that uses `Class.forName`, 
and its surrounding _try_ block as well.

```java  
public class MusicDML {

    public static void main(String[] args) {
/*
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
*/
        
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3335/music",
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {
            
            //String artist = "Elf";
            String artist = "Neil Young";
            String query = "SELECT * FROM artists WHERE artist_name='%s'"
                    .formatted(artist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

Next, I'm going to change my artist name, from _Elf_, to _Neil Young_.
I happen to know that Neil Young's not in the **artists** table.
I'll run the code again:

```html  
result = true
```

This time, I don't get any message about deprecated code.
I'm also still getting a _result_ that's **true**,
even though I know for a certainty, 
that _Neil Young_ isn't in the **artists** database.
This method, _execute_, always returns **true** 
when it's used with a _select_ statement.
This means this boolean result can't be used to test 
for the existence of a record in a table.
Instead, I'll test the data in the _resultSet_.

```java  
public class MusicDML {

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3335/music",
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {

            String artist = "Neil Young";
            String query = "SELECT * FROM artists WHERE artist_name='%s'"
                    .formatted(artist);

            boolean result = statement.execute(query);
            System.out.println("result = " + result);

            var rs = statement.getResultSet();
            boolean found = (rs != null && rs.next());
            System.out.println("Artist was " + (found ? "found" : "not found"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll use the _getResultSet_ method on statement to get that data. 
I'll set up a **boolean** variable,
which I'll call found, which will be true if the _resultSet_'s not **null**, 
and the next method returns **true**. 
I'll use this variable to print whether the artist was found or not found.
Running this code again:

```html  
result = true
Artist was not found
```

I still get _result_ is **true**, but now I get _artist was not found_.
In general, you'll use _executeQuery_ for _select_ statements, 
but there may be some isolated use cases 
where using _execute_ makes more sense.
Before I continue, I'm going to set up a few public static methods on this class.

```java  
public class MusicDML {

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3335/music",
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {

            String artist = "Neil Young";
            String query = "SELECT * FROM artists WHERE artist_name='%s'"
                    .formatted(artist);

            boolean result = statement.execute(query);
            System.out.println("result = " + result);

            var rs = statement.getResultSet();
            boolean found = (rs != null && rs.next());
            System.out.println("Artist was " + (found ? "found" : "not found"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean printRecords(ResultSet resultSet) throws SQLException {
        
        boolean foundData = false;
        var meta = resultSet.getMetaData();

        System.out.println("===================");

        for (int i = 1; i <= meta.getColumnCount(); i++) {
            System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
        }
        
        System.out.println();

        while (resultSet.next()) {
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%-15s", resultSet.getString(i));
            }
            System.out.println();
            foundData = true;
        }
        return foundData;
    }
}
```

The first will _printRecords_, given a _resultSet_.
I'll make it private and static, and it'll return a **boolean**,
This will take a _resultSet_ as a parameter, 
and throw an _SQLException_, and I'll call it _printRecords_.
It will return **true** if records were found.
I'll initialize a **boolean** variable, found data, to **false**. 
I'll return this value from this method.
I'll open up the **Main** class, from the previous section, 
and what I want to do is, copy the code 
that prints the _resultSet_ in tabular form.
I'll copy the code, starting at the line,
where I declare the meta variable, 
and copy all the statements through to the end of the _while_ loop code.
I'll paste that in my new private method on the **MusicDML** class.
Finally, I just want to set the variable, _foundData_, 
to **true** in the _while_ loop.
I'll put this at the end of the _while_ loop.
So this method will print records found returning **true**, 
or else just return **false** if no records were found.
The next method I'll create is an _executeSelect_ method,
also private and static, and returning a **boolean**.

```java  
public class MusicDML {

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3335/music",
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS"));
             Statement statement = connection.createStatement();
        ) {

            String artist = "Neil Young";
            String query = "SELECT * FROM artists WHERE artist_name='%s'"
                    .formatted(artist);

            boolean result = statement.execute(query);
            System.out.println("result = " + result);

            var rs = statement.getResultSet();
            boolean found = (rs != null && rs.next());
            System.out.println("Artist was " + (found ? "found" : "not found"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean printRecords(ResultSet resultSet) throws SQLException {

        boolean foundData = false;
        var meta = resultSet.getMetaData();

        System.out.println("===================");

        for (int i = 1; i <= meta.getColumnCount(); i++) {
            System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
        }

        System.out.println();

        while (resultSet.next()) {
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%-15s", resultSet.getString(i));
            }
            System.out.println();
            foundData = true;
        }
        return foundData;
    }

    private static boolean executeSelect(Statement statement, String table, String columnName, String columnValue)
          throws SQLException {
        
        String query = "SELECT * FROM %s WHERE %s='%s'".formatted(table, columnName, columnValue);
        var rs = statement.executeQuery(query);
        if (rs != null) {
            return printRecords(rs);
        }
        return false;
    }
}
```

I'll make this generic, so that it can be used to select data from any record,
using a single column name and value.
This method has four parameters, 
the _statement_ object, a _tableName_, a _columnName_, and a _columnValue_.
It'll throw an _SQLException_, so I don't have to handle it in the method.
I'll create the _SELECT_ statement, using the data passed. 
In this case, I'll go back to calling `statement.executeQuery`, 
and getting a _resultSet_ back from that. 
If I get a _resultSet_ back, I'll execute the _printRecords_ method,
and return it's **boolean** value.
Otherwise, I'll return **false** from this method.
What's significant about this method is it's not creating the connection 
to the database or an additional statement.
Instead, an existing statement is passed to it.
Creating connections and statements can be expensive operations.
It's good practice to close connections,
as soon as you're done using them, it's true.
But on the other hand, it's also a good idea to keep them open 
if you know you're going to be executing a series of related statements.
So this method will be used with an existing open statement.
Getting back to the _main_ method on this class, 
I'm going to remove the code I've got that queries the **artists** table.

```java  
public static void main(String[] args) {
    
    try (Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3335/music", 
            System.getenv("MYSQL_USER"), 
            System.getenv("MYSQL_PASS")); 
         Statement statement = connection.createStatement();
         ) {
/*
        String artist = "Neil Young";
        String query = "SELECT * FROM artists WHERE artist_name='%s'"
                .formatted(artist);

        boolean result = statement.execute(query);
        System.out.println("result = " + result);

        var rs = statement.getResultSet();
        boolean found = (rs != null && rs.next());
        System.out.println("Artist was " + (found ? "found" : "not found"));
*/

        String tableName = "music.artists";
        String columnName = "artist_name";
        String columnValue = "Elf";
        if (!executeSelect(statement, tableName, columnName, columnValue)) {
            System.out.println("Maybe we should add this record");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

So I'll remove everything in the _try_ block.
Next, I'll set up a couple of variables,
table name, column name, and column value.
I'll set up the _tableName_. 
This time I'll go back to including the schema name 
as part of the table name. 
My _columnName_ is _artist_name_. 
The _columnValue_ is the value of the artist name,
so I'll use _Elf_ again here. 
I'll call _executeSelect_, passing the statement, table, and column data.
I'll wrap this in an _if_ statement, 
so that if nothing is found for this artist, 
I'll print that _maybe we should add this record_.
I'll run this:

```html  
===================
ARTIST_ID      ARTIST_NAME
2              Elf
```

And here, because I'm using _Elf_, I'll get Elf's information 
printed in tabular form.
I'll again change _Elf_, to _Neil Young_.
I'll rerun this:

```html  
===================
ARTIST_ID      ARTIST_NAME
Maybe we should add this record
```

This code still prints the column names,
which means the _resultSet_ got returned with metadata, but not results.
And I also see the text, _maybe we should add this record_.
So let's do that.
I'll create another private static method,
this time to insert data into a table.

```java  
private static boolean insertRecord(Statement statement, String table, 
                                    String[] columnNames, String[] columnValues)
        throws SQLException {
    
    String colNames = String.join(",", columnNames);
    String colValues = String.join("','", columnValues);
    String query = "INSERT INTO %s (%s) VALUES ('%s')".formatted(table, colNames, colValues);
    System.out.println(query);
    boolean insertResult = statement.execute(query);
    System.out.println("insertedResult= = " + insertResult);
    return insertResult;    
    int recordsInserted = statement.getUpdateCount();
}
```

This will be similar to the _selectRecord_ method, 
but I'll call it _insertRecord_,
and instead of a single _columnName_ and single column value, 
I'll pass arrays there.
This method will also throw an _SQLException_.
I'll join the column names into a comma-delimited string.
I'll do the same with column values,
but I want them to be enclosed in single quotes.
I'll construct the `insert` DML query, with these values, 
using the _formatted_ method.
I'll print the generated _insert_ statement, 
so you can see it. 
I'll call _execute_ on the _statement_,
passing it my generated _insert_ query, 
and getting the **boolean** result back.
I'll print the result.
And return that result.
Ok, so now, I'll add a call to this,
in the code in the _main_ method.

```java  
public static void main(String[] args) {
    
    try (Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3335/music", 
            System.getenv("MYSQL_USER"), 
            System.getenv("MYSQL_PASS")); 
         Statement statement = connection.createStatement();
         ) {

        String tableName = "music.artists";
        String columnName = "artist_name";
        String columnValue = "Neil Young";
        if (!executeSelect(statement, tableName, columnName, columnValue)) {
            System.out.println("Maybe we should add this record");
            insertRecord(statement, tableName, new String[]{columnName}, new String[]{columnValue});
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

I'll pass the _statement_ and _tableName_,
and I'll pass string _arrays_, using a single value in each array, 
the same values as before.
Let's run this and see what happens.

```html  
===================
ARTIST_ID      ARTIST_NAME
Maybe we should add this record
INSERT INTO music.artists (artist_name) VALUES ('Neil Young')
insertResult = false
```

You can see the insert DML statement that was created printed out.
Then `insertResult = false`, is printed.
Does this mean this statement failed?
No, actually it doesn't.
First, if a SQL statement really fails, 
meaning if it gets an error, I would've gotten an exception,
and the application would have ended there.
Are you wondering if this statement didn't work
because we didn't include artist id?
Actually, for any of the tables in this database,
the IDs are automatically generated for us 
when a new record is inserted,
because of how this schema was set up.
This means we don't have to include artist ID in the insert statement, 
as one of the values that need to get inserted.
If you have MySQL WorkBench open, you can try running a select query
to confirm _Neil Young_ was really added.
So why did we get false back?
As it turns out, we'll only get **true** back 
if we're running a select query, so again
you can't use this boolean value, 
to confirm whether the record was added.

```java  
private static boolean insertRecord(Statement statement, String table, 
                                    String[] columnNames, String[] columnValues)
        throws SQLException {
    
    String colNames = String.join(",", columnNames);
    String colValues = String.join("','", columnValues);
    String query = "INSERT INTO %s (%s) VALUES ('%s')".formatted(table, colNames, colValues);
    System.out.println(query);
    boolean insertResult = statement.execute(query);
    
    //System.out.println("insertedResult= = " + insertResult);
    //return insertResult;    
    
    int recordsInserted = statement.getUpdateCount();
    if (recordsInserted > 0) {
        executeSelect(statement, table, columnNames[0], columnValues[0]);
    }
    return recordsInserted > 0;
}
```

I'll remove that code from the _insertRecord_ method.
Instead, I'll check the _getUpdateCount_, on the statement.
I'll pass that value to a _recordsInserted_ variable. 
If that's greater than zero,
then that means a record was inserted in this case. 
For good measure, I'll call _executeSelect_,
passing the first column name and value, to select the data. 
I'll return **true** if the number of records inserted is greater than zero.
I'll run my code again:

```html  
ARTIST_ID      ARTIST_NAME
202            Neil Young
```

This prints out that _Neil Young_ was added, 
with the generated artist id, that MySQL automatically generated.
This didn't execute an insert, because we inserted this in the previous run.
So I'll change the artist name again:

```java  
public static void main(String[] args) {
    
    try (Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3335/music", 
            System.getenv("MYSQL_USER"), 
            System.getenv("MYSQL_PASS")); 
         Statement statement = connection.createStatement();
         ) {

        String tableName = "music.artists";
        String columnName = "artist_name";
        String columnValue = "Bob Dylan";
        if (!executeSelect(statement, tableName, columnName, columnValue)) {
            System.out.println("Maybe we should add this record");
            insertRecord(statement, tableName, new String[]{columnName}, new String[]{columnValue});
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

this time to _Bob Dylan_.
I'll re-run this code.

```html  
===================
ARTIST_ID      ARTIST_NAME
Maybe we should add this record
INSERT INTO music.artists (artist_name) VALUES ('Bob Dylan')
===================
ARTIST_ID      ARTIST_NAME
203            Bob Dylan
```

Now I can see the insert statement that was executed,
and I get confirmation the _Bob Dylan_ was added.

So _execute_ method on the **Statement** type 
can be used for any SQL statement.
This method returns a **boolean** value which can be a bit confusing.
We looked at using _execute_ for both the _select_ and _insert_ statements.
I'll be continuing this discussion, 
but I'll now cover the _delete_ and _update_ statements.
So again, I'll create a method. 
This method will execute a _delete_ statement generically, 
given a table name, column name, and column value.

```java  
private static boolean deleteRecord(Statement statement, String table, String columnName, String columnValue)
        throws SQLException {
    
    String query = "DELETE FROM %s WHERE %s='%s'".formatted(table, columnName, columnValue);
    System.out.println(query);
    statement.execute(query);
  
    int recordsDeleted = statement.getUpdateCount();
    if (recordsDeleted > 0) {
        executeSelect(statement, table, columnName, columnValue);
    }
    return recordsDeleted > 0;
}
```

This is a little dangerous without any validation, 
but let's just assume validation took place already somewhere.
I'll construct a DELETE SQL statement.
Here I'm using the _formatted_ method, with the method arguments, 
which you've seen me do a lot. 
I'll print the generated query. 
I'll execute it.
I'll get the update count like I did when I ran the update statement. 
This time it returns the number of records deleted. 
I can use this method when I execute any _update_, _delete_, 
or _insert_ statement. 
If that number's greater than zero,
I'll run a select query again, just to confirm the data was deleted. 
I'll return **true** if any records were deleted.
Now that I've got this method,
I'll add a call to it, in the _main_ method.

```java  
public static void main(String[] args) {
    
    try (Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3335/music", 
            System.getenv("MYSQL_USER"), 
            System.getenv("MYSQL_PASS")); 
         Statement statement = connection.createStatement();
         ) {

        String tableName = "music.artists";
        String columnName = "artist_name";
        String columnValue = "Bob Dylan";
        if (!executeSelect(statement, tableName, columnName, columnValue)) {
            System.out.println("Maybe we should add this record");
            insertRecord(statement, tableName, new String[]{columnName}, new String[]{columnValue});
        } else {
          deleteRecord(statement, tableName, columnName, columnValue);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

I'll do this by adding an else block,
so if a record was found, I'll delete it
with a call to my deleteRecord method.
Running this code:

```html  
===================
ARTIST_ID      ARTIST_NAME    
203            Bob Dylan      
DELETE FROM music.artists WHERE artist_name='Bob Dylan'
===================
ARTIST_ID      ARTIST_NAME
```

I can see that the initial select returns _Bob Dylan_.
Then I see the generated _delete_ statement.
And the next select returns no data for _Bob Dylan_.
So I've successfully deleted _Bob Dylan_ from the **music** database.
Now I'll change the value from _Bob Dylan_,
to _Elf_ who already had data set up,
when I imported the database.
I'll rerun this code for Elf:

```html  
===================
ARTIST_ID      ARTIST_NAME
2              Elf
DELETE FROM music.artists WHERE artist_name='Elf'
Exception in thread "main" java.lang.RuntimeException: java.sql.SQLIntegrityConstraintViolationException: Cannot delete or update a parent row: a foreign key constraint fails (`music`.`albums`, CONSTRAINT `FK_ARTISTID` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`artist_id`))
at .MusicDML.main()
Caused by: java.sql.SQLIntegrityConstraintViolationException: Cannot delete or update a parent row: a foreign key constraint fails (`music`.`albums`, CONSTRAINT `FK_ARTISTID` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`artist_id`))
at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:118)
at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
at com.mysql.cj.jdbc.StatementImpl.executeInternal(StatementImpl.java:770)
at com.mysql.cj.jdbc.StatementImpl.execute(StatementImpl.java:653)
at .MusicDML.deleteRecord()
at .MusicDML.main()
```

I can see the output from the select record, 
followed by the _delete_ statement that was generated.
But after that, I can see that the _execute_ statement failed.
I get an _SQLIntegrityConstraintViolationException_ with the message 
that I can't _delete_ or _update_ a parent row,
since a foreign key constraint fails.
Ok, so what does this mean?

![image45](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image45.png?raw=true)

You might remember this diagram from an earlier section.
I showed it to you when we imported 
the **music** database into _MySQL workbench_.
Notice where FK is shown on the **songs** and **albums** table.
These are foreign keys which describe dependencies.
**Albums** has a foreign key, the artist ID, 
as a column in its table.
If I'd been successful at deleting _Elf_ in my code, 
I would have been stuck with records in the **albums** table, 
for an artist who no longer existed in our data.
This would break the integrity of the data 
and cause a lot of problems.
Since this is pretty undesirable, 
Foreign key relationships are commonly set up in databases, 
to prevent users from inadvertently deleting parent records,
and leaving orphaned child records around.
Now we didn't have this problem with _Bob Dylan_,
because we never created any album records for that artist,
and therefore no song records either.
We can change this behavior in the database
by allowing a _cascade delete_.
This means, when we delete an artist,
the _delete_ would then be applied, 
or cascaded down to the albums, 
deleting all albums whose artist id is the deleted artist.
We'd also have to enable cascade delete on the **songs** table, 
so that if an album is deleted, 
songs associated with that album would get deleted too.
For now, I'm not going to change the **music** database,
and I'll keep the restrictions on deletion.
So far, I've shown you 
three of the four crud operations, using `statement.execute`.
The final statement is the _u_ in _crud_, the _update_ statement, 
so I'll implement that next.
Again, I'll create a private static method.

```java  
private static boolean updateRecord(Statement statement, String table, 
                                    String matchedColumn, String matchedValue,
                                    String updatedColumn, String updatedValue) 
        throws SQLException {
    
    String query = "UPDATE %s SET %s = '%s' WHERE %s='%s'"
            .formatted(table, updatedColumn, updatedValue, matchedColumn, matchedValue);
    System.out.println(query);
    statement.execute(query);
  
    int recordsUpdated = statement.getUpdateCount();
    if (recordsUpdated > 0) {
        executeSelect(statement, table, updatedColumn, updatedValue);
    }
    return recordsUpdated > 0;
}
```

This time I'll copy the _deleteRecord_ method, and paste a copy just below.
I'll change the name to _updateRecord_. 
I'll also change the parameter names, from _columnName_ and _columnValue_, 
to _updatedColumn_ and _updatedValue_ respectively.
These represent the column that needs to be updated, 
and the value it will get updated to.
I'll next remove the query string,
and replace it with an _Update_ query.
And on the _formatted_ part,
I'll again change the names of the last two arguments,
to _updatedColumn_ and _updatedValue_.
I need to do the same thing, 
when I call the _executeSelect_ statement, 
a couple lines below, in the if _recordsDeleted_ clause.
I'll also take the opportunity, 
to change the name of _recordsDeleted_, to _recordsUpdated_, 
in line with the other changes; we have made in this method.
Next, I'll add two additional parameters,
to the method's declaration.
These will identify the data, used in the _where_ clause,
so I'll call the _matchedColumn_ and _matchedValue_.
I'll insert these as the third and four parameters.
I'll add these to the _formatted_ method.
This is the data used to create the _where_ clause.
I'll add these two new arguments, 
as additional arguments to the _formatted_ method.
That's all I need to do in this method.
To call it, I'll go back to the _main_ method,
and I'll comment out that _deleteRecord_ call.
Then I'll instead call the _updateRecord_.

```java  
public static void main(String[] args) {
    
    try (Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3335/music", 
            System.getenv("MYSQL_USER"), 
            System.getenv("MYSQL_PASS")); 
         Statement statement = connection.createStatement();
         ) {

        String tableName = "music.artists";
        String columnName = "artist_name";
        String columnValue = "Elf";
        if (!executeSelect(statement, tableName, columnName, columnValue)) {
            System.out.println("Maybe we should add this record");
            insertRecord(statement, tableName, new String[]{columnName}, new String[]{columnValue});
        } else {
          //deleteRecord(statement, tableName, columnName, columnValue);

          updateRecord(statement, tableName, columnName,
                  columnValue, columnName,
                  columnValue.toUpperCase());
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

This starts out like the others, so I'll pass statement, 
the table name and column name.
Next, I'll pass the column value. 
The second and third arguments will look for an artist,
whatever I have in column value. 
Next, I need to say what will be updated, 
so I'll pass column name again,
because I'm updating the same column I'm matching on, 
in this case the artist name. 
And I want to update the artist's name to all _uppercases_.
Running this code:

```html  
===================
ARTIST_ID      ARTIST_NAME    
2              Elf            
UPDATE music.artists SET artist_name = 'ELF' WHERE artist_name='Elf'
===================
ARTIST_ID      ARTIST_NAME    
2              ELF 
```

The code gets the data for an Artist named _Elf_ in mixed case 
and displays it, and I see the artist id and artist name.
Then I can see the update statement I generated.
This updates the **artists** table, 
setting the artist name to _ELF_ in uppercase.
This update is done on the record 
which currently has artist name equal to _Elf_.
The code does a final query on the artist table,
but it's using the new artist name,
and you can see that printed out.
In this case, I've successfully updated a record in the **music** database.
All the examples I've shown you so far,
using _insert_, _delete_ and _update_, have been focused on 
a single record in the database.
In the next scenario, I'll be inserting all the data needed, 
to add an album with songs, for a new artist.
To demonstrate this, I'll create a new method,
private static void, called _insertArtistAlbum_.

```java  
private static void insertArtistAlbum(Statement statement, String artistName, String albumName) 
        throws SQLException {
    
    String artistInsert = "INSERT INTO music.artists (artist_name) VALUES (%s)"
            .formatted(statement.enquoteLiteral(artistName));
    System.out.println(artistInsert);
    statement.execute(artistInsert, Statement.RETURN_GENERATED_KEYS);

}
```

This will take a statement, an artist name, and an album name, 
and it'll throw an _SQLException_.
I'll start with inserting the artist, 
which ultimately is the top of the relationship tree.
I'll again manually create this _insert_ statement. 
This time, though, I'm not going to enclose the `%s` in single quotes. 
Instead, I'll call `statement.enquoteLiteral`, passing it _artistName_. 
This method will enclose the string in single quotes, 
as well as escape any single quotes contained in the text, 
so it's better to use this if your driver supports it. 
This method was added in _JDK.9_ to the **statement** interface, 
as a default method. 
I'll print this statement out. 
This time, I'll call an overloaded version of the _execute_ method.
This one has a second argument, which takes an **int**, 
and in this case I'll pass a constant on the **Statement** interface,
called _RETURN_GENERATED_KEYS_. 
Doing this will return the automatically generated ID,
and store it in a special _resultSet_. 
The reason I want to do this is I need the artist id 
when I create the album record, 
because of the foreign key field in that table. 
Using this mechanism, I don't have to include my own select query 
to get the newly generated id.

```java  
private static void insertArtistAlbum(Statement statement, String artistName, String albumName) 
        throws SQLException {
    
    String artistInsert = "INSERT INTO music.artists (artist_name) VALUES (%s)"
            .formatted(statement.enquoteLiteral(artistName));
    System.out.println(artistInsert);
    statement.execute(artistInsert, Statement.RETURN_GENERATED_KEYS);

    ResultSet rs = statement.getGeneratedKeys();
    int artistId = (rs != null && rs.next()) ? rs.getInt(1) : -1;
    String albumInsert = ("INSERT INTO music.albums (album_name, artist_id)" + 
            " VALUES (%s, %d)").formatted(statement.enquoteLiteral(albumName), artistId);
    System.out.println(albumInsert);
    statement.execute(albumInsert, Statement.RETURN_GENERATED_KEYS);
    rs = statement.getGeneratedKeys();
    int albumId = (rs != null && rs.next()) ? rs.getInt(1) : -1;
    
}
```

The key, the artist id in this case, gets returned in a _resultSet_ 
I get by calling _getGeneratedKeys_.
I'll set up a variable, called artist id, 
and if I got data back from the previous statement,
I'll set it to `rs.getInt`, passing a `1` there. 
Otherwise, I'll just set this value to a default of `-1`. 
If I did have an artist id of `-1`, 
I'd probably want to abort this operation, 
but I'm just going to keep this code simple here, 
and assume this is successful,
and I'll get a good artist id. 
Now I can generate an _albumInsert_ statement, 
using the artist id I got back, which this record needs, 
because it's a child record of the artist.
Again, I'll use the _enquoteLiteral_ method on _albumName_ here. 
I'll print this statement out.
I'll again call _execute_, with _RETURN_GENERATED_KEYS_ passed to it.
As I did before, I'll use _getGeneratedKeys_ to get the _resultSet_
that has the key, the album id.
And like I did before, I'll retrieve my new album id from that _resultSet_.

```java  
private static void insertArtistAlbum(Statement statement, String artistName, String albumName) 
        throws SQLException {
    
    String artistInsert = "INSERT INTO music.artists (artist_name) VALUES (%s)"
            .formatted(statement.enquoteLiteral(artistName));
    System.out.println(artistInsert);
    statement.execute(artistInsert, Statement.RETURN_GENERATED_KEYS);

    ResultSet rs = statement.getGeneratedKeys();
    int artistId = (rs != null && rs.next()) ? rs.getInt(1) : -1;
    String albumInsert = ("INSERT INTO music.albums (album_name, artist_id)" + 
            " VALUES (%s, %d)").formatted(statement.enquoteLiteral(albumName), artistId);
    System.out.println(albumInsert);
    statement.execute(albumInsert, Statement.RETURN_GENERATED_KEYS);
    rs = statement.getGeneratedKeys();
    int albumId = (rs != null && rs.next()) ? rs.getInt(1) : -1;
    
    String[] songs = new String[]{
            "You're No Good",
            "Talkin' New York",
            "In My Time of Dyin'",
            "Man of Constant Sorrow",
            "Fixin' to Die",
            "Pretty Peggy-O",
            "Highway 51 Blues"
    };

    String songInsert = "INSERT INTO music.songs " + 
            "(track_number, song_title, album_id) VALUES (%d, %s, %d)";
  
    for (int i = 0; i < songs.length; i++) {
        String songQuery = songInsert.formatted(i + 1, statement.enquoteLiteral(songs[i]), albumId);
        System.out.println(songQuery);
        statement.execute(songQuery);
    }
    executeSelect(statement, "music.albumview", "album_name", "Bob Dylan");
}
```

I'll set up an array of song titles, from _Bob Dylan_'s record album of the same name.
First, _You're no good_. 
Then, _Talkin' New York_.
You'll see here that several of his titles include a single quote in them. 
I'll continue with _In My Time of Dyin_. 
_Man of Constant Sorrow_. 
_Fixin' to Die_, _Pretty Peggy-O_, 
and I'll stop at _Highway 51 Blues_,
even though there are 6 more songs on this album.
I'll set up the _songInsert_ **String**.
I'll insert a track number, song title, and album id.
I'll loop through the array of songs.
And create a song query each time, constructed using the _songInsert_ string.
This time the _enquoteLiteral_ method will escape the single quote 
in the song title as well.
I'll print each of these queries out.
And I'll execute each.
Finally, I'll call the _executeSelect_ method on _albumview_, 
which will give us all the related data, in one single select statement.
Reviewing this code, I first insert the artist data, 
and get a key back, the artist id in this case.
I then can use that key to insert the related album record, 
a child of the artist table's record for this artist.
Again, I get the album key back, and then use that, 
to create the dependent song records.
Now, to run this code,
I'll edit the _main_ method to use this.

```java  
public static void main(String[] args) {
    
    try (Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3335/music", 
            System.getenv("MYSQL_USER"), 
            System.getenv("MYSQL_PASS")); 
         Statement statement = connection.createStatement();
         ) {

        String tableName = "music.artists";
        String columnName = "artist_name";
        //String columnValue = "Elf";
        String columnValue = "Bob Dylan";
        if (!executeSelect(statement, tableName, columnName, columnValue)) {
            //System.out.println("Maybe we should add this record");
            //insertRecord(statement, tableName, new String[]{columnName}, new String[]{columnValue});
          insertArtistAlbum(statement, columnValue, columnValue);
        } else {
          updateRecord(statement, tableName, columnName,
                  columnValue, columnName,
                  columnValue.toUpperCase());
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

I'll first remove the code in the _if-clause_.
I'll replace those with a call to my new method, _insertArtistAlbum_.
This takes the statement, and the artist name, which is the second argument. 
In this case, I'll pass that as the third value here as well,
because I'm going to have my album be the same name as the artist, for my test case.
I'll use _Bob Dylan_ again, so I'll change the _columnValue_ here, from _Elf_ to _Bob Dylan_.
Ok, so it's time to test this out, so I'll run it.

```html  
===================
ARTIST_ID      ARTIST_NAME    
INSERT INTO music.artists (artist_name) VALUES ('Bob Dylan')
INSERT INTO music.albums (album_name, artist_id) VALUES ('Bob Dylan', 204)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (1, 'You''re No Good', 879)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (2, 'Talkin'' New York', 879)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (3, 'In My Time of Dyin''', 879)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (4, 'Man of Constant Sorrow', 879)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (5, 'Fixin'' to Die', 879)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (6, 'Pretty Peggy-O', 879)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (7, 'Highway 51 Blues', 879)
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
```

First, our _select_ statement, which prints a table of records, 
just printed a header row, because _Bob Dylan_ isn't in the **music** database yet.
Then we start seeing the generated _insert_ statements.
The first isn't that interesting, except to check
that the _enclosedLiteral_ method worked, 
and we can see that with the single quotes around _Bob Dylan_.
The next statement, though, notices that I've got the artist ID there,
which I got by specifying _RETURN_GENERATED_KEYS_ in the _execute_ method.
Then I see a series of song inserts, and there too 
you can see the album id is passed to each of these.
I again used _RETURN_GENERATED_KEYS_ to make that happen.
Finally, the call to _executeSelect_ that queries the view 
confirms all the data was added and correctly set up.

This time, I'll be deleting all the records in three tables, 
associated with a single artist.
I'll start by deleting the songs, then the associated album, 
and finally the artist.
I want all of this data to be deleted in a single _transaction_.
What I mean by that is, if something goes wrong with deleting 
any single one of these records, I don't want any of the records to be deleted.
I want all records to be deleted, or none at all in other words.
To do this, I need to exercise more control over 
how the statement commits the changes to the database.

In all the statements I've executed so far, 
I've been doing an implicit commit, 
taking advantage of an auto commit setting on the connection.
The **AUTOCOMMIT** option tells the database,
to automatically commit changes after every statement.
We never manually had to call commit on the statement for our changes 
to be persisted to the database.
**By default, a connection object is in auto-commit mode.**
This can be useful for small changes, 
but it can also be dangerous if you make a mistake. 
When you make changes by executing queries,
these changes are first stored in a temporary location, 
called a redo log or journal file.
When you execute a commit, 
these changes are then persisted permanently to the database.
If you turn _autocommit_ off, you can run a series of statements 
and review the effects of your changes from this temporary location, 
before you execute a manual commit.
It's a good idea to turn _AUTOCOMMIT_ off, 
if you want a series of related statements to be treated, 
as a single atomic operation.
This means all the statements in that group have to be run successfully 
before they actually get persisted permanently.

In the database world, a transaction is a series of one 
or more database operations (such as inserts, updates, or deletes)
that are treated as a single unit of work.
**Transactions** ensure that database operations are atomic, 
meaning they either all succeed, or all fail.
If any part of the _transaction_ fails,
the _transaction_ gets **rolled back**, 
and no changes are applied to the database.
In JDBC, a _transaction_ is initialized 
when we turn _AUTOCOMMIT_ off, on the **connection** object. 
Let's look at this in some code.

I'm going to add a method to the **MusicDML** class, 
as the last method in that class.
This will be private, static and void.
I'll call it _deleteArtistAlbum_.

```java  
private static void deleteArtistAlbum(Connection conn, Statement statement, String artistName, String albumName)
        throws SQLException {
  
    System.out.println("AUTOCOMMIT = " + conn.getAutoCommit());
}
```

The first parameter I'll pass to this is my connection, 
and you'll see why in just a minute.
After this, like the _insertArtistAlbum_, 
I'll include the statement, the artist name, and the album name.
This method will throw an _SQLException_.
I'll start with one statement in this method, 
and I'll just print out what I get by calling get _autocommit_ method on the connection.
I'll be coming back to this method, but first, 
I'll invoke it from the _main_ method.
I want to run it, to see what the connection's current auto commit value is,
before I do anything else.

```java  
public static void main(String[] args) {
    
    try (Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3335/music", 
            System.getenv("MYSQL_USER"), 
            System.getenv("MYSQL_PASS")); 
         Statement statement = connection.createStatement();
         ) {

        String tableName = "music.artists";
        String columnName = "artist_name";
        String columnValue = "Bob Dylan";
        if (!executeSelect(statement, tableName, columnName, columnValue)) {
            insertArtistAlbum(statement, columnValue, columnValue);
        } else {
            //updateRecord(statement, tableName, columnName, columnValue, columnName, columnValue.toUpperCase());
            try {
                deleteArtistAlbum(connection, statement, columnValue, columnValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            executeSelect(statement, "music.albumview", "album_name", columnValue);
            executeSelect(statement, "music.albums", "album_name", columnValue);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

So in the _main_ method, I'll be replacing the code I have in the _else_ statement.
I'll start by deleting the statements that are there now.
I'll make a call to my _deleteArtistAlbum_ statement instead.
First, I'll wrap this in a _try-catch_, 
because I want to see the results even if I get an error.
I'll pass the connection first, then the statement.
And since I'm deleting _Bob Dylan_ here,
I'll pass artist name as the album name too.
If I do get an exception executing the _delete_
I'll print its stack trace. 
After this, I'll print out any album data, from the _albumview_.
There shouldn't be any records after the _delete_ method 
if it's successful, but this will confirm it. 
For good measure, I'll also print any data in `music.albums` for this album
I'll run this:

```html  
===================
ARTIST_ID      ARTIST_NAME    
204            Bob Dylan      
AUTOCOMMIT = true
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
===================
ALBUM_ID       ALBUM_NAME     ARTIST_ID      
879            Bob Dylan      204 
```

I'll get _Bob Dylan_ back with his artist id,
because he's already in the database,
we inserted him before.
And you can see _AUTOCOMMIT_ on the **connection** is set to **true**.
This is the default, for any JDBC connection.
Getting back to my _deleteArtistAlbum_ method,
I'll start setting up some _delete_ queries.

```java  
private static void deleteArtistAlbum(Connection conn, Statement statement, String artistName, String albumName)
        throws SQLException {

    System.out.println("AUTOCOMMIT = " + conn.getAutoCommit());
    String deleteSongs = """
            DELETE FROM music.songs WHERE album_id =
            (SELECT ALBUM_ID from music.albums WHERE album_name = '%s')"""
            .formatted(albumName);
  
    int deletedSongs = statement.executeUpdate(deleteSongs);
    System.out.printf("Deleted %d rows from music.songs%n", deletedSongs);
    String deleteAlbums = "DELETE FROM music.albums WHERE album_name='%s".formatted(albumName);
    int deletedAlbums = statement.executeUpdate(deleteAlbums);
    System.out.printf("Deleted %d rows from music.albums%n", deletedAlbums);
}
```

I'll set up the **songs** query as a text block, so `deleteSongs = """`.
I'll delete from `music.songs`.
I could have done a query to figure out what album id was first. 
Instead, I can use a subquery in my SQL,
which does the same thing, 
so I'll set album id to the result of this subquery.
The SQL subquery is in parentheses, 
and is just another _Select_ statement.
This lets me query the _ALBUM_ID_ from `music.albums` 
within the same SQL statement.
Don't worry if you don't understand the SQL completely.
My goal here is not to teach you SQL, 
but to teach you how Java executes it,
and the consequences to expect, depending on how you execute it. 
I'll pass _albumName_ to the _formatted_ method. 
So next, instead of using _execute_, 
I'll use another method on statement, called _executeUpdate_. 
This returns the number of records that were affected by this statement,
so in this case, it'll return the number of records deleted. 
I'll print that number out.
I'll now set up the _deleteAlbums_ query.
Once songs are deleted, 
I should be able to successfully delete the associated album.
So here, I'll delete from albums, but in this case,
let's imagine I forgot the closing single quote. 
And again, I'll call _executeUpdate_ and return the result 
to a variable called _deletedAlbums_. 
And I'll print that out.
I'll run this:

```html  
===================
ARTIST_ID      ARTIST_NAME    
204            Bob Dylan      
AUTOCOMMIT = true
Deleted 7 rows from music.songs
java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near ''Bob Dylan' at line 1
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:121)
	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
	at com.mysql.cj.jdbc.StatementImpl.executeUpdateInternal(StatementImpl.java:1344)
	at com.mysql.cj.jdbc.StatementImpl.executeLargeUpdate(StatementImpl.java:2090)
	at com.mysql.cj.jdbc.StatementImpl.executeUpdate(StatementImpl.java:1253)
	at .MusicDML.deleteArtistAlbum()
	at .MusicDML.main()
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
===================
ALBUM_ID       ALBUM_NAME     ARTIST_ID      
879            Bob Dylan      204 
```

So you can see first, that _Bob Dylan_ is still in our database,
and I've deleted `7` songs successfully.
Then I get an exception that there's an error in the SQL syntax.
After this, I can see the data in the view get printed, 
but there's no data in the view.
If I only queried the view, 
I wouldn't realize I've got a problem in my database.
I have an album that exists, that has no songs in it.
There's no foreign key constraint that prevents this scenario,
but it's still probably not an ideal situation,
to have an album with no songs in our data.
For this reason, I don't want any songs 
to get deleted if the album record deletion fails.
I want to treat these two _execute_ methods as a unit,
so it's either all success or all failure.
In other words, I want to establish a **transaction**.
To achieve this, I first have to turn _autocommit_ off on the **connection**.
Before that, I'll first fix my error in the code, 
and include the ending single quote in the _deleteAlbums_ string.

```java  
private static void deleteArtistAlbum(Connection conn, Statement statement, String artistName, String albumName)
        throws SQLException {

    System.out.println("AUTOCOMMIT = " + conn.getAutoCommit());
    String deleteSongs = """
            DELETE FROM music.songs WHERE album_id =
            (SELECT ALBUM_ID from music.albums WHERE album_name = '%s')"""
            .formatted(albumName);
  
    int deletedSongs = statement.executeUpdate(deleteSongs);
    System.out.printf("Deleted %d rows from music.songs%n", deletedSongs);
    //String deleteAlbums = "DELETE FROM music.albums WHERE album_name='%s".formatted(albumName);
    String deleteAlbums = "DELETE FROM music.albums WHERE album_name='%s'".formatted(albumName);
    
    int deletedAlbums = statement.executeUpdate(deleteAlbums);
    System.out.printf("Deleted %d rows from music.albums%n", deletedAlbums);
    
    String deleteArtist = "DELETE FROM music.artists WHERE artist_name='%s'".formatted(artistName);
    int deletedArtists = statement.executeUpdate(deleteAlbums);
    System.out.printf("Deleted %d rows from music.albums%n", deletedAlbums);
}
```

I'll include deleting _Bob Dylan_ from the artist table.
I want my code to fully delete all _Bob Dylan_ data.
My delete artist statement is straightforward, similar to delete album.
I'll call _executeUpdate_ again, and get the count of _deletedArtists_.
I'll print how many artists were deleted, which is hopefully only one.
I'll run the code as it is, so that all the _Bob Dylan_ data is removed.

```html  
===================
ARTIST_ID      ARTIST_NAME
204            Bob Dylan
AUTOCOMMIT = true
Deleted 0 rows from music.songs
Deleted 1 rows from music.albums
Deleted 1 rows from music.albums
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE
===================
ALBUM_ID       ALBUM_NAME     ARTIST_ID 
```

I'll run it again, which will insert all the data.

```html  
===================
ARTIST_ID      ARTIST_NAME
INSERT INTO music.artists (artist_name) VALUES ('Bob Dylan')
INSERT INTO music.albums (album_name, artist_id) VALUES ('Bob Dylan', 205)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (1, 'You''re No Good', 880)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (2, 'Talkin'' New York', 880)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (3, 'In My Time of Dyin''', 880)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (4, 'Man of Constant Sorrow', 880)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (5, 'Fixin'' to Die', 880)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (6, 'Pretty Peggy-O', 880)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (7, 'Highway 51 Blues', 880)
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE
Bob Dylan      Bob Dylan      1              You're No Good
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die
Bob Dylan      Bob Dylan      6              Pretty Peggy-O
Bob Dylan      Bob Dylan      7              Highway 51 Blues
```

Now, I've got my seven songs back.
Getting back to my _deleteArtistAlbum_ method,
I'll remove that single quote again, 
in the _deleteAlbums_ string, to force an exception.

```java  
private static void deleteArtistAlbum(Connection conn, Statement statement, String artistName, String albumName)
        throws SQLException {

    System.out.println("AUTOCOMMIT = " + conn.getAutoCommit());
    conn.setAutoCommit(false);
    String deleteSongs = """
            DELETE FROM music.songs WHERE album_id =
            (SELECT ALBUM_ID from music.albums WHERE album_name = '%s')"""
            .formatted(albumName);
  
    int deletedSongs = statement.executeUpdate(deleteSongs);
    System.out.printf("Deleted %d rows from music.songs%n", deletedSongs);
    String deleteAlbums = "DELETE FROM music.albums WHERE album_name='%s".formatted(albumName);
    //String deleteAlbums = "DELETE FROM music.albums WHERE album_name='%s'".formatted(albumName);
  
    int deletedAlbums = statement.executeUpdate(deleteAlbums);
    System.out.printf("Deleted %d rows from music.albums%n", deletedAlbums);
  
    String deleteArtist = "DELETE FROM music.artists WHERE artist_name='%s'".formatted(artistName);
    int deletedArtists = statement.executeUpdate(deleteArtist);
    System.out.printf("Deleted %d rows from music.albums%n", deleteArtist);
    conn.commit();
    conn.setAutoCommit(true);
}
```

This time, though, I'll put all these _delete_ statements inside a **transaction**.
To do this, I'll first turn auto commit off.
This is done by calling _setAutoCommit_ 
on the **connection**, and passing it **false**.
At the end of this method, I'll manually commit 
by calling the _commit_ method on the **connection**.
After this transaction completes, I want to set auto commit back to **true**.
I'll run this again, with these changes.

```html  
===================
ARTIST_ID      ARTIST_NAME    
205            Bob Dylan      
AUTOCOMMIT = true
Deleted 7 rows from music.songs
java.sql.SQLSyntaxErrorException : You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near ''Bob Dylan' at line 1
    at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException()
    at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException()
    at com.mysql.cj.jdbc.StatementImpl.executeUpdateInternal()
    at com.mysql.cj.jdbc.StatementImpl.executeLargeUpdate()
    at .MusicDML.deleteArtistAlbum()
    at .MusicDML.main()
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
===================
ALBUM_ID       ALBUM_NAME     ARTIST_ID 
```

This output looks exactly the same as before.
It looks like `7` rows were deleted.
Until another commit is done,
this deletion operation's results are sitting in a temporary location, 
waiting to be committed.
We really don't want them to be committed to this connection.
I really want these data to be rolled back from the temporary location,
as if they were never executed.

```java  
private static void deleteArtistAlbum(Connection conn, Statement statement, String artistName, String albumName)
        throws SQLException {
    try {
        System.out.println("AUTOCOMMIT = " + conn.getAutoCommit());
        conn.setAutoCommit(false);
        String deleteSongs = """
            DELETE FROM music.songs WHERE album_id =
            (SELECT ALBUM_ID from music.albums WHERE album_name = '%s')"""
                .formatted(albumName);

        int deletedSongs = statement.executeUpdate(deleteSongs);
        System.out.printf("Deleted %d rows from music.songs%n", deletedSongs);
        String deleteAlbums = "DELETE FROM music.albums WHERE album_name='%s".formatted(albumName);
        int deletedAlbums = statement.executeUpdate(deleteAlbums);
        System.out.printf("Deleted %d rows from music.albums%n", deletedAlbums);
        
        String deleteArtist = "DELETE FROM music.artists WHERE artist_name='%s'".formatted(artistName);
        int deletedArtists = statement.executeUpdate(deleteArtist);
        System.out.printf("Deleted %d rows from music.albums%n", deletedArtists);
        conn.commit();
    } catch (SQLException e) {
        e.printStackTrace();
        conn.rollback();
    }
    conn.setAutoCommit(true);
}
```

To do this, I need to wrap a _try_ block around my transaction code.
I'll add a _try_ statement at the start.
And I want the catch to be after I attempt the commit, 
and before I set _autocommit_ to **true**.
I'll catch the _SQLException_ here.
And print the stack trace.
Finally, I'll call rollback on the connection. 
This will set the state of the temporary storage back 
to the original state when the transaction started, 
before I executed any statements at all.
I'll run this.

```html  
===================
ARTIST_ID      ARTIST_NAME    
205            Bob Dylan      
AUTOCOMMIT = true
Deleted 7 rows from music.songs
java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near ''Bob Dylan' at line 1
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:121)
	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
	at com.mysql.cj.jdbc.StatementImpl.executeUpdateInternal(StatementImpl.java:1344)
	at com.mysql.cj.jdbc.StatementImpl.executeLargeUpdate(StatementImpl.java:2090)
	at com.mysql.cj.jdbc.StatementImpl.executeUpdate(StatementImpl.java:1253)
	at .MusicDML.deleteArtistAlbum()
	at .MusicDML.main()
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
===================
ALBUM_ID       ALBUM_NAME     ARTIST_ID      
880            Bob Dylan      205      
```

My output starts out the same with it looking like `7` songs were deleted.
But now I can see them in the view, even after the exception.
Because I'm sharing my connection across a session with multiple methods, 
I have to execute the _rollback_ method here,
to clean up the data so other sessions don't inadvertently work 
with my uncommitted changes, or even worse commit them.
If I had opened and closed the connection in my delete artist album method, 
this wouldn't have been an issue. 
I'll fix the error in my code again.
I'll run that again:

```html  
===================
ARTIST_ID      ARTIST_NAME    
205            Bob Dylan      
AUTOCOMMIT = true
Deleted 7 rows from music.songs
Deleted 1 rows from music.albums
Deleted 1 rows from music.albums
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
===================
ALBUM_ID       ALBUM_NAME     ARTIST_ID   
```

Now, Bob Dylan and his album and songs have all been deleted as a single unit,
in a database transaction that consisted of three individual SQL deletion statements.
Each _executeUpdate_ statement makes a round-trip to the database, 
which for a remote database server, can be expensive.
You can usually increase performance if you batch up your statements.
To demonstrate this, I'll first remove each of the _executeUpdate_ statements 
in my method, and the print statement that follows each one.

```java  
private static void deleteArtistAlbum(Connection conn, Statement statement, String artistName, String albumName)
        throws SQLException {
    try {
        System.out.println("AUTOCOMMIT = " + conn.getAutoCommit());
        conn.setAutoCommit(false);
        String deleteSongs = """
            DELETE FROM music.songs WHERE album_id =
            (SELECT ALBUM_ID from music.albums WHERE album_name = '%s')"""
                .formatted(albumName);

        //int deletedSongs = statement.executeUpdate(deleteSongs);
        //System.out.printf("Deleted %d rows from music.songs%n", deletedSongs);
        String deleteAlbums = "DELETE FROM music.albums WHERE album_name='%s'".formatted(albumName);
        
        //int deletedAlbums = statement.executeUpdate(deleteAlbums);
        //System.out.printf("Deleted %d rows from music.albums%n", deletedAlbums);
        String deleteArtist = "DELETE FROM music.artists WHERE artist_name='%s'".formatted(artistName);
        
        //int deletedArtists = statement.executeUpdate(deleteArtist);
        //System.out.printf("Deleted %d rows from music.albums%n", deletedArtists);
      
        statement.addBatch(deleteSongs);
        statement.addBatch(deleteAlbums);
        statement.addBatch(deleteArtist);
        int[] results = statement.executeBatch();
        System.out.println(Arrays.toString(results));
        conn.commit();
    } catch (SQLException e) {
        e.printStackTrace();
        conn.rollback();
    }
    conn.setAutoCommit(true);
}
```

Before the commit statement, I'll add three _addBatch_ statements, one for each query.
The _addBatch_ method is on statement, 
so here I'll pass the _deleteSongs_ string to that method. 
I'll next add the _deleteAlbums_ string. 
And finally, the _deleteArtist_ string.
The _addBatch_ method doesn't execute anything, 
it simply adds the statement to a list of statements,
that will get passed to the server.
To execute the statements, I have to call `statement.executeBatch`.
This method returns an integer array, containing the results of each statement, 
as if we ran _executeUpdate_ on each, so in our case,
we should get the number of records deleted for each statement. 
I'll print this data out.
I'll run this:

```html  
===================
ARTIST_ID      ARTIST_NAME    
INSERT INTO music.artists (artist_name) VALUES ('Bob Dylan')
INSERT INTO music.albums (album_name, artist_id) VALUES ('Bob Dylan', 206)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (1, 'You''re No Good', 885)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (2, 'Talkin'' New York', 885)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (3, 'In My Time of Dyin''', 885)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (4, 'Man of Constant Sorrow', 885)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (5, 'Fixin'' to Die', 885)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (6, 'Pretty Peggy-O', 885)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (7, 'Highway 51 Blues', 885)
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
```

and again I have to run it twice, first to insert _Bob Dylan_'s data, 
and the second to execute this code.

```html  
===================
ARTIST_ID      ARTIST_NAME
206            Bob Dylan      
AUTOCOMMIT = true
[7, 1, 1]
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
===================
ALBUM_ID       ALBUM_NAME     ARTIST_ID   
```

In this output, after the _autocommit_ equals **true**,
you can see the individual results of this batch execution, 
printed in the integer array.
The first statement affected `7` records,
and the next two affected `1` record, 
so this is consistent with the results we saw before.
So again, I'll introduce my error on the album string, 
removing the last single quote.

```java  
private static void deleteArtistAlbum(Connection conn, Statement statement, String artistName, String albumName)
        throws SQLException {
    try {
        System.out.println("AUTOCOMMIT = " + conn.getAutoCommit());
        conn.setAutoCommit(false);
        String deleteSongs = """
            DELETE FROM music.songs WHERE album_id =
            (SELECT ALBUM_ID from music.albums WHERE album_name = '%s')"""
                .formatted(albumName);

        //String deleteAlbums = "DELETE FROM music.albums WHERE album_name='%s'".formatted(albumName);
        String deleteAlbums = "DELETE FROM music.albums WHERE album_name='%s".formatted(albumName);
        
        String deleteArtist = "DELETE FROM music.artists WHERE artist_name='%s'".formatted(artistName);
        
        statement.addBatch(deleteSongs);
        statement.addBatch(deleteAlbums);
        statement.addBatch(deleteArtist);
        int[] results = statement.executeBatch();
        System.out.println(Arrays.toString(results));
        conn.commit();
    } catch (SQLException e) {
        e.printStackTrace();
        conn.rollback();
    }
    conn.setAutoCommit(true);
}
```

I'll run the code, first to insert the bob Dylan records: 

```html  
===================
ARTIST_ID      ARTIST_NAME    
INSERT INTO music.artists (artist_name) VALUES ('Bob Dylan')
INSERT INTO music.albums (album_name, artist_id) VALUES ('Bob Dylan', 207)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (1, 'You''re No Good', 886)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (2, 'Talkin'' New York', 886)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (3, 'In My Time of Dyin''', 886)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (4, 'Man of Constant Sorrow', 886)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (5, 'Fixin'' to Die', 886)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (6, 'Pretty Peggy-O', 886)
INSERT INTO music.songs (track_number, song_title, album_id) VALUES (7, 'Highway 51 Blues', 886)
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
```

Then to delete them:

```html  
===================
ARTIST_ID      ARTIST_NAME    
211            Bob Dylan      
AUTOCOMMIT = true
java.sql.BatchUpdateException: Cannot delete or update a parent row: a foreign key constraint fails (`music`.`albums`, CONSTRAINT `FK_ARTISTID` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`artist_id`))
	at com.mysql.cj.jdbc.exceptions.SQLError.createBatchUpdateException(SQLError.java:223)
	at com.mysql.cj.jdbc.StatementImpl.executeBatchInternal(StatementImpl.java:900)
	at com.mysql.cj.jdbc.StatementImpl.executeBatch(StatementImpl.java:802)
	at .MusicDML.deleteArtistAlbum()
	at .MusicDML.main()
Caused by: java.sql.SQLIntegrityConstraintViolationException: Cannot delete or update a parent row: a foreign key constraint fails (`music`.`albums`, CONSTRAINT `FK_ARTISTID` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`artist_id`))
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:118)
	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
	at com.mysql.cj.jdbc.StatementImpl.executeUpdateInternal(StatementImpl.java:1344)
	at com.mysql.cj.jdbc.StatementImpl.executeBatchInternal(StatementImpl.java:866)
	... 3 more
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
===================
ALBUM_ID       ALBUM_NAME     ARTIST_ID      
886            Bob Dylan      207  
```

So whether it's batch or not, I get an error 
and this code will roll back the changes as you can see, 
because the songs being listed here in the output.
But examine the error we're getting here.
It's not the error I was expecting which was an SQL Syntax Error, 
the error I got previously.
Instead, I'm getting a **foreign key constraint**.
This error would be thrown if the code was trying to delete an artist,
and a related album still exists.
Is this batch code, `int[] results = statement.executeBatch();`, 
actually executing all three statements?
Well, yes, it is.
That may seem like unexpected behavior, 
but each JDBC provider can choose how they want to handle batch statements.
The _MySQL ConnectorJ_ driver, by default, will execute every statement,
even if previous statements throw errors.

This can be controlled by a property on the driver.
I'll pull up the Driver's API information.
On this page, we have properties 
that are associated with the JDBC Statement class in the MySQL driver.
The second property is called continue Batch on Error, 
and the default value is set to true,
so the behavior we seem to be seeing corresponds to this documentation.
To change this, I can set a property with a Properties file, 
but since I'm not using a _Properties_ file, in this case, 
I'll simply set the value in the _url_ string.

```java  
public static void main(String[] args) {
    
    try (Connection connection = DriverManager.getConnection(
            //"jdbc:mysql://localhost:3335/music", 
            "jdbc:mysql://localhost:3335/music?continueBatchOnError=false", 
            System.getenv("MYSQL_USER"), 
            System.getenv("MYSQL_PASS")); 
         Statement statement = connection.createStatement();
         ) {

        String tableName = "music.artists";
        String columnName = "artist_name";
        String columnValue = "Bob Dylan";
        if (!executeSelect(statement, tableName, columnName, columnValue)) {
            insertArtistAlbum(statement, columnValue, columnValue);
        } else {
            //updateRecord(statement, tableName, columnName, columnValue, columnName, columnValue.toUpperCase());
            try {
                deleteArtistAlbum(connection, statement, columnValue, columnValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            executeSelect(statement, "music.albumview", "album_name", columnValue);
            executeSelect(statement, "music.albums", "album_name", columnValue);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

So in the _main_ method, where I have my jdbc connection string, 
I can add parameters, by first adding a question mark, 
after the schema name, and then provide a list of values.
Here I want the _continueBatchOnError_ field to be set to **false**, 
so I'll add that.
I'll rerun the code after this change.

```html  
===================
ARTIST_ID      ARTIST_NAME    
207            Bob Dylan      
AUTOCOMMIT = true
java.sql.BatchUpdateException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near ''Bob Dylan' at line 1
	at com.mysql.cj.jdbc.exceptions.SQLError.createBatchUpdateException(SQLError.java:223)
	at com.mysql.cj.jdbc.StatementImpl.executeBatchInternal(StatementImpl.java:900)
	at com.mysql.cj.jdbc.StatementImpl.executeBatch(StatementImpl.java:802)
	at .MusicDML.deleteArtistAlbum()
	at .MusicDML.main()
Caused by: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near ''Bob Dylan' at line 1
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:121)
	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
	at com.mysql.cj.jdbc.StatementImpl.executeUpdateInternal(StatementImpl.java:1344)
	at com.mysql.cj.jdbc.StatementImpl.executeBatchInternal(StatementImpl.java:866)
	... 3 more
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
===================
ALBUM_ID       ALBUM_NAME     ARTIST_ID      
886            Bob Dylan      207  
```

And again, I get an error, but this time, 
it's significant that it's the SQL syntax error.
Setting this property may or may not make sense 
depending on what you want to happen.
But if you're expecting these statements to be run as a single transaction, 
it makes sense to set this to false, 
and have the batch processing stop after the first failure.
I'll fix my error.
I'll run that again:

```html  
===================
ARTIST_ID      ARTIST_NAME    
207            Bob Dylan      
AUTOCOMMIT = true
[7, 1, 1]
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
===================
ALBUM_ID       ALBUM_NAME     ARTIST_ID   
```

Successfully deleting any data for _Bob Dylan_.

I want to reiterate a few key points about using _executeBatch_:

* Multiple SQL statements can be bundled together 
and sent to the database in one trip, 
reducing the overhead of multiple round-trips to the database.
* _executeBatch_ does not necessarily execute all statements sent 
if a failure occurs in one of the statements. 
This depends on the type of driver and how it's configured, 
as this example showed.
* It's still important to manage transactions 
with the use of commit and roll back when you use this method. 
* Batch processing is often used to improve the performance of inserting, 
updating, or deleting multiple rows in a database at once.
</div>

## [g. Creating a Database with SQL Exceptions]()
<div align="justify">

In the last several sections, I've been focusing on DML, or Data Manipulation Language, 
because in many ways it's easier to learn and understand.
In this section, I'll use the methods I've been covering, 
but apply them to Data Definition Language, or DDL.
I've created a new Main class.

```java  
public class Main {

    private static String USE_SCHEMA = "USE storefront";
    
    public static void main(String[] args) {
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setUser(System.getenv("MYSQLUSER"));
        dataSource.setPassword(System.getenv("MYSQLPASS"));
    }
}
```

I'll set up a static query string, 
so private, static, final, String, _USE_SCHEMA_,
and I'll set that to the literal text, _USE storefront_.
This statement is a DDL statement.
It sets the default database for the session.
I'll use this statement to test if I need to create the storefront database.
To do this, in the _main_ method, I'll set up a basic datasource.
Since I'm running in a standalone environment, 
I'll create a new instance, using the class name of the _MysqlDataSource_. 
I'll set the server name, localhost, and the port, `3335`. 
I'll set the user to be an environment variable, which I've done before. 
I'll do the same thing for the password.
Notice that here, I'm not specifying any schema or database name
when I'm setting up the data source.
That's because I plan to create it.
First, though, I'll set up a method to test if the database exists, 
so I'll create that.

```java  
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class Main {

    private static String USE_SCHEMA = "USE storefront";
    
    public static void main(String[] args) {
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setUser(System.getenv("MYSQLUSER"));
        dataSource.setPassword(System.getenv("MYSQLPASS"));
        
        try (Connection conn = dataSource.getConnection()) {
            
            if (!checkSchema(conn)) {
                System.out.println("storefront schema does not exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static boolean checkSchema(Connection conn) {
        
        try (Statement statement = conn.createStatement()) {
            statement.execute(USE_SCHEMA);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
```

I'll make this private and static, and return a boolean.
I'll call it checkSchema, and pass it an existing connection.
It's complaining about the import, I'll fix that in a bit. 
I'll create a new statement object in a _try-with-resources_. 
I'll execute the _USE_SCHEMA_ statement. 
If this throws an exception, I'll print the stack trace. 
And return **false** if I do get an exception. 
Otherwise, I'll return **true**.
If you get into a situation 
where IntelliJ doesn't show the import popup, 
do this to force the popup.
We want to choose `java.sqlConnection` here.
This should fix the import for **Statement** as well.
Scrolling up, you can see it.
Next, I'll call this method from the _main_ method.
I'll use a _try-with-resources_, 
to first get the connection from the data source,
with the _getConnection_ method. 
I'll execute _checkSchema_, as part of an _if_ condition. 
So if it returns **false**, 
I'll print that the _storefront_ schema does not exist.
AndI'll set up the usual _catch_ clause here.
I'll set up my environment variables
as part of the run configuration.
You've seen me do this several times by now, 
so I'll just do it real quickly.
So Run, Edit Configurations.
In the environment variables input field,
I'll set up _MYSQLUSER_, equal to _devUser_, 
then I need to follow that with a semicolon, not a comma.
Then I'll type _MYSQLPASS_ and set that to my password.
You'd put your own password there.
Now I can run this code:

```html  
java.sql.SQLSyntaxErrorException: Unknown database 'storefront'
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:121)
	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
	at com.mysql.cj.jdbc.StatementImpl.executeInternal(StatementImpl.java:770)
	at com.mysql.cj.jdbc.StatementImpl.execute(StatementImpl.java:653)
	at Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course04_CreatingDatabase.Main.checkSchema(Main.java:32)
	at Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course04_CreatingDatabase.Main.main(Main.java:21)
```

And sure enough, I get an unknown database, _storefront_.
Now, in truth, the code in the _checkSchema_ method is flawed, 
since it just assumes any exception thrown, 
means that the database doesn't exist.
A lot of problems could happen here,
that have nothing to do with whether this database exists or not.
The _SQLException_ comes with additional information we can examine.
This includes an SQL state String, derived from a list of codes, 
from one of two possible conventions used to standardize codes.
To figure out which convention the driver uses,
we can query a class called _DatabaseMetaData_,
which we can get from the connection.
Next, there's an integer error code, that's specific to each vendor.
These codes can often help determine, with more accuracy, 
the exact error that occurred.
Before I add any more code, I'll show you how 
to get information from the database metadata class.
This can be retrieved from the **connection** object, 
with a method called _getMetaData_.
I'll add this code as the first statement inside the _try_ block
I'll create a variable called _metaData_, 
assign it the value I get from the _getMetaData_ method.

```java  
public class Main {

    private static String USE_SCHEMA = "USE storefront";
    
    public static void main(String[] args) {
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setUser(System.getenv("MYSQLUSER"));
        dataSource.setPassword(System.getenv("MYSQLPASS"));
        
        try (Connection conn = dataSource.getConnection()) {
            
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println(metaData.getSQLStateType());
            if (!checkSchema(conn)) {
                System.out.println("storefront schema does not exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static boolean checkSchema(Connection conn) {
        
        try (Statement statement = conn.createStatement()) {
            statement.execute(USE_SCHEMA);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
```

I'll pause after I type `metadata.`.
I want you to see the many things you can learn about your database, from this instance.
If you're writing JDBC code, you really should try 
to be familiar with your driver's features, 
and this class and its methods will help you explore
the driver configuration and database specifics.
In this case, I want to execute _getSQLStateType_,
so I'll scroll down and select that.
I'll run the code again:

```html  
2
java.sql.SQLSyntaxErrorException: Unknown database 'storefront'
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:121)
	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
	at com.mysql.cj.jdbc.StatementImpl.executeInternal(StatementImpl.java:770)
	at com.mysql.cj.jdbc.StatementImpl.execute(StatementImpl.java:653)
	at .Main.checkSchema()
	at .Main.main()
storefront schema does not exist
```

Notice that a `2` is printed out, before the exception.
That's not immediately useful information.
I'll control click on the class **DatabaseMetaData**, and search for _SQLState_.

```java  
int sqlStateXOpen = 1;

/**
 *  A possible return value for the method
 * {@code DatabaseMetaData.getSQLStateType} which is used to indicate
 * whether the value returned by the method
 * {@code SQLException.getSQLState} is an SQLSTATE value.
 *
 * @since 1.6
 */
int sqlStateSQL = 2;

/**
 *  A possible return value for the method
 * {@code DatabaseMetaData.getSQLStateType} which is used to indicate
 * whether the value returned by the method
 * {@code SQLException.getSQLState} is an SQL99 SQLSTATE value.
 * <P>
 * <b>Note:</b>This constant remains only for compatibility reasons. Developers
 * should use the constant {@code sqlStateSQL} instead.
 *
 * @since 1.4
 */
int sqlStateSQL99 = sqlStateSQL;
```

Here you can see there's a couple of constant values,
and the _sqlstateSQL_ is set to 2.
This indicates this driver is using the latest SQL conventions,
and not the _sqlStateXOpen_ conventions.
I'll show you how to look codes up, using one of these conventions, in a minute.
First, I'll write some code to explore the error I got back.

```java  
public class Main {

    private static String USE_SCHEMA = "USE storefront";
    
    public static void main(String[] args) {
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setUser(System.getenv("MYSQLUSER"));
        dataSource.setPassword(System.getenv("MYSQLPASS"));
        
        try (Connection conn = dataSource.getConnection()) {
            
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println(metaData.getSQLStateType());
            if (!checkSchema(conn)) {
                System.out.println("storefront schema does not exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static boolean checkSchema(Connection conn) {
        
        try (Statement statement = conn.createStatement()) {
            statement.execute(USE_SCHEMA);
        } catch (SQLException e) {
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
```

I'll add this code after the _printStackTrace_, in the _checkSchema_ method.
I'll print the _SQLState_ first.
Then the _error code_, and finally the _message_.
Notice here, I'm using `System.err`, and not `System.out`,
which I don't think I've used before.
`System.err` is standard output for errors.
In our case, `System.err` and `System.out` both output to the console.
If you're working in a production client server environment, 
it's good practice to output errors to `System.err`.
These messages get displayed,
even if `System.out` messages get redirected to a file, 
or some other output stream.
Configuring standard streams to different output sources is more common,
in a client server environment.
I'll run this code again:

```html  
2
SQLState: 42000
Error Code: 1049
Message: Unknown database 'storefront'
java.sql.SQLSyntaxErrorException: Unknown database 'storefront'
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:121)
	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
	at com.mysql.cj.jdbc.StatementImpl.executeInternal(StatementImpl.java:770)
	at com.mysql.cj.jdbc.StatementImpl.execute(StatementImpl.java:653)
	at Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course04_CreatingDatabase.Main.checkSchema(Main.java:59)
	at Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course04_CreatingDatabase.Main.main(Main.java:36)
storefront schema does not exist
```

Now I've got `SQLState = 42000`.
And `Error Code = 1049`.
And the message is an unknown database storefront.
I'll go to a browser and type _wiki SQLSTATE_.
This brings up the wikipedia page for SQLState values.
Here, I'll search for 42000.
The message for this code is, _syntax error or access rule violation_.
Since these are standard codes, it's a good idea 
to try to use these if you can, versus the database vendor code.
For this scenario, though, that code is not super useful.
We don't really know if we have a syntax error, it's an access issue,
or the schema just doesn't exist.
Now let's look up the MySQL Error code.
I'll pull up the server error reference page on MySQL's documentation.
I'll search for 1049.
And here, you see the MySQL string code, 
and that's followed by the SQLState, 42000.
This is followed by a specific message, _unknown database_, 
which was printed in the exception we got.
Let's search for 42000 on this page.
You can see, there are many errors that fall into that state.
I'll change the code to test the more specific MySQL error code.

```java  
public class Main {

    private static String USE_SCHEMA = "USE storefront";
    
    private static int MYSQL_DB_NOT_FOUND = 1049;
    
    public static void main(String[] args) {
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setUser(System.getenv("MYSQLUSER"));
        dataSource.setPassword(System.getenv("MYSQLPASS"));
        
        try (Connection conn = dataSource.getConnection()) {
            
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println(metaData.getSQLStateType());
            if (!checkSchema(conn)) {
                System.out.println("storefront schema does not exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //private static boolean checkSchema(Connection conn) {
    private static boolean checkSchema(Connection conn) throws SQLException{
        
        try (Statement statement = conn.createStatement()) {
            statement.execute(USE_SCHEMA);
        } catch (SQLException e) {
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
          
            //return false;
            if (conn.getMetaData().getDatabaseProductName().equals("MySQL") && e.getErrorCode() == MYSQL_DB_NOT_FOUND) {
                return false;
            } else throw e;
        }
        return true;
    }
}
```

First, I'll set up a constant on my class.
I'll call this _MYSQL_DB_NOT_FOUND_, and assign that, 1049.
Next, in my exception clause, 
I'll test both the database product name, and the error code.
If my database product is _MySQL_, and the error code is _1049_, 
then I know for sure the schema doesn't exist in the _MySQL_ server.
I'll remove the return false statement,
and replace it with the following code.
I'll use _getMetaData_, and chain the _getDatabaseProductName_ to that, 
and compare that value to the literal string value, _MySQL_.
Additionally, I'll check if the error code equals _MYSQL_DB_NOT_FOUND_'s value.
If both these conditions are **true**, then I'll return **false**, 
meaning the _storefront_ database doesn't exist. 
Otherwise, I'll propagate the error out to the _main_ method.
This code doesn't compile though, because I need to catch or specify, 
so I'll include the throws _SQLException_ in the method declaration.
I'll just add that manually.
Let's run this again:

```html  
2
SQLState: 42000
Error Code: 1049
Message: Unknown database 'storefront'
java.sql.SQLSyntaxErrorException: Unknown database 'storefront'
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:121)
	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
	at com.mysql.cj.jdbc.StatementImpl.executeInternal(StatementImpl.java:770)
	at com.mysql.cj.jdbc.StatementImpl.execute(StatementImpl.java:653)
	at Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course04_CreatingDatabase.Main.checkSchema(Main.java:76)
	at Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course04_CreatingDatabase.Main.main(Main.java:39)
storefront schema does not exist
```

I get the same result, but now I have confidence 
that I know exactly what the problem is.
I wanted to spend a little extra time with _SQLExceptions_, 
because it's important to understand as much about the error as possible, 
that's returned from the database server.
Ok, so let's create the _storefront_ now.

```java  
public class Main {

    private static String USE_SCHEMA = "USE storefront";
    
    private static int MYSQL_DB_NOT_FOUND = 1049;
    
    public static void main(String[] args) {
        
        var dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setUser(System.getenv("MYSQLUSER"));
        dataSource.setPassword(System.getenv("MYSQLPASS"));
        
        try (Connection conn = dataSource.getConnection()) {
            
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println(metaData.getSQLStateType());
            if (!checkSchema(conn)) {
                System.out.println("storefront schema does not exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static boolean checkSchema(Connection conn) throws SQLException{
        
        try (Statement statement = conn.createStatement()) {
            statement.execute(USE_SCHEMA);
        } catch (SQLException e) {
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
          
            if (conn.getMetaData().getDatabaseProductName().equals("MySQL") && e.getErrorCode() == MYSQL_DB_NOT_FOUND) {
                return false;
            } else throw e;
        }
        return true;
    }
    
    private static void setUpSchema(Connection conn) throws SQLExceptipon {
        
        String createSchema = "CREATE SCHEMA storefront";
        
        String createOrder = """
                CREATE TABLE storefront.order (
                order_id int NOT NULL AUTO_INCREMENT,
                order_date DATETIME NOT NULL,
                PRIMARY KEY (order_id)
                )""";
        
        String createOrderDetails = """
                CREATE TABLE storefront.order_details (
                order_detail_id int NOT NULL AUTO_INCREMENT,
                item_description text,
                order_id int DEFAULT NULL,
                PRIMARY KEY (order_detail_id),
                KEY FK_ORDERID (order_id),
                CONSTRAINT FK_ORDERID FOREIGN KEY (order_id)
                REFERENCES storefront.order (order_id) ON DELETE CASCADE
                )""";
    }
}
```

I'll create a new method, that'll be private and static, returns void,
and I'll call it _setUpSchema_.
It will take a connection throwing an _SQLException_.
Remember that for MySQL, the term database and schema are used interchangeably, 
so the first statement I need is, `CREATE SCHEMA storefront`,
which will create the _storefront_ database when executed. 
My first table is the order table,
so I'll have a _createOrder_ string using a _text block_,
The DDL I need is, `CREATE TABLE storefront.order`, followed by an opening parentheses. 
That's followed by a list of columns and types.
My first column is _order_id_, an **int**, it can't be **null**, 
and it's going to auto increment.
Next, _order_date_, a datetime field, and again that should never be **null**. 
I need to specify the primary key, which is _order_id_. 
The closing parentheses end the column list, 
and that's all I need to create this table.
I'll add a second table that has a relationship to the first, named _createOrderDetails_.
This will be similar to the code for order.
So `CREATE TABLE storefront.order_details (`.
The _order_detail_id_, an **int**, can't be **null**, 
and gets auto incremented.
_Item_description_ is a **text** field. 
The third column is _order_id_.
This is the column that connects the two tables.
My primary key is _order_detail_id_. 
But there's another key, a foreign key. 
It's pretty common to prefix a foreign key with _FK_, 
so I'll call it _FK_ORDERID_. 
This foreign key is on the column, _order_id_. 
Next, I have to describe what's called a constraint. 
This describes both the relationship to the other table, 
and limitations or constraints on table processing.
So _order_id_ in this table references order_id in the parent table, `storefront.order`.
This time, I want a _delete to cascade_ to its child records.
This code will set up a parent child relationship between these two tables.
And importantly, they are treated like a single unit when I delete the parent.
This means, if I delete an order, the details will also get deleted.
Don't worry if you don't understand all of this SQL code.
My goal here is first to show you that whether you execute DML, or DDL, 
the process to do it in Java, is the same.
There is one significant difference I want to note, however.
You can roll back a few of the DDL statements, 
so it's less common to execute DDL in a transaction.
Whether you can execute DDL statements in a transaction is something 
that's specific to the database vendor.
The second goal of this exercise was to give you a schema for upcoming challenges.
Now, I have to add the code to execute these statements.

```java  
private static void setUpSchema(Connection conn) throws SQLException {
    
    String createSchema = "CREATE SCHEMA storefront";

    String createOrder = """
            CREATE TABLE storefront.order (
            order_id int NOT NULL AUTO_INCREMENT,
            order_date DATETIME NOT NULL,
            PRIMARY KEY (order_id)
            )""";
  
    String createOrderDetails = """
            CREATE TABLE storefront.order_details (
            order_detail_id int NOT NULL AUTO_INCREMENT,
            item_description text,
            order_id int DEFAULT NULL,
            PRIMARY KEY (order_detail_id),
            KEY FK_ORDERID (order_id),
            CONSTRAINT FK_ORDERID FOREIGN KEY (order_id)
            REFERENCES storefront.order (order_id) ON DELETE CASCADE
            )""";
    
    try (Statement statement = conn.createStatement()) {
        System.out.println("Creating storefront Database");
        statement.execute(createSchema);
        if (checkSchema(conn)) {
            statement.execute(createOrder);
            System.out.println("Successfully Created Order");
            statement.execute(createOrderDetails);
            System.out.println("Successfully Created Order Details");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

I'll use it a _try-with-resources_ again.
Creating a statement within that. 
I'll print that this is creating a _storefront_ database.
I'll call _execute_ on my _createSchema_ first.
I'll double-check that it really got created by calling _checkSchema_. 
If that's true, I'll create the _order_ table. 
I'll print that it was. 
Created.
I'll execute the creation of the _order details_ table next. 
And I'll again print that it was successfully created. 
Otherwise, I'll catch the exception and print it.
I'll add the call to this method in the _main_ method.

```java  
public static void main(String[] args) {
    
    
    var dataSource = new MysqlDataSource();
    dataSource.setServerName("localhost");
    dataSource.setPort(3335);
    dataSource.setUser(System.getenv("MYSQLUSER"));
    dataSource.setPassword(System.getenv("MYSQLPASS"));
  
    try (Connection conn = dataSource.getConnection()) {
        DatabaseMetaData metaData = conn.getMetaData();
        System.out.println(metaData.getSQLStateType());
        if (!checkSchema(conn)) {
            System.out.println("storefront schema does not exist");
            
            setUpSchema(conn);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
```

I'll put it in the _if_ statement, after the `system.out.println` statement.
I need to pass that the connection instance.
Before I run this, I'm going to first open a session, 
in MySQL WorkBench, for my devUser.
I'll use the workbench, to independently confirm the schema and tables got added.
Getting back to IntelliJ, I'll run my code:

```html  
2
SQLState: 42000
Error Code: 1049
Message: Unknown database 'storefront'
java.sql.SQLSyntaxErrorException: Unknown database 'storefront'
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:121)
	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
	at com.mysql.cj.jdbc.StatementImpl.executeInternal(StatementImpl.java:770)
	at com.mysql.cj.jdbc.StatementImpl.execute(StatementImpl.java:653)
	at Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course04_CreatingDatabase.Main.checkSchema(Main.java:88)
	at Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course04_CreatingDatabase.Main.main(Main.java:52)
storefront schema does not exist
Creating storefront Database
Successfully Created Order
Successfully Created Order Details
```

Everything seems to have worked fine.
I get that the application successfully created order, and order details, 
and that the storefront schema was created.
Jumping back to MySQL Workbench, I'll refresh the schema panel.
The refresh icon is on the right of the _SCHEMAS_ label.
When I click on that, I should now see the _storefront_ schema listed.
I'll expand that, and I'll expand _Tables_, 
and there I can see my two tables created by the Java code, 
_Order_ and _Order Details_.
If you have any problems at all with your own code, 
you can right-click on the _storefront_ schema in this tool,
and select drop schema, then try your java code again, 
to recreate the schema from scratch again.
</div>

## [h. JDBC Challenge](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course05_JDBCChallenge/README.md#jdbc-challenge)
<div align="justify">

In this challenge, you'll be using MySQL Workbench 
to verify that the changes you make in Java get persisted correctly.
There is one change you'll probably need to make, though, 
to the settings in MySQL Workbench to do this.
My SQL Workbench has something called connection caching,
This means data is cached and not refreshed immediately from persisted data.
I'll show you how to configure it, to make the refresh interval much shorter.
This will let you see the changes made in your app, 
reflected immediately in MySQL Workbench. 
Open MySQL Workbench to your development session.
Click on _Edit_, then _Preferences_.
This will bring up a dialog.
Select _SQL Editor_ on the left pane.
In the right panel, there's a section called 
_MySQL_ session, with three entries.

![image51](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image51.png?raw=true)

The second one is the read time out interval,
and the one you want to change.
Set this to 1 second.
Click ok, and now, as you're working on this challenge, 
you can jump over to SQL Workbench to test your results.

For your first JDBC Challenge,
I want you to 

* Write the Java code that inserts an _Order_, 
and at least two _Order_ details into your database. 
This should be done in a transaction. 
* Use MySQL Workbench to confirm your inserts worked. 
* Write Code that deletes an _Order_ and its _order details_.
* Again, use MySQL Workbench to confirm your order and its line items 
were actually deleted.

I've included the date time format that MySQL expects this field to be in.

```html  
Hint: The MySQL Date Time Format is: yyyy-MM-dd HH:mm:ss
```
</div>

## [i. Prepared Statement]()
<div align="justify">

So far, up until now, to run SQL on the server,
I've first constructed a static string.
This was done by either hard coding all values
to be used in the query, by string concatenation,
or using _formatted_ strings.
I've then called any one of several _execute_ methods, 
which in general follow the same process.
When the request is received by the _Database Server_:

* the **Query is Parsed** into operable code,
which means the SQL syntax is checked,
and tables and column names are verified. 
* **An Execution Plan is Created**, 
which means the database server analyzes the query, 
and works out an optimal way to execute the statement.

The result is a compiled statement.
Once the statement is compiled,
it's stored in a cache on the database server.
This means it can be reused without having 
to be recompiled each time it is executed.
Importantly, this compiled statement can also be stored 
as a special kind of JDBC statement.
A _PreparedStatement_ in JDBC is a precompiled SQL statement.
You might remember, when we were reviewing regular expressions, 
we could get a compiled regular expression by using the `Pattern.compile`.
In some ways, this is similar to pre-compiling an SQL statement.
In both cases, a **String** is passed, 
which needs to get interpreted, as some operation that will occur.
The string first needs to get parsed,
its syntax tested, and some optimizations may optionally be applied.
There is some overhead with this process,
so if you're using the statement multiple times,
it makes sense to precompile it.

A _PreparedStatement_ is used to execute the same statement multiple times,
with parameter value placeholders.
This can improve performance, as I just stated, 
since the database server doesn't need to parse and compile the statement 
each time it is executed.
A parameter in an SQL string passed to a _PreparedStatement_ 
is defined with a question mark as a placeholder, shown in this example.

![image54](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image54.png?raw=true)

Notice here that I'm not including single quotes around the question mark.
This is because when you use a prepared statement, 
the work of enclosing literals is determined by the type and done for us.
When you use the prepared statement, you'll pass the values at that time,
specifying their data types as well.
This means the values passed as data will never be interpreted as SQL code.
Because of this, and very importantly, 
PreparedStatements help prevent SQL injection attacks.

You can have multiple parameters in the SQL string, 
and they can be different types.
For example, album id in the **songs** table is a number.
Specifying placeholders is the same, regardless of the type of parameter,
as I show here, in this example.

![image55](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image55.png?raw=true)

Let's get back to some code, and set up a couple of prepared statements.
I've got a new Main class.
In this code, I'll again use a datasource.
In a server environment, you wouldn't be instantiating
a new instance of a known driver class like we'll do here,
but all other operations would be the same.

```java  
import com.mysql.cj.jdbc.MysqlDataSource;
public class Main {

    public static void main(String[] args) {
        
        var dataSource = new MysqlDataSource();
        
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setDatabaseName("music");
        try {
            dataSource.setContinueBatchOnError(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

I'll create a new source with my specific driver here, 
which gives me a basic data source.
You may or may not have to manually 
include an import statement for this, since IntelliJ may not. 
You should be able to hover over that class,
and select import class in most cases if the auto import doesn't work. 
I'll use the set methods on the data source, to set the server name, 
so localhost, the port, 3335, and the database name,
so I'll be using _music_ again for this section.
In a previous section, I added a property on the connection string, 
called _setContinueBatchOnError_, which I set to **false**.
Without setting this to **false**, 
my batched statements will all get executed,
instead of stopping at the first error.
I can use one of the set methods on my datasource to do this, 
so I'll call _setContinueBatchOnError_.
I need to wrap that with a _try-catch_,
so I'll use IntelliJ's help to do that.
I'll set up my username and password as environment variables,
which I've done multiple times.
So I'll select _Run_ from the menu, then _edit configurations_.
In the dialog, I'll set up the two variables as I did previously.

```java  
import com.mysql.cj.jdbc.MysqlDataSource;
public class Main {

    public static void main(String[] args) {
        
        var dataSource = new MysqlDataSource();
        
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setDatabaseName("music");
        try {
            dataSource.setContinueBatchOnError(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = dataSource.getConnection(
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS"));
        ) {
            String sql = "SELECT * FROM music.albumview where artist_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Elf");
            ResultSet resultSet = ps.executeQuery();
            printRecords(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean printRecords(ResultSet resultSet) throws SQLException {
        boolean foundData = false;
        var meta = resultSet.getMetaData();
        System.out.println("===================");

        for (int i = 1; i <= meta.getColumnCount(); i++) {
            System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
        }
        System.out.println();

        while (resultSet.next()) {
            for (int i = 1; i <= meta.getColumnCount(); i++) {
            System.out.printf("%-15s", resultSet.getString(i));
        }
        System.out.println();
        foundData = true;
        }
        return foundData;
    }
}
```

Ok, once we've done that, I'll get a connection next to my code.
I'll do this in a _try-with-resources_, and call _getConnection_ on datasource.
I'll pass the environment variables, for the username, and password.
I'll add the usual _catch_ clause.
Next I'll set up a simple select statement, as a string, 
which will get data from the `music.albumview`.
You've seen this before, but now, 
instead of using a formatted static string, 
or a concatenated string, for the artist name value,
I'll insert a question mark there. 
This is how you parameterize an SQL statement
when you plan to use it in a prepared statement.
I'll set up my prepared statement, which I'll call _ps_.
I'll call `connection.prepareStatement`, 
passing this method the SQL string variable.
Next, I can set the value of that parameter
by calling _setString_ on the prepared statement.
This method takes an index, and again, SQL starts at index 1. 
I'll pass _Elf_ as the value, the artist, to this statement. 
I can invoke _executeQuery_ on a prepared statement as well,
which will return a result set.
So note, I'm not calling the _enquoteLiteral_ method.
When replacing a placeholder in a prepared statement, 
you specify the type by calling the relevant _set_ method, 
_setString_ in this case.
The server will appropriately _enquoteLiterals_ as needed, based on that type.
Before I run this, I'll add a _printRecords_ method.
I had this method in the **MusicDML** class.
I'll just paste this into my class, and since I've discussed it previously, 
I won't re-hash it here.
Getting back to the _main_ method, I'll add a call to the _printRecords_,
after I execute the query.
Ok, as you can see, `PreparedStatement ps = connection.prepareStatement(sql);`
it's not too different from a **Statement**,
except when you get a _preparedStatement_,
you have to pass a sql statement to that method.
In most cases, this SQL statement is sent to the DBMS, 
when you call _prepareStatement_, and it gets compiled at that point.
This means the prepared statement object contains a precompiled statement instance.
That then gets passed to the server, when statements are executed.
The string gets passed to the _prepareStatement_ method,
can optionally contain parameters.
Parameters are specified using question marks in the query statement, 
as I showed you above, so these are placeholders for data.
I can set the parameter values by calling a _set_ method.
These placeholders are another important feature of the prepared statement.
They ensure that user input is always treated as data values, 
and never as executable SQL code.
This makes it difficult for attackers to inject malicious SQL statements.
In this case, I use _setString_, to set the data value.
If the data type is something else, 
there's a whole series of set methods to choose from,
like _setArray_, _setBlob_, and the usual ones, 
_setLong_, _setDouble_ and so forth.
I'll run this code:

```html  
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Carolina County BallELF            1              Carolina County Ball
Carolina County BallELF            2              L.A. 59        
Carolina County BallELF            3              Ain't It All Amusing
Carolina County BallELF            4              Happy          
Carolina County BallELF            5              Annie New Orleans
Carolina County BallELF            6              Rocking Chair Rock 'n Roll Blues
Carolina County BallELF            7              Rainbow        
Carolina County BallELF            8              Do The Same Thing
Carolina County BallELF            9              Blanche        
Trying To Burn The SunELF            1              Black Swampy Water
Trying To Burn The SunELF            2              Prentice Wood  
Trying To Burn The SunELF            3              When She Smiles
Trying To Burn The SunELF            4              Good Time Music
Trying To Burn The SunELF            5              Liberty Road   
Trying To Burn The SunELF            6              Shotgun Boogie 
Trying To Burn The SunELF            7              Wonderworld    
Trying To Burn The SunELF            8              Streetwalker 
```

And here I get the data for the artist _Elf_'s two albums.
Another important aspect of the prepared statement is its ability to be reused.
I'll next use _preparedStatements_ to again insert data into the **music** database.
This time I'll insert the data from a csv file.
You can find this file, named `NewAlbums.csv`.
I've included the file in my package folder.
I'll open this, so you can see what it contains.

```html  
Bob Dylan,Bob Dylan,1,You're No Good
Bob Dylan,Bob Dylan,2,Talkin' New York
Bob Dylan,Bob Dylan,3,In My Time of Dyin'
Bob Dylan,Bob Dylan,4,Man of Constant Sorrow
Bob Dylan,Bob Dylan,5,Fixin' to Die
Bob Dylan,Bob Dylan,6,Pretty Peggy-O
Bob Dylan,Bob Dylan,7,Highway 51 Blues
Bob Dylan,Bob Dylan,8,Gospel Plow
Bob Dylan,Bob Dylan,9,Baby Let Me Follow You Down
Bob Dylan,Bob Dylan,10,House of the Risin' Sun
Bob Dylan,Bob Dylan,11,Freight Train Blues
Bob Dylan,Bob Dylan,12,Song to Woody
Bob Dylan,Bob Dylan,13,See That My Grave Is Kept Clean
Bob Dylan,Blonde on Blonde,1,Rainy Day Women
Bob Dylan,Blonde on Blonde,2,Pledging My Time
Bob Dylan,Blonde on Blonde,3,Visions of Johanna
Bob Dylan,Blonde on Blonde,4,One of Us Must Know (Sooner or Later)
Bob Dylan,Blonde on Blonde,5,I Want You
Bob Dylan,Blonde on Blonde,6,Stuck Inside of Mobile with the Memphis Blues Again
Bob Dylan,Blonde on Blonde,7,Leopard-Skin Pill-Box Hat
Bob Dylan,Blonde on Blonde,8,Just Like a Woman
Bob Dylan,Blonde on Blonde,9,Most Likely You Go Your Way (And I'll Go Mine)
Bob Dylan,Blonde on Blonde,10,Temporary Like Achilles
Bob Dylan,Blonde on Blonde,11,Absolutely Sweet Marie
Bob Dylan,Blonde on Blonde,12,Fourth Time Around
Bob Dylan,Blonde on Blonde,13,Obviously Five Believers
Bob Dylan,Blonde on Blonde,14,Sad-Eyed Lady of the Lowlands
```

In this case, I've got two of _Bob Dylan_'s albums set up as a series of records.
This looks a lot like the album view.
Each record has the artist name, the album name, the track number, and the song title.
I'll start with the parameterized strings, as static strings.

```java  
private static String ARTIST_INSERT = "INSERT INTO music.artists (artist_name) VALUES (?)";
private static String ALBUM_INSERT = "INSERT INTO music.albums (artist_id, album_name) VALUES (?, ?)";
private static String SONG_INSERT = "INSERT INTO music.songs (album_id, track_number, song_title) VALUES (?, ?, ?)";
```

The first will be the `Artist insert` statement.
This is a simple _insert_ statement that inserts one parameterized value, the artist name. 
Next is the `Album insert` statement. 
The _album insert_ statement has two columns which need to be added, 
the artist id and the album name, so I'll set up two placeholders. 
Lastly, there's the `song insert` statement. 
Here, we've got 3 columns, and 3 placeholders,
for album id, track number, and song title.
Next, I'll create a method called _addArtist_.

```java  
private static int addArtist(PreparedStatement ps, Connection conn, String artistName) throws SQLException {

    int artistId = -1;
    ps.setString(1, artistName);
    int insertedCount = ps.executeUpdate();
    if (insertedCount > 0) {
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            artistId = generatedKeys.getInt(1);
            System.out.println("Auto-incremented ID: " + artistId);
        }
    }
    return artistId;
}
```

This will take a prepared statement, a connection, and an _artistName_.
It throws an _SQLException_.
I'll initialize the _artistId_ to `-1`. 
I'll set the first parameter to the _artistName_.
I'll then call _executeUpdate_, to get the insert count.
If the count is greater than zero, I'll get the generated key, which is the _artistId_. 
I get any generated keys by calling _getGeneratedKeys_ on the prepared statement. 
That returns a _resultSet_ containing the keys. 
I'll get the first key, since I'll only have one record. 
And I'll set id to the first field in the _resultSet_.
I'll print that out.
And I'll return the artist id from this method, 
because I'll need it when I insert the other records.
I'll copy that code, and make a copy directly below.

```java  
private static int addAlbum(PreparedStatement ps, Connection conn, int artistId, String albumName) 
        throws SQLException {
    
    int albumId = -1;
    ps.setInt(1, artistId);
    ps.setString(2, albumName);
    int insertedCount = ps.executeUpdate();
    if (insertedCount > 0) {
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
          albumId = generatedKeys.getInt(1);
          System.out.println("Auto-incremented ID: " + albumId);
        }
    }
    return albumId;
}
```

I'll change the name to _addAlbum_,
and insert an _artistId_ as the third argument.
I need to change _artistName_ to _albumName_, for the fourth argument.
In the `ps.setString` statement, I have to both change the parameter index, 
from _1_ to _2_, and change _artistName_ to _albumName_
Next, I'll change _albumId_ to _artistId_, in four instances.
So first, when I'm declaring the first variable.
Then in the if `generatedKeys.next` block, where I assign the generated key.
And I want to print _albumId_ in the statement after that.
Lastly, I'll return _albumId_, not _artistId_ from this method.
Finally, I need to include the _artistId_ as the first parameter 
that gets set on the prepared statement.
The first parameter in the sql is for _artistId_. 
This time, I'll use set int to do this, passing it _artistId_.
For the next method, _addSong_, I'll just type this one in.
This method starts the same way, so private, static and int.

```java  
private static int addSong(PreparedStatement ps, Connection conn, int albumId, int trackNo, String songTitle) 
        throws SQLException {

    int songId = -1;
    ps.setInt(1, albumId);
    ps.setInt(2, trackNo);
    ps.setString(3, songTitle);
    int insertedCount = ps.executeUpdate();
    if (insertedCount > 0) {
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
          songId = generatedKeys.getInt(1);
          System.out.println("Auto-incremented ID: " + songId);
        }
    }
    return songId;
}
```

I'll call it _addSong_, and the parameters are _PreparedStatement_, 
_connection_, _albumId_, _trackNo_, and _songTitle_.
This also throws an _SQLException_.
I'll set the initial value of the _songId_ to `-1`. 
The first parameter is the _albumId_, an **int**. 
The second parameter is another **int**, the track number. 
And then the song title, a **string**, as the third one. 
I'll call _executeUpdate_ on the prepared statement, 
and that returns the number of rows inserted.
If something was inserted, then, I'll retrieve the generated key, the _songId_. 
First, I'll call next on that _resultSet_. 
Then, the first column will contain the _songId_. 
I'll print that out.
Now I've got add methods for all three tables, related to an artist.
I need to create a method that loops through the records in the csv file, 
and then calls each of these methods appropriately.

I'm not creating the prepared statement in any of these methods 
because they may be called multiple times. 
Instead, I'm passing a prepared statement to each method.
To execute these, I'll create a method that will loop through 
the records in my csv file, determining when each needs to be called, 
and executing them.

```java  
private static void addDataFromFile(Connection conn) throws SQLException {

    List<String> records = null;
    try {
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course06_PreparedStatements/NewAlbums.csv";
        records = Files.readAllLines(Path.of(pathName));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
  
    String lastAlbum = null;
    String lastArtist = null;
    int artistId = -1;
    int albumId = -1;
    try (PreparedStatement psArtist = conn.prepareStatement(ARTIST_INSERT, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement psAlbum = conn.prepareStatement(ALBUM_INSERT, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement psSong = conn.prepareStatement(SONG_INSERT, Statement.RETURN_GENERATED_KEYS);
    ) {
        
    } catch (SQLException e) {
        conn.rollback();
        throw new RuntimeException(e);
    }
}
```

I'll call this method, _addDataFromFile_, 
and that'll take an open connection, and throw an _SQLException_.
I'll set a variable for the records in the file, so a list of string. 
I'll need a _try-catch_, 
because I'll be calling _readAllLines_ on the **Files** class. 
I'll pass it the path of my file, which is in the package directory. 
And it's named `NewAlbums.csv`. 
If an _IOException_ is thrown, I'll throw a runtime exception instead.
After this, I'll have a list of records, which are comma-delimited.
I'll set up some local variables next.
I'll initialize _lastAlbum_ to **null**, and the same
with a variable named _lastArtist_.
I'll check these variables to determine 
if a new album or new artist is indicated in the file.
The file has grouped the artist, there's only one, _Bob Dylan_, 
and it's grouped the 2 album's song titles.
I'll initialize the _artistId_ to `-1`.
And the same for _albumId_. 
Next, I'll set up a _try-with-resources_ block, and in the _try_ clause,
I'll get prepared statements for each of my insert queries. 
The first I'll call _psArtist_. 
Because I want the generated key back, 
I can pass the _RETURN_GENERATED_KEYS_ to the _prepareStatement_ method, as a second argument. 
Next, I've got _psAlbum_, for the album insert statement, 
and again I'll pass that constant. 
The last prepared statement is for the insert song code, 
and again I'll want the auto generated key back.
I'll leave the _try_ block empty for the moment.
I'll complete the _try with a catch_ clause.
Here, I'll call the rollback method on a connection 
if something throws an error. 
And I'll propagate the error up, but as a runtime exception. 

```java  
private static void addDataFromFile(Connection conn) throws SQLException {

    List<String> records = null;
    try {
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course06_PreparedStatements/NewAlbums.csv";
        records = Files.readAllLines(Path.of(pathName));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
  
    String lastAlbum = null;
    String lastArtist = null;
    int artistId = -1;
    int albumId = -1;
    try (PreparedStatement psArtist = conn.prepareStatement(ARTIST_INSERT, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement psAlbum = conn.prepareStatement(ALBUM_INSERT, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement psSong = conn.prepareStatement(SONG_INSERT, Statement.RETURN_GENERATED_KEYS);
    ) {
        conn.setAutoCommit(false);
        for (String record : records) {
            String[] columns = record.split(",");
            if (lastArtist == null || !lastArtist.equals(columns[0])) {
                lastArtist = columns[0];
                artistId = addArtist(psArtist, conn, lastArtist);
            }
            if (lastAlbum == null || !lastAlbum.equals(columns[1])) {
                lastAlbum = columns[1];
                albumId = addAlbum(psAlbum, conn, artistId, lastAlbum);
            }
            addSong(psSong, conn, albumId, Integer.parseInt(columns[2]), columns[3]);
        }

        conn.commit();
        conn.setAutoCommit(true);
    } catch (SQLException e) {
        conn.rollback();
        throw new RuntimeException(e);
    }
}
```

In the _try_ clause, the first thing I want to do is turn off autocommit.
I do that by passing **false**, to the set _AutoCommit_ method on connection. 
I'll loop through the records I got from the file.
I'll split each record into columns, using the comma as a delimiter. 
I only want to insert an artist once,
so I'll check if the artist name has changed or if it's **null**. 
If either of these conditions was met, 
then I'll set the _lastArtist_ to the value in the first column, 
which is the artist name in my file.
I'm assuming here that these are all new artists for simplicity's sake. 
And I'll execute my method, _addArtist_, passing it my prepared Statement, 
the connection and the artist name. 
I'll do the same thing for album name, so it will only get inserted once 
when the value of _lastAlbum_ changes. 
Album name is in the second column, in my file. 
Then I'll call my _addAlbum_ method, 
passing the album prepared statement, the connection, 
the _artistId_ I got when I called _addArtist_, 
and the _lastAlbum_, which should have the value of the new album to be added. 
This method returns the _albumId_. 
Finally, each record represents a song on the album.
So I'll call _addSong_ for every record,
passing the prepared statement for the song SQL, 
the connection, the _albumId_ I got back from adding the last album. 
The last two arguments are the track number, and song title,
which are my third and fourth columns in the csv file. 
If all of these statements executed successfully, I'll call _commit_.
And I'll set _autocommit_ back to **true**.
Finally, I'll just call this last method from the _main_ method.

```java  
public static void main(String[] args) {
    
    var dataSource = new MysqlDataSource();

    dataSource.setServerName("localhost");
    dataSource.setPort(3335);
    dataSource.setDatabaseName("music");
    try {
        dataSource.setContinueBatchOnError(false);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
  
    try (Connection connection = dataSource.getConnection(
            System.getenv("MYSQL_USER"),
            System.getenv("MYSQL_PASS"));
    ) {
        addDataFromFile(connection);
        
        String sql = "SELECT * FROM music.albumview where artist_name = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        //ps.setString(1, "Elf");
        ps.setString(1, "Bob Dylan");
        ResultSet resultSet = ps.executeQuery();
        printRecords(resultSet);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

I'll call this method first, passing it the current connection. 
I want to see the data we get back from the view 
after the execution of this method, so that's why I put it here,
before the select code below.
I'll update the parameter for the prepared statement I use to select data,
making that _Bob Dylan_ instead of _Elf_.
I'll run this code and see what happens.

```html  
Auto-incremented ID: 212
Auto-incremented ID: 887
Auto-incremented ID: 5407
Auto-incremented ID: 5408
Auto-incremented ID: 5409
Auto-incremented ID: 5410
Auto-incremented ID: 5411
Auto-incremented ID: 5412
Auto-incremented ID: 5413
Auto-incremented ID: 5414
Auto-incremented ID: 5415
Auto-incremented ID: 5416
Auto-incremented ID: 5417
Auto-incremented ID: 5418
Auto-incremented ID: 5419
Auto-incremented ID: 888
Auto-incremented ID: 5420
Auto-incremented ID: 5421
Auto-incremented ID: 5422
Auto-incremented ID: 5423
Auto-incremented ID: 5424
Auto-incremented ID: 5425
Auto-incremented ID: 5426
Auto-incremented ID: 5427
Auto-incremented ID: 5428
Auto-incremented ID: 5429
Auto-incremented ID: 5430
Auto-incremented ID: 5431
Auto-incremented ID: 5432
Auto-incremented ID: 5433
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Blonde on BlondeBob Dylan      1              Rainy Day Women
Blonde on BlondeBob Dylan      2              Pledging My Time
Blonde on BlondeBob Dylan      3              Visions of Johanna
Blonde on BlondeBob Dylan      4              One of Us Must Know (Sooner or Later)
Blonde on BlondeBob Dylan      5              I Want You     
Blonde on BlondeBob Dylan      6              Stuck Inside of Mobile with the Memphis Blues Again
Blonde on BlondeBob Dylan      7              Leopard-Skin Pill-Box Hat
Blonde on BlondeBob Dylan      8              Just Like a Woman
Blonde on BlondeBob Dylan      9              Most Likely You Go Your Way (And I'll Go Mine)
Blonde on BlondeBob Dylan      10             Temporary Like Achilles
Blonde on BlondeBob Dylan      11             Absolutely Sweet Marie
Blonde on BlondeBob Dylan      12             Fourth Time Around
Blonde on BlondeBob Dylan      13             Obviously Five Believers
Blonde on BlondeBob Dylan      14             Sad-Eyed Lady of the Lowlands
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
Bob Dylan      Bob Dylan      8              Gospel Plow    
Bob Dylan      Bob Dylan      9              Baby Let Me Follow You Down
Bob Dylan      Bob Dylan      10             House of the Risin' Sun
Bob Dylan      Bob Dylan      11             Freight Train Blues
Bob Dylan      Bob Dylan      12             Song to Woody  
Bob Dylan      Bob Dylan      13             See That My Grave Is Kept Clean
```

First, I can see a series of statements with IDs printed,
these don't include the type, but I can guess, by their order.
At the start, there's the artist, then the album,
then followed by a series of song inserts.
You can see the consecutive numbers listed for the song inserts.
And after this code runs, you can see all the data
from the select from the albumview was printed.
Even though I'm not using batch processing here, 
I've still been able to take advantage of the prepared statement's precompiled feature.
This means the execution of each of these queries should be much faster than 
if I had just used a statement.
But I'm still making a round trip to the database server, 
so about 30 round trips in this case.
Let's add batch processing to this code.
Because I need to retrieve the automatically
generated artist id and album id,
I can't batch these statements.
But I can batch up the song insert records, 
for a little more efficiency.
Before I do this, I'll open up SQL Workbench, 
and change the album and songs tables, to allow cascade deletes, 
and then we'll delete all this data for _Bob Dylan_ before we re-run.
So open My SQL Workbench for your dev user.

![image56](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image56.png?raw=true)

Make sure you have the _schemas_ tab open in the left panel, and highlight albums.
Click the tool or wrench icon to the right.
Look for a series of tabs below the form.
Select the _Foreign Keys_ tab.
On this screen, highlight _FK ARTIST ID_.
This lists the columns and any referenced column, 
so you can see artist id does have a referenced column.
On the furthest right panel, are some foreign key options.
You can see that on _Delete_, it's set to _restrict_.
I'll change this to _CASCADE_ from the drop-down.
I'll select the _Apply_ button.
This will display the DDL Script 
that gets run to effect this change.

![image57](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image57.png?raw=true)

I'll select _APPLY_ next.
I should get a message that the SQL script
was successfully applied to the database.

![image58](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image58.png?raw=true)

I'll select _Finish_, and I'll go through the same series of steps 
for the _songs_ table.
So I'll select songs, and click the wrench icon.
That pops up a tab, and I'll select foreign keys again.
I'll highlight _FK ALBUM ID_ this time.
I'll change the value of the On _Delete_ option 
from _RESTRICT_ to _CASCADE_.
I'll again hit the _Apply_ button.
I'll hit the next _apply_ button.
And finally, the _finish_ button.
To test this, I'll open a query tab.

![image59](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image59.png?raw=true)

This is the first icon under the top menu listing.
First, let's query the view for _Bob Dylan_ data.
Executing this statement with the lightning bolt,
I should get the same information my java code listed in the last execution.
Now I'll delete the artist _Bob Dylan_.
If I set my cursor directly before the word delete, I can run just this statement 
by selecting the lightning bolt icon, with an eye over the top of it.

![image61](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image61.png?raw=true)

You can see at the bottom of the screen I get `Error Code 1175`.
You are using _Safe_ mode.
This is a built-in MySQL Workbench feature 
that prevents you doing an entire table deletion.
It's a useful feature to prevent a potential disaster.
You often do not want to delete an entire table contents, after all.
But here, we do want to do that.
I need to go into preferences and turn off this feature.
So I'll click _Edit_ and select _Preferences_.
I'll then click SQL Editor.

![image62](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image62.png?raw=true)

And turn off this safeguard by unchecking the _Safe Updates box_.
Note that it protects both deletes and updates of an entire table. 
Which happens when we do not filter the SQL Statement in some way. 
As is the case, here. 
I'll click _OK_.
I'll re-run the sql delete code again, and I get the same error.
I'll try a refresh, but I think I have to restart MySQL Workbench 
for this change to take effect.
I'll re-execute the select again, just to see if that works.
I'm still getting the same error, so I'll close down and re-start MySQL Workbench.
Right, so let's try the select and delete again.

![image63](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image63.png?raw=true)

Success, finally. We don't get any errors.
Without the _cascade delete_, I would have gotten errors trying to do this.
I'll set my cursor before the select keyword,
and again click the _execute_ inline icon.
This executes the select against the view, and now I have no data.

![image64](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image64.png?raw=true),

So my single delete statement, deleted two related albums, 
as well as 27 related songs.
I'll jump back to IntelliJ, and I'll make some changes to this code,
to support batching up all the song records.
I'll start with the _addSong_ method.

```java  
//private static int addSong(PreparedStatement ps, Connection conn, int albumId, int trackNo, String songTitle) 
private static void addSong(PreparedStatement ps, Connection conn, int albumId, int trackNo, String songTitle) 
        throws SQLException {

    //int songId = -1;
    ps.setInt(1, albumId);
    ps.setInt(2, trackNo);
    ps.setString(3, songTitle);
    
/*
    int insertedCount = ps.executeUpdate();
    if (insertedCount > 0) {
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
          songId = generatedKeys.getInt(1);
          System.out.println("Auto-incremented ID: " + songId);
        }
    }
    return songId;
*/
    
    ps.addBatch();
}
```

First, I'll make the return type **void**, and not **int**.
Then I'll remove the first statement, where I initialize _songID_.
I'll remove the entire if statement, and all the statements in there.
I don't need to get the *songID*s for my code, so I'll remove all these.
Finally, I'll remove the return statement.
Next, I'll remove the _executeUpdate_ method call,
since we are batching the addition of the songs.
I'll execute add Batch on the prepared statement.
Again, add batch won't execute anything 
it just populates a list of statements that will be run,
when the executeBatch method gets called.
I'll go back to the _addDataFromFile_ method,
and scroll down, to just before
the commit statement I make.

```java  
private static void addDataFromFile(Connection conn) throws SQLException {

    List<String> records = null;
    try {
        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course06_PreparedStatements/NewAlbums.csv";
        records = Files.readAllLines(Path.of(pathName));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
  
    String lastAlbum = null;
    String lastArtist = null;
    int artistId = -1;
    int albumId = -1;
  
    try (PreparedStatement psArtist = conn.prepareStatement(ARTIST_INSERT, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement psAlbum = conn.prepareStatement(ALBUM_INSERT, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement psSong = conn.prepareStatement(SONG_INSERT, Statement.RETURN_GENERATED_KEYS);
    ) {
        conn.setAutoCommit(false);
    
        for (String record : records) {
            String[] columns = record.split(",");
            if (lastArtist == null || !lastArtist.equals(columns[0])) {
                lastArtist = columns[0];
                artistId = addArtist(psArtist, conn, lastArtist);
            }
            if (lastAlbum == null || !lastAlbum.equals(columns[1])) {
                lastAlbum = columns[1];
                albumId = addAlbum(psAlbum, conn, artistId, lastAlbum);
            }
            addSong(psSong, conn, albumId, Integer.parseInt(columns[2]), columns[3]);
        }

        int[] inserts = psSong.executeBatch();
        int totalInserts = Arrays.stream(inserts).sum();
        System.out.printf("%d song records added %n", inserts.length);
        
        conn.commit();
        conn.setAutoCommit(true);
    } catch (SQLException e) {
        conn.rollback();
        throw new RuntimeException(e);
    }
}
```

Here, I'll be executing all the queued up statements 
on the _psSong_ **prepareStatement**.
Each statement returns the number of records effected,
returned as an **int** array there.
I'll set up an **int** array variable called _inserts_, 
and then _executeBatch_. 
If everything worked perfectly, 
the _inserts_ length should be the number of records inserted,
but it's possible a count could be zero if something went wrong. 
I'll create a _totalInserts_ variable. 
I'll set up a mini stream here, with `Arrays.stream`, 
passing it the _inserts_ array and then terminating with the _sum_ operation.
This quickly sums all this data.
I'll print this information out.
And I'll run this again.

```html  
Auto-incremented ID: 213
Auto-incremented ID: 889
Auto-incremented ID: 890
27 song records added 
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Blonde on BlondeBob Dylan      1              Rainy Day Women
Blonde on BlondeBob Dylan      2              Pledging My Time
Blonde on BlondeBob Dylan      3              Visions of Johanna
Blonde on BlondeBob Dylan      4              One of Us Must Know (Sooner or Later)
Blonde on BlondeBob Dylan      5              I Want You     
Blonde on BlondeBob Dylan      6              Stuck Inside of Mobile with the Memphis Blues Again
Blonde on BlondeBob Dylan      7              Leopard-Skin Pill-Box Hat
Blonde on BlondeBob Dylan      8              Just Like a Woman
Blonde on BlondeBob Dylan      9              Most Likely You Go Your Way (And I'll Go Mine)
Blonde on BlondeBob Dylan      10             Temporary Like Achilles
Blonde on BlondeBob Dylan      11             Absolutely Sweet Marie
Blonde on BlondeBob Dylan      12             Fourth Time Around
Blonde on BlondeBob Dylan      13             Obviously Five Believers
Blonde on BlondeBob Dylan      14             Sad-Eyed Lady of the Lowlands
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
Bob Dylan      Bob Dylan      8              Gospel Plow    
Bob Dylan      Bob Dylan      9              Baby Let Me Follow You Down
Bob Dylan      Bob Dylan      10             House of the Risin' Sun
Bob Dylan      Bob Dylan      11             Freight Train Blues
Bob Dylan      Bob Dylan      12             Song to Woody  
Bob Dylan      Bob Dylan      13             See That My Grave Is Kept Clean
```

My first three statements of output are the artist id, 
and the `2` new albumIDs.
Then I get the statement that 27 songs were inserted.
This was done with one trip to the database server.
And then I can see from the view, the records are all there.
Anytime you're executing a series of statements, 
whose only difference is column values, 
as was the case in this code, you should use a prepared statement.
The _PreparedStatement_ has many advantages over the Statement.

* **Pre-compilation** involves parsing, optimizing, 
and storing the SQL statement in a format 
that can be efficiently executed by the database server. 
This process occurs only once, making subsequent executions faster.
* **Parameterized Queries** are supported with placeholders. 
These are identified as question marks in the SQL string.
They're used for dynamic data values,
and get replaced with actual values at runtime.
These help prevent SQL injection attacks, by separating SQL code, from user input.
* Another advantage is **Efficient Reuse**.
A PreparedStatement can be used multiple times, 
executing the same or similar SQL statements with different parameter values. 
This results in improved performance. 
* With parameterized queries, we get **Automatic Type Conversion**. 
This means a **PreparedStatement** handles the conversion,
between Java and SQL data types, ensuring data compatibility. 
* Using prepared statements help with **Readability and Maintainability**. 
Code that uses PreparedStatements is easier to read and understand, 
than looking at a series of concatenated SQL strings with user input, for example. 
* A PreparedStatement also provides **Type Safety** 
when binding parameters to SQL statements.
This helps avoid data type mismatches, that lead to runtime errors or data corruption.

With all of that being said, our insert code still has a couple of major flaws, 
because of the very nature of having to execute up to three insert statements 
for a single song on an album.
I wasn't able to batch the entire process.
So if I had 1000 new albums and 1000 new artists, 
I'd still be doing 2000 round trips to the database.
If a problem occurred inserting the second album, 
none of the song titles for the first album would have been inserted
because of the way I coded this.
In the next section, I'll demonstrate how to address some of these problems,
with something called a stored procedure.
You can think of this as a method on the database server, we can access.
But before I get into that, I've got another challenge for you.
</div>

## [j. Prepared Statement Challenge](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course07_PreparedStatementChallenge/README.md#preparedstatement-challenge)
<div align="justify">

It's now your turn to apply the skills you just learned, about DDL, 
PreparedStatements, transactions, and batch processing.
You can start with the code in the previous section,
but create a new class for this code.
First, I want you to 

* Change the structure of the _order_details_ table, 
adding a new column called quantity, a number.
I'm purposely not telling you which DDL command to use, 
since this should be easy to look up.
Once you've made this change to your database, 
you'll proceed with adding data.
* Use data from `Orders.csv` to add orders to your _storefront_ database.
I've included this file in the package folder, to use as input.
This file contains information for `5` different orders.
The format of this file is different from the last section, 
so be sure to study it a little bit, so you know how to read it.
* Use PreparedStatements to insert each order and its related items. 
* Batch up the line items for each order, but batch only line items for a single order.
* Use a transaction for each individual order, rolling back the order insert 
if something fails, but allowing other orders to be inserted.
</div>

## [k. Callable Statement]()
<div align="justify">

There are many challenges and concerns when you're working with databases via JDBC.
I've listed many of them here.

* Performance Tuning
* Transaction Management
* Resource Leaks
* Data Type Mapping
* Concurrency Control
* Scalability
* Testing and Integration
* Vendor Lock-In
* SQL Injection
* Monitoring and Logging

This can be a daunting list for a Java developer whose not an expert in databases.
If you're working in an environment using an enterprise database, 
you've probably got people on your team, 
who have a lot more knowledge about many of these issues than you do,
or can hope to learn in a short time.
For this reason, the best place to deal with many of these issues 
is on the database server itself.
If you've got _Database Administrators or DBAs_, and SQL Developers, 
your job will get a lot easier.
They'll tackle many of these issues with database server features, 
like indices, constraints, triggers, and stored procedures.
They'll puzzle over explain plans for performance issues, 
and write all kinds of monitoring and logging code.
They're often experts in tuning queries,
so who better to write your queries than this impressive group of people.
Even if you don't have access to this talent pool. 
You should consider addressing 
at least some of the problems listed above with server solutions.
This is often done with functions and routines written in SQL, 
or transactional or procedural languages, that integrate easily with SQL.
These items are often called **stored procedures** as a category, 
though there are two separate database artifacts, 
the procedure and the function.

**Stored procedures** are a way to encapsulate complex SQL logic, 
and data manipulation, into reusable modules
that can be called from within the database.
They can help to improve application performance, modularity, and security.
They're precompiled queries, or segments of code,
that are stored as just another database object.
Your JDBC code can be simplified quite a bit 
if you're able to take advantage of stored procedures.
A stored procedure can also be written externally, in Java, Python, or c, 
and stored with a wrapping procedure in the database,
which means that code will run on the server.
I won't be covering this last bit, 
but you should know that some databases, like SQLLite and H2, 
only support these external types of stored procedures.
I'll be covering functions in a later section,
and will explain the differences later.

It's widespread in an enterprise environment,
to protect access to the database, 
and to restrict what your clients are allowed to do.
Because of this, you might find that
most of your JDBC work will be done with requests to stored procedures.
Java supports the execution and retrieval of results 
from these code segments, with a special kind of statement, 
named the **CallableStatement**.
Like a **PreparedStatement**, 
this first takes a parameterized string when you get an instance.
The object referenced will already be compiled in the database server.
Like a method in Java, you pass in data with parameters, 
and you may or may not get data back.
Depending on your database, 
the data coming back might be a result returned from a function, 
or it could be returned with declared output parameters.
Some databases also support a hybrid parameter,
an input output parameter, 
which lets you pass data which may be modified by the procedure, and passed back.
I'll explain these parameters a bit more later,
as we explore how to use the _CallableStatement_.
First, we need access to one.

~~~~sql  
USE `music`;
DROP procedure IF EXISTS `addAlbum`;

USE `music`;
DROP procedure IF EXISTS `music`.`addAlbum`;

DELIMITER $$
USE `music`$$
CREATE DEFINER=`devuser`@`localhost` PROCEDURE `addAlbum`(artistName TEXT, albumName TEXT, songTitles JSON)
BEGIN
	DECLARE val_artist_id INT DEFAULT NULL;
    DECLARE val_album_id INT DEFAULT NULL;
    DECLARE i INT DEFAULT 0;
    DECLARE num_items INT;
    DECLARE song_title VARCHAR(255);
    SET num_items = JSON_LENGTH(songTitles);
    
	SELECT artist_id INTO val_artist_id FROM artists
		WHERE  artist_name = artistName;

	START TRANSACTION;
	IF val_artist_id IS NULL THEN
        -- Insert a new order
        INSERT INTO artists (artist_name) VALUES (artistName);
        -- Get artist_id of last artist inserted
		SELECT LAST_INSERT_ID() INTO val_artist_id;
    END IF;

	SELECT album_id INTO val_album_id FROM albums
		WHERE album_name = albumName AND artist_id = val_artist_id;
        
    IF val_album_id IS NULL THEN
		-- Insert a new album
		INSERT INTO albums (artist_id, album_name) VALUES (val_artist_id, albumName);
		-- Get album_id of last artist inserted
		SELECT LAST_INSERT_ID() INTO val_album_id;
        
        -- Loop through the JSON Song Titles Array
		WHILE i < num_items DO
			-- JSON functions extract the right element, and unquote it
			SET song_title = JSON_UNQUOTE(JSON_EXTRACT(songTitles, CONCAT('$[', i, ']')));	
            -- Insert a new song, track number is assigned here.
			INSERT INTO songs (album_id, track_number, song_title)
				VALUES (val_album_id, i + 1, song_title); 
			SET i = i + 1;
            
		END WHILE;
	END IF;    
    COMMIT;
END$$

DELIMITER ;
~~~~

I've included a DDL script in the package folder of this section, 
named `addAlbumSongs.SQL`.
You should download that script and put it somewhere on your local file system,
where you can access it easily.
Next, open MySQL Workbench to your _development_ connection.

![image68](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image68.png?raw=true)

In this case, you'll select the second SQL Editor icon,
which will prompt you to open an SQL script.
Find the script you downloaded, and select it, and hit the open button.
That should load this script in your edit panel.

![image69](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image69.png?raw=true)

Now, you want to execute this script, 
so the first lightning bolt icon there will do it.
If you refresh the schema panel, 
you should confirm that you see this new procedure, 
named _addAlbum_, under the _Stored Procedures_ listing.
Click on that, and select the wrench or tool icon there.

![image70](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image70.png?raw=true)

This will load up the stored procedure's code.
I won't get into this code too much,
but what you do need to know is what the parameters are to this routine.
There's three of them, an _artistName_, a **text** field, 
an _albumName_, also **text**, and the last is _songTitles_, a **json** type.
MySQL stored procedures don't currently support **arrays**,
but they do support **json** objects, which can include **arrays**.
In this case, this is a way to pass an array of **text**, 
which will be an array of _songTitles_ to this stored procedure.

~~~~sql  
IF val_artist_id IS NULL THEN
    -- Insert a new order
    INSERT INTO artists (artist_name) VALUES (artistName);
    -- Get artist_id of last artist inserted
    SELECT LAST_INSERT_ID() INTO val_artist_id;
END IF;
~~~~  

This code will add the artist if it's not in the database, 
or get the artist id if it is.

~~~~sql  
IF val_album_id IS NULL THEN
    -- Insert a new album
    INSERT INTO albums (artist_id, album_name) VALUES (val_artist_id, albumName);
    -- Get album_id of last artist inserted
    SELECT LAST_INSERT_ID() INTO val_album_id;
    
    -- Loop through the JSON Song Titles Array
    WHILE i < num_items DO
        -- JSON functions extract the right element, and unquote it
        SET song_title = JSON_UNQUOTE(JSON_EXTRACT(songTitles, CONCAT('$[', i, ']')));
        -- Insert a new song, track number is assigned here.
        INSERT INTO songs (album_id, track_number, song_title)
        VALUES (val_album_id, i + 1, song_title);
        SET i = i + 1;
    
    END WHILE;
END IF;
~~~~  

It'll then add the album if it's not there,
then inserts all the songs associated with that album, 
looping through the _songs_ parameter,
using MySQL JSON functions.

All three of these parameters, 
_artistName_, _albumName_, _songTitles_, are input parameters.
How do I know?
If you don't see one of the keywords, _in_, _out_, or _in-out_, 
then the parameter is by default an input parameter, 
so the keyword _in_ is implied.
This is important to know, for the callable statement.
Before we get back to IntelliJ,
let's make sure we've removed any _Bob Dylan_ records in the music data set.
I'll open another SQL edit panel, and type in a delete statement.

~~~~sql  
delete from music.artists where artist_name='Bob Dylan';
~~~~

In this case, I'm using all lowercases for the SQL keywords as I type it in,
since this is just an adhoc query.
I told you to use uppercase for SQL keywords,
and I know I haven't always been consistent,
and will sometimes forget to do it myself.

![image71](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image71.png?raw=true)

There's a beautify reformat option in this _SQL Edit_ panel, 
and that's the little broom icon.
I'll click that.

![image72](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image72.png?raw=true)

I don't always like the way it reformats it, 
but it does change the keywords to be uppercase.
Sometimes I just want my query to be on a single line and quickly execute
it, so I may not beautify it.
But if I am using the code in a JDBC statement, or a stored procedure,
I do try to be more disciplined at any rate. 
I'll execute this statement.
I'll execute that, so that the music database won't have any data for _Bob Dylan_,
when we first execute this procedure.
You'll hopefully remember that we added _cascade deletes_ on the albums and songs, 
so this single statement will clean up all that related data.
Switching back to IntelliJ, I'll create a new class,
which I'll call **MusicCallableStatement**, and it'll have a _main_ method.

```java  
public class MusicCallableStatement {

    private static final int ARTIST_COLUMN = 0;
    private static final int ALBUM_COLUMN = 1;
    private static final int SONG_COLUMN = 3;

    public static void main(String[] args) {

    }
}
```

I'll start by setting up a couple of constants.
These constants just represent the column index of the data in the csv file.
So the artist name is in the first column, so `ARTIST_COLUMN = 0`.
The Album's in the next column, index `1`.
The song title's in column 4, so index `3` here.
I'm not going to use track number in this case.
The stored procedure will assign the track number in order, 
based on the order of the songs.
I'll be setting this up as a **Map**, 
keyed by **string**, with a nested **map** as the value.
I'll set up my map variable. 

```java  
public class MusicCallableStatement {

    private static final int ARTIST_COLUMN = 0;
    private static final int ALBUM_COLUMN = 1;
    private static final int SONG_COLUMN = 3;

    public static void main(String[] args) {
      
        Map<String, Map<String, String>> albums = null;
    }
}
```

Its value is a **map**, keyed by **string**, with a value of **string**. 
I'll call this _albums_ and initialize it to **null**.
I'll show you another way to read the song data, from the `NewAlbums.csv`.
This time, I'll use the lines method, which returns a stream of string.
All of this is a good review, of streams,
and it'll actually make passing the data, to the stored procedure, a bit easier.
Just a reminder, that when you call `Files.lines`,
you should do it in a _try-with-resources_, 
so the stream is automatically closed. 
I'll do that here. 

```java  
public class MusicCallableStatement {

    private static final int ARTIST_COLUMN = 0;
    private static final int ALBUM_COLUMN = 1;
    private static final int SONG_COLUMN = 3;

    public static void main(String[] args) {
      
        Map<String, Map<String, String>> albums = null;

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course08_CallableStatements/NewAlbums.csv";
        try (var lines = Files.lines(Path.of(pathName))) {          // Stream<String>

            albums = lines.map(s -> s.split(","))
                    .collect(Collectors.groupingBy(s -> s[ARTIST_COLUMN], 
                            Collectors.groupingBy(s -> s[ALBUM_COLUMN], 
                                    Collectors.mapping(s -> s[SONG_COLUMN], 
                                            Collectors.joining(
                                                    "\",\"",
                                                    "[\"",
                                                    "\"]"
                                            )))));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
    }
}
```

Inside the _try_ clause, I'll set up a variable, simply called lines. 
I'll call `Files.lines` with a **Path** 
to the `NewAlbums.csv` file in the package folder. 
That returns a stream of **strings**.
I've got an error on the lines method, 
because I need to include a _catch_ clause,
so I'll hover over that error, and pick, add _catch_ clause.
Inside the _try_ block, I'll set up the stream pipeline.
So the first thing I'll do is, split each line by the comma.
I do this with the _map_ operation, and a lambda that splits the string. 
This means my stream goes from a stream of strings, 
to a stream of arrays of strings. 
I'll collect this data into a map with `Collectors.groupingBy`.
The first level is grouped by the artist. 
Now, my file only has _Bob Dylan_ as an artist, 
but let's say we did not know that. 
The first level of the mapping is the artist. 
The next grouping is the album, I'll call `Collectors.groupingBy` again,
and pass the value on the _ALBUM_COLUMN_.
Then, I need to get at the _SONG_COLUMN_ to collect the song titles.
I'll do this with mapping, returning only the song title. 
Next, I'll use `collectors.joining`, which ultimately uses _StringJoiner_. 
The goal here is to make this look like a **json** array, 
which really just looks like a printed array.
The delimiter is a comma, but I also need to wrap each song title in double quotes, 
so I escape that with a backslash, on either side of the comma. 
And that'll be wrapped in square brackets, which will be my prefix and suffix elements. 
I need the starting double quote as well, for the prefix. 
The same goes for the closing double quote, and closing bracket.
I'll print this out, so you can see the result.

```java  
public class MusicCallableStatement {

    private static final int ARTIST_COLUMN = 0;
    private static final int ALBUM_COLUMN = 1;
    private static final int SONG_COLUMN = 3;

    public static void main(String[] args) {
      
        Map<String, Map<String, String>> albums = null;

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course08_CallableStatements/NewAlbums.csv";
        try (var lines = Files.lines(Path.of(pathName))) {          // Stream<String>

            albums = lines.map(s -> s.split(","))
                    .collect(Collectors.groupingBy(s -> s[ARTIST_COLUMN], 
                            Collectors.groupingBy(s -> s[ALBUM_COLUMN], 
                                    Collectors.mapping(s -> s[SONG_COLUMN], 
                                            Collectors.joining(
                                                    "\",\"",
                                                    "[\"",
                                                    "\"]"
                                            )))));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

        albums.forEach((artist, artistAlbums) -> {
            artistAlbums.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });
        });
    }
}
```

I'll loop through the albums, the key was the artist's name, 
and the value is that artist's albums. 
Next, I'll loop through the nested map's data. 
The key is the album name, and the value is the string value, the array of songs. 
I'll print that out.
I'll run this.

```html  
Bob Dylan : ["You're No Good","Talkin' New York","In My Time of Dyin'","Man of Constant Sorrow","Fixin' to Die","Pretty Peggy-O","Highway 51 Blues","Gospel Plow","Baby Let Me Follow You Down","House of the Risin' Sun","Freight Train Blues","Song to Woody","See That My Grave Is Kept Clean"]
Blonde on Blonde : ["Rainy Day Women","Pledging My Time","Visions of Johanna","One of Us Must Know (Sooner or Later)","I Want You","Stuck Inside of Mobile with the Memphis Blues Again","Leopard-Skin Pill-Box Hat","Just Like a Woman","Most Likely You Go Your Way (And I'll Go Mine)","Temporary Like Achilles","Absolutely Sweet Marie","Fourth Time Around","Obviously Five Believers","Sad-Eyed Lady of the Lowlands"]
```

And you can see this file has two albums,
and I've got all the songs in a single comma delimited string,
each song title enclosed, in double quotes.
Next, I'll go over to the **Main** class from a previous section,
and I basically want to copy the entire _main_ method.
And I'll paste that at the end of the _main_ method in the **MusicCallableStatement** class.

```java  
public class MusicCallableStatement {

    private static final int ARTIST_COLUMN = 0;
    private static final int ALBUM_COLUMN = 1;
    private static final int SONG_COLUMN = 3;

    public static void main(String[] args) {
      
        Map<String, Map<String, String>> albums = null;

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course08_CallableStatements/NewAlbums.csv";
        try (var lines = Files.lines(Path.of(pathName))) {          // Stream<String>

            albums = lines.map(s -> s.split(","))
                    .collect(Collectors.groupingBy(s -> s[ARTIST_COLUMN], 
                            Collectors.groupingBy(s -> s[ALBUM_COLUMN], 
                                    Collectors.mapping(s -> s[SONG_COLUMN], 
                                            Collectors.joining(
                                                    "\",\"",
                                                    "[\"",
                                                    "\"]"
                                            )))));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

        albums.forEach((artist, artistAlbums) -> {
            artistAlbums.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });
        });

        var dataSource = new MysqlDataSource();
  
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setDatabaseName("music");
        
/*
        try {
            dataSource.setContinueBatchOnError(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
*/

        try (Connection connection = dataSource.getConnection(System.getenv("MYSQL_USER"), System.getenv("MYSQL_PASS"));) {
          
            //addDataFromFile(connection);

            //String sql = "SELECT * FROM music.albumview where artist_name = ?";
            String sql = "SELECT * FROM music.albumview WHERE artist_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Bob Dylan");
            ResultSet resultSet = ps.executeQuery();
            //printRecords(resultSet);
            Main.printRecords(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

I'll remove the code that _setContinueBatchOnError_ flag,
so that first pasted _try-catch_ block.
I don't need to worry about this now,
because my stored procedure will handle the entire transaction.
I'll also remove the call to _addDataFromFile_ method there.
I'll keep the select statement there, 
and the prepared statement that uses that, 
just to confirm that data does really get added.
Here is a case _where_ I missed making an SQL keyword uppercase, 
so I'll change the case of the _where_ clause, in this statement.
I've got a compiler error on _printRecords_,
because that method's on the **Main** class.
I'll open the **Main** class, and change the modifier 
from **private** to **public** on that method.
Now, back to the **MusicCallableStatement** class,
I can just prefix the method _printRecords_,
with the class name, `Main.`.
Ok, so now that this code compiles.
I'll add some code, at the start of this _try_ block, 
before my first SQL statement there.
I'll be executing the stored procedure, using a **CallableStatement**.

```java  
public class MusicCallableStatement {

    private static final int ARTIST_COLUMN = 0;
    private static final int ALBUM_COLUMN = 1;
    private static final int SONG_COLUMN = 3;

    public static void main(String[] args) {
      
        Map<String, Map<String, String>> albums = null;

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course08_CallableStatements/NewAlbums.csv";
        try (var lines = Files.lines(Path.of(pathName))) {          // Stream<String>

            albums = lines.map(s -> s.split(","))
                    .collect(Collectors.groupingBy(s -> s[ARTIST_COLUMN], 
                            Collectors.groupingBy(s -> s[ALBUM_COLUMN], 
                                    Collectors.mapping(s -> s[SONG_COLUMN], 
                                            Collectors.joining(
                                                    "\",\"",
                                                    "[\"",
                                                    "\"]"
                                            )))));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

        albums.forEach((artist, artistAlbums) -> {
            artistAlbums.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });
        });

        var dataSource = new MysqlDataSource();
  
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setDatabaseName("music");

        try (Connection connection = dataSource.getConnection(System.getenv("MYSQL_USER"), System.getenv("MYSQL_PASS"));) {

            CallableStatement cs = connection.prepareCall("CALL music.addAlbum(?,?,?)");
          
            albums.forEach((artist, albumMap) -> {
                albumMap.forEach((album, songs) -> {
                    try {
                        cs.setString(1, artist);
                        cs.setString(2, album);
                        cs.setString(3, songs);
                        cs.execute();
  
                    } catch (SQLException e) {
                        System.err.println(e.getErrorCode() + " " + e.getMessage());
                    }
                });
            });

            String sql = "SELECT * FROM music.albumview WHERE artist_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Bob Dylan");
            ResultSet resultSet = ps.executeQuery();
            Main.printRecords(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

You get a callable statement, much in the same way as a **preparedStatement**, 
but you invoke prepare call. 
And you pass that an SQL string. 
To execute a procedure in the database,
you usually use the keyword _call_, followed by the procedure name. 
Again, I'll prefix that with the schema name. 
And like a prepared statement, I use a question mark for parameters. 
In this case, the stored procedure has three parameters, 
so I'll put three question marks there. 
A _CallableStatement_ also gets parsed and compiled, 
which means I can re-use this statement efficiently.
Next, I'll use my _albums_ map, 
looping through the keys and values, with a for each method.
The key is the _artist_, and the value is the _nested map_. 
I'll again use _forEach_, and now the key is the album, 
and the value is the _songs_ array, a **string**, a **json** array. 
I have to include a _try-catch_ in this lambda expression. 
Like a prepared statement, I set the values for the placeholders,
so first the _artist_, then the _album_, and next is the _json_, 
which is really just a **string** ultimately.
I can run this, using the _execute_ method. 
This stored procedure doesn't return any data. 
If I get an exception, 
I'll print the more specific vendor error code and the message.
And that's all I need.
Before I run this, I have to set up my system environment variables, as usual.
That's done in the run configuration.
Once I have that set up, I'll run this code:

```html  
Bob Dylan : ["You're No Good","Talkin' New York","In My Time of Dyin'","Man of Constant Sorrow","Fixin' to Die","Pretty Peggy-O","Highway 51 Blues","Gospel Plow","Baby Let Me Follow You Down","House of the Risin' Sun","Freight Train Blues","Song to Woody","See That My Grave Is Kept Clean"]
Blonde on Blonde : ["Rainy Day Women","Pledging My Time","Visions of Johanna","One of Us Must Know (Sooner or Later)","I Want You","Stuck Inside of Mobile with the Memphis Blues Again","Leopard-Skin Pill-Box Hat","Just Like a Woman","Most Likely You Go Your Way (And I'll Go Mine)","Temporary Like Achilles","Absolutely Sweet Marie","Fourth Time Around","Obviously Five Believers","Sad-Eyed Lady of the Lowlands"]
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Blonde on BlondeBob Dylan      1              Rainy Day Women
Blonde on BlondeBob Dylan      2              Pledging My Time
Blonde on BlondeBob Dylan      3              Visions of Johanna
Blonde on BlondeBob Dylan      4              One of Us Must Know (Sooner or Later)
Blonde on BlondeBob Dylan      5              I Want You     
Blonde on BlondeBob Dylan      6              Stuck Inside of Mobile with the Memphis Blues Again
Blonde on BlondeBob Dylan      7              Leopard-Skin Pill-Box Hat
Blonde on BlondeBob Dylan      8              Just Like a Woman
Blonde on BlondeBob Dylan      9              Most Likely You Go Your Way (And I'll Go Mine)
Blonde on BlondeBob Dylan      10             Temporary Like Achilles
Blonde on BlondeBob Dylan      11             Absolutely Sweet Marie
Blonde on BlondeBob Dylan      12             Fourth Time Around
Blonde on BlondeBob Dylan      13             Obviously Five Believers
Blonde on BlondeBob Dylan      14             Sad-Eyed Lady of the Lowlands
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
Bob Dylan      Bob Dylan      8              Gospel Plow    
Bob Dylan      Bob Dylan      9              Baby Let Me Follow You Down
Bob Dylan      Bob Dylan      10             House of the Risin' Sun
Bob Dylan      Bob Dylan      11             Freight Train Blues
Bob Dylan      Bob Dylan      12             Song to Woody  
Bob Dylan      Bob Dylan      13             See That My Grave Is Kept Clean
```

Here, you can see the data in the _albumview_, 
which confirms that I successfully added the artist, 
the two albums, and all the related songs in that csv file, 
with just two method calls, to code stored in the database.
Some database vendors do support arrays, but MySQL is not one of them, 
so that's why I used the json workaround.
As I've said, it's important to understand what features your _RDMBS_ supports.
If you're using callable statements, 
it's less likely you're concerned about being database agnostic with your JDBC code.
So in this instance, I showed you an example of a stored procedure, 
that took three input parameters, and didn't return any data.

Executing a procedure stored on the database server 
from Java using JDBC and the **CallableStatement** is often a preferred approach
for many enterprise organizations.
That's why we passed a set of data to the stored procedure, 
which in turn managed inserting that data appropriately,
into related tables in the _music_ database.
In addition to passing data as parameters into a procedure, 
there are a couple of ways to get data back.
First, MySQL has three different types of parameter types for its procedures.

| Parameter Type | Description                                                                                                    | Read? | Write? |
|----------------|----------------------------------------------------------------------------------------------------------------|-------|--------|
| IN             | Used to pass values to the stored procedure. If a type is not specified, than it is implicitly an IN parameter | Y     | N      |
| OUT            | Used to return values from the stored procedure to the calling program.                                        | N     | Y      |
| INOUT          | Used to pass values to the stored procedure, which can modify the values and return the modified values back.  | Y     | Y      |

The in Parameter is a read-only parameter, and the default type, 
meaning we don't have to specify the keyword, _IN_ in our MySQL procedures
if this is the type of parameter you want.
This is data you pass to the procedure, and it doesn't get modified.
The _OUT_ parameter is a write-only parameter.
You must specify the _OUT_ type for this one.
The _INOUT_ parameter is a hybrid parameter, so you can pass data in,
which the procedure can modify, and return.
These types are specific to MySQL,
but many vendors have similar types, 
the syntax just might be slightly different.
This is where JDBC is an advantage, because you, as a developer, 
don't really need to worry about the syntax, just the concepts,
and how to interact with code that has these different parameter types.
I'll start first, with an example of the _OUT_ parameter.
To set this up, I'll open up my _development_ session on MySQL Workbench.
I'll expand the _Stored Procedures_, 
and click on the _addAlbum_ procedure there, and select the _tool_ icon.
This opens the _edit_ window, and I can edit this.
I'll add a new line 
after the localhost value in the _CREATE DEFINER_ statement.
The use of _CREATE DEFINER_ is important in situations 
where you want to control who can execute this procedure.

~~~~sql  
CREATE DEFINER=`devUser`@`localhost` PROCEDURE `addAlbum`(artistName TEXT, albumName TEXT, songTitles JSON)
BEGIN

    DECLARE val_artist_id INT DEFAULT NULL;
    DECLARE val_album_id INT DEFAULT NULL;
    DECLARE i INT DEFAULT 0;
    DECLARE num_items INT;
    DECLARE song_title VARCHAR(255);
    SET num_items = JSON_LENGTH(songTitles);
    
    SELECT artist_id INTO val_artist_id FROM artists WHERE  artist_name = artistName;

    START TRANSACTION;
    IF val_artist_id IS NULL THEN
        -- Insert a new order
        INSERT INTO artists (artist_name) VALUES (artistName);
        -- Get artist_id of last artist inserted
        SELECT LAST_INSERT_ID() INTO val_artist_id;
    END IF;

    SELECT album_id INTO val_album_id FROM albums WHERE album_name = albumName AND artist_id = val_artist_id;
        
    IF val_album_id IS NULL THEN
        -- Insert a new album
        INSERT INTO albums (artist_id, album_name) VALUES (val_artist_id, albumName);
        -- Get album_id of last artist inserted
        SELECT LAST_INSERT_ID() INTO val_album_id;
        
        -- Loop through the JSON Song Titles Array
        WHILE i < num_items DO
            -- JSON functions extract the right element, and unquote it
            SET song_title = JSON_UNQUOTE(JSON_EXTRACT(songTitles, CONCAT('$[', i, ']')));	
            -- Insert a new song, track number is assigned here.
            INSERT INTO songs (album_id, track_number, song_title) VALUES (val_album_id, i + 1, song_title); 
            SET i = i + 1;
        END WHILE;
    END IF;    
    COMMIT;
END
~~~~

In this case, this means the procedure will get executed with _devUser_'s privileges.
Right now, you don't have to worry about too much about this statement,
or the code in this procedure.
This first bit is added automatically if you create a new procedure.
What I want to do here is, change the procedure name, 
from _addAlbum_ to _addAlbumReturnCounts_.

~~~~sql  
CREATE DEFINER=`devUser`@`localhost` 
PROCEDURE `addAlbumReturnCounts`(IN artistName TEXT, IN albumName TEXT, IN songTitles JSON, OUT count INT)
BEGIN

    DECLARE val_artist_id INT DEFAULT NULL;
    DECLARE val_album_id INT DEFAULT NULL;
    DECLARE i INT DEFAULT 0;
    DECLARE num_items INT;
    DECLARE song_title VARCHAR(255);
    SET num_items = JSON_LENGTH(songTitles);
    
    SELECT artist_id INTO val_artist_id FROM artists WHERE  artist_name = artistName;

    START TRANSACTION;
    IF val_artist_id IS NULL THEN
        -- Insert a new order
        INSERT INTO artists (artist_name) VALUES (artistName);
        -- Get artist_id of last artist inserted
        SELECT LAST_INSERT_ID() INTO val_artist_id;
    END IF;

    SELECT album_id INTO val_album_id FROM albums WHERE album_name = albumName AND artist_id = val_artist_id;
        
    IF val_album_id IS NULL THEN
        -- Insert a new album
        INSERT INTO albums (artist_id, album_name) VALUES (val_artist_id, albumName);
        -- Get album_id of last artist inserted
        SELECT LAST_INSERT_ID() INTO val_album_id;
        
        -- Loop through the JSON Song Titles Array
        WHILE i < num_items DO
            -- JSON functions extract the right element, and unquote it
            SET song_title = JSON_UNQUOTE(JSON_EXTRACT(songTitles, CONCAT('$[', i, ']')));	
            -- Insert a new song, track number is assigned here.
            INSERT INTO songs (album_id, track_number, song_title) VALUES (val_album_id, i + 1, song_title); 
            SET i = i + 1;
        END WHILE;
    END IF;    
    COMMIT;
END
~~~~

By doing this, I'll actually be creating a new procedure with this name.
Before I save this though, I'll add a fourth parameter.
I'll start with the keyword **OUT**,
and by convention this is uppercase, 
my parameter name will be _count_, and it's type is **int**.
I could also change my other three parameters, 
and include the keyword, **IN**, and I'll do this.
It's usually best practice to explicitly specify the **IN** parameter type,
though not required, as you saw.
I'll hit the _Apply_ button.
That will pop up the DDL script that'll get executed.
I'll hit _apply_ again.
Then finish.
I'll refresh the schema panel,
and I should see my new procedure there.

![image73](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image73.png?raw=true)

So I created an **OUT** parameter, 
but I didn't do anything with this parameter in my code.
I still have the procedure up in the edit window,
so I'll scroll to the end of this code.

~~~~sql  
CREATE DEFINER=`devUser`@`localhost` 
PROCEDURE `addAlbumReturnCounts`(IN artistName TEXT, IN albumName TEXT, IN songTitles JSON, OUT count INT)
BEGIN

    DECLARE val_artist_id INT DEFAULT NULL;
    DECLARE val_album_id INT DEFAULT NULL;
    DECLARE i INT DEFAULT 0;
    DECLARE num_items INT;
    DECLARE song_title VARCHAR(255);
    SET num_items = JSON_LENGTH(songTitles);
    
    SELECT artist_id INTO val_artist_id FROM artists WHERE  artist_name = artistName;

    START TRANSACTION;
    IF val_artist_id IS NULL THEN
        -- Insert a new order
        INSERT INTO artists (artist_name) VALUES (artistName);
        -- Get artist_id of last artist inserted
        SELECT LAST_INSERT_ID() INTO val_artist_id;
    END IF;

    SELECT album_id INTO val_album_id FROM albums WHERE album_name = albumName AND artist_id = val_artist_id;
        
    IF val_album_id IS NULL THEN
        -- Insert a new album
        INSERT INTO albums (artist_id, album_name) VALUES (val_artist_id, albumName);
        -- Get album_id of last artist inserted
        SELECT LAST_INSERT_ID() INTO val_album_id;
        
        -- Loop through the JSON Song Titles Array
        WHILE i < num_items DO
            -- JSON functions extract the right element, and unquote it
            SET song_title = JSON_UNQUOTE(JSON_EXTRACT(songTitles, CONCAT('$[', i, ']')));	
            -- Insert a new song, track number is assigned here.
            INSERT INTO songs (album_id, track_number, song_title) VALUES (val_album_id, i + 1, song_title); 
            SET i = i + 1;
        END WHILE;
    END IF;    
    COMMIT;
    SET count = i;
END
~~~~

Right after the _commit_, 
I'll set the _count_ variable to the value in the eye variable,
which will tell me how many songs got inserted.
I'll say again, don't get too stressed about the code in this procedure.
If you follow along with me, this should compile fine, 
and you don't really need to understand all the details about how this works.
I've also included this procedure's script in the package folder 
if you have any troubles.
You can drop your version, and import the script,
like I showed you, with the first procedure in a previous section.
For now, I'll again _Apply_ that, twice, and then hit _finish_.
I'll open an SQL edit panel, 
and delete all my data for _Bob Dylan_ again.

~~~~sql  
DELETE FROM music.artists WHERE artist_name = 'Bob Dylan';
~~~~

Now, let's get back to IntelliJ to the class 
I was using previously, **MusicCallableStatement**.
In this code, I'll change the name of the stored procedure that gets called,
in the callable statement code here.

```java  
public class MusicCallableStatement {

    private static final int ARTIST_COLUMN = 0;
    private static final int ALBUM_COLUMN = 1;
    private static final int SONG_COLUMN = 3;

    public static void main(String[] args) {
      
        Map<String, Map<String, String>> albums = null;

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course08_CallableStatements/NewAlbums.csv";
        try (var lines = Files.lines(Path.of(pathName))) {          // Stream<String>

            albums = lines.map(s -> s.split(","))
                    .collect(Collectors.groupingBy(s -> s[ARTIST_COLUMN], 
                            Collectors.groupingBy(s -> s[ALBUM_COLUMN], 
                                    Collectors.mapping(s -> s[SONG_COLUMN], 
                                            Collectors.joining(
                                                    "\",\"",
                                                    "[\"",
                                                    "\"]"
                                            )))));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

        albums.forEach((artist, artistAlbums) -> {
            artistAlbums.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });
        });

        var dataSource = new MysqlDataSource();
  
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setDatabaseName("music");

        try (Connection connection = dataSource.getConnection(System.getenv("MYSQL_USER"), System.getenv("MYSQL_PASS"));) {

            //CallableStatement cs = connection.prepareCall("CALL music.addAlbum(?,?,?)");
            CallableStatement cs = connection.prepareCall("CALL music.addAlbumReturnCounts(?,?,?)");
          
            albums.forEach((artist, albumMap) -> {
                albumMap.forEach((album, songs) -> {
                    try {
                        cs.setString(1, artist);
                        cs.setString(2, album);
                        cs.setString(3, songs);
                        cs.execute();
  
                    } catch (SQLException e) {
                        System.err.println(e.getErrorCode() + " " + e.getMessage());
                    }
                });
            });

            String sql = "SELECT * FROM music.albumview WHERE artist_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Bob Dylan");
            ResultSet resultSet = ps.executeQuery();
            Main.printRecords(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

I'll run this now, executing the new stored procedure:

```html  
Bob Dylan : ["You're No Good","Talkin' New York","In My Time of Dyin'","Man of Constant Sorrow","Fixin' to Die","Pretty Peggy-O","Highway 51 Blues","Gospel Plow","Baby Let Me Follow You Down","House of the Risin' Sun","Freight Train Blues","Song to Woody","See That My Grave Is Kept Clean"]
Blonde on Blonde : ["Rainy Day Women","Pledging My Time","Visions of Johanna","One of Us Must Know (Sooner or Later)","I Want You","Stuck Inside of Mobile with the Memphis Blues Again","Leopard-Skin Pill-Box Hat","Just Like a Woman","Most Likely You Go Your Way (And I'll Go Mine)","Temporary Like Achilles","Absolutely Sweet Marie","Fourth Time Around","Obviously Five Believers","Sad-Eyed Lady of the Lowlands"]
0 Parameter count is not registered as an output parameter
0 Parameter count is not registered as an output parameter
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE
```

This time, I get an error for each attempt that the parameter,
named _count_ is not registered as an output parameter.
There are a couple of problems here, actually.
First, I need another question mark, as a parameter placeholder,
in the call to this stored procedure.
So I'll quickly add that.

```java  
//CallableStatement cs = connection.prepareCall("CALL music.addAlbum(?,?,?)");
CallableStatement cs = connection.prepareCall("CALL music.addAlbumReturnCounts(?,?,?)");
CallableStatement cs = connection.prepareCall("CALL music.addAlbumReturnCounts(?,?,?,?)");
```

And I'll try running this again.

```html  
Bob Dylan : ["You're No Good","Talkin' New York","In My Time of Dyin'","Man of Constant Sorrow","Fixin' to Die","Pretty Peggy-O","Highway 51 Blues","Gospel Plow","Baby Let Me Follow You Down","House of the Risin' Sun","Freight Train Blues","Song to Woody","See That My Grave Is Kept Clean"]
Blonde on Blonde : ["Rainy Day Women","Pledging My Time","Visions of Johanna","One of Us Must Know (Sooner or Later)","I Want You","Stuck Inside of Mobile with the Memphis Blues Again","Leopard-Skin Pill-Box Hat","Just Like a Woman","Most Likely You Go Your Way (And I'll Go Mine)","Temporary Like Achilles","Absolutely Sweet Marie","Fourth Time Around","Obviously Five Believers","Sad-Eyed Lady of the Lowlands"]
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Blonde on BlondeBob Dylan      1              Rainy Day Women
Blonde on BlondeBob Dylan      2              Pledging My Time
Blonde on BlondeBob Dylan      3              Visions of Johanna
Blonde on BlondeBob Dylan      4              One of Us Must Know (Sooner or Later)
Blonde on BlondeBob Dylan      5              I Want You     
Blonde on BlondeBob Dylan      6              Stuck Inside of Mobile with the Memphis Blues Again
Blonde on BlondeBob Dylan      7              Leopard-Skin Pill-Box Hat
Blonde on BlondeBob Dylan      8              Just Like a Woman
Blonde on BlondeBob Dylan      9              Most Likely You Go Your Way (And I'll Go Mine)
Blonde on BlondeBob Dylan      10             Temporary Like Achilles
Blonde on BlondeBob Dylan      11             Absolutely Sweet Marie
Blonde on BlondeBob Dylan      12             Fourth Time Around
Blonde on BlondeBob Dylan      13             Obviously Five Believers
Blonde on BlondeBob Dylan      14             Sad-Eyed Lady of the Lowlands
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
Bob Dylan      Bob Dylan      8              Gospel Plow    
Bob Dylan      Bob Dylan      9              Baby Let Me Follow You Down
Bob Dylan      Bob Dylan      10             House of the Risin' Sun
Bob Dylan      Bob Dylan      11             Freight Train Blues
Bob Dylan      Bob Dylan      12             Song to Woody  
Bob Dylan      Bob Dylan      13             See That My Grave Is Kept Clean
```

And now this works, and data gets added.
So registering an _out_ parameter isn't actually required.
You register the _out_ parameter if you want to get the data back.
Since I do want the result that gets passed back in that parameter,
I'll show you how to do that next.
To get that data, there are two steps.

```java  
public class MusicCallableStatement {

    private static final int ARTIST_COLUMN = 0;
    private static final int ALBUM_COLUMN = 1;
    private static final int SONG_COLUMN = 3;

    public static void main(String[] args) {
      
        Map<String, Map<String, String>> albums = null;

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course08_CallableStatements/NewAlbums.csv";
        try (var lines = Files.lines(Path.of(pathName))) {          // Stream<String>

            albums = lines.map(s -> s.split(","))
                    .collect(Collectors.groupingBy(s -> s[ARTIST_COLUMN], 
                            Collectors.groupingBy(s -> s[ALBUM_COLUMN], 
                                    Collectors.mapping(s -> s[SONG_COLUMN], 
                                            Collectors.joining(
                                                    "\",\"",
                                                    "[\"",
                                                    "\"]"
                                            )))));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

        albums.forEach((artist, artistAlbums) -> {
            artistAlbums.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });
        });

        var dataSource = new MysqlDataSource();
  
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setDatabaseName("music");

        try (Connection connection = dataSource.getConnection(System.getenv("MYSQL_USER"), System.getenv("MYSQL_PASS"));) {

            CallableStatement cs = connection.prepareCall("CALL music.addAlbumReturnCounts(?,?,?,?)");
          
            albums.forEach((artist, albumMap) -> {
                albumMap.forEach((album, songs) -> {
                    try {
                        cs.setString(1, artist);
                        cs.setString(2, album);
                        cs.setString(3, songs);
                        
                        cs.registerOutParameter(4, Types.INTEGER);
                        cs.execute();
                        System.out.printf("%d songs were added for %s%n", cs.getInt(4), album);
  
                    } catch (SQLException e) {
                        System.err.println(e.getErrorCode() + " " + e.getMessage());
                    }
                });
            });

            String sql = "SELECT * FROM music.albumview WHERE artist_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Bob Dylan");
            ResultSet resultSet = ps.executeQuery();
            Main.printRecords(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

Before the _execute_ method, I need to call _registerOutParameter_, 
specifying the index as 4. 
The second parameter is the data type, and I'll set that to `Types.INTEGER`.
Types is a class in the `java.sql` package, 
which defines constants used to identify generic SQL types, 
called JDBC types, which will be translated to java types.
That's the first thing that needs to be done,
to get data returned on the **CallableStatement**.
The next part is to retrieve this data from the statement, after it's executed.
I'll print the number of songs, and the album name. 
I can get the output parameter's value by calling _getInt_, 
and passing the appropriate index, so the index is 4 here.
Before I run this, I want to again delete the data for _Bob Dylan_, 
so I'll jump back to the MySQL Workbench session, 
and re-execute the delete artists statement there.
And now, back to IntelliJ, I'll run my code.

```html  
Bob Dylan : ["You're No Good","Talkin' New York","In My Time of Dyin'","Man of Constant Sorrow","Fixin' to Die","Pretty Peggy-O","Highway 51 Blues","Gospel Plow","Baby Let Me Follow You Down","House of the Risin' Sun","Freight Train Blues","Song to Woody","See That My Grave Is Kept Clean"]
Blonde on Blonde : ["Rainy Day Women","Pledging My Time","Visions of Johanna","One of Us Must Know (Sooner or Later)","I Want You","Stuck Inside of Mobile with the Memphis Blues Again","Leopard-Skin Pill-Box Hat","Just Like a Woman","Most Likely You Go Your Way (And I'll Go Mine)","Temporary Like Achilles","Absolutely Sweet Marie","Fourth Time Around","Obviously Five Believers","Sad-Eyed Lady of the Lowlands"]
13 songs were added for Bob Dylan
14 songs were added for Blonde on Blonde
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Blonde on BlondeBob Dylan      1              Rainy Day Women
Blonde on BlondeBob Dylan      2              Pledging My Time
Blonde on BlondeBob Dylan      3              Visions of Johanna
Blonde on BlondeBob Dylan      4              One of Us Must Know (Sooner or Later)
Blonde on BlondeBob Dylan      5              I Want You     
Blonde on BlondeBob Dylan      6              Stuck Inside of Mobile with the Memphis Blues Again
Blonde on BlondeBob Dylan      7              Leopard-Skin Pill-Box Hat
Blonde on BlondeBob Dylan      8              Just Like a Woman
Blonde on BlondeBob Dylan      9              Most Likely You Go Your Way (And I'll Go Mine)
Blonde on BlondeBob Dylan      10             Temporary Like Achilles
Blonde on BlondeBob Dylan      11             Absolutely Sweet Marie
Blonde on BlondeBob Dylan      12             Fourth Time Around
Blonde on BlondeBob Dylan      13             Obviously Five Believers
Blonde on BlondeBob Dylan      14             Sad-Eyed Lady of the Lowlands
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
Bob Dylan      Bob Dylan      8              Gospel Plow    
Bob Dylan      Bob Dylan      9              Baby Let Me Follow You Down
Bob Dylan      Bob Dylan      10             House of the Risin' Sun
Bob Dylan      Bob Dylan      11             Freight Train Blues
Bob Dylan      Bob Dylan      12             Song to Woody  
Bob Dylan      Bob Dylan      13             See That My Grave Is Kept Clean
```

Before all the records are printed, notice the two statements there.
`13` songs were added for the album, _Bob Dylan_,
and `14` songs were added for _Blonde on Blonde_.
So that's a demonstration of two-way communication with a stored procedure.

Next, I'll create a third procedure, in MySQL Workbench.
This script will be in the resources folder as well 
if you encounter any problems, just editing the current procedure, 
which I'll do here now.
I'll be creating this manually, 
by editing another procedure, 
so I'll open the _addAlbumReturnCounts_ procedure, using the tool icon.

~~~~sql  
CREATE DEFINER=`devUser`@`localhost` 
PROCEDURE `addAlbumInOutCounts`(IN artistName TEXT, IN albumName TEXT, IN songTitles JSON, INOUT count INT)
BEGIN

    DECLARE val_artist_id INT DEFAULT NULL;
    DECLARE val_album_id INT DEFAULT NULL;
    DECLARE i INT DEFAULT 0;
    DECLARE num_items INT;
    DECLARE song_title VARCHAR(255);
    SET num_items = JSON_LENGTH(songTitles);
    
    SELECT artist_id INTO val_artist_id FROM artists WHERE  artist_name = artistName;

    START TRANSACTION;
    IF val_artist_id IS NULL THEN
        -- Insert a new order
        INSERT INTO artists (artist_name) VALUES (artistName);
        -- Get artist_id of last artist inserted
        SELECT LAST_INSERT_ID() INTO val_artist_id;
    END IF;

    SELECT album_id INTO val_album_id FROM albums WHERE album_name = albumName AND artist_id = val_artist_id;
        
    IF val_album_id IS NULL THEN
        -- Insert a new album
        INSERT INTO albums (artist_id, album_name) VALUES (val_artist_id, albumName);
        -- Get album_id of last artist inserted
        SELECT LAST_INSERT_ID() INTO val_album_id;
        
        -- Loop through the JSON Song Titles Array
        WHILE i < num_items DO
            -- JSON functions extract the right element, and unquote it
            SET song_title = JSON_UNQUOTE(JSON_EXTRACT(songTitles, CONCAT('$[', i, ']')));	
            -- Insert a new song, track number is assigned here.
            INSERT INTO songs (album_id, track_number, song_title) VALUES (val_album_id, i + 1, song_title); 
            SET i = i + 1;
        END WHILE;
    END IF;    
    COMMIT;
    IF count <> i THEN
        -- LOGGING, AUDIT, EXCEPTION HANDLING HERE
        SET count = i;
    END IF;
END
~~~~

I'll change the name to _addAlbumInOutCounts_,
and I'll change that fourth parameter from an _OUT_ parameter 
to an _INOUT_ parameter.
Before I apply these changes, I'll scroll to the bottom, 
and add an _if_ statement around the code 
that sets the value of the _count_ parameter.
In this case, I'll check if the number of records 
that got inserted equals the count, passed as a parameter. 
If this were a real scenario, I might do something additional, 
like log the information, or pass data to an audit table,
or raise some kind of error.
I'll hit _apply_ again then _finish_.
I'll refresh the schema panel.
I'll see my third stored procedure there,
and now I can use it in my JDBC code.
I'll again go to an SQL editing panel,
and delete the data for _Bob Dylan_.
Getting back to my Java code in IntelliJ, I'll make two changes to my code.

```java  
public class MusicCallableStatement {

    private static final int ARTIST_COLUMN = 0;
    private static final int ALBUM_COLUMN = 1;
    private static final int SONG_COLUMN = 3;

    public static void main(String[] args) {
      
        Map<String, Map<String, String>> albums = null;

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course08_CallableStatements/NewAlbums.csv";
        try (var lines = Files.lines(Path.of(pathName))) {          // Stream<String>

            albums = lines.map(s -> s.split(","))
                    .collect(Collectors.groupingBy(s -> s[ARTIST_COLUMN], 
                            Collectors.groupingBy(s -> s[ALBUM_COLUMN], 
                                    Collectors.mapping(s -> s[SONG_COLUMN], 
                                            Collectors.joining(
                                                    "\",\"",
                                                    "[\"",
                                                    "\"]"
                                            )))));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

        albums.forEach((artist, artistAlbums) -> {
            artistAlbums.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });
        });

        var dataSource = new MysqlDataSource();
  
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setDatabaseName("music");

        try (Connection connection = dataSource.getConnection(System.getenv("MYSQL_USER"), System.getenv("MYSQL_PASS"));) {

            //CallableStatement cs = connection.prepareCall("CALL music.addAlbumReturnCounts(?,?,?,?)");
            CallableStatement cs = connection.prepareCall("CALL music.addAlbumInOutCounts(?,?,?,?)");
          
            albums.forEach((artist, albumMap) -> {
                albumMap.forEach((album, songs) -> {
                    try {
                        cs.setString(1, artist);
                        cs.setString(2, album);
                        cs.setString(3, songs);
                        
                        cs.setInt(4, 10);
                        cs.registerOutParameter(4, Types.INTEGER);
                        cs.execute();
                        System.out.printf("%d songs were added for %s%n", cs.getInt(4), album);
  
                    } catch (SQLException e) {
                        System.err.println(e.getErrorCode() + " " + e.getMessage());
                    }
                });
            });

            String sql = "SELECT * FROM music.albumview WHERE artist_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Bob Dylan");
            ResultSet resultSet = ps.executeQuery();
            Main.printRecords(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

First, I'll change the name of the procedure I'm calling, 
so from _addAlbumReturnCounts_ to _addAlbumInOutCounts_. 
Next, I'll pass an initial value to parameter 4.
Normally, this would be the record count you expect to be inserted, 
but I'll just set it to an arbitrary number, like 10.
I'll call set int here, and the parameter is 4, 
and the value of count is 10.
I'll run this:

```html  
Bob Dylan : ["You're No Good","Talkin' New York","In My Time of Dyin'","Man of Constant Sorrow","Fixin' to Die","Pretty Peggy-O","Highway 51 Blues","Gospel Plow","Baby Let Me Follow You Down","House of the Risin' Sun","Freight Train Blues","Song to Woody","See That My Grave Is Kept Clean"]
Blonde on Blonde : ["Rainy Day Women","Pledging My Time","Visions of Johanna","One of Us Must Know (Sooner or Later)","I Want You","Stuck Inside of Mobile with the Memphis Blues Again","Leopard-Skin Pill-Box Hat","Just Like a Woman","Most Likely You Go Your Way (And I'll Go Mine)","Temporary Like Achilles","Absolutely Sweet Marie","Fourth Time Around","Obviously Five Believers","Sad-Eyed Lady of the Lowlands"]
13 songs were added for Bob Dylan
14 songs were added for Blonde on Blonde
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Blonde on BlondeBob Dylan      1              Rainy Day Women
Blonde on BlondeBob Dylan      2              Pledging My Time
Blonde on BlondeBob Dylan      3              Visions of Johanna
Blonde on BlondeBob Dylan      4              One of Us Must Know (Sooner or Later)
Blonde on BlondeBob Dylan      5              I Want You     
Blonde on BlondeBob Dylan      6              Stuck Inside of Mobile with the Memphis Blues Again
Blonde on BlondeBob Dylan      7              Leopard-Skin Pill-Box Hat
Blonde on BlondeBob Dylan      8              Just Like a Woman
Blonde on BlondeBob Dylan      9              Most Likely You Go Your Way (And I'll Go Mine)
Blonde on BlondeBob Dylan      10             Temporary Like Achilles
Blonde on BlondeBob Dylan      11             Absolutely Sweet Marie
Blonde on BlondeBob Dylan      12             Fourth Time Around
Blonde on BlondeBob Dylan      13             Obviously Five Believers
Blonde on BlondeBob Dylan      14             Sad-Eyed Lady of the Lowlands
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
Bob Dylan      Bob Dylan      8              Gospel Plow    
Bob Dylan      Bob Dylan      9              Baby Let Me Follow You Down
Bob Dylan      Bob Dylan      10             House of the Risin' Sun
Bob Dylan      Bob Dylan      11             Freight Train Blues
Bob Dylan      Bob Dylan      12             Song to Woody  
Bob Dylan      Bob Dylan      13             See That My Grave Is Kept Clean
```

So even though I passed in 10 as the _count_ to parameter 4, 
you can see that I'm getting the actual count back,
13 and 14 songs inserted there.
If I rerun it immediately without first deleting the data:

```html  
Bob Dylan : ["You're No Good","Talkin' New York","In My Time of Dyin'","Man of Constant Sorrow","Fixin' to Die","Pretty Peggy-O","Highway 51 Blues","Gospel Plow","Baby Let Me Follow You Down","House of the Risin' Sun","Freight Train Blues","Song to Woody","See That My Grave Is Kept Clean"]
Blonde on Blonde : ["Rainy Day Women","Pledging My Time","Visions of Johanna","One of Us Must Know (Sooner or Later)","I Want You","Stuck Inside of Mobile with the Memphis Blues Again","Leopard-Skin Pill-Box Hat","Just Like a Woman","Most Likely You Go Your Way (And I'll Go Mine)","Temporary Like Achilles","Absolutely Sweet Marie","Fourth Time Around","Obviously Five Believers","Sad-Eyed Lady of the Lowlands"]
0 songs were added for Bob Dylan
0 songs were added for Blonde on Blonde
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Blonde on BlondeBob Dylan      1              Rainy Day Women
Blonde on BlondeBob Dylan      2              Pledging My Time
Blonde on BlondeBob Dylan      3              Visions of Johanna
Blonde on BlondeBob Dylan      4              One of Us Must Know (Sooner or Later)
Blonde on BlondeBob Dylan      5              I Want You     
Blonde on BlondeBob Dylan      6              Stuck Inside of Mobile with the Memphis Blues Again
Blonde on BlondeBob Dylan      7              Leopard-Skin Pill-Box Hat
Blonde on BlondeBob Dylan      8              Just Like a Woman
Blonde on BlondeBob Dylan      9              Most Likely You Go Your Way (And I'll Go Mine)
Blonde on BlondeBob Dylan      10             Temporary Like Achilles
Blonde on BlondeBob Dylan      11             Absolutely Sweet Marie
Blonde on BlondeBob Dylan      12             Fourth Time Around
Blonde on BlondeBob Dylan      13             Obviously Five Believers
Blonde on BlondeBob Dylan      14             Sad-Eyed Lady of the Lowlands
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
Bob Dylan      Bob Dylan      8              Gospel Plow    
Bob Dylan      Bob Dylan      9              Baby Let Me Follow You Down
Bob Dylan      Bob Dylan      10             House of the Risin' Sun
Bob Dylan      Bob Dylan      11             Freight Train Blues
Bob Dylan      Bob Dylan      12             Song to Woody  
Bob Dylan      Bob Dylan      13             See That My Grave Is Kept Clean
```

You can see that `0` records were inserted,
which is what you'd expect in this case.
Now I'll switch over to MySQL Workbench, 
and once again execute my _delete_ statement.
And now, right back to IntelliJ.
Before I run the code again, I'll first comment out the line, 
where I'm setting the fourth parameter, to an initial value, 10 in this case.
I'll see if I can run it this way:

```html  
Bob Dylan : ["You're No Good","Talkin' New York","In My Time of Dyin'","Man of Constant Sorrow","Fixin' to Die","Pretty Peggy-O","Highway 51 Blues","Gospel Plow","Baby Let Me Follow You Down","House of the Risin' Sun","Freight Train Blues","Song to Woody","See That My Grave Is Kept Clean"]
Blonde on Blonde : ["Rainy Day Women","Pledging My Time","Visions of Johanna","One of Us Must Know (Sooner or Later)","I Want You","Stuck Inside of Mobile with the Memphis Blues Again","Leopard-Skin Pill-Box Hat","Just Like a Woman","Most Likely You Go Your Way (And I'll Go Mine)","Temporary Like Achilles","Absolutely Sweet Marie","Fourth Time Around","Obviously Five Believers","Sad-Eyed Lady of the Lowlands"]
0 songs were added for Bob Dylan
0 songs were added for Blonde on Blonde
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Blonde on BlondeBob Dylan      1              Rainy Day Women
Blonde on BlondeBob Dylan      2              Pledging My Time
Blonde on BlondeBob Dylan      3              Visions of Johanna
Blonde on BlondeBob Dylan      4              One of Us Must Know (Sooner or Later)
Blonde on BlondeBob Dylan      5              I Want You     
Blonde on BlondeBob Dylan      6              Stuck Inside of Mobile with the Memphis Blues Again
Blonde on BlondeBob Dylan      7              Leopard-Skin Pill-Box Hat
Blonde on BlondeBob Dylan      8              Just Like a Woman
Blonde on BlondeBob Dylan      9              Most Likely You Go Your Way (And I'll Go Mine)
Blonde on BlondeBob Dylan      10             Temporary Like Achilles
Blonde on BlondeBob Dylan      11             Absolutely Sweet Marie
Blonde on BlondeBob Dylan      12             Fourth Time Around
Blonde on BlondeBob Dylan      13             Obviously Five Believers
Blonde on BlondeBob Dylan      14             Sad-Eyed Lady of the Lowlands
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
Bob Dylan      Bob Dylan      8              Gospel Plow    
Bob Dylan      Bob Dylan      9              Baby Let Me Follow You Down
Bob Dylan      Bob Dylan      10             House of the Risin' Sun
Bob Dylan      Bob Dylan      11             Freight Train Blues
Bob Dylan      Bob Dylan      12             Song to Woody  
Bob Dylan      Bob Dylan      13             See That My Grave Is Kept Clean
```

And yes, you can see this does actually run.
But the results are misleading.
Notice that I do get data back when I just deleted it, 
before running this code.
This means this code must have added the data.
But it says `0` songs were added for each album.
There's a couple of things going on here, I want to talk about.

First, even if I don't set the _INOUT_ parameter to a value,
it will get initialized to a default value,
depending on the data type, and the DBMS vendor.
Here, _count_ wasn't initialized to `0`, it was initialized to **null**.
And **null** has unexpected 
and sometimes confusing results in SQL code if you're new to it.
In this stored procedure, I first check if _count_ is not equal to _i_,
and if it's not I'll set _count_.
The reason this returns **false** when value is **NULL**, 
is because the **NULL** variable represents **an unknown value**.
In databases, a variable or parameter that's **null** isn't equal to, 
and it's also not equal to, another value.
This had an unexpected result here,
and could produce unexpected results 
if a null parameter value is used in SQL statements.
The moral of the story here is 
that you should always initialize your _IN_ and _INOUT_ parameters.
This way, you can avoid any unexpected behavior that could arise, 
as we're seeing here.
I'll uncomment that line there so this code is operational.

Ok, so those are the two ways to get data back from a stored procedure.
There's another way to get data back from stored code in the database server,
and that's by using **CallableStatement**, 
but executing a function, rather than a procedure.
A function's purpose is to return a value, 
usually the result of some calculation or formula.
In contrast, a stored procedure is often used for performing a sequence of operations,
data manipulation, or enforcing business rules within the database.

So far, we looked at stored procedures, how to call them, 
and also how in some cases we can get data back.
As already discussed, Stored procedures are designed 
to execute a sequence of SQL statements,
and can perform multiple operations within a single call.
They can modify the state of the database,
meaning they can create, update, or delete data, 
as the _addAlbum_ series of procedures did.
Procedures can also control transaction management,
ensuring data consistency and integrity.

In relational database management systems, like MySQL, 
precompiled collections of SQL statements can be either a stored procedure
or a stored function.
Both are stored in the database, and both can be executed as a single unit.
While each may encapsulate common database operations, they are different.
Specifically, stored functions are designed to **perform a specific calculation**, 
or data manipulation, and **return a single value**.
The key characteristics of a stored function are:

1. As I just stated, it **Returns a value**.
Stored functions are expected to return a single value, 
such as an integer, string, date, or a result set. 
2. A stored function **is Immutable**.
Stored functions are generally designed to be deterministic, 
and should not have side effects. 
This means that calling a function with the same inputs should always produce the same output, 
and functions should not modify data in the database. 
3. Lastly, a stored function **can be used inSQL expressions**.
This means you can use it directly in _SELECT_ statements, _WHERE_ clauses, or _JOIN_ conditions,
to compute values used in queries.

| Stored Function     | Stored Procedure              |
|---------------------|-------------------------------|
| Data Validation     | Data Modification             |
| Data Conversion     | ETL(Extract, Transform, Load) |
| Complex Expressions | Business Logic                |
| Calculations        | Batch Processing              |

This table lists some of the use cases for each of these stored objects.
You can think of a function as a targeted and individual unit of work.
It can be used for validating data before insertion or updating.
A stored function can be used for converting data, from one format, to another.
It can be used to reuse complex expressions that are used frequently.
Finally, a stored function is often used to perform some calculation or formula.
On the other hand, a stored procedure is like a mini program in the database, 
and has much broader goals.
Use a stored procedure for data modifications, like inserting, updating or deleting records.
Stored procedures are often used to extract, transform and load data (making use of functions)
to load data from one table to another.
Or maybe you've got some business logic, 
these rules can be encapsulated in a stored procedure.
And we've already seen that a stored procedure can be used 
for batch and transactional processing.
You might be less likely to call a stored function from your _CallableStatement_, 
than you would a Stored Procedure, but I still want to cover it.
There are a couple of differences.
The first is that the SQL string will always start with a placeholder,
so a question mark for the returned result.
The second is that MySQL stored functions only support _IN_ parameters. 
This isn't true for other vendors.
This just means you don't specify parameter types 
in the parameter declarations of the stored function, in MySQL.

First, let's get started by loading and examining a simple function in MySQL Workbench.
First, download the file called calcAlbumLength.sql from the package folder of this section.
Open MySQL Workbench, and use the development session we've been using all along.
Make sure you have the _schemas_ panel open.
Select the second icon from the menu of icons, which is, open a SQL script from a file.
Browse to the file you downloaded, and select that.
I'll select the first lightning icon and that will execute this script.
If I refresh the _schema_ panel, I can open the _Functions_ node.
And there I can see the new stored function there, _calcAlbumLength_.
I'll select the tool icon.

![image74](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image74.png?raw=true)

What you'll notice is that this looks a lot like a stored procedure.
It has the keyword _FUNCTION_ instead of _PROCEDURE_,
but it has parameters defined in a similar fashion.

~~~~sql  
CREATE DEFINER=`devuser`@`localhost` FUNCTION `calcAlbumLength`(albumName TEXT) RETURNS double
    READS SQL DATA
BEGIN
	DECLARE length DOUBLE DEFAULT 0.0;

	SELECT 
    COUNT(*) * 2.5
INTO length FROM
    music.albumview
WHERE
    album_name = albumName;
        
	RETURN length;
END
~~~~

One thing I want to show you is, if I now decide to declare the input type explicitly,
by adding the keyword _IN_, before _albumName_.

~~~~sql  
CREATE DEFINER=`devuser`@`localhost` FUNCTION `calcAlbumLength`(IN albumName TEXT) RETURNS double
    READS SQL DATA
BEGIN
	DECLARE length DOUBLE DEFAULT 0.0;

	SELECT 
    COUNT(*) * 2.5
INTO length FROM
    music.albumview
WHERE
    album_name = albumName;
        
	RETURN length;
END
~~~~

Notice that this is an error in this case.
This is one difference that you can't specify the parameter type _IN_ your stored function, 
because all parameters are by default, type _IN_ Parameters.
I'll revert that change, removing _IN_ from the parameter declaration.
The next thing you'll notice is that this function returns a double.
And then notice the next line, that says _READS SQL DATA_.
I'm going to remove this for a minute.
I'll apply this change.
And hit _apply_ a second time.

![image75](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image75.png?raw=true)

Now, I've got a problem.
There was an error while applying the SQL script to the database.
I'll scroll down a bit, until I see `Error 1418`.
Here, the statement says, 
_this function has none of DETERMINISTIC, NO SQL, or READS SQL DATA in its declaration_.
You might remember I mentioned that functions should be immutable or deterministic.
Deterministic means that running the code with the same input
should result in the same output each time.
And they should never have side effects, like altering the database state.
I should specify at least one of these statements in my function, 
or if none of these is the case, I can pursue another option, 
which I won't get into in this course.
I'll cancel out of this, and now in my code,
I'll type `DETERMINISTIC`, where I originally had `READS SQL DATA`.

~~~~sql  
CREATE DEFINER=`devuser`@`localhost` FUNCTION `calcAlbumLength`(albumName TEXT) RETURNS double
    DETERMINISTIC
BEGIN
	DECLARE length DOUBLE DEFAULT 0.0;

	SELECT 
    COUNT(*) * 2.5
INTO length FROM
    music.albumview
WHERE
    album_name = albumName;
        
	RETURN length;
END
~~~~

And I'll try to apply that.
This time it works, meaning it got saved and compiled without any errors.
Although this may work, it doesn't really make sense in this case 
unless the data in the tables will never change. 
If the data can change, then it won't be deterministic.
So I'll revert this code back to the way it was.
This is a really simple function It first counts 
the number of records in the _albumview_ for a single album.
Then the code multiplies this, by the static value, `2.5`.
If song length were part of our songs table, then we'd sum up those values.
So here, `COUNT(*) * 2.5`, 
I'm just mocking up an average length of 2.5 minutes per song.
That is selected into the length variable, which gets returned from this function.
What's kind of nice about functions is that 
we can use them in _select_ statements, and _where_ clauses.
I'll show you this in the workbench, so I'll open a SQL tab.
I'll select the distinct album name just in case this data has duplicates, 
and now, instead of adding another column name, 
I can call my function, passing it the album name column name.

~~~~sql  
SELECT distinct
    album_name, calcAlbumLength(album_name)
FROM
    music.albums
ORDER BY album_name;
~~~~

This means this function will get called for every record, 
with that record's album name, to calculate the album length.
I'll select from the _albums_ table, and order by album name.
I'll execute this:

![image76](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image76.png?raw=true)

And you'll see a grid displayed, first my album names listed alphabetically, 
and then the result of my function, a double value, representing the length, 
in minutes, of each album.
This gives you an idea of relative length if all the songs were a uniform recording size.
Now, let's see how we'd use this in Java code.
I'll go back to my prepared statement project in IntelliJ.
I'll be adding code to call this function in the **MusicCallableStatement** class.
First, I'll comment out the code that calls the stored procedure.
I'll use a block comment here, blocking out everything
from the _CallableStatement_ declaration, down to the end of the _forEach_ looping.
I'm going to call my function after the data is printed out,
so after `Main.printRecords`.

```java  
public class MusicCallableStatement {

    private static final int ARTIST_COLUMN = 0;
    private static final int ALBUM_COLUMN = 1;
    private static final int SONG_COLUMN = 3;

    public static void main(String[] args) {
      
        Map<String, Map<String, String>> albums = null;

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course08_CallableStatements/NewAlbums.csv";
        try (var lines = Files.lines(Path.of(pathName))) {          // Stream<String>

            albums = lines.map(s -> s.split(","))
                    .collect(Collectors.groupingBy(s -> s[ARTIST_COLUMN], 
                            Collectors.groupingBy(s -> s[ALBUM_COLUMN], 
                                    Collectors.mapping(s -> s[SONG_COLUMN], 
                                            Collectors.joining(
                                                    "\",\"",
                                                    "[\"",
                                                    "\"]"
                                            )))));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

        albums.forEach((artist, artistAlbums) -> {
            artistAlbums.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });
        });

        var dataSource = new MysqlDataSource();
  
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setDatabaseName("music");

        try (Connection connection = dataSource.getConnection(System.getenv("MYSQL_USER"), System.getenv("MYSQL_PASS"));) {
/*
            CallableStatement cs = connection.prepareCall("CALL music.addAlbumInOutCounts(?,?,?,?)");
          
            albums.forEach((artist, albumMap) -> {
                albumMap.forEach((album, songs) -> {
                    try {
                        cs.setString(1, artist);
                        cs.setString(2, album);
                        cs.setString(3, songs);
                        
                        cs.setInt(4, 10);
                        cs.registerOutParameter(4, Types.INTEGER);
                        cs.execute();
                        System.out.printf("%d songs were added for %s%n", cs.getInt(4), album);
  
                    } catch (SQLException e) {
                        System.err.println(e.getErrorCode() + " " + e.getMessage());
                    }
                });
            });
*/

            String sql = "SELECT * FROM music.albumview WHERE artist_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Bob Dylan");
            ResultSet resultSet = ps.executeQuery();
            Main.printRecords(resultSet);

            CallableStatement csf = connection.prepareCall(" ? = CALL music.calcAlbumLength(?) ");
            csf.registerOutParameter(1, Types.DOUBLE);

            albums.forEach((artist, albumMap) -> {
                albumMap.keySet().forEach((albumName) -> {
                    try {
                        csf.setString(2, albumName);
                        csf.execute();
                        double result = csf.getDouble(1);
                        System.out.printf("Length of %s is %.1f%n", albumName, result);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

Whether you're calling a stored procedure or a stored function, 
you still call _prepareCall_ on the connection. 
This time I'll assign it to a callable statement variable, named _csf_. 
I'll pass the string that calls the _calcAlbumLength_ function. 
In this case, I start with a `?=` followed by the keyword call, then the stored function, 
`music.calcAlbumLength` and that takes one parameter.
The first question mark is for the result of the function.
I only need to register the result once, 
and this is done just like an output parameter.
The returned result is index 1.
I'll use the `java.sql.Types` class to specify the type as a **double**.
Next, I'll loop through my data in the **Map**, so through the key, 
the _artist_, and the nested map, which has all the new album data. 
I'll loop through _albumMap_, but just the _albumName_ keys.
I'll include a _try_ clause. 
I'll set the function's parameter, index 2, to the _albumName_. 
What this means is, I'll be calling 
the _calcAlbumLength_ for each of the _bob dylan_ albums in my map. 
Next, I'll call _execute_ on the _csf_ statement.
I can get the result from the statement 
by simply calling one of the get methods on index 1.
I'll print this data out, so length of %s, 
that'll be the _albumName_ is `%.1f`, that'll be the record length. 
And next, the usual _catch_ clause.
Ok, so this code is similar to calling a stored procedure.
I'll run this now:

```html  
Bob Dylan : ["You're No Good","Talkin' New York","In My Time of Dyin'","Man of Constant Sorrow","Fixin' to Die","Pretty Peggy-O","Highway 51 Blues","Gospel Plow","Baby Let Me Follow You Down","House of the Risin' Sun","Freight Train Blues","Song to Woody","See That My Grave Is Kept Clean"]
Blonde on Blonde : ["Rainy Day Women","Pledging My Time","Visions of Johanna","One of Us Must Know (Sooner or Later)","I Want You","Stuck Inside of Mobile with the Memphis Blues Again","Leopard-Skin Pill-Box Hat","Just Like a Woman","Most Likely You Go Your Way (And I'll Go Mine)","Temporary Like Achilles","Absolutely Sweet Marie","Fourth Time Around","Obviously Five Believers","Sad-Eyed Lady of the Lowlands"]
0 songs were added for Bob Dylan
0 songs were added for Blonde on Blonde
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Blonde on BlondeBob Dylan      1              Rainy Day Women
Blonde on BlondeBob Dylan      2              Pledging My Time
Blonde on BlondeBob Dylan      3              Visions of Johanna
Blonde on BlondeBob Dylan      4              One of Us Must Know (Sooner or Later)
Blonde on BlondeBob Dylan      5              I Want You     
Blonde on BlondeBob Dylan      6              Stuck Inside of Mobile with the Memphis Blues Again
Blonde on BlondeBob Dylan      7              Leopard-Skin Pill-Box Hat
Blonde on BlondeBob Dylan      8              Just Like a Woman
Blonde on BlondeBob Dylan      9              Most Likely You Go Your Way (And I'll Go Mine)
Blonde on BlondeBob Dylan      10             Temporary Like Achilles
Blonde on BlondeBob Dylan      11             Absolutely Sweet Marie
Blonde on BlondeBob Dylan      12             Fourth Time Around
Blonde on BlondeBob Dylan      13             Obviously Five Believers
Blonde on BlondeBob Dylan      14             Sad-Eyed Lady of the Lowlands
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
Bob Dylan      Bob Dylan      8              Gospel Plow    
Bob Dylan      Bob Dylan      9              Baby Let Me Follow You Down
Bob Dylan      Bob Dylan      10             House of the Risin' Sun
Bob Dylan      Bob Dylan      11             Freight Train Blues
Bob Dylan      Bob Dylan      12             Song to Woody  
Bob Dylan      Bob Dylan      13             See That My Grave Is Kept Clean
java.sql.SQLException : Parameter number 1 is not an OUT parameter
    at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException()
    at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException()
    at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException()
    at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException()
    at com.mysql.cj.jdbc.CallableStatement.checkIsOutputParam()
    at com.mysql.cj.jdbc.CallableStatement.registerOutParameter()
    at com.mysql.cj.jdbc.CallableStatement.registerOutParameter()
    at .MusicCallableStatement.main()
```

But for this, I'm getting an error, _parameter number 1 is not an out parameter_.
This error's a bit confusing if you don't understand the problem.
I'm going to go up to the prepareCall method, and make a minor change.

```java  
public class MusicCallableStatement {

    private static final int ARTIST_COLUMN = 0;
    private static final int ALBUM_COLUMN = 1;
    private static final int SONG_COLUMN = 3;

    public static void main(String[] args) {
      
        Map<String, Map<String, String>> albums = null;

        String pathName = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course08_CallableStatements/NewAlbums.csv";
        try (var lines = Files.lines(Path.of(pathName))) {          // Stream<String>

            albums = lines.map(s -> s.split(","))
                    .collect(Collectors.groupingBy(s -> s[ARTIST_COLUMN], 
                            Collectors.groupingBy(s -> s[ALBUM_COLUMN], 
                                    Collectors.mapping(s -> s[SONG_COLUMN], 
                                            Collectors.joining(
                                                    "\",\"",
                                                    "[\"",
                                                    "\"]"
                                            )))));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

        albums.forEach((artist, artistAlbums) -> {
            artistAlbums.forEach((key, value) -> {
                System.out.println(key + " : " + value);
            });
        });

        var dataSource = new MysqlDataSource();
  
        dataSource.setServerName("localhost");
        dataSource.setPort(3335);
        dataSource.setDatabaseName("music");

        try (Connection connection = dataSource.getConnection(System.getenv("MYSQL_USER"), System.getenv("MYSQL_PASS"));) {

            String sql = "SELECT * FROM music.albumview WHERE artist_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "Bob Dylan");
            ResultSet resultSet = ps.executeQuery();
            Main.printRecords(resultSet);

            //CallableStatement csf = connection.prepareCall(" ? = CALL music.calcAlbumLength(?) ");
            CallableStatement csf = connection.prepareCall("{ ? = CALL music.calcAlbumLength(?) }");
            csf.registerOutParameter(1, Types.DOUBLE);

            albums.forEach((artist, albumMap) -> {
                albumMap.keySet().forEach((albumName) -> {
                    try {
                        csf.setString(2, albumName);
                        csf.execute();
                        double result = csf.getDouble(1);
                        System.out.printf("Length of %s is %.1f%n", albumName, result);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

I'll start and end the text in quotes, with an opening and closing curly brace.
I'll run the code again:

```html  
Bob Dylan : ["You're No Good","Talkin' New York","In My Time of Dyin'","Man of Constant Sorrow","Fixin' to Die","Pretty Peggy-O","Highway 51 Blues","Gospel Plow","Baby Let Me Follow You Down","House of the Risin' Sun","Freight Train Blues","Song to Woody","See That My Grave Is Kept Clean"]
Blonde on Blonde : ["Rainy Day Women","Pledging My Time","Visions of Johanna","One of Us Must Know (Sooner or Later)","I Want You","Stuck Inside of Mobile with the Memphis Blues Again","Leopard-Skin Pill-Box Hat","Just Like a Woman","Most Likely You Go Your Way (And I'll Go Mine)","Temporary Like Achilles","Absolutely Sweet Marie","Fourth Time Around","Obviously Five Believers","Sad-Eyed Lady of the Lowlands"]
===================
ALBUM_NAME     ARTIST_NAME    TRACK_NUMBER   SONG_TITLE     
Blonde on BlondeBob Dylan      1              Rainy Day Women
Blonde on BlondeBob Dylan      2              Pledging My Time
Blonde on BlondeBob Dylan      3              Visions of Johanna
Blonde on BlondeBob Dylan      4              One of Us Must Know (Sooner or Later)
Blonde on BlondeBob Dylan      5              I Want You     
Blonde on BlondeBob Dylan      6              Stuck Inside of Mobile with the Memphis Blues Again
Blonde on BlondeBob Dylan      7              Leopard-Skin Pill-Box Hat
Blonde on BlondeBob Dylan      8              Just Like a Woman
Blonde on BlondeBob Dylan      9              Most Likely You Go Your Way (And I'll Go Mine)
Blonde on BlondeBob Dylan      10             Temporary Like Achilles
Blonde on BlondeBob Dylan      11             Absolutely Sweet Marie
Blonde on BlondeBob Dylan      12             Fourth Time Around
Blonde on BlondeBob Dylan      13             Obviously Five Believers
Blonde on BlondeBob Dylan      14             Sad-Eyed Lady of the Lowlands
Bob Dylan      Bob Dylan      1              You're No Good 
Bob Dylan      Bob Dylan      2              Talkin' New York
Bob Dylan      Bob Dylan      3              In My Time of Dyin'
Bob Dylan      Bob Dylan      4              Man of Constant Sorrow
Bob Dylan      Bob Dylan      5              Fixin' to Die  
Bob Dylan      Bob Dylan      6              Pretty Peggy-O 
Bob Dylan      Bob Dylan      7              Highway 51 Blues
Bob Dylan      Bob Dylan      8              Gospel Plow    
Bob Dylan      Bob Dylan      9              Baby Let Me Follow You Down
Bob Dylan      Bob Dylan      10             House of the Risin' Sun
Bob Dylan      Bob Dylan      11             Freight Train Blues
Bob Dylan      Bob Dylan      12             Song to Woody  
Bob Dylan      Bob Dylan      13             See That My Grave Is Kept Clean
Length of Bob Dylan is 32.5
Length of Blonde on Blonde is 35.0
```

And scroll to the bottom of the output.
Here, you can see the code ended without any errors, 
and I get the album lengths back from executing my function this way.
The length of Bob Dylan is 32.5, and the length of blonde on blonde is 35.0.
What are the curly braces here, and why did this work?

```java  
CallableStatement csf = connection.prepareCall("{ ? = CALL music.calcAlbumLength(?) }");
csf.registerOutParameter(1, Types.DOUBLE);
```

Without the curly braces, the JDBC driver interprets the string literally, 
so it wants to execute a _call_ command on the database.
But in most databases, you don't execute stored functions, 
using the _call_ keyword, like you would for a stored procedure.
Because it thinks you're calling a stored procedure, 
the starting question mark is simply ignored.
That's why we got the error in the first try,
_the parameter number `1` is not an out parameter_,
because the driver didn't factor in the first question mark.
Using the curly braces informs the JDBC driver
that you want it to perform extra processing or translation.
These curly braces are called an **escape sequence**,
and they're supported in special cases.

**JDBC Escape sequences** provide a way to execute database-specific operations, 
in a more consistent and portable manner, across different database systems.
They're enclosed in curly braces `{}`, and are used within SQL statements.
There are certain things that aren't database agnostic.

* This includes date, time, and timestamp literals.
You saw this, when I gave you a specific format for the date, in MySQL, in a challenge.
The default date format is different, depending on the database.
* Scalar functions such as numeric, string, and data type conversion functions may differ. 
For example, the function to transform a string 
to uppercase may have a different name in various databases. 
* Escape characters for wildcards used in _LIKE_ clauses may be different between databases. 
* And the execution of stored procedures and functions may be performed with either a _call_, 
or _execute_ method, or some other keyword. 
Similarly, the execution of a function may be performed differently across databases.
For these features, and a few others, you can use an escape sequence, 
usually with special syntax specified.
In the case of stored functions, the key is the question mark equals _CALL_, 
part of the string.
This informs the JDBC driver that you want it to prepare the code, to execute a function.

The other items listed on this list have special indicators as well, 
and you can find these by googling JDBC escape sequences, if you're interested.
You might be wondering why I didn't need the escape sequence 
for the stored procedures we looked at.
In general, the keyword _call_ is supported by most databases for stored procedures.
This means I could go over to the MySQL workbench,
and execute the _call_ command with the procedure name 
and specified parameters in a SQL Query editor.
I could do the same in most RDBMS's, 
so the use of the escape sequences in a stored procedure _call_ is often optional.
To execute of a function isn't done with the command _call_ in MySQL, 
so as you saw, we needed to escape this string, which allowed it to run successfully.

Stored procedures are designed for executing multiple operations, 
modifying data, and enforcing business logic.
In contrast, stored functions are primarily used for calculations and data transformations.
Both enhance code reusability, improve performance, 
and promote encapsulation of complex database operations.
And even better, they simplify the JDBC code you have to write.
Now that you're a little familiar with stored procedures and stored functions, 
I've got a challenge for you coming up next.
</div>

## [l. CallableStatement Challenge](https://github.com/korhanertancakmak/JAVA/tree/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/Course10_CallableStatementChallenge/README.md#callablestatement-challenge)
<div align="justify">

In this challenge, I want you to revisit the _storefront_ database, 
which you've worked on for the other challenges in previous sections.
You'll be creating a stored procedure named _addOrder_.
Alternatively, you can load the procedure included 
in the package folder, in a file called `addOrder.sql`.
The stored procedure has four parameters.
Two are input parameters: 

* There's _OrderDate_, a **DATETIME** parameter. 
* And also, _OrderDetails_, a **JSON** parameter, 
which should contain an array of json strings, 
that each have the item description and quantity.

Two are output parameters:

* There's the _OrderId_, an **INT** parameter, 
which will contain the order id of the inserted order.
* And the second is, _InsertedRecords_, another **INT** parameter, 
which returns then number of detail records, inserted for that order.

This stored procedure inserts the order and its details, 
if it can't find an existing order, for the date time specified.
You can use the _addAlbum_ procedure in the _music_ schema, 
which we used in a previous section, as a starting point, 
if you do want to try to create this procedure, on your own.
Again, this procedure is in the package folder
if you don't want this additional SQL challenge.

Your Java code should use a _CallableStatement_ to call the _addOrder_ procedure.
You'll pass a `java.sql.TimeStamp` as the first parameter.

![image77](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image77.png?raw=true)

A **TimeStamp** and **DateTime** field are often interchangeable in many databases.
For this, you'll need to transform a string, into a `java.sql.TimeStamp`.
You can do this with a **DateTimeFormatter**, and the use of **LocalDateTime**.
Next, you'll pass a string as the second parameter.
This will be the json string, representing an array of order details.
The input for the array of details, as a json string, should look as shown above.
Finally, you'll need to register two output parameters, 
both ints, for the order id, and the number of order detail records inserted.

Make sure you delete the orders in a MySQL Workbench session, 
which were inserted in the previous challenge.

![image78](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image78.png?raw=true)

This SQL code shows you how to _delete_ the order and its details.
Remember, we have a _cascade delete_ set up, 
so deleting the order, will delete any related records, 
in the order detail table as well.
I'm also showing the DDL statements, 
which will reset the _AUTO_INCREMENT_ to `1` on both tables.
You can execute these statements 
if you want your first order to have an id of `1` again.
The DDL statements are optional.

![image79](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image79.png?raw=true)

Earlier in the course, in a previous challenge,
the File Writing Challenge, in the Input-Output (I/O), 
working with **Files** in Java, we created a _toString_, 
IntelliJ template, and called it **JsonBuilder**, 
which used the **StringJoiner**.
You could leverage this to create the JSON string parameter 
if you walked through that exercise with me, 
and also created this template in your IntelliJ environment.
This means you can go through the process to add a _toString_ method, 
but select the **JsonBuilder** template instead,
adding this to the order detail record.
Alternately, you could write your own method to do this.

![image80](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image80.png?raw=true)

Use the **DateTimeFormatter** with the pattern shown here.
You'll notice that I'm using a _U_, 
where I normally would use a _Y_, for the digits in the year.
I haven't really covered Using the _u_ pattern, versus the _y_ pattern, 
for the year in a date pattern.
This is actually kind of a complicated subject, 
so I've included a link to an interesting `stackoverflow.com` discussion, 
if you want to learn more, and dig into the complexities, 
[here](https://stackoverflow.com/questions/41177442/uuuu-versus-yyyy-in-datetimeformatter-formatting-pattern-codes-in-java).
I'm suggesting you use the _u_ pattern in this challenge, 
because it causes the parsing to fail with an exception, 
on the one bad date in our data, when using what's called strict parsing.
I'll show you what happens in both cases
when I walk through my own code.

![image81](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image81.png?raw=true)

You can create a **LocalDateTime**, using the **DateTimeFormatter**, 
and then transform it to a SQL **TimeStamp** type.
A **TimeStamp** field can be used for a SQL parameter of type **DateTime**.
</div>

## [m. Java Persistence API]()

### Introduction to JPA and ORM
<div align="justify">

So far, in this section of the course, I've focused on JDBC as the means for communicating with a database.
This is for good reason, since it's part of Java's Standard Edition implementation, 
or core Java in other words.
We've walked through writing SQL statements, and executing them for the usual _CRUD_ functionality.
I've also demonstrated that this work can be more tightly-knit to the database server,
by using stored procedures that have been written, to do some work for you.
Even so, you're still in the business of having to write some database interaction code.

The alternative is something called the **Java Persistence API**, or **JPA** for short.
JPA is a specification.
There's no default implementation of the specification,
provided in Java's Standard Edition.
That being said, I still want to cover the topic briefly.
There's a great chance, you'll be working on an application that uses some form of it.
Some popular implementations are **Hibernate**, **Spring JPA**, and **EclipseLink**, 
to name a few.
These are called **JPA Providers**.
In 2019, the _Java Persistence API_ officially changed its name to _Jakarta Persistence API_.
It was previously part of Java's Enterprise Edition, which has since become open source.
_The Persistence API_ became one of the Jakarta projects.
The link [here](https://jakarta.ee/specifications/persistence/3.1/apidocs/jakarta.persistence/module-summary.html) 
is Jakarta's API documentation for JPA, 
and unless you're supporting legacy code, you should start here.

A few of the key concepts of the JPA specification include:

* A technique known as **Object-Relational Mapping, or ORM**. 
* **The Entity** (which is the mapped object), 
and the **Entity Manager** which manages lifecycles of these entities.
* **The Persistence Context** is a special cached area where the entities exist.

You can think of JPA as an extra layer of functionality, 
between your application code and the JDBC code you saw in the last sections.
**Object-Relational Mapping** is a method of mapping database tables defined by columns, 
to a class with correlated fields, and setters and getters.
A record in a table then becomes an instance of one of these classes.
**ORM** frameworks often automate the process of object relational mapping.

* They **Simplify code**, reducing the need to write boilerplate classes. 
* They **Simplify database operations**, hiding the complexities of SQL queries, 
and handling database interactions for you.
* They **Provide code portability** across different database systems.
* _ORM_ Frameworks also **Encourage a more structured and organized approach** to data access.

Typically, an **Entity** is a class that represents a table in a relational database.

![image82](https://github.com/korhanertancakmak/JAVA/blob/master/src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_18_WorkingWithDatabases/images/image82.png?raw=true)

Each **Entity** instance corresponds to a row in that table.
Each entity is annotated with metadata, to instruct JPA providers, 
how to map its fields to a table's columns, relationships to other tables,
and lifecycle management techniques to use.
The example above is relatively simple of an annotated class **Artist**,
which would be an _entity_ for our **artists** table in the _music_ database, 
that we've been working with.
In this example, the annotation **Entity** describes this class as the **Entity** type.
A framework implementing JPA, will identify entities by this annotation.
The annotation that follows next is table, further describes the table name,
so that is _artists_ in this case.
Next, the fields in this class also have annotations 
which describe their database correlated information.
So I have _Id_ and that's the _artist_id_.
I include the column annotation, which specifies the ID's column name.
Next, I describe the field, _artistName_, as mapping to the column name, _artist_name_.
After this, I've declared a list of _albums_, for this artist.
Above this field, I specify information about the relationship.
I use the _one to many_ annotation, then the _join column_,
with the joined id so _album_id_.

The **JPA Provider** implements the _entity manager_.
An _entity_ can exist in a managed state, managed by the **Entity Manager**.
Or it can exist in a detached state outside an **Entity Manager**.
A detached entity can then be merged into an **EntityManager**
if a commit is needed to a persistence layer.

| Operation  | Method   | Description                                                                                                                                                                                                     |
|------------|----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| create     | persist  | Makes a detached instance managed and persistent.                                                                                                                                                               |
| read       | find     | This method searches for an entity of the specified class and primary key, and returns an instance that will be managed and persistent                                                                          |
| update     | merge    | If an entity is managed, updates will be propagated automatically to the persistence layer, on a commit. If the entity is not yet managed, a merge method will make the entity managed and persist the changes. |
| delete     | remove   | Removes the entity instance from management, and executes a delete on the persistence layer if the instance is managed (not detached).                                                                          |

There are some key methods on the **EntityManager** interface to know,
that line up with the _CRUD_ operations.
The _persist_ method makes a detached instance managed, 
and it's this instance's state will get persisted on a commit.
The _find_ method searches for an entity of the specified class 
using the primary key, in the persistent layer.
It returns an instance that will be managed and automatically persisted.
If an entity is managed, updates will be propagated automatically to the persistence layer, on a commit.
If the entity is not yet managed, a _merge_ method will make the entity managed.
The _remove_ method removes the entity instance from management,
and executes a _delete_ on the persistence layer.
I'll be walking through examples of these methods coming up.

The persistence context has multiple purposes:

* It tracks the lifecycle state of managed entities.
These states include new, managed, detached, and removed, 
reflecting any changes you make to their properties. 
* It synchronizes changes made to managed entities with the database. 
This happens automatically when a transaction is committed or flushed, 
ensuring the database state reflects the state of your in-memory objects. 
* It performs identity management, ensuring unique entity identity within a transaction. 
Even if you create duplicate objects with the same identifier,
the persistence context recognizes and manages them as a single entity. 
* It acts as a cache, reducing database roundtrips 
by keeping frequently accessed entities in memory for faster retrieval.

The objective of this section and the ones that follow aren't to give you 
a deep dive into _JPA_.
Instead, I want to give you a simple introduction to this architecture, 
which is used by so many frameworks.
Let's get back to some code, and start exploring these concepts a little bit, 
in a concrete way.

```java  
public class Main {

    public static void main(String[] args) {

    }
}
```

I've created the usual **Main** class set up.
Next, I need the jakarta JPA module.
I could get this module from **Maven**, but I also need a _JPA Provider_.
For the provider, I'll use **Hibernate**, and that comes packaged with the JPA,
so I can just include that one module.
I'll get this module from **Maven**.
In the next section of the course I'll be talking more about **Maven**,
so just follow along with me here.
I'll type in `org.hibernate.orm`, and press enter.
I'll get this error but will give it a moment, and a list should pop up.
Rather than scroll down and look for what I want, 
I can continue to type in this field, 
so I'll add `:hibernate-core` in this input field, 
after the `org.hibernate.orm`.
It's important that you don't have any spaces before or after the colon.
Now I'll see the versions of this module listed, 
and I want to make sure I get one that's the latest version, 6 or higher.
For me, at the time of this recording, its **6.5.0 Final**.
I'll select that.
Another dialog pops up.
I'll make sure transitive dependencies are checked in that dialog and click ok.
Now, it's important to examine the classes in the version you picked, 
to verify that you have the `jakarta.persistence-api` in this set of classes, 
and that it's at least version `3.1`.
In addition, you want to verify that the module you pick has _hibernate-core_ there,
and it's at least version `6`.
Make sure you apply these changes on this screen, by selecting the ok or apply button.
If you aren't seeing jakarta's persistence `3.1` jar there, you should try these steps again,
or alternately manually add jakarta's persistence api 3.1 or higher.
Since things change more than they stay the same, let me just quickly show you 
how to add that module separately.
If you've verified this package in your module, 
you don't need to do this next step and can skip adding this module.
Again, I'll open the module settings, with Libraries selected, and click the `+` button.
And select _From Maven_.
I'll type `jakarta.persistence:` in there.
I could hit enter here, which gives me that error, or optionally I can do a search.
When you press enter, it's expecting a fully qualified coordinate, or module identifier,
and in these cases, we're supplying only a part.
The search is a bit more friendly, and that's the search icon next to this field.
Hitting that rather than enter doesn't give me an error, 
and it displays information about how many matches it found, 
and after a slight pause, will display them.
Now I see the different versions of this package, and I'll pick the `3.1.0` version.
You probably don't want to pick any version with **M** or **B** in the suffix,
these are milestone and beta versions, which may not be the most stable versions.
**RC** stands for **release candidate**, so again, I'd just stick to one without those suffixes.
This module has a single jar file in it, and you'll see that displayed in the classes list,
after you add this.
Again, click OK or apply.
In my case, I don't need this jar file in my dependencies twice, 
so I'll simply open module settings again to the _Library_ section, 
and remove this `jakarta.persistence` module here.
Again, it's part of the hibernate module I included, so for me, it's not necessary.
The purpose of **Maven** is to help us sort these dependencies out, but again, 
I'll cover that in a later section of the course.
Now that we've got the libraries we need, we can get back to coding.
I'll start with creating a couple of entities for the _music_ database.
There are tools, including a **Hibernate** plugin for IntelliJ, 
that will generate entities for you, based on your database schema.
This plugin's not available however, for the community edition, which we're using.
Since we only have three tables, I'll create these manually, 
which is a good exercise anyway, as you're learning.

```java  
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "artists")
public class Artist { 
    
    @Id
    @Column(name="artist_id")
    private int artistId;
  
    @Column(name="artist_name")
    private String artistName;
  
    public Artist() {
    }
}
```

So first, I'll create the **Artist** in the same package.
To make this an entity,
I simply use the annotation, **Entity**.
IntelliJ should be able to resolve this annotation and include an import. 
For me, though, I have to select **Entity** by double-clicking, and it adds the import.
This is part of the package, `jakarta.persistence`, 
which is included in the **hibernate core** package, as I mentioned before.
If I hover over the entity annotation, I can see how IntelliJ resolved it.
If you don't see `jakarta.persistence`, then you should go back to the installation of the module.
Specifically, you really don't want to see `javax.persistence`.
This `jakarta.persistence` contains all the annotations, 
and the interfaces for the JPA specification.
An entity usually has a relationship to a table, so I'll set that up.
I'll add the **Table** annotation, after **Entity**,
but before the class declaration. 
Here I'll specify the name of the table in the music schema which is _artists_.
You can see the import was not added automatically, 
so I'll just click it and do an ALT-ENTER 
and ensure I choose the `jakarta.persistence.Table` like this.
My artist table only has two columns,_artist_id_ and _artist_name_, 
so I'll set up two fields on this class, to represent those columns.
I'll start with the annotation, _Id_.
Every database entity requires this on one field, which represents the primary key. 
I'll map the field to the column name, _artist_id_, 
which is the primary key for _artists_. 
I'll fix the imports in a bit. 
And here I'll have my declaration for the field name, so private `int artistId`.
And again, I'll map the next field, this time to the _artist_name_ column.
I'll declare this field as a **string**, and call it _artistName_.
As you saw, the imports for _Id_ and _Column_ were added automatically for me.
I need some constructors and getters and setters, 
so I'll generate these, starting with the constructors.
All JPA entities are required to have a no args constructor, so I'll add that first.
I'll pick constructor from the _Generate_ menu.

```java  
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "artists")
public class Artist { 
    @Id
    @Column(name="artist_id")
    private int artistId;
  
    @Column(name="artist_name")
    private String artistName;
  
    public Artist() {
    }
  
    public Artist(String artistName) {
        this.artistName = artistName;
    }
  
    public Artist(int artistId, String artistName) {
        this.artistId = artistId;
        this.artistName = artistName;
    }
  
    public String getArtistName() {
        return artistName;
    }
  
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
  
    @Override
    public String toString() {
        return "Artist{" + 
                "artistId=" + artistId + 
                ", artistName='" + artistName + '\'' + 
                '}';
    }
}
```

I'll click on the select _None_ button at the bottom of this dialog, as the option.
That generates my no args constructor.
I'll repeat this process for a second constructor, but in this case, 
I'll select _artistName_ in the dialog, and click _OK_ to create a one argument constructor.
That generates a constructor with the _artistName_ as the only parameter.
I'll repeat this one last time, so again picking constructor from the _Generate_ menu.
In this case, I'll select both fields, then click the ok button.
That's my third and last constructor for this class.
Now, I'll add a getter and setter, selecting that combo from the _Generate_ menu.
I'll pick just the _artistName_, since I don't need these methods for _id_.
IntelliJ generates the _getArtistName_ and _setArtistName_ methods for me.
Finally, I'll include a _toString_ method, and again I'll generate that, 
using Alt+Insert, and picking toString from the _Generate Menu_ shown.
The template might be set to the **JSONBuilder** template, 
from the last challenge, as it was for me.
I'll instead choose the **String concat** template, 
from the select list here I'll select both fields in the next dialog, and hit ok.
And we are done.
I've created my first JPA Entity.
It's really just an annotated pojo.
In the next section, we'll use this entity 
to communicate with the artist table in the database.
</div>

### JPA in Action
<div align="justify">

I've created the first **Entity**, the **Artist** class,
which mirrors the table in the _music_ schema, and has an ID and an artist name.
Before I can use the entity, I need to do a little setup with my JPA provider.
The **Hibernate** documentation provides samples for different types of setups.
In this case, I want to use the JPA version of Hibernate, 
so I'll be creating a `persistence.xml` file, in a `META-INF` folder,
which needs to be on the class path.
The xml properties set in this file will define information 
to create a persistence context.
This context gets created by **Hibernate**, 
and you can think of this as a special managed storage place for entities,
that includes a connection to the database.
The `META-INF` folder is usually at the root of a web application's folder.
In this case, I'll just add it under the package folder for testing.
What I'll do is create the file which will initially be empty, 
and then paste the contents in. 
Either method will work. 
Selecting the package folder in the project panel, 
I'll select _New File_, then enter `META-INF/persistence.xml`.
I'll paste the xml code in there.

~~~~sql  
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
             version="2.0">

    <persistence-unit name="Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music">

        <properties>
            <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver" />
            <property name="jakarta.persistence.jdbc.url"    value="jdbc:mysql://localhost:3335/music" />
            <property name="jakarta.persistence.jdbc.user"   value="devUser" />
            <property name="jakarta.persistence.jdbc.password" value="your-password" />
        </properties>

    </persistence-unit>
</persistence>
~~~~

The first thing you'll see is that the root node of this xml document is persistence,
and there are some namespaces and xml specifications set up there.
This is just part of the standard configuration for this type of xml document.
Next, a child node is included, and it's named _persistence-unit_.
A persistence unit needs a unique name, 
so I've called this one the same as my package

`Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music`

But it can be anything you want there.
Next, as part of this unit, I need the properties that will set up the connection to the data.
In my case, this will consist of connection details to the MySQL driver.
Notice that the property names are specifically referring to `jakarta.persistence.jdbc` keys.
This is to avoid conflict with legacy code, 
which might use key names like `connection.driver` as an example.
These are the recommended key names for Jakarta's JPA.
So the first is the driver, and the value in this case is the MySQL driver,
that came with the jdbc driver jar file we included in this project, in the last section.
After this, I've got name and value pairs for the database url, the username, and the password.
Make sure you change the password in this file to be the one you created for your devUser.
You might wonder why it's ok to include the password in this file.
This file usually will be behind a firewall on a server node, 
with access controlled by the operating system privileges.
This means it's less of a concern than hard-coding a password in java source code, for example.
I'll save this and close it.
This file needs to be on the class path, 
so I'll edit the run configuration for my **Main** class.
The easiest way to do this is right-click the run arrow in the gutter, 
next to the _main_ method.
There I'll see _Modify Run Configuration_ as an option, and I can select that.
From this screen, there's a bit of text on the right of the build 
and run pane, and it says _Modify Options_. 
I can click that. 
From the menu of options that's displayed, I'll select _modify classpath_.
After selecting this, I'll now see the _Modify classpath_ section on the edit configuration form.
I'll select the _plus_ sign on this section's embedded menu bar, then I'll select _Include_, 
to include a new path on the classpath, and I'll navigate to
the `META-INF` folder under the source directory.
And I'll hit ok, I should now see that directory in the classpath options.
I'll hit ok to close this dialog.
Now, it's time to put all these pieces together.
Getting back to the _main_ method in the **Main** class,
I'll start with a _try-with-resources_ statement.

```java  
public class Main {

    public static void main(String[] args) {

      String persistenceUnitName = "Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music";
      try (var sessionFactory =
                   Persistence.createEntityManagerFactory(persistenceUnitName);
           EntityManager entityManager = sessionFactory.createEntityManager();
      ) {

        var transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(new Artist("Muddy Water"));
        transaction.commit();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
}
```

The first thing I need is an **EntityManagerFactory**.
This serves as a factory for producing **EntityManager** instances. 
I get this, by calling _createEntityManagerFactory_ on a **Persistence** class 
passing it the _persistenceUnitName_, that I declared in my `persistence.xml` file.
This process creates the persistence context, that establishes database connections, 
and manages connection pools for efficient resource usage.
Once I have this factory class, I can get an **EntityManager** from that object, 
by calling _createEntityManager_ on that.
Both the _EntityManagerFactory_, and the **EntityManager** are auto-closeable,
so putting them in the _try-with-resources_ block means they'll be closed automatically. 
I'll be adding the code in the block in a moment. 
For now, I'll complete this with a _catch_ block.
And print the stacktrace of any exception.
You might be thinking at this point that 
this code doesn't really feel simpler, than JDBC code.
Frameworks usually take a little bit of work up front, to get them configured, 
but you'll see many advantages, once you get that work behind you.
Inside the _try_ block, I'll get a _transaction_ from the _entityManager_.
It's a good idea to include any changes to a managed entity, within a transaction,
giving you the opportunity to test the results, 
and roll back if the results aren't what you expect.
Once I have a _transaction_ instance, I can call _begin_ on that.
Next, I'll call _persist_ on my entity manager, 
and pass it a new instance of the **Artist** class, 
using the constructor that takes artist name. 
In this case, I'll pass `Muddy Water`. 
I'm purposely entering a typo in the name,
it should really be `Muddy Waters`, 
and that's so I can demonstrate an update in a little bit. 
Lastly, I want to commit the transaction.
In this code, there's no reference to any SQL code, 
and no explicit reference to database interactivity.
Before I run this, I want to open a MySQL Workbench session, 
using the development session I've used throughout this section.
In the sql edit pane, I'll write a select query 
that will list the artists by descending artist id.

~~~~sql  
SELECT * FROM music.artists ORDER BY artist_id DESC;
~~~~

I'll execute that.
My last two artists are two that I added previously, `Bob Dylan`, and `Neil Young`, 
and then I'll have the `Chemical Brothers`, with _artist_id_ `201`.
Your own list may be a bit different, based on the number of times you executed code, 
or if you explored a bit on your own.
To get back on the same page, let's delete all the artists we added,
that have an id greater than `201`.

~~~~sql  
DELETE FROM music.artists WHERE artist_id > 201;
~~~~

I'll type in a delete statement that deletes from `music.artists`,
where _artist_id_ is greater than `201`.
I can execute that statement by highlighting it and clicking the lightning bolt.
After executing it, I'll delete it from my SQL edit panel here.
I'll rerun the select statement, 
and now `Chemical Brothers` should be my last artist.
To make sure my next id will be `202`, 
I'll execute a DDL statement to alter the auto increment.
I'm doing this because it's likely it's been updated, 
because of the adds and deletes we did over the course of several sections.
I really just don't want any gaps in my IDs,
and it'd be nice if we were all on the same page.

~~~~sql  
ALTER TABLE music.artists AUTO_INCREMENT = 202;
~~~~

I'll ALTER The table `music.artists` and make _AUTO_INCREMENT_ equals `202`.
I can highlight just this one statement, and execute just this, which I'll do now.
I'll see that in the action output.
I'll remove that statement from my edit pane here, 
so I don't accidentally execute it again.
If I did have a record with an ID greater than this number, 
it wouldn't work, but since my last id was `201`, 
so this shouldn't be a problem in this case.
I'll jump back over to IntelliJ, and run my code there:

```html  
May 01, 2024 11:07:13 PM org.hibernate.jpa.internal.util.LogHelper logPersistenceUnitInformation
INFO: HHH000204: Processing PersistenceUnitInfo [name: Section_18_WorkingWithDatabases.Course11_IntroductionJPA.music]
May 01, 2024 11:07:13 PM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 6.5.0.Final
May 01, 2024 11:07:14 PM org.hibernate.cache.internal.RegionFactoryInitiator initiateService
INFO: HHH000026: Second-level cache disabled
May 01, 2024 11:07:14 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using built-in connection pool (not intended for production use)
May 01, 2024 11:07:14 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: Loaded JDBC driver class: com.mysql.cj.jdbc.Driver
May 01, 2024 11:07:14 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001012: Connecting with JDBC URL [jdbc:mysql://localhost:3335/music]
May 01, 2024 11:07:14 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=devUser}
May 01, 2024 11:07:14 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 01, 2024 11:07:14 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH10001115: Connection pool size: 20 (min=1)
May 01, 2024 11:07:15 PM org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator initiateService
INFO: HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
May 01, 2024 11:07:15 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:mysql://localhost:3335/music]
```

I'll get a bunch of **hibernate** messages printed out.
About midway down, you'll see the connection properties, 
with the password masked with asterisks, and the username, devUser.
Then you'll see _Autocommit_ mode is **false**, connection pool size is `20`.
All of this is automatically created and set up for you by **hibernate**.
What I don't see is any real confirmation that data was inserted.

~~~~sql  
SELECT * FROM music.artists ORDER BY artist_id DESC;
~~~~

I'll switch back over to MySQL Workbench and re-execute my select query.
And there at the top of the result grid with an _artist_id_ of `202`, is _Muddy Water_.
This confirms that the hibernate code executed an insert, 
and this artist was persisted successfully.
And I didn't have to write a single line of SQL code in my Java code to make it happen.
If I changed databases, I'd just need to update the `persistence.xml` file, 
and that would be it.
I'm going to edit this file, so I'll again go back to IntelliJ.
I'll open that xml file up, and I'll add another property in the persistence unit section.

~~~~sql  
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
             version="2.0">

    <persistence-unit name="Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music">

        <properties>
            <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver" />
            <property name="jakarta.persistence.jdbc.url"    value="jdbc:mysql://localhost:3335/music" />
            <property name="jakarta.persistence.jdbc.user"   value="devUser" />
            <property name="jakarta.persistence.jdbc.password" value="abc123" />
            <property name="hibernate.show_sql" value="true" />
        </properties>

    </persistence-unit>
</persistence>
~~~~

Here, the property name is `hibernate.show_sql`, and the _value_ is **true**,
which will show the SQL code in the output.
I'll save that and rerun my code:

```html  
May 01, 2024 11:11:56 PM org.hibernate.jpa.internal.util.LogHelper logPersistenceUnitInformation
INFO: HHH000204: Processing PersistenceUnitInfo [name: Section_18_WorkingWithDatabases.Course11_IntroductionJPA.music]
May 01, 2024 11:11:56 PM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 6.5.0.Final
May 01, 2024 11:11:56 PM org.hibernate.cache.internal.RegionFactoryInitiator initiateService
INFO: HHH000026: Second-level cache disabled
May 01, 2024 11:11:56 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using built-in connection pool (not intended for production use)
May 01, 2024 11:11:56 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: Loaded JDBC driver class: com.mysql.cj.jdbc.Driver
May 01, 2024 11:11:56 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001012: Connecting with JDBC URL [jdbc:mysql://localhost:3335/music]
May 01, 2024 11:11:56 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=devUser}
May 01, 2024 11:11:56 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 01, 2024 11:11:56 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH10001115: Connection pool size: 20 (min=1)
May 01, 2024 11:11:57 PM org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator initiateService
INFO: HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Hibernate: insert into artists (artist_name,artist_id) values (?,?)
May 01, 2024 11:11:57 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:mysql://localhost:3335/music]
```

Notice, the third from the last statement.
In my case, the text is a different color from the others.
It starts with Hibernate colon, then it shows me the insert statement
that this _persist_ method used.
Now I'll have two _Muddy Water_ artists in my table, 
which I can confirm by switching to MySQL workbench,
and executing that select statement again.
I don't really want two _muddy water_ artists in my database, 
so I'll delete artist ID `203`.
This time, though, I'll use **Hibernate's JPA** code to do it.
Back to IntelliJ, I'll first comment out the persist statement.
Next, I want to retrieve the artist from the database, whose id is `203`.

```java  
public class Main {

    public static void main(String[] args) {

      String persistenceUnitName = "Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music";
      try (var sessionFactory =
                   Persistence.createEntityManagerFactory(persistenceUnitName);
           EntityManager entityManager = sessionFactory.createEntityManager();
      ) {

        var transaction = entityManager.getTransaction();
        transaction.begin();
        
        Artist artist = entityManager.find(Artist.class, 203);
        System.out.println(artist);
        //entityManager.persist(new Artist("Muddy Water"));
        transaction.commit();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
}
```

I do this with the _find_ method on **EntityManager**.
This method takes at least two arguments,
the first is the entity class, and the second is the id.
So I'll pass `Artist.class`, and `2O3` as the arguments. 
I'll print the instance I get back, running this code:

```html  
May 01, 2024 11:17:07 PM org.hibernate.jpa.internal.util.LogHelper logPersistenceUnitInformation
INFO: HHH000204: Processing PersistenceUnitInfo [name: Section_18_WorkingWithDatabases.Course11_IntroductionJPA.music]
May 01, 2024 11:17:07 PM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 6.5.0.Final
May 01, 2024 11:17:07 PM org.hibernate.cache.internal.RegionFactoryInitiator initiateService
INFO: HHH000026: Second-level cache disabled
May 01, 2024 11:17:07 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using built-in connection pool (not intended for production use)
May 01, 2024 11:17:07 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: Loaded JDBC driver class: com.mysql.cj.jdbc.Driver
May 01, 2024 11:17:07 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001012: Connecting with JDBC URL [jdbc:mysql://localhost:3335/music]
May 01, 2024 11:17:07 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=devUser}
May 01, 2024 11:17:07 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 01, 2024 11:17:07 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH10001115: Connection pool size: 20 (min=1)
May 01, 2024 11:17:08 PM org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator initiateService
INFO: HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Hibernate: select a1_0.artist_id,a1_0.artist_name from artists a1_0 where a1_0.artist_id=?
Artist{artistId=203, artistName='Muddy Water'}
May 01, 2024 11:17:08 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:mysql://localhost:3335/music]
```

There are a couple of output statements, 
again near the last lines of this **hibernate** output, that I want you to see.
I should see the sql statement used to retrieve the data,
followed by the artist entity printed out.
And you can see it there, a _select_ statement.
This statement is using something called an alias, 
which is just a temporary name given to the table, 
but it's selecting the _artist_id_ and _artist_name_ from the _artists_ table, 
based on an _artist_id_ parameter.
This is followed by the artist's _toString_ method.
So that confirms, I was able to read artist 2O3's data from the database, 
using the _find_ method.
Now, I'll delete this artist, using the _remove_ method.

```java  
public class Main {

    public static void main(String[] args) {

      String persistenceUnitName = "Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music";
      try (var sessionFactory =
                   Persistence.createEntityManagerFactory(persistenceUnitName);
           EntityManager entityManager = sessionFactory.createEntityManager();
      ) {

        var transaction = entityManager.getTransaction();
        transaction.begin();
        
        Artist artist = entityManager.find(Artist.class, 203);
        System.out.println(artist);
        entityManager.remove(artist);
        //entityManager.persist(new Artist("Muddy Water"));
        transaction.commit();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
}
```

I just pass the artist instance to that method.
I'll run the code with this change.

```html  
May 01, 2024 11:22:09 PM org.hibernate.jpa.internal.util.LogHelper logPersistenceUnitInformation
INFO: HHH000204: Processing PersistenceUnitInfo [name: Section_18_WorkingWithDatabases.Course11_IntroductionJPA.music]
May 01, 2024 11:22:09 PM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 6.5.0.Final
May 01, 2024 11:22:09 PM org.hibernate.cache.internal.RegionFactoryInitiator initiateService
INFO: HHH000026: Second-level cache disabled
May 01, 2024 11:22:10 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using built-in connection pool (not intended for production use)
May 01, 2024 11:22:10 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: Loaded JDBC driver class: com.mysql.cj.jdbc.Driver
May 01, 2024 11:22:10 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001012: Connecting with JDBC URL [jdbc:mysql://localhost:3335/music]
May 01, 2024 11:22:10 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=devUser}
May 01, 2024 11:22:10 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 01, 2024 11:22:10 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH10001115: Connection pool size: 20 (min=1)
May 01, 2024 11:22:11 PM org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator initiateService
INFO: HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Hibernate: select a1_0.artist_id,a1_0.artist_name from artists a1_0 where a1_0.artist_id=?
Artist{artistId=203, artistName='Muddy Water'}
Hibernate: delete from artists where artist_id=?
May 01, 2024 11:22:11 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:mysql://localhost:3335/music]
```

I've got the additional delete sql statement printed out, after the artist _toString_.
If I pop back over to MySQL Workbench, and execute the select, 
I should have only one _Muddy Water_ record, for artist id `202`.
This query confirms that the **hibernate** code deleted artist id `2O3`.
I really want the artist's name to be _Muddy Waters_ with an _s_, 
on the end of _Water_, so I next want to update artist `2O2`.
Back to IntelliJ, I'll again edit my code in the _main_ method.

```java  
public class Main {

    public static void main(String[] args) {

      String persistenceUnitName = "Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music";
      try (var sessionFactory =
                   Persistence.createEntityManagerFactory(persistenceUnitName);
           EntityManager entityManager = sessionFactory.createEntityManager();
      ) {

        var transaction = entityManager.getTransaction();
        transaction.begin();
        
        //Artist artist = entityManager.find(Artist.class, 203);
        Artist artist = entityManager.find(Artist.class, 202);
        System.out.println(artist);
        artist.setArtistName("Muddy Waters");
        //entityManager.remove(artist);
        //entityManager.persist(new Artist("Muddy Water"));
        transaction.commit();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
}
```

This time I'll change the find method, to find artist id `2O2`,
And I'll comment out the _remove_ method here.
I'll update the name of the artist object, using my _setter_ method on **artist**.
So I'll add this statement after the `System.out.println` statement.
I'll run this:

```html  
May 01, 2024 11:25:55 PM org.hibernate.jpa.internal.util.LogHelper logPersistenceUnitInformation
INFO: HHH000204: Processing PersistenceUnitInfo [name: Section_18_WorkingWithDatabases.Course11_IntroductionJPA.music]
May 01, 2024 11:25:55 PM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 6.5.0.Final
May 01, 2024 11:25:55 PM org.hibernate.cache.internal.RegionFactoryInitiator initiateService
INFO: HHH000026: Second-level cache disabled
May 01, 2024 11:25:55 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using built-in connection pool (not intended for production use)
May 01, 2024 11:25:55 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: Loaded JDBC driver class: com.mysql.cj.jdbc.Driver
May 01, 2024 11:25:55 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001012: Connecting with JDBC URL [jdbc:mysql://localhost:3335/music]
May 01, 2024 11:25:55 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=devUser}
May 01, 2024 11:25:55 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 01, 2024 11:25:55 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH10001115: Connection pool size: 20 (min=1)
May 01, 2024 11:25:56 PM org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator initiateService
INFO: HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Hibernate: select a1_0.artist_id,a1_0.artist_name from artists a1_0 where a1_0.artist_id=?
Artist{artistId=202, artistName='Muddy Water'}
Hibernate: update artists set artist_name=? where artist_id=?
May 01, 2024 11:25:56 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:mysql://localhost:3335/music]
```

Now, you'll see the update statement in the output.
I'll switch again to MySQL Workbench, and confirm that change was really committed correctly,
by again executing my select statement.
And there, you can see that artist id `202`, has the name _Muddy Waters_ now.
Switching back to IntelliJ, I want to look at this code a little more closely.
Are you wondering, how did this really work?
I'm just calling a _setter_ on a Java object.
How did that get persisted to the database?
Maybe you were expecting a call to an _update_ method, for example.
Well, the _find_ method not only retrieved the record from the database.
It populated the **entity** _artist_, but importantly it also set it to a managed state.
This means changes made to the **entity**, 
the java object, will get persisted on a _commit_ statement.
The entity manager identifies state changes between 
what's in the database and the data in the _entity_ object, 
and builds appropriate SQL queries to keep the database in sync with the java object.
That's the beauty of using a JPA provider, which monitors and manages the entity's state,
and keeps the data synchronized to the data layer.
It's all done seamlessly for you.
How would I do this if I didn't want to first query the database?
I'll show you that next.
I'll comment out the _find_ statement, and also the _setArtistName_ method.

```java  
public class Main {

    public static void main(String[] args) {

      String persistenceUnitName = "Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music";
      try (var sessionFactory =
                   Persistence.createEntityManagerFactory(persistenceUnitName);
           EntityManager entityManager = sessionFactory.createEntityManager();
      ) {

        var transaction = entityManager.getTransaction();
        transaction.begin();
        
        //Artist artist = entityManager.find(Artist.class, 203);
        //Artist artist = entityManager.find(Artist.class, 202);
        Artist artist = new Artist(202, "Muddy Water");
        System.out.println(artist);
        //artist.setArtistName("Muddy Waters");
        //entityManager.remove(artist);
        //entityManager.persist(new Artist("Muddy Water"));
        transaction.commit();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
}
```

Instead of calling find, I'll just create a new instance of **Artist**, 
using the constructor that takes id, and artist name.
I'll pass 2O2, an id I know is in the data, and the name _Muddy Water_.
If I run this code the way it is:

```html  
May 01, 2024 11:32:50 PM org.hibernate.jpa.internal.util.LogHelper logPersistenceUnitInformation
INFO: HHH000204: Processing PersistenceUnitInfo [name: Section_18_WorkingWithDatabases.Course11_IntroductionJPA.music]
May 01, 2024 11:32:50 PM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 6.5.0.Final
May 01, 2024 11:32:50 PM org.hibernate.cache.internal.RegionFactoryInitiator initiateService
INFO: HHH000026: Second-level cache disabled
May 01, 2024 11:32:50 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using built-in connection pool (not intended for production use)
May 01, 2024 11:32:50 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: Loaded JDBC driver class: com.mysql.cj.jdbc.Driver
May 01, 2024 11:32:50 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001012: Connecting with JDBC URL [jdbc:mysql://localhost:3335/music]
May 01, 2024 11:32:50 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=devUser}
May 01, 2024 11:32:50 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 01, 2024 11:32:50 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH10001115: Connection pool size: 20 (min=1)
May 01, 2024 11:32:51 PM org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator initiateService
INFO: HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Artist{artistId=202, artistName='Muddy Water'}
May 01, 2024 11:32:51 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:mysql://localhost:3335/music]
```

Notice, there are no generated SQL statements in the output.
Nothing persisted. 
I just have my output statement printed there, with the new name, 
_Muddy Water_, but this wasn't saved to the database.
Because I created the **Artist Entity** manually,
it's not managed by the **Entity Manager**, 
until I call either a _persist_ or _merge_ method.
I use _merge_ if I'm specifying an id, as I'm doing here.

```java  
public class Main {

    public static void main(String[] args) {

        String persistenceUnitName = "Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music";
        try (var sessionFactory =
                     Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager entityManager = sessionFactory.createEntityManager();
        ) {
  
          var transaction = entityManager.getTransaction();
          transaction.begin();
          
          Artist artist = new Artist(202, "Muddy Water");
          System.out.println(artist);
          entityManager.merge(artist);
          
          transaction.commit();
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}
```

I'll insert a call to _merge_, passing it _artist_, just before the _commit_ statement.
I'll rerun my code:

```html  
May 01, 2024 11:35:36 PM org.hibernate.jpa.internal.util.LogHelper logPersistenceUnitInformation
INFO: HHH000204: Processing PersistenceUnitInfo [name: Section_18_WorkingWithDatabases.Course11_IntroductionJPA.music]
May 01, 2024 11:35:36 PM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 6.5.0.Final
May 01, 2024 11:35:36 PM org.hibernate.cache.internal.RegionFactoryInitiator initiateService
INFO: HHH000026: Second-level cache disabled
May 01, 2024 11:35:36 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using built-in connection pool (not intended for production use)
May 01, 2024 11:35:36 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: Loaded JDBC driver class: com.mysql.cj.jdbc.Driver
May 01, 2024 11:35:36 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001012: Connecting with JDBC URL [jdbc:mysql://localhost:3335/music]
May 01, 2024 11:35:36 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=devUser}
May 01, 2024 11:35:36 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 01, 2024 11:35:36 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH10001115: Connection pool size: 20 (min=1)
May 01, 2024 11:35:37 PM org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator initiateService
INFO: HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Artist{artistId=202, artistName='Muddy Water'}
Hibernate: select a1_0.artist_id,a1_0.artist_name from artists a1_0 where a1_0.artist_id=?
Hibernate: update artists set artist_name=? where artist_id=?
May 01, 2024 11:35:37 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:mysql://localhost:3335/music]
```

Here, in this output, you can see a _select_ statement was generated by hibernate, 
as well as an _update_ statement.
**Hibernate** figured out that the current state of the managed entity, 
when compared to the data in the persistence layer, was different,
and determined an _update_ was needed.
This _update_ took place on the _commit_ statement.
I'll confirm it once again in MySQL workbench,
running the select statement there.
And artist id 202, is back to _Muddy Water_ without the _s_ on the end.
Getting back to IntelliJ, I'll take a minute here to revert those last few changes.
So I'll revert the _merge_ method call, 
as well as the statement with the artist constructor.

```java  
public class Main {

    public static void main(String[] args) {

        String persistenceUnitName = "Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music";
        try (var sessionFactory =
                     Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager entityManager = sessionFactory.createEntityManager();
        ) {
  
          var transaction = entityManager.getTransaction();
          transaction.begin();
          
          Artist artist = entityManager.find(Artist.class, 202);
          //Artist artist = new Artist(202, "Muddy Water");
          artist.setArtistName("Muddy Water");
          System.out.println(artist);
          //entityManager.merge(artist);
          
          transaction.commit();
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}
```

I'll get back the call to the setter, and finally revert back a comment line, 
so the code is calling the _find_ method, for `artist_id=202`.
I'll run this once again, so that my artist is changed back to _Muddy Waters_.

```html  
May 01, 2024 11:42:30 PM org.hibernate.jpa.internal.util.LogHelper logPersistenceUnitInformation
INFO: HHH000204: Processing PersistenceUnitInfo [name: Section_18_WorkingWithDatabases.Course11_IntroductionJPA.music]
May 01, 2024 11:42:30 PM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 6.5.0.Final
May 01, 2024 11:42:30 PM org.hibernate.cache.internal.RegionFactoryInitiator initiateService
INFO: HHH000026: Second-level cache disabled
May 01, 2024 11:42:31 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using built-in connection pool (not intended for production use)
May 01, 2024 11:42:31 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: Loaded JDBC driver class: com.mysql.cj.jdbc.Driver
May 01, 2024 11:42:31 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001012: Connecting with JDBC URL [jdbc:mysql://localhost:3335/music]
May 01, 2024 11:42:31 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=devUser}
May 01, 2024 11:42:31 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 01, 2024 11:42:31 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH10001115: Connection pool size: 20 (min=1)
May 01, 2024 11:42:32 PM org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator initiateService
INFO: HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Hibernate: select a1_0.artist_id,a1_0.artist_name from artists a1_0 where a1_0.artist_id=?
Artist{artistId=202, artistName='Muddy Waters'}
Hibernate: update artists set artist_name=? where artist_id=?
May 01, 2024 11:42:32 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:mysql://localhost:3335/music]
```

So in this code, we've used **Hibernate** as our _JPA Provider_ 
to insert a new record into the **artists** table, 
using the `EntityManager.persist` method.
We retrieved a record with the _find_ method,
which required the entity class as the first argument, 
and the entity id as the second.
We deleted a record with the _remove_ method.
Updating data though was done automatically (if the entity was managed), 
when the transaction ended with a _commit_.
You saw that I could use _merge_, to use an existing entity, 
with a valid id, to have it be managed and persisted, 
after a manual creation of the entity.
</div>

### JPA with Related Tables
<div align="justify">

In this section, I'll continue to look at JPA, 
but this time as it pertains to table relationships.
I'll create a second **entity** class for the data that's in the **albums** table.
To do this, I'll open the project panel, and highlight **Artist**, 
and copy that from the context menu.
I'll now highlight the _music_ node, and do a paste in windows.
I'll change the name to **Album**.
This will create the **Album** class, and open it in a new edit tab.
Next I'll use IntelliJ's _replace_ functionality, with control+R, 
making sure to match a case, so selecting the `Cc` icon there.
Make sure it's selected.
I'll first replace `artist` with a lower case `a` to `album`.
I'll pick _replaceAll_ there.
I'll repeat that for `Artist` with a capital `A`, 
and `Album` with a capital `A`.
And with that, I now have an operational entity for `Album`.

```java
@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="album_id")
    private int albumId;

    @Column(name="album_name")
    private String albumName;

    public Album() {
    }

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public Album(int albumId, String albumName) {
        this.albumId = albumId;
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                '}';
    }
}
```

I'll edit this class, and include one additional annotation to _id_ field 
on the **Album** entity.
This is the _GeneratedValue_ annotation, which requires a strategy to be specified,
so here I'll set that to `GenerationType.IDENTITY`.
This informs the provider that the database is going to be assigning the _id_ value.
You might ask why I didn't need it on the _ArtistId_.
This is a JPA Provider specific requirement.
It's here not really to ensure that the album id gets a valid auto incremented id.
This happens in MySQL, remember, 
because of the way we configured that table in that database server.
This was confirmed with the **Artist** entity when a new artist was added, 
it was given the _ID_ in the auto Increment field for that table.
So why did I add it here?
This annotation is required by this JPA Provider 
to ensure that the parent id, the _artistID_ in other words, the foreign key, 
is properly saved to the child, during the insertion of a new record.
There are other means to enforce this, but this is the simplest way,
for the example I'll be showing you.
Again, it's not my intention to make you JPA experts.
I just want you to have a starting point, with working code,
for your own explorations into this topic. 
To establish a relationship between the **Artist** and **Album**, 
I'll switch to the **Artist** class, and add a field for albums, 
after the artist name, and before the first constructor.

```java
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @Column(name="artist_id")
    private int artistId;
  
    @Column(name="artist_name")
    private String artistName;
    
    @OneToMany
    @JoinColumn(name="artist_id")
    private List<Album> albums = new ArrayList<>();
    
    public Artist() {
    }
  
    public Artist(String artistName) {
        this.artistName = artistName;
    }
  
    public Artist(int artistId, String artistName) {
        this.artistId = artistId;
        this.artistName = artistName;
    }
  
    public List<Album> getAlbums() {
        return albums;
    }
    
    public String getArtistName() {
        return artistName;
    }
  
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
  
    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                ", albums =" + albums +
                '}';
    }
}
```

So private **List**, typed with **album**. 
I'll call it _albums_ and initialize it to a new **ArrayList**.
I need to map this relationship with annotations.
First, I need a relationship description, and in this case, 
this will be `one-to-many`.
Next, I need the `JoinColumn`, which is _artist_id_. 
You'll remember that _artist_id_ is a foreign key column on the **album** table, 
and this is how the two tables are joined together in a query.
I'll generate a getter for this field, placing this method, 
above the _getArtistName_ method.
I'll use ALT+Insert key combination to do that, and just pick getter, 
I don't need a setter for this field.
Finally, I'll edit the Artist's _toString_ method, adding a statement,
so that it prints out the _albums_ list.
With these minor edits, I've now got two related **Entities**, 
related by the _albums_ list, on **Artist**.
Getting back to the **Main** class and the _main_ method, 
I'll remove the _setArtistName_ statement.
I'll change my id to `2O1`, which is the id for the `Chemical Brothers` group.
I'll execute this code the way it is, 
which means I'm just selecting `Chemical Brothers` from the database.

```java
public class Main {

    public static void main(String[] args) {

        String persistenceUnitName = "Section_18_WorkingWithDatabases.Course12_JPARelatedTables.music";
    
    
        try (var sessionFactory =
                     Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager entityManager = sessionFactory.createEntityManager();
        ) {
    
            var transaction = entityManager.getTransaction();
            transaction.begin();
      
            Artist artist = entityManager.find(Artist.class, 201);
            System.out.println(artist);
      
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
  
  
    }
}
```

```html  
May 02, 2024 4:09:56 AM org.hibernate.jpa.internal.util.LogHelper logPersistenceUnitInformation
INFO: HHH000204: Processing PersistenceUnitInfo [name: Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music]
May 02, 2024 4:09:56 AM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 6.5.0.Final
May 02, 2024 4:09:56 AM org.hibernate.cache.internal.RegionFactoryInitiator initiateService
INFO: HHH000026: Second-level cache disabled
May 02, 2024 4:09:57 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using built-in connection pool (not intended for production use)
May 02, 2024 4:09:57 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: Loaded JDBC driver class: com.mysql.cj.jdbc.Driver
May 02, 2024 4:09:57 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001012: Connecting with JDBC URL [jdbc:mysql://localhost:3335/music]
May 02, 2024 4:09:57 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=devUser}
May 02, 2024 4:09:57 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 02, 2024 4:09:57 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH10001115: Connection pool size: 20 (min=1)
May 02, 2024 4:09:58 AM org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator initiateService
INFO: HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Hibernate: select a1_0.artist_id,a1_0.artist_name from artists a1_0 where a1_0.artist_id=?
Hibernate: select a1_0.artist_id,a1_0.album_id,a1_0.album_name from albums a1_0 where a1_0.artist_id=?
Artist{artistId=201, artistName='Chemical Brothers', albums =[Album{albumId=289, albumName='Push The Button'}, Album{albumId=728, albumName='Push The Button'}]}
May 02, 2024 4:09:58 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:mysql://localhost:3335/music]
```

If I examine the output, I'll see two _select_ statements were executed by **hibernate**,
the first got the **artist** record, 
and the second got the **album** records associated with this **artist**.
The **artist** entity is printed out, so artist id `2O1` is the `Chemical Brothers`,
and this artist has two _albums_ in the data, `Push The Button`, and actually, 
a duplicate, _albumId_ `728`, also named `Push the Button`.
Maybe some of you have noticed this before, but my _music_ data is a bit flawed,
and contains duplicate album names.
This wasn't really by design, but I decided to keep it this way,
as an opportunity for a future challenge.
By adding a new annotated **Entity** for **Album**, 
and a field on **Artist** with a list of these entities,
**Hibernate** was able to properly join the tables.
It returned the results to the **Artist** and **Album** instances.
I hope you're starting to grasp some of the benefits 
you can derive with this specification.
Java developers don't have to know SQL, 
or get bogged down in understanding relational databases to successfully retrieve data.
So let's now deal with the duplicate albums, associated with this artist.
I'll add a method to the artist entity 
to remove duplicate albums if they have the same name.
To do this, I first want the **Album** entity to implement **Comparable**.

```java
@Entity
@Table(name = "albums")
public class Album implements Comparable<Album> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="album_id")
    private int albumId;

    @Column(name="album_name")
    private String albumName;

    public Album() {
    }

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public Album(int albumId, String albumName) {
        this.albumId = albumId;
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Album o) {
      ///return 0;
      return this.albumName.compareTo(o.getAlbumName());
    }
}
```

I'll add this to the declaration, typing the **Comparable** interface with the **Album** entity.
This won't compile, so I'll hover over the error, and I'll select _Implement methods_ there.
This inserts a _compareTo_ method.
I want to compare **Album** names in this code, so I'll replace the `return 0;` statement.
I'll return the comparison of this instance's album name with that of the method parameter's.
On the **Artist** class, I'll create a method called _removeDuplicates_.
I'll insert this after the setter method for artist name.

```java
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @Column(name="artist_id")
    private int artistId;
  
    @Column(name="artist_name")
    private String artistName;
    
    @OneToMany
    @JoinColumn(name="artist_id")
    private List<Album> albums = new ArrayList<>();
    
    public Artist() {
    }
  
    public Artist(String artistName) {
        this.artistName = artistName;
    }
  
    public Artist(int artistId, String artistName) {
        this.artistId = artistId;
        this.artistName = artistName;
    }
  
    public List<Album> getAlbums() {
        return albums;
    }
    
    public String getArtistName() {
        return artistName;
    }
  
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void removeDuplicates() {
  
        var set = new TreeSet<>(albums);
        albums.clear();
        albums.addAll(set);
    }
    
    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                ", albums =" + albums +
                '}';
    }
}
```

I'll make this public and void, and call it _removeDuplicates_. 
I can deduce using a set, so I'll do that, 
passing albums to a new instance of a **TreeSet**. 
This will be ordered, and unique by album name. 
Next, I'll clear the collection. 
I'll repopulate the list with the elements in the set.
Before I attempt to run this, I'll go back to MySQL Workbench, 
and query these records in the **albums** table.
I'll open a new SQL Tab for this.

~~~~sql  
SELECT * FROM music.albums WHERE album_id IN (289, 728);
~~~~

I want to select the albums using the album ID's I saw in my output, so album id 289, and 728.
If I execute this, I'll see I have two records returned, both have `artist ID=201`, as you'd expect.
I'll get back to IntelliJ, and the _main_ method, 
and call my new _removeDuplicates_ method on the **artist** entity.

```java
public class Main {

    public static void main(String[] args) {

        String persistenceUnitName = "Section_18_WorkingWithDatabases.Course12_JPARelatedTables.music";
    
    
        try (var sessionFactory =
                     Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager entityManager = sessionFactory.createEntityManager();
        ) {
    
            var transaction = entityManager.getTransaction();
            transaction.begin();
      
            Artist artist = entityManager.find(Artist.class, 201);
            System.out.println(artist);
            artist.removeDuplicates();
            System.out.println(artist);
      
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
  
  
    }
}
```

I'll insert this after I print what I got from the database. 
I'll print out the _artist_ entity, a second time, after this change.
I'll run this code:

```html  
May 02, 2024 4:28:21 AM org.hibernate.jpa.internal.util.LogHelper logPersistenceUnitInformation
INFO: HHH000204: Processing PersistenceUnitInfo [name: Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music]
May 02, 2024 4:28:21 AM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 6.5.0.Final
May 02, 2024 4:28:21 AM org.hibernate.cache.internal.RegionFactoryInitiator initiateService
INFO: HHH000026: Second-level cache disabled
May 02, 2024 4:28:21 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using built-in connection pool (not intended for production use)
May 02, 2024 4:28:21 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: Loaded JDBC driver class: com.mysql.cj.jdbc.Driver
May 02, 2024 4:28:21 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001012: Connecting with JDBC URL [jdbc:mysql://localhost:3335/music]
May 02, 2024 4:28:21 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=devUser}
May 02, 2024 4:28:21 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 02, 2024 4:28:21 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH10001115: Connection pool size: 20 (min=1)
May 02, 2024 4:28:22 AM org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator initiateService
INFO: HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Hibernate: select a1_0.artist_id,a1_0.artist_name from artists a1_0 where a1_0.artist_id=?
Hibernate: select a1_0.artist_id,a1_0.album_id,a1_0.album_name from albums a1_0 where a1_0.artist_id=?
Artist{artistId=201, artistName='Chemical Brothers', albums =[Album{albumId=289, albumName='Push The Button'}, Album{albumId=728, albumName='Push The Button'}]}
Artist{artistId=201, artistName='Chemical Brothers', albums =[Album{albumId=289, albumName='Push The Button'}]}
Hibernate: update albums set artist_id=null where artist_id=? and album_id=?
May 02, 2024 4:28:22 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:mysql://localhost:3335/music]
```

So you can see _artist_ before we removed the duplicate, as well as after,
where our list only has `album Id = 289`.
Now, this statement just shows us what the entity's state was, 
at the time each `System.out.println` was executed.
It's not really a confirmation that this is what it looks like in the database here.
The other thing I want you to see from this output is that 
an _update_ statement was executed on **albums**.
I'll go back to MySQL Workbench, and re-execute my last query.

| album_id | album_name      | artist_id |
|----------|-----------------|-----------|
| 289      | Push The Button | 201       |
| 728      | Push The Button | NULL      |

Notice that album id `728` still exists, 
but it's not associated to an artist, because _artistID_ is **null**.
This is called an orphaned record, and it's a pretty **undesirable** situation.
Instead of deleting the **album** record, it simply updated the _artistID_ to **null**.
That's not what I wanted to happen.
I can change this behavior by including a couple of values 
on the `One-To-Many` annotation on **Artist**.
Before I go do that, I'll reset my data back to the way it was,
while I'm still here, in MySQL Workbench.

~~~~sql  
UPDATE music.albums SET artist_id = 201 WHERE album_id = 728;

SELECT * FROM music.albums WHERE album_id IN (289, 728);
~~~~

I'll insert this statement before the select statement, so in the same SQL tab.
This statement will just put the album id back in that record.
I can run this individually or run both scripts as a unit.
I'll run both to confirm that my data was restored to the way it was.
Both records should have artist id equal to `2O1` now.
Ok, so back to IntelliJ, and the **Artist** class.
I need to change the `One-To-Many` annotation 
that I've declared above the _albums_ field declaration.

```java
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @Column(name="artist_id")
    private int artistId;
  
    @Column(name="artist_name")
    private String artistName;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="artist_id")
    private List<Album> albums = new ArrayList<>();
    
    public Artist() {
    }
  
    public Artist(String artistName) {
        this.artistName = artistName;
    }
  
    public Artist(int artistId, String artistName) {
        this.artistId = artistId;
        this.artistName = artistName;
    }
  
    public List<Album> getAlbums() {
        return albums;
    }
    
    public String getArtistName() {
        return artistName;
    }
  
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void removeDuplicates() {
  
        var set = new TreeSet<>(albums);
        albums.clear();
        albums.addAll(set);
    }
    
    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                ", albums =" + albums +
                '}';
    }
}
```

In this case, I'm going to define some key value pairs or annotation members.
The first key is _cascade_, and here, I'll specify that I want `CascadeType.ALL`.
This means I want new child entities persisted, deleted child entities removed,
updates to children persisted, and so forth.
Basically, all changes to parent and child are kept in sync, 
via the **entity** and its attributes.
In addition to this, I'll include `orphanRemove = true`, 
as the next key value pair.
This instructs the persistence provider 
to automatically delete child entities when they become orphaned.
We really need both of these key value pairs to be declared
for the behavior to be correct in this case.
I'll rerun my code with this change.

```html  
May 02, 2024 4:48:32 AM org.hibernate.jpa.internal.util.LogHelper logPersistenceUnitInformation
INFO: HHH000204: Processing PersistenceUnitInfo [name: Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music]
May 02, 2024 4:48:32 AM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 6.5.0.Final
May 02, 2024 4:48:32 AM org.hibernate.cache.internal.RegionFactoryInitiator initiateService
INFO: HHH000026: Second-level cache disabled
May 02, 2024 4:48:33 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using built-in connection pool (not intended for production use)
May 02, 2024 4:48:33 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: Loaded JDBC driver class: com.mysql.cj.jdbc.Driver
May 02, 2024 4:48:33 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001012: Connecting with JDBC URL [jdbc:mysql://localhost:3335/music]
May 02, 2024 4:48:33 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=devUser}
May 02, 2024 4:48:33 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 02, 2024 4:48:33 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH10001115: Connection pool size: 20 (min=1)
May 02, 2024 4:48:33 AM org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator initiateService
INFO: HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Hibernate: select a1_0.artist_id,a1_0.artist_name from artists a1_0 where a1_0.artist_id=?
Hibernate: select a1_0.artist_id,a1_0.album_id,a1_0.album_name from albums a1_0 where a1_0.artist_id=?
Artist{artistId=201, artistName='Chemical Brothers', albums =[Album{albumId=289, albumName='Push The Button'}, Album{albumId=728, albumName='Push The Button'}]}
Artist{artistId=201, artistName='Chemical Brothers', albums =[Album{albumId=289, albumName='Push The Button'}]}
Hibernate: update albums set artist_id=null where artist_id=? and album_id=?
Hibernate: delete from albums where album_id=?
May 02, 2024 4:48:34 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:mysql://localhost:3335/music]
```

Notice that in addition to the _update_ statement that's shown, 
there's an additional _delete_ statement there, so `delete from albums`.
Again, I'll switch over to MySQL Workbench, 
and I'll execute just my select query for the two album records.
I do this by highlighting only the _select_ statement, and executing it.

| album_id | album_name      | artist_id |
|----------|-----------------|-----------|
| 289      | Push The Button | 201       |

You can see why having an easy-to-use user interface like MySQL Workbench, 
to verify your work on the database side is extremely helpful.
This time, the duplicate album was actually removed from the **albums** table.
So that's pretty handy.
I've created two related entities, 
and now I can remove albums from the database 
by simply removing albums from the **Artist** instance's list.
Let's now add a new album.
So I'll get back to the **Artist** entity, 
and I'll add an _addAlbum_ method, on the **Artist** entity class.
I'll put this after the setter for artist name,
and before the _removeDuplicates_ method.

```java
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @Column(name="artist_id")
    private int artistId;
  
    @Column(name="artist_name")
    private String artistName;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="artist_id")
    private List<Album> albums = new ArrayList<>();
    
    public Artist() {
    }
  
    public Artist(String artistName) {
        this.artistName = artistName;
    }
  
    public Artist(int artistId, String artistName) {
        this.artistId = artistId;
        this.artistName = artistName;
    }
  
    public List<Album> getAlbums() {
        return albums;
    }
    
    public String getArtistName() {
        return artistName;
    }
  
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void addAlbum(String albumName) {
        albums.add(new Album(albumName));
    }
    
    public void removeDuplicates() {
  
        var set = new TreeSet<>(albums);
        albums.clear();
        albums.addAll(set);
    }
    
    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", artistName='" + artistName + '\'' +
                ", albums =" + albums +
                '}';
    }
}
```

I'll make it public and void, and it'll have one parameter, the _albumName_. 
And it'll simply add a `new Album` instance, 
using the album name in the constructor, 
adding that to the _albums_ list field.
Getting back to the **Main** class's _main_ method:

```java
public class Main {

    public static void main(String[] args) {

        String persistenceUnitName = "Section_18_WorkingWithDatabases.Course12_JPARelatedTables.music";
    
    
        try (var sessionFactory =
                     Persistence.createEntityManagerFactory(persistenceUnitName);
             EntityManager entityManager = sessionFactory.createEntityManager();
        ) {
    
            var transaction = entityManager.getTransaction();
            transaction.begin();
      
            Artist artist = entityManager.find(Artist.class, 202);
            System.out.println(artist);
            //artist.removeDuplicates();
            artist.addAlbum("The Best of Muddy Waters");
            System.out.println(artist);
      
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

I'll change the id of the artist 
I'm retrieving back to `2O2`, the `Muddy Waters` Id.
I'll remove the code that _removesDuplicates_
Instead, I'll call addAlbum, and pass it the
string literal, the best of Muddy Waters.
I'll rerun the code.

```html  
May 02, 2024 4:55:49 AM org.hibernate.jpa.internal.util.LogHelper logPersistenceUnitInformation
INFO: HHH000204: Processing PersistenceUnitInfo [name: Section_18_WorkingWithDatabases.Course11_JavaPersistenceAnnotations.music]
May 02, 2024 4:55:49 AM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 6.5.0.Final
May 02, 2024 4:55:49 AM org.hibernate.cache.internal.RegionFactoryInitiator initiateService
INFO: HHH000026: Second-level cache disabled
May 02, 2024 4:55:49 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using built-in connection pool (not intended for production use)
May 02, 2024 4:55:49 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: Loaded JDBC driver class: com.mysql.cj.jdbc.Driver
May 02, 2024 4:55:49 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001012: Connecting with JDBC URL [jdbc:mysql://localhost:3335/music]
May 02, 2024 4:55:49 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=devUser}
May 02, 2024 4:55:49 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 02, 2024 4:55:49 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH10001115: Connection pool size: 20 (min=1)
May 02, 2024 4:55:50 AM org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator initiateService
INFO: HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
Hibernate: select a1_0.artist_id,a1_0.artist_name from artists a1_0 where a1_0.artist_id=?
Hibernate: select a1_0.artist_id,a1_0.album_id,a1_0.album_name from albums a1_0 where a1_0.artist_id=?
Artist{artistId=202, artistName='Muddy Waters', albums =[]}
Artist{artistId=202, artistName='Muddy Waters', albums =[Album{albumId=0, albumName='The Best of Muddy Waters'}]}
Hibernate: insert into albums (album_name) values (?)
Hibernate: update albums set artist_id=? where album_id=?
May 02, 2024 4:55:50 AM org.hibernate.engine.jdbc.spi.SqlExceptionHelper logExceptions
WARN: SQL Error: 0, SQLState: S0022
May 02, 2024 4:55:50 AM org.hibernate.engine.jdbc.spi.SqlExceptionHelper logExceptions
ERROR: Column 'album_id' not found.
May 02, 2024 4:55:51 AM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:mysql://localhost:3335/music]
```

Here, you can see from the output that _muddy waters_ had no album data, as you'd expect,
when we got it from the database.
But you can see an _insert_ statement into albums, which is a good sign.
That's followed by an _update_ to the albums record,
which is setting the parent id, or artist id.
I'll go back to MySQL Workbench and execute a new select statement.

~~~~sql  
UPDATE music.albums SET artist_id = 201 WHERE album_id = 728;

SELECT * FROM music.albums WHERE album_id IN (289, 728);

SELECT * FROM music.albums WHERE album_id = 202;
~~~~

Select star from music dot albums, where artist id equals `2O2`.
I'll execute that _select_ statement, 
and I'll see the new album there for artist id `202`.

| album_id | album_name               | artist_id |
|----------|--------------------------|-----------|
| 905      | The Best of Muddy Waters | 202       |

Your album id may be different from the way you see here, 
but you should see a positive id in there, and not a zero or **null**.
So again, this demonstrates how I can manipulate the state of a couple of Java objects, 
and the changes get propagated to the database layer 
with no additional code except a transactional commit.
This is why JPA is such a popular framework.
Java developers get to do what they're probably best at, 
which is focusing on Object-Oriented techniques, 
without having to become experts in relational databases.
With a bit of initial configuration, and the use of a few annotations, 
you can have a fully functioning database application without writing a single SQL statement.
</div>

### JPA Queries
<div align="justify">

Up until now, I've used JPA to map a database record to a special type of object, 
called an entity, using Hibernate as the JPA Provider.
This was great, when working with a single targeted record, and its associated data.
You'll probably at some point still need to query the database for a set of records.
To do this, you can use a JPA query.
You know by now that a query in the database is often a _select_ statement of some kind, 
but can also be an _update_, _insert_ or _delete_ statement.
In JPA, you have three options to execute a query,
all of which still rely on the existence of an **entity** class.
You can use a **JPA Query**, using a special query language, named **JPQL**. 
There's something called a **Criteria Builder**, 
which is a more programmatic way to put together the selection request. 
Finally, if you're comfortable in SQL, 
or maybe you have a special need to increase performance, for example, 
you can still use a Native Query, which relies on SQL.
The **Java Persistence Query Language**, or JPQL, is an object-oriented query language.
It's specifically designed to work with entities in Java applications that use JPA.
It provides a way to query data stored in relational databases,
without needing to understand the details of how the data is actually structured there.
Let's start by looking at JPQL, in some code.
I'll be using the same project from the last couple of sections.
Using this project means I can continue to use the entities, 
**Artist** and **Album**, that I set up previously.
To use JPQL, you actually query entities, and not tables specifically.
I'll create a new class, and I'll call this class, **MainQuery**.
I'll include a _main_ method, using the PSVM shortcut.
Before I add any code to the _main_ method in this class, 
I'll first create a private static method, also in this class.

```java  

```

This method is going to return a List of Artist entity types, based on some query.
So I'll start with private, static, then
List, with Artist declared in angle brackets,
so my List will only have artist entities in it.
I'll name this method get Artists JPQL.
It will have two parameters. First, an
EntityManager, which I'll be creating
and managing, in the main method, and passing
to this method. I'll also include a string,
which I'll call matchedValue. So next, I'll create
a variable, a string for my query statement. I'll
call this JPQL, and I'll set that to a string
literal. Select a, from artist, a. This looks
a lot like SQL. The letter a, in this case is a
variable, as well as a table alias. Notice that
I'm selecting, not from artists, or music.artists,
so not from the table name. Instead, I select from
Artist, with a capital A, which actually refers to
my Artist entity, the Artist class in other words.
I can execute this query, using
a method on the EntityManager.
I'll use Local Variable type inference, to set up
the next variable, so var as the type, and query
as the variable name. I'll make that equal to the
value I get back, when I execute createQuery on
the EM method argument, which is really an Entity
manager. This method takes two arguments, first
is a string, that contains JPQL, so I'll pass my
variable for that. The second argument specifies
the class name, of the entity that's being
queried, so here, I need to pass Artist.class.
Finally, the query object I get back, has a
method called getResultList on it. In this case,
it'll return a List of Artists, so I can just
return that directly here, from this method.
And that's it.
These three lines
of code will get all the artists, from
the music schema, from the artists table.
Before I run this, notice,

```html  

```

from Intelli J's inlay hint,
the type that's inferred, for the query variable.
It's a TypedQuery, a generic type, an interface in
this case, that has a type argument of Artist.
I can control click on that hint.
This opens up a decompiled
class file, in a new tab.
There are tools that'll take a class file, and
decompile it into source code, which can show
you quite a bit of the implementation details.
In this case, I can see this is a typed interface,
that extends Query.
If I hover over the name,
TypedQuery, I can see that this interface
is part of the jakarta.persistence package.
I'll pull this interface
up, in the Jakarta API docs.
To find this link on your own, just google
the terms, jakarta and TypedQuery together,
to find the latest version, or
most current version you can.
I'll scroll down a bit on this page,
so you can see the list of methods,
declared on this interface.
You'll see at the top of the list,
there's the getResultList method,
which I'll be executing in this code.
This is followed by getResultStream,
which returns a stream of the entity type.
You know I like streams, so this method could be
particularly useful, for using stream operations
we've become pretty familiar with.
This will let us quickly investigate,
or manipulate query results.
Let me give you a word of caution though.
You want to avoid, if you can, using
a stream to filter or limit results,
though it might seem simpler.
It's usually a good idea to
limit data going across a network.
If you can add the criteria to the
where clause of the JPQL query that gets executed
on the server, you'll be better off than trying
to filter the stream on the client side..
But you can imagine using the stream result
to create some sort of Mapped collection,
based on column values in a table (as their
translated to entity fields).
I'll be showing you an example
of this in a little bit.
There's also getSingleResult,
so maybe you only care about one entity,
though several might match your query.
For example, if you've got a sorted query,
you might only want the first record,
sometimes called the top, and this method
limits the data returned, to a single record.
There's a couple of useful set methods,
and then a whole series of set Parameter
methods, as you can see.
Getting back to the code,
I'll run my first JPA query.

```html  

```

To do this, I need to add some
code in the main method.
I'll set up a variable,
for the results from the getArtistsJPQL method, so
a List of Artists, and I'll initialize it to null.
Like I did previously, I'll first instantiate an
Entity Manager Factory from the Persistence type,
within a try with resources declaration.
I'll pass that the name of my persistence
unit. Hopefully you'll remember, I set this
up in the persistence.xml file. In this case,
the persistence unit is named, dev dot lpa dot
music. Next, I create an Entity Manager instance,
using a method on the factory, again this is still
in the try with resources declaration. I'll get
back to the code that goes in the try block,
in a second. I'll just complete the statement
with a catch block, Printing the stack trace of
any error I get, ignoring Intelli J's warning,
that this isn't very robust handling of a problem.
My variable, artists, will get set to the result
of calling my get Artists JPQL method.
When using JPA Queries, it's a good idea
to wrap the queries in a transaction. I'll
get a transaction from the entity manager.
I'll call transaction begin next. I need to pass
the entity manager variable, and then a string,
and I'll just set that to an empty literal
string to start. You might have noticed,
I never used the second parameter, in my method's
code block. I plan to change that code shortly,
to use it. Finally, I'll print the results I
get back. I can use for each on the artists
list variable, and a method reference, to print
each artist, that's in this resulting list.
I'll call transaction commit here at the end.
You might be wondering why you need a transaction
when you're just querying records.
This is kind of a complicated subject
and has to do with lazy loading vs eager
fetching of dependent tables, as well as
transactional integrity between related tables.
If you do have problems with your queries,
and you're not including them in a transaction
block, let me recommend trying this to start.
Now I'll run this code.

```html  

```

So you'll see, in the output,
all of the artists in the table, in my
case that's 202 artists, that get printed.
Each record contains all the
row data in each artist record,
so that's just artist id and artist name.
Notice though, that each record also
includes, all the artist's album records.
In my JPQL statement, when I specified, select a,
from artist, I didn't specify what data I wanted,
meaning what fields that mapped to columns.
So it returned all the data for
all the fields on this entity.
You'll recall that I set up the entities, by
declaring a relationship on the albums field,
a list, declared in artist, to the album entity.
All of the underlying database querying,
with the appropriate joins was done seamlessly
by the JPA Provider, in this case hibernate.
This means, I'm able to write a very simple,
generic query against an Artist entity class,
to get related data, in this
case albums, for each artist.
This doesn't require the developer retrieving
data, to know anything about inner or outer joins,
or foreign keys, or any other specific
implementation details of the database.
It also doesn't require
views, on the database server,
for simpler querying of a normalized schema.
You may still want to employ those techniques,
but in this case, none of that is
necessary, in the operation of this query.
This might be a welcome relief, if you
don't want to deal with databases directly.
Now, I'll alter the query slightly, this time
just getting artists whose name contains S T E V.
First, I'll change my select statement.
I'll add a where clause, so I'll append the text,
where, a.artistName, like, colon partialName.
In this case, you can see I'm using the attribute
name on the Artist entity, and not the column name
in the table, which is artist underscore name.
My placeholder parameter, which is called a named
parameter in this case, is specified by a colon,
followed by any variable name I want.
So here, I'm calling it partial Name,
since I'll match on part of the artist's name.
Next I need to actually pass a value,
to this placeholder parameter.
So I'll add a call to a
setParameter method, on the query variable.
One version of this method takes two strings.
The first is the named parameter, so I'll pass
partialName as a string literal. The literal value
should match the named parameter, used in your
JPQL statement. The second argument to the set
parameter method, is the value to be used, so I'll
pass it the matched Value, the method argument
that contains the criteria to match on.
Back In the main method,
I'll change how I invoke this method.
Instead of passing an empty string literal,
I'll pass a pattern, an S Q L pattern I mean.
Let me set this up, so first I include a percent
sign, then S T E and V, and another percent sign.
The percent sign is the S Q L wildcard specifier,
in a pattern, that's used by a LIKE clause in SQL.
It means match on zero, or many characters.
Java's regular expression specifier to do
something similar, you'll remember, is a dot.
Because I'm starting with a percent sign, this
will match the contiguous letters, S T E V,
in any part of the album name.
I'll run that.

```html  

```

You can see I get only 6 artists that matched
that, most of them start with the first name,
Steve, but there's also Seasick
Steve, and Stevie Ray Vaughan.
I could change my pattern, if I wanted only
artists whose names started with S T E V, by
removing the first percent sign.
That would eliminate Seasick Steve,
which you can try on your own, if you want to.
JPQL doesn't eliminate the need to understand
all SQL constructs, as this example demonstrates.
In this code, you still needed to understand the
LIKE clause and valid patterns,
to use it effectively here.
In addition to named parameters, you can use
placement parameters in the query string.
This is similar to the question
mark place holder which we saw,
in the prepared and callable statements.
In this case, in addition to the question
mark, you have to include a numeric value.
Numeric placeholder specifiers must start at 1.
But they don't have to be in sequential
order, and you can reuse a numeric
placeholder, in a single JPQL string.
I'll change my getArtistsPLQL method,
replacing colon matched value, with a
question mark, followed by the number 1.
I'll next change the setParameter method,
passing in the number 1, as the first argument.
If I run the code this way,

```html  

```

I'll get the same results,
as I did with a named parameter.
Named placeholders make more readable code,
in my opinion, but it's up to you,
or maybe the framework you're using,
which kind of placeholder type you'll use.
In this video, I've given you your first taste of
using JPQL, with a very simple select statement.
The first brought back all data for all records,
and the second query brought back all
data, for a limited set of records,
matching criteria on the artist name.
I'll end this video here, before it gets too long.
In the next video, I'll continue using
this project and this class's method,
and show you slightly more complex JPQL
queries, so let's move on to that video.


~~~~sql  

~~~~
</div>

## [n. JPA Challenge]()
<div align="justify">

```java  

```

```html  

```

</div>

## [o. JPA Bonus Challenge]()
<div align="justify">

```java  

```

```html  

```

</div>