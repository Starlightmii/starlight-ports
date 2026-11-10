package com.oplus.zoom;

import com.android.wm.shell.draganddrop.DragAndDropPolicyExt;
import com.oplus.zoom.common.ReflectionHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusZoomFeature {
    private static volatile OplusZoomFeature sInstance;
    private DragAndDropPolicyExt mDragAndDropPolicyExt;
    private ZoomController mZoomController;

    private OplusZoomFeature() {
    }

    public static OplusZoomFeature getInstance() {
        if (sInstance == null) {
            synchronized (OplusZoomFeature.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new OplusZoomFeature();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public boolean hasShellZoomFeature() {
        return ReflectionHelper.OplusZoomTaskManager_supportZoomTaskController();
    }

    public void injectDragAndDropControllerEx(DragAndDropPolicyExt dragAndDropPolicyExt) {
        this.mDragAndDropPolicyExt = dragAndDropPolicyExt;
    }

    public void injectZoomController(ZoomController zoomController) {
        this.mZoomController = zoomController;
    }

    public void release() {
        DragAndDropPolicyExt dragAndDropPolicyExt = this.mDragAndDropPolicyExt;
        if (dragAndDropPolicyExt != null) {
            dragAndDropPolicyExt.releaseSurface();
        }
    }
}
