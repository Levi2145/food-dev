package com.L.service.impl;
import com.L.pojo.Users;
import com.L.pojo.bo.UserBO;
import com.L.service.UsersService;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class UsersServiceImpl implements UsersService {
    private final Map<String, Users> users = new ConcurrentHashMap<String, Users>();
    public boolean queryUsernameIsExist(String username) { return users.containsKey(username); }
    public Users createUser(UserBO userBO) { Users user = new Users(); users.put(userBO.getUsername(), user); return user; }
    public Users queryUserForLogin(String username, String password) { return users.get(username); }
}
