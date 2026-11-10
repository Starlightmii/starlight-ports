package com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusMobileIconViewModelCommon {
    StateFlow getActivityVisible();

    StateFlow getIcon();

    StateFlow getMobileActivityResId();

    StateFlow getNetworkTypeIcon();

    StateFlow getNetworkTypeText();

    StateFlow getRoamId();

    int getSubscriptionId();

    StateFlow isRoaming();

    StateFlow isUnstackedUseTheme17();

    StateFlow isVisible();
}
