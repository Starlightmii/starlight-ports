package com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model;

import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import com.android.keyguard.logging.KeyguardLogger$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TipModel {
    private final IconSource iconSource;
    private final String slot;
    private final String text;
    public final int type;

    public TipModel(int i, String str, IconSource iconSource, String str2) {
        this.type = i;
        this.text = str;
        this.iconSource = iconSource;
        this.slot = str2;
    }

    public final String component2() {
        return this.text;
    }

    public final IconSource component3() {
        return this.iconSource;
    }

    public final String component4() {
        return this.slot;
    }

    public final TipModel copy(int i, String str, IconSource iconSource, String str2) {
        return new TipModel(i, str, iconSource, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TipModel)) {
            return false;
        }
        TipModel tipModel = (TipModel) obj;
        return this.type == tipModel.type && Intrinsics.areEqual(this.text, tipModel.text) && Intrinsics.areEqual(this.iconSource, tipModel.iconSource) && Intrinsics.areEqual(this.slot, tipModel.slot);
    }

    public final IconSource getIconSource() {
        return this.iconSource;
    }

    public final String getSlot() {
        return this.slot;
    }

    public final String getText() {
        return this.text;
    }

    public final int getType() {
        return this.type;
    }

    public final int hashCode() {
        return this.slot.hashCode() + ((this.iconSource.hashCode() + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(Integer.hashCode(this.type) * 31, 31, this.text)) * 31);
    }

    public String toString() {
        String str = this.text;
        IconSource iconSource = this.iconSource;
        String str2 = this.slot;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(this.type, "TipModel(type=", ", text=", str, ", iconSource=");
        sbM.append(iconSource);
        sbM.append(", slot=");
        sbM.append(str2);
        sbM.append(")");
        return sbM.toString();
    }
}
