package com.oplus.systemui.notification.lockscreen.notification;

import androidx.constraintlayout.widget.ConstraintSet$;
import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleNotificationData extends CapsuleItemData {
    public static final String CAPSULE_NOTIF_KEY_PREFIX = "notification_capsule";
    public static final Companion Companion = new Companion();
    private final CapsuleNotificationContainerView capsuleNotificationContainerView;
    private final String entryKey;
    private final Integer iconColor;
    public final int orderPriority;
    public final boolean suppressAnimator;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CapsuleNotificationData(String str, CapsuleNotificationContainerView capsuleNotificationContainerView, Integer num, boolean z) {
        super(str, capsuleNotificationContainerView, !z);
        this.entryKey = str;
        this.capsuleNotificationContainerView = capsuleNotificationContainerView;
        this.iconColor = num;
        this.suppressAnimator = z;
        this.orderPriority = Integer.MAX_VALUE;
    }

    public final String component1() {
        return this.entryKey;
    }

    public final CapsuleNotificationContainerView component2() {
        return this.capsuleNotificationContainerView;
    }

    public final CapsuleNotificationData copy(String str, CapsuleNotificationContainerView capsuleNotificationContainerView, Integer num, boolean z) {
        return new CapsuleNotificationData(str, capsuleNotificationContainerView, num, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CapsuleNotificationData)) {
            return false;
        }
        CapsuleNotificationData capsuleNotificationData = (CapsuleNotificationData) obj;
        return Intrinsics.areEqual(this.entryKey, capsuleNotificationData.entryKey) && Intrinsics.areEqual(this.capsuleNotificationContainerView, capsuleNotificationData.capsuleNotificationContainerView) && Intrinsics.areEqual(this.iconColor, capsuleNotificationData.iconColor) && this.suppressAnimator == capsuleNotificationData.suppressAnimator;
    }

    public final CapsuleNotificationContainerView getCapsuleNotificationContainerView() {
        return this.capsuleNotificationContainerView;
    }

    public final String getEntryKey() {
        return this.entryKey;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData
    public int getOrderPriority() {
        return this.orderPriority;
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData
    public Integer getPrimaryColor() {
        return this.iconColor;
    }

    public final boolean getSuppressAnimator() {
        return this.suppressAnimator;
    }

    public final int hashCode() {
        int iHashCode = (this.capsuleNotificationContainerView.hashCode() + (this.entryKey.hashCode() * 31)) * 31;
        Integer num = this.iconColor;
        return Boolean.hashCode(this.suppressAnimator) + ((iHashCode + (num == null ? 0 : num.hashCode())) * 31);
    }

    public String toString() {
        String key = getKey();
        int orderPriority = getOrderPriority();
        CapsuleNotificationContainerView capsuleNotificationContainerView = this.capsuleNotificationContainerView;
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(orderPriority, "CapsuleNotificationData(key=", key, ", orderPriority=", ", capsuleNotificationContainerView=");
        sbM.append(capsuleNotificationContainerView);
        sbM.append(")");
        return sbM.toString();
    }

    public /* synthetic */ CapsuleNotificationData(String str, CapsuleNotificationContainerView capsuleNotificationContainerView, Integer num, boolean z, int i) {
        this(str, capsuleNotificationContainerView, (i & 4) != 0 ? null : num, (i & 8) != 0 ? false : z);
    }
}
