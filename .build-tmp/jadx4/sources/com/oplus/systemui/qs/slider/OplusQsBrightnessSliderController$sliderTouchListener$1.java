package com.oplus.systemui.qs.slider;

import android.view.MotionEvent;
import android.view.View;
import androidx.car.app.serialization.Bundler$;
import com.android.systemui.qs.brightness.MirrorController;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.aod.aodclock.off.AodClockLayout$3$;
import com.oplus.systemui.qs.base.seek.OplusQsBaseToggleSliderLayout;
import com.oplus.systemui.statusbar.policy.OplusQsBrightnessSliderMirrorController;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBrightnessSliderController$sliderTouchListener$1 implements View.OnTouchListener {
    public /* synthetic */ OplusQsBrightnessSliderController this$0;

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        MirrorController mirrorController;
        if (OplusQsBrightnessSliderController.access$enforceAdminBlocked(this.this$0)) {
            return true;
        }
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController = this.this$0;
        if (!((OplusQsBaseToggleSliderLayout) ((ViewController) oplusQsBrightnessSliderController).mView).isTouchDelegate) {
            OplusQsBrightnessSliderMirrorController oplusQsBrightnessSliderMirrorController = oplusQsBrightnessSliderController.mMirrorController;
            OplusQsBrightnessSliderMirrorController oplusQsBrightnessSliderMirrorController2 = oplusQsBrightnessSliderMirrorController instanceof OplusQsBrightnessSliderMirrorController ? oplusQsBrightnessSliderMirrorController : null;
            if (oplusQsBrightnessSliderMirrorController2 != null) {
                motionEvent.getClass();
                oplusQsBrightnessSliderMirrorController2.onSliderTouchEvent(motionEvent);
            }
        }
        int action = motionEvent.getAction();
        if (action != 1 && action != 3) {
            return false;
        }
        OplusQsBrightnessSliderController oplusQsBrightnessSliderController2 = this.this$0;
        String str = oplusQsBrightnessSliderController2.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            AodClockLayout$3$.ExternalSyntheticOutline0.m("onTouchEventFinished, mTracking=", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR), oplusQsBrightnessSliderController2.getMTracking());
        }
        if (!this.this$0.getMTracking() || (mirrorController = this.this$0.mMirrorController) == null) {
            return false;
        }
        mirrorController.hideMirror();
        return false;
    }
}
