package com.oplus.systemui.plugins.qs.customize.view.deformation;

import android.content.Context;
import android.view.View;
import com.oplus.systemui.plugins.qs.CellCalculatorManager;
import com.oplus.systemui.plugins.qs.customize.view.tile.QsLabelView;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.viewholder.CellInfo;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ITileViewCreator {
    View fetchTileView(QsLabelView qsLabelView, SpanSize spanSize, boolean z);

    default CellInfo getCellInfo() {
        return null;
    }

    default CellCalculatorManager.LayoutConstraint getLayoutConstraint(Context context, SpanSize spanSize, boolean z, boolean z2, boolean z3) {
        return CellCalculatorManager.Companion.getInstance(context).getLayoutConstraint(spanSize, z, z2, z3);
    }

    default void onInnerViewChanged(View view) {
    }

    default void recycle(SpanSize spanSize, View view) {
    }
}
