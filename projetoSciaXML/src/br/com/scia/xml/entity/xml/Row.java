package br.com.scia.xml.entity.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("row")
public class Row {

	@XStreamAsAttribute
	private String id;
	
	private ObjectItem p1;	
	private ObjectItem p2;

	public String getId() {
		return id;
	}
	public ObjectItem getP1() {
		return p1;
	}
	public ObjectItem getP2() {
		return p2;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setP1(ObjectItem p1) {
		this.p1 = p1;
	}
	public void setP2(ObjectItem p2) {
		this.p2 = p2;
	}
	
	public Row(String id, ObjectItem p1, ObjectItem p2) {
		super();
		this.id = id;
		this.p1 = p1;
		this.p2 = p2;
	}
	
}
