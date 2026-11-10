package com.oplusos.systemui.common.blurability.screenshot;

import java.util.concurrent.Executors;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class StaticBlurManager$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                StaticBlurManager.Companion companion = StaticBlurManager.Companion;
                return CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob((Job) null).plus(StaticBlurManager.Companion.getWorkDispatcher$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon()));
            default:
                StaticBlurManager.Companion companion2 = StaticBlurManager.Companion;
                return ExecutorsKt.from(Executors.newSingleThreadExecutor());
        }
    }
}
