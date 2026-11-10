package com.oplus.systemui.plugins.qs.customize.component;

import android.util.Log;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTilesComponent$removeAnimateEnd$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSTilesComponent this$0;

    public /* synthetic */ OplusQSTilesComponent$removeAnimateEnd$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "OplusQSTilesComponent-->[removeAnimateEnd] start ");
                }
                OplusQSTilesComponent.refreshEmptyCell$default(this.this$0);
                break;
            default:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "OplusQSTilesComponent-->[onEnterAddingMode] onAddingTransitionEnd");
                }
                OplusQSTilesComponent oplusQSTilesComponent = this.this$0;
                oplusQSTilesComponent.pageIndexBeforeEnterAdding = oplusQSTilesComponent.getCurrentPage();
                OplusQSTilesComponent.refreshEmptyCell$default(this.this$0);
                this.this$0.recyclerView.smoothScrollToPosition(this.this$0.getCustomLayoutManager().getLayoutConfig().getSpecifyLoc().size());
                this.this$0.onAddingTransitionEnd = null;
                break;
        }
    }
}
