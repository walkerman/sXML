package br.com.scia.xml.entity.view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import javax.swing.JOptionPane;

public class TipoPecaXY extends Tipo implements EventHandler<ActionEvent>{

	private Hyperlink remover;
	private TextField quantidadeReference;
	
	public TipoPecaXY(String ordem, String item) {
		super();
		this.setItem(item);
		this.remover = new Hyperlink("Remover");
		this.remover.setOnAction(this);
		this.setOrdem(ordem);
	}
	
	@Override
	public void handle(ActionEvent event) {
		this.getTableReference().getItems().remove(this);
		
		List<Tipo> itensTable = this.getTableReference().getItems();
		ArrayList<String> itensSumario = new ArrayList<String>();
		for (int i = 0; i < itensTable.size(); i++) {
			Tipo t = itensTable.get(i);
			t.setOrdem(String.valueOf(i+1));
			itensSumario.add(t.getItem().toString());
		}
		
		this.getSumarioReference().getItems().removeAll(this.getSumarioReference().getItems());
		this.getSumarioReference().getItems().addAll(itensSumario);
		
		this.quantidadeReference.setText(String.valueOf(this.getTableReference().getItems().size()));
		
		JOptionPane.showMessageDialog(null, "Peça " + this.getOrdem().toString() + " removida com sucesso.");		
	}
	public Hyperlink getRemover() {
		return remover;
	}
	public void setRemover(Hyperlink remover) {
		this.remover = remover;
	}	
	public TextField getQuantidadeReference() {
		return quantidadeReference;
	}

	public void setQuantidadeReference(TextField quantidadeReference) {
		this.quantidadeReference = quantidadeReference;
	}
	
}
