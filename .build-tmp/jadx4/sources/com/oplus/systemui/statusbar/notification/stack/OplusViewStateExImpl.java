package com.oplus.systemui.statusbar.notification.stack;

import android.animation.ObjectAnimator;
import android.view.NotificationHeaderView;
import android.view.View;
import com.android.systemui.statusbar.notification.PhysicsProperty;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.OplusViewStateEx;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusViewStateExImpl implements OplusViewStateEx {
    private ViewState base;
    private final Lazy centralSurfaces$delegate = LazyKt__LazyJVMKt.lazy(new OplusViewStateExImpl$$ExternalSyntheticLambda0());

    private final CentralSurfacesImpl getCentralSurfaces() {
        return (CentralSurfacesImpl) this.centralSurfaces$delegate.getValue();
    }

    public boolean isSummaryHeader(View view) {
        if (view instanceof NotificationHeaderView) {
            return ((NotificationHeaderView) view).getParent() instanceof NotificationChildrenContainer;
        }
        return false;
    }

    public void onBeforeStartYTranslationAnimation(ObjectAnimator objectAnimator, AnimationProperties animationProperties) {
        animationProperties.getClass();
    }

    public boolean shouldFilterAnimateY(View view, float f) {
        ExpandableView expandableView = view instanceof ExpandableView ? (ExpandableView) view : null;
        int actualHeight = expandableView != null ? expandableView.getActualHeight() : view.getHeight();
        float displayHeight = getCentralSurfaces().getDisplayHeight();
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int i = iArr[1];
        if (i + actualHeight < 0 || i > displayHeight) {
            return ((float) actualHeight) + f < 0.0f || f > displayHeight;
        }
        return false;
    }

    public boolean shouldUsePhysicsForMovement(View view, PhysicsProperty physicsProperty) {
        return true;
    }

    /* JADX INFO: renamed from: getBase, reason: merged with bridge method [inline-methods] */
    public ViewState m463getBase() {
        return this.base;
    }

    public void setBase(ViewState viewState) {
        this.base = viewState;
    }
}
