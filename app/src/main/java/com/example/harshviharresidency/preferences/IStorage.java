package com.example.harshviharresidency.preferences;


import androidx.annotation.Nullable;

public interface IStorage {
    String getUserFName();

    void setUserFName(@Nullable String userFName);

    String getUserLName();

    void setUserLName(@Nullable String userLName);

    String getUserId();

    void setUserId(@Nullable String userId);

    String getFullName();

    void setFullName(@Nullable String fullName);

    String getMobileNo();

    void setMobileNo(@Nullable String mobileNo);

    String getHouseNo();

    void setHouseNo(@Nullable String houseNo);

    String getFamilyMember();

    void setFamilyMember(@Nullable String familyMember);

    String getEmail();

    void setEmail(@Nullable String email);

    String getProfileImage();

    void setProfileImage(@Nullable String profileImage);
    class Factory {
        public static IStorage get() {
            return PreferenceImpl.getInstance();
        }

    }

}
