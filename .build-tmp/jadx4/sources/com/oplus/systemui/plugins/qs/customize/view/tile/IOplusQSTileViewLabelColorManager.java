package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.content.Context;
import android.content.res.ColorStateList;
import com.android.systemui.plugins.qs.QSTile;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IOplusQSTileViewLabelColorManager {
    public static final Companion Companion = Companion.$$INSTANCE;

    void disableTheme(boolean z);

    Pair<ColorStateList, ColorStateList> getColorByTileState(Context context, QSTile.State state);

    void initLabelColor(Context context);
}
