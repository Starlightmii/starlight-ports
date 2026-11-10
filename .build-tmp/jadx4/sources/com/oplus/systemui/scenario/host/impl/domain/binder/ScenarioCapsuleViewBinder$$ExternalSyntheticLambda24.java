package com.oplus.systemui.scenario.host.impl.domain.binder;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScenarioCapsuleViewBinder$$ExternalSyntheticLambda24 implements Function2 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ float f$0;

    public final Object invoke(Object obj, Object obj2) {
        int i = this.$r8$classId;
        float f = this.f$0;
        Composer composer = (Composer) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i) {
            case 0:
                if (composer.shouldExecute((iIntValue & 3) != 2, iIntValue & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1012817377, iIntValue, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.ScenarioSection.<anonymous>.<anonymous> (ScenarioCapsuleViewBinder.kt:368)");
                    }
                    SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.Companion, f), composer, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composer.skipToGroupEnd();
                }
                break;
            case 1:
                if (composer.shouldExecute((iIntValue & 3) != 2, iIntValue & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1255192097, iIntValue, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.PrivacyIcons.<anonymous> (ScenarioCapsuleViewBinder.kt:409)");
                    }
                    SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.Companion, f), composer, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composer.skipToGroupEnd();
                }
                break;
            default:
                if (composer.shouldExecute((iIntValue & 3) != 2, iIntValue & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(940036769, iIntValue, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.PrivacyIcons.<anonymous> (ScenarioCapsuleViewBinder.kt:399)");
                    }
                    SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.Companion, f), composer, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composer.skipToGroupEnd();
                }
                break;
        }
        return Unit.INSTANCE;
    }
}
