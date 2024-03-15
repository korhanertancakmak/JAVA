package InsuranceManagementSystem.InsuranceCompany;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 Design an abstract class named "Codes.InsuranceManagementSystem.Insurance" that represents insurance.
 In this abstract class will have variables such as:

 name of the insurance (String),
 insurance fee (double)
 start-end date of insurance

 Additionally, an abstract function named "calculate" will be defined.
 This abstract function will be populated in the inherited classes below.

 Derived from this abstract class, design 4 subclasses.

 "HealthInsurance" (private health insurance),
 "ResidenceInsurance" (home insurance),
 "TravelInsurance" (travel insurance),
 "CarInsurance" (car insurance)
**/

public abstract class Insurance {

    protected String insuranceName;
    protected double insurancePrice;
    protected final LocalDate insuranceStartDate = LocalDate.now();
    protected LocalDate insuranceEndDate;

    public Insurance(String insuranceName, LocalDate insuranceEndDate) {
        this.insuranceName = insuranceName;
        this.insuranceEndDate = insuranceEndDate;
    }

    abstract double calculate();

    abstract void setInsurancePrice(double margin);

    public LocalDate getInsuranceStartDate() {
        return insuranceStartDate;
    }

    public LocalDate getInsuranceEndDate() {
        return insuranceEndDate;
    }
}

class HealthInsurance extends Insurance{


    public HealthInsurance(LocalDate insuranceEndDate) {
        super("Health Insurance", insuranceEndDate);
    }

    // 500.0 $ monthly
    @Override
    double calculate() {
        LocalDate startDate = getInsuranceStartDate();
        LocalDate endDate = getInsuranceEndDate();
        long monthsDifference = ChronoUnit.MONTHS.between(startDate, endDate);
        this.insurancePrice = 500.0 * monthsDifference;
        return this.insurancePrice;
    }

    @Override
    void setInsurancePrice(double margin) { this.insurancePrice = margin * calculate(); }
}

class ResidenceInsurance extends Insurance{


    public ResidenceInsurance(LocalDate insuranceEndLocalDate) {
        super("Residence Insurance", insuranceEndLocalDate);
    }

    // 1000.0 $ monthly
    @Override
    double calculate() {
        LocalDate startDate = getInsuranceStartDate();
        LocalDate endDate = getInsuranceEndDate();
        long monthsDifference = ChronoUnit.MONTHS.between(startDate, endDate);
        this.insurancePrice = 1000.0 * monthsDifference;
        return this.insurancePrice;
    }

    @Override
    void setInsurancePrice(double margin) { this.insurancePrice = margin * calculate(); }
}

class TravelInsurance extends Insurance{


    public TravelInsurance(LocalDate insuranceEndLocalDate) {
        super("Travel Insurance", insuranceEndLocalDate);
    }

    // 250.0 $ monthly
    @Override
    double calculate() {
        LocalDate startDate = getInsuranceStartDate();
        LocalDate endDate = getInsuranceEndDate();
        long monthsDifference = ChronoUnit.MONTHS.between(startDate, endDate);
        this.insurancePrice = 250.0 * monthsDifference;
        return this.insurancePrice;
    }

    @Override
    void setInsurancePrice(double margin) { this.insurancePrice = margin * calculate(); }
}

class CarInsurance extends Insurance{


    public CarInsurance(LocalDate insuranceEndLocalDate) {
        super("Car Insurance", insuranceEndLocalDate);
    }

    // 750.0 $ monthly
    @Override
    double calculate() {
        LocalDate startDate = getInsuranceStartDate();
        LocalDate endDate = getInsuranceEndDate();
        long monthsDifference = ChronoUnit.MONTHS.between(startDate, endDate);
        this.insurancePrice = 750.0 * monthsDifference;
        return this.insurancePrice;
    }

    @Override
    void setInsurancePrice(double margin) { this.insurancePrice = margin * calculate(); }
}