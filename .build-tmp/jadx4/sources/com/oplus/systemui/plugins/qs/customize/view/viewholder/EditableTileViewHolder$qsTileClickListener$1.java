package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.content.Context;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditableTileViewHolder$qsTileClickListener$1 implements OplusQSResizeableTileView.TileClickListener {
    public /* synthetic */ Context $context;
    public /* synthetic */ TileEditViewModel $viewModel;

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileView.TileClickListener
    public final void tileOnClick(String str, QSTile.State state) {
        String str2 = state != null ? state.spec : null;
        if (str2 != null) {
            Pair<String, String> specSizeAndLoc = this.$viewModel.getSpecSizeAndLoc(str2);
            SeparateQSStatisicUtil.collectShortcutsCenterEvent(this.$context, str + ":" + specSizeAndLoc.getFirst(), false, (String) specSizeAndLoc.getSecond());
        }
    }
}
