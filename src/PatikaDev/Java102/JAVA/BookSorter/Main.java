package JAVA.BookSorter;

import java.util.*;

/**
Design a class named Book. 
Inherit this class from the Comparable interface and override the "compareTo" method. 
In this method, write the code that sorts the book by name from A to Z. 
Create 5 objects from this class and store the objects in a Set type structure. 
Then, for the second time, use a data structure of type Set and arrange the books according to the number of pages.

The Book class consists of book name, number of pages, author's name, and publication date variables.
**/

public class Main {
    public static void main(String[] args) {
        Set<Book> bookList = new TreeSet<>();
        bookList.add(new Book("The Hobbit",320, "J. R. R. Tolkien", 1937));
        bookList.add(new Book("Verity",336, "Colleen Hoover",2019));
        bookList.add(new Book("Worthy",416, "Jamie Kern Lima",2024));
        bookList.add(new Book("Atomic Habits",321, "James Clear",2018));
        bookList.add(new Book("Fourth Wing",512, "Rebecca Yarros",2023));

        for (Book bd : bookList)
            System.out.println(bd.getBookName() + " " +
                    bd.getNumsOfPgs() + " " +
                    bd.getAuthorsName() + " " +
                    bd.getPublicationYear());
    }
}
