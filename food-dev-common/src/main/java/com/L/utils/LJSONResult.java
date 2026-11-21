package com.L.utils;

import java.util.Map;

/**
 *
 * @Title: LJSONResult.java
 * @Package com.L.utils
 * @Description: 自定义响应数据结构
 *               本类可提供给 H5/ios/安卓/公众号/小程序 使用
 *               前端接受此类数据（json object)后，可自行根据业务去实现相关功能
 *
 *               200: 表示成功
 *               500: 表示错误，错误信息在msg字段中
 *               501: bean验证错误，不管多少个错误都以map形式返回
 *               502: 拦截器拦截到用户token出错
 *               555: 异常抛出信息
 *               556: 用户qq校验异常
 * @Copyright: Copyright (c) 2020
 * @Company: www.L.com
 * @author L
 * @version V1.0
 */
public class LJSONResult {
    private final Integer status;
    private final String msg;
    private final Object data;

    public LJSONResult(Integer status, String msg, Object data) {
        this.status = status; this.msg = msg; this.data = data;
    }
    public static LJSONResult ok(Object data) { return new LJSONResult(200, "success", data); }
    public Integer getStatus() { return status; }
    public String getMsg() { return msg; }
    public Object getData() { return data; }

    public static LJSONResult errorMsg(String msg) {
        return new LJSONResult(500, msg, null);
    }

    public static LJSONResult errorMap(Object data) {
        return new LJSONResult(501, "error", data);
    }

    public static LJSONResult errorTokenMsg(String msg) {
        return new LJSONResult(502, msg, null);
    }

    public static LJSONResult errorException(String msg) {
        return new LJSONResult(555, msg, null);
    }

    public static LJSONResult errorUserQQ(String msg) {
        return new LJSONResult(556, msg, null);
    }
}
