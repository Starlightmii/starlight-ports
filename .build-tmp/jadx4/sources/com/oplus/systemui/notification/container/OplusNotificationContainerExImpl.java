package com.oplus.systemui.notification.container;

import android.content.Context;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.DependencyEx;
import com.android.systemui.common.shared.model.NotificationContainerBounds;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor;
import com.oplus.systemui.keyguard.anim.OplusPanelKeyguardAnimControllerImpl;
import com.oplus.systemui.notification.lockscreen.OplusNotificationCapsuleInteractor;
import com.oplus.systemui.notification.lockscreen.OplusNotificationCapsuleInteractorImpl;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguard;
import com.oplus.systemui.statusbar.util.KeyguardNotificationUploadUtil;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusNotificationContainerExImpl extends OplusNotificationContainerEx {
    private static final Companion Companion = new Companion();
    private static final String TAG = "OplusNotificationContainerExImpl";
    private final Lazy lockscreenShadeTransitionControllerEx;
    private final Lazy notificationDependOnKeyguard;
    private final Lazy oplusPanelKeyguardAnimControllerImpl;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.container.OplusNotificationContainerExImpl$mapNotificationKeyguardAlphaFlow$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        /* synthetic */ float F$0;
        /* synthetic */ float F$1;
        int label;

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            float fFloatValue = ((Number) obj).floatValue();
            float fFloatValue2 = ((Number) obj2).floatValue();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(3, (Continuation) obj3);
            anonymousClass1.F$0 = fFloatValue;
            anonymousClass1.F$1 = fFloatValue2;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            float f = this.F$0;
            float f2 = this.F$1;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxFloat(Math.min(f, f2));
            }
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    public OplusNotificationContainerExImpl(Lazy lazy, Lazy lazy2, Lazy lazy3) {
        this.lockscreenShadeTransitionControllerEx = lazy;
        this.notificationDependOnKeyguard = lazy2;
        this.oplusPanelKeyguardAnimControllerImpl = lazy3;
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public Flow alphaForShadeAndQsExpansion(Flow flow) {
        return FlowKt.emptyFlow();
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public Flow bouncerToGoneTransition(Flow flow) {
        return FlowKt.emptyFlow();
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public Flow dozingToDreamingTransition(Flow flow) {
        return FlowKt.emptyFlow();
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public Flow dozingToLockscreenTransition(Flow flow) {
        return FlowKt.emptyFlow();
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public Flow dozingToOccludedTransition(Flow flow) {
        return FlowKt.emptyFlow();
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public Flow dozingToPrimaryBouncerTransition(Flow flow) {
        return FlowKt.emptyFlow();
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public Flow lockscreenToGoneTransition(Flow flow) {
        return FlowKt.emptyFlow();
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public Flow lockscreenToOccludedTransition(Flow flow) {
        return FlowKt.emptyFlow();
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public Flow lockscreenToPrimaryBouncerTransition(Flow flow) {
        return ((NotificationDependOnKeyguard) this.notificationDependOnKeyguard.get()).getClockAlpha();
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public Flow mapNotificationKeyguardAlphaFlow(Flow flow) {
        return FlowKt.combine(flow, ((OplusPanelKeyguardAnimControllerImpl) this.oplusPanelKeyguardAnimControllerImpl.get()).getNotificationAnimAlpha(), new AnonymousClass1(3, null));
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public Flow occludedToLockscreenTransition(Flow flow) {
        return FlowKt.emptyFlow();
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public Flow offToLockscreenTransition(Flow flow) {
        return FlowKt.emptyFlow();
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public SharedNotificationContainerInteractor.ConfigurationBasedDimensions onConfigurationBasedDimensions(Context context, SharedNotificationContainerInteractor.ConfigurationBasedDimensions configurationBasedDimensions) {
        return configurationBasedDimensions.copy(configurationBasedDimensions.useSplitShade, configurationBasedDimensions.useLargeScreenHeader, context.getResources().getDimensionPixelSize(2131172246), configurationBasedDimensions.marginBottom, configurationBasedDimensions.marginTop, configurationBasedDimensions.marginTopLargeScreen, configurationBasedDimensions.keyguardSplitShadeTopMargin);
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public final void onMaxAlphaForKeyguard(float f) {
        ((OplusNotificationCapsuleInteractorImpl) ((OplusNotificationCapsuleInteractor) DependencyEx.sDependency.getDependency(OplusNotificationCapsuleInteractor.class))).updateKeyguardCapsuleViewAlpha(f);
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public NotificationContainerBounds onNotificationContainerBounds(boolean z, NotificationContainerBounds notificationContainerBounds, int i, float f, boolean z2, float f2) {
        return z ? NotificationContainerBounds.copy$default(notificationContainerBounds, f, 0.0f, ((OplusLockscreenShadeTransitionControllerEx) this.lockscreenShadeTransitionControllerEx.get()).isAnimRunning(), 2) : NotificationContainerBounds.copy$default(notificationContainerBounds, f, 0.0f, false, 2);
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public Flow primaryBouncerToLockscreenTransition(Flow flow) {
        return FlowKt.emptyFlow();
    }

    @Override // com.oplus.systemui.notification.container.OplusNotificationContainerEx
    public final void setMaxAllowedKeyguardNotifications(int i) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "setMaxDisplayedNotifications: maxAllowed="), null, 8);
        KeyguardNotificationUploadUtil.Companion.getInstance().setMaxAllowedKeyguardNotifications(i);
    }
}
