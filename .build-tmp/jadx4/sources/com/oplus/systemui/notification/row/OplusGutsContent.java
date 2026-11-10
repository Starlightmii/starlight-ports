package com.oplus.systemui.notification.row;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.android.systemui.statusbar.notification.row.NotificationGuts;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusGutsContent extends LinearLayout implements NotificationGuts.GutsContent, View.OnClickListener {
    public OplusGutsContent(Context context) {
        super(context);
    }

    public int getActualHeight() {
        return 0;
    }

    public View getContentView() {
        return this;
    }

    public final boolean handleCloseControls(boolean z, boolean z2) {
        return false;
    }

    public final boolean needsFalsingProtection() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
    }

    public final void onFinishedClosing() {
    }

    public void setGutsParent(NotificationGuts notificationGuts) {
    }

    public final boolean shouldBeSavedOnClose() {
        return false;
    }

    public final boolean willBeRemoved() {
        return false;
    }
}
