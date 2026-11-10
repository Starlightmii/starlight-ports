package com.oplus.systemui.notification.row.material;

import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import java.util.List;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class NotificationMaterialColorEx {
    private final StateFlow blurMode;
    private final StateFlow currentMaterialColor;
    private final StateFlow isKeyguardReverseColor;
    private final StateFlow isMaterialColor;

    public NotificationMaterialColorEx() {
        Boolean bool = Boolean.FALSE;
        this.isMaterialColor = StateFlowKt.MutableStateFlow(bool);
        this.currentMaterialColor = StateFlowKt.MutableStateFlow(MaterialColorMode.UNINITIATED);
        this.isKeyguardReverseColor = StateFlowKt.MutableStateFlow(bool);
        this.blurMode = StateFlowKt.MutableStateFlow(NotificationBlurMode.UNINITIATED);
    }

    public void applyColorModeForHeadsUp(ExpandableNotificationRow expandableNotificationRow, boolean z) {
    }

    public void applyColorModeForRow(ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
    }

    public StateFlow getBlurMode() {
        return this.blurMode;
    }

    public NotificationBlurMode getBlurModeForRow(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        return NotificationBlurMode.UNINITIATED;
    }

    public MaterialColorMode getColorModeForRow(ExpandableNotificationRow expandableNotificationRow) {
        return MaterialColorMode.UNINITIATED;
    }

    public StateFlow getCurrentMaterialColor() {
        return this.currentMaterialColor;
    }

    public StateFlow isKeyguardReverseColor() {
        return this.isKeyguardReverseColor;
    }

    public StateFlow isMaterialColor() {
        return this.isMaterialColor;
    }

    public void updateColorOnModeChanged(ExpandableNotificationRow expandableNotificationRow) {
    }

    public void updateNotificationColorMode(ExpandableNotificationRow expandableNotificationRow) {
    }

    public void updateNotificationColorMode(List<? extends ExpandableView> list, AmbientState ambientState) {
    }
}
