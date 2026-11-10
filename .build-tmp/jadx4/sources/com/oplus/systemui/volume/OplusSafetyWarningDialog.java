package com.oplus.systemui.volume;

import android.content.DialogInterface;
import android.media.AudioManager;
import androidx.appcompat.app.AlertDialog;
import com.android.systemui.Dependency;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.oplus.systemui.volume.repository.SafetyWarningRepository;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusSafetyWarningDialog implements DialogInterface.OnDismissListener, DialogInterface.OnClickListener {
    public AlertDialog alertDialog;
    public 3 keyListener;
    public AudioManager mAudioManager;
    public 1 mReceiver;
    public 2 showListener;

    public AlertDialog getSafetyWarningDialog() {
        return this.alertDialog;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.mAudioManager.disableSafeMediaVolume();
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        ((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class)).unregisterReceiver(this.mReceiver);
        this.alertDialog = null;
        SafetyWarningRepository.showSafetyWarningH.2.1 r2 = (SafetyWarningRepository.showSafetyWarningH.2.1) this;
        Object safetyWarningLock = r2.this$0.getSafetyWarningLock();
        SafetyWarningRepository safetyWarningRepository = r2.this$0;
        synchronized (safetyWarningLock) {
            safetyWarningRepository.setSafetyWarning(null);
        }
        r2.this$0.volumeInteractor.recheckH(null);
    }
}
