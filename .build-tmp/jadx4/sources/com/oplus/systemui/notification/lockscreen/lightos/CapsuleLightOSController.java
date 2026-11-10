package com.oplus.systemui.notification.lockscreen.lightos;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface CapsuleLightOSController {
    default void changeBindDirection(boolean z) {
    }

    void init();

    default boolean interceptRequestImmersiveState(int i) {
        return false;
    }

    default void onAnimEnd() {
    }

    default void onAnimStart(boolean z) {
    }

    default void onCapsuleContainerDrag(float f) {
    }

    default void onCapsuleContainerFling(float f) {
    }

    default void resetLightOSViewState() {
    }

    default void updateAnimProgress(float f, boolean z) {
    }
}
