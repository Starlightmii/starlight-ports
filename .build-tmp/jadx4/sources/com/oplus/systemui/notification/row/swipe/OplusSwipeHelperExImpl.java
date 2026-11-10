package com.oplus.systemui.notification.row.swipe;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.FloatProperty;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.systemui.SwipeHelper;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationMenuRow;
import com.android.systemui.statusbar.notification.row.NotificationMenuRowExt;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.stack.MediaContainerView;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.oplus.systemui.notification.lockscreen.base.LockScreenNotificationKotlinExKt;
import com.oplus.systemui.notification.row.NotificationMenuRowExtImpl;
import com.oplus.systemui.notification.row.OplusSwipeHelperEx;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomMenuRow;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.GlobalActionsUtils;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusSwipeHelperExImpl implements OplusSwipeHelperEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusSwipeHelperExImpl";
    private SwipeHelper base;
    private final Context context;
    public boolean isTouchDown;
    private final OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusSwipeHelperExImpl(Context context, OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState) {
        this.context = context;
        this.oplusLockscreenShadeTransitionControllerEx = oplusLockscreenShadeTransitionControllerEx;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0036  */
    /* JADX WARN: Code duplicated, block: B:22:0x003f  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx
    public boolean canChildBeDragged(SwipeHelper.Callback callback, View view) {
        if (view instanceof MediaContainerView) {
            return false;
        }
        if (view instanceof OplusCustomRow) {
            OplusCustomRow oplusCustomRow = (OplusCustomRow) view;
            OplusExpandableViewEx oplusExpandableViewEx = ((ExpandableView) oplusCustomRow).mExpandableViewEx;
            if (!Intrinsics.areEqual(oplusExpandableViewEx != null ? Float.valueOf(oplusExpandableViewEx.getImmersiveProgress()) : null, 1.0f)) {
                OplusCustomRow.Companion companion = OplusCustomRow.Companion;
                CustomRowEntry entry = oplusCustomRow.getEntry();
                if (!companion.isOplusMediaCustomRow(entry != null ? entry.getServiceId() : null)) {
                    if (!this.oplusLockscreenShadeTransitionControllerEx.isAnimRunning()) {
                        callback.getClass();
                        return true;
                    }
                }
            } else if (!this.oplusLockscreenShadeTransitionControllerEx.isAnimRunning()) {
                callback.getClass();
                return true;
            }
        } else if (!this.oplusLockscreenShadeTransitionControllerEx.isAnimRunning()) {
            callback.getClass();
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx
    public void doLongClickCallback(View view, int i, int i2) {
        if (view instanceof OplusCustomRow) {
            ((OplusCustomRow) view).doLongClick(null);
        }
    }

    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx
    public boolean getHasOplusLogic() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx
    public NotificationMenuRowPlugin getProvider(View view) {
        if (view instanceof OplusCustomRow) {
            return ((OplusCustomRow) view).getProvider();
        }
        if (view instanceof ExpandableNotificationRow) {
            return ((ExpandableNotificationRow) view).getProvider();
        }
        return null;
    }

    public final float getSwipeFarEnoughFraction(float f) {
        return GlobalActionsUtils.px2dp(this.context, f) > 450 ? 0.5f : 0.6f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx
    public FloatProperty<View> getTranslateContent(View view) {
        if (view instanceof OplusCustomRow) {
            return ((OplusCustomRow) view).getTranslationContent();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx
    public Animator getTranslateViewAnimator(View view, float f, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        if (view instanceof OplusCustomRow) {
            return ((OplusCustomRow) view).getTranslateViewAnimator(f, animatorUpdateListener);
        }
        return null;
    }

    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx
    public boolean isNeedResetTouchedView(View view, View view2, boolean z) {
        return (view != view2 || z || this.isTouchDown) ? false : true;
    }

    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx
    public boolean isOplusCustomRow(View view) {
        return view instanceof OplusCustomRow;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx
    public boolean isRemoved(View view) {
        if (!(view instanceof OplusCustomRow)) {
            return false;
        }
        return false;
    }

    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx
    public boolean onInterceptTouchEvent(View view, MotionEvent motionEvent, boolean z, SwipeHelper swipeHelper) {
        NotificationMenuRowPlugin provider;
        if (motionEvent != null && motionEvent.getAction() == 0) {
            this.isTouchDown = true;
        }
        if (((Boolean) this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue() && motionEvent != null && motionEvent.getAction() == 2 && view != null) {
            NotificationSwipeHelper notificationSwipeHelper = (NotificationSwipeHelper) swipeHelper;
            notificationSwipeHelper.getClass();
            if ((view instanceof ExpandableNotificationRow) && (provider = ((ExpandableNotificationRow) view).getProvider()) != null && (provider.getMenuView() == null || provider != notificationSwipeHelper.getCurrentMenuRow())) {
                LogUtil.d(LogUtil.TAG_COMMON, "NotificationSwipeHelper", "Mock on DownUpdate");
                notificationSwipeHelper.onDownUpdate(view);
            }
        }
        return super.onInterceptTouchEvent(view, motionEvent, z, swipeHelper);
    }

    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx
    public void resetChildView(View view) {
        List<ExpandableNotificationRow> attachedChildren;
        SwipeHelper base;
        if (view instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
            if (!expandableNotificationRow.mIsSummaryWithChildren || (attachedChildren = expandableNotificationRow.getAttachedChildren()) == null) {
                return;
            }
            for (ExpandableNotificationRow expandableNotificationRow2 : attachedChildren) {
                if (expandableNotificationRow2 != null && expandableNotificationRow2.getTranslation() != 0.0f && (base = getBase()) != null) {
                    base.forceResetSwipeState(expandableNotificationRow2);
                }
            }
        }
    }

    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx
    public boolean shouldNotShowMenuExt(MotionEvent motionEvent, View view, float f, NotificationMenuRowPlugin notificationMenuRowPlugin) {
        FeatureOption.INSTANCE.getClass();
        if ((FeatureOption.isExpRegion() && (view instanceof ExpandableNotificationRow)) || motionEvent.getActionMasked() == 3) {
            return true;
        }
        if (notificationMenuRowPlugin instanceof NotificationMenuRow) {
            NotificationMenuRow notificationMenuRow = (NotificationMenuRow) notificationMenuRowPlugin;
            if (notificationMenuRow.mNotificationMenuRowExt.isSwipeToEnd(notificationMenuRow.getTranslation(), notificationMenuRowPlugin)) {
                return true;
            }
        }
        boolean z = notificationMenuRowPlugin instanceof OplusCustomMenuRow;
        if (z) {
            OplusCustomMenuRow oplusCustomMenuRow = (OplusCustomMenuRow) notificationMenuRowPlugin;
            if (oplusCustomMenuRow.isSwipeToEnd(oplusCustomMenuRow.getTranslation(), notificationMenuRowPlugin)) {
                return true;
            }
        }
        return z && ((OplusCustomMenuRow) notificationMenuRowPlugin).isImmersiveState();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx
    public boolean shouldSkipTouch(MotionEvent motionEvent, View view) {
        return (view instanceof OplusCustomRow) && ((OplusCustomRow) view).getChildCount() > 0;
    }

    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx
    public boolean shouldSnapChildToDismiss(View view, View view2, float f) {
        if (view == null || view2 == null || view2.equals(view) || f == 0.0f) {
            return false;
        }
        ExpandableView expandableView = view instanceof ExpandableView ? (ExpandableView) view : null;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "shouldSnapChildToDismiss: not the same view, targetLeft=" + f + ", key=" + (expandableView != null ? LockScreenNotificationKotlinExKt.getLogKey(expandableView) : null), null, 8);
        if (!(view instanceof ExpandableNotificationRow)) {
            return false;
        }
        ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
        NotificationMenuRow provider = expandableNotificationRow.getProvider();
        NotificationMenuRow notificationMenuRow = provider instanceof NotificationMenuRow ? provider : null;
        if (Intrinsics.areEqual(notificationMenuRow != null ? Float.valueOf(notificationMenuRow.getTranslation()) : null, 0.0f) || notificationMenuRow == null || notificationMenuRow.isSnappingToDismiss() || !notificationMenuRow.isSnappedAndOnSameSide()) {
            return false;
        }
        String key = expandableNotificationRow.getKey();
        Float fValueOf = Float.valueOf(notificationMenuRow.getTranslation());
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m("shouldSnapChildToDismiss: key=", key, ", targetLeft=", f, ", translation=");
        sbM.append(fValueOf);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx
    public boolean shouldSwipeStates(View view) {
        if (!(view instanceof ExpandableNotificationRow) || ((ExpandableNotificationRow) view).getTranslation() == 0.0f) {
            return (view instanceof OplusCustomRow) && ((OplusCustomRow) view).getTranslation() != 0.0f;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx
    public void startMenuDismissAnimation(View view) {
        NotificationMenuRowExt ext;
        NotificationMenuRowExt notificationMenuRowExt;
        if (view instanceof ExpandableNotificationRow) {
            NotificationMenuRow provider = ((ExpandableNotificationRow) view).getProvider();
            NotificationMenuRow notificationMenuRow = provider instanceof NotificationMenuRow ? provider : null;
            if (notificationMenuRow != null && (notificationMenuRowExt = notificationMenuRow.mNotificationMenuRowExt) != null) {
                ((NotificationMenuRowExtImpl) notificationMenuRowExt).onDismissRow();
            }
        }
        if (view instanceof OplusCustomRow) {
            NotificationMenuRowPlugin provider2 = ((OplusCustomRow) view).getProvider();
            OplusCustomMenuRow oplusCustomMenuRow = provider2 instanceof OplusCustomMenuRow ? (OplusCustomMenuRow) provider2 : null;
            if (oplusCustomMenuRow == null || (ext = oplusCustomMenuRow.getExt()) == null) {
                return;
            }
            ((NotificationMenuRowExtImpl) ext).onDismissRow();
        }
    }

    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx, com.oplusos.systemui.common.BaseBinder
    public SwipeHelper getBase() {
        return this.base;
    }

    @Override // com.oplus.systemui.notification.row.OplusSwipeHelperEx, com.oplusos.systemui.common.BaseBinder
    public void setBase(SwipeHelper swipeHelper) {
        this.base = swipeHelper;
    }
}
