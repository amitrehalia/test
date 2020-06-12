package com.tutorialspoint;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "radcheck")
@XmlAccessorType(XmlAccessType.FIELD)
public class Radcheckinfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	   private int id;
	   private String username;
	   private String attribute;
	   private String op;
	   private String value;
	   
	   public Radcheckinfo(){
		   
	   }
	public Radcheckinfo(int id, String username, String attribute, String op, String value) {
		this.id = id;
		this.username = username;
		this.attribute = attribute;
		this.op = op;
		this.value = value;
	}
	
	public Radcheckinfo(String username, String attribute, String op, String value) {
		this.username = username;
		this.attribute = attribute;
		this.op = op;
		this.value = value;
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	 
	public String getAttribute() {
		return attribute;
	}
	
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	 
	public String getOp() {
		return op;
	}
	
	public void setOp(String op) {
		this.op = op;
	}
	 
	 
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	/*
	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;

		else if(!(object instanceof Radcheckinfo)){
			return false;
		}else {
			Radcheckinfo user = (Radcheckinfo)object;
			if(id == user.getId()
					&& username.equals(user.getUsername())
					&& attribute.equals(user.getAttribute())
					&& op.equals(user.getOp())
					&& value.equals(user.getValue())
					){
				return true;
			}
		}
		return false;

	}*/
}
