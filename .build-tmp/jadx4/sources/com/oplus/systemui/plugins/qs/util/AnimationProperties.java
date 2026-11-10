package com.oplus.systemui.plugins.qs.util;

import android.util.ArrayMap;
import android.util.Property;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AnimationProperties {
    public long delay;
    public long duration;
    public Consumer mAnimationCancelAction;
    public Consumer mAnimationEndAction;
    public ArrayMap mInterpolatorMap;

    public AnimationProperties setAnimationCancelAction(Consumer<Property> consumer) {
        this.mAnimationCancelAction = consumer;
        return this;
    }

    public AnimationProperties setAnimationEndAction(Consumer<Property> consumer) {
        this.mAnimationEndAction = consumer;
        return this;
    }

    public AnimationProperties setDelay(long j) {
        this.delay = j;
        return this;
    }

    public AnimationProperties setDuration(long j) {
        this.duration = j;
        return this;
    }
}
