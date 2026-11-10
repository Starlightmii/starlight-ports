package com.oplus.systemui.statusbar.notification.row;

import com.android.systemui.statusbar.notification.animation.RowAnimationStateEx;
import com.android.systemui.statusbar.notification.animation.ScaleState;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateExKt;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusExpandableViewExImpl$timeoutRunnable$1 implements Runnable {
    public /* synthetic */ OplusExpandableViewExImpl this$0;

    /* JADX WARN: Code duplicated, block: B:30:0x0068  */
    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        OplusExpandableViewEx oplusExpandableViewEx;
        ExpandableViewState viewState;
        OplusExpandableViewStateEx oplusExpandableViewStateEx;
        RowAnimationStateEx rowAnimationStateEx;
        ScaleState rowScale;
        if (((COUIDynamicAnimation) this.this$0.getTouchScaleAnimator()).mRunning) {
            return;
        }
        ExpandableView view = this.this$0.getView();
        Object currentClipWidthSource = null;
        boolean zAreEqual = Intrinsics.areEqual((view == null || (rowAnimationStateEx = OplusExpandableViewStateExKt.getRowAnimationStateEx(view)) == null || (rowScale = ((RowAnimationStateExImpl) rowAnimationStateEx).getRowScale("scale_for_touch")) == null) ? null : Float.valueOf(rowScale.getScaleX()), 1.0f);
        ExpandableView view2 = this.this$0.getView();
        if (view2 == null || (viewState = view2.getViewState()) == null || (oplusExpandableViewStateEx = viewState.mExpandableViewStateEx) == null || oplusExpandableViewStateEx.getClipWidth() != 0) {
            ExpandableView view3 = this.this$0.getView();
            if (view3 != null && (oplusExpandableViewEx = view3.mExpandableViewEx) != null) {
                currentClipWidthSource = oplusExpandableViewEx.getCurrentClipWidthSource();
            }
            if (Intrinsics.areEqual(currentClipWidthSource, "clip_width_touch_scale")) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        if (!zAreEqual || z) {
            this.this$0.getTouchScaleAnimator().animateToFinalPosition(1.0f);
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, "ExpandableViewExtImpl", "tryStartScaleAnimation: timeout");
        }
    }
}
