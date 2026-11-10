package com.oplusos.systemui.common.blurability.screenshot;

import android.os.IBinder;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CaptureParams {
    public final float compressRatioOfScreenSize;
    private final IBinder[] excludedWindowTokens;
    private final Function0 requireDefaultDrawable;

    public CaptureParams(float f, IBinder[] iBinderArr, Function0 function0) {
        this.compressRatioOfScreenSize = f;
        this.excludedWindowTokens = iBinderArr;
        this.requireDefaultDrawable = function0;
    }

    public final float getCompressRatioOfScreenSize() {
        return this.compressRatioOfScreenSize;
    }

    public final IBinder[] getExcludedWindowTokens() {
        return this.excludedWindowTokens;
    }

    public final Function0 getRequireDefaultDrawable() {
        return this.requireDefaultDrawable;
    }
}
