package br.com.scia.xml.entity.view;

import javafx.scene.control.CheckBox;


public class TipoPoste extends Tipo{
	
	private CheckBox habilitar;
	
	public TipoPoste(String item) {
		super();
		this.setItem(item);
		this.habilitar = new CheckBox();
		this.habilitar.setSelected(true);
	}
	public CheckBox getHabilitar() {
		return habilitar;
	}
	public void setHabilitar(CheckBox habilitar) {
		this.habilitar = habilitar;
	}
}
