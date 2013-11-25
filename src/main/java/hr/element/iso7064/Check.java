package hr.element.iso7064;

public interface Check {

    public CheckResult check(final String code);

    public boolean validate(final String code);
}
