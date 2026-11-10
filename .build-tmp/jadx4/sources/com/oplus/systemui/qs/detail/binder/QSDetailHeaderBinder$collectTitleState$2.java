package com.oplus.systemui.qs.detail.binder;

import android.widget.TextView;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailHeaderBinder$collectTitleState$2 implements FlowCollector {
    public /* synthetic */ TextView $headerTitle;

    public final Object emit(Object obj, Continuation continuation) {
        this.$headerTitle.setTextColor(((Number) obj).intValue());
        return Unit.INSTANCE;
    }
}
