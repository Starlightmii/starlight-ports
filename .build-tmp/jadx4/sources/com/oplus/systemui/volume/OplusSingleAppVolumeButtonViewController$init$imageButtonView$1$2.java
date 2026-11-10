package com.oplus.systemui.volume;

import android.view.MotionEvent;
import android.view.View;
import com.oplus.systemui.volume.touch.SingleAppVolumeButtonTouchHandler;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSingleAppVolumeButtonViewController$init$imageButtonView$1$2 implements View.OnTouchListener {
    public /* synthetic */ OplusSingleAppVolumeButtonViewController this$0;

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        SingleAppVolumeButtonTouchHandler singleAppVolumeButtonTouchHandler = this.this$0.touchHandler;
        if (singleAppVolumeButtonTouchHandler == null) {
            return false;
        }
        motionEvent.getClass();
        return singleAppVolumeButtonTouchHandler.onTouchEvent(motionEvent);
    }
}
