package com.oplusos.systemui.common.view;

import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionLost$1 implements Runnable {
    public /* synthetic */ FrameInfo $frameInfo;
    public /* synthetic */ long $frameNumber;
    public /* synthetic */ Function1 $positionUpdated;

    @Override // java.lang.Runnable
    public final void run() {
        this.$frameInfo.setFrameNumber(this.$frameNumber);
        this.$frameInfo.getRect().setEmpty();
        this.$positionUpdated.invoke(this.$frameInfo);
    }
}
