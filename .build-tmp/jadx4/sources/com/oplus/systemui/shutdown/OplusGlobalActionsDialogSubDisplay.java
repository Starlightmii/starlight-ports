package com.oplus.systemui.shutdown;

import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.os.CancellationSignal;
import android.os.UserHandle;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.WindowManager;
import android.window.WindowOnBackInvokedDispatcher;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.Dependency;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.navigationbar.TaskbarDelegate$1$;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.qs.deprecated.PagedTileLayout$2$;
import com.android.systemui.shutdown.GlobalActionsDialogSubDisplayEx;
import com.coui.appcompat.roundRect.COUIRoundDrawable;
import com.oplus.systemui.navigationbar.utils.OplusNavBarUtils;
import com.oplus.systemui.shutdown.subdisplay.ShutdownWindowView;
import com.oplus.systemui.shutdown.subdisplay.ShutdownWindowView$OnCancelListener;
import com.oplus.systemui.shutdown.subdisplay.ShutdownWindowView$OnEmergencyListener;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplusos.systemui.common.util.GlobalActionsUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusGlobalActionsDialogSubDisplay extends GlobalActionsDialogSubDisplayEx implements ShutdownWindowView$OnCancelListener, ShutdownWindowView$OnEmergencyListener, DeviceStateController.StateChangedListener {
    public CancellationSignal mCancelSignal;
    public final Context mContext;
    public final OplusSubDisplayUtils mOplusSubDisplayUtils;
    public WindowManager.LayoutParams mParams;
    public ShutdownWindowView mShutdownWindowView;
    public final 2 mSubDisplayAuthenticationListener;
    public Context mWindowContext;
    public final H mHandler = new H(this);
    public boolean mIsAddView = false;
    public boolean mRegistered = false;
    public final 1 mReceiver = new 1(this);

    /* JADX INFO: renamed from: -$$Nest$maddViewToSubDisplay, reason: not valid java name */
    public static void m373$$Nest$maddViewToSubDisplay(OplusGlobalActionsDialogSubDisplay oplusGlobalActionsDialogSubDisplay) {
        1 r0 = oplusGlobalActionsDialogSubDisplay.mReceiver;
        LogUtil.i(LogUtil.TAG_GLOBALACTIONS, "GlobalActionsDialogSubDisplay", "addViewToSubDisplay: ");
        oplusGlobalActionsDialogSubDisplay.mWindowContext = oplusGlobalActionsDialogSubDisplay.mContext.createDisplayContext(oplusGlobalActionsDialogSubDisplay.getDisplay()).createWindowContext(2038, null).createContextAsUser(new UserHandle(OsBinderCacheUtils.getCurrentUserId()), 1);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        oplusGlobalActionsDialogSubDisplay.mParams = layoutParams;
        layoutParams.screenOrientation = 2;
        int i = layoutParams.flags & (-65539);
        layoutParams.flags = i;
        layoutParams.flags = i | oplusGlobalActionsDialogSubDisplay.getLayoutParamsFlag() | 260;
        WindowManager.LayoutParams layoutParams2 = oplusGlobalActionsDialogSubDisplay.mParams;
        layoutParams2.format = -3;
        layoutParams2.gravity = 17;
        layoutParams2.setBlurBehindRadius(GlobalActionsUtils.dp2px(oplusGlobalActionsDialogSubDisplay.mContext, 20.0f));
        WindowManager.LayoutParams layoutParams3 = oplusGlobalActionsDialogSubDisplay.mParams;
        layoutParams3.height = -1;
        layoutParams3.width = -1;
        layoutParams3.type = 2020;
        layoutParams3.layoutInDisplayCutoutMode = 1;
        ShutdownWindowView shutdownWindowViewInflate = LayoutInflater.from(oplusGlobalActionsDialogSubDisplay.mWindowContext).inflate(2131559394, (ViewGroup) null);
        oplusGlobalActionsDialogSubDisplay.mShutdownWindowView = shutdownWindowViewInflate;
        shutdownWindowViewInflate.playEnterAnim();
        oplusGlobalActionsDialogSubDisplay.mShutdownWindowView.setOnCancelListener(oplusGlobalActionsDialogSubDisplay);
        oplusGlobalActionsDialogSubDisplay.mShutdownWindowView.setOnEmergencyListener(oplusGlobalActionsDialogSubDisplay);
        ((WindowManager) oplusGlobalActionsDialogSubDisplay.mWindowContext.getSystemService(WindowManager.class)).addView(oplusGlobalActionsDialogSubDisplay.mShutdownWindowView, oplusGlobalActionsDialogSubDisplay.mParams);
        ViewRootImpl parent = oplusGlobalActionsDialogSubDisplay.mShutdownWindowView.getRootView().getParent();
        if (parent != null) {
            ShutdownWindowView shutdownWindowView = oplusGlobalActionsDialogSubDisplay.mShutdownWindowView;
            WindowOnBackInvokedDispatcher onBackInvokedDispatcher = parent.getOnBackInvokedDispatcher();
            3 r7 = new 3();
            r7.this$0 = oplusGlobalActionsDialogSubDisplay;
            VarHandle.storeStoreFence();
            OplusNavBarUtils.invokedCallback(shutdownWindowView, onBackInvokedDispatcher, r7);
        }
        Drawable cOUIRoundDrawable = new COUIRoundDrawable();
        cOUIRoundDrawable.setFillColor(oplusGlobalActionsDialogSubDisplay.mWindowContext.getColor(2131104081));
        oplusGlobalActionsDialogSubDisplay.mShutdownWindowView.setBackground(cOUIRoundDrawable);
        oplusGlobalActionsDialogSubDisplay.mShutdownWindowView.setAuthenticationListener(oplusGlobalActionsDialogSubDisplay.mSubDisplayAuthenticationListener);
        oplusGlobalActionsDialogSubDisplay.mIsAddView = true;
        DeviceStateController.getInstance().addCallback(oplusGlobalActionsDialogSubDisplay);
        if (!oplusGlobalActionsDialogSubDisplay.mRegistered) {
            ((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class)).registerReceiver(r0, new IntentFilter("android.intent.action.SCREEN_OFF"), (Executor) null, new UserHandle(OsBinderCacheUtils.getCurrentUserId()));
            ((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class)).registerReceiver(r0, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"), (Executor) null, new UserHandle(OsBinderCacheUtils.getCurrentUserId()));
            oplusGlobalActionsDialogSubDisplay.mRegistered = true;
        }
        LogUtil.i(LogUtil.TAG_GLOBALACTIONS, "GlobalActionsDialogSubDisplay", "addViewToSubDisplay is success");
    }

    /* JADX INFO: renamed from: -$$Nest$mremoveViewFromSubDisplay, reason: not valid java name */
    public static void m374$$Nest$mremoveViewFromSubDisplay(OplusGlobalActionsDialogSubDisplay oplusGlobalActionsDialogSubDisplay) {
        if (oplusGlobalActionsDialogSubDisplay.mShutdownWindowView != null) {
            try {
                ((WindowManager) oplusGlobalActionsDialogSubDisplay.mWindowContext.getSystemService(WindowManager.class)).removeView(oplusGlobalActionsDialogSubDisplay.mShutdownWindowView);
                oplusGlobalActionsDialogSubDisplay.mIsAddView = false;
                DeviceStateController.getInstance().removeCallback(oplusGlobalActionsDialogSubDisplay);
                if (oplusGlobalActionsDialogSubDisplay.mRegistered) {
                    ((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class)).unregisterReceiver(oplusGlobalActionsDialogSubDisplay.mReceiver);
                    oplusGlobalActionsDialogSubDisplay.mRegistered = false;
                }
            } catch (Exception e) {
                LogUtil.e(LogUtil.TAG_GLOBALACTIONS, "GlobalActionsDialogSubDisplay", "removeViewFromSubDisplay: " + e);
            }
        }
    }

    public OplusGlobalActionsDialogSubDisplay(Context context, KeyguardUpdateMonitor keyguardUpdateMonitor) {
        2 r5 = new 2();
        r5.this$0 = this;
        VarHandle.storeStoreFence();
        this.mSubDisplayAuthenticationListener = r5;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, 2132019256);
        Configuration configuration = new Configuration(contextThemeWrapper.getResources().getConfiguration());
        configuration.densityDpi = 296;
        this.mContext = contextThemeWrapper.createContextAsUser(new UserHandle(OsBinderCacheUtils.getCurrentUserId()), 1).createConfigurationContext(configuration);
        this.mOplusSubDisplayUtils = new OplusSubDisplayUtils(context);
    }

    private Display getDisplay() {
        return ((DisplayManager) this.mContext.getSystemService("display")).getDisplay(this.mOplusSubDisplayUtils.getSecondaryDisplayId());
    }

    private int getLayoutParamsFlag() {
        return 17564672;
    }

    public final void destroy() {
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public final void dismissDialog() {
        H h = this.mHandler;
        h.removeMessages(2);
        h.sendEmptyMessage(2);
    }

    @Override // com.oplusos.systemui.navigationbar.devicestate.DeviceStateController.StateChangedListener
    public final void onStateChanged(int i) {
        PagedTileLayout$2$.ExternalSyntheticOutline0.m(i, "onStateChanged: ", LogUtil.TAG_GLOBALACTIONS, "GlobalActionsDialogSubDisplay");
        if ((i == 2 || i == 3 || i == 0) && this.mIsAddView && this.mOplusSubDisplayUtils.getSupportSubDisplay()) {
            dismissDialog();
        }
    }

    public final void showDialog() {
        boolean zIsOpenOrHalfOpen = DeviceStateController.getInstance().isOpenOrHalfOpen();
        TaskbarDelegate$1$.ExternalSyntheticOutline0.m("isOpenOrHalfOpen: ", LogUtil.TAG_GLOBALACTIONS, "GlobalActionsDialogSubDisplay", zIsOpenOrHalfOpen);
        if (zIsOpenOrHalfOpen) {
            return;
        }
        H h = this.mHandler;
        h.removeMessages(1);
        h.sendEmptyMessage(1);
    }
}
