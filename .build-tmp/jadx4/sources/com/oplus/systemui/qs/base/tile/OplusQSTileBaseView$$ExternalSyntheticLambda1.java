package com.oplus.systemui.qs.base.tile;

import android.view.View;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.qs.QSTile;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSTileBaseView$$ExternalSyntheticLambda1 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ View f$0;
    public /* synthetic */ QSTile f$1;
    public /* synthetic */ Expandable f$2;

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                View view = this.f$0;
                QSTile qSTile = this.f$1;
                Expandable expandable = this.f$2;
                if (view.isClickable()) {
                    qSTile.preloadForSecondaryClick(expandable);
                }
                break;
            default:
                View view2 = this.f$0;
                QSTile qSTile2 = this.f$1;
                Expandable expandable2 = this.f$2;
                if (view2.isClickable()) {
                    qSTile2.releasePreloadedForSecondaryClick(expandable2);
                }
                break;
        }
        return Unit.INSTANCE;
    }
}
