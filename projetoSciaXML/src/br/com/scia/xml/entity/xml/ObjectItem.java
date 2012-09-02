package br.com.scia.xml.entity.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class ObjectItem {
	
	@XStreamAsAttribute
	private String v;
	
	@XStreamAsAttribute
	private String i;
		
	@XStreamAsAttribute
	private String n;
	
	@XStreamAsAttribute
	private String t;
	
	private Header h;

	@XStreamImplicit(itemFieldName="row")
	private List<Row> row;
	
	public String getV() {
		return v;
	}

	public String getI() {
		return i;
	}

	public String getN() {
		return n;
	}

	public String getT() {
		return t;
	}

	public void setV(String v) {
		this.v = v;
	}

	public void setI(String i) {
		this.i = i;
	}

	public void setN(String n) {
		this.n = n;
	}

	public void setT(String t) {
		this.t = t;
	}

	public Header getH() {
		return h;
	}

	public void setH(Header h) {
		this.h = h;
	}

	public List<Row> getRow() {
		return row;
	}

	public void setRow(List<Row> row) {
		this.row = row;
	}

	
	public ObjectItem() {
		super();
	}

	public ObjectItem(String v, String i, String n, String t, Header h,
			List<Row> row) {
		super();
		this.v = v;
		this.i = i;
		this.n = n;
		this.t = t;
		this.h = h;
		this.row = row;
	}
	
}
