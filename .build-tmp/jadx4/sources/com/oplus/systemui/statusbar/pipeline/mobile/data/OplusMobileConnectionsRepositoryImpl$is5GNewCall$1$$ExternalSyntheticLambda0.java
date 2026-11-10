package com.oplus.systemui.statusbar.pipeline.mobile.data;

import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.systemui.statusbar.observer.DualSimStateObserver;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusMobileConnectionsRepositoryImpl$is5GNewCall$1$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ OplusMobileConnectionsRepositoryImpl$is5GNewCall$1$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ObserverDispatcher.getInstance().unregisterObserver((OplusMobileConnectionsRepositoryImpl$is5GNewCall$1$newCallObserver$1) obj);
                break;
            default:
                DualSimStateObserver.Companion companion = DualSimStateObserver.Companion;
                LogUtil.i$default(LogUtil.TAG_STATUSBAR, "OplusMobileConnectionsRepository", KeyguardLogger$.ExternalSyntheticOutline0.m(" isDualSimOpen removeListener:", companion.getInstance().isDualSimOpen()), null, 8);
                companion.getInstance().removeListener((OplusMobileConnectionsRepositoryImpl$isDualPsSim$1$listener$1) obj);
                break;
        }
        return Unit.INSTANCE;
    }
}
