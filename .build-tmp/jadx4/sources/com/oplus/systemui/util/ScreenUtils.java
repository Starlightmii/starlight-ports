package com.oplus.systemui.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.SystemProperties;
import android.view.View;
import android.view.Window;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScreenUtils {
    public static final ScreenUtils INSTANCE = new ScreenUtils();
    private static final String PERSIST_SYS_DISPLAY_DENSITY = "persist.sys.display.density";
    private static final String TAG = "ScreenUtils";

    public static final int dip2px(Context context, float f) {
        return (int) (dip2pxFloat(context, f) + 0.5f);
    }

    public static final float dip2pxFloat(Context context, float f) {
        return f * context.getResources().getDisplayMetrics().density;
    }

    public static final float getFixedDisplayDensity() {
        return getFixedDisplayDensityDpi() / 160.0f;
    }

    public static final int getFixedDisplayDensityDpi() {
        KeyguardUtils.Companion.getClass();
        return KeyguardUtils.Companion.getInitialDisplayDensity(false);
    }

    public static final int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", OplusAnimationController.PACKAGE_SYSTEM);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static final float getVariableDisplayDensity() {
        int variableDisplayDensityDpi = getVariableDisplayDensityDpi();
        if (variableDisplayDensityDpi == -1) {
            variableDisplayDensityDpi = getFixedDisplayDensityDpi();
        }
        return variableDisplayDensityDpi / 160.0f;
    }

    public static final int getVariableDisplayDensityDpi() {
        return SystemProperties.getInt(PERSIST_SYS_DISPLAY_DENSITY, -1);
    }

    public static final void initCommonBaseWindow(Activity activity) {
        Window window = activity.getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.getDecorView().setSystemUiVisibility(1280);
        window.setStatusBarColor(0);
        View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 16);
    }

    public static final int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static final void setDefaultDisplay(Context context) {
        if (context.equals(context.getApplicationContext())) {
            LogUtil.w$default(8, LogUtil.TAG_COMMON, TAG, "setDefaultDisplay forbidden, applicationContext can not fixed.......");
            return;
        }
        Configuration configuration = context.getResources().getConfiguration();
        int fixedDisplayDensityDpi = getFixedDisplayDensityDpi();
        if (fixedDisplayDensityDpi != -1) {
            configuration.densityDpi = fixedDisplayDensityDpi;
            context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
        }
    }
}
