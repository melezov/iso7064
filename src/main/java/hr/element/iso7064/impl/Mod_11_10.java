package hr.element.iso7064.impl;

import hr.element.iso7064.CheckAlgorithm;

public enum Mod_11_10 implements CheckAlgorithm {
    INSTANCE;

    @Override
    public boolean validate(final int[] digits) {
        int a = 10;

        final int end = digits.length - 1;
        for (int x = 0; x < end; x++) {
            a = (a + digits[x]) % 10;
            if (a == 0) a = 10;
            a = (a << 1) % 11;
        }

        final int control = a == 1 ? 0 : 11 - a;
        final int last = digits[digits.length - 1];

        return last == control;
    }
}
