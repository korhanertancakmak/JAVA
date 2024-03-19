package CourseCodes.NewSections.Section_19_Concurrency.Course27_WatcherServiceFileWatcher;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

//Part-1
/**
                                                WatchService Interface

        In this lecture, The final type I want to talk about is the WatchService. This is a special type of service, which
    watches registered objects for changes and events. For example, a file manager may use a watch service, to monitor
    a directory for changes, so that it can update its display of the list of files, when files are created or deleted.

                                                Using a Watch Service

        A Watchable object is registered with the watch service. When events occur on the registered object, they're queued
    on the watch service. A consumer can retrieve and process the event information, using the poll or take methods on the
    queue. Let's look at one type of the WatchService, the file watcher. I've FileWatcherExample Class and again I will
    create a main method here.
 **/
//End-Part-1

record Student(String name, int enrolledYear, int studentId) implements Comparable<Student> {

    @Override
    public int compareTo(Student o) {
        return this.studentId - o.studentId;
    }
}

class StudentId {

    private int id = 0;

    public int getId() {
        return id;
    }

    public synchronized int getNextId() {
        return ++id;
    }
}

class AtomicStudentId {

    private final AtomicInteger nextId = new AtomicInteger(0);

    public int getId() {
        return nextId.get();
    }

    public int getNextId() {
        return nextId.incrementAndGet();
    }
}

public class Main {

    private static Random random = new Random();

    private static ConcurrentSkipListSet<Student> studentSet = new ConcurrentSkipListSet<>();

    public static void main(String[] args) {

        AtomicStudentId idGenerator = new AtomicStudentId();
        Callable<Student> studentMaker = () -> {
            int studentId = idGenerator.getNextId();
            Student student = new Student("Tim " + studentId, random.nextInt(2018, 2025), studentId);
            studentSet.add(student);
            return student;
        };

        var executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            studentSet.clear();
            try {
                var futures = executor.invokeAll(Collections.nCopies(10, studentMaker));
                System.out.println("# of students = " + studentSet.size());
//                studentSet.forEach(System.out::println);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        executor.shutdown();
    }
}
