package com.oplus.systemui.plugins.qs.customize.view.viewholder.preview;

import android.graphics.drawable.Drawable;
import com.android.systemui.accessibility.keygesture.shared.model.KeyGestureConfirmInfo$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ResourcePreviewData implements BasePreviewData {
    private final Drawable drawable;
    private final CharSequence label;
    private final CharSequence secondaryLabel;
    private final CharSequence spec;

    public ResourcePreviewData(Drawable drawable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        this.drawable = drawable;
        this.label = charSequence;
        this.spec = charSequence2;
        this.secondaryLabel = charSequence3;
    }

    public final Drawable component1() {
        return this.drawable;
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

    public final ResourcePreviewData copy(Drawable drawable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return new ResourcePreviewData(drawable, charSequence, charSequence2, charSequence3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResourcePreviewData)) {
            return false;
        }
        ResourcePreviewData resourcePreviewData = (ResourcePreviewData) obj;
        return Intrinsics.areEqual(this.drawable, resourcePreviewData.drawable) && Intrinsics.areEqual(this.label, resourcePreviewData.label) && Intrinsics.areEqual(this.spec, resourcePreviewData.spec) && Intrinsics.areEqual(this.secondaryLabel, resourcePreviewData.secondaryLabel);
    }

    public final Drawable getDrawable() {
        return this.drawable;
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
        Drawable drawable = this.drawable;
        return this.secondaryLabel.hashCode() + KeyGestureConfirmInfo$.ExternalSyntheticOutline0.m(this.spec, KeyGestureConfirmInfo$.ExternalSyntheticOutline0.m(this.label, (drawable == null ? 0 : drawable.hashCode()) * 31, 31), 31);
    }

    public String toString() {
        return "ResourcePreviewData(drawable=" + this.drawable + ", label=" + ((Object) this.label) + ", spec=" + ((Object) this.spec) + ", secondaryLabel=" + ((Object) this.secondaryLabel) + ")";
    }

    public /* synthetic */ ResourcePreviewData(Drawable drawable, CharSequence charSequence, CharSequence charSequence2) {
        this(drawable, charSequence, charSequence2, "");
    }
}
