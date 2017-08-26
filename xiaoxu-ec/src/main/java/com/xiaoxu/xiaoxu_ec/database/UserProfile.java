package com.xiaoxu.xiaoxu_ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by xiaoxu on 2017/8/26.
 * user entity
 */

@Entity(nameInDb = "user_profile")
public class UserProfile {

        @Id
        private long userId = 0;
        private String name = null;
        private String avatar = null;
        private String gender = null;
        private String address = null;


}
