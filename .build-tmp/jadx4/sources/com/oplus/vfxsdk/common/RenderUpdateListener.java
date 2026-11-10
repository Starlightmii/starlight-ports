package com.oplus.vfxsdk.common;

import android.os.Trace;
import java.lang.invoke.VarHandle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RenderUpdateListener {
    private final Function0 flush;
    private final String TAG = "VFX:RenderUpdateListener";
    private final CopyOnWriteArrayList<Animator> listeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Animator> playingAnim = new CopyOnWriteArrayList<>();
    private final ConcurrentHashMap<String, AnimEndListener> animEndListenerMap = new ConcurrentHashMap<>();

    public RenderUpdateListener(Function0 function0) {
        this.flush = function0;
    }

    public final void addUpdate(Animator animator) {
        if (this.listeners.contains(animator)) {
            return;
        }
        String name = animator.getData().getName();
        this.listeners.add(animator);
        this.playingAnim.add(animator);
        if (this.animEndListenerMap.containsKey(name)) {
            return;
        }
        RenderUpdateListener$addUpdate$animEndListener$1 renderUpdateListener$addUpdate$animEndListener$1 = new RenderUpdateListener$addUpdate$animEndListener$1();
        renderUpdateListener$addUpdate$animEndListener$1.this$0 = this;
        renderUpdateListener$addUpdate$animEndListener$1.$listener = animator;
        VarHandle.storeStoreFence();
        this.animEndListenerMap.put(name, renderUpdateListener$addUpdate$animEndListener$1);
        animator.addAnimEndListener(renderUpdateListener$addUpdate$animEndListener$1);
    }

    public final ConcurrentHashMap<String, AnimEndListener> getAnimEndListenerMap() {
        return this.animEndListenerMap;
    }

    public final Function0 getFlush() {
        return this.flush;
    }

    public final CopyOnWriteArrayList<Animator> getPlayingAnim() {
        return this.playingAnim;
    }

    public final void onUpdate(double d) {
        if (this.playingAnim.isEmpty()) {
            return;
        }
        Trace.beginSection("Animator.onUpdate");
        for (Animator animator : this.listeners) {
            if (animator.isPlay()) {
                animator.onUpdate(d);
            }
        }
        Trace.endSection();
        Trace.beginSection("Animator.flush");
        this.flush.invoke();
        CopyOnWriteArrayList<Animator> copyOnWriteArrayList = this.listeners;
        AnonymousClass2 anonymousClass2 = new Function1() { // from class: com.oplus.vfxsdk.common.RenderUpdateListener.onUpdate.2
            public final Boolean invoke(Animator animator2) {
                return Boolean.valueOf(!animator2.isPlay());
            }
        };
        RenderUpdateListener$$ExternalSyntheticLambda0 renderUpdateListener$$ExternalSyntheticLambda0 = new RenderUpdateListener$$ExternalSyntheticLambda0();
        renderUpdateListener$$ExternalSyntheticLambda0.f$0 = anonymousClass2;
        VarHandle.storeStoreFence();
        copyOnWriteArrayList.removeIf(renderUpdateListener$$ExternalSyntheticLambda0);
        Trace.endSection();
    }

    public final void removeUpdate(Animator animator) {
        this.listeners.remove(animator);
        AnimEndListener animEndListener = this.animEndListenerMap.get(animator.getData().getName());
        if (animEndListener != null) {
            animator.removeAnimEndListener(animEndListener);
        }
        this.animEndListenerMap.remove(animator.getData().getName());
        this.playingAnim.remove(animator);
    }
}
