package com.oplusos.systemui.common.rendercache;

import android.view.Choreographer;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StaggerTask {
    private final Choreographer choreographer;
    private final Choreographer.FrameCallback frameCallback;
    public int frameIndex;
    private final List<List<RenderBooster>> groups;
    public final int invalidateType;
    private Function0 onComplete;
    public final boolean onlyScanVisible;

    /* JADX WARN: Multi-variable type inference failed */
    public StaggerTask(Choreographer choreographer, List<? extends List<RenderBooster>> list, int i, boolean z) {
        this.choreographer = choreographer;
        this.groups = list;
        this.invalidateType = i;
        this.onlyScanVisible = z;
        StaggerTask$frameCallback$1 staggerTask$frameCallback$1 = new StaggerTask$frameCallback$1();
        staggerTask$frameCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.frameCallback = staggerTask$frameCallback$1;
    }

    public final Function0 getOnComplete() {
        return this.onComplete;
    }

    public final void notifyComplete() {
        Function0 function0 = this.onComplete;
        if (function0 != null) {
            function0.invoke();
        }
        this.onComplete = null;
    }

    public final void requestBoosterForGroup(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            RenderBooster renderBooster = (RenderBooster) it.next();
            if (renderBooster.getTargetView().isAttachedToWindow()) {
                RenderBooster.requestBooster$default(renderBooster, this.invalidateType, this.onlyScanVisible, 4);
            }
        }
    }

    public final void setOnComplete(Function0 function0) {
        this.onComplete = function0;
    }

    public final void start() {
        if (this.groups.size() > 1) {
            this.choreographer.postFrameCallback(this.frameCallback);
            return;
        }
        List list = (List) CollectionsKt___CollectionsKt.firstOrNull(this.groups);
        if (list != null) {
            requestBoosterForGroup(list);
        }
        notifyComplete();
    }

    public final void stop() {
        this.choreographer.removeFrameCallback(this.frameCallback);
    }
}
