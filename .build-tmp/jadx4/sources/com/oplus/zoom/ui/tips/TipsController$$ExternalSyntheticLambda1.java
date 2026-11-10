package com.oplus.zoom.ui.tips;

import com.oplus.zoom.common.ZoomPositionInfo;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class TipsController$$ExternalSyntheticLambda1 implements TipsManager.TipsHideListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ TipsController f$0;

    public /* synthetic */ TipsController$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    @Override // com.oplus.zoom.ui.tips.TipsManager.TipsHideListener
    public final void onTipsHide(ZoomPositionInfo zoomPositionInfo) {
        int i = this.$r8$classId;
        TipsController tipsController = this.f$0;
        switch (i) {
            case 0:
                tipsController.lambda$initFlexibleTips$4(zoomPositionInfo);
                break;
            case 1:
                tipsController.lambda$initManager$0(zoomPositionInfo);
                break;
            case 2:
                tipsController.lambda$initManager$1(zoomPositionInfo);
                break;
            case 3:
                tipsController.lambda$initManager$2(zoomPositionInfo);
                break;
            default:
                tipsController.lambda$initManager$3(zoomPositionInfo);
                break;
        }
    }
}
