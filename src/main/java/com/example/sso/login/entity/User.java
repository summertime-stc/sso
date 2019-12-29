package com.example.sso.login.entity;

import java.io.Serializable;
import lombok.Data;
import java.util.Date;

/**
 *  user
 * @author 大狼狗 2019-12-28
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * userid
     */
    private Integer userid;

    /**
     * name
     */
    private String name;

    /**
     * birthday
     */
    private Date birthday;

    /**
     * modifytime
     */
    private Date modifytime;

    /**
     * password
     */
    private String password;

    public User() {
    }

}

