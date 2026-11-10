package io.github.libxposed.service;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class XposedProvider extends ContentProvider {
    private static final String TAG = "XposedProvider";

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        if (!str.equals(IXposedService.SEND_BINDER) || bundle == null) {
            return null;
        }
        IBinder binder = bundle.getBinder("binder");
        if (binder != null) {
            Log.d(TAG, "binder received: " + binder);
            XposedServiceHelper.onBinderReceived(binder);
        }
        return new Bundle();
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Context context = getContext();
        Objects.requireNonNull(context);
        int i5 = context.getApplicationInfo().targetSdkVersion;
        if (Build.VERSION.SDK_INT >= 30 && i5 >= 30) {
            RemotePreferences.shouldNotifyCleared = true;
        }
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
