package com.oplus.systemui.notification.row.oplusgroup;

import android.util.Pools;
import android.view.View;
import android.view.ViewPropertyAnimator;
import com.android.systemui.statusbar.notification.ImageTransformState;
import com.android.systemui.statusbar.notification.TransformState;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GroupIconTransformState extends ImageTransformState {
    public static final Companion Companion = new Companion();
    private static final Pools.SimplePool<GroupIconTransformState> sInstancePool = new Pools.SimplePool<>(40);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final GroupIconTransformState obtain() {
            GroupIconTransformState groupIconTransformState = (GroupIconTransformState) GroupIconTransformState.sInstancePool.acquire();
            return groupIconTransformState != null ? groupIconTransformState : new GroupIconTransformState();
        }
    }

    public final void recycle() {
        super.recycle();
        if (GroupIconTransformState.class.equals(GroupIconTransformState.class)) {
            sInstancePool.release(this);
        }
    }

    public boolean transformViewTo(TransformState transformState, float f) {
        ViewPropertyAnimator viewPropertyAnimatorAnimate;
        View transformedView = getTransformedView();
        if (transformedView != null && (viewPropertyAnimatorAnimate = transformedView.animate()) != null) {
            viewPropertyAnimatorAnimate.cancel();
        }
        transformViewFullyTo(transformState, f);
        return true;
    }
}
