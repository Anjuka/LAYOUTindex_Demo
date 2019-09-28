package com.anjukakoralage.layoutindexdemo.helper;

/**
 * Created by anjukakoralage on 26,September,2019
 */
public class Constant {

    public static class HTTP {

        public static final String BASE_URL = "https://reqres.in/api/";
    }

    public static final class DataBase {

        public static final String DB_NAME = "users";
        public static final String TABLE_NAME = "user";
        public static final int DB_VERSION = 1;

        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String F_NAME = "first_name";
        public static final String L_NAME = "last_name";
        public static final String AVATAR = "avatar";

        public static final String DROP_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "" +
               "(" + ID + "PRIMARY KEY not null," +
                EMAIL + "TEXT not null," +
                F_NAME + "TEXT not null," +
                L_NAME + "TEXT not null," +
                AVATAR + "TEXT not null)" ;
    }
}
