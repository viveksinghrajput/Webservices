/*
*StateTemp.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Apr 4, 2018
*/
package com.rural.webservice.DTO;

import java.util.Set;

public class StateTemp {
	private int id;
	private String name;
	private Set<CityTemp> cities;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<CityTemp> getCities() {
		return cities;
	}
	public void setCities(Set<CityTemp> cities) {
		this.cities = cities;
	}
	

}
