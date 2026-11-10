package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Switch;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QSTileView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSHighlightTileViewAccessibilityHelper {
    private String mAccessibilityClass;
    public boolean mClicked;
    public boolean mTileState;
    private final QSTileView qsTileView;

    public OplusQSHighlightTileViewAccessibilityHelper(QSTileView qSTileView) {
        this.qsTileView = qSTileView;
    }

    public final void handleQSTileStateChanged(QSTile.State state) {
        boolean z;
        this.mAccessibilityClass = state.state == 0 ? null : state.expandedAccessibilityClassName;
        if (!(state instanceof QSTile.BooleanState) || this.mTileState == (z = ((QSTile.AdapterState) ((QSTile.BooleanState) state)).value)) {
            return;
        }
        this.mTileState = z;
        this.mClicked = false;
    }

    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (TextUtils.isEmpty(this.mAccessibilityClass)) {
            return;
        }
        accessibilityEvent.setClassName(this.mAccessibilityClass);
        if (Switch.class.getName().equals(this.mAccessibilityClass)) {
            boolean z = this.mClicked;
            boolean z2 = this.mTileState;
            if (z) {
                z2 = !z2;
            }
            accessibilityEvent.setContentDescription(this.qsTileView.getResources().getString(z2 ? 2131956739 : 2131956738));
            accessibilityEvent.setChecked(z2);
        }
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        boolean z = false;
        accessibilityNodeInfo.setSelected(false);
        if (TextUtils.isEmpty(this.mAccessibilityClass)) {
            return;
        }
        accessibilityNodeInfo.setClassName(this.mAccessibilityClass);
        if (Switch.class.getName().equals(this.mAccessibilityClass)) {
            boolean z2 = this.mClicked;
            boolean z3 = this.mTileState;
            if (!z2) {
                z = z3;
            } else if (!z3) {
                z = true;
            }
            accessibilityNodeInfo.setText(this.qsTileView.getResources().getString(z ? 2131956739 : 2131956738));
            accessibilityNodeInfo.setChecked(z);
            accessibilityNodeInfo.setCheckable(true);
            if (this.qsTileView.isLongClickable()) {
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_LONG_CLICK.getId(), this.qsTileView.getResources().getString(2131951846)));
            }
        }
    }
}
