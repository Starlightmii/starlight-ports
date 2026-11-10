package com.oplus.systemui.navigationbar.utils;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.Debug;
import android.os.Trace;
import android.util.Log;
import android.util.Size;
import android.view.Display;
import android.view.IWindowManager;
import android.view.WindowManager;
import android.view.WindowMetrics;
import androidx.collection.FloatList$;
import com.oplusos.systemui.common.feature.FeatureOption;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavBarScreenSizeHelper {
    private static final String TAG = "ScreenSizeHelper";
    private static volatile NavBarScreenSizeHelper instance;
    private final Context ctx;
    private final DisplayManager displayManager;
    private WindowManager mWindowManager;
    private Size screenSize = new Size(0, 0);
    public static final Companion Companion = new Companion();
    private static final Lazy iWindowManager$delegate = LazyKt__LazyJVMKt.lazy(new NavBarScreenSizeHelper$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        private final IWindowManager getIWindowManager() {
            return (IWindowManager) NavBarScreenSizeHelper.iWindowManager$delegate.getValue();
        }

        public final NavBarScreenSizeHelper getInstance(Context context) {
            if (NavBarScreenSizeHelper.instance == null) {
                synchronized (NavBarScreenSizeHelper.class) {
                    if (NavBarScreenSizeHelper.instance == null) {
                        NavBarScreenSizeHelper.instance = new NavBarScreenSizeHelper(context);
                    }
                }
            }
            return NavBarScreenSizeHelper.instance;
        }
    }

    public NavBarScreenSizeHelper(Context context) {
        this.ctx = context;
        this.displayManager = (DisplayManager) context.getSystemService("display");
        this.mWindowManager = (WindowManager) context.getSystemService("window");
    }

    public static final NavBarScreenSizeHelper getInstance(Context context) {
        return Companion.getInstance(context);
    }

    public final Context getCtx() {
        return this.ctx;
    }

    public final Rect getScreenRect(Context context) {
        Rect rect;
        WindowMetrics currentWindowMetrics;
        Trace.traceBegin(8L, "getScreenRect");
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isSupportWmSizeResolutionSwitch()) {
            Log.i(TAG, "isSupportWmSizeResolutionSwitch = true");
            WindowManager windowManager = this.mWindowManager;
            Rect bounds = (windowManager == null || (currentWindowMetrics = windowManager.getCurrentWindowMetrics()) == null) ? null : currentWindowMetrics.getBounds();
            rect = new Rect(0, 0, bounds != null ? bounds.width() : -1, bounds != null ? bounds.height() : -1);
        } else {
            rect = new Rect(0, 0, context.getDisplay().getMode().getPhysicalWidth(), context.getDisplay().getMode().getPhysicalHeight());
        }
        int iWidth = rect.width();
        int iHeight = rect.height();
        int rotation = context.getDisplay().getRotation();
        String caller = Debug.getCaller();
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("getScreenRect: ", iWidth, iHeight, "x", ", rotation: ");
        sbM.append(rotation);
        sbM.append(", stack: ");
        sbM.append(caller);
        Log.i(TAG, sbM.toString());
        Trace.traceEnd(8L);
        return rect;
    }

    public final Size getScreenSize() {
        return this.screenSize;
    }

    public final boolean isTablet(Context context) {
        Rect screenRect = getScreenRect(context);
        return ((float) Math.min(screenRect.width(), screenRect.height())) / (((float) context.getResources().getConfiguration().densityDpi) / 160.0f) >= 550.0f;
    }

    public final void setScreenSize(Size size) {
        this.screenSize = size;
    }

    public final void updateScreenSize(Context context) {
        Display display;
        Point point = new Point();
        DisplayManager displayManager = this.displayManager;
        if (displayManager != null && (display = displayManager.getDisplay(context.getDisplayId())) != null) {
            display.getRealSize(point);
        }
        setScreenSize(new Size(point.x, point.y));
    }
}
