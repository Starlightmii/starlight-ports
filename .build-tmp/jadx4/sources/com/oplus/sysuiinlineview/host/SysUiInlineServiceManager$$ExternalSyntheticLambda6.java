package com.oplus.sysuiinlineview.host;

import android.util.Log;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SysUiInlineServiceManager$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ SysUiInlineServiceManager f$0;

    public /* synthetic */ SysUiInlineServiceManager$$ExternalSyntheticLambda6(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        SysUiInlineServiceManager sysUiInlineServiceManager = this.f$0;
        switch (i) {
            case 0:
                SysUiInlineServiceManager.m491$r8$lambda$XYmfsNQ6sqLAwJKPhRfTGiLiMU(sysUiInlineServiceManager);
                break;
            case 1:
                SysUiInlineServiceManager.m492$r8$lambda$cmmZ9y4tVN4dCpLw5dEpeArDjY(sysUiInlineServiceManager);
                break;
            case 2:
                SysUiInlineServiceManager.$r8$lambda$tsSFN9fXDOvpiDGg0uCxVoqcxzM(sysUiInlineServiceManager);
                break;
            default:
                boolean zIsListening = sysUiInlineServiceManager.isListening();
                Log.d("SysUiInlineServiceManager", Intrinsics.stringPlus(Boolean.valueOf(zIsListening), "checkListenerStatus: listening="));
                if (!zIsListening) {
                    sysUiInlineServiceManager.onStopListening();
                } else {
                    sysUiInlineServiceManager.resetHandleMessageTimeout();
                }
                break;
        }
    }
}
