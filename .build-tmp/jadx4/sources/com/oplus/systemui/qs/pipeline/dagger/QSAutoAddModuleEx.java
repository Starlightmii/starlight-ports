package com.oplus.systemui.qs.pipeline.dagger;

import android.content.res.Resources;
import com.android.systemui.qs.pipeline.domain.model.AutoAddable;
import com.oplus.systemui.qs.pipeline.domain.autoaddable.StaticPromoteTileAutoAddable;
import java.util.Set;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface QSAutoAddModuleEx {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final Set<AutoAddable> providesPromotionTileAutoAddable(Resources resources, StaticPromoteTileAutoAddable.Factory factory) {
            return StaticPromoteTileAutoAddable.Companion.parsePromoteTileResource(resources, factory);
        }
    }
}
