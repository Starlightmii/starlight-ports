package com.oplus.systemui.qs;

import com.android.systemui.qs.QSNode;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSContainerImplController$$ExternalSyntheticLambda1 implements Function2 {
    public /* synthetic */ OplusQSContainerImplController f$0;
    public /* synthetic */ QSNode f$1;

    public final Object invoke(Object obj, Object obj2) {
        this.f$0.updateNodeState(this.f$1, ((Float) obj).floatValue(), ((Float) obj2).floatValue());
        return Unit.INSTANCE;
    }
}
