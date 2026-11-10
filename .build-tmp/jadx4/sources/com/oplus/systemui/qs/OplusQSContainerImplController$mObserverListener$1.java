package com.oplus.systemui.qs;

import com.android.systemui.qs.PageIndicator;
import com.android.systemui.qs.deprecated.PagedTileLayout;
import com.android.systemui.qs.deprecated.QSPanel;
import com.android.systemui.util.ViewController;
import com.oplusos.systemui.common.observer.IObserverListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSContainerImplController$mObserverListener$1 implements IObserverListener {
    public /* synthetic */ OplusQSContainerImplController this$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        PageIndicator pageIndicator;
        QSPanel qSPanel = ((ViewController) this.this$0.mQsPanelController).mView;
        qSPanel.updatePadding();
        if ((qSPanel.mTileLayout instanceof PagedTileLayout) && (pageIndicator = qSPanel.mFooterPageIndicator) != null) {
            pageIndicator.setVisibility(8);
        }
        QSPanel.QSTileLayout qSTileLayout = qSPanel.mTileLayout;
        if (qSTileLayout != null) {
            qSTileLayout.updateResources();
        }
    }
}
