package com.oplus.zoom.ui.tips;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.android.wm.shell.common.ShellExecutor;
import com.oplus.flexible.ScreenUtils;
import com.oplus.splitscreen.ReflectionHelper;
import com.oplus.splitscreen.util.OplusActivityManagerWrapper;
import com.oplus.zoom.common.ZoomPositionInfo;
import com.oplus.zoom.common.ZoomSettingsObserver;
import com.oplus.zoom.ui.ZoomUiManager;
import com.oplus.zoom.ui.common.UiLogUtils;
import com.oplus.zoom.ui.common.ZoomSharedPrefHelper;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class TipsController {
    private static final String TAG = "Tips";
    private static final int WHAT = 1;
    private Context mContext;
    private int mCurrentUserId;
    private TipsManager mFlexibleLongClickManager;
    private TipsManager mHandleTipsManager;
    private Handler mHandler;
    private boolean mIsMini;
    private Executor mMainExecutor;
    private TipsManager mMiniGestureCloseTipsManager;
    private TipsManager mMiniTipsManager;
    private TipsManager mScaleTipsManager;
    private TipsManager mSimpleModeTipsManager;
    private SnackBarManager mSnackBarManager;
    private ToastManager mToastManager;
    private ZoomUiManager mUiManager;
    private int mIsTipsViewShowing = 0;
    private boolean mIsNeedShowGestureTips = false;
    private boolean mHideAllTips = false;

    public TipsController(Context context, Handler handler, ZoomUiManager zoomUiManager, ShellExecutor shellExecutor) {
        this.mMainExecutor = null;
        this.mContext = context;
        this.mUiManager = zoomUiManager;
        initManager(context);
        this.mMainExecutor = this.mContext.getMainExecutor();
    }

    private void initFlexibleTips(Context context) {
        ScreenUtils.getInstance().initAndRegisterFoldingModeChangeObserver(context.getApplicationContext());
        TipsManager tipsManager = new TipsManager(context);
        this.mFlexibleLongClickManager = tipsManager;
        TipsController$$ExternalSyntheticLambda1 tipsController$$ExternalSyntheticLambda1 = new TipsController$$ExternalSyntheticLambda1(0);
        tipsController$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        tipsManager.setTipsChangeListener(tipsController$$ExternalSyntheticLambda1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dismissSnackBar$7() {
        this.mSnackBarManager.dismissSnackBar();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initFlexibleTips$4(ZoomPositionInfo zoomPositionInfo) {
        UiLogUtils.d("Tips", "mFlexibleLongClickManager gone");
        this.mSnackBarManager.showSnackBar();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initManager$0(ZoomPositionInfo zoomPositionInfo) {
        UiLogUtils.d("Tips", "mHandleTipsManager gone");
        showScaleTips(zoomPositionInfo, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initManager$1(ZoomPositionInfo zoomPositionInfo) {
        UiLogUtils.d("Tips", "mScaleTipsManager gone");
        if (!ReflectionHelper.FlexibleWindowManager_isSupportPocketStudio(this.mContext) || ScreenUtils.getInstance().isFolded() || this.mFlexibleLongClickManager == null) {
            this.mSnackBarManager.showSnackBar();
            return;
        }
        ActivityManager.RunningTaskInfo runningTaskWithoutZoom = OplusActivityManagerWrapper.getInstance().getRunningTaskWithoutZoom();
        UiLogUtils.d("Tips", "mScaleTipsManager runningTask:" + runningTaskWithoutZoom + ",info:" + zoomPositionInfo);
        if (runningTaskWithoutZoom == null || runningTaskWithoutZoom.getActivityType() == 2) {
            this.mSnackBarManager.showSnackBar();
        } else {
            showFlexibleLongClickTip(zoomPositionInfo, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initManager$2(ZoomPositionInfo zoomPositionInfo) {
        if (this.mIsNeedShowGestureTips) {
            showMiniGestureCloseTips(zoomPositionInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initManager$3(ZoomPositionInfo zoomPositionInfo) {
        UiLogUtils.d("Tips", "mSimpleModeTipsManager gone");
        showFlexibleLongClickTip(zoomPositionInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCompatibilityToast$5(int i, int i2, String str, String str2, Bundle bundle) {
        this.mToastManager.showCompatibilityToast(i, i2, str, str2, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateUiStyle$6() {
        initManager(this.mContext);
    }

    private void showFlexibleLongClickTip(ZoomPositionInfo zoomPositionInfo, boolean z) {
        if (this.mHideAllTips || this.mIsMini) {
            UiLogUtils.d("Tips", "showFlexibleLongClickTip mHideAllTips:" + this.mHideAllTips + ",mIsMini:" + this.mIsMini);
            return;
        }
        if (!ReflectionHelper.FlexibleWindowManager_isSupportPocketStudio(this.mContext) || ScreenUtils.getInstance().isFolded()) {
            UiLogUtils.d("Tips", "showFlexibleLongClickTip ignore");
            return;
        }
        int i = ZoomSharedPrefHelper.getInt(this.mContext, "flexible_long_tip_" + this.mCurrentUserId, 0);
        if (z) {
            if (i == 0) {
                UiLogUtils.d("Tips", "showFlexibleLongClickTip isShowForce");
                showTipsView(zoomPositionInfo, 104, this.mFlexibleLongClickManager);
                return;
            }
            return;
        }
        if (i != 0) {
            UiLogUtils.d("Tips", "showFlexibleLongClickTip has shown");
            return;
        }
        ActivityManager.RunningTaskInfo runningTaskWithoutZoom = OplusActivityManagerWrapper.getInstance().getRunningTaskWithoutZoom();
        UiLogUtils.d("Tips", "showFlexibleLongClickTip runningTask:" + runningTaskWithoutZoom + ",info:" + zoomPositionInfo);
        if (runningTaskWithoutZoom == null || runningTaskWithoutZoom.getActivityType() == 2) {
            return;
        }
        UiLogUtils.d("Tips", "showFlexibleLongClickTip");
        showTipsView(zoomPositionInfo, 104, this.mFlexibleLongClickManager);
    }

    private void showFlexibleLongClickTipDelay(ZoomPositionInfo zoomPositionInfo) {
        this.mHandler.removeMessages(1);
        Message messageObtain = Message.obtain();
        messageObtain.obj = zoomPositionInfo;
        messageObtain.what = 1;
        this.mHandler.sendMessageDelayed(messageObtain, 300L);
    }

    private void showMiniGestureCloseTips(ZoomPositionInfo zoomPositionInfo) {
        if (this.mHideAllTips || !this.mIsMini) {
            UiLogUtils.d("Tips", "call last tip hide, dont show gesture close tips");
            return;
        }
        if (ZoomSharedPrefHelper.getInt(this.mContext, "mini_gesture_close_tip_" + this.mCurrentUserId, 0) == 0) {
            UiLogUtils.d("Tips", "showMiniGestureCloseTips");
            showTipsView(zoomPositionInfo, 105, this.mMiniGestureCloseTipsManager);
        }
    }

    private void showMiniTip(ZoomPositionInfo zoomPositionInfo) {
        if (ZoomSharedPrefHelper.getInt(this.mContext, "mini_tip_" + this.mCurrentUserId, 0) == 0) {
            UiLogUtils.d("Tips", "showMiniTips");
            showTipsView(zoomPositionInfo, 103, this.mMiniTipsManager);
        } else {
            if (this.mIsNeedShowGestureTips) {
                showMiniGestureCloseTips(zoomPositionInfo);
                return;
            }
            if (this.mFlexibleLongClickManager != null) {
                this.mHandler.removeMessages(1);
                this.mFlexibleLongClickManager.lambda$new$0();
            }
            TipsManager tipsManager = this.mScaleTipsManager;
            if (tipsManager != null) {
                tipsManager.lambda$new$0();
            }
        }
    }

    private void showScaleTips(ZoomPositionInfo zoomPositionInfo, boolean z) {
        if (this.mHideAllTips || this.mIsMini) {
            UiLogUtils.d("Tips", "call last tip hide, dont show");
            return;
        }
        int i = ZoomSharedPrefHelper.getInt(this.mContext, "scale_tip_" + this.mCurrentUserId, 0);
        TipsManager tipsManager = this.mScaleTipsManager;
        if (i == 0) {
            showTipsView(zoomPositionInfo, 102, tipsManager);
            return;
        }
        tipsManager.updateZoom(zoomPositionInfo);
        if (z) {
            showFlexibleLongClickTip(zoomPositionInfo);
        } else {
            showFlexibleLongClickTipDelay(zoomPositionInfo);
        }
    }

    private void showTipsView(ZoomPositionInfo zoomPositionInfo, int i, TipsManager tipsManager) {
        if (tipsManager == null) {
            return;
        }
        if (this.mIsTipsViewShowing == i) {
            UiLogUtils.w("Tips", "is tip view showing on zoom show");
            return;
        }
        hideAllTipsView();
        this.mHideAllTips = false;
        UiLogUtils.d("Tips", "showTipsView:" + zoomPositionInfo + " flag :" + i);
        setIsTipsViewShowing(i);
        tipsManager.showTips(zoomPositionInfo, i);
        if (i == 101) {
            ZoomSharedPrefHelper.putInt(this.mContext, "simple_mode_tip_" + this.mCurrentUserId, 1);
            return;
        }
        if (i == 100) {
            ZoomSharedPrefHelper.putInt(this.mContext, "handle_tip_" + this.mCurrentUserId, 1);
            return;
        }
        if (i == 102) {
            ZoomSharedPrefHelper.putInt(this.mContext, "scale_tip_" + this.mCurrentUserId, 1);
            return;
        }
        if (i == 103) {
            ZoomSharedPrefHelper.putInt(this.mContext, "mini_tip_" + this.mCurrentUserId, 1);
            return;
        }
        if (i == 104) {
            ZoomSharedPrefHelper.putInt(this.mContext, "flexible_long_tip_" + this.mCurrentUserId, 1);
            return;
        }
        if (i == 105) {
            ZoomSharedPrefHelper.putInt(this.mContext, "mini_gesture_close_tip_" + this.mCurrentUserId, 1);
        }
    }

    public void dismissSnackBar() {
        if (this.mSnackBarManager != null) {
            Executor executor = this.mMainExecutor;
            TipsController$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new TipsController$.ExternalSyntheticLambda0(1);
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            executor.execute(externalSyntheticLambda0);
        }
    }

    public ZoomUiManager getUiManager() {
        return this.mUiManager;
    }

    public void hideAllTipsView() {
        if (this.mIsTipsViewShowing == 0) {
            return;
        }
        if (this.mHandleTipsManager == null || this.mScaleTipsManager == null || this.mSimpleModeTipsManager == null || this.mMiniTipsManager == null || this.mMiniGestureCloseTipsManager == null) {
            UiLogUtils.w("Tips", "hideAllTipsView error, manager null");
            return;
        }
        UiLogUtils.d("Tips", "hideAllTipsView");
        this.mHideAllTips = true;
        this.mHandleTipsManager.lambda$new$0();
        this.mScaleTipsManager.lambda$new$0();
        this.mMiniTipsManager.lambda$new$0();
        this.mSimpleModeTipsManager.lambda$new$0();
        if (this.mFlexibleLongClickManager != null) {
            this.mHandler.removeMessages(1);
            this.mFlexibleLongClickManager.lambda$new$0();
        }
        this.mMiniGestureCloseTipsManager.lambda$new$0();
        setIsTipsViewShowing(0);
    }

    public void initManager(Context context) {
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.oplus.zoom.ui.tips.TipsController.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message == null || message.what != 1) {
                    return;
                }
                Object obj = message.obj;
                if (obj instanceof ZoomPositionInfo) {
                    TipsController.this.showFlexibleLongClickTip((ZoomPositionInfo) obj);
                }
            }
        };
        this.mHandleTipsManager = new TipsManager(context);
        this.mScaleTipsManager = new TipsManager(context);
        this.mMiniTipsManager = new TipsManager(context);
        this.mSimpleModeTipsManager = new TipsManager(context);
        this.mSnackBarManager = new SnackBarManager(this, context);
        this.mToastManager = new ToastManager(context);
        initFlexibleTips(context);
        this.mMiniGestureCloseTipsManager = new TipsManager(context);
        TipsManager tipsManager = this.mHandleTipsManager;
        TipsController$$ExternalSyntheticLambda1 tipsController$$ExternalSyntheticLambda1 = new TipsController$$ExternalSyntheticLambda1(1);
        tipsController$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        tipsManager.setTipsChangeListener(tipsController$$ExternalSyntheticLambda1);
        TipsManager tipsManager2 = this.mScaleTipsManager;
        TipsController$$ExternalSyntheticLambda1 tipsController$$ExternalSyntheticLambda2 = new TipsController$$ExternalSyntheticLambda1(2);
        tipsController$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        tipsManager2.setTipsChangeListener(tipsController$$ExternalSyntheticLambda2);
        TipsManager tipsManager3 = this.mMiniTipsManager;
        TipsController$$ExternalSyntheticLambda1 tipsController$$ExternalSyntheticLambda3 = new TipsController$$ExternalSyntheticLambda1(3);
        tipsController$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        tipsManager3.setTipsChangeListener(tipsController$$ExternalSyntheticLambda3);
        TipsManager tipsManager4 = this.mSimpleModeTipsManager;
        TipsController$$ExternalSyntheticLambda1 tipsController$$ExternalSyntheticLambda4 = new TipsController$$ExternalSyntheticLambda1(4);
        tipsController$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        tipsManager4.setTipsChangeListener(tipsController$$ExternalSyntheticLambda4);
    }

    public void onUserSwitch(int i) {
        initManager(this.mContext);
    }

    public void setIsTipsViewShowing(int i) {
        this.mIsTipsViewShowing = i;
    }

    public void showCompatibilityToast(int i, int i2, String str, String str2, Bundle bundle) {
        Executor executor = this.mMainExecutor;
        TipsController$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new TipsController$.ExternalSyntheticLambda2();
        externalSyntheticLambda2.f$0 = this;
        externalSyntheticLambda2.f$1 = i;
        externalSyntheticLambda2.f$2 = i2;
        externalSyntheticLambda2.f$3 = str;
        externalSyntheticLambda2.f$4 = str2;
        externalSyntheticLambda2.f$5 = bundle;
        VarHandle.storeStoreFence();
        executor.execute(externalSyntheticLambda2);
    }

    public void showMiniTipsOnZoomWindowShow(ZoomPositionInfo zoomPositionInfo, boolean z, boolean z2) {
        this.mIsNeedShowGestureTips = z2;
        showTipsOnZoomWindowShow(zoomPositionInfo, Boolean.valueOf(z));
    }

    public void showTipsOnZoomWindowShow(ZoomPositionInfo zoomPositionInfo, Boolean bool) {
        this.mCurrentUserId = ActivityManager.getCurrentUser();
        this.mIsMini = bool.booleanValue();
        this.mHideAllTips = false;
        if (bool.booleanValue()) {
            showMiniTip(zoomPositionInfo);
            return;
        }
        this.mHideAllTips = false;
        boolean simpleModeEnable = ZoomSettingsObserver.getInstance().getSimpleModeEnable();
        Context context = this.mContext;
        if (simpleModeEnable) {
            if (ZoomSharedPrefHelper.getInt(context, "simple_mode_tip_" + this.mCurrentUserId, 0) != 0) {
                showFlexibleLongClickTipDelay(zoomPositionInfo);
                return;
            } else {
                UiLogUtils.d("Tips", "showSimpleModeTip");
                showTipsView(zoomPositionInfo, 101, this.mSimpleModeTipsManager);
                return;
            }
        }
        int i = ZoomSharedPrefHelper.getInt(context, "handle_tip_" + this.mCurrentUserId, 0);
        if (i == 0) {
            UiLogUtils.d("Tips", "showTipsOnZoomWindowShow TIP_HANDLE_LONG_PRESS");
            showTipsView(zoomPositionInfo, 100, this.mHandleTipsManager);
        } else if (i != 1) {
            showFlexibleLongClickTipDelay(zoomPositionInfo);
        } else {
            UiLogUtils.d("Tips", "showTipsOnZoomWindowShow showScaleTips");
            showScaleTips(zoomPositionInfo, false);
        }
    }

    public void updateUiStyle(Configuration configuration) {
        Executor executor = this.mMainExecutor;
        TipsController$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new TipsController$.ExternalSyntheticLambda0(0);
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        executor.execute(externalSyntheticLambda0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFlexibleLongClickTip(ZoomPositionInfo zoomPositionInfo) {
        showFlexibleLongClickTip(zoomPositionInfo, false);
    }
}
