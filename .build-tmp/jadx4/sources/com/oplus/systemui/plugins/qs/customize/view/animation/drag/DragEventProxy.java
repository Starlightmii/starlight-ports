package com.oplus.systemui.plugins.qs.customize.view.animation.drag;

import android.view.DragEvent;
import android.view.View;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DragEventProxy implements View.OnDragListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "DragEventProxy";
    public boolean isDropHandled;
    private final OnDragEventListener onDragEventListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public DragEventProxy(OnDragEventListener onDragEventListener) {
        this.onDragEventListener = onDragEventListener;
    }

    @Override // android.view.View.OnDragListener
    public boolean onDrag(View view, DragEvent dragEvent) {
        OnDragEventListener onDragEventListener;
        int action = dragEvent.getAction();
        if (action == 1) {
            this.isDropHandled = false;
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "onDragStart", null, 8);
            OnDragEventListener onDragEventListener2 = this.onDragEventListener;
            if (onDragEventListener2 != null) {
                onDragEventListener2.onDragStart(dragEvent.getX(), dragEvent.getY(), dragEvent.getLocalState());
            }
            return true;
        }
        if (action != 2) {
            if (action == 3) {
                this.isDropHandled = true;
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "onDrop", null, 8);
                OnDragEventListener onDragEventListener3 = this.onDragEventListener;
                if (onDragEventListener3 != null) {
                    onDragEventListener3.onDrop(dragEvent.getX(), dragEvent.getY(), dragEvent.getLocalState());
                    return false;
                }
            } else if (action == 4) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("onDragEnd: ", dragEvent.getResult()), null, 8);
                OnDragEventListener onDragEventListener4 = this.onDragEventListener;
                if (onDragEventListener4 != null) {
                    onDragEventListener4.onDragEnd(dragEvent.getResult(), dragEvent.getX(), dragEvent.getY(), dragEvent.getLocalState());
                }
                this.isDropHandled = false;
                return false;
            }
        } else if (!this.isDropHandled && (onDragEventListener = this.onDragEventListener) != null) {
            onDragEventListener.onDragLocation(dragEvent.getX(), dragEvent.getY(), dragEvent.getLocalState());
        }
        return false;
    }
}
