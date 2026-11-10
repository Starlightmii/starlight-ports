package com.oplus.systemui.qs.slider;

import android.view.MotionEvent;
import com.oplus.systemui.qs.base.util.Gefingerpoken;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBrightnessSliderController$interceptListener$1 implements Gefingerpoken {
    public /* synthetic */ OplusQsBrightnessSliderController this$0;

    @Override // com.oplus.systemui.qs.base.util.Gefingerpoken
    public /* bridge */ /* synthetic */ boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.oplus.systemui.qs.base.util.Gefingerpoken
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController = this.this$0;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            oplusQsBrightnessSliderController.falsingManager.isFalseTouch(10);
        }
        OplusQsBrightnessSliderController.access$enforceAdminBlocked(oplusQsBrightnessSliderController);
        return false;
    }

    @Override // com.oplus.systemui.qs.base.util.Gefingerpoken
    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }
}
