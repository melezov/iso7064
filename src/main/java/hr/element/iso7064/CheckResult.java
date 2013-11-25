package hr.element.iso7064;

public class CheckResult {

    protected final CheckResultType type;
    protected final String message;

    public CheckResultType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public boolean isValid() {
        return type.isValid();
    }

    public boolean isInvalid() {
        return type.isInvalid();
    }

    public boolean isError() {
        return type.isError();
    }

    public CheckResult(final CheckResultType type, final String message) {
        this.type = type;
        this.message = message;
    }

    @Override
    public String toString() {
        return type + "(" + message + ")";
    }
}
