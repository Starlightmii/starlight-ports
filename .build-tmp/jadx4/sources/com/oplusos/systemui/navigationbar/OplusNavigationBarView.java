package com.oplusos.systemui.navigationbar;

import android.R;
import android.content.Context;
import android.graphics.Region;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import androidx.slice.widget.RowView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.DependencyEx;
import com.android.systemui.ExpandHelper$;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.navigationbar.NavigationBarDependencyEx;
import com.android.systemui.navigationbar.NavigationBarGestureUpEx;
import com.android.systemui.navigationbar.NavigationBarViewControlEx;
import com.android.systemui.navigationbar.UtilsStaticToolsEx;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$;
import com.android.systemui.navigationbar.views.NavigationBarTransitions;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.shared.statusbar.phone.BarTransitions;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplus.zoom.ui.tips.TipsManager;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger$;
import com.oplusos.systemui.statusbar.controller.ScreenBurnInProtector;
import com.oplusos.systemui.statusbar.util.AnimationDuringFoldingUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusNavigationBarView extends NavigationBarView implements ScreenBurnInProtector.OnBurnInPreventCallback {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean mFoldState;
    public final AnonymousClass2 mFoldStateListener;
    public final AnonymousClass1 mHandler;
    public final boolean mIsSubDisplay;
    public int mMaxDrawableHeight;
    public int mMaxDrawableWith;
    public int mNavBarHeight;
    public NavigationBarViewControlEx mNavBarViewControlEx;
    public NavigationBarGestureUpEx mNavigationBarGestureUpEx;
    public final OplusNavigationBarView$$ExternalSyntheticLambda1 mTaskStackChangedConsumer;
    public UtilsStaticToolsEx mUtilsStaticToolsEx;
    public final WindowManager mWindowManager;

    public static /* synthetic */ void $r8$lambda$ItfbCsuSAtRjNJRuqHWyv8z5Vl4(OplusNavigationBarView oplusNavigationBarView) {
        if (QuickStepContract.isGesturalMode(((NavigationBarView) oplusNavigationBarView).mNavBarMode)) {
            return;
        }
        oplusNavigationBarView.getUtilsStaticToolsEx().onTaskStackChanged();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: -$$Nest$mgetDrawableParams, reason: not valid java name */
    public static void m624$$Nest$mgetDrawableParams(OplusNavigationBarView oplusNavigationBarView, int i, int i2) {
        oplusNavigationBarView.getClass();
        try {
            oplusNavigationBarView.mMaxDrawableWith = pxToDp(Math.max(oplusNavigationBarView.getImeSwitchButton().getImageDrawable() != null ? oplusNavigationBarView.getImeSwitchButton().getImageDrawable().getIntrinsicWidth() : 0, oplusNavigationBarView.getAccessibilityButton().getImageDrawable() != null ? oplusNavigationBarView.getAccessibilityButton().getImageDrawable().getIntrinsicWidth() : 0), ((FrameLayout) oplusNavigationBarView).mContext);
            oplusNavigationBarView.mMaxDrawableHeight = pxToDp(Math.max(oplusNavigationBarView.getImeSwitchButton().getImageDrawable() != null ? oplusNavigationBarView.getImeSwitchButton().getImageDrawable().getIntrinsicHeight() : 0, oplusNavigationBarView.getAccessibilityButton().getImageDrawable() != null ? oplusNavigationBarView.getAccessibilityButton().getImageDrawable().getIntrinsicHeight() : 0), ((FrameLayout) oplusNavigationBarView).mContext);
            boolean zIsGetFoldOpenNavigationLayout = oplusNavigationBarView.getNavigationBarViewControlEx().isGetFoldOpenNavigationLayout(((FrameLayout) oplusNavigationBarView).mContext);
            boolean zIsGesturalMode = QuickStepContract.isGesturalMode(((NavigationBarView) oplusNavigationBarView).mNavBarMode);
            LogUtil.i(LogUtil.TAG_NAVBAR, "OplusNavigationBarView", "OnGlobalLayoutListener width:" + i + " ,height:" + i2 + " ,mMaxDrawableWith:" + oplusNavigationBarView.mMaxDrawableWith + " ,mMaxDrawableHeight:" + oplusNavigationBarView.mMaxDrawableHeight + " ,mNavBarHeight:" + oplusNavigationBarView.mNavBarHeight + " ,isFoldOpen:" + zIsGetFoldOpenNavigationLayout + " ,isGesturalMode:" + zIsGesturalMode);
            if (zIsGetFoldOpenNavigationLayout || zIsGesturalMode) {
                return;
            }
            oplusNavigationBarView.setNavBarPadding();
        } catch (Exception e) {
            LogUtil.e(LogUtil.TAG_NAVBAR, "OplusNavigationBarView", "OnGlobalLayoutListener error:" + e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.oplusos.systemui.navigationbar.OplusNavigationBarView$1] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.oplusos.systemui.navigationbar.OplusNavigationBarView$2] */
    public OplusNavigationBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mNavBarHeight = pxToDp(getResources().getDimensionPixelSize(R.dimen.notification_2025_expand_button_horizontal_icon_padding), ((FrameLayout) this).mContext);
        OplusNavigationBarView$$ExternalSyntheticLambda1 oplusNavigationBarView$$ExternalSyntheticLambda1 = new OplusNavigationBarView$$ExternalSyntheticLambda1();
        oplusNavigationBarView$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.mTaskStackChangedConsumer = oplusNavigationBarView$$ExternalSyntheticLambda1;
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.oplusos.systemui.navigationbar.OplusNavigationBarView.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int i = message.what;
                if (i == 1000) {
                    OplusNavigationBarView.this.animate().cancel();
                    OplusNavigationBarView.this.setAlpha(0.0f);
                    LogUtil.d(LogUtil.TAG_NAVBAR, "OplusNavigationBarView", "set set the alpha of NavigationBarView to 0");
                } else if (i == 2000) {
                    OplusNavigationBarView.this.animate().cancel();
                    OplusNavigationBarView.this.animate().alpha(1.0f).setDuration(AnimationDuringFoldingUtil.ALPHA_DURATION.longValue()).setInterpolator(new AccelerateInterpolator()).setListener(new 1(this)).start();
                    LogUtil.d(LogUtil.TAG_NAVBAR, "OplusNavigationBarView", "set the alpha of NavigationBarView recover to 1");
                } else {
                    if (i != 3000) {
                        return;
                    }
                    OplusNavigationBarView oplusNavigationBarView = OplusNavigationBarView.this;
                    int i2 = message.arg1;
                    int i3 = OplusNavigationBarView.$r8$clinit;
                    oplusNavigationBarView.updateWindowAlpha(i2);
                }
            }
        };
        this.mFoldStateListener = new DeviceStateController.StateChangedListener() { // from class: com.oplusos.systemui.navigationbar.OplusNavigationBarView.2
            @Override // com.oplusos.systemui.navigationbar.devicestate.DeviceStateController.StateChangedListener
            public final void onStateChanged(int i) {
                DeviceStateController.getInstance().getClass();
                boolean z = i == 2 || i == 3 || i == 99;
                OplusNavigationBarView oplusNavigationBarView = OplusNavigationBarView.this;
                if (oplusNavigationBarView.mFoldState != z) {
                    oplusNavigationBarView.mFoldState = z;
                    oplusNavigationBarView.mHandler.removeMessages(TipsManager.DELAY_SING_TAP_HIDE_TIME);
                    AnonymousClass1 anonymousClass1 = OplusNavigationBarView.this.mHandler;
                    if (!anonymousClass1.sendMessageDelayed(anonymousClass1.obtainMessage(TipsManager.DELAY_SING_TAP_HIDE_TIME), AnimationDuringFoldingUtil.RECOVER_ALPHA_DURATION.longValue())) {
                        LogUtil.d(LogUtil.TAG_NAVBAR, "OplusNavigationBarView", "do not set set the alpha of NavigationBarView to 0, because send delay message fail");
                        return;
                    }
                    removeMessages(1000);
                    AnonymousClass1 anonymousClass2 = OplusNavigationBarView.this.mHandler;
                    anonymousClass2.sendMessage(anonymousClass2.obtainMessage(1000));
                }
            }
        };
        this.mFoldState = DeviceStateController.getInstance().isOpenOrHalfOpen();
        this.mWindowManager = (WindowManager) ((FrameLayout) this).mContext.getSystemService(WindowManager.class);
        UtilsStaticToolsEx utilsStaticToolsExImpl = ((NavigationBarDependencyEx) DependencyEx.sDependency.getDependency(NavigationBarDependencyEx.class)).getUtilsStaticToolsExImpl();
        Context context2 = ((FrameLayout) this).mContext;
        boolean zIsSubDisplay = utilsStaticToolsExImpl.isSubDisplay(context2, context2.getDisplayId());
        this.mIsSubDisplay = zIsSubDisplay;
        if (zIsSubDisplay) {
            return;
        }
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.oplusos.systemui.navigationbar.OplusNavigationBarView.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                OplusNavigationBarView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = OplusNavigationBarView.this.getWidth();
                int height = OplusNavigationBarView.this.getHeight();
                OplusNavigationBarView oplusNavigationBarView = OplusNavigationBarView.this;
                oplusNavigationBarView.mNavBarHeight = OplusNavigationBarView.pxToDp(height, ((FrameLayout) oplusNavigationBarView).mContext);
                OplusNavigationBarView oplusNavigationBarView2 = OplusNavigationBarView.this;
                int i = oplusNavigationBarView2.mNavBarHeight;
                OplusNavigationBarView.m624$$Nest$mgetDrawableParams(oplusNavigationBarView2, width, height);
            }
        });
    }

    private NavigationBarViewControlEx getNavigationBarViewControlEx() {
        if (this.mNavBarViewControlEx == null) {
            this.mNavBarViewControlEx = ((NavigationBarDependencyEx) DependencyEx.sDependency.getDependency(NavigationBarDependencyEx.class)).getNarBarViewControlEx();
        }
        return this.mNavBarViewControlEx;
    }

    private UtilsStaticToolsEx getUtilsStaticToolsEx() {
        if (this.mUtilsStaticToolsEx == null) {
            this.mUtilsStaticToolsEx = ((NavigationBarDependencyEx) DependencyEx.sDependency.getDependency(NavigationBarDependencyEx.class)).getUtilsStaticToolsExImpl();
        }
        return this.mUtilsStaticToolsEx;
    }

    public static int pxToDp(int i, Context context) {
        return (int) (i / context.getResources().getDisplayMetrics().density);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void dump(PrintWriter printWriter) {
        super.dump(printWriter);
        if (isOverviewEnabled$1()) {
            int i = ((NavigationBarView) this).mDisabledFlags;
            if ((2097152 & i) == 0 && (i & 4194304) == 0) {
                return;
            }
        }
        Context context = ((FrameLayout) this).mContext;
        Handler handler = OplusNotificationLogger.LOG_HANDLER;
        OplusNotificationLogger$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusNotificationLogger$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = context;
        VarHandle.storeStoreFence();
        handler.post(externalSyntheticLambda0);
    }

    public int getCurrentRotation() {
        ClipboardOverlayView$.ExternalSyntheticOutline0.m(((NavigationBarView) this).mCurrentRotation, LogUtil.TAG_NAVBAR, "OplusNavigationBarView", new StringBuilder("getCurrentRotation() = "));
        return ((NavigationBarView) this).mCurrentRotation;
    }

    public OplusNavigationBarInflaterView getInflaterView() {
        return (OplusNavigationBarInflaterView) ((NavigationBarView) this).mNavigationInflaterView;
    }

    public NavigationBarGestureUpEx getNavigationBarGestureUpEx() {
        if (this.mNavigationBarGestureUpEx == null) {
            this.mNavigationBarGestureUpEx = ((NavigationBarDependencyEx) DependencyEx.sDependency.getDependency(NavigationBarDependencyEx.class)).getNavigationBarGestureUpEx();
        }
        return this.mNavigationBarGestureUpEx;
    }

    public final void getSwitchViewRegion(Region region) {
        getNavigationBarViewControlEx().getSwitchViewRegion(region);
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((NavigationBarView) this).mEdgeBackGestureHandler.setHomeButton((QuickStepContract.isGesturalMode(((NavigationBarView) this).mNavBarMode) ? getHomeHandle() : getHomeButton()).getCurrentView());
        if (((NavigationBarView) this).mNavBarMode == 0) {
            ScreenBurnInProtector.Companion companion = ScreenBurnInProtector.Companion;
            if (!companion.getInstance().hasCallback(this)) {
                companion.getInstance().addCallback(this);
            }
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled()) {
            DeviceStateController.getInstance().addCallback(this.mFoldStateListener);
        }
        openFoldChangeRefreshView();
    }

    public final void onBurnInPreventTrigger(float f) {
        float translationX = ((NavigationBarView) this).mHorizontal.getTranslationX();
        if (Math.abs(f - translationX) > 1.0f) {
            f = f < translationX ? translationX - 1.0f : translationX + 1.0f;
        }
        ((NavigationBarView) this).mHorizontal.setTranslationX(f);
        int i = ((NavigationBarView) this).mCurrentRotation;
        View view = ((NavigationBarView) this).mVertical;
        if (i == 3) {
            view.setTranslationY(f);
        } else {
            view.setTranslationY(-f);
        }
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ScreenBurnInProtector.Companion.getInstance().removeCallback(this);
        if (getRotationButtonController() != null) {
            getRotationButtonController().setTaskStackChangedConsumer((Consumer) null);
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled()) {
            removeMessages(1000);
            removeMessages(TipsManager.DELAY_SING_TAP_HIDE_TIME);
            DeviceStateController.getInstance().removeCallback(this.mFoldStateListener);
        }
        if (hasMessages(3000)) {
            removeMessages(3000);
        }
    }

    public final void onFinishInflate() {
        getNavigationBarGestureUpEx().onFinishInflate(this);
        super.onFinishInflate();
        getInflaterView().setParentView(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void openFoldChangeRefreshView() {
        boolean zIsGetFoldOpenNavigationLayout = getNavigationBarViewControlEx().isGetFoldOpenNavigationLayout(((FrameLayout) this).mContext);
        boolean zIsEnableTaskbar = getUtilsStaticToolsEx().isEnableTaskbar(((FrameLayout) this).mContext);
        StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("isFoldOpen: ", ",mIsFoldOpen ", zIsGetFoldOpenNavigationLayout);
        sbM.append(getInflaterView().getIsFoldOpen());
        LogUtil.d(LogUtil.TAG_NAVBAR, "OplusNavigationBarView", sbM.toString());
        if (getInflaterView() == null) {
            LogUtil.d(LogUtil.TAG_NAVBAR, "OplusNavigationBarView", "getInflaterView() == null");
            return;
        }
        if (zIsEnableTaskbar && !zIsGetFoldOpenNavigationLayout && getInflaterView().getIsFoldOpen()) {
            LogUtil.d(LogUtil.TAG_NAVBAR, "OplusNavigationBarView", "openFoldChangeRefreshView resizeLayout ");
            AnonymousClass1 anonymousClass1 = this.mHandler;
            OplusNavigationBarView$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusNavigationBarView$.ExternalSyntheticLambda0(1);
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            anonymousClass1.post(externalSyntheticLambda0);
        }
    }

    public final void reorient() {
        super.reorient();
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isTablet()) {
            getInflaterView().onLikelyDefaultLayoutChange();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void resizeView() {
        if (getInflaterView() != null) {
            getInflaterView().resizeLayout();
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled() && hasMessages(TipsManager.DELAY_SING_TAP_HIDE_TIME)) {
                removeMessages(1000);
                removeMessages(TipsManager.DELAY_SING_TAP_HIDE_TIME);
                animate().cancel();
                setAlpha(1.0f);
                LogUtil.d(LogUtil.TAG_NAVBAR, "OplusNavigationBarView", "remove MSG_RECOVER_ALPHA msg and recover alpha to 1");
            }
        }
        getNavigationBarGestureUpEx().initGestureUpGuideBarView();
        super.resizeView();
        NavigationBarTransitions navigationBarTransitions = ((NavigationBarView) this).mBarTransitions;
        BarTransitions.BarBackgroundDrawable barBackgroundDrawable = ((BarTransitions) navigationBarTransitions).mBarBackground;
        if (barBackgroundDrawable instanceof OplusBarBackgroundDrawable) {
            OplusBarBackgroundDrawable oplusBarBackgroundDrawable = (OplusBarBackgroundDrawable) barBackgroundDrawable;
            if (oplusBarBackgroundDrawable.isNavBarDrawable) {
                oplusBarBackgroundDrawable.setOpaque(navigationBarTransitions.mView.getContext());
            }
        }
    }

    public final void setNavBarMode(int i, boolean z) {
        LogUtil.d(LogUtil.TAG_NAVBAR, "OplusNavigationBarView", KeyguardLogger$.ExternalSyntheticOutline0.m("b/182478748 NavigationBarView.onNavModeChanged: mode=", i, " callers=", LogUtil.panicEnabled ? Debug.getCallers(5) : "***"));
        super.setNavBarMode(i, z);
        if (((NavigationBarView) this).mNavBarMode == 0) {
            ScreenBurnInProtector.Companion companion = ScreenBurnInProtector.Companion;
            if (!companion.getInstance().hasCallback(this)) {
                companion.getInstance().addCallback(this);
            }
        } else {
            ScreenBurnInProtector.Companion companion2 = ScreenBurnInProtector.Companion;
            if (companion2.getInstance().hasCallback(this)) {
                ((NavigationBarView) this).mHorizontal.setTranslationX(0.0f);
                ((NavigationBarView) this).mVertical.setTranslationY(0.0f);
                companion2.getInstance().removeCallback(this);
            }
        }
        if (getRotationButtonController() != null) {
            if (QuickStepContract.isGesturalMode(((NavigationBarView) this).mNavBarMode)) {
                getRotationButtonController().setTaskStackChangedConsumer((Consumer) null);
            } else {
                getRotationButtonController().setTaskStackChangedConsumer(this.mTaskStackChangedConsumer);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setNavBarPadding() {
        if (this.mIsSubDisplay) {
            LogUtil.i(LogUtil.TAG_NAVBAR, "OplusNavigationBarView", "setNavBarPadding isSubDisplay is true ");
            return;
        }
        if (this.mMaxDrawableWith == 0 || this.mMaxDrawableHeight == 0) {
            StringBuilder sb = new StringBuilder("setNavBarPadding error mMaxDrawableWith:");
            sb.append(this.mMaxDrawableWith);
            sb.append("  ,mMaxDrawableHeight:");
            EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.mMaxDrawableHeight, LogUtil.TAG_NAVBAR, "OplusNavigationBarView", sb);
            return;
        }
        int navBarPaddingP2 = getNavigationBarViewControlEx().getNavBarPaddingP2(((FrameLayout) this).mContext, this.mNavBarHeight, this.mMaxDrawableWith, this.mMaxDrawableHeight);
        if (navBarPaddingP2 > 0) {
            getInflaterView().setNavBarPadding(navBarPaddingP2);
        }
    }

    public final void updateCurrentView() {
        super.updateCurrentView();
        if (getUtilsStaticToolsEx().isFoldOpenNavigationLayout()) {
            ViewGroup viewGroup = (ViewGroup) getCurrentView().findViewById(2131364120);
            boolean z = getUtilsStaticToolsEx().getNavButtonsSwitchDirect() == 0;
            viewGroup.clearAnimation();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewGroup.getLayoutParams();
            layoutParams.gravity = z ? 8388611 : 8388613;
            viewGroup.setLayoutParams(layoutParams);
            viewGroup.setTranslationX(0.0f);
            ((NavigationBarDependencyEx) DependencyEx.sDependency.getDependency(NavigationBarDependencyEx.class)).getNarBarViewControlEx().inflatePositionSwitchView(getCurrentView(), this, ((NavigationBarView) this).mIsDisableSwitchView);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateNavButtonIcons() {
        super.updateNavButtonIcons();
        if (getNavigationBarViewControlEx().isGetFoldOpenNavigationLayout(((FrameLayout) this).mContext)) {
            ExpandHelper$.ExternalSyntheticOutline0.m(LogUtil.TAG_NAVBAR, "OplusNavigationBarView", new StringBuilder("updatePositionSwitch mIsDisableSwitchView = "), ((NavigationBarView) this).mIsDisableSwitchView);
            getNavigationBarViewControlEx().updatePositionSwitchView(((NavigationBarView) this).mIsDisableSwitchView);
        }
    }

    public final void updateSideGestureBarVisible(int i) {
        if (!getUtilsStaticToolsEx().isGestureSideMode() || ((NavigationBarView) this).mNavigationInflaterView == null) {
            return;
        }
        getHomeHandle().setVisibility(i == 0 ? 0 : 4);
        ((NavigationBarView) this).mNavigationInflaterView.setVisibility(i == 0 ? 0 : 8);
        updateWindowAlphaDelay(i != 0 ? 8 : 0);
    }

    public final void updateSlippery() {
        setSlippery(!(((NavigationBarView) this).mShowSwipeUpUi && isOverviewEnabled$1()));
    }

    public final void updateStates() {
        FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("b/182478748 updateStates, callers=", LogUtil.panicEnabled ? Debug.getCallers(5) : "***", LogUtil.TAG_NAVBAR, "OplusNavigationBarView");
        super.updateStates();
        if (getUtilsStaticToolsEx().isGestureUpMode()) {
            Executor executor = ((NavigationBarView) this).mBgExecutor;
            OplusNavigationBarView$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusNavigationBarView$.ExternalSyntheticLambda0(0);
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            executor.execute(externalSyntheticLambda0);
        }
    }

    public final void updateSwipeUpGestureBarMode() {
        if (getUtilsStaticToolsEx().isGestureUpMode()) {
            getNavigationBarGestureUpEx().setGestureKeysMode();
        }
    }

    public final void updateSwipeUpGestureBarVisible(int i) {
        if (getUtilsStaticToolsEx().isGestureUpMode()) {
            getNavigationBarGestureUpEx().setVisibility(i == 0 ? 0 : 8);
            updateWindowAlpha(i == 0 ? 0 : 8);
        }
    }

    public final void updateViewVisible$1() {
        if (!getUtilsStaticToolsEx().isHideNavBarGestureMode()) {
            getNavigationBarGestureUpEx().setVisibility(8);
            ((NavigationBarView) this).mNavigationInflaterView.setVisibility(0);
            updateWindowAlphaDelay(0);
            return;
        }
        ((NavigationBarView) this).mNavigationInflaterView.setVisibility(8);
        if (!getUtilsStaticToolsEx().isGestureUpMode()) {
            getNavigationBarGestureUpEx().setVisibility(8);
            updateWindowAlphaDelay(8);
        } else {
            boolean showGuideBar = getNavigationBarGestureUpEx().getShowGuideBar();
            getNavigationBarGestureUpEx().setVisibility(showGuideBar ? 0 : 8);
            updateWindowAlpha(showGuideBar ? 0 : 8);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateWindowAlpha(int i) {
        if (getParent() == null || !isAttachedToWindow()) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = ((View) getParent()).getLayoutParams();
        if (!(layoutParams instanceof WindowManager.LayoutParams)) {
            LogUtil.d(LogUtil.TAG_NAVBAR, "OplusNavigationBarView", "updateWindowAlpha not LayoutParams");
            return;
        }
        WindowManager.LayoutParams layoutParams2 = (WindowManager.LayoutParams) layoutParams;
        float f = layoutParams2.alpha;
        layoutParams2.alpha = i == 0 ? 1.0f : 0.0f;
        LogUtil.d(LogUtil.TAG_NAVBAR, "OplusNavigationBarView", "updateWindowAlpha currentFlags: " + f + ", lp.alpha: " + layoutParams2.alpha);
        if (layoutParams2.alpha != f) {
            this.mWindowManager.updateViewLayout((View) getParent(), layoutParams2);
        }
    }

    public final void updateWindowAlphaDelay(int i) {
        if (hasMessages(3000)) {
            removeMessages(3000);
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 3000;
        messageObtain.arg1 = i;
        sendMessageDelayed(messageObtain, 350L);
    }
}
