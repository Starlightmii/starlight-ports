package com.oplus.systemui.keyguard.wallpaper.pictorial.slider;

import android.R;
import android.animation.ArgbEvaluator;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Looper;
import android.os.Trace;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.collection.FloatList$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.internal.widget.ViewPager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.keyguard.OplusLockIconView;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.OplusLockIconViewControllerEx;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.biometrics.ui.PromptIconState$;
import com.android.systemui.keyguard.OplusKeyguardBottomAreaControllerEx;
import com.android.systemui.keyguard.OplusKeyguardViewMediatorEx;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.NotificationPanelView;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.charge.viewmodel.OplusChargeAnimController$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.aod.aodclock.constant.AodData;
import com.oplus.systemui.keyguard.view.CustomOplusKeyguardStyleClock;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialFunctionContainer;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialPreloadView;
import com.oplus.systemui.keyguard.wallpaper.uiengine.KeyguardEngineContainer;
import com.oplus.systemui.keyguard.watcher.PictorialStateWatcher;
import com.oplus.systemui.scene.OplusSceneModeObserver;
import com.oplus.systemui.statusbar.widget.SysuiLifecycle;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.util.BrandUtils;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class KeyguardSliderLayout extends ViewPager implements PictorialStateWatcher.Observer, ConfigurationController.ConfigurationListener {
    public static volatile Method sSetMeasuredDimensionRawMethod;
    public static volatile boolean sSetMeasuredDimensionRawResolved;
    public final ContentResolver mContentResolver;
    public int mCurOffset;
    public final int mGlanceDark;
    public final KeyguardSliderLayout$$ExternalSyntheticLambda1 mHideSliderLayoutRunnable;
    public boolean mIgnoreSetAlphaOnConfigChanged;
    public float mInitialTouchX;
    public float mInitialTouchY;
    public boolean mIsInSleep;
    public boolean mIsObserverRegistered;
    public boolean mIsPointUp;
    public boolean mIsTouchMoving;
    public KeyguardEngineContainer mKeyguardEngineContainer;
    public final KeyguardSliderAdapter mKeyguardSliderAdapter;
    public int mLastLiveClockRemirrorOffsetPixels;
    public OplusLockIconView mLockIconContainer;
    public boolean mMainPagerSelected;
    public PictorialFunctionContainer mMainSliderPager;
    public int mMainSliderPagerIndex;
    public NotificationPanelView mNotificationPanelView;
    public NotificationStackScrollLayout.OnEmptySpaceClickListener mOnEmptySpaceClickListener;
    public final AnonymousClass3 mOnPageChangeListener;
    public final KeyguardSliderLayout$$ExternalSyntheticLambda3 mOnTipsListener;
    public OplusKeyguardViewMediatorEx mOplusKeyguardViewMediatorEx;
    public OplusLockIconViewControllerEx mOplusLockIconViewController;
    public View mPictorialDynamicView;
    public PictorialMediator mPictorialMediator;
    public PictorialPreloadView mPictorialPreloadViewOnLeft;
    public PictorialPreloadView mPictorialPreloadViewOnRight;
    public boolean mQsExpand;
    public View mQsFrame;
    public final KeyguardSliderLayout$$ExternalSyntheticLambda1 mReloadKeyguardSlideLayoutRunnable;
    public boolean mReseting;
    public int mScreenHeight;
    public int mScreenWidth;
    public int mSelectPage;
    public final List mSlidePagers;
    public boolean mSliding;
    public NotificationStackScrollLayout mStackScrollLayout;
    public final AnonymousClass1 mStateListener;
    public final int mStatusBarHeight;
    public int mStatusBarState;
    public CustomOplusKeyguardStyleClock mStyleClock;
    public boolean mTouchIsClick;
    public final int mTouchSlop;
    public final KeyguardSliderPageTransformer mTransformer;
    public KeyguardUpdateMonitor mUpdateMonitor;
    public final KeyguardUpdateMonitorCallback mUpdateMonitorCallback;

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.slider.KeyguardSliderLayout$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements StatusBarStateController.StateListener {
        public /* synthetic */ KeyguardSliderLayout this$0;

        public final void onStateChanged(int i) {
            this.this$0.mStatusBarState = i;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.slider.KeyguardSliderLayout$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 extends KeyguardUpdateMonitorCallback {
        public /* synthetic */ KeyguardSliderLayout this$0;

        public final void onDeviceProvisioned() {
            this.this$0.updateSliderPagers(2);
        }

        public final void onKeyguardOccludeChange(boolean z, boolean z2) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("onKeyguardOccludeChange occluded= ", "KeyguardSliderLayout", z);
            KeyguardSliderLayout keyguardSliderLayout = this.this$0;
            if ((keyguardSliderLayout.mMainPagerSelected && keyguardSliderLayout.mCurOffset == 0) || z) {
                return;
            }
            keyguardSliderLayout.resetPosition();
        }

        public final void onKeyguardVisibilityChanged(boolean z) {
            KeyguardSliderLayout keyguardSliderLayout = this.this$0;
            if (z) {
                keyguardSliderLayout.removeCallbacks(keyguardSliderLayout.mHideSliderLayoutRunnable);
                keyguardSliderLayout.setVisibility(0);
                keyguardSliderLayout.mMainPagerSelected = true;
                ((ArrayList) keyguardSliderLayout.mSlidePagers).forEach(new KeyguardSliderLayout$$ExternalSyntheticLambda6(1));
                return;
            }
            keyguardSliderLayout.resetPosition();
            if (keyguardSliderLayout.mUpdateMonitor == null || keyguardSliderLayout.mPictorialMediator == null) {
                return;
            }
            if (!keyguardSliderLayout.mSlidePagers.isEmpty()) {
                keyguardSliderLayout.postDelayed(keyguardSliderLayout.mHideSliderLayoutRunnable, 80L);
                return;
            }
            boolean z2 = (keyguardSliderLayout.isKeyguardPictorialEnable() && keyguardSliderLayout.mPictorialMediator.isLeftSwipeDisplaySwitchOn()) || keyguardSliderLayout.mPictorialMediator.isMainSwitchOffAndCanLeftSwipe();
            KeyguardUpdateMonitor keyguardUpdateMonitor = keyguardSliderLayout.mUpdateMonitor;
            if (keyguardUpdateMonitor.mDeviceProvisioned) {
                if (keyguardUpdateMonitor.mUserManager.isUserUnlocked(KeyguardUpdateMonitor.getCurrentUser()) && z2) {
                    KgdLogUtil.i("KeyguardSliderLayout", "check pictorial connect!");
                    keyguardSliderLayout.removeCallbacks(keyguardSliderLayout.mReloadKeyguardSlideLayoutRunnable);
                    keyguardSliderLayout.postDelayed(keyguardSliderLayout.mReloadKeyguardSlideLayoutRunnable, 500L);
                }
            }
        }

        public final void onStartedGoingToSleep(int i) {
            KeyguardSliderLayout keyguardSliderLayout = this.this$0;
            keyguardSliderLayout.mIsInSleep = true;
            keyguardSliderLayout.mIsTouchMoving = false;
            if (!keyguardSliderLayout.mMainPagerSelected || keyguardSliderLayout.mCurOffset != 0) {
                keyguardSliderLayout.resetPosition();
            }
            keyguardSliderLayout.mMainPagerSelected = true;
        }

        public final void onStartedWakingUp() {
            this.this$0.mIsInSleep = false;
        }

        public final void onUserSwitchComplete(int i) {
            this.this$0.updateSliderPagers(4);
        }

        public final void onUserUnlocked() {
            KeyguardSliderLayout keyguardSliderLayout = this.this$0;
            keyguardSliderLayout.removeCallbacks(keyguardSliderLayout.mReloadKeyguardSlideLayoutRunnable);
            boolean zIsNeedDelayUnlock$1 = keyguardSliderLayout.mPictorialMediator.isNeedDelayUnlock$1();
            KeyguardSliderLayout$$ExternalSyntheticLambda1 keyguardSliderLayout$$ExternalSyntheticLambda1 = keyguardSliderLayout.mReloadKeyguardSlideLayoutRunnable;
            if (zIsNeedDelayUnlock$1) {
                keyguardSliderLayout.postDelayed(keyguardSliderLayout$$ExternalSyntheticLambda1, 2000L);
            } else {
                keyguardSliderLayout.postDelayed(keyguardSliderLayout$$ExternalSyntheticLambda1, 500L);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.slider.KeyguardSliderLayout$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements ViewPager.OnPageChangeListener {
        public /* synthetic */ KeyguardSliderLayout this$0;

        public final void onPageScrollStateChanged(int i) {
            KgdLogUtil.d("KeyguardSliderLayout", "onPageScrollStateChanged:" + i);
            List list = this.this$0.mSlidePagers;
            KeyguardSliderLayout$3$$ExternalSyntheticLambda0 keyguardSliderLayout$3$$ExternalSyntheticLambda0 = new KeyguardSliderLayout$3$$ExternalSyntheticLambda0();
            keyguardSliderLayout$3$$ExternalSyntheticLambda0.f$0 = i;
            VarHandle.storeStoreFence();
            ((ArrayList) list).forEach(keyguardSliderLayout$3$$ExternalSyntheticLambda0);
            this.this$0.mSliding = i == 1 || i == 2;
            if (i == 1) {
                ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusKeyguardBottomAreaController().onSliderPageDragStarted();
            }
            if (i == 0) {
                KeyguardSliderLayout keyguardSliderLayout = this.this$0;
                if (!keyguardSliderLayout.mMainPagerSelected || keyguardSliderLayout.mMainSliderPager == null) {
                    return;
                }
                if (!KeyguardSliderLayout.isViewHorizontallyOffset(keyguardSliderLayout.mLockIconContainer) && !KeyguardSliderLayout.isViewHorizontallyOffset(keyguardSliderLayout.mKeyguardEngineContainer) && !KeyguardSliderLayout.isViewHorizontallyOffset(keyguardSliderLayout.mPictorialDynamicView)) {
                    NotificationPanelView notificationPanelView = keyguardSliderLayout.mNotificationPanelView;
                    if (notificationPanelView == null) {
                        return;
                    }
                    int childCount = notificationPanelView.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        View childAt = notificationPanelView.getChildAt(i2);
                        if (childAt == keyguardSliderLayout || !KeyguardSliderLayout.isViewHorizontallyOffset(childAt)) {
                        }
                    }
                    return;
                }
                KgdLogUtil.i("KeyguardSliderLayout", "onPageScrollStateChanged IDLE with offset, reset position");
                KeyguardSliderLayout keyguardSliderLayout2 = this.this$0;
                keyguardSliderLayout2.pageScrolled(keyguardSliderLayout2.mMainSliderPager, 0.0f, 0);
            }
        }

        public final void onPageScrolled(int i, float f, int i2) {
        }

        public final void onPageSelected(int i) {
            KeyguardSliderLayout keyguardSliderLayout = this.this$0;
            List list = keyguardSliderLayout.mSlidePagers;
            IKeyguardSliderPager iKeyguardSliderPager = (list == null || list.isEmpty()) ? null : (IKeyguardSliderPager) ((ArrayList) keyguardSliderLayout.mSlidePagers).get(i);
            if (iKeyguardSliderPager != null) {
                iKeyguardSliderPager.onPageSelected(true);
                for (IKeyguardSliderPager iKeyguardSliderPager2 : this.this$0.mSlidePagers) {
                    if (iKeyguardSliderPager2 != iKeyguardSliderPager) {
                        iKeyguardSliderPager2.onPageSelected(false);
                    }
                }
                this.this$0.mMainPagerSelected = iKeyguardSliderPager.pagerType() == 102;
            }
            this.this$0.mSelectPage = i;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class KeyguardSliderPageTransformer implements ViewPager.PageTransformer {
        public /* synthetic */ KeyguardSliderLayout this$0;

        public final void transformPage(View view, float f) {
            if (view != null && view.getVisibility() == 0) {
                int width = view.getWidth();
                boolean z = view instanceof PictorialFunctionContainer;
                KeyguardSliderLayout keyguardSliderLayout = this.this$0;
                if (z) {
                    KeyguardUpdateMonitor keyguardUpdateMonitor = keyguardSliderLayout.mUpdateMonitor;
                    if ((keyguardUpdateMonitor == null || keyguardUpdateMonitor.isKeyguardVisible$1()) && !keyguardSliderLayout.mIsInSleep) {
                        KeyguardSliderLayout keyguardSliderLayout2 = this.this$0;
                        keyguardSliderLayout2.pageScrolled(keyguardSliderLayout2.mMainSliderPager, f, (int) (width * f));
                        return;
                    } else {
                        KeyguardSliderLayout keyguardSliderLayout3 = this.this$0;
                        keyguardSliderLayout3.pageScrolled(keyguardSliderLayout3.mMainSliderPager, 0.0f, 0);
                        return;
                    }
                }
                if (view == keyguardSliderLayout.mPictorialPreloadViewOnLeft) {
                    KeyguardUtils.Companion.getClass();
                    if (KeyguardUtils.Companion.isFloatEqual(1.0f, f)) {
                        f = -1.0f;
                    }
                    transformView(f, view);
                    KeyguardSliderLayout keyguardSliderLayout4 = this.this$0;
                    keyguardSliderLayout4.pageScrolled(keyguardSliderLayout4.mPictorialPreloadViewOnLeft, f, (int) (width * f));
                    return;
                }
                if (view == keyguardSliderLayout.mPictorialPreloadViewOnRight) {
                    KeyguardUtils.Companion.getClass();
                    if (KeyguardUtils.Companion.isFloatEqual(-1.0f, f)) {
                        f = 1.0f;
                    }
                    transformView(f, view);
                    KeyguardSliderLayout keyguardSliderLayout5 = this.this$0;
                    keyguardSliderLayout5.pageScrolled(keyguardSliderLayout5.mPictorialPreloadViewOnRight, f, (int) (width * f));
                }
            }
        }

        public final void transformView(float f, View view) {
            int measuredWidth = view instanceof PictorialPreloadView ? this.this$0.mScreenWidth : view.getMeasuredWidth();
            if (Math.abs(f) <= 1.0f) {
                view.setTranslationX(((measuredWidth * 0.20000005f) / 2.0f) * f);
                float fAbs = ((1.0f - Math.abs(f)) * (-0.20000005f)) + 1.2f;
                view.setScaleX(fAbs);
                view.setScaleY(fAbs);
            }
        }
    }

    public KeyguardSliderLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIgnoreSetAlphaOnConfigChanged = false;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mStateListener = anonymousClass1;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mUpdateMonitorCallback = anonymousClass2;
        this.mSlidePagers = new ArrayList();
        this.mReseting = false;
        this.mLastLiveClockRemirrorOffsetPixels = Integer.MIN_VALUE;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        this.mOnPageChangeListener = anonymousClass3;
        KeyguardSliderLayout$$ExternalSyntheticLambda1 keyguardSliderLayout$$ExternalSyntheticLambda1 = new KeyguardSliderLayout$$ExternalSyntheticLambda1(0);
        keyguardSliderLayout$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.mHideSliderLayoutRunnable = keyguardSliderLayout$$ExternalSyntheticLambda1;
        KeyguardSliderLayout$$ExternalSyntheticLambda1 keyguardSliderLayout$$ExternalSyntheticLambda2 = new KeyguardSliderLayout$$ExternalSyntheticLambda1(1);
        keyguardSliderLayout$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.mReloadKeyguardSlideLayoutRunnable = keyguardSliderLayout$$ExternalSyntheticLambda2;
        KeyguardSliderLayout$$ExternalSyntheticLambda3 keyguardSliderLayout$$ExternalSyntheticLambda3 = new KeyguardSliderLayout$$ExternalSyntheticLambda3();
        keyguardSliderLayout$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.mOnTipsListener = keyguardSliderLayout$$ExternalSyntheticLambda3;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mGlanceDark = context.getColor(2131101636);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mContentResolver = context.getContentResolver();
        FeatureOption.INSTANCE.getClass();
        int[] defaultScreenSize = (FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled()) ? KeyguardUtils.Companion.getKeyguardUtils(context).getDefaultScreenSize() : KeyguardUtils.Companion.getKeyguardUtils(context).getRealScreenSize();
        int i = defaultScreenSize[0];
        int i2 = defaultScreenSize[1];
        if (!FeatureOption.isLargeScreenFolding()) {
            this.mScreenWidth = Math.min(i, i2);
            this.mScreenHeight = Math.max(i, i2);
        } else if (FeatureOption.isWhiteSwanFoldDevice()) {
            this.mScreenWidth = i;
            this.mScreenHeight = i2;
        } else {
            int[] iArrNormalizeRealSizeToOrientation = normalizeRealSizeToOrientation(i, i2, context.getResources().getConfiguration().orientation);
            this.mScreenWidth = iArrNormalizeRealSizeToOrientation[0];
            this.mScreenHeight = iArrNormalizeRealSizeToOrientation[1];
        }
        this.mStatusBarHeight = getResources().getDimensionPixelOffset(R.dimen.text_size_subhead_material);
        KeyguardSliderAdapter keyguardSliderAdapter = new KeyguardSliderAdapter();
        keyguardSliderAdapter.mPagers = new ArrayList();
        this.mKeyguardSliderAdapter = keyguardSliderAdapter;
        KeyguardSliderPageTransformer keyguardSliderPageTransformer = new KeyguardSliderPageTransformer();
        keyguardSliderPageTransformer.this$0 = this;
        VarHandle.storeStoreFence();
        this.mTransformer = keyguardSliderPageTransformer;
        setOnPageChangeListener(anonymousClass3);
        setAdapter(this.mKeyguardSliderAdapter);
    }

    private OplusLockIconViewControllerEx getLockIconViewController() {
        synchronized (this) {
            try {
                if (this.mOplusLockIconViewController == null) {
                    this.mOplusLockIconViewController = ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusLockIconViewController();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return this.mOplusLockIconViewController;
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
                    KgdLogUtil.w("KeyguardSliderLayout", "getSetMeasuredDimensionRawMethod err=" + e.getMessage());
                }
            } finally {
                sSetMeasuredDimensionRawResolved = true;
            }
        }
        return sSetMeasuredDimensionRawMethod;
    }

    public static boolean isViewHorizontallyOffset(View view) {
        return (view == null || view.getTranslationX() == 0.0f) ? false : true;
    }

    public static int[] normalizeRealSizeToOrientation(int i, int i2, int i3) {
        if (i <= 0 || i2 <= 0) {
            return new int[]{i, i2};
        }
        if (i3 == 1) {
            if (i > i2) {
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("normalizeRealSizeToOrientation: portrait, swap ", i, i2, "x", " -> ");
                sbM.append(i2);
                sbM.append("x");
                sbM.append(i);
                KgdLogUtil.i("KeyguardSliderLayout", sbM.toString());
                return new int[]{i2, i};
            }
        } else if (i3 == 2 && i < i2) {
            StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("normalizeRealSizeToOrientation: landscape, swap ", i, i2, "x", " -> ");
            sbM2.append(i2);
            sbM2.append("x");
            sbM2.append(i);
            KgdLogUtil.i("KeyguardSliderLayout", sbM2.toString());
            return new int[]{i2, i};
        }
        return new int[]{i, i2};
    }

    public static void setClockDeepSceneTranslationX(int i, View view) {
        if (view == null) {
            return;
        }
        view.setTranslationX(i * (-1));
        if (i == 0) {
            view.setClipBounds(null);
            return;
        }
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        rect.right += i;
        view.setClipBounds(rect);
    }

    private void setStyleClockDeepSceneAlphaTranslationX(int i) {
        CustomOplusKeyguardStyleClock customOplusKeyguardStyleClock = this.mStyleClock;
        if (customOplusKeyguardStyleClock == null) {
            return;
        }
        View view = customOplusKeyguardStyleClock.getKeyguardPlugin().getClockPlugin().getView(4);
        View view2 = this.mStyleClock.getKeyguardPlugin().getClockPlugin().getView(6);
        setClockDeepSceneTranslationX(i, view);
        setClockDeepSceneTranslationX(i, view2);
    }

    public final void entryIcon(boolean z) {
        if (this.mMainSliderPagerIndex == this.mSelectPage) {
            PictorialPreloadView pictorialPreloadView = this.mPictorialPreloadViewOnRight;
            if (pictorialPreloadView == null || !pictorialPreloadView.readyToShow()) {
                KgdLogUtil.i("KeyguardSliderLayout", "onClickEntry no readyToShow, return");
                return;
            }
            setCurrentItem(this.mMainSliderPagerIndex + 1);
            PictorialMediator pictorialMediator = this.mPictorialMediator;
            pictorialMediator.getClass();
            KgdLogUtil.i("PictorialMediator", "actionForClickEntryIcon");
            Bundle bundle = new Bundle();
            bundle.putInt("category", 10001);
            pictorialMediator.mIsUiEngineSwipe = z;
            bundle.putString("eventname", z ? "mag_icon_swipe" : "mag_icon_click");
            pictorialMediator.reportStatisticData(bundle);
        }
    }

    public final boolean forbidLeftSlide() {
        if (this.mUpdateMonitor.getIsSecureLockDeviceEnabled()) {
            return true;
        }
        BrandUtils.INSTANCE.getClass();
        if (BrandUtils.isRealme && ChildrenModeObserver.getInstance().mIsChildrenMode) {
            return true;
        }
        PictorialPreloadView pictorialPreloadView = this.mPictorialPreloadViewOnRight;
        return (pictorialPreloadView == null || pictorialPreloadView.readyToShow()) ? false : true;
    }

    public final boolean forbidRightSlide() {
        if (this.mUpdateMonitor.getIsSecureLockDeviceEnabled()) {
            return true;
        }
        BrandUtils.INSTANCE.getClass();
        if (BrandUtils.isRealme && ChildrenModeObserver.getInstance().mIsChildrenMode) {
            return true;
        }
        PictorialPreloadView pictorialPreloadView = this.mPictorialPreloadViewOnLeft;
        return (pictorialPreloadView == null || pictorialPreloadView.readyToShow()) ? false : true;
    }

    public final boolean goSwipeEntry() {
        boolean zIsLeftSwipeDisplaySwitchOn = this.mPictorialMediator.isLeftSwipeDisplaySwitchOn();
        boolean z = this.mStatusBarState != 1;
        boolean z2 = OplusSceneModeObserver.Companion.getInstance(((ViewPager) this).mContext).isInBrickMode;
        if (this.mMainSliderPager == null || z || this.mQsExpand || z2 || ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusKeyguardBottomAreaController().isLaunchingAffordance() || !zIsLeftSwipeDisplaySwitchOn) {
            return false;
        }
        entryIcon(true);
        KgdLogUtil.i("KeyguardSliderLayout", "goSwipeEntry");
        return true;
    }

    public final void handleEmptySpaceClick(MotionEvent motionEvent) {
        if (this.mStackScrollLayout == null || this.mOnEmptySpaceClickListener == null) {
            return;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 1) {
            if (actionMasked == 2 && this.mTouchIsClick) {
                if (Math.abs(motionEvent.getY() - this.mInitialTouchY) > this.mTouchSlop || Math.abs(motionEvent.getX() - this.mInitialTouchX) > this.mTouchSlop) {
                    this.mTouchIsClick = false;
                    return;
                }
                return;
            }
            return;
        }
        if (this.mStatusBarState == 1 && this.mTouchIsClick && !this.mQsExpand) {
            NotificationPanelViewController$.ExternalSyntheticLambda3 externalSyntheticLambda3 = this.mOnEmptySpaceClickListener;
            float f = this.mInitialTouchX;
            float f2 = this.mInitialTouchY;
            NotificationPanelViewController notificationPanelViewController = externalSyntheticLambda3.f$0;
            notificationPanelViewController.mExt.onEmptySpaceClick(f, f2, notificationPanelViewController.mBarState, notificationPanelViewController.mQsController, notificationPanelViewController.mExpandedFraction);
        }
    }

    public final boolean isKeyguardPictorialEnable() {
        PictorialMediator pictorialMediator;
        if (this.mUpdateMonitor != null && (pictorialMediator = this.mPictorialMediator) != null && ((pictorialMediator.isMainSwitchOn() || this.mPictorialMediator.isMainSwitchOffAndCanLeftSwipe()) && this.mPictorialMediator.mPictorialExist)) {
            if (this.mUpdateMonitor.mUserManager.isUserUnlocked(KeyguardUpdateMonitor.getCurrentUser()) && this.mUpdateMonitor.mDeviceProvisioned) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.mIsObserverRegistered) {
            PictorialStateWatcher pictorialStateWatcher = PictorialStateWatcher.getInstance();
            SysuiLifecycle.ViewLifecycle viewLifecycleViewAttachLifecycle = SysuiLifecycle.viewAttachLifecycle(this);
            pictorialStateWatcher.getClass();
            pictorialStateWatcher.observe(viewLifecycleViewAttachLifecycle.getLifecycle(), this);
            this.mIsObserverRegistered = true;
        }
        KeyguardUpdateMonitor keyguardUpdateMonitor = (KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class);
        this.mUpdateMonitor = keyguardUpdateMonitor;
        keyguardUpdateMonitor.registerCallback(this.mUpdateMonitorCallback);
        this.mPictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        OplusKeyguardDependencyEx oplusKeyguardDependencyEx = (OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class);
        if (oplusKeyguardDependencyEx != null) {
            this.mOplusLockIconViewController = oplusKeyguardDependencyEx.getOplusLockIconViewController();
            this.mOplusKeyguardViewMediatorEx = oplusKeyguardDependencyEx.getOplusKeyguardViewMediatorEx();
        }
        ((SysuiStatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).addCallback(this.mStateListener, 2);
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getConfigurationController().addCallback(this);
        setStatusBarWindowView(((CentralSurfacesImpl) DependencyEx.sDependency.getDependency(CentralSurfacesImpl.class)).getNotificationShadeWindowView());
        this.mPictorialMediator.getPictorialFactory().registerFingerprintTipsShowCallback();
        updateSliderPagers(-1);
    }

    public final void onConfigChanged(Configuration configuration) {
        PictorialPreloadView pictorialPreloadView;
        PictorialPreloadView pictorialPreloadView2;
        int i = ((ViewPager) this).mContext.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        int iMax = ((ViewPager) this).mContext.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isLargeScreenFolding()) {
            int iMin = Math.min(i, iMax);
            iMax = Math.max(i, iMax);
            i = iMin;
        } else if (!FeatureOption.isWhiteSwanFoldDevice()) {
            int[] iArrNormalizeRealSizeToOrientation = normalizeRealSizeToOrientation(i, iMax, configuration.orientation);
            i = iArrNormalizeRealSizeToOrientation[0];
            iMax = iArrNormalizeRealSizeToOrientation[1];
        }
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("onConfigChanged widthPixels ", i, iMax, " heightPixels ", " ScreenWidth ");
        sbM.append(this.mScreenWidth);
        sbM.append(" ScreenHeight ");
        sbM.append(this.mScreenHeight);
        sbM.append(" h: ");
        sbM.append(getMeasuredHeight());
        sbM.append(" w: ");
        sbM.append(getMeasuredWidth());
        KgdLogUtil.i("KeyguardSliderLayout", sbM.toString());
        if (this.mScreenWidth == i && this.mScreenHeight == iMax) {
            return;
        }
        KgdLogUtil.i("KeyguardSliderLayout", "Device resolution changed, update views");
        this.mScreenWidth = i;
        this.mScreenHeight = iMax;
        setChildHeight();
        KeyguardSliderPageTransformer keyguardSliderPageTransformer = this.mTransformer;
        if (keyguardSliderPageTransformer != null && (pictorialPreloadView2 = this.mPictorialPreloadViewOnRight) != null) {
            keyguardSliderPageTransformer.transformView(1.0f, pictorialPreloadView2);
        }
        KeyguardSliderPageTransformer keyguardSliderPageTransformer2 = this.mTransformer;
        if (keyguardSliderPageTransformer2 != null && (pictorialPreloadView = this.mPictorialPreloadViewOnLeft) != null) {
            keyguardSliderPageTransformer2.transformView(-1.0f, pictorialPreloadView);
        }
        if (!this.mSliding) {
            this.mIgnoreSetAlphaOnConfigChanged = true;
        }
        resetPosition();
        if (this.mIgnoreSetAlphaOnConfigChanged) {
            this.mIgnoreSetAlphaOnConfigChanged = false;
        }
        requestLayout();
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mIsObserverRegistered) {
            PictorialStateWatcher.getInstance().removeCallback((PictorialStateWatcher.Observer) this);
            this.mIsObserverRegistered = false;
        }
        ((StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).removeCallback(this.mStateListener);
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getConfigurationController().removeCallback(this);
    }

    public final boolean onHeader() {
        View view = this.mQsFrame;
        if (view != null && this.mInitialTouchX >= view.getX() && this.mInitialTouchX <= this.mQsFrame.getX() + this.mQsFrame.getWidth()) {
            float f = this.mInitialTouchY;
            if (f >= this.mStatusBarHeight && f <= 0.0f) {
                return true;
            }
        }
        return false;
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = (this.mPictorialPreloadViewOnLeft == null && this.mPictorialPreloadViewOnRight == null) ? false : true;
        boolean z2 = getChildCount() == 0;
        boolean z3 = this.mStatusBarState != 1;
        boolean z4 = KeyguardUtils.Companion.getKeyguardUtils(((ViewPager) this).mContext).isOplusKeyguardLoad;
        boolean z5 = OplusSceneModeObserver.Companion.getInstance(((ViewPager) this).mContext).isInBrickMode;
        boolean zIsLaunchingAffordance = ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusKeyguardBottomAreaController().isLaunchingAffordance();
        boolean z6 = AodData.getInstance(((ViewPager) this).mContext).mAodIsInShow;
        if (motionEvent.getAction() == 0) {
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("onInterceptTouchEvent emptyLayout:", ", notKeyguardState:", ", isOplusKeyguardLoad:", z2, z3);
            KeyguardLogger$.ExternalSyntheticOutline0.m(", isInBrickMode:", ", isLaunchingAffordance:", sbM, z4, z5);
            KeyguardLogger$.ExternalSyntheticOutline0.m(", canSwipe:", ", isAodIsInShow:", sbM, zIsLaunchingAffordance, z);
            sbM.append(z6);
            KgdLogUtil.d("KeyguardSliderLayout", sbM.toString());
        }
        if (!z2 && !z3 && !z4 && !z5 && !zIsLaunchingAffordance && z && !z6) {
            if (motionEvent.getAction() == 0) {
                this.mTouchIsClick = true;
                this.mInitialTouchX = motionEvent.getX();
                this.mInitialTouchY = motionEvent.getY();
            }
            if (!onHeader()) {
                try {
                    return super.onInterceptTouchEvent(motionEvent);
                } catch (IllegalArgumentException e) {
                    KgdLogUtil.e("KeyguardSliderLayout", "onInterceptTouchEvent return false invalid event " + e);
                }
            }
        }
        return false;
    }

    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setPaddingRelative(0, 0, 0, 0);
        setChildHeight();
    }

    @Override // com.oplus.systemui.keyguard.watcher.PictorialStateWatcher.Observer
    public final void onPictorialActivateChange() {
        updateSliderPagers(6);
    }

    @Override // com.oplus.systemui.keyguard.watcher.PictorialStateWatcher.Observer
    public final void onPictorialAppEnableChanged() {
        updateSliderPagers(11);
    }

    @Override // com.oplus.systemui.keyguard.watcher.PictorialStateWatcher.Observer
    public final void onPictorialAppReplace() {
        updateSliderPagers(8);
    }

    @Override // com.oplus.systemui.keyguard.watcher.PictorialStateWatcher.Observer
    public final void onPictorialEnableSwitchChange(boolean z) {
        updateSliderPagers(9);
    }

    @Override // com.oplus.systemui.keyguard.watcher.PictorialStateWatcher.Observer
    public final void onPictorialMainSwitchChange(boolean z) {
        updateSliderPagers(0);
    }

    @Override // com.oplus.systemui.keyguard.watcher.PictorialStateWatcher.Observer
    public final void onPictorialSwipeSwitchChange() {
        updateSliderPagers(7);
    }

    @Override // com.oplus.systemui.keyguard.watcher.PictorialStateWatcher.Observer
    public final void onSuperPowerChanged() {
        updateSliderPagers(10);
    }

    /* JADX WARN: Code duplicated, block: B:62:0x010d  */
    /* JADX WARN: Code duplicated, block: B:75:0x0136 A[Catch: all -> 0x0029, Exception -> 0x002d, IllegalArgumentException -> 0x0030, Merged into TryCatch #6 {all -> 0x0029, IllegalArgumentException -> 0x0030, Exception -> 0x002d, blocks: (B:5:0x001d, B:7:0x0022, B:18:0x0034, B:22:0x0045, B:25:0x006c, B:28:0x0072, B:34:0x008c, B:36:0x00a2, B:38:0x00a8, B:45:0x00ce, B:47:0x00d4, B:48:0x00e2, B:50:0x00e8, B:55:0x0100, B:73:0x0132, B:75:0x0136, B:83:0x0155, B:93:0x0177, B:99:0x0188, B:63:0x0110, B:67:0x011f, B:69:0x0123, B:70:0x0126, B:71:0x012b, B:104:0x0197, B:110:0x01b1, B:116:0x01de), top: B:135:0x001d }, TRY_LEAVE] */
    /* JADX WARN: Code duplicated, block: B:82:0x0153  */
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        StringBuilder sb;
        String string;
        boolean zOnTouchEvent;
        StringBuilder sb2;
        char c;
        StringBuilder sb3;
        StringBuilder sb4;
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        int actionMasked = motionEventObtain.getActionMasked();
        if (actionMasked != 2) {
            OplusLockIconView$.ExternalSyntheticOutline0.m(actionMasked, "onTouchEvent ev = ", "KeyguardSliderLayout");
        }
        try {
            boolean z = (this.mPictorialPreloadViewOnLeft == null && this.mPictorialPreloadViewOnRight == null) ? false : true;
            boolean z2 = KeyguardUtils.Companion.getKeyguardUtils(((ViewPager) this).mContext).isOplusKeyguardLoad;
            boolean z3 = this.mStatusBarState != 1;
            boolean z4 = OplusSceneModeObserver.Companion.getInstance(((ViewPager) this).mContext).isInBrickMode;
            boolean z5 = ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mPrimaryBouncerShowing;
            boolean z6 = AodData.getInstance(((ViewPager) this).mContext).mAodIsInShow;
            if (!z2 && !z3 && !this.mQsExpand && !z4 && !((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusKeyguardBottomAreaController().isLaunchingAffordance() && z && !z5 && !z6) {
                float x = motionEventObtain.getX();
                float y = motionEventObtain.getY();
                OplusKeyguardBottomAreaControllerEx oplusKeyguardBottomAreaController = ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusKeyguardBottomAreaController();
                if (actionMasked != 0 || oplusKeyguardBottomAreaController.getIconAtPosition(x, y) == null) {
                    if (motionEventObtain.getAction() == 0) {
                        this.mTouchIsClick = true;
                        this.mInitialTouchX = motionEventObtain.getX();
                        this.mInitialTouchY = motionEventObtain.getY();
                    }
                    if (!onHeader()) {
                        handleEmptySpaceClick(motionEventObtain);
                        if (actionMasked != 0) {
                            if (actionMasked == 1) {
                                this.mIsTouchMoving = false;
                                this.mIsPointUp = true;
                            } else if (actionMasked == 2) {
                                c = motionEventObtain.getX() - this.mInitialTouchX < 0.0f ? (char) 1 : (char) 2;
                                if (!this.mIsInSleep) {
                                    this.mIsTouchMoving = true;
                                }
                            } else if (actionMasked == 3) {
                                this.mIsTouchMoving = false;
                                this.mIsPointUp = true;
                            }
                            if (!this.mIsPointUp) {
                                if (c != 1 && forbidLeftSlide()) {
                                    try {
                                        motionEventObtain.recycle();
                                        return true;
                                    } catch (RuntimeException e) {
                                        e = e;
                                        sb3 = new StringBuilder("event.recycle() Exception = ");
                                    }
                                } else if (c == 2 || !forbidRightSlide()) {
                                    zOnTouchEvent = super.onTouchEvent(motionEventObtain);
                                    try {
                                        motionEventObtain.recycle();
                                        return zOnTouchEvent;
                                    } catch (RuntimeException e2) {
                                        e = e2;
                                        sb2 = new StringBuilder("event.recycle() Exception = ");
                                    }
                                } else {
                                    try {
                                        motionEventObtain.recycle();
                                        return true;
                                    } catch (RuntimeException e3) {
                                        e = e3;
                                        sb3 = new StringBuilder("event.recycle() Exception = ");
                                    }
                                }
                                sb3.append(e.getMessage());
                                string = sb3.toString();
                                return true;
                            }
                            zOnTouchEvent = super.onTouchEvent(motionEventObtain);
                            try {
                                motionEventObtain.recycle();
                                return zOnTouchEvent;
                            } catch (RuntimeException e4) {
                                e = e4;
                                sb2 = new StringBuilder("event.recycle() Exception = ");
                            }
                        } else {
                            this.mIsPointUp = false;
                            this.mIsTouchMoving = false;
                        }
                        c = 0;
                        if (!this.mIsPointUp) {
                            zOnTouchEvent = super.onTouchEvent(motionEventObtain);
                            motionEventObtain.recycle();
                            return zOnTouchEvent;
                        }
                        if (c != 1) {
                        }
                        if (c == 2) {
                        }
                        zOnTouchEvent = super.onTouchEvent(motionEventObtain);
                        motionEventObtain.recycle();
                        return zOnTouchEvent;
                    }
                    MotionEvent motionEventObtain2 = MotionEvent.obtain(motionEventObtain);
                    motionEventObtain2.setAction(3);
                    super.onTouchEvent(motionEventObtain2);
                    motionEventObtain2.recycle();
                    try {
                        motionEventObtain.recycle();
                        return false;
                    } catch (RuntimeException e5) {
                        e = e5;
                        sb4 = new StringBuilder("event.recycle() Exception = ");
                    }
                } else {
                    MotionEvent motionEventObtain3 = MotionEvent.obtain(motionEventObtain);
                    motionEventObtain3.setAction(3);
                    super.onTouchEvent(motionEventObtain3);
                    motionEventObtain3.recycle();
                    try {
                        motionEventObtain.recycle();
                        return false;
                    } catch (RuntimeException e6) {
                        e = e6;
                        sb4 = new StringBuilder("event.recycle() Exception = ");
                    }
                }
                sb4.append(e.getMessage());
                string = sb4.toString();
                return false;
            }
            MotionEvent motionEventObtain4 = MotionEvent.obtain(motionEventObtain);
            motionEventObtain4.setAction(3);
            super.onTouchEvent(motionEventObtain4);
            motionEventObtain4.recycle();
            try {
                motionEventObtain.recycle();
                return false;
            } catch (RuntimeException e7) {
                e = e7;
                zOnTouchEvent = false;
                sb2 = new StringBuilder("event.recycle() Exception = ");
            }
            sb2.append(e.getMessage());
            string = sb2.toString();
            return zOnTouchEvent;
        } catch (IllegalArgumentException e8) {
            KgdLogUtil.e("KeyguardSliderLayout", " invalid event " + e8);
            try {
                motionEventObtain.recycle();
                return false;
            } catch (RuntimeException e9) {
                e = e9;
                sb = new StringBuilder("event.recycle() Exception = ");
                sb.append(e.getMessage());
                string = sb.toString();
                return false;
            }
        } catch (Exception e10) {
            KgdLogUtil.e("KeyguardSliderLayout", "Exception = " + e10.getMessage());
            try {
                motionEventObtain.recycle();
                return false;
            } catch (RuntimeException e11) {
                e = e11;
                sb = new StringBuilder("event.recycle() Exception = ");
                sb.append(e.getMessage());
                string = sb.toString();
                return false;
            }
        } finally {
            try {
                motionEventObtain.recycle();
            } catch (RuntimeException e12) {
                KgdLogUtil.e("KeyguardSliderLayout", "event.recycle() Exception = " + e12.getMessage());
            }
        }
    }

    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (getVisibility() == 0 && i == 0 && getParent() == view && this.mUpdateMonitor.isKeyguardVisible$1() && ((ScreenLifecycle) Dependency.sDependency.getDependencyInner(ScreenLifecycle.class)).getScreenState() == 0) {
            requestFocus();
        }
    }

    public final void pageScrolled(IKeyguardSliderPager iKeyguardSliderPager, float f, int i) {
        if (iKeyguardSliderPager != null) {
            iKeyguardSliderPager.onPageScrolled(f, i);
            if (iKeyguardSliderPager.pagerType() == 102) {
                if (!this.mPictorialMediator.getPictorialFactory().getPictorialPreloadHelper().isOnlyShowWallpaperOnLockScreen()) {
                    transformViews(this.mPictorialDynamicView, i, 0.0f);
                }
                if (this.mPictorialMediator.isGlanceStyle() || ((this.mPictorialMediator.isTbStyle() && !this.mPictorialMediator.isAutoPlaySwitchOn()) || this.mPictorialMediator.isOplusGLStyle())) {
                    Object[] objArr = {this.mPictorialPreloadViewOnLeft, this.mPictorialPreloadViewOnRight, this.mMainSliderPager};
                    int iIntValue = ((Integer) ArgbEvaluator.getInstance().evaluate(Math.abs(f), 0, Integer.valueOf(this.mGlanceDark))).intValue();
                    for (int i2 = 0; i2 < 3; i2++) {
                        Object obj = objArr[i2];
                        if (obj instanceof View) {
                            View view = (View) obj;
                            if (f == 0.0f) {
                                view.setBackgroundColor(0);
                            } else {
                                view.setBackgroundColor(iIntValue);
                            }
                        }
                    }
                }
                transformViews(this.mLockIconContainer, i, f);
                transformViews(this.mKeyguardEngineContainer, i, f);
                transformNotificationPanel(f, i);
                setStyleClockDeepSceneAlphaTranslationX(i);
            }
            this.mCurOffset = i;
        }
    }

    public final void resetPosition() {
        KgdLogUtil.d("KeyguardSliderLayout", "resetPosition, getScrollX:" + getScrollX());
        this.mIsTouchMoving = false;
        if (this.mPictorialPreloadViewOnRight != null) {
            this.mReseting = true;
            if (getCurrentItem() == this.mMainSliderPagerIndex) {
                KgdLogUtil.d("KeyguardSliderLayout", "resetByViewPagerItself equal index");
                int iIndexOf = this.mSlidePagers.indexOf(this.mPictorialPreloadViewOnRight);
                this.mPictorialPreloadViewOnRight.mBlockSlideTrigger = true;
                setCurrentItem(iIndexOf, false);
                setCurrentItem(this.mMainSliderPagerIndex, false);
                this.mPictorialPreloadViewOnRight.mBlockSlideTrigger = false;
            } else {
                KgdLogUtil.d("KeyguardSliderLayout", "resetByViewPagerItself");
                setCurrentItem(this.mMainSliderPagerIndex, false);
            }
            this.mReseting = false;
            pageScrolled(this.mMainSliderPager, 0.0f, 0);
        }
        this.mSliding = false;
    }

    public final void setChildHeight() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != null && childAt.getHeight() > this.mStatusBarHeight) {
                try {
                    Method setMeasuredDimensionRawMethod = getSetMeasuredDimensionRawMethod();
                    if (setMeasuredDimensionRawMethod != null) {
                        setMeasuredDimensionRawMethod.invoke(childAt, Integer.valueOf(this.mScreenWidth), Integer.valueOf(this.mScreenHeight));
                    } else {
                        KgdLogUtil.w("KeyguardSliderLayout", "setChildHeight skip: setMeasuredDimensionRaw unavailable");
                    }
                } catch (Exception e) {
                    KgdLogUtil.w("KeyguardSliderLayout", "Exception = " + e.getMessage());
                }
                if (childAt.getHeight() < this.mScreenHeight) {
                    ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                    layoutParams.width = this.mScreenWidth;
                    layoutParams.height = this.mScreenHeight;
                    childAt.setLayoutParams(layoutParams);
                }
            }
        }
    }

    public void setCurrentItem(int i) {
        KgdLogUtil.d("KeyguardSliderLayout", "setCurrentItem:" + i);
        super.setCurrentItem(i);
        if (this.mMainSliderPagerIndex != i) {
            return;
        }
        Iterator it = this.mSlidePagers.iterator();
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                this.mMainPagerSelected = true;
                return;
            }
            IKeyguardSliderPager iKeyguardSliderPager = (IKeyguardSliderPager) it.next();
            if (i2 != this.mMainSliderPagerIndex) {
                z = false;
            }
            iKeyguardSliderPager.onPageSelected(z);
            i2++;
        }
    }

    public void setImmersive(boolean z) {
        PictorialFunctionContainer pictorialFunctionContainer = this.mMainSliderPager;
        if (pictorialFunctionContainer != null) {
            pictorialFunctionContainer.setImmersive(z);
        }
    }

    public void setLayoutAlpha(float f) {
        PictorialFunctionContainer pictorialFunctionContainer;
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.mUpdateMonitor;
        if (keyguardUpdateMonitor == null || !keyguardUpdateMonitor.isKeyguardVisible$1() || (pictorialFunctionContainer = this.mMainSliderPager) == null) {
            return;
        }
        pictorialFunctionContainer.setProcessY(f);
    }

    public void setOnEmptySpaceClickListener(NotificationStackScrollLayout.OnEmptySpaceClickListener onEmptySpaceClickListener) {
        this.mOnEmptySpaceClickListener = onEmptySpaceClickListener;
    }

    public void setQsExpanded(boolean z) {
        this.mQsExpand = z;
        List list = this.mSlidePagers;
        KeyguardSliderLayout$$ExternalSyntheticLambda0 keyguardSliderLayout$$ExternalSyntheticLambda0 = new KeyguardSliderLayout$$ExternalSyntheticLambda0();
        keyguardSliderLayout$$ExternalSyntheticLambda0.f$0 = z;
        VarHandle.storeStoreFence();
        ((ArrayList) list).forEach(keyguardSliderLayout$$ExternalSyntheticLambda0);
    }

    public void setStatusBarWindowView(NotificationShadeWindowView notificationShadeWindowView) {
        this.mNotificationPanelView = notificationShadeWindowView.findViewById(2131364201);
        this.mStackScrollLayout = notificationShadeWindowView.findViewById(2131364206);
        this.mPictorialDynamicView = notificationShadeWindowView.findViewById(2131364541);
        this.mLockIconContainer = notificationShadeWindowView.findViewById(2131363783);
        this.mKeyguardEngineContainer = (KeyguardEngineContainer) notificationShadeWindowView.findViewById(2131363591);
        this.mQsFrame = this.mNotificationPanelView.findViewById(2131364768);
        this.mStyleClock = this.mNotificationPanelView.findViewById(2131363607);
    }

    public final void transformNotificationPanel(float f, int i) {
        KeyguardSliderLayout$$ExternalSyntheticLambda7 keyguardSliderLayout$$ExternalSyntheticLambda7 = new KeyguardSliderLayout$$ExternalSyntheticLambda7();
        keyguardSliderLayout$$ExternalSyntheticLambda7.f$0 = this;
        keyguardSliderLayout$$ExternalSyntheticLambda7.f$1 = f;
        keyguardSliderLayout$$ExternalSyntheticLambda7.f$2 = i;
        VarHandle.storeStoreFence();
        KgdLogUtil.d("KeyguardSliderLayout", "KeyguardSliderLayout", 8, false, keyguardSliderLayout$$ExternalSyntheticLambda7);
        if (Math.abs(i) <= this.mTouchSlop * 3 || this.mReseting) {
            ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBiometricAuthController().notifyMagazineShowing(false);
        } else {
            ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBiometricAuthController().notifyMagazineShowing(true);
        }
        NotificationPanelView notificationPanelView = this.mNotificationPanelView;
        if (notificationPanelView != null) {
            int childCount = notificationPanelView.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = notificationPanelView.getChildAt(i2);
                if (childAt != this) {
                    transformViews(childAt, i, f);
                }
            }
        }
        OplusKeyguardViewMediatorEx oplusKeyguardViewMediatorEx = this.mOplusKeyguardViewMediatorEx;
        if (oplusKeyguardViewMediatorEx != null) {
            oplusKeyguardViewMediatorEx.swipeOneShotClockTransXForSliderLayout(i);
        }
        if (i == this.mLastLiveClockRemirrorOffsetPixels) {
            return;
        }
        this.mLastLiveClockRemirrorOffsetPixels = i;
        OplusLockIconView$.ExternalSyntheticOutline0.m(i, "remirrorLiveClockForSlider offsetPx=", "KeyguardSliderLayout");
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getKeyguardStyleClockController().requestLiveClockMirrorRedraw();
    }

    public final void transformViews(View view, int i, float f) {
        OplusLockIconViewControllerEx lockIconViewController;
        if (view == null) {
            return;
        }
        float f2 = i;
        if (view.getTranslationX() == f2) {
            return;
        }
        if (this.mIgnoreSetAlphaOnConfigChanged) {
            KgdLogUtil.i("KeyguardSliderLayout", "ignore set alpha");
        } else {
            float fAbs = 1.0f - Math.abs(f);
            if (view != this.mLockIconContainer || (lockIconViewController = getLockIconViewController()) == null) {
                view.setAlpha(fAbs);
            } else {
                lockIconViewController.setLockIconAlpha(fAbs);
            }
        }
        view.setTranslationX(f2);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:34:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:36:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:38:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:70:0x016d  */
    public final void updateSliderPagers(int i) {
        boolean zIsMainSwitchOffAndCanLeftSwipe;
        PictorialPreloadView pictorialPreloadView;
        int i2;
        int size;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "updateSliderPagers post to main, reason = ", ", thread = ");
            sbM.append(Thread.currentThread().getName());
            KgdLogUtil.w("KeyguardSliderLayout", sbM.toString());
            KeyguardSliderLayout$$ExternalSyntheticLambda4 keyguardSliderLayout$$ExternalSyntheticLambda4 = new KeyguardSliderLayout$$ExternalSyntheticLambda4();
            keyguardSliderLayout$$ExternalSyntheticLambda4.f$0 = this;
            keyguardSliderLayout$$ExternalSyntheticLambda4.f$1 = i;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnUiThread(keyguardSliderLayout$$ExternalSyntheticLambda4);
            return;
        }
        Trace.traceBegin(8L, "KeyguardSliderLayout#updateSliderPagers");
        KgdLogUtil.i("KeyguardSliderLayout", "updateSliderPagers reason = " + i);
        int size2 = ((ArrayList) this.mSlidePagers).size();
        this.mSlidePagers.clear();
        boolean zIsKeyguardPictorialEnable = isKeyguardPictorialEnable();
        if ((zIsKeyguardPictorialEnable && this.mPictorialMediator.isLeftSwipeDisplaySwitchOn()) || this.mPictorialMediator.isMainSwitchOffAndCanLeftSwipe()) {
            if (this.mPictorialPreloadViewOnRight == null) {
                this.mPictorialPreloadViewOnRight = (PictorialPreloadView) LayoutInflater.from(getContext()).inflate(2131558943, (ViewGroup) null);
            }
            this.mSlidePagers.add(this.mPictorialPreloadViewOnRight);
        } else {
            PictorialPreloadView pictorialPreloadView2 = this.mPictorialPreloadViewOnRight;
            if (pictorialPreloadView2 != null) {
                pictorialPreloadView2.detached();
                this.mPictorialPreloadViewOnRight = null;
            }
        }
        if (zIsKeyguardPictorialEnable && this.mPictorialMediator.isRightSwipeDisplaySwitchOn()) {
            if (this.mPictorialPreloadViewOnLeft == null) {
                PictorialPreloadView pictorialPreloadView3 = (PictorialPreloadView) LayoutInflater.from(getContext()).inflate(2131558943, (ViewGroup) null);
                this.mPictorialPreloadViewOnLeft = pictorialPreloadView3;
                pictorialPreloadView3.setDirection(0);
            }
            this.mSlidePagers.add(this.mPictorialPreloadViewOnLeft);
        } else {
            PictorialMediator pictorialMediator = this.mPictorialMediator;
            if (pictorialMediator.mIsMetaDataSupportRightSwipe) {
                if (pictorialMediator.isRightSwipeDisplaySwitchOn()) {
                    zIsMainSwitchOffAndCanLeftSwipe = pictorialMediator.isMainSwitchOffAndCanLeftSwipe();
                } else {
                    KgdLogUtil.i("PictorialMediator", "isMainSwitchOffAndCanRightSwipe isRightSwipeDisplaySwitchOn is false");
                }
                if (zIsMainSwitchOffAndCanLeftSwipe) {
                    if (this.mPictorialPreloadViewOnLeft == null) {
                        PictorialPreloadView pictorialPreloadView4 = (PictorialPreloadView) LayoutInflater.from(getContext()).inflate(2131558943, (ViewGroup) null);
                        this.mPictorialPreloadViewOnLeft = pictorialPreloadView4;
                        pictorialPreloadView4.setDirection(0);
                    }
                    this.mSlidePagers.add(this.mPictorialPreloadViewOnLeft);
                } else {
                    pictorialPreloadView = this.mPictorialPreloadViewOnLeft;
                    if (pictorialPreloadView != null) {
                        pictorialPreloadView.detached();
                        this.mPictorialPreloadViewOnLeft = null;
                    }
                }
            } else {
                KgdLogUtil.i("PictorialMediator", "isMainSwitchOffAndCanRightSwipe mIsMetaDataSupportRightSwipe is false");
            }
            zIsMainSwitchOffAndCanLeftSwipe = false;
            if (zIsMainSwitchOffAndCanLeftSwipe) {
                if (this.mPictorialPreloadViewOnLeft == null) {
                    PictorialPreloadView pictorialPreloadView5 = (PictorialPreloadView) LayoutInflater.from(getContext()).inflate(2131558943, (ViewGroup) null);
                    this.mPictorialPreloadViewOnLeft = pictorialPreloadView5;
                    pictorialPreloadView5.setDirection(0);
                }
                this.mSlidePagers.add(this.mPictorialPreloadViewOnLeft);
            } else {
                pictorialPreloadView = this.mPictorialPreloadViewOnLeft;
                if (pictorialPreloadView != null) {
                    pictorialPreloadView.detached();
                    this.mPictorialPreloadViewOnLeft = null;
                }
            }
        }
        boolean zIsEmpty = this.mSlidePagers.isEmpty();
        PictorialFunctionContainer pictorialFunctionContainer = this.mMainSliderPager;
        if (!zIsEmpty) {
            if (pictorialFunctionContainer == null) {
                PictorialFunctionContainer pictorialFunctionContainer2 = new PictorialFunctionContainer(getContext());
                this.mMainSliderPager = pictorialFunctionContainer2;
                pictorialFunctionContainer2.setOnTipsListener(this.mOnTipsListener);
            }
            this.mSlidePagers.add(this.mMainSliderPager);
        } else if (pictorialFunctionContainer != null) {
            pictorialFunctionContainer.detached();
            this.mMainSliderPager = null;
        }
        List list = this.mSlidePagers;
        if (list == null || list.isEmpty()) {
            removeAllViews();
            setVisibility(8);
            KeyguardSliderAdapter keyguardSliderAdapter = this.mKeyguardSliderAdapter;
            List list2 = keyguardSliderAdapter.mPagers;
            if (list2 != null) {
                list2.clear();
                keyguardSliderAdapter.notifyDataSetChanged();
            }
            transformNotificationPanel(0.0f, 0);
        } else {
            if (!this.mSlidePagers.isEmpty() && ((ArrayList) this.mSlidePagers).size() != 1) {
                Collections.sort(this.mSlidePagers, new KeyguardSliderLayout$$ExternalSyntheticLambda5());
            }
            KeyguardUpdateMonitor keyguardUpdateMonitor = this.mUpdateMonitor;
            setVisibility((keyguardUpdateMonitor == null || !keyguardUpdateMonitor.isKeyguardVisible$1()) ? 4 : 0);
            List list3 = this.mSlidePagers;
            if (list3 == null || list3.isEmpty() || (size = ((ArrayList) list3).size()) == 1) {
                i2 = 0;
            } else if (size == 3) {
                i2 = 1;
            } else if (size == 2) {
                Iterator it = list3.iterator();
                loop0: while (true) {
                    i2 = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            break loop0;
                        }
                        IKeyguardSliderPager iKeyguardSliderPager = (IKeyguardSliderPager) it.next();
                        if (iKeyguardSliderPager.direction() != 1) {
                            if (iKeyguardSliderPager.direction() == 0) {
                                i2 = 1;
                            }
                        }
                    }
                }
            } else {
                i2 = 0;
            }
            this.mMainSliderPagerIndex = i2;
            this.mKeyguardSliderAdapter.setPageData(this.mSlidePagers);
            setPageTransformer(true, this.mTransformer);
            setOffscreenPageLimit(3);
            setCurrentItem(this.mMainSliderPagerIndex, false);
            pageScrolled(this.mMainSliderPager, 0.0f, 0);
        }
        KgdLogUtil.i("KeyguardSliderLayout", "updateSliderPagers : mSlidePagers = " + ((ArrayList) this.mSlidePagers).size());
        if (this.mPictorialMediator.getPictorialFactory().getPictorialPreloadHelper().needUpdatePreloadViewAtFirst(this.mPictorialMediator) && getVisibility() == 0) {
            PictorialPreloadView pictorialPreloadView6 = this.mPictorialPreloadViewOnLeft;
            if (pictorialPreloadView6 != null) {
                pictorialPreloadView6.show();
            }
            PictorialPreloadView pictorialPreloadView7 = this.mPictorialPreloadViewOnRight;
            if (pictorialPreloadView7 != null) {
                pictorialPreloadView7.show();
            }
        }
        if ((i == 7 || i == 8) && getVisibility() == 0) {
            PictorialPreloadView pictorialPreloadView8 = this.mPictorialPreloadViewOnLeft;
            if (pictorialPreloadView8 != null) {
                pictorialPreloadView8.show();
            }
            PictorialPreloadView pictorialPreloadView9 = this.mPictorialPreloadViewOnRight;
            if (pictorialPreloadView9 != null) {
                pictorialPreloadView9.show();
                KgdLogUtil.d("KeyguardSliderLayout", "originPageCount=" + size2);
                if (size2 == 0) {
                    PictorialPreloadView pictorialPreloadView10 = this.mPictorialPreloadViewOnRight;
                    pictorialPreloadView10.getClass();
                    KgdLogUtil.i("PictorialPreloadView", "updateCurrentPreloadWallpaper");
                    pictorialPreloadView10.mPictorialWallpaperHelper.updateCurrentPreloadWallpaper();
                }
            }
        }
        boolean z = this.mPictorialMediator.mPictorialExist;
        ContentResolver contentResolver = this.mContentResolver;
        Settings.System.putIntForUser(contentResolver, "pictorial_exist", z ? 1 : 0, KeyguardUpdateMonitor.getCurrentUser());
        boolean zIsPictorialEnable = this.mPictorialMediator.isPictorialEnable(true);
        StringBuilder sb = new StringBuilder("mPictorialPreloadViewOnRight not null=");
        PromptIconState$.ExternalSyntheticOutline0.m(z ? 1 : 0, ", exist=", ", isPictorialEnable=", sb, this.mPictorialPreloadViewOnRight != null);
        OplusChargeAnimController$.ExternalSyntheticOutline0.m("KeyguardSliderLayout", sb, zIsPictorialEnable);
        if (this.mPictorialPreloadViewOnRight == null || !zIsPictorialEnable) {
            Settings.System.putIntForUser(contentResolver, "pictorial_enable", 0, KeyguardUpdateMonitor.getCurrentUser());
        } else {
            Settings.System.putIntForUser(contentResolver, "pictorial_enable", 1, KeyguardUpdateMonitor.getCurrentUser());
        }
        Trace.traceEnd(8L);
    }

    public KeyguardSliderLayout(Context context) {
        this(context, null);
    }
}
