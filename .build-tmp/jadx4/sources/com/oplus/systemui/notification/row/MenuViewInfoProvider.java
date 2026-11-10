package com.oplus.systemui.notification.row;

import android.util.Size;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface MenuViewInfoProvider {
    int getIconMargin();

    int getIconMarginExtra();

    Size getIconSize(boolean z);

    default void updateMetaBall() {
    }
}
