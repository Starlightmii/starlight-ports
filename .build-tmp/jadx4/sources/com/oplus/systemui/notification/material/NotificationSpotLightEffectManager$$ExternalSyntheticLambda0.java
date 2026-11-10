package com.oplus.systemui.notification.material;

import com.android.systemui.Dependency;
import com.android.systemui.statusbar.policy.ConfigurationController;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationSpotLightEffectManager$$ExternalSyntheticLambda0 implements Function0 {
    public final Object invoke() {
        Object obj;
        try {
            obj = Result.constructor-impl((ConfigurationController) Dependency.sDependency.getDependencyInner(ConfigurationController.class));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (obj instanceof Result.Failure) {
            obj = null;
        }
        return (ConfigurationController) obj;
    }
}
