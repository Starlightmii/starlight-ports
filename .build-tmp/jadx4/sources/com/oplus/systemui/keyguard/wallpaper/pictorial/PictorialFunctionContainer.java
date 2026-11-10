package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.viewpager.widget.ViewPager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.wallpaper.KeyguardWallpaperInfo;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBottomViewHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager;
import com.oplus.systemui.keyguard.wallpaper.pictorial.tips.OnTipsListener;
import com.oplus.systemui.keyguard.wallpaper.pictorial.tips.PictorialEntryView;
import com.oplus.systemui.keyguard.watcher.OplusKeyguardWatcher;
import com.oplus.systemui.keyguard.watcher.PictorialStateWatcher;
import com.oplus.systemui.keyguard.watcher.WallpaperChangeWatcher;
import com.oplus.systemui.scene.OplusSceneModeObserver;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class PictorialFunctionContainer extends FrameLayout implements IKeyguardSliderPager, WallpaperChangeWatcher.Observer, PictorialMediator.IPictorialMediatorCallback, ConfigurationController.ConfigurationListener {
    public static volatile Method sSetMeasuredDimensionRawMethod;
    public static volatile boolean sSetMeasuredDimensionRawResolved;
    public final float mAnimAlpha;
    public boolean mBouncerShow;
    public float mCurrentProcessY;
    public boolean mIsImmersive;
    public KeyguardUpdateMonitorCallback mMonitorCallback;
    public final AnonymousClass5 mObserver;
    public final AnonymousClass3 mOplusKeyguardObserver;
    public final PictorialEntryView mPictorialEntryView;
    public PictorialMediator mPictorialMediator;
    public PictorialBottomViewHelper mPictorialMessageViewHelper;
    public final AnonymousClass4 mPictorialStateObserver;
    public boolean mPreloadViewLoadSuccess;
    public final AnonymousClass1 mStateListener;
    public KeyguardUpdateMonitor mUpdateMonitor;

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialFunctionContainer$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements StatusBarStateController.StateListener {
        public /* synthetic */ PictorialFunctionContainer this$0;

        public final void onStateChanged(int i) {
            PictorialFunctionContainer pictorialFunctionContainer = this.this$0;
            Method method = PictorialFunctionContainer.sSetMeasuredDimensionRawMethod;
            pictorialFunctionContainer.updateTipsViewState();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialFunctionContainer$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 extends KeyguardUpdateMonitorCallback {
        public /* synthetic */ PictorialFunctionContainer this$0;

        public final void onDreamingStateChanged(boolean z) {
            PictorialFunctionContainer pictorialFunctionContainer = this.this$0;
            if (SmoothTransitionController.isPanoramicAodEnable(((FrameLayout) pictorialFunctionContainer).mContext)) {
                pictorialFunctionContainer.setVisibility(0);
            } else {
                pictorialFunctionContainer.setVisibility(z ? 4 : 0);
            }
        }

        public final void onKeyguardBouncerStateChanged(boolean z) {
            PictorialFunctionContainer pictorialFunctionContainer = this.this$0;
            pictorialFunctionContainer.mBouncerShow = z;
            pictorialFunctionContainer.updateTipsViewState();
        }

        public final void onKeyguardVisibilityChanged(boolean z) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("onKeyguardVisibilityChanged showing = ", "PictorialFunctionContainer", z);
            PictorialFunctionContainer pictorialFunctionContainer = this.this$0;
            Method method = PictorialFunctionContainer.sSetMeasuredDimensionRawMethod;
            pictorialFunctionContainer.updateTipsViewState();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialFunctionContainer$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements OplusKeyguardWatcher.Observer {
        public /* synthetic */ PictorialFunctionContainer this$0;

        @Override // com.oplus.systemui.keyguard.watcher.OplusKeyguardWatcher.Observer
        public final void onOplusKeyguardLoad(boolean z) {
            PictorialFunctionContainer pictorialFunctionContainer = this.this$0;
            Method method = PictorialFunctionContainer.sSetMeasuredDimensionRawMethod;
            pictorialFunctionContainer.updateViewState();
        }

        @Override // com.oplus.systemui.keyguard.watcher.OplusKeyguardWatcher.Observer
        public final void onSetBackground(Drawable drawable) {
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialFunctionContainer$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass4 implements PictorialStateWatcher.Observer {
        public /* synthetic */ PictorialFunctionContainer this$0;

        @Override // com.oplus.systemui.keyguard.watcher.PictorialStateWatcher.Observer
        public final void onPictorialPreloadViewLoad(boolean z) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("onPictorialPreloadViewLoad success = ", "PictorialFunctionContainer", z);
            PictorialFunctionContainer pictorialFunctionContainer = this.this$0;
            pictorialFunctionContainer.mPreloadViewLoadSuccess = z;
            pictorialFunctionContainer.updateTipsViewState();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialFunctionContainer$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements WakefulnessLifecycle.Observer {
        public /* synthetic */ PictorialFunctionContainer this$0;

        public final void onFinishedWakingUp() {
            PictorialFunctionContainer pictorialFunctionContainer = this.this$0;
            KeyguardUpdateMonitor keyguardUpdateMonitor = pictorialFunctionContainer.mUpdateMonitor;
            boolean z = keyguardUpdateMonitor != null && keyguardUpdateMonitor.mIsDreaming;
            int screenState = ((ScreenLifecycle) Dependency.sDependency.getDependencyInner(ScreenLifecycle.class)).getScreenState();
            if (z && screenState == 2) {
                return;
            }
            pictorialFunctionContainer.setVisibility(0);
        }

        public final void onStartedGoingToSleep() {
            PictorialFunctionContainer pictorialFunctionContainer = this.this$0;
            if (SmoothTransitionController.isPanoramicAodEnable(((FrameLayout) pictorialFunctionContainer).mContext)) {
                pictorialFunctionContainer.setVisibility(0);
            } else {
                pictorialFunctionContainer.setVisibility(4);
            }
        }
    }

    public static String $r8$lambda$opIDy9KUL1wyN1uHDdzxfMxM2zs(PictorialFunctionContainer pictorialFunctionContainer) {
        StringBuilder sb = new StringBuilder("mPreloadViewLoadSuccess = ");
        sb.append(pictorialFunctionContainer.mPreloadViewLoadSuccess);
        sb.append(", isKeyguardShowing = ");
        KeyguardUpdateMonitor keyguardUpdateMonitor = pictorialFunctionContainer.mUpdateMonitor;
        sb.append(keyguardUpdateMonitor != null && keyguardUpdateMonitor.mKeyguardShowing);
        sb.append(", !mBouncerShow = ");
        sb.append(!pictorialFunctionContainer.mBouncerShow);
        sb.append(", isInBrickMode = ");
        sb.append(OplusSceneModeObserver.Companion.getInstance(((FrameLayout) pictorialFunctionContainer).mContext).isInBrickMode);
        return sb.toString();
    }

    public PictorialFunctionContainer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mStateListener = anonymousClass1;
        this.mAnimAlpha = 1.0f;
        this.mIsImmersive = false;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mMonitorCallback = anonymousClass2;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        this.mOplusKeyguardObserver = anonymousClass3;
        AnonymousClass4 anonymousClass4 = new AnonymousClass4();
        anonymousClass4.this$0 = this;
        VarHandle.storeStoreFence();
        this.mPictorialStateObserver = anonymousClass4;
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        anonymousClass5.this$0 = this;
        VarHandle.storeStoreFence();
        this.mObserver = anonymousClass5;
        setLayoutParams(new ViewPager.LayoutParams());
        setVisibility(0);
        setTag(102);
        this.mPictorialEntryView = new PictorialEntryView(((FrameLayout) this).mContext);
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(2131168806);
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(2131168807);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 53;
        layoutParams.setMarginsRelative(0, dimensionPixelSize2, dimensionPixelSize, 0);
        this.mPictorialEntryView.setImageResource(2131235181);
        this.mPictorialEntryView.setLayoutParams(layoutParams);
        this.mPictorialEntryView.setVisibility(8);
        addView(this.mPictorialEntryView);
        OplusKeyguardWatcher.Companion.getInstance().addCallback((OplusKeyguardWatcher.Observer) anonymousClass3);
    }

    private static Method getSetMeasuredDimensionRawMethod() {
        if (sSetMeasuredDimensionRawMethod == null && !sSetMeasuredDimensionRawResolved) {
            try {
                try {
                    Class<?> cls = Class.forName("android.view.View");
                    Class<?> cls2 = Integer.TYPE;
                    Method declaredMethod = cls.getDeclaredMethod("setMeasuredDimensionRaw", cls2, cls2);
                    declaredMethod.setAccessible(true);
                    sSetMeasuredDimensionRawMethod = declaredMethod;
                } catch (Exception e) {
                    KgdLogUtil.i("PictorialFunctionContainer", "getSetMeasuredDimensionRawMethod err=" + e.getMessage());
                }
            } finally {
                sSetMeasuredDimensionRawResolved = true;
            }
        }
        return sSetMeasuredDimensionRawMethod;
    }

    public static void setMessageViewWidth(View view, int i, int i2) {
        try {
            Method setMeasuredDimensionRawMethod = getSetMeasuredDimensionRawMethod();
            if (setMeasuredDimensionRawMethod != null) {
                setMeasuredDimensionRawMethod.invoke(view, Integer.valueOf(i), Integer.valueOf(i2));
            } else {
                KgdLogUtil.w("PictorialFunctionContainer", "setMessageViewWidth skip: setMeasuredDimensionRaw unavailable");
            }
        } catch (Exception e) {
            KgdLogUtil.i("PictorialFunctionContainer", "Exception =" + e.getMessage());
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final void attached() {
        this.mUpdateMonitor = (KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class);
        PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        this.mPictorialMediator = pictorialMediator;
        this.mPictorialMessageViewHelper = pictorialMediator.getPictorialFactory().getPictorialBottomViewHelper();
        this.mPictorialMediator.getPictorialFactory().getPictorialPreloadHelper();
        updateViewState();
        this.mUpdateMonitor.registerCallback(this.mMonitorCallback);
        this.mPictorialMediator.registerCallback(this);
        PictorialStateWatcher.getInstance().addCallback((PictorialStateWatcher.Observer) this.mPictorialStateObserver);
        WallpaperChangeWatcher.getInstance().addCallback((WallpaperChangeWatcher.Observer) this);
        ((SysuiStatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).addCallback(this.mStateListener, 2);
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getConfigurationController().addCallback(this);
        OplusKeyguardWatcher.Companion.getInstance().addCallback((OplusKeyguardWatcher.Observer) this.mOplusKeyguardObserver);
        ((WakefulnessLifecycle) Dependency.sDependency.getDependencyInner(WakefulnessLifecycle.class)).addObserver(this.mObserver);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final void detached() {
        this.mPictorialEntryView.mCreate = false;
        this.mPictorialMediator.unregisterCallback(this);
        WallpaperChangeWatcher.getInstance().removeCallback((WallpaperChangeWatcher.Observer) this);
        PictorialStateWatcher.getInstance().removeCallback((PictorialStateWatcher.Observer) this.mPictorialStateObserver);
        this.mUpdateMonitor.removeCallback(this.mMonitorCallback);
        ((StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).removeCallback(this.mStateListener);
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getConfigurationController().removeCallback(this);
        OplusKeyguardWatcher.Companion.getInstance().removeCallback((OplusKeyguardWatcher.Observer) this.mOplusKeyguardObserver);
        ((WakefulnessLifecycle) Dependency.sDependency.getDependencyInner(WakefulnessLifecycle.class)).removeObserver(this.mObserver);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final int direction() {
        return 1;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public View getView() {
        return this;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final void hide() {
        updateTipsViewState();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator.IPictorialMediatorCallback
    public final void onFingerPrintIconChanged() {
        this.mPictorialMessageViewHelper.updatePictorialGuideView(((FrameLayout) this).mContext, this);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator.IPictorialMediatorCallback
    public final void onMessageViewInfoChanged(PictorialMessageViewInfo pictorialMessageViewInfo) {
        if (pictorialMessageViewInfo != null) {
            KgdLogUtil.i("PictorialFunctionContainer", "updateMessageViewInfo info = " + pictorialMessageViewInfo);
            PictorialEntryView pictorialEntryView = this.mPictorialEntryView;
            pictorialEntryView.mViewInfo = pictorialMessageViewInfo;
            Drawable entryDrawable = pictorialMessageViewInfo.getEntryDrawable();
            if (entryDrawable != null) {
                pictorialEntryView.setImageDrawable(entryDrawable);
            }
            pictorialEntryView.updateTipsState();
            updateTipsViewState();
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final void onPageScrollStateChanged(int i) {
        if (i == 1 || i == 2) {
            updateTipsViewState();
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final void onPageScrolled(float f, int i) {
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final void onPageSelected(boolean z) {
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator.IPictorialMediatorCallback
    public final void onPictorialGuideInfoChanged(PictorialGuideEntity pictorialGuideEntity) {
        KgdLogUtil.i("PictorialFunctionContainer", "onPictorialGuideInfoChanged info = " + pictorialGuideEntity);
        this.mPictorialMessageViewHelper.onPictorialGuideInfoChanged(((FrameLayout) this).mContext, pictorialGuideEntity, this);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator.IPictorialMediatorCallback
    public final void onSwitchPictorialApp() {
        this.mPictorialMessageViewHelper = this.mPictorialMediator.getPictorialFactory().getPictorialBottomViewHelper();
        this.mPictorialMediator.getPictorialFactory().getPictorialPreloadHelper();
        onPictorialGuideInfoChanged(null);
    }

    @Override // com.oplus.systemui.keyguard.watcher.WallpaperChangeWatcher.Observer
    public final void onWallpaperChange(KeyguardWallpaperInfo keyguardWallpaperInfo) {
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final int pagerType() {
        return 102;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public void setImmersive(boolean z) {
        this.mIsImmersive = z;
    }

    public void setOnTipsListener(OnTipsListener onTipsListener) {
        this.mPictorialEntryView.setOnTipsListener(onTipsListener);
    }

    /* JADX WARN: Code duplicated, block: B:4:0x0005 A[PHI: r0
      0x0005: PHI (r0v6 float) = (r0v0 float), (r0v1 float) binds: [B:3:0x0003, B:6:0x000b] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public void setProcessY(float f) {
        float f2 = 0.0f;
        if (f < 0.0f) {
            f = f2;
        } else {
            f2 = 1.0f;
            if (f > 1.0f) {
                f = f2;
            }
        }
        KeyguardUtils.Companion companion = KeyguardUtils.Companion;
        float f3 = this.mCurrentProcessY;
        companion.getClass();
        if (KeyguardUtils.Companion.isFloatEqual(f, f3)) {
            return;
        }
        setAlpha(Math.min(this.mAnimAlpha, f));
        this.mCurrentProcessY = f;
        updateTipsViewState();
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public /* bridge */ /* synthetic */ void setQsExpanded(boolean z) {
        super.setQsExpanded(z);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.slider.IKeyguardSliderPager
    public final void show() {
        updateTipsViewState();
    }

    public final void updateTipsViewState() {
        KeyguardUpdateMonitor keyguardUpdateMonitor;
        PictorialFunctionContainer$$ExternalSyntheticLambda0 pictorialFunctionContainer$$ExternalSyntheticLambda0 = new PictorialFunctionContainer$$ExternalSyntheticLambda0();
        pictorialFunctionContainer$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        KgdLogUtil.d("PictorialFunctionContainer", "pict_tips_view", pictorialFunctionContainer$$ExternalSyntheticLambda0);
        this.mPictorialEntryView.setAllowStates((!this.mPreloadViewLoadSuccess || (keyguardUpdateMonitor = this.mUpdateMonitor) == null || !keyguardUpdateMonitor.mKeyguardShowing || this.mBouncerShow || this.mIsImmersive || OplusSceneModeObserver.Companion.getInstance(((FrameLayout) this).mContext).isInBrickMode) ? false : true);
        this.mPictorialEntryView.updateTipsState();
    }

    public final void updateViewState() {
        boolean z = KeyguardUtils.Companion.getKeyguardUtils(((FrameLayout) this).mContext).isOplusKeyguardLoad;
        PictorialEntryView pictorialEntryView = this.mPictorialEntryView;
        if (z) {
            pictorialEntryView.mCreate = true;
        } else {
            pictorialEntryView.mCreate = false;
        }
        updateTipsViewState();
    }

    public PictorialFunctionContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public PictorialFunctionContainer(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, -1);
    }

    public PictorialFunctionContainer(Context context) {
        this(context, null);
    }
}
