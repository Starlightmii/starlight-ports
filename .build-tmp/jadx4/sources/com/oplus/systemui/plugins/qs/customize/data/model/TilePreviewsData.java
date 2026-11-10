package com.oplus.systemui.plugins.qs.customize.data.model;

import android.graphics.drawable.Drawable;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.plugins.qs.QSTile;
import com.oplusos.systemui.common.model.SpanSize;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TilePreviewsData {
    private final SpanSize defaultSize;
    private Drawable drawable;
    private QSTile.Icon icon;
    private String label;
    private String secondaryLabel;
    private final String tileSpec;

    public /* synthetic */ TilePreviewsData(String str, String str2, QSTile.Icon icon, SpanSize spanSize, String str3, int i) {
        this(str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? null : icon, (Drawable) null, spanSize, (i & 32) != 0 ? "" : str3);
    }

    public final String component1() {
        return this.tileSpec;
    }

    public final String component2() {
        return this.label;
    }

    public final QSTile.Icon component3() {
        return this.icon;
    }

    public final Drawable component4() {
        return this.drawable;
    }

    public final SpanSize component5() {
        return this.defaultSize;
    }

    public final String component6() {
        return this.secondaryLabel;
    }

    public final TilePreviewsData copy(String str, String str2, QSTile.Icon icon, Drawable drawable, SpanSize spanSize, String str3) {
        return new TilePreviewsData(str, str2, icon, drawable, spanSize, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TilePreviewsData)) {
            return false;
        }
        TilePreviewsData tilePreviewsData = (TilePreviewsData) obj;
        return Intrinsics.areEqual(this.tileSpec, tilePreviewsData.tileSpec) && Intrinsics.areEqual(this.label, tilePreviewsData.label) && Intrinsics.areEqual(this.icon, tilePreviewsData.icon) && Intrinsics.areEqual(this.drawable, tilePreviewsData.drawable) && Intrinsics.areEqual(this.defaultSize, tilePreviewsData.defaultSize) && Intrinsics.areEqual(this.secondaryLabel, tilePreviewsData.secondaryLabel);
    }

    public final SpanSize getDefaultSize() {
        return this.defaultSize;
    }

    public final Drawable getDrawable() {
        return this.drawable;
    }

    public final QSTile.Icon getIcon() {
        return this.icon;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getSecondaryLabel() {
        return this.secondaryLabel;
    }

    public final String getTileSpec() {
        return this.tileSpec;
    }

    public final int hashCode() {
        int iM = PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(this.tileSpec.hashCode() * 31, 31, this.label);
        QSTile.Icon icon = this.icon;
        int iHashCode = (iM + (icon == null ? 0 : icon.hashCode())) * 31;
        Drawable drawable = this.drawable;
        int iHashCode2 = drawable != null ? drawable.hashCode() : 0;
        return this.secondaryLabel.hashCode() + ((this.defaultSize.hashCode() + ((iHashCode + iHashCode2) * 31)) * 31);
    }

    public final void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public final void setIcon(QSTile.Icon icon) {
        this.icon = icon;
    }

    public final void setLabel(String str) {
        this.label = str;
    }

    public final void setSecondaryLabel(String str) {
        this.secondaryLabel = str;
    }

    public String toString() {
        String str = this.tileSpec;
        String str2 = this.label;
        QSTile.Icon icon = this.icon;
        Drawable drawable = this.drawable;
        SpanSize spanSize = this.defaultSize;
        String str3 = this.secondaryLabel;
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("TilePreviewsData(tileSpec=", str, ", label=", str2, ", icon=");
        sbM.append(icon);
        sbM.append(", drawable=");
        sbM.append(drawable);
        sbM.append(", defaultSize=");
        sbM.append(spanSize);
        sbM.append(", secondaryLabel=");
        sbM.append(str3);
        sbM.append(")");
        return sbM.toString();
    }

    public TilePreviewsData(String str, String str2, QSTile.Icon icon, Drawable drawable, SpanSize spanSize, String str3) {
        this.tileSpec = str;
        this.label = str2;
        this.icon = icon;
        this.drawable = drawable;
        this.defaultSize = spanSize;
        this.secondaryLabel = str3;
    }
}
