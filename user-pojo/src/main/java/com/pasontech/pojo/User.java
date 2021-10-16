package com.pasontech.pojo;

import javax.validation.constraints.NotBlank;
import java.util.Date;


/**
 * @author pason.wang
 * @date 2021-10-11 09:11:12
 */
public class User {

    /**
     * user id
     */
    private Long id;

    /**
     * user name
     */
    private String name;

    /**
     * user date of birth
     */
    private String dob;

    /**
     * user address
     */
    private String address;

    /**
     * user description
     */
    private String description;

    /**
     * user created date
     */
    private Date createdAt;


    /**
     * extensive field
     * username user nick name
     */
    private String userName;

    /**
     * extension field
     * password
     */
    private String password;

    /**
     * extension field
     * distance
     */
    private String distance;

    /**
     * extension field
     * positionId
     */
    private Integer postionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob == null ? null : dob.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Integer getPostionId() {
        return postionId;
    }

    public void setPostionId(Integer postionId) {
        this.postionId = postionId;
    }
}