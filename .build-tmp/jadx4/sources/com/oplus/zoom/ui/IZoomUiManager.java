package com.oplus.zoom.ui;

import android.app.ActivityManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import com.android.wm.shell.shared.annotations.ExternalThread;
import com.oplus.zoom.common.ZoomPositionInfo;
import com.oplus.zoom.ui.floathandle.FloatHandleInfo;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
@ExternalThread
public interface IZoomUiManager {
    void addFloatHandle(FloatHandleInfo floatHandleInfo);

    void dismissSnackBar();

    FloatHandleInfo getFloatHandleInfo(int i);

    Rect getFloatHandleRegion(Rect rect, float f);

    void hideAllTipsView();

    void onConfigChanged(Configuration configuration);

    void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo);

    void removeFloatHandle(int i);

    void showCompatibilityToast(int i, int i2, String str, String str2, Bundle bundle);

    void showTipsOnZoomWindowShow(ZoomPositionInfo zoomPositionInfo, boolean z);

    void showTipsOnZoomWindowShow(ZoomPositionInfo zoomPositionInfo, boolean z, boolean z2);
}
