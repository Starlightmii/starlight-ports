package com.oplus.systemui.volume.view;

import android.content.DialogInterface;
import android.util.Log;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeDialogView$setDialogListener$2 implements DialogInterface.OnDismissListener {
    public /* synthetic */ OplusVolumeDialogView this$0;

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        OplusVolumeDialogView.CustomDialog volumeDialog;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeDialogView-->dismissVolumeDialog");
        }
        this.this$0.cancelDismissFallback();
        if (this.this$0.getSliderState() != OplusVolumeDialogView.SliderState.SHOWING || (volumeDialog = this.this$0.getVolumeDialog()) == null || !volumeDialog.isShowing()) {
            OplusVolumeDialogView.access$applyDialogSessionCleanup(this.this$0);
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeDialogView-->dismissVolumeDialog: superseded by new show, skip destructive teardown");
        }
        this.this$0.applyPanelLeaveCleanup();
    }
}
