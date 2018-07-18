package calculator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CustomTests {
    @Test
    public void testLegalIntInput() {
        String input = "123456";
        NumericInput num = new NumericInput();
        num.extendInput(input);
        assertTrue(num.isInFinalState());
    }

    @Test
    public void testIllegalIntInput() {
        String input = "1234ab";
        NumericInput num = new NumericInput();
        num.extendInput(input);
        assertFalse(num.isInFinalState());
    }
}
