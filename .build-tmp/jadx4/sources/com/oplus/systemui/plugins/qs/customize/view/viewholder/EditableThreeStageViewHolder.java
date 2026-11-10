package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.qs.QSTileView;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.ThreeStageDeformationAnimationController;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableThreeStageView;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSThreeStageLayout;
import com.oplus.systemui.plugins.qs.customize.viewmodel.TileEditViewModel;
import com.oplus.systemui.plugins.qs.customize.viewmodel.model.TileUiType;
import com.oplus.systemui.plugins.qs.util.VibratorHelper;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditableThreeStageViewHolder extends EditableTileViewHolder {
    public boolean isListening;

    public EditableThreeStageViewHolder(Context context, ViewGroup viewGroup, TileEditViewModel tileEditViewModel) {
        super(context, viewGroup, tileEditViewModel, true);
        this.isListening = ((EditableTileViewHolder) this).isListening;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableTileViewHolder
    public IDeformationAnimationController createTileDeformationAnimationController() {
        return !VibratorHelper.Companion.getInstance(getContext()).hasVibrator() ? super.createTileDeformationAnimationController() : new ThreeStageDeformationAnimationController();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableTileViewHolder
    public QSTileView fetchTileView(SpanSize spanSize, boolean z) {
        return EditableTileViewHolder.Companion.fetch(spanSize, TileUiType.THREE_STAGE, getContext());
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableTileViewHolder
    public final boolean isListening() {
        return this.isListening;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.EditableTileViewHolder, com.oplus.systemui.plugins.qs.customize.view.viewholder.BaseEditableViewHolder
    public void setListening(boolean z) {
        super.setListening(z);
        if (this.isListening != z) {
            this.isListening = z;
            QSTileView currRealTileView = getCurrRealTileView();
            OplusQSResizeableThreeStageView oplusQSResizeableThreeStageView = currRealTileView instanceof OplusQSResizeableThreeStageView ? (OplusQSResizeableThreeStageView) currRealTileView : null;
            if (oplusQSResizeableThreeStageView != null) {
                View iconView = oplusQSResizeableThreeStageView.getIconView().getIconView();
                OplusQSThreeStageLayout oplusQSThreeStageLayout = iconView instanceof OplusQSThreeStageLayout ? (OplusQSThreeStageLayout) iconView : null;
                QSTile.State tileState = oplusQSResizeableThreeStageView.getTileState();
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "OplusQSResizeableThreeStageView-->".concat("state.threeStageMode: " + (tileState != null ? Integer.valueOf(tileState.threeStageMode) : null) + " curMode: " + (oplusQSThreeStageLayout != null ? Integer.valueOf(oplusQSThreeStageLayout.getCurrentMode()) : null)));
                }
                if (tileState == null || oplusQSThreeStageLayout == null || tileState.threeStageMode == oplusQSThreeStageLayout.getCurrentMode()) {
                    return;
                }
                oplusQSThreeStageLayout.setSelectedIndex(tileState.threeStageMode, tileState.contentDescription);
            }
        }
    }
}
