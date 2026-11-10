package com.oplus.systemui.notification.base.radius;

import android.content.Context;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Scalar;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RadiusParams {
    private static final String GROUP_NAME = "通用";
    private static final String SERVER_NAME = "个人调试";
    public static final RadiusParams INSTANCE = new RadiusParams();
    private static final Lazy CARD_MENU_CONER$delegate = LazyKt__LazyJVMKt.lazy(new RadiusParams$$ExternalSyntheticLambda0());

    public final Scalar getCARD_MENU_CONER() {
        return (Scalar) CARD_MENU_CONER$delegate.getValue();
    }

    public final void initOliveLink(Context context) {
        OliveLinkConnector.INSTANCE.start(context, SERVER_NAME);
    }
}
