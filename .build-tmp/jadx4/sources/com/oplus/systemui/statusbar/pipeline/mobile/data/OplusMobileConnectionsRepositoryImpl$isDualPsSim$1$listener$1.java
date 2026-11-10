package com.oplus.systemui.statusbar.pipeline.mobile.data;

import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.systemui.statusbar.observer.DualSimStateObserver;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileConnectionsRepositoryImpl$isDualPsSim$1$listener$1 implements IObserverListener {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        DualSimStateObserver.Companion companion = DualSimStateObserver.Companion;
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, "OplusMobileConnectionsRepository", KeyguardLogger$.ExternalSyntheticOutline0.m(" isDualSimOpen onchange:", companion.getInstance().isDualSimOpen()), null, 8);
        this.$$this$conflatedCallbackFlow.trySend-JP2dKIU(Boolean.valueOf(companion.getInstance().isDualSimOpen()));
    }
}
