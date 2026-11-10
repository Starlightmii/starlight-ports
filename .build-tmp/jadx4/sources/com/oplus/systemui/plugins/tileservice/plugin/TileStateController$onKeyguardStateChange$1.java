package com.oplus.systemui.plugins.tileservice.plugin;

import android.content.ComponentName;
import java.util.function.BiConsumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileStateController$onKeyguardStateChange$1 implements BiConsumer {
    public /* synthetic */ TileStateController this$0;

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        TileStateController.TileStateChangeListener tileStateChangeListener;
        ComponentName componentName = (ComponentName) obj;
        if (((TileStateController.OplusCustomTileServiceInfo) obj2).getAvailableWhenLock() || (tileStateChangeListener = this.this$0.listener) == null) {
            return;
        }
        ((OplusTileControllerPlugin$tileStateChangeListener$1) tileStateChangeListener).refreshState(componentName);
    }
}
