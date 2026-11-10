package com.oplus.systemui.statusbar.notification.stack;

import com.android.systemui.Dependency;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusExpandableViewStateExImpl$$ExternalSyntheticLambda0 implements Function0 {
    public final Object invoke() {
        OplusExpandableViewStateExImpl.Companion companion = OplusExpandableViewStateExImpl.Companion;
        return (StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class);
    }
}
