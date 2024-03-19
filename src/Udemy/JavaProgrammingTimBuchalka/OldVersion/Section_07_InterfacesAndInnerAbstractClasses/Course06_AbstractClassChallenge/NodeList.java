package CourseCodes.OldSections.Section_07_InterfacesAndInnerAbstractClasses.Course06_AbstractClassChallenge;

public interface NodeList {
    ListItem getRoot();
    boolean addItem(ListItem item);
    boolean removeItem(ListItem item);
    void traverse(ListItem root);
}
