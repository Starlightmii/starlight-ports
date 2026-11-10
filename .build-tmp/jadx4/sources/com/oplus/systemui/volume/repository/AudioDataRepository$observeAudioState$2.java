package com.oplus.systemui.volume.repository;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlowImpl;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class AudioDataRepository$observeAudioState$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ AudioDataRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioDataRepository$observeAudioState$2(AudioDataRepository audioDataRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = audioDataRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new AudioDataRepository$observeAudioState$2(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object value;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        StateFlowImpl stateFlowImpl = this.this$0._audioState;
        AudioDataRepository audioDataRepository = this.this$0;
        do {
            value = stateFlowImpl.getValue();
        } while (!stateFlowImpl.compareAndSet(value, AudioDataRepository.AudioState.copy$default((AudioDataRepository.AudioState) value, 0, audioDataRepository.getAudioManager().isMusicActive(), audioDataRepository.checkCallActive(), false, false, 25)));
        return Unit.INSTANCE;
    }
}
