package com.oplus.systemui.qs.base.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOutlineProvider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface QsViewInfoProvider {
    default Drawable getBackgroundDrawable() {
        return getBackgroundView().getBackground();
    }

    View getBackgroundView();

    QsViewOutlineProvider getBgOutlineProvider();

    Context getContext();

    default String getLogTag() {
        return "";
    }

    default void updateBackground(Drawable drawable) {
        getBackgroundView().setBackground(drawable);
        if (drawable instanceof LightStyleStrokeDrawable) {
            ViewOutlineProvider outlineProvider = getBackgroundView().getOutlineProvider();
            QsViewOutlineProvider qsViewOutlineProvider = outlineProvider instanceof QsViewOutlineProvider ? (QsViewOutlineProvider) outlineProvider : null;
            if (qsViewOutlineProvider != null) {
                qsViewOutlineProvider.refreshSmoothRound();
            }
        }
    }

    default void updateBgOutlineProvider(QsViewOutlineProvider qsViewOutlineProvider) {
        getBackgroundView().setOutlineProvider(qsViewOutlineProvider);
        getBackgroundView().setClipToOutline(qsViewOutlineProvider != null);
        if (qsViewOutlineProvider != null) {
            qsViewOutlineProvider.invalidateOutline();
        }
    }
}
