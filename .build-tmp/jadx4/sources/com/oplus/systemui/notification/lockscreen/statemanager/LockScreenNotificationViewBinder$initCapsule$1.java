package com.oplus.systemui.notification.lockscreen.statemanager;

import android.os.Bundle;
import android.support.v4.content.FileProvider$;
import androidx.car.app.serialization.Bundler$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl;
import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData;
import com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveTouchHandler;
import com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController;
import com.oplus.systemui.notification.lockscreen.seedling.SeedlingViewModel;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenNotificationViewBinder$initCapsule$1 implements NotificationCapsuleContainerControllerImpl.OutActionListener, OplusImmersiveTouchHandler.ImmersiveTouchCallback, SeedlingViewModel.SeedingStateCallback {
    public /* synthetic */ LockScreenNotificationViewBinder this$0;

    public void onGoBackGesture() {
        ILockScreenExtStateController iLockScreenExtStateController = this.this$0.lockScreenExtStateController;
        ILockScreenExtStateController.Companion companion = ILockScreenExtStateController.Companion;
        iLockScreenExtStateController.goToExtState(true, 0, "ImmersiveGoBack", 121, null);
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl.OutActionListener
    public void onImmerToImmerClick(CapsuleItemData capsuleItemData) {
        LockScreenNotificationViewBinder.access$immerToImmer(this.this$0, "capsuleClick");
    }

    @Override // com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl.OutActionListener
    public void onItemClick(CapsuleItemData capsuleItemData) {
        LockScreenNotificationViewBinder.access$onCapsuleItemClick(this.this$0, capsuleItemData);
    }

    @Override // com.oplus.systemui.notification.lockscreen.seedling.SeedlingViewModel.SeedingStateCallback
    public void onRequestImmersiveState(boolean z, String str, int i, OplusCustomRow oplusCustomRow) {
        LockScreenNotificationViewBinder lockScreenNotificationViewBinder = this.this$0;
        if (((CapsuleLightOSController) lockScreenNotificationViewBinder.capsuleLightOSManager.get()).interceptRequestImmersiveState(i)) {
            return;
        }
        if (!z) {
            if (lockScreenNotificationViewBinder.lockScreenExtStateController.isImmersiveState()) {
                lockScreenNotificationViewBinder.lockScreenExtStateController.goToExtState(true, lockScreenNotificationViewBinder.lockScreenExtStateController.getLastExtState(), str, i, null);
                return;
            } else {
                LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, "LockScreenNotificationViewBinder", Bundler$.ExternalSyntheticOutline0.m("onRequestImmersiveState: ", str, ", fail, because of not in immersive state"));
                return;
            }
        }
        if (lockScreenNotificationViewBinder.isSameImmersionItem(oplusCustomRow.getEntry())) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, "LockScreenNotificationViewBinder", Bundler$.ExternalSyntheticOutline0.m("onRequestImmersiveState: ", str, ", fail, because of same item"));
            return;
        }
        ILockScreenExtStateController iLockScreenExtStateController = lockScreenNotificationViewBinder.lockScreenExtStateController;
        String strM = FileProvider$.ExternalSyntheticOutline0.m("seedingRequest: ", str);
        ILockScreenExtStateController.ExtStateEvent.Companion companion = ILockScreenExtStateController.ExtStateEvent.Companion;
        Bundle bundle = new Bundle();
        CustomRowEntry entry = oplusCustomRow.getEntry();
        iLockScreenExtStateController.goToExtState(true, 2, strM, i, companion.withImmersiveKey(bundle, entry != null ? entry.getKey() : null));
    }
}
