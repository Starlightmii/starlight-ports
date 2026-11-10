package com.oplus.zoom;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.view.SurfaceControl;
import android.window.DisplayAreaInfo;
import android.window.TaskAppearedInfo;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.oplus.zoom.common.LogD;
import com.oplus.zoom.common.ZoomDisplayController;
import com.oplus.zoom.common.ZoomInsetsController;
import com.oplus.zoom.common.ZoomSettingDispatcher;
import com.oplus.zoom.ui.IZoomUiManager;
import com.oplus.zoom.ui.common.ZoomSharedPrefHelper;
import com.oplus.zoomwindow.IOplusZoomTaskInfoCallback;
import com.oplus.zoomwindow.OplusZoomTaskInfo;
import java.lang.invoke.VarHandle;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ZoomRootTaskController implements ShellTaskOrganizer.TaskListener, ZoomDisplayController.DisplayChangedListener, ZoomInsetsController.ImePositionListener {
    private static final String TAG = "ZoomController";
    private static final int TIME_OUT_FOR_CLEAR_FIX_ROTATION = 3000;
    private final ShellExecutor mAnimExecutor;
    private final Context mContext;
    private final ShellExecutor mMainExecutor;
    private final SyncTransactionQueue mSyncQueue;
    private final IZoomUiManager mUiManager;
    private final ZoomController mZoomController;
    private final SparseArray<ZoomRootTaskManager> mRootTaskManagers = new SparseArray<>();
    private final Object mManagersLock = new Object();
    private final SparseArray<ZoomRootTaskManager> mFloatTaskManagers = new SparseArray<>();
    private final SparseArray<OplusZoomTaskInfo> mFloatZoomTasksInfo = new SparseArray<>();
    private ArrayMap<Integer, Runnable> mTimeoutClearFixRotationRunnables = new ArrayMap<>();
    private ZoomSettingDispatcher.CallbackListener mCallbackListener = new AnonymousClass1();

    /* JADX INFO: renamed from: com.oplus.zoom.ZoomRootTaskController$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    class AnonymousClass1 implements ZoomSettingDispatcher.CallbackListener {
        Uri mSuperPowerModeSetting = Settings.System.getUriFor("super_powersave_launcher_enter");
        Uri childrenModeSetting = Settings.Global.getUriFor("children_mode_on");

        public AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onSettingChanged$1() {
            ZoomRootTaskController.this.requestChangeZoomState(-1, 6, true, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onUserSwitched$0() {
            ZoomRootTaskController.this.requestChangeZoomState(-1, 6, true, false);
        }

        @Override // com.oplus.zoom.common.ZoomSettingDispatcher.CallbackListener
        public void onBroadCastReceive(Context context, Intent intent) {
            if (intent == null || TextUtils.isEmpty(intent.getAction()) || !intent.getAction().equals("android.intent.action.USER_REMOVED")) {
                return;
            }
            int intExtra = intent.getIntExtra("android.intent.extra.user_handle", 0);
            LogD.d("ZoomController", "onBroadCastReceive userId=" + intExtra);
            ZoomSharedPrefHelper.remove(ZoomRootTaskController.this.mContext, "handle_tip_" + intExtra);
            ZoomSharedPrefHelper.remove(ZoomRootTaskController.this.mContext, "scale_tip_" + intExtra);
            ZoomSharedPrefHelper.remove(ZoomRootTaskController.this.mContext, "simple_mode_tip_" + intExtra);
            ZoomSharedPrefHelper.remove(ZoomRootTaskController.this.mContext, "mini_tip_" + intExtra);
            ZoomSharedPrefHelper.remove(ZoomRootTaskController.this.mContext, "flexible_long_tip_" + intExtra);
        }

        @Override // com.oplus.zoom.common.ZoomSettingDispatcher.CallbackListener
        public void onSettingChanged(boolean z, Uri uri, int i) {
            if (!uri.equals(this.mSuperPowerModeSetting)) {
                if (uri.equals(this.childrenModeSetting) && Settings.Global.getInt(ZoomRootTaskController.this.mContext.getContentResolver(), "children_mode_on", 0) == 1) {
                    ZoomRootTaskController.this.mMainExecutor.execute(new ZoomRootTaskController$1$$ExternalSyntheticLambda1());
                    return;
                }
                return;
            }
            ShellExecutor shellExecutor = ZoomRootTaskController.this.mMainExecutor;
            ZoomRootTaskController$1$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new ZoomRootTaskController$1$.ExternalSyntheticLambda0(0);
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            shellExecutor.execute(externalSyntheticLambda0);
        }

        @Override // com.oplus.zoom.common.ZoomSettingDispatcher.CallbackListener
        public void onUserSwitched(int i) {
            ShellExecutor shellExecutor = ZoomRootTaskController.this.mMainExecutor;
            ZoomRootTaskController$1$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new ZoomRootTaskController$1$.ExternalSyntheticLambda0(1);
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            shellExecutor.execute(externalSyntheticLambda0);
        }
    }

    public ZoomRootTaskController(Context context, SyncTransactionQueue syncTransactionQueue, ShellExecutor shellExecutor, ShellExecutor shellExecutor2, IZoomUiManager iZoomUiManager, ZoomController zoomController) {
        this.mSyncQueue = syncTransactionQueue;
        this.mContext = context;
        this.mMainExecutor = shellExecutor;
        this.mAnimExecutor = shellExecutor2;
        this.mZoomController = zoomController;
        this.mUiManager = iZoomUiManager;
        ZoomDisplayController.getInstance().registerDisplayChangedListener(this);
        ZoomInsetsController.getInstance().addImePositionListener(this);
        ZoomSettingDispatcher.getInstance().addCallbackListener(this.mCallbackListener);
    }

    private void addFixRotationRunnable(int i, Runnable runnable) {
        synchronized (this.mTimeoutClearFixRotationRunnables) {
            this.mTimeoutClearFixRotationRunnables.put(Integer.valueOf(i), runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: clearFixRotationFlag, reason: merged with bridge method [inline-methods] */
    public void lambda$onFixedRotationStarted$6(int i) {
        Runnable fixRotationRunnable = getFixRotationRunnable(i);
        if (fixRotationRunnable != null) {
            this.mMainExecutor.removeCallbacks(fixRotationRunnable);
            removeFixRotationRunnable(i);
            ZoomRootTaskController$$ExternalSyntheticLambda4 zoomRootTaskController$$ExternalSyntheticLambda4 = new ZoomRootTaskController$$ExternalSyntheticLambda4(1);
            zoomRootTaskController$$ExternalSyntheticLambda4.f$0 = i;
            VarHandle.storeStoreFence();
            forAllRootTaskManagers(zoomRootTaskController$$ExternalSyntheticLambda4);
        }
    }

    private void clearFixRotationRunnable() {
        synchronized (this.mTimeoutClearFixRotationRunnables) {
            this.mTimeoutClearFixRotationRunnables.clear();
        }
    }

    private Runnable getFixRotationRunnable(int i) {
        Runnable runnable;
        synchronized (this.mTimeoutClearFixRotationRunnables) {
            runnable = this.mTimeoutClearFixRotationRunnables.get(Integer.valueOf(i));
        }
        return runnable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$clearFixRotationFlag$8(int i, ZoomRootTaskManager zoomRootTaskManager) {
        zoomRootTaskManager.onFixedRotationFinished(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$getCurrentZoomTaskInfo$16(OplusZoomTaskInfo oplusZoomTaskInfo, ZoomRootTaskManager zoomRootTaskManager) {
        oplusZoomTaskInfo.copy(zoomRootTaskManager.getCurrentZoomTaskInfo());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$getCurrentZoomTaskInfo$17(int i, OplusZoomTaskInfo oplusZoomTaskInfo, ZoomRootTaskManager zoomRootTaskManager) {
        LogD.d("ZoomController", "forSpecifiedRootTaskManager taskId = " + i);
        oplusZoomTaskInfo.copy(zoomRootTaskManager.getCurrentZoomTaskInfo());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onTaskInfoChanged$3(ActivityManager.RunningTaskInfo runningTaskInfo, ZoomRootTaskManager zoomRootTaskManager) {
        zoomRootTaskManager.onTaskInfoChanged(runningTaskInfo, this.mZoomController.getLeash(runningTaskInfo.taskId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onTaskVanished$2(ActivityManager.RunningTaskInfo runningTaskInfo, ZoomRootTaskManager zoomRootTaskManager) {
        onZoomExit(null, runningTaskInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onZoomExit$12(ActivityManager.RunningTaskInfo runningTaskInfo, int i, ZoomRootTaskManager zoomRootTaskManager) {
        zoomRootTaskManager.onZoomExit(runningTaskInfo);
        this.mZoomController.unRegisterTransitionListener(i);
        this.mRootTaskManagers.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onZoomTaskEvent$13(int i, ZoomRootTaskManager zoomRootTaskManager) {
        zoomRootTaskManager.onZoomTaskEvent(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestChangeZoomState$14(int i, int i2, boolean z, boolean z2, ZoomRootTaskManager zoomRootTaskManager) {
        LogD.d("ZoomController", "requestChangeZoomState  taskId = " + i);
        zoomRootTaskManager.requestChangeZoomState(i2, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestChangeZoomState$15(int i, int i2, boolean z, boolean z2, ZoomRootTaskManager zoomRootTaskManager) {
        LogD.d("ZoomController", "requestChangeZoomState  taskId = " + i);
        zoomRootTaskManager.requestChangeZoomState(i2, z, z2);
    }

    private void removeFixRotationRunnable(int i) {
        synchronized (this.mTimeoutClearFixRotationRunnables) {
            this.mTimeoutClearFixRotationRunnables.remove(Integer.valueOf(i));
        }
    }

    public void forAllRootTaskManagers(Consumer<ZoomRootTaskManager> consumer) {
        synchronized (this.mManagersLock) {
            try {
                LogD.d("ZoomController", "forAllRootTaskManagers  managerSize = " + this.mRootTaskManagers.size());
                for (int i = 0; i < this.mRootTaskManagers.size(); i++) {
                    ZoomRootTaskManager zoomRootTaskManagerValueAt = this.mRootTaskManagers.valueAt(i);
                    if (zoomRootTaskManagerValueAt != null) {
                        consumer.accept(zoomRootTaskManagerValueAt);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void forSpecifiedRootTaskManager(int i, Consumer<ZoomRootTaskManager> consumer) {
        synchronized (this.mManagersLock) {
            try {
                ZoomRootTaskManager zoomRootTaskManager = this.mRootTaskManagers.get(i);
                if (zoomRootTaskManager != null) {
                    consumer.accept(zoomRootTaskManager);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void getCurrentZoomTaskInfo(int i, IOplusZoomTaskInfoCallback iOplusZoomTaskInfoCallback) {
        try {
            LogD.d("ZoomController", "getCurrentZoomTaskInfo taskId = " + i);
            OplusZoomTaskInfo oplusZoomTaskInfo = new OplusZoomTaskInfo();
            if (i == -1) {
                ZoomRootTaskController$.ExternalSyntheticLambda14 externalSyntheticLambda14 = new ZoomRootTaskController$.ExternalSyntheticLambda14(0);
                externalSyntheticLambda14.f$0 = oplusZoomTaskInfo;
                VarHandle.storeStoreFence();
                forAllRootTaskManagers(externalSyntheticLambda14);
            } else {
                ZoomRootTaskController$.ExternalSyntheticLambda15 externalSyntheticLambda15 = new ZoomRootTaskController$.ExternalSyntheticLambda15();
                externalSyntheticLambda15.f$0 = i;
                externalSyntheticLambda15.f$1 = oplusZoomTaskInfo;
                VarHandle.storeStoreFence();
                forSpecifiedRootTaskManager(i, externalSyntheticLambda15);
            }
            iOplusZoomTaskInfoCallback.onZoomInfoCallback(oplusZoomTaskInfo);
        } catch (RemoteException e) {
            LogD.e("ZoomController", "exception = " + e);
        }
    }

    public boolean isWaitForFixRotationEnd(int i) {
        return getFixRotationRunnable(i) != null;
    }

    public void notifyZoomRotateChanged(int i, int i2, OplusZoomTaskInfo oplusZoomTaskInfo) {
        this.mZoomController.notifyZoomRotateChanged(i, i2, oplusZoomTaskInfo);
    }

    public void notifyZoomStateChange(OplusZoomTaskInfo oplusZoomTaskInfo) {
        this.mZoomController.notifyZoomStateChange(oplusZoomTaskInfo);
    }

    public void onConfigChanged(Configuration configuration) {
        ZoomRootTaskController$$ExternalSyntheticLambda2 zoomRootTaskController$$ExternalSyntheticLambda2 = new ZoomRootTaskController$$ExternalSyntheticLambda2(1);
        zoomRootTaskController$$ExternalSyntheticLambda2.f$0 = configuration;
        VarHandle.storeStoreFence();
        forAllRootTaskManagers(zoomRootTaskController$$ExternalSyntheticLambda2);
    }

    @Override // com.oplus.zoom.common.ZoomDisplayController.DisplayChangedListener
    public void onDisplayChanged(int i, int i2, Configuration configuration) {
        ZoomRootTaskController$$ExternalSyntheticLambda19 zoomRootTaskController$$ExternalSyntheticLambda19 = new ZoomRootTaskController$$ExternalSyntheticLambda19();
        zoomRootTaskController$$ExternalSyntheticLambda19.f$0 = i;
        zoomRootTaskController$$ExternalSyntheticLambda19.f$1 = i2;
        zoomRootTaskController$$ExternalSyntheticLambda19.f$2 = configuration;
        VarHandle.storeStoreFence();
        forAllRootTaskManagers(zoomRootTaskController$$ExternalSyntheticLambda19);
    }

    @Override // com.oplus.zoom.common.ZoomDisplayController.DisplayChangedListener
    public void onFixedRotationFinished(int i) {
        lambda$onFixedRotationStarted$6(i);
    }

    @Override // com.oplus.zoom.common.ZoomDisplayController.DisplayChangedListener
    public void onFixedRotationStarted(int i, int i2) {
        Runnable fixRotationRunnable = getFixRotationRunnable(i);
        if (fixRotationRunnable != null) {
            this.mMainExecutor.removeCallbacks(fixRotationRunnable);
        }
        ZoomRootTaskController$$ExternalSyntheticLambda0 zoomRootTaskController$$ExternalSyntheticLambda0 = new ZoomRootTaskController$$ExternalSyntheticLambda0();
        zoomRootTaskController$$ExternalSyntheticLambda0.f$0 = this;
        zoomRootTaskController$$ExternalSyntheticLambda0.f$1 = i;
        VarHandle.storeStoreFence();
        this.mMainExecutor.executeDelayed(zoomRootTaskController$$ExternalSyntheticLambda0, 3000L);
        addFixRotationRunnable(i, zoomRootTaskController$$ExternalSyntheticLambda0);
        ZoomRootTaskController$$ExternalSyntheticLambda1 zoomRootTaskController$$ExternalSyntheticLambda1 = new ZoomRootTaskController$$ExternalSyntheticLambda1();
        zoomRootTaskController$$ExternalSyntheticLambda1.f$0 = i;
        zoomRootTaskController$$ExternalSyntheticLambda1.f$1 = i2;
        VarHandle.storeStoreFence();
        forAllRootTaskManagers(zoomRootTaskController$$ExternalSyntheticLambda1);
    }

    @Override // com.oplus.zoom.common.ZoomInsetsController.ImePositionListener
    public void onImePositionChanged(int i, boolean z, int i2, boolean z2) {
        ZoomRootTaskController$$ExternalSyntheticLambda5 zoomRootTaskController$$ExternalSyntheticLambda5 = new ZoomRootTaskController$$ExternalSyntheticLambda5(0);
        zoomRootTaskController$$ExternalSyntheticLambda5.f$0 = i;
        zoomRootTaskController$$ExternalSyntheticLambda5.f$1 = z;
        zoomRootTaskController$$ExternalSyntheticLambda5.f$2 = i2;
        zoomRootTaskController$$ExternalSyntheticLambda5.f$3 = z2;
        VarHandle.storeStoreFence();
        forAllRootTaskManagers(zoomRootTaskController$$ExternalSyntheticLambda5);
    }

    public void onRecentHomeClicked() {
        LogD.d("ZoomController", "onRecentHomeClicked");
        forAllRootTaskManagers(new ZoomRootTaskController$$ExternalSyntheticLambda11());
    }

    @Override // com.oplus.zoom.common.ZoomDisplayController.DisplayChangedListener
    public void onRotateDisplay(int i, int i2, int i3, DisplayAreaInfo displayAreaInfo, WindowContainerTransaction windowContainerTransaction) {
        ZoomRootTaskController$$ExternalSyntheticLambda16 zoomRootTaskController$$ExternalSyntheticLambda16 = new ZoomRootTaskController$$ExternalSyntheticLambda16();
        zoomRootTaskController$$ExternalSyntheticLambda16.f$0 = i;
        zoomRootTaskController$$ExternalSyntheticLambda16.f$1 = i2;
        zoomRootTaskController$$ExternalSyntheticLambda16.f$2 = i3;
        zoomRootTaskController$$ExternalSyntheticLambda16.f$3 = displayAreaInfo;
        zoomRootTaskController$$ExternalSyntheticLambda16.f$4 = windowContainerTransaction;
        VarHandle.storeStoreFence();
        forAllRootTaskManagers(zoomRootTaskController$$ExternalSyntheticLambda16);
    }

    public void onSplitDragAnimationEnd(boolean z) {
        ZoomRootTaskController$.ExternalSyntheticLambda7 externalSyntheticLambda7 = new ZoomRootTaskController$.ExternalSyntheticLambda7(0);
        externalSyntheticLambda7.f$0 = z;
        VarHandle.storeStoreFence();
        forAllRootTaskManagers(externalSyntheticLambda7);
    }

    public void onSplitDragAnimationStart(boolean z) {
        ZoomRootTaskController$.ExternalSyntheticLambda7 externalSyntheticLambda7 = new ZoomRootTaskController$.ExternalSyntheticLambda7(1);
        externalSyntheticLambda7.f$0 = z;
        VarHandle.storeStoreFence();
        forAllRootTaskManagers(externalSyntheticLambda7);
    }

    public void onTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
        int i = runningTaskInfo.hasParentTask() ? runningTaskInfo.parentTaskId : runningTaskInfo.taskId;
        ZoomRootTaskController$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new ZoomRootTaskController$.ExternalSyntheticLambda3(2);
        externalSyntheticLambda3.f$1 = runningTaskInfo;
        externalSyntheticLambda3.f$0 = surfaceControl;
        VarHandle.storeStoreFence();
        forSpecifiedRootTaskManager(i, externalSyntheticLambda3);
    }

    public void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
        LogD.d("ZoomController", "onTaskInfoChanged taskInfo = " + runningTaskInfo);
        int i = runningTaskInfo.hasParentTask() ? runningTaskInfo.parentTaskId : runningTaskInfo.taskId;
        ZoomRootTaskController$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new ZoomRootTaskController$.ExternalSyntheticLambda3(1);
        externalSyntheticLambda3.f$0 = this;
        externalSyntheticLambda3.f$1 = runningTaskInfo;
        VarHandle.storeStoreFence();
        forSpecifiedRootTaskManager(i, externalSyntheticLambda3);
    }

    public void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
        LogD.d("ZoomController", "onTaskVanished taskInfo = " + runningTaskInfo);
        ZoomRootTaskController$$ExternalSyntheticLambda2 zoomRootTaskController$$ExternalSyntheticLambda2 = new ZoomRootTaskController$$ExternalSyntheticLambda2(0);
        zoomRootTaskController$$ExternalSyntheticLambda2.f$0 = runningTaskInfo;
        VarHandle.storeStoreFence();
        forAllRootTaskManagers(zoomRootTaskController$$ExternalSyntheticLambda2);
        if (runningTaskInfo.hasParentTask()) {
            return;
        }
        int i = runningTaskInfo.taskId;
        Consumer<ZoomRootTaskManager> externalSyntheticLambda3 = new ZoomRootTaskController$.ExternalSyntheticLambda3<>(0);
        ((ZoomRootTaskController$.ExternalSyntheticLambda3) externalSyntheticLambda3).f$0 = this;
        ((ZoomRootTaskController$.ExternalSyntheticLambda3) externalSyntheticLambda3).f$1 = runningTaskInfo;
        VarHandle.storeStoreFence();
        forSpecifiedRootTaskManager(i, externalSyntheticLambda3);
    }

    public void onZoomEnter(OplusZoomTaskInfo oplusZoomTaskInfo, ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
        ZoomRootTaskController zoomRootTaskController;
        LogD.d("ZoomController", "onZoomEnter zoomTaskInfo = " + oplusZoomTaskInfo);
        if (surfaceControl == null || !surfaceControl.isValid()) {
            LogD.e("ZoomController", "onZoomEnter leash is null or not valid");
            return;
        }
        synchronized (this.mManagersLock) {
            try {
                int i = runningTaskInfo.taskId;
                ZoomRootTaskManager zoomRootTaskManager = this.mRootTaskManagers.get(i);
                if (zoomRootTaskManager == null) {
                    zoomRootTaskController = this;
                    ZoomRootTaskManager zoomRootTaskManager2 = new ZoomRootTaskManager(this.mContext, this.mSyncQueue, this.mMainExecutor, this.mAnimExecutor, this.mUiManager, zoomRootTaskController);
                    zoomRootTaskController.mRootTaskManagers.put(i, zoomRootTaskManager2);
                    zoomRootTaskManager = zoomRootTaskManager2;
                } else {
                    zoomRootTaskController = this;
                }
                zoomRootTaskManager.onZoomEnter(oplusZoomTaskInfo, runningTaskInfo, surfaceControl);
                zoomRootTaskController.mZoomController.registerTransitionListener(i, zoomRootTaskManager);
                SparseArray<TaskAppearedInfo> allAppearedTasks = zoomRootTaskController.mZoomController.getAllAppearedTasks();
                if (allAppearedTasks != null && allAppearedTasks.size() != 0) {
                    for (int size = allAppearedTasks.size() - 1; size >= 0; size--) {
                        TaskAppearedInfo taskAppearedInfoValueAt = allAppearedTasks.valueAt(size);
                        if (taskAppearedInfoValueAt != null && taskAppearedInfoValueAt.getTaskInfo().parentTaskId == oplusZoomTaskInfo.taskId && taskAppearedInfoValueAt.getTaskInfo().getWindowingMode() == 100) {
                            SurfaceControl leash = zoomRootTaskController.mZoomController.getLeash(taskAppearedInfoValueAt.getTaskInfo().taskId);
                            zoomRootTaskManager.onTaskInfoChanged(taskAppearedInfoValueAt.getTaskInfo(), leash);
                            LogD.d("ZoomController", "trigger taskInfoChanged for child task : " + taskAppearedInfoValueAt.getTaskInfo() + " leash : " + leash);
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void onZoomExit(OplusZoomTaskInfo oplusZoomTaskInfo, ActivityManager.RunningTaskInfo runningTaskInfo) {
        LogD.d("ZoomController", "onZoomExit zoomTaskInfo = " + oplusZoomTaskInfo);
        int i = runningTaskInfo.taskId;
        ZoomRootTaskController$.ExternalSyntheticLambda13 externalSyntheticLambda13 = new ZoomRootTaskController$.ExternalSyntheticLambda13();
        externalSyntheticLambda13.f$0 = this;
        externalSyntheticLambda13.f$1 = runningTaskInfo;
        externalSyntheticLambda13.f$2 = i;
        VarHandle.storeStoreFence();
        forSpecifiedRootTaskManager(i, externalSyntheticLambda13);
    }

    public void onZoomTaskChanged(OplusZoomTaskInfo oplusZoomTaskInfo) {
        LogD.d("ZoomController", "onZoomTaskChanged zoomTaskInfo = " + oplusZoomTaskInfo);
        int i = oplusZoomTaskInfo.taskId;
        ZoomRootTaskController$.ExternalSyntheticLambda14 externalSyntheticLambda14 = new ZoomRootTaskController$.ExternalSyntheticLambda14(1);
        externalSyntheticLambda14.f$0 = oplusZoomTaskInfo;
        VarHandle.storeStoreFence();
        forSpecifiedRootTaskManager(i, externalSyntheticLambda14);
    }

    public void onZoomTaskEvent(int i, int i2) {
        LogD.d("ZoomController", "notifyZoomTaskEvent taskId = " + i + ", event = " + i2);
        ZoomRootTaskController$$ExternalSyntheticLambda4 zoomRootTaskController$$ExternalSyntheticLambda4 = new ZoomRootTaskController$$ExternalSyntheticLambda4(0);
        zoomRootTaskController$$ExternalSyntheticLambda4.f$0 = i2;
        VarHandle.storeStoreFence();
        forSpecifiedRootTaskManager(i, zoomRootTaskController$$ExternalSyntheticLambda4);
    }

    public void requestAnimationStart() {
        this.mZoomController.onTransitionStart();
    }

    public void requestChangeZoomState(int i, int i2, boolean z, boolean z2) {
        LogD.d("ZoomController", "requestChangeZoomState taskId = " + i + ", flag = " + i2);
        if (i == -1) {
            ZoomRootTaskController$$ExternalSyntheticLambda5 zoomRootTaskController$$ExternalSyntheticLambda5 = new ZoomRootTaskController$$ExternalSyntheticLambda5(2);
            zoomRootTaskController$$ExternalSyntheticLambda5.f$0 = i;
            zoomRootTaskController$$ExternalSyntheticLambda5.f$2 = i2;
            zoomRootTaskController$$ExternalSyntheticLambda5.f$1 = z;
            zoomRootTaskController$$ExternalSyntheticLambda5.f$3 = z2;
            VarHandle.storeStoreFence();
            forAllRootTaskManagers(zoomRootTaskController$$ExternalSyntheticLambda5);
            return;
        }
        ZoomRootTaskController$$ExternalSyntheticLambda5 zoomRootTaskController$$ExternalSyntheticLambda6 = new ZoomRootTaskController$$ExternalSyntheticLambda5(1);
        zoomRootTaskController$$ExternalSyntheticLambda6.f$0 = i;
        zoomRootTaskController$$ExternalSyntheticLambda6.f$2 = i2;
        zoomRootTaskController$$ExternalSyntheticLambda6.f$1 = z;
        zoomRootTaskController$$ExternalSyntheticLambda6.f$3 = z2;
        VarHandle.storeStoreFence();
        forSpecifiedRootTaskManager(i, zoomRootTaskController$$ExternalSyntheticLambda6);
    }

    public void requestChangeZoomTask(WindowContainerToken windowContainerToken, int i, boolean z) {
        this.mZoomController.requestChangeZoomTask(windowContainerToken, i, z);
    }

    public void setInputToken(WindowContainerToken windowContainerToken, IBinder iBinder) {
        this.mZoomController.setInputToken(windowContainerToken, iBinder);
    }
}
