package com.oplus.systemui.plugins.qs.customize.viewmodel;

import com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigRepository;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepUserTileConfigRepository;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.TimeoutCancellationException;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SepQSHostAdapter$$ExternalSyntheticLambda0 implements Function1 {
    public /* synthetic */ ISepTileConfigRepository f$0;

    public final Object invoke(Object obj) {
        ISepTileConfigRepository iSepTileConfigRepository = this.f$0;
        Throwable th = (Throwable) obj;
        if (th instanceof CancellationException) {
            LogUtil.i$default(LogUtil.TAG_QS, "SepQSHostAdapter", "cancel tracking!", null, 8);
        } else if (th instanceof TimeoutCancellationException) {
            LogUtil.i$default(LogUtil.TAG_QS, "SepQSHostAdapter", "tracking timeout!", null, 8);
            ((SepUserTileConfigRepository) iSepTileConfigRepository).updateMatchedState(true);
        } else {
            LogUtil.i$default(LogUtil.TAG_QS, "SepQSHostAdapter", "tracking finished!", null, 8);
            ((SepUserTileConfigRepository) iSepTileConfigRepository).updateMatchedState(true);
        }
        return Unit.INSTANCE;
    }
}
