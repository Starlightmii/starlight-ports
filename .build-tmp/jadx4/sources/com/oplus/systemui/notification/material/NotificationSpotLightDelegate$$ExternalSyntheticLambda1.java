package com.oplus.systemui.notification.material;

import com.coui.appcompat.spotlight.COUISpotLightEffectDrawable;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationSpotLightDelegate$$ExternalSyntheticLambda1 implements Function0 {
    public /* synthetic */ NotificationSpotLightDelegate f$0;

    public final Object invoke() {
        NotificationSpotLightDelegate notificationSpotLightDelegate = this.f$0;
        COUISpotLightEffectDrawable cOUISpotLightEffectDrawable = new COUISpotLightEffectDrawable();
        NotificationSpotLightDelegate$newDrawableWithProgressListener$1$1 notificationSpotLightDelegate$newDrawableWithProgressListener$1$1 = new NotificationSpotLightDelegate$newDrawableWithProgressListener$1$1();
        notificationSpotLightDelegate$newDrawableWithProgressListener$1$1.this$0 = notificationSpotLightDelegate;
        notificationSpotLightDelegate$newDrawableWithProgressListener$1$1.$d = cOUISpotLightEffectDrawable;
        VarHandle.storeStoreFence();
        cOUISpotLightEffectDrawable.setSpotLightProgressScopeListener(notificationSpotLightDelegate$newDrawableWithProgressListener$1$1);
        return cOUISpotLightEffectDrawable;
    }
}
