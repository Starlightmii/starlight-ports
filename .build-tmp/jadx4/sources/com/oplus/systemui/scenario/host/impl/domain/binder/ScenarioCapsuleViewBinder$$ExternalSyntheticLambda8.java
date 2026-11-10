package com.oplus.systemui.scenario.host.impl.domain.binder;

import android.view.ViewGroup;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScenarioCapsuleViewBinder$$ExternalSyntheticLambda8 implements Function2 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public final Object invoke(Object obj, Object obj2) {
        int i = this.$r8$classId;
        Object obj3 = this.f$0;
        switch (i) {
            case 0:
                ((Integer) obj2).getClass();
                ((ScenarioCapsuleViewBinder) obj3).CapsuleContent((Composer) obj, RecomposeScopeImplKt.updateChangedFlags(9));
                break;
            case 1:
                ((Integer) obj2).getClass();
                ((ScenarioCapsuleViewBinder) obj3).PrivacySection((Composer) obj, RecomposeScopeImplKt.updateChangedFlags(9));
                break;
            case 2:
                ((Integer) obj2).getClass();
                ((ScenarioCapsuleViewBinder) obj3).ScenarioSection((Composer) obj, RecomposeScopeImplKt.updateChangedFlags(9));
                break;
            default:
                ViewGroup viewGroup = (ViewGroup) obj3;
                Composer composer = (Composer) obj;
                int iIntValue = ((Integer) obj2).intValue();
                if (composer.shouldExecute((iIntValue & 3) != 2, iIntValue & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(474498581, iIntValue, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.bind.<anonymous>.<anonymous>.<anonymous> (ScenarioCapsuleViewBinder.kt:181)");
                    }
                    ScenarioCapsuleViewBinder scenarioCapsuleViewBinder = ScenarioCapsuleViewBinder.INSTANCE;
                    scenarioCapsuleViewBinder.SyncHostVisibility(viewGroup, composer, 64);
                    scenarioCapsuleViewBinder.CapsuleContent(composer, 8);
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
