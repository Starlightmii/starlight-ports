package com.oplusos.systemui.common.rendercache;

import android.view.View;
import android.widget.TextView;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class RenderBooster$$ExternalSyntheticLambda0 implements Function1 {
    public final Object invoke(Object obj) {
        RenderBooster.Companion companion = RenderBooster.Companion;
        return Boolean.valueOf(((View) obj) instanceof TextView);
    }
}
