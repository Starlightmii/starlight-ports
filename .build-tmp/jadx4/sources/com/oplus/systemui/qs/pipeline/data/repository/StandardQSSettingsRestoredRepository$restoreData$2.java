package com.oplus.systemui.qs.pipeline.data.repository;

import com.android.systemui.qs.pipeline.data.model.RestoreData;
import com.android.systemui.qs.pipeline.shared.QSPipelineLoggerEx;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final /* synthetic */ class StandardQSSettingsRestoredRepository$restoreData$2 extends AdaptedFunctionReference implements Function2 {
    public final Object invoke(Object obj, Object obj2) {
        ((QSPipelineLoggerEx) ((AdaptedFunctionReference) this).receiver).logSettingsRestored((RestoreData) obj);
        return Unit.INSTANCE;
    }
}
