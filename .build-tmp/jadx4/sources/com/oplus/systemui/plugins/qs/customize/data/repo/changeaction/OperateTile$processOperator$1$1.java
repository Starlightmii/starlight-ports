package com.oplus.systemui.plugins.qs.customize.data.repo.changeaction;

import com.android.systemui.plugins.qs.TileSpecOperate;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import java.util.function.Predicate;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OperateTile$processOperator$1$1 implements Predicate {
    public /* synthetic */ TileSpecOperate.TileFilter $operate;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return Intrinsics.areEqual(((TileConfig) obj).getTileSpec(), this.$operate.getFromSpec());
    }
}
