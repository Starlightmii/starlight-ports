package com.oplus.systemui.notification.lockscreen.capsule;

import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimParams;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class CapsuleItemView$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ CapsuleItemView$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                CapsuleItemView.Companion companion = CapsuleItemView.Companion;
                return (ILockScreenExtStateController) DependencyEx.sDependency.getDependency(ILockScreenExtStateController.class);
            case 1:
                CapsuleItemView.Companion companion2 = CapsuleItemView.Companion;
                return ExtStateAnimParams.INSTANCE.toInterpolator(ExtStateAnimParams.CapsuleProgressRadio.INSTANCE.getCAPSULE_TO_STACK_ALPHA());
            case 2:
                CapsuleItemView.Companion companion3 = CapsuleItemView.Companion;
                return (StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class);
            default:
                CapsuleItemView.Companion companion4 = CapsuleItemView.Companion;
                return (OplusLockscreenShadeTransitionControllerEx) DependencyEx.sDependency.getDependency(OplusLockscreenShadeTransitionControllerEx.class);
        }
    }
}
