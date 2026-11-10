package com.oplusos.systemui.common.view;

import android.graphics.RenderNode;
import android.view.View;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewExtKt$addPositionUpdateListener$1$positionUpdateListener$1 implements RenderNode.PositionUpdateListener {
    public /* synthetic */ FrameInfo $frameInfo;
    public /* synthetic */ Function1 $positionUpdated;
    public /* synthetic */ View $this_addPositionUpdateListener;

    public final void positionChanged(long j, int i, int i2, int i3, int i4) {
        View view = this.$this_addPositionUpdateListener;
        FrameInfo frameInfo = this.$frameInfo;
        Function1 function1 = this.$positionUpdated;
        ViewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionChanged$1 viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionChanged$1 = new ViewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionChanged$1();
        viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionChanged$1.$frameInfo = frameInfo;
        viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionChanged$1.$frameNumber = j;
        viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionChanged$1.$left = i;
        viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionChanged$1.$top = i2;
        viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionChanged$1.$right = i3;
        viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionChanged$1.$bottom = i4;
        viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionChanged$1.$positionUpdated = function1;
        VarHandle.storeStoreFence();
        view.post(viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionChanged$1);
    }

    public final void positionLost(long j) {
        View view = this.$this_addPositionUpdateListener;
        FrameInfo frameInfo = this.$frameInfo;
        Function1 function1 = this.$positionUpdated;
        ViewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionLost$1 viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionLost$1 = new ViewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionLost$1();
        viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionLost$1.$frameInfo = frameInfo;
        viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionLost$1.$frameNumber = j;
        viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionLost$1.$positionUpdated = function1;
        VarHandle.storeStoreFence();
        view.post(viewExtKt$addPositionUpdateListener$1$positionUpdateListener$1$positionLost$1);
    }
}
