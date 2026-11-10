package com.oplusos.systemui.common.view;

import android.os.Trace;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionChanged$1 implements Runnable {
    public /* synthetic */ int $bottom;
    public /* synthetic */ FrameInfo $frameInfo;
    public /* synthetic */ long $frameNumber;
    public /* synthetic */ int $left;
    public /* synthetic */ Function1 $positionUpdated;
    public /* synthetic */ int $right;
    public /* synthetic */ int $top;

    @Override // java.lang.Runnable
    public final void run() {
        Trace.traceBegin(8L, "ViewExt-positionChanged");
        this.$frameInfo.setFrameNumber(this.$frameNumber);
        this.$frameInfo.getRect().set(this.$left, this.$top, this.$right, this.$bottom);
        this.$positionUpdated.invoke(this.$frameInfo);
        Trace.traceEnd(8L);
    }
}
