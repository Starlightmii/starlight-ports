package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.content.Context;
import android.content.res.ColorStateList;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSHighlightTileViewLabelColorManager implements IOplusQSTileViewLabelColorManager {
    public boolean disableTheme;
    private ColorStateList mColorLabelActive;
    private ColorStateList mColorLabelInActive;
    private ColorStateList mColorLabelUnavailable;
    private ColorStateList mColorSecondLabelActive;

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.IOplusQSTileViewLabelColorManager
    public final void disableTheme(boolean z) {
        this.disableTheme = z;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.IOplusQSTileViewLabelColorManager
    public Pair<ColorStateList, ColorStateList> getColorByTileState(Context context, QSTile.State state) {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        if (ScrimUtil.isWallpaperBlurDisable()) {
            Integer numValueOf = state != null ? Integer.valueOf(state.state) : null;
            if ((numValueOf != null && numValueOf.intValue() == 0) || (numValueOf != null && numValueOf.intValue() == 101)) {
                colorStateList2 = context.getColorStateList(2131103711);
            } else {
                colorStateList2 = OplusDarkModeUtil.isNightMode(context) ? context.getColorStateList(2131103285) : context.getColorStateList(2131103709);
            }
            colorStateList2.getClass();
            return new Pair<>(colorStateList2, colorStateList2);
        }
        Integer numValueOf2 = state != null ? Integer.valueOf(state.state) : null;
        if ((numValueOf2 != null && numValueOf2.intValue() == 0) || (numValueOf2 != null && numValueOf2.intValue() == 101)) {
            ColorStateList colorStateList3 = QsColorUtil.isNeedUseDarkThemeColor(context, this.disableTheme) ? context.getColorStateList(2131103291) : this.mColorLabelUnavailable;
            return new Pair<>(colorStateList3, colorStateList3);
        }
        if (numValueOf2 != null && numValueOf2.intValue() == 2) {
            ColorStateList colorStateList4 = this.mColorLabelActive;
            if (QsColorUtil.isNeedUseDarkThemeColor(context, this.disableTheme)) {
                colorStateList4 = context.getColorStateList(2131103285);
                colorStateList = context.getColorStateList(2131103290);
            } else {
                colorStateList = colorStateList4;
            }
            return new Pair<>(colorStateList4, colorStateList);
        }
        if (numValueOf2 == null || numValueOf2.intValue() != 1) {
            return null;
        }
        ColorStateList colorStateList5 = this.mColorLabelInActive;
        boolean z = state.toggleable;
        boolean z2 = this.disableTheme;
        if (z) {
            if (QsColorUtil.isNeedUseDarkThemeColor(context, z2)) {
                colorStateList5 = context.getColorStateList(2131103292);
            }
        } else if (QsColorUtil.isNeedUseDarkThemeColor(context, z2)) {
            colorStateList5 = context.getColorStateList(2131103292);
        }
        return new Pair<>(colorStateList5, colorStateList5);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.tile.IOplusQSTileViewLabelColorManager
    public void initLabelColor(Context context) {
        this.mColorLabelUnavailable = context.getColorStateList(!this.disableTheme ? 2131103710 : 2131103711);
        this.mColorLabelActive = context.getColorStateList(!this.disableTheme ? 2131103707 : 2131103708);
        this.mColorLabelInActive = context.getColorStateList(!this.disableTheme ? 2131103706 : 2131103709);
        this.mColorSecondLabelActive = context.getColorStateList(2131103712);
    }
}
