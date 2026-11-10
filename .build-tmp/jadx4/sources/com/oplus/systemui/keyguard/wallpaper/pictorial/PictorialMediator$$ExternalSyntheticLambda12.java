package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Trace;
import com.oplus.transition.OplusAnimationController;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialMediator$$ExternalSyntheticLambda12 implements Runnable {
    public /* synthetic */ PictorialMediator f$0;
    public /* synthetic */ String f$2;
    public /* synthetic */ PackageManager f$3;

    @Override // java.lang.Runnable
    public final void run() {
        PictorialMediator pictorialMediator = this.f$0;
        String str = this.f$2;
        PackageManager packageManager = this.f$3;
        AtomicBoolean atomicBoolean = PictorialMediator.sPictorialFestivalWallpaperUsed;
        Trace.traceBegin(8L, "checkIsSupportSwipeLeft");
        Intent intent = new Intent();
        intent.setClassName("com.heytap.pictorial", str);
        if (packageManager != null) {
            List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, OplusAnimationController.FLAG_ALLOW_LIGHTOS_CORNER_RADIUS);
            if (listQueryIntentActivities == null || listQueryIntentActivities.size() <= 0) {
                pictorialMediator.setIsSupportLeftSwipe(false);
            } else {
                pictorialMediator.setIsSupportLeftSwipe(true);
            }
        }
        Trace.traceEnd(8L);
    }
}
