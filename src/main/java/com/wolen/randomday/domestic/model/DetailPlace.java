package com.wolen.randomday.domestic.model;

import com.wolen.randomday.domestic.review.model.Review;

public class DetailPlace {
	
	
	private int id;
	private String name;
	private String category;
	private String roadAddress;
	private String telephone;
	private boolean isLike;
	private Review review;
	private int likeCount;
	private String menu;
	private String ImageURL;
	
	public DetailPlace(String name, String category, String roadAddress, String telephone, Review review, int likeCount, String menu, String imageURL) {
		
		this.name = name;
		this.category = category;
		this.roadAddress = roadAddress;
		this.telephone = telephone;
		this.review = review;
		this.likeCount = likeCount;
		this.menu = menu;
		this.ImageURL = imageURL;
		
	}
	
	
	
	
	public String getImageURL() {
		return ImageURL;
	}




	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
	}




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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public boolean isLike() {
		return isLike;
	}
	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	
	
	
	
	
}
