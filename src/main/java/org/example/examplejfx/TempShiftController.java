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
    @FXML
    private Label lbValueToConvertDescription;

    @FXML
    private TextField txtName;


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
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(-459.67, 10000, 0);
        spnValue.setValueFactory(valueFactory);
    }


    //Metodos anotados con @FXML, pueden ser invocados cuando ocurre un evento(onClick, onDrag, etc) en un Control(Button, Label, radioButton, etc)
    @FXML
    private void onConvertButton(){
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

        answer = makeConversion();

        lbAnswer.setText("El resultado es " + answer + " °" + answerSymbol);
    }

    @FXML
    private void onCelsiusToFahrenheitRD() {
        answerSymbol = 'F';//Cambiar simbolo que usaremos en la respuesta
        lbValueToConvertDescription.setText("Ingresa el valor a convertir en °C: ");
    }
    @FXML
    private void onFahrenheitToCelsiusRD() {
        answerSymbol = 'C';//Cambiar simbolo que usaremos en la respuesta
        lbValueToConvertDescription.setText("Ingresa el valor a convertir en °F: ");
    }

    @FXML
    private void onShowAndCleanButton() {

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(null);
        errorAlert.setHeaderText("Error!");

        if(lbAnswer.getText().equals("")) {//Si hay alguna respuesta
            errorAlert.setContentText("Debe hacer una conversion primero");
            errorAlert.showAndWait();
            return;
        }

        if(txtName.getText().equals("")) {//Si hay algun usuario
            errorAlert.setContentText("Debe escribir su nombre de usuario");
            errorAlert.showAndWait();
            return;
        }


        StringBuilder strBuilder = new StringBuilder(); //Nos ayuda a hacer un String en varios pasos

        strBuilder.append("Nombre de usuario: ");
        strBuilder.append(txtName.getText());
        strBuilder.append("\nTipo de conversión: ");
        strBuilder.append( rdFahrenheitToCelsius.isSelected() ? "de °F a °C" : "de °C a °F");
        strBuilder.append("\nValor obtenido: ");
        strBuilder.append(makeConversion());
        strBuilder.append(" °"+answerSymbol);
        strBuilder.append("\nValor original: ");
        strBuilder.append(spnValue.getValue());
        strBuilder.append( rdCelsiusToFahrenheit.isSelected() ? " °C" : " °F");


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText("Informacion:");
        alert.setContentText(strBuilder.toString());
        alert.showAndWait();
        clean();
    }


    private double makeConversion(){
        double value = spnValue.getValue().doubleValue();//Podemos acceder a la informacion de los controls, desde cualquier parte de la aplicación

        if(rdCelsiusToFahrenheit.isSelected())
            return value * (9.0 / 5) + 32;
        else
            return  (value - 32) * (5.0 / 9);
    }

    private void clean() {
        txtName.clear();
        toggleGroup.getSelectedToggle().setSelected(false);//Ponemos falso el elemento seleccionado
        spnValue.getValueFactory().setValue(0.0);
        lbAnswer.setText("");
        lbValueToConvertDescription.setText("Ingresa el valor a convertir: ");
        answerSymbol = '\0';//Este no es del fmxl, pero lo limpiamos igual
    }
}