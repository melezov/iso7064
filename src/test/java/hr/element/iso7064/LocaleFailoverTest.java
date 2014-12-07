package hr.element.iso7064;

import hr.element.iso7064.impl.Mod_11_10;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class LocaleFailoverTest {
    private static Check vbdiCheck;

    @BeforeClass
    public static void initCheck() {
        vbdiCheck = new CheckBuilder("VBDI", Mod_11_10.INSTANCE, 7)
                .setLocale(Locale.GERMANY) // will default to English resource
                .build();
    }

    @Test
    public void checkSuccess() {
        final CheckResult result = vbdiCheck.check("1001005");
        assertSame(CheckResultType.SUCCESS, result.getType());
        assertEquals("VBDI looks valid", result.getMessage());
    }

    @Test
    public void checkEmpty() {
        final CheckResult result = vbdiCheck.check("");
        assertSame(CheckResultType.EMPTY, result.getType());
        assertEquals("VBDI is empty", result.getMessage());
    }

    @Test
    public void checkNonDigitCharacters() {
        final CheckResult result = vbdiCheck.check("123x567");
        assertSame(CheckResultType.NON_DIGIT_CHARACTERS, result.getType());
        assertEquals("VBDI contains non-digit characters", result.getMessage());
    }

    @Test
    public void checkInsufficientDigits() {
        final CheckResult result = vbdiCheck.check("123456");
        assertSame(CheckResultType.INSUFFICIENT_DIGITS, result.getType());
        assertEquals("VBDI is too short, got 6 digits where 7 were expected", result.getMessage());
    }

    @Test
    public void checkSurplusDigits() {
        final CheckResult result = vbdiCheck.check("12345678");
        assertSame(CheckResultType.SURPLUS_DIGITS, result.getType());
        assertEquals("VBDI is too long, got 8 digits where 7 were expected", result.getMessage());
    }

    @Test
    public void checkInvalidChecksum() {
        final CheckResult result = vbdiCheck.check("1001009");
        assertSame(CheckResultType.INVALID_CHECKSUM, result.getType());
        assertEquals("VBDI is invalid", result.getMessage());
    }
}
