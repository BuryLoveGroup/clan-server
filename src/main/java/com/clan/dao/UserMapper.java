package com.clan.dao;

import com.clan.bean.User;

/**
 * Created by robot on 2017/11/7.
 */
public interface UserMapper {

    void regist(User user);
    User getUserById();
}
