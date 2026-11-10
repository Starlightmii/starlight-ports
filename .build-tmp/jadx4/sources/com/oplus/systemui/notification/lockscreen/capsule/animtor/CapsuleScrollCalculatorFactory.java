package com.oplus.systemui.notification.lockscreen.capsule.animtor;

import android.content.Context;
import com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleMetaBallScrollCalculator;
import com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleMetaBallUtils;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleScrollCalculatorFactory {
    public static final CapsuleScrollCalculatorFactory INSTANCE = new CapsuleScrollCalculatorFactory();
    private static final Lazy defaultCalculator$delegate;
    private static final Lazy metaBallCalculator$delegate;

    static {
        final int i = 0;
        metaBallCalculator$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculatorFactory$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return new CapsuleMetaBallScrollCalculator();
                    default:
                        return new CapsuleDefaultScrollCalculator();
                }
            }
        });
        final int i2 = 1;
        defaultCalculator$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.capsule.animtor.CapsuleScrollCalculatorFactory$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return new CapsuleMetaBallScrollCalculator();
                    default:
                        return new CapsuleDefaultScrollCalculator();
                }
            }
        });
    }

    private final CapsuleDefaultScrollCalculator getDefaultCalculator() {
        return (CapsuleDefaultScrollCalculator) defaultCalculator$delegate.getValue();
    }

    private final CapsuleMetaBallScrollCalculator getMetaBallCalculator() {
        return (CapsuleMetaBallScrollCalculator) metaBallCalculator$delegate.getValue();
    }

    public final CapsuleScrollCalculator getCalculator(Context context) {
        return context == null ? false : CapsuleMetaBallUtils.isSupportMetaBall(context) ? getMetaBallCalculator() : getDefaultCalculator();
    }
}
