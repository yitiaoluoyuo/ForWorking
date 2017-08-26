package com.xiaoxu.xiaoxu_ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxu.xiaoxu_ec.database.UserProfile;

/**
 * Created by xiaoxu on 2017/8/26.
 * sign helper
 */

public class SignStatueHandler {

    public static void onSignIn(String response, ISignSuccessListener signListener) {
        //请求成功后解析并获取，返会的json字符串"data"里的数据
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long id = profileJson.getLong("id");
        final String username = profileJson.getString("username");
        final String email = profileJson.getString("email");
        final String phone = profileJson.getString("phone");
        final int role = profileJson.getInteger("role");
        final long createTime = profileJson.getLong("createTime");
        final long updateTime = profileJson.getLong("createTime");


        final UserProfile profile = new UserProfile(id, username, email, phone, role,createTime,updateTime);

        //DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功了
        //AccountManager.setSignInState(true);
        //signListener.onSignInSuccess();
    }


    public static void onSignUp(String response, ISignSuccessListener signListener) {

       /* final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        //把返回的数据插入到数据库
        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);*/

        //已经注册并登录成功了
        //AccountManager.setSignInState(true);
        //注册成功后的回调处理
        //signListener.onSignUpSuccess();
    }
}
