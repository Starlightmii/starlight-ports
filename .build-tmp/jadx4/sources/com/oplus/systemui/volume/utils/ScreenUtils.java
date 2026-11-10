package com.oplus.systemui.volume.utils;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.android.internal.policy.SystemBarUtils;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScreenUtils {
    public static final ScreenUtils INSTANCE = new ScreenUtils();

    public static final Rect getDraggableBounds(Context context, int i) {
        return new Rect(0, getStatusBarHeight(context), getScreenWidth(context) - i, (getScreenHeight(context) - i) - getNavigationBarHeight(context));
    }

    public static final int getNavigationBarHeight(Context context) {
        return NavBarUtils.getNavigationBarHeight(context);
    }

    public static final int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static final int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static final int getStatusBarHeight(Context context) {
        return SystemBarUtils.getStatusBarHeight(context);
    }

    public static final Rect getStickableBounds(Context context, int i, int i2) {
        return new Rect(i2, getStatusBarHeight(context), (getScreenWidth(context) - i) - i2, (getScreenHeight(context) - i) - getNavigationBarHeight(context));
    }
}
