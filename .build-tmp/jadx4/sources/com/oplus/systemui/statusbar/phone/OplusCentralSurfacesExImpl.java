package com.oplus.systemui.statusbar.phone;

import android.app.Fragment;
import android.app.PendingIntent;
import android.app.TaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.ActivityIntentHelper;
import com.android.systemui.activity.data.repository.ActivityIntentRepository;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.fragments.FragmentHostManager;
import com.android.systemui.fragments.FragmentService;
import com.android.systemui.keyboardshortcuts.OplusKeyboardShortcutsDependencyInstance;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.anim.IOplusPanelKeyguardAnimController;
import com.android.systemui.keyguard.anim.IOplusWallpaperAnimController;
import com.android.systemui.keyguard.anim.transition.OplusKgdTransitionAnimationController;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.PluginDependencyProvider;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.plugins.statusbar.SysUiStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.power.shared.model.WakeSleepReason;
import com.android.systemui.power.shared.model.WakefulnessModel;
import com.android.systemui.power.shared.model.WakefulnessState;
import com.android.systemui.qs.brightness.MirrorController;
import com.android.systemui.qs.deprecated.QSFragmentLegacy;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.NotificationShadeWindowViewController;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeSurface;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.notification.NotificationActivityStarter;
import com.android.systemui.statusbar.notification.NotificationLaunchAnimatorControllerProvider;
import com.android.systemui.statusbar.notification.collection.NotificationCenterManagerEx;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilderEx;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.OplusCentralSurfacesEx;
import com.android.systemui.statusbar.phone.PhoneStatusBarTransitions;
import com.android.systemui.statusbar.phone.PhoneStatusBarView;
import com.android.systemui.statusbar.phone.PhoneStatusBarViewController;
import com.android.systemui.statusbar.phone.StartInKeyGuardShowParams;
import com.android.systemui.statusbar.phone.StartInKeyGuardShowTypeMethods;
import com.android.systemui.statusbar.phone.StatusBarHideIconsForBouncerManager;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter;
import com.android.systemui.statusbar.phone.UbiquitousClickAfterDismissedParams;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.wm.shell.startingsurface.SplashscreenContentDrawer;
import com.android.wm.shell.startingsurface.StartingSurface;
import com.oplus.keyguard.utils.KeyguardRapidUnlockUtils;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.aod.utils.AodCustomizeKeyguardSwitchObserver;
import com.oplus.systemui.common.flavor.OplusFlavorManager;
import com.oplus.systemui.keyguard.OplusKeyguardViewMediatorExImpl;
import com.oplus.systemui.keyguard.anim.OplusKgdAnimParams;
import com.oplus.systemui.navigationbar.gesture.sidegesture.EventInterceptInjectHelper;
import com.oplus.systemui.notification.NotificationExtFlag;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpAnimator;
import com.oplus.systemui.notification.lockscreen.launch.CapsuleLaunchAnimatorControllerProvider;
import com.oplus.systemui.notification.lockscreen.launch.CapsuleTransitionAnimatorController;
import com.oplus.systemui.notification.notificationcenter.NotificationCenterManager;
import com.oplus.systemui.oplusplugin.OplusPluginDependencyProvider;
import com.oplus.systemui.separate.OplusSimpleQSFragment;
import com.oplus.systemui.statusbar.SysUiStateControllerImpl;
import com.oplus.systemui.statusbar.notification.row.menupanel.NotificationPopupMenuController;
import com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopDialogController;
import com.oplus.systemui.statusbar.notification.stack.OplusNotificationSectionsManagerExImpl;
import com.oplus.systemui.statusbar.seeding.OplusSeedlingCardController;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplus.systemui.statusbar.util.StatusBarHelper;
import com.oplus.systemui.utils.Util;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.util.OpUtils;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCentralSurfacesExImpl extends OplusCentralSurfacesEx implements StartInKeyGuardShowTypeMethods {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusCentralSurfacesExI";
    private ActivityIntentHelper activityIntentHelper;
    private final ActivityStarter activityStarter;
    private final Lazy capsuleLaunchAnimatorControllerProvider;
    private CentralSurfaces centralSurfaces;
    private final Context context;
    private final Lazy flavorManager;
    private final Lazy headsUpAnimator;
    private final KeyguardStateController keyguardStateController;
    private final Lazy keyguardViewMediatorExImpl;
    private final Lazy keyguardViewMediatorLazy;
    private final NotificationLockscreenUserManager lockScreenUserManager;
    public int mDoubleTapPowerApp;
    private final ContentObserver mDoubleTapPowerObserver;
    private final ActivityIntentHelper notifActivityIntentHelper;
    private NotificationActivityStarter notificationActivityStarter;
    private final Lazy notificationCenterManagerEx;
    private final Lazy notificationMenuPanelControllerLazy;
    private final Lazy notificationSectionsManager;
    private final Lazy notificationTopDialogControllerLazy;
    private final Lazy oplusPanelKeyguardAnimController;
    private final OplusPluginDependencyProvider oplusPluginDependencyProvider;
    private final Lazy oplusSeedlingCardController;
    private final Lazy oplusWallpaperAnimController;
    private final PluginDependencyProvider pluginDependencyProvider;
    private final Lazy powerInteractor;
    private final ShadeController shadeController;
    private final ShadeListBuilderEx shadeListBuilderEx;
    public boolean shouldBeKeyguard;
    private final SysUiStateControllerImpl sysUiStateController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.OplusCentralSurfacesExImpl$onCentralSurfacesStartComplete$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements KeyguardStateController.Callback {
        public /* synthetic */ CentralSurfaces $centralSurfaces;
        public /* synthetic */ OplusCentralSurfacesExImpl this$0;

        public final void onKeyguardGoingAwayChanged() {
            KgdLogUtil.i(OplusCentralSurfacesExImpl.TAG, "onKeyguardGoingAwayChanged->updateScrimController");
            CentralSurfaces centralSurfaces = this.$centralSurfaces;
            OplusCentralSurfacesExImpl$onCentralSurfacesStartComplete$1$onKeyguardGoingAwayChanged$1 oplusCentralSurfacesExImpl$onCentralSurfacesStartComplete$1$onKeyguardGoingAwayChanged$1 = new OplusCentralSurfacesExImpl$onCentralSurfacesStartComplete$1$onKeyguardGoingAwayChanged$1();
            oplusCentralSurfacesExImpl$onCentralSurfacesStartComplete$1$onKeyguardGoingAwayChanged$1.$centralSurfaces = centralSurfaces;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnUiThread(oplusCentralSurfacesExImpl$onCentralSurfacesStartComplete$1$onKeyguardGoingAwayChanged$1);
        }

        public final void onPrimaryBouncerShowingChanged() {
            StatusBarHideIconsForBouncerManager statusBarHideIconsForBouncerManager = this.$centralSurfaces.getStatusBarHideIconsForBouncerManager();
            if (statusBarHideIconsForBouncerManager != null) {
                statusBarHideIconsForBouncerManager.setBouncerShowingAndTriggerUpdate(this.this$0.keyguardStateController.mPrimaryBouncerShowing);
            }
        }
    }

    public OplusCentralSurfacesExImpl(Context context, KeyguardStateController keyguardStateController, NotificationLockscreenUserManager notificationLockscreenUserManager, Lazy lazy, Lazy lazy2, ActivityStarter activityStarter, Lazy lazy3, Lazy lazy4, ShadeListBuilderEx shadeListBuilderEx, ShadeController shadeController, Lazy lazy5, PluginDependencyProvider pluginDependencyProvider, OplusPluginDependencyProvider oplusPluginDependencyProvider, SysUiStateControllerImpl sysUiStateControllerImpl, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9, Lazy lazy10, Lazy lazy11, Lazy lazy12, Lazy lazy13, ActivityIntentHelper activityIntentHelper) {
        this.context = context;
        this.keyguardStateController = keyguardStateController;
        this.lockScreenUserManager = notificationLockscreenUserManager;
        this.notificationCenterManagerEx = lazy;
        this.notificationMenuPanelControllerLazy = lazy2;
        this.activityStarter = activityStarter;
        this.notificationSectionsManager = lazy3;
        this.flavorManager = lazy4;
        this.shadeListBuilderEx = shadeListBuilderEx;
        this.shadeController = shadeController;
        this.notificationTopDialogControllerLazy = lazy5;
        this.pluginDependencyProvider = pluginDependencyProvider;
        this.oplusPluginDependencyProvider = oplusPluginDependencyProvider;
        this.sysUiStateController = sysUiStateControllerImpl;
        this.oplusPanelKeyguardAnimController = lazy6;
        this.oplusWallpaperAnimController = lazy7;
        this.keyguardViewMediatorLazy = lazy8;
        this.headsUpAnimator = lazy9;
        this.oplusSeedlingCardController = lazy10;
        this.powerInteractor = lazy11;
        this.keyguardViewMediatorExImpl = lazy12;
        this.capsuleLaunchAnimatorControllerProvider = lazy13;
        this.notifActivityIntentHelper = activityIntentHelper;
        final Handler handler = new Handler(Looper.getMainLooper());
        this.mDoubleTapPowerObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.statusbar.phone.OplusCentralSurfacesExImpl$mDoubleTapPowerObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                int intForUser = Settings.Secure.getIntForUser(this.this$0.context.getContentResolver(), "double_tap_power_button_value", 0, -2);
                Log.i("OplusCentralSurfacesExI", "DoubleTapPower: app=" + intForUser);
                this.this$0.mDoubleTapPowerApp = intForUser;
            }
        };
    }

    public final void collapseShadePanel() {
        this.shadeController.animateCollapseShade(0);
    }

    public QS createDefaultQSFragment(FragmentService fragmentService, NotificationShadeWindowView notificationShadeWindowView, boolean z) {
        if (z) {
            FragmentHostManager fragmentHostManager = fragmentService.getFragmentHostManager(notificationShadeWindowView);
            QS qsInstantiate = fragmentHostManager.mPlugins.instantiate(fragmentHostManager.mContext, OplusSimpleQSFragment.class.getName(), (Bundle) null);
            qsInstantiate.getClass();
            return qsInstantiate;
        }
        FragmentHostManager fragmentHostManager2 = fragmentService.getFragmentHostManager(notificationShadeWindowView);
        QS qsInstantiate2 = fragmentHostManager2.mPlugins.instantiate(fragmentHostManager2.mContext, QSFragmentLegacy.class.getName(), (Bundle) null);
        qsInstantiate2.getClass();
        return qsInstantiate2;
    }

    public void dismissSettingsPanel(String str) {
        NotificationPopupMenuController.dismissPopupMenu$default((NotificationPopupMenuController) this.notificationMenuPanelControllerLazy.get(), str, !((KeyguardViewMediator) this.keyguardViewMediatorLazy.get()).mGoingToSleep, 4);
        ((OplusNotificationSectionsManagerExImpl) this.notificationSectionsManager.get()).dismissSeedlingPopupMenu(str);
        ((NotificationTopDialogController) this.notificationTopDialogControllerLazy.get()).dismissDialog(str);
    }

    public ActivityTransitionAnimator.Controller getAnimatorController(ExpandableNotificationRow expandableNotificationRow, NotificationLaunchAnimatorControllerProvider notificationLaunchAnimatorControllerProvider) {
        CapsuleTransitionAnimatorController animatorController;
        if (((ExpandableView) expandableNotificationRow).mExpandableViewEx.getCapsuleProgress() == 1.0f && (animatorController = ((CapsuleLaunchAnimatorControllerProvider) this.capsuleLaunchAnimatorControllerProvider.get()).getAnimatorController(expandableNotificationRow, (Runnable) null)) != null) {
            return animatorController;
        }
        if (expandableNotificationRow.isChildInGroup() && !expandableNotificationRow.mPinnedStatus.isPinned() && !expandableNotificationRow.isHeadsUpState()) {
            ExpandableNotificationRow notificationParent = expandableNotificationRow.getNotificationParent();
            if (notificationParent.mPinnedStatus.isPinned() && notificationParent.isHeadsUpState()) {
                return notificationLaunchAnimatorControllerProvider.getAnimatorController(notificationParent, (Runnable) null);
            }
        }
        return notificationLaunchAnimatorControllerProvider.getAnimatorController(expandableNotificationRow, (Runnable) null);
    }

    public int getBackgroundColor(TaskInfo taskInfo, Optional<StartingSurface> optional) {
        int backgroundColor;
        if (optional.isPresent()) {
            backgroundColor = optional.get().getBackgroundColor(taskInfo);
        } else {
            Log.w("CentralSurfaces", "No starting surface, defaulting to SystemBGColor");
            backgroundColor = SplashscreenContentDrawer.getSystemBGColor();
        }
        return OplusCentralSurfacesExImplKt.refactSplashscreenBgColor(this.context, backgroundColor);
    }

    public final CentralSurfaces getCentralSurfaces() {
        return this.centralSurfaces;
    }

    public Intent getDoubleTapPowerOpAppIntent(int i, int i2) {
        int i3;
        PackageManager packageManager = this.context.getPackageManager();
        Intent intent = null;
        if (i == 1 && (i3 = this.mDoubleTapPowerApp) != 0) {
            if (i3 == 1) {
                intent = new Intent("coloros.wallet.intent.action.OPEN");
                intent.setFlags(268468224);
            } else if (i3 == 2 && Util.isPackageInstalledAsUser(this.context, Util.ALEXA_PKG, 0, i2)) {
                List list = OpUtils.PACKAGE_LISTENERS;
                FeatureOption.INSTANCE.getClass();
                if (FeatureOption.isExpRegion()) {
                    Log.d(TAG, "launch alexa");
                    Intent intent2 = new Intent();
                    intent2.setAction(Util.ALEXA_ACTION);
                    intent2.setPackage(Util.ALEXA_PKG);
                    if (intent2.resolveActivity(packageManager) == null) {
                        Log.i(TAG, "getLaunchIntent of alexa");
                        intent2 = packageManager.getLaunchIntentForPackage(Util.ALEXA_PKG);
                    }
                    intent = intent2;
                    if (intent != null) {
                        intent.putExtra("isAlexa", true);
                    }
                }
            }
            Log.i(TAG, "DoubleTapPower: getDoubleTapPowerOpAppIntent " + this.mDoubleTapPowerApp + ", " + intent);
        }
        return intent;
    }

    public boolean getIsPanelShowing() {
        if (((OplusNotificationSectionsManagerExImpl) this.notificationSectionsManager.get()).isSeedlingPopupMenuShowing()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusCentralSurfacesExI-->getIsPanelShowing: isSeedlingPopupMenuShowing");
                return true;
            }
        } else {
            if (!((NotificationPopupMenuController) this.notificationMenuPanelControllerLazy.get()).isPopupMenuShowing()) {
                return false;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusCentralSurfacesExI-->getIsPanelShowing: isNotificationPopupMenuShowing");
            }
        }
        return true;
    }

    public boolean getShouldBeKeyguard() {
        return this.shouldBeKeyguard;
    }

    public void initKeyguard(NotificationShadeWindowViewController notificationShadeWindowViewController, NotificationPanelViewController notificationPanelViewController) {
        OplusKgdAnimParams.INSTANCE.initOliveLink(this.context);
        ((IOplusPanelKeyguardAnimController) this.oplusPanelKeyguardAnimController.get()).init(notificationPanelViewController);
        ((IOplusWallpaperAnimController) this.oplusWallpaperAnimController.get()).init(notificationPanelViewController);
    }

    public final boolean isCustomizeKgdSwitchOFF() {
        AodCustomizeKeyguardSwitchObserver.Companion.getClass();
        return AodCustomizeKeyguardSwitchObserver.isCustomizeKgdSwitchOFF;
    }

    public final boolean isInBackGesture() {
        return ((EventInterceptInjectHelper) EventInterceptInjectHelper.INSTANCE.get()).isInGesture();
    }

    public final boolean isInterceptScrimToUnlock() {
        boolean z = this.keyguardStateController.mKeyguardGoingAway;
        boolean oplusKeyguardGoingAway = ((OplusKeyguardViewMediatorExImpl) this.keyguardViewMediatorExImpl.get()).getOplusKeyguardGoingAway();
        boolean z2 = this.keyguardStateController.mShowing;
        boolean z3 = z2 && ((KeyguardViewMediator) this.keyguardViewMediatorLazy.get()).mWakeAndUnlocking && this.keyguardStateController.mOccluded;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(" isWakingUnlockToOcclude=", "OplusCentralSurfacesExI-->", TextFieldImplKt$.ExternalSyntheticOutline0.m("isInterceptScrimToUnlock isKeyguardGoingAway=", " isOplusKeyguardGoingAway=", " isShowing=", z, oplusKeyguardGoingAway), z2, z3));
        }
        return z2 && (z || oplusKeyguardGoingAway || z3);
    }

    public final boolean isPanoramicAodInShowWhenFpUnlock() {
        return KeyguardRapidUnlockUtils.Companion.getInstance().isPanoramicAodFpUnlock;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x005d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:29:0x005e A[RETURN] */
    public final boolean isShowOverLockscreen(Object obj, boolean z) {
        ActivityIntentHelper activityIntentHelper;
        if (obj instanceof Intent) {
            if (this.keyguardStateController.mShowing && (activityIntentHelper = this.activityIntentHelper) != null && ActivityIntentRepository.wouldActivityInfoShowOverLockscreen(activityIntentHelper.getTargetActivityInfo((Intent) obj, this.lockScreenUserManager.getCurrentUserId(), false))) {
                return true;
            }
            return false;
        }
        if (!(obj instanceof PendingIntent)) {
            if ((obj instanceof Integer) && this.keyguardStateController.mShowing && ((Integer) obj).intValue() != -1 && z) {
                return true;
            }
            return false;
        }
        if (this.keyguardStateController.mShowing) {
            if (!z) {
                ActivityIntentHelper activityIntentHelper2 = this.activityIntentHelper;
                if (activityIntentHelper2 != null) {
                    if (activityIntentHelper2.wouldPendingShowOverLockscreen(this.lockScreenUserManager.getCurrentUserId(), (PendingIntent) obj)) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean isTransitionInterrupt(ShadeSurface shadeSurface) {
        if (shadeSurface instanceof NotificationPanelViewController) {
            return NotificationExtFlag.OPLUS_HEADSUP ? ((HeadsUpAnimator) this.headsUpAnimator.get()).getIsNeedInterceptZoom() : ((NotificationPanelViewController) shadeSurface).getExt().getIsNeedInterceptZoom();
        }
        return false;
    }

    public final boolean isWakingFromFingerprintButNotUnlocking(boolean z) {
        SmoothTransitionController.Companion companion = SmoothTransitionController.Companion;
        boolean z2 = false;
        if (!companion.isPanoramicAodEnable(this.context) && !companion.isWorkShopAodEnable(this.context)) {
            return false;
        }
        if (!z) {
            WakefulnessModel wakefulnessModel = (WakefulnessModel) ((PowerInteractor) this.powerInteractor.get()).getDetailedWakefulness().getValue();
            if (wakefulnessModel.getInternalWakefulnessState() == WakefulnessState.STARTING_TO_WAKE && wakefulnessModel.getLastWakeReason() == WakeSleepReason.OTHER && wakefulnessModel.getLastWakeReason().getPowerManagerWakeReason() == 98) {
                z2 = true;
            }
        }
        if (z2 && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "OplusCentralSurfacesExI-->isWakingFromFingerprintButNotUnlocking = true");
        }
        return z2;
    }

    public final boolean loopStartActivity(Context context, Iterator<? extends Intent> it, String str) {
        ActivityIntentHelper activityIntentHelper;
        if (!it.hasNext()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusCentralSurfacesExI-->loopStartActivity, iterator has no next");
            }
            return false;
        }
        try {
            Intent next = it.next();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                StringBuilder sb = new StringBuilder("OplusCentralSurfacesExI-->");
                sb.append("loopStartActivity, intent.action: " + (next != null ? next.getAction() : ""));
                Log.d("SystemUi--Notification", sb.toString());
            }
            boolean z = this.keyguardStateController.mShowing && next != null && (activityIntentHelper = this.activityIntentHelper) != null && ActivityIntentRepository.wouldActivityInfoShowOverLockscreen(activityIntentHelper.getTargetActivityInfo(next, this.lockScreenUserManager.getCurrentUserId(), false));
            loopStartActivity.postKeyguardAction.1 r8 = new loopStartActivity.postKeyguardAction.1();
            r8.this$0 = this;
            r8.$intent = next;
            r8.$iterator = it;
            r8.$showOverLockscreen = z;
            VarHandle.storeStoreFence();
            if (z) {
                ((OplusSeedlingCardController) this.oplusSeedlingCardController.get()).setShowActivityOverLockscreen(true);
                r8.onDismiss();
            } else {
                this.activityStarter.dismissKeyguardThenExecute(r8, (Runnable) null, false);
            }
            if (str != null) {
                NotificationStatisticUtil.ubiquitousNotificationClick(context, str);
                return true;
            }
        } catch (Exception e) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                String string = e.toString();
                Log.d("SystemUi--Notification", "OplusCentralSurfacesExI-->".concat(string != null ? string : ""));
            }
        }
        return true;
    }

    public void onCentralSurfacesStartComplete(CentralSurfaces centralSurfaces, ActivityIntentHelper activityIntentHelper, NotificationActivityStarter notificationActivityStarter, NotificationStackScrollLayoutController notificationStackScrollLayoutController, NotificationPanelViewController notificationPanelViewController) {
        this.centralSurfaces = centralSurfaces;
        this.activityIntentHelper = activityIntentHelper;
        this.notificationActivityStarter = notificationActivityStarter;
        ((NotificationCenterManager) ((NotificationCenterManagerEx) this.notificationCenterManagerEx.get())).onCentralSurfacesStartComplete();
        this.pluginDependencyProvider.allowPluginDependency(SysUiStateController.class, this.sysUiStateController);
        this.oplusPluginDependencyProvider.allowPluginDependency(SysUiStateController.class, this.sysUiStateController);
        this.mDoubleTapPowerObserver.onChange(true);
        this.context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("double_tap_power_button_value"), true, this.mDoubleTapPowerObserver, -1);
        this.shadeListBuilderEx.setNotificationPanelViewController(notificationPanelViewController);
        StatusBarHelper.Companion.getInstance(this.context).registerCurrentTypefaceObserver();
        OplusKeyboardShortcutsDependencyInstance.INSTANCE.getOplusKeyboardShortcutsInstance().getKeyboardShortcutsGuideEx().initialize();
        KeyguardStateControllerImpl keyguardStateControllerImpl = this.keyguardStateController;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$centralSurfaces = centralSurfaces;
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        KeyguardStateControllerImpl keyguardStateControllerImpl2 = keyguardStateControllerImpl;
        keyguardStateControllerImpl2.getClass();
        keyguardStateControllerImpl2.addCallback(anonymousClass1);
    }

    public final void onScreenTurnedOff(boolean z) {
        if (z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CentralSurfaces-->instantly collapse the shade when screen turned off");
            }
            this.shadeController.collapseShade(false);
        }
    }

    public void onStatusBarViewUpdated(PhoneStatusBarView phoneStatusBarView, PhoneStatusBarViewController phoneStatusBarViewController, PhoneStatusBarTransitions phoneStatusBarTransitions) {
        ((OplusFlavorManager) this.flavorManager.get()).getMiniCapsuleManager().injectStatusBarView(phoneStatusBarView);
    }

    public void setBrightnessMirrorController(Fragment fragment, MirrorController mirrorController) {
        QSFragmentLegacy qSFragmentLegacy = fragment instanceof QSFragmentLegacy ? (QSFragmentLegacy) fragment : null;
        if (qSFragmentLegacy != null) {
            qSFragmentLegacy.setBrightnessMirrorController(mirrorController);
        }
    }

    public final void setCentralSurfaces(CentralSurfaces centralSurfaces) {
        this.centralSurfaces = centralSurfaces;
    }

    public void setShouldBeKeyguard(boolean z) {
        this.shouldBeKeyguard = z;
    }

    public final boolean shouldDeferHideKeyguardForRemoteAppOpen() {
        OplusKgdTransitionAnimationController oplusKgdTransitionAnimationController;
        OplusKeyguardViewMediatorExImpl oplusKeyguardViewMediatorExImpl = (OplusKeyguardViewMediatorExImpl) this.keyguardViewMediatorExImpl.get();
        if (oplusKeyguardViewMediatorExImpl == null || (oplusKgdTransitionAnimationController = oplusKeyguardViewMediatorExImpl.getOplusKgdTransitionAnimationController()) == null) {
            return false;
        }
        return oplusKgdTransitionAnimationController.shouldDeferHideKeyguardForRemoteAppOpen();
    }

    public boolean startInKeyGuardShow(StartInKeyGuardShowParams startInKeyGuardShowParams) {
        try {
            if (startInKeyGuardShowParams instanceof StartInKeyGuardShowParams.StartIntentInKeyGuardShowParam) {
                return startIntentInKeyGuardShow(((StartInKeyGuardShowParams.StartIntentInKeyGuardShowParam) startInKeyGuardShowParams).getIterator(), ((StartInKeyGuardShowParams.StartIntentInKeyGuardShowParam) startInKeyGuardShowParams).getSeedServiceId(), ((StartInKeyGuardShowParams.StartIntentInKeyGuardShowParam) startInKeyGuardShowParams).getOverLockscreen(), ((StartInKeyGuardShowParams.StartIntentInKeyGuardShowParam) startInKeyGuardShowParams).getOverrideTransition());
            }
            if (startInKeyGuardShowParams instanceof StartInKeyGuardShowParams.StartPendingIntentInKeyGuardShowParam) {
                String seedServiceId = ((StartInKeyGuardShowParams.StartPendingIntentInKeyGuardShowParam) startInKeyGuardShowParams).getSeedServiceId();
                PendingIntent pendingIntent = ((StartInKeyGuardShowParams.StartPendingIntentInKeyGuardShowParam) startInKeyGuardShowParams).getPendingIntent();
                boolean overLockscreen = ((StartInKeyGuardShowParams.StartPendingIntentInKeyGuardShowParam) startInKeyGuardShowParams).getOverLockscreen();
                boolean overrideTransition = ((StartInKeyGuardShowParams.StartPendingIntentInKeyGuardShowParam) startInKeyGuardShowParams).getOverrideTransition();
                boolean zIsShowOverLockscreen = isShowOverLockscreen(pendingIntent, overLockscreen);
                startTypeAction(new UbiquitousClickAfterDismissedParams.UbiquitousClickPendingIntentParam(zIsShowOverLockscreen, pendingIntent, overrideTransition, seedServiceId), zIsShowOverLockscreen, seedServiceId);
                return true;
            }
            if (!(startInKeyGuardShowParams instanceof StartInKeyGuardShowParams.StartTaskIdInKeyGuardShowParam)) {
                throw new NoWhenBranchMatchedException();
            }
            String seedServiceId2 = ((StartInKeyGuardShowParams.StartTaskIdInKeyGuardShowParam) startInKeyGuardShowParams).getSeedServiceId();
            int taskId = ((StartInKeyGuardShowParams.StartTaskIdInKeyGuardShowParam) startInKeyGuardShowParams).getTaskId();
            Intent baseIntent = ((StartInKeyGuardShowParams.StartTaskIdInKeyGuardShowParam) startInKeyGuardShowParams).getBaseIntent();
            boolean overLockscreen2 = ((StartInKeyGuardShowParams.StartTaskIdInKeyGuardShowParam) startInKeyGuardShowParams).getOverLockscreen();
            boolean overrideTransition2 = ((StartInKeyGuardShowParams.StartTaskIdInKeyGuardShowParam) startInKeyGuardShowParams).getOverrideTransition();
            boolean zIsShowOverLockscreen2 = isShowOverLockscreen(Integer.valueOf(taskId), overLockscreen2);
            startTypeAction(new UbiquitousClickAfterDismissedParams.UbiquitousClickTaskIdParam(zIsShowOverLockscreen2, taskId, baseIntent, overrideTransition2, seedServiceId2), zIsShowOverLockscreen2, seedServiceId2);
            return true;
        } catch (Exception e) {
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, e.toString());
            return true;
        }
    }

    public final boolean startIntentInKeyGuardShow(Iterator it, String str, boolean z, boolean z2) {
        if (!it.hasNext()) {
            if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                return false;
            }
            Log.d("SystemUi--Notification", "OplusCentralSurfacesExI-->startInKeyGuardShow, iterator has no next");
            return false;
        }
        Intent intent = (Intent) it.next();
        boolean zIsShowOverLockscreen = isShowOverLockscreen(intent, z);
        StatusBarNotificationActivityStarter statusBarNotificationActivityStarter = this.notificationActivityStarter;
        if (statusBarNotificationActivityStarter instanceof StatusBarNotificationActivityStarter) {
            startTypeAction(new UbiquitousClickAfterDismissedParams.UbiquitousClickIntentParam(intent, it, zIsShowOverLockscreen, statusBarNotificationActivityStarter, z2, str), zIsShowOverLockscreen, str);
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x002c A[PHI: r4
      0x002c: PHI (r4v11 boolean) = (r4v4 boolean), (r4v6 boolean), (r4v16 boolean) binds: [B:29:0x007f, B:18:0x005a, B:11:0x002a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:31:0x0082  */
    public final void startTypeAction(UbiquitousClickAfterDismissedParams ubiquitousClickAfterDismissedParams, boolean z, String str) {
        boolean zIsActivity;
        boolean z2;
        int currentUserId = this.lockScreenUserManager.getCurrentUserId();
        if (ubiquitousClickAfterDismissedParams instanceof UbiquitousClickAfterDismissedParams.UbiquitousClickIntentParam) {
            Intent intent = ((UbiquitousClickAfterDismissedParams.UbiquitousClickIntentParam) ubiquitousClickAfterDismissedParams).getIntent();
            if (intent != null) {
                zIsActivity = intent.resolveActivity(this.context.getPackageManager()) != null;
                if (this.notifActivityIntentHelper.getTargetActivityInfo(intent, currentUserId, false) == null) {
                    z2 = true;
                }
            } else {
                zIsActivity = true;
            }
            z2 = false;
        } else if (ubiquitousClickAfterDismissedParams instanceof UbiquitousClickAfterDismissedParams.UbiquitousClickPendingIntentParam) {
            PendingIntent pendingIntent = ((UbiquitousClickAfterDismissedParams.UbiquitousClickPendingIntentParam) ubiquitousClickAfterDismissedParams).getPendingIntent();
            if (pendingIntent != null) {
                zIsActivity = pendingIntent.isActivity();
                ActivityIntentHelper activityIntentHelper = this.notifActivityIntentHelper;
                activityIntentHelper.getClass();
                ActivityIntentRepository.Companion.getClass();
                if (ActivityIntentRepository.toActivityInfo(pendingIntent.queryIntentComponents(852096), pendingIntent.getIntent(), 852096, currentUserId, activityIntentHelper.mPm) == null) {
                    z2 = true;
                }
            } else {
                zIsActivity = true;
            }
            z2 = false;
        } else {
            if (!(ubiquitousClickAfterDismissedParams instanceof UbiquitousClickAfterDismissedParams.UbiquitousClickTaskIdParam)) {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return;
            }
            Intent baseIntent = ((UbiquitousClickAfterDismissedParams.UbiquitousClickTaskIdParam) ubiquitousClickAfterDismissedParams).getBaseIntent();
            if (baseIntent != null) {
                zIsActivity = baseIntent.resolveActivity(this.context.getPackageManager()) != null;
                if (this.notifActivityIntentHelper.getTargetActivityInfo(baseIntent, currentUserId, false) == null) {
                    z2 = true;
                }
            } else {
                zIsActivity = true;
            }
            z2 = false;
        }
        boolean z3 = !(zIsActivity && z2) && this.activityStarter.shouldAnimateLaunch(zIsActivity);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("OplusCentralSurfacesExI-->", "SystemUi--Notification", TextFieldImplKt$.ExternalSyntheticOutline0.m("isActivityIntent=", ", wouldLaunchResolverActivity=", ", animate=", zIsActivity, z2), z3);
        }
        OplusCentralSurfacesExImpl$startTypeAction$postKeyguardAction$1 oplusCentralSurfacesExImpl$startTypeAction$postKeyguardAction$1 = new OplusCentralSurfacesExImpl$startTypeAction$postKeyguardAction$1();
        oplusCentralSurfacesExImpl$startTypeAction$postKeyguardAction$1.this$0 = this;
        oplusCentralSurfacesExImpl$startTypeAction$postKeyguardAction$1.$params = ubiquitousClickAfterDismissedParams;
        oplusCentralSurfacesExImpl$startTypeAction$postKeyguardAction$1.$animate = z3;
        oplusCentralSurfacesExImpl$startTypeAction$postKeyguardAction$1.$showOverLockscreen = z;
        VarHandle.storeStoreFence();
        if (z) {
            ((OplusSeedlingCardController) this.oplusSeedlingCardController.get()).setShowActivityOverLockscreen(true);
            oplusCentralSurfacesExImpl$startTypeAction$postKeyguardAction$1.onDismiss();
        } else {
            this.activityStarter.dismissKeyguardThenExecute(oplusCentralSurfacesExImpl$startTypeAction$postKeyguardAction$1, (Runnable) null, false);
        }
        if (str != null) {
            NotificationStatisticUtil.ubiquitousNotificationClick(this.context, str);
        }
    }
}
