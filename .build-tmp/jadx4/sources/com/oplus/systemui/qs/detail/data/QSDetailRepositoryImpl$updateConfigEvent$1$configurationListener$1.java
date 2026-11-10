package com.oplus.systemui.qs.detail.data;

import android.content.res.Configuration;
import com.android.systemui.common.coroutine.ChannelExt;
import com.android.systemui.statusbar.policy.ConfigurationController;
import kotlin.Unit;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSDetailRepositoryImpl$updateConfigEvent$1$configurationListener$1 implements ConfigurationController.ConfigurationListener {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;

    public final void onConfigChanged(Configuration configuration) {
        ChannelExt.INSTANCE.trySendWithFailureLogging(this.$$this$conflatedCallbackFlow, Unit.INSTANCE, "QSDetailRepository", "configChangeEvent");
    }
}
