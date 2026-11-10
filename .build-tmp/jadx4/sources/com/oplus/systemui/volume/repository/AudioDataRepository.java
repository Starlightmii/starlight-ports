package com.oplus.systemui.volume.repository;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import androidx.appcompat.app.AppCompatDelegateImpl$;
import androidx.compose.animation.ChangeSize$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.oplus.systemui.volume.OplusVolumeDialogImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AudioDataRepository {
    public static final Companion Companion = new Companion();
    public static final String OPLUS_AUDIO_CALL_STREAM_ACTIVE_TRUE = "true";
    public static final String OPLUS_AUDIO_GET_CALL_STREAM_ACTIVE = "OPLUS_AUDIO_GET_CALL_STREAM_ACTIVE";
    public static final String TAG = "AudioDataRepository";
    private final MutableStateFlow _audioState;
    private final MutableSharedFlow _deviceChanges;
    private final Lazy audioManager$delegate;
    private final StateFlow audioState;
    private final Executor bgExecutor;
    private final Context context;
    private final SharedFlow deviceChanges;
    private final AudioManager.OnCommunicationDeviceChangedListener deviceListener;
    private final AudioManager.OnModeChangedListener modeListener;
    private final Flow musicActiveFlow;
    private final CoroutineScope repositoryScope;
    private final OplusVolumeDialogImpl volumeInteractor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AudioState {
        public final boolean isAudioCallActive;
        public final boolean isMusicActive;
        public final boolean isSpeakerphoneOn;
        public final boolean isStreamInBluetoothSco;
        public final int mode;

        public AudioState(int i, boolean z, boolean z2, boolean z3, boolean z4) {
            this.mode = i;
            this.isMusicActive = z;
            this.isAudioCallActive = z2;
            this.isStreamInBluetoothSco = z3;
            this.isSpeakerphoneOn = z4;
        }

        public static /* synthetic */ AudioState copy$default(AudioState audioState, int i, boolean z, boolean z2, boolean z3, boolean z4, int i2) {
            if ((i2 & 1) != 0) {
                i = audioState.mode;
            }
            int i3 = i;
            if ((i2 & 2) != 0) {
                z = audioState.isMusicActive;
            }
            boolean z5 = z;
            if ((i2 & 4) != 0) {
                z2 = audioState.isAudioCallActive;
            }
            boolean z6 = z2;
            if ((i2 & 8) != 0) {
                z3 = audioState.isStreamInBluetoothSco;
            }
            boolean z7 = z3;
            if ((i2 & 16) != 0) {
                z4 = audioState.isSpeakerphoneOn;
            }
            return audioState.copy(i3, z5, z6, z7, z4);
        }

        public final AudioState copy(int i, boolean z, boolean z2, boolean z3, boolean z4) {
            return new AudioState(i, z, z2, z3, z4);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AudioState)) {
                return false;
            }
            AudioState audioState = (AudioState) obj;
            return this.mode == audioState.mode && this.isMusicActive == audioState.isMusicActive && this.isAudioCallActive == audioState.isAudioCallActive && this.isStreamInBluetoothSco == audioState.isStreamInBluetoothSco && this.isSpeakerphoneOn == audioState.isSpeakerphoneOn;
        }

        public final int getMode() {
            return this.mode;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isSpeakerphoneOn) + ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(Integer.hashCode(this.mode) * 31, 31, this.isMusicActive), 31, this.isAudioCallActive), 31, this.isStreamInBluetoothSco);
        }

        public String toString() {
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("AudioState(mode=", this.mode, ", isMusicActive=", ", isAudioCallActive=", this.isMusicActive);
            KeyguardLogger$.ExternalSyntheticOutline0.m(", isStreamInBluetoothSco=", ", isSpeakerphoneOn=", sbM, this.isAudioCallActive, this.isStreamInBluetoothSco);
            return AppCompatDelegateImpl$.ExternalSyntheticOutline0.m(")", sbM, this.isSpeakerphoneOn);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static AudioManager $r8$lambda$SbxA7YEyZ2FKWWEY9vNCmJBgGHs(AudioDataRepository audioDataRepository) {
        return (AudioManager) audioDataRepository.context.getSystemService("audio");
    }

    public AudioDataRepository(Context context, OplusVolumeDialogImpl oplusVolumeDialogImpl, Executor executor) {
        this.context = context;
        this.volumeInteractor = oplusVolumeDialogImpl;
        this.bgExecutor = executor;
        AudioDataRepository$$ExternalSyntheticLambda0 audioDataRepository$$ExternalSyntheticLambda0 = new AudioDataRepository$$ExternalSyntheticLambda0();
        audioDataRepository$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.audioManager$delegate = LazyKt__LazyJVMKt.lazy(audioDataRepository$$ExternalSyntheticLambda0);
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(new AudioState(0, false, false, false, false));
        this._audioState = MutableStateFlow;
        StateFlow stateFlowAsStateFlow = FlowKt.asStateFlow(MutableStateFlow);
        this.audioState = stateFlowAsStateFlow;
        MutableSharedFlow mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7);
        this._deviceChanges = mutableSharedFlowMutableSharedFlow$default;
        this.deviceChanges = mutableSharedFlowMutableSharedFlow$default;
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob((Job) null).plus(ExecutorsKt.from(executor)));
        this.repositoryScope = CoroutineScope;
        AudioDataRepository$deviceListener$1 audioDataRepository$deviceListener$1 = new AudioDataRepository$deviceListener$1();
        audioDataRepository$deviceListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.deviceListener = audioDataRepository$deviceListener$1;
        AudioDataRepository$modeListener$1 audioDataRepository$modeListener$1 = new AudioDataRepository$modeListener$1();
        audioDataRepository$modeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.modeListener = audioDataRepository$modeListener$1;
        AudioManager audioManager = getAudioManager();
        audioManager.addOnCommunicationDeviceChangedListener(executor, audioDataRepository$deviceListener$1);
        audioManager.addOnModeChangedListener(executor, audioDataRepository$modeListener$1);
        FlowKt.launchIn(FlowKt.onEach(mutableSharedFlowMutableSharedFlow$default, new AudioDataRepository$observeAudioState$1(this, null)), CoroutineScope);
        BuildersKt.launch$default(CoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AudioDataRepository$observeAudioState$2(this, null), 3);
        AudioDataRepository$special$$inlined$map$1 audioDataRepository$special$$inlined$map$1 = new AudioDataRepository$special$$inlined$map$1();
        audioDataRepository$special$$inlined$map$1.$this_unsafeTransform$inlined = stateFlowAsStateFlow;
        VarHandle.storeStoreFence();
        this.musicActiveFlow = FlowKt.distinctUntilChanged(audioDataRepository$special$$inlined$map$1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AudioManager getAudioManager() {
        return (AudioManager) this.audioManager$delegate.getValue();
    }

    public final boolean checkCallActive() {
        return Intrinsics.areEqual(getAudioManager().getParameters(OPLUS_AUDIO_GET_CALL_STREAM_ACTIVE), "true");
    }

    public final void destroy() {
        getAudioManager().removeOnCommunicationDeviceChangedListener(this.deviceListener);
        getAudioManager().removeOnModeChangedListener(this.modeListener);
        CoroutineScopeKt.cancel(this.repositoryScope, (CancellationException) null);
    }

    public final void fetchStreamActiveState() {
        Object value;
        AudioManager audioManager = getAudioManager();
        boolean zIsMusicActive = audioManager.isMusicActive();
        boolean zAreEqual = Intrinsics.areEqual(audioManager.getParameters(OPLUS_AUDIO_GET_CALL_STREAM_ACTIVE), "true");
        StateFlowImpl stateFlowImpl = this._audioState;
        do {
            value = stateFlowImpl.getValue();
        } while (!stateFlowImpl.compareAndSet(value, AudioState.copy$default((AudioState) value, 0, getAudioManager().isMusicActive(), checkCallActive(), false, false, 25)));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("Stream state - Music: ", ", Call: ", "AudioDataRepository-->", zIsMusicActive, zAreEqual));
        }
    }

    public final StateFlow getAudioState() {
        return this.audioState;
    }

    public final Context getContext() {
        return this.context;
    }

    public final SharedFlow getDeviceChanges() {
        return this.deviceChanges;
    }

    public final AudioManager.OnCommunicationDeviceChangedListener getDeviceListener() {
        return this.deviceListener;
    }

    public final AudioManager.OnModeChangedListener getModeListener() {
        return this.modeListener;
    }

    public final Flow getMusicActiveFlow() {
        return this.musicActiveFlow;
    }

    public final boolean isAudioCallMode(int i) {
        AudioState audioState = (AudioState) this.audioState.getValue();
        return audioState.getMode() == 2 || audioState.getMode() == 3 || i == 0 || audioState.isAudioCallActive;
    }
}
