package com.oplus.systemui.plugins.qs.customize.view.viewholder.preview;

import com.android.systemui.accessibility.keygesture.shared.model.KeyGestureConfirmInfo$;
import com.android.systemui.plugins.qs.QSTile;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TilePreviewData implements BasePreviewData {
    private final QSTile.Icon icon;
    private final CharSequence label;
    private final CharSequence secondaryLabel;
    private final CharSequence spec;

    public TilePreviewData(QSTile.Icon icon, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        this.icon = icon;
        this.label = charSequence;
        this.spec = charSequence2;
        this.secondaryLabel = charSequence3;
    }

    public final QSTile.Icon component1() {
        return this.icon;
    }

    public final CharSequence component2() {
        return this.label;
    }

    public final CharSequence component3() {
        return this.spec;
    }

    public final CharSequence component4() {
        return this.secondaryLabel;
    }

    public final TilePreviewData copy(QSTile.Icon icon, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return new TilePreviewData(icon, charSequence, charSequence2, charSequence3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TilePreviewData)) {
            return false;
        }
        TilePreviewData tilePreviewData = (TilePreviewData) obj;
        return Intrinsics.areEqual(this.icon, tilePreviewData.icon) && Intrinsics.areEqual(this.label, tilePreviewData.label) && Intrinsics.areEqual(this.spec, tilePreviewData.spec) && Intrinsics.areEqual(this.secondaryLabel, tilePreviewData.secondaryLabel);
    }

    public final QSTile.Icon getIcon() {
        return this.icon;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.BasePreviewData
    public CharSequence getLabel() {
        return this.label;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.BasePreviewData
    public CharSequence getSecondaryLabel() {
        return this.secondaryLabel;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.BasePreviewData
    public CharSequence getSpec() {
        return this.spec;
    }

    public final int hashCode() {
        QSTile.Icon icon = this.icon;
        return this.secondaryLabel.hashCode() + KeyGestureConfirmInfo$.ExternalSyntheticOutline0.m(this.spec, KeyGestureConfirmInfo$.ExternalSyntheticOutline0.m(this.label, (icon == null ? 0 : icon.hashCode()) * 31, 31), 31);
    }

    public String toString() {
        return "TilePreviewData(icon=" + this.icon + ", label=" + ((Object) this.label) + ", spec=" + ((Object) this.spec) + ", secondaryLabel=" + ((Object) this.secondaryLabel) + ")";
    }
}
