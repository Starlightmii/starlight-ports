package com.oplus.systemui.statusbar.seeding;

import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginManager$seedlingEnableListener$1 implements IObserverListener {
    public /* synthetic */ SeedlingPluginManager this$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        SeedlingPluginManager seedlingPluginManager = this.this$0;
        SeedlingPluginManager.AnonymousClass1 anonymousClass1 = new SeedlingPluginManager.AnonymousClass1(1);
        anonymousClass1.this$0 = seedlingPluginManager;
        anonymousClass1.$listener = this;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(anonymousClass1);
    }
}
