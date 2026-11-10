package com.oplus.systemui.separate;

import android.util.Log;
import android.view.ViewTreeObserver;
import com.android.systemui.statusbar.policy.Clock;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSimpleHeader$initClockView$2 implements ViewTreeObserver.OnGlobalLayoutListener {
    public /* synthetic */ OplusQSSimpleHeader this$0;

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        Clock clock;
        ViewTreeObserver viewTreeObserver;
        Clock clock2 = this.this$0.clockView;
        if (clock2 != null && (viewTreeObserver = clock2.getViewTreeObserver()) != null) {
            viewTreeObserver.removeOnGlobalLayoutListener(this);
        }
        Clock clock3 = this.this$0.clockView;
        if (clock3 != null) {
            clock3.setPivotX((!DeviceUtil.isRtl() || (clock = this.this$0.clockView) == null) ? 0.0f : clock.getWidth());
        }
        Clock clock4 = this.this$0.clockView;
        if (clock4 != null) {
            Clock clock5 = this.this$0.clockView;
            clock4.setPivotY(clock5 != null ? clock5.getBaseline() : 0.0f);
        }
        this.this$0.getQsFakeController().updateClockPivot$1();
        OplusQSSimpleHeader oplusQSSimpleHeader = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sb = new StringBuilder("OplusQSSimpleHeader-->");
            Clock clock6 = oplusQSSimpleHeader.clockView;
            Float fValueOf = clock6 != null ? Float.valueOf(clock6.getPivotY()) : null;
            Clock clock7 = oplusQSSimpleHeader.clockView;
            Float fValueOf2 = clock7 != null ? Float.valueOf(clock7.getPivotX()) : null;
            Clock clock8 = oplusQSSimpleHeader.clockView;
            Integer numValueOf = clock8 != null ? Integer.valueOf(clock8.getWidth()) : null;
            Clock clock9 = oplusQSSimpleHeader.clockView;
            sb.append("onGlobalLayout updateClockPivot clockView?.pivotY: " + fValueOf + ",clockView.pivotX: " + fValueOf2 + ",clockView.width: " + numValueOf + ",clockView.height: " + (clock9 != null ? Integer.valueOf(clock9.getHeight()) : null));
            Log.d("SystemUi--Notification", sb.toString());
        }
        if (this.this$0.getPanelAnimationEx().getPanelExpandFraction().getRawFraction() == 1.0f) {
            this.this$0.getQsFakeController().updateQSClockScale(1.0f);
        }
    }
}
