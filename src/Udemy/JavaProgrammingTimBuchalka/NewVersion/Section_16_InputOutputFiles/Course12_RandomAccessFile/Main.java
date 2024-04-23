package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course12_RandomAccessFile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class Main {

    private static final Map<Long, Long> indexedIds = new LinkedHashMap<>();    // key = record id, value = file pointer position
    private static int recordsInFile = 0;                                       // # of records in the file


    public static void main(String[] args) {

        //BuildStudentData.build("studentData", false);            // commented via Part-11

        String cwdPath = "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course12_RandomAccessFile/";
        try (RandomAccessFile ra = new RandomAccessFile(cwdPath + "studentData.dat", "r")) {
            loadIndex(ra, 0);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a Student Id or 0 to quit");
            while (scanner.hasNext()) {
                long studentId = Long.parseLong(scanner.nextLine());
                if (studentId < 1) {
                    break;
                }
                ra.seek(indexedIds.get(studentId));
                String targetedRecord = ra.readUTF();
                System.out.println(targetedRecord);
                System.out.println("Enter another Student Id or 0 to quit");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadIndex(RandomAccessFile ra, int indexPosition) {
        try {
            ra.seek(indexPosition);
            recordsInFile = ra.readInt();
            System.out.println(recordsInFile);
            for (int i = 0; i < recordsInFile; i++) {
                indexedIds.put(ra.readLong(), ra.readLong());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

