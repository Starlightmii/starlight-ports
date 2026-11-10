package com.oplus.tingle.ipc;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.oplus.tingle.Constants;
import com.oplus.tingle.ipc.cursor.ProviderCursor;
import com.oplus.tingle.ipc.utils.Logger;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class Slave {
    public static Application mApp;
    public static IBinder sMaster;
    public static final List sProxy = new CopyOnWriteArrayList();

    public static boolean getMaster(Context context) {
        IBinder binder;
        try {
            Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://" + Constants.getMasterProviderUri()), null, null, null);
            try {
                if (cursorQuery == null) {
                    Logger.e("Engine", "Get query cursor null.", new Object[0]);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    binder = null;
                } else {
                    String[] strArr = ProviderCursor.DEFAULT_COLUMNS;
                    Bundle extras = cursorQuery.getExtras();
                    if (extras != null) {
                        binder = extras.getBinder("IBinder");
                    } else {
                        Logger.e("ProviderCursor", "Get cursor bundle null.", new Object[0]);
                        binder = null;
                    }
                    cursorQuery.close();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (cursorQuery != null) {
                        try {
                            cursorQuery.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        } catch (Exception e) {
            Logger.e("Engine", "Close cursor failed : " + e.toString(), new Object[0]);
        }
        if (binder == null) {
            Bundle bundleCall = context.getContentResolver().call(Constants.getMasterProviderUri(), "sendBinder", (String) null, (Bundle) null);
            if (bundleCall != null) {
                binder = bundleCall.getBinder(Constants.getExtraBinder());
            } else {
                Logger.e("Engine", "Get Master IBinder from provider call is null", new Object[0]);
                binder = null;
            }
        }
        if (binder != null) {
            try {
                binder.linkToDeath(new Slave$.ExternalSyntheticLambda0(), 0);
            } catch (RemoteException e2) {
                Logger.e("Slave", "GetMaster linkToDeath Error : " + e2, new Object[0]);
            }
            sMaster = binder;
        } else {
            sMaster = null;
            Logger.e("Slave", "Get Binder is null, reset sMaster = null.", new Object[0]);
        }
        return sMaster != null;
    }
}
