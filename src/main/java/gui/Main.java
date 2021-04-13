/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("viewsandcontrollers/main/BasicFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
     
    /*
     * Stage stage = new Stage();
		
	
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../modal/NuevoFXML.fxml"));
		
		/*ControllerNuevo nuevo;
		if(libro==null) {
			nuevo= new ControllerNuevo();
		}else {
			nuevo= new ControllerNuevo(libro);
		}
		
		fxmlLoader.setController(nuevo);
		Parent root1 = fxmlLoader.load();
		stage.setScene(new Scene(root1));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(((Node) event.getSource()).getScene().getWindow());
		stage.showAndWait();
     */
}
