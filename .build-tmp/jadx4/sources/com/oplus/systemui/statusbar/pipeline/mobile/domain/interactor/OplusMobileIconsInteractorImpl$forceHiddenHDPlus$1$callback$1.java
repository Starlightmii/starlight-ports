package com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor;

import android.content.Context;
import android.support.v4.content.FileProvider$;
import com.android.systemui.tuner.TunerService;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileIconsInteractorImpl$forceHiddenHDPlus$1$callback$1 implements TunerService.Tunable {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;
    public /* synthetic */ Context $context;

    public final void onTuningChanged(String str, String str2) {
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, "OplusMobileIconsInteractorImpl", FileProvider$.ExternalSyntheticOutline0.m("onTuningChanged:", str2), null, 8);
        String string = this.$context.getString(2131956645);
        ProducerScope producerScope = this.$$this$conflatedCallbackFlow;
        boolean z = false;
        if (str2 != null && StringsKt__StringsKt.contains(str2, string, false)) {
            z = true;
        }
        producerScope.trySend-JP2dKIU(Boolean.valueOf(z));
    }
}
