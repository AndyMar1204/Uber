/**
 * 
 */
package com.andyman.beans;

/**
 * @author AndyMan
 *
 */
public class Position {
	private int id_;
	private double longitude;
	private double latitude;
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Position(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public Position() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the id
	 */
	public int getId_() {
		return id_;
	}
	/**
	 * @param id the id to set
	 */
	public void setId_(int id) {
		this.id_ = id;
	}
	@Override
	public String toString() {
		return String.format("Position [id=%s, longitude=%s, latitude=%s]", id_, longitude, latitude);
	}
	
}
