package com.oplus.systemui.separate;

import android.content.res.Configuration;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.coui.appcompat.panel.COUIBottomSheetDialog;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsCenterModeSelectDialogController$mConfigurationListener$1 implements ConfigurationController.ConfigurationListener {
    public /* synthetic */ QsCenterModeSelectDialogController this$0;

    public final void onConfigChanged(Configuration configuration) {
        QsCenterModeSelectDialogController qsCenterModeSelectDialogController = this.this$0;
        QsCenterModeSelectDialogController.Companion companion = QsCenterModeSelectDialogController.Companion;
        if (qsCenterModeSelectDialogController.isPhoneLandscape()) {
            LogUtil.i$default(LogUtil.TAG_QS, "QsCenterModeSelectDialogController", "onConfigChanged :isLandSplitMode", null, 8);
            QsCenterModeSelectDialogController.access$handleDialogDismiss(qsCenterModeSelectDialogController);
        }
        boolean z = (qsCenterModeSelectDialogController.context.getResources().getConfiguration().uiMode & 48) == 32;
        boolean z2 = qsCenterModeSelectDialogController.mNightMode;
        COUIBottomSheetDialog cOUIBottomSheetDialog = qsCenterModeSelectDialogController.mBottomSheetDialog;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("onConfigChanged: new = ", ", old = ", ", dialog = ", z, z2);
        sbM.append(cOUIBottomSheetDialog);
        sbM.append(", config = ");
        sbM.append(configuration);
        LogUtil.i$default(LogUtil.TAG_QS, "QsCenterModeSelectDialogController", sbM.toString(), null, 8);
        if (z != qsCenterModeSelectDialogController.mNightMode) {
            qsCenterModeSelectDialogController.mNightMode = z;
            qsCenterModeSelectDialogController.reShowDialog();
        }
    }

    public final void onSmallestScreenWidthChanged() {
        LogUtil.i$default(LogUtil.TAG_QS, "QsCenterModeSelectDialogController", "onSmallestScreenWidthChanged", null, 8);
        this.this$0.reShowDialog();
    }
}
