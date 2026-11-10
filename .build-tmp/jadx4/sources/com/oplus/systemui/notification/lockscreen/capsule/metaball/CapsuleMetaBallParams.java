package com.oplus.systemui.notification.lockscreen.capsule.metaball;

import android.content.Context;
import android.view.animation.PathInterpolator;
import com.oplusos.systemui.common.olivelink.CubicBezier;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Scalar;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleMetaBallParams {
    public static final CapsuleMetaBallParams INSTANCE = new CapsuleMetaBallParams();
    private static final String OLIVE_LINK_SERVER_NAME = "Capsule_MetaBall";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SwipeParam {
        private static final String GROUP_NAME = "滑动相关参数";
        public static final SwipeParam INSTANCE = new SwipeParam();
        private static final CubicBezier capsuleContentAlpha;
        private static final PathInterpolator capsuleContentAlphaInterpolation;
        private static final Lazy capsuleGradientLineDistance$delegate;
        private static final Lazy capsuleMaxMargin$delegate;
        private static final Lazy capsuleOverscrollDistance$delegate;
        private static final Lazy capsuleOverscrollDistanceY$delegate;
        private static final Lazy capsuleStartHeight$delegate;

        static {
            final int i = 0;
            capsuleMaxMargin$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleMetaBallParams$SwipeParam$$ExternalSyntheticLambda0
                public final Object invoke() {
                    switch (i) {
                        case 0:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊之间最大间隔", "滑动相关参数", Float.valueOf(0.0f), 16);
                        case 1:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动时的起始高度", "滑动相关参数", Float.valueOf(110.0f), 16);
                        case 2:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动时描边与溶球效果的响应距离", "滑动相关参数", Float.valueOf(60.0f), 16);
                        case 3:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动超出边界的宽度占宽度的%", "滑动相关参数", Float.valueOf(0.08f), 16);
                        default:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动超出边界的高度占高度的%", "滑动相关参数", Float.valueOf(0.2f), 16);
                    }
                }
            });
            final int i2 = 1;
            capsuleStartHeight$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleMetaBallParams$SwipeParam$$ExternalSyntheticLambda0
                public final Object invoke() {
                    switch (i2) {
                        case 0:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊之间最大间隔", "滑动相关参数", Float.valueOf(0.0f), 16);
                        case 1:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动时的起始高度", "滑动相关参数", Float.valueOf(110.0f), 16);
                        case 2:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动时描边与溶球效果的响应距离", "滑动相关参数", Float.valueOf(60.0f), 16);
                        case 3:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动超出边界的宽度占宽度的%", "滑动相关参数", Float.valueOf(0.08f), 16);
                        default:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动超出边界的高度占高度的%", "滑动相关参数", Float.valueOf(0.2f), 16);
                    }
                }
            });
            final int i3 = 2;
            capsuleGradientLineDistance$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleMetaBallParams$SwipeParam$$ExternalSyntheticLambda0
                public final Object invoke() {
                    switch (i3) {
                        case 0:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊之间最大间隔", "滑动相关参数", Float.valueOf(0.0f), 16);
                        case 1:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动时的起始高度", "滑动相关参数", Float.valueOf(110.0f), 16);
                        case 2:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动时描边与溶球效果的响应距离", "滑动相关参数", Float.valueOf(60.0f), 16);
                        case 3:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动超出边界的宽度占宽度的%", "滑动相关参数", Float.valueOf(0.08f), 16);
                        default:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动超出边界的高度占高度的%", "滑动相关参数", Float.valueOf(0.2f), 16);
                    }
                }
            });
            final int i4 = 3;
            capsuleOverscrollDistance$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleMetaBallParams$SwipeParam$$ExternalSyntheticLambda0
                public final Object invoke() {
                    switch (i4) {
                        case 0:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊之间最大间隔", "滑动相关参数", Float.valueOf(0.0f), 16);
                        case 1:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动时的起始高度", "滑动相关参数", Float.valueOf(110.0f), 16);
                        case 2:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动时描边与溶球效果的响应距离", "滑动相关参数", Float.valueOf(60.0f), 16);
                        case 3:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动超出边界的宽度占宽度的%", "滑动相关参数", Float.valueOf(0.08f), 16);
                        default:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动超出边界的高度占高度的%", "滑动相关参数", Float.valueOf(0.2f), 16);
                    }
                }
            });
            final int i5 = 4;
            capsuleOverscrollDistanceY$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleMetaBallParams$SwipeParam$$ExternalSyntheticLambda0
                public final Object invoke() {
                    switch (i5) {
                        case 0:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊之间最大间隔", "滑动相关参数", Float.valueOf(0.0f), 16);
                        case 1:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动时的起始高度", "滑动相关参数", Float.valueOf(110.0f), 16);
                        case 2:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动时描边与溶球效果的响应距离", "滑动相关参数", Float.valueOf(60.0f), 16);
                        case 3:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动超出边界的宽度占宽度的%", "滑动相关参数", Float.valueOf(0.08f), 16);
                        default:
                            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, "Capsule_MetaBall", "胶囊滑动超出边界的高度占高度的%", "滑动相关参数", Float.valueOf(0.2f), 16);
                    }
                }
            });
            CubicBezier cubicBezierSafeCubicBezier = OliveLinkConnector.INSTANCE.safeCubicBezier(CapsuleMetaBallParams.OLIVE_LINK_SERVER_NAME, "胶囊滑动是内容alpha变化曲线", 0.77f, 0.0f, 0.175f, 1.0f, GROUP_NAME, "");
            capsuleContentAlpha = cubicBezierSafeCubicBezier;
            capsuleContentAlphaInterpolation = new PathInterpolator(cubicBezierSafeCubicBezier.x1(), cubicBezierSafeCubicBezier.y1(), cubicBezierSafeCubicBezier.x2(), cubicBezierSafeCubicBezier.y2());
        }

        public final CubicBezier getCapsuleContentAlpha() {
            return capsuleContentAlpha;
        }

        public final PathInterpolator getCapsuleContentAlphaInterpolation() {
            return capsuleContentAlphaInterpolation;
        }

        public final Scalar getCapsuleGradientLineDistance() {
            return (Scalar) capsuleGradientLineDistance$delegate.getValue();
        }

        public final Scalar getCapsuleMaxMargin() {
            return (Scalar) capsuleMaxMargin$delegate.getValue();
        }

        public final Scalar getCapsuleOverscrollDistance() {
            return (Scalar) capsuleOverscrollDistance$delegate.getValue();
        }

        public final Scalar getCapsuleOverscrollDistanceY() {
            return (Scalar) capsuleOverscrollDistanceY$delegate.getValue();
        }

        public final Scalar getCapsuleStartHeight() {
            return (Scalar) capsuleStartHeight$delegate.getValue();
        }
    }

    public final void initOliveLink(Context context) {
        OliveLinkConnector.INSTANCE.start(context, OLIVE_LINK_SERVER_NAME);
    }
}
