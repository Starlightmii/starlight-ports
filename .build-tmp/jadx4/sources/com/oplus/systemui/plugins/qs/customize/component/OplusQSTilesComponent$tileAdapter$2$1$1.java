package com.oplus.systemui.plugins.qs.customize.component;

import android.util.Log;
import androidx.collection.FloatList$;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTilesComponent$tileAdapter$2$1$1 extends RecyclerView.AdapterDataObserver {
    public /* synthetic */ OplusQSTilesComponent this$0;

    public final void onItemRangeChanged(int i, int i2) {
        OplusQSTilesComponent oplusQSTilesComponent = this.this$0;
        Integer itemPageIndex = oplusQSTilesComponent.getCustomLayoutManager().getItemPageIndex(i);
        if (itemPageIndex != null) {
            int iIntValue = itemPageIndex.intValue();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", NotificationPanelViewController$.ExternalSyntheticOutline0.m(iIntValue, oplusQSTilesComponent.getCurrentPage(), ", currentPage= ", "OplusQSTilesComponent-->", FloatList$.ExternalSyntheticOutline0.m("[onItemRangeChanged] positionStart= ", i, i2, ", itemCount= ", ", pageIndex= ")));
            }
            oplusQSTilesComponent.getItemSpringAnimator().setChangeAnimatorDelay(iIntValue != oplusQSTilesComponent.getCurrentPage() ? 200L : 0L);
        }
    }

    public final void onItemRangeInserted(int i, int i2) {
        OplusQSTilesComponent oplusQSTilesComponent = this.this$0;
        Integer itemPageIndex = oplusQSTilesComponent.getCustomLayoutManager().getItemPageIndex(i);
        if (itemPageIndex != null) {
            int iIntValue = itemPageIndex.intValue();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", NotificationPanelViewController$.ExternalSyntheticOutline0.m(iIntValue, oplusQSTilesComponent.getCurrentPage(), ", currentPage= ", "OplusQSTilesComponent-->", FloatList$.ExternalSyntheticOutline0.m("[onItemRangeInserted] positionStart= ", i, i2, ", itemCount=", ", pageIndex= ")));
            }
            oplusQSTilesComponent.getItemSpringAnimator().setAddAnimatorDelay(iIntValue != oplusQSTilesComponent.getCurrentPage() ? 200L : 0L);
        }
    }
}
