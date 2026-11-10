package com.oplus.transition;

import android.app.ActivityManager;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Debug;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.WindowContainerToken;
import com.android.systemui.media.controls.domain.resume.MediaResumeListener$getResumeAction$1$;
import com.android.wm.shell.recents.IRecentsAnimationRunner;
import com.android.wm.shell.recents.RecentsTransitionHandler;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.transition.TransitionLog;
import com.android.wm.shell.transition.Transitions;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ShellPreReadyController {
    private static final int DEPTH = 7;
    private static final String IS_IN_PREREADY_RECENTS = "isInPreReadyOfRecents";
    private static final String IS_IN_PREREADY_REMOTE = "getInPreReady";
    private static final String KEY_EXTRA_IN_PRE_READY = "in_pre_ready";
    private static final String RECENTS_PREREADY_ENABLE = "recents_anim_pre_ready_enable";
    public static final int RECENT_ABORT = 5;
    public static final int RECENT_FAIL = 4;
    public static final int RECENT_PENDING = 0;
    public static final int RECENT_PLAYING = 3;
    public static final int RECENT_READY = 2;
    public static final int RECENT_REQUEST = 1;
    private static final String TAG = "ShellPreReady";
    private RecentsTransitionHandler.RecentsController mRecentController;
    private final Transitions.TransitionObserver mTransitionObserver;
    private Transitions mTransitions;
    private final Object mLock = new Object();
    private int mPreReadyRecentsState = 0;
    private ArrayMap<WindowContainerToken, RemoteAnimationTarget> mPreReadyRecentsMap = null;
    private SurfaceControl.Transaction mFinishTransaction = null;
    private IBinder mRecentsTransition = null;

    public ShellPreReadyController(Transitions transitions) {
        Transitions.TransitionObserver transitionObserver = new Transitions.TransitionObserver() { // from class: com.oplus.transition.ShellPreReadyController.1
            public void onTransitionReady(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
                synchronized (ShellPreReadyController.this.mLock) {
                    try {
                        if (iBinder == ShellPreReadyController.this.mRecentsTransition && ShellPreReadyController.this.mPreReadyRecentsState < 3) {
                            TransitionLog.always("ShellPreReady If pre ready is later than really ready, abort pre ready transition: " + iBinder + " ,current state: " + ShellPreReadyController.this.mPreReadyRecentsState);
                            ShellPreReadyController.this.setPreReadyRecentsState(5);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        };
        this.mTransitionObserver = transitionObserver;
        this.mTransitions = transitions;
        transitions.registerObserver(transitionObserver);
    }

    private ArrayList<RemoteAnimationTarget> buildPreStartTarget(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction) {
        int size = transitionInfo.getChanges().size();
        int size2 = transitionInfo.getChanges().size() * 3;
        TransitionUtil.LeafTaskFilter leafTaskFilter = new TransitionUtil.LeafTaskFilter(transitionInfo);
        ArrayList<RemoteAnimationTarget> arrayList = new ArrayList<>();
        ArrayMap<WindowContainerToken, RemoteAnimationTarget> arrayMap = new ArrayMap<>();
        for (int i = 0; i < transitionInfo.getChanges().size(); i++) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
            ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
            RemoteAnimationTarget remoteAnimationTargetNewTarget = null;
            if (TransitionUtil.isWallpaper(change)) {
                transaction.setAlpha(change.getLeash(), 1.0f);
                transaction.show(change.getLeash());
            } else if (leafTaskFilter.test(change)) {
                remoteAnimationTargetNewTarget = TransitionUtil.newTarget(change, size - i, transitionInfo, transaction, (ArrayMap) null);
                TransitionLog.debug("ShellPreReady newtarget : " + remoteAnimationTargetNewTarget + "; change: " + change);
                if (TransitionUtil.isClosingType(change.getMode()) && taskInfo.topActivityType != 2) {
                    int i2 = size2 - i;
                    TransitionLog.debug("ShellPreReady pausing leaf taskId=" + taskInfo.taskId + " at layer " + i2);
                    transaction.setLayer(remoteAnimationTargetNewTarget.leash, i2);
                }
            }
            if (change.getContainer() != null && remoteAnimationTargetNewTarget != null) {
                arrayMap.put(change.getContainer(), remoteAnimationTargetNewTarget);
                arrayList.add(remoteAnimationTargetNewTarget);
            }
        }
        synchronized (this.mLock) {
            try {
                if (this.mPreReadyRecentsState != 5) {
                    this.mPreReadyRecentsMap = arrayMap;
                    return arrayList;
                }
                TransitionLog.debug("ShellPreReadybuildPreStartTarget abort");
                releasePreReadyMap(arrayMap);
                arrayList.clear();
                return arrayList;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void cleanUp() {
        this.mRecentController = null;
        this.mRecentsTransition = null;
        this.mFinishTransaction = null;
        releasePreReadyMap(this.mPreReadyRecentsMap);
        this.mPreReadyRecentsMap = null;
    }

    private static void closeTransactions(SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        if (transaction != null) {
            transaction.close();
        }
        if (transaction2 != null) {
            transaction2.close();
        }
    }

    private void releasePreReadyMap(ArrayMap<WindowContainerToken, RemoteAnimationTarget> arrayMap) {
        if (arrayMap == null) {
            return;
        }
        for (int i = 0; i < arrayMap.size(); i++) {
            SurfaceControl surfaceControl = arrayMap.valueAt(i).leash;
            if (surfaceControl != null && surfaceControl.isValid()) {
                arrayMap.valueAt(i).leash.release();
            }
        }
    }

    private void resetRecentsState() {
        synchronized (this.mLock) {
            setPreReadyRecentsState(0);
        }
        TransitionLog.debug("ShellPreReady resetRecentsState callers: " + Debug.getCallers(7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPreReadyRecentsState(int i) {
        TransitionLog.debug("ShellPreReady setPreReadyRecentsState from " + this.mPreReadyRecentsState + " to " + i);
        this.mPreReadyRecentsState = i;
    }

    private void setRecentController(RecentsTransitionHandler.RecentsController recentsController) {
        this.mRecentController = recentsController;
    }

    private void startPreready(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, RecentsTransitionHandler.RecentsController recentsController, IRecentsAnimationRunner iRecentsAnimationRunner, ArrayList<RemoteAnimationTarget> arrayList) {
        synchronized (this.mLock) {
            try {
                int i = this.mPreReadyRecentsState;
                if (i != 2) {
                    if (i == 5) {
                        cleanUp();
                    } else {
                        setPreReadyRecentsState(0);
                    }
                    closeTransactions(transaction, transaction2);
                    return;
                }
                setPreReadyRecentsState(3);
                Bundle bundleM = MediaResumeListener$getResumeAction$1$.ExternalSyntheticOutline0.m(KEY_EXTRA_IN_PRE_READY, true);
                try {
                    transaction.apply();
                    TransitionLog.debug("ShellPreReady start pre ready recents infoId: " + transitionInfo.getDebugId() + "; apps.size: " + arrayList.size());
                    iRecentsAnimationRunner.onAnimationStart(recentsController, (RemoteAnimationTarget[]) arrayList.toArray(new RemoteAnimationTarget[arrayList.size()]), new RemoteAnimationTarget[0], new Rect(), bundleM, transitionInfo);
                } catch (RemoteException unused) {
                    TransitionLog.debug("ShellPreReady Error starting recents animation");
                    synchronized (this.mLock) {
                        setPreReadyRecentsState(4);
                        recentsController.cancel("preReady onAnimationStart() failed");
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void cancelRecents(RecentsTransitionHandler.RecentsController recentsController) {
        synchronized (this.mLock) {
            if (recentsController != null) {
                try {
                    RecentsTransitionHandler.RecentsController recentsController2 = this.mRecentController;
                    if (recentsController2 != null && recentsController == recentsController2) {
                        SurfaceControl.Transaction transaction = this.mFinishTransaction;
                        if (transaction != null) {
                            transaction.apply();
                            this.mFinishTransaction.close();
                        }
                        cleanUp();
                        setPreReadyRecentsState(0);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void onRecentsCleanup(RecentsTransitionHandler.RecentsController recentsController) {
        synchronized (this.mLock) {
            if (recentsController != null) {
                try {
                    RecentsTransitionHandler.RecentsController recentsController2 = this.mRecentController;
                    if (recentsController2 != null && recentsController == recentsController2) {
                        cleanUp();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            setPreReadyRecentsState(0);
        }
    }

    public void onRecentsControlStart() {
        this.mFinishTransaction = null;
    }

    public void requestRecentsTransition(RecentsTransitionHandler.RecentsController recentsController, Bundle bundle) {
        if (recentsController == null || bundle == null || !bundle.getBoolean(RECENTS_PREREADY_ENABLE)) {
            return;
        }
        synchronized (this.mLock) {
            try {
                if (this.mPreReadyRecentsState != 0) {
                    return;
                }
                setPreReadyRecentsState(1);
                setRecentController(recentsController);
                TransitionLog.always("ShellPreReady enable pre ready recents");
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void requestRecentsTransitionResult(IBinder iBinder) {
        if (iBinder == null) {
            return;
        }
        this.mRecentsTransition = iBinder;
        TransitionLog.debug("ShellPreReady request recents transition: " + iBinder);
    }

    public void startPreReadyRecents(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        if (transitionInfo == null || transaction == null || transaction2 == null) {
            closeTransactions(transaction, transaction2);
            resetRecentsState();
            return;
        }
        synchronized (this.mLock) {
            try {
                int i = this.mPreReadyRecentsState;
                if (i != 1) {
                    if (i == 5) {
                        cleanUp();
                    } else {
                        setPreReadyRecentsState(0);
                    }
                    closeTransactions(transaction, transaction2);
                    return;
                }
                setPreReadyRecentsState(2);
                RecentsTransitionHandler.RecentsController recentController = this.mTransitions.getRecentsTransitionHandler().getRecentController(this.mRecentsTransition);
                TransitionLog.debug("ShellPreReady startPreReadyRecents: getLastController()= " + recentController + "; mRecentController=" + this.mRecentController);
                if (recentController == null) {
                    recentController = this.mRecentController;
                }
                RecentsTransitionHandler.RecentsController recentsController = recentController;
                if (recentsController == null || recentsController.getListener() == null) {
                    TransitionLog.debug("ShellPreReady start pre ready recents fail controller or listener is null");
                    closeTransactions(transaction, transaction2);
                    resetRecentsState();
                    return;
                }
                this.mFinishTransaction = transaction2;
                synchronized (this.mLock) {
                    try {
                        ArrayList<RemoteAnimationTarget> arrayListBuildPreStartTarget = buildPreStartTarget(transitionInfo, transaction);
                        for (int i2 = 0; i2 < transitionInfo.getRootCount(); i2++) {
                            transaction.show(transitionInfo.getRoot(i2).getLeash());
                        }
                        TransitionLog.debug("ShellPreReady startPreReadyRecents: forwarding to controller=" + recentsController);
                        startPreready(transitionInfo, transaction, transaction2, recentsController, recentsController.getListener(), arrayListBuildPreStartTarget);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public RemoteAnimationTarget takePreReadyTarget(WindowContainerToken windowContainerToken) {
        synchronized (this.mLock) {
            if (windowContainerToken != null) {
                try {
                    ArrayMap<WindowContainerToken, RemoteAnimationTarget> arrayMap = this.mPreReadyRecentsMap;
                    if (arrayMap != null && this.mPreReadyRecentsState == 3) {
                        return arrayMap.remove(windowContainerToken);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return null;
        }
    }

    public boolean transitionInPreReadyRecents(TransitionInfo transitionInfo, IBinder iBinder) {
        if (transitionInfo == null || iBinder == null) {
            return false;
        }
        synchronized (this.mLock) {
            try {
                if (iBinder != this.mRecentsTransition) {
                    return false;
                }
                int i = this.mPreReadyRecentsState;
                Boolean bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, IS_IN_PREREADY_RECENTS, null, new Object[0]);
                return bool != null && bool.booleanValue() && i >= 2 && i <= 3;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean transitionInPreReadyRemote(TransitionInfo transitionInfo) {
        Boolean bool;
        return (transitionInfo == null || (bool = (Boolean) OplusTransitionReflectionHelper.methodInvokeOnExt(transitionInfo, IS_IN_PREREADY_REMOTE, null, new Object[0])) == null || !bool.booleanValue()) ? false : true;
    }
}
