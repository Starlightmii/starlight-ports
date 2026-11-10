package com.oplus.systemui.notification.lockscreen.base;

import android.util.Log;
import androidx.collection.FloatList$;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class CapsuleTextColorController$start$2$1$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ CapsuleTextColorController this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.base.CapsuleTextColorController$start$2$1$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ CapsuleTextColorController this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        public final Object emit(Object obj, Continuation continuation) {
            switch (this.$r8$classId) {
                case 0:
                    ((Boolean) obj).booleanValue();
                    this.this$0.applyNewTextColorModel("isThirdThemeModeChange");
                    break;
                default:
                    ILockScreenExtStateController.ExtStateEvent extStateEvent = (ILockScreenExtStateController.ExtStateEvent) obj;
                    CapsuleTextColorController capsuleTextColorController = this.this$0;
                    boolean z = capsuleTextColorController.isImmersiveState;
                    boolean z2 = extStateEvent.getNewState() == 2;
                    if (z != z2) {
                        capsuleTextColorController.isImmersiveState = z2;
                        String str = z2 ? "EnterImmersiveState" : "ExitImmersiveState";
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            int oldState = extStateEvent.getOldState();
                            int newState = extStateEvent.getNewState();
                            boolean z3 = capsuleTextColorController.isImmersiveState;
                            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("onExtStateChanged: ", oldState, newState, " -> ", ", isImmersiveState: ");
                            sbM.append(z3);
                            sbM.append(", reason: ");
                            sbM.append(str);
                            Log.d("SystemUi--Notification", "CapsuleTextColorController-->".concat(sbM.toString()));
                        }
                        capsuleTextColorController.applyNewTextColorModel(str);
                    }
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CapsuleTextColorController$start$2$1$1(CapsuleTextColorController capsuleTextColorController, Continuation continuation) {
        super(2, continuation);
        this.this$0 = capsuleTextColorController;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new CapsuleTextColorController$start$2$1$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            StateFlow stateFlowIsThirdThemeMode = ScrimControllerEx.Companion.isThirdThemeMode();
            CapsuleTextColorController capsuleTextColorController = this.this$0;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
            anonymousClass1.this$0 = capsuleTextColorController;
            VarHandle.storeStoreFence();
            this.label = 1;
            if (stateFlowIsThirdThemeMode.collect(anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        Regex$.ExternalSyntheticBUOutline0.m$1();
        return null;
    }
}
