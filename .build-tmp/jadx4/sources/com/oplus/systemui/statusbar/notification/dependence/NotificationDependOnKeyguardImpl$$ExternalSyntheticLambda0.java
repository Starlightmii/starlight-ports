package com.oplus.systemui.statusbar.notification.dependence;

import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.DependencyEx;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationDependOnKeyguardImpl$$ExternalSyntheticLambda0 implements Function0 {
    public final Object invoke() {
        return (OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class);
    }
}
