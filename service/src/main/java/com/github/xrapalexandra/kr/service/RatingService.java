package com.github.xrapalexandra.kr.service;

import com.github.xrapalexandra.kr.model.Rating;

public interface RatingService {

    int addRating(Rating rating);

    void delRating(Rating rating);

    Double getAvrRatingByProductId(int productId);
}
