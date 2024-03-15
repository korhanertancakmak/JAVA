package PatikaDev.Java102.JAVA.InsuranceManagementSystem.InsuranceCompany;

/**
 Codes.InsuranceManagementSystem.Address information is of two types.
 Design two classes: Home address ("HomeAddress") and
 Business Codes.InsuranceManagementSystem.Address ("BusinessAddress").
 These address classes will inherit from an interface called "Codes.InsuranceManagementSystem.Address."
 However, you will decide which functions should be in this interface.
**/
public interface Address {
    String getAddress();

    void setAddress(Address address);
}

class HomeAddress implements Address {
    private String street;
    private String city;
    private String state;
    private String postalCode;

    public HomeAddress(String street, String city, String state, String postalCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String getAddress() {
        return street + " " + city + " " + state + " " + postalCode;
    }

    @Override
    public void setAddress(Address address) {
        this.street = ((HomeAddress) address).getStreet();
        this.city = ((HomeAddress) address).getCity();
        this.state = ((HomeAddress) address).getState();
        this.postalCode = ((HomeAddress) address).getPostalCode();
    }
}

class BusinessAddress implements Address {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String companyName;

    public BusinessAddress(String street, String city, String state, String postalCode, String companyName) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.companyName = companyName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String getAddress() {
        return "Business Address for " + companyName + ":\n" + street + "\n" + city + ", " + state + " " + postalCode;
    }

    @Override
    public void setAddress(Address address) {
        this.companyName = ((BusinessAddress) address).companyName;
        this.street = ((BusinessAddress) address).getStreet();
        this.city = ((BusinessAddress) address).getCity();
        this.state = ((BusinessAddress) address).getState();
        this.postalCode = ((BusinessAddress) address).getPostalCode();
    }
}