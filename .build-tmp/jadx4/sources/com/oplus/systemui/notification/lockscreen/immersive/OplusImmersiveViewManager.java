package com.oplus.systemui.notification.lockscreen.immersive;

import android.graphics.Rect;
import android.support.v4.content.FileProvider$;
import android.util.Log;
import android.view.View;
import android.view.ViewRootImpl;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.WindowOnBackInvokedDispatcher;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.keyguard.clockstyle.IKeyguardStyleClockController;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewProxy;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.ui.view.SharedNotificationContainer;
import com.android.systemui.statusbar.phone.ScrimController;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateDecider;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusImmersiveViewManager implements OnBackInvokedCallback {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusImmersiveViewManager";
    private final OplusImmersiveBgContainer bgContainer;
    private final Lazy capsuleCardControl;
    private final LockScreenNotificationExtStateDecider extStateDecider;
    private final Lazy immersiveCardControl;
    public boolean isAllowToShowImmersiveView;
    private final Lazy keyguardStyleClockController;
    public float keyguardViewAlpha = 1.0f;
    public float keyguardVisualAlpha = 1.0f;
    public float launchViewAlpha = 1.0f;
    private final Lazy lockScreenExtStateController;
    private final Lazy notificationCapsuleInteractor;
    private final Lazy nssl;
    public boolean registerBackCallback;
    private final Lazy scrimController;
    private final Lazy sharedNotificationContainer;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveViewManager$bindForStack$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements OplusExpandableViewProxy {
        public /* synthetic */ OplusCustomRow $stackRow;

        public String getProxyName() {
            CustomRowEntry entry = this.$stackRow.getEntry();
            return FileProvider$.ExternalSyntheticOutline0.m("imms:", entry != null ? entry.getKey() : null);
        }

        public View getProxyView() {
            return null;
        }

        public final boolean isForCapsuleShowing() {
            return false;
        }

        public /* bridge */ /* synthetic */ void setAlpha(float f) {
            super.setAlpha(f);
        }

        public void setCapsuleProgress(float f) {
        }

        public /* bridge */ /* synthetic */ void setClipRect(Rect rect) {
            super.setClipRect(rect);
        }

        public /* bridge */ /* synthetic */ void setImmersiveProgress(float f) {
            super.setImmersiveProgress(f);
        }

        public /* bridge */ /* synthetic */ void setTopRadius(float f) {
            super.setTopRadius(f);
        }

        public /* bridge */ /* synthetic */ void setTranslationY(float f) {
            super.setTranslationY(f);
        }
    }

    public static Unit $r8$lambda$jB0dglZAuHo6EgZeUrInQNN8q0g(OplusImmersiveViewManager oplusImmersiveViewManager) {
        ((CapsuleCardControl) oplusImmersiveViewManager.capsuleCardControl.get()).onImmerStateAnimFinished();
        return Unit.INSTANCE;
    }

    public OplusImmersiveViewManager(OplusImmersiveBgContainer oplusImmersiveBgContainer, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, LockScreenNotificationExtStateDecider lockScreenNotificationExtStateDecider) {
        this.bgContainer = oplusImmersiveBgContainer;
        this.lockScreenExtStateController = lazy;
        this.sharedNotificationContainer = lazy2;
        this.nssl = lazy3;
        this.capsuleCardControl = lazy4;
        this.immersiveCardControl = lazy5;
        this.keyguardStyleClockController = lazy6;
        this.notificationCapsuleInteractor = lazy7;
        this.scrimController = lazy8;
        this.extStateDecider = lockScreenNotificationExtStateDecider;
    }

    public static void logAlpha(String str, float f, float f2) {
        if (f == f2) {
            return;
        }
        if ((f2 == 1.0f || f2 == 0.0f) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusImmersiveViewManager-->".concat("logAlpha:" + str + ":: alpha=" + f2));
        }
    }

    private final void setRegisterBackCallback(boolean z) {
        if (this.registerBackCallback != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("registerBackCallback:", "OplusImmersiveViewManager-->", "SystemUi--Notification", z);
            }
            this.registerBackCallback = z;
            checkIfNeedRegisterBackInvoked();
        }
    }

    public final void bindForStack(OplusCustomRow oplusCustomRow, boolean z) {
        OplusExpandableViewProxy proxy = ((ExpandableView) oplusCustomRow).mExpandableViewEx.getProxy();
        if (proxy != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                CustomRowEntry entry = oplusCustomRow.getEntry();
                Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("bindForStack:: stackRow = ", entry != null ? entry.getKey() : null, ",skip old=", proxy.getProxyName(), "OplusImmersiveViewManager-->"));
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            CustomRowEntry entry2 = oplusCustomRow.getEntry();
            Log.d("SystemUi--Notification", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("bindForStack:: stackRow = ", entry2 != null ? entry2.getKey() : null, ", isImmersiveShowing = ", z, "OplusImmersiveViewManager-->"));
        }
        OplusExpandableViewEx oplusExpandableViewEx = ((ExpandableView) oplusCustomRow).mExpandableViewEx;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.$stackRow = oplusCustomRow;
        VarHandle.storeStoreFence();
        oplusExpandableViewEx.setProxy(anonymousClass3);
    }

    public final void checkIfNeedRegisterBackInvoked() {
        WindowOnBackInvokedDispatcher onBackInvokedDispatcher;
        WindowOnBackInvokedDispatcher onBackInvokedDispatcher2;
        if (this.registerBackCallback && this.isAllowToShowImmersiveView) {
            ViewRootImpl viewRootImpl = this.bgContainer.getViewRootImpl();
            if (viewRootImpl == null || (onBackInvokedDispatcher2 = viewRootImpl.getOnBackInvokedDispatcher()) == null) {
                return;
            }
            onBackInvokedDispatcher2.registerOnBackInvokedCallback(1000000, this);
            return;
        }
        ViewRootImpl viewRootImpl2 = this.bgContainer.getViewRootImpl();
        if (viewRootImpl2 == null || (onBackInvokedDispatcher = viewRootImpl2.getOnBackInvokedDispatcher()) == null) {
            return;
        }
        onBackInvokedDispatcher.unregisterOnBackInvokedCallback(this);
    }

    public final float getKeyguardViewAlpha() {
        return this.keyguardViewAlpha;
    }

    public final boolean isCurrentlyAnimating() {
        return this.bgContainer.isCurrentlyAnimating();
    }

    public final boolean isSurfaceBg() {
        return this.bgContainer.isSurfaceBg();
    }

    public final void notifyCapsuleLayoutChanged() {
        OplusImmersiveBgContainer oplusImmersiveBgContainer = this.bgContainer;
        oplusImmersiveBgContainer.notifyCenterRect();
        oplusImmersiveBgContainer.checkIfNeedReLayout("notifyCapsuleLayoutChanged");
    }

    public final void notifyHideSurfaceView() {
        this.bgContainer.notifyHideSurfaceView();
    }

    public final void notifyImmersiveClockBottomChanged(int i) {
        this.bgContainer.notifyImmersiveClockBottomChanged(i);
    }

    @Override // android.window.OnBackInvokedCallback
    public final void onBackInvoked() {
        OplusImmersiveTouchHandler touchHandler = this.bgContainer.getTouchHandler();
        if (touchHandler != null) {
            touchHandler.onBackInvoked();
        }
    }

    public final void onShadeState() {
        OplusImmersiveBgContainer oplusImmersiveBgContainer = this.bgContainer;
        if (oplusImmersiveBgContainer.isCurrentlyAnimating()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ImmersiveBg-->onShadeState:but isAnimationRunning");
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "ImmersiveBg-->onShadeState:try remove");
        }
        oplusImmersiveBgContainer.onAnimationFinished();
    }

    public final void setAllowToShowImmersiveView(boolean z) {
        if (this.isAllowToShowImmersiveView != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("isAllowToShowImmersiveView:", "OplusImmersiveViewManager-->", "SystemUi--Notification", z);
            }
            this.isAllowToShowImmersiveView = z;
            OplusImmersiveBgContainer oplusImmersiveBgContainer = this.bgContainer;
            if (z) {
                oplusImmersiveBgContainer.setVisibility(0);
            } else {
                oplusImmersiveBgContainer.setVisibility(4);
            }
            checkIfNeedRegisterBackInvoked();
        }
    }

    public final void setImmersiveTouchCallback(OplusImmersiveTouchHandler.ImmersiveTouchCallback immersiveTouchCallback) {
        OplusImmersiveBgContainer oplusImmersiveBgContainer = this.bgContainer;
        oplusImmersiveBgContainer.setTouchHandler(new OplusImmersiveTouchHandler(oplusImmersiveBgContainer, immersiveTouchCallback, this.lockScreenExtStateController, (CapsuleCardControl) this.capsuleCardControl.get(), this.notificationCapsuleInteractor, this.extStateDecider));
    }

    public final void setKeyguardViewAlpha(float f) {
        this.keyguardViewAlpha = f;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void setSeedingDataForBg(CustomRowEntry customRowEntry, OplusCustomRow oplusCustomRow, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("setSeedingDataForBg:: data = ", customRowEntry != null ? customRowEntry.getKey() : null, "OplusImmersiveViewManager-->", "SystemUi--Notification");
        }
        if (customRowEntry == null) {
            ((NotificationStackScrollLayout) this.nssl.get()).requestChildrenUpdate$1();
            this.bgContainer.hideCurrent(z);
            this.bgContainer.setOnClickListener(null);
            setRegisterBackCallback(false);
            return;
        }
        setRegisterBackCallback(true);
        View immersiveCardView = customRowEntry.getImmersiveCardView();
        if (immersiveCardView == null) {
            TextView textView = new TextView(this.bgContainer.getContext());
            textView.setText(customRowEntry.getPackageName() + OplusPreventModeData.SEPARATOR + customRowEntry.getKey());
            textView.setBackgroundColor(-3355444);
            textView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            immersiveCardView = textView;
        }
        this.bgContainer.showView(immersiveCardView, customRowEntry, oplusCustomRow, z);
    }

    public final void start() {
        this.bgContainer.setLockScreenExtStateController((ILockScreenExtStateController) this.lockScreenExtStateController.get());
        this.bgContainer.setCapsuleCardControl((CapsuleCardControl) this.capsuleCardControl.get());
        this.bgContainer.setImmersiveCardControl((ImmersiveCardControl) this.immersiveCardControl.get());
        this.bgContainer.setSharedNotificationContainer((SharedNotificationContainer) this.sharedNotificationContainer.get());
        this.bgContainer.setKeyguardStyleClockController((IKeyguardStyleClockController) this.keyguardStyleClockController.get());
        this.bgContainer.setScrimController((ScrimController) this.scrimController.get());
        OplusImmersiveBgContainer oplusImmersiveBgContainer = this.bgContainer;
        OplusImmersiveViewManager$$ExternalSyntheticLambda0 oplusImmersiveViewManager$$ExternalSyntheticLambda0 = new OplusImmersiveViewManager$$ExternalSyntheticLambda0();
        oplusImmersiveViewManager$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        oplusImmersiveBgContainer.addOnAnimationFinishedListener(oplusImmersiveViewManager$$ExternalSyntheticLambda0);
    }

    public final void unBindForStack(OplusCustomRow oplusCustomRow) {
        String proxyName;
        OplusExpandableViewProxy proxy = ((ExpandableView) oplusCustomRow).mExpandableViewEx.getProxy();
        if (proxy == null || (proxyName = proxy.getProxyName()) == null || !StringsKt__StringsJVMKt.startsWith(proxyName, "imms", false)) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            CustomRowEntry entry = oplusCustomRow.getEntry();
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("unBindForStack:: stackRow = ", entry != null ? entry.getKey() : null, "OplusImmersiveViewManager-->", "SystemUi--Notification");
        }
        ((ExpandableView) oplusCustomRow).mExpandableViewEx.setProxy((OplusExpandableViewProxy) null);
    }

    public final void updateKeyguardViewAlpha(float f) {
        logAlpha(SeparateQSStatisicUtil.VALUE_KEYGUARD, this.keyguardVisualAlpha, f);
        this.keyguardVisualAlpha = f;
        this.bgContainer.setAlpha(Math.min(f, this.launchViewAlpha));
    }

    public final void updateLaunchViewAlpha(float f) {
        logAlpha("launch", this.launchViewAlpha, f);
        this.launchViewAlpha = f;
        this.bgContainer.setAlpha(Math.min(this.keyguardVisualAlpha, f));
    }

    public final void updateScaleWithPivot(float f, float f2, float f3) {
        this.bgContainer.updateScaleWithPivotData(f, f2, f3);
    }
}
