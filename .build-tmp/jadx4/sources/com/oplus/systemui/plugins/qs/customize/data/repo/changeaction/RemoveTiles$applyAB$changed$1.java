package com.oplus.systemui.plugins.qs.customize.data.repo.changeaction;

import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import java.util.function.Predicate;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RemoveTiles$applyAB$changed$1 implements Predicate {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ RemoveTiles this$0;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                break;
        }
        return this.this$0.getTileSpecs().contains(((TileConfig) obj).getTileSpec());
    }
}
