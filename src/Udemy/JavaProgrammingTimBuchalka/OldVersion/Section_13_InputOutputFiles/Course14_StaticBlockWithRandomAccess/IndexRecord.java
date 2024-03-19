package CourseCodes.OldSections.Section_13_InputOutputFiles.Course14_StaticBlockWithRandomAccess;

/**
 * Created by timbuchalka on 22/2/17.
 */
public class IndexRecord {
    private int startByte;
    private int length;

    public IndexRecord(int startByte, int length) {
        this.startByte = startByte;
        this.length = length;
    }

    public int getStartByte() {
        return startByte;
    }

    public void setStartByte(int startByte) {
        this.startByte = startByte;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
