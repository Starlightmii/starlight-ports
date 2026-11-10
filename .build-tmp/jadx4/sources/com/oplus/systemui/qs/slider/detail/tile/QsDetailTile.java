package com.oplus.systemui.qs.slider.detail.tile;

import android.content.Intent;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.qs.QSTile;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface QsDetailTile {
    void addCallback(QSTile.Callback callback);

    void click(Expandable expandable);

    int getClickTipsType();

    int getDetailType();

    Intent getLongClickIntent();

    String getSecondaryLabel(QSTile.State state);

    String getTileLabel();

    String getTileSpec();

    boolean isAvailable();

    void longClick(Expandable expandable);

    void removeCallback(QSTile.Callback callback);

    void secondaryClick(Expandable expandable);

    void setListening(boolean z);
}
