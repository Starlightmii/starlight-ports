package com.oplus.systemui.qs.media;

import android.media.AudioManager;
import com.android.systemui.plugins.VolumeDialogController;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumeSeekBarViewModel$mVolumeCallback$1 implements VolumeSeekBarViewModel.VolumeControllerCallbackAdapter {
    public /* synthetic */ VolumeSeekBarViewModel this$0;

    public final void onCaptionEnabledStateChanged(Boolean bool, Boolean bool2) {
    }

    @Override // com.oplus.systemui.qs.media.VolumeSeekBarViewModel.VolumeControllerCallbackAdapter
    public final void onShowSafetyWarning(int i) {
        OplusNotificationLogger.logMediaPanel("VolumeSeekBarViewModel", "onShowSafetyWarning");
        VolumeSeekBarViewModel volumeSeekBarViewModel = this.this$0;
        VolumeSeekBarViewModel.Companion companion = VolumeSeekBarViewModel.Companion;
        AudioManager audioManager = volumeSeekBarViewModel.mAudioManager;
        companion.getClass();
        volumeSeekBarViewModel.savedMusicVolumeLevel(audioManager != null ? audioManager.getLastAudibleStreamVolume(3) : 0);
        AudioManager audioManager2 = volumeSeekBarViewModel.mAudioManager;
        volumeSeekBarViewModel.setVolumeBarProgress((audioManager2 != null ? audioManager2.getLastAudibleStreamVolume(3) : 0) * 100);
    }

    public final void onStateChanged(VolumeDialogController.State state, int i) {
        VolumeDialogController.StreamState streamState;
        VolumeSeekBarViewModel volumeSeekBarViewModel = this.this$0;
        super.onStateChanged(state, i);
        if (i != 3 || (streamState = (VolumeDialogController.StreamState) state.states.get(i)) == null) {
            return;
        }
        int i2 = streamState.level;
        VolumeSeekBarViewModel.Companion.getClass();
        volumeSeekBarViewModel.curVolumeLevel = i2 * 100;
        if (volumeSeekBarViewModel.disallowHandleStateChange || volumeSeekBarViewModel.mIsUserTouchTracking || volumeSeekBarViewModel.isVolumeBarUnavailable()) {
            return;
        }
        volumeSeekBarViewModel.setVolumeBarProgress(volumeSeekBarViewModel.curVolumeLevel);
    }

    public final void onVolumeChangedFromKey() {
    }
}
