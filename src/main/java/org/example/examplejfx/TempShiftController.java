package org.example.examplejfx;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TempShiftController {
    //Atributos con anotacion @FXML, acceden a un elemento con el mismo fx:id que el nombre del atributo
    @FXML
    private Label lbAnswer;

    @FXML
    private RadioButton rdCelsiusToFahrenheit;

    @FXML
    private RadioButton rdFahrenheitToCelsius;

    @FXML
    private Spinner<Double> spnValue;


    private  ToggleGroup toggleGroup;//Este atributo no es de FXML, solo nos permitira agrupar los radioButtons

    private Character answerSymbol;

    @FXML //Este metodo se ejecuta al iniciar el controlador
    public void initialize() {
        //Agrupar a los radio buttons en un mismo grupo, para que solo uno pueda estar selecionado a la vez
        toggleGroup = new ToggleGroup();
        rdCelsiusToFahrenheit.setToggleGroup(toggleGroup);
        rdFahrenheitToCelsius.setToggleGroup(toggleGroup);

        //Asignar valueFactory al Spinner de Valor.
        //SpinnerValueFactory crea un intancia que se encarga de manejar los limites y valor por defecto del spinner
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(-273.15, 10000, 0);
        spnValue.setValueFactory(valueFactory);
    }


    //Metodos anotados con @FXML, pueden ser invocados cuando se ocurre un evento(onClick, onDrag, etc) en un Controls(Button, Label, radioButton, etc)
    @FXML
    private void onConvertButton(){
        double value = spnValue.getValue().doubleValue();
        double answer;

        if(toggleGroup.getSelectedToggle() == null) {//Verificar si hay un elemento seleccionado del grupo
            //Si no lo hay, mostrar una alerta
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error");
            alert.setHeaderText("Error de conversión");
            alert.setContentText("Debe seleccionar un modo de conversión");
            // Metodo que bloquea las demas ventanas y espera hasta que el usario interactue con el cuadro de dialogo
            alert.showAndWait();
            return;
        }

        if(rdCelsiusToFahrenheit.isSelected())
            answer = value * (9.0 / 5) + 32;
        else
            answer = (value - 32) * (5.0 / 9);

        lbAnswer.setText("El resultado es " + answer + " " + answerSymbol + "°");
    }

    @FXML
    private void onCelsiusToFahrenheitRD() {
        answerSymbol = 'F';//Cambiar simbolo que usaremos en la respuesta
    }
    @FXML
    private void onFahrenheitToCelsiusRD() {
        answerSymbol = 'C';//Cambiar simbolo que usaremos en la respuesta
    }
}