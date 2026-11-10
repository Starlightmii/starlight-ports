package com.oplus.systemui.privacy;

import android.util.Log;
import android.view.View;
import androidx.collection.FloatList$;
import com.android.systemui.statusbar.events.SystemStatusAnimationCallback;
import com.android.systemui.statusbar.events.SystemStatusAnimationSchedulerImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPrivacyDotViewControllerImpl$hideDotView$1 implements Runnable {
    public /* synthetic */ Object $dot;
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ OplusPrivacyDotViewControllerImpl$hideDotView$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                View viewFindViewById = ((View) this.$dot).findViewById(2131364661);
                if (viewFindViewById != null) {
                    viewFindViewById.setVisibility(4);
                }
                break;
            case 1:
                SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl = ((OplusPrivacyDotViewControllerImpl) this.$dot).animationScheduler;
                SystemStatusAnimationCallback systemStatusAnimationCallback = ((OplusPrivacyDotViewControllerImpl) this.$dot).systemStatusAnimationCallback;
                SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl2 = systemStatusAnimationSchedulerImpl;
                systemStatusAnimationSchedulerImpl2.getClass();
                systemStatusAnimationSchedulerImpl2.addCallback(systemStatusAnimationCallback);
                break;
            case 2:
                OplusPrivacyDotViewControllerImpl oplusPrivacyDotViewControllerImpl = (OplusPrivacyDotViewControllerImpl) this.$dot;
                if (oplusPrivacyDotViewControllerImpl.displayConfigPending) {
                    int i = oplusPrivacyDotViewControllerImpl.pendingTargetRotation;
                    int i2 = oplusPrivacyDotViewControllerImpl.pendingTargetWidth;
                    int i3 = oplusPrivacyDotViewControllerImpl.pendingTargetHeight;
                    StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("applyDisplayConfig by timeout targetRot=", i, i2, " targetSize=", "x");
                    sbM.append(i3);
                    Log.w("OplusPrivacyDotViewController", sbM.toString());
                    ((OplusPrivacyDotViewControllerImpl) this.$dot).applyDisplayConfig("timeout");
                }
                break;
            default:
                OplusPrivacyDotViewControllerImpl.access$processNextViewState((OplusPrivacyDotViewControllerImpl) this.$dot);
                break;
        }
    }
}
