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

        public String getCheckLogin() {
            String userId = sharedpreferences.getString("userId","");
            return userId;
        }

        public void login(String userId) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("userId", userId);
            editor.apply();
        }

        public void logout() {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.remove("userId");
            editor.apply();
        }


    public String getRoleUser() {
        String userId = sharedpreferences.getString("userRole","User");
        return userId;
    }

    public void setRoleUser(String role) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("userRole", role);
        editor.apply();
    }

    public void removeRole() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove("userRole");
        editor.apply();
    }

}
