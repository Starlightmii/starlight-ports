package com.oplus.systemui.notification.lockscreen.olivelink;

import android.content.Context;
import android.util.Log;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.effect.CapsuleAnimUtilsKt;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Scalar;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.olivelink.SpringType;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleSpringAnimParams extends OLiveLinkBaseParams {
    public static final CapsuleSpringAnimParams INSTANCE = new CapsuleSpringAnimParams();
    private static final String OLIVE_LINK_SERVER_NAME = "CapsuleItemSpring";
    private static final String TAG = "CapsuleSpringAnimParams";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AppearParams extends OLiveLinkBaseParams.OLiveLinkGroup implements CapsuleItemSpringDataBuilder {
        public static final AppearParams INSTANCE;
        private static final Spring alphaSpringCommon;
        private static final Spring alphaSpringN3;
        private static final Spring blurSpringCommon;
        private static final Spring blurSpringN3;
        private static final Spring heightSpringCommon;
        private static final Spring heightSpringN3;
        private static final Spring transYSpringCommon;
        private static final Spring transYSpringN3;
        private static final Spring widthSpringCommon;
        private static final Spring widthSpringN3;

        static {
            AppearParams appearParams = new AppearParams(1, "新增动画_0到1");
            INSTANCE = appearParams;
            widthSpringCommon = appearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "宽度曲线", 0.4f, 0.5f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            widthSpringN3 = appearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "宽度曲线_N3", 0.0f, 0.35f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            heightSpringCommon = appearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "高度曲线", 0.0f, 0.2f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            heightSpringN3 = appearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "高度曲线_N3", 0.0f, 0.1f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            alphaSpringCommon = appearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "淡入曲线", 0.0f, 0.2f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            alphaSpringN3 = appearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "淡入曲线_N3", 0.0f, 0.2f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            blurSpringCommon = appearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "前景模糊曲线", 0.0f, 0.5f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            blurSpringN3 = appearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "前景模糊曲线_N3", 0.0f, 0.4f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            transYSpringCommon = appearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "内容Y轴位移曲线", 0.24f, 0.5f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            transYSpringN3 = appearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "内容Y轴位移曲线_N3", 0.0f, 0.32f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
        }

        @Override // com.oplus.systemui.notification.lockscreen.olivelink.CapsuleSpringAnimParams.CapsuleItemSpringDataBuilder
        public CapsuleItemSpringData buildCapsuleItemSpringData(float f, float f2, float f3, float f4) {
            Common common = Common.INSTANCE;
            float fFloatValue = common.getMinWidth().get().floatValue();
            return new CapsuleItemSpringData(common.getMaxBlur().get().floatValue(), -common.getMaxTransY().get().floatValue(), common.getMinScale().get().floatValue(), common.getMinAlpha().get().floatValue(), fFloatValue, f2, fFloatValue, f4, CapsuleAnimUtilsKt.floatAnim$default(getWidthSpring(), fFloatValue, f2, 0.0f, null, null, 28), CapsuleAnimUtilsKt.floatAnim$default(getHeightSpring(), fFloatValue, f4, 0.0f, null, null, 28), CapsuleAnimUtilsKt.floatAnim$default(getBlurSpring(), common.getMaxBlur().get().floatValue(), 0.0f, 0.0f, null, null, 28));
        }

        public final Spring getAlphaSpring() {
            CapsuleSpringAnimParams.INSTANCE.getClass();
            NotificationFeatureOption.INSTANCE.getClass();
            return NotificationFeatureOption.isCapsuleN3() ? alphaSpringN3 : alphaSpringCommon;
        }

        public final Spring getBlurSpring() {
            CapsuleSpringAnimParams.INSTANCE.getClass();
            NotificationFeatureOption.INSTANCE.getClass();
            return NotificationFeatureOption.isCapsuleN3() ? blurSpringN3 : blurSpringCommon;
        }

        public final Spring getHeightSpring() {
            CapsuleSpringAnimParams.INSTANCE.getClass();
            NotificationFeatureOption.INSTANCE.getClass();
            return NotificationFeatureOption.isCapsuleN3() ? heightSpringN3 : heightSpringCommon;
        }

        public final Spring getTransYSpring() {
            CapsuleSpringAnimParams.INSTANCE.getClass();
            NotificationFeatureOption.INSTANCE.getClass();
            return NotificationFeatureOption.isCapsuleN3() ? transYSpringN3 : transYSpringCommon;
        }

        public final Spring getWidthSpring() {
            CapsuleSpringAnimParams.INSTANCE.getClass();
            NotificationFeatureOption.INSTANCE.getClass();
            return NotificationFeatureOption.isCapsuleN3() ? widthSpringN3 : widthSpringCommon;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface CapsuleItemSpringDataBuilder {
        static /* synthetic */ CapsuleItemSpringData buildCapsuleItemSpringData$default(CapsuleItemSpringDataBuilder capsuleItemSpringDataBuilder, float f, float f2, float f3, float f4, int i) {
            if ((i & 1) != 0) {
                f = 0.0f;
            }
            if ((i & 2) != 0) {
                f2 = 0.0f;
            }
            if ((i & 4) != 0) {
                f3 = 0.0f;
            }
            if ((i & 8) != 0) {
                f4 = 0.0f;
            }
            return capsuleItemSpringDataBuilder.buildCapsuleItemSpringData(f, f2, f3, f4);
        }

        CapsuleItemSpringData buildCapsuleItemSpringData(float f, float f2, float f3, float f4);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Common extends OLiveLinkBaseParams.OLiveLinkGroup {
        public static final Common INSTANCE;
        private static final Scalar maxBlur;
        private static final Scalar maxTransY;
        private static final Scalar minAlpha;
        private static final Scalar minScale;
        private static final Scalar minWidth;

        static {
            Common common = new Common(0, "公共参数");
            INSTANCE = common;
            minWidth = common.safeScalar(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "最小宽度", Float.valueOf(60.0f));
            maxBlur = common.safeScalar(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "最大前景模糊", Float.valueOf(120.0f));
            maxTransY = common.safeScalar(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "内容Y轴位移", Float.valueOf(400.0f));
            minScale = common.safeScalar(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "内容最小缩放", Float.valueOf(0.2f));
            minAlpha = common.safeScalar(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "最小Alpha", Float.valueOf(0.0f));
        }

        public final Scalar getMaxBlur() {
            return maxBlur;
        }

        public final Scalar getMaxTransY() {
            return maxTransY;
        }

        public final Scalar getMinAlpha() {
            return minAlpha;
        }

        public final Scalar getMinScale() {
            return minScale;
        }

        public final Scalar getMinWidth() {
            return minWidth;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DisappearParams extends OLiveLinkBaseParams.OLiveLinkGroup implements CapsuleItemSpringDataBuilder {
        public static final DisappearParams INSTANCE;
        private static final Spring alphaSpringCommon;
        private static final Spring alphaSpringN3;
        private static final Spring blurSpringCommon;
        private static final Spring blurSpringN3;
        private static final Spring heightSpringCommon;
        private static final Spring heightSpringN3;
        private static final Spring scaleSpringCommon;
        private static final Spring scaleSpringN3;
        private static final Spring widthSpringCommon;
        private static final Spring widthSpringN3;

        static {
            DisappearParams disappearParams = new DisappearParams(2, "删除动画_1到0");
            INSTANCE = disappearParams;
            widthSpringCommon = disappearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "宽度曲线", 0.0f, 0.4f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            widthSpringN3 = disappearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "宽度曲线_N3", 0.0f, 0.25f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            heightSpringCommon = disappearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "高度曲线", 0.0f, 0.55f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            heightSpringN3 = disappearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "高度曲线_N3", 0.0f, 0.35f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            alphaSpringCommon = disappearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "淡出曲线", 0.0f, 0.3f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            alphaSpringN3 = disappearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "淡出曲线_N3", 0.0f, 0.2f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            blurSpringCommon = disappearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "前景模糊曲线", 0.0f, 0.2f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            blurSpringN3 = disappearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "前景模糊曲线_N3", 0.0f, 0.15f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            scaleSpringCommon = disappearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "内容缩放曲线", 0.0f, 0.25f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            scaleSpringN3 = disappearParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "内容缩放曲线_N3", 0.0f, 0.2f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
        }

        @Override // com.oplus.systemui.notification.lockscreen.olivelink.CapsuleSpringAnimParams.CapsuleItemSpringDataBuilder
        public CapsuleItemSpringData buildCapsuleItemSpringData(float f, float f2, float f3, float f4) {
            Common common = Common.INSTANCE;
            float fFloatValue = common.getMinWidth().get().floatValue();
            return new CapsuleItemSpringData(common.getMaxBlur().get().floatValue(), -common.getMaxTransY().get().floatValue(), common.getMinScale().get().floatValue(), common.getMinAlpha().get().floatValue(), f, fFloatValue, f3, fFloatValue, CapsuleAnimUtilsKt.floatAnim$default(getWidthSpring(), f, fFloatValue, 0.0f, null, null, 28), CapsuleAnimUtilsKt.floatAnim$default(getHeightSpring(), f3, fFloatValue, 0.0f, null, null, 28), CapsuleAnimUtilsKt.floatAnim$default(AppearParams.INSTANCE.getBlurSpring(), 0.0f, common.getMaxBlur().get().floatValue(), 0.0f, null, null, 28));
        }

        public final Spring getAlphaSpring() {
            CapsuleSpringAnimParams.INSTANCE.getClass();
            NotificationFeatureOption.INSTANCE.getClass();
            return NotificationFeatureOption.isCapsuleN3() ? alphaSpringN3 : alphaSpringCommon;
        }

        public final Spring getBlurSpring() {
            CapsuleSpringAnimParams.INSTANCE.getClass();
            NotificationFeatureOption.INSTANCE.getClass();
            return NotificationFeatureOption.isCapsuleN3() ? blurSpringN3 : blurSpringCommon;
        }

        public final Spring getHeightSpring() {
            CapsuleSpringAnimParams.INSTANCE.getClass();
            NotificationFeatureOption.INSTANCE.getClass();
            return NotificationFeatureOption.isCapsuleN3() ? heightSpringN3 : heightSpringCommon;
        }

        public final Spring getScaleSpring() {
            CapsuleSpringAnimParams.INSTANCE.getClass();
            NotificationFeatureOption.INSTANCE.getClass();
            return NotificationFeatureOption.isCapsuleN3() ? scaleSpringN3 : scaleSpringCommon;
        }

        public final Spring getWidthSpring() {
            CapsuleSpringAnimParams.INSTANCE.getClass();
            NotificationFeatureOption.INSTANCE.getClass();
            return NotificationFeatureOption.isCapsuleN3() ? widthSpringN3 : widthSpringCommon;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class EarTransXSpringData {
        private final COUISpringAnimation appearSpring;
        private final COUISpringAnimation disappearSpring;
        public final float maxTransX;
        private final COUISpringAnimation switchSpring;

        public EarTransXSpringData(float f, COUISpringAnimation cOUISpringAnimation, COUISpringAnimation cOUISpringAnimation2, COUISpringAnimation cOUISpringAnimation3) {
            this.maxTransX = f;
            this.appearSpring = cOUISpringAnimation;
            this.disappearSpring = cOUISpringAnimation2;
            this.switchSpring = cOUISpringAnimation3;
        }

        public final COUISpringAnimation component2() {
            return this.appearSpring;
        }

        public final COUISpringAnimation component3() {
            return this.disappearSpring;
        }

        public final COUISpringAnimation component4() {
            return this.switchSpring;
        }

        public final EarTransXSpringData copy(float f, COUISpringAnimation cOUISpringAnimation, COUISpringAnimation cOUISpringAnimation2, COUISpringAnimation cOUISpringAnimation3) {
            return new EarTransXSpringData(f, cOUISpringAnimation, cOUISpringAnimation2, cOUISpringAnimation3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EarTransXSpringData)) {
                return false;
            }
            EarTransXSpringData earTransXSpringData = (EarTransXSpringData) obj;
            return Float.compare(this.maxTransX, earTransXSpringData.maxTransX) == 0 && Intrinsics.areEqual(this.appearSpring, earTransXSpringData.appearSpring) && Intrinsics.areEqual(this.disappearSpring, earTransXSpringData.disappearSpring) && Intrinsics.areEqual(this.switchSpring, earTransXSpringData.switchSpring);
        }

        public final COUISpringAnimation getAppearSpring() {
            return this.appearSpring;
        }

        public final COUISpringAnimation getDisappearSpring() {
            return this.disappearSpring;
        }

        public final float getMaxTransX() {
            return this.maxTransX;
        }

        public final COUISpringAnimation getSwitchSpring() {
            return this.switchSpring;
        }

        public final int hashCode() {
            return this.switchSpring.hashCode() + ((this.disappearSpring.hashCode() + ((this.appearSpring.hashCode() + (Float.hashCode(this.maxTransX) * 31)) * 31)) * 31);
        }

        public String toString() {
            return "EarTransXSpringData(maxTransX=" + this.maxTransX + ", appearSpring=" + this.appearSpring + ", disappearSpring=" + this.disappearSpring + ", switchSpring=" + this.switchSpring + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class KEYGUARD extends OLiveLinkBaseParams.OLiveLinkGroup {
        public static final KEYGUARD INSTANCE;
        private static final Spring downToCapsuleSpring;
        private static final Scalar downToCapsuleVelocity;
        private static final Scalar hScrollMaxVelocity;
        private static final Spring hScrollSpring;
        private static final Scalar upToStackMaxVelocity;
        private static final Spring upToStackSpring;

        static {
            KEYGUARD keyguard = new KEYGUARD(5, "快捷入口联动");
            INSTANCE = keyguard;
            downToCapsuleVelocity = keyguard.safeScalar(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "列表切胶囊初速度", Float.valueOf(600.0f));
            downToCapsuleSpring = keyguard.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "列表切胶囊曲线", 0.4f, 0.6f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.6f, SpringType.SPRING_ANIMATION);
            hScrollMaxVelocity = keyguard.safeScalar(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "左右还原最大速度", Float.valueOf(800.0f));
            hScrollSpring = keyguard.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "左右还原曲线", 0.3f, 0.6f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.7f, SpringType.SPRING_ANIMATION);
            upToStackMaxVelocity = keyguard.safeScalar(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "胶囊切列表最大速度", Float.valueOf(500.0f));
            upToStackSpring = keyguard.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "胶囊切列表曲线", 0.4f, 0.6f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.6f, SpringType.SPRING_ANIMATION);
        }

        public final Spring getDownToCapsuleSpring() {
            return downToCapsuleSpring;
        }

        public final Scalar getDownToCapsuleVelocity() {
            return downToCapsuleVelocity;
        }

        public final Scalar getHScrollMaxVelocity() {
            return hScrollMaxVelocity;
        }

        public final Spring getHScrollSpring() {
            return hScrollSpring;
        }

        public final Scalar getUpToStackMaxVelocity() {
            return upToStackMaxVelocity;
        }

        public final Spring getUpToStackSpring() {
            return upToStackSpring;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SwitchParams extends OLiveLinkBaseParams.OLiveLinkGroup implements CapsuleItemSpringDataBuilder {
        public static final SwitchParams INSTANCE;
        private static final Spring heightSpringCommon;
        private static final Spring heightSpringN3;
        private static final Spring widthSpringCommon;
        private static final Spring widthSpringN3;
        private static final Scalar widthVelocityBPAnimLevel;
        private static final Scalar widthVelocityCommon;
        private static final Scalar widthVelocityN3;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class EarTransX {
            public static final EarTransX INSTANCE = new EarTransX();
            private static final Spring appearSpringCommon;
            private static final Spring appearSpringN3;
            private static final Spring disappearSpringCommon;
            private static final Spring disappearSpringN3;
            private static final Scalar earVelocity;
            private static final Scalar earVelocityBPAnimLevel;
            private static final Scalar maxTransX;
            private static final Scalar maxTransXBPAnimLevel;
            private static final Spring switchSpringCommon;
            private static final Spring switchSpringN3;

            static {
                SwitchParams switchParams = SwitchParams.INSTANCE;
                maxTransX = switchParams.safeScalar(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "耳朵最大水平位移", Float.valueOf(250.0f));
                maxTransXBPAnimLevel = switchParams.safeScalar(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "耳朵最大水平位移_轻量", Float.valueOf(200.0f));
                appearSpringCommon = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "耳朵显示曲线", 0.3f, 0.9f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
                appearSpringN3 = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "耳朵显示曲线_N3", 0.3f, 0.5f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
                disappearSpringCommon = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "耳朵消失曲线", 0.0f, 0.3f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
                disappearSpringN3 = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "耳朵消失曲线_N3", 0.0f, 0.15f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
                earVelocity = switchParams.safeScalar(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "耳朵切换速度", Float.valueOf(5000.0f));
                earVelocityBPAnimLevel = switchParams.safeScalar(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "耳朵切换速度_轻量", Float.valueOf(3700.0f));
                switchSpringCommon = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "耳朵切换曲线", 0.3f, 0.7f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
                switchSpringN3 = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "耳朵切换曲线_N3", 0.0f, 0.4f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            }

            /* JADX WARN: Code duplicated, block: B:9:0x001e  */
            public static float resolveEarVelocity() {
                Scalar scalar;
                if (CapsuleSpringAnimParams.access$isSmallSizeLightOs(CapsuleSpringAnimParams.INSTANCE)) {
                    NotificationFeatureOption.INSTANCE.getClass();
                    if (NotificationFeatureOption.isCapsuleN3()) {
                        scalar = earVelocity;
                    } else {
                        scalar = earVelocityBPAnimLevel;
                    }
                } else {
                    scalar = earVelocity;
                }
                return scalar.get().floatValue();
            }

            /* JADX WARN: Code duplicated, block: B:9:0x001e  */
            public static float resolveMaxTransX() {
                Scalar scalar;
                if (CapsuleSpringAnimParams.access$isSmallSizeLightOs(CapsuleSpringAnimParams.INSTANCE)) {
                    NotificationFeatureOption.INSTANCE.getClass();
                    if (NotificationFeatureOption.isCapsuleN3()) {
                        scalar = maxTransX;
                    } else {
                        scalar = maxTransXBPAnimLevel;
                    }
                } else {
                    scalar = maxTransX;
                }
                return scalar.get().floatValue();
            }

            public final Spring getAppearSpring() {
                CapsuleSpringAnimParams.INSTANCE.getClass();
                NotificationFeatureOption.INSTANCE.getClass();
                return NotificationFeatureOption.isCapsuleN3() ? appearSpringN3 : appearSpringCommon;
            }

            public final Spring getDisappearSpring() {
                CapsuleSpringAnimParams.INSTANCE.getClass();
                NotificationFeatureOption.INSTANCE.getClass();
                return NotificationFeatureOption.isCapsuleN3() ? disappearSpringN3 : disappearSpringCommon;
            }

            public final Scalar getEarVelocity() {
                return earVelocity;
            }

            public final Scalar getMaxTransX() {
                return maxTransX;
            }

            public final Spring getSwitchSpring() {
                CapsuleSpringAnimParams.INSTANCE.getClass();
                NotificationFeatureOption.INSTANCE.getClass();
                return NotificationFeatureOption.isCapsuleN3() ? switchSpringN3 : switchSpringCommon;
            }
        }

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class NewCard {
            public static final NewCard INSTANCE = new NewCard();
            private static final Spring blurSpringCommon;
            private static final Spring blurSpringN3;
            private static final Spring transYSpringCommon;
            private static final Spring transYSpringN3;

            static {
                SwitchParams switchParams = SwitchParams.INSTANCE;
                blurSpringCommon = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "新卡前景模糊曲线", 0.0f, 0.5f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
                blurSpringN3 = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "新卡前景模糊曲线_N3", 0.0f, 0.4f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
                transYSpringCommon = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "新卡内容Y轴位移曲线", 0.2f, 0.6f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
                transYSpringN3 = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "新卡内容Y轴位移曲线_N3", 0.0f, 0.32f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            }

            public final Spring getBlurSpring() {
                CapsuleSpringAnimParams.INSTANCE.getClass();
                NotificationFeatureOption.INSTANCE.getClass();
                return NotificationFeatureOption.isCapsuleN3() ? blurSpringN3 : blurSpringCommon;
            }

            public final Spring getTransYSpring() {
                CapsuleSpringAnimParams.INSTANCE.getClass();
                NotificationFeatureOption.INSTANCE.getClass();
                return NotificationFeatureOption.isCapsuleN3() ? transYSpringN3 : transYSpringCommon;
            }
        }

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class OldCard {
            public static final OldCard INSTANCE = new OldCard();
            private static final Spring alphaSpringCommon;
            private static final Spring alphaSpringN3;
            private static final Spring blurSpringCommon;
            private static final Spring blurSpringN3;
            private static final Spring scaleSpringCommon;
            private static final Spring scaleSpringN3;

            static {
                SwitchParams switchParams = SwitchParams.INSTANCE;
                blurSpringCommon = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "旧卡前景模糊曲线", 0.0f, 0.9f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
                blurSpringN3 = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "旧卡前景模糊曲线_N3", 0.0f, 0.6f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
                alphaSpringCommon = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "旧卡内容淡出曲线", 0.0f, 0.3f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
                alphaSpringN3 = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "旧卡内容淡出曲线_N3", 0.0f, 0.1f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
                scaleSpringCommon = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "旧卡内容缩放曲线", 0.0f, 0.15f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
                scaleSpringN3 = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "旧卡内容缩放曲线_N3", 0.0f, 0.3f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            }

            public final Spring getAlphaSpring() {
                CapsuleSpringAnimParams.INSTANCE.getClass();
                NotificationFeatureOption.INSTANCE.getClass();
                return NotificationFeatureOption.isCapsuleN3() ? alphaSpringN3 : alphaSpringCommon;
            }

            public final Spring getBlurSpring() {
                CapsuleSpringAnimParams.INSTANCE.getClass();
                NotificationFeatureOption.INSTANCE.getClass();
                return NotificationFeatureOption.isCapsuleN3() ? blurSpringN3 : blurSpringCommon;
            }

            public final Spring getScaleSpring() {
                CapsuleSpringAnimParams.INSTANCE.getClass();
                NotificationFeatureOption.INSTANCE.getClass();
                return NotificationFeatureOption.isCapsuleN3() ? scaleSpringN3 : scaleSpringCommon;
            }
        }

        static {
            SwitchParams switchParams = new SwitchParams(3, "新增动画_1到多");
            INSTANCE = switchParams;
            widthVelocityCommon = switchParams.safeScalar(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "宽度初始速度", Float.valueOf(-4800.0f));
            widthVelocityN3 = switchParams.safeScalar(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "宽度初始速度_N3", Float.valueOf(8000.0f));
            widthVelocityBPAnimLevel = switchParams.safeScalar(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "宽度初始速度_轻量", Float.valueOf(-3500.0f));
            widthSpringCommon = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "宽度曲线", 0.4f, 0.9f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            widthSpringN3 = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "宽度曲线_N3", 0.4f, 0.55f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            heightSpringCommon = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "高度曲线", 0.0f, 0.4f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
            heightSpringN3 = switchParams.safeSpring(CapsuleSpringAnimParams.OLIVE_LINK_SERVER_NAME, "高度曲线_N3", 0.0f, 0.3f, 0.0f, (240 & 32) != 0 ? 0.0f : 109.66f, (240 & 64) != 0 ? 0.0f : 0.0f, SpringType.SPRING_ANIMATION);
        }

        @Override // com.oplus.systemui.notification.lockscreen.olivelink.CapsuleSpringAnimParams.CapsuleItemSpringDataBuilder
        public CapsuleItemSpringData buildCapsuleItemSpringData(float f, float f2, float f3, float f4) {
            float fFloatValue = getWidthVelocity().get().floatValue();
            EarTransX earTransX = EarTransX.INSTANCE;
            earTransX.getClass();
            float fResolveMaxTransX = EarTransX.resolveMaxTransX();
            float fResolveEarVelocity = EarTransX.resolveEarVelocity();
            Common common = Common.INSTANCE;
            return new CapsuleItemSpringData(common.getMaxBlur().get().floatValue(), -common.getMaxTransY().get().floatValue(), common.getMinScale().get().floatValue(), common.getMinAlpha().get().floatValue(), f, f2, f3, f4, CapsuleAnimUtilsKt.floatAnim$default(getWidthSpring(), f, f2, 0.0f, Float.valueOf(fFloatValue), null, 20), CapsuleAnimUtilsKt.floatAnim$default(getHeightSpring(), f3, f4, 0.0f, null, null, 28), CapsuleAnimUtilsKt.floatAnim$default(OldCard.INSTANCE.getBlurSpring(), 0.0f, common.getMaxBlur().get().floatValue(), 0.0f, null, null, 28), CapsuleAnimUtilsKt.floatAnim$default(NewCard.INSTANCE.getBlurSpring(), common.getMaxBlur().get().floatValue(), 0.0f, 0.0f, null, null, 28), new EarTransXSpringData(fResolveMaxTransX, CapsuleAnimUtilsKt.floatAnim$default(earTransX.getAppearSpring(), fResolveMaxTransX, 0.0f, 0.0f, null, null, 28), CapsuleAnimUtilsKt.floatAnim$default(earTransX.getDisappearSpring(), 0.0f, fResolveMaxTransX, 0.0f, null, null, 28), CapsuleAnimUtilsKt.floatAnim$default(earTransX.getSwitchSpring(), 0.0f, 0.0f, 0.0f, Float.valueOf(-fResolveEarVelocity), null, 20)));
        }

        public final Spring getHeightSpring() {
            CapsuleSpringAnimParams.INSTANCE.getClass();
            NotificationFeatureOption.INSTANCE.getClass();
            return NotificationFeatureOption.isCapsuleN3() ? heightSpringN3 : heightSpringCommon;
        }

        public final Spring getWidthSpring() {
            CapsuleSpringAnimParams.INSTANCE.getClass();
            NotificationFeatureOption.INSTANCE.getClass();
            return NotificationFeatureOption.isCapsuleN3() ? widthSpringN3 : widthSpringCommon;
        }

        public final Scalar getWidthVelocity() {
            CapsuleSpringAnimParams.INSTANCE.getClass();
            NotificationFeatureOption.INSTANCE.getClass();
            if (NotificationFeatureOption.isCapsuleN3()) {
                return widthVelocityN3;
            }
            return (ScrimUtil.isBPAnimLevel() || ScrimUtil.isBAnimLevel()) ? widthVelocityBPAnimLevel : widthVelocityCommon;
        }
    }

    public static final boolean access$isSmallSizeLightOs(CapsuleSpringAnimParams capsuleSpringAnimParams) {
        capsuleSpringAnimParams.getClass();
        return (ScrimUtil.isBPAnimLevel() || ScrimUtil.isBAnimLevel()) && ((float) CapsuleConfig.INSTANCE.getItemWidth()) < 400.0f;
    }

    public final void initOliveLink(Context context) {
        OliveLinkConnector.INSTANCE.start(context, OLIVE_LINK_SERVER_NAME);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleSpringAnimParams-->".concat(String.valueOf(Common.INSTANCE)));
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleSpringAnimParams-->".concat(String.valueOf(AppearParams.INSTANCE)));
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleSpringAnimParams-->".concat(String.valueOf(DisappearParams.INSTANCE)));
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleSpringAnimParams-->".concat(String.valueOf(SwitchParams.INSTANCE)));
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleSpringAnimParams-->".concat(String.valueOf(SwitchParams.OldCard.INSTANCE)));
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleSpringAnimParams-->".concat(String.valueOf(SwitchParams.NewCard.INSTANCE)));
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleSpringAnimParams-->".concat(String.valueOf(SwitchParams.EarTransX.INSTANCE)));
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleSpringAnimParams-->".concat(String.valueOf(KEYGUARD.INSTANCE)));
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            NotificationFeatureOption.INSTANCE.getClass();
            Log.d("SystemUi--Notification", MobileInputLogger$.ExternalSyntheticOutline0.m("capsuleDefaultState=", NotificationFeatureOption.isOplusN1CapsuleDefaultState(), ",isCapsuleN3=", "CapsuleSpringAnimParams-->", NotificationFeatureOption.isCapsuleN3()));
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleItemSpringData {
        private final EarTransXSpringData earTransXSpringData;
        public final float endHeight;
        public final float endWidth;
        private final COUISpringAnimation heightSpringAnim;
        public final float maxBlur;
        public final float maxTransY;
        public final float minAlpha;
        public final float minScale;
        private final COUISpringAnimation p1BlurSpringAnim;
        private final COUISpringAnimation p2BlurSpringAnim;
        public final float startHeight;
        public final float startWidth;
        private final COUISpringAnimation widthSpringAnim;

        public CapsuleItemSpringData(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, COUISpringAnimation cOUISpringAnimation, COUISpringAnimation cOUISpringAnimation2, COUISpringAnimation cOUISpringAnimation3, COUISpringAnimation cOUISpringAnimation4, EarTransXSpringData earTransXSpringData) {
            this.maxBlur = f;
            this.maxTransY = f2;
            this.minScale = f3;
            this.minAlpha = f4;
            this.startWidth = f5;
            this.endWidth = f6;
            this.startHeight = f7;
            this.endHeight = f8;
            this.widthSpringAnim = cOUISpringAnimation;
            this.heightSpringAnim = cOUISpringAnimation2;
            this.p1BlurSpringAnim = cOUISpringAnimation3;
            this.p2BlurSpringAnim = cOUISpringAnimation4;
            this.earTransXSpringData = earTransXSpringData;
        }

        public final COUISpringAnimation component10() {
            return this.heightSpringAnim;
        }

        public final COUISpringAnimation component11() {
            return this.p1BlurSpringAnim;
        }

        public final COUISpringAnimation component12() {
            return this.p2BlurSpringAnim;
        }

        public final EarTransXSpringData component13() {
            return this.earTransXSpringData;
        }

        public final COUISpringAnimation component9() {
            return this.widthSpringAnim;
        }

        public final CapsuleItemSpringData copy(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, COUISpringAnimation cOUISpringAnimation, COUISpringAnimation cOUISpringAnimation2, COUISpringAnimation cOUISpringAnimation3, COUISpringAnimation cOUISpringAnimation4, EarTransXSpringData earTransXSpringData) {
            return new CapsuleItemSpringData(f, f2, f3, f4, f5, f6, f7, f8, cOUISpringAnimation, cOUISpringAnimation2, cOUISpringAnimation3, cOUISpringAnimation4, earTransXSpringData);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CapsuleItemSpringData)) {
                return false;
            }
            CapsuleItemSpringData capsuleItemSpringData = (CapsuleItemSpringData) obj;
            return Float.compare(this.maxBlur, capsuleItemSpringData.maxBlur) == 0 && Float.compare(this.maxTransY, capsuleItemSpringData.maxTransY) == 0 && Float.compare(this.minScale, capsuleItemSpringData.minScale) == 0 && Float.compare(this.minAlpha, capsuleItemSpringData.minAlpha) == 0 && Float.compare(this.startWidth, capsuleItemSpringData.startWidth) == 0 && Float.compare(this.endWidth, capsuleItemSpringData.endWidth) == 0 && Float.compare(this.startHeight, capsuleItemSpringData.startHeight) == 0 && Float.compare(this.endHeight, capsuleItemSpringData.endHeight) == 0 && Intrinsics.areEqual(this.widthSpringAnim, capsuleItemSpringData.widthSpringAnim) && Intrinsics.areEqual(this.heightSpringAnim, capsuleItemSpringData.heightSpringAnim) && Intrinsics.areEqual(this.p1BlurSpringAnim, capsuleItemSpringData.p1BlurSpringAnim) && Intrinsics.areEqual(this.p2BlurSpringAnim, capsuleItemSpringData.p2BlurSpringAnim) && Intrinsics.areEqual(this.earTransXSpringData, capsuleItemSpringData.earTransXSpringData);
        }

        public final EarTransXSpringData getEarTransXSpringData() {
            return this.earTransXSpringData;
        }

        public final float getEndHeight() {
            return this.endHeight;
        }

        public final float getEndWidth() {
            return this.endWidth;
        }

        public final COUISpringAnimation getHeightSpringAnim() {
            return this.heightSpringAnim;
        }

        public final float getMaxBlur() {
            return this.maxBlur;
        }

        public final float getMaxTransY() {
            return this.maxTransY;
        }

        public final float getMinAlpha() {
            return this.minAlpha;
        }

        public final float getMinScale() {
            return this.minScale;
        }

        public final COUISpringAnimation getP1BlurSpringAnim() {
            return this.p1BlurSpringAnim;
        }

        public final COUISpringAnimation getP2BlurSpringAnim() {
            return this.p2BlurSpringAnim;
        }

        public final float getStartHeight() {
            return this.startHeight;
        }

        public final float getStartWidth() {
            return this.startWidth;
        }

        public final COUISpringAnimation getWidthSpringAnim() {
            return this.widthSpringAnim;
        }

        public final int hashCode() {
            int iHashCode = (this.p1BlurSpringAnim.hashCode() + ((this.heightSpringAnim.hashCode() + ((this.widthSpringAnim.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(this.endHeight, ChangeSize$.ExternalSyntheticOutline0.m(this.startHeight, ChangeSize$.ExternalSyntheticOutline0.m(this.endWidth, ChangeSize$.ExternalSyntheticOutline0.m(this.startWidth, ChangeSize$.ExternalSyntheticOutline0.m(this.minAlpha, ChangeSize$.ExternalSyntheticOutline0.m(this.minScale, ChangeSize$.ExternalSyntheticOutline0.m(this.maxTransY, Float.hashCode(this.maxBlur) * 31, 31), 31), 31), 31), 31), 31), 31)) * 31)) * 31)) * 31;
            COUISpringAnimation cOUISpringAnimation = this.p2BlurSpringAnim;
            int iHashCode2 = (iHashCode + (cOUISpringAnimation == null ? 0 : cOUISpringAnimation.hashCode())) * 31;
            EarTransXSpringData earTransXSpringData = this.earTransXSpringData;
            return iHashCode2 + (earTransXSpringData != null ? earTransXSpringData.hashCode() : 0);
        }

        public String toString() {
            COUISpringAnimation cOUISpringAnimation = this.widthSpringAnim;
            COUISpringAnimation cOUISpringAnimation2 = this.heightSpringAnim;
            COUISpringAnimation cOUISpringAnimation3 = this.p1BlurSpringAnim;
            COUISpringAnimation cOUISpringAnimation4 = this.p2BlurSpringAnim;
            EarTransXSpringData earTransXSpringData = this.earTransXSpringData;
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("CapsuleItemSpringData(maxBlur=", this.maxBlur, ", maxTransY=", this.maxTransY, ", minScale=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.minScale, ", minAlpha=", this.minAlpha, ", startWidth=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.startWidth, ", endWidth=", this.endWidth, ", startHeight=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, this.startHeight, ", endHeight=", this.endHeight, ", widthSpringAnim=");
            sbM.append(cOUISpringAnimation);
            sbM.append(", heightSpringAnim=");
            sbM.append(cOUISpringAnimation2);
            sbM.append(", p1BlurSpringAnim=");
            sbM.append(cOUISpringAnimation3);
            sbM.append(", p2BlurSpringAnim=");
            sbM.append(cOUISpringAnimation4);
            sbM.append(", earTransXSpringData=");
            sbM.append(earTransXSpringData);
            sbM.append(")");
            return sbM.toString();
        }

        public /* synthetic */ CapsuleItemSpringData(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, COUISpringAnimation cOUISpringAnimation, COUISpringAnimation cOUISpringAnimation2, COUISpringAnimation cOUISpringAnimation3) {
            this(f, f2, f3, f4, f5, f6, f7, f8, cOUISpringAnimation, cOUISpringAnimation2, cOUISpringAnimation3, null, null);
        }
    }
}
