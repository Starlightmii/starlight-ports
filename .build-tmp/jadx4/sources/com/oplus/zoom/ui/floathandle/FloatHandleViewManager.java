package com.oplus.zoom.ui.floathandle;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import com.oplus.zoom.ui.baseview.BaseViewManager;
import com.oplus.zoom.ui.common.UiLogUtils;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class FloatHandleViewManager extends BaseViewManager<FloatHandleView> {
    private static final String TAG = "FloatHandle";
    private Context mContext;
    private FloatHandleController mController;
    private int mSide;
    private FloatHandleStatus mStatus;

    public FloatHandleViewManager(Context context, int i, FloatHandleStatus floatHandleStatus, FloatHandleController floatHandleController) {
        super(context);
        this.mContext = context;
        this.mStatus = floatHandleStatus;
        this.mController = floatHandleController;
        this.mSide = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$relaunchFloatHandleView$1(FloatHandleInfo floatHandleInfo) {
        Executor mainExecutor = this.mContext.getMainExecutor();
        FloatHandleViewManager$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new FloatHandleViewManager$.ExternalSyntheticLambda0(0);
        externalSyntheticLambda0.f$0 = this;
        externalSyntheticLambda0.f$1 = floatHandleInfo;
        VarHandle.storeStoreFence();
        mainExecutor.execute(externalSyntheticLambda0);
    }

    /* JADX INFO: renamed from: addFloatHandleView, reason: merged with bridge method [inline-methods] */
    public void lambda$relaunchFloatHandleView$0(FloatHandleInfo floatHandleInfo) {
        boolean zInitTargetView = initTargetView();
        T t = this.mTarget;
        if (!zInitTargetView) {
            if (t == 0 || !((FloatHandleView) t).getView().isAttachedToWindow()) {
                UiLogUtils.w("FloatHandle", "addFloatHandleView mTarget may not AttachedToWindow : " + this.mTarget);
                return;
            } else {
                UiLogUtils.i("FloatHandle", "refreshIconForFullFloatHandleView start");
                ((FloatHandleView) this.mTarget).showFloatHandleViewWithAnim(2, floatHandleInfo.packageName, floatHandleInfo.userId);
                UiLogUtils.i("FloatHandle", "refreshIconForFullFloatHandleView end");
                return;
            }
        }
        if (t != 0) {
            UiLogUtils.i("FloatHandle", "showFullFloatHandleView start : mTarget = " + this.mTarget);
            if (((FloatHandleView) this.mTarget).initState(floatHandleInfo, 1, 2)) {
                addView();
            } else {
                this.mTarget = null;
                UiLogUtils.w("FloatHandle", "initTargetViewAndState failed, just return");
            }
            UiLogUtils.i("FloatHandle", "showFullFloatHandleView end");
        }
    }

    @Override // com.oplus.zoom.ui.baseview.BaseViewManager
    public FloatHandleView createView() {
        UiLogUtils.d("FloatHandle", "FloatHandleView createView start");
        FloatHandleView floatHandleView = new FloatHandleView(this.mContext, this.mSide, this.mStatus, this);
        floatHandleView.setHook(this);
        return floatHandleView;
    }

    public FloatHandleController getFloatHandleController() {
        return this.mController;
    }

    public void hideFloatHandleView(FloatHandleInfo floatHandleInfo) {
        T t = this.mTarget;
        if (t == 0 || !((FloatHandleView) t).getView().isAttachedToWindow()) {
            UiLogUtils.w("FloatHandle", "hideFloatHandleView mTarget may not AttachedToWindow : " + this.mTarget);
        } else {
            UiLogUtils.i("FloatHandle", "hideFloatHandleView start");
            ((FloatHandleView) this.mTarget).showFloatHandleViewWithAnim(1, floatHandleInfo.packageName, floatHandleInfo.userId);
            UiLogUtils.i("FloatHandle", "hideFloatHandleView end");
        }
    }

    @Override // com.oplus.zoom.ui.baseview.BaseViewManager, com.oplus.zoom.ui.baseview.ViewHook
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((FloatHandleView) this.mTarget).animAdd(null);
    }

    public void refreshFloatHandleView(FloatHandleInfo floatHandleInfo) {
        T t = this.mTarget;
        if (t == 0 || !((FloatHandleView) t).getView().isAttachedToWindow()) {
            UiLogUtils.w("FloatHandle", "refreshFloatHandleView mTarget may not AttachedToWindow : " + this.mTarget);
        } else {
            UiLogUtils.i("FloatHandle", "refreshConfigForFloatHandleView start");
            ((FloatHandleView) this.mTarget).refreshFloatHandleView(floatHandleInfo.packageName, floatHandleInfo.userId);
            UiLogUtils.i("FloatHandle", "refreshConfigForFloatHandleView end");
        }
    }

    public void relaunchFloatHandleView(FloatHandleInfo floatHandleInfo) {
        if (floatHandleInfo == null || floatHandleInfo.packageName == null) {
            UiLogUtils.w("FloatHandle", "Failed to relaunch handle view: info is null");
            return;
        }
        if (floatHandleInfo.currentUser != ActivityManager.getCurrentUser()) {
            UiLogUtils.w("not current user, don't relaunchFloatHandleView");
            return;
        }
        if (this.mTarget != 0) {
            UiLogUtils.i("FloatHandle", "relaunchFloatHandleView start : mTarget = " + this.mTarget);
            removeFloatHandleImmediately();
            if (((FloatHandleView) this.mTarget).initState(floatHandleInfo, 4, 8)) {
                Handler mainThreadHandler = this.mContext.getMainThreadHandler();
                FloatHandleViewManager$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new FloatHandleViewManager$.ExternalSyntheticLambda0(1);
                externalSyntheticLambda0.f$0 = this;
                externalSyntheticLambda0.f$1 = floatHandleInfo;
                VarHandle.storeStoreFence();
                mainThreadHandler.postDelayed(externalSyntheticLambda0, 550L);
            } else {
                UiLogUtils.w("FloatHandle", "initTargetViewAndState failed, just return");
                this.mTarget = null;
                this.mStatus.setCurrentLockPkg(null);
                this.mStatus.setCurrentUserId(0);
            }
            UiLogUtils.i("FloatHandle", "relaunchFloatHandleView end");
        }
    }

    public void removeFloatHandleImmediately() {
        T t = this.mTarget;
        if (t == 0 || !((FloatHandleView) t).getView().isAttachedToWindow()) {
            UiLogUtils.w("FloatHandle", "removeFloatHandleImmediately mTarget may not AttachedToWindow : " + this.mTarget);
        } else {
            UiLogUtils.i("FloatHandle", "removeFloatHandleImmediately start");
            ((FloatHandleView) this.mTarget).removeFloatHandleImmediately();
            UiLogUtils.i("FloatHandle", "removeFloatHandleImmediately end");
        }
    }

    public void removeFloatHandleView(boolean z) {
        T t = this.mTarget;
        if (t == 0 || !((FloatHandleView) t).getView().isAttachedToWindow()) {
            UiLogUtils.w("FloatHandle", "mTarget may not AttachedToWindow : " + this.mTarget);
        } else {
            UiLogUtils.i("FloatHandle", "deleteFloatHandleView start");
            ((FloatHandleView) this.mTarget).removeFloatHandleViewWithAnim(z);
            UiLogUtils.i("FloatHandle", "deleteFloatHandleView end");
        }
    }

    public void showFloatHandleView(FloatHandleInfo floatHandleInfo) {
        T t = this.mTarget;
        if (t == 0 || !((FloatHandleView) t).getView().isAttachedToWindow()) {
            UiLogUtils.w("FloatHandle", "showFloatHandleView mTarget may not AttachedToWindow : " + this.mTarget);
        } else {
            UiLogUtils.i("FloatHandle", "showHalfFloatHandleView start");
            ((FloatHandleView) this.mTarget).showFloatHandleViewWithAnim(4, floatHandleInfo.packageName, floatHandleInfo.userId);
            UiLogUtils.i("FloatHandle", "showHalfFloatHandleView end");
        }
    }
}
