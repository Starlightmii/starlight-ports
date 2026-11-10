package com.oplus.systemui.media.seedling.utils;

import com.oplus.wrapper.os.SystemProperties;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FeatureOption {
    private static final String FEATURE_FLUID_CONFIG = "com.oplus.mediacontroller.fluidConfig";
    private static final String TAG = "FeatureOption";
    public static final FeatureOption INSTANCE = new FeatureOption();
    private static final Lazy fluidConfigFeature$delegate = LazyKt__LazyJVMKt.lazy(new FeatureOption$$ExternalSyntheticLambda0());
    public static boolean isMediaUMSEnabled = SystemProperties.getBoolean("persist.debug.mc.seedling", false);

    public final Pair<Boolean, Integer> getFluidConfigFeature() {
        return (Pair) fluidConfigFeature$delegate.getValue();
    }

    public final void setMediaUMSEnabled(boolean z) {
        isMediaUMSEnabled = z;
    }
}
