package com.oplus.systemui.plugins.qs.customize.component;

import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.qs.base.widget.recyclerview.OplusItemSpringAnimator;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTilesComponent$animationListener$1 implements OplusItemSpringAnimator.RecyclerViewAnimationListener {
    public /* synthetic */ OplusQSTilesComponent this$0;

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.OplusItemSpringAnimator.RecyclerViewAnimationListener
    public final boolean onAnimateAdd(RecyclerView.ViewHolder viewHolder) {
        LogUtil.i$default(LogUtil.TAG_QS, "OplusQSTilesComponent", "onAnimateAdd: " + viewHolder, null, 8);
        OplusQSTilesComponent oplusQSTilesComponent = this.this$0;
        Function2 function2 = oplusQSTilesComponent.onAdd;
        if (function2 != null) {
            return ((Boolean) function2.invoke(oplusQSTilesComponent.getTileAdapter().getDataList().get(viewHolder.getAbsoluteAdapterPosition()), viewHolder)).booleanValue();
        }
        return false;
    }
}
