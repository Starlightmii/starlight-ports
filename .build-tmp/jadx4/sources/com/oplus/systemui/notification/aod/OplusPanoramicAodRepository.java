package com.oplus.systemui.notification.aod;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusPanoramicAodRepository {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface PanoramicAodProgressChangeListener {
        void onPanoramicAodStateChanged(boolean z, boolean z2);

        void onProgressChanged(float f);
    }

    void addCallback(PanoramicAodProgressChangeListener panoramicAodProgressChangeListener);

    float getPanoramicAodProgress();

    boolean getShouldUpdateUi();

    boolean getSwitchAodEnable();

    void removeCallback(PanoramicAodProgressChangeListener panoramicAodProgressChangeListener);
}
