package com.oplus.systemui.statusbar.notification.stack;

import com.android.systemui.statusbar.notification.AnimatableProperty;
import com.android.systemui.statusbar.notification.OplusPhysicsProperty;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusExpandableViewStateExImplKt {
    public static final OplusPhysicsProperty toPhysicsProperty(AnimatableProperty animatableProperty, float f) {
        return OplusPhysicsPropertyCache.INSTANCE.getOrCreate(animatableProperty.getAnimatorTag(), animatableProperty.getProperty(), f);
    }
}
