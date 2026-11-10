package com.oplus.systemui.notification.lockscreen.capsule.modle;

import android.view.View;
import com.oplus.systemui.notification.lockscreen.config.CapsuleDebug;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class CapsuleItemData {
    public static final Companion Companion = new Companion();
    public final boolean animAdd;
    private final View contentView;
    private final String key;
    private final Integer primaryColor = -1;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CapsuleItemData(String str, View view, boolean z) {
        this.key = str;
        this.contentView = view;
        this.animAdd = z;
    }

    public final boolean getAnimAdd() {
        return this.animAdd;
    }

    public int getCardLevel() {
        return -1;
    }

    public final View getContentView() {
        return this.contentView;
    }

    public final String getKey() {
        return this.key;
    }

    public abstract int getOrderPriority();

    public long getPinDuration() {
        Long capsulePinDuration = CapsuleDebug.INSTANCE.getCapsulePinDuration(3000L);
        if (capsulePinDuration != null) {
            return capsulePinDuration.longValue();
        }
        return 3000L;
    }

    public long getPinExtensionDuration() {
        Long capsulePinExtensionDuration = CapsuleDebug.INSTANCE.getCapsulePinExtensionDuration(2000L);
        if (capsulePinExtensionDuration != null) {
            return capsulePinExtensionDuration.longValue();
        }
        return 2000L;
    }

    public Integer getPrimaryColor() {
        return this.primaryColor;
    }
}
