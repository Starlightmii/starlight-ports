package com.oplus.systemui.plugins.qs.editmodeswitch;

import com.oplus.systemui.plugins.qs.OplusQSRootView;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditModeSwitchAnimationManager_Factory implements Factory {
    public InstanceFactory rootViewProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public EditModeSwitchAnimationManager m222get() {
        return new EditModeSwitchAnimationManager((OplusQSRootView) this.rootViewProvider.get());
    }
}
