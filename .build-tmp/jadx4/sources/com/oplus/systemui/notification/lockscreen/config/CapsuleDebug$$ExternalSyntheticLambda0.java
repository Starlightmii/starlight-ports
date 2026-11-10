package com.oplus.systemui.notification.lockscreen.config;

import android.os.SystemProperties;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class CapsuleDebug$$ExternalSyntheticLambda0 implements Function0 {
    public final Object invoke() {
        Object obj;
        try {
            obj = Result.constructor-impl(Boolean.valueOf(SystemProperties.getBoolean("debug.capsule.log.switch.itemViewUpdater", false)));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Boolean bool = Boolean.FALSE;
        if (obj instanceof Result.Failure) {
            obj = bool;
        }
        Boolean bool2 = (Boolean) obj;
        bool2.booleanValue();
        return bool2;
    }
}
