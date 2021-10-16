package com.pasontech.mapper;

import com.pasontech.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author pason.wang
 * @date 2021-10-11 11:34:14
 */
@Mapper
public interface UserMapper {

    /**
     * query user info by user id
     * @param id userId
     * @return User entity
     */
    User selectByUserId(@Param("id") Long id);


    /**
     * query user info by username
     * @param userName
     * @return User entity
     */
    User selectByUsername(String userName);


    /**
     * query user nums by username
     * @param userName
     * @return User count
     */
    int countByUsername(String userName);

    /**
     * delete user info by user id
     * @param id userId
     * @return affected rows
     */
    int deleteByUserId(@Param("id") Long id);

    /**
     * insert a user info
     * @param user entity
     * @return affected rows
     */
    int insert(User user);

    /**
     * update user info according to related info
     * @param record
     * @return affected rows
     */
    int updateByExampleSelective(@Param("record") User record);

}
