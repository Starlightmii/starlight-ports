package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.view.View;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableTileViewHolder$qsTileClickListener$1;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSResizeableTileView$init$tileLongClick$1 implements View.OnLongClickListener {
    public /* synthetic */ Expandable $expandable;
    public /* synthetic */ QSTile $tile;
    public /* synthetic */ OplusQSResizeableTileView this$0;

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_QS_SHOW_DIALOG)) {
            return false;
        }
        this.$tile.longClick(this.$expandable);
        OplusQSResizeableTileView.TileClickListener tileClickListener = this.this$0.getTileClickListener();
        if (tileClickListener == null) {
            return true;
        }
        ((EditableTileViewHolder$qsTileClickListener$1) tileClickListener).tileOnClick(SeparateQSStatisicUtil.CLICK_QS_PANEL_EVENT_LONG_CLICK, this.this$0.getTileState());
        return true;
    }
}
