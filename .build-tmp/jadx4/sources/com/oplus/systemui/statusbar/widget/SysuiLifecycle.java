package com.oplus.systemui.statusbar.widget;

import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class SysuiLifecycle {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ViewLifecycle implements LifecycleOwner, View.OnAttachStateChangeListener {
        public LifecycleRegistry mLifecycle;

        public Lifecycle getLifecycle() {
            return this.mLifecycle;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            this.mLifecycle.markState(Lifecycle.State.RESUMED);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            this.mLifecycle.markState(Lifecycle.State.DESTROYED);
        }
    }

    public static ViewLifecycle viewAttachLifecycle(View view) {
        ViewLifecycle viewLifecycle = new ViewLifecycle();
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(viewLifecycle);
        viewLifecycle.mLifecycle = lifecycleRegistry;
        view.addOnAttachStateChangeListener(viewLifecycle);
        if (!view.isAttachedToWindow()) {
            VarHandle.storeStoreFence();
            return viewLifecycle;
        }
        lifecycleRegistry.markState(Lifecycle.State.RESUMED);
        VarHandle.storeStoreFence();
        return viewLifecycle;
    }
}
