package com.oplus.systemui.statusbar.pipeline.battery.ui.model;

import android.graphics.drawable.Drawable;
import android.hidl.base.V1_0.DebugInfo$;
import androidx.compose.animation.core.MonoSpline$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OldHorizontal {
    private final String contentDescription;
    private final Drawable foregroundDrawable;
    public final int powerLevel;
    private final Drawable progressDrawable;
    public final int tintColor;

    public OldHorizontal(int i, Drawable drawable, Drawable drawable2, int i2, String str) {
        this.powerLevel = i;
        this.progressDrawable = drawable;
        this.foregroundDrawable = drawable2;
        this.tintColor = i2;
        this.contentDescription = str;
    }

    public final Drawable component2() {
        return this.progressDrawable;
    }

    public final Drawable component3() {
        return this.foregroundDrawable;
    }

    public final String component5() {
        return this.contentDescription;
    }

    public final OldHorizontal copy(int i, Drawable drawable, Drawable drawable2, int i2, String str) {
        return new OldHorizontal(i, drawable, drawable2, i2, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OldHorizontal)) {
            return false;
        }
        OldHorizontal oldHorizontal = (OldHorizontal) obj;
        return this.powerLevel == oldHorizontal.powerLevel && Intrinsics.areEqual(this.progressDrawable, oldHorizontal.progressDrawable) && Intrinsics.areEqual(this.foregroundDrawable, oldHorizontal.foregroundDrawable) && this.tintColor == oldHorizontal.tintColor && Intrinsics.areEqual(this.contentDescription, oldHorizontal.contentDescription);
    }

    public final String getContentDescription() {
        return this.contentDescription;
    }

    public final Drawable getForegroundDrawable() {
        return this.foregroundDrawable;
    }

    public final int getPowerLevel() {
        return this.powerLevel;
    }

    public final Drawable getProgressDrawable() {
        return this.progressDrawable;
    }

    public final int getTintColor() {
        return this.tintColor;
    }

    public final int hashCode() {
        int iHashCode = Integer.hashCode(this.powerLevel) * 31;
        Drawable drawable = this.progressDrawable;
        int iHashCode2 = (iHashCode + (drawable == null ? 0 : drawable.hashCode())) * 31;
        Drawable drawable2 = this.foregroundDrawable;
        return this.contentDescription.hashCode() + MonoSpline$.ExternalSyntheticOutline0.m(this.tintColor, (iHashCode2 + (drawable2 != null ? drawable2.hashCode() : 0)) * 31, 31);
    }

    public String toString() {
        Drawable drawable = this.progressDrawable;
        Drawable drawable2 = this.foregroundDrawable;
        String str = this.contentDescription;
        StringBuilder sb = new StringBuilder("OldHorizontal(powerLevel=");
        sb.append(this.powerLevel);
        sb.append(", progressDrawable=");
        sb.append(drawable);
        sb.append(", foregroundDrawable=");
        sb.append(drawable2);
        sb.append(", tintColor=");
        sb.append(this.tintColor);
        sb.append(", contentDescription=");
        return DebugInfo$.ExternalSyntheticOutline0.m(sb, str, ")");
    }
}
