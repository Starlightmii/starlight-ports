package com.oplus.systemui.qs.pipeline.data.repository;

import android.content.Intent;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class TileSpecSeparateQSRepository$1$$ExternalSyntheticLambda0 implements Function2 {
    public final Object invoke(Object obj, Object obj2) {
        return Integer.valueOf(((Intent) obj).getIntExtra("android.intent.extra.user_handle", -1));
    }
}
