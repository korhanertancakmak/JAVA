# [Section-18. Working with Databases]()

## [a. Database Basics]()
<div align="justify">

```java  

```

```html  

```

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



On this slide, I'm showing you several surveys
of the popularity of market place databases.
The first is stackoverflow's annual survey which;;
solicits feedback from developers,;
on what they use professionally.;
This survey is limited to respondents, so it could;
be argued it's skewed towards professionals who;;
are just more likely to take such a survey.
The second is from Statista,;;
and the third from DB-Engines, which;
are similar and have similar results.;
The last survey polls the internet for;
monthly statistics, using the number of;;
mentions of the system on websites, the frequency;
of technical discussions, and the number of job;;
offers as part of their scoring system.
Make sure to check out these surveys,;;
and others if you're interested, or if you're;
trying to make this decision for yourself.;
However you look at this feedback,;
MySQL is squarely near the top,;;
so that's another reason I chose it.
Finally, just because the course will
be taught using MySQL, this doesn't;
mean that you have to use it.;
You're welcome to use whichever database you;
want, especially if you have another already;;
installed, and have experience there.
I'll be providing scripts to create;;
a specific database schema, for MySQL,;
which could be tweaked for your database.;
In addition, I'll show you several;
different connection strings,;;
to connect to different databases.
I'll be making an effort to use ANSI SQL,;;
a standard set of commands, which;
most databases should support.;
I'll try to avoid vendor specific SQL;
statements, or point them out when I can.;
There are good reasons to use;
ANSI SQL in your java code.;
Doing so, lets you swap the database engine,;
by simply changing your connection string.;
This does sometimes happen, as corporations;
decide the best way to house their data,;;
based on their own unique situation, and as;
technologies change and compete with one another.;
I'll explain all of this in;
more detail as we proceed.;
Ok, so let's move forward.
The next three videos will be;;
the MySQL installation videos, so pick the;
one appropriate for your operating system.
</div>