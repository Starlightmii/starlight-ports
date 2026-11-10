package com.oplus.zoom.ui.baseview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import androidx.slice.widget.RowView$;
import com.oplus.zoom.ui.baseview.IView;
import com.oplus.zoom.ui.common.UiLogUtils;
import java.lang.invoke.VarHandle;
import java.util.function.Supplier;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BaseViewManager<T extends IView> implements ViewHook {
    public final String TAG = getClass().getSimpleName();
    public final Context mContext;
    public final LayoutInflater mInflater;
    private boolean mPendingRemoved;
    public int mState;
    public T mTarget;
    private final Supplier<T> mViewSupplier;
    public final WindowManager mWM;

    public BaseViewManager(Context context) {
        BaseViewManager$$ExternalSyntheticLambda1 baseViewManager$$ExternalSyntheticLambda1 = new BaseViewManager$$ExternalSyntheticLambda1();
        baseViewManager$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.mViewSupplier = baseViewManager$$ExternalSyntheticLambda1;
        this.mState = 0;
        this.mContext = context;
        this.mWM = (WindowManager) context.getSystemService("window");
        this.mInflater = LayoutInflater.from(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$removeView$0() {
        UiLogUtils.d("removeView() WM removes view : " + this.mTarget.getView());
        this.mWM.removeView(this.mTarget.getView());
    }

    public void addView() {
        T t = this.mTarget;
        if (t == null) {
            UiLogUtils.w("Failed to addView() : mTarget is null !");
            return;
        }
        if (t.getView().isAttachedToWindow()) {
            UiLogUtils.w("mTarget already AttachedToWindow : " + this.mTarget);
        } else {
            if (this.mState != 0) {
                UiLogUtils.w("Invalid state: " + this.mState);
                return;
            }
            UiLogUtils.i("addView() for : " + this.mTarget + ", state = " + this.mState);
            this.mWM.addView(this.mTarget.getView(), this.mTarget.getWindowParams());
            this.mState = 1;
        }
    }

    public abstract T createView();

    public int getState() {
        return this.mState;
    }

    public View getView() {
        return this.mTarget.getView();
    }

    public boolean initTargetView() {
        if (this.mTarget != null || this.mState != 0) {
            UiLogUtils.w("Failed to initTargetView() - Invalid operation : mTarget = " + this.mTarget + ", mState = " + this.mState);
            return false;
        }
        this.mTarget = this.mViewSupplier.get();
        UiLogUtils.i("Init view for " + this.TAG + ", mTarget = " + this.mTarget);
        return true;
    }

    @Override // com.oplus.zoom.ui.baseview.ViewHook
    public void onAttachedToWindow() {
        UiLogUtils.d(this.mTarget + " onAttachedToWindow()");
        this.mState = 2;
        if (this.mPendingRemoved) {
            if (this.mTarget != null) {
                UiLogUtils.w("pendingRemoved onAttachedToWindow() - need removeView");
                this.mWM.removeView(this.mTarget.getView());
            }
            this.mPendingRemoved = false;
        }
    }

    @Override // com.oplus.zoom.ui.baseview.ViewHook
    public void onDetachedFromWindow() {
        UiLogUtils.d(this.mTarget + " onDetachedFromWindow()");
        this.mState = 0;
        this.mTarget = null;
        this.mPendingRemoved = false;
    }

    @Override // com.oplus.zoom.ui.baseview.ViewHook
    public void relayout() {
        updateView();
    }

    public void removeView(boolean z) {
        StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("removeView() anim = ", ", state = ", z);
        sbM.append(this.mState);
        UiLogUtils.d(sbM.toString());
        T t = this.mTarget;
        if (t == null) {
            UiLogUtils.d("removeView() : mTarget is null!");
            return;
        }
        if (!t.getView().isAttachedToWindow()) {
            UiLogUtils.w("mTarget may not AttachedToWindow : " + this.mTarget);
            this.mPendingRemoved = true;
            return;
        }
        if (this.mState != 2) {
            UiLogUtils.w("Invalid state: " + this.mState);
            return;
        }
        this.mState = 3;
        final BaseViewManager$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new BaseViewManager$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        if (z) {
            this.mTarget.animRemove(new AnimatorListenerAdapter() { // from class: com.oplus.zoom.ui.baseview.BaseViewManager.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    T t2 = BaseViewManager.this.mTarget;
                    if (t2 != null && t2.getView().isAttachedToWindow()) {
                        externalSyntheticLambda0.run();
                        return;
                    }
                    UiLogUtils.w("mTarget may not AttachedToWindow : " + BaseViewManager.this.mTarget);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    T t2 = BaseViewManager.this.mTarget;
                    if (t2 != null && t2.getView().isAttachedToWindow()) {
                        externalSyntheticLambda0.run();
                        return;
                    }
                    UiLogUtils.w("mTarget may not AttachedToWindow : " + BaseViewManager.this.mTarget);
                }
            });
        } else {
            externalSyntheticLambda0.run();
        }
    }

    @Override // com.oplus.zoom.ui.baseview.ViewHook
    public void removeViewOnCallBack() {
        removeView(false);
    }

    public void updateView() {
        T t = this.mTarget;
        if (t == null || !t.getView().isAttachedToWindow()) {
            UiLogUtils.w("mTarget may not AttachedToWindow : " + this.mTarget);
        } else {
            if (this.mState == 2) {
                this.mWM.updateViewLayout(this.mTarget.getView(), this.mTarget.getWindowParams());
                return;
            }
            UiLogUtils.w("Invalid state: " + this.mState);
        }
    }
}
