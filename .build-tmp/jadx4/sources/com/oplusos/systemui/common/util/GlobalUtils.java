package com.oplusos.systemui.common.util;

import android.graphics.Rect;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultRegistry$;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GlobalUtils {
    public static final GlobalUtils INSTANCE = new GlobalUtils();
    private static final String TAG = "GlobalUtils";
    public static boolean mayInterceptShadeTouchEvent = false;
    public static volatile boolean panelClosedOnDown = true;
    public static int sDialogShowingCount;
    public static boolean sIsMediaOutputFromOutside;

    public static final void genCancelMotionEvent(Function1 function1) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        motionEventObtain.setSource(4098);
        function1.invoke(motionEventObtain);
        motionEventObtain.recycle();
    }

    public static final boolean getAndResetInterceptShadeTouchEvent() {
        boolean z = mayInterceptShadeTouchEvent;
        mayInterceptShadeTouchEvent = false;
        return z;
    }

    public static final boolean hasDialogShowing() {
        return sDialogShowingCount > 0;
    }

    public static final boolean isTouchInView(MotionEvent motionEvent, View view) {
        if (view == null) {
            return false;
        }
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2).contains(rawX, rawY);
    }

    public static final void sendCancelMotionEvent(ViewGroup viewGroup, String str) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        motionEventObtain.setSource(4098);
        if (viewGroup != null) {
            viewGroup.dispatchTouchEvent(motionEventObtain);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                StringBuilder sbM = ActivityResultRegistry$.ExternalSyntheticOutline0.m("sendCancelMotionEvent reason: ", str, ", handle view: ");
                sbM.append(viewGroup.getClass().getSimpleName());
                Log.d("SystemUi--Notification", "GlobalUtils-->".concat(sbM.toString()));
            }
        }
        motionEventObtain.recycle();
    }

    public static final void updateDialogShowing(boolean z, boolean z2) {
        int i = sDialogShowingCount;
        if (i == 0 && z && z2) {
            mayInterceptShadeTouchEvent = true;
        } else {
            mayInterceptShadeTouchEvent = false;
        }
        if (z) {
            sDialogShowingCount = i + 1;
        } else {
            int i2 = i - 1;
            sDialogShowingCount = i2 >= 0 ? i2 : 0;
        }
    }
}
