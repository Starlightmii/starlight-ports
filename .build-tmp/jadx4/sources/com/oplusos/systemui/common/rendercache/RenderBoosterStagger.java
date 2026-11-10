package com.oplusos.systemui.common.rendercache;

import android.view.Choreographer;
import android.view.ViewGroup;
import java.lang.invoke.VarHandle;
import java.util.HashMap;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RenderBoosterStagger {
    public static final RenderBoosterStagger INSTANCE = new RenderBoosterStagger();
    private static final HashMap<ViewGroup, StaggerTask> runningTasks = new HashMap<>();
    private static final Choreographer choreographer = Choreographer.getInstance();

    public static Unit $r8$lambda$KY_0JMW4pm5ArOVXryANSxdFFss(ViewGroup viewGroup, StaggerTask staggerTask, Function0 function0) {
        HashMap<ViewGroup, StaggerTask> map = runningTasks;
        if (map.get(viewGroup) == staggerTask) {
            map.remove(viewGroup);
            if (function0 != null) {
                function0.invoke();
            }
        }
        return Unit.INSTANCE;
    }

    public final void cancel(ViewGroup viewGroup) {
        StaggerTask staggerTaskRemove = runningTasks.remove(viewGroup);
        if (staggerTaskRemove != null) {
            staggerTaskRemove.stop();
        }
    }

    public final void schedule(ViewGroup viewGroup, List<RenderBooster> list, int i, boolean z, RenderBoosterGroupStrategy renderBoosterGroupStrategy, Function0 function0) {
        if (list.isEmpty()) {
            return;
        }
        if (renderBoosterGroupStrategy == null) {
            renderBoosterGroupStrategy = RenderBoosterDeviceTier.Companion.resolveDeviceTier(viewGroup.getContext()).getGroupStrategy();
        }
        StaggerTask staggerTask = new StaggerTask(choreographer, ((ChunkGroupStrategy) renderBoosterGroupStrategy).partition(list), i, z);
        RenderBoosterStagger$$ExternalSyntheticLambda0 renderBoosterStagger$$ExternalSyntheticLambda0 = new RenderBoosterStagger$$ExternalSyntheticLambda0();
        renderBoosterStagger$$ExternalSyntheticLambda0.f$0 = viewGroup;
        renderBoosterStagger$$ExternalSyntheticLambda0.f$1 = staggerTask;
        renderBoosterStagger$$ExternalSyntheticLambda0.f$2 = function0;
        VarHandle.storeStoreFence();
        staggerTask.setOnComplete(renderBoosterStagger$$ExternalSyntheticLambda0);
        runningTasks.put(viewGroup, staggerTask);
        staggerTask.start();
    }
}
