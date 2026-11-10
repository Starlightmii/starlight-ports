package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.view.View;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableTileViewHolder$qsTileClickListener$1;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;
import com.oplusos.systemui.common.view.ViewExtKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSResizeableTileView$init$tileClick$1 implements View.OnClickListener {
    public /* synthetic */ Object $expandable;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ QSTile $tile;
    public /* synthetic */ OplusQSResizeableTileView this$0;

    public /* synthetic */ OplusQSResizeableTileView$init$tileClick$1(int i) {
        this.$r8$classId = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        View view2;
        switch (this.$r8$classId) {
            case 0:
                Expandable expandable = (Expandable) this.$expandable;
                if (expandable != null && (view2 = expandable.getView()) != null) {
                    view2.setTag(2131364805, view.getTag());
                }
                this.$tile.click((Expandable) this.$expandable);
                OplusQSResizeableTileView.TileClickListener tileClickListener = this.this$0.getTileClickListener();
                if (tileClickListener != null) {
                    ((EditableTileViewHolder$qsTileClickListener$1) tileClickListener).tileOnClick("click", this.this$0.getTileState());
                }
                break;
            default:
                if (!QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_QS_SHOW_DIALOG)) {
                    this.$tile.secondaryClick((Expandable) null);
                    View view3 = (View) this.$expandable;
                    if (view3 != null) {
                        ViewExtKt.setPerformClickedFlag(view3, true);
                    }
                    OplusQSResizeableTileView.TileClickListener tileClickListener2 = this.this$0.getTileClickListener();
                    if (tileClickListener2 != null) {
                        ((EditableTileViewHolder$qsTileClickListener$1) tileClickListener2).tileOnClick("secondaryClick", this.this$0.getTileState());
                    }
                    break;
                }
                break;
        }
    }
}
