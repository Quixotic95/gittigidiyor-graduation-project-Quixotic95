package com.gittigidiyor.quxiotic95.creditapp.utility;

public class LoanLimitCalculator {

    private static final double CREDIT_LIMIT_MULTIPLIER = 4;
    private static final double LOWER_CREDIT_SCORE_FOR_DENY = 500;
    private static final double UPPER_CREDIT_SCORE_FOR_CALCULATION = 1000;

    public static double calculateLoanLimit(double monthlyIncome, double creditScore) {
        if (creditScore < LOWER_CREDIT_SCORE_FOR_DENY) {
            return 0;
        } else if (LOWER_CREDIT_SCORE_FOR_DENY <= creditScore && creditScore <= UPPER_CREDIT_SCORE_FOR_CALCULATION) {
            if (monthlyIncome < 5000) {
                return 10000;
            } else if (monthlyIncome >= 5000) {
                return 20000;
            } else {
                throw new RuntimeException("Something went wrong while calculating Loan Limit");
            }
        } else if (creditScore >= UPPER_CREDIT_SCORE_FOR_CALCULATION) {
            return monthlyIncome * CREDIT_LIMIT_MULTIPLIER;
        } else {
            throw new RuntimeException("Something went wrong while calculating Loan Limit");
        }
    }

}
