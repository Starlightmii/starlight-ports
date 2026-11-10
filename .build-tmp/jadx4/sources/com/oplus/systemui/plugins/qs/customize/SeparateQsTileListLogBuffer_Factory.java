package com.oplus.systemui.plugins.qs.customize;

import com.android.systemui.log.LogBuffer;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateQsTileListLogBuffer_Factory implements Factory {
    public DoubleCheck loggerProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public SeparateQsTileListLogBuffer m124get() {
        return new SeparateQsTileListLogBuffer((LogBuffer) this.loggerProvider.get());
    }
}
