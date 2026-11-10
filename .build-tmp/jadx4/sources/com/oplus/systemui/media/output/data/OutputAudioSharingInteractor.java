package com.oplus.systemui.media.output.data;

import android.bluetooth.BluetoothDevice;
import com.oplus.systemui.media.output.OplusMediaOutputSwitchingPage;
import java.util.function.Consumer;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OutputAudioSharingInteractor {
    private final StateFlow audioSharingAvailable;
    private final StateFlow audioSharingState;
    private final StateFlow audioSharingTemporary;
    private final CoroutineScope bgScope;
    private final OutputAudioSharingRepository repository;

    public OutputAudioSharingInteractor(OutputAudioSharingRepository outputAudioSharingRepository, CoroutineScope coroutineScope) {
        this.repository = outputAudioSharingRepository;
        this.bgScope = coroutineScope;
        this.audioSharingState = outputAudioSharingRepository.getAudioSharingState();
        this.audioSharingTemporary = outputAudioSharingRepository.getAudioSharingTemporary();
        this.audioSharingAvailable = outputAudioSharingRepository.getAudioSharingAvailable();
    }

    public final void addOnPhoneModeChangedListener(OplusMediaOutputSwitchingPage.OnDataChangeListener onDataChangeListener) {
        this.repository.addOnPhoneModeChangedListener(onDataChangeListener);
    }

    public final void checkAudioSharingAvailable() {
        this.repository.checkAudioSharingAvailable();
    }

    public final StateFlow getAudioSharingAvailable() {
        return this.audioSharingAvailable;
    }

    public final StateFlow getAudioSharingState() {
        return this.audioSharingState;
    }

    public final StateFlow getAudioSharingTemporary() {
        return this.audioSharingTemporary;
    }

    public final CoroutineScope getBackgroundScope() {
        return this.bgScope;
    }

    public final int getPhoneMode() {
        return this.repository.getPhoneMode();
    }

    public final StateFlow getScannedDevices() {
        return this.repository.getScannedDevices();
    }

    public final boolean inAudioSharing() {
        return ((Boolean) this.audioSharingState.getValue()).booleanValue();
    }

    public final boolean inAudioSharingTemporary() {
        return ((Boolean) this.audioSharingTemporary.getValue()).booleanValue();
    }

    public final void removeOnPhoneModeChangedListener() {
        this.repository.removeOnPhoneModeChangedListener();
    }

    public final void setIsBluetoothConnected(boolean z) {
        this.repository.setBluetoothConnected(z);
    }

    public final void startScan() {
        this.repository.setScanBluetoothDevice(true);
    }

    public final void startSharing(BluetoothDevice bluetoothDevice, Consumer<Boolean> consumer) {
        this.repository.startSharing(bluetoothDevice, consumer);
    }

    public final void stopScan() {
        this.repository.setScanBluetoothDevice(false);
    }
}
