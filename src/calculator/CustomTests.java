package calculator;

import UNumber.UNumber;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void testAdditionWithoutUnits() {
        BusinessLogic bl = new BusinessLogic();
        bl.setOperand1("12", "0", 0, 0, 0);
        bl.setOperand2("23", "0", 0, 0, 0);
        String ans = bl.addition();
        UNumber thirtyFive = new UNumber(35);
        assertEquals(ans, thirtyFive.toString());
    }
}
