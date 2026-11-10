package com.oplus.systemui.qs.pipeline.dagger;

import com.oplus.systemui.qs.pipeline.domain.operate.QSTileMapperProvider;
import java.util.Map;
import javax.inject.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface QSTileMapperModule {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String TILE_MAPPER = "QSTileMapper";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String TILE_MAPPER = "QSTileMapper";

        public final Map<String, Provider> providerTileMappers(QSTileMapperProvider qSTileMapperProvider) {
            return qSTileMapperProvider.getTileMappers();
        }
    }
}
