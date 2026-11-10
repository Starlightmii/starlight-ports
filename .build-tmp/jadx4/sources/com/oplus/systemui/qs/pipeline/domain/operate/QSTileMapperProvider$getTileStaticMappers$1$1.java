package com.oplus.systemui.qs.pipeline.domain.operate;

import com.oplus.systemui.preventmode.OplusPreventModeData;
import java.util.List;
import javax.inject.Provider;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSTileMapperProvider$getTileStaticMappers$1$1 implements Provider {
    public /* synthetic */ List $elements;

    public final List<String> get() {
        return StringsKt__StringsKt.split$default((CharSequence) this.$elements.get(1), new String[]{OplusPreventModeData.SEPARATOR}, 0, 6);
    }
}
