package com.oplus.systemui.statusbar.notification.keymagicservice;

import android.app.StatusBarManager;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class KeyBaseController {
    public static final Companion Companion = new Companion();
    public static final String MAGIC_KEY_APP_NAME = "app_name";
    public static final String MAGIC_KEY_FLUID_CLOUD_TEXT = "fluid_cloud_text";
    public static final String MAGIC_KEY_LONG_PRESS_ERR_TEXT = "long_press_err_text";
    public static final String MAGIC_KEY_ONLY_SHOE_SIDE_ANIM = "only_show_side_anim";
    public static final String MAGIC_KEY_RES_ID = "icon_res_id";
    public static final String MAGIC_KEY_RES_NAME = "res_name";
    public static final String MAGIC_KEY_SIDE_TEXT = "side_text";
    public static final String MAGIC_KEY_START_RESULT = "startResult";
    private static final String TAG = "KeyBaseController";
    public static final String VALUE_APP_NAME = "com.android.systemui";
    public static final String VALUE_ONLY_SHOE_SIDE_ANIM = "true";
    public static final String VALUE_START_RESULT_CLOSE = "close";
    public static final String VALUE_START_RESULT_ERROR = "err";
    public static final String VALUE_START_RESULT_REPLY = "reply";
    private final Context context;
    private final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final Handler mainHandler = SystemUIThreadUtil.getMainHandler();
    private PowerManager powerManager;
    private final Lazy shadeController;
    private final StatusBarManager statusBarManager;

    public KeyBaseController(Context context, KeyguardUpdateMonitor keyguardUpdateMonitor, Lazy lazy) {
        this.context = context;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.shadeController = lazy;
        this.powerManager = (PowerManager) context.getSystemService("power");
        this.statusBarManager = (StatusBarManager) context.getSystemService("statusbar");
    }

    public final void dissmissNotificationCenter() {
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(this.context.getUserId(), "dissmissNotificationCenter, context.userid="), null, 8);
        Runnable runnable = new dissmissNotificationCenter.1();
        ((dissmissNotificationCenter.1) runnable).this$0 = this;
        VarHandle.storeStoreFence();
        this.mainHandler.post(runnable);
    }

    public final Handler getMainHandler() {
        return this.mainHandler;
    }

    public Message handleActionButtonClick() {
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "handleActionButtonClick", null, 8);
        return null;
    }

    public Message handleActionButtonLongPress() {
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "handleActionButtonLongPress", null, 8);
        return null;
    }

    public final boolean isOnKeyGuard() {
        return this.keyguardUpdateMonitor.mKeyguardShowing;
    }

    public final boolean isScreenOn() {
        if (SmoothTransitionController.Companion.getInstance(this.context).isPanoramicAod()) {
            return true;
        }
        return this.powerManager.isInteractive();
    }

    public void onCrate() {
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "onCrate", null, 8);
    }

    public void onDestroy() {
    }
}
