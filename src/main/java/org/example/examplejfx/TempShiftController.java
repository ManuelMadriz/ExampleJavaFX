package org.example.examplejfx;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TempShiftController {
    @FXML
    private Label lbAnswer;

    @FXML
    private RadioButton rdCelsiusToFahrenheit;

    @FXML
    private RadioButton rdFahrenheitToCelsius;

    @FXML
    private Spinner<Double> spnValue;

    //Este atributo no es de FXML, solo nos permitira agrupar los radioButtons
    private  ToggleGroup toggleGroup;

    @FXML //Este metodo se ejecuta al iniciar el controlador
    public void initialize() {
        //Agrupar a los radio buttons en un mismo grupo, para que solo uno pueda estar selecionado a la vez
        toggleGroup = new ToggleGroup();
        rdCelsiusToFahrenheit.setToggleGroup(toggleGroup);
        rdFahrenheitToCelsius.setToggleGroup(toggleGroup);

        //Asignar valueFactory al Spiner de Valor.
        //SpinnerValueFactory crea un intancia que se encarga de manejar los limites y valor por defecto del spinner
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(-273.15, 10000, 0);
        spnValue.setValueFactory(valueFactory);




    }

    @FXML
    private void onConvertButton(){
        double value = spnValue.getValue().doubleValue();
        double answer;

        if(toggleGroup.getSelectedToggle() == null) {//Verificar si hay un elemento seleccionado del grupo
            return;
        }

        if(rdCelsiusToFahrenheit.isSelected()) {
            answer = value * (9.0 / 5) + 32;
            lbAnswer.setText("El resultado es " + answer + " C°");
            return;
        }

        answer = (value - 32) * (5.0 / 9);
        lbAnswer.setText("El resultado es " + answer + " F°");
        return;
    }
}