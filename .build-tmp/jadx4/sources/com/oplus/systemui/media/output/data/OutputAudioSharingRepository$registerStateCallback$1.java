package com.oplus.systemui.media.output.data;

import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.bluetooth.OplusA2dpSharingManager;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OutputAudioSharingRepository$registerStateCallback$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ OutputAudioSharingRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OutputAudioSharingRepository$registerStateCallback$1(OutputAudioSharingRepository outputAudioSharingRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = outputAudioSharingRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new OutputAudioSharingRepository$registerStateCallback$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean zRegisterStateCallback;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        OplusA2dpSharingManager oplusA2dpSharingManager = this.this$0.getOplusA2dpSharingManager();
        if (oplusA2dpSharingManager != null) {
            OutputAudioSharingRepository outputAudioSharingRepository = this.this$0;
            outputAudioSharingRepository._audioSharingState.setValue(Boxing.boxBoolean(oplusA2dpSharingManager.inSharingMode()));
            oplusA2dpSharingManager.unregisterStateCallback(outputAudioSharingRepository.stateCallback);
            zRegisterStateCallback = oplusA2dpSharingManager.registerStateCallback(outputAudioSharingRepository.mainExecutor, outputAudioSharingRepository.stateCallback);
        } else {
            zRegisterStateCallback = false;
        }
        this.this$0.isStateCallbackRegistered = zRegisterStateCallback;
        LogUtil.i$default(LogUtil.TAG_QS, "OutputAudioSharingRepository", KeyguardLogger$.ExternalSyntheticOutline0.m("registerStateCallback: register = ", zRegisterStateCallback), null, 8);
        return Unit.INSTANCE;
    }
}
