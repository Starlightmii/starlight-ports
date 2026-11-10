package com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.dynamicevent;

import android.graphics.drawable.Drawable;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import com.android.keyguard.logging.KeyguardLogger$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DynamicEvent {
    public int animType;
    private final String desc;
    private Drawable drawable;
    public final int eventType;
    public boolean isIconTint;
    private String pkgName;
    public final int showType;
    private final String text;

    public DynamicEvent(int i, String str, String str2, int i2, Drawable drawable, boolean z, int i3, String str3) {
        this.eventType = i;
        this.text = str;
        this.desc = str2;
        this.showType = i2;
        this.drawable = drawable;
        this.isIconTint = z;
        this.animType = i3;
        this.pkgName = str3;
    }

    public final String component2() {
        return this.text;
    }

    public final String component3() {
        return this.desc;
    }

    public final Drawable component5() {
        return this.drawable;
    }

    public final String component8() {
        return this.pkgName;
    }

    public final DynamicEvent copy(int i, String str, String str2, int i2, Drawable drawable, boolean z, int i3, String str3) {
        return new DynamicEvent(i, str, str2, i2, drawable, z, i3, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DynamicEvent)) {
            return false;
        }
        DynamicEvent dynamicEvent = (DynamicEvent) obj;
        return this.eventType == dynamicEvent.eventType && Intrinsics.areEqual(this.text, dynamicEvent.text) && Intrinsics.areEqual(this.desc, dynamicEvent.desc) && this.showType == dynamicEvent.showType && Intrinsics.areEqual(this.drawable, dynamicEvent.drawable) && this.isIconTint == dynamicEvent.isIconTint && this.animType == dynamicEvent.animType && Intrinsics.areEqual(this.pkgName, dynamicEvent.pkgName);
    }

    public final int getAnimType() {
        return this.animType;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final Drawable getDrawable() {
        return this.drawable;
    }

    public final int getEventType() {
        return this.eventType;
    }

    public final String getPkgName() {
        return this.pkgName;
    }

    public final int getShowType() {
        return this.showType;
    }

    public final String getText() {
        return this.text;
    }

    public final int hashCode() {
        int iM = MonoSpline$.ExternalSyntheticOutline0.m(this.animType, ChangeSize$.ExternalSyntheticOutline0.m((this.drawable.hashCode() + MonoSpline$.ExternalSyntheticOutline0.m(this.showType, PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(Integer.hashCode(this.eventType) * 31, 31, this.text), 31, this.desc), 31)) * 31, 31, this.isIconTint), 31);
        String str = this.pkgName;
        return iM + (str == null ? 0 : str.hashCode());
    }

    public final void setAnimType(int i) {
        this.animType = i;
    }

    public final void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public final void setIconTint(boolean z) {
        this.isIconTint = z;
    }

    public final void setPkgName(String str) {
        this.pkgName = str;
    }

    public String toString() {
        String str = this.text;
        String str2 = this.desc;
        Drawable drawable = this.drawable;
        boolean z = this.isIconTint;
        int i = this.animType;
        String str3 = this.pkgName;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(this.eventType, "DynamicEvent(eventType=", ", text=", str, ", desc=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(this.showType, str2, ", showType=", ", drawable=", sbM);
        sbM.append(drawable);
        sbM.append(", isIconTint=");
        sbM.append(z);
        sbM.append(", animType=");
        sbM.append(i);
        sbM.append(", pkgName=");
        sbM.append(str3);
        sbM.append(")");
        return sbM.toString();
    }
}
