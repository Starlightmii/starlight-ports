package com.oplus.systemui.volume;

import android.content.Context;
import com.android.settingslib.volume.data.repository.AudioRepository;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.volume.VolumeDialogComponent;
import com.android.systemui.volume.VolumeUI;
import com.android.systemui.volume.domain.interactor.AudioSharingInteractor;
import com.android.systemui.volume.shared.VolumeLogger;
import com.android.systemui.volume.ui.navigation.VolumeNavigator;
import com.oplus.systemui.opthreekey.dagger.OpThreekeyComponent;
import com.oplus.systemui.volume.subDisplay.OplusVolumeDialogComponentSubDisplay;
import java.io.PrintWriter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeUI extends VolumeUI {
    private final Context context;
    private final VolumeDialogComponent volumeDialogComponent;

    public OplusVolumeUI(Context context, VolumeDialogComponent volumeDialogComponent, OplusVolumeDialogComponentSubDisplay oplusVolumeDialogComponentSubDisplay, OpThreekeyComponent opThreekeyComponent, AudioRepository audioRepository, AudioSharingInteractor audioSharingInteractor, JavaAdapter javaAdapter, VolumeLogger volumeLogger, VolumeNavigator volumeNavigator) {
        super(context, volumeDialogComponent, audioRepository, audioSharingInteractor, javaAdapter, volumeLogger, volumeNavigator);
        this.context = context;
        this.volumeDialogComponent = volumeDialogComponent;
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        super.dump(printWriter, strArr);
    }

    public final Context getContext() {
        return this.context;
    }

    public final VolumeDialogComponent getVolumeDialogComponent() {
        return this.volumeDialogComponent;
    }
}
