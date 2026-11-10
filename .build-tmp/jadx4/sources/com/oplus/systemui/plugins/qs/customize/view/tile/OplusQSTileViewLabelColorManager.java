package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSTileViewLabelColorManager implements IOplusQSTileViewLabelColorManager {
    private ColorStateList mColorLabelDefault;
    private ColorStateList mColorLabelUnavailable;
    private ColorStateList mColorSecondLabelDefault;
    private ColorStateList mColorSecondLabelUnavailable;

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.IOplusQSTileViewLabelColorManager
    public final void disableTheme(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.IOplusQSTileViewLabelColorManager
    public Pair<ColorStateList, ColorStateList> getColorByTileState(Context context, QSTile.State state) {
        Integer numValueOf = state != null ? Integer.valueOf(state.state) : null;
        return ((numValueOf != null && numValueOf.intValue() == 0) || (numValueOf != null && numValueOf.intValue() == 101)) ? new Pair<>(this.mColorLabelUnavailable, this.mColorSecondLabelUnavailable) : new Pair<>(this.mColorLabelDefault, this.mColorSecondLabelDefault);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.IOplusQSTileViewLabelColorManager
    public void initLabelColor(Context context) {
        if (!QsColorUtil.isTextNeedUseLightColorWhenLight(context, QsColorUtil.getQsColorState())) {
            this.mColorLabelDefault = context.getColorStateList(2131103752);
            this.mColorLabelUnavailable = context.getColorStateList(2131103754);
            this.mColorSecondLabelDefault = context.getColorStateList(2131103756);
            this.mColorSecondLabelUnavailable = context.getColorStateList(2131103757);
            return;
        }
        this.mColorLabelDefault = context.getColorStateList(R.color.white);
        int i = QsColorUtil.QS_TILE_LABEL_UNAVAILABLE_COLOR;
        this.mColorLabelUnavailable = ColorStateList.valueOf(i);
        this.mColorSecondLabelDefault = context.getColorStateList(R.color.white);
        this.mColorSecondLabelUnavailable = ColorStateList.valueOf(i);
    }
}
