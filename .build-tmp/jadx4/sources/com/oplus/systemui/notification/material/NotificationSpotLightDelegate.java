package com.oplus.systemui.notification.material;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Trace;
import android.support.v4.content.FileProvider$;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatDelegateImpl$;
import androidx.collection.FloatList$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.Dependency;
import com.android.systemui.controls.management.StructureAdapter$StructureHolder$;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.coui.appcompat.spotlight.COUISpotLightEffectDrawable;
import com.oplus.systemui.notification.base.radius.NotificationPath;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSpotLightDelegate {
    private static final String TAG = "NotificationSpotLightDelegate";
    private final Runnable availabilityListener;
    private ClipSpec cachedClipSpec;
    public int capsuleSpotLightAlpha;
    private final Path defaultClipPath;
    private final RectF defaultClipRectF;
    private final NotificationPath defaultOplusPath;
    private final NotificationSpotLightDelegate$detachListener$1 detachListener;
    private final NotificationSpotLightDelegate$drawableCallback$1 drawableCallback;
    private KeyguardStateController keyguardStateController;
    private SpotLightPressPhase pressPhase;
    public float radius;
    private View sessionHost;
    private final Lazy spotLightDrawable$delegate;
    private final Runnable uiModeListener;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, new NotificationSpotLightDelegate$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ClipSpec {
        public final int bottom;
        public final float bottomCornerRadius;
        public final int left;
        public final int right;
        public final int top;
        public final float topCornerRadius;
        public final boolean useSmoothRadius;

        public ClipSpec(int i, int i2, int i3, int i4, float f, float f2, boolean z) {
            this.left = i;
            this.top = i2;
            this.right = i3;
            this.bottom = i4;
            this.topCornerRadius = f;
            this.bottomCornerRadius = f2;
            this.useSmoothRadius = z;
        }

        public final ClipSpec copy(int i, int i2, int i3, int i4, float f, float f2, boolean z) {
            return new ClipSpec(i, i2, i3, i4, f, f2, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ClipSpec)) {
                return false;
            }
            ClipSpec clipSpec = (ClipSpec) obj;
            return this.left == clipSpec.left && this.top == clipSpec.top && this.right == clipSpec.right && this.bottom == clipSpec.bottom && Float.compare(this.topCornerRadius, clipSpec.topCornerRadius) == 0 && Float.compare(this.bottomCornerRadius, clipSpec.bottomCornerRadius) == 0 && this.useSmoothRadius == clipSpec.useSmoothRadius;
        }

        public final int getBottom() {
            return this.bottom;
        }

        public final float getBottomCornerRadius() {
            return this.bottomCornerRadius;
        }

        public final int getHeight() {
            return this.bottom - this.top;
        }

        public final int getLeft() {
            return this.left;
        }

        public final int getRight() {
            return this.right;
        }

        public final int getTop() {
            return this.top;
        }

        public final float getTopCornerRadius() {
            return this.topCornerRadius;
        }

        public final boolean getUseSmoothRadius() {
            return this.useSmoothRadius;
        }

        public final int getWidth() {
            return this.right - this.left;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.useSmoothRadius) + ChangeSize$.ExternalSyntheticOutline0.m(this.bottomCornerRadius, ChangeSize$.ExternalSyntheticOutline0.m(this.topCornerRadius, MonoSpline$.ExternalSyntheticOutline0.m(this.bottom, MonoSpline$.ExternalSyntheticOutline0.m(this.right, MonoSpline$.ExternalSyntheticOutline0.m(this.top, Integer.hashCode(this.left) * 31, 31), 31), 31), 31), 31);
        }

        public String toString() {
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("ClipSpec(left=", this.left, this.top, ", top=", ", right=");
            RectList$.ExternalSyntheticOutline0.m(this.right, this.bottom, ", bottom=", ", topCornerRadius=", sbM);
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.topCornerRadius, ", bottomCornerRadius=", this.bottomCornerRadius, ", useSmoothRadius=");
            return AppCompatDelegateImpl$.ExternalSyntheticOutline0.m(")", sbM, this.useSmoothRadius);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface NotificationSpotLightClipPathProvider {
        ClipSpec getClipSpec();
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class SpotLightPressPhase {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ SpotLightPressPhase[] $VALUES;
        public static final SpotLightPressPhase ENDING_WAIT_EXTINGUISH;
        public static final SpotLightPressPhase GLOWING;
        public static final SpotLightPressPhase OUTSIDE_SETTLED;
        public static final SpotLightPressPhase OUTSIDE_WAIT_EXTINGUISH;
        public static final SpotLightPressPhase SWIPE_EXPAND_SETTLED;
        public static final SpotLightPressPhase SWIPE_EXPAND_WAIT_EXTINGUISH;
        public static final SpotLightPressPhase UNDEFINED;

        static {
            SpotLightPressPhase spotLightPressPhase = new SpotLightPressPhase("UNDEFINED", 0);
            UNDEFINED = spotLightPressPhase;
            SpotLightPressPhase spotLightPressPhase2 = new SpotLightPressPhase("GLOWING", 1);
            GLOWING = spotLightPressPhase2;
            SpotLightPressPhase spotLightPressPhase3 = new SpotLightPressPhase("OUTSIDE_WAIT_EXTINGUISH", 2);
            OUTSIDE_WAIT_EXTINGUISH = spotLightPressPhase3;
            SpotLightPressPhase spotLightPressPhase4 = new SpotLightPressPhase("OUTSIDE_SETTLED", 3);
            OUTSIDE_SETTLED = spotLightPressPhase4;
            SpotLightPressPhase spotLightPressPhase5 = new SpotLightPressPhase("ENDING_WAIT_EXTINGUISH", 4);
            ENDING_WAIT_EXTINGUISH = spotLightPressPhase5;
            SpotLightPressPhase spotLightPressPhase6 = new SpotLightPressPhase("SWIPE_EXPAND_WAIT_EXTINGUISH", 5);
            SWIPE_EXPAND_WAIT_EXTINGUISH = spotLightPressPhase6;
            SpotLightPressPhase spotLightPressPhase7 = new SpotLightPressPhase("SWIPE_EXPAND_SETTLED", 6);
            SWIPE_EXPAND_SETTLED = spotLightPressPhase7;
            SpotLightPressPhase[] spotLightPressPhaseArr = {spotLightPressPhase, spotLightPressPhase2, spotLightPressPhase3, spotLightPressPhase4, spotLightPressPhase5, spotLightPressPhase6, spotLightPressPhase7};
            $VALUES = spotLightPressPhaseArr;
            $ENTRIES = EnumEntriesKt.enumEntries(spotLightPressPhaseArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static SpotLightPressPhase valueOf(String str) {
            return (SpotLightPressPhase) Enum.valueOf(SpotLightPressPhase.class, str);
        }

        public static SpotLightPressPhase[] values() {
            return (SpotLightPressPhase[]) $VALUES.clone();
        }
    }

    public NotificationSpotLightDelegate() {
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        NotificationSpotLightDelegate$$ExternalSyntheticLambda1 notificationSpotLightDelegate$$ExternalSyntheticLambda1 = new NotificationSpotLightDelegate$$ExternalSyntheticLambda1();
        notificationSpotLightDelegate$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.spotLightDrawable$delegate = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, notificationSpotLightDelegate$$ExternalSyntheticLambda1);
        this.pressPhase = SpotLightPressPhase.UNDEFINED;
        NotificationSpotLightDelegate$uiModeListener$1 notificationSpotLightDelegate$uiModeListener$1 = new NotificationSpotLightDelegate$uiModeListener$1(1);
        notificationSpotLightDelegate$uiModeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.availabilityListener = notificationSpotLightDelegate$uiModeListener$1;
        NotificationSpotLightDelegate$uiModeListener$1 notificationSpotLightDelegate$uiModeListener$2 = new NotificationSpotLightDelegate$uiModeListener$1(0);
        notificationSpotLightDelegate$uiModeListener$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.uiModeListener = notificationSpotLightDelegate$uiModeListener$2;
        Path path = new Path();
        this.defaultClipPath = path;
        this.defaultClipRectF = new RectF();
        this.defaultOplusPath = new NotificationPath(path);
        this.capsuleSpotLightAlpha = 255;
        NotificationSpotLightDelegate$drawableCallback$1 notificationSpotLightDelegate$drawableCallback$1 = new NotificationSpotLightDelegate$drawableCallback$1();
        notificationSpotLightDelegate$drawableCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.drawableCallback = notificationSpotLightDelegate$drawableCallback$1;
        NotificationSpotLightDelegate$detachListener$1 notificationSpotLightDelegate$detachListener$1 = new NotificationSpotLightDelegate$detachListener$1();
        notificationSpotLightDelegate$detachListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.detachListener = notificationSpotLightDelegate$detachListener$1;
    }

    public static final void access$drawInternal(NotificationSpotLightDelegate notificationSpotLightDelegate, View view, Canvas canvas, NotificationSpotLightClipPathProvider notificationSpotLightClipPathProvider) {
        SpotLightPressPhase spotLightPressPhase;
        SpotLightPressPhase spotLightPressPhase2;
        SpotLightPressPhase spotLightPressPhase3;
        SpotLightPressPhase spotLightPressPhase4;
        SpotLightPressPhase spotLightPressPhase5;
        notificationSpotLightDelegate.getClass();
        if (!NotificationSpotLightEffectManager.spotLightEnabled || notificationSpotLightDelegate.sessionHost != view || (spotLightPressPhase = notificationSpotLightDelegate.pressPhase) == (spotLightPressPhase2 = SpotLightPressPhase.UNDEFINED) || spotLightPressPhase == (spotLightPressPhase3 = SpotLightPressPhase.OUTSIDE_SETTLED) || spotLightPressPhase == (spotLightPressPhase4 = SpotLightPressPhase.SWIPE_EXPAND_SETTLED)) {
            return;
        }
        ClipSpec clipSpec = null;
        if (notificationSpotLightClipPathProvider != null) {
            ClipSpec clipSpec2 = notificationSpotLightClipPathProvider.getClipSpec();
            if (clipSpec2 != null && clipSpec2.getWidth() > 0 && clipSpec2.getHeight() > 0) {
                clipSpec = clipSpec2;
            }
        } else {
            int width = view.getWidth();
            int iSpotLightHostHeight = spotLightHostHeight(view);
            if (width > 0 && iSpotLightHostHeight > 0) {
                float f = notificationSpotLightDelegate.radius;
                clipSpec = new ClipSpec(0, 0, width, iSpotLightHostHeight, f, f, true);
            }
        }
        if (clipSpec == null) {
            return;
        }
        if (NotificationSpotLightEffectManager.spotLightEnabled && (spotLightPressPhase5 = notificationSpotLightDelegate.pressPhase) != spotLightPressPhase2 && spotLightPressPhase5 != spotLightPressPhase3 && spotLightPressPhase5 != spotLightPressPhase4) {
            int width2 = view.getWidth();
            int iSpotLightHostHeight2 = spotLightHostHeight(view);
            if (width2 > 0 && iSpotLightHostHeight2 > 0) {
                notificationSpotLightDelegate.getSpotLightDrawable().setBounds(0, 0, width2, iSpotLightHostHeight2);
            }
        }
        notificationSpotLightDelegate.getSpotLightDrawable().setAlpha(view instanceof CapsuleItemView ? notificationSpotLightDelegate.capsuleSpotLightAlpha : 255);
        if (!clipSpec.equals(notificationSpotLightDelegate.cachedClipSpec)) {
            notificationSpotLightDelegate.cachedClipSpec = clipSpec;
            notificationSpotLightDelegate.defaultClipPath.reset();
            notificationSpotLightDelegate.defaultClipRectF.set(clipSpec.getLeft(), clipSpec.getTop(), clipSpec.getRight(), clipSpec.getBottom());
            if (clipSpec.getUseSmoothRadius()) {
                notificationSpotLightDelegate.defaultOplusPath.addSmoothRoundRect(notificationSpotLightDelegate.defaultClipRectF, clipSpec.getTopCornerRadius(), clipSpec.getBottomCornerRadius());
            } else {
                notificationSpotLightDelegate.defaultClipPath.addRoundRect(notificationSpotLightDelegate.defaultClipRectF, clipSpec.getTopCornerRadius(), clipSpec.getBottomCornerRadius(), Path.Direction.CW);
            }
        }
        Path path = notificationSpotLightDelegate.defaultClipPath;
        int iSave = canvas.save();
        canvas.clipPath(path);
        try {
            notificationSpotLightDelegate.getSpotLightDrawable().draw(canvas);
        } finally {
            canvas.restoreToCount(iSave);
        }
    }

    public static final void access$onSpotLightProgress(NotificationSpotLightDelegate notificationSpotLightDelegate, COUISpotLightEffectDrawable cOUISpotLightEffectDrawable, float f) {
        if (cOUISpotLightEffectDrawable == notificationSpotLightDelegate.getSpotLightDrawable() && notificationSpotLightDelegate.sessionHost != null && f <= 0.01f) {
            int iOrdinal = notificationSpotLightDelegate.pressPhase.ordinal();
            if (iOrdinal == 2) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "NotificationSpotLightDelegate-->[NotifCard-SpotLight] extinguish finish (paused outside)");
                }
                notificationSpotLightDelegate.setPressPhase(SpotLightPressPhase.OUTSIDE_SETTLED);
                return;
            }
            if (iOrdinal != 4) {
                if (iOrdinal != 5) {
                    return;
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "NotificationSpotLightDelegate-->[NotifCard-SpotLight] extinguish finish (swipe expand suppressed)");
                }
                notificationSpotLightDelegate.setPressPhase(SpotLightPressPhase.SWIPE_EXPAND_SETTLED);
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationSpotLightDelegate-->[NotifCard-SpotLight] extinguish finish");
            }
            notificationSpotLightDelegate.setPressPhase(SpotLightPressPhase.UNDEFINED);
            notificationSpotLightDelegate.getSpotLightDrawable().setCallback(null);
            Trace.traceBegin(8L, "NotifCard-SpotLight finish");
            notificationSpotLightDelegate.getSpotLightDrawable().setEnabled(false);
            Trace.traceEnd(8L);
            notificationSpotLightDelegate.sessionHost = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:63:0x0151  */
    public static final void access$onTouchEventInternal(NotificationSpotLightDelegate notificationSpotLightDelegate, View view, MotionEvent motionEvent) {
        SpotLightPressPhase spotLightPressPhase;
        NotificationSpotLightGestureCoordinator notificationSpotLightGestureCoordinator;
        int iFindOwnedPointerIndex;
        notificationSpotLightDelegate.getClass();
        if (NotificationSpotLightEffectManager.spotLightEnabled) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                NotificationSpotLightGestureCoordinator notificationSpotLightGestureCoordinator2 = NotificationSpotLightGestureCoordinator.INSTANCE;
                notificationSpotLightGestureCoordinator2.onDown(view, motionEvent);
                if (notificationSpotLightGestureCoordinator2.isOwner(view, notificationSpotLightGestureCoordinator2.pointerIdForAction(motionEvent))) {
                    String logKey = notificationSpotLightGestureCoordinator2.getLogKey(view);
                    float x = motionEvent.getX(0);
                    float y = motionEvent.getY(0);
                    StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m("[NotifCard-SpotLight] ACTION_DOWN,key=", logKey, ",pos=", x, " - ");
                    sbM.append(y);
                    LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
                    View view2 = notificationSpotLightDelegate.sessionHost;
                    if (view2 != null && view2 != view) {
                        notificationSpotLightGestureCoordinator2.releaseAllForHost(view2);
                        notificationSpotLightDelegate.getSpotLightDrawable().cancel();
                        notificationSpotLightDelegate.getSpotLightDrawable().setCallback(null);
                        notificationSpotLightDelegate.sessionHost = null;
                        notificationSpotLightDelegate.setPressPhase(SpotLightPressPhase.UNDEFINED);
                        view2.invalidate();
                    }
                    notificationSpotLightDelegate.sessionHost = view;
                    notificationSpotLightDelegate.radius = StructureAdapter$StructureHolder$.ExternalSyntheticOutline0.m(2131170626, view);
                    notificationSpotLightDelegate.getSpotLightDrawable().setEnabled(NotificationSpotLightEffectManager.spotLightEnabled);
                    notificationSpotLightDelegate.getSpotLightDrawable().setCallback(notificationSpotLightDelegate.drawableCallback);
                    Object tag = view.getTag(2131364204);
                    Boolean bool = Boolean.TRUE;
                    if (!Intrinsics.areEqual(tag, bool)) {
                        view.setTag(2131364204, bool);
                        view.addOnAttachStateChangeListener(notificationSpotLightDelegate.detachListener);
                    }
                    notificationSpotLightDelegate.setPressPhase(SpotLightPressPhase.GLOWING);
                    notificationSpotLightDelegate.applySpotLightParamsForCurrentUiMode();
                    notificationSpotLightDelegate.setLightPosition(0, motionEvent, view);
                    Trace.traceBegin(8L, "NotifCard-SpotLight glow");
                    notificationSpotLightDelegate.getSpotLightDrawable().glow();
                    Trace.traceEnd(8L);
                    return;
                }
                return;
            }
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (notificationSpotLightDelegate.sessionHost == view && (spotLightPressPhase = notificationSpotLightDelegate.pressPhase) != SpotLightPressPhase.SWIPE_EXPAND_WAIT_EXTINGUISH && spotLightPressPhase != SpotLightPressPhase.SWIPE_EXPAND_SETTLED && (iFindOwnedPointerIndex = (notificationSpotLightGestureCoordinator = NotificationSpotLightGestureCoordinator.INSTANCE).findOwnedPointerIndex(view, motionEvent)) >= 0) {
                        float x2 = motionEvent.getX(iFindOwnedPointerIndex);
                        float y2 = motionEvent.getY(iFindOwnedPointerIndex);
                        int width = view.getWidth();
                        int iSpotLightHostHeight = spotLightHostHeight(view);
                        COUISpotLightEffectDrawable spotLightDrawable = notificationSpotLightDelegate.getSpotLightDrawable();
                        if (x2 < 0.0f || y2 < 0.0f || x2 > width || y2 > iSpotLightHostHeight) {
                            if (notificationSpotLightDelegate.pressPhase == SpotLightPressPhase.GLOWING) {
                                spotLightDrawable.extinguish();
                                StringBuilder sbM2 = ConstraintSet$.ExternalSyntheticOutline0.m("[NotifCard-SpotLight] ACTION_MOVE out of bounds,key=", notificationSpotLightGestureCoordinator.getLogKey(view), ",pos=", x2, " - ");
                                sbM2.append(y2);
                                sbM2.append(", idx=");
                                sbM2.append(iFindOwnedPointerIndex);
                                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM2.toString(), null, 8);
                                notificationSpotLightDelegate.setPressPhase(SpotLightPressPhase.OUTSIDE_WAIT_EXTINGUISH);
                                return;
                            }
                            return;
                        }
                        SpotLightPressPhase spotLightPressPhase2 = notificationSpotLightDelegate.pressPhase;
                        if (spotLightPressPhase2 == SpotLightPressPhase.OUTSIDE_WAIT_EXTINGUISH || spotLightPressPhase2 == SpotLightPressPhase.OUTSIDE_SETTLED) {
                            spotLightDrawable.glow();
                            StringBuilder sbM3 = ConstraintSet$.ExternalSyntheticOutline0.m("[NotifCard-SpotLight] ACTION_MOVE inside bounds,key=", notificationSpotLightGestureCoordinator.getLogKey(view), ",pos=", x2, " - ");
                            sbM3.append(y2);
                            sbM3.append(", idx=");
                            sbM3.append(iFindOwnedPointerIndex);
                            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM3.toString(), null, 8);
                            notificationSpotLightDelegate.setPressPhase(SpotLightPressPhase.GLOWING);
                        }
                        notificationSpotLightDelegate.setLightPosition(iFindOwnedPointerIndex, motionEvent, view);
                        return;
                    }
                    return;
                }
                if (actionMasked != 3 && actionMasked != 6) {
                    return;
                }
            }
            if (notificationSpotLightDelegate.sessionHost != view) {
                return;
            }
            NotificationSpotLightGestureCoordinator notificationSpotLightGestureCoordinator3 = NotificationSpotLightGestureCoordinator.INSTANCE;
            int iPointerIdForAction = notificationSpotLightGestureCoordinator3.pointerIdForAction(motionEvent);
            if (notificationSpotLightGestureCoordinator3.isOwner(view, iPointerIdForAction)) {
                int actionMasked2 = motionEvent.getActionMasked();
                String logKey2 = notificationSpotLightGestureCoordinator3.getLogKey(view);
                float x3 = motionEvent.getX(0);
                float y3 = motionEvent.getY(0);
                StringBuilder sbM4 = KeyguardLogger$.ExternalSyntheticOutline0.m(actionMasked2, "[NotifCard-SpotLight] ACTION_UP/ACTION_CANCEL=", ",key=", logKey2, ",pos=");
                sbM4.append(x3);
                sbM4.append(" - ");
                sbM4.append(y3);
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM4.toString(), null, 8);
                int iOrdinal = notificationSpotLightDelegate.pressPhase.ordinal();
                if (iOrdinal == 1) {
                    notificationSpotLightDelegate.getSpotLightDrawable().extinguish();
                    notificationSpotLightDelegate.setPressPhase(SpotLightPressPhase.ENDING_WAIT_EXTINGUISH);
                } else if (iOrdinal == 2) {
                    notificationSpotLightDelegate.setPressPhase(SpotLightPressPhase.ENDING_WAIT_EXTINGUISH);
                } else if (iOrdinal == 3) {
                    notificationSpotLightDelegate.getSpotLightDrawable().extinguish();
                    notificationSpotLightDelegate.setPressPhase(SpotLightPressPhase.ENDING_WAIT_EXTINGUISH);
                } else if (iOrdinal == 5) {
                    notificationSpotLightDelegate.setPressPhase(SpotLightPressPhase.ENDING_WAIT_EXTINGUISH);
                } else if (iOrdinal == 6) {
                    notificationSpotLightDelegate.setPressPhase(SpotLightPressPhase.UNDEFINED);
                    notificationSpotLightDelegate.getSpotLightDrawable().setCallback(null);
                    Trace.traceBegin(8L, "NotifCard-SpotLight finish");
                    notificationSpotLightDelegate.getSpotLightDrawable().setEnabled(false);
                    Trace.traceEnd(8L);
                    notificationSpotLightDelegate.sessionHost = null;
                }
                notificationSpotLightDelegate.setLightPosition(0, motionEvent, view);
                NotificationSpotLightGestureCoordinator.releasePointer(iPointerIdForAction);
                notificationSpotLightGestureCoordinator3.releaseAllForHost(view);
            }
        }
    }

    public static final void access$registerGlobalListeners(NotificationSpotLightDelegate notificationSpotLightDelegate) {
        NotificationSpotLightEffectManager.addOnSpotLightAvailabilityChangedListener(notificationSpotLightDelegate.availabilityListener);
        NotificationSpotLightEffectManager.addOnSpotLightUiModeChangedListener(notificationSpotLightDelegate.uiModeListener);
    }

    public static final void access$suppressUntilPointerUpInternal(NotificationSpotLightDelegate notificationSpotLightDelegate, View view, boolean z) {
        SpotLightPressPhase spotLightPressPhase;
        SpotLightPressPhase spotLightPressPhase2;
        if (notificationSpotLightDelegate.sessionHost != view || (spotLightPressPhase = notificationSpotLightDelegate.pressPhase) == (spotLightPressPhase2 = SpotLightPressPhase.SWIPE_EXPAND_WAIT_EXTINGUISH) || spotLightPressPhase == SpotLightPressPhase.SWIPE_EXPAND_SETTLED) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, FileProvider$.ExternalSyntheticOutline0.m("[NotifCard-SpotLight] suppressUntilPointerUp,key=", NotificationSpotLightGestureCoordinator.INSTANCE.getLogKey(view)), null, 8);
        if (z) {
            notificationSpotLightDelegate.getSpotLightDrawable().extinguish();
            notificationSpotLightDelegate.setPressPhase(spotLightPressPhase2);
        } else {
            notificationSpotLightDelegate.endSessionDueToDetach(view);
        }
        view.invalidate();
    }

    public static final void access$syncEnabledState(NotificationSpotLightDelegate notificationSpotLightDelegate) {
        boolean z = NotificationSpotLightEffectManager.spotLightEnabled;
        if (notificationSpotLightDelegate.sessionHost != null) {
            notificationSpotLightDelegate.getSpotLightDrawable().setEnabled(z);
        } else {
            notificationSpotLightDelegate.getSpotLightDrawable().setEnabled(false);
        }
        View view = notificationSpotLightDelegate.sessionHost;
        if (view != null) {
            view.invalidate();
        }
    }

    public static final void draw(View view, Canvas canvas) {
        Companion.draw(view, canvas);
    }

    private final COUISpotLightEffectDrawable getSpotLightDrawable() {
        return (COUISpotLightEffectDrawable) this.spotLightDrawable$delegate.getValue();
    }

    public static final void markLazyParamsKind(View view, NotificationSpotLightParamsKind notificationSpotLightParamsKind) {
        Companion.markLazyParamsKind(view, notificationSpotLightParamsKind);
    }

    public static final void onTouchEvent(View view, MotionEvent motionEvent) {
        Companion.onTouchEvent(view, motionEvent);
    }

    private final void setPressPhase(SpotLightPressPhase spotLightPressPhase) {
        if (this.pressPhase != spotLightPressPhase) {
            this.pressPhase = spotLightPressPhase;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationSpotLightDelegate-->".concat("[NotifCard-SpotLight] pressPhase forward to " + spotLightPressPhase));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int spotLightHostHeight(View view) {
        if (view instanceof ExpandableNotificationRow) {
            return ((ExpandableNotificationRow) view).getActualHeight();
        }
        if (view instanceof OplusCustomRow) {
            return ((OplusCustomRow) view).getActualHeight();
        }
        return view instanceof CapsuleItemView ? ((CapsuleItemView) view).getActualHeight() : view.getHeight();
    }

    public static final void suppressUntilPointerUp(View view, boolean z) {
        Companion.suppressUntilPointerUp(view, z);
    }

    public final void applySpotLightParamsForCurrentUiMode() {
        ExpandableNotificationRow expandableNotificationRow;
        if (NotificationSpotLightEffectManager.spotLightEnabled && (expandableNotificationRow = this.sessionHost) != null) {
            Object tag = expandableNotificationRow.getTag(2131364205);
            NotificationSpotLightParamsKind notificationSpotLightParamsKind = tag instanceof NotificationSpotLightParamsKind ? (NotificationSpotLightParamsKind) tag : null;
            if (notificationSpotLightParamsKind == null) {
                notificationSpotLightParamsKind = ((expandableNotificationRow instanceof ExpandableNotificationRow) && expandableNotificationRow.mIsHeadsUp) ? NotificationSpotLightParamsKind.HEADS_LAYOUT_CARD : NotificationSpotLightParamsKind.NOTIFICATION_CARD;
            }
            if (this.keyguardStateController == null) {
                this.keyguardStateController = (KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class);
            }
            KeyguardStateControllerImpl keyguardStateControllerImpl = this.keyguardStateController;
            boolean z = false;
            if (keyguardStateControllerImpl != null && keyguardStateControllerImpl.mShowing) {
                z = true;
            }
            NotificationSpotLightParams.SpotLightEffectParams spotLightEffectParamsResolveSpotLightEffectParams = NotificationSpotLightParams.Companion.resolveSpotLightEffectParams(notificationSpotLightParamsKind);
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "NotifCard-SpotLight  CardType = " + notificationSpotLightParamsKind.name() + " spotLightType = " + spotLightEffectParamsResolveSpotLightEffectParams.getSpotLightType() + " isOnKeyguard=" + z, null, 8);
            spotLightEffectParamsResolveSpotLightEffectParams.applyTo(expandableNotificationRow.getContext(), getSpotLightDrawable(), notificationSpotLightParamsKind);
            expandableNotificationRow.invalidate();
        }
    }

    public final void endSessionDueToDetach(View view) {
        NotificationSpotLightGestureCoordinator.INSTANCE.releaseAllForHost(view);
        getSpotLightDrawable().cancel();
        getSpotLightDrawable().setCallback(null);
        getSpotLightDrawable().setEnabled(false);
        view.removeOnAttachStateChangeListener(this.detachListener);
        view.setTag(2131364204, null);
        this.sessionHost = null;
        setPressPhase(SpotLightPressPhase.UNDEFINED);
    }

    public final void setLightPosition(int i, MotionEvent motionEvent, View view) {
        boolean z = motionEvent.getActionMasked() == 0;
        int width = z ? view.getWidth() : getSpotLightDrawable().getBounds().width();
        int iSpotLightHostHeight = z ? spotLightHostHeight(view) : getSpotLightDrawable().getBounds().height();
        if (width <= 0 || iSpotLightHostHeight <= 0) {
            return;
        }
        float x = motionEvent.getX(i);
        float y = motionEvent.getY(i);
        if (x > 0.0f) {
            float f = width;
            if (x >= f || y <= 0.0f) {
                return;
            }
            float f2 = iSpotLightHostHeight;
            if (y >= f2) {
                return;
            }
            getSpotLightDrawable().setLightPosition(x / f, y / f2);
        }
    }

    public static final void draw(View view, Canvas canvas, NotificationSpotLightClipPathProvider notificationSpotLightClipPathProvider) {
        Companion.draw(view, canvas, notificationSpotLightClipPathProvider);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        private final NotificationSpotLightDelegate getInstance() {
            return (NotificationSpotLightDelegate) NotificationSpotLightDelegate.instance$delegate.getValue();
        }

        public final void draw(View view, Canvas canvas, NotificationSpotLightClipPathProvider notificationSpotLightClipPathProvider) {
            NotificationSpotLightDelegate.access$drawInternal(getInstance(), view, canvas, notificationSpotLightClipPathProvider);
        }

        public final void init() {
            NotificationSpotLightDelegate.access$registerGlobalListeners(getInstance());
        }

        public final void markLazyParamsKind(View view, NotificationSpotLightParamsKind notificationSpotLightParamsKind) {
            getInstance().getClass();
            view.setTag(2131364205, notificationSpotLightParamsKind);
        }

        public final void onTouchEvent(View view, MotionEvent motionEvent) {
            NotificationSpotLightDelegate.access$onTouchEventInternal(getInstance(), view, motionEvent);
        }

        public final void suppressUntilPointerUp() {
            View view = getInstance().sessionHost;
            if (view == null) {
                return;
            }
            NotificationSpotLightDelegate.access$suppressUntilPointerUpInternal(getInstance(), view, false);
        }

        public final void updateCapsuleSpotLightAlpha(int i) {
            int iCoerceIn = RangesKt___RangesKt.coerceIn(i, 0, 255);
            if (getInstance().capsuleSpotLightAlpha != iCoerceIn) {
                getInstance().capsuleSpotLightAlpha = iCoerceIn;
            }
        }

        public final void draw(View view, Canvas canvas) {
            draw(view, canvas, null);
        }

        public final void suppressUntilPointerUp(View view, boolean z) {
            NotificationSpotLightDelegate.access$suppressUntilPointerUpInternal(getInstance(), view, z);
        }
    }
}
