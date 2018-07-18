package calculator;


import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertTrue;

class NumericInputTest {
    public static void main(String[] args) {
        Result res = JUnitCore.runClasses(CustomTests.class);
        for (Failure fail: res.getFailures()) {
            System.out.println(fail.toString());
        }
    }
}