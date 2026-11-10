package com.oplus.systemui.navigationbar.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.oplus.systemui.navigationbar.gesture.otherbusiness.IOtherBusiness;
import com.oplus.systemui.navigationbar.gesture.otherbusiness.OtherBusinessManager;
import com.oplus.systemui.navigationbar.gesture.otherbusiness.OtherBusinessManager$setupMetaDataListener$1;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OcrScreenCompatVersion$appChangeReceiver$1 extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        OcrScreenCompatVersion ocrScreenCompatVersion = OcrScreenCompatVersion.INSTANCE;
        ocrScreenCompatVersion.getClass();
        ocrScreenCompatVersion.setMetaDataSupport(OcrScreenCompatVersion.getOcrScreenMetaDataEnable(context));
        if (OcrScreenCompatVersion.isMetaDataSupport != OcrScreenCompatVersion.isLastMetaDataSupport) {
            OcrScreenCompatVersion.isLastMetaDataSupport = OcrScreenCompatVersion.isMetaDataSupport;
            Iterator it = ocrScreenCompatVersion.getChangeListeners().iterator();
            while (it.hasNext()) {
                OtherBusinessManager otherBusinessManager = ((OtherBusinessManager$setupMetaDataListener$1) ((OcrScreenCompatVersion.IMetaDataChange) it.next())).this$0;
                IOtherBusiness iOtherBusiness = otherBusinessManager.business;
                if (iOtherBusiness != null) {
                    iOtherBusiness.release();
                }
                otherBusinessManager.business = otherBusinessManager.createBusinessInstance();
                IOtherBusiness iOtherBusiness2 = otherBusinessManager.business;
                if (iOtherBusiness2 != null) {
                    iOtherBusiness2.init();
                }
            }
        }
    }
}
