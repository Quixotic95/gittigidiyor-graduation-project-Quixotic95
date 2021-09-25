package com.gittigidiyor.quxiotic95.creditapp.utility;

public class CreditScoreGenerator {

    public static double generateCreditScore(String tckn) {

        int lastNumberOfTckn = Integer.parseInt(String.valueOf(tckn.charAt(10)));

        if (lastNumberOfTckn == 2) {
            return 550;
        } else if (lastNumberOfTckn == 4) {
            return 1000;
        } else if (lastNumberOfTckn == 6) {
            return 400;
        } else if (lastNumberOfTckn == 8) {
            return 900;
        } else if (lastNumberOfTckn == 0) {
            return 2000;
        } else {
            throw new RuntimeException("Something went wrong while generating Credit Score!");
        }
    }

}
