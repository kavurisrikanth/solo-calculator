package calculator;

import UNumber.UNumber;

import java.util.Scanner;


/**
 * <p> Title: CalculatorValue Class. </p>
 * 
 * <p> Description: A component of a JavaFX demonstration application that performs computations </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 4.00	2017-10-18 Long integer implementation of the CalculatorValue class 
 * 
 */
public class CalculatorValue {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	// These are the major values that define a calculator value
	// long measuredValue = 0;
//    double measuredValue = 0;
	private UNumber measuredValue, errorTerm;
	private String errorMessage = "";

	private Unit myUnit = new Unit();

	/*****
	 * This is the default constructor
	 */
	public CalculatorValue() {
	}

	/*****
	 * This constructor creates a calculator value based on a long integer. For future calculators, it
	 * is best to avoid using this constructor.
	 */
//	public CalculatorValue(long v) {
//		measuredValue = v;
//	}

//    public CalculatorValue(double v) {
//        measuredValue = v;
//    }

    public CalculatorValue(double v) {
        measuredValue = new UNumber(v);
        errorTerm     = new UNumber(0);
    }

    public CalculatorValue(UNumber v) {
        measuredValue = v;
		errorTerm     = new UNumber(0);
    }

    public CalculatorValue(double v, double e) {
        measuredValue = new UNumber(v);
        errorTerm     = new UNumber(e);
    }

    public CalculatorValue(UNumber v, UNumber e) {
        measuredValue = v;
        errorTerm     = e;
    }

	/*****
	 * This copy constructor creates a duplicate of an already existing calculator value
	 */
	public CalculatorValue(CalculatorValue v) {
		measuredValue = v.measuredValue;
		errorTerm     = v.errorTerm;
		errorMessage  = v.errorMessage;
		myUnit        = new Unit(v.getMyUnit());
	}

	/*
	 * This constructor creates a calculator value from a string... Due to the nature
	 * of the input, there is a high probability that the input has errors, so the
	 * routine returns the value with the error message value set to empty or the string
	 * of an error message.
	 */

	/*****
	public CalculatorValue(String s) {
		measuredValue = 0;
		if (s.length() == 0) {								// If there is nothing there,
			errorMessage = "***Error*** Input is empty";		// signal an error	
			return;												
		}
		// If the first character is a plus sign, ignore it.
		int start = 0;										// Start at character position zero
		boolean negative = false;							// Assume the value is not negative
		if (s.charAt(start) == '+')							// See if the first character is '+'
			 start++;										// If so, skip it and ignore it
		
		// If the first character is a minus sign, skip over it, but remember it
		else if (s.charAt(start) == '-'){					// See if the first character is '-'
			start++;											// if so, skip it
			negative = true;									// but do not ignore it
		}
		
		// See if the user-entered string can be converted into an integer value
		Scanner tempScanner = new Scanner(s.substring(start));// Create scanner for the digits
		if (!tempScanner.hasNextLong()) {					// See if the next token is a valid
			errorMessage = "***Error*** Invalid value"; 		// integer value.  If not, signal there
			tempScanner.close();								// return a zero
			return;												
		}
		
		// Convert the user-entered string to a integer value and see if something else is following it
		measuredValue = tempScanner.nextLong();				// Convert the value and check to see
		if (tempScanner.hasNext()) {							// that there is nothing else is 
			errorMessage = "***Error*** Excess data"; 		// following the value.  If so, it
			tempScanner.close();								// is an error.  Therefore we must
			measuredValue = 0;								// return a zero value.
			return;													
		}
		tempScanner.close();
		errorMessage = "";
		if (negative)										// Return the proper value based
			measuredValue = -measuredValue;					// on the state of the flag that
	}
	*/

	public CalculatorValue(String v, String e) {
	    getMeasuredValueFromString(v);
	    getErrorTermFromString(e);
    }

    public CalculatorValue(String v, String e, Double m, Double l, Double t) {
	    this(v, e);
	    myUnit.setAll(m, l, t);
    }

	private void getMeasuredValueFromString(String s) {
        measuredValue = new UNumber(0);
        if (s.length() == 0) {								// If there is nothing there,
            errorMessage = "***Error*** Input is empty";		// signal an error
            return;
        }
        // If the first character is a plus sign, ignore it.
        int start = 0;										// Start at character position zero
        boolean negative = false;							// Assume the value is not negative
        if (s.charAt(start) == '+')							// See if the first character is '+'
            start++;										// If so, skip it and ignore it

            // If the first character is a minus sign, skip over it, but remember it
        else if (s.charAt(start) == '-'){					// See if the first character is '-'
            start++;											// if so, skip it
            negative = true;									// but do not ignore it
        }

        // See if the user-entered string can be converted into an integer value
        Scanner tempScanner = new Scanner(s.substring(start));// Create scanner for the digits
        if (!tempScanner.hasNextDouble()) {					// See if the next token is a valid
            errorMessage = "***Error*** Invalid value"; 		// integer value.  If not, signal there
            tempScanner.close();								// return a zero
            return;
        }

        // Convert the user-entered string to a integer value and see if something else is following it
        measuredValue = new UNumber(tempScanner.nextDouble());				// Convert the value and check to see
        if (tempScanner.hasNext()) {							// that there is nothing else is
            errorMessage = "***Error*** Excess data"; 		// following the value.  If so, it
            tempScanner.close();								// is an error.  Therefore we must
            measuredValue = new UNumber(0);								// return a zero value.
            return;
        }
        tempScanner.close();
        errorMessage = "";
        if (negative)										// Return the proper value based
            measuredValue.setSign(false);					// on the state of the flag that
    }

    private void getErrorTermFromString(String s) {
        errorTerm = new UNumber(0);
        if (s.length() == 0) {								// If there is nothing there,
            errorMessage = "***Error*** Input is empty";		// signal an error
            return;
        }
        // If the first character is a plus sign, ignore it.
        int start = 0;										// Start at character position zero
        boolean negative = false;							// Assume the value is not negative
        if (s.charAt(start) == '+')							// See if the first character is '+'
            start++;										// If so, skip it and ignore it

            // If the first character is a minus sign, skip over it, but remember it
        else if (s.charAt(start) == '-'){					// See if the first character is '-'
            start++;											// if so, skip it
            negative = true;									// but do not ignore it
        }

        // See if the user-entered string can be converted into an integer value
        Scanner tempScanner = new Scanner(s.substring(start));// Create scanner for the digits
        if (!tempScanner.hasNextDouble()) {					// See if the next token is a valid
            errorMessage = "***Error*** Invalid value"; 		// integer value.  If not, signal there
            tempScanner.close();								// return a zero
            return;
        }

        // Convert the user-entered string to a integer value and see if something else is following it
        errorTerm = new UNumber(tempScanner.nextDouble());				// Convert the value and check to see
        if (tempScanner.hasNext()) {							// that there is nothing else is
            errorMessage = "***Error*** Excess data"; 		// following the value.  If so, it
            tempScanner.close();								// is an error.  Therefore we must
            errorTerm = new UNumber(0);								// return a zero value.
            return;
        }
        tempScanner.close();
        errorMessage = "";
        if (negative)										// Return the proper value based
            errorTerm.setSign(false);					// on the state of the flag that
    }

	/*
	Getters and Setters
	*/
	
	/*****
	 * This is the start of the getters and setters
	 * 
	 * Get the error message
	 */
    String getErrorMessage(){
		return errorMessage;
	}

    private Unit getMyUnit() {
        return myUnit;
    }

    /*****
	 * Set the current value of a calculator value to a specific long integer
	 */
//	public void setValue(long v){
//		measuredValue = v;
//	}

    public void setValue(double v) {
        measuredValue = new UNumber(v);
    }

    /*****
	 * Set the current value of a calculator error message to a specific string
	 */
	public void setErrorMessage(String m){
		errorMessage = m;
	}
	
	/*****
	 * Set the current value of a calculator value to the value of another (copy)
	 */
	public void setValue(CalculatorValue v){
		measuredValue = v.measuredValue;
		errorMessage = v.errorMessage;
	}
	
	/*
	The toString() Method
	*/
	
	/*****
	 * This is the default toString method
	 * 
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	public String toString() {
		return measuredValue + " " + errorTerm + " " + myUnit;
	}
	
	/*****
	 * This is the debug toString method
	 * 
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	public String debugToString() {
		return "measuredValue = " + measuredValue + "\nerrorMessage = " + errorMessage + "\n";
	}

	
	/*
	The computation methods
	*/
	

	/*******************************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume that the
	 * caller has verified that things are okay for the operation to take place.  These methods understand
	 * the technical details of the values and their reputations, hiding those details from the business 
	 * logic and user interface modules.
	 * 
	 * Since this is addition and we do not yet support units, we don't recognize any errors.
	 */
    void add(CalculatorValue v) {
		if(!myUnit.equals(v.getMyUnit())) {
			errorMessage = "***Error***: Units don't match!";
	        return;
        }

		measuredValue.add(v.measuredValue);
		errorTerm.add(v.errorTerm);
		errorMessage = "";
	}

	/*****
	 * The following methods have not been implemented.  The code here is just a stub to allow the code to
	 * properly compile and run.
	 * 
	 * @param v Other CalculatorValue
	 */
    void sub(CalculatorValue v) {
        if(!myUnit.equals(v.getMyUnit())) {
            errorMessage = "***Error***: Units don't match!";
            return;
        }

	    measuredValue.sub(v.measuredValue);
        errorTerm.add(v.errorTerm);
		errorMessage = "";
	}

	void mpy(CalculatorValue v) {
	    measuredValue.mpy(v.measuredValue);
	    myUnit.mpy(v.getMyUnit());

	    // Calculate the error term product.
	    UNumber v1mv = new UNumber(this.measuredValue),
                v1et = new UNumber(this.errorTerm),
                v2mv = new UNumber(v.measuredValue),
                v2et = new UNumber(v.errorTerm);

        v1mv.abs();

        // v1et now contains the v1 error fraction...
	    v1et.div(v1mv);

        v2mv.abs();

        // ... and so does v2et
        v2et.div(v1mv);

        // add them both up
        v1et.add(v2et);

        // and multiply the result by the absolute value of the product.
        UNumber absProd = new UNumber(measuredValue);
        absProd.abs();
        v1et.mpy(absProd);

        // Now put the error term back.
        this.errorTerm = v1et;

		errorMessage = "";
	}

	void div(CalculatorValue v) {
	    if(v.measuredValue.isZero()) {
	        errorMessage = "***Error***: Division by zero!";
	        return;
        }

        measuredValue.div(v.measuredValue);
	    myUnit.div(v.getMyUnit());

        // Calculate the error term product.
        UNumber v1mv = new UNumber(this.measuredValue),
                v1et = new UNumber(this.errorTerm),
                v2mv = new UNumber(v.measuredValue),
                v2et = new UNumber(v.errorTerm);

        // Absolute value of the first value
        v1mv.abs();

        // v1et now contains the v1 error fraction...
        v1et.div(v1mv);

        // Absolute value of the second value
        v2mv.abs();

        // ... and so does v2et
        v2et.div(v1mv);

        // add them both up
        v1et.add(v2et);

        // and multiply the result by the absolute value of the product.
        UNumber absQuot = new UNumber(measuredValue);
        absQuot.abs();
        v1et.mpy(absQuot);

        // Now put the error term back.
        this.errorTerm = v1et;

		errorMessage = "";
	}

	void sqrt() {
	    if (measuredValue.isNegative()) {
	        errorMessage = "***Error***: Square root of negative number!";
	        return;
        }

        // For now, do nothing.
//        measuredValue =  UNumber(0);
		measuredValue.sqrt();
	    myUnit.sqrt();

        UNumber v1mv = new UNumber(this.measuredValue),
                v1et = new UNumber(this.errorTerm);

        // Absolute value of the first value
        v1mv.abs();

        // v1et now contains the v1 error fraction...
        v1et.div(v1mv);

        // Multiply the error fraction with the absolute value of the square root.
        UNumber absSqrt = new UNumber(measuredValue);
        absSqrt.abs();
        v1et.mpy(absSqrt);

        // And now divide the whole thing by 2.
        UNumber two = new UNumber(2);
        v1et.div(two);

        this.errorTerm = v1et;

	    errorMessage = "";
    }
}
