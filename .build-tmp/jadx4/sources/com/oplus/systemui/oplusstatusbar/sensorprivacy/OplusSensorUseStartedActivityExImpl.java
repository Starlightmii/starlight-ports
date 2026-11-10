package com.oplus.systemui.oplusstatusbar.sensorprivacy;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import com.android.systemui.sensorprivacy.OplusSensorUseStartedActivityEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSensorUseStartedActivityExImpl extends OplusSensorUseStartedActivityEx {
    public OplusSensorUseStartedActivityExImpl(Context context) {
    }

    public AlertDialog createOplusSensorUseDialog(Context context, int i, DialogInterface.OnClickListener onClickListener, DialogInterface.OnDismissListener onDismissListener) {
        return new OplusSensorUseDialog(context, i, onClickListener, onDismissListener).getDialog();
    }
}
