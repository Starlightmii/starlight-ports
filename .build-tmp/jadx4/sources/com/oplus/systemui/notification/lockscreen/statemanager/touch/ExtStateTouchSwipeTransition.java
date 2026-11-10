package com.oplus.systemui.notification.lockscreen.statemanager.touch;

import android.os.Bundle;
import androidx.car.app.serialization.Bundler$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.immersive.ImmersiveCardControl;
import com.oplus.systemui.notification.lockscreen.statemanager.FocusCard;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateDecider;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ExtStateTouchSwipeTransition implements IExtStateTouchSwipeAction {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ExtStateTouchSwipeTransition";
    private final Function0 enableSinkingStack;
    private final LockScreenNotificationExtStateDecider extStateDecider;
    private final ImmersiveCardControl immersiveCardControl;
    private final ILockScreenExtStateController stateManager;
    private final String tag;
    private final ExtStateTouchHandlerEx.ExtStateTouchHandlerType type;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ExtStateTouchSwipeTransition(ExtStateTouchHandlerEx.ExtStateTouchHandlerType extStateTouchHandlerType, ILockScreenExtStateController iLockScreenExtStateController, ImmersiveCardControl immersiveCardControl, Function0 function0, LockScreenNotificationExtStateDecider lockScreenNotificationExtStateDecider) {
        this.type = extStateTouchHandlerType;
        this.stateManager = iLockScreenExtStateController;
        this.immersiveCardControl = immersiveCardControl;
        this.enableSinkingStack = function0;
        this.extStateDecider = lockScreenNotificationExtStateDecider;
        this.tag = extStateTouchHandlerType.getTag();
    }

    private final int getCurrentState() {
        return ((ILockScreenExtStateController.ExtStateEvent) this.stateManager.getExtStateEvent().getValue()).getNewState();
    }

    public static void goToExtState$default(ExtStateTouchSwipeTransition extStateTouchSwipeTransition, int i, String str, int i2) {
        extStateTouchSwipeTransition.stateManager.goToExtState(true, i, str, i2, null);
    }

    public final void swipeDown() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(getCurrentState(), "[swipeDown] currentState=", "ExtStateTouchSwipeTransition-->", "SystemUi--Notification");
        }
        goToExtState$default(this, this.extStateDecider.onNsslSlideToDown(getCurrentState()), this.tag + ":l_swipe_down", 101);
    }

    public final void swipeUp() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(getCurrentState(), "[swipeUp] currentState=", "ExtStateTouchSwipeTransition-->", "SystemUi--Notification");
        }
        ILockScreenExtStateController.ExtStateEvent extStateEvent = (ILockScreenExtStateController.ExtStateEvent) this.stateManager.getExtStateEvent().getValue();
        int currentState = getCurrentState();
        if (currentState != 1) {
            if (currentState != 3) {
                return;
            }
            goToExtState$default(this, 0, this.tag + ":l_swipe_up_sink", 111);
            return;
        }
        boolean z = extStateEvent.getOldState() == 2;
        if (z) {
            String strM = Bundler$.ExternalSyntheticOutline0.m(this.tag, ":l_swipe_up:isFromImmersive");
            ILockScreenExtStateController.ExtStateEvent.Companion companion = ILockScreenExtStateController.ExtStateEvent.Companion;
            Bundle bundle = new Bundle();
            FocusCard focusCard = this.immersiveCardControl.getFocusCard();
            if (focusCard == null) {
                focusCard = this.immersiveCardControl.getLastFocusCard();
            }
            this.stateManager.goToExtState(true, 2, strM, 111, companion.withImmersiveKey(bundle, focusCard != null ? focusCard.getKey() : null));
            return;
        }
        goToExtState$default(this, this.extStateDecider.onCapsuleSlideToUp(), this.tag + ":l_swipe_up:" + z, 111);
    }
}
