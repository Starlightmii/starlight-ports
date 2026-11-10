package com.oplus.systemui.scenario.host.impl.domain.binder;

import android.view.View;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.compose.runtime.State;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScenarioCapsuleViewBinder$$ExternalSyntheticLambda9 implements Function0 {
    public /* synthetic */ boolean f$0;
    public /* synthetic */ View f$1;
    public /* synthetic */ State f$2;

    public final Object invoke() {
        int i;
        boolean z = this.f$0;
        View view = this.f$1;
        State state = this.f$2;
        if (((Boolean) state.getValue()).booleanValue() || !z) {
            i = view.getVisibility() == 4 ? 4 : 0;
        } else {
            i = 8;
        }
        if (view.getVisibility() != i) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MobileInputLogger$.ExternalSyntheticOutline0.m(i, "ScenarioCapsuleViewBinder-->", "SystemUi--QuickSettings", TextFieldImplKt$.ExternalSyntheticOutline0.m("SyncHostVisibility showCapsule=", ", isLandscape=", ", visibility=", ((Boolean) state.getValue()).booleanValue(), z));
            }
            view.setVisibility(i);
        }
        return Unit.INSTANCE;
    }
}
