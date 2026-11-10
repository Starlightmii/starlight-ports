package com.oplus.systemui.plugins.qs.util;

import android.view.View;
import android.view.ViewTreeObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewLifecycleOwner implements LifecycleOwner {
    private final LifecycleRegistry registry;
    private final View view;
    private final ViewTreeObserver.OnWindowFocusChangeListener windowFocusListener;
    private final ViewTreeObserver.OnWindowVisibilityChangeListener windowVisibleListener;

    public ViewLifecycleOwner(View view) {
        this.view = view;
        ViewLifecycleOwner$windowVisibleListener$1 viewLifecycleOwner$windowVisibleListener$1 = new ViewLifecycleOwner$windowVisibleListener$1();
        viewLifecycleOwner$windowVisibleListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.windowVisibleListener = viewLifecycleOwner$windowVisibleListener$1;
        ViewLifecycleOwner$windowFocusListener$1 viewLifecycleOwner$windowFocusListener$1 = new ViewLifecycleOwner$windowFocusListener$1();
        viewLifecycleOwner$windowFocusListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.windowFocusListener = viewLifecycleOwner$windowFocusListener$1;
        this.registry = new LifecycleRegistry(this);
    }

    public Lifecycle getLifecycle() {
        return this.registry;
    }

    public final void onCreate() {
        this.registry.setCurrentState(Lifecycle.State.CREATED);
        this.view.getViewTreeObserver().addOnWindowFocusChangeListener(this.windowFocusListener);
        updateState$4$1();
    }

    public final void onDestroy() {
        this.view.getViewTreeObserver().removeOnWindowFocusChangeListener(this.windowFocusListener);
        this.registry.setCurrentState(Lifecycle.State.DESTROYED);
    }

    public final void updateState$4$1() {
        Lifecycle.State state;
        LifecycleRegistry lifecycleRegistry = this.registry;
        if (this.view.getWindowVisibility() != 0) {
            state = Lifecycle.State.CREATED;
        } else {
            state = !this.view.hasWindowFocus() ? Lifecycle.State.STARTED : Lifecycle.State.RESUMED;
        }
        lifecycleRegistry.setCurrentState(state);
    }
}
