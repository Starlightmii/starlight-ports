package com.oplus.systemui.panelanimation.notification;

import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusNotificationRowShowAnimationExImpl$$ExternalSyntheticLambda0 implements Function1 {
    public final Object invoke(Object obj) {
        LogMessage logMessage = (LogMessage) obj;
        return "updateShelfPosition, yTranslation=" + logMessage.getDouble1() + ", recordSetTransitionYWay=" + logMessage.getInt1();
    }
}
