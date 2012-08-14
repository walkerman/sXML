package br.com.scia.xml.entity.view;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javax.swing.JOptionPane;

public class TipoPecaXY implements EventHandler<ActionEvent>{

	private SimpleStringProperty ordem;
	private SimpleStringProperty item;
	private Hyperlink remover;
	private TableView<TipoPecaXY> tableReference;
	private ComboBox<String> sumarioReference;
	private TextField quantidadeReference;
	
	public TipoPecaXY(String ordem, String item) {
		super();
		this.item = new SimpleStringProperty(item);
		this.remover = new Hyperlink("Remover");
		this.remover.setOnAction(this);
		this.ordem = new SimpleStringProperty(ordem);
	}
	
	public String getOrdem() {
		return ordem.get();
	}
	public void setOrdem(String ordem) {
		this.ordem.set(ordem);
	}
	public String getItem() {
		return item.get();
	}
	public void setItem(String tipo) {
		this.item.set(tipo);
	}
	public Hyperlink getRemover() {
		return remover;
	}
	public void setRemover(Hyperlink remover) {
		this.remover = remover;
	}
	public TableView<TipoPecaXY> getTableReference() {
		return tableReference;
	}
	public void setTableReference(TableView<TipoPecaXY> tableReference) {
		this.tableReference = tableReference;
	}
	
	@Override
	public void handle(ActionEvent event) {
		this.tableReference.getItems().removeAll(this);
		
		List<TipoPecaXY> itensTable = this.tableReference.getItems();
		ArrayList<String> itensSumario = new ArrayList<String>();
		for (int i = 0; i < itensTable.size(); i++) {
			TipoPecaXY t = itensTable.get(i);
			t.setOrdem(String.valueOf(i+1));
			itensSumario.add(t.getItem());
		}
		
		this.sumarioReference.getItems().removeAll(this.sumarioReference.getItems());
		this.sumarioReference.getItems().addAll(itensSumario);
		
		this.quantidadeReference.setText(String.valueOf(this.tableReference.getItems().size()));
		
		JOptionPane.showMessageDialog(null, "Travessa " + ordem.get() + " removida com sucesso.");		
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
