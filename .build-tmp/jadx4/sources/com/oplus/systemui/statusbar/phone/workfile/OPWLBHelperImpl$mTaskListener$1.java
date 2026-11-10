package com.oplus.systemui.statusbar.phone.workfile;

import android.content.ComponentName;
import com.android.systemui.shared.system.TaskStackChangeListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OPWLBHelperImpl$mTaskListener$1 implements TaskStackChangeListener {
    public /* synthetic */ OPWLBHelperImpl this$0;

    public final void onTaskCreated(int i, ComponentName componentName) {
        OPWLBHelperImpl oPWLBHelperImpl = this.this$0;
        if (OPWLBHelperImpl.access$isPopUpEnabled(oPWLBHelperImpl)) {
            OPWLBHelperImpl.access$checkAndShowPopup(oPWLBHelperImpl, i);
        }
    }

    public final void onTaskMovedToFront(int i) {
        OPWLBHelperImpl oPWLBHelperImpl = this.this$0;
        if (OPWLBHelperImpl.access$isPopUpEnabled(oPWLBHelperImpl)) {
            OPWLBHelperImpl.access$checkAndShowPopup(oPWLBHelperImpl, i);
        }
    }
}
