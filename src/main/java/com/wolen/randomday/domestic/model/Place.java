package com.wolen.randomday.domestic.model;

public class Place {
	private int id;
	private int placeId;
	private String title;
	private String category;
	private String address;
	private String roadAddress;
	private String telephone;
	private double longitude;
	private double latitude;
	private String imageURL;
	private boolean isLike;
	
	
	public Place(int id, int placeId , String title, String category, String address, String roadAddress, String telephone,
			double longitude, double latitude, String imageURL) {
		
		this.id = id;
		this.placeId = placeId;
		this.title = title;
		this.category = category;
		this.address = address;
		this.roadAddress = roadAddress;
		this.telephone = telephone;
		this.longitude = longitude;
		this.latitude = latitude;
		this.imageURL = imageURL;

	}
	
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public int getPlaceId() {
		return placeId;
	}





	public boolean isLike() {
		return isLike;
	}





	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}





	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}





	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRoadAddress() {
		return roadAddress;
	}
	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageUrl(String imageURL) {
		this.imageURL = imageURL;
	}
	
	
	
	
	
}
