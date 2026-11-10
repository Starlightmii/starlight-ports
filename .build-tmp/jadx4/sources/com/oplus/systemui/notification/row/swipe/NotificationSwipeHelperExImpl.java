package com.oplus.systemui.notification.row.swipe;

import android.animation.Animator;
import android.content.Context;
import android.util.IndentingPrintWriter;
import android.view.MotionEvent;
import android.view.View;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelperEx;
import com.android.systemui.util.DumpUtilsKt;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSwipeHelperExImpl extends OplusSwipeHelperExImpl implements NotificationSwipeHelperEx {

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.swipe.NotificationSwipeHelperExImpl$dump$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ View $menuExposedView;
        public /* synthetic */ IndentingPrintWriter $printWriter;
        public /* synthetic */ View $translatingParentView;

        @Override // java.lang.Runnable
        public final void run() {
            this.$printWriter.append("mTouchedView : ").print(this.$menuExposedView);
            this.$printWriter.append("translatingParentView: ").print(this.$translatingParentView);
            boolean z = this.$menuExposedView instanceof ExpandableNotificationRow;
            IndentingPrintWriter indentingPrintWriter = this.$printWriter;
            if (!z) {
                indentingPrintWriter.println();
                return;
            }
            PrintWriter printWriterAppend = indentingPrintWriter.append(" key : ");
            ExpandableNotificationRow expandableNotificationRow = this.$menuExposedView;
            printWriterAppend.println(expandableNotificationRow == null ? "null" : expandableNotificationRow.getLoggingKey());
            this.$printWriter.append(" translation : ").println(this.$menuExposedView.getTranslation());
        }
    }

    public NotificationSwipeHelperExImpl(Context context, OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState) {
        super(context, oplusLockscreenShadeTransitionControllerEx, oplusSeparateNotificationAndQSState);
    }

    public void dump(PrintWriter printWriter, View view, View view2) {
        IndentingPrintWriter indentingPrintWriterAsIndenting = DumpUtilsKt.asIndenting(printWriter);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$printWriter = indentingPrintWriterAsIndenting;
        anonymousClass1.$menuExposedView = view;
        anonymousClass1.$translatingParentView = view2;
        VarHandle.storeStoreFence();
        DumpUtilsKt.withIncreasedIndent(indentingPrintWriterAsIndenting, anonymousClass1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onChildSnappedBack(NotificationSwipeHelper notificationSwipeHelper, View view, NotificationMenuRowPlugin notificationMenuRowPlugin) {
        NotificationMenuRowPlugin provider;
        if (view instanceof ExpandableNotificationRow) {
            provider = ((ExpandableNotificationRow) view).getProvider();
        } else {
            provider = view instanceof OplusCustomRow ? ((OplusCustomRow) view).getProvider() : null;
        }
        if (provider == null) {
            if (notificationMenuRowPlugin != null) {
                notificationMenuRowPlugin.resetMenu();
            }
            notificationSwipeHelper.setCurrentMenuRow((NotificationMenuRowPlugin) null);
        } else if (!Intrinsics.areEqual(notificationMenuRowPlugin, provider)) {
            provider.resetMenu();
        } else {
            notificationMenuRowPlugin.resetMenu();
            notificationSwipeHelper.setCurrentMenuRow((NotificationMenuRowPlugin) null);
        }
    }

    public void prepareDismissAnimationIfNeeded(View view, Animator animator) {
        if (view instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
            if (expandableNotificationRow.isHeadsUpState() && expandableNotificationRow.mPinnedStatus.isPinned()) {
                expandableNotificationRow.mRowEx.boostFrameRate(animator);
            }
        }
    }

    public void prepareSnapAnimationIfNeeded(View view, PhysicsAnimator<View> physicsAnimator) {
        if (view instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
            if (expandableNotificationRow.isHeadsUpState() && expandableNotificationRow.mPinnedStatus.isPinned()) {
                expandableNotificationRow.mRowEx.setBoostFrameRate(true);
            }
            prepareSnapAnimationIfNeeded.listener.1 r1 = new prepareSnapAnimationIfNeeded.listener.1();
            r1.$view = (ExpandableNotificationRow) view;
            VarHandle.storeStoreFence();
            physicsAnimator.addEndListener(r1);
        }
    }

    public void snapBackTranslatingRowIfMenuExposedViewNull(NotificationSwipeHelper notificationSwipeHelper, View view, View view2, boolean z) {
        ExpandableNotificationRow expandableNotificationRow = view2 instanceof ExpandableNotificationRow ? (ExpandableNotificationRow) view2 : null;
        if (expandableNotificationRow == null || view != null || expandableNotificationRow.mRemoved) {
            return;
        }
        Animator animator = expandableNotificationRow.mTranslateAnim;
        if ((animator == null || !animator.isRunning()) && notificationSwipeHelper.getTranslation(expandableNotificationRow) != 0.0f) {
            notificationSwipeHelper.snapChildIfNeeded(expandableNotificationRow, z, 0.0f);
        }
    }

    public void trackMotionEvent(View view, MotionEvent motionEvent) {
        if (view instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
            if (expandableNotificationRow.isHeadsUpState() && expandableNotificationRow.mPinnedStatus.isPinned()) {
                int action = motionEvent.getAction();
                if (action != 1) {
                    if (action == 2) {
                        expandableNotificationRow.mRowEx.setBoostFrameRate(true);
                        return;
                    } else if (action != 3) {
                        return;
                    }
                }
                expandableNotificationRow.mRowEx.setBoostFrameRate(false);
            }
        }
    }
}
