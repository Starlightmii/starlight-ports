package com.oplusos.systemui.common.rendercache;

import android.view.Choreographer;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StaggerTask$frameCallback$1 implements Choreographer.FrameCallback {
    public /* synthetic */ StaggerTask this$0;

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j) {
        StaggerTask staggerTask = this.this$0;
        int i = staggerTask.frameIndex;
        int size = staggerTask.groups.size();
        StaggerTask staggerTask2 = this.this$0;
        if (i >= size) {
            staggerTask2.notifyComplete();
            return;
        }
        staggerTask2.requestBoosterForGroup((List) staggerTask2.groups.get(this.this$0.frameIndex));
        StaggerTask staggerTask3 = this.this$0;
        int i2 = staggerTask3.frameIndex + 1;
        staggerTask3.frameIndex = i2;
        int size2 = staggerTask3.groups.size();
        StaggerTask staggerTask4 = this.this$0;
        if (i2 < size2) {
            staggerTask4.choreographer.postFrameCallback(this);
        } else {
            staggerTask4.notifyComplete();
        }
    }
}
