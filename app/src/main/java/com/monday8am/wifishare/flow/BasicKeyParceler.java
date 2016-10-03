package com.monday8am.wifishare.flow;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import flow.KeyParceler;

/**
 * Created by anton on 03/10/16.
 */

public class BasicKeyParceler implements KeyParceler {


    @NonNull
    @Override
    public Parcelable toParcelable(@NonNull Object key) {
        return (Parcelable) key;
    }

    @NonNull
    @Override
    public Object toKey(@NonNull Parcelable parcelable) {
        return parcelable;
    }
}
