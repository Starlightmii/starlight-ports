package com.oplus.systemui.plugins.qs;

import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepQSTileViewBinder$collectQuickEntranceDrawable$2 implements FlowCollector {
    public /* synthetic */ List $nodes;

    public final Object emit(Object obj, Continuation continuation) {
        Iterator it = this.$nodes.iterator();
        while (it.hasNext()) {
            ((IQsComponent) it.next()).updateQuickEntranceDrawable();
        }
        return Unit.INSTANCE;
    }
}
