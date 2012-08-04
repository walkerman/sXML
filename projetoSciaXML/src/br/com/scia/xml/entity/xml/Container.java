package br.com.scia.xml.entity.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias ("container")
public class Container {
	
	@XStreamAsAttribute
	private String id;
	
	@XStreamAsAttribute
	private String t;
	
	private TableNode table;

	public Container(String id, String t, TableNode table) {
		this.id = id;
		this.t = t;
		this.table = table;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public TableNode getTable() {
		return table;
	}

	public void setTable(TableNode table) {
		this.table = table;
	}	
}
