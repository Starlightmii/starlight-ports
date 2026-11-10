package com.oplus.systemui.statusbar.pipeline;

import android.graphics.Typeface;
import com.android.systemui.common.coroutine.ChannelExt;
import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import kotlin.Pair;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusWifiSignalExImpl$bindEx$typefaceCallback$1$callback$1 implements SystemTypefaceHelper.SystemUIFontCallback {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;
    public /* synthetic */ OplusWifiSignalExImpl this$0;

    @Override // com.oplusos.systemui.common.typeface.SystemTypefaceHelper.SystemUIFontCallback
    public final void setFontTypeface(Typeface typeface, int i) {
        ChannelExt.trySendWithFailureLogging$default(ChannelExt.INSTANCE, this.$$this$conflatedCallbackFlow, new Pair(SystemTypefaceHelper.Companion.getInstance(this.this$0.context).getNetworkIconTextTypeface(typeface, i, 70), Integer.valueOf(i)), "OplusWifiSignalExImpl");
    }
}
