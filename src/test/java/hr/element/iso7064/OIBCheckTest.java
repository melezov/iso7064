package hr.element.iso7064;

import hr.element.iso7064.impl.Mod_11_10;
import java.util.Locale;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OIBCheckTest {
    private static Check oibCheck;

    @BeforeClass
    public static void initCheck() {
        oibCheck = new CheckBuilder("OIB", Mod_11_10.INSTANCE, 11)
                .setLocale(new Locale("HR"))
                .build();
    }

    @Test
    public void checkSuccess() {
        final CheckResult result = oibCheck.check("12345678903");
        assertSame(CheckResultType.SUCCESS, result.getType());
        assertEquals("OIB izgleda ispravno", result.getMessage());
    }

    @Test
    public void checkEmpty() {
        final CheckResult result = oibCheck.check("");
        assertSame(CheckResultType.EMPTY, result.getType());
        assertEquals("OIB je prazan", result.getMessage());
    }

    @Test
    public void checkNonDigitCharacters() {
        final CheckResult result = oibCheck.check("12345x78903");
        assertSame(CheckResultType.NON_DIGIT_CHARACTERS, result.getType());
        assertEquals("OIB se mora sastojati samo od znamenki", result.getMessage());
    }

    @Test
    public void checkInsufficientDigits() {
        final CheckResult result = oibCheck.check("1234567890");
        assertSame(CheckResultType.INSUFFICIENT_DIGITS, result.getType());
        assertEquals("OIB je prekratak (ima samo 10, a treba biti 11 znamenki)", result.getMessage());
    }

    @Test
    public void checkSurplusDigits() {
        final CheckResult result = oibCheck.check("123456789012");
        assertSame(CheckResultType.SURPLUS_DIGITS, result.getType());
        assertEquals("OIB je predugačak (ima 12, a treba biti 11 znamenki)", result.getMessage());
    }

    @Test
    public void checkInvalidChecksum() {
        final CheckResult result = oibCheck.check("12345678901");
        assertSame(CheckResultType.INVALID_CHECKSUM, result.getType());
        assertEquals("OIB je neispravan", result.getMessage());
    }
}
