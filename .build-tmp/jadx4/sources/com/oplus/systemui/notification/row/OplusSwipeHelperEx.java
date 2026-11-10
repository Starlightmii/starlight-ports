package com.oplus.systemui.notification.row;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.FloatProperty;
import android.view.MotionEvent;
import android.view.View;
import com.android.systemui.SwipeHelper;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.oplusos.systemui.common.BaseBinder;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusSwipeHelperEx extends BaseBinder {
    default boolean canChildBeDragged(SwipeHelper.Callback callback, View view) {
        callback.getClass();
        return true;
    }

    void doLongClickCallback(View view, int i, int i2);

    @Override // com.oplusos.systemui.common.BaseBinder
    /* synthetic */ Object getBase();

    default boolean getHasOplusLogic() {
        return false;
    }

    NotificationMenuRowPlugin getProvider(View view);

    FloatProperty<View> getTranslateContent(View view);

    Animator getTranslateViewAnimator(View view, float f, ValueAnimator.AnimatorUpdateListener animatorUpdateListener);

    boolean isNeedResetTouchedView(View view, View view2, boolean z);

    default boolean isOplusCustomRow(View view) {
        return false;
    }

    boolean isRemoved(View view);

    default boolean onInterceptTouchEvent(View view, MotionEvent motionEvent, boolean z, SwipeHelper swipeHelper) {
        return z;
    }

    default void resetChildView(View view) {
    }

    @Override // com.oplusos.systemui.common.BaseBinder
    /* synthetic */ void setBase(Object obj);

    default boolean shouldNotShowMenuExt(MotionEvent motionEvent, View view, float f, NotificationMenuRowPlugin notificationMenuRowPlugin) {
        return false;
    }

    default boolean shouldSkipTouch(MotionEvent motionEvent, View view) {
        return false;
    }

    default boolean shouldSnapChildToDismiss(View view, View view2, float f) {
        return false;
    }

    default boolean shouldSwipeStates(View view) {
        return false;
    }

    void startMenuDismissAnimation(View view);
}
