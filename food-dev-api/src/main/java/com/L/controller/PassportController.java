package com.L.controller;


@Api(value = "注册登录",tags = {"用于注册登录的相关接口"})
public class PassportController {

    @Autowired
    private UserService userService;

    @ApiOperation()
    @GetMapping ("/usernameIsExist")
    public int usernameIsExist(@RequestParam String username){

        // 1.判断用户名不能为空
        if (StringUtils.isBlank(username)){
            return LJSONResult.errorMsg("用户名不能为空");
        }
        //2.查找注册的用户名是否存在
        boolean isExist = userService.queryUsernameIsExist (username) ;
        if (isExist){
            return LJSONResult.errorMsg("用户名已经存在");
        }
        //3.请求成功，用户名没有重复
        return LJSONResult.ok();
    }

    @PostMapping
    public LJSONResoult regist(UserBO userBO){
//        拿到用户名，密码，校验码
        //0.判断用户名和密码必须不为空
//1.查询用户名是否存在
//2.密码长度不能少于6位
//3.判断两次密码是否一致
//4.实现注册
        return LJSONResult.ok();
    }

    public LJSONResoult login(@RequestParam UserBO userBO,
                              HttpServletRequest request,
                              HttpServletResponse response){
//        0.用户名密码判空
//        1.实现登录
        Users userResult =userService.queryUserForLogin();
        return  LJSONResult.ok(userResult);
//        2.if错误返回返回null

//        3.浏览器不显示重要隐私信息
        userResult = setNullProperty(userResult);
        CookieUtils.setCookie();
    }

    private Users setNullProperty(Users userResult){

        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

    public LJSONResoult logout(@RequestParam String userId,
                              HttpServletRequest request,
                              HttpServletResponse response){
//        0.清楚用户相关的cookie，cookieUtils.deleteCookie();
//        1.清空购物车
//        2.分布式会话中需要清楚用户数据
    }
}
