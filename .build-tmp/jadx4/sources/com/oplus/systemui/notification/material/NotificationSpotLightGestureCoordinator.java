package com.oplus.systemui.notification.material;

import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSpotLightGestureCoordinator {
    private static final String TAG = "NotificationSpotLightGestureCoordinator";
    public static final NotificationSpotLightGestureCoordinator INSTANCE = new NotificationSpotLightGestureCoordinator();
    private static final SparseArray<WeakReference<View>> ownerByPointerId = new SparseArray<>();

    public static void releasePointer(int i) {
        ownerByPointerId.remove(i);
    }

    public final View findHostIfPointerTracked(MotionEvent motionEvent) {
        View view;
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            WeakReference<View> weakReference = ownerByPointerId.get(motionEvent.getPointerId(i));
            if (weakReference != null && (view = weakReference.get()) != null) {
                return view;
            }
        }
        return null;
    }

    public final int findOwnedPointerIndex(View view, MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            if (isOwner(view, motionEvent.getPointerId(i))) {
                return i;
            }
        }
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String getLogKey(View view) {
        if (view instanceof ExpandableNotificationRow) {
            return ((ExpandableNotificationRow) view).getKey();
        }
        if (!(view instanceof OplusCustomRow)) {
            return view.toString();
        }
        CustomRowEntry entry = ((OplusCustomRow) view).getEntry();
        if (entry != null) {
            return entry.getKey();
        }
        return null;
    }

    public final boolean isOwner(View view, int i) {
        WeakReference<View> weakReference = ownerByPointerId.get(i);
        return (weakReference != null ? weakReference.get() : null) == view;
    }

    public final void onDown(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 || actionMasked == 5) {
            int iPointerIdForAction = pointerIdForAction(motionEvent);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationSpotLightGestureCoordinator-->".concat("[NotifCard-SpotLight] onDown pointerId=" + iPointerIdForAction + ", key=" + INSTANCE.getLogKey(view)));
            }
            ownerByPointerId.put(iPointerIdForAction, new WeakReference<>(view));
        }
    }

    public final int pointerIdForAction(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = (actionMasked == 5 || actionMasked == 6) ? motionEvent.getActionIndex() : 0;
        if (actionIndex < 0 || actionIndex >= motionEvent.getPointerCount()) {
            return 0;
        }
        return motionEvent.getPointerId(actionIndex);
    }

    public final void releaseAllForHost(View view) {
        int size = ownerByPointerId.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return;
            }
            SparseArray<WeakReference<View>> sparseArray = ownerByPointerId;
            WeakReference<View> weakReferenceValueAt = sparseArray.valueAt(size);
            if ((weakReferenceValueAt != null ? weakReferenceValueAt.get() : null) == view) {
                sparseArray.removeAt(size);
            }
        }
    }
}
