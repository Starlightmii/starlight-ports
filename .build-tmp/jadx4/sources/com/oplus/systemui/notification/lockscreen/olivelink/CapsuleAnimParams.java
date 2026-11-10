package com.oplus.systemui.notification.lockscreen.olivelink;

import android.content.Context;
import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import com.coui.appcompat.animation.COUISpringInterpolator;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Scalar;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.olivelink.SpringType;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleAnimParams {
    public static final CapsuleAnimParams INSTANCE = new CapsuleAnimParams();
    private static final String OLIVE_LINK_SERVER_NAME = "Capsule";
    private static final String TAG = "CapsuleAnimParams";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleDebugParams extends OLiveLinkGroup {
        public static final CapsuleDebugParams INSTANCE;
        private static final Scalar appearWithEar;
        private static final Scalar disappearWithEar;

        static {
            CapsuleDebugParams capsuleDebugParams = new CapsuleDebugParams(CapsuleAnimParams.OLIVE_LINK_SERVER_NAME, "07.其他");
            INSTANCE = capsuleDebugParams;
            disappearWithEar = capsuleDebugParams.safeScalar(Bundler$.ExternalSyntheticOutline0.m(capsuleDebugParams.getGroupName(), ":带小耳朵消失"), 1);
            appearWithEar = capsuleDebugParams.safeScalar(capsuleDebugParams.getGroupName() + ":带小耳朵显示", 1);
        }

        public final Scalar getAppearWithEar() {
            return appearWithEar;
        }

        public final Scalar getDisappearWithEar() {
            return disappearWithEar;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleScrollParams extends OLiveLinkGroup {
        private static final Scalar BLUR_MAX;
        private static final Spring HScrollParam;
        public static final CapsuleScrollParams INSTANCE;
        private static final Scalar SCROLL_UP_CURVE_RATIO;
        private static final Scalar SCROLL_UP_MAX_DISTANCE;
        private static final Spring VScrollBackParam;
        private static final Scalar baseCardMinWidth;
        private static final Scalar baseCardTranXRatio;
        private static final Scalar baseCardTranxMax;
        private static final Scalar hScrollTranXMax;
        private static final Scalar hScrollTranXRatio;
        private static final Scalar nextCardExpandRadio;

        static {
            CapsuleScrollParams capsuleScrollParams = new CapsuleScrollParams(CapsuleAnimParams.OLIVE_LINK_SERVER_NAME, "06.胶囊容器滑动参数");
            INSTANCE = capsuleScrollParams;
            HScrollParam = OLiveLinkGroup.safeSpring$default(capsuleScrollParams, "胶囊左右滑放手动画", 0.4f, 0.3f);
            VScrollBackParam = OLiveLinkGroup.safeSpring$default(capsuleScrollParams, "胶囊上滑放手动画", 0.45f, 0.35f);
            Float fValueOf = Float.valueOf(0.21f);
            SCROLL_UP_CURVE_RATIO = capsuleScrollParams.safeScalar("上滑跟手橡皮筋系数", fValueOf);
            SCROLL_UP_MAX_DISTANCE = capsuleScrollParams.safeScalar("上滑跟手最大距离", Float.valueOf(400.0f));
            BLUR_MAX = capsuleScrollParams.safeScalar("滑动模糊最大值", Float.valueOf(30.0f));
            baseCardMinWidth = capsuleScrollParams.safeScalar("左右滑焦点卡最小宽度", Float.valueOf(40.0f));
            baseCardTranXRatio = capsuleScrollParams.safeScalar("左右滑焦点卡内缩橡皮筋系数", fValueOf);
            baseCardTranxMax = capsuleScrollParams.safeScalar("左右滑焦点卡内缩最大距离", Float.valueOf(15.0f));
            nextCardExpandRadio = capsuleScrollParams.safeScalar("左右滑Next卡放大橡皮筋系数", Float.valueOf(0.4f));
            hScrollTranXRatio = capsuleScrollParams.safeScalar("左右滑挤压橡皮筋系数", Float.valueOf(0.56f));
            hScrollTranXMax = capsuleScrollParams.safeScalar("左右滑挤压最大距离", Float.valueOf(20.0f));
        }

        public final Scalar getBLUR_MAX() {
            return BLUR_MAX;
        }

        public final Scalar getBaseCardMinWidth() {
            return baseCardMinWidth;
        }

        public final Scalar getBaseCardTranXRatio() {
            return baseCardTranXRatio;
        }

        public final Scalar getBaseCardTranxMax() {
            return baseCardTranxMax;
        }

        public final Spring getHScrollParam() {
            return HScrollParam;
        }

        public final Scalar getHScrollTranXMax() {
            return hScrollTranXMax;
        }

        public final Scalar getHScrollTranXRatio() {
            return hScrollTranXRatio;
        }

        public final Scalar getNextCardExpandRadio() {
            return nextCardExpandRadio;
        }

        public final Scalar getSCROLL_UP_CURVE_RATIO() {
            return SCROLL_UP_CURVE_RATIO;
        }

        public final Scalar getSCROLL_UP_MAX_DISTANCE() {
            return SCROLL_UP_MAX_DISTANCE;
        }

        public final Spring getVScrollBackParam() {
            return VScrollBackParam;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class OLiveLinkGroup {
        private final String groupName;
        private final String serverName;

        public OLiveLinkGroup(String str, String str2) {
            this.serverName = str;
            this.groupName = str2;
        }

        public static /* synthetic */ Spring safeSpring$default(CapsuleScrollParams capsuleScrollParams, String str, float f, float f2) {
            return capsuleScrollParams.safeSpring(str, f, f2, 0.0f, 0.0f, 0.0f, SpringType.SPRING_ANIMATION);
        }

        public final String getGroupName() {
            return this.groupName;
        }

        public final String getServerName() {
            return this.serverName;
        }

        public final <T extends Number> Scalar safeScalar(String str, T t) {
            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, this.serverName, str, this.groupName, t, 16);
        }

        public final Spring safeSpring(String str, float f, float f2, float f3, float f4, float f5, SpringType springType) {
            return OliveLinkConnector.safeSpring$default(OliveLinkConnector.INSTANCE, this.serverName, str, f, f2, f3, f4, f5, springType, this.groupName, null, OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION);
        }
    }

    public final void initOliveLink(Context context) {
        OliveLinkConnector.INSTANCE.start(context, OLIVE_LINK_SERVER_NAME);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleAnimParams-->".concat(String.valueOf(CapsuleScrollParams.INSTANCE)));
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleAnimParams-->".concat(String.valueOf(CapsuleDebugParams.INSTANCE)));
        }
    }

    public final void loadOLiveLinkParam(COUISpringAnimation cOUISpringAnimation, Spring spring) {
        if (cOUISpringAnimation.getSpring() == null) {
            cOUISpringAnimation.setSpring(new COUISpringForce());
        }
        cOUISpringAnimation.getSpring().setResponse(spring.response()).setBounce(spring.bounce());
    }

    public final COUISpringInterpolator toSpringInterpolation(Spring spring) {
        return new COUISpringInterpolator(spring.response(), spring.bounce());
    }
}
