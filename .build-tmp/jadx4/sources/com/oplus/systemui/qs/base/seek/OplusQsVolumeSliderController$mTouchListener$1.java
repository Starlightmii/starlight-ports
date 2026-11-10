package com.oplus.systemui.qs.base.seek;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsVolumeSliderController$mTouchListener$1 implements View.OnTouchListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQsVolumeSliderController this$0;

    public /* synthetic */ OplusQsVolumeSliderController$mTouchListener$1(int i) {
        this.$r8$classId = i;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        switch (this.$r8$classId) {
            case 0:
                if (!((OplusQsBaseToggleSliderLayout) this.this$0.mView).isEditable() && motionEvent.getAction() == 0) {
                    this.this$0.getVolumeController().updateHint();
                }
                break;
            default:
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.this$0.isTrackingTouch = true;
                } else if (action == 1 || action == 3) {
                    this.this$0.isTrackingTouch = false;
                }
                break;
        }
        return false;
    }
}
