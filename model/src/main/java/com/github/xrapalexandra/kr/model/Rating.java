package com.github.xrapalexandra.kr.model;

public class Rating {

    private int ratingId;
    private int rating;
    private int userId;
    private int productId;

    public Rating(int rating, int userId, int productId) {
        this.rating = rating;
        this.userId = userId;
        this.productId = productId;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ratingId=" + ratingId +
                ", rating=" + rating +
                ", userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
