package com.oplus.systemui.volume.eximpl;

import android.content.Context;
import com.android.settingslib.volume.data.repository.AudioRepository;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.volume.OplusVolumeControllerEx;
import com.android.systemui.volume.OplusVolumeUIEx;
import com.oplus.systemui.volume.repository.AudioRepositoryEmptyImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeUIExImpl implements OplusVolumeUIEx {
    private final Context context;
    private OplusVolumeControllerExImpl mOplusVolumeControllerExImpl;

    public OplusVolumeUIExImpl(Context context) {
        this.context = context;
    }

    public AudioRepository getAudioRepositoryExptyImpl() {
        return new AudioRepositoryEmptyImpl();
    }

    public final Context getContext() {
        return this.context;
    }

    public OplusVolumeControllerEx getOplusVolumeController(Context context, VolumeDialogController volumeDialogController) {
        if (this.mOplusVolumeControllerExImpl == null) {
            this.mOplusVolumeControllerExImpl = new OplusVolumeControllerExImpl(context, volumeDialogController);
        }
        OplusVolumeControllerExImpl oplusVolumeControllerExImpl = this.mOplusVolumeControllerExImpl;
        oplusVolumeControllerExImpl.getClass();
        return oplusVolumeControllerExImpl;
    }
}
