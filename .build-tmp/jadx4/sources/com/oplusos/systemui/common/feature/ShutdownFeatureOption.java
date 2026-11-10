package com.oplusos.systemui.common.feature;

import android.os.SystemProperties;
import com.oplus.keyguard.util.FeatureOption$isCompactWindowMode$2$;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ShutdownFeatureOption {
    public static final ShutdownFeatureOption INSTANCE = new ShutdownFeatureOption();
    private static final String LONG_PRESS_POWERKEY_SHUTDOWN = "oplus.software.long_press_powerkey_shutdown";
    private static final String OPLUS_SHUTDOWN_FINDER_SUPPORT = "ro.bluetooth.finder.supported";
    private static final String SHORT_PRESS_POWERKEY_SHUTDOWN = "oplus.software.short_press_powerkey_shutdown";
    private static final Lazy isBreenoAsVoiceAssistant$delegate;
    private static final Lazy isPressPowerButtonThreeSecondsToShutDownSupport$delegate;
    private static final Lazy isShutdownFinderSupport$delegate;

    static {
        final int i = 0;
        isBreenoAsVoiceAssistant$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.ShutdownFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.short_press_powerkey_shutdown");
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.long_press_powerkey_shutdown");
                    default:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.bluetooth.finder.supported", false));
                }
            }
        });
        final int i2 = 1;
        isPressPowerButtonThreeSecondsToShutDownSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.ShutdownFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.short_press_powerkey_shutdown");
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.long_press_powerkey_shutdown");
                    default:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.bluetooth.finder.supported", false));
                }
            }
        });
        final int i3 = 2;
        isShutdownFinderSupport$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.feature.ShutdownFeatureOption$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.short_press_powerkey_shutdown");
                    case 1:
                        return FeatureOption$isCompactWindowMode$2$.ExternalSyntheticOutline0.m("oplus.software.long_press_powerkey_shutdown");
                    default:
                        return Boolean.valueOf(SystemProperties.getBoolean("ro.bluetooth.finder.supported", false));
                }
            }
        });
    }

    public static boolean isBreenoAsVoiceAssistant() {
        return ((Boolean) isBreenoAsVoiceAssistant$delegate.getValue()).booleanValue();
    }

    public static boolean isPressPowerButtonThreeSecondsToShutDownSupport() {
        return ((Boolean) isPressPowerButtonThreeSecondsToShutDownSupport$delegate.getValue()).booleanValue();
    }

    public static boolean isShutdownFinderSupport() {
        return ((Boolean) isShutdownFinderSupport$delegate.getValue()).booleanValue();
    }
}
