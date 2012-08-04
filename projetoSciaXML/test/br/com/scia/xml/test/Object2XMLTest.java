 package br.com.scia.xml.test;

import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;

import br.com.scia.xml.entity.xml.Container;
import br.com.scia.xml.entity.xml.Definition;
import br.com.scia.xml.entity.xml.Header;
import br.com.scia.xml.entity.xml.HeaderItem;
import br.com.scia.xml.entity.xml.ObjectItem;
import br.com.scia.xml.entity.xml.Project;
import br.com.scia.xml.entity.xml.Row;
import br.com.scia.xml.entity.xml.TableNode;

public class Object2XMLTest {
public static void main(String[] args) {
		Row row = new Row("id", new ObjectItem("vv", "i", "n", "t",null,null),
				new ObjectItem("vv", "i", "n", "t",null,null));
	
	ArrayList<Row> rows = new ArrayList<Row>();
	rows.add(row);
	
	br.com.scia.xml.entity.xml.Object o = new br.com.scia.xml.entity.xml.Object(new ObjectItem("v", "i", "n", "t",null,null), 
																		      new ObjectItem("v", "i", "n", "t",null,null), 
																		      new ObjectItem("v", "i", "n", "t",null,null), 
																		      new ObjectItem("v", "i", "n", "t",null,null), 
																		      new ObjectItem("v", "i", "n", "t",null,null), 
																		      new ObjectItem("v", "i", "n", "t",null,null), 
																		      new ObjectItem("v", "i", "n", "t",null,null), 
																		      new ObjectItem("v", "i", "n", "t",
																	    		         new Header(new HeaderItem("h0"), 
																	    		        		    new HeaderItem("h1"), 
																	    		        		    null, null, null, null, null, null, null, 
																	    		        		    null, null, null, null, null, null, null,
																	    		        		    null, null, null, null, null),
																	    		         rows),																		      
																		      new ObjectItem("v", "i", "n", "t",null,null),
																		      new ObjectItem("v", "i", "n", "t",null,null),
																		      new ObjectItem("v", "i", "n", "t",null,null),
																		      new ObjectItem("v", "i", "n", "t",null,null),
																		      new ObjectItem("v", "i", "n", "t",null,null),
																		      new ObjectItem("v", "i", "n", "t",null,null), 
																		      new ObjectItem("v", "i", "n", "t",null,null), 
																		      new ObjectItem("v", "i", "n", "t",null,null), 
																		      new ObjectItem("v", "i", "n", "t",null,null),
																		      new ObjectItem("v", "i", "n", "t",null,null),
																		      new ObjectItem("v", "i", "n", "t",null,null),
																		      new ObjectItem("v", "i", "n", "t",null,null),
																		      new ObjectItem("v", "i", "n", "t",null,null));
	
	br.com.scia.xml.entity.xml.Object o2 = new br.com.scia.xml.entity.xml.Object(new ObjectItem("v", "i", "n", "t",null,null), 
																		       new ObjectItem("v", "i", "n", "t",null,null), 
																		       new ObjectItem("v", "i", "n", "t",null,null), 
																		       new ObjectItem("v", "i", "n", "t",null,null), 
																		       new ObjectItem("v", "i", "n", "t",null,null), 
																		       new ObjectItem("v", "i", "n", "t",null,null),
																		       new ObjectItem("v", "i", "n", "t",null,null),
																		       new ObjectItem("v", "i", "n", "t",null,null),
																		       new ObjectItem("v", "i", "n", "t",null,null),
																		       new ObjectItem("v", "i", "n", "t",null,null), 
																		       new ObjectItem("v", "i", "n", "t",null,null), 
																		       new ObjectItem("v", "i", "n", "t",null,null), 
																		       new ObjectItem("v", "i", "n", "t",null,null), 
																		       new ObjectItem("v", "i", "n", "t",null,null),
																		       new ObjectItem("v", "i", "n", "t",null,null),
																		       new ObjectItem("v", "i", "n", "t",null,null),
																		       new ObjectItem("v", "i", "n", "t",null,null),
																		       new ObjectItem("v", "i", "n", "t",null,null),
																		       new ObjectItem("v", "i", "n", "t",null,null),
																		       new ObjectItem("v", "i", "n", "t",null,null),
																		       new ObjectItem("v", "i", "n", "t",null,null));
	
	ArrayList<br.com.scia.xml.entity.xml.Object> lista1 = new ArrayList<br.com.scia.xml.entity.xml.Object>();
	lista1.add(o);
	
	ArrayList<br.com.scia.xml.entity.xml.Object> lista2 = new ArrayList<br.com.scia.xml.entity.xml.Object>();
	//lista2.add(o);
	lista2.add(o2);
	
	Header header1 = new Header(new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"),
			   new HeaderItem("t"));
	
	Header header2 = new Header(new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"),
				   new HeaderItem("t"));
	
	Container c1 = new Container("c1", 
					 "t1", 
					 new TableNode("id1", 
							       "t1", 
							       "name", 
							       header1, 
							       lista1)
					);
	
	Container c2 = new Container("c1", 
					 "t1", 
					 new TableNode("id1", 
							       "t1", 
							       "name", 
							       header2, 
							       lista2)
					);
	
	ArrayList<Container> array = new ArrayList<Container>();
	array.add(c1);
	array.add(c2);
	
	Project p = new Project("avonts", new Definition("pans"), array );
	
	XStream xStream = new XStream();
	xStream.autodetectAnnotations(true);
	
	String a = xStream.toXML(p);
	
	System.out.println(a);
}
}
