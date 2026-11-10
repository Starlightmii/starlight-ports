package com.oplus.tingle.ipc.internal;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.SystemProperties;
import android.provider.Settings;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSHighlightTileViewLabelTextHandler;
import com.oplus.tingle.Constants;
import com.oplus.tingle.ipc.Slave;
import com.oplus.tingle.ipc.serviceproxy.app.ActivityManagerProxy;
import com.oplus.tingle.ipc.serviceproxy.app.NotificationManagerProxy;
import com.oplus.tingle.ipc.serviceproxy.inner.WindowManagerInnerProxy;
import com.oplus.tingle.ipc.serviceproxy.net.wifi.WifiManagerProxy;
import com.oplus.tingle.ipc.serviceproxy.pm.PackageManagerProxy;
import com.oplus.tingle.ipc.serviceproxy.view.WindowManagerProxy;
import com.oplus.tingle.ipc.utils.Logger;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class TingleProvider extends ContentProvider {
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
        if (!Logger.sInitialized.getAndSet(true)) {
            if (context == null || context.getContentResolver() == null) {
                Logger.sDEBUG = false;
            } else {
                if (Constants.getAppPlatformPackageName().equals(context.getPackageName())) {
                    context.getContentResolver().registerContentObserver(Settings.System.getUriFor("log_switch_type"), false, new Logger.LoggerObserver(null));
                }
                Logger.sDEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
            }
        }
        Context context2 = getContext();
        if (!context2.getApplicationInfo().packageName.equals(Constants.getAppPlatformPackageName())) {
            IBinder iBinder = Slave.sMaster;
            if (context2 instanceof Application) {
                Slave.mApp = (Application) context2;
            } else {
                Slave.mApp = (Application) context2.getApplicationContext();
            }
            List list = Slave.sProxy;
            ActivityManagerProxy activityManagerProxy = new ActivityManagerProxy();
            activityManagerProxy.mServiceName = "activity";
            CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) list;
            copyOnWriteArrayList.add(activityManagerProxy);
            PackageManagerProxy packageManagerProxy = new PackageManagerProxy();
            packageManagerProxy.mServiceName = "package";
            copyOnWriteArrayList.add(packageManagerProxy);
            NotificationManagerProxy notificationManagerProxy = new NotificationManagerProxy();
            notificationManagerProxy.mServiceName = "notification";
            copyOnWriteArrayList.add(notificationManagerProxy);
            WindowManagerProxy windowManagerProxy = new WindowManagerProxy();
            windowManagerProxy.mServiceName = "window";
            windowManagerProxy.mShouldUseLock = false;
            copyOnWriteArrayList.add(windowManagerProxy);
            WifiManagerProxy wifiManagerProxy = new WifiManagerProxy();
            wifiManagerProxy.mServiceName = OplusQSHighlightTileViewLabelTextHandler.WIFI;
            copyOnWriteArrayList.add(wifiManagerProxy);
            WindowManagerInnerProxy windowManagerInnerProxy = new WindowManagerInnerProxy();
            windowManagerInnerProxy.mServiceName = "windowInner";
            windowManagerInnerProxy.mShouldUseLock = false;
            copyOnWriteArrayList.add(windowManagerInnerProxy);
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
