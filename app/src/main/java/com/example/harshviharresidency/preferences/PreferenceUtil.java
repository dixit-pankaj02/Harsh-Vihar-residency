package com.example.harshviharresidency.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {
    final private static String PREF_NAME = "HARSH_VIHAR_PREF";
    final private static String USER_ID = "USER_ID";
    final private static String USER_F_NAME = "USER_F_NAME";
    final private static String USER_L_NAME = "USER_L_NAME";
    final private static String FULL_NAME = "FULL_NAME";
    final private static String MOBILE_NO = "MOBILE_NO";
    final private static String HOUSE_NO = "HOUSE_NO";
    final private static String FAMILY_MEMBER = "FAMILY_MEMBER";
    final private static String PROFILE_IMAGE = "PROFILE_IMAGE";
    final private static String EMAIL = "EMAIL";


    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getPrefsEditor(Context context) {
        return getPrefs(context).edit();
    }

    public static void saveUserId(Context context, String userid) {
        SharedPreferences.Editor prefsEditor = getPrefsEditor(context);
        prefsEditor.putString(USER_ID, userid);
        prefsEditor.commit();
    }

    public static String getUserId(Context context) {
        SharedPreferences mPrefs = getPrefs(context);
        return mPrefs.getString(USER_ID, null);
    }

    public static void saveUserFName(Context context, String userFName) {
        SharedPreferences.Editor prefsEditor = getPrefsEditor(context);
        prefsEditor.putString(USER_F_NAME, userFName);
        prefsEditor.commit();
    }

    public static String getUserFName(Context context) {
        SharedPreferences mPrefs = getPrefs(context);
        return mPrefs.getString(USER_F_NAME, "");
    }

    public static String getUserLName(Context context) {
        SharedPreferences mPrefs = getPrefs(context);
        return mPrefs.getString(USER_L_NAME, "");
    }

    public static void saveUserLName(Context context, String UserLName) {
        SharedPreferences.Editor prefsEditor = getPrefsEditor(context);
        prefsEditor.putString(USER_L_NAME, UserLName);
        prefsEditor.commit();
    }

    public static void saveFullName(Context context, String fullname) {
        SharedPreferences.Editor prefsEditor = getPrefsEditor(context);
        prefsEditor.putString(FULL_NAME, fullname);
        prefsEditor.commit();
    }

    public static String getFullName(Context context) {
        SharedPreferences mPrefs = getPrefs(context);
        return mPrefs.getString(FULL_NAME, "");
    }

    public static void saveProfileImage(Context context, String profileimage) {
        SharedPreferences.Editor prefsEditor = getPrefsEditor(context);
        prefsEditor.putString(PROFILE_IMAGE, profileimage);
        prefsEditor.commit();
    }

    public static String getProfileImage(Context context) {
        SharedPreferences mPrefs = getPrefs(context);
        return mPrefs.getString(PROFILE_IMAGE, "");
    }


    public static String getMobileNo(Context context) {
        SharedPreferences mPrefs = getPrefs(context);
        return mPrefs.getString(MOBILE_NO, "");
    }

    public static void saveMobileNo(Context context, String MobileNo) {
        SharedPreferences.Editor prefsEditor = getPrefsEditor(context);
        prefsEditor.putString(MOBILE_NO, MobileNo);
        prefsEditor.commit();
    }

    public static String getHouseNo(Context context) {
        SharedPreferences mPrefs = getPrefs(context);
        return mPrefs.getString(HOUSE_NO, "");
    }

    public static void saveHouseNo(Context context, String HouseNo) {
        SharedPreferences.Editor prefsEditor = getPrefsEditor(context);
        prefsEditor.putString(HOUSE_NO, HouseNo);
        prefsEditor.commit();
    }

    public static String getFamilyMember(Context context) {
        SharedPreferences mPrefs = getPrefs(context);
        return mPrefs.getString(FAMILY_MEMBER, "");
    }

    public static void saveFamilyMember(Context context, String familyMember) {
        SharedPreferences.Editor prefsEditor = getPrefsEditor(context);
        prefsEditor.putString(FAMILY_MEMBER, familyMember);
        prefsEditor.commit();
    }

    public static String getEmail(Context context) {
        SharedPreferences mPrefs = getPrefs(context);
        return mPrefs.getString(EMAIL, "");
    }
    public static void saveEmail(Context context, String Email) {
        SharedPreferences.Editor prefsEditor = getPrefsEditor(context);
        prefsEditor.putString(EMAIL, Email);
        prefsEditor.commit();
    }
}
