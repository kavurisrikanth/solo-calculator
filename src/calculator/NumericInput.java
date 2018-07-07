/*
 * Implements a Finite State Machine to recognize numeric input.
 */

package calculator;

class NumericInput {

    private String input, errorMsg;
    private int currentState, currentIndex;
    private boolean inFinalState;
    private char invalidChar;

    /*****
     * Constructor
     */
    NumericInput() {
        input = "";
        currentState = 0;
        currentIndex = 0;
        inFinalState = true;
    }

    void extendInput(String newInput) {
        input = newInput;

        if(!validateInput()) {
            errorMsg = "Invalid character: " + invalidChar + ". " + errorMsg;
        }
    }

    /*****
     * Extends the current input by one character.
     * @param someChar: Some character.
     */
    private void extendInput(char someChar) {
        // Extend input and send for validation
        String backup = input;
        input += someChar;

        if(input.equals(backup))
            return;

        if(!validateInput()) {
            invalidChar = someChar;
            errorMsg = "Invalid character: " + invalidChar + ". " + errorMsg;
            input = backup;
        }
    }

    /*****
     * Validates the input string. This method runs the Finite State Machine
     * and decides whether the current input is valid or not.
     */
    private boolean validateInput() {
        char currentChar = input.charAt(currentIndex);
        int nextState = -1;

        switch (currentState) {
            case 0:
                /*
                 * From state 0, there are 3 possible inputs.
                 * A digit, which takes us to state 1.
                 * A decimal, which takes us to state 2.
                 * Anything else, which keeps us at state 0.
                 */
                if(currentChar >= '0' && currentChar <= '9') {
                    nextState = 1;
                    inFinalState = true;
                } else {
                    if(currentChar == '.') {
                        nextState = 2;
                        inFinalState = false;
                    } else {
                        errorMsg = "You're only allowed to enter a digit or a decimal point.";
                        return false;
                    }
                }
                break;

            case 1:
                if(currentChar >= '0' && currentChar <= '9') {
                    nextState = 1;
                    inFinalState = true;
                } else {
                    if(currentChar == '.') {
                        nextState = 2;
                        inFinalState = false;
                    } else if(currentChar == 'e') {
                        nextState = 4;
                        inFinalState = false;
                    } else {
                        errorMsg = "You're only allowed to enter a digit or a decimal point or the letter 'e'.";
                        return false;
                    }
                }
                break;

            case 2:
                if(currentChar >= '0' && currentChar <= '9') {
                    nextState = 3;
                    inFinalState = true;
                } else {
                    errorMsg = "You're only allowed to enter a digit.";
                    return false;
                }
                break;

            case 3:
                if(currentChar >= '0' && currentChar <= '9') {
                    nextState = 3;
                    inFinalState = true;
                } else {
                    if(currentChar == 'e') {
                        nextState = 4;
                        inFinalState = false;
                    } else {
                        errorMsg = "You're only allowed to enter a digit or the letter 'e'.";
                        return false;
                    }
                }
                break;

            case 4:
                if(currentChar >= '0' && currentChar <= '9') {
                    nextState = 5;
                    inFinalState = true;
                } else {
                    if(currentChar == '+' || currentChar == '-') {
                        nextState = 6;
                        inFinalState = false;
                    } else {
                        errorMsg = "You're only allowed to enter a digit or the characters '+' or '-'.";
                        return false;
                    }
                }
                break;

            case 5:
            case 6:
                if(currentChar >= '0' && currentChar <= '9') {
                    nextState = 5;
                    inFinalState = true;
                } else {
                    errorMsg = "You're only allowed to enter a digit.";
                    return false;
                }
                break;
        }

        currentState = nextState;
        return true;
    }

    public int toInt() {
        return Integer.parseInt(input);
    }

    public String getInput() {
        if (inFinalState)
            return input;
        return null;
    }
}
