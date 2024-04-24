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
This means there may be functions stored with the database,
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
which is free and open source, or _Microsoft's SQL Server_
which has a free version.
I decided on _MySQL_ for several reasons.

* First, It's free, and downloaded from Oracle,
  and the installation isn't too difficult.
* Second, I think it's important to learn about
  _CallableStatements_, and for that,
  we needed a database that supports stored procedures.
  There are workarounds for _H2_ and _SQLite_,
  but I felt the workarounds might have been more confusing to students.
* Third, I offer a beginner's course in _SQL_ which also uses MySQL,
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
I'll be providing scripts to create a specific database schema,
for _MySQL_, which could be tweaked for your database.
In addition, I'll show you several different connection strings
to connect to different databases.
I'll be making an effort to use _ANSI SQL_,
a standard set of commands, which most databases should support.
I'll try to avoid vendor-specific _SQL_ statements,
or point them out when I can.
There are good reasons to use _ANSI SQL_ in your java code.
Doing so, lets you swap the database engine,
by simply changing your connection string.
This does sometimes happen,
as corporations decide the best way to house their data,
based on their own unique situation,
and as technologies change and compete with one another.
I'll explain all of this in more detail as we proceed.
Ok, so let's move forward.
</div>

## [a. Database Basics]()

### Installation For Windows
<div align="justify">

```java  

```

```html  

```

In this section, we're going to go ahead 
and install the _MySQL_ database server on a Windows machine.
The first thing we want to do is go to the website _dev.MySQL.com_. 
You may get a pop-up about accepting cookies, 
so do whatever you normally do when you get that pop-up. 



The download will work fine if you decide to decline all cookies.
Ok, once we get here, I'm going to click the download link.
I'll scroll down to towards the bottom and click
MySQL Community GPL Downlaods and click that.
We want the MySQL Installer for
Windows link, so click that.
On the next screen, Microsoft
Windows should already be selected.
Below that, we have two choices. Both
choices will install the same version,
and if you have a connection to the internet, then
it's not really important which one you choose.
The first one, the mysql-installer-web-community
one, will start the installation and then download
most of the files it needs, while it's installing.
You'd use that if you want to install MySQL on
the computer that you're using to download
– which is what we're doing at the moment.
But you might want to install the program
on several computers, or on a computer that
doesn't have internet access. In that
case, you can choose the second option,
and download the entire installer. As you
can see, that's a larger initial download.
Before we decide which one to use, notice
that there's an Information tab on that
screen – the blue "Information" icon.
Click that, to get a bit more information
about the installer choices we have.
There's a brief description of what the
installer includes: the latest version of the
MySQL Server itself, MySQL Workbench – which
we'll use to interact with the Server – and
some sample databases and documentation.
Below that, there's a note about Choosing
the right file. That pretty much echoes
what I've just said about the two options.
The note is interesting: "MySQL Installer is
32 bit, but will install both
32 bit and 64 bit binaries".
In a minute, I'm going to show you how to select
that, when we go through the installation process.
So you'll need to know whether
your Windows computer is
running a 32 or 64-bit version of Windows.



And you can generally find that in "About",
in the control panel for Windows
10 or earlier. If on Windows 11,
which I am go into Settings. Type "about" in
the search box, then click "About your PC".
And depending on your version of Windows, it'll
tell you System type 64-bit operating system.
We're not so much concerned about the
processor, because it is possible to have
a 32-bit version of windows running on a 64-bit



processor. We're interested in this bit here,
the 64-bit operating system.
You'll need to know whether you're
running the 32 or 64-bit version.



We've just seen how to check that
information on Windows 10 and 11.
If you're running an older version



of windows, you can generally find that
in the system part of control panel.
There'll be a screen similar to this, where you
can find out which version of Windows you're
running, and whether it's a 32 or 64-bit edition.



And again, we'll need that when we get to the
stage of installing MySQL.
Ok, back in my browser,
click the General Availability (GA) Releases
tab, to get back to the choice of installers.
I suggest you just grab the full product – the
second option – because you can re-install it,
if you need to, without having to download again.
I'm going to choose the second one,
so click its Download link.
Now it's asking whether we want to set up
an Oracle Web Account, because Oracle now own the
MySQL product. If you already have an account, you
can log in. We're going to click on "No thanks,
just start my download", then click Save file if
it doesn't start downloading automatically.
Now we need to open the file that we've
downloaded, so I'm just going
to select "Open file" here.
If you're using a different browser,
do whatever you'd normally do,
to open a file that you've downloaded.
The installer starts, and I'll give that a moment.
Click on "Yes", If it pops up, to allow
this app to make changes to your device,
and the installation should proceed.
It may pop up again and ask you to allow
this app to make changes again. In my case, it
did pop up that twice, so click "Yes" again.
If you get an option to apply an upgrade, click
"Yes" to allow the upgrade to be applied as well.
And now we get this screen, where
we can choose the setup type.
There are various radio buttons here to the left,
and to the right, it gives you a description
of what it's actually going to install.
So you can see we have a number of options.
You've got a Server only. This installs only the
MySQL server, which would be useful if you want
to run the server on a different computer,



rather than on your development machine.
We've got a Client only option – which is a sort
of compliment to the Server only option. This is
the one we'd install on our development
and user computers, if the server was
running on another computer.



We've got a Full option,
which installs everything.
But the option we're going to select
is the Custom option, so that we're installing
only the things that we need for this course.
I'm going to click on Custom,
and I'm going to click on Next.
Once we've done that, we get this screen here. On
the left-hand side, there's a list of available
products, and the right-hand side shows a
list of the products that are to be installed.
Currently, that's empty, because
we haven't selected anything yet.
So I'm going to come over here first, and expand
MySQL Servers by clicking on the plus. Do the
same to expand MySQL Server, then again to expand
the latest version – which for me is version 8.0.
You may have a later version there, and that's
fine. Install whatever is the latest version
available. For me, at the time I'm recording
this video, that's version 8.0. You can see
there are older versions I can choose, but
don't do that. Go with the latest version.
In fact, after expanding that, we can see version
8.0.34. You can see a lot of other versions like
8.0.33. Just choose the latest version you see.
The X64, at the end, indicates that this is a
64-bit version. If you see X86 there, then you'll
be installing the 32-bit version. You've already
checked whether your operating system is 32-bit
or 64-bit, so make sure that you choose the
appropriate version for your operating system.
I mention that because earlier versions of
the installer used to show both the
32-bit and the 64-bit versions here.
The installer changes whenever there's a
new version, and Oracle might decide to
show both versions again, in the future.
So if they do, choose the X64 version on
a 64-bit version of Windows, and the X86
version on a 32-bit version of Windows.
I'm running a 64-bit version of Windows, so



I'll select the X64 version – the only one
that's showing here – then click on the arrow,
to move it across to the right-hand side.
So that's now going to be installed.
The next thing we want to do is come over
here, to applications, and click on the plus
again to expand it. Expand MySQL Workbench,
by clicking on the plus, then again to
expand MySQL Workbench 8.0.34 – X64.
Once again, you may have to choose X64
or X86, if those options appear for you.
So I'm going to select the MySQL Workbench
8.0.34 – X64, and click the arrow to move
that over to the right-hand side.
I'll ignore all the older versions.
Once again, the version numbers
may change in the future. In fact,
the MySQL Server and Workbench programs
might even have different version numbers.
That's fine – they are often the same, but one
program might be updated separately to the other.
Depending on when you're watching this
video, they might be a later version,
but the principle is still the same. Just select
the relevant version, and select the 64-bit or
32-bit version if that option's available.
At this point, we've selected the only two
products we need, so I'm going to click
on Next, and then I'm going to click on
Execute. This is going to start out
downloading the MySQL software first.
And after that we will start the installation
of both those products, onto this computer.
And once that's finished, we need
to go through a configuration step,
to set up user names and so on and so forth.
Okay. So it's now finished downloading. I'm going
to click on Next and Execute to install.
Now the install is complete its time to
configure. I'll click Next again. That
will bring up the configuration options.
And we've got a Config Type dropdown here,
where we can select various different options.
We're going to leave the default of Development
Computer, since that's exactly what we're doing
here – we're using MySQL as developers.
It shouldn't normally be necessary to
change any other options here – the
defaults will work for most computers.
Now in terms of the port, it needs to be a unique
port number that's not used anywhere else, or it's
not used for another application on this computer.
So 3306 will be fine for my computer.
Normally, that will work
for your computer as well.
The rest of the defaults are okay. You don't need
to change anything else there, click on Next.
Now we get to specify the Authentication Method.
You shouldn't normally change this,
we want strong password encryption –
that keeps our installation secure.
If you've already written MySQL applications,
and they can't be updated, then you can
specify the old-style authentication here.
But we're starting from scratch, and don't
have any existing applications to support. So
we won't change anything here, just click Next.
Now it's asking for what the root password is.
Now the root account is created automatically
by MySQL, and is a logon that is the
most powerful user in the system.
And generally speaking, it's good
to use that username and password
very sparingly – only when you really need to
do some sort of super administrative tasks.
So what we're going to do is assign a password
for it, which we need to do, but then we're
going to create a new user that we're going
to be using for our day-to-day use of MySQL.
So you want to enter a password that's
hard to guess, and is quite secure.
So I'm going to type in a password, then confirm
it by typing it in again. The password strength
is showing as Strong, so that's good.
Ok, That's the root user. As I said,
you'd only log in as this user when you need to do
administrative tasks. It's not a good idea to use
such a powerful login for normal use.
So the next thing we need to do,
is click on Add User.
We're going to create another user,
that we're going to be using for
the normal tasks in this course.
Type in the name of the user. I'm going to call
this one devuser – all one word – but you can
call it literally whatever you like.
This is the user that we'll log in as,
when we're working with our databases.
For the host, I'm going to change that to
localhost, using the dropdown arrow.
localhost means this computer,
so that ensures that it can only be used
locally. That's more secure, because we
only want to be using this username locally.
We don't want access from any external source.
Some students have reported that they don't
get localhost here – instead they see the IP
address 127.0.0.1. That's fine, that's the IP
address that localhost resolves to, so choose
that address if that's what you see here.
We're going to leave the Role set to DB
admin. There's other options you can choose
here, but I'm not going to change any of
that – I'm going to leave it on admin for now.
A database administrator can do things like
creating databases and tables, which is
what we'll want to do in this course.
And we also need to enter a password for this.
I suggest you make this password
different to the root user.
And make sure you remember both passwords.
I'm going to enter my devuser
password, then confirm it.
In this case, you can see again,
I've got a strong password.
So now we're done with that, click OK, then Next.
The next step is to configure the Windows service.
I'm not going to go into a lot of detail
about this, you can generally accept the
defaults on this screen.
I will mention that Start
the MySQL Server at System Startup checkbox.
If you'll be doing a lot of work with MySQL,
then you'll probably want to leave that ticked.
But if your computer is low on memory, or is quite
old and slow, then you should untick that box. In
a moment, I'll show you what it does, and how you
can start and stop the service manually.
So we're going to accept all
the defaults, and click on Next.
Leave everything unchanged on the Server
File Permissions dialogue, and click Next again.
We've now configured everything, and this screen
just gives us a chance to go back if
we decide we want to change something.
We don't, so click Execute.
The installer runs through the process



of configuring MySQL on this computer.
When it's finished, click on Finish.
Click on Next, and you can see we're now done –
the installation procedure has been completed.
Leave the checkbox to Start MySQL workbench
after setup ticked. And that's because we want
to run the Workbench, just to confirm the



installation is okay. So click on Finish.
Before we move on, I'm going
to close the browser down.
Ok, what I'd like to do next, is show you
what that "Start the MySQL Server at System
Startup" checkbox does – what effect it has, and
what you can do if you decided not to tick it.
We want to go into the Services on
our computer, and Microsoft move that
option around on different versions of Windows.
The way that will work on all current versions,
is to use the Windows key and R, then
type services.msc into the Run box.



Press Enter, and the services
management console starts up.
And once you've done that, you want to scroll on
down here, until we get to MySQL. The services
usually appear in alphabetical order,
and the name we're looking for is the
one we used when configuring the service.
We didn't change the default, so for me,
it's called MySQL80 – because I installed
version 8.0. Your name might be slightly
different, but you should be able to
find your MySQL service in the list.
And there's MySQL80. And you can see
in this case, it's actually Running



and the Startup Type is set to Automatic.
That's a good sign. The fact that it's
running tells us that the MySQL service has been



successfully installed, and is actually running.



That checkbox that we ticked, causes
the Startup Type to be Automatic.
You can override this, if your computer
is low on resources – if you haven't got
a lot of memory, you can change this.
So instead of it starting automatically
every time your computer starts, you can
right-click that and go into properties,
and you could make that Manual, and click on OK.
And what that would mean is, it won't start
automatically each time you turn the computer on.
So you could start the computer, and if you decide
you're doing some development in this course,
you could just come over to here, right-click,
and click on start. And that would start
the service for that particular session.
In my case, I'm happy to
have this run automatically.



I've got enough resources, so
I'm going to right-click again,
go into Properties, and choose Automatic.
So that every time I reboot the computer,
MySQL is available for me.
We've confirmed that's running okay,



so close the services down. All right. Getting
back now to the MySQL workbench, that started
automatically once MySQL was installed.
I want to start by clicking on this local
instance, so click on that, and it
brings up this little screen. It's
asking for the root user password.
So I'm going to type in that root
password that I set, earlier in the video.
And I'm going to choose not to save it. You
could choose to save the password if you like,
but for security reasons I prefer not to do that.
Click on OK.
And this has opened up, which is
a good sign that we got the password correct.
If we come over here, on the left,
and click on Server Status, that gives us
a bit of an overview of our MySQL service.
A lot of the information is
hidden by that right-hand pane,
so click the rightmost button, above it.
You can see we've got 3 panes here,
and each of those buttons corresponds to one
of the panes. The rightmost one has a blue
bar on its right-hand side. That shows that it
represents the right-hand pane, and clicking
it will toggle that right-hand pane on and off.
When we remove that pane, we can see the graphical
stats about our server.
It's Running, as you can



see here – so that's good.
We can also see the number
of connections, and other statistics.
But obviously, there won't be a lot
happening at the moment, and that's because
we haven't really started using it yet.
But bear in mind that we have actually
connected to the server, because we've
used MySQL Workbench to do that – in case you're
wondering where the connections are coming from.
Ok, come over here, on the left,
and click on Users and Privileges.
We can see the list of users – so you can
see there that we had the root account,
which was automatically created for us, and
also the devuser account that we created.
You might see a few other users, which are used
internally by MySQL. If you don't see them,
don't worry – sometimes the default settings
are changed, and these accounts don't show
up. That's fine, they're not accounts that we
need to do anything with, and it's best that
we don't make any changes to them.
The account that we'll be using,
most of the time, is this devuser account.
We want to be able to create new databases,
and perform all the other database-related
tasks that we're going to learn about.
That means we have to give this
account the necessary access.
Select devuser on the left, then
click the Administrative Roles tab.
You have full control over the level of access
that you can grant to users, but it's often easier
to use these roles that have already been created.
For example, there's a UserAdmin role that can
create users, but can't alter databases,
and there's a DBManager role that can
manage databases, but doesn't have the
access needed to create or modify users.
We will want to perform all tasks, so click the
top option – DBA – to make devuser a Database
Administrator. We'll be able to perform
all tasks, when we log in as devuser.
If you make any changes here, make sure you
click Apply to apply the change, otherwise
the access rights won't be granted.
All right. So click on View now,
and from the View menu select Home.
We go back to where we were. Now we're
going to come over here, and click on this
little wrench – to the right of the plus
sign – which is for managing connections.
That should open up this Manage Server
Connections screen here, as you can see.
So I'm going to click on this Local Instance
MySQL80, and we're going to click the Test
Connection button, at the bottom right.
If it wants a password, that root
password, again, then type it in.
And you can see that we've
Successfully made the MySQL connection.
So that tells us that MySQL is working correctly,
and we're able to connect to the database.
So that's okay. I'm going to click on OK.
All right. Now let's click on the New
button – over on the left. This time
we're going to add a new connection.
We're going to add a new connection, for
what we're going to be using in this course.
For the Connection Name, I'm just
going to call it development,
but you can call it whatever you like.
And we're going to change the Username.
Instead of root, we're going to change that
to the username we created, which was devuser.
Then I'm going to click on test connection, to
make sure we can connect as our development user.
We'll be asked for the password, so
enter your devuser password this time.
Remembering that this isn't the
root user this is the other user.
Click on OK, and you can see we made a
successful connection. So that tells us
that the username and password are valid,
and we're good to go. So click on OK.
Close this screen, and we've now got this second
connection configured under the MySQL Connections.
We're going to be able to use that in the course.
I'm going to finish the video
here. Now we're actually done.
In the next video, as I mentioned
at the start, it's a video for Mac users.
So if you've just installed this on Windows,
and you haven't got a Mac and/or a Linux
machine, you can skip the next one or two videos,
and move on to the video after that – where
we start working with this MySQL instance that
we've just installed and configured.
I'll see you, in that next video.

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