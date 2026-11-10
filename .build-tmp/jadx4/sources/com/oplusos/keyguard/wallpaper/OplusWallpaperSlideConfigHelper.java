package com.oplusos.keyguard.wallpaper;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusWallpaperSlideConfigHelper {
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new OplusWallpaperSlideConfigHelper$$ExternalSyntheticLambda0());
    public double customAngleInRadians;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static /* synthetic */ void getInstance$annotations() {
        }

        public final OplusWallpaperSlideConfigHelper getInstance() {
            return (OplusWallpaperSlideConfigHelper) OplusWallpaperSlideConfigHelper.instance$delegate.getValue();
        }
    }

    public static final OplusWallpaperSlideConfigHelper getInstance() {
        return Companion.getInstance();
    }

    public final double getCustomAngleInRadians() {
        return this.customAngleInRadians;
    }
}
