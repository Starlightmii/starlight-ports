package com.oplus.systemui.qs.anim;

import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.qs.OplusQSTransitionAnimatorController;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.shade.ShadeControllerImpl;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import dagger.Lazy;
import java.util.Locale;
import java.util.Set;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSActivityStarter {
    private static final String START_EXTRA_KEY = "StartFromKeyguard";
    private static final String TAG = "OplusQSActivityStarter";
    private final ActivityStarter activityStarter;
    private final Lazy keyguardStateController;
    private final Lazy oplusSeparateNotificationAndQSExLazy;
    private final Lazy platformBlurManager;
    private final CoroutineScope scope;
    private final ShadeControllerImpl shadeControllerImpl;
    private final ShadeInteractor shadeInteractor;
    public static final Companion Companion = new Companion();
    private static final Set<String> ANIMATION_CONTROLLER_BLACK_LIST = SetsKt__SetsKt.setOf(new String[]{"com.oplus.eyeprotect", "com.oplus.linker", "com.coloros.oshare"});

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final boolean access$isLargeScreen(Companion companion) {
            companion.getClass();
            FeatureOption.INSTANCE.getClass();
            return (FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled() && DeviceStateController.getInstance().isOpenOrHalfOpen()) || FeatureOption.isTablet();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class QSTransitionAnimator {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ QSTransitionAnimator[] $VALUES;
        public static final QSTransitionAnimator DIALOG;
        public static final QSTransitionAnimator QUICKENTRANCE;
        public static final QSTransitionAnimator SETTINGS;

        static {
            QSTransitionAnimator qSTransitionAnimator = new QSTransitionAnimator("SETTINGS", 0);
            SETTINGS = qSTransitionAnimator;
            QSTransitionAnimator qSTransitionAnimator2 = new QSTransitionAnimator("DIALOG", 1);
            DIALOG = qSTransitionAnimator2;
            QSTransitionAnimator qSTransitionAnimator3 = new QSTransitionAnimator("QUICKENTRANCE", 2);
            QUICKENTRANCE = qSTransitionAnimator3;
            QSTransitionAnimator[] qSTransitionAnimatorArr = {qSTransitionAnimator, qSTransitionAnimator2, qSTransitionAnimator3};
            $VALUES = qSTransitionAnimatorArr;
            $ENTRIES = EnumEntriesKt.enumEntries(qSTransitionAnimatorArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static QSTransitionAnimator valueOf(String str) {
            return (QSTransitionAnimator) Enum.valueOf(QSTransitionAnimator.class, str);
        }

        public static QSTransitionAnimator[] values() {
            return (QSTransitionAnimator[]) $VALUES.clone();
        }
    }

    public OplusQSActivityStarter(ActivityStarter activityStarter, Lazy lazy, CoroutineScope coroutineScope, Lazy lazy2, ShadeInteractor shadeInteractor, ShadeControllerImpl shadeControllerImpl, Lazy lazy3) {
        this.activityStarter = activityStarter;
        this.keyguardStateController = lazy;
        this.scope = coroutineScope;
        this.oplusSeparateNotificationAndQSExLazy = lazy2;
        this.shadeInteractor = shadeInteractor;
        this.shadeControllerImpl = shadeControllerImpl;
        this.platformBlurManager = lazy3;
    }

    public final ActivityTransitionAnimator.Controller getTileAnimationController(Expandable expandable, View view, boolean z) {
        if (!shouldDisableTransitionAnimator()) {
            return new OplusQSTransitionAnimatorController(view, ((OplusSeparateNotificationAndQSEx) this.oplusSeparateNotificationAndQSExLazy.get()).enableSeparateNotificationAndQS(), this.shadeControllerImpl, this.shadeInteractor, this.scope, Companion.access$isLargeScreen(Companion) ? this.platformBlurManager : null).getActivityTransitionAnimator();
        }
        if (z) {
            return expandable.activityTransitionController(32);
        }
        return null;
    }

    public final void onAnimationButtonClicked(View view, Intent intent, QSTransitionAnimator qSTransitionAnimator) {
        OplusQSTransitionAnimatorController oplusQSSettingsTransitionAnimatorController;
        if (intent != null) {
            if (shouldDisableTransitionAnimator()) {
                this.activityStarter.postStartActivityDismissingKeyguard(intent, 0);
                return;
            }
            boolean z = ((KeyguardStateController) this.keyguardStateController.get()).mShowing;
            if (z) {
                intent.putExtra(START_EXTRA_KEY, ((KeyguardStateController) this.keyguardStateController.get()).mShowing);
            }
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "postStartActivityDismissingKeyguard intent: " + intent + " keyguardIsShowing:" + z, null, 8);
            boolean zEnableSeparateNotificationAndQS = ((OplusSeparateNotificationAndQSEx) this.oplusSeparateNotificationAndQSExLazy.get()).enableSeparateNotificationAndQS();
            int iOrdinal = qSTransitionAnimator.ordinal();
            if (iOrdinal == 0) {
                oplusQSSettingsTransitionAnimatorController = new OplusQSSettingsTransitionAnimatorController(view, this.shadeControllerImpl, zEnableSeparateNotificationAndQS, this.shadeInteractor, this.scope, Companion.access$isLargeScreen(Companion) ? this.platformBlurManager : null);
            } else if (iOrdinal == 1) {
                oplusQSSettingsTransitionAnimatorController = new OplusQSDialogTransitionAnimatorController(view, this.shadeControllerImpl, zEnableSeparateNotificationAndQS, this.shadeInteractor, this.scope);
            } else {
                if (iOrdinal != 2) {
                    LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                    return;
                }
                oplusQSSettingsTransitionAnimatorController = new OplusQSTransitionAnimatorController(view, zEnableSeparateNotificationAndQS, this.shadeControllerImpl, this.shadeInteractor, this.scope, Companion.access$isLargeScreen(Companion) ? this.platformBlurManager : null);
            }
            this.activityStarter.postStartActivityDismissingKeyguard(intent, 0, oplusQSSettingsTransitionAnimatorController.getActivityTransitionAnimator());
        }
    }

    public final void postStartActivityDismissingKeyguard(Expandable expandable, Intent intent, boolean z) {
        View view = expandable != null ? expandable.getView() : null;
        if (intent != null) {
            if (shouldDisableTransitionAnimator() || view == null) {
                this.activityStarter.postStartActivityDismissingKeyguard(intent, 0);
                return;
            }
            boolean z2 = ((KeyguardStateController) this.keyguardStateController.get()).mShowing;
            if (z2) {
                intent.putExtra(START_EXTRA_KEY, ((KeyguardStateController) this.keyguardStateController.get()).mShowing);
            }
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "onTileClickJumpToApp intent: " + intent + " keyguardIsShowing:" + z2, null, 8);
            this.activityStarter.postStartActivityDismissingKeyguard(intent, 0, getTileAnimationController(expandable, view, z));
        }
    }

    public final boolean shouldDisableTransitionAnimator() {
        return ScrimUtil.isBAnimLevel() || !((KeyguardStateController) this.keyguardStateController.get()).isUnlocked();
    }

    public final void postStartActivityDismissingKeyguard(Expandable expandable, PendingIntent pendingIntent, boolean z) {
        View view = expandable != null ? expandable.getView() : null;
        if (pendingIntent != null) {
            String creatorPackage = pendingIntent.getCreatorPackage();
            String lowerCase = creatorPackage != null ? creatorPackage.toLowerCase(Locale.ROOT) : null;
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "onTileClickJumpToApp PendingIntent: " + pendingIntent + ", pkg=" + lowerCase, null, 8);
            ActivityStarter activityStarter = this.activityStarter;
            if (view == null) {
                activityStarter.postStartActivityDismissingKeyguard(pendingIntent);
            } else {
                Companion.getClass();
                activityStarter.startPendingIntentMaybeDismissingKeyguard(pendingIntent, (Runnable) null, CollectionsKt___CollectionsKt.contains(ANIMATION_CONTROLLER_BLACK_LIST, lowerCase) ? null : getTileAnimationController(expandable, view, z));
            }
        }
    }
}
