package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer;
import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer_Factory;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepRestoreTileConfig_Factory implements Factory {
    public Provider contextInstanceProvider;
    public SeparateQsTileListLogBuffer_Factory separateQsTileLoggerProvider;
    public Provider staticTileConfigRepositoryProvider;

    /* JADX INFO: renamed from: get, reason: merged with bridge method [inline-methods] */
    public SepRestoreTileConfig m152get() {
        return new SepRestoreTileConfig((ContextInstance) this.contextInstanceProvider.get(), (SepStaticTileConfigRepository) this.staticTileConfigRepositoryProvider.get(), (SeparateQsTileListLogBuffer) this.separateQsTileLoggerProvider.get());
    }
}
