package com.github.xrapalexandra.kr.service.impl;

import com.github.xrapalexandra.kr.dao.RatingDao;
import com.github.xrapalexandra.kr.dao.impl.DefaultRatingDao;
import com.github.xrapalexandra.kr.model.Rating;
import com.github.xrapalexandra.kr.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class DefaultRatingService implements RatingService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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
        rating.setRatingId(ratingDao.addRating(rating));
        logger.info("Add {} into DataBase.", rating);
        return rating.getRatingId();
    }

    @Override
    public void delRating(Rating rating) {
        ratingDao.delRating(rating);
        logger.info("Delete {} from DataBase.", rating);
    }

    @Override
    public Double getAvrRatingByProductId(int productId) {
        return ratingDao.getAvrRatingByProductId(productId);
    }
}
