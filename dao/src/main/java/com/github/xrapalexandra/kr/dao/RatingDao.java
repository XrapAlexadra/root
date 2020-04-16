package com.github.xrapalexandra.kr.dao;

import com.github.xrapalexandra.kr.model.Rating;

public interface RatingDao {

    int addRating(Rating rating);

    void delRating(Rating rating);

    Double getAvrRatingByProductId(int productId);
}
