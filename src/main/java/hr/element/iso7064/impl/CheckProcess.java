package hr.element.iso7064.impl;

import static hr.element.iso7064.CheckResultType.*;
import hr.element.iso7064.Check;
import hr.element.iso7064.CheckAlgorithm;
import hr.element.iso7064.CheckBuilder;
import hr.element.iso7064.CheckResult;
import hr.element.iso7064.CheckResultType;

import java.util.ResourceBundle;

public class CheckProcess
        implements Check {

    protected final CheckBuilder checkBuilder;
    protected final ResourceBundle messages;
    protected final CheckAlgorithm checkAlgorithm;

    public CheckProcess(final CheckBuilder checkBuilder) {
        this.checkBuilder = checkBuilder;
        this.messages = ResourceBundle.getBundle(
                checkBuilder.getTemplate(), checkBuilder.getLocale());
        this.checkAlgorithm = checkBuilder.getAlgorithm();
    }

    protected CheckResult buildResult(final CheckResultType type, final Object... args) {
        final Object[] msgArgs = new Object[args.length + 1];
        msgArgs[0] = checkBuilder.getName();
        System.arraycopy(args, 0, msgArgs, 1, args.length);

        return new CheckResult(
                type,
                String.format(messages.getString(type.name()), msgArgs));
    }

    protected CheckResult performValidation(final int[] digits) {
        return buildResult(checkAlgorithm.validate(digits)
                ? SUCCESS
                : INVALID_CHECKSUM);
    }

    @Override
    public CheckResult check(final String code) {
        try {
            if (code.isEmpty()) {
                return buildResult(EMPTY);
            }

            final char[] chars
                    = (checkBuilder.getTrimWhitespaces()
                    ? code.trim()
                    : code).toCharArray();

            final int[] digits = new int[chars.length];

            for (int index = 0; index < chars.length; index++) {
                final char c = chars[index];

                if (c < '0' || c > '9') {
                    return buildResult(NON_DIGIT_CHARACTERS);
                }

                digits[index] = c - '0';
            }

            final int lengthDelta = checkBuilder.getLength() - digits.length;

            if (lengthDelta == 0) {
                return performValidation(digits);
            }

            if (lengthDelta > 0) {
                return buildResult(INSUFFICIENT_DIGITS, digits.length, checkBuilder.getLength());
            }

            if (!checkBuilder.getLeadingZeroesAllowed()) {
                return buildResult(SURPLUS_DIGITS, digits.length, checkBuilder.getLength());
            }

            for (int index = lengthDelta; index > 1; index--) {
                if (digits[index] != 0) {
                    return buildResult(SURPLUS_DIGITS, digits.length, checkBuilder.getLength());
                }
            }

            final int[] offsetDigits = new int[checkBuilder.getLength()];
            System.arraycopy(digits, lengthDelta, offsetDigits, 0, offsetDigits.length);
            return performValidation(digits);
        } catch (final Throwable t) {
            return buildResult(UNKNOWN_ERROR, t.getMessage());
        }
    }

    @Override
    public boolean validate(final String code) {
        return check(code).getType().isValid();
    }
}
