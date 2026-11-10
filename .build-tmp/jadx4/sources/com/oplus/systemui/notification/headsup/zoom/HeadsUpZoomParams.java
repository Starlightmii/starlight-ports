package com.oplus.systemui.notification.headsup.zoom;

import android.content.Context;
import android.util.Log;
import androidx.compose.animation.core.MonoSpline$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.android.systemui.statusbar.notification.animation.RowAnimationStateEx;
import com.android.systemui.statusbar.notification.animation.ScaleState;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundViewExt;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpAnimator;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerWindow;
import com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import kotlin.jvm.internal.Intrinsics;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpZoomParams extends HeadsUpAnimator.NormalHeadsUpParams {
    private static final Companion Companion = new Companion();
    private static final String TAG = "HeadsUpZoomParams";
    private final Lazy activityStarter;
    private final AmbientState ambientState;
    private final Context context;
    private final Lazy headsUpAnimator;
    private final HeadsUpContainerWindow headsUpContainer;
    public boolean isStartedZoomAnimation;
    public final int minScaleHeight;
    public final int minZoomHeight;
    private ExpandableNotificationRow view;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public HeadsUpZoomParams(Context context, Lazy lazy, AmbientState ambientState, Lazy lazy2, HeadsUpContainerWindow headsUpContainerWindow) {
        super(ambientState);
        this.context = context;
        this.activityStarter = lazy;
        this.ambientState = ambientState;
        this.headsUpAnimator = lazy2;
        this.headsUpContainer = headsUpContainerWindow;
        this.minScaleHeight = context.getResources().getDimensionPixelSize(2131170810);
        this.minZoomHeight = context.getResources().getDimensionPixelSize(2131170674);
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpAnimator.NormalHeadsUpParams
    public final boolean endHeadsUpTrackingEvent() {
        if (!this.isStartedZoomAnimation) {
            return false;
        }
        this.isStartedZoomAnimation = false;
        return true;
    }

    public final Context getContext() {
        return this.context;
    }

    public final ExpandableNotificationRow getView() {
        return this.view;
    }

    @Override // com.oplus.systemui.notification.headsup.windowframe.HeadsUpAnimator.NormalHeadsUpParams, com.oplus.systemui.notification.headsup.windowframe.HeadsUpAnimator.HeadsUpParams
    public Float onTouchMove(HeadsUpAnimator.MoveData moveData) {
        float deltaY;
        NotificationBackgroundView notificationBackgroundView;
        NotificationBackgroundViewExt ext;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        RowAnimationStateEx rowAnimationStateEx;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "HeadsUpZoomParams-->isStartedZoomAnimation start!");
        }
        if (this.view == null) {
            Path$.ExternalSyntheticBUOutline0.m("view must be set");
            return null;
        }
        if (!this.isStartedZoomAnimation) {
            float maxHeadsUpPullHeight = this.ambientState.getExt().getMaxHeadsUpPullHeight();
            moveData.getHoldY();
            moveData.getCurrY();
            if (moveData.getStartY() > moveData.getHoldY()) {
                deltaY = (moveData.getDeltaY() / 3.0f) + moveData.getStartY();
            } else {
                deltaY = moveData.getDeltaY() + moveData.getStartY() > moveData.getHoldY() ? (((moveData.getDeltaY() + moveData.getStartY()) - moveData.getHoldY()) / 3.0f) + moveData.getHoldY() : moveData.getDeltaY() + moveData.getStartY();
            }
            if (deltaY != moveData.getCurrY()) {
                float holdY = deltaY - moveData.getHoldY();
                ExpandableNotificationRow expandableNotificationRow = this.view;
                if (!Intrinsics.areEqual(expandableNotificationRow != null ? Float.valueOf(expandableNotificationRow.getScaleX()) : null, 1.0f)) {
                    float f = this.minScaleHeight;
                    if (holdY > f) {
                        float f2 = maxHeadsUpPullHeight - f;
                        if (f2 > 0.0f) {
                            float f3 = ((holdY - f) * 0.050000012f) / f2;
                            resetZoomScalex(1.0f - (0.050000012f > f3 ? f3 : 0.050000012f));
                        }
                    } else {
                        ExpandableNotificationRow trackedHeadsUpRow = this.ambientState.getTrackedHeadsUpRow();
                        if (trackedHeadsUpRow != null) {
                            trackedHeadsUpRow.setScaleX(1.0f);
                        }
                        ExpandableNotificationRow trackedHeadsUpRow2 = this.ambientState.getTrackedHeadsUpRow();
                        if (trackedHeadsUpRow2 != null && (rowAnimationStateEx = HeadsUpZoomParamsKt.getRowAnimationStateEx(trackedHeadsUpRow2)) != null) {
                            ((RowAnimationStateExImpl) rowAnimationStateEx).requestRowScale("headsup_zoom", ScaleState.Companion.getINVALID());
                        }
                        resetZoomScalex(1.0f);
                    }
                }
            }
            float currY = moveData.getCurrY() - moveData.getHoldY();
            float fFloatValue = ((Number) moveData.getVelocityYGetter().invoke()).floatValue();
            float f4 = this.minZoomHeight;
            if (currY < f4) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "HeadsUpZoomParams-->startZoomAnimator::curPullHeight < minZoomHeight");
                }
                this.isStartedZoomAnimation = false;
            } else if (!this.isStartedZoomAnimation) {
                if ((fFloatValue <= 6000.0f || currY < f4) && currY < maxHeadsUpPullHeight) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "HeadsUpZoomParams-->startZoomAnimator::isStartedZoomAnimation = false");
                    }
                    this.isStartedZoomAnimation = false;
                } else {
                    boolean zOnStartFloatWindow = ((StatusBarNotificationActivityStarter) this.activityStarter.get()).getActivityStarterEx().onStartFloatWindow((StatusBarNotificationActivityStarter) this.activityStarter.get(), this.view, fFloatValue);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("HeadsUpZoomParams-->", "SystemUi--Notification", MonoSpline$.ExternalSyntheticOutline0.m("startZoomAnimator,velocityTrackerY:", fFloatValue, ",curPullHeight:", currY, " "), zOnStartFloatWindow);
                    }
                    if (zOnStartFloatWindow) {
                        ExpandableNotificationRow expandableNotificationRow2 = this.view;
                        if (expandableNotificationRow2 != null && (oplusExpandableNotificationRowEx = expandableNotificationRow2.mRowEx) != null) {
                            oplusExpandableNotificationRowEx.setIsZoomBackUpHeadsUp(false);
                        }
                        this.isStartedZoomAnimation = true;
                        ExpandableNotificationRow expandableNotificationRow3 = this.view;
                        if (expandableNotificationRow3 != null && (notificationBackgroundView = ((ActivatableNotificationView) expandableNotificationRow3).mBackgroundNormal) != null && (ext = notificationBackgroundView.getExt()) != null) {
                            ext.onHeadsUpStateChanged(false);
                        }
                        this.headsUpContainer.ignoreTouchMotionCallback();
                    }
                }
            }
            if (!this.isStartedZoomAnimation) {
                return Float.valueOf(deltaY);
            }
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "HeadsUpZoomParams-->isStartedZoomAnimation");
            return null;
        }
        return null;
    }

    public final void resetZoomScalex(float f) {
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx2;
        RowAnimationStateEx rowAnimationStateEx;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("resetZoomScalex::", f, "HeadsUpZoomParams-->", "SystemUi--Notification");
        }
        ExpandableNotificationRow expandableNotificationRow = this.view;
        if (expandableNotificationRow != null && (rowAnimationStateEx = HeadsUpZoomParamsKt.getRowAnimationStateEx(expandableNotificationRow)) != null) {
            ((RowAnimationStateExImpl) rowAnimationStateEx).requestRowScale("headsup_zoom", ScaleState.Companion.invoke(f));
        }
        ExpandableNotificationRow expandableNotificationRow2 = this.view;
        if (expandableNotificationRow2 != null) {
            expandableNotificationRow2.setScaleX(f);
        }
        ExpandableNotificationRow expandableNotificationRow3 = this.view;
        if (f == 1.0f) {
            if (expandableNotificationRow3 == null || (oplusExpandableNotificationRowEx2 = expandableNotificationRow3.mRowEx) == null) {
                return;
            }
            oplusExpandableNotificationRowEx2.setIsZoomBackUpHeadsUp(false);
            return;
        }
        if (expandableNotificationRow3 == null || (oplusExpandableNotificationRowEx = expandableNotificationRow3.mRowEx) == null) {
            return;
        }
        oplusExpandableNotificationRowEx.setIsZoomBackUpHeadsUp(true);
    }

    public final void setView(ExpandableNotificationRow expandableNotificationRow) {
        this.view = expandableNotificationRow;
    }
}
