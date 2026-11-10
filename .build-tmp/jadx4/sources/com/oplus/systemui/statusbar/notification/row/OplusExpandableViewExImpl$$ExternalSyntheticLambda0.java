package com.oplus.systemui.statusbar.notification.row;

import com.android.systemui.Dependency;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusExpandableViewExImpl$$ExternalSyntheticLambda0 implements Function0 {
    public final Object invoke() {
        return (AmbientState) Dependency.sDependency.getDependencyInner(AmbientState.class);
    }
}
