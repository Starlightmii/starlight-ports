package com.oplus.systemui.statusbar.notification.livealert.data.model;

import android.service.notification.StatusBarNotification;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SbnModel {
    public final int entryFlag;
    private final StatusBarNotification sbn;

    public SbnModel(StatusBarNotification statusBarNotification, int i) {
        this.sbn = statusBarNotification;
        this.entryFlag = i;
    }

    public final StatusBarNotification component1() {
        return this.sbn;
    }

    public final SbnModel copy(StatusBarNotification statusBarNotification, int i) {
        return new SbnModel(statusBarNotification, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SbnModel)) {
            return false;
        }
        SbnModel sbnModel = (SbnModel) obj;
        return Intrinsics.areEqual(this.sbn, sbnModel.sbn) && this.entryFlag == sbnModel.entryFlag;
    }

    public final int getEntryFlag() {
        return this.entryFlag;
    }

    public final StatusBarNotification getSbn() {
        return this.sbn;
    }

    public final int hashCode() {
        return Integer.hashCode(this.entryFlag) + (this.sbn.hashCode() * 31);
    }

    public String toString() {
        return "SbnModel(sbn=" + this.sbn + ", entryFlag=" + this.entryFlag + ")";
    }
}
