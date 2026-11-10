package com.oplus.systemui.media.seedling;

import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.android.HandlerDispatcherKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusMediaSeedlingController$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusMediaSeedlingController f$0;

    public /* synthetic */ OplusMediaSeedlingController$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        OplusMediaSeedlingController oplusMediaSeedlingController = this.f$0;
        switch (i) {
            case 0:
                return HandlerDispatcherKt.from(oplusMediaSeedlingController.bgHandler, MediaSeedlingBgThread.INSTANCE.getThreadName());
            default:
                return CoroutineScopeKt.CoroutineScope(oplusMediaSeedlingController.getSeedlingBgDispatcher());
        }
    }
}
