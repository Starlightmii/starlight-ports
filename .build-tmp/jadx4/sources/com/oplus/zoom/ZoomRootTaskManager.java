package com.oplus.zoom;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.SparseArray;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.window.DisplayAreaInfo;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.transition.Transitions;
import com.oplus.zoom.common.LogD;
import com.oplus.zoom.common.ZoomUtil;
import com.oplus.zoom.transition.ZoomTransitionController;
import com.oplus.zoom.ui.IZoomUiManager;
import com.oplus.zoom.zoomstate.IZoomStateManager;
import com.oplus.zoom.zoomstate.ZoomStateManager;
import com.oplus.zoomwindow.OplusZoomTaskInfo;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ZoomRootTaskManager implements ZoomTransitionController.IZoomTransitionListener {
    private static final String TAG = "ZoomController";
    private final ShellExecutor mAnimExecutor;
    private final SparseArray<State> mChildTasks = new SparseArray<>();
    private final Context mContext;
    private boolean mHasFocus;
    private final ShellExecutor mMainExecutor;
    private final ZoomRootTaskController mRootTaskController;
    private ActivityManager.RunningTaskInfo mRootTaskInfo;
    private final SyncTransactionQueue mSyncQueue;
    private IZoomUiManager mUiManager;
    private boolean mVisible;
    private int mVisibleTask;
    private IZoomStateManager mZoomStateManager;

    public ZoomRootTaskManager(Context context, SyncTransactionQueue syncTransactionQueue, ShellExecutor shellExecutor, ShellExecutor shellExecutor2, IZoomUiManager iZoomUiManager, ZoomRootTaskController zoomRootTaskController) {
        this.mContext = context;
        this.mSyncQueue = syncTransactionQueue;
        this.mMainExecutor = shellExecutor;
        this.mAnimExecutor = shellExecutor2;
        this.mUiManager = iZoomUiManager;
        this.mRootTaskController = zoomRootTaskController;
    }

    private void initChildTask(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
        LogD.d("ZoomController", "initChildTask taskInfo = " + runningTaskInfo);
        State state = new State(0);
        state.mTaskInfo = runningTaskInfo;
        state.mLeash = surfaceControl;
        this.mChildTasks.put(runningTaskInfo.taskId, state);
        SyncTransactionQueue syncTransactionQueue = this.mSyncQueue;
        ZoomRootTaskManager$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new ZoomRootTaskManager$.ExternalSyntheticLambda1();
        externalSyntheticLambda1.f$0 = this;
        externalSyntheticLambda1.f$1 = surfaceControl;
        VarHandle.storeStoreFence();
        syncTransactionQueue.runInSync(externalSyntheticLambda1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initChildTask$0(SurfaceControl surfaceControl, SurfaceControl.Transaction transaction) {
        IZoomStateManager iZoomStateManager;
        if (surfaceControl == null || !surfaceControl.isValid() || (iZoomStateManager = this.mZoomStateManager) == null || !iZoomStateManager.getRootLeash().isValid()) {
            return;
        }
        transaction.reparent(surfaceControl, this.mZoomStateManager.getRootLeash()).setScale(surfaceControl, 1.0f, 1.0f).setWindowCrop(surfaceControl, null).setPosition(surfaceControl, 0.0f, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onChildTaskInfoChanged$1(State state, SurfaceControl.Transaction transaction) {
        SurfaceControl surfaceControl = state.mLeash;
        if (surfaceControl != null) {
            surfaceControl.isValid();
        }
    }

    private void onChildTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
        State state = this.mChildTasks.get(runningTaskInfo.taskId);
        if (state == null) {
            return;
        }
        SyncTransactionQueue syncTransactionQueue = this.mSyncQueue;
        ZoomRootTaskManager$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new ZoomRootTaskManager$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = state;
        VarHandle.storeStoreFence();
        syncTransactionQueue.runInSync(externalSyntheticLambda0);
        state.mTaskInfo = runningTaskInfo;
        LogD.d("ZoomController", "onChildTaskChanged taskInfo = " + runningTaskInfo);
    }

    private void updateChildTaskFocusStateIfNeed() {
        boolean z = true;
        int size = this.mChildTasks.size() - 1;
        while (true) {
            if (size < 0) {
                z = false;
                break;
            } else if (this.mChildTasks.valueAt(size).mTaskInfo.isFocused) {
                break;
            } else {
                size--;
            }
        }
        if (this.mHasFocus != z) {
            this.mHasFocus = z;
            this.mZoomStateManager.onChildTaskFocusChanged(z);
        }
    }

    private void updateChildTaskVisibilityIfNeed() {
        int i;
        boolean z = true;
        int size = this.mChildTasks.size() - 1;
        while (true) {
            if (size < 0) {
                z = false;
                i = -1;
                break;
            } else {
                ActivityManager.RunningTaskInfo runningTaskInfo = this.mChildTasks.valueAt(size).mTaskInfo;
                if (runningTaskInfo.isVisible) {
                    i = runningTaskInfo.taskId;
                    break;
                }
                size--;
            }
        }
        if (this.mVisible == z && i == this.mVisibleTask) {
            return;
        }
        this.mVisible = z;
        this.mVisibleTask = i;
        this.mZoomStateManager.onChildTaskVisibilityChanged(z);
    }

    public ActivityManager.RunningTaskInfo getChildTaskInfo(int i) {
        State state = this.mChildTasks.get(i);
        if (state != null) {
            return state.mTaskInfo;
        }
        return null;
    }

    public SurfaceControl getChildTaskLeash(int i) {
        State state = this.mChildTasks.get(i);
        if (state != null) {
            return state.mLeash;
        }
        return null;
    }

    public OplusZoomTaskInfo getCurrentZoomTaskInfo() {
        return this.mZoomStateManager.getZoomTaskInfo();
    }

    public int getTopVisibleZoomTask() {
        State stateValueAt;
        int size = this.mChildTasks.size();
        do {
            size--;
            SparseArray<State> sparseArray = this.mChildTasks;
            if (size < 0) {
                if (sparseArray.size() == 0) {
                    return -1;
                }
                SparseArray<State> sparseArray2 = this.mChildTasks;
                return sparseArray2.valueAt(sparseArray2.size() - 1).mTaskInfo.taskId;
            }
            stateValueAt = sparseArray.valueAt(size);
        } while (!stateValueAt.mTaskInfo.isVisible);
        LogD.d("ZoomController", "getTopZoomVisibleTaskLeash mTaskInfo = " + stateValueAt.mTaskInfo);
        return stateValueAt.mTaskInfo.taskId;
    }

    public boolean isWaitForFixRotationEnd() {
        return this.mRootTaskController.isWaitForFixRotationEnd(this.mRootTaskInfo.displayId);
    }

    public void notifyZoomRotateChanged(int i, int i2, OplusZoomTaskInfo oplusZoomTaskInfo) {
        this.mRootTaskController.notifyZoomRotateChanged(i, i2, oplusZoomTaskInfo);
    }

    public void notifyZoomStateChange(OplusZoomTaskInfo oplusZoomTaskInfo) {
        this.mRootTaskController.notifyZoomStateChange(oplusZoomTaskInfo);
        ZoomUtil.sCurrentZoomTaskInfo = oplusZoomTaskInfo;
        TransitionUtil.setOplusZoomTaskScaleRect(oplusZoomTaskInfo.scaleRect);
    }

    public void onConfigChanged(Configuration configuration) {
        if (configuration != null) {
            this.mZoomStateManager.onConfigChanged(configuration);
        }
    }

    public void onDisplayChanged(int i, int i2, Configuration configuration) {
        if (i != this.mRootTaskInfo.displayId) {
            return;
        }
        this.mZoomStateManager.onDisplayChanged(i2, configuration);
    }

    public void onFixedRotationFinished(int i) {
        if (i != this.mRootTaskInfo.displayId) {
            return;
        }
        this.mZoomStateManager.onFixedRotationFinished();
    }

    public void onFixedRotationStarted(int i, int i2) {
        if (i != this.mRootTaskInfo.displayId) {
            return;
        }
        this.mZoomStateManager.onFixedRotationStarted(i2);
    }

    public void onImePositionChanged(int i, boolean z, int i2, boolean z2) {
        if (i != this.mRootTaskInfo.displayId) {
            return;
        }
        this.mZoomStateManager.onImePositionChanged(z, i2, z2);
    }

    public void onRecentHomeClicked() {
        this.mZoomStateManager.onRecentHomeClicked();
    }

    public void onRotateDisplay(int i, int i2, int i3, DisplayAreaInfo displayAreaInfo, WindowContainerTransaction windowContainerTransaction) {
        if (i != this.mRootTaskInfo.displayId) {
            return;
        }
        this.mZoomStateManager.onRotateDisplay(i2, i3, windowContainerTransaction);
    }

    public void onSplitDragAnimationEnd(boolean z) {
        this.mZoomStateManager.onSplitDragAnimationEnd(z);
    }

    public void onSplitDragAnimationStart(boolean z) {
        this.mZoomStateManager.onSplitDragAnimationStart(z);
    }

    public void onTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
        if (this.mRootTaskInfo.taskId != runningTaskInfo.taskId) {
            initChildTask(runningTaskInfo, surfaceControl);
            updateChildTaskVisibilityIfNeed();
            updateChildTaskFocusStateIfNeed();
        }
    }

    public void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
        int i = this.mRootTaskInfo.taskId;
        int i2 = runningTaskInfo.taskId;
        if (i == i2) {
            this.mRootTaskInfo = runningTaskInfo;
            this.mZoomStateManager.onTaskInfoChanged(runningTaskInfo);
        } else if (this.mChildTasks.contains(i2)) {
            onChildTaskInfoChanged(runningTaskInfo);
        } else {
            initChildTask(runningTaskInfo, surfaceControl);
        }
        updateChildTaskVisibilityIfNeed();
        updateChildTaskFocusStateIfNeed();
    }

    public void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (this.mChildTasks.contains(runningTaskInfo.taskId)) {
            this.mChildTasks.remove(runningTaskInfo.taskId);
            updateChildTaskVisibilityIfNeed();
            updateChildTaskFocusStateIfNeed();
        }
    }

    @Override // com.oplus.zoom.transition.ZoomTransitionController.IZoomTransitionListener
    public void onTransitionCancel(int i, int i2) {
        if (this.mRootTaskInfo.taskId != i2) {
            return;
        }
        this.mZoomStateManager.cancelRemoteTransition(i);
    }

    @Override // com.oplus.zoom.transition.ZoomTransitionController.IZoomTransitionListener
    public boolean onTransitionStart(int i, int i2, RemoteAnimationTarget[] remoteAnimationTargetArr, ZoomTransitionController.FinishCallback finishCallback) {
        if (this.mRootTaskInfo.taskId != i2) {
            return false;
        }
        return this.mZoomStateManager.startRemoteTransition(i, remoteAnimationTargetArr, finishCallback);
    }

    public void onZoomEnter(OplusZoomTaskInfo oplusZoomTaskInfo, ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
        this.mRootTaskInfo = runningTaskInfo;
        ZoomStateManager zoomStateManager = new ZoomStateManager(this.mContext, this.mSyncQueue, this.mMainExecutor, this.mAnimExecutor, this.mUiManager, this);
        this.mZoomStateManager = zoomStateManager;
        zoomStateManager.onZoomEnter(oplusZoomTaskInfo, runningTaskInfo, surfaceControl);
    }

    public void onZoomExit(ActivityManager.RunningTaskInfo runningTaskInfo) {
        this.mZoomStateManager.onZoomExit(runningTaskInfo);
        this.mZoomStateManager = null;
    }

    public void onZoomTaskChanged(OplusZoomTaskInfo oplusZoomTaskInfo) {
        this.mZoomStateManager.onZoomInfoChanged(oplusZoomTaskInfo);
    }

    public void onZoomTaskEvent(int i) {
        this.mZoomStateManager.onZoomTaskEvent(i);
    }

    public void requestAnimationStart() {
        this.mRootTaskController.requestAnimationStart();
    }

    public void requestChangeZoomState(int i, boolean z, boolean z2) {
        this.mZoomStateManager.requestChangeZoomStateFromOutside(i, z, z2);
    }

    public void requestChangeZoomTask(WindowContainerToken windowContainerToken, int i, boolean z) {
        this.mRootTaskController.requestChangeZoomTask(windowContainerToken, i, z);
    }

    public void setInputToken(WindowContainerToken windowContainerToken, IBinder iBinder) {
        this.mRootTaskController.setInputToken(windowContainerToken, iBinder);
    }

    public boolean startAnimation(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, Transitions.TransitionFinishCallback transitionFinishCallback) {
        return this.mZoomStateManager.startTransition(transaction, surfaceControl, transitionFinishCallback);
    }
}
