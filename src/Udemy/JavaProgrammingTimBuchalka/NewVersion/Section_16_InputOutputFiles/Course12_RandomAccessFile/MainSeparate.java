package Udemy.JavaProgrammingTimBuchalka.NewVersion.Section_16_InputOutputFiles.Course12_RandomAccessFile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MainSeparate {

    private static final Map<Long, Long> indexedIds = new LinkedHashMap<>();    // key = record id, value = file pointer position
    private static int recordsInFile = 0;                                       // # of records in the file

    static {
        try (RandomAccessFile rb = new RandomAccessFile(
                "./src/Udemy/JavaProgrammingTimBuchalka/NewVersion/Section_16_InputOutputFiles/Course12_RandomAccessFile/student.idx",
                "r");) {
            loadIndex(rb, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        BuildStudentData.build("student", true);            // commented via Part-11 and uncommented via Part-15

        String cwdPath = "./src/CourseCodes/NewSections/Section_18_InputOutputFiles/Course12_RandomAccessFile/";
        try (RandomAccessFile ra = new RandomAccessFile(cwdPath + "student.dat", "r")) {
            //loadIndex(ra, 0);
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
