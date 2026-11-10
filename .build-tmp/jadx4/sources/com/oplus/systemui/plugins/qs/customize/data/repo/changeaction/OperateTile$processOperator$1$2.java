package com.oplus.systemui.plugins.qs.customize.data.repo.changeaction;

import com.android.systemui.plugins.qs.TileSpecOperate;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import java.util.function.UnaryOperator;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OperateTile$processOperator$1$2 implements UnaryOperator {
    public /* synthetic */ TileSpecOperate.TileMapper $operate;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        TileConfig tileConfig = (TileConfig) obj;
        return Intrinsics.areEqual(tileConfig.getTileSpec(), this.$operate.getFromSpec()) ? TileConfig.copy$default(tileConfig, this.$operate.getToSpec(), null, null, null, 14) : tileConfig;
    }
}
