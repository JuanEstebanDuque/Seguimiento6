package model;

import java.io.Serializable;

public class Vallas implements Serializable{
	//attributes
	private double width;
	private double height;
	private boolean inUse;
	private String brand;
	
	
	//relations
	
	//methods
	public Vallas() {
		
	}
	
	public Vallas(double width, double height, boolean inUse, String brand) {
		this.width = width;
		this.height = height;
		this.inUse = inUse;
		this.brand = brand;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
}
