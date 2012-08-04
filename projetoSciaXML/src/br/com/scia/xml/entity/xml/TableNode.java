package br.com.scia.xml.entity.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias ("table")
public class TableNode {
	
	@XStreamAsAttribute
	private String id;
	
	@XStreamAsAttribute
	private String t;
	
	@XStreamAsAttribute
	private String name;
	
	private Header h;
		
	@XStreamImplicit(itemFieldName="obj")
	private List<Object> objects;

	public String getId() {
		return id;
	}

	public String getT() {
		return t;
	}

	public String getName() {
		return name;
	}

	public Header getH() {
		return h;
	}

	public List<Object> getObjects() {
		return objects;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setT(String t) {
		this.t = t;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setH(Header h) {
		this.h = h;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}

	public TableNode(String id, String t, String name, Header h,
			List<Object> objects) {
		super();
		this.id = id;
		this.t = t;
		this.name = name;
		this.h = h;
		this.objects = objects;
	}
	
	
}
