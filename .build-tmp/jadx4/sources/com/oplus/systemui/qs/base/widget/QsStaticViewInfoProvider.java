package com.oplus.systemui.qs.base.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface QsStaticViewInfoProvider extends QsViewInfoProvider {
    @Override // com.oplus.systemui.qs.base.widget.QsViewInfoProvider
    /* bridge */ /* synthetic */ default Drawable getBackgroundDrawable() {
        return super.getBackgroundDrawable();
    }

    @Override // com.oplus.systemui.qs.base.widget.QsViewInfoProvider
    /* synthetic */ View getBackgroundView();

    @Override // com.oplus.systemui.qs.base.widget.QsViewInfoProvider
    /* synthetic */ QsViewOutlineProvider getBgOutlineProvider();

    @Override // com.oplus.systemui.qs.base.widget.QsViewInfoProvider
    /* synthetic */ Context getContext();

    Drawable getGlobalThemeBackgroundDrawable();

    @Override // com.oplus.systemui.qs.base.widget.QsViewInfoProvider
    /* bridge */ /* synthetic */ default String getLogTag() {
        return super.getLogTag();
    }

    int getNormalBackGroundColor();

    @Override // com.oplus.systemui.qs.base.widget.QsViewInfoProvider
    /* bridge */ /* synthetic */ default void updateBackground(Drawable drawable) {
        super.updateBackground(drawable);
    }

    @Override // com.oplus.systemui.qs.base.widget.QsViewInfoProvider
    /* bridge */ /* synthetic */ default void updateBgOutlineProvider(QsViewOutlineProvider qsViewOutlineProvider) {
        super.updateBgOutlineProvider(qsViewOutlineProvider);
    }
}
