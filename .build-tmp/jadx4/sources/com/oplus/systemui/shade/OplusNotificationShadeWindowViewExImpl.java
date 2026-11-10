package com.oplus.systemui.shade;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import android.view.Choreographer;
import android.view.ChoreographerAddon;
import android.view.IChoreographerExt;
import android.view.IViewRootImplExt;
import android.view.IViewRootImplWrapper;
import android.view.View;
import android.view.ViewRootImpl;
import android.view.ViewRootImplAddon;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.panelanimation.platformblur.PlatformBlurManager;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.OplusNotificationShadeWindowViewEx;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.statusbar.phone.OplusCentralSurfacesEx;
import com.android.systemui.util.LargeScreenUtils;
import com.oplus.keyguard.utils.KeyguardRapidUnlockUtils;
import com.oplus.systemui.biometrics.finger.FingerPrintTimeStat;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplusos.keyguard.statistic.UnlockTimeStat;
import com.oplusos.keyguard.utils.KeyguardThreadUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationShadeWindowViewExImpl extends OplusNotificationShadeWindowViewEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusNotificationShadeWindowViewEx";
    public final AnonymousClass1 mGCTask;
    public boolean mIsFirstPendingGcOnBoot;

    /* JADX INFO: renamed from: com.oplus.systemui.shade.OplusNotificationShadeWindowViewExImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusNotificationShadeWindowViewExImpl this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    MessageQueue queue = SystemUIThreadUtil.getMainHandler().getLooper().getQueue();
                    OplusNotificationShadeWindowViewExImpl oplusNotificationShadeWindowViewExImpl = this.this$0;
                    1 r1 = new 1();
                    r1.this$0 = oplusNotificationShadeWindowViewExImpl;
                    VarHandle.storeStoreFence();
                    queue.addIdleHandler(r1);
                    break;
                case 1:
                    boolean z = this.this$0.mIsFirstPendingGcOnBoot;
                    boolean z2 = Process.getPss(Process.myPid()) / 1048576 > 350;
                    if (z || z2) {
                        Runtime.getRuntime().gc();
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            Log.d("SystemUi--Notification", "OplusNotificationShadeWindowViewEx-->GC after keyguard gone");
                        }
                        this.this$0.mIsFirstPendingGcOnBoot = false;
                        KeyguardThreadUtil.Companion.runOnGcThreadDelay(1.1.2.INSTANCE, 5000L);
                    }
                    break;
                default:
                    FingerPrintTimeStat.Companion.getInstance(this.this$0.getContext()).recordFinishedTime();
                    UnlockTimeStat companion = UnlockTimeStat.Companion.getInstance(this.this$0.getContext());
                    if (companion != null) {
                        companion.recordFinishedTime(this.this$0.getContext());
                    }
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.shade.OplusNotificationShadeWindowViewExImpl$onWindowVisibilityChanged$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        @Override // java.lang.Runnable
        public final void run() {
            KeyguardRapidUnlockUtils.Companion.getInstance().composeKeyguard(true, "applyVisibility_hide");
        }
    }

    public OplusNotificationShadeWindowViewExImpl(Context context) {
        super(context);
        this.mIsFirstPendingGcOnBoot = true;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mGCTask = anonymousClass1;
    }

    public final boolean disableMargins() {
        return ((Boolean) ((OplusSeparateNotificationAndQSState) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSState.class)).getEnableSeparateNotificationAndQS().getValue()).booleanValue() || LargeScreenUtils.shouldUseSplitNotificationShade(getContext().getResources()) || DeviceUtil.shouldUseLargeFoldConfig(getContext().getResources());
    }

    public final boolean isKeyguardBouncerContainerVisible() {
        NotificationShadeWindowView view = getView();
        View viewFindViewById = view != null ? view.findViewById(2131363557) : null;
        return viewFindViewById != null && viewFindViewById.getVisibility() == 0;
    }

    public void onCloseSystemDialogs(String str) {
        KeyguardUpdateMonitor keyguardUpdateMonitor;
        OplusSeparateNotificationAndQSEx oplusSeparateNotificationAndQSEx = (OplusSeparateNotificationAndQSEx) DependencyEx.sDependency.getDependency(OplusSeparateNotificationAndQSEx.class);
        if (oplusSeparateNotificationAndQSEx != null && oplusSeparateNotificationAndQSEx.enableSeparateNotificationAndQS() && (keyguardUpdateMonitor = (KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)) != null) {
            oplusSeparateNotificationAndQSEx.collapseQSPanel(!keyguardUpdateMonitor.mKeyguardShowing, false, false);
        }
        OplusCentralSurfacesEx oplusCentralSurfacesEx = (OplusCentralSurfacesEx) DependencyEx.sDependency.getDependency(OplusCentralSurfacesEx.class);
        if (oplusCentralSurfacesEx != null) {
            oplusCentralSurfacesEx.collapseShadePanel();
        }
    }

    public void onVisibilityChanged(View view, int i) {
        if (view == getView()) {
            setWindowViewStartVisibleTime(i == 0 ? System.currentTimeMillis() : Long.MAX_VALUE);
        }
    }

    public void onWindowVisibilityChanged(int i, NotificationShadeWindowView notificationShadeWindowView) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "onWindowVisibilityChanged to ", "OplusNotificationShadeWindowViewEx-->", "SystemUi--Keyguard");
        }
        if (i == 0) {
            KeyguardRapidUnlockUtils.Companion.getInstance().composeKeyguard(true, "applyVisibility_show");
            return;
        }
        notificationShadeWindowView.postOnAnimation(AnonymousClass2.INSTANCE);
        FingerPrintTimeStat.Companion.getInstance(getContext()).getClass();
        FingerPrintTimeStat.onKeyguardViewInvisibleTime = SystemClock.elapsedRealtime();
        Handler handler = new Handler(Looper.getMainLooper());
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(2);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    public void setEnableTraversalLast(boolean z) {
        IChoreographerExt choreographerExt = ChoreographerAddon.getChoreographerExt(Choreographer.getInstance());
        if (choreographerExt != null) {
            choreographerExt.setEnableTraversalLast(z);
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setEnableTraversalLast enable=", z), null, 8);
    }

    public void setWindowIsNeedDrawLast(View view, boolean z) {
        IViewRootImplExt extImpl;
        ViewRootImpl viewRootImpl = view.getViewRootImpl();
        if (viewRootImpl != null) {
            IViewRootImplWrapper iViewRootImplWrapperOplusWrapper = ViewRootImplAddon.oplusWrapper(viewRootImpl);
            if (iViewRootImplWrapperOplusWrapper != null && (extImpl = iViewRootImplWrapperOplusWrapper.getExtImpl()) != null) {
                extImpl.setIsNeedDrawLast(z);
            }
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setWindowIsNeedDrawLast isNeed=", z), null, 8);
        }
    }

    public final void timeoutNeedPauseBlur() {
        ((PlatformBlurManager) DependencyEx.sDependency.getDependency(PlatformBlurManager.class)).handleTimeoutPauseBlur(true);
    }
}
