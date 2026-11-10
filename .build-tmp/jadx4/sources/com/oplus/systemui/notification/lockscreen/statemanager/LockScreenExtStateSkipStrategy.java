package com.oplus.systemui.notification.lockscreen.statemanager;

import android.content.Context;
import android.util.Log;
import androidx.compose.animation.core.MonoSpline$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.notification.emptyshade.ui.view.EmptyShadeView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.oplus.systemui.notification.lockscreen.stack.algorithm.StackAlgorithmManager;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenExtStateSkipStrategy {
    public static final Companion Companion = new Companion();
    private static final String TAG = "LockScreenExtStateSkipStrategy";
    private final Context context;
    private final Lazy lockScreenNotificationDispatcher;
    private final Lazy notificationKeyguardHelper;
    private final Lazy npvc;
    private final Lazy nsslController;
    private final StackAlgorithmManager stackAlgorithmManager;

    public LockScreenExtStateSkipStrategy(Context context, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, StackAlgorithmManager stackAlgorithmManager) {
        this.context = context;
        this.notificationKeyguardHelper = lazy;
        this.lockScreenNotificationDispatcher = lazy2;
        this.npvc = lazy3;
        this.nsslController = lazy4;
        this.stackAlgorithmManager = stackAlgorithmManager;
    }

    private final boolean getEnableSinkingState() {
        return ((NotificationKeyguardHelper) this.notificationKeyguardHelper.get()).mLockScreenNotificationEnableSinkingObserver.getLockScreenNotificationEnableSinking();
    }

    private final float getMeasureMaxNotificationHeight() {
        return ((NotificationPanelViewController) this.npvc.get()).getExt().getMaxVerticalSpaceForLockScreenNotification();
    }

    private final float getMeasureNotificationItemHeight() {
        return this.context.getResources().getDimensionPixelSize(2131170702);
    }

    public static void log(String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "LockScreenExtStateSkipStrategy-->".concat(str));
        }
    }

    public final boolean shouldSkipTargetStateToNext(int i) {
        if (i != 0 && i != 3) {
            log("shouldSkipTargetStateToNext [false]: nextTarget=" + i + ", reason=nextTarget not stack/sinking");
            return false;
        }
        if (!getEnableSinkingState()) {
            log("shouldSkipTargetStateToNext [false]: nextTarget=" + i + ", reason=sinking disabled");
            return false;
        }
        List<ExpandableNotificationRow> visibleChildren = ((NotificationStackScrollLayoutController) this.nsslController.get()).getView().getVisibleChildren();
        if (!(visibleChildren instanceof Collection) || !visibleChildren.isEmpty()) {
            for (ExpandableNotificationRow expandableNotificationRow : visibleChildren) {
                if (expandableNotificationRow instanceof ExpandableNotificationRow) {
                    ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
                    if (expandableNotificationRow2.mIsSummaryWithChildren && expandableNotificationRow2.isGroupExpanded()) {
                        log("shouldSkipTargetStateToNext [false]: nextTarget=" + i + ", reason=hasExpandedGroupNotification");
                        return false;
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (ExpandableView expandableView : ((NotificationStackScrollLayoutController) this.nsslController.get()).getView().getVisibleChildren()) {
            if (!((ViewState) expandableView.getViewState()).hidden && !(expandableView instanceof EmptyShadeView)) {
                if (expandableView instanceof OplusCustomRow) {
                    arrayList2.add(expandableView);
                } else {
                    arrayList.add(expandableView);
                }
            }
        }
        float measureMaxNotificationHeight = getMeasureMaxNotificationHeight();
        float fComputeSeedlingNeededHeight = this.stackAlgorithmManager.computeSeedlingNeededHeight(arrayList2);
        float fComputeSinkingStaticStackedAreaHeight = this.stackAlgorithmManager.computeSinkingStaticStackedAreaHeight(arrayList);
        float measureNotificationItemHeight = getMeasureNotificationItemHeight();
        float f = ((measureMaxNotificationHeight - fComputeSeedlingNeededHeight) - fComputeSinkingStaticStackedAreaHeight) / measureNotificationItemHeight;
        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("calculateDiscriminability: H=", measureMaxNotificationHeight, ", H_fluid=", fComputeSeedlingNeededHeight, ", H_stack=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, fComputeSinkingStaticStackedAreaHeight, ", H_item=", measureNotificationItemHeight, ", D=");
        sbM.append(f);
        sbM.append(OplusPreventModeData.SEPARATOR);
        log(sbM.toString());
        if (f >= 1.0f) {
            log("shouldSkipTargetStateToNext [false]: nextTarget=" + i + ", reason=has enough space");
            return false;
        }
        log("shouldSkipTargetStateToNext: [true] nextTarget=" + i + ", reason=D < 1");
        return true;
    }
}
