package com.oplus.systemui.statusbar.seeding;

import android.view.ViewGroup;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IScaledAncestorView {
    default void addScaleChangedListener(ScaleChangedListener scaleChangedListener) {
    }

    DIRECTION getPivotXDirection();

    boolean getScaleAnimateEnable();

    List<ScaleChangedListener> getScaleChangedListeners();

    float getScaleFraction(boolean z);

    ViewGroup getScaledAncestorView();

    default void removeScaleChangedListener(ScaleChangedListener scaleChangedListener) {
    }

    void setScaleAnimateEnable(boolean z);

    void setScaleChangedListeners(List<ScaleChangedListener> list);
}
