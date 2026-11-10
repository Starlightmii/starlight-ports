package com.oplus.zoom.transition;

import android.app.ActivityManager;
import android.os.IBinder;
import android.util.SparseArray;
import android.view.IRemoteAnimationFinishedCallback;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import com.android.systemui.animation.DefaultTransitionHelper$;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.transition.Transitions;
import com.oplus.zoom.ZoomController;
import com.oplus.zoom.ZoomRootTaskController;
import com.oplus.zoom.ZoomRootTaskManager;
import com.oplus.zoom.common.LogD;
import java.lang.invoke.VarHandle;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ZoomTransitionController implements Transitions.TransitionHandler {
    private static final String TAG = "ZoomAnimation";
    private ShellExecutor mAnimExecutor;
    private final Transitions mTransition;
    private ZoomController mZoomController;
    private ZoomRootTaskController mZoomRootTaskController;
    private final SparseArray<IZoomTransitionListener> mTransitionListener = new SparseArray<>();
    private final SparseArray<RemoteTransition> mRunningTransition = new SparseArray<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface IZoomTransitionListener {
        void onTransitionCancel(int i, int i2);

        boolean onTransitionStart(int i, int i2, RemoteAnimationTarget[] remoteAnimationTargetArr, FinishCallback finishCallback);
    }

    public ZoomTransitionController(ZoomController zoomController, Transitions transitions, ShellExecutor shellExecutor, ZoomRootTaskController zoomRootTaskController) {
        this.mZoomController = zoomController;
        this.mAnimExecutor = shellExecutor;
        this.mTransition = transitions;
        transitions.addHandler(this);
        this.mZoomRootTaskController = zoomRootTaskController;
    }

    private RemoteAnimationTarget[] collectTargetInSameParent(RemoteAnimationTarget[] remoteAnimationTargetArr, int i) {
        ActivityManager.RunningTaskInfo runningTaskInfo;
        RemoteAnimationTarget[] remoteAnimationTargetArr2 = new RemoteAnimationTarget[remoteAnimationTargetArr.length];
        int i2 = 0;
        for (RemoteAnimationTarget remoteAnimationTarget : remoteAnimationTargetArr) {
            if (remoteAnimationTarget != null && (runningTaskInfo = remoteAnimationTarget.taskInfo) != null && runningTaskInfo.parentTaskId == i) {
                remoteAnimationTargetArr2[i2] = remoteAnimationTarget;
                i2++;
            }
        }
        return remoteAnimationTargetArr2;
    }

    private boolean isZoomTransitionChange(TransitionInfo.Change change) {
        if (change.getTaskInfo() != null && change.getTaskInfo().getWindowingMode() == 100) {
            return change.getContainer() != null;
        }
        LogD.w("ZoomAnimation", "isZoomTransitionChange change = " + change);
        return false;
    }

    private boolean isZoomTransitionRequest(TransitionInfo transitionInfo) {
        if ((transitionInfo.getFlags() & 47360) == 0 && transitionInfo.getType() != 5) {
            return true;
        }
        LogD.d("ZoomAnimation", "info.getFlags =" + transitionInfo.getFlags() + " info.getType = " + transitionInfo.getType());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startAnimation$0(int i, boolean[] zArr, SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, Transitions.TransitionFinishCallback transitionFinishCallback, ZoomRootTaskManager zoomRootTaskManager) {
        LogD.w("ZoomAnimation", "ZoomTransitionController startAnimation taskId = " + i);
        zArr[0] = zoomRootTaskManager.startAnimation(transaction, surfaceControl, transitionFinishCallback);
    }

    public WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        if (transitionRequestInfo.getTriggerTask() == null || transitionRequestInfo.getTriggerTask().getWindowingMode() != 100) {
            transitionRequestInfo.getTriggerTask();
            return null;
        }
        if (transitionRequestInfo.getType() == 3 || transitionRequestInfo.getType() == 101 || transitionRequestInfo.getType() == 1) {
            return new WindowContainerTransaction();
        }
        return null;
    }

    /* JADX INFO: renamed from: onRemoteAnimationFinish, reason: merged with bridge method [inline-methods] */
    public void lambda$onRemoteTransitionStart$1(int i, int i2) {
        LogD.d("ZoomAnimation", "onRemoteTransitionEnd");
        RemoteTransition remoteTransition = this.mRunningTransition.get(i);
        if (remoteTransition != null) {
            remoteTransition.removeHandler(Integer.valueOf(i2));
            if (remoteTransition.allDone()) {
                this.mRunningTransition.remove(i);
                this.mZoomController.onTransitionEnd(i);
            }
        }
    }

    public void onRemoteTransitionCancel(int i, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
        LogD.d("ZoomAnimation", "onRemoteTransitionCancel");
        RemoteTransition remoteTransition = this.mRunningTransition.get(i);
        if (remoteTransition != null) {
            Iterator it = remoteTransition.getHandlers().iterator();
            while (it.hasNext()) {
                int iIntValue = ((Integer) it.next()).intValue();
                IZoomTransitionListener iZoomTransitionListener = this.mTransitionListener.get(iIntValue);
                if (iZoomTransitionListener != null) {
                    iZoomTransitionListener.onTransitionCancel(i, iIntValue);
                }
            }
        }
        this.mRunningTransition.remove(i);
    }

    public void onRemoteTransitionStart(int i, RemoteAnimationTarget[] remoteAnimationTargetArr) {
        ActivityManager.RunningTaskInfo runningTaskInfo;
        LogD.d("ZoomAnimation", "onRemoteTransitionStart");
        RemoteTransition remoteTransition = new RemoteTransition(this, i);
        for (RemoteAnimationTarget remoteAnimationTarget : remoteAnimationTargetArr) {
            if (remoteAnimationTarget != null && (runningTaskInfo = remoteAnimationTarget.taskInfo) != null && !remoteTransition.hasHandler(Integer.valueOf(runningTaskInfo.parentTaskId))) {
                int i2 = remoteAnimationTarget.taskInfo.parentTaskId;
                RemoteAnimationTarget[] remoteAnimationTargetArrCollectTargetInSameParent = collectTargetInSameParent(remoteAnimationTargetArr, i2);
                for (int i3 = 0; i3 < this.mTransitionListener.size(); i3++) {
                    IZoomTransitionListener iZoomTransitionListenerValueAt = this.mTransitionListener.valueAt(i3);
                    LogD.d("ZoomAnimation", "target parentTaskId: " + i2);
                    if (iZoomTransitionListenerValueAt != null) {
                        ZoomTransitionController$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new ZoomTransitionController$.ExternalSyntheticLambda1();
                        externalSyntheticLambda1.f$0 = this;
                        externalSyntheticLambda1.f$1 = i;
                        externalSyntheticLambda1.f$2 = i2;
                        VarHandle.storeStoreFence();
                        if (iZoomTransitionListenerValueAt.onTransitionStart(i, i2, remoteAnimationTargetArrCollectTargetInSameParent, externalSyntheticLambda1)) {
                            remoteTransition.addHandler(Integer.valueOf(i2));
                            break;
                        }
                    }
                }
            }
        }
        if (remoteTransition.allDone()) {
            this.mZoomController.onTransitionEnd(i);
        } else {
            this.mRunningTransition.put(i, remoteTransition);
        }
    }

    public void registerTransitionListener(int i, IZoomTransitionListener iZoomTransitionListener) {
        LogD.d("ZoomAnimation", "registerTransitionListener taskId = " + i);
        this.mTransitionListener.put(i, iZoomTransitionListener);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        ActivityManager.RunningTaskInfo taskInfo;
        if (!isZoomTransitionRequest(transitionInfo)) {
            return false;
        }
        for (int iM = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM >= 0; iM--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(iM);
            if (change.getTaskInfo() != null && change.getTaskInfo().getWindowingMode() == 100) {
                boolean[] zArr = new boolean[1];
                for (int iM2 = DefaultTransitionHelper$.ExternalSyntheticOutline0.m(transitionInfo, 1); iM2 >= 0; iM2--) {
                    TransitionInfo.Change change2 = (TransitionInfo.Change) transitionInfo.getChanges().get(iM2);
                    if (change2.getMode() == 6 && change2.getStartRotation() != change2.getEndRotation()) {
                        return false;
                    }
                    if (isZoomTransitionChange(change2) && (taskInfo = change2.getTaskInfo()) != null) {
                        int i = taskInfo.hasParentTask() ? taskInfo.parentTaskId : 0;
                        if (i != 0) {
                            SurfaceControl leash = change2.getLeash();
                            LogD.w("ZoomAnimation", "ZoomTransitionController startAnimation taskLeash = " + leash);
                            ZoomRootTaskController zoomRootTaskController = this.mZoomRootTaskController;
                            ZoomTransitionController$$ExternalSyntheticLambda0 zoomTransitionController$$ExternalSyntheticLambda0 = new ZoomTransitionController$$ExternalSyntheticLambda0();
                            zoomTransitionController$$ExternalSyntheticLambda0.f$0 = i;
                            zoomTransitionController$$ExternalSyntheticLambda0.f$1 = zArr;
                            zoomTransitionController$$ExternalSyntheticLambda0.f$2 = transaction;
                            zoomTransitionController$$ExternalSyntheticLambda0.f$3 = leash;
                            zoomTransitionController$$ExternalSyntheticLambda0.f$4 = transitionFinishCallback;
                            VarHandle.storeStoreFence();
                            zoomRootTaskController.forSpecifiedRootTaskManager(i, zoomTransitionController$$ExternalSyntheticLambda0);
                        }
                    }
                }
                boolean z = zArr[0];
                if (!z) {
                    LogD.w("ZoomAnimation", " has no anim for this TransitionInfo" + transitionInfo);
                    transaction.apply();
                }
                return z;
            }
        }
        return false;
    }

    public void startZoomTransition(int i, WindowContainerTransaction windowContainerTransaction) {
        this.mTransition.startTransition(i, windowContainerTransaction, this);
    }

    public void unRegisterTransitionListener(int i) {
        LogD.d("ZoomAnimation", "unRegisterTransitionListener taskId = " + i);
        this.mTransitionListener.remove(i);
    }
}
