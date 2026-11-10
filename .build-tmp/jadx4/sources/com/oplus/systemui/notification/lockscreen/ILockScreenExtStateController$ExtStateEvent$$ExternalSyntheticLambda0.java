package com.oplus.systemui.notification.lockscreen;

import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ILockScreenExtStateController$ExtStateEvent$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ ILockScreenExtStateController.ExtStateEvent f$0;

    public final Object invoke() {
        ILockScreenExtStateController.ExtStateEvent extStateEvent = this.f$0;
        int i = extStateEvent.newState;
        int i2 = extStateEvent.oldState;
        if (i2 == 0) {
            if (i == 1) {
                return ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.STACK_TO_CAPSULE;
            }
            if (i != 2) {
                return i != 3 ? ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.UNKNOWN : ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.STACK_TO_SINKING;
            }
            return ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.STACK_TO_IMMERSIVE;
        }
        if (i2 == 1) {
            if (i == 0) {
                return ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.CAPSULE_TO_STACK;
            }
            if (i != 2) {
                return i != 3 ? ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.UNKNOWN : ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.CAPSULE_TO_SINKING;
            }
            return ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.CAPSULE_TO_IMMERSIVE;
        }
        if (i2 != 2) {
            if (i2 != 3) {
                return ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.UNKNOWN;
            }
            if (i != 0) {
                return i != 1 ? ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.UNKNOWN : ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.SINKING_TO_CAPSULE;
            }
            return ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.SINKING_TO_STACK;
        }
        if (i == 0) {
            return ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.IMMERSIVE_TO_STACK;
        }
        if (i != 1) {
            return i != 2 ? ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.UNKNOWN : ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.IMMERSIVE_TO_IMMERSIVE;
        }
        return ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.IMMERSIVE_TO_CAPSULE;
    }
}
