package com.oplus.systemui.statusbar;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.MathUtils;
import android.view.Choreographer;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.DependencyEx;
import com.android.systemui.LauncherProxyService;
import com.android.systemui.animation.ShadeInterpolation;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.panelanimation.platformblur.PlatformBlurManager;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.power.shared.model.WakefulnessModel;
import com.android.systemui.shared.recents.ILauncherProxy;
import com.android.systemui.statusbar.BlurUtils;
import com.android.systemui.statusbar.NotificationShadeDepthControllerEx;
import com.android.systemui.statusbar.notification.domain.interactor.NotificationLaunchAnimationInteractor;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.util.WallpaperController;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommonImpl;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationShadeDepthControllerExImp extends NotificationShadeDepthControllerEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationShadeDepthC";
    private final Context context;
    public float currentIconZoomOut;
    public int currentLockScreenType;
    public final boolean isOplusLogic;
    public float mCurrentZoomOut;
    private final NotificationDependOnCommon notificationDependOnCommon;
    private final NotificationLaunchAnimationInteractor notificationLaunchInteractor;
    private final LauncherProxyService overviewProxyService;
    private final PanelAnimationEx panelAnimationEx;
    private final Lazy platformBlurManager;
    private final PowerInteractor powerInteractor;
    private final AtomicInteger sceneFlags;
    private final Handler timeoutHandler;
    private final Runnable timeoutRunnable;
    private final Choreographer.FrameCallback updateBlurCallbackOplus;
    private final WallpaperController wallpaperController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.NotificationShadeDepthControllerExImp$restrainNavigationBarInvertColor$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements IntUnaryOperator {
        public /* synthetic */ boolean $isStart;
        public /* synthetic */ int $sceneFlag;

        @Override // java.util.function.IntUnaryOperator
        public final int applyAsInt(int i) {
            boolean z = this.$isStart;
            int i2 = this.$sceneFlag;
            if (z) {
                return i2 | i;
            }
            if (i2 == 2) {
                int i3 = i & 2;
            }
            return (~i2) & i;
        }
    }

    public NotificationShadeDepthControllerExImp(Context context, WallpaperController wallpaperController, LauncherProxyService launcherProxyService, NotificationLaunchAnimationInteractor notificationLaunchAnimationInteractor, NotificationDependOnCommon notificationDependOnCommon, PanelAnimationEx panelAnimationEx, Lazy lazy, PowerInteractor powerInteractor) {
        super(context);
        this.context = context;
        this.wallpaperController = wallpaperController;
        this.overviewProxyService = launcherProxyService;
        this.notificationLaunchInteractor = notificationLaunchAnimationInteractor;
        this.notificationDependOnCommon = notificationDependOnCommon;
        this.panelAnimationEx = panelAnimationEx;
        this.platformBlurManager = lazy;
        this.powerInteractor = powerInteractor;
        this.isOplusLogic = true;
        this.currentLockScreenType = -1;
        NotificationShadeDepthControllerExImp$updateBlurCallbackOplus$1 notificationShadeDepthControllerExImp$updateBlurCallbackOplus$1 = new NotificationShadeDepthControllerExImp$updateBlurCallbackOplus$1();
        notificationShadeDepthControllerExImp$updateBlurCallbackOplus$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.updateBlurCallbackOplus = notificationShadeDepthControllerExImp$updateBlurCallbackOplus$1;
        this.sceneFlags = new AtomicInteger(0);
        this.timeoutHandler = new Handler(Looper.getMainLooper());
        NotificationShadeDepthControllerExImp$timeoutRunnable$1 notificationShadeDepthControllerExImp$timeoutRunnable$1 = new NotificationShadeDepthControllerExImp$timeoutRunnable$1();
        notificationShadeDepthControllerExImp$timeoutRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.timeoutRunnable = notificationShadeDepthControllerExImp$timeoutRunnable$1;
    }

    public static final void access$updateWallpaperZoom(NotificationShadeDepthControllerExImp notificationShadeDepthControllerExImp) {
        CentralSurfacesImpl centralSurfacesImpl = (CentralSurfacesImpl) DependencyEx.sDependency.getDependency(CentralSurfacesImpl.class);
        float fMax = Math.max(Math.max(Math.max((MathUtils.constrain(notificationShadeDepthControllerExImp.getController().getShadeAnimation().getRadius(), notificationShadeDepthControllerExImp.getController().getBlurUtils().getMinBlurRadius(), notificationShadeDepthControllerExImp.getController().getBlurUtils().getMaxBlurRadius()) * 0.19999999f) + (notificationShadeDepthControllerExImp.getController().getBlurUtils().blurRadiusOfRatio(ShadeInterpolation.getNotificationScrimAlpha(notificationShadeDepthControllerExImp.getController().shouldApplyShadeBlur() ? notificationShadeDepthControllerExImp.getController().getShadeExpansion() : 0.0f)) * 0.8f), notificationShadeDepthControllerExImp.getController().getBlurUtils().blurRadiusOfRatio(notificationShadeDepthControllerExImp.getController().getShadeExpansion() * ShadeInterpolation.getNotificationScrimAlpha(notificationShadeDepthControllerExImp.getController().getQsPanelExpansion()))), notificationShadeDepthControllerExImp.getController().getBlurUtils().blurRadiusOfRatio(notificationShadeDepthControllerExImp.getController().getTransitionToFullShadeProgress())), notificationShadeDepthControllerExImp.getController().wakeAndUnlockBlurRadius);
        if (notificationShadeDepthControllerExImp.getController().getBlursDisabledForAppLaunch() || notificationShadeDepthControllerExImp.getController().getBlursDisabledForUnlock()) {
            fMax = 0.0f;
        }
        int iBlurRadiusOfRatio = 0;
        if (!notificationShadeDepthControllerExImp.getController().getScrimsVisible() && !ChildrenModeObserver.getInstance().mIsChildrenMode && !((NotificationDependOnCommonImpl) notificationShadeDepthControllerExImp.notificationDependOnCommon).isFullyCollapsed() && notificationShadeDepthControllerExImp.getController().getShadeExpansion() > 0.0f && !((Boolean) notificationShadeDepthControllerExImp.notificationLaunchInteractor.isLaunchAnimationRunning().getValue()).booleanValue()) {
            if (notificationShadeDepthControllerExImp.panelAnimationEx.getUseLayeredAnimation() && centralSurfacesImpl.mState != 1) {
                BlurUtils blurUtils = notificationShadeDepthControllerExImp.getController().getBlurUtils();
                float blurFraction = notificationShadeDepthControllerExImp.panelAnimationEx.getPanelExpandFraction().getBlurFraction();
                float blurFraction2 = centralSurfacesImpl.getSeparateNotificationAndQS().getBlurFraction();
                if (blurFraction < blurFraction2) {
                    blurFraction = blurFraction2;
                }
                iBlurRadiusOfRatio = (int) blurUtils.blurRadiusOfRatio(blurFraction);
            } else if (!((WakefulnessModel) notificationShadeDepthControllerExImp.powerInteractor.getDetailedWakefulness().getValue()).isAsleep()) {
                iBlurRadiusOfRatio = (int) fMax;
            }
        }
        float fRatioOfBlurRadius = notificationShadeDepthControllerExImp.getController().getBlurUtils().ratioOfBlurRadius(iBlurRadiusOfRatio);
        try {
            if (notificationShadeDepthControllerExImp.currentLockScreenType == 2) {
                notificationShadeDepthControllerExImp.wallpaperController.setNotificationShadeZoom(1.0f);
            } else if (notificationShadeDepthControllerExImp.mCurrentZoomOut != fRatioOfBlurRadius) {
                notificationShadeDepthControllerExImp.wallpaperController.setNotificationShadeZoom(fRatioOfBlurRadius);
            }
            float fAbs = Math.abs(fRatioOfBlurRadius - notificationShadeDepthControllerExImp.mCurrentZoomOut);
            if (fAbs > 0.0f && fAbs <= 1.0f) {
                ILauncherProxy proxy = notificationShadeDepthControllerExImp.overviewProxyService.getProxy();
                if (proxy != null) {
                    proxy.iconPaperZoomOut(fRatioOfBlurRadius);
                }
                notificationShadeDepthControllerExImp.currentIconZoomOut = fRatioOfBlurRadius;
            }
            notificationShadeDepthControllerExImp.mCurrentZoomOut = fRatioOfBlurRadius;
        } catch (Exception e) {
            Log.w(TAG, "Can't set zoom. Window is gone:", e);
        }
    }

    public final int getCurrentLockScreenType() {
        return this.currentLockScreenType;
    }

    public final boolean isOplusLogic() {
        return this.isOplusLogic;
    }

    public final void restrainNavigationBarInvertColor(int i, boolean z) {
        int i2 = this.sceneFlags.get();
        AtomicInteger atomicInteger = this.sceneFlags;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$isStart = z;
        anonymousClass1.$sceneFlag = i;
        VarHandle.storeStoreFence();
        atomicInteger.getAndUpdate(anonymousClass1);
        if (i2 != this.sceneFlags.get()) {
            String strPadStart = StringsKt__StringsKt.padStart(Integer.toBinaryString(i), 3, '0');
            String strPadStart2 = StringsKt__StringsKt.padStart(Integer.toBinaryString(this.sceneFlags.get()), 3, '0');
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("sceneFlag: 0x", strPadStart, ", isStart = ", z, ", Current sceneFlags: 0x");
            sbM.append(strPadStart2);
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, sbM.toString(), null, 8);
            getController().restrainNavigationBarInvertColor(this.sceneFlags.get() != 0);
        }
        if (z) {
            this.timeoutHandler.removeCallbacks(this.timeoutRunnable);
            this.timeoutHandler.postDelayed(this.timeoutRunnable, 5000L);
        }
    }

    public void scheduleUpdate(Choreographer choreographer) {
        if (!((Boolean) ((PlatformBlurManager) this.platformBlurManager.get()).getUseMotionBlur().getValue()).booleanValue() && !ScrimUtil.isLowGaussianLevel(this.context)) {
            FlavorOneFeatureOption.INSTANCE.getClass();
            if ((!FlavorOneFeatureOption.isFlavorOneDevice() || FlavorOneFeatureOption.sPlatformLevel >= 3) && !SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState) {
                choreographer.postFrameCallback(this.updateBlurCallbackOplus);
                return;
            }
        }
        if (this.currentIconZoomOut == 0.0f) {
            return;
        }
        ILauncherProxy proxy = this.overviewProxyService.getProxy();
        if (proxy != null) {
            proxy.iconPaperZoomOut(0.0f);
        }
        this.currentIconZoomOut = 0.0f;
    }

    public final void setCurrentLockScreenType(int i) {
        this.currentLockScreenType = i;
    }
}
