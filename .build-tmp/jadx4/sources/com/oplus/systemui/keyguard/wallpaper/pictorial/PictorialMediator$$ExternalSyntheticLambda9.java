package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.os.Bundle;
import android.os.Message;
import android.os.Trace;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.media.controls.domain.resume.MediaResumeListener$getResumeAction$1$;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialMediator$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;
    public /* synthetic */ int f$1;

    public /* synthetic */ PictorialMediator$$ExternalSyntheticLambda9(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                PictorialMediator.$r8$lambda$afokr_6l6CvzisMooRn8EX__67Q((PictorialMediator) this.f$0, this.f$1);
                break;
            case 1:
                PictorialMediator pictorialMediator = (PictorialMediator) this.f$0;
                int i = this.f$1;
                PictorialMediator.AnonymousClass3 anonymousClass3 = pictorialMediator.mHandler;
                Trace.traceBegin(8L, "updatePictorialGuideEntryInfo");
                if (i == KeyguardUpdateMonitor.getCurrentUser()) {
                    PictorialGuideEntity pictorialGuidInfo = pictorialMediator.mPictorialServiceConnected ? pictorialMediator.mPictorialCursorHelper.getPictorialGuidInfo(pictorialMediator.mDefaultDensityContext, pictorialMediator.mContentResolver, pictorialMediator.mPictorialAppDataImpl.getUsePictorialViewShowUrl()) : null;
                    if (anonymousClass3.hasMessages(4)) {
                        anonymousClass3.removeMessages(4);
                    }
                    Message messageObtainMessage = anonymousClass3.obtainMessage(4);
                    messageObtainMessage.obj = pictorialGuidInfo;
                    messageObtainMessage.arg1 = i;
                    anonymousClass3.sendMessage(messageObtainMessage);
                    Trace.traceEnd(8L);
                } else {
                    StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "updatePictorialGuideEntryInfo not same userID in runnable, return, userId = ", ", KeyguardUpdateMonitor.getCurrentUser() = ");
                    sbM.append(KeyguardUpdateMonitor.getCurrentUser());
                    KgdLogUtil.i("PictorialMediator", sbM.toString());
                }
                break;
            default:
                PictorialMediator.AnonymousClass4 anonymousClass4 = (PictorialMediator.AnonymousClass4) this.f$0;
                int i2 = this.f$1;
                PictorialMediator pictorialMediator2 = anonymousClass4.this$0;
                boolean z = i2 == 1;
                AtomicBoolean atomicBoolean = PictorialMediator.sPictorialFestivalWallpaperUsed;
                try {
                    Bundle bundleCall = pictorialMediator2.mContentResolver.call("com.oplus.wallpapers.InspirationThemeProvider", "notify_lehua_switch_from_lockscreen", (String) null, MediaResumeListener$getResumeAction$1$.ExternalSyntheticOutline0.m("key_lehua_switch_open", z));
                    if (bundleCall != null) {
                        KgdLogUtil.i("PictorialMediator", "notifyInspirationThemeSettingChanged result = " + bundleCall.getBoolean("notify_lehua_switch_success"));
                    } else {
                        KgdLogUtil.i("PictorialMediator", "notifyInspirationThemeSettingChanged result is null");
                    }
                } catch (Exception e) {
                    KgdLogUtil.e("PictorialMediator", "notifyInspirationThemeSettingChanged Exception = " + e.getMessage());
                    return;
                }
                break;
        }
    }
}
