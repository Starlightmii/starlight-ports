package com.oplusos.systemui.common.observer;

import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AbstractObserver$Companion$toSecureUri$1 implements Function {
    public static final AbstractObserver$Companion$toSecureUri$1 INSTANCE = new AbstractObserver$Companion$toSecureUri$1();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return MultiUserContentHelper.Companion.getSecureUri((String) obj);
    }
}
