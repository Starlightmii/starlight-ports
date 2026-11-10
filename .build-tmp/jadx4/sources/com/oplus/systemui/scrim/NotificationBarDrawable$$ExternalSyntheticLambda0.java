package com.oplus.systemui.scrim;

import android.graphics.Paint;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationBarDrawable$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ NotificationBarDrawable f$0;

    public final Object invoke() {
        NotificationBarDrawable notificationBarDrawable = this.f$0;
        NotificationBarDrawable.Companion companion = NotificationBarDrawable.Companion;
        Paint paint = new Paint();
        paint.setColor(notificationBarDrawable.context.getColor(2131102932));
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }
}
