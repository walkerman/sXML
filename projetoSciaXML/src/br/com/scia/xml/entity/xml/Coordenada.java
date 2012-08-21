package br.com.scia.xml.entity.xml;

public class Coordenada {
	
	private String id;
	private String name;
	private Double x;
	private Double y;
	private Double z;
	
	public Coordenada(){
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}		
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public Double getZ() {
		return z;
	}
	public void setZ(Double z) {
		this.z = z;
	}

}
