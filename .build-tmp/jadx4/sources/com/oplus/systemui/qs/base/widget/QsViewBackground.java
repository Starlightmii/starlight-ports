package com.oplus.systemui.qs.base.widget;

import com.android.systemui.plugins.qs.QSTile;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface QsViewBackground {
    default Pair<Integer, Integer> getDeformBackgroundColor() {
        return new Pair<>(0, 0);
    }

    default void handleStateChanged(QSTile.State state) {
    }

    default void onBackgroundAttach() {
    }

    default void onBackgroundDetach() {
    }

    default void refreshViewBackground() {
    }

    default void setInDeform(boolean z) {
    }
}
