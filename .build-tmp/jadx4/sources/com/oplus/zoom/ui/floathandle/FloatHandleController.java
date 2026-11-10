package com.oplus.zoom.ui.floathandle;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.text.TextUtils;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.oplus.content.OplusIntent;
import com.oplus.transition.OplusAnimationController;
import com.oplus.zoom.ui.ZoomUiManager;
import com.oplus.zoom.ui.common.UiLogUtils;
import com.oplus.zoom.ui.common.UiUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class FloatHandleController {
    private static final String KEY_WINDOWING_MODE = "android.activity.windowingMode";
    private static final int MSG_REMOVE_FLOAT = 10;
    private static final String TAG = "FloatHandleController";
    private Context mContext;
    private Handler mFloatHandler;
    private boolean mInChildMode;
    private boolean mInZenMode;
    private FloatHandleViewManager mLastTargetManager;
    private FloatHandleViewManager mLeftFloatHandleViewManager;
    private Executor mMainExecutor;
    private Handler mMainHandler;
    private FloatHandleViewManager mRightFloatHandleViewManager;
    private FloatHandleStatus mStatus;
    private SyncTransactionQueue mSyncQueue;
    private FloatHandleViewManager mTargetManager;
    private ZoomUiManager mUiManager;

    public FloatHandleController(Context context, SyncTransactionQueue syncTransactionQueue, Handler handler, ZoomUiManager zoomUiManager) {
        this.mMainExecutor = null;
        this.mContext = context;
        this.mSyncQueue = syncTransactionQueue;
        this.mMainHandler = handler;
        this.mUiManager = zoomUiManager;
        this.mMainExecutor = context.getMainExecutor();
        initHandler();
        FloatHandleStatus floatHandleStatus = new FloatHandleStatus(context);
        this.mStatus = floatHandleStatus;
        this.mLeftFloatHandleViewManager = new FloatHandleViewManager(this.mContext, 1, floatHandleStatus, this);
        this.mRightFloatHandleViewManager = new FloatHandleViewManager(this.mContext, 2, this.mStatus, this);
        this.mTargetManager = this.mLeftFloatHandleViewManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: addFloatHandle, reason: merged with bridge method [inline-methods] */
    public void lambda$addFloatHandle$0() {
        UiLogUtils.d("addFloatHandle");
        FloatHandleInfo floatHandleInfo = getFloatHandleInfo();
        FloatHandleViewManager floatHandleViewManager = this.mTargetManager;
        this.mLastTargetManager = floatHandleViewManager;
        FloatHandleViewManager floatHandleViewManager2 = floatHandleInfo.side == 0 ? this.mLeftFloatHandleViewManager : this.mRightFloatHandleViewManager;
        this.mTargetManager = floatHandleViewManager2;
        if (floatHandleViewManager != null && floatHandleViewManager2 != floatHandleViewManager) {
            floatHandleViewManager.removeFloatHandleView(false);
        }
        this.mTargetManager.lambda$relaunchFloatHandleView$0(getFloatHandleInfo());
    }

    private void initHandler() {
        this.mFloatHandler = new Handler(Looper.getMainLooper()) { // from class: com.oplus.zoom.ui.floathandle.FloatHandleController.1
            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void lambda$handleMessage$0() {
                FloatHandleController.this.mTargetManager.removeFloatHandleView(true);
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what != 10) {
                    return;
                }
                UiLogUtils.d("mFloatHandler removeFloatHandle");
                Executor executor = FloatHandleController.this.mMainExecutor;
                FloatHandleController$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new FloatHandleController$.ExternalSyntheticLambda0(5);
                externalSyntheticLambda0.f$0 = this;
                VarHandle.storeStoreFence();
                executor.execute(externalSyntheticLambda0);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onDisplayChanged$1() {
        this.mStatus.clearRestoreInfo();
        this.mTargetManager.refreshFloatHandleView(getFloatHandleInfo());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshFloatHandle$2() {
        this.mTargetManager.refreshFloatHandleView(getFloatHandleInfo());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateFloatHandleInSpecMode, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void lambda$updateZenModeState$3() {
        FloatHandleInfo floatHandleInfo = this.mStatus.getFloatHandleInfo();
        if (this.mInChildMode || this.mInZenMode) {
            hideFloatHandle();
        } else if (UiUtil.isPackageShowOnFront(this.mUiManager.getActivityManager(), floatHandleInfo.packageName, floatHandleInfo.userId)) {
            hideFloatHandle();
        } else {
            showFloatHandle();
        }
    }

    public FloatHandleInfo getFloatHandleInfo() {
        return this.mStatus.getFloatHandleInfo();
    }

    public Rect getFloatHandleRegion(Rect rect, float f) {
        return this.mStatus.getFloatHandleRegion(rect, f);
    }

    public void hideFloatHandle() {
        this.mTargetManager.hideFloatHandleView(getFloatHandleInfo());
    }

    public void onConfigurationChange(Configuration configuration) {
        this.mStatus.onConfigurationChange(configuration);
    }

    public void onDisplayChanged(Configuration configuration) {
        if (this.mStatus.isFloatHandleShow()) {
            Executor executor = this.mMainExecutor;
            FloatHandleController$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new FloatHandleController$.ExternalSyntheticLambda0(1);
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            executor.execute(externalSyntheticLambda0);
        }
    }

    public void onPackageRemove(String str, int i) {
        FloatHandleInfo floatHandleInfo = this.mStatus.getFloatHandleInfo();
        if (str.equalsIgnoreCase(floatHandleInfo.packageName) && floatHandleInfo.userId == i) {
            removeFloatHandle();
        }
    }

    public void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (this.mStatus.isFloatHandleShow()) {
            FloatHandleInfo floatHandleInfo = this.mStatus.getFloatHandleInfo();
            ComponentName componentName = runningTaskInfo.realActivity;
            String packageName = componentName != null ? componentName.getPackageName() : null;
            if (!TextUtils.isEmpty(packageName) && packageName.equalsIgnoreCase(floatHandleInfo.packageName) && floatHandleInfo.userId == runningTaskInfo.userId) {
                removeFloatHandle();
            }
        }
    }

    public void onUserSwitched(int i) {
        if (this.mStatus.isFloatHandleShow() || this.mStatus.getCurrentUserId() != i) {
            UiLogUtils.d("onUserSwitched removeFloatHandle userId = " + i);
            removeFloatHandle();
        }
    }

    public void refreshFloatHandle() {
        if (this.mStatus.isFloatHandleShow()) {
            Executor executor = this.mMainExecutor;
            FloatHandleController$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new FloatHandleController$.ExternalSyntheticLambda0(2);
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            executor.execute(externalSyntheticLambda0);
        }
    }

    public void relaunchFloatHandle() {
        this.mTargetManager.relaunchFloatHandleView(getFloatHandleInfo());
    }

    public void removeFloatHandle() {
        this.mFloatHandler.removeMessages(10);
        Message messageObtain = Message.obtain();
        messageObtain.what = 10;
        this.mFloatHandler.sendMessageDelayed(messageObtain, 300L);
    }

    public void showFloatHandle() {
        this.mTargetManager.showFloatHandleView(getFloatHandleInfo());
    }

    public void startZoomWindow(FloatHandleInfo floatHandleInfo) {
        UiLogUtils.d("startZoomWindow info = " + floatHandleInfo);
        Bundle bundle = new Bundle();
        bundle.putInt("android:activity.mZoomLaunchFlags", 4);
        bundle.putInt(KEY_WINDOWING_MODE, 100);
        boolean z = false;
        if (floatHandleInfo.taskId != -1) {
            try {
                ActivityManager.getService().startActivityFromRecents(floatHandleInfo.taskId, bundle);
                z = true;
                UiLogUtils.d("startZoomWindow from recent taskId = " + floatHandleInfo.taskId);
            } catch (Exception e) {
                UiLogUtils.e("startZoomWindow from recent error ", e);
            }
        }
        if (z) {
            return;
        }
        ComponentName componentName = floatHandleInfo.componentName;
        if (componentName == null) {
            ResolveInfo resolveInfo = UiUtil.getResolveInfo(this.mContext, floatHandleInfo.packageName);
            componentName = resolveInfo != null ? new ComponentName(floatHandleInfo.packageName, resolveInfo.activityInfo.name) : null;
        }
        try {
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.addFlags(270532608);
            intent.setComponent(componentName);
            OplusIntent.setOplusFlags(intent, OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT);
            this.mContext.startActivityAsUser(intent, bundle, UserHandle.of(floatHandleInfo.userId));
            UiLogUtils.d("startZoomWindow by intent = " + intent);
        } catch (Exception e2) {
            UiLogUtils.e("startZoomWindow by intent error ", e2);
        }
    }

    public void updateChildModeState(boolean z) {
        if (this.mStatus.isFloatHandleShow()) {
            this.mInChildMode = z;
            Executor executor = this.mMainExecutor;
            FloatHandleController$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new FloatHandleController$.ExternalSyntheticLambda0(3);
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            executor.execute(externalSyntheticLambda0);
        }
    }

    public void updateSupperPowerModeState(boolean z) {
        if (this.mStatus.isFloatHandleShow() && z) {
            removeFloatHandle();
        }
    }

    public void updateZenModeState(boolean z) {
        if (this.mStatus.isFloatHandleShow()) {
            this.mInZenMode = z;
            Executor executor = this.mMainExecutor;
            FloatHandleController$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new FloatHandleController$.ExternalSyntheticLambda0(0);
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            executor.execute(externalSyntheticLambda0);
        }
    }

    public void addFloatHandle(FloatHandleInfo floatHandleInfo) {
        this.mStatus.addFloatHandleInfo(floatHandleInfo);
        Executor executor = this.mMainExecutor;
        FloatHandleController$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new FloatHandleController$.ExternalSyntheticLambda0(4);
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        executor.execute(externalSyntheticLambda0);
    }
}
