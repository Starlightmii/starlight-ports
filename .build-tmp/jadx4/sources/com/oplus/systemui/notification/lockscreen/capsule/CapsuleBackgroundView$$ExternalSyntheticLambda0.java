package com.oplus.systemui.notification.lockscreen.capsule;

import com.android.systemui.DependencyEx;
import com.oplus.systemui.notification.color.MaterialColorManager;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class CapsuleBackgroundView$$ExternalSyntheticLambda0 implements Function0 {
    public final Object invoke() {
        CapsuleBackgroundView.Companion companion = CapsuleBackgroundView.Companion;
        return (MaterialColorManager) DependencyEx.sDependency.getDependency(MaterialColorManager.class);
    }
}
