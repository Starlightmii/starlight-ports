package com.oplus.systemui.qs.slider;

import android.content.Context;
import android.content.Intent;
import com.android.systemui.qs.brightness.BrightnessMirrorController;
import com.oplusos.systemui.common.receiver.IReceiverListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBrightnessSliderController$themeChangeListener$1 implements IReceiverListener {
    public /* synthetic */ OplusQsBrightnessSliderController this$0;

    @Override // com.oplusos.systemui.common.receiver.IReceiverListener
    public final void onReceive(Context context, Intent intent) {
        if ("oplus.intent.action.SKIN_CHANGED".equals(intent.getAction())) {
            BrightnessMirrorController brightnessMirrorController = this.this$0.mMirrorController;
            BrightnessMirrorController brightnessMirrorController2 = brightnessMirrorController instanceof BrightnessMirrorController ? brightnessMirrorController : null;
            if (brightnessMirrorController2 != null) {
                brightnessMirrorController2.reinflate();
            }
        }
    }
}
