package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.util.Log;
import android.view.View;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSThreeStageLayout$3$1 implements View.OnClickListener {
    public /* synthetic */ int $index;
    public /* synthetic */ OplusQSThreeStageLayout this$0;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.$index;
        if (i == 0) {
            this.this$0.setTag(2);
        } else if (i == 1) {
            this.this$0.setTag(1);
        } else if (i == 2) {
            this.this$0.setTag(0);
        }
        OplusQSThreeStageLayout oplusQSThreeStageLayout = this.this$0;
        int i2 = this.$index;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Object tag = oplusQSThreeStageLayout.getTag();
            int i3 = oplusQSThreeStageLayout.preSelectedIndex;
            int i4 = oplusQSThreeStageLayout.selectedIndex;
            StringBuilder sb = new StringBuilder("onClickListener: tag:");
            sb.append(tag);
            sb.append(" index:");
            sb.append(i2);
            sb.append(" preSelectedIndex:");
            Log.d("SystemUi--QuickSettings", NotificationPanelViewController$.ExternalSyntheticOutline0.m(i3, i4, " selectedIndex:", "OplusQSThreeStageLayout-->", sb));
        }
        OplusQSThreeStageLayout oplusQSThreeStageLayout2 = this.this$0;
        if (oplusQSThreeStageLayout2.selectedIndex != this.$index) {
            oplusQSThreeStageLayout2.viewClick.invoke();
            this.this$0.selectSegmentAt(this.$index);
            View.OnClickListener onClickListener = this.this$0.onClickListener;
            if (onClickListener != null) {
                onClickListener.onClick(this.this$0);
            }
        }
    }
}
