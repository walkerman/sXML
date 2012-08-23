package br.com.scia.xml.entity.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TipoViga implements EventHandler<ActionEvent>{

	private SimpleStringProperty item;
	private CheckBox habilitar;
	private TableView<TipoViga> tableReference;
	private ComboBox<String> sumarioReference;
	private TextField quantidadeReference;
	
	public TipoViga(String ordem, String item) {
		super();
		this.item = new SimpleStringProperty(item);
		this.habilitar = new CheckBox();
		this.habilitar.setOnAction(this);
	}
	
	public String getItem() {
		return item.get();
	}
	public void setItem(String tipo) {
		this.item.set(tipo);
	}
	public CheckBox getHabilitar() {
		return habilitar;
	}
	public void setHabilitarr(CheckBox habilitar) {
		this.habilitar = habilitar;
	}
	public TableView<TipoViga> getTableReference() {
		return tableReference;
	}
	public void setTableReference(TableView<TipoViga> tableReference) {
		this.tableReference = tableReference;
	}
	
	@Override
	public void handle(ActionEvent event) {
		// Viga esta habilitada ou não?			
	}
	
	@Override
	public String toString() {
		return this.getItem().toString();
	}

	public ComboBox<String> getSumarioReference() {
		return sumarioReference;
	}

	public void setSumarioReference(ComboBox<String> sumarioReference) {
		this.sumarioReference = sumarioReference;
	}

	public TextField getQuantidadeReference() {
		return quantidadeReference;
	}

	public void setQuantidadeReference(TextField quantidadeReference) {
		this.quantidadeReference = quantidadeReference;
	}
	
}
