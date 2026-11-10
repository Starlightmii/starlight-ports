package com.oplus.systemui.separate;

import android.view.View;
import com.oplus.systemui.statusbar.seeding.ScaleChangedListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSimpleQSFakeController$systemIconsScaleChangedListener$1 implements ScaleChangedListener {
    public /* synthetic */ OplusSimpleQSFakeController this$0;

    @Override // com.oplus.systemui.statusbar.seeding.ScaleChangedListener
    public final void onScaleChanged(float f) {
        View statusIcons = this.this$0.getStatusIcons();
        if (statusIcons != null) {
            statusIcons.setScaleX(f);
            statusIcons.setScaleY(f);
        }
    }
}
