package com.oplus.systemui.separate;

import com.android.keyguard.logging.KeyguardLogger$;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelPluginScanner$observerListener$1 implements IObserverListener {
    public /* synthetic */ OplusPanelPluginScanner this$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        LogUtil.i$default(LogUtil.TAG_DEVICE, "OplusPanelPluginScanner", KeyguardLogger$.ExternalSyntheticOutline0.m("onChange value: ", z), null, 8);
        this.this$0.updatePluginState$1();
    }
}
