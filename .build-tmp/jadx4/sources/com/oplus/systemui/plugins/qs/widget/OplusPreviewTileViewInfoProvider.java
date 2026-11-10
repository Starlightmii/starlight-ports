package com.oplus.systemui.plugins.qs.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.widget.QsStaticViewInfoProvider;
import com.oplus.systemui.qs.base.widget.QsViewOutlineProvider;
import com.oplusos.systemui.common.util.ScrimUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusPreviewTileViewInfoProvider implements QsStaticViewInfoProvider {
    private final View mixView;
    private final QsViewOutlineProvider outlineProvider;

    public OplusPreviewTileViewInfoProvider(QsViewOutlineProvider qsViewOutlineProvider, View view) {
        this.outlineProvider = qsViewOutlineProvider;
        this.mixView = view;
    }

    @Override // com.oplus.systemui.qs.base.widget.QsStaticViewInfoProvider, com.oplus.systemui.qs.base.widget.QsViewInfoProvider
    public Drawable getBackgroundDrawable() {
        return super.getBackgroundDrawable();
    }

    @Override // com.oplus.systemui.qs.base.widget.QsStaticViewInfoProvider, com.oplus.systemui.qs.base.widget.QsViewInfoProvider
    public View getBackgroundView() {
        return this.mixView;
    }

    @Override // com.oplus.systemui.qs.base.widget.QsStaticViewInfoProvider, com.oplus.systemui.qs.base.widget.QsViewInfoProvider
    public QsViewOutlineProvider getBgOutlineProvider() {
        return this.outlineProvider;
    }

    @Override // com.oplus.systemui.qs.base.widget.QsStaticViewInfoProvider, com.oplus.systemui.qs.base.widget.QsViewInfoProvider
    public Context getContext() {
        return this.mixView.getContext();
    }

    @Override // com.oplus.systemui.qs.base.widget.QsStaticViewInfoProvider
    public Drawable getGlobalThemeBackgroundDrawable() {
        return null;
    }

    @Override // com.oplus.systemui.qs.base.widget.QsStaticViewInfoProvider, com.oplus.systemui.qs.base.widget.QsViewInfoProvider
    public String getLogTag() {
        return super.getLogTag();
    }

    @Override // com.oplus.systemui.qs.base.widget.QsStaticViewInfoProvider
    public int getNormalBackGroundColor() {
        if (ScrimUtil.isWallpaperBlurDisable()) {
            return getContext().getColor(QsColorUtil.isGlobalThemeApplied(getContext()) ? 2131103738 : 2131103739);
        }
        return QsColorUtil.QS_TILE_PREVIEW_BG_COLOR;
    }

    @Override // com.oplus.systemui.qs.base.widget.QsStaticViewInfoProvider, com.oplus.systemui.qs.base.widget.QsViewInfoProvider
    public void updateBackground(Drawable drawable) {
        super.updateBackground(drawable);
    }

    @Override // com.oplus.systemui.qs.base.widget.QsStaticViewInfoProvider, com.oplus.systemui.qs.base.widget.QsViewInfoProvider
    public void updateBgOutlineProvider(QsViewOutlineProvider qsViewOutlineProvider) {
        super.updateBgOutlineProvider(qsViewOutlineProvider);
    }
}
