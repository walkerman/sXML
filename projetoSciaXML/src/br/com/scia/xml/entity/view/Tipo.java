package br.com.scia.xml.entity.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

public class Tipo {

	private SimpleStringProperty ordem;
	private SimpleStringProperty item;
	private TableView<Tipo> tableReference;
	private ComboBox<String> sumarioReference;
	
	public String getOrdem() {
		return ordem.getValue();
	}
	public void setOrdem(String ordem) {
		this.ordem = new SimpleStringProperty(ordem);
	}
	public String getItem() {
		return item.getValue();
	}
	public void setItem(String item) {
		this.item = new SimpleStringProperty(item);
	}
	public TableView<Tipo> getTableReference() {
		return tableReference;
	}
	public void setTableReference(TableView<Tipo> tableReference) {
		this.tableReference = tableReference;
	}
	public ComboBox<String> getSumarioReference() {
		return sumarioReference;
	}
	public void setSumarioReference(ComboBox<String> sumarioReference) {
		this.sumarioReference = sumarioReference;
	}	
	@Override
	public String toString() {
		return this.getItem().toString();
	}
}
