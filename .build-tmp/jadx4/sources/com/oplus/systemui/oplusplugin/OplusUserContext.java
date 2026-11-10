package com.oplus.systemui.oplusplugin;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$;
import androidx.fragment.app.Fragment$5$;
import com.android.systemui.plugins.statusbar.UserProxy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusUserContext implements UserProxy {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OplusUserContext";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ContentProviderClient acquireUnstableContentProviderClient(Context context, int i, String str) {
        String authorityWithoutUserId = ContentProvider.getAuthorityWithoutUserId(str);
        String str2 = i + "@" + authorityWithoutUserId;
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("acquireUnstableContentProviderClient, originAuthority ", str, " authorityWithoutUserId ", authorityWithoutUserId, " authorityWithUserId ");
        sbM.append(str2);
        sbM.append(" UserContextImp ");
        sbM.append(context);
        sbM.append(" userId ");
        sbM.append(i);
        Log.d(TAG, sbM.toString());
        return context.getContentResolver().acquireUnstableContentProviderClient(str2);
    }

    public void bindService(Context context, int i, Intent intent, ServiceConnection serviceConnection) {
        context.bindServiceAsUser(intent, serviceConnection, -1, UserHandle.of(i));
    }

    public UserHandle getUserHandle(int i) {
        Log.d(TAG, "getUserHandle userId " + i);
        return UserHandle.of(i);
    }

    public void notifyChange(Context context, int i, Uri uri, ContentObserver contentObserver, int i2) {
        Log.d(TAG, "notifyChange uri " + uri + " UserContextImp " + context + " userId " + i + " observer " + contentObserver + " flags " + i2);
        context.getContentResolver().notifyChange(uri, contentObserver, i2, i);
    }

    public void registerContentObserver(Context context, int i, Uri uri, boolean z, ContentObserver contentObserver) {
        context.getContentResolver().registerContentObserver(uri, z, contentObserver, i);
        StringBuilder sb = new StringBuilder("registerContentObserver uri ");
        sb.append(uri);
        sb.append(" UserContextImp ");
        sb.append(context);
        sb.append(" userId ");
        Fragment$5$.ExternalSyntheticOutline0.m(i, TAG, sb);
    }

    public void registerReceiver(Context context, int i, BroadcastReceiver broadcastReceiver) {
        context.registerReceiverAsUser(broadcastReceiver, UserHandle.of(i), null, null, null);
    }

    public void sendBroadcast(Context context, int i, Intent intent) {
        context.sendBroadcastAsUser(intent, UserHandle.of(i));
    }

    public void startActivity(Context context, int i, Intent intent) {
        context.startActivityAsUser(intent, UserHandle.of(i));
    }

    public boolean startActivityFromTask(Context context, int i, int i2, Bundle bundle) {
        try {
            return ActivityManager.isStartResultSuccessful(ActivityTaskManager.getService().startActivityFromRecents(i2, bundle));
        } catch (Exception unused) {
            return false;
        }
    }

    public void startService(Context context, int i, Intent intent) {
        context.startServiceAsUser(intent, UserHandle.of(i));
    }

    public void unbindService(Context context, int i, ServiceConnection serviceConnection) {
        context.unbindService(serviceConnection);
    }

    public void unregisterContentObserver(Context context, int i, ContentObserver contentObserver) {
        Log.d(TAG, "registerContentObserver UserContextImp " + context + " userId " + i);
        context.getContentResolver().unregisterContentObserver(contentObserver);
    }

    public void unregisterReceiver(Context context, int i, BroadcastReceiver broadcastReceiver) {
        context.unregisterReceiver(broadcastReceiver);
    }

    public void startActivity(Context context, int i, Intent intent, Bundle bundle) {
        context.startActivityAsUser(intent, bundle, UserHandle.of(i));
    }

    public void bindService(Context context, int i, Intent intent, ServiceConnection serviceConnection, int i2) {
        context.bindServiceAsUser(intent, serviceConnection, i2, UserHandle.of(i));
    }

    public void registerReceiver(Context context, int i, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        context.registerReceiverAsUser(broadcastReceiver, UserHandle.of(i), intentFilter, null, null);
    }

    public void notifyChange(Context context, int i, Uri uri, ContentObserver contentObserver) {
        Log.d(TAG, "notifyChange uri " + uri + " UserContextImp " + context + " userId " + i + " observer " + contentObserver);
        context.getContentResolver().notifyChange(uri, contentObserver, true, i);
    }

    public ContentProviderClient acquireUnstableContentProviderClient(Context context, int i, Uri uri) {
        Uri uriMaybeAddUserId = ContentProvider.maybeAddUserId(uri, i);
        Log.d(TAG, "acquireUnstableContentProviderClient uri " + uri + " userUri " + uriMaybeAddUserId + " UserContextImp " + context + " userId " + i);
        return context.getContentResolver().acquireUnstableContentProviderClient(uriMaybeAddUserId);
    }
}
