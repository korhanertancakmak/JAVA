package CourseCodes.NewSections.Exercises0030To0049.Challenge0044_MobilePhone;

import java.util.ArrayList;

public class MobilePhone {

    private String myNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();
    }

    public boolean addNewContact(Contact contact) {
        // has one parameter of type Contact and returns a boolean. Returns true if the contact doesn't exist, or false
        // if the contact already exists.
        if (findContact(contact) != -1) {
            return false;
        } else {
            return myContacts.add(contact);
        }
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        // has two parameters of type Contact (the old contact that will be updated with the new contact) and returns a
        // boolean. Returns true if the contact exists and was updated successfully, or false if the contact doesn't exist.

        if (findContact(oldContact) != -1) {
            myContacts.set(findContact(oldContact), newContact);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeContact(Contact contact) {
        // has one parameter of type Contact and returns a boolean. Returns true if the contact exists and was removed
        // successfully, or false if the contact doesn't exist.

        if (findContact(contact) != -1) {
            myContacts.remove(findContact(contact));
            return true;
        } else {
            return false;
        }
    }

    private int findContact(Contact contact) {
        // This method has one parameter of type Contact and returns an int. The returned value is its position in the
        // ArrayList, it will either be -1 (doesn't exist) or a value greater than or equal to 0 (does exist).
        return findContact(contact.getName());
    }

    private int findContact(String name) {
        // same as above, only it has one parameter of type String.
        for (int i = 0; i < myContacts.size(); i++) {
            Contact contact = myContacts.get(i);
            if (contact.getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Contact queryContact(String name) {
        // has one parameter of type String and returns a Contact. Use the String to search the name and then return the
        // Contact. Return null otherwise.
        if (findContact(name) != -1)
            return myContacts.get(findContact(name));
        else {
            return null;
        }
    }

    public void printContacts() {
        // has no parameters and doesn't return anything. Print the contacts in the following format:
        // Contact List:
        // 1. Bob -> 31415926
        // 2. Alice -> 16180339
        // 3. Tom -> 11235813
        // 4. Jane -> 23571113
        System.out.println("Contact List:");
        for (int i = 0; i < myContacts.size(); i++) {
            Contact contact = myContacts.get(i);
            System.out.printf("%d. %s -> %s", i + 1, contact.getName(), contact.getPhoneNumber());
        }

    }
}

