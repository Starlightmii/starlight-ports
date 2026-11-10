package com.oplus.systemui.plugins.qs.util;

import android.util.Property;
import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AnimatableProperty$Companion$from$1 extends AnimatableProperty {
    public /* synthetic */ int $animatorTag;
    public /* synthetic */ int $endValueTag;
    public /* synthetic */ AnimatableProperty$Companion$from$property$1 $property;
    public /* synthetic */ int $startValueTag;

    @Override // com.oplus.systemui.plugins.qs.util.AnimatableProperty
    public int getAnimationEndTag() {
        return this.$endValueTag;
    }

    @Override // com.oplus.systemui.plugins.qs.util.AnimatableProperty
    public int getAnimationStartTag() {
        return this.$startValueTag;
    }

    @Override // com.oplus.systemui.plugins.qs.util.AnimatableProperty
    public int getAnimatorTag() {
        return this.$animatorTag;
    }

    @Override // com.oplus.systemui.plugins.qs.util.AnimatableProperty
    public Property<View, Float> getProperty() {
        return this.$property;
    }
}
