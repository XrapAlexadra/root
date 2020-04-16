package com.github.xrapalexandra.kr.service.impl;

import com.github.xrapalexandra.kr.dao.RatingDao;
import com.github.xrapalexandra.kr.dao.impl.DefaultRatingDao;
import com.github.xrapalexandra.kr.model.Rating;
import com.github.xrapalexandra.kr.service.RatingService;

public class DefaultRatingService implements RatingService {

    private DefaultRatingService() {
    }

    private static volatile RatingService instance;

    public static RatingService getInstance() {
        RatingService localInstance = instance;
        if (localInstance == null) {
            synchronized (RatingService.class) {
                localInstance = instance;
                if (localInstance == null)
                    localInstance = instance = new DefaultRatingService();
            }
        }
        return localInstance;
    }

    private RatingDao ratingDao = DefaultRatingDao.getInstance();

    @Override
    public int addRating(Rating rating) {
        return ratingDao.addRating(rating);
    }

    @Override
    public void delRating(Rating rating) {
        ratingDao.delRating(rating);
    }

    @Override
    public Double getAvrRatingByProductId(int productId) {
        return ratingDao.getAvrRatingByProductId(productId);
    }
}
