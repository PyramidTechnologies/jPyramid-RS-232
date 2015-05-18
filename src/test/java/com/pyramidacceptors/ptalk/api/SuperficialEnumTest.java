package com.pyramidacceptors.ptalk.api;

import com.pyramidacceptors.ptalk.api.event.Events;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by catix on 5/17/2015.
 */
public class SuperficialEnumTest {

    public static void superficialEnumCodeCoverage(Class<? extends Enum<?>> enumClass) {
        try {
            for (Object o : (Object[])enumClass.getMethod("values").invoke(null)) {
                enumClass.getMethod("valueOf", String.class).invoke(null, o.toString());
            }
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This is only for a "completionist's" obsession. It serves zero
     * purpose in testing and just makes the code coverage say 100% for enum
     * data types.
     * @throws Exception
     */
    @Test
    public void testAllEnums() throws Exception {
        superficialEnumCodeCoverage(RS232Configuration.RS232State.class);
        superficialEnumCodeCoverage(APILevel.class);
        superficialEnumCodeCoverage(BillDirections.class);
        superficialEnumCodeCoverage(Events.class);

        // pass
        assertTrue(true);
    }

}
