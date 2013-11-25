package hr.element.iso7064;

public enum CheckResultType {

    SUCCESS(true, false),
    EMPTY(false, false),
    NON_DIGIT_CHARACTERS(false, false),
    INSUFFICIENT_DIGITS(false, false),
    SURPLUS_DIGITS(false, false),
    INVALID_CHECKSUM(false, false),
    UNKNOWN_ERROR(false, true);

    protected final boolean valid;
    protected final boolean error;

    private CheckResultType(
            final boolean valid,
            final boolean error) {
        this.valid = valid;
        this.error = error;
    }

    public boolean isValid() {
        return valid;
    }

    public boolean isInvalid() {
        return !valid;
    }

    public boolean isError() {
        return error;
    }
}
