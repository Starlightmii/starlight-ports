package com.oplusos.systemui.common.rendercache;

import java.lang.ref.WeakReference;
import java.util.function.Predicate;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RenderBooster$Companion$removeBoosterTarget$1 implements Predicate {
    public /* synthetic */ RenderBooster $target;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        WeakReference weakReference = (WeakReference) obj;
        return Intrinsics.areEqual(weakReference.get(), this.$target) || weakReference.get() == null;
    }
}
