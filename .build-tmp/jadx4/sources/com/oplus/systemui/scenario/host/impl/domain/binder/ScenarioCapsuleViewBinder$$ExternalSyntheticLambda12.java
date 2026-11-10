package com.oplus.systemui.scenario.host.impl.domain.binder;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScenarioCapsuleViewBinder$$ExternalSyntheticLambda12 implements Function2 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ ScenarioCapsuleViewBinder f$0;
    public /* synthetic */ Object f$1;
    public /* synthetic */ Object f$2;

    public final Object invoke(Object obj, Object obj2) {
        switch (this.$r8$classId) {
            case 0:
                ((Integer) obj2).getClass();
                this.f$0.CapsuleAnimatedVisibility((Modifier) this.f$1, (Function2) this.f$2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(561));
                break;
            case 1:
                ((Integer) obj2).getClass();
                this.f$0.CapsuleAnimatedContent((Modifier) this.f$1, (Function3) this.f$2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(561));
                break;
            default:
                ((Integer) obj2).getClass();
                this.f$0.PrivacyIcons((String) this.f$1, (State) this.f$2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(519));
                break;
        }
        return Unit.INSTANCE;
    }
}
