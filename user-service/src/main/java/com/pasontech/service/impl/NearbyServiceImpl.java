package com.pasontech.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.paosntech.common.ResponseVO;
import com.pasontech.pojo.User;
import com.pasontech.service.NearbyService;

import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class NearbyServiceImpl implements NearbyService {

    private static final String REDIS_KEY = "user:location";

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * people nearby
     *
     * @param radius      default 1000m
     * @param lon         longitude
     * @param lat         latitude
     * @return
     */
    public ResponseVO<List<User>> findNearBy(Long userId, Integer radius, Float lon, Float lat) {
        // default radius 1000m
        if (radius == null) {
            radius = 1000;
        }

        // get user lat, lon
        Point point = null;
        if (lon == null || lat == null) {
            // get lat and lon from redis
            List<Point> points = redisTemplate.opsForGeo().position(REDIS_KEY, userId);
            if (points == null || points.isEmpty()) {
                throw new IllegalArgumentException("failed to gain position info");
            }
            point = points.get(0);
        } else {
            point = new Point(lon, lat);
        }
        // define distance unit
        Distance distance = new Distance(radius, RedisGeoCommands.DistanceUnit.METERS);
        // init Geo related params
        RedisGeoCommands.GeoRadiusCommandArgs args =
                RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs();
        // limit the distance
        args.limit(20).includeDistance().sortAscending();
        Circle circle = new Circle(point, distance);
        // get nearby user GeoLocation info
        GeoResults<RedisGeoCommands.GeoLocation> geoResult =
                redisTemplate.opsForGeo().radius(REDIS_KEY, circle, args);
        // init map
        Map<Integer, User> nearMeVOMap = Maps.newLinkedHashMap();

        geoResult.forEach(result -> {
            RedisGeoCommands.GeoLocation<Integer> geoLocation = result.getContent();
            // init
            User nearByUser = new User();
            nearByUser.setPostionId(geoLocation.getName());
            // get distance
            Double dist = result.getDistance().getValue();
            String distanceStr = NumberUtil.round(dist, 1).toString() + "m";
            nearByUser.setDistance(distanceStr);
            nearMeVOMap.put(geoLocation.getName(), nearByUser);
        });
        // get recent nearby info
        Integer[] userIds = nearMeVOMap.keySet().toArray(new Integer[]{});
        return ResponseVO.success(Lists.newArrayList(nearMeVOMap.values()));
    }
}
