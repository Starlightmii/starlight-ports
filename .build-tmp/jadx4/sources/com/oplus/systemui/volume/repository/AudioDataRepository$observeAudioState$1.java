package com.oplus.systemui.volume.repository;

import android.media.AudioDeviceInfo;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.biometrics.ui.PromptIconState$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.StateFlowImpl;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class AudioDataRepository$observeAudioState$1 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AudioDataRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioDataRepository$observeAudioState$1(AudioDataRepository audioDataRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = audioDataRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        AudioDataRepository$observeAudioState$1 audioDataRepository$observeAudioState$1 = new AudioDataRepository$observeAudioState$1(this.this$0, continuation);
        audioDataRepository$observeAudioState$1.L$0 = obj;
        return audioDataRepository$observeAudioState$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((AudioDeviceInfo) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object value;
        AudioDeviceInfo audioDeviceInfo = (AudioDeviceInfo) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        boolean zContains = CollectionsKt___CollectionsKt.contains(SetsKt__SetsKt.setOf(new Integer[]{Boxing.boxInt(7), Boxing.boxInt(26)}), audioDeviceInfo != null ? Boxing.boxInt(audioDeviceInfo.getType()) : null);
        boolean z = false;
        if (audioDeviceInfo != null && audioDeviceInfo.getType() == 2) {
            z = true;
        }
        boolean z2 = z;
        StateFlowImpl stateFlowImpl = this.this$0._audioState;
        do {
            value = stateFlowImpl.getValue();
        } while (!stateFlowImpl.compareAndSet(value, AudioDataRepository.AudioState.copy$default((AudioDataRepository.AudioState) value, 0, false, false, zContains, z2, 7)));
        AudioDataRepository audioDataRepository = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Integer numBoxInt = audioDeviceInfo != null ? Boxing.boxInt(audioDeviceInfo.getType()) : null;
            int mode = ((AudioDataRepository.AudioState) audioDataRepository._audioState.getValue()).getMode();
            boolean z3 = ((AudioDataRepository.AudioState) audioDataRepository._audioState.getValue()).isMusicActive;
            StringBuilder sb = new StringBuilder("Device changed: ");
            sb.append(numBoxInt);
            sb.append(" | Speaker: ");
            sb.append(z2);
            sb.append(" | BT SCO: ");
            PromptIconState$.ExternalSyntheticOutline0.m(mode, " | mode: ", " | isMusicActive: ", sb, zContains);
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("AudioDataRepository-->", "SystemUi--Volume", sb, z3);
        }
        this.this$0.volumeInteractor.setScoMaxLevel(zContains);
        return Unit.INSTANCE;
    }
}
