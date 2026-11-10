package com.oplus.systemui.qs.pipeline.dagger;

import com.oplus.systemui.qs.pipeline.domain.operate.QSCustomTileFilterProvider;
import java.util.Map;
import javax.inject.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface QSCustomTileFilterModule {
    public static final String CUSTOM_TILE_FILTER = "QSCustomTileFilter";
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String CUSTOM_TILE_FILTER = "QSCustomTileFilter";

        public final Map<String, Provider> providerTileFilters(QSCustomTileFilterProvider qSCustomTileFilterProvider) {
            return qSCustomTileFilterProvider.getTileFilters();
        }
    }
}
