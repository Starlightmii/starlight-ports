package com.oplus.systemui.util;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.ui.spatial.RectList$;
import com.android.launcher3.icons.IconProvider$;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.util.kotlin.UtilsKt;
import com.oplus.systemui.keyguard.helper.OplusKeyguardBottomAreaColorHelper;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateManager;
import com.oplus.systemui.notification.lockscreen.statemanager.NotificationDisplayRectCallback;
import com.oplus.systemui.notification.lockscreen.statemanager.NotificationDisplayRectCallbackWrapper;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockscreenNotificationNsslSlideHotArea {
    public static final Companion Companion = new Companion();
    private static final String TAG = "LockscreenNotificationNsslSlideHotArea";
    private final Rect boundsRect = new Rect();
    private final Context context;
    private final ILockScreenExtStateController lockScreenExtStateController;
    public final NotificationStackScrollLayout nssl;
    public float nsslContentBottom;
    public float nsslContentTop;
    private final NotificationStackScrollLayoutController nsslController;
    private final OplusKeyguardBottomAreaColorHelper oplusKeyguardBottomAreaColorHelper;

    /* JADX INFO: renamed from: com.oplus.systemui.util.LockscreenNotificationNsslSlideHotArea$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements NotificationDisplayRectCallback {
        public /* synthetic */ LockscreenNotificationNsslSlideHotArea this$0;

        @Override // com.oplus.systemui.notification.lockscreen.statemanager.NotificationDisplayRectCallback
        public final void onNotificationDisplayRectChanged(float f, float f2) {
            if (LogUtil.getNormalDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "LockscreenNotificationNsslSlideHotArea-->".concat(ListPopupWindow$.ExternalSyntheticOutline0.m("onNotificationDisplayRectChanged = [", f, OplusPreventModeData.SEPARATOR, f2, "]")));
            }
            LockscreenNotificationNsslSlideHotArea lockscreenNotificationNsslSlideHotArea = this.this$0;
            lockscreenNotificationNsslSlideHotArea.nsslContentTop = f;
            lockscreenNotificationNsslSlideHotArea.nsslContentBottom = f2;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public LockscreenNotificationNsslSlideHotArea(Context context, NotificationStackScrollLayoutController notificationStackScrollLayoutController, ILockScreenExtStateController iLockScreenExtStateController, OplusKeyguardBottomAreaColorHelper oplusKeyguardBottomAreaColorHelper) {
        NotificationDisplayRectCallbackWrapper notificationDisplayRectCallbackWrapper;
        this.context = context;
        this.nsslController = notificationStackScrollLayoutController;
        this.lockScreenExtStateController = iLockScreenExtStateController;
        this.oplusKeyguardBottomAreaColorHelper = oplusKeyguardBottomAreaColorHelper;
        this.nssl = notificationStackScrollLayoutController.getView();
        LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager = iLockScreenExtStateController instanceof LockScreenNotificationExtStateManager ? (LockScreenNotificationExtStateManager) iLockScreenExtStateController : null;
        if (lockScreenNotificationExtStateManager == null || (notificationDisplayRectCallbackWrapper = lockScreenNotificationExtStateManager.getNotificationDisplayRectCallbackWrapper()) == null) {
            return;
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        notificationDisplayRectCallbackWrapper.addNotificationDisplayRectCallback(anonymousClass1, true);
    }

    private final int getLeftBoarder() {
        return Math.max(getNsslBound().left, getNsslSideMargin());
    }

    private final int getLockScreenBottomAreaTop() {
        return this.oplusKeyguardBottomAreaColorHelper.getColorRect().top;
    }

    private final int getLockScreenTopPadding() {
        return this.nsslController.getTopPadding();
    }

    private final Rect getNsslBound() {
        this.nssl.getBoundsOnScreen(this.boundsRect);
        return this.boundsRect;
    }

    private final int getNsslSideMargin() {
        return UtilsKt.toPx(16, this.context);
    }

    private final int getRightBoarder() {
        return Math.min(getNsslBound().right, UtilsKt.toPx(this.context.getResources().getConfiguration().screenWidthDp, this.context) - getNsslSideMargin());
    }

    public final boolean isInSlideDownHotArea(float f, float f2) {
        if (LogUtil.getNormalDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            int leftBoarder = getLeftBoarder();
            int lockScreenTopPadding = getLockScreenTopPadding();
            int rightBoarder = getRightBoarder();
            int lockScreenBottomAreaTop = getLockScreenBottomAreaTop();
            float f3 = this.nsslContentBottom;
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("isInSlideDownHotArea = [", f, OplusPreventModeData.SEPARATOR, f2, "][");
            RectList$.ExternalSyntheticOutline0.m(leftBoarder, lockScreenTopPadding, OplusPreventModeData.SEPARATOR, OplusPreventModeData.SEPARATOR, sbM);
            RectList$.ExternalSyntheticOutline0.m(rightBoarder, lockScreenBottomAreaTop, OplusPreventModeData.SEPARATOR, OplusPreventModeData.SEPARATOR, sbM);
            sbM.append(f3);
            sbM.append("]");
            Log.d("SystemUi--Notification", "LockscreenNotificationNsslSlideHotArea-->".concat(sbM.toString()));
        }
        return f >= ((float) getLeftBoarder()) && f2 >= ((float) getLockScreenTopPadding()) && f <= ((float) getRightBoarder()) && f2 <= Math.max((float) getLockScreenBottomAreaTop(), this.nsslContentBottom);
    }

    public final boolean isInSlideUpHotArea(float f, float f2) {
        if (LogUtil.getNormalDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            int leftBoarder = getLeftBoarder();
            float f3 = this.nsslContentTop;
            int rightBoarder = getRightBoarder();
            float f4 = this.nsslContentBottom;
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("isInSlideDownHotArea = [", f, OplusPreventModeData.SEPARATOR, f2, "][");
            IconProvider$.ExternalSyntheticOutline0.m(f3, leftBoarder, OplusPreventModeData.SEPARATOR, OplusPreventModeData.SEPARATOR, sbM);
            sbM.append(rightBoarder);
            sbM.append(OplusPreventModeData.SEPARATOR);
            sbM.append(f4);
            sbM.append("]");
            Log.d("SystemUi--Notification", "LockscreenNotificationNsslSlideHotArea-->".concat(sbM.toString()));
        }
        return f >= ((float) getLeftBoarder()) && f2 >= this.nsslContentTop && f <= ((float) getRightBoarder()) && f2 <= this.nsslContentBottom;
    }

    public final boolean isTouchInNsslHotAreaForImmersiveState(float f, float f2) {
        return f > ((float) getLeftBoarder()) && f < ((float) getRightBoarder()) && f2 > ((float) this.nsslController.getTopPadding()) && !this.nsslController.mView.isBelowLastNotification(f, f2);
    }
}
