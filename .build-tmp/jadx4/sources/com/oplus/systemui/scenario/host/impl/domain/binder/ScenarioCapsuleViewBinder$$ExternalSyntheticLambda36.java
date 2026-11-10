package com.oplus.systemui.scenario.host.impl.domain.binder;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScenarioCapsuleViewBinder$$ExternalSyntheticLambda36 implements Function2 {
    public /* synthetic */ ScenarioCapsuleViewBinder f$0;
    public /* synthetic */ String f$1;
    public /* synthetic */ int f$2;
    public /* synthetic */ Modifier f$3;
    public /* synthetic */ Function0 f$4;
    public /* synthetic */ int f$5;

    public final Object invoke(Object obj, Object obj2) {
        ScenarioCapsuleViewBinder scenarioCapsuleViewBinder = this.f$0;
        String str = this.f$1;
        int i = this.f$2;
        Modifier modifier = this.f$3;
        Function0 function0 = this.f$4;
        int i2 = this.f$5;
        ((Integer) obj2).getClass();
        scenarioCapsuleViewBinder.CapsuleImage(str, i, modifier, function0, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }
}
