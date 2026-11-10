package com.oplus.zoom.ui.floathandle;

import android.content.ComponentName;
import androidx.collection.FloatList$;
import com.oplus.zoom.common.ZoomPositionInfo;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class FloatHandleInfo {
    public static final int FLOAT_HANDLE_ON_LEFT = 0;
    public static final int FLOAT_HANDLE_ON_RIGHT = 1;
    public ComponentName componentName;
    public int currentUser;
    public String packageName;
    public float scaleY;
    public int side = 0;
    public int taskId;
    public int userId;
    public ZoomPositionInfo zoomPositionInfo;
    public int zoomState;

    public void copy(FloatHandleInfo floatHandleInfo) {
        if (floatHandleInfo != null) {
            this.taskId = floatHandleInfo.taskId;
            this.componentName = floatHandleInfo.componentName;
            this.packageName = floatHandleInfo.packageName;
            this.userId = floatHandleInfo.userId;
            this.currentUser = floatHandleInfo.currentUser;
            this.side = floatHandleInfo.side;
            this.scaleY = floatHandleInfo.scaleY;
            this.zoomPositionInfo = floatHandleInfo.zoomPositionInfo;
            this.zoomState = floatHandleInfo.zoomState;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FloatHandleInfo{taskId = ");
        sb.append(this.taskId);
        sb.append(", packageName = ");
        sb.append(this.packageName);
        sb.append(", ComponentName = ");
        sb.append(this.componentName);
        sb.append(", userId =");
        sb.append(this.userId);
        sb.append(", currentUser =");
        sb.append(this.currentUser);
        sb.append(", side = ");
        sb.append(this.side);
        sb.append(", scaleY = ");
        sb.append(this.scaleY);
        sb.append(", zoomPositionInfo = ");
        sb.append(this.zoomPositionInfo);
        sb.append(", zoomState = ");
        return FloatList$.ExternalSyntheticOutline0.m(sb, this.zoomState, '}');
    }
}
