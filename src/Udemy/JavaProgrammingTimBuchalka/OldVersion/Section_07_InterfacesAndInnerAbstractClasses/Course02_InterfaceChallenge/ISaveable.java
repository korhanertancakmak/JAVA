package CourseCodes.OldSections.Section_07_InterfacesAndInnerAbstractClasses.Course02_InterfaceChallenge;

import java.util.List;

public interface ISaveable {
    List<String> write();
    void read(List<String> savedValues);

}
