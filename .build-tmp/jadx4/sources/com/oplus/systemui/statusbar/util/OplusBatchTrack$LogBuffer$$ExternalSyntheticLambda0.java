package com.oplus.systemui.statusbar.util;

import com.android.systemui.compose.RecompositionTracerKt$;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusBatchTrack$LogBuffer$$ExternalSyntheticLambda0 implements Function1 {
    public final Object invoke(Object obj) {
        return ArraysKt___ArraysKt.joinToString$default((String[]) obj, "|", (CharSequence) null, (RecompositionTracerKt$.ExternalSyntheticLambda0) null, 62);
    }
}
