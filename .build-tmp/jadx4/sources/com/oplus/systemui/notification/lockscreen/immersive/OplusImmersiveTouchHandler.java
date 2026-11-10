package com.oplus.systemui.notification.lockscreen.immersive;

import android.util.Log;
import android.view.MotionEvent;
import androidx.compose.animation.core.MonoSpline$;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.systemui.DependencyEx;
import com.android.systemui.keyguard.OplusKeyguardBottomAreaControllerEx;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerTouchHandler;
import com.oplus.systemui.notification.lockscreen.capsule.utils.ViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateDecider;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationViewBinder;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationViewBinder$initCapsule$1;
import com.oplus.systemui.notification.lockscreen.statemanager.touch.ExtStateTouchHandlerEx;
import com.oplus.systemui.notification.lockscreen.statemanager.touch.ExtStateTouchSwipeTransition;
import com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusImmersiveTouchHandler {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusImmersiveTouchHandler";
    private final OplusImmersiveBgContainer bgContainer;
    private final CapsuleCardControl capsuleCardControl;
    private final ExtStateTouchHandlerEx continuousSwipeHandler;
    private final LockScreenNotificationExtStateDecider extStateDecider;
    private final ExtStateTouchSwipeTransition extStateTouchSwipeTransition;
    private GestureType gestureType = GestureType.NONE;
    private final ImmersiveTouchCallback immersiveTouchCallback;
    public float initialX;
    public float initialY;
    public boolean isGestureRecognized;
    private final Lazy keyguardBottomAreaController$delegate;
    private final dagger.Lazy lockScreenExtStateController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class GestureType {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ GestureType[] $VALUES;
        public static final GestureType CLICK;
        public static final GestureType IMMER_TO_IMMER_CLICK;
        public static final GestureType NONE;
        public static final GestureType SWIPE_DOWN;
        public static final GestureType SWIPE_LEFT;
        public static final GestureType SWIPE_RIGHT;

        static {
            GestureType gestureType = new GestureType("NONE", 0);
            NONE = gestureType;
            GestureType gestureType2 = new GestureType("CLICK", 1);
            CLICK = gestureType2;
            GestureType gestureType3 = new GestureType("SWIPE_DOWN", 2);
            SWIPE_DOWN = gestureType3;
            GestureType gestureType4 = new GestureType("SWIPE_LEFT", 3);
            SWIPE_LEFT = gestureType4;
            GestureType gestureType5 = new GestureType("SWIPE_RIGHT", 4);
            SWIPE_RIGHT = gestureType5;
            GestureType gestureType6 = new GestureType("IMMER_TO_IMMER_CLICK", 5);
            IMMER_TO_IMMER_CLICK = gestureType6;
            GestureType[] gestureTypeArr = {gestureType, gestureType2, gestureType3, gestureType4, gestureType5, gestureType6};
            $VALUES = gestureTypeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(gestureTypeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static GestureType valueOf(String str) {
            return (GestureType) Enum.valueOf(GestureType.class, str);
        }

        public static GestureType[] values() {
            return (GestureType[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface ImmersiveTouchCallback {
    }

    public OplusImmersiveTouchHandler(OplusImmersiveBgContainer oplusImmersiveBgContainer, ImmersiveTouchCallback immersiveTouchCallback, dagger.Lazy lazy, CapsuleCardControl capsuleCardControl, dagger.Lazy lazy2, LockScreenNotificationExtStateDecider lockScreenNotificationExtStateDecider) {
        this.bgContainer = oplusImmersiveBgContainer;
        this.immersiveTouchCallback = immersiveTouchCallback;
        this.lockScreenExtStateController = lazy;
        this.capsuleCardControl = capsuleCardControl;
        this.extStateDecider = lockScreenNotificationExtStateDecider;
        final int i = 0;
        this.keyguardBottomAreaController$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveTouchHandler$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusKeyguardBottomAreaController();
                    default:
                        return Boolean.valueOf(((NotificationKeyguardHelper) DependencyEx.sDependency.getDependency(NotificationKeyguardHelper.class)).mLockScreenNotificationEnableSinkingObserver.getLockScreenNotificationEnableSinking());
                }
            }
        });
        ExtStateTouchHandlerEx.ExtStateTouchHandlerType extStateTouchHandlerType = ExtStateTouchHandlerEx.ExtStateTouchHandlerType.IMMERSIVE;
        final int i2 = 1;
        ExtStateTouchSwipeTransition extStateTouchSwipeTransition = new ExtStateTouchSwipeTransition(extStateTouchHandlerType, (ILockScreenExtStateController) lazy.get(), (ImmersiveCardControl) DependencyEx.sDependency.getDependency(ImmersiveCardControl.class), new Function0() { // from class: com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveTouchHandler$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusKeyguardBottomAreaController();
                    default:
                        return Boolean.valueOf(((NotificationKeyguardHelper) DependencyEx.sDependency.getDependency(NotificationKeyguardHelper.class)).mLockScreenNotificationEnableSinkingObserver.getLockScreenNotificationEnableSinking());
                }
            }
        }, lockScreenNotificationExtStateDecider);
        this.extStateTouchSwipeTransition = extStateTouchSwipeTransition;
        this.continuousSwipeHandler = new ExtStateTouchHandlerEx(extStateTouchHandlerType, (ILockScreenExtStateController) lazy.get(), extStateTouchSwipeTransition);
    }

    private final GestureType getClickType() {
        return (NotificationCapsuleContainerTouchHandler.Companion.isQuickImmerToImmer((ILockScreenExtStateController) this.lockScreenExtStateController.get()) && this.capsuleCardControl.isInCapsuleArea(this.initialX, this.initialY)) ? GestureType.IMMER_TO_IMMER_CLICK : GestureType.CLICK;
    }

    private final OplusKeyguardBottomAreaControllerEx getKeyguardBottomAreaController() {
        return (OplusKeyguardBottomAreaControllerEx) this.keyguardBottomAreaController$delegate.getValue();
    }

    public final void handleGesture(GestureType gestureType, float f, float f2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sb = new StringBuilder("handleGesture: performing ");
            sb.append(gestureType);
            sb.append(" action, deltaX=");
            sb.append(f);
            sb.append(", deltaY=");
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sb, f2, "OplusImmersiveTouchHandler-->", "SystemUi--Notification");
        }
        int iOrdinal = gestureType.ordinal();
        if (iOrdinal == 1) {
            ((LockScreenNotificationViewBinder$initCapsule$1) this.immersiveTouchCallback).onGoBackGesture();
            return;
        }
        if (iOrdinal == 2) {
            this.bgContainer.requestDisallowInterceptTouchEvent(true);
            ILockScreenExtStateController iLockScreenExtStateController = ((LockScreenNotificationViewBinder$initCapsule$1) this.immersiveTouchCallback).this$0.lockScreenExtStateController;
            ILockScreenExtStateController.Companion companion = ILockScreenExtStateController.Companion;
            iLockScreenExtStateController.goToExtState(true, 1, "ImmersiveGoCapsule", 121, null);
            return;
        }
        if (iOrdinal == 3) {
            ((LockScreenNotificationViewBinder$initCapsule$1) this.immersiveTouchCallback).onGoBackGesture();
        } else if (iOrdinal == 4) {
            ((LockScreenNotificationViewBinder$initCapsule$1) this.immersiveTouchCallback).onGoBackGesture();
        } else {
            if (iOrdinal != 5) {
                return;
            }
            LockScreenNotificationViewBinder.access$immerToImmer(((LockScreenNotificationViewBinder$initCapsule$1) this.immersiveTouchCallback).this$0, "immerBg");
        }
    }

    public final void onBackInvoked() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "OplusImmersiveTouchHandler-->onBackInvoked");
        }
        ((LockScreenNotificationViewBinder$initCapsule$1) this.immersiveTouchCallback).onGoBackGesture();
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.isGestureRecognized = false;
        }
        return this.isGestureRecognized;
    }

    /* JADX WARN: Code duplicated, block: B:63:0x0102  */
    /* JADX WARN: Code duplicated, block: B:65:0x010b  */
    /* JADX WARN: Code duplicated, block: B:67:0x010e  */
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        float fAbs;
        float fAbs2;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onTouchEvent: ", ViewExpandKtKt.getActionName(motionEvent), "OplusImmersiveTouchHandler-->", "SystemUi--Notification");
        }
        if ((((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isImmersiveState() || motionEvent.getActionMasked() != 0) && (motionEvent.getAction() != 0 || (getKeyguardBottomAreaController().getIconAtPosition(motionEvent.getRawX(), motionEvent.getRawY()) == null && motionEvent.getRawY() > CapsuleConfig.INSTANCE.getStatusBarHeight()))) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.initialX = motionEvent.getRawX();
                this.initialY = motionEvent.getRawY();
                this.isGestureRecognized = false;
                this.gestureType = GestureType.NONE;
                this.continuousSwipeHandler.onTouchEvent(motionEvent);
                return true;
            }
            if (action == 1) {
                this.continuousSwipeHandler.onTouchEvent(motionEvent);
                if (this.isGestureRecognized) {
                    this.isGestureRecognized = false;
                    return false;
                }
                fAbs = Math.abs(motionEvent.getRawX() - this.initialX);
                fAbs2 = Math.abs(motionEvent.getRawY() - this.initialY);
                if (!this.isGestureRecognized && motionEvent.getAction() == 1 && fAbs < 20.0f && fAbs2 < 20.0f) {
                    this.gestureType = getClickType();
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        boolean zIsImmersiveState = ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isImmersiveState();
                        StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("onTouchEvent: recognized click, deltaX=", fAbs, ", deltaY=", fAbs2, "c=");
                        sbM.append(zIsImmersiveState);
                        sbM.append(",e=$");
                        Log.d("SystemUi--Notification", "OplusImmersiveTouchHandler-->".concat(sbM.toString()));
                    }
                    handleGesture(this.gestureType, fAbs, fAbs2);
                    return true;
                }
            } else if (action == 2) {
                GestureType gestureType = this.gestureType;
                GestureType gestureType2 = GestureType.SWIPE_DOWN;
                if (gestureType == gestureType2 && this.continuousSwipeHandler.onTouchEvent(motionEvent)) {
                    return true;
                }
                if (!this.isGestureRecognized) {
                    float rawX = motionEvent.getRawX() - this.initialX;
                    float rawY = motionEvent.getRawY() - this.initialY;
                    if (!this.isGestureRecognized) {
                        float fAbs3 = Math.abs(rawX);
                        float fAbs4 = Math.abs(rawY);
                        if (rawY >= -20.0f && ((20.0f <= fAbs3 && fAbs3 <= 500.0f) || (20.0f <= fAbs4 && fAbs4 <= 500.0f))) {
                            if (Math.abs(rawX) > Math.abs(rawY)) {
                                gestureType2 = rawX > 0.0f ? GestureType.SWIPE_RIGHT : GestureType.SWIPE_LEFT;
                            }
                            this.gestureType = gestureType2;
                            this.isGestureRecognized = true;
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                GestureType gestureType3 = this.gestureType;
                                StringBuilder sb = new StringBuilder("onTouchEvent: recognized ");
                                sb.append(gestureType3);
                                sb.append(", deltaX=");
                                sb.append(rawX);
                                sb.append(", deltaY=");
                                VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sb, rawY, "OplusImmersiveTouchHandler-->", "SystemUi--Notification");
                            }
                            handleGesture(this.gestureType, rawX, rawY);
                            return true;
                        }
                    }
                }
            } else if (action == 3) {
                this.continuousSwipeHandler.onTouchEvent(motionEvent);
                if (this.isGestureRecognized) {
                    this.isGestureRecognized = false;
                    return false;
                }
                fAbs = Math.abs(motionEvent.getRawX() - this.initialX);
                fAbs2 = Math.abs(motionEvent.getRawY() - this.initialY);
                if (!this.isGestureRecognized) {
                    this.gestureType = getClickType();
                    if (LogUtil.getNormalDebug()) {
                        boolean zIsImmersiveState2 = ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isImmersiveState();
                        StringBuilder sbM2 = MonoSpline$.ExternalSyntheticOutline0.m("onTouchEvent: recognized click, deltaX=", fAbs, ", deltaY=", fAbs2, "c=");
                        sbM2.append(zIsImmersiveState2);
                        sbM2.append(",e=$");
                        Log.d("SystemUi--Notification", "OplusImmersiveTouchHandler-->".concat(sbM2.toString()));
                    }
                    handleGesture(this.gestureType, fAbs, fAbs2);
                    return true;
                }
            }
        }
        return false;
    }
}
