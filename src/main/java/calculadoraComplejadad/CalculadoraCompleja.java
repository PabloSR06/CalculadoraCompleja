package calculadoraComplejadad;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraCompleja extends Application {

	private ComboBox<String> simbolo;
	private ObjectProperty<String> seleccionado = new SimpleObjectProperty<>();

	private TextField real1;
	private TextField real2;
	private TextField realTotal;

	private TextField imaginario1;
	private TextField imaginario2;
	private TextField imaginarioTotal;

	private Separator separador;

	private Label simbolo1;
	private Label simbolo2;
	private Label simboloTotal;

	private Complejo complejo1;
	private Complejo complejo2;
	private Complejo complejoResultado;

	@Override
	public void start(Stage primaryStage) throws Exception {

		simbolo = new ComboBox<String>();
		simbolo.getItems().addAll("+", "-", "*", "/");

		real1 = new TextField();
		real1.setPrefWidth(50);

		real2 = new TextField();
		real2.setPrefWidth(50);

		realTotal = new TextField();
		realTotal.setPrefWidth(50);
		realTotal.setDisable(true);

		imaginario1 = new TextField();
		imaginario1.setPrefWidth(50);

		imaginario2 = new TextField();
		imaginario2.setPrefWidth(50);

		imaginarioTotal = new TextField();
		imaginarioTotal.setPrefWidth(50);
		imaginarioTotal.setDisable(true);

		separador = new Separator();

		simbolo1 = new Label("+");
		simbolo2 = new Label("+");
		simboloTotal = new Label("+");

		complejo1 = new Complejo();
		complejo2 = new Complejo();
		complejoResultado = new Complejo();

		VBox combo = new VBox(5, simbolo);
		combo.setAlignment(Pos.CENTER);

		HBox suma1 = new HBox(5, real1, simbolo1, imaginario1, new Label("i"));
		suma1.setAlignment(Pos.CENTER);

		HBox suma2 = new HBox(5, real2, simbolo2, imaginario2, new Label("i"));
		suma2.setAlignment(Pos.CENTER);

		HBox resultado = new HBox(5, realTotal, simboloTotal, imaginarioTotal, new Label("i"));
		resultado.setAlignment(Pos.CENTER);

		VBox operaciones = new VBox(5, suma1, suma2, separador, resultado);
		operaciones.setAlignment(Pos.CENTER);

		HBox root = new HBox(5, combo, operaciones);
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root, 320, 200);
		primaryStage.setTitle("Calculadora Compleja");
		primaryStage.setScene(scene);
		primaryStage.show();

		// bindings

		// real
		Bindings.bindBidirectional(real1.textProperty(), complejo1.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(real2.textProperty(), complejo2.realProperty(), new NumberStringConverter());
		// imaginario
		Bindings.bindBidirectional(imaginario1.textProperty(), complejo1.imaginarioProperty(),
				new NumberStringConverter());
		Bindings.bindBidirectional(imaginario2.textProperty(), complejo2.imaginarioProperty(),
				new NumberStringConverter());

		realTotal.textProperty().bind(complejoResultado.imaginarioProperty().asString());
		imaginarioTotal.textProperty().bind(complejoResultado.realProperty().asString());

		seleccionado.bind(simbolo.getSelectionModel().selectedItemProperty());
		seleccionado.addListener((o, ov, nv) -> {
			switch (nv) {
			case "+":

				complejoResultado.imaginarioProperty().bind(complejo1.realProperty().add(complejo2.realProperty()));
				complejoResultado.realProperty()
						.bind(complejo1.imaginarioProperty().add(complejo2.imaginarioProperty()));

				break;
			case "-":

				complejoResultado.imaginarioProperty()
						.bind(complejo1.realProperty().subtract(complejo2.realProperty()));
				complejoResultado.realProperty()
						.bind(complejo1.imaginarioProperty().subtract(complejo2.imaginarioProperty()));

				break;
			case "*":

				complejoResultado.imaginarioProperty()
						.bind((complejo1.realProperty().multiply(complejo2.realProperty()))
								.subtract(complejo1.imaginarioProperty().multiply(complejo2.imaginarioProperty())));
				complejoResultado.realProperty()
						.bind((complejo1.realProperty().multiply(complejo2.imaginarioProperty()))
								.add(complejo1.imaginarioProperty().multiply(complejo2.realProperty())));

				break;
			case "/":

				complejoResultado.imaginarioProperty()
						.bind((complejo1.realProperty().multiply(complejo2.realProperty()))
								.add(complejo1.imaginarioProperty().multiply(complejo2.imaginarioProperty()))
								.divide(complejo2.realProperty().multiply(complejo2.realProperty())
										.add(complejo2.imaginarioProperty().multiply(complejo2.imaginarioProperty()))));
				complejoResultado.realProperty()
						.bind((complejo1.imaginarioProperty().multiply(complejo2.realProperty()))
								.subtract(complejo1.realProperty().multiply(complejo2.imaginarioProperty()))
								.divide(complejo2.realProperty().multiply(complejo2.realProperty())
										.add(complejo2.imaginarioProperty().multiply(complejo2.imaginarioProperty()))));

				break;

			default:
				break;
			}
		});

		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
