package com.oplus.zoom;

import android.app.ActivityManager;
import android.app.OplusActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.SparseArray;
import android.view.IRemoteAnimationFinishedCallback;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.window.TaskAppearedInfo;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayImeController;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.common.SystemWindows;
import com.android.wm.shell.common.TaskStackListenerCallback;
import com.android.wm.shell.common.TaskStackListenerImpl;
import com.android.wm.shell.shared.annotations.ExternalThread;
import com.android.wm.shell.shared.annotations.ShellAnimationThread;
import com.android.wm.shell.shared.annotations.ShellMainThread;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import com.oplus.flexibletask.FlexibleController;
import com.oplus.zoom.common.LogD;
import com.oplus.zoom.common.ReflectionHelper;
import com.oplus.zoom.common.ZoomDCSManager;
import com.oplus.zoom.common.ZoomDisplayController;
import com.oplus.zoom.common.ZoomInsetsController;
import com.oplus.zoom.common.ZoomParameterHelper;
import com.oplus.zoom.common.ZoomSettingDispatcher;
import com.oplus.zoom.common.ZoomSettingsObserver;
import com.oplus.zoom.transition.ZoomTransitionController;
import com.oplus.zoom.ui.IZoomUiManager;
import com.oplus.zoom.ui.ZoomUiManager;
import com.oplus.zoomwindow.IOplusZoomTaskInfoCallback;
import com.oplus.zoomwindow.IOplusZoomTaskListener;
import com.oplus.zoomwindow.OplusZoomTaskInfo;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ZoomController {
    private static final String TAG = "ZoomController";
    protected ShellExecutor mAnimExecutor;
    private Context mContext;
    private final DisplayController mDisplayController;
    private final DisplayImeController mDisplayImeController;
    private final DisplayInsetsController mDisplayInsetsController;
    protected ShellExecutor mMainExecutor;
    protected Handler mMainHandler;
    private final RootTaskDisplayAreaOrganizer mRootTDAOrganizer;
    private ZoomRootTaskController mRootTaskController;
    private ShellTaskOrganizer mShellTaskOrganizer;
    private boolean mSupportShellZoom;
    private final SyncTransactionQueue mSyncQueue;
    private final SystemWindows mSystemWindows;
    private TaskStackListenerImpl mTaskStackListener;
    private ZoomTransitionController mTransitionController;
    private final Transitions mTransitions;
    private IZoomUiManager mUiManager;
    private OplusActivityManager mActivityManager = new OplusActivityManager();
    private OplusZoomTaskListenerImpl mZoomTaskListener = new OplusZoomTaskListenerImpl(this, 0);
    private final ZoomImpl mImpl = new ZoomImpl(this);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    @ExternalThread
    class ZoomImpl implements IZoom {
        private ZoomController mController;

        public ZoomImpl(ZoomController zoomController) {
            this.mController = zoomController;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$hideZoomWindow$0(int i) {
            ZoomController.this.mRootTaskController.requestChangeZoomState(i, 6, true, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onConfigChanged$2(Configuration configuration) {
            ZoomController.this.mRootTaskController.onConfigChanged(configuration);
            ZoomController.this.mUiManager.onConfigChanged(configuration);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$requestChangeZoomState$1(int i, int i2) {
            ZoomController.this.mRootTaskController.requestChangeZoomState(i, i2, true, false);
        }

        @Override // com.oplus.zoom.IZoom
        public void hideZoomWindow(int i) {
            if (ZoomController.this.mSupportShellZoom) {
                ShellExecutor shellExecutor = ZoomController.this.mMainExecutor;
                ZoomController$ZoomImpl$$ExternalSyntheticLambda0 zoomController$ZoomImpl$$ExternalSyntheticLambda0 = new ZoomController$ZoomImpl$$ExternalSyntheticLambda0(0);
                zoomController$ZoomImpl$$ExternalSyntheticLambda0.f$0 = this;
                zoomController$ZoomImpl$$ExternalSyntheticLambda0.f$1 = i;
                VarHandle.storeStoreFence();
                shellExecutor.execute(zoomController$ZoomImpl$$ExternalSyntheticLambda0);
            }
        }

        @Override // com.oplus.zoom.IZoom
        public void onConfigChanged(Configuration configuration) {
            if (ZoomController.this.mSupportShellZoom) {
                ShellExecutor shellExecutor = ZoomController.this.mMainExecutor;
                ZoomController$ZoomImpl$$ExternalSyntheticLambda1 zoomController$ZoomImpl$$ExternalSyntheticLambda1 = new ZoomController$ZoomImpl$$ExternalSyntheticLambda1(0);
                zoomController$ZoomImpl$$ExternalSyntheticLambda1.f$0 = this;
                zoomController$ZoomImpl$$ExternalSyntheticLambda1.f$1 = configuration;
                VarHandle.storeStoreFence();
                shellExecutor.execute(zoomController$ZoomImpl$$ExternalSyntheticLambda1);
            }
        }

        @Override // com.oplus.zoom.IZoom
        public void requestChangeZoomState(int i, int i2) {
            if (ZoomController.this.mSupportShellZoom) {
                ShellExecutor shellExecutor = ZoomController.this.mMainExecutor;
                ZoomController$ZoomImpl$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new ZoomController$ZoomImpl$.ExternalSyntheticLambda2(0);
                externalSyntheticLambda2.f$0 = this;
                externalSyntheticLambda2.f$1 = i;
                externalSyntheticLambda2.f$2 = i2;
                VarHandle.storeStoreFence();
                shellExecutor.execute(externalSyntheticLambda2);
            }
        }

        @Override // com.oplus.zoom.IZoom
        public void startZoomWindow(int i, Bundle bundle) {
            if (ZoomController.this.mSupportShellZoom) {
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                windowContainerTransaction.startTask(i, bundle);
                ZoomController.this.mSyncQueue.queue(windowContainerTransaction);
            }
        }
    }

    public ZoomController(Context context, DisplayController displayController, DisplayImeController displayImeController, DisplayInsetsController displayInsetsController, TaskStackListenerImpl taskStackListenerImpl, ShellTaskOrganizer shellTaskOrganizer, SystemWindows systemWindows, SyncTransactionQueue syncTransactionQueue, Transitions transitions, Handler handler, @ShellMainThread ShellExecutor shellExecutor, @ShellAnimationThread ShellExecutor shellExecutor2, RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer, ShellInit shellInit) {
        this.mContext = context;
        this.mSystemWindows = systemWindows;
        this.mDisplayController = displayController;
        this.mDisplayImeController = displayImeController;
        this.mDisplayInsetsController = displayInsetsController;
        this.mTaskStackListener = taskStackListenerImpl;
        this.mSyncQueue = syncTransactionQueue;
        this.mTransitions = transitions;
        this.mMainHandler = handler;
        this.mMainExecutor = shellExecutor;
        this.mAnimExecutor = shellExecutor2;
        this.mRootTDAOrganizer = rootTaskDisplayAreaOrganizer;
        this.mShellTaskOrganizer = shellTaskOrganizer;
        ZoomController$$ExternalSyntheticLambda0 zoomController$$ExternalSyntheticLambda0 = new ZoomController$$ExternalSyntheticLambda0();
        zoomController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        shellInit.addInitCallback(zoomController$$ExternalSyntheticLambda0, this);
        LogD.d("ZoomController", "ZoomController is init");
        FlexibleController.getInstance().initialize(context, displayController, shellTaskOrganizer, handler, shellExecutor);
    }

    public IZoom asZoom() {
        return this.mImpl;
    }

    public void attachToDisplayArea(int i, SurfaceControl surfaceControl, SurfaceControl.Transaction transaction) {
        this.mRootTDAOrganizer.attachToDisplayArea(i, surfaceControl, transaction);
    }

    public SparseArray<TaskAppearedInfo> getAllAppearedTasks() {
        return this.mShellTaskOrganizer.getTasks();
    }

    public SurfaceControl getLeash(int i) {
        return this.mShellTaskOrganizer.peekTaskLeash(i);
    }

    /* JADX INFO: renamed from: initialize, reason: merged with bridge method [inline-methods] */
    public void lambda$new$0() {
        if (!ReflectionHelper.OplusZoomTaskManager_supportZoomTaskController()) {
            LogD.w("ZoomController", "unsupport zoom task controller");
            return;
        }
        this.mSupportShellZoom = true;
        ZoomDisplayController.getInstance().init(this.mDisplayController, this.mContext, this.mShellTaskOrganizer);
        LogD.d("ZoomController", "ZoomInsetsController is init");
        ZoomInsetsController.getInstance().init(this.mDisplayController, this.mDisplayInsetsController);
        LogD.d("ZoomController", "ZoomSettingDispatcher is init");
        ZoomSettingDispatcher.getInstance().init(this.mContext, this.mMainHandler);
        LogD.d("ZoomController", "ZoomSettingsObserver is init");
        ZoomSettingsObserver.getInstance().init(this.mContext);
        LogD.d("ZoomController", "ZoomDCSManager is init");
        ZoomDCSManager.getInstance().init(this.mContext);
        LogD.d("ZoomController", "OplusZoomFeature is init");
        OplusZoomFeature.getInstance().injectZoomController(this);
        IZoomUiManager iZoomUiManagerAsZoomUi = new ZoomUiManager(this.mContext, this.mSyncQueue, this.mMainHandler, asZoom(), this.mMainExecutor).asZoomUi();
        this.mUiManager = iZoomUiManagerAsZoomUi;
        this.mRootTaskController = new ZoomRootTaskController(this.mContext, this.mSyncQueue, this.mMainExecutor, this.mAnimExecutor, iZoomUiManagerAsZoomUi, this);
        ZoomParameterHelper.getInstance().init(this.mContext, ZoomDisplayController.getInstance());
        this.mTransitionController = new ZoomTransitionController(this, this.mTransitions, this.mAnimExecutor, this.mRootTaskController);
        ReflectionHelper.OplusZoomTaskManager_registerZoomTaskListener(this.mZoomTaskListener);
        this.mTaskStackListener.addListener(new TaskStackListenerCallback() { // from class: com.oplus.zoom.ZoomController.1
            public void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
                LogD.d("ZoomController", "onTaskMovedToFront, task id:" + runningTaskInfo.taskId);
                ZoomController.this.mUiManager.onTaskMovedToFront(runningTaskInfo);
            }

            public void onTaskRemoved(int i) {
                LogD.d("ZoomController", "onTaskRemoved");
            }
        });
        this.mShellTaskOrganizer.addListenerForType(this.mRootTaskController, new int[]{-100});
        LogD.d("ZoomController", "initialize finished");
    }

    public void notifyZoomRotateChanged(int i, int i2, OplusZoomTaskInfo oplusZoomTaskInfo) {
        ReflectionHelper.OplusZoomTaskManager_onZoomRotateChanged(i, i2, oplusZoomTaskInfo);
    }

    public void notifyZoomStateChange(OplusZoomTaskInfo oplusZoomTaskInfo) {
        if (oplusZoomTaskInfo.launchFrom == 8) {
            LogD.d("ZoomController", "forceCancelRecentsTransition while zoomTaskInfo.launchFrom == FLAG_ON_START_ZOOM_TO_FULL");
            this.mTransitions.forceCancelRecentsTransition();
        }
        ReflectionHelper.OplusZoomTaskManager_onZoomStateChanged(oplusZoomTaskInfo);
    }

    public void onTransitionEnd(int i) {
        ReflectionHelper.OplusZoomTaskManager_onTransitionEnd(i);
    }

    public void onTransitionStart() {
        ReflectionHelper.OplusZoomTaskManager_onTransitionStart();
    }

    public void registerTransitionListener(int i, ZoomTransitionController.IZoomTransitionListener iZoomTransitionListener) {
        this.mTransitionController.registerTransitionListener(i, iZoomTransitionListener);
    }

    public void requestChangeZoomTask(WindowContainerToken windowContainerToken, int i, boolean z) {
        ReflectionHelper.OplusZoomTaskManager_requestChangeZoomTask(windowContainerToken, i, z);
    }

    public void setInputToken(WindowContainerToken windowContainerToken, IBinder iBinder) {
        ReflectionHelper.OplusZoomTaskManager_setInputToken(windowContainerToken, iBinder);
    }

    public void unRegisterTransitionListener(int i) {
        this.mTransitionController.unRegisterTransitionListener(i);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    class OplusZoomTaskListenerImpl extends IOplusZoomTaskListener.Stub {
        private OplusZoomTaskListenerImpl() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$getCurrentZoomInfo$7(int i, IOplusZoomTaskInfoCallback iOplusZoomTaskInfoCallback) {
            ZoomController.this.mRootTaskController.getCurrentZoomTaskInfo(i, iOplusZoomTaskInfoCallback);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$notifyShowCompatibilityToast$9(int i, int i2, String str, String str2, Bundle bundle) {
            ZoomController.this.mUiManager.showCompatibilityToast(i, i2, str, str2, bundle);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$notifySystemEvent$4(int i) {
            if (i != 1) {
                return;
            }
            ZoomController.this.mRootTaskController.onRecentHomeClicked();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$notifyZoomTaskEvent$3(int i, int i2) {
            ZoomController.this.mRootTaskController.onZoomTaskEvent(i, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onRemoteTransitionCancel$8(int i, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
            ZoomController.this.mTransitionController.onRemoteTransitionCancel(i, iRemoteAnimationFinishedCallback);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onRemoteTransitionStart$6(int i, RemoteAnimationTarget[] remoteAnimationTargetArr) {
            ZoomController.this.mTransitionController.onRemoteTransitionStart(i, remoteAnimationTargetArr);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onZoomEnter$0(OplusZoomTaskInfo oplusZoomTaskInfo, ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
            ZoomController.this.mRootTaskController.onZoomEnter(oplusZoomTaskInfo, runningTaskInfo, surfaceControl);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onZoomExit$2(OplusZoomTaskInfo oplusZoomTaskInfo, ActivityManager.RunningTaskInfo runningTaskInfo) {
            ZoomController.this.mRootTaskController.onZoomExit(oplusZoomTaskInfo, runningTaskInfo);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onZoomTaskChanged$1(OplusZoomTaskInfo oplusZoomTaskInfo) {
            ZoomController.this.mRootTaskController.onZoomTaskChanged(oplusZoomTaskInfo);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$requestChangeZoomState$5(int i, int i2, boolean z) {
            ZoomController.this.mRootTaskController.requestChangeZoomState(i, i2, z, true);
        }

        public void getCurrentZoomInfo(int i, IOplusZoomTaskInfoCallback iOplusZoomTaskInfoCallback) {
            ShellExecutor shellExecutor = ZoomController.this.mMainExecutor;
            ZoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0 zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0 = new ZoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0(0);
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0.f$0 = this;
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0.f$1 = i;
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0.f$2 = iOplusZoomTaskInfoCallback;
            VarHandle.storeStoreFence();
            shellExecutor.execute(zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0);
        }

        public void notifyShowCompatibilityToast(int i, int i2, String str, String str2, Bundle bundle) {
            ShellExecutor shellExecutor = ZoomController.this.mMainExecutor;
            ZoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda6 zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda6 = new ZoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda6();
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda6.f$0 = this;
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda6.f$1 = i;
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda6.f$2 = i2;
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda6.f$3 = str;
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda6.f$4 = str2;
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda6.f$5 = bundle;
            VarHandle.storeStoreFence();
            shellExecutor.execute(zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda6);
        }

        public void notifySystemEvent(int i) {
            ShellExecutor shellExecutor = ZoomController.this.mMainExecutor;
            ZoomController$ZoomImpl$$ExternalSyntheticLambda0 zoomController$ZoomImpl$$ExternalSyntheticLambda0 = new ZoomController$ZoomImpl$$ExternalSyntheticLambda0(1);
            zoomController$ZoomImpl$$ExternalSyntheticLambda0.f$0 = this;
            zoomController$ZoomImpl$$ExternalSyntheticLambda0.f$1 = i;
            VarHandle.storeStoreFence();
            shellExecutor.execute(zoomController$ZoomImpl$$ExternalSyntheticLambda0);
        }

        public void notifyZoomTaskEvent(int i, int i2) {
            ShellExecutor shellExecutor = ZoomController.this.mMainExecutor;
            ZoomController$ZoomImpl$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new ZoomController$ZoomImpl$.ExternalSyntheticLambda2(1);
            externalSyntheticLambda2.f$0 = this;
            externalSyntheticLambda2.f$1 = i;
            externalSyntheticLambda2.f$2 = i2;
            VarHandle.storeStoreFence();
            shellExecutor.execute(externalSyntheticLambda2);
        }

        public void onRemoteTransitionCancel(int i, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
            ShellExecutor shellExecutor = ZoomController.this.mMainExecutor;
            ZoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0 zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0 = new ZoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0(2);
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0.f$0 = this;
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0.f$1 = i;
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0.f$2 = iRemoteAnimationFinishedCallback;
            VarHandle.storeStoreFence();
            shellExecutor.execute(zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0);
        }

        public void onRemoteTransitionStart(int i, RemoteAnimationTarget[] remoteAnimationTargetArr) {
            ShellExecutor shellExecutor = ZoomController.this.mMainExecutor;
            ZoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0 zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0 = new ZoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0(1);
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0.f$0 = this;
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0.f$1 = i;
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0.f$2 = remoteAnimationTargetArr;
            VarHandle.storeStoreFence();
            shellExecutor.execute(zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0);
        }

        public void onZoomEnter(OplusZoomTaskInfo oplusZoomTaskInfo, ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
            ShellExecutor shellExecutor = ZoomController.this.mMainExecutor;
            ZoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda7 zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda7 = new ZoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda7();
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda7.f$0 = this;
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda7.f$1 = oplusZoomTaskInfo;
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda7.f$2 = runningTaskInfo;
            zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda7.f$3 = surfaceControl;
            VarHandle.storeStoreFence();
            shellExecutor.execute(zoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda7);
        }

        public void onZoomExit(OplusZoomTaskInfo oplusZoomTaskInfo, ActivityManager.RunningTaskInfo runningTaskInfo) {
            ShellExecutor shellExecutor = ZoomController.this.mMainExecutor;
            ZoomController$OplusZoomTaskListenerImpl$.ExternalSyntheticLambda8 externalSyntheticLambda8 = new ZoomController$OplusZoomTaskListenerImpl$.ExternalSyntheticLambda8();
            externalSyntheticLambda8.f$0 = this;
            externalSyntheticLambda8.f$1 = oplusZoomTaskInfo;
            externalSyntheticLambda8.f$2 = runningTaskInfo;
            VarHandle.storeStoreFence();
            shellExecutor.execute(externalSyntheticLambda8);
        }

        public void onZoomTaskChanged(OplusZoomTaskInfo oplusZoomTaskInfo) {
            ShellExecutor shellExecutor = ZoomController.this.mMainExecutor;
            ZoomController$ZoomImpl$$ExternalSyntheticLambda1 zoomController$ZoomImpl$$ExternalSyntheticLambda1 = new ZoomController$ZoomImpl$$ExternalSyntheticLambda1(1);
            zoomController$ZoomImpl$$ExternalSyntheticLambda1.f$0 = this;
            zoomController$ZoomImpl$$ExternalSyntheticLambda1.f$1 = oplusZoomTaskInfo;
            VarHandle.storeStoreFence();
            shellExecutor.execute(zoomController$ZoomImpl$$ExternalSyntheticLambda1);
        }

        public void requestChangeZoomState(int i, int i2, boolean z) {
            ShellExecutor shellExecutor = ZoomController.this.mMainExecutor;
            ZoomController$OplusZoomTaskListenerImpl$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new ZoomController$OplusZoomTaskListenerImpl$.ExternalSyntheticLambda3();
            externalSyntheticLambda3.f$0 = this;
            externalSyntheticLambda3.f$1 = i;
            externalSyntheticLambda3.f$2 = i2;
            externalSyntheticLambda3.f$3 = z;
            VarHandle.storeStoreFence();
            shellExecutor.execute(externalSyntheticLambda3);
        }

        public /* synthetic */ OplusZoomTaskListenerImpl(ZoomController zoomController, int i) {
            this();
        }
    }

    public void attachToDisplayArea(int i, SurfaceControl.Builder builder) {
        this.mRootTDAOrganizer.attachToDisplayArea(i, builder);
    }
}
