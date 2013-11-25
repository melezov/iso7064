package hr.element.iso7064;

import hr.element.iso7064.impl.CheckProcess;

import java.util.Locale;

public class CheckBuilder {

    protected final String name;
    protected final CheckAlgorithm algorithm;
    protected final int length;
    protected final Locale locale;
    protected final String template;
    protected final boolean leadingZeroesAllowed;
    protected final boolean trimWhitespaces;

    protected CheckBuilder(
            final String name,
            final CheckAlgorithm algorithm,
            final int length,
            final Locale locale,
            final String template,
            final boolean leadingZeroesAllowed,
            final boolean trimWhitespaces) {
        this.name = name;
        this.algorithm = algorithm;
        this.locale = locale;
        this.length = length;
        this.template = template;
        this.leadingZeroesAllowed = leadingZeroesAllowed;
        this.trimWhitespaces = trimWhitespaces;
    }

    public CheckBuilder(
            final String name,
            final CheckAlgorithm algorithm,
            final int length) {
        this(name, algorithm, length, Locale.getDefault(), "hr.element.iso7064.Messages", false, false);
    }

    public String getName() {
        return name;
    }

    public CheckAlgorithm getAlgorithm() {
        return algorithm;
    }

    public int getLength() {
        return length;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getTemplate() {
        return template;
    }

    public boolean getLeadingZeroesAllowed() {
        return leadingZeroesAllowed;
    }

    public boolean getTrimWhitespaces() {
        return trimWhitespaces;
    }

    public CheckBuilder setName(final String name) {
        return new CheckBuilder(name, algorithm, length, locale, template, leadingZeroesAllowed, trimWhitespaces);
    }

    public CheckBuilder setAlgorithm(final CheckAlgorithm algorithm) {
        return new CheckBuilder(name, algorithm, length, locale, template, leadingZeroesAllowed, trimWhitespaces);
    }

    public CheckBuilder setLength(final int length) {
        return new CheckBuilder(name, algorithm, length, locale, template, leadingZeroesAllowed, trimWhitespaces);
    }

    public CheckBuilder setLocale(final Locale locale) {
        return new CheckBuilder(name, algorithm, length, locale, template, leadingZeroesAllowed, trimWhitespaces);
    }

    public CheckBuilder setLeadingZeroesAllowed(final boolean leadingZeroesAllowed) {
        return new CheckBuilder(name, algorithm, length, locale, template, leadingZeroesAllowed, trimWhitespaces);
    }

    public CheckBuilder setTrimWhitespaces(final boolean trimWhitespaces) {
        return new CheckBuilder(name, algorithm, length, locale, template, leadingZeroesAllowed, trimWhitespaces);
    }

    public Check build() {
        return new CheckProcess(this);
    }
}
