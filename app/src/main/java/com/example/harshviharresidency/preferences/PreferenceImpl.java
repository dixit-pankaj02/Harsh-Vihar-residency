package com.example.harshviharresidency.preferences;

import android.content.Context;

import androidx.annotation.Nullable;


public class PreferenceImpl implements IStorage {
    private static IStorage preference;
    private Context mContext;

    public void init(Context context) {
        mContext = context;
    }

    private PreferenceImpl() {

    }

    public static IStorage getInstance() {
        if (preference == null) {
            preference = new PreferenceImpl();
        }
        return preference;
    }

    @Override
    public String getUserFName() {
        return PreferenceUtil.getUserFName(mContext);
    }

    @Override
    public void setUserFName(@Nullable String userFName) {
        PreferenceUtil.saveUserFName(mContext, userFName);
    }

    @Override
    public String getUserLName() {
        return PreferenceUtil.getUserLName(mContext);
    }

    @Override
    public void setUserLName(@Nullable String userLName) {
        PreferenceUtil.saveUserLName(mContext, userLName);
    }

    @Override
    public String getUserId() {
        return PreferenceUtil.getUserId(mContext);
    }

    @Override
    public void setUserId(@Nullable String userId) {
        PreferenceUtil.saveUserId(mContext, userId);
    }

    @Override
    public String getFullName() {
        return PreferenceUtil.getFullName(mContext);
    }

    @Override
    public void setFullName(@Nullable String fullName) {
        PreferenceUtil.saveFullName(mContext, fullName);
    }

    @Override
    public String getMobileNo() {
        return PreferenceUtil.getMobileNo(mContext);
    }

    @Override
    public void setMobileNo(@Nullable String mobileNo) {
        PreferenceUtil.saveMobileNo(mContext, mobileNo);
    }

    @Override
    public String getHouseNo() {
        return PreferenceUtil.getHouseNo(mContext);
    }

    @Override
    public void setHouseNo(@Nullable String houseNo) {
        PreferenceUtil.saveHouseNo(mContext, houseNo);
    }

    @Override
    public String getFamilyMember() {
        return PreferenceUtil.getFamilyMember(mContext);
    }

    @Override
    public void setFamilyMember(@Nullable String familyMember) {
        PreferenceUtil.saveFamilyMember(mContext, familyMember);
    }

    @Override
    public String getEmail() {
        return PreferenceUtil.getEmail(mContext);
    }

    @Override
    public void setEmail(@Nullable String email) {
        PreferenceUtil.saveEmail(mContext, email);
    }

    @Override
    public String getProfileImage() {
        return PreferenceUtil.getProfileImage(mContext);
    }

    @Override
    public void setProfileImage(@Nullable String profileImage) {
        PreferenceUtil.saveProfileImage(mContext, profileImage);
    }

}
