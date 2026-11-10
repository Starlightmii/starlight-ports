package com.oplus.systemui.notification.headsup.windowframe;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Region;
import android.os.Binder;
import android.os.RemoteException;
import android.os.Trace;
import android.view.IWindow;
import android.view.IWindowSession;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.recyclerview.widget.ChildHelper$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.domain.interactor.RemoteInputInteractor;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.notification.base.OplusPanelViewInteractor;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.notification.headsup.FloatingActionHelper;
import com.oplus.systemui.notification.headsup.nssl.HeadsUpNsslStateManager;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.shade.OplusNotificationShadeWindowControllerExImpl;
import com.oplus.systemui.statusbar.seeding.OneHandedObserver;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpContainerWindow implements HeadsUpContainerControl {
    public static final Companion Companion = new Companion();
    private static final String TAG = "HeadsUpContainerWindow";
    private final Lazy ccView$delegate;
    private final Context context;
    private final DumpManager dumpManager;
    private final FloatingActionHelper floatingActionHelper;
    private final HeadsUpNsslStateManager headsUpNsslStateManager;
    public boolean isOneHandedEnable;
    private Region lastRegion;
    private final LogBuffer logBuffer;
    private final WindowManager.LayoutParams lp;
    private final dagger.Lazy notificationMaterialColor;
    private final dagger.Lazy notificationShadeWindowControllerExImpl;
    private final OplusPanelViewInteractor oplusPanelViewInteractor;
    private final RemoteInputInteractor remoteInputInteractor;
    private final NotificationRemoteInputManager remoteInputManager;
    private final ViewGroup rootView;
    private final HeadsUpCOUItouchMotionAnimator touchMotion;
    private final ViewBlurManager viewBlurManager;
    private final Lazy wm$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$KmrlM24zAWPkoRTgP-XqLvpsHi0, reason: not valid java name */
    public static HeadsUpLayout m59$r8$lambda$KmrlM24zAWPkoRTgPXqLvpsHi0(HeadsUpContainerWindow headsUpContainerWindow) {
        Context context = headsUpContainerWindow.context;
        LogBuffer logBuffer = headsUpContainerWindow.logBuffer;
        HeadsUpNsslStateManager headsUpNsslStateManager = headsUpContainerWindow.headsUpNsslStateManager;
        HeadsUpContainerWindow$view$2 headsUpContainerWindow$view$2 = new HeadsUpContainerWindow$view$2(1);
        headsUpContainerWindow$view$2.this$0 = headsUpContainerWindow;
        VarHandle.storeStoreFence();
        HeadsUpLayout headsUpLayout = new HeadsUpLayout(context, logBuffer, headsUpNsslStateManager, headsUpContainerWindow$view$2);
        headsUpContainerWindow.dumpManager.registerDumpable(headsUpLayout);
        headsUpLayout.setBlurManager(headsUpContainerWindow.viewBlurManager);
        headsUpLayout.setRemoteInputManager(headsUpContainerWindow.remoteInputManager);
        headsUpLayout.setFloatingActionHelper(headsUpContainerWindow.floatingActionHelper);
        headsUpLayout.setNotificationMaterialColor((NotificationMaterialColorEx) headsUpContainerWindow.notificationMaterialColor.get());
        RepeatWhenAttachedKt.repeatWhenAttached$default(headsUpLayout, (CoroutineContext) null, new HeadsUpContainerWindow$ccView$2$2$1(headsUpContainerWindow, null), 3);
        return headsUpLayout;
    }

    public static WindowManager $r8$lambda$cmsM9WJ_brVkYFsf0YyrHYhsS6U(HeadsUpContainerWindow headsUpContainerWindow) {
        return (WindowManager) headsUpContainerWindow.context.getSystemService("window");
    }

    public HeadsUpContainerWindow(Context context, ViewBlurManager viewBlurManager, LogBuffer logBuffer, RemoteInputInteractor remoteInputInteractor, HeadsUpNsslStateManager headsUpNsslStateManager, dagger.Lazy lazy, NotificationRemoteInputManager notificationRemoteInputManager, OplusPanelViewInteractor oplusPanelViewInteractor, FloatingActionHelper floatingActionHelper, dagger.Lazy lazy2, DumpManager dumpManager) {
        this.context = context;
        this.viewBlurManager = viewBlurManager;
        this.logBuffer = logBuffer;
        this.remoteInputInteractor = remoteInputInteractor;
        this.headsUpNsslStateManager = headsUpNsslStateManager;
        this.notificationShadeWindowControllerExImpl = lazy;
        this.remoteInputManager = notificationRemoteInputManager;
        this.oplusPanelViewInteractor = oplusPanelViewInteractor;
        this.floatingActionHelper = floatingActionHelper;
        this.notificationMaterialColor = lazy2;
        this.dumpManager = dumpManager;
        HeadsUpCOUItouchMotionAnimator.Companion companion = HeadsUpCOUItouchMotionAnimator.Companion;
        HeadsUpContainerWindow$$ExternalSyntheticLambda0 headsUpContainerWindow$$ExternalSyntheticLambda0 = new HeadsUpContainerWindow$$ExternalSyntheticLambda0(0);
        headsUpContainerWindow$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        HeadsUpContainerWindow$$ExternalSyntheticLambda0 headsUpContainerWindow$$ExternalSyntheticLambda1 = new HeadsUpContainerWindow$$ExternalSyntheticLambda0(1);
        headsUpContainerWindow$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.touchMotion = companion.create(context, headsUpContainerWindow$$ExternalSyntheticLambda0, headsUpContainerWindow$$ExternalSyntheticLambda1, new HeadsUpContainerWindow$touchMotion$3(2, this, HeadsUpContainerWindow.class, "handleOneHandedMode", "handleOneHandedMode(Landroid/view/View;F)V", 0));
        HeadsUpContainerWindow$$ExternalSyntheticLambda0 headsUpContainerWindow$$ExternalSyntheticLambda2 = new HeadsUpContainerWindow$$ExternalSyntheticLambda0(2);
        headsUpContainerWindow$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.wm$delegate = LazyKt__LazyJVMKt.lazy(headsUpContainerWindow$$ExternalSyntheticLambda2);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2017, -2122055064, -3);
        layoutParams.token = new Binder();
        layoutParams.gravity = 49;
        layoutParams.setFitInsetsTypes(0);
        layoutParams.softInputMode = 16;
        layoutParams.setTitle("NotificationHeadsUp");
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.privateFlags |= OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION;
        this.lp = layoutParams;
        HeadsUpContainerWindow$$ExternalSyntheticLambda0 headsUpContainerWindow$$ExternalSyntheticLambda3 = new HeadsUpContainerWindow$$ExternalSyntheticLambda0(3);
        headsUpContainerWindow$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.ccView$delegate = LazyKt__LazyJVMKt.lazy(headsUpContainerWindow$$ExternalSyntheticLambda3);
        this.lastRegion = new Region();
        this.rootView = getCcView();
    }

    public static final void access$initExcludeRegion(HeadsUpContainerWindow headsUpContainerWindow) {
        try {
            Region region = headsUpContainerWindow.getRegion(headsUpContainerWindow.getCcView(), null);
            IWindowSession windowSession = WindowManagerGlobal.getWindowSession();
            Trace.traceBegin(8L, "initExcludeRegion:" + region);
            headsUpContainerWindow.lastRegion = region;
            headsUpContainerWindow.logBuffer.log(TAG, LogLevel.DEBUG, "initExcludeRegion::" + region, (Throwable) null);
            windowSession.updateTapExcludeRegion(IWindow.Stub.asInterface(headsUpContainerWindow.getCcView().getWindowToken()), region);
            Trace.traceEnd(8L);
        } catch (RemoteException e) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "initExcludeRegion:", e.getMessage(), LogUtil.TAG_NOTIFICATION, TAG);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HeadsUpLayout getCcView() {
        return (HeadsUpLayout) this.ccView$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WindowManager getWm() {
        return (WindowManager) this.wm$delegate.getValue();
    }

    private final void setUxProcess(boolean z) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setUxProcess: ", z), null, 8);
        ((OplusNotificationShadeWindowControllerExImpl) this.notificationShadeWindowControllerExImpl.get()).adjustShadeUxFlag(this.context, z);
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerControl
    public float getBaseTranslationY() {
        HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = this.touchMotion;
        if (headsUpCOUItouchMotionAnimator != null) {
            return headsUpCOUItouchMotionAnimator.getBaseTranslationY();
        }
        View view = getView();
        if (view != null) {
            return view.getTranslationY();
        }
        return 0.0f;
    }

    public final Region getRegion(View view, Float f) {
        float fFloatValue;
        int actualHeight = view instanceof ExpandableNotificationRow ? ((ExpandableNotificationRow) view).getActualHeight() : view.getHeight();
        Region region = new Region();
        region.set(view.getLeft(), view.getTop(), view.getRight(), view.getTop() + actualHeight);
        HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = this.touchMotion;
        if (headsUpCOUItouchMotionAnimator == null || view != getView()) {
            fFloatValue = f != null ? f.floatValue() : view.getTranslationY();
        } else {
            fFloatValue = HeadsUpCOUItouchMotionAnimator.composedTranslationY$default(headsUpCOUItouchMotionAnimator, f, 2);
        }
        region.translate((int) view.getTranslationX(), (int) fFloatValue);
        return region;
    }

    public final ViewGroup getRootView() {
        return this.rootView;
    }

    public final HeadsUpCOUItouchMotionAnimator getTouchMotion() {
        return this.touchMotion;
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerControl
    public float getTranslationY() {
        HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = this.touchMotion;
        if (headsUpCOUItouchMotionAnimator != null) {
            return headsUpCOUItouchMotionAnimator.getRealTranslationY();
        }
        View view = getView();
        if (view != null) {
            return view.getTranslationY();
        }
        return 0.0f;
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerControl
    public View getView() {
        if (getCcView().getChildCount() == 0) {
            return null;
        }
        return getCcView().getChildAt(0);
    }

    public final void handleOneHandedMode(float f, View view) {
        if (view instanceof ExpandableNotificationRow) {
            boolean oneHandedEnable = OneHandedObserver.Companion.getInstance().getOneHandedEnable();
            boolean z = f < 0.0f;
            boolean z2 = this.isOneHandedEnable;
            if (z2 && (!oneHandedEnable || (oneHandedEnable && !z))) {
                ((ActivatableNotificationView) ((ExpandableNotificationRow) view)).mBackgroundNormal.setClipTopAmount(0);
                this.isOneHandedEnable = false;
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "HeadsUpBackground setClipTopAmount = 0", null, 8);
            } else if (oneHandedEnable && z) {
                if (!z2) {
                    this.isOneHandedEnable = true;
                }
                ((ActivatableNotificationView) ((ExpandableNotificationRow) view)).mBackgroundNormal.setClipTopAmount((int) Math.abs(f));
            }
        }
    }

    public final void ignoreTouchMotionCallback() {
        HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = this.touchMotion;
        if (headsUpCOUItouchMotionAnimator == null || headsUpCOUItouchMotionAnimator.ignoredTouchMotionCallbackEnabledInternal) {
            return;
        }
        headsUpCOUItouchMotionAnimator.ignoredTouchMotionCallbackEnabledInternal = true;
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerControl
    public boolean isHeadsUpView(View view) {
        return getCcView().isHeadsUpView(view);
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerControl
    public void notifySizeOrLocationChanged(Float f) {
        try {
            View view = getView();
            if (view == null) {
                return;
            }
            Region region = getRegion(getCcView(), null);
            region.op(getRegion(view, f), Region.Op.DIFFERENCE);
            if (Intrinsics.areEqual(this.lastRegion, region)) {
                return;
            }
            this.lastRegion = region;
            IWindowSession windowSession = WindowManagerGlobal.getWindowSession();
            Trace.traceBegin(8L, "setTouchExclusionRegion:" + region);
            this.logBuffer.log(TAG, LogLevel.DEBUG, "notifySizeOrLocationChanged::" + region, (Throwable) null);
            windowSession.updateTapExcludeRegion(IWindow.Stub.asInterface(getCcView().getWindowToken()), region);
            Trace.traceEnd(8L);
        } catch (RemoteException e) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "setTouchExclusionRegion:", e.getMessage(), LogUtil.TAG_NOTIFICATION, TAG);
        }
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerControl
    public void onConfigChanged(Configuration configuration) {
        getCcView().onConfigurationChanged(configuration);
        HeadsUpLayout ccView = getCcView();
        HeadsUpContainerWindow$view$2 headsUpContainerWindow$view$2 = new HeadsUpContainerWindow$view$2(2);
        headsUpContainerWindow$view$2.this$0 = this;
        VarHandle.storeStoreFence();
        ccView.post(headsUpContainerWindow$view$2);
    }

    public final HeadsUpLayout providerHeadsUpLayout() {
        return getCcView();
    }

    public final void requestDisallowLongPress() {
        if (getView() == null) {
            return;
        }
        getCcView().requestDisallowLongPress();
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerControl
    public void reuseSwipeHelper(NotificationSwipeHelper notificationSwipeHelper) {
        getCcView().setSwipeHelper(notificationSwipeHelper);
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerControl
    public void setBaseTranslationY(float f) {
        if (this.touchMotion != null) {
            return;
        }
        View view = getView();
        if (view != null) {
            view.setTransitionAlpha(1.0f);
            view.setTranslationY(f);
            handleOneHandedMode(f, view);
        }
        notifySizeOrLocationChanged(null);
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerControl
    public void setHeadsUpHelper(OplusHeadsUpTouchHelper oplusHeadsUpTouchHelper) {
        getCcView().setTouchHelper(oplusHeadsUpTouchHelper);
        HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = this.touchMotion;
        oplusHeadsUpTouchHelper.setCouiMotionForwarder(headsUpCOUItouchMotionAnimator != null ? headsUpCOUItouchMotionAnimator.getCouiMotionForwarder() : null);
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerControl
    public void setTranslationY(float f) {
        HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = this.touchMotion;
        if (headsUpCOUItouchMotionAnimator != null) {
            headsUpCOUItouchMotionAnimator.setBaseTranslationY(f);
            return;
        }
        View view = getView();
        if (view != null) {
            view.setTransitionAlpha(1.0f);
            view.setTranslationY(f);
            handleOneHandedMode(f, view);
        }
        notifySizeOrLocationChanged(null);
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerControl
    public void setView(View view) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, ChildHelper$.ExternalSyntheticOutline0.m(view, "setView:"), null, 8);
        int i = 0;
        if (view == null) {
            LogBuffer logBuffer = this.logBuffer;
            LogLevel logLevel = LogLevel.DEBUG;
            logBuffer.log(TAG, logLevel, ListPopupWindow$.ExternalSyntheticOutline0.m(getCcView().getChildCount(), "setView: remove window:"), (Throwable) null);
            ExpandableNotificationRow view2 = getView();
            HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator = this.touchMotion;
            if (headsUpCOUItouchMotionAnimator != null) {
                headsUpCOUItouchMotionAnimator.detachView(view2);
            }
            if ((view2 instanceof ExpandableNotificationRow) && view2.mExpandAnimationRunning) {
                LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "zoom animator is running, try to remove view ");
            }
            getCcView().setVisibility(4);
            getCcView().removeAllViews();
            if (getCcView().getParent() != null) {
                getWm().removeView(getCcView());
            } else {
                this.logBuffer.log(TAG, logLevel, "setView: remove window:no parent", (Throwable) null);
            }
            if (((Boolean) this.oplusPanelViewInteractor.isVisibility().getValue()).booleanValue()) {
                return;
            }
            setUxProcess(false);
            return;
        }
        getCcView().setVisibility(0);
        if (getCcView().getParent() != null) {
            getCcView().removeAllViews();
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "setView: remove all views before add", null, 8);
        }
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "remove parent: " + view.getParent(), null, 8);
            viewGroup.removeView(view);
        }
        HeadsUpLayout ccView = getCcView();
        ccView.updateResource$2(ccView.getResources());
        ccView.setLayoutDirection(2);
        getCcView().addView(view);
        HeadsUpCOUItouchMotionAnimator headsUpCOUItouchMotionAnimator2 = this.touchMotion;
        if (headsUpCOUItouchMotionAnimator2 != null) {
            headsUpCOUItouchMotionAnimator2.attachView(view);
        }
        this.lp.flags |= 8;
        if (getCcView().getParent() == null) {
            getWm().addView(getCcView(), this.lp);
        } else {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "setView: has already been added", null, 8);
        }
        HeadsUpLayout ccView2 = getCcView();
        HeadsUpContainerWindow$view$2 headsUpContainerWindow$view$2 = new HeadsUpContainerWindow$view$2(i);
        headsUpContainerWindow$view$2.this$0 = this;
        VarHandle.storeStoreFence();
        ccView2.post(headsUpContainerWindow$view$2);
    }
}
