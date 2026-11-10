package com.oplus.systemui.separate;

import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.qs.QSElement;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.plugins.qs.SpanSizeInPlugin;
import com.oplus.systemui.qs.OplusQuickStatusBarHeaderController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSManager$SeparateQSHost$bindQSElement$1 implements QSElement {
    public /* synthetic */ OplusQuickStatusBarHeaderController $headerController;

    public final void addIgnoredSlot(String str) {
        this.$headerController.addIgnoredSlot(str);
    }

    public final View createView(SpanSizeInPlugin spanSizeInPlugin, boolean z, boolean z2) {
        return this.$headerController.getView();
    }

    public ViewGroup getClockContainer() {
        return this.$headerController.getMClockContainer();
    }

    public ViewGroup getQsStatusIconsView() {
        return this.$headerController.getMStatusIconsView();
    }

    public void setListening(boolean z) {
        this.$headerController.setListening(z);
    }

    public final void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType) {
        this.$headerController.updateColor();
    }
}
