package com.oplusos.systemui.navigationbar;

import android.util.Log;
import android.util.SparseArray;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EdgeBackGestureHandlerController {
    public static final EdgeBackGestureHandlerController INSTANCE = new EdgeBackGestureHandlerController();
    private static SparseArray<EdgeBackGestureHandler> mEdgeBackController = new SparseArray<>();

    public static final EdgeBackGestureHandler getEdgeBackController(int i) {
        Log.i("EdgeBackGestureHandlerController", "getEdgeBackController, mConfigurations: " + mEdgeBackController + ", displayId: " + i);
        if (mEdgeBackController.contains(i)) {
            return mEdgeBackController.get(i);
        }
        return null;
    }

    public static final SparseArray<EdgeBackGestureHandler> getEdgeBackHandlerArrs() {
        return mEdgeBackController;
    }

    public static final void putEdgeBackController(int i, EdgeBackGestureHandler edgeBackGestureHandler) {
        if (!mEdgeBackController.contains(i)) {
            mEdgeBackController.put(i, edgeBackGestureHandler);
        }
        Log.i("EdgeBackGestureHandlerController", "putEdgeBackController, mConfigurations: " + mEdgeBackController + ", displayId: " + i);
    }
}
