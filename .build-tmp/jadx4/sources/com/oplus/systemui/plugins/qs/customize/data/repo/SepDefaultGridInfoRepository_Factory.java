package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.oplus.systemui.plugins.qs.ContextInstance;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepDefaultGridInfoRepository_Factory implements Factory {
    public Provider contextInstanceProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public SepDefaultGridInfoRepository m148get() {
        return new SepDefaultGridInfoRepository((ContextInstance) this.contextInstanceProvider.get());
    }
}
