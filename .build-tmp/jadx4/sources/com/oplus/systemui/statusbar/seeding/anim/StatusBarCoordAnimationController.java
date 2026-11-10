package com.oplus.systemui.statusbar.seeding.anim;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.notification.utlils.DeviceStateUtils;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginManager;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusBarCoordAnimationController implements SeedlingPlugin.ChangeListener {
    private static final Companion Companion = new Companion();
    private static final String TAG = "StatusBarCoordAnimationController";
    private final Context context;
    private StatusBarAnimatorManager keyguardStatusBarAnimatorManager;
    public boolean needCoordAnimator = true;
    private final StatusBarCoordAnimationController$seedlingPluginCallback$1 seedlingPluginCallback;
    private final SeedlingPluginManager seedlingPluginManager;
    private StatusBarAnimatorManager statusBarAnimatorManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StatusBarCoordAnimationController(Context context, SeedlingPluginManager seedlingPluginManager) {
        this.context = context;
        this.seedlingPluginManager = seedlingPluginManager;
        StatusBarCoordAnimationController$seedlingPluginCallback$1 statusBarCoordAnimationController$seedlingPluginCallback$1 = new StatusBarCoordAnimationController$seedlingPluginCallback$1();
        statusBarCoordAnimationController$seedlingPluginCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.seedlingPluginCallback = statusBarCoordAnimationController$seedlingPluginCallback$1;
    }

    public static String bundleToString(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null) {
                sb.append(" " + str + ": " + obj + ";");
            }
        }
        return sb.toString();
    }

    private final boolean getNeedAddCallback() {
        return this.statusBarAnimatorManager == null && this.keyguardStatusBarAnimatorManager == null;
    }

    private final boolean getNeedRemoveCallback() {
        return this.statusBarAnimatorManager == null && this.keyguardStatusBarAnimatorManager == null;
    }

    public final void dump(PrintWriter printWriter) {
        if (printWriter != null) {
            printWriter.println(TAG);
        }
        if (printWriter != null) {
            OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "needCoordAnimator: ", this.needCoordAnimator);
        }
    }

    public void onChangeCancel(Bundle bundle) {
        StatusBarAnimationData statusBarAnimationDataFromBundle;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "StatusBarCoordAnimationController-->".concat("onChangeCancel: ".concat(bundleToString(bundle))));
        }
        if (this.needCoordAnimator && (statusBarAnimationDataFromBundle = StatusBarAnimationData.Companion.fromBundle(bundle)) != null) {
            if (statusBarAnimationDataFromBundle.isLockscreen) {
                StatusBarAnimatorManager statusBarAnimatorManager = this.keyguardStatusBarAnimatorManager;
                if (statusBarAnimatorManager != null) {
                    statusBarAnimatorManager.cancelAnimation(statusBarAnimationDataFromBundle);
                    return;
                }
                return;
            }
            StatusBarAnimatorManager statusBarAnimatorManager2 = this.statusBarAnimatorManager;
            if (statusBarAnimatorManager2 != null) {
                statusBarAnimatorManager2.cancelAnimation(statusBarAnimationDataFromBundle);
            }
        }
    }

    public void onChangeEnd(Bundle bundle) {
        StatusBarAnimationData statusBarAnimationDataFromBundle;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "StatusBarCoordAnimationController-->".concat("onChangeEnd: ".concat(bundleToString(bundle))));
        }
        if (this.needCoordAnimator && (statusBarAnimationDataFromBundle = StatusBarAnimationData.Companion.fromBundle(bundle)) != null) {
            if (statusBarAnimationDataFromBundle.isLockscreen) {
                StatusBarAnimatorManager statusBarAnimatorManager = this.keyguardStatusBarAnimatorManager;
                if (statusBarAnimatorManager != null) {
                    statusBarAnimatorManager.endAnimation(statusBarAnimationDataFromBundle);
                    return;
                }
                return;
            }
            StatusBarAnimatorManager statusBarAnimatorManager2 = this.statusBarAnimatorManager;
            if (statusBarAnimatorManager2 != null) {
                statusBarAnimatorManager2.endAnimation(statusBarAnimationDataFromBundle);
            }
        }
    }

    public void onChangeStart(Bundle bundle) {
        StatusBarAnimationData statusBarAnimationDataFromBundle;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "StatusBarCoordAnimationController-->".concat("onChangeStart: ".concat(bundleToString(bundle))));
        }
        if (this.needCoordAnimator && (statusBarAnimationDataFromBundle = StatusBarAnimationData.Companion.fromBundle(bundle)) != null) {
            if (statusBarAnimationDataFromBundle.isLockscreen) {
                StatusBarAnimatorManager statusBarAnimatorManager = this.keyguardStatusBarAnimatorManager;
                if (statusBarAnimatorManager != null) {
                    statusBarAnimatorManager.startAnimation(statusBarAnimationDataFromBundle);
                    return;
                }
                return;
            }
            StatusBarAnimatorManager statusBarAnimatorManager2 = this.statusBarAnimatorManager;
            if (statusBarAnimatorManager2 != null) {
                statusBarAnimatorManager2.startAnimation(statusBarAnimationDataFromBundle);
            }
        }
    }

    public final void onKeyguardStatusBarViewCreated(ViewGroup viewGroup) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "StatusBarCoordAnimationController-->onKeyguardStatusBarViewCreated");
        }
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(2131365362);
        if (viewGroup2 != null) {
            if (getNeedAddCallback()) {
                this.seedlingPluginManager.addCallback(this.seedlingPluginCallback);
            }
            this.keyguardStatusBarAnimatorManager = new StatusBarAnimatorManager(this.context, new KeyguardStatusBarViewProvider(viewGroup2));
        }
    }

    public final void onKeyguardStatusBarViewDestroy() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "StatusBarCoordAnimationController-->onKeyguardStatusBarViewDestroy");
        }
        this.keyguardStatusBarAnimatorManager = null;
        if (getNeedRemoveCallback()) {
            this.seedlingPluginManager.removeCallback(this.seedlingPluginCallback);
        }
    }

    public void onPreChange(Bundle bundle) {
        StatusBarAnimationData statusBarAnimationDataFromBundle;
        Context context = this.context;
        this.needCoordAnimator = DeviceStateUtils.isPortraitInSmallScreen(context, !DeviceUtil.isLandscape(context));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("onPreChange: ", bundleToString(bundle), " needCoordAnimator: ", this.needCoordAnimator, "StatusBarCoordAnimationController-->"));
        }
        if (this.needCoordAnimator && (statusBarAnimationDataFromBundle = StatusBarAnimationData.Companion.fromBundle(bundle)) != null) {
            if (statusBarAnimationDataFromBundle.isLockscreen) {
                StatusBarAnimatorManager statusBarAnimatorManager = this.keyguardStatusBarAnimatorManager;
                if (statusBarAnimatorManager != null) {
                    statusBarAnimatorManager.prepareAnimation(statusBarAnimationDataFromBundle);
                    return;
                }
                return;
            }
            StatusBarAnimatorManager statusBarAnimatorManager2 = this.statusBarAnimatorManager;
            if (statusBarAnimatorManager2 != null) {
                statusBarAnimatorManager2.prepareAnimation(statusBarAnimationDataFromBundle);
            }
        }
    }

    public final void onStatusBarViewCreated(View view) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "StatusBarCoordAnimationController-->onStatusBarViewCreated");
        }
        ViewGroup viewGroup = (ViewGroup) view.findViewById(2131365363);
        if (viewGroup != null) {
            if (getNeedAddCallback()) {
                this.seedlingPluginManager.addCallback(this.seedlingPluginCallback);
            }
            this.statusBarAnimatorManager = new StatusBarAnimatorManager(this.context, new StatusBarViewProvider(viewGroup));
        }
    }
}
