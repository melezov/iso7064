package hr.element.iso7064;

import hr.element.iso7064.impl.Mod_11_10;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class WhitespaceTest {
    private static CheckBuilder checkBuilder;

    @BeforeClass
    public static void initCheck() {
        checkBuilder = new CheckBuilder("xxx", Mod_11_10.INSTANCE, 11);
    }

    @Test
    public void checkWhitespacesAllowed() {
        final CheckResult result = checkBuilder
                .setTrimWhitespaces(true)
                .build().check(" \t\r\n12345678903 \t\r\n");
        assertSame(CheckResultType.SUCCESS, result.getType());
    }

    @Test
    public void checkWhitespacesNotAllowed() {
        final CheckResult result = checkBuilder
                .setTrimWhitespaces(false)
                .build().check(" \t\r\n12345678903 \t\r\n");
        assertSame(CheckResultType.NON_DIGIT_CHARACTERS, result.getType());
    }
}
