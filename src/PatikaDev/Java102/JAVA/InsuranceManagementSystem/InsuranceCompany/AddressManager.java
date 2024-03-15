package InsuranceManagementSystem.InsuranceCompany;

/**
 Design a class called "Codes.InsuranceManagementSystem.AddressManager" that is responsible for adding and
 removing customer addresses.
 Define two static functions in this class that can add or remove elements
 to the address list of the "Codes.InsuranceManagementSystem.User" object.
 You determine these function names.
**/

public class AddressManager {

    public static boolean addAddress(Address address, User user) {
        return user.getAddressList().add(address);
    }

    public static boolean removeAddress(Address address, User user) {
        return user.getAddressList().remove(address);
    }
}
