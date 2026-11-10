package com.oplus.systemui.scenario.host.impl.domain.binder;

import android.view.View;
import android.view.ViewGroup;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScenarioCapsuleViewBinder$$ExternalSyntheticLambda3 implements Function2 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;
    public /* synthetic */ Object f$1;

    public final Object invoke(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 0:
                PrivacyScenarioCapsuleViewModel privacyScenarioCapsuleViewModel = (PrivacyScenarioCapsuleViewModel) this.f$0;
                ViewGroup viewGroup = (ViewGroup) this.f$1;
                Composer composer = (Composer) obj;
                int iIntValue = ((Integer) obj2).intValue();
                if (composer.shouldExecute((iIntValue & 3) != 2, iIntValue & 1)) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1972221644, iIntValue, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.bind.<anonymous>.<anonymous> (ScenarioCapsuleViewBinder.kt:180)");
                    }
                    ScenarioCapsuleViewBinder scenarioCapsuleViewBinder = ScenarioCapsuleViewBinder.INSTANCE;
                    ScenarioCapsuleViewBinder$$ExternalSyntheticLambda8 scenarioCapsuleViewBinder$$ExternalSyntheticLambda8 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda8(3);
                    scenarioCapsuleViewBinder$$ExternalSyntheticLambda8.f$0 = viewGroup;
                    VarHandle.storeStoreFence();
                    scenarioCapsuleViewBinder.CapsuleDecorProvider(privacyScenarioCapsuleViewModel, ComposableLambdaKt.rememberComposableLambda(474498581, true, scenarioCapsuleViewBinder$$ExternalSyntheticLambda8, composer, 54), composer, 560);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composer.skipToGroupEnd();
                }
                break;
            case 1:
                ((Integer) obj2).getClass();
                ((ScenarioCapsuleViewBinder) this.f$0).SyncHostVisibility((View) this.f$1, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(65));
                break;
            case 2:
                ((Integer) obj2).getClass();
                ((ScenarioCapsuleViewBinder) this.f$0).CapsuleBackground((Modifier) this.f$1, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(71));
                break;
            default:
                ((Integer) obj2).getClass();
                ((ScenarioCapsuleViewBinder) this.f$0).FirstLaunchProvider((Function2) this.f$1, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(71));
                break;
        }
        return Unit.INSTANCE;
    }
}
