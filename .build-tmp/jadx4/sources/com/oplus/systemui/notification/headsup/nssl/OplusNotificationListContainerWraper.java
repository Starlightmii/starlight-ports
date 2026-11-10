package com.oplus.systemui.notification.headsup.nssl;

import android.content.Context;
import android.support.v4.content.FileProvider$;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.recyclerview.widget.ChildHelper$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.statusbar.NotificationSwipeActionHelper;
import com.android.systemui.statusbar.notification.LaunchAnimationParameters;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.notification.aod.notification.NotificationAodTransition;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpLayoutController;
import com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpNotificationInteractor;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableViewExImpl;
import com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutControllerExtImpl;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationListContainerWraper implements NotificationListContainer, OplusHeadsUpRowChanger {
    private static final Companion Companion = new Companion();
    private static final String TAG = "OplusNotificationList";
    private final NotificationAodTransition aonTransition;
    private final Context context;
    private NotificationEntry currentEntry;
    private final Lazy headsUpInteractor;
    private final Lazy headsUpLayoutController;
    private final HeadsUpNsslStateManager headsUpNsslStateManager;
    private final Lazy nsslControllerExtImpl;
    public NotificationListContainer orgin;
    private final HeadsUpTempViewCreator tempViewCreator;
    private Function0 waitOnViewRemoveFromParentCallback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: $r8$lambda$Hf-PjbbCk4Sidzs_Ww7aOQC4nLY, reason: not valid java name */
    public static Unit m58$r8$lambda$HfPjbbCk4Sidzs_Ww7aOQC4nLY(Function1 function1, OplusNotificationListContainerWraper oplusNotificationListContainerWraper) {
        function1.invoke(oplusNotificationListContainerWraper.getTempView());
        return Unit.INSTANCE;
    }

    public OplusNotificationListContainerWraper(Context context, HeadsUpTempViewCreator headsUpTempViewCreator, Lazy lazy, Lazy lazy2, Lazy lazy3, HeadsUpNsslStateManager headsUpNsslStateManager, NotificationAodTransition notificationAodTransition) {
        this.context = context;
        this.tempViewCreator = headsUpTempViewCreator;
        this.headsUpInteractor = lazy;
        this.nsslControllerExtImpl = lazy2;
        this.headsUpLayoutController = lazy3;
        this.headsUpNsslStateManager = headsUpNsslStateManager;
        this.aonTransition = notificationAodTransition;
    }

    private final ExpandableView getTempView() {
        return this.tempViewCreator.getTemp().getRow();
    }

    public static void removeFromParent(View view) {
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, ChildHelper$.ExternalSyntheticOutline0.m(view, "removeFromParent: "), null, 8);
            viewGroup.removeView(view);
            viewGroup.removeTransientView(view);
        }
    }

    public void addContainerView(View view) {
        ExpandableNotificationRow row;
        if (this.tempViewCreator.hasInit()) {
            NotificationEntry notificationEntry = this.currentEntry;
            ViewGroup.LayoutParams layoutParams = null;
            if (Intrinsics.areEqual(notificationEntry != null ? notificationEntry.getRow() : null, view)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    ExpandableView tempView = getTempView();
                    NotificationEntry notificationEntry2 = this.currentEntry;
                    String logKeyNonNull = notificationEntry2 != null ? NotificationUtilsKt.getLogKeyNonNull(notificationEntry2) : null;
                    int containerChildCount = getOrgin().getContainerChildCount();
                    StringBuilder sb = new StringBuilder("addContainerView need use tempView ");
                    sb.append(tempView);
                    sb.append(": ");
                    sb.append(logKeyNonNull);
                    sb.append("  ");
                    MobileInputLogger$.ExternalSyntheticOutline0.m(containerChildCount, "OplusNotificationList-->", "SystemUi--Notification", sb);
                }
                ExpandableView tempView2 = getTempView();
                NotificationEntry notificationEntry3 = this.currentEntry;
                if (notificationEntry3 != null && (row = notificationEntry3.getRow()) != null) {
                    layoutParams = row.getLayoutParams();
                }
                tempView2.setLayoutParams(layoutParams);
                removeFromParent(getTempView());
                getOrgin().addContainerView(getTempView());
                delayCallOnViewRemoveFromParent("addContainerView");
                return;
            }
        }
        getOrgin().addContainerView(view);
    }

    public void addContainerViewAt(View view, int i) {
        ExpandableNotificationRow row;
        layoutParams = null;
        ViewGroup.LayoutParams layoutParams = null;
        if (this.tempViewCreator.hasInit()) {
            NotificationEntry notificationEntry = this.currentEntry;
            if (Intrinsics.areEqual(notificationEntry != null ? notificationEntry.getRow() : null, view)) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    ExpandableView tempView = getTempView();
                    NotificationEntry notificationEntry2 = this.currentEntry;
                    String logKeyNonNull = notificationEntry2 != null ? NotificationUtilsKt.getLogKeyNonNull(notificationEntry2) : null;
                    StringBuilder sb = new StringBuilder("addContainerViewAt need use tempView ");
                    sb.append(i);
                    sb.append(" ");
                    sb.append(tempView);
                    sb.append(": ");
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(logKeyNonNull, "OplusNotificationList-->", "SystemUi--Notification", sb);
                }
                ExpandableView tempView2 = getTempView();
                NotificationEntry notificationEntry3 = this.currentEntry;
                if (notificationEntry3 != null && (row = notificationEntry3.getRow()) != null) {
                    layoutParams = row.getLayoutParams();
                }
                tempView2.setLayoutParams(layoutParams);
                removeFromParent(getTempView());
                getOrgin().addContainerViewAt(getTempView(), i);
                delayCallOnViewRemoveFromParent("addContainerViewAt");
                return;
            }
        }
        if ((view != null ? view.getParent() : null) != null) {
            removeFromParent(view);
        }
        getOrgin().addContainerViewAt(view, i);
    }

    public void applyLaunchAnimationParams(LaunchAnimationParameters launchAnimationParameters) {
    }

    public void bindRow(ExpandableNotificationRow expandableNotificationRow) {
        getOrgin().bindRow(expandableNotificationRow);
    }

    public void changeViewPosition(ExpandableView expandableView, int i) {
        if (this.tempViewCreator.hasInit()) {
            NotificationEntry notificationEntry = this.currentEntry;
            if (Intrinsics.areEqual(notificationEntry != null ? notificationEntry.getRow() : null, expandableView)) {
                if (getTempView().getParent() == null) {
                    getOrgin().addContainerViewAt(getTempView(), i);
                    NotificationEntry notificationEntry2 = this.currentEntry;
                    LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "changeViewPosition:no parent " + (notificationEntry2 != null ? NotificationUtilsKt.getLogKeyNonNull(notificationEntry2) : null) + " " + expandableView + " " + i);
                    return;
                }
                NotificationEntry notificationEntry3 = this.currentEntry;
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "changeViewPosition: " + (notificationEntry3 != null ? NotificationUtilsKt.getLogKeyNonNull(notificationEntry3) : null) + " " + expandableView + " " + i, null, 8);
                getOrgin().changeViewPosition(getTempView(), i);
                return;
            }
        }
        getOrgin().changeViewPosition(expandableView, i);
    }

    public void cleanUpViewStateForEntry(NotificationEntry notificationEntry) {
        getOrgin().cleanUpViewStateForEntry(notificationEntry);
    }

    public final void delayCallOnViewRemoveFromParent(String str) {
        Function0 function0 = this.waitOnViewRemoveFromParentCallback;
        if (function0 != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                KeyguardSettingProvider$.ExternalSyntheticOutline0.m("delayCallOnViewRemoveFromParent: ", str, "OplusNotificationList-->", "SystemUi--Notification");
            }
            function0.invoke();
            this.waitOnViewRemoveFromParentCallback = null;
        }
    }

    public View getContainerChildAt(int i) {
        View containerChildAt = getOrgin().getContainerChildAt(i);
        if (!this.tempViewCreator.hasInit() || !Intrinsics.areEqual(getTempView(), containerChildAt)) {
            return containerChildAt;
        }
        NotificationEntry notificationEntry = this.currentEntry;
        if (notificationEntry != null) {
            return notificationEntry.getRow();
        }
        return null;
    }

    public int getContainerChildCount() {
        return getOrgin().getContainerChildCount();
    }

    public final NotificationListContainer getOrgin() {
        NotificationListContainer notificationListContainer = this.orgin;
        if (notificationListContainer != null) {
            return notificationListContainer;
        }
        return null;
    }

    public NotificationSwipeActionHelper getSwipeActionHelper() {
        return getOrgin().getSwipeActionHelper();
    }

    public int getTopClippingStartLocation() {
        return getOrgin().getTopClippingStartLocation();
    }

    public ViewGroup getViewParentForNotification() {
        ExpandableNotificationRow row;
        NotificationEntry notificationEntry = this.currentEntry;
        return (notificationEntry == null || (row = notificationEntry.getRow()) == null || !row.isHeadsUpState()) ? getOrgin().getViewParentForNotification() : ((HeadsUpLayoutController) this.headsUpLayoutController.get()).headsUpLayout();
    }

    public final boolean hasPulsingNotifications() {
        return getOrgin().hasPulsingNotifications();
    }

    public final void init(Function1 function1) {
        HeadsUpTempViewCreator headsUpTempViewCreator = this.tempViewCreator;
        OplusNotificationListContainerWraper$$ExternalSyntheticLambda0 oplusNotificationListContainerWraper$$ExternalSyntheticLambda0 = new OplusNotificationListContainerWraper$$ExternalSyntheticLambda0();
        oplusNotificationListContainerWraper$$ExternalSyntheticLambda0.f$0 = function1;
        oplusNotificationListContainerWraper$$ExternalSyntheticLambda0.f$1 = this;
        VarHandle.storeStoreFence();
        headsUpTempViewCreator.init(oplusNotificationListContainerWraper$$ExternalSyntheticLambda0);
    }

    public boolean isInVisibleLocation(NotificationEntry notificationEntry) {
        return getOrgin().isInVisibleLocation(notificationEntry);
    }

    public void notifyGroupChildAdded(ExpandableView expandableView) {
        ExpandableNotificationRow row;
        if (expandableView instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) expandableView;
            if (expandableNotificationRow.getParent() != null) {
                ViewParent parent = expandableNotificationRow.getParent();
                NotificationEntry notificationEntry = this.currentEntry;
                if (Intrinsics.areEqual(parent, (notificationEntry == null || (row = notificationEntry.getRow()) == null) ? null : row.getChildrenContainer())) {
                    this.headsUpNsslStateManager.clearTempState(expandableNotificationRow);
                    ((HeadsUpLayoutController) this.headsUpLayoutController.get()).notifyGroupChildAdded(expandableView);
                    return;
                }
            }
        }
        getOrgin().notifyGroupChildAdded(expandableView);
    }

    public void notifyGroupChildRemoved(ExpandableView expandableView, ViewGroup viewGroup) {
        ExpandableNotificationRow row;
        OplusExpandableViewEx oplusExpandableViewEx;
        if (expandableView != null && (oplusExpandableViewEx = expandableView.mExpandableViewEx) != null) {
            ((OplusExpandableViewExImpl) oplusExpandableViewEx).onViewRemoved();
        }
        if ((expandableView instanceof ExpandableNotificationRow) && viewGroup != null) {
            NotificationEntry notificationEntry = this.currentEntry;
            if (viewGroup.equals((notificationEntry == null || (row = notificationEntry.getRow()) == null) ? null : row.getChildrenContainer())) {
                this.aonTransition.onRemoveView(expandableView);
                ((HeadsUpLayoutController) this.headsUpLayoutController.get()).notifyGroupChildRemoved((ExpandableNotificationRow) expandableView);
                return;
            }
        }
        getOrgin().notifyGroupChildRemoved(expandableView, viewGroup);
        this.aonTransition.onRemoveView(expandableView);
    }

    public void onHeightChanged(ExpandableView expandableView, boolean z, String str) {
        getOrgin().onHeightChanged(expandableView, z, str);
    }

    public void onReset(ExpandableView expandableView) {
        getOrgin().onReset(expandableView);
    }

    public void removeContainerView(View view) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            boolean zHasInit = this.tempViewCreator.hasInit();
            NotificationEntry notificationEntry = this.currentEntry;
            ExpandableNotificationRow row = notificationEntry != null ? notificationEntry.getRow() : null;
            Log.d("SystemUi--Notification", "OplusNotificationList-->".concat("removeContainerView: hasInit " + zHasInit + "; currentEntry.row: " + row + ";  view: " + view + "; parent: " + view.getParent() + " "));
        }
        if (view instanceof ExpandableView) {
            ((OplusExpandableViewExImpl) ((ExpandableView) view).mExpandableViewEx).onViewRemoved();
        }
        if (this.tempViewCreator.hasInit()) {
            NotificationEntry notificationEntry2 = this.currentEntry;
            if (Intrinsics.areEqual(notificationEntry2 != null ? notificationEntry2.getRow() : null, view)) {
                boolean z = false;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    NotificationEntry notificationEntry3 = this.currentEntry;
                    String logKeyNonNull = notificationEntry3 != null ? NotificationUtilsKt.getLogKeyNonNull(notificationEntry3) : null;
                    NotificationEntry notificationEntry4 = this.currentEntry;
                    Log.d("SystemUi--Notification", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("removeContainerView: need remove ", logKeyNonNull, "},", notificationEntry4 != null && notificationEntry4.isCanceled(), "OplusNotificationList-->"));
                }
                getOrgin().removeContainerView(getTempView());
                ((NotificationStackScrollLayoutControllerExtImpl) this.nsslControllerExtImpl.get()).removeViewGroupChangeAllowed(view);
                OplusHeadsUpNotificationInteractor oplusHeadsUpNotificationInteractor = (OplusHeadsUpNotificationInteractor) this.headsUpInteractor.get();
                NotificationEntry notificationEntry5 = this.currentEntry;
                if (notificationEntry5 != null && notificationEntry5.isCanceled()) {
                    z = true;
                }
                oplusHeadsUpNotificationInteractor.dismiss(z, "view removed", null);
                this.aonTransition.onRemoveView(getTempView());
                return;
            }
        }
        getOrgin().removeContainerView(view);
        this.aonTransition.onRemoveView(view);
    }

    public final void resetExposedMenuView(boolean z, boolean z2) {
        getOrgin().resetExposedMenuView(false, true);
    }

    public void setChildTransferInProgress(boolean z) {
        getOrgin().setChildTransferInProgress(z);
    }

    public void setExpandingNotification(ExpandableNotificationRow expandableNotificationRow) {
        getOrgin().setExpandingNotification(expandableNotificationRow);
    }

    @Override // com.oplus.systemui.notification.headsup.nssl.OplusHeadsUpRowChanger
    public void setHeadsUpDismissed(NotificationEntry notificationEntry) {
        ExpandableNotificationRow row;
        ExpandableNotificationRow row2;
        List attachedChildren;
        if (this.tempViewCreator.hasInit() && Intrinsics.areEqual(this.currentEntry, notificationEntry)) {
            String logKeyNonNull = NotificationUtilsKt.getLogKeyNonNull(notificationEntry);
            float translationX = getTempView().getTranslationX();
            float translationY = getTempView().getTranslationY();
            StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m("setHeadsUpDismissed: ", logKeyNonNull, ", tempViewX: ", translationX, ", tempViewY: ");
            sbM.append(translationY);
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
            this.currentEntry = null;
            ExpandableNotificationRow row3 = notificationEntry.getRow();
            if (row3 != null) {
                row3.setTranslationY(getTempView().getTranslationY());
            }
            ExpandableNotificationRow row4 = notificationEntry.getRow();
            if (row4 != null) {
                row4.setTranslationX(0.0f);
            }
            ExpandableNotificationRow row5 = notificationEntry.getRow();
            if (row5 != null) {
                row5.setFinalActualHeight(getTempView().getActualHeight());
            }
            if (getTempView().getParent() != null) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "OplusNotificationList-->setHeadsUpDismissed: need replace");
                }
                NotificationListContainer orgin = getOrgin();
                ExpandableView tempView = getTempView();
                int containerChildCount = orgin.getContainerChildCount();
                int i = 0;
                while (true) {
                    if (i >= containerChildCount) {
                        i = -1;
                        break;
                    } else if (Intrinsics.areEqual(orgin.getContainerChildAt(i), tempView)) {
                        break;
                    } else {
                        i++;
                    }
                }
                getOrgin().removeContainerView(getTempView());
                if (i != -1) {
                    getOrgin().addContainerViewAt(notificationEntry.getRow(), i);
                    if (notificationEntry.getRow().mIsSummaryWithChildren && (row2 = notificationEntry.getRow()) != null && (attachedChildren = row2.getAttachedChildren()) != null) {
                        Iterator it = attachedChildren.iterator();
                        while (it.hasNext()) {
                            getOrgin().notifyGroupChildAdded((ExpandableNotificationRow) it.next());
                        }
                    }
                    if (notificationEntry.isCanceled() || ((row = notificationEntry.getRow()) != null && row.mRemoved)) {
                        LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "setHeadsUpDismissed: has cancel");
                    }
                }
            } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                ExpandableNotificationRow row6 = notificationEntry.getRow();
                Log.d("SystemUi--Notification", "OplusNotificationList-->".concat("setHeadsUpDismissed: need skip; parent: " + (row6 != null ? row6.getParent() : null)));
            }
            if (this.waitOnViewRemoveFromParentCallback != null) {
                LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "setHeadsUpDismissed: need clear callback");
                this.waitOnViewRemoveFromParentCallback = null;
            }
        }
    }

    @Override // com.oplus.systemui.notification.headsup.nssl.OplusHeadsUpRowChanger
    public void setHeadsUpShowing(NotificationEntry notificationEntry, Function0 function0) {
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        ExpandableNotificationRow row;
        List attachedChildren;
        this.currentEntry = notificationEntry;
        if (!this.tempViewCreator.hasInit() || notificationEntry.getRow().getParent() == null) {
            this.waitOnViewRemoveFromParentCallback = function0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("setHeadsUpShowing: has not attach: ", NotificationUtilsKt.getLogKeyNonNull(notificationEntry), "OplusNotificationList-->", "SystemUi--Notification");
                return;
            }
            return;
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, FileProvider$.ExternalSyntheticOutline0.m("setHeadsUpShowing: ", NotificationUtilsKt.getLogKeyNonNull(notificationEntry)), null, 8);
        getTempView().setLayoutParams(notificationEntry.getRow().getLayoutParams());
        NotificationListContainer orgin = getOrgin();
        ExpandableNotificationRow row2 = notificationEntry.getRow();
        int containerChildCount = orgin.getContainerChildCount();
        int i = 0;
        while (true) {
            if (i >= containerChildCount) {
                i = -1;
                break;
            } else if (Intrinsics.areEqual(orgin.getContainerChildAt(i), row2)) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1) {
            this.waitOnViewRemoveFromParentCallback = function0;
            PipelineEntry parent = notificationEntry.getParent();
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "setHeadsUpShowing: find null,p=", parent != null ? NotificationUtilsKt.getLogKeyNonNull(parent) : null, LogUtil.TAG_NOTIFICATION, TAG);
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "setHeadsUpShowing: find =", "OplusNotificationList-->", "SystemUi--Notification");
        }
        getOrgin().removeContainerView(notificationEntry.getRow());
        this.aonTransition.onRemoveView(notificationEntry.getRow());
        if (notificationEntry.getRow().mIsSummaryWithChildren && (row = notificationEntry.getRow()) != null && (attachedChildren = row.getAttachedChildren()) != null) {
            Iterator it = attachedChildren.iterator();
            while (it.hasNext()) {
                getOrgin().notifyGroupChildRemoved((ExpandableNotificationRow) it.next(), notificationEntry.getRow().getChildrenContainer());
            }
        }
        ExpandableNotificationRow row3 = notificationEntry.getRow();
        if (row3 != null && (oplusExpandableNotificationRowEx = row3.mRowEx) != null) {
            ((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx).updateShouldEncrypted();
        }
        removeFromParent(getTempView());
        getOrgin().addContainerViewAt(getTempView(), i);
        if (this.waitOnViewRemoveFromParentCallback != null) {
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, "callOnViewRemoveFromParentImmediate: hasOldOne");
            this.waitOnViewRemoveFromParentCallback = null;
        }
        function0.invoke();
    }

    public void setMaxDisplayedNotifications(int i) {
        getOrgin().setMaxDisplayedNotifications(i);
    }

    public final void setOrgin(NotificationListContainer notificationListContainer) {
        this.orgin = notificationListContainer;
    }

    @Override // com.oplus.systemui.notification.headsup.nssl.OplusHeadsUpRowChanger
    public void setTempHeadsUpViewVisibility(int i) {
        if (this.tempViewCreator.hasInit()) {
            getTempView().setVisibility(i);
        }
    }
}
