package com.example.demo.common;

import java.util.HashMap;

/**
 * 统一数据返回的封装类
 */

public class AjaxResult {
    /**
     * 业务逻辑执行成功时返回
     * @param data
     * @return
     */
    public static HashMap<String, Object> success(Object data){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "");
        result.put("data", data);
        return result;
    }

    public static HashMap<String, Object> success(String msg, Object data) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }

    /**
     *业务逻辑执行失败时返回
     * @param code
     * @param msg
     * @return
     */
    public static HashMap<String, Object> fail(int code, String msg) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", "");
        return result;
    }
}
