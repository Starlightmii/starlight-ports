package com.oplus.systemui.notification.row;

import android.content.Context;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Scalar;
import com.oplusos.systemui.common.olivelink.Spring;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowParams {
    public static final NotificationMenuRowParams INSTANCE = new NotificationMenuRowParams();
    private static final String OLIVE_LINK_SERVER_NAME = "Capsule_MetaBall";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnimateParamFour {
        public static final AnimateParamFour INSTANCE = new AnimateParamFour();
        private static final String GROUP_NAME = "融球参数";
        private static final Scalar BlendRange = OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, NotificationMenuRowParams.OLIVE_LINK_SERVER_NAME, "融球参数BlendRange", GROUP_NAME, Float.valueOf(3.5f), 16);

        public final Scalar getBlendRange() {
            return BlendRange;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnimateParamOne {
        private static final Scalar DeleteDistance;
        private static final Scalar FADE_END;
        private static final Scalar FADE_START;
        private static final String GROUP_NAME = "一段动效参数";
        private static final Scalar MinimumVisibleChange_ONE;
        private static final Scalar SettingDistance;
        public static final AnimateParamOne INSTANCE = new AnimateParamOne();
        private static final Lazy oneAnimate$delegate = LazyKt__LazyJVMKt.lazy(new NotificationMenuRowParams$AnimateParamOne$$ExternalSyntheticLambda0(0));

        static {
            OliveLinkConnector oliveLinkConnector = OliveLinkConnector.INSTANCE;
            FADE_START = OliveLinkConnector.safeScalar$default(oliveLinkConnector, NotificationMenuRowParams.OLIVE_LINK_SERVER_NAME, "一段动效参数_渐变开始", GROUP_NAME, Float.valueOf(0.6f), 16);
            FADE_END = OliveLinkConnector.safeScalar$default(oliveLinkConnector, NotificationMenuRowParams.OLIVE_LINK_SERVER_NAME, "一段动效参数_渐变结束", GROUP_NAME, Float.valueOf(1.0f), 16);
            MinimumVisibleChange_ONE = OliveLinkConnector.safeScalar$default(oliveLinkConnector, NotificationMenuRowParams.OLIVE_LINK_SERVER_NAME, "MinimumVisibleChange-一段动效", GROUP_NAME, Float.valueOf(0.001f), 16);
            Float fValueOf = Float.valueOf(8.0f);
            SettingDistance = OliveLinkConnector.safeScalar$default(oliveLinkConnector, NotificationMenuRowParams.OLIVE_LINK_SERVER_NAME, "Setting按钮跟随位移距离（dp）", GROUP_NAME, fValueOf, 16);
            DeleteDistance = OliveLinkConnector.safeScalar$default(oliveLinkConnector, NotificationMenuRowParams.OLIVE_LINK_SERVER_NAME, "Delete按钮跟随位移距离（dp）", GROUP_NAME, fValueOf, 16);
        }

        public final Scalar getDeleteDistance() {
            return DeleteDistance;
        }

        public final Scalar getFADE_END() {
            return FADE_END;
        }

        public final Scalar getFADE_START() {
            return FADE_START;
        }

        public final Scalar getMinimumVisibleChange_ONE() {
            return MinimumVisibleChange_ONE;
        }

        public final Spring getOneAnimate() {
            return (Spring) oneAnimate$delegate.getValue();
        }

        public final Scalar getSettingDistance() {
            return SettingDistance;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnimateParamThree {
        private static final Scalar DISMISS_NOTIFICATION_BOUNCE;
        private static final Scalar DISMISS_NOTIFICATION_RESPONSE;
        private static final String GROUP_NAME = "完全删除动效参数";
        private static final Scalar LOTTIE_DURATION;
        private static final Scalar MinimumVisibleChange_DISMISS;
        public static final AnimateParamThree INSTANCE = new AnimateParamThree();
        private static final Lazy blurChange$delegate = LazyKt__LazyJVMKt.lazy(new NotificationMenuRowParams$AnimateParamOne$$ExternalSyntheticLambda0(1));
        private static final Lazy alphaChange$delegate = LazyKt__LazyJVMKt.lazy(new NotificationMenuRowParams$AnimateParamOne$$ExternalSyntheticLambda0(2));

        static {
            OliveLinkConnector oliveLinkConnector = OliveLinkConnector.INSTANCE;
            LOTTIE_DURATION = OliveLinkConnector.safeScalar$default(oliveLinkConnector, NotificationMenuRowParams.OLIVE_LINK_SERVER_NAME, "垃圾桶动效参数_lottie动画时长", GROUP_NAME, 200L, 16);
            DISMISS_NOTIFICATION_RESPONSE = OliveLinkConnector.safeScalar$default(oliveLinkConnector, NotificationMenuRowParams.OLIVE_LINK_SERVER_NAME, "补位通知_位移spring曲线-response", GROUP_NAME, Double.valueOf(0.8d), 16);
            DISMISS_NOTIFICATION_BOUNCE = OliveLinkConnector.safeScalar$default(oliveLinkConnector, NotificationMenuRowParams.OLIVE_LINK_SERVER_NAME, "补位通知_位移spring曲线-bounce", GROUP_NAME, Double.valueOf(0.3d), 16);
            MinimumVisibleChange_DISMISS = OliveLinkConnector.safeScalar$default(oliveLinkConnector, NotificationMenuRowParams.OLIVE_LINK_SERVER_NAME, "MinimumVisibleChange-完全删除动效", GROUP_NAME, Float.valueOf(1.0f), 16);
        }

        public final Spring getAlphaChange() {
            return (Spring) alphaChange$delegate.getValue();
        }

        public final Spring getBlurChange() {
            return (Spring) blurChange$delegate.getValue();
        }

        public final Scalar getDISMISS_NOTIFICATION_BOUNCE() {
            return DISMISS_NOTIFICATION_BOUNCE;
        }

        public final Scalar getDISMISS_NOTIFICATION_RESPONSE() {
            return DISMISS_NOTIFICATION_RESPONSE;
        }

        public final Scalar getLOTTIE_DURATION() {
            return LOTTIE_DURATION;
        }

        public final Scalar getMinimumVisibleChange_DISMISS() {
            return MinimumVisibleChange_DISMISS;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnimateParamTwo {
        private static final String GROUP_NAME = "二段动效参数";
        private static final Scalar MinimumVisibleChange_TWO;
        private static final Scalar TWO_ANIMATE_THRESHOLD;
        public static final AnimateParamTwo INSTANCE = new AnimateParamTwo();
        private static final Lazy twoAnimatedelete$delegate = LazyKt__LazyJVMKt.lazy(new NotificationMenuRowParams$AnimateParamOne$$ExternalSyntheticLambda0(3));
        private static final Lazy twoAnimateSetting$delegate = LazyKt__LazyJVMKt.lazy(new NotificationMenuRowParams$AnimateParamOne$$ExternalSyntheticLambda0(4));
        private static final Lazy twoAnimateBackdelete$delegate = LazyKt__LazyJVMKt.lazy(new NotificationMenuRowParams$AnimateParamOne$$ExternalSyntheticLambda0(5));
        private static final Lazy twoAnimateBackSetting$delegate = LazyKt__LazyJVMKt.lazy(new NotificationMenuRowParams$AnimateParamOne$$ExternalSyntheticLambda0(6));

        static {
            OliveLinkConnector oliveLinkConnector = OliveLinkConnector.INSTANCE;
            TWO_ANIMATE_THRESHOLD = OliveLinkConnector.safeScalar$default(oliveLinkConnector, NotificationMenuRowParams.OLIVE_LINK_SERVER_NAME, "二合一动效位移阈值(0-158.4px)(40dp)", GROUP_NAME, Float.valueOf(40.0f), 16);
            MinimumVisibleChange_TWO = OliveLinkConnector.safeScalar$default(oliveLinkConnector, NotificationMenuRowParams.OLIVE_LINK_SERVER_NAME, "MinimumVisibleChange-二段动效", GROUP_NAME, Float.valueOf(1.0f), 16);
        }

        public final Scalar getMinimumVisibleChange_TWO() {
            return MinimumVisibleChange_TWO;
        }

        public final Scalar getTWO_ANIMATE_THRESHOLD() {
            return TWO_ANIMATE_THRESHOLD;
        }

        public final Spring getTwoAnimateBackSetting() {
            return (Spring) twoAnimateBackSetting$delegate.getValue();
        }

        public final Spring getTwoAnimateBackdelete() {
            return (Spring) twoAnimateBackdelete$delegate.getValue();
        }

        public final Spring getTwoAnimateSetting() {
            return (Spring) twoAnimateSetting$delegate.getValue();
        }

        public final Spring getTwoAnimatedelete() {
            return (Spring) twoAnimatedelete$delegate.getValue();
        }
    }

    public final void initOliveLink(Context context) {
        OliveLinkConnector.INSTANCE.start(context, OLIVE_LINK_SERVER_NAME);
    }

    public final void loadOLiveLinkParam(COUISpringAnimation cOUISpringAnimation, Spring spring) {
        if (cOUISpringAnimation.getSpring() == null) {
            cOUISpringAnimation.setSpring(new COUISpringForce());
        }
        cOUISpringAnimation.getSpring().setResponse(spring.response()).setBounce(spring.bounce());
    }
}
