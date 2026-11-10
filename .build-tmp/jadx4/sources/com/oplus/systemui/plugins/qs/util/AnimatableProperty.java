package com.oplus.systemui.plugins.qs.util;

import android.util.FloatProperty;
import android.util.Property;
import android.view.View;
import java.lang.invoke.VarHandle;
import java.util.function.BiConsumer;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class AnimatableProperty {
    public static final Companion Companion = new Companion();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX WARN: Type inference failed for: r0v1, types: [com.oplus.systemui.plugins.qs.util.AnimatableProperty$Companion$from$property$1] */
        public final AnimatableProperty from(final String str, final BiConsumer<View, Float> biConsumer, final Function<View, Float> function, int i, int i2, int i3) {
            ?? r0 = new FloatProperty(str) { // from class: com.oplus.systemui.plugins.qs.util.AnimatableProperty$Companion$from$property$1
                @Override // android.util.Property
                public final Float get(Object obj) {
                    return (Float) function.apply((View) obj);
                }

                @Override // android.util.FloatProperty
                public final void setValue(Object obj, float f) {
                    biConsumer.accept((View) obj, Float.valueOf(f));
                }
            };
            AnimatableProperty$Companion$from$1 animatableProperty$Companion$from$1 = new AnimatableProperty$Companion$from$1();
            animatableProperty$Companion$from$1.$startValueTag = i2;
            animatableProperty$Companion$from$1.$endValueTag = i3;
            animatableProperty$Companion$from$1.$animatorTag = i;
            animatableProperty$Companion$from$1.$property = r0;
            VarHandle.storeStoreFence();
            return animatableProperty$Companion$from$1;
        }
    }

    public abstract int getAnimationEndTag();

    public abstract int getAnimationStartTag();

    public abstract int getAnimatorTag();

    public abstract Property<View, Float> getProperty();
}
