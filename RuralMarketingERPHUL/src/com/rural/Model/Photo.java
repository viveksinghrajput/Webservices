/*
*Photo.java
*Created By		:Bhagya Lakshmi Mula
*Created Date	:Jul 4, 2018
*/
package com.rural.Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="photo_master")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "photos")
public class Photo implements Serializable{
	
	
	     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 public Photo(){
	    }
	   /* public Photo(WarehouseMaster warehouseMaster){
	       this.warehouseMaster=warehouseMaster;
	    }
*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	/*@Column(name = "trackId")
	private String trackId;*/
   
    @Column(name = "time")
    private String time;
    
    @Column(name = "gpsTime")
    private String gpsTime;

	@Column(name = "dataTypeId")
	private int dataType;
    
    @Column(name = "photoTypeId")
	private int type;
    
    @Column(name = "imageName")
    private String name;
    
    @Column(name = "path")
    private String path;
    
    @Column(name = "latitude")
    private String lat;
    
    @Column(name = "longitude")
    private String lon;
    
    @Column(name = "cellLatitude")
    private String netLat;
    
    @Column(name = "cellLongitude")
    private String netLon;
    
    @Column(name = "locAcc")
    private String locAcc;
    
    @Column(name = "userId")
    private int userId;
    
    @Column(name = "subId")
    private int subId;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getGpsTime() {
		return gpsTime;
	}

	public void setGpsTime(String gpsTime) {
		this.gpsTime = gpsTime;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getNetLat() {
		return netLat;
	}

	public void setNetLat(String netLat) {
		this.netLat = netLat;
	}

	public String getNetLon() {
		return netLon;
	}

	public void setNetLon(String netLon) {
		this.netLon = netLon;
	}

	public String getLocAcc() {
		return locAcc;
	}

	public void setLocAcc(String locAcc) {
		this.locAcc = locAcc;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
    
    
    
 /*   @ManyToOne(cascade=CascadeType.ALL) 
	private WarehouseMaster warehouseMaster;*/
    
	
	/*public WarehouseMaster getWarehouseMaster() {
		return warehouseMaster;
	}
	public void setWarehouseMaster(WarehouseMaster warehouseMaster) {
		this.warehouseMaster = warehouseMaster;
	}*/
	


}
