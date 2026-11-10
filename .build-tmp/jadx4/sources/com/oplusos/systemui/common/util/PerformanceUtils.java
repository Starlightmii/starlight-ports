package com.oplusos.systemui.common.util;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ServiceManager;
import android.util.Log;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PerformanceUtils {
    public static final PerformanceUtils INSTANCE = new PerformanceUtils();
    private static final String ISURFACE = "android.ui.ISurfaceComposer";
    private static final String TAG = "PerformanceUtils";

    public static final void notifyAnimationNotification() {
        notifySFAnimating(400);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "PerformanceUtils-->notifyAnimationNotification notifySFAnimating done");
        }
    }

    public static final void notifySFAnimating(int i) {
        Object obj;
        IBinder service = ServiceManager.getService("SurfaceFlinger");
        if (service == null) {
            return;
        }
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken(ISURFACE);
            if (i <= 0) {
                parcelObtain.writeInt(400);
            } else {
                parcelObtain.writeInt(i);
            }
            obj = Result.constructor-impl(Boolean.valueOf(service.transact(21020, parcelObtain, null, 1)));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.e$default(8, LogUtil.TAG_COMMON, TAG, "notifySFAnimating error: " + th2);
        }
        parcelObtain.recycle();
    }
}
