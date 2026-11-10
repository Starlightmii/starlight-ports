package com.oplus.systemui.scenario.host.impl.domain.binder;

import androidx.compose.foundation.text.CoreTextFieldKt$;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.material3.Shapes;
import androidx.compose.material3.Typography;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScenarioCapsuleViewBinder$$ExternalSyntheticLambda4 implements Function2 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Function2 f$0;

    public /* synthetic */ ScenarioCapsuleViewBinder$$ExternalSyntheticLambda4(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj, Object obj2) {
        int i = this.$r8$classId;
        int i2 = 2;
        Function2 function2 = this.f$0;
        switch (i) {
            case 0:
                Composer composer = (Composer) obj;
                int iIntValue = ((Integer) obj2).intValue();
                if (composer.shouldExecute((iIntValue & 3) != 2, iIntValue & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1761375684, iIntValue, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.CapsuleDecorProvider.<anonymous> (ScenarioCapsuleViewBinder.kt:207)");
                    }
                    ScenarioCapsuleViewBinder$$ExternalSyntheticLambda4 scenarioCapsuleViewBinder$$ExternalSyntheticLambda4 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda4(i2);
                    scenarioCapsuleViewBinder$$ExternalSyntheticLambda4.f$0 = function2;
                    VarHandle.storeStoreFence();
                    MaterialThemeKt.MaterialTheme((ColorScheme) null, (Shapes) null, (Typography) null, ComposableLambdaKt.rememberComposableLambda(361808144, true, scenarioCapsuleViewBinder$$ExternalSyntheticLambda4, composer, 54), composer, 3072, 7);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composer.skipToGroupEnd();
                }
                break;
            case 1:
                Composer composer2 = (Composer) obj;
                int iIntValue2 = ((Integer) obj2).intValue();
                if (composer2.shouldExecute((iIntValue2 & 3) != 2, iIntValue2 & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(498583408, iIntValue2, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.FirstLaunchProvider.<anonymous> (ScenarioCapsuleViewBinder.kt:527)");
                    }
                    if (CoreTextFieldKt$.ExternalSyntheticOutline0.m(function2, composer2, 0)) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composer2.skipToGroupEnd();
                }
                break;
            default:
                Composer composer3 = (Composer) obj;
                int iIntValue3 = ((Integer) obj2).intValue();
                if (composer3.shouldExecute((iIntValue3 & 3) != 2, iIntValue3 & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(361808144, iIntValue3, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.CapsuleDecorProvider.<anonymous>.<anonymous> (ScenarioCapsuleViewBinder.kt:207)");
                    }
                    if (CoreTextFieldKt$.ExternalSyntheticOutline0.m(function2, composer3, 0)) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composer3.skipToGroupEnd();
                }
                break;
        }
        return Unit.INSTANCE;
    }
}
