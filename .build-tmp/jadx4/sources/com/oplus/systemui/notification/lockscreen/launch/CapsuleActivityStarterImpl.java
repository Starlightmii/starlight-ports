package com.oplus.systemui.notification.lockscreen.launch;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.RemoteAnimationAdapter;
import android.view.View;
import com.android.systemui.DependencyEx;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.domain.interactor.ShadeAnimationInteractor;
import com.android.systemui.shade.domain.interactor.ShadeDialogContextInteractor;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.phone.ActivityStarterUtilsKt;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarterEx;
import com.android.systemui.statusbar.phone.StatusBarTransitionAnimatorController;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleActivityStarterImpl implements CapsuleActivityStarter {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleActivityStarter";
    private final ActivityTransitionAnimator activityTransitionAnimator;
    private final CommandQueue commandQueue;
    private final CommonLaunchAnimatorControllerProvider commonLaunchAnimatorControllerProvider;
    private final Context context;
    private final ShadeDialogContextInteractor contextInteractor;
    private final KeyguardViewMediator keyguardViewMediator;
    private final Handler mainThreadHandler;
    private final NotificationShadeWindowController notificationShadeWindowController;
    private final ShadeAnimationInteractor shadeAnimationInteractor;
    private final ShadeController shadeController;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.launch.CapsuleActivityStarterImpl$startViewIntent$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ActivityTransitionAnimator.LegacyPendingIntentStarter {
        public /* synthetic */ int $displayId;
        public /* synthetic */ Intent $fillInIntent;
        public /* synthetic */ PendingIntent $intent;
        public /* synthetic */ boolean $isActivityIntent;
        public /* synthetic */ CapsuleActivityStarterImpl this$0;

        public final int startPendingIntent(RemoteAnimationAdapter remoteAnimationAdapter) {
            Bundle bundleCreateActivityOptions = ActivityStarterUtilsKt.createActivityOptions(this.$displayId, remoteAnimationAdapter);
            PendingIntent pendingIntent = this.$intent;
            CapsuleActivityStarterImpl capsuleActivityStarterImpl = this.this$0;
            int iSendAndReturnResult = pendingIntent.sendAndReturnResult(capsuleActivityStarterImpl.context, 0, this.$fillInIntent, null, null, null, bundleCreateActivityOptions);
            StatusBarNotificationActivityStarterEx statusBarNotificationActivityStarterEx = (StatusBarNotificationActivityStarterEx) DependencyEx.sDependency.getDependency(StatusBarNotificationActivityStarterEx.class);
            boolean z = this.$isActivityIntent;
            Handler handler = capsuleActivityStarterImpl.mainThreadHandler;
            startPendingIntent.1 r3 = new startPendingIntent.1();
            r3.this$0 = capsuleActivityStarterImpl;
            VarHandle.storeStoreFence();
            statusBarNotificationActivityStarterEx.collapseOnMainThread(z, handler, r3);
            return iSendAndReturnResult;
        }
    }

    public CapsuleActivityStarterImpl(Context context, ShadeDialogContextInteractor shadeDialogContextInteractor, ActivityTransitionAnimator activityTransitionAnimator, ShadeAnimationInteractor shadeAnimationInteractor, ShadeController shadeController, NotificationShadeWindowController notificationShadeWindowController, CommandQueue commandQueue, KeyguardViewMediator keyguardViewMediator, Handler handler, CommonLaunchAnimatorControllerProvider commonLaunchAnimatorControllerProvider) {
        this.context = context;
        this.contextInteractor = shadeDialogContextInteractor;
        this.activityTransitionAnimator = activityTransitionAnimator;
        this.shadeAnimationInteractor = shadeAnimationInteractor;
        this.shadeController = shadeController;
        this.notificationShadeWindowController = notificationShadeWindowController;
        this.commandQueue = commandQueue;
        this.keyguardViewMediator = keyguardViewMediator;
        this.mainThreadHandler = handler;
        this.commonLaunchAnimatorControllerProvider = commonLaunchAnimatorControllerProvider;
    }

    @Override // com.oplus.systemui.notification.lockscreen.launch.CapsuleActivityStarter
    public void startViewIntent(PendingIntent pendingIntent, Intent intent, ExpandableView expandableView, boolean z, boolean z2) {
        try {
            int displayId = this.contextInteractor.getContext().getDisplayId();
            StatusBarTransitionAnimatorController statusBarTransitionAnimatorController = new StatusBarTransitionAnimatorController(this.commonLaunchAnimatorControllerProvider.getAnimatorController(expandableView, null), this.shadeAnimationInteractor, this.shadeController, this.notificationShadeWindowController, this.commandQueue, displayId, z2, Boolean.FALSE, (Bundle) null, (View) null, this.keyguardViewMediator.isShowingAndNotOccluded());
            ActivityTransitionAnimator activityTransitionAnimator = this.activityTransitionAnimator;
            String creatorPackage = pendingIntent.getCreatorPackage();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.$displayId = displayId;
            anonymousClass1.$intent = pendingIntent;
            anonymousClass1.this$0 = this;
            anonymousClass1.$fillInIntent = intent;
            anonymousClass1.$isActivityIntent = z2;
            VarHandle.storeStoreFence();
            activityTransitionAnimator.startPendingIntentWithAnimation(statusBarTransitionAnimatorController, z, creatorPackage, false, true, anonymousClass1);
        } catch (PendingIntent.CanceledException unused) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "PendingIntent cancelled");
        }
    }
}
