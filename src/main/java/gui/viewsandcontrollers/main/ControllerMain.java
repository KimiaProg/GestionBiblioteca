package gui.viewsandcontrollers.main;

import java.io.IOException;

import negocio.Negocio;
import negocio.model.*;
import negocio.model.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerMain {

	private static ObservableList<Libro> catalogo = FXCollections.observableArrayList();

	@FXML
	TableView<Libro> table;

	@FXML
	Button botonEliminar;
	@FXML
	Button botonEditar;

	@FXML
	TableColumn<Libro, String> titulo;
	@FXML
	TableColumn<Libro, String> isbn;
	@FXML
	TableColumn<Libro, String> autor;
	@FXML
	TableColumn<Libro, Genero> genero;
	@FXML
	TableColumn<Libro, Integer> paginas;

	@FXML
	public void initialize() {

		titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		autor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		genero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		paginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
		
		botonEditar.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
		botonEliminar.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
		

	}

	@FXML
	private void nuevo(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
		Stage parent = (Stage) source.getScene().getWindow();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../modal/NuevoFXML.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage dialog = new Stage();
		dialog.setScene(new Scene(root1));
		dialog.initOwner(parent);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.showAndWait();

		catalogo.add(Negocio.getCatalogoNegocio().get(Negocio.getCatalogoNegocio().size()-1));
		table.getItems().add(catalogo.get(catalogo.size() - 1));
	}
	

	@FXML
	private void editar(ActionEvent event) throws IOException {
		Libro libroAEditar = table.getSelectionModel().getSelectedItem();
		Node source = (Node) event.getSource();
		Stage parent = (Stage) source.getScene().getWindow();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../modal/NuevoFXML.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage dialog = new Stage();
		dialog.setScene(new Scene(root1));
		dialog.initOwner(parent);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.showAndWait();

		int indiceOflibroEdit = catalogo.indexOf(libroAEditar);

		Libro ultimo= catalogo.get(catalogo.size()-1);
		catalogo.remove(catalogo.size()-1);
		catalogo.set(indiceOflibroEdit,ultimo);
		
		//catalogo.remove(indiceOflibroEdit+1);
		
		table.getItems().removeAll(catalogo);
		for(Libro lib: catalogo) {
			System.out.println(lib.toString());
		}
		table.getItems().addAll(catalogo);

		/*int tempIndex = indiceOflibroEdit;

		while (tempIndex >= 0) {
			table.getItems().remove(tempIndex);

			tempIndex--;
		}
		
		table.getItems().remove(indiceOflibroEdit+1);*/
		
		

	}

	@FXML
	private void eliminar(ActionEvent event) throws IOException {
		Negocio.delete(table.getSelectionModel().getSelectedItem());
		table.getItems().removeAll(catalogo);
		catalogo.removeAll(catalogo);
		catalogo.addAll(Negocio.getCatalogoNegocio());
		table.getItems().addAll(catalogo);
		//table.getItems().remove(table.getSelectionModel().getSelectedItem());
	}

	@FXML
	private void salvar(ActionEvent event) throws IOException {

	}

	@FXML
	private void cargar(ActionEvent event) throws IOException {

	}

	public static ObservableList<Libro> getCatalogo() {
		return catalogo;
	}
}
