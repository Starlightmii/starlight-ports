package com.oplus.systemui.statusbar.icon.ui.binder;

import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationIconContainerViewBinder$bindAnimationsEnabled$$inlined$collectTracingEach$1 implements FlowCollector {
    public /* synthetic */ NotificationIconContainer $receiver$inlined;

    public final Object emit(Object obj, Continuation continuation) {
        String str = Trace.isTagEnabled(4096L) ? "NIC#bindAnimationsEnabled" : null;
        boolean z = str != null;
        if (z) {
            TraceUtilsKt.beginSlice(4096L, str);
        }
        try {
            this.$receiver$inlined.setAnimationsEnabled(((Boolean) obj).booleanValue());
            return Unit.INSTANCE;
        } finally {
            if (z) {
                Trace.traceEnd(4096L);
            }
        }
    }
}
