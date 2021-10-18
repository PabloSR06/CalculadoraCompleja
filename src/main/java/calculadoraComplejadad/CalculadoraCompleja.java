package calculadoraComplejadad;

import comboBox.Alumno;
import javafx.application.Application;
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

public class CalculadoraCompleja extends Application {
	
	private ComboBox<String> simbolo;
	private Button igual;
	
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

	@Override
	public void start(Stage primaryStage) throws Exception {

		simbolo = new ComboBox<String>();
		igual = new Button("=");
		simbolo.getItems().addAll(
				"+", "-", "*", "/"
				);
				
		real1 = new TextField();
		real1.setPrefWidth(50);
		
		real2 = new TextField();
		real2.setPrefWidth(50);
		
		realTotal = new TextField();
		realTotal.setPrefWidth(50);
		
		imaginario1 = new TextField();
		imaginario1.setPrefWidth(50);
		
		imaginario2 = new TextField();
		imaginario2.setPrefWidth(50);
		
		imaginarioTotal = new TextField();
		imaginarioTotal.setPrefWidth(50);
		
		separador = new Separator();
		
		simbolo1 = new Label("+");
		simbolo2 = new Label("+");
		simboloTotal = new Label("+");
		
		
		VBox combo = new VBox(5, simbolo);
		combo.setAlignment(Pos.CENTER);

		HBox suma1 = new HBox(5, real1, simbolo1, imaginario1);
		suma1.setAlignment(Pos.CENTER);

		HBox suma2 = new HBox(5, real2, simbolo2,  imaginario2);
		suma2.setAlignment(Pos.CENTER);

		HBox resultado = new HBox(5, realTotal, simboloTotal,  imaginarioTotal);
		resultado.setAlignment(Pos.CENTER);

		VBox operaciones = new VBox(5, suma1, suma2,separador, resultado);
		operaciones.setAlignment(Pos.CENTER);

		VBox boton = new VBox(5, igual);
		boton.setAlignment(Pos.CENTER);
		
		HBox root = new HBox(5, combo, operaciones, boton);
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("Calculadora Compleja");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		//real1.textProperty().bindBidirectional
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
