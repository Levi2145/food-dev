package com.L.service;
import com.L.pojo.Users;
import com.L.pojo.bo.UserBO;

public interface UsersService {

    /**
     * 判断用户名是否存在
     */
    public boolean queryUsernameIsExist(String username);

    /*
     * 用户注册
     * */
    Users createUser(UserBO userBO);

    /**
     * 检索用户名和密码是否匹配，用于登录
     */
    public Users queryUserForLogin(String username, String password);
}
