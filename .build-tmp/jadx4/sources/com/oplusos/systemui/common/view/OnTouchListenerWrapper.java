package com.oplusos.systemui.common.view;

import android.view.MotionEvent;
import android.view.View;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OnTouchListenerWrapper implements View.OnTouchListener {
    private final Lazy _onTouchListeners$delegate = LazyKt__LazyJVMKt.lazy(new OnTouchListenerWrapper$$ExternalSyntheticLambda0());
    private final List<View.OnTouchListener> onTouchListeners = get_onTouchListeners();

    private final CopyOnWriteArrayList<View.OnTouchListener> get_onTouchListeners() {
        return (CopyOnWriteArrayList) this._onTouchListeners$delegate.getValue();
    }

    public final void addTouchListener(View.OnTouchListener onTouchListener, boolean z) {
        if (this.onTouchListeners.contains(onTouchListener)) {
            return;
        }
        if (z) {
            get_onTouchListeners().add(0, onTouchListener);
        } else {
            get_onTouchListeners().add(onTouchListener);
        }
    }

    public final void clearTouchListener() {
        get_onTouchListeners().clear();
    }

    public final List<View.OnTouchListener> getOnTouchListeners() {
        return this.onTouchListeners;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Iterator it = CollectionsKt___CollectionsKt.toList(this.onTouchListeners).iterator();
        while (it.hasNext()) {
            if (((View.OnTouchListener) it.next()).onTouch(view, motionEvent)) {
                return true;
            }
        }
        return false;
    }

    public final void removeTouchListener(View.OnTouchListener onTouchListener) {
        get_onTouchListeners().remove(onTouchListener);
    }
}
