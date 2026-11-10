package com.oplus.systemui.statusbar.seeding.anim;

import android.os.Bundle;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Arrays;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusBarAnimationData {
    public static final Companion Companion = new Companion();
    private final long[] durations;
    public final long id;
    public final boolean isLockscreen;
    private final StatusBarAnimationType type;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final StatusBarAnimationData fromBundle(Bundle bundle) {
            StatusBarAnimationType statusBarAnimationTypeValueOf;
            long[] longArray;
            try {
                long j = bundle.getLong("animationId");
                String string = bundle.getString("animationType");
                if (string != null && (statusBarAnimationTypeValueOf = StatusBarAnimationType.valueOf(string)) != null && (longArray = bundle.getLongArray("animationDurations")) != null) {
                    return new StatusBarAnimationData(j, statusBarAnimationTypeValueOf, longArray, bundle.getBoolean("isLockscreen", false));
                }
                return null;
            } catch (IllegalArgumentException e) {
                GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "fromBundle Exception occurred: ", e.getMessage(), LogUtil.TAG_STATUSBAR, "StatusBarAnimationData");
                return null;
            }
        }
    }

    public StatusBarAnimationData(long j, StatusBarAnimationType statusBarAnimationType, long[] jArr, boolean z) {
        this.id = j;
        this.type = statusBarAnimationType;
        this.durations = jArr;
        this.isLockscreen = z;
    }

    public final StatusBarAnimationType component2() {
        return this.type;
    }

    public final long[] component3() {
        return this.durations;
    }

    public final StatusBarAnimationData copy(long j, StatusBarAnimationType statusBarAnimationType, long[] jArr, boolean z) {
        return new StatusBarAnimationData(j, statusBarAnimationType, jArr, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!StatusBarAnimationData.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        StatusBarAnimationData statusBarAnimationData = (StatusBarAnimationData) obj;
        return this.id == statusBarAnimationData.id && this.type == statusBarAnimationData.type && Arrays.equals(this.durations, statusBarAnimationData.durations) && this.isLockscreen == statusBarAnimationData.isLockscreen;
    }

    public final long[] getDurations() {
        return this.durations;
    }

    public final long getId() {
        return this.id;
    }

    public final StatusBarAnimationType getType() {
        return this.type;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isLockscreen) + ((Arrays.hashCode(this.durations) + ((this.type.hashCode() + (Long.hashCode(this.id) * 31)) * 31)) * 31);
    }

    public String toString() {
        return "StatusBarAnimationData(id=" + this.id + ", type=" + this.type + ", durations=" + Arrays.toString(this.durations) + ", isLockscreen=" + this.isLockscreen + ")";
    }
}
