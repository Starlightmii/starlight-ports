package com.oplus.systemui.notification.lockscreen;

import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.power.shared.model.WakefulnessModel;
import com.android.systemui.util.SeparateModeHelper;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateManager;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function6;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$5 extends SuspendLambda implements Function6 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    /* synthetic */ boolean Z$3;
    int label;
    final /* synthetic */ OplusNotificationCapsuleInteractorImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$5(OplusNotificationCapsuleInteractorImpl oplusNotificationCapsuleInteractorImpl, Continuation continuation) {
        super(6, continuation);
        this.this$0 = oplusNotificationCapsuleInteractorImpl;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
        boolean zBooleanValue3 = ((Boolean) obj4).booleanValue();
        boolean zBooleanValue4 = ((Boolean) obj5).booleanValue();
        OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$5 oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$5 = new OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$5(this.this$0, (Continuation) obj6);
        oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$5.L$0 = (WakefulnessModel) obj;
        oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$5.Z$0 = zBooleanValue;
        oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$5.Z$1 = zBooleanValue2;
        oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$5.Z$2 = zBooleanValue3;
        oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$5.Z$3 = zBooleanValue4;
        return oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$5.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0078  */
    public final Object invokeSuspend(Object obj) {
        boolean z;
        WakefulnessModel wakefulnessModel = (WakefulnessModel) this.L$0;
        boolean z2 = this.Z$0;
        boolean z3 = this.Z$1;
        boolean z4 = this.Z$2;
        boolean z5 = this.Z$3;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        float fFloatValue = ((Number) this.this$0.shadeRepository.getLockscreenShadeExpansion().getValue()).floatValue();
        boolean z6 = false;
        boolean z7 = z3 && ((fFloatValue > 0.0f ? 1 : (fFloatValue == 0.0f ? 0 : -1)) == 0);
        boolean zIsImmersiveState = ((LockScreenNotificationExtStateManager) this.this$0.lockScreenExtStateController.get()).isImmersiveState();
        boolean separateMode = SeparateModeHelper.INSTANCE.getSeparateMode();
        if (wakefulnessModel.isAwake()) {
            z = true;
        } else {
            SmoothTransitionController.Companion companion = SmoothTransitionController.Companion;
            if (companion.isPanoramicAodEnable(this.this$0.context) || companion.getInstance(this.this$0.context).isWorkShopAod()) {
                z = true;
            } else {
                z = false;
            }
        }
        boolean z8 = z7 && (separateMode || !zIsImmersiveState);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("keyguardNotificationCapsule: detailedWakefulness isAwake=", ", isAllowShowInOff=", ", isKeyguardShowing=", wakefulnessModel.isAwake(), z);
            KeyguardLogger$.ExternalSyntheticOutline0.m(", isQuickSettingsVisible=", ", lockscreenShadeExpansion=", sbM, z2, z3);
            sbM.append(fFloatValue);
            sbM.append(", isKeyguardToNtfCenterComplete=");
            sbM.append(z4);
            sbM.append(", forceHide=");
            Log.d("SystemUi--Keyguard", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(", hideCapsuleForQsVisible=", "OplusNotificationCapsuleInteractor-->", sbM, z5, z8));
        }
        if (z && z2 && !z8 && !z4 && !z5) {
            z6 = true;
        }
        return Boxing.boxBoolean(z6);
    }
}
