package com.oplus.systemui.notification.lockscreen.capsule.event;

import android.content.Context;
import android.util.Log;
import com.oplus.systemui.notification.lockscreen.olivelink.OLiveLinkBaseParams;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Scalar;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleSpotLightParams {
    public static final CapsuleSpotLightParams INSTANCE = new CapsuleSpotLightParams();
    private static final String OLIVE_LINK_SERVER_NAME = "胶囊光效";
    private static final String TAG = "CapsuleSpotLightParams";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DragScaleParams extends OLiveLinkBaseParams.OLiveLinkGroup {
        private static final Scalar DEAD_ZONE_X;
        private static final Scalar DEAD_ZONE_Y;
        public static final DragScaleParams INSTANCE;
        private static final Scalar MAX_DRAG_DISTANCE;
        private static final Scalar MAX_RUBBER_DISTANCE;
        private static final Scalar MOVE_STRENGTH;
        private static final Scalar TRANS_X_CURVE_RATIO;
        private static final Scalar TRANS_X_MAX;
        private static final Scalar TYPE;

        static {
            DragScaleParams dragScaleParams = new DragScaleParams(11, "形变参数");
            INSTANCE = dragScaleParams;
            TYPE = dragScaleParams.safeScalar(CapsuleSpotLightParams.OLIVE_LINK_SERVER_NAME, "类型", 1);
            Float fValueOf = Float.valueOf(8.0f);
            DEAD_ZONE_X = dragScaleParams.safeScalar(CapsuleSpotLightParams.OLIVE_LINK_SERVER_NAME, "X方向死区", fValueOf);
            DEAD_ZONE_Y = dragScaleParams.safeScalar(CapsuleSpotLightParams.OLIVE_LINK_SERVER_NAME, "Y方向死区", fValueOf);
            MOVE_STRENGTH = dragScaleParams.safeScalar(CapsuleSpotLightParams.OLIVE_LINK_SERVER_NAME, "移动强度", Float.valueOf(1.0f));
            Float fValueOf2 = Float.valueOf(2000.0f);
            MAX_DRAG_DISTANCE = dragScaleParams.safeScalar(CapsuleSpotLightParams.OLIVE_LINK_SERVER_NAME, "最大拖动距离", fValueOf2);
            MAX_RUBBER_DISTANCE = dragScaleParams.safeScalar(CapsuleSpotLightParams.OLIVE_LINK_SERVER_NAME, "最大弹性距离", fValueOf2);
            TRANS_X_MAX = dragScaleParams.safeScalar(CapsuleSpotLightParams.OLIVE_LINK_SERVER_NAME, "X轴最大值", fValueOf2);
            TRANS_X_CURVE_RATIO = dragScaleParams.safeScalar(CapsuleSpotLightParams.OLIVE_LINK_SERVER_NAME, "X轴曲线比例", Float.valueOf(0.5f));
        }

        public final Scalar getDEAD_ZONE_X() {
            return DEAD_ZONE_X;
        }

        public final Scalar getDEAD_ZONE_Y() {
            return DEAD_ZONE_Y;
        }

        public final Scalar getMAX_DRAG_DISTANCE() {
            return MAX_DRAG_DISTANCE;
        }

        public final Scalar getMAX_RUBBER_DISTANCE() {
            return MAX_RUBBER_DISTANCE;
        }

        public final Scalar getMOVE_STRENGTH() {
            return MOVE_STRENGTH;
        }

        public final Scalar getTRANS_X_CURVE_RATIO() {
            return TRANS_X_CURVE_RATIO;
        }

        public final Scalar getTRANS_X_MAX() {
            return TRANS_X_MAX;
        }

        public final Scalar getTYPE() {
            return TYPE;
        }
    }

    public final void initOliveLink(Context context) {
        OliveLinkConnector.INSTANCE.start(context, OLIVE_LINK_SERVER_NAME);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleSpotLightParams-->".concat(String.valueOf(DragScaleParams.INSTANCE)));
        }
    }
}
