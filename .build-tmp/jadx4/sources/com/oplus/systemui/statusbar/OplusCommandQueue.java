package com.oplus.systemui.statusbar;

import android.app.Application;
import android.app.IOplusStatusBar;
import android.app.OplusStatusBarManager;
import android.content.Context;
import android.os.UserHandle;
import android.util.Log;
import androidx.constraintlayout.motion.widget.MotionLayout$;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.dump.DumpHandler;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.navigationbar.NavigationBarGestureEx;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import com.oplus.onehanded.OneHandedHelper;
import com.oplus.splitscreen.SplitToggleHelper;
import com.oplus.systemui.keyguard.lockdead.LockDeadUtil;
import com.oplus.systemui.security.SecurityLockModeManager;
import com.oplus.systemui.statusbar.observer.StatusBarExpandableObserver;
import com.oplus.systemui.usb.OplusUsbBackend;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ReflectionUtils;
import com.oplusos.systemui.common.utils.Util;
import dagger.Lazy;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCommandQueue extends CommandQueue {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusCommandQueue";
    private final Context context;
    private final OplusStatusBar mOplusStatusBar;
    private final NavigationBarGestureEx navigationBarGestureEx;
    private final SecurityLockModeManager securityLockModeManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class OplusStatusBar extends IOplusStatusBar.Stub {
        private final Context context;
        private final NavigationBarGestureEx navigationBarGestureEx;

        public OplusStatusBar(NavigationBarGestureEx navigationBarGestureEx, Context context) {
            this.navigationBarGestureEx = navigationBarGestureEx;
            this.context = context;
        }

        public void controlOneHandedMode(int i, String str) {
            try {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "OplusCommandQueue-->".concat("controlOneHandedMode cmd=" + i + ",pkgName=" + str));
                }
                OneHandedHelper.getInstance().controlOneHandedMode(i, str);
            } catch (Exception unused) {
                LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, OplusCommandQueue.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("controlOneHandedMode  failed  cmd=", i, ", pkgName=", str));
            }
        }

        public void notifyInputMethodKeyboardPosition(boolean z) {
        }

        public void notifyMultiWindowFocusChanged(int i) {
        }

        public void onSystemBarOFlagChanged(int i) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onSystemBarOFlagChanged oplusFlags:", Integer.toHexString(i), "OplusCommandQueue-->", "SystemUi--Statusbar");
            }
            this.navigationBarGestureEx.refreshNavBarIcons(i);
            this.navigationBarGestureEx.updateOplusFlagAllowBackGestureIgnoreVisibility(i);
        }

        public void onSystemUiOFlagChanged(int i) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onSystemUiOFlagChanged oplusFlags:", Integer.toHexString(i), "OplusCommandQueue-->", "SystemUi--Statusbar");
            }
            OplusUsbBackend.getInstance(this.context).setBlockedUsbDialogFlagStatus(i);
        }

        public void setStatusBarFunction(int i, String str) {
        }

        public void toggleSplitScreen(int i) {
            SplitToggleHelper.getInstance().toggleSplitScreenMode(i);
        }

        public void topIsFullscreen(boolean z) {
        }

        public void updateAllowGestureIgnoreVisibility(boolean z) {
        }

        public void updateBlockedUsbDialogAbility(boolean z) {
        }

        public void updateNavBarVisibility(int i) {
        }

        public void updateNavBarVisibilityWithPkg(int i, String str) {
        }
    }

    public OplusCommandQueue(Context context, DisplayTracker displayTracker, CommandRegistry commandRegistry, DumpHandler dumpHandler, Lazy lazy, SecurityLockModeManager securityLockModeManager, NavigationBarGestureEx navigationBarGestureEx) {
        super(context, displayTracker, commandRegistry, dumpHandler, lazy);
        this.context = context;
        this.securityLockModeManager = securityLockModeManager;
        this.navigationBarGestureEx = navigationBarGestureEx;
        OplusStatusBar oplusStatusBar = new OplusStatusBar(navigationBarGestureEx, context);
        this.mOplusStatusBar = oplusStatusBar;
        String processName = Application.getProcessName();
        String packageName = context.getPackageName();
        Log.d(TAG, "registerStatusBar: processName = " + processName + ", pkgName = " + packageName);
        if (UserHandle.myUserId() == 0 && Intrinsics.areEqual(packageName, processName)) {
            registerOplusStatusBarCallback(oplusStatusBar);
        }
    }

    private final void registerOplusStatusBarCallback(OplusStatusBar oplusStatusBar) {
        try {
            ReflectionUtils.methodInvoke(new OplusStatusBarManager(), "registerOplusStatusBar", new Class[]{IOplusStatusBar.class}, IOplusStatusBar.Stub.asInterface(oplusStatusBar.asBinder()));
        } catch (Exception e) {
            MotionLayout$.ExternalSyntheticOutline0.m$1("registerOplusStatusBarCallback e=", e.getMessage(), TAG);
        }
    }

    public Object observe(LifecycleOwner lifecycleOwner, Object obj) {
        return observe(lifecycleOwner.getLifecycle(), obj);
    }

    public boolean panelsEnabled() {
        if (Util.isChildrenOrStudyCenterMode()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "OplusCommandQueue-->panelsEnabled--isChildrenOrStudyCenterMode disable");
            }
            return false;
        }
        LockDeadUtil.INSTANCE.getClass();
        if (LockDeadUtil.isLockDeadState) {
            LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, "panelsEnabled -- disabled by LockDeadUtil.isLockDeadState");
            return false;
        }
        if (this.securityLockModeManager.securityLockMode == 1) {
            LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, "panelsEnabled -- disabled by isInSecurityLockMode");
            return false;
        }
        if (!StatusBarExpandableObserver.getInstance().mIsStatusBarExpandDisableMode) {
            return super.panelsEnabled();
        }
        LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, "panelsEnabled--isStatusBarExpandDisableMode disable");
        return false;
    }

    public /* bridge */ /* synthetic */ Object observe(Lifecycle lifecycle, Object obj) {
        super/*com.android.systemui.statusbar.policy.CallbackController*/.observe(lifecycle, obj);
        return obj;
    }
}
