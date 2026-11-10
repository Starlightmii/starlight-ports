package com.oplus.systemui.notification.row.material.wrapper;

import android.view.View;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class TextViewMaterialWrapper$$ExternalSyntheticLambda0 implements Function1 {
    public final Object invoke(Object obj) {
        Object parent = ((View) obj).getParent();
        if (parent instanceof View) {
            return (View) parent;
        }
        return null;
    }
}
