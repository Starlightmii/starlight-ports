package com.oplus.systemui.statusbar.corner;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusCutoutDecorProviderImplKt {
    public static final int access$toLayoutGravity(int i, int i2) {
        if (i2 == 0) {
            if (i == 0) {
                return 19;
            }
            if (i != 1) {
                return i != 2 ? 81 : 21;
            }
            return 49;
        }
        if (i2 == 1) {
            if (i == 0) {
                return 81;
            }
            if (i != 1) {
                return i != 2 ? 21 : 49;
            }
            return 19;
        }
        if (i2 != 3) {
            if (i == 0) {
                return 21;
            }
            if (i != 1) {
                return i != 2 ? 49 : 19;
            }
            return 81;
        }
        if (i == 0) {
            return 49;
        }
        if (i != 1) {
            return i != 2 ? 19 : 81;
        }
        return 21;
    }
}
