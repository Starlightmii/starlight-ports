package com.oplus.systemui.qs.res.data.repository;

import android.content.Context;
import android.content.Intent;
import com.android.systemui.common.coroutine.ChannelExt;
import com.oplusos.systemui.common.receiver.IReceiverListener;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSThemeConfigRepository$themeChangeEvent$1$listener$1 implements IReceiverListener {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;

    @Override // com.oplusos.systemui.common.receiver.IReceiverListener
    public final void onReceive(Context context, Intent intent) {
        ChannelExt.trySendWithFailureLogging$default(ChannelExt.INSTANCE, this.$$this$conflatedCallbackFlow, intent, "QSThemeConfigRepository");
    }
}
