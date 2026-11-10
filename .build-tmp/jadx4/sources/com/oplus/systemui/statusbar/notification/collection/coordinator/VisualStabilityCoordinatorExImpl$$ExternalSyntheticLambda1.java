package com.oplus.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.Dependency;
import com.android.systemui.statusbar.notification.collection.render.GroupMembershipManager;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class VisualStabilityCoordinatorExImpl$$ExternalSyntheticLambda1 implements Function0 {
    public final Object invoke() {
        return (GroupMembershipManager) Dependency.sDependency.getDependencyInner(GroupMembershipManager.class);
    }
}
