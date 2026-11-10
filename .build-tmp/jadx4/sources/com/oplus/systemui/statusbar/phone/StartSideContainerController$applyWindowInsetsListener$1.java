package com.oplus.systemui.statusbar.phone;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StartSideContainerController$applyWindowInsetsListener$1 implements View.OnApplyWindowInsetsListener {
    public /* synthetic */ StartSideContainerController this$0;

    @Override // android.view.View.OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        if (this.this$0.updateDisplayParameters()) {
            this.this$0.updateStartSideContainerLayout();
        }
        return view.onApplyWindowInsets(windowInsets);
    }
}
