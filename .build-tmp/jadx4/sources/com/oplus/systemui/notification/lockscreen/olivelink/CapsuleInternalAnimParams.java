package com.oplus.systemui.notification.lockscreen.olivelink;

import android.content.Context;
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Spring;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleInternalAnimParams {
    public static final CapsuleInternalAnimParams INSTANCE = new CapsuleInternalAnimParams();
    private static final String OLIVE_LINK_SERVER_NAME = "CapsuleInternal";
    private static final String TAG = "CapsuleInternalAnimParams";

    public final void initOliveLink(Context context) {
        OliveLinkConnector.INSTANCE.start(context, OLIVE_LINK_SERVER_NAME);
    }

    public final COUISpringInterpolator toSpringInterpolation(Spring spring) {
        return new COUISpringInterpolator(spring.response(), spring.bounce());
    }
}
