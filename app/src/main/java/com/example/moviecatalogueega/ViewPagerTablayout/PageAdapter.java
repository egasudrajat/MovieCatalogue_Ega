package com.example.moviecatalogueega.ViewPagerTablayout;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.moviecatalogueega.R;

public class PageAdapter extends FragmentPagerAdapter {
    private final Context mcontext;

    PageAdapter(@NonNull Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mcontext = context;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Tab1_Fragment();
                break;
            case 1:
                fragment = new Tab2_Fragment();
                break;
            case 2:
                fragment = new Tab3_Fragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.Tab1,
            R.string.Tab2,
            R.string.Tab3
    };

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mcontext.getResources().getString(TAB_TITLES[position]);
    }
}
