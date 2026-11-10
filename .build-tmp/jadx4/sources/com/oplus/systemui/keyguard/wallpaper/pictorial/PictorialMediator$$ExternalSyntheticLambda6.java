package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Trace;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialMediator$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ PictorialMediator f$0;
    public /* synthetic */ Object f$1;

    public /* synthetic */ PictorialMediator$$ExternalSyntheticLambda6(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                PictorialMediator pictorialMediator = this.f$0;
                Bundle bundle = (Bundle) this.f$1;
                AtomicBoolean atomicBoolean = PictorialMediator.sPictorialFestivalWallpaperUsed;
                Trace.traceBegin(8L, "reportStatisticData");
                try {
                    pictorialMediator.mPictorialCursorHelper.reportStatisticData(pictorialMediator.mContentResolver, pictorialMediator.mPictorialAppDataImpl.getStatisticUri(), pictorialMediator.mPictorialAppDataImpl.getStatisticMethod(), bundle);
                } catch (Exception e) {
                    KgdLogUtil.w("PictorialMediator", "e = " + e.toString());
                }
                Trace.traceEnd(8L);
                break;
            default:
                PictorialMediator pictorialMediator2 = this.f$0;
                ContentValues contentValues = (ContentValues) this.f$1;
                AtomicBoolean atomicBoolean2 = PictorialMediator.sPictorialFestivalWallpaperUsed;
                pictorialMediator2.getClass();
                Trace.traceBegin(8L, "notifyPictorialFinish");
                try {
                    pictorialMediator2.mPictorialCursorHelper.update(pictorialMediator2.mContentResolver, pictorialMediator2.mPictorialAppDataImpl.getKeyguardNotifyFinishUri(), contentValues);
                } catch (Exception e2) {
                    KgdLogUtil.w("PictorialMediator", "e = " + e2.toString());
                }
                Trace.traceEnd(8L);
                break;
        }
    }
}
