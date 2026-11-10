package com.oplus.systemui.qs.scenario.data.repository;

import com.android.systemui.common.coroutine.ChannelExt;
import com.android.systemui.privacy.PrivacyItemController;
import java.util.List;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSPrivacyRepository$privacyItems$1$callback$1 implements PrivacyItemController.Callback {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;

    public final void onPrivacyItemsChanged(List list) {
        ChannelExt.trySendWithFailureLogging$default(ChannelExt.INSTANCE, this.$$this$conflatedCallbackFlow, list, "OplusQSPrivacyRepository");
    }
}
