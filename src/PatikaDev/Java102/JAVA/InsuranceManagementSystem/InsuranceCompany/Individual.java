package InsuranceManagementSystem.InsuranceCompany;

import java.time.LocalDate;

public class Individual extends Account{


    public Individual(User user) {
        super(user);
    }

    @Override
    Individual login(String userEmail, String userPassWord) {
        if (userEmail.equalsIgnoreCase(this.user.getEmail()) && userPassWord.equalsIgnoreCase(this.user.getPassword())) {
            this.authenticationStatus = AuthenticationStatus.SUCCESS;
            this.user.setLastLoginLocalDate();
            return this;
        } else {
            this.authenticationStatus = AuthenticationStatus.FAIL;
            return null;
        }
    }

    @Override
    void addInsurance() {
        System.out.println("Welcome to adding an insurance to your account!");
        while(true) {
            System.out.println("Here are the packages you can purchase : ");
            System.out.println("-".repeat(50));
            System.out.println("0 - Get back to the Main Menu");
            System.out.println("1 - Health Insurance");
            System.out.println("2 - Residence Insurance");
            System.out.println("3 - Travel Insurance");
            System.out.println("4 - Car Insurance");
            System.out.println("-".repeat(50));
            System.out.print("Your choice : ");
            byte chosenInsurance = input.nextByte();
            System.out.println("Please enter the end date of the insurance package in the format of (Year, Month, Day)");
            switch (chosenInsurance) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    LocalDate endDate = LocalDate.of(input.nextInt(), input.nextInt(), input.nextInt());
                    Insurance insurance = new HealthInsurance(endDate);
                    this.insurances.add(insurance);
                    this.insurances.getLast().setInsurancePrice(1.0);
                    System.out.println("Health Insurance is added to the user's account!");
                    return;
                }
                case 2 -> {
                    LocalDate endDate = LocalDate.of(input.nextInt(), input.nextInt(), input.nextInt());
                    Insurance insurance = new ResidenceInsurance(endDate);
                    this.insurances.add(insurance);
                    this.insurances.getLast().setInsurancePrice(1.0);
                    System.out.println("Residence Insurance is added to the user's account!");
                    return;
                }
                case 3 -> {
                    LocalDate endDate = LocalDate.of(input.nextInt(), input.nextInt(), input.nextInt());
                    Insurance insurance = new TravelInsurance(endDate);
                    this.insurances.add(insurance);
                    this.insurances.getLast().setInsurancePrice(1.0);
                    System.out.println("Travel Insurance is added to the user's account!");
                    return;
                }
                case 4 -> {
                    LocalDate endDate = LocalDate.of(input.nextInt(), input.nextInt(), input.nextInt());
                    Insurance insurance = new CarInsurance(endDate);
                    this.insurances.add(insurance);
                    this.insurances.getLast().setInsurancePrice(1.0);
                    System.out.println("Car Insurance is added to the user's account!");
                    return;
                }
                default -> System.out.println("Enter a valid value!");
            }
        }
    }

    @Override
    public int compareTo(Account other) {
        // Implement comparison logic based on your criteria
        // For example, compare based on email
        return this.user.getEmail().compareTo(other.getUser().getEmail());
    }

    @Override
    public int hashCode() {
        return this.user.getEmail().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.user.equals(obj);
    }
}