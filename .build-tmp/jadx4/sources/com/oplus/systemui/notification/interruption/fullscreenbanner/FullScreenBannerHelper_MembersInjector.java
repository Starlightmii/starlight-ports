package com.oplus.systemui.notification.interruption.fullscreenbanner;

import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.oplus.systemui.statusbar.notification.helper.HeadsUpHelper;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginManager;
import dagger.Lazy;
import dagger.MembersInjector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class FullScreenBannerHelper_MembersInjector implements MembersInjector {
    public static void injectMActivityStarterEx(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mActivityStarterEx = lazy;
    }

    public static void injectMActivityTransitionAnimator(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mActivityTransitionAnimator = lazy;
    }

    public static void injectMAmbientStateEx(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mAmbientStateEx = lazy;
    }

    public static void injectMAonAuthStarter(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mAonAuthStarter = lazy;
    }

    public static void injectMCentralSurfaces(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mCentralSurfaces = lazy;
    }

    public static void injectMCommandQueue(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mCommandQueue = lazy;
    }

    public static void injectMFullScreenBannerColorController(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mFullScreenBannerColorController = lazy;
    }

    public static void injectMHeadsUpHelper(FullScreenBannerHelper fullScreenBannerHelper, HeadsUpHelper headsUpHelper) {
        fullScreenBannerHelper.mHeadsUpHelper = headsUpHelper;
    }

    public static void injectMHeadsUpManager(FullScreenBannerHelper fullScreenBannerHelper, HeadsUpManager headsUpManager) {
        fullScreenBannerHelper.mHeadsUpManager = headsUpManager;
    }

    public static void injectMLockscreenUserManager(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mLockscreenUserManager = lazy;
    }

    public static void injectMNotifInflateDelayStrategy(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mNotifInflateDelayStrategy = lazy;
    }

    public static void injectMNotificationDependOnCommon(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mNotificationDependOnCommon = lazy;
    }

    public static void injectMNotificationDependOnKeyguard(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mNotificationDependOnKeyguard = lazy;
    }

    public static void injectMNotificationMaterialColorEx(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mNotificationMaterialColorEx = lazy;
    }

    public static void injectMNotificationShadeWindowController(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mNotificationShadeWindowController = lazy;
    }

    public static void injectMNotificationShadeWindowControllerEx(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mNotificationShadeWindowControllerEx = lazy;
    }

    public static void injectMNotificationStackScrollLayoutController(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mNotificationStackScrollLayoutController = lazy;
    }

    public static void injectMPanelViewControllerEx(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mPanelViewControllerEx = lazy;
    }

    public static void injectMPrivacyHelper(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mPrivacyHelper = lazy;
    }

    public static void injectMSeedlingPluginManager(FullScreenBannerHelper fullScreenBannerHelper, SeedlingPluginManager seedlingPluginManager) {
        fullScreenBannerHelper.mSeedlingPluginManager = seedlingPluginManager;
    }

    public static void injectMShadeAnimationInteractor(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mShadeAnimationInteractor = lazy;
    }

    public static void injectMShadeController(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mShadeController = lazy;
    }

    public static void injectMShadeSurface(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mShadeSurface = lazy;
    }

    public static void injectMStatusBarWindowControllerStore(FullScreenBannerHelper fullScreenBannerHelper, Lazy lazy) {
        fullScreenBannerHelper.mStatusBarWindowControllerStore = lazy;
    }
}
