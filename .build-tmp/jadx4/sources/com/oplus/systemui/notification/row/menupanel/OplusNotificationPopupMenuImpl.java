package com.oplus.systemui.notification.row.menupanel;

import android.content.Context;
import android.os.Handler;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.plugins.statusbar.NotificationSwipeActionHelper;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.statusbar.notification.animation.AlphaState;
import com.android.systemui.statusbar.notification.animation.RowAnimationStateEx;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateExKt;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.row.NotificationMenuRowProviderKt;
import com.oplus.systemui.notification.row.OplusNotificationPopupMenu;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguard;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguardImpl;
import com.oplus.systemui.statusbar.notification.helper.NotificationCenterHelper;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl;
import com.oplus.systemui.statusbar.notification.row.menupanel.NotificationPopupMenuController;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplus.systemui.util.NotificationUtil;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.Unit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationPopupMenuImpl extends OplusNotificationPopupMenu {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ExpandableNotificationRowControllerExtImpl";
    public final boolean allowLongPress;
    private final Context context;
    private final Lazy lockScreenExtStateController;
    private final Handler mMainHandler;
    private final Lazy notificationDependOnKeyguard;
    private final NotificationPopupMenuController notificationMenuPanelController;
    private final NotificationPanelViewController notificationPanelViewController;
    private final NotificationStackScrollLayoutController notificationStackScrollLayoutController;
    private final Lazy stackCardControl;
    private final StatusBarStateController statusBarStateController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.menupanel.OplusNotificationPopupMenuImpl$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ExpandableNotificationRow.LongPressListener {
        public /* synthetic */ ExpandableNotificationRow $row;
        public /* synthetic */ OplusNotificationPopupMenuImpl this$0;

        public final boolean onLongPress(ExpandableNotificationRow expandableNotificationRow, int i, int i2, NotificationMenuRowPlugin.MenuItem menuItem) {
            OplusNotificationEntryEx ex;
            OplusNotificationEntryEx ex2;
            OplusNotificationPopupMenuImpl oplusNotificationPopupMenuImpl = this.this$0;
            boolean z = NotificationStatisticUtil.sIsSwipeLeftToShowMenu;
            ExpandableNotificationRow expandableNotificationRow2 = this.$row;
            String encryptionKey = null;
            if (z) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    NotificationEntry entry = expandableNotificationRow2.getEntry();
                    if (entry != null && (ex = entry.getEx()) != null) {
                        encryptionKey = ex.getEncryptionKey();
                    }
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("click setting icon onLongPress-- ", encryptionKey, "ExpandableNotificationRowControllerExtImpl-->", "SystemUi--Notification");
                }
                OplusNotificationPopupMenuImpl.access$recordRowLongPress(oplusNotificationPopupMenuImpl, expandableNotificationRow2);
                oplusNotificationPopupMenuImpl.tryShowPopupMenu(expandableNotificationRow2, false);
                return true;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                NotificationEntry entry2 = expandableNotificationRow2.getEntry();
                if (entry2 != null && (ex2 = entry2.getEx()) != null) {
                    encryptionKey = ex2.getEncryptionKey();
                }
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onLongPress-- ", encryptionKey, "ExpandableNotificationRowControllerExtImpl-->", "SystemUi--Notification");
            }
            OplusNotificationPopupMenuImpl.access$recordRowLongPress(oplusNotificationPopupMenuImpl, expandableNotificationRow2);
            oplusNotificationPopupMenuImpl.tryShowPopupMenu(expandableNotificationRow2, true);
            return true;
        }
    }

    public static Unit $r8$lambda$fNxQdPMIg70ELy1_UZZUEDoi9GI(ExpandableNotificationRow expandableNotificationRow, OplusNotificationPopupMenuImpl oplusNotificationPopupMenuImpl, boolean z, OplusNotificationPopupMenuImpl$.ExternalSyntheticLambda0 externalSyntheticLambda0) {
        if (expandableNotificationRow.mIsHeadsUp && expandableNotificationRow.mPinnedStatus.isPinned()) {
            expandableNotificationRow.mRowEx.setBoostFrameRate(true);
        }
        boolean z2 = ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).mKeyguardOccluded;
        StatusBarStateController statusBarStateController = oplusNotificationPopupMenuImpl.statusBarStateController;
        OplusNotificationPopupMenuImpl$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new OplusNotificationPopupMenuImpl$.ExternalSyntheticLambda2();
        externalSyntheticLambda2.f$0 = externalSyntheticLambda0;
        externalSyntheticLambda2.f$1 = expandableNotificationRow;
        VarHandle.storeStoreFence();
        boolean zIsKeyguardShowing = ((NotificationDependOnKeyguardImpl) ((NotificationDependOnKeyguard) oplusNotificationPopupMenuImpl.notificationDependOnKeyguard.get())).isKeyguardShowing();
        showPopupMenu.r.1 r4 = new showPopupMenu.r.1(0);
        r4.this$0 = oplusNotificationPopupMenuImpl;
        r4.$isOnKeyGard = zIsKeyguardShowing;
        r4.$isKeyguardOccluded = z2;
        r4.$row = expandableNotificationRow;
        r4.$onAnimationEnd = externalSyntheticLambda2;
        VarHandle.storeStoreFence();
        if (statusBarStateController.getState() == 1) {
            ILockScreenExtStateController.tryGotoShadeLockState$default((ILockScreenExtStateController) oplusNotificationPopupMenuImpl.lockScreenExtStateController.get(), "LongPressRowOnLockScreen", 0.0f, r4, 20);
            externalSyntheticLambda2.invoke();
        } else if ((ScrimUtil.isMiddleOrHighAnimLevel(oplusNotificationPopupMenuImpl.context) || ScrimUtil.isBPAnimLevel()) && z) {
            OplusExpandableViewEx oplusExpandableViewEx = ((ExpandableView) expandableNotificationRow).mExpandableViewEx;
            if (oplusExpandableViewEx != null) {
                oplusExpandableViewEx.executeRunnableAnimationFinish(r4);
            }
        } else {
            r4.run();
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$wUVF2Ixub1VO_nR6fzx0G3onhEk(OplusNotificationPopupMenuImpl oplusNotificationPopupMenuImpl) {
        NotificationListContainer notificationListContainer = oplusNotificationPopupMenuImpl.notificationStackScrollLayoutController.getNotificationListContainer();
        NotificationSwipeActionHelper swipeActionHelper = notificationListContainer != null ? notificationListContainer.getSwipeActionHelper() : null;
        NotificationSwipeHelper notificationSwipeHelper = swipeActionHelper instanceof NotificationSwipeHelper ? (NotificationSwipeHelper) swipeActionHelper : null;
        if (notificationSwipeHelper != null) {
            notificationSwipeHelper.resetExposedMenuView(true, true);
        }
        oplusNotificationPopupMenuImpl.notificationStackScrollLayoutController.mView.setOverScrollAmount(0.0f, true, true, true);
        return Unit.INSTANCE;
    }

    public OplusNotificationPopupMenuImpl(Context context, NotificationPopupMenuController notificationPopupMenuController, Handler handler, Lazy lazy, boolean z, StatusBarStateController statusBarStateController, NotificationStackScrollLayoutController notificationStackScrollLayoutController, NotificationPanelViewController notificationPanelViewController, Lazy lazy2, Lazy lazy3) {
        this.context = context;
        this.notificationMenuPanelController = notificationPopupMenuController;
        this.mMainHandler = handler;
        this.notificationDependOnKeyguard = lazy;
        this.allowLongPress = z;
        this.statusBarStateController = statusBarStateController;
        this.notificationStackScrollLayoutController = notificationStackScrollLayoutController;
        this.notificationPanelViewController = notificationPanelViewController;
        this.lockScreenExtStateController = lazy2;
        this.stackCardControl = lazy3;
    }

    public static final void access$executeRunnableNsslAnimationFinish(OplusNotificationPopupMenuImpl oplusNotificationPopupMenuImpl, showPopupMenu.r.1 r3) {
        NotificationStackScrollLayout view = oplusNotificationPopupMenuImpl.notificationStackScrollLayoutController.getView();
        if (view == null || !view.mStateAnimator.isRunning()) {
            r3.run();
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "ExpandableNotificationRowControllerExtImpl-->executeRunnableNsslAnimationFinish");
        }
        executeRunnableNsslAnimationFinish.2 r0 = new executeRunnableNsslAnimationFinish.2();
        r0.$nssl = view;
        r0.$runnable = r3;
        VarHandle.storeStoreFence();
        view.runAfterAnimationFinished$1(r0);
    }

    public static final void access$recordRowLongPress(OplusNotificationPopupMenuImpl oplusNotificationPopupMenuImpl, ExpandableNotificationRow expandableNotificationRow) {
        String str;
        if (NotificationStatisticUtil.sIsSwipeLeftToShowMenu) {
            NotificationStatisticUtil.setSwipeLeftToShowMenu(false);
            str = "right_menu_settings";
        } else {
            str = "long_press";
        }
        boolean z = expandableNotificationRow.mIsHeadsUp;
        Context context = oplusNotificationPopupMenuImpl.context;
        if (z) {
            NotificationStatisticUtil.collectNotificationMenuEvent(context, expandableNotificationRow.getEntry(), "", str, 1000);
        } else {
            NotificationStatisticUtil.collectNotificationMenuEvent(context, expandableNotificationRow.getEntry(), "", str, oplusNotificationPopupMenuImpl.statusBarStateController.getState());
        }
    }

    public final boolean getAllowLongPress() {
        return this.allowLongPress;
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // com.oplus.systemui.notification.row.OplusNotificationPopupMenu
    public void init(NotificationEntry notificationEntry, ExpandableNotificationRow expandableNotificationRow) {
        super.init(notificationEntry, expandableNotificationRow);
        if (this.allowLongPress) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            anonymousClass1.$row = expandableNotificationRow;
            VarHandle.storeStoreFence();
            expandableNotificationRow.setLongPressListener(anonymousClass1);
        }
    }

    public final void tryShowPopupMenu(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        NotificationShadeWindowView notificationShadeWindowView;
        StatusBarNotification sbn;
        AlphaState rowAlpha$default;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        if (expandableNotificationRow.mIsSummaryWithChildren && (oplusExpandableNotificationRowEx = expandableNotificationRow.mRowEx) != null && ((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx).isOriginConversation()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "ExpandableNotificationRowControllerExtImpl-->tryShowPopupMenu-- isOriginConversation");
            }
            expandableNotificationRow.mExpandClickListener.onClick(expandableNotificationRow);
            return;
        }
        if (!this.notificationMenuPanelController.isPopupMenuShowing()) {
            if (expandableNotificationRow.isChildInGroup() && !expandableNotificationRow.isGroupExpanded()) {
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "canShowPopupMenu-- isChildInGroup and is not group expanded, ignore longPress!", null, 8);
            } else if (expandableNotificationRow.mExpandAnimationRunning) {
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "canShowPopupMenu-- isExpandAnimationRunning, ignore longPress!", null, 8);
            } else if (expandableNotificationRow.mIsSummaryWithChildren && expandableNotificationRow.isGroupExpanded() && expandableNotificationRow.getAttachedChildren().size() > 1) {
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "canShowPopupMenu-- isSummaryWithChildren, ignore longPress!", null, 8);
            } else {
                RowAnimationStateEx rowAnimationStateEx = OplusExpandableViewStateExKt.getRowAnimationStateEx(expandableNotificationRow);
                if (((rowAnimationStateEx == null || (rowAlpha$default = RowAnimationStateEx.getRowAlpha$default(rowAnimationStateEx)) == null) ? 1.0f : rowAlpha$default.getAlpha()) < 1.0f) {
                    LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "canShowPopupMenu-- row animationStateEx alpha < 1, ignore longPress!", null, 8);
                } else {
                    NotificationCenterHelper notificationCenterHelper = (NotificationCenterHelper) DependencyEx.sDependency.getDependency(NotificationCenterHelper.class);
                    Context context = this.context;
                    NotificationEntry entry = expandableNotificationRow.getEntry();
                    if (!notificationCenterHelper.isNoPopmenuByPkg(context, (entry == null || (sbn = entry.getSbn()) == null) ? null : sbn.getPackageName())) {
                        CentralSurfaces centralSurfaces = this.notificationPanelViewController.mCentralSurfaces;
                        if (centralSurfaces != null && (notificationShadeWindowView = centralSurfaces.getNotificationShadeWindowView()) != null) {
                            NotificationUtil.sendCancelMotionEvent(notificationShadeWindowView, " tryShowPopupMenu");
                        }
                        OplusNotificationPopupMenuImpl$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusNotificationPopupMenuImpl$.ExternalSyntheticLambda0();
                        externalSyntheticLambda0.f$0 = this;
                        VarHandle.storeStoreFence();
                        OplusNotificationPopupMenuImpl$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new OplusNotificationPopupMenuImpl$.ExternalSyntheticLambda1();
                        externalSyntheticLambda1.f$0 = expandableNotificationRow;
                        externalSyntheticLambda1.f$1 = this;
                        externalSyntheticLambda1.f$2 = z;
                        externalSyntheticLambda1.f$3 = externalSyntheticLambda0;
                        VarHandle.storeStoreFence();
                        ViewBlurProxy viewBlurProxy = ((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal.getExt().getViewBlurProxy();
                        MetaBallBlurDrawable metaBallBlurDrawableTryGetMetaBallBlurDrawable = NotificationMenuRowProviderKt.tryGetMetaBallBlurDrawable(viewBlurProxy != null ? viewBlurProxy.getBlurDrawable(null) : null);
                        if (metaBallBlurDrawableTryGetMetaBallBlurDrawable != null) {
                            NotificationMenuRowProviderKt.setBlendRange(metaBallBlurDrawableTryGetMetaBallBlurDrawable, 1, "tryShowPopupMenu");
                        }
                        tryShowPopupMenu.4 r9 = new tryShowPopupMenu.4();
                        r9.$showAction = externalSyntheticLambda1;
                        VarHandle.storeStoreFence();
                        expandableNotificationRow.post(r9);
                        return;
                    }
                    LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "canShowPopupMenu--pkg is in blacklist ", null, 8);
                }
            }
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "ExpandableNotificationRowControllerExtImpl-->canShowPopupMenu--Panel isShowing!");
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "ExpandableNotificationRowControllerExtImpl-->tryShowPopupMenu-- can not show!");
        }
    }
}
