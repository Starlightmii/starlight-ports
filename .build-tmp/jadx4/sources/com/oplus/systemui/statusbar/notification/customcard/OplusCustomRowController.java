package com.oplus.systemui.statusbar.notification.customcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.collection.render.NodeController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomRowController implements NodeController {
    private Context context;
    private CustomRowEntry entry;
    private final String nodeLabel = "OplusCustomRowController";
    private OplusCustomRow oplusCustomRow;

    public OplusCustomRowController(Context context) {
        this.context = context;
    }

    public /* bridge */ /* synthetic */ void addChildAt(NodeController nodeController, int i) {
        super.addChildAt(nodeController, i);
    }

    public /* bridge */ /* synthetic */ View getChildAt(int i) {
        return super.getChildAt(i);
    }

    public /* bridge */ /* synthetic */ int getChildCount() {
        return super.getChildCount();
    }

    public final CustomRowEntry getEntry() {
        return this.entry;
    }

    public String getNodeLabel() {
        return this.nodeLabel;
    }

    public final OplusCustomRow getOplusCustomRow() {
        return this.oplusCustomRow;
    }

    public OplusCustomRow getView() {
        OplusCustomRow oplusCustomRow = this.oplusCustomRow;
        oplusCustomRow.getClass();
        return oplusCustomRow;
    }

    public /* bridge */ /* synthetic */ void moveChildTo(NodeController nodeController, int i) {
        super.moveChildTo(nodeController, i);
    }

    public final boolean offerToKeepInParentForAnimation() {
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.view.View, com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow] */
    public final void reinflateView(ViewGroup viewGroup, CustomRowEntry customRowEntry) {
        int iIndexOfChild;
        LaunchableView launchableView = this.oplusCustomRow;
        if (launchableView != null) {
            launchableView.removeFromTransientContainer();
            if (launchableView.getParent() == viewGroup) {
                iIndexOfChild = viewGroup.indexOfChild(launchableView);
                viewGroup.removeView(launchableView);
            } else {
                iIndexOfChild = -1;
            }
        } else {
            iIndexOfChild = -1;
        }
        ?? r0 = (OplusCustomRow) LayoutInflater.from(this.context).inflate(2131559211, viewGroup, false);
        if (iIndexOfChild != -1) {
            viewGroup.addView((View) r0, iIndexOfChild);
        }
        this.oplusCustomRow = r0;
        r0.setEntry(customRowEntry);
        this.entry = customRowEntry;
    }

    public /* bridge */ /* synthetic */ void removeChild(NodeController nodeController, boolean z) {
        super.removeChild(nodeController, z);
    }

    public final boolean removeFromParentIfKeptForAnimation() {
        return false;
    }

    public final void resetKeepInParentForAnimation() {
    }

    public final void setEntry(CustomRowEntry customRowEntry) {
        this.entry = customRowEntry;
    }

    public final void setOplusCustomRow(OplusCustomRow oplusCustomRow) {
        this.oplusCustomRow = oplusCustomRow;
    }
}
