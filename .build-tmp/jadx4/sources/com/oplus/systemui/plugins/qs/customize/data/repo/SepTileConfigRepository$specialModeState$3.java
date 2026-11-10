package com.oplus.systemui.plugins.qs.customize.data.repo;

import kotlin.Pair;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final /* synthetic */ class SepTileConfigRepository$specialModeState$3 extends AdaptedFunctionReference implements Function3 {
    public static final SepTileConfigRepository$specialModeState$3 INSTANCE = new SepTileConfigRepository$specialModeState$3();

    public SepTileConfigRepository$specialModeState$3() {
        super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return new Pair(Boxing.boxBoolean(((Boolean) obj).booleanValue()), Boxing.boxBoolean(((Boolean) obj2).booleanValue()));
    }
}
