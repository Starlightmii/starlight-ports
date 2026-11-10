package com.oplus.systemui.plugins.qs.quickentrance.popupwindow;

import android.util.Log;
import android.view.View;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.LazyKt__LazyJVMKt$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSMoreButtonPopupWindowController$defaultCallback$1 implements OplusQSMoreButtonPopupWindowController.Callback {
    public /* synthetic */ OplusQSMoreButtonPopupWindowController this$0;

    @Override // com.oplus.systemui.plugins.qs.quickentrance.popupwindow.OplusQSMoreButtonPopupWindowController.Callback
    public final void handleClick(View view, OplusQSMoreButtonPopupWindow.Type type) {
        OplusQSMoreButtonPopupWindowController oplusQSMoreButtonPopupWindowController = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSMoreButtonPopupWindowController-->".concat("handleClick type: " + type));
        }
        int iOrdinal = type.ordinal();
        if (iOrdinal == 0) {
            oplusQSMoreButtonPopupWindowController.hideCouiPopup();
            oplusQSMoreButtonPopupWindowController.stub.showMediaSwitchDialog(oplusQSMoreButtonPopupWindowController.context, view);
            return;
        }
        if (iOrdinal == 1) {
            oplusQSMoreButtonPopupWindowController.hideCouiPopup();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusQSMoreButtonPopupWindowController-->userButton OnClick");
            }
            oplusQSMoreButtonPopupWindowController.qsQSQuickEntrance.handleUserSwitchClick(view);
            return;
        }
        if (iOrdinal == 2) {
            oplusQSMoreButtonPopupWindowController.hideCouiPopup();
            oplusQSMoreButtonPopupWindowController.fgsDialogController.showDialog(view);
        } else if (iOrdinal != 3) {
            LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        } else {
            oplusQSMoreButtonPopupWindowController.hideCouiPopup();
            oplusQSMoreButtonPopupWindowController.qsQSQuickEntrance.handleQSSettingsOnClick((View) oplusQSMoreButtonPopupWindowController.moreBtn.get());
        }
    }
}
