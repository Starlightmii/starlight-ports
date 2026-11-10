package com.oplusos.systemui.common.blurability.screenshot;

import java.lang.ref.WeakReference;
import java.util.function.Predicate;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StaticBlurManager$clearNullRef$1 implements Predicate {
    public static final StaticBlurManager$clearNullRef$1 INSTANCE = new StaticBlurManager$clearNullRef$1();

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((WeakReference) obj).get() == null;
    }
}
