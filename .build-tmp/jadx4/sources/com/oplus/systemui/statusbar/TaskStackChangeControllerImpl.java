package com.oplus.systemui.statusbar;

import com.android.systemui.plugins.statusbar.TaskStackChangeController;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TaskStackChangeControllerImpl implements TaskStackChangeController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "TaskStackChangeControllerImpl";
    private final List<TaskStackChangeController.ITaskChangedListener> listeners = new ArrayList();
    private final TaskStackChangeControllerImpl$taskStackChangeListener$1 taskStackChangeListener;
    private final TaskStackChangeListeners taskStackChangeListeners;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public TaskStackChangeControllerImpl(TaskStackChangeListeners taskStackChangeListeners) {
        this.taskStackChangeListeners = taskStackChangeListeners;
        TaskStackChangeControllerImpl$taskStackChangeListener$1 taskStackChangeControllerImpl$taskStackChangeListener$1 = new TaskStackChangeControllerImpl$taskStackChangeListener$1();
        taskStackChangeControllerImpl$taskStackChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.taskStackChangeListener = taskStackChangeControllerImpl$taskStackChangeListener$1;
        taskStackChangeListeners.registerTaskStackListener(taskStackChangeControllerImpl$taskStackChangeListener$1);
    }

    public void addListener(TaskStackChangeController.ITaskChangedListener iTaskChangedListener) {
        if (this.listeners.contains(iTaskChangedListener)) {
            return;
        }
        this.listeners.add(iTaskChangedListener);
    }

    public void removeListener(TaskStackChangeController.ITaskChangedListener iTaskChangedListener) {
        this.listeners.remove(iTaskChangedListener);
    }
}
