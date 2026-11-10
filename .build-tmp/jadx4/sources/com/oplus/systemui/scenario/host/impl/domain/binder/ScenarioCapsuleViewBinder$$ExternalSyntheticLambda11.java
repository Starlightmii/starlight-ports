package com.oplus.systemui.scenario.host.impl.domain.binder;

import androidx.compose.foundation.text.CoreTextFieldKt$;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScenarioCapsuleViewBinder$$ExternalSyntheticLambda11 implements Function3 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Function2 f$0;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int i = this.$r8$classId;
        Function2 function2 = this.f$0;
        Composer composer = (Composer) obj2;
        int iIntValue = ((Integer) obj3).intValue();
        switch (i) {
            case 0:
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1457150375, iIntValue, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.CapsuleAnimatedVisibility.<anonymous> (ScenarioCapsuleViewBinder.kt:514)");
                }
                if (CoreTextFieldKt$.ExternalSyntheticOutline0.m(function2, composer, 0)) {
                    ComposerKt.traceEventEnd();
                }
                break;
            default:
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1272470497, iIntValue, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.ImageAnimatedVisibility.<anonymous> (ScenarioCapsuleViewBinder.kt:580)");
                }
                if (CoreTextFieldKt$.ExternalSyntheticOutline0.m(function2, composer, 0)) {
                    ComposerKt.traceEventEnd();
                }
                break;
        }
        return Unit.INSTANCE;
    }
}
