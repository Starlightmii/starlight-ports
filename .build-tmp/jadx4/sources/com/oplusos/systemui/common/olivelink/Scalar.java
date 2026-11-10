package com.oplusos.systemui.common.olivelink;

import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface Scalar {
    Number get();

    void registerChangeListener(Object obj, Function0 function0);

    void set(Number number);
}
