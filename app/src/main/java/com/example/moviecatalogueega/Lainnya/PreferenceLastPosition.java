package com.example.moviecatalogueega.Lainnya;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceLastPosition {
    private final String PREFS_NAME = "last_adapter_position";
    private final String keyPosition = "position";
    private final String keyOffset = "offset";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    public PreferenceLastPosition(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }

    public int getPosition() {
        return mSharedPreferences.getInt(keyPosition, 0);
    }

    public void setPosition(int position) {
        editor.putInt(keyPosition, position);
        editor.commit();
    }

    public int getOffset() {
        return mSharedPreferences.getInt(keyOffset, 0);
    }

    public void setOffset(int offset) {
        editor.putInt(keyOffset, offset);
        editor.commit();
    }




}
