package com.oplus.systemui.qs.pipeline.data.repository;

import android.content.res.Resources;
import com.android.systemui.qs.pipeline.data.repository.TilesSettingConverter;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class TileSpecSpecialModeRepository$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ Resources f$0;
    public /* synthetic */ int f$1;

    public final Object invoke() {
        Resources resources = this.f$0;
        int i = this.f$1;
        int i2 = TileSpecSpecialModeRepository.$r8$clinit;
        return TilesSettingConverter.INSTANCE.toTilesList(resources.getString(i));
    }
}
