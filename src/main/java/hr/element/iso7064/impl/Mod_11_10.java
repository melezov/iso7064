package hr.element.iso7064.impl;

import hr.element.iso7064.CheckAlgorithm;

public enum Mod_11_10 implements CheckAlgorithm {
    INSTANCE;

    @Override
    public boolean validate(final int[] digits) {
        int a = 10;

        for (int x = 0; x < digits.length - 1; x++) {
            a = (a + digits[x]) % 10;
            if (a == 0) {
                a = 10;
            }
            a <<= 1;
            a = a % 11;
        }

        int control = 11 - a;
        if (control == 10) {
            control = 0;
        }

        final int last = digits[digits.length - 1];

        return last == control;
    }
}
