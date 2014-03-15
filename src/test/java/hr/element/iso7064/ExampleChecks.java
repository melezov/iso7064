package hr.element.iso7064;

import hr.element.iso7064.impl.Mod_11_10;

public class ExampleChecks {
    public static final Check OIBCheck
            = new CheckBuilder("OIB", Mod_11_10.INSTANCE, 11).build();

    public static final Check VBDICheck
            = new CheckBuilder("VBDI", Mod_11_10.INSTANCE, 7).build();
}
