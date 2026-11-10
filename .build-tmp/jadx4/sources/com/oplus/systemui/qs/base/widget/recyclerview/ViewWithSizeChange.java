package com.oplus.systemui.qs.base.widget.recyclerview;

import android.view.View;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewWithSizeChange {
    public static final Companion Companion = new Companion();
    private final CopyOnWriteArraySet<OnSizeChangeListener> _onSizeChangeListeners;
    private final Set<OnSizeChangeListener> onSizeChangeListeners;
    private final View targetView;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final ViewWithSizeChange injectOnSizeChange(View view, OnSizeChangeListener onSizeChangeListener) {
            Object tag = view.getTag(2131365580);
            ViewWithSizeChange viewWithSizeChange = tag instanceof ViewWithSizeChange ? (ViewWithSizeChange) tag : null;
            if (viewWithSizeChange == null) {
                viewWithSizeChange = new ViewWithSizeChange(view);
                view.setTag(2131365580, viewWithSizeChange);
            }
            if (onSizeChangeListener != null) {
                viewWithSizeChange.addListener(onSizeChangeListener);
            }
            return viewWithSizeChange;
        }

        public final void removeOnSizeChange(View view, OnSizeChangeListener onSizeChangeListener) {
            Object tag = view.getTag(2131365580);
            ViewWithSizeChange viewWithSizeChange = tag instanceof ViewWithSizeChange ? (ViewWithSizeChange) tag : null;
            if (viewWithSizeChange == null) {
                return;
            }
            if (onSizeChangeListener != null) {
                viewWithSizeChange.removeListener(onSizeChangeListener);
            }
            if (viewWithSizeChange.getOnSizeChangeListeners().isEmpty()) {
                view.setTag(2131365580, null);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface OnSizeChangeListener {
    }

    public ViewWithSizeChange(View view) {
        this.targetView = view;
        CopyOnWriteArraySet<OnSizeChangeListener> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
        this._onSizeChangeListeners = copyOnWriteArraySet;
        this.onSizeChangeListeners = copyOnWriteArraySet;
    }

    public final void addListener(OnSizeChangeListener onSizeChangeListener) {
        this._onSizeChangeListeners.add(onSizeChangeListener);
    }

    public final Set<OnSizeChangeListener> getOnSizeChangeListeners() {
        return this.onSizeChangeListeners;
    }

    public final View getTargetView() {
        return this.targetView;
    }

    public final void notifySizeChanged(int i, int i2, int i3, int i4) {
        Iterator<T> it = this._onSizeChangeListeners.iterator();
        while (it.hasNext()) {
            ((RecyclerViewHeightChangeAnimation) ((OnSizeChangeListener) it.next())).onSizeChange(i, i2, i3, i4);
        }
    }

    public final void removeListener(OnSizeChangeListener onSizeChangeListener) {
        this._onSizeChangeListeners.remove(onSizeChangeListener);
    }
}
