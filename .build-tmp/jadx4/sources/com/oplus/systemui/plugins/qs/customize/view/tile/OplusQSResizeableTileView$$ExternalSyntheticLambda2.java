package com.oplus.systemui.plugins.qs.customize.view.tile;

import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSResizeableTileView$$ExternalSyntheticLambda2 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSResizeableTileView f$0;

    public final Object invoke() {
        int i = this.$r8$classId;
        OplusQSResizeableTileView oplusQSResizeableTileView = this.f$0;
        switch (i) {
            case 0:
                int i2 = OplusQSResizeableTileView.$r8$clinit;
                return new OplusQSHighlightTileViewAccessibilityHelper(oplusQSResizeableTileView);
            case 1:
                int i3 = OplusQSResizeableTileView.$r8$clinit;
                return oplusQSResizeableTileView.initializeLabelColorManager();
            case 2:
                int i4 = OplusQSResizeableTileView.$r8$clinit;
                return oplusQSResizeableTileView.initializeIconFrame();
            case 3:
                int i5 = OplusQSResizeableTileView.$r8$clinit;
                return oplusQSResizeableTileView.initializeBgView();
            case 4:
                int i6 = OplusQSResizeableTileView.$r8$clinit;
                return oplusQSResizeableTileView.initializeLeftView();
            default:
                int i7 = OplusQSResizeableTileView.$r8$clinit;
                return oplusQSResizeableTileView.initializePadLockView();
        }
    }
}
