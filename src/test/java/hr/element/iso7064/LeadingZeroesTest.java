package hr.element.iso7064;

import hr.element.iso7064.impl.Mod_11_10;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class LeadingZeroesTest {
    private static CheckBuilder checkBuilder;

    @BeforeClass
    public static void initCheck() {
        checkBuilder = new CheckBuilder("xxx", Mod_11_10.INSTANCE, 11);
    }

    @Test
    public void checkWhitespacesAllowed() {
        final CheckResult result = checkBuilder
                .setLeadingZeroesAllowed(true)
                .build().check("00012345678903");
        assertSame(CheckResultType.SUCCESS, result.getType());
    }

    @Test
    public void checkWhitespacesNotAllowed() {
        final CheckResult result = checkBuilder
                .setLeadingZeroesAllowed(false)
                .build().check("00012345678903");
        assertSame(CheckResultType.SURPLUS_DIGITS, result.getType());
    }
}
