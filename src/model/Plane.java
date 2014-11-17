package model;

import java.io.Serializable;

public class Plane implements Serializable{
	private static final long serialVersionUID = 1L;
	private int planeId;
	private int plane_number;
	private int first_class_capacity;
	private int business_capacity;
	private int economy_capacity;
	
	public Plane(int planeId, int plane_number, int first_class_capacity,
			int business_capacity, int economy_capacity) {
		super();
		this.planeId = planeId;
		this.plane_number = plane_number;
		this.first_class_capacity = first_class_capacity;
		this.business_capacity = business_capacity;
		this.economy_capacity = economy_capacity;
	}

	public int getPlaneId() {
		return planeId;
	}

	public int getPlane_number() {
		return plane_number;
	}

	public void setPlane_number(int plane_number) {
		this.plane_number = plane_number;
	}

	public int getFirst_class_capacity() {
		return first_class_capacity;
	}

	public void setFirst_class_capacity(int first_class_capacity) {
		this.first_class_capacity = first_class_capacity;
	}

	public int getBusiness_capacity() {
		return business_capacity;
	}

	public void setBusiness_capacity(int business_capacity) {
		this.business_capacity = business_capacity;
	}

	public int getEconomy_capacity() {
		return economy_capacity;
	}

	public void setEconomy_capacity(int economy_capacity) {
		this.economy_capacity = economy_capacity;
	}

	@Override
	public String toString() {
		return "Plane " + plane_number;
	}
}
