package br.com.scia.xml.entity.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;

public class TipoViga extends Tipo implements EventHandler<ActionEvent>{

	private CheckBox habilitar;
	
	public TipoViga(String item) {
		super();
		this.setItem(item);
		this.habilitar = new CheckBox();
		this.habilitar.setSelected(true);
		this.habilitar.setOnAction(this);
	}
	public CheckBox getHabilitar() {
		return habilitar;
	}
	public void setHabilitarr(CheckBox habilitar) {
		this.habilitar = habilitar;
	}
	@Override
	public void handle(ActionEvent event) {
		if (this.habilitar.isSelected())
			this.getSumarioReference().getItems().add(this.getItem());
		else
			this.getSumarioReference().getItems().remove(this.getItem());
	}
	
	
}
