package com.oplus.systemui.separate;

import android.util.Log;
import android.view.View;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSimpleQSFakeController$layoutChangedListener$1 implements View.OnLayoutChangeListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusSimpleQSFakeController this$0;

    public /* synthetic */ OplusSimpleQSFakeController$layoutChangedListener$1(int i) {
        this.$r8$classId = i;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        switch (this.$r8$classId) {
            case 0:
                if (i != i5) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "OplusSimpleQSFakeController-->layoutChangedListener,updateFakeElementLoc");
                    }
                    this.this$0.updateFakeElementLoc();
                }
                break;
            default:
                View statusIcons = this.this$0.getStatusIcons();
                if (statusIcons != null) {
                    statusIcons.setPivotX(statusIcons.isLayoutRtl() ? 0.0f : statusIcons.getWidth());
                    statusIcons.setPivotY(statusIcons.getHeight() / 2.0f);
                }
                break;
        }
    }
}
