package com.oplus.systemui.statusbar.notification.collection;

import android.graphics.drawable.Drawable;
import com.android.keyguard.logging.KeyguardLogger$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPackage {
    private String label;
    private Drawable packageIcon;
    public int targetSdk;

    public NotificationPackage(int i, String str, Drawable drawable) {
        this.targetSdk = i;
        this.label = str;
        this.packageIcon = drawable;
    }

    public final String component2() {
        return this.label;
    }

    public final Drawable component3() {
        return this.packageIcon;
    }

    public final NotificationPackage copy(int i, String str, Drawable drawable) {
        return new NotificationPackage(i, str, drawable);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotificationPackage)) {
            return false;
        }
        NotificationPackage notificationPackage = (NotificationPackage) obj;
        return this.targetSdk == notificationPackage.targetSdk && Intrinsics.areEqual(this.label, notificationPackage.label) && Intrinsics.areEqual(this.packageIcon, notificationPackage.packageIcon);
    }

    public final String getLabel() {
        return this.label;
    }

    public final Drawable getPackageIcon() {
        return this.packageIcon;
    }

    public final int getTargetSdk() {
        return this.targetSdk;
    }

    public final int hashCode() {
        int iHashCode = Integer.hashCode(this.targetSdk) * 31;
        String str = this.label;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Drawable drawable = this.packageIcon;
        return iHashCode2 + (drawable != null ? drawable.hashCode() : 0);
    }

    public final void setLabel(String str) {
        this.label = str;
    }

    public final void setPackageIcon(Drawable drawable) {
        this.packageIcon = drawable;
    }

    public final void setTargetSdk(int i) {
        this.targetSdk = i;
    }

    public String toString() {
        int i = this.targetSdk;
        String str = this.label;
        Drawable drawable = this.packageIcon;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(i, "NotificationPackage(targetSdk=", ", label=", str, ", packageIcon=");
        sbM.append(drawable);
        sbM.append(")");
        return sbM.toString();
    }
}
