package com.pasontech.service;

import com.paosntech.common.ResponseVO;
import com.pasontech.pojo.User;

import java.util.List;

public interface NearbyService {


    ResponseVO<List<User>> findNearBy(Long userId, Integer radius, Float lon, Float lat);
}
