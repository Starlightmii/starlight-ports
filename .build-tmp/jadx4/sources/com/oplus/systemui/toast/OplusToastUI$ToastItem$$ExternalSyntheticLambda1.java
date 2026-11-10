package com.oplus.systemui.toast;

import android.animation.Animator;
import android.app.ITransientNotificationCallback;
import android.os.IBinder;
import android.view.View;
import android.widget.ToastPresenter;
import androidx.car.app.SurfaceContainer$;
import androidx.constraintlayout.motion.widget.MotionLayout$;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusToastUI$ToastItem$$ExternalSyntheticLambda1 implements Consumer {
    public /* synthetic */ OplusToastUI.ToastItem f$0;
    public /* synthetic */ ITransientNotificationCallback f$1;
    public /* synthetic */ int f$2;

    /* JADX WARN: Code duplicated, block: B:16:0x0044  */
    /* JADX WARN: Code duplicated, block: B:18:0x0048  */
    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        Animator animator;
        OplusToastUI.ToastItem toastItem = this.f$0;
        ITransientNotificationCallback iTransientNotificationCallback = this.f$1;
        int i = this.f$2;
        OplusSystemUIToast oplusSystemUIToast = (OplusSystemUIToast) obj;
        int i2 = toastItem.mUid;
        String str = toastItem.mPackageName;
        OplusToastUI oplusToastUI = toastItem.this$0;
        if (oplusSystemUIToast == null) {
            return;
        }
        if (toastItem.mCancelled) {
            MotionLayout$.ExternalSyntheticOutline0.m("toast show cancelled for ", str, "OplusToastUI");
            return;
        }
        oplusToastUI.mToast = oplusSystemUIToast;
        if (oplusSystemUIToast.getInAnimation() == null && oplusSystemUIToast.getOutAnimation() == null) {
            animator = oplusSystemUIToast.mInAnimator;
            if (animator != null) {
                animator.start();
            }
        } else {
            View view = oplusSystemUIToast.mToastView;
            if (view instanceof OplusToastLayout) {
                OplusToastLayout oplusToastLayout = (OplusToastLayout) view;
                OplusSystemUIToast$$ExternalSyntheticLambda0 oplusSystemUIToast$$ExternalSyntheticLambda0 = new OplusSystemUIToast$$ExternalSyntheticLambda0();
                oplusSystemUIToast$$ExternalSyntheticLambda0.f$0 = oplusSystemUIToast;
                oplusSystemUIToast$$ExternalSyntheticLambda0.f$1 = oplusToastLayout;
                VarHandle.storeStoreFence();
                oplusToastLayout.setVisibilityChangedListener(oplusSystemUIToast$$ExternalSyntheticLambda0);
            } else {
                animator = oplusSystemUIToast.mInAnimator;
                if (animator != null) {
                    animator.start();
                }
            }
        }
        oplusToastUI.mCallback = iTransientNotificationCallback;
        ToastPresenter toastPresenter = new ToastPresenter(toastItem.mDisplayContext, oplusToastUI.mIAccessibilityManager, oplusToastUI.mNotificationManager, str);
        oplusToastUI.mPresenter = toastPresenter;
        toastPresenter.getLayoutParams().setTrustedOverlay();
        oplusToastUI.mToastLogger.logOnShowToast(i2, str, toastItem.mText.toString(), toastItem.mToken.toString());
        boolean z = true;
        OplusNotificationLogger.logToastState(SurfaceContainer$.ExternalSyntheticOutline0.m(i2, "", new StringBuilder()), str, toastItem.mToken.toString(), true);
        ToastPresenter toastPresenter2 = oplusToastUI.mPresenter;
        View view2 = oplusToastUI.mToast.getView();
        IBinder iBinder = toastItem.mToken;
        IBinder iBinder2 = toastItem.mCurrentWindowToken;
        int iIntValue = oplusToastUI.mToast.getGravity().intValue();
        int iIntValue2 = oplusToastUI.mToast.getXOffset().intValue();
        int iIntValue3 = oplusToastUI.mToast.getYOffset().intValue();
        float fIntValue = oplusToastUI.mToast.getHorizontalMargin().intValue();
        float fIntValue2 = oplusToastUI.mToast.getVerticalMargin().intValue();
        ITransientNotificationCallback iTransientNotificationCallback2 = oplusToastUI.mCallback;
        OplusSystemUIToast oplusSystemUIToast2 = oplusToastUI.mToast;
        if (oplusSystemUIToast2.getInAnimation() == null && oplusSystemUIToast2.getOutAnimation() == null) {
            z = false;
        }
        toastPresenter2.show(view2, iBinder, iBinder2, i, iIntValue, iIntValue2, iIntValue3, fIntValue, fIntValue2, iTransientNotificationCallback2, z);
    }
}
