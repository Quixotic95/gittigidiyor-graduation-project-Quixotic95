package com.gittigidiyor.quixotic95.loanapp.validation.validator;

import com.gittigidiyor.quixotic95.loanapp.validation.TCKNConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

public class TCKNConstraintValidator implements ConstraintValidator<TCKNConstraint, String> {

    @Override
    public void initialize(TCKNConstraint tcknConstraint) {
    }

    @Override
    public boolean isValid(String tckn, ConstraintValidatorContext constraintValidatorContext) {

        ArrayList<Integer> firstListOfNumbers = new ArrayList<>();
        firstListOfNumbers.add(charToIntegerParser(tckn.charAt(0)));
        firstListOfNumbers.add(charToIntegerParser(tckn.charAt(2)));
        firstListOfNumbers.add(charToIntegerParser(tckn.charAt(4)));
        firstListOfNumbers.add(charToIntegerParser(tckn.charAt(6)));
        firstListOfNumbers.add(charToIntegerParser(tckn.charAt(8)));

        int firstTotal = 0;

        int secondTotal = 0;

        ArrayList<Integer> secondListOfNumbers = new ArrayList<>();
        secondListOfNumbers.add(charToIntegerParser(tckn.charAt(1)));
        secondListOfNumbers.add(charToIntegerParser(tckn.charAt(3)));
        secondListOfNumbers.add(charToIntegerParser(tckn.charAt(5)));
        secondListOfNumbers.add(charToIntegerParser(tckn.charAt(7)));

        for (int number : firstListOfNumbers) {
            firstTotal += number;
        }

        for (int number : secondListOfNumbers) {
            secondTotal += number;
        }

        if (((firstTotal * 7) + (secondTotal * 9)) % 10 != charToIntegerParser(tckn.charAt(9))) {
            return false;
        }

        return (firstTotal * 8) % 10 == charToIntegerParser(tckn.charAt(10));
    }

    private Integer charToIntegerParser(char c) {
        return (Integer.parseInt(String.valueOf(c)));
    }
}
