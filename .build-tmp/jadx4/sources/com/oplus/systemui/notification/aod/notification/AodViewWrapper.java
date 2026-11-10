package com.oplus.systemui.notification.aod.notification;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface AodViewWrapper {
    public static final Companion Companion = Companion.$$INSTANCE;

    AodColor createAodColor(int i);

    default AodColor getAodColor() {
        AodColor aodColor = Companion.getAodColor(getView());
        if (aodColor != null) {
            return aodColor;
        }
        return null;
    }

    View getView();

    boolean isSame(AodColor aodColor);

    void restoreColor(AodColor aodColor);

    default void setAodColor(AodColor aodColor) {
        Companion.setAodColor(getView(), aodColor);
    }

    void setView(View view);

    void updateColor(AodColor aodColor, float f);
}
