package Udemy.JavaProgrammingTimBuchalka.OldVersion.Section_07_InterfacesAndInnerAbstractClasses.Course01_Interfaces;

public interface ITelephone {
    void powerOn();
    void dial(int phoneNumber);
    void answer();
    boolean callPhone(int phoneNumber);
    boolean isRinging();
}
