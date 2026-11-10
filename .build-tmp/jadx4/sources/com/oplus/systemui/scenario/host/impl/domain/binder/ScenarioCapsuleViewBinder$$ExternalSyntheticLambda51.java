package com.oplus.systemui.scenario.host.impl.domain.binder;

import android.content.Context;
import android.view.View;
import com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper;
import com.oplus.systemui.scenario.host.impl.domain.widget.ScenarioLightBackgroundDrawable;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ScenarioCapsuleViewBinder$$ExternalSyntheticLambda51 implements Function1 {
    public final Object invoke(Object obj) {
        View view = new View((Context) obj);
        view.setId(2131364994);
        ScenarioLightBackgroundDrawable scenarioLightBackgroundDrawable = new ScenarioLightBackgroundDrawable(view, new TileDrawableWrapper(null));
        view.setTag(scenarioLightBackgroundDrawable);
        view.setBackground(scenarioLightBackgroundDrawable);
        return view;
    }
}
