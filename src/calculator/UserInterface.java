
package calculator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.StringConverter;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with String
 * objects and passes work to other classes to deal with all other aspects of the computation.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @author Lynn Robert Carter & Srikanth Kavuri
 * 
 * @version 4.00	2017-10-17 The JavaFX-based GUI for the implementation of a calculator
 * 
 */

public class UserInterface {

    private Label label_Operand1 = new Label("First operand");
	private TextField text_Operand1 = new TextField();
	private Label label_Operand2 = new Label("Second operand");
	private TextField text_Operand2 = new TextField();
	private Label label_Result = new Label("Result");
	private TextField text_Result = new TextField();

	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("×");				// The multiply symbol: \u00D7
	private Button button_Div = new Button("÷");				// The divide symbol: \u00F7
	private Button button_Sqrt = new Button("\u221a");
	private Button button_Clr = new Button("CLR");
	private Button button_MoveToFirst = new Button("Move to First");

    private Label label_errOperand1 = new Label("");
	private Label label_errOperand2 = new Label("");
	private Label label_errResult = new Label("");
	// If the multiplication and/or division symbols do not display properly, replace the 
	// quoted strings used in the new Button constructor call with the <backslash>u00xx values
	// shown on the same line. This is the Unicode representation of those characters and will
	// work regardless of the underlying hardware being used.
	
	private double buttonSpace;		// This is the white space between the operator buttons.

	private double textFieldWidth = Calculator.WINDOW_WIDTH * 40.0 / 100;
	private ObservableList<Integer> numbers =
            FXCollections.observableArrayList(IntStream.rangeClosed(-16, 16).boxed().collect(Collectors.toList()));

    private ComboBox<Integer> operand1_MassComboBox = new ComboBox<>(numbers),
            operand1_TimeComboBox = new ComboBox<>(numbers),
            operand1_LengthComboBox = new ComboBox<>(numbers),
            operand2_MassComboBox = new ComboBox<>(numbers),
            operand2_TimeComboBox = new ComboBox<>(numbers),
            operand2_LengthComboBox = new ComboBox<>(numbers);

 	private Label
            operand1_MassLabel      = new Label("M"),
            operand1_LengthLabel    = new Label("L"),
            operand1_TimeLabel      = new Label("T"),
            operand1_ErrorTermLabel = new Label("Error Term"),
            operand1_UnitsLabel     = new Label("Units"),
            operand2_MassLabel      = new Label("M"),
            operand2_LengthLabel    = new Label("L"),
            operand2_TimeLabel      = new Label("T"),
            operand2_ErrorTermLabel = new Label("Error Term"),
            operand2_UnitsLabel     = new Label("Units"),
            result_MassLabel        = new Label("M"),
            result_LengthLabel      = new Label("L"),
            result_TimeLabel        = new Label("T"),
            result_ErrorTermLabel = new Label("Error Term"),
            result_UnitsLabel       = new Label("Units");

 	private TextField
            result_MassValueText   = new TextField("0"),
            result_LengthValueText = new TextField("0"),
            result_TimeValueText   = new TextField("0");

 	private TextField
            operand1_errorTerm = new TextField(),
            operand2_errorTerm = new TextField(),
            result_ErrorTerm = new TextField();

 	// Doubles used for setting up the UI. The co-ordinates and widths.
 	private double
            labelBuffer             = 18,
            theTopY                 = 10,
            checkBoxesY             = theTopY + 50,
            op1TopY                 = checkBoxesY + 50,
            op2TopY                 = op1TopY + 95,
            op1EditablesY           = op1TopY + 30,
            op2EditablesY           = op2TopY + 30,
            resultTopY              = op2TopY + 95,
            resultDisplayY          = resultTopY + 30,
            buttonsY                = resultTopY + 100,
            theLeftX                = 18,
            errorTermTextFieldWidth = Calculator.WINDOW_WIDTH * 0.2,
            partOneEndX             = textFieldWidth + theLeftX,
            partTwoStartX           = partOneEndX,
            unitsStartX             = partTwoStartX + 2 * labelBuffer + errorTermTextFieldWidth;

 	// Labels for error terms (the plus/minus signs)
    private Label label_plusMinus_One   = new Label("\u00B1"),
                  label_plusMinus_Two   = new Label("\u00B1"),
                  label_plusMinus_Three = new Label("\u00B1");

    // Variable to hold the font. Makes it easier to change later.
    private String labelFont = "Tahoma";

    /* This is the link to the business logic */
    private BusinessLogic perform = new BusinessLogic();

	
	/*
	Constructors
	*/


	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	public UserInterface(Pane theRoot) {

	    // Setup basic UI stuff not related to any of the operands or results.
		// There are gaps. Compute the button space accordingly.
		buttonSpace = Calculator.WINDOW_WIDTH / 7;

        // Label theScene with the name of the calculator, centered at the top of the pane
        // These are the application values required by the user interface
        Label label_IntegerCalculator = new Label("Astronomical Calculator");
        setupLabelUI(label_IntegerCalculator, labelFont, 24, Calculator.WINDOW_WIDTH, Pos.CENTER, 0, theTopY);

        // Setup operand 1 stuff
        setupOperands(theRoot);

        // Setup results stuff
        setupResults(theRoot);

        setupErrorTerms(theRoot);
        setupUnits(theRoot);

        // Set up string converters for combo boxes.
        setupComboBoxes();

        // Finally, setup buttons.
        setupButtons(theRoot);
		
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_IntegerCalculator);

	}

    /**
     * This method sets up combo boxes for the units.
     */
	private void setupComboBoxes() {
        operand1_MassComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Integer object) {
                if (object == null)
                    return null;
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        });

        operand1_LengthComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Integer object) {
                if (object == null)
                    return null;
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        });

        operand1_TimeComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Integer object) {
                if (object == null)
                    return null;
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        });

        operand2_MassComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Integer object) {
                if (object == null)
                    return null;
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        });

        operand2_LengthComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Integer object) {
                if (object == null)
                    return null;
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        });

        operand2_TimeComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Integer object) {
                if (object == null)
                    return null;
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        });
    }

    /**
     * This method sets up labels and text boxes for operand 1.
     * @param theRoot - The root of the stage. All added elements are children of the root.
     */
	private void setupOperands(Pane theRoot) {
        /*
            First, setup operand 1.
         */

        // Label the first operand just above it, left aligned
        setupLabelUI(label_Operand1, labelFont, 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, theLeftX, op1TopY);

        // Establish the first text input operand field and when anything changes in operand 1,
        // process both fields to ensure that we are ready to perform as soon as possible.
        setupTextUI(text_Operand1, labelFont, 18, textFieldWidth, Pos.BASELINE_LEFT, theLeftX, op1EditablesY, true);
        text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {
            setOperand1();
            enableButtonsIfDisabled();
        });

        // Move focus to the second operand when the user presses the enter (return) key
        text_Operand1.setOnAction((event) -> operand1_errorTerm.requestFocus());

        double op1ErrorsY = op1EditablesY + 35;

        // Establish an error message for the first operand just above it with, left aligned
        setupLabelUI(label_errOperand1, labelFont, 16, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, op1ErrorsY);
        label_errOperand1.setTextFill(Color.RED);


        /*
            Operand 1 done. Now setup operand 2.
         */

        // Label the second operand just above it, left aligned
        setupLabelUI(label_Operand2, labelFont, 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, theLeftX, op2TopY);

        // Establish the second text input operand field and when anything changes in operand 2,
        // process both fields to ensure that we are ready to perform as soon as possible.
        setupTextUI(text_Operand2, labelFont, 18, textFieldWidth, Pos.BASELINE_LEFT, theLeftX, op2EditablesY, true);
        text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {
            setOperand2();
            enableButtonsIfDisabled();
        });
        // Move the focus to the result when the user presses the enter (return) key
        text_Operand2.setOnAction((event) -> operand2_errorTerm.requestFocus());

        double op2ErrorsY = op2EditablesY + 35;

        // Establish an error message for the second operand just above it with, left aligned
        setupLabelUI(label_errOperand2, labelFont, 16, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, op2ErrorsY);
        label_errOperand2.setTextFill(Color.RED);

        theRoot.getChildren().addAll(label_Operand1, text_Operand1, label_errOperand1,
                                     label_Operand2, text_Operand2, label_errOperand2);
    }

    private void setupResults(Pane theRoot) {
	    /*
	        Just like operands 1 and 2, setup results. The only difference is that the TextBoxes
	        are not editable.
	     */

        // Label the result just above the result output field, left aligned
        setupLabelUI(label_Result, labelFont, 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, theLeftX, resultTopY);

        // Establish the result output field.  It is not editable, so the text can be selected and copied,
        // but it cannot be altered by the user.  The text is left aligned.
        setupTextUI(text_Result, labelFont, 18, textFieldWidth, Pos.BASELINE_LEFT, theLeftX, resultDisplayY, false);

        double resultErrorsY = resultDisplayY + 30;

        // Establish an error message for the second operand just above it with, left aligned
        setupLabelUI(label_errResult, labelFont, 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, resultErrorsY);
        label_errResult.setTextFill(Color.RED);

        theRoot.getChildren().addAll(label_Result, text_Result, label_errResult);
    }

    private void setupErrorTerms(Pane theRoot) {
	    /*
        The error terms for operand 1
         */

        // Setup Label
        setupLabelUI(operand1_ErrorTermLabel, labelFont, 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, partTwoStartX + labelBuffer, op1TopY);

        setupLabelUI(label_plusMinus_One, labelFont, 26, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, partTwoStartX, op1EditablesY);
        setupTextUI(operand1_errorTerm, labelFont, 18, errorTermTextFieldWidth, Pos.BASELINE_LEFT, partTwoStartX + labelBuffer, op1EditablesY, true);

        operand1_errorTerm.textProperty().addListener((observable, oldValue, newValue) -> {
            setOperand1();
            enableButtonsIfDisabled();
            System.out.println("Setting up first operand's error term!");
        });

        System.out.println(text_Operand1.getOnMouseClicked());
        System.out.println(operand1_errorTerm.getOnMouseClicked());

        /*
        The error terms for operand 2
         */

        // Setup Label
        setupLabelUI(operand2_ErrorTermLabel, labelFont, 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, partTwoStartX + labelBuffer, op2TopY);

        setupLabelUI(label_plusMinus_Two, labelFont, 26, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, partTwoStartX, op2EditablesY);
        setupTextUI(operand2_errorTerm, labelFont, 18, errorTermTextFieldWidth, Pos.BASELINE_LEFT, partTwoStartX + labelBuffer, op2EditablesY, true);

        operand2_errorTerm.textProperty().addListener((observable, oldValue, newValue) -> {
            setOperand2();
            enableButtonsIfDisabled();
        });

        /*
        Error terms for the result
         */
        setupLabelUI(result_ErrorTermLabel, labelFont, 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, partTwoStartX + labelBuffer, resultTopY);

        setupLabelUI(label_plusMinus_Three, labelFont, 26, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, partTwoStartX, resultDisplayY);
        setupTextUI(result_ErrorTerm, labelFont, 18, errorTermTextFieldWidth, Pos.BASELINE_LEFT, partTwoStartX + labelBuffer, resultDisplayY, false);

        /*
        * Add all the elements to the root.
        */
        theRoot.getChildren().addAll(
                operand1_errorTerm, operand1_ErrorTermLabel, label_plusMinus_One,
                operand2_errorTerm, operand2_ErrorTermLabel, label_plusMinus_Two,
                result_ErrorTerm, result_ErrorTermLabel, label_plusMinus_Three
        );
    }

    private void setupUnits(Pane theRoot) {
	    /*
         Units for operand 1
          */

        // Set up the label for the first operand's units.
        setupLabelUI(operand1_UnitsLabel, labelFont, 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, unitsStartX, op1TopY);

        // Setup operand 1 combo boxes and labels
        setupLabelUI(operand1_MassLabel, labelFont, 16, textFieldWidth/3, Pos.BASELINE_LEFT, unitsStartX, op1EditablesY);
        double comboBoxWidth = 70;
        setupComboBoxUI(operand1_MassComboBox, comboBoxWidth, unitsStartX + labelBuffer, op1EditablesY, true);

        setupLabelUI(operand1_LengthLabel, labelFont, 16, textFieldWidth/3, Pos.BASELINE_LEFT, unitsStartX + 2 * labelBuffer + comboBoxWidth, op1EditablesY);
        setupComboBoxUI(operand1_LengthComboBox, comboBoxWidth, unitsStartX + 3 * labelBuffer + comboBoxWidth, op1EditablesY, true);

        setupLabelUI(operand1_TimeLabel, labelFont, 16, textFieldWidth/3, Pos.BASELINE_LEFT, unitsStartX + 4 * labelBuffer + 2 * comboBoxWidth, op1EditablesY);
        setupComboBoxUI(operand1_TimeComboBox, comboBoxWidth, unitsStartX + 5 * labelBuffer + 2 * comboBoxWidth, op1EditablesY, true);

        operand1_MassComboBox.getSelectionModel().select(16);
        operand1_LengthComboBox.getSelectionModel().select(16);
        operand1_TimeComboBox.getSelectionModel().select(16);

        operand1_MassComboBox.valueProperty().addListener((obs, oldVal, newVal) -> setOperand1());
        operand1_LengthComboBox.valueProperty().addListener((obs, oldVal, newVal) -> setOperand1());
        operand1_TimeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> setOperand1());

	    /*
         Units for operand 2
          */

        // Set up the label for the second operand's units.
        setupLabelUI(operand2_UnitsLabel, labelFont, 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, unitsStartX, op2TopY);

        // Setup operand 2 combo boxes and labels
        setupLabelUI(operand2_MassLabel, labelFont, 16, textFieldWidth/3, Pos.BASELINE_LEFT, unitsStartX, op2EditablesY);
        setupComboBoxUI(operand2_MassComboBox, comboBoxWidth, unitsStartX + labelBuffer, op2EditablesY, true);

        setupLabelUI(operand2_LengthLabel, labelFont, 16, textFieldWidth/3, Pos.BASELINE_LEFT, unitsStartX + 2 * labelBuffer + comboBoxWidth, op2EditablesY);
        setupComboBoxUI(operand2_LengthComboBox, comboBoxWidth, unitsStartX + 3 * labelBuffer + comboBoxWidth, op2EditablesY, true);

        setupLabelUI(operand2_TimeLabel, labelFont, 16, textFieldWidth/3, Pos.BASELINE_LEFT, unitsStartX + 4 * labelBuffer + 2 * comboBoxWidth, op2EditablesY);
        setupComboBoxUI(operand2_TimeComboBox, comboBoxWidth, unitsStartX + 5 * labelBuffer + 2 * comboBoxWidth, op2EditablesY, true);

        operand2_MassComboBox.getSelectionModel().select(16);
        operand2_LengthComboBox.getSelectionModel().select(16);
        operand2_TimeComboBox.getSelectionModel().select(16);

        operand2_MassComboBox.valueProperty().addListener((obs, oldVal, newVal) -> setOperand2());
        operand2_LengthComboBox.valueProperty().addListener((obs, oldVal, newVal) -> setOperand2());
        operand2_TimeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> setOperand2());

	    /*
        Units for result
         */
        // Set up the label for the result's units.
        setupLabelUI(result_UnitsLabel, labelFont, 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, unitsStartX, resultTopY);

        // Setup operand 2 combo boxes and labels
        setupLabelUI(result_MassLabel, labelFont, 16, textFieldWidth/3, Pos.BASELINE_LEFT, unitsStartX, resultDisplayY);
        setupTextUI(result_MassValueText, labelFont, 18, comboBoxWidth, Pos.BASELINE_LEFT, unitsStartX + labelBuffer, resultDisplayY, false);

        setupLabelUI(result_LengthLabel, labelFont, 16, textFieldWidth/3, Pos.BASELINE_LEFT, unitsStartX + 2 * labelBuffer + comboBoxWidth, resultDisplayY);
        setupTextUI(result_LengthValueText, labelFont, 18, comboBoxWidth, Pos.BASELINE_LEFT, unitsStartX + 3 * labelBuffer + comboBoxWidth, resultDisplayY, false);

        setupLabelUI(result_TimeLabel, labelFont, 16, textFieldWidth/3, Pos.BASELINE_LEFT, unitsStartX + 4 * labelBuffer + 2 * comboBoxWidth, resultDisplayY);
        setupTextUI(result_TimeValueText, labelFont, 18, comboBoxWidth, Pos.BASELINE_LEFT, unitsStartX + 5 * labelBuffer + 2 * comboBoxWidth, resultDisplayY, false);

        theRoot.getChildren().addAll(
                operand1_MassComboBox, operand1_LengthComboBox, operand1_TimeComboBox,
                operand2_MassComboBox, operand2_LengthComboBox, operand2_TimeComboBox,
                result_MassValueText, result_LengthValueText, result_TimeValueText,
                operand1_MassLabel, operand1_LengthLabel, operand1_TimeLabel, operand1_UnitsLabel,
                operand2_MassLabel, operand2_LengthLabel, operand2_TimeLabel, operand2_UnitsLabel,
                result_MassLabel, result_LengthLabel, result_TimeLabel, result_UnitsLabel
        );
    }

    private void setupButtons(Pane theRoot) {
        /*
         Attributes
        */

        double BUTTON_WIDTH = 60;
        double BUTTON_OFFSET = BUTTON_WIDTH / 2;

        // Establish the ADD "+" button, position it, and link it to methods to accomplish its work
        setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1 * buttonSpace- BUTTON_OFFSET, buttonsY);
        button_Add.setOnAction((event) -> addOperands());
        button_Add.setDisable(true);    // Start off with button disabled.

        // Establish the SUB "-" button, position it, and link it to methods to accomplish its work
        setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * buttonSpace- BUTTON_OFFSET, buttonsY);
        button_Sub.setOnAction((event) -> subOperands());
        button_Sub.setDisable(true);    // Start off with button disabled.

        // Establish the MPY "x" button, position it, and link it to methods to accomplish its work
        setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * buttonSpace- BUTTON_OFFSET, buttonsY);
        button_Mpy.setOnAction((event) -> mpyOperands());
        button_Mpy.setDisable(true);    // Start off with button disabled.

        // Establish the DIV "/" button, position it, and link it to methods to accomplish its work
        setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * buttonSpace- BUTTON_OFFSET, buttonsY);
        button_Div.setOnAction((event) -> divOperands());
        button_Div.setDisable(true);    // Start off with button disabled.

        // Establish the SQRT button, position it, and link it to methods to accomplish its work
        setupButtonUI(button_Sqrt, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 5 * buttonSpace- BUTTON_OFFSET, buttonsY);
        button_Sqrt.setOnAction((event) -> sqrtOperands());
        button_Sqrt.setDisable(true);    // Start off with button disabled.

        // Establish the CLR button, position it, and link it to methods to accomplish its work
        setupButtonUI(button_Clr, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 6 * buttonSpace- BUTTON_OFFSET, buttonsY);
        button_Clr.setOnAction((event) -> clearAll());
        button_Clr.setDisable(true);    // Start off with button disabled.

        // Establish the Move to First button, position it, and link it to methods to accomplish its work
        setupButtonUI(button_MoveToFirst, "Symbol", 14, BUTTON_WIDTH, Pos.BASELINE_LEFT, 160, resultTopY - 5);
        button_MoveToFirst.setOnAction((event) -> moveSecondToFirst());
        button_MoveToFirst.setDisable(true);    // Start off with button disabled.

        theRoot.getChildren().addAll(
                button_Add, button_Sub, button_Mpy, button_Div,
                button_Sqrt, button_Clr, button_MoveToFirst
        );
    }

    /**********
     * Private local method to enable the five(?) buttons if they are disabled.
     */
	private void enableButtonsIfDisabled() {
        String operand1Text = text_Operand1.getText(),
                operand2Text = text_Operand2.getText(),
                operand1ErrorTermText = operand1_errorTerm.getText(),
                operand2ErrorTermText = operand2_errorTerm.getText(),
                resultText = text_Result.getText(),
                resultErrorTermText = result_ErrorTerm.getText();

        /*
         * A bit of a special case. If there is nothing in the second operand text bar,
         * then enabling this button makes no sense.
         */
        if (!resultText.isEmpty() && ! resultErrorTermText.isEmpty()) {
            if (button_MoveToFirst.isDisabled())
                button_MoveToFirst.setDisable(false);
        } else {
            if (!button_MoveToFirst.isDisabled())
                button_MoveToFirst.setDisable(true);
        }

        if (!operand1Text.isEmpty() || !operand1ErrorTermText.isEmpty() || !operand2Text.isEmpty() || !operand2ErrorTermText.isEmpty()) {
            /*
             * If any of the text boxes has something in it, then enable all disabled buttons.
             */
            if (button_Add.isDisabled())
                button_Add.setDisable(false);

            if (button_Sub.isDisabled())
                button_Sub.setDisable(false);

            if (button_Mpy.isDisabled())
                button_Mpy.setDisable(false);

            if (button_Div.isDisabled())
                button_Div.setDisable(false);

            if (button_Sqrt.isDisabled())
                button_Sqrt.setDisable(false);

            if(button_Clr.isDisabled())
                button_Clr.setDisable(false);
        } else {
            /*
             * Otherwise, disable all enabled buttons.
             */
            if (!button_Add.isDisabled())
                button_Add.setDisable(true);

            if (!button_Sub.isDisabled())
                button_Sub.setDisable(true);

            if (!button_Mpy.isDisabled())
                button_Mpy.setDisable(true);

            if (!button_Div.isDisabled())
                button_Div.setDisable(true);

            if (!button_Sqrt.isDisabled())
                button_Sqrt.setDisable(true);

            if(!button_Clr.isDisabled())
                button_Clr.setDisable(true);
        }
    }

    /**********
     * Private local method to clear all text fields.
     */
    private void clearAll() {
        text_Operand1.setText("");
        text_Operand2.setText("");
        text_Result.setText("");
        label_errOperand1.setText("");
        label_errOperand2.setText("");
        label_errResult.setText("");

        operand1_errorTerm.setText("0");
        operand2_errorTerm.setText("0");
        result_ErrorTerm.setText("0");

        operand1_MassComboBox.getSelectionModel().select(16);
        operand1_LengthComboBox.getSelectionModel().select(16);
        operand1_TimeComboBox.getSelectionModel().select(16);

        operand2_MassComboBox.getSelectionModel().select(16);
        operand2_LengthComboBox.getSelectionModel().select(16);
        operand2_TimeComboBox.getSelectionModel().select(16);

        result_MassValueText.setText("0");
        result_LengthValueText.setText("0");
        result_TimeValueText.setText("0");

        text_Operand1.requestFocus();
    }

    /**********
     * Private local method to move contents of second text box to first.
     */
    private void moveSecondToFirst() {
        text_Operand1.setText(text_Operand2.getText());
        operand1_MassComboBox.getSelectionModel().select(operand2_MassComboBox.getSelectionModel().getSelectedIndex());
        operand1_LengthComboBox.getSelectionModel().select(operand2_LengthComboBox.getSelectionModel().getSelectedIndex());
        operand1_TimeComboBox.getSelectionModel().select(operand2_TimeComboBox.getSelectionModel().getSelectedIndex());

        text_Operand2.setText("");
        operand2_MassComboBox.getSelectionModel().select(16);
        operand2_LengthComboBox.getSelectionModel().select(16);
        operand2_TimeComboBox.getSelectionModel().select(16);

        result_MassValueText.setText("0");
        result_LengthValueText.setText("0");
        result_TimeValueText.setText("0");
    }

	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}

    /**********
     * Private local method to initialize the UI for a ComboBox.
     */
	private void setupComboBoxUI(ComboBox cb, double w, double x, double y, boolean e) {
        cb.setMinWidth(w);
        cb.setMaxWidth(w);
        cb.setLayoutX(x);
        cb.setLayoutY(y);
        cb.setEditable(e);
    }

    private void setupCheckBoxUI(CheckBox cb, String ff, double f, double w, double x, double y) {
	    cb.setFont(Font.font(ff, f));
        cb.setMinWidth(w);
        cb.setMaxWidth(w);
        cb.setLayoutX(x);
        cb.setLayoutY(y);
    }
	
	/*
	User Interface Actions
	*/

	/**********
	 * Private local method to set the value of the first operand given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if 
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */
	private void setOperand1() {
		text_Result.setText("");								// Any change of an operand probably invalidates
		label_Result.setText("Result");						// the result, so we clear the old result.
		label_errResult.setText("");
		if (perform.setOperand1(
		        text_Operand1.getText(),
                operand1_errorTerm.getText(),
                operand1_MassComboBox.getSelectionModel().getSelectedItem(),
                operand1_LengthComboBox.getSelectionModel().getSelectedItem(),
                operand1_TimeComboBox.getSelectionModel().getSelectedItem())
                ) {	// Set the operand and see if there was an error
			label_errOperand1.setText("");					// If no error, clear this operands error
			if (text_Operand2.getText().length() == 0)		// If the other operand is empty, clear its error
				label_errOperand2.setText("");				// as well.
		}
		else 												// If there's a problem with the operand, display
			label_errOperand1.setText(perform.getOperand1ErrorMessage());	// the error message that was generated
	}
	
	
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is exactly the
	 * same as used for the first operand, above.
	 */
	private void setOperand2() {
		text_Result.setText("");								// See setOperand1's comments. The logic is the same!
		label_Result.setText("Result");				
		label_errResult.setText("");
		if (perform.setOperand2(
		        text_Operand2.getText(),
                operand2_errorTerm.getText(),
                operand2_MassComboBox.getSelectionModel().getSelectedItem(),
                operand2_LengthComboBox.getSelectionModel().getSelectedItem(),
                operand2_TimeComboBox.getSelectionModel().getSelectedItem())
                ) {
			label_errOperand2.setText("");
			if (text_Operand1.getText().length() == 0)
				label_errOperand1.setText("");
		}
		else
			label_errOperand2.setText(perform.getOperand1ErrorMessage());
	}
	
	
	/**********
	 * This method is called when an binary operation button has been pressed. It assesses if there are issues 
	 * with either of the binary operands or they are not defined. If not return false (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean binaryOperandIssues() {
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
		String errorMessage2 = perform.getOperand2ErrorMessage();
		if (errorMessage1.length() > 0) {						// Check the first.  If the string is not empty
			label_errOperand1.setText(errorMessage1);			// there's an error message, so display it.
			if (errorMessage2.length() > 0) {					// Check the second and display it if there is
				label_errOperand2.setText(errorMessage2);		// and error with the second as well.
				return true;										// Return true when both operands have errors
			}
			else {
				return true;										// Return true when only the first has an error
			}
		}
		else if (errorMessage2.length() > 0) {					// No error with the first, so check the second
			label_errOperand2.setText(errorMessage2);			// operand. If non-empty string, display the error
			return true;											// message and return true... the second has an error
		}														// Signal there are issues
		
		// If the code reaches here, neither the first nor the second has an error condition. The following code
		// check to see if the operands are defined.
		if (!perform.getOperand1Defined()) {						// Check to see if the first operand is defined
			label_errOperand1.setText("No value found");			// If not, this is an issue for a binary operator
			if (!perform.getOperand2Defined()) {					// Now check the second operand. It is is also
				label_errOperand2.setText("No value found");		// not defined, then two messages should be displayed
				return true;										// Signal there are issues
			}
			return true;
		} else if (!perform.getOperand2Defined()) {				// If the first is defined, check the second. Both
			label_errOperand2.setText("No value found");			// operands must be defined for a binary operator.
			return true;											// Signal there are issues
		}
		
		return false;											// Signal there are no issues with the operands
	}

    private boolean unaryOperandIssues() {
        String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
        if (errorMessage1.length() > 0) {						// Check the first.  If the string is not empty
            label_errOperand1.setText(errorMessage1);			// there's an error message, so display it.
                return true;										// Return true when only the first has an error
        }

        // If the code reaches here, neither the first nor the second has an error condition. The following code
        // check to see if the operands are defined.
        if (!perform.getOperand1Defined()) {						// Check to see if the first operand is defined
            label_errOperand1.setText("No value found");			// If not, this is an issue for a binary operator
                return true;										// Signal there are issues
        }

        return false;											// Signal there are no issues with the operands
    }

	/*
	 * This portion of the class defines the actions that take place when the various calculator
	 * buttons (add, subtract, multiply, and divide) are pressed.
	 */

	/**********
	 * This is the add routine
	 * 
	 */
	private void addOperands(){
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		
		// If the operands are defined and valid, request the business logic method to do the addition and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.addition();						// Call the business logic add method
		label_errResult.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
		    String[] pieces = theAnswer.split("\\s");
		    String result = pieces[0],
                    error = pieces[1],
                    mass = pieces[2],
                    length = pieces[3],
                    time = pieces[4];

			text_Result.setText(result);							// If okay, display it in the result field and
            result_MassValueText.setText(mass);
            result_LengthValueText.setText(length);
            result_TimeValueText.setText(time);

            result_ErrorTerm.setText(error);

			label_Result.setText("Sum");								// change the title of the field to "Sum"
		}
		else {														// Some error occurred while doing the addition.
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}
	}

	/**********
	 * This is the subtract routine
	 * 
	 */
	private void subOperands(){
	    // Validation, same as for other operations. See Addition above for detailed comments.
		if(binaryOperandIssues())
			return;

		// Perform the op and check the result, same as for other operations.
		String theAnswer = perform.subtraction();
		label_errResult.setText("");

		if(theAnswer.length() > 0) {
            String[] pieces = theAnswer.split("\\s");
            String result = pieces[0],
                    error = pieces[1],
                    mass = pieces[2],
                    length = pieces[3],
                    time = pieces[4];

            text_Result.setText(result);
            result_MassValueText.setText(mass);
            result_LengthValueText.setText(length);
            result_TimeValueText.setText(time);

            result_ErrorTerm.setText(error);

		    label_Result.setText("Difference");
        } else {
		    text_Result.setText("");
		    label_Result.setText("Result");
		    label_errResult.setText(perform.getResultErrorMessage());
        }
	}

	/**********
	 * This is the multiply routine
	 * 
	 */
	private void mpyOperands(){
        // Validation, same as for other operations. See Addition above for detailed comments.
        if(binaryOperandIssues())
            return;

        // Perform the op and check the result, same as for other operations.
        String theAnswer = perform.multiplication();
        label_errResult.setText("");

        if(theAnswer.length() > 0) {
            String[] pieces = theAnswer.split("\\s");
            String result = pieces[0],
                    error = pieces[1],
                    mass = pieces[2],
                    length = pieces[3],
                    time = pieces[4];

            text_Result.setText(result);
            result_MassValueText.setText(mass);
            result_LengthValueText.setText(length);
            result_TimeValueText.setText(time);

            result_ErrorTerm.setText(error);

            label_Result.setText("Product");
        } else {
            text_Result.setText("");
            label_Result.setText("Result");
            label_errResult.setText(perform.getResultErrorMessage());
        }
	}

	/**********
	 * This is the divide routine.  If the divisor is zero, the divisor is declared to be invalid.
	 * 
	 */
	private void divOperands(){
        // Validation, same as for other operations. See Addition above for detailed comments.
        if(binaryOperandIssues())
            return;

        // Perform the op and check the result, same as for other operations.
        String theAnswer = perform.division();
        label_errResult.setText("");

        if(theAnswer.length() > 0) {
            String[] pieces = theAnswer.split("\\s");
            String result = pieces[0],
                    error = pieces[1],
                    mass = pieces[2],
                    length = pieces[3],
                    time = pieces[4];

            text_Result.setText(result);
            result_MassValueText.setText(mass);
            result_LengthValueText.setText(length);
            result_TimeValueText.setText(time);

            result_ErrorTerm.setText(error);

            label_Result.setText("Quotient");
        } else {
            text_Result.setText("");
            label_Result.setText("Result");
            label_errResult.setText(perform.getResultErrorMessage());
        }
	}

	private void sqrtOperands() {
        // Validation, same as for other operations. See Addition above for detailed comments.
        if(unaryOperandIssues())
            return;

        // Perform the op and check the result, same as for other operations.
        String theAnswer = perform.squareRoot();
        label_errResult.setText("");

        if(theAnswer.length() > 0) {
            String[] pieces = theAnswer.split("\\s");
            String result  = pieces[0],
                    error  = pieces[1],
                    mass   = pieces[2],
                    length = pieces[3],
                    time   = pieces[4];

            text_Result.setText(result);
            result_MassValueText.setText(mass);
            result_LengthValueText.setText(length);
            result_TimeValueText.setText(time);

            result_ErrorTerm.setText(error);

            label_Result.setText("Square Root (" + text_Operand1.getText() + ")");
        } else {
            text_Result.setText("");
            label_Result.setText("Result");
            label_errResult.setText(perform.getResultErrorMessage());
        }
    }
}
