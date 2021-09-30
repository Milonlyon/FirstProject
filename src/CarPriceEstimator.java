import java.time.LocalDate;

public class CarPriceEstimator {

    public double getSalePrice(String makeAndModel, int yearManufactured, double milesDriven,
                               int airBagsCount, boolean hasAbs, boolean hasEbd,
                               boolean hasRearViewCamera, boolean hasSunRoof, boolean hasAutoAC,
                               boolean hasAccidentHistory) {

        double salePrice = getPrice(makeAndModel, yearManufactured);
        int currentYear = LocalDate.now().getYear();
        int ageOfCar = currentYear - yearManufactured + 1;
        System.out.println("ageOfCar: " +  ageOfCar);


        // 1. Compute based on yearly depreciation value:
        // Note: Use compound arithmetic assignment operators
        if(ageOfCar <= 10) {                                       //       if age of car is less than or equal to 10 then
            salePrice -= (((5d/100) * salePrice)* ageOfCar);       //               price depreciates by 5% of original sale price every year
            System.out.println("salePrice after depreciation: " + salePrice);
        }
        else {
            return salePrice * 0.1;                                //(i.e., 10% of current salePrice);
        }
        // 2. Security Features

        if (!(airBagsCount >= 2 && hasAbs && hasEbd)) {            //      if car does NOT have atleast two airbags AND abs AND ebd
            salePrice = salePrice - 1000;                                   //              then reduce price by $1000
            System.out.println("salePrice after accounting for security features: " + salePrice);
        }
        // 3. Comfort Features

        if(hasRearViewCamera && (hasSunRoof || hasAutoAC)) {   // if car has rear-view camera AND either sunroof OR auto AC then
            salePrice += 500;                                  //    increment price by $500
            System.out.println("salePrice after accounting for comfort features: " + salePrice);
        }
        // 4. Past accidents

        if(hasAccidentHistory){                        // if car was involved in an accident then
            salePrice -=500;                                   //     reduce price by $500
            System.out.println("salePrice after accounting for past accidents: " + salePrice);
        }


        // 5. Based on additional miles driven
        double expectedMilesDriven = ageOfCar * 10000.0;
        double additionalMiles = milesDriven - expectedMilesDriven;


        if(additionalMiles > 1000 && additionalMiles <= 10000){             // a) if # miles over-driven is greater than 1000 AND less than or equal to 10000 then
            salePrice -= 500;                                               //        reduce sale price by 500
        } else if(additionalMiles > 10000 && additionalMiles <= 30000){     // b) if # miles over-driven is greater than 10000 AND less than or equal to 30000 then
            salePrice -= 1000;                                              //        reduce sale price by 1000
        } else if(additionalMiles > 30000){                                // c) if # miles over-driven is greater than 30000 then
            salePrice -= 1500;                                              //        reduce sale price by 1500
        }
        System.out.println("salePrice after accounting for miles driven: " + salePrice);
        return salePrice;
    }

    private double getPrice(String makeAndModel, int yearManufactured) {
        if (makeAndModel.equalsIgnoreCase("ford ecosport")) {
            return 20000.0;
        } else if (makeAndModel.equalsIgnoreCase("honda city")) {
            return 25000.0;
        } else if (makeAndModel.equalsIgnoreCase("toyota camry hybrid")) {
            return 30000.0;
        }
        return 20000.0;
    }

    public static void main(String[] args) {
        CarPriceEstimator carPriceEstimator = new CarPriceEstimator();
        double salesPrice = carPriceEstimator.getSalePrice("ford ecosport", 2018, 60000.0, 2, true, false, true, false, false, true);
    }
}


