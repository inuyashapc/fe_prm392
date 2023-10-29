package com.example.projectprmteam2;

import android.content.Context;
import android.content.SharedPreferences;

public class Sharepreference {

        public static final String PREFERENCE_NAME = "PREFERENCE_DATA";
        private final SharedPreferences sharedpreferences;
        public Sharepreference(Context context) {
            System.out.println(context);
            sharedpreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            System.out.println(sharedpreferences);
        }

        public boolean getCheckLogin() {
            boolean check = sharedpreferences.getBoolean("checkLogin", false);
            return check;
        }

        public void login(boolean check) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean("checkLogin", check);
            editor.apply();
        }

        public void logout() {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.remove("checkLogin");
            editor.apply();
        }

}
