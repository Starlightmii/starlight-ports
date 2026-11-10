package com.oplus.systemui.media.seedling;

import com.android.app.displaylib.PerDisplayInstanceRepositoryImpl$;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusSeedlingMediaDataCombineLatest$$ExternalSyntheticLambda1 implements Function1 {
    public final Object invoke(Object obj) {
        SeedlingMediaData seedlingMediaData = (SeedlingMediaData) obj;
        OplusSeedlingMediaDataCombineLatest.Companion companion = OplusSeedlingMediaDataCombineLatest.Companion;
        return PerDisplayInstanceRepositoryImpl$.ExternalSyntheticOutline0.m(seedlingMediaData.getState(), seedlingMediaData.getPackageName(), "_");
    }
}
