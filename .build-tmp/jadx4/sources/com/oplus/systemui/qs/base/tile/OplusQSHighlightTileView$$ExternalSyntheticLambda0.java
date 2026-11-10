package com.oplus.systemui.qs.base.tile;

import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.qs.QSTile;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSHighlightTileView$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ QSTile f$0;
    public /* synthetic */ Expandable f$1;

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                QSTile qSTile = this.f$0;
                Expandable expandable = this.f$1;
                OplusQSHighlightTileView.Companion companion = OplusQSHighlightTileView.Companion;
                qSTile.preloadForSecondaryClick(expandable);
                break;
            default:
                QSTile qSTile2 = this.f$0;
                Expandable expandable2 = this.f$1;
                OplusQSHighlightTileView.Companion companion2 = OplusQSHighlightTileView.Companion;
                qSTile2.releasePreloadedForSecondaryClick(expandable2);
                break;
        }
        return Unit.INSTANCE;
    }
}
