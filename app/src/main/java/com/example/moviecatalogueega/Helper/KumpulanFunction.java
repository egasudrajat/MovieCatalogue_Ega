package com.example.moviecatalogueega.Helper;

import android.content.Context;
import android.content.Intent;

public class KumpulanFunction {

    public static double hitungvolume(double pjg, double lbr, double tnggi) {
        return pjg * lbr * tnggi;
    }

    public static void PindahIntent(Context context, Class<?> kelas){
        Intent intent = new Intent(context, kelas);
        context.startActivity(intent);
    }
}
