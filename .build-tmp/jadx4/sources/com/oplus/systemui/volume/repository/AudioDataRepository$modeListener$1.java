package com.oplus.systemui.volume.repository;

import android.media.AudioManager;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlinx.coroutines.flow.StateFlowImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AudioDataRepository$modeListener$1 implements AudioManager.OnModeChangedListener {
    public /* synthetic */ AudioDataRepository this$0;

    @Override // android.media.AudioManager.OnModeChangedListener
    public final void onModeChanged(int i) {
        boolean zCheckCallActive = this.this$0.checkCallActive();
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("Mode changed to: ", i, ", isMusicActive: ", ", isAudioCallActive: ", this.this$0.getAudioManager().isMusicActive());
        sbM.append(zCheckCallActive);
        LogUtil.i$default(LogUtil.TAG_VOLUME, AudioDataRepository.TAG, sbM.toString(), null, 8);
        StateFlowImpl stateFlowImpl = this.this$0._audioState;
        AudioDataRepository audioDataRepository = this.this$0;
        while (true) {
            Object value = stateFlowImpl.getValue();
            int i2 = i;
            if (stateFlowImpl.compareAndSet(value, AudioDataRepository.AudioState.copy$default((AudioDataRepository.AudioState) value, i2, audioDataRepository.getAudioManager().isMusicActive(), zCheckCallActive, false, false, 24))) {
                return;
            } else {
                i = i2;
            }
        }
    }
}
