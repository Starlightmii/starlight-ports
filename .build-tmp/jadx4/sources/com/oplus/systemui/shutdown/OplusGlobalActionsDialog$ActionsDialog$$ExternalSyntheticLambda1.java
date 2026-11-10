package com.oplus.systemui.shutdown;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.animation.PathInterpolator;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.MultiAnimViewDelegate;
import com.oplus.systemui.volume.OplusVolumeTipsControler;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusGlobalActionsDialog$ActionsDialog$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusGlobalActionsDialog.ActionsDialog f$0;

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        OplusGlobalActionsDialog.ActionsDialog actionsDialog = this.f$0;
        switch (i) {
            case 0:
                int i2 = OplusGlobalActionsDialog.ActionsDialog.$r8$clinit;
                PathInterpolator pathInterpolator = new PathInterpolator(0.3f, 0.0f, 0.1f, 1.0f);
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(actionsDialog.mContainer, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, 1.0f, 0.0f);
                objectAnimatorOfFloat.setInterpolator(pathInterpolator);
                objectAnimatorOfFloat.setDuration(417L);
                OplusGlobalActionsDialog$ActionsDialog$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new OplusGlobalActionsDialog$ActionsDialog$.ExternalSyntheticLambda2(0);
                externalSyntheticLambda2.f$0 = actionsDialog;
                VarHandle.storeStoreFence();
                objectAnimatorOfFloat.addUpdateListener(externalSyntheticLambda2);
                ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(actionsDialog.mContainer, MultiAnimViewDelegate.PROPERTY_SCALE_X, 1.0f, 0.9f);
                ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(actionsDialog.mContainer, MultiAnimViewDelegate.PROPERTY_SCALE_Y, 1.0f, 0.9f);
                objectAnimatorOfFloat2.setDuration(417L);
                objectAnimatorOfFloat3.setDuration(417L);
                objectAnimatorOfFloat2.setInterpolator(pathInterpolator);
                objectAnimatorOfFloat3.setInterpolator(pathInterpolator);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2, objectAnimatorOfFloat3);
                animatorSet.addListener(new OplusGlobalActionsDialog.ActionsDialog.3(actionsDialog, 1));
                animatorSet.start();
                break;
            default:
                int i3 = OplusGlobalActionsDialog.ActionsDialog.$r8$clinit;
                actionsDialog.getClass();
                ShutdownBlurManager.INSTANCE.tryTakeScreenshotForShutdownBlur(actionsDialog.mContext, false);
                break;
        }
    }
}
