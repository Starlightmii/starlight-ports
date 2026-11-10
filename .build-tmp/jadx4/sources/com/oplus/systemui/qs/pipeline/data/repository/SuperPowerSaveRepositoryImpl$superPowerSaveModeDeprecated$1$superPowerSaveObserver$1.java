package com.oplus.systemui.qs.pipeline.data.repository;

import com.android.systemui.common.coroutine.ChannelExt;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SuperPowerSaveRepositoryImpl$superPowerSaveModeDeprecated$1$superPowerSaveObserver$1 implements IObserverListener {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, "SuperPowerSaveRepositoryImpl", "onChange: selfChange= " + z + ", " + this, null, 8);
        ChannelExt.trySendWithFailureLogging$default(ChannelExt.INSTANCE, this.$$this$conflatedCallbackFlow, Unit.INSTANCE, "SuperPowerSaveRepositoryImpl");
    }
}
