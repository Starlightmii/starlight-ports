package com.oplus.systemui.subdisplay;

import android.app.ActivityOptions;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.DisplayInfo;
import android.view.View;
import android.view.ViewRootImpl;
import android.view.WindowManager;
import com.android.keyguard.base.OplusKeyguardCtrl$;
import com.oplus.systemui.aod.aodclock.off.BaseRootLayout;
import com.oplus.systemui.subdisplay.aod.SubDisplayAodRootLayout;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.util.Iterator;
import kotlin.jvm.internal.ArrayIteratorKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSubDisplayUtils {
    public static final Companion Companion = new Companion();
    private static final String DISPLAY_CATEGORY_ALL_INCLUDING_DISABLED = "android.hardware.display.category.ALL_INCLUDING_DISABLED";
    private static final String TAG = "SubDisplayUtils";
    private static volatile OplusSubDisplayUtils instance;
    public boolean isAdd;
    private final Context mContext;
    private Display mSecondaryDisplay;
    private WindowManager mWindowManager;
    private BaseRootLayout subDisplayAodRoot;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusSubDisplayUtils getInstance(Context context) {
            OplusSubDisplayUtils oplusSubDisplayUtils;
            OplusSubDisplayUtils oplusSubDisplayUtils2 = OplusSubDisplayUtils.instance;
            if (oplusSubDisplayUtils2 != null) {
                return oplusSubDisplayUtils2;
            }
            synchronized (this) {
                oplusSubDisplayUtils = OplusSubDisplayUtils.instance;
                if (oplusSubDisplayUtils == null) {
                    oplusSubDisplayUtils = new OplusSubDisplayUtils(context);
                    OplusSubDisplayUtils.instance = oplusSubDisplayUtils;
                }
            }
            return oplusSubDisplayUtils;
        }
    }

    public OplusSubDisplayUtils(Context context) {
        this.mContext = context;
    }

    public final boolean getBaseSubDisplayShow() {
        return getSupportSubDisplay() && DeviceStateController.getInstance().isFolding();
    }

    public final BaseRootLayout getInitSubDisplayAodRootView() {
        ViewRootImpl viewRootImpl;
        if (getSubDisplayContext() == null) {
            return null;
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.subDisplayAodRoot = new SubDisplayAodRootLayout(getSubDisplayContext());
        layoutParams.setTitle("NotificationShade");
        layoutParams.type = 2038;
        layoutParams.format = -2;
        layoutParams.screenOrientation = 7;
        Context subDisplayContext = getSubDisplayContext();
        WindowManager windowManager = subDisplayContext != null ? (WindowManager) subDisplayContext.getSystemService(WindowManager.class) : null;
        this.mWindowManager = windowManager;
        if (windowManager != null) {
            windowManager.addView(this.subDisplayAodRoot, layoutParams);
        }
        BaseRootLayout baseRootLayout = this.subDisplayAodRoot;
        if (baseRootLayout != null) {
            baseRootLayout.removeCallbacks(baseRootLayout.mHideRunnable);
        }
        Context context = this.mContext;
        if (context != null) {
            int color = context.getColor(2131099842);
            BaseRootLayout baseRootLayout2 = this.subDisplayAodRoot;
            if (baseRootLayout2 != null) {
                baseRootLayout2.setBackgroundColor(color);
            }
        }
        BaseRootLayout baseRootLayout3 = this.subDisplayAodRoot;
        if (baseRootLayout3 != null) {
            baseRootLayout3.setVisibility(0);
        }
        BaseRootLayout baseRootLayout4 = this.subDisplayAodRoot;
        if (baseRootLayout4 != null && (viewRootImpl = baseRootLayout4.getViewRootImpl()) != null) {
            viewRootImpl.setReportNextDraw(false, "subaod");
        }
        synchronized (this) {
            this.isAdd = true;
        }
        return this.subDisplayAodRoot;
    }

    public final Display getSecondaryDisplay() {
        Display display = this.mSecondaryDisplay;
        if (display != null) {
            return display;
        }
        Context context = this.mContext;
        if (context == null) {
            return null;
        }
        DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
        Display display2 = displayManager.getDisplay(0);
        Iterator it = ArrayIteratorKt.iterator(displayManager.getDisplays(DISPLAY_CATEGORY_ALL_INCLUDING_DISABLED));
        while (it.hasNext()) {
            Display display3 = (Display) it.next();
            if (display3.getDisplayId() != 0 && display3.getType() == display2.getType()) {
                this.mSecondaryDisplay = display3;
                return display3;
            }
        }
        return null;
    }

    public final int getSecondaryDisplayHeight(float f) {
        int i = (int) (720.0f * f);
        if (getSecondaryDisplay() == null) {
            return i;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display secondaryDisplay = getSecondaryDisplay();
        if (secondaryDisplay != null) {
            secondaryDisplay.getRealMetrics(displayMetrics);
        }
        OplusKeyguardCtrl$.ExternalSyntheticOutline0.m("displayMetrics.heightPixels:", displayMetrics.heightPixels, displayMetrics.widthPixels, " displayMetrics.widthPixels:", TAG);
        int i2 = displayMetrics.heightPixels;
        int i3 = displayMetrics.widthPixels;
        return i2 < i3 ? i3 : i2;
    }

    public final int getSecondaryDisplayId() {
        Display secondaryDisplay = getSecondaryDisplay();
        if (secondaryDisplay != null) {
            return secondaryDisplay.getDisplayId();
        }
        return 0;
    }

    public final DisplayInfo getSecondaryDisplayInfo(DisplayInfo displayInfo) {
        Display secondaryDisplay = getSecondaryDisplay();
        if (secondaryDisplay != null) {
            secondaryDisplay.getDisplayInfo(displayInfo);
        }
        return displayInfo;
    }

    public final Integer getSecondaryDisplayState() {
        Display secondaryDisplay = getSecondaryDisplay();
        if (secondaryDisplay != null) {
            return Integer.valueOf(secondaryDisplay.getState());
        }
        return null;
    }

    public final int getSecondaryDisplayWidth(float f) {
        int i = (int) (360.0f * f);
        if (getSecondaryDisplay() == null) {
            return i;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display secondaryDisplay = getSecondaryDisplay();
        if (secondaryDisplay != null) {
            secondaryDisplay.getRealMetrics(displayMetrics);
        }
        OplusKeyguardCtrl$.ExternalSyntheticOutline0.m("displayMetrics.heightPixels:", displayMetrics.heightPixels, displayMetrics.widthPixels, " displayMetrics.widthPixels:", TAG);
        return displayMetrics.widthPixels;
    }

    public final BaseRootLayout getSubDisplayAodRoot() {
        return this.subDisplayAodRoot;
    }

    public final Context getSubDisplayContext() {
        Display secondaryDisplay;
        Context contextCreateDisplayContext;
        if (this.mContext == null || (secondaryDisplay = getSecondaryDisplay()) == null || (contextCreateDisplayContext = this.mContext.createDisplayContext(secondaryDisplay)) == null) {
            return null;
        }
        return contextCreateDisplayContext.createWindowContext(2038, null);
    }

    public final boolean getSubDisplayShow() {
        return getSupportSubDisplay() && DeviceStateController.getInstance().isFolding();
    }

    public final boolean getSupportSubDisplay() {
        FeatureOption.INSTANCE.getClass();
        return FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled();
    }

    public final void removeRootWindow() {
        View view = this.subDisplayAodRoot;
        if (view == null || !this.isAdd) {
            return;
        }
        WindowManager windowManager = this.mWindowManager;
        if (windowManager != null) {
            windowManager.removeViewImmediate(view);
        }
        synchronized (this) {
            this.isAdd = false;
        }
    }

    public final void setLaunchDisplayIdIfNeeded(ActivityOptions activityOptions) {
        if (getSupportSubDisplay()) {
            activityOptions.setLaunchDisplayId(0);
        }
    }
}
