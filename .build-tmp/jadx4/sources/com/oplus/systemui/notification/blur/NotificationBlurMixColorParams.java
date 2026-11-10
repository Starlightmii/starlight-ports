package com.oplus.systemui.notification.blur;

import android.content.Context;
import android.graphics.Color;
import androidx.car.app.serialization.Bundler$;
import com.oplus.systemui.notification.row.material.NotificationBlurMode;
import com.oplusos.systemui.common.olivelink.AndroidColor;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Scalar;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationBlurMixColorParams {
    private static final Color DEFAULT_CAPSULE_LIGHT_BOTTOM;
    private static final Color DEFAULT_CAPSULE_LIGHT_TOP;
    private static final Color DEFAULT_DECORATORS_DARK_BOTTOM;
    private static final Color DEFAULT_DECORATORS_DARK_TOP;
    private static final Color DEFAULT_DECORATORS_LIGHT_BOTTOM;
    private static final Color DEFAULT_DECORATORS_LIGHT_BOTTOM_A;
    private static final Color DEFAULT_DECORATORS_LIGHT_BOTTOM_B;
    private static final Color DEFAULT_DECORATORS_LIGHT_TOP;
    private static final Color DEFAULT_DECORATORS_LIGHT_TOP_A;
    private static final Color DEFAULT_DECORATORS_LIGHT_TOP_B;
    private static final Color DEFAULT_DECORATORS_NO_STROKE_BOTTOM;
    private static final Color DEFAULT_DECORATORS_NO_STROKE_TOP;
    private static final Color DEFAULT_HEADS_UP_BOTTOM;
    private static final Color DEFAULT_HEADS_UP_BOTTOM_DARK;
    private static final Color DEFAULT_HEADS_UP_TOP;
    private static final Color DEFAULT_HEADS_UP_TOP_DARK;
    public static final String OLIVE_LINK_SERVER_NAME = "NotificationBlurMix";
    public static final String OLIVE_LINK_SERVER_NAME_KEYGUARD_BLUR_MIX = "KeyguardNotificationBlurMix";
    public static final NotificationBlurMixColorParams INSTANCE = new NotificationBlurMixColorParams();
    private static final Color DEFAULT_LIGHT_TOP = Color.valueOf(Color.parseColor("#19404040"));
    private static final Color DEFAULT_LIGHT_BOTTOM = Color.valueOf(Color.parseColor("#4D737373"));
    private static final Color DEFAULT_LIGHT_TOP_NO_STROKE = Color.valueOf(Color.parseColor("#80404040"));
    private static final Color DEFAULT_LIGHT_BOTTOM_NO_STROKE = Color.valueOf(Color.parseColor("#B2737373"));
    private static final Color DEFAULT_LIGHT_TOP_REGION_A = Color.valueOf(Color.parseColor("#4C595959"));
    private static final Color DEFAULT_LIGHT_BOTTOM_REGION_A = Color.valueOf(Color.parseColor("#724D4D4D"));
    private static final Color DEFAULT_LIGHT_TOP_REGION_B = Color.valueOf(Color.parseColor("#4D666666"));
    private static final Color DEFAULT_LIGHT_BOTTOM_REGION_B = Color.valueOf(Color.parseColor("#404D4D4D"));
    private static final Color DEFAULT_LIGHT_TOP_REGION_B_NO_STROKE = Color.valueOf(Color.parseColor("#73808080"));
    private static final Color DEFAULT_LIGHT_BOTTOM_REGION_B_NO_STROKE = Color.valueOf(Color.parseColor("#404D4D4D"));
    private static final Color DEFAULT_DARK_TOP = Color.valueOf(Color.parseColor("#59404040"));
    private static final Color DEFAULT_DARK_BOTTOM = Color.valueOf(Color.parseColor("#66737373"));
    private static final Color DEFAULT_DARK_TOP_NO_STROKE = Color.valueOf(Color.parseColor("#5A404040"));
    private static final Color DEFAULT_DARK_BOTTOM_NO_STROKE = Color.valueOf(Color.parseColor("#B2737373"));
    private static final Color SHOULD_DARKEN_STACK_FIRST_MIXED_TOP_LAYER_A = Color.valueOf(Color.parseColor("#4D595959"));
    private static final Color SHOULD_DARKEN_STACK_FIRST_MIXED_BOTTOM_LAYER_A = Color.valueOf(Color.parseColor("#594D4D4D"));
    private static final Color SHOULD_DARKEN_STACK_SECOND_MIXED_TOP_LAYER_A = Color.valueOf(Color.parseColor("#4D595959"));
    private static final Color SHOULD_DARKEN_STACK_SECOND_MIXED_BOTTOM_LAYER_A = Color.valueOf(Color.parseColor("#404D4D4D"));
    private static final Color SHOULD_DARKEN_STACK_FIRST_MIXED_TOP_LAYER_B = Color.valueOf(Color.parseColor("#4D595959"));
    private static final Color SHOULD_DARKEN_STACK_FIRST_MIXED_BOTTOM_LAYER_B = Color.valueOf(Color.parseColor("#404D4D4D"));
    private static final Color SHOULD_DARKEN_STACK_FIRST_MIXED_TOP_LAYER_B_NO_STROKE = Color.valueOf(Color.parseColor("#73737373"));
    private static final Color SHOULD_DARKEN_STACK_FIRST_MIXED_BOTTOM_LAYER_B_NO_STROKE = Color.valueOf(Color.parseColor("#404D4D4D"));
    private static final Color SHOULD_DARKEN_STACK_SECOND_MIXED_TOP_LAYER_B = Color.valueOf(Color.parseColor("#4D4D4D4D"));
    private static final Color SHOULD_DARKEN_STACK_SECOND_MIXED_BOTTOM_LAYER_B = Color.valueOf(Color.parseColor("#404D4D4D"));
    private static final Color SHOULD_DARKEN_STACK_SECOND_MIXED_TOP_LAYER_B_NO_STROKE = Color.valueOf(Color.parseColor("#73666666"));
    private static final Color SHOULD_DARKEN_STACK_SECOND_MIXED_BOTTOM_LAYER_B_NO_STROKE = Color.valueOf(Color.parseColor("#404D4D4D"));

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleCardMixedColors extends OLiveLinkGroup {
        public static final CapsuleCardMixedColors INSTANCE;
        private static final AndroidColor bottomLayerLight;
        private static final AndroidColor topLayerLight;

        static {
            CapsuleCardMixedColors capsuleCardMixedColors = new CapsuleCardMixedColors(NotificationGradientStrokeLineAdapter.CAPSULE_OLIVE_LINK_SERVER_NAME, "锁屏胶囊混色");
            INSTANCE = capsuleCardMixedColors;
            topLayerLight = capsuleCardMixedColors.color(Bundler$.ExternalSyntheticOutline0.m("01.", capsuleCardMixedColors.getGroupName(), ":LUMINOSITY"), NotificationBlurMixColorParams.DEFAULT_CAPSULE_LIGHT_TOP);
            bottomLayerLight = capsuleCardMixedColors.color(Bundler$.ExternalSyntheticOutline0.m("02.", capsuleCardMixedColors.getGroupName(), ":DODGE_BG"), NotificationBlurMixColorParams.DEFAULT_CAPSULE_LIGHT_BOTTOM);
        }

        public final AndroidColor getBottomLayerLight() {
            return bottomLayerLight;
        }

        public final AndroidColor getTopLayerLight() {
            return topLayerLight;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DecoratorsCardMixedColors extends OLiveLinkGroup {
        public static final DecoratorsCardMixedColors INSTANCE;
        private static final AndroidColor bottomLayerDark;
        private static final AndroidColor bottomLayerDarkNoStroke;
        private static final AndroidColor bottomLayerLight;
        private static final AndroidColor bottomLayerLightNoStroke;
        private static final AndroidColor topLayerDark;
        private static final AndroidColor topLayerDarkNoStroke;
        private static final AndroidColor topLayerLight;
        private static final AndroidColor topLayerLightNoStroke;

        static {
            DecoratorsCardMixedColors decoratorsCardMixedColors = new DecoratorsCardMixedColors(NotificationBlurMixColorParams.OLIVE_LINK_SERVER_NAME, "删除按钮混色");
            INSTANCE = decoratorsCardMixedColors;
            topLayerLight = decoratorsCardMixedColors.color(Bundler$.ExternalSyntheticOutline0.m(decoratorsCardMixedColors.getGroupName(), ":亮色_LUMINOSITY"), NotificationBlurMixColorParams.DEFAULT_DECORATORS_LIGHT_TOP);
            bottomLayerLight = decoratorsCardMixedColors.color(Bundler$.ExternalSyntheticOutline0.m(decoratorsCardMixedColors.getGroupName(), ":亮色_DODGE_BG"), NotificationBlurMixColorParams.DEFAULT_DECORATORS_LIGHT_BOTTOM);
            topLayerDark = decoratorsCardMixedColors.color(Bundler$.ExternalSyntheticOutline0.m(decoratorsCardMixedColors.getGroupName(), ":暗色_LUMINOSITY"), NotificationBlurMixColorParams.DEFAULT_DECORATORS_DARK_TOP);
            bottomLayerDark = decoratorsCardMixedColors.color(Bundler$.ExternalSyntheticOutline0.m(decoratorsCardMixedColors.getGroupName(), ":暗色_DODGE_BG"), NotificationBlurMixColorParams.DEFAULT_DECORATORS_DARK_BOTTOM);
            topLayerLightNoStroke = decoratorsCardMixedColors.color(Bundler$.ExternalSyntheticOutline0.m(decoratorsCardMixedColors.getGroupName(), ":亮色_关闭描边_LUMINOSITY"), NotificationBlurMixColorParams.DEFAULT_DECORATORS_NO_STROKE_TOP);
            bottomLayerLightNoStroke = decoratorsCardMixedColors.color(Bundler$.ExternalSyntheticOutline0.m(decoratorsCardMixedColors.getGroupName(), ":亮色_关闭描边_DODGE_BG"), NotificationBlurMixColorParams.DEFAULT_DECORATORS_NO_STROKE_BOTTOM);
            topLayerDarkNoStroke = decoratorsCardMixedColors.color(Bundler$.ExternalSyntheticOutline0.m(decoratorsCardMixedColors.getGroupName(), ":暗色_关闭描边_LUMINOSITY"), NotificationBlurMixColorParams.DEFAULT_DECORATORS_NO_STROKE_TOP);
            bottomLayerDarkNoStroke = decoratorsCardMixedColors.color(Bundler$.ExternalSyntheticOutline0.m(decoratorsCardMixedColors.getGroupName(), ":暗色_关闭描边_DODGE_BG"), NotificationBlurMixColorParams.DEFAULT_DECORATORS_NO_STROKE_BOTTOM);
        }

        public final AndroidColor getBottomLayerDark() {
            return bottomLayerDark;
        }

        public final AndroidColor getBottomLayerDarkNoStroke() {
            return bottomLayerDarkNoStroke;
        }

        public final AndroidColor getBottomLayerLight() {
            return bottomLayerLight;
        }

        public final AndroidColor getBottomLayerLightNoStroke() {
            return bottomLayerLightNoStroke;
        }

        public final AndroidColor getTopLayerDark() {
            return topLayerDark;
        }

        public final AndroidColor getTopLayerDarkNoStroke() {
            return topLayerDarkNoStroke;
        }

        public final AndroidColor getTopLayerLight() {
            return topLayerLight;
        }

        public final AndroidColor getTopLayerLightNoStroke() {
            return topLayerLightNoStroke;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DecoratorsCardMixedColorsB extends OLiveLinkGroup {
        public static final DecoratorsCardMixedColorsB INSTANCE;
        private static final AndroidColor bottomLayerLight;
        private static final AndroidColor topLayerLight;

        static {
            DecoratorsCardMixedColorsB decoratorsCardMixedColorsB = new DecoratorsCardMixedColorsB(NotificationBlurMixColorParams.OLIVE_LINK_SERVER_NAME_KEYGUARD_BLUR_MIX, "收起按钮混色-B区");
            INSTANCE = decoratorsCardMixedColorsB;
            topLayerLight = decoratorsCardMixedColorsB.color(Bundler$.ExternalSyntheticOutline0.m(decoratorsCardMixedColorsB.getGroupName(), ":亮色_LUMINOSITY"), NotificationBlurMixColorParams.DEFAULT_DECORATORS_LIGHT_TOP_B);
            bottomLayerLight = decoratorsCardMixedColorsB.color(Bundler$.ExternalSyntheticOutline0.m(decoratorsCardMixedColorsB.getGroupName(), ":亮色_DODGE_BG"), NotificationBlurMixColorParams.DEFAULT_DECORATORS_LIGHT_BOTTOM_B);
        }

        public final AndroidColor getBottomLayerLight() {
            return bottomLayerLight;
        }

        public final AndroidColor getTopLayerLight() {
            return topLayerLight;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class NormalCardMixedColorsB extends NormalCardMixedColors {
        public static final NormalCardMixedColorsB INSTANCE = new NormalCardMixedColorsB(NotificationBlurMixColorParams.DEFAULT_LIGHT_TOP_REGION_B, NotificationBlurMixColorParams.DEFAULT_LIGHT_BOTTOM_REGION_B, "B区", NotificationBlurMixColorParams.OLIVE_LINK_SERVER_NAME_KEYGUARD_BLUR_MIX, "锁屏通知混色");
    }

    static {
        Color colorValueOf = Color.valueOf(Color.parseColor("#33A6A6A6"));
        DEFAULT_DECORATORS_LIGHT_TOP = colorValueOf;
        Color colorValueOf2 = Color.valueOf(Color.parseColor("#99666666"));
        DEFAULT_DECORATORS_LIGHT_BOTTOM = colorValueOf2;
        DEFAULT_DECORATORS_NO_STROKE_TOP = Color.valueOf(Color.parseColor("#40A6A6A6"));
        DEFAULT_DECORATORS_NO_STROKE_BOTTOM = Color.valueOf(Color.parseColor("#A6666666"));
        DEFAULT_DECORATORS_DARK_TOP = colorValueOf;
        DEFAULT_DECORATORS_DARK_BOTTOM = colorValueOf2;
        DEFAULT_DECORATORS_LIGHT_TOP_A = Color.valueOf(Color.parseColor("#7F595959"));
        DEFAULT_DECORATORS_LIGHT_BOTTOM_A = Color.valueOf(Color.parseColor("#CC3E3E3E"));
        DEFAULT_DECORATORS_LIGHT_TOP_B = Color.valueOf(Color.parseColor("#7F404040"));
        DEFAULT_DECORATORS_LIGHT_BOTTOM_B = Color.valueOf(Color.parseColor("#CC595959"));
        DEFAULT_HEADS_UP_TOP = Color.valueOf(Color.parseColor("#8C595959"));
        DEFAULT_HEADS_UP_BOTTOM = Color.valueOf(Color.parseColor("#FF333333"));
        DEFAULT_HEADS_UP_TOP_DARK = Color.valueOf(Color.parseColor("#80BFBFBF"));
        DEFAULT_HEADS_UP_BOTTOM_DARK = Color.valueOf(Color.parseColor("#CC333333"));
        DEFAULT_CAPSULE_LIGHT_TOP = Color.valueOf(Color.parseColor("#59595959"));
        DEFAULT_CAPSULE_LIGHT_BOTTOM = Color.valueOf(Color.parseColor("#404D4D4D"));
    }

    public static int resolveCapsuleMixedBottomLayer() {
        return resolveColor(CapsuleCardMixedColors.INSTANCE.getBottomLayerLight().get(), DEFAULT_CAPSULE_LIGHT_BOTTOM);
    }

    public static int resolveCapsuleMixedTopLayer() {
        return resolveColor(CapsuleCardMixedColors.INSTANCE.getTopLayerLight().get(), DEFAULT_CAPSULE_LIGHT_TOP);
    }

    public static int resolveColor(Color color, Color color2) {
        int argb = color.toArgb();
        return argb == 0 ? color2.toArgb() : argb;
    }

    public static int resolveDecoratorsMixedBottomLayer(boolean z, boolean z2) {
        Color color;
        Color color2;
        if (z) {
            DecoratorsCardMixedColors decoratorsCardMixedColors = DecoratorsCardMixedColors.INSTANCE;
            color = (z2 ? decoratorsCardMixedColors.getBottomLayerDark() : decoratorsCardMixedColors.getBottomLayerDarkNoStroke()).get();
        } else {
            DecoratorsCardMixedColors decoratorsCardMixedColors2 = DecoratorsCardMixedColors.INSTANCE;
            color = (z2 ? decoratorsCardMixedColors2.getBottomLayerLight() : decoratorsCardMixedColors2.getBottomLayerLightNoStroke()).get();
        }
        if (z2) {
            color2 = z ? DEFAULT_DECORATORS_DARK_BOTTOM : DEFAULT_DECORATORS_LIGHT_BOTTOM;
        } else {
            color2 = DEFAULT_DECORATORS_NO_STROKE_BOTTOM;
        }
        return resolveColor(color, color2);
    }

    public static int resolveDecoratorsMixedBottomLayerA() {
        return resolveColor(DecoratorsCardMixedColorsA.INSTANCE.getBottomLayerLight().get(), DEFAULT_DECORATORS_LIGHT_BOTTOM_A);
    }

    public static int resolveDecoratorsMixedBottomLayerB() {
        return resolveColor(DecoratorsCardMixedColorsB.INSTANCE.getBottomLayerLight().get(), DEFAULT_DECORATORS_LIGHT_BOTTOM_B);
    }

    public static int resolveDecoratorsMixedTopLayer(boolean z, boolean z2) {
        Color color;
        Color color2;
        if (z) {
            DecoratorsCardMixedColors decoratorsCardMixedColors = DecoratorsCardMixedColors.INSTANCE;
            color = (z2 ? decoratorsCardMixedColors.getTopLayerDark() : decoratorsCardMixedColors.getTopLayerDarkNoStroke()).get();
        } else {
            DecoratorsCardMixedColors decoratorsCardMixedColors2 = DecoratorsCardMixedColors.INSTANCE;
            color = (z2 ? decoratorsCardMixedColors2.getTopLayerLight() : decoratorsCardMixedColors2.getTopLayerLightNoStroke()).get();
        }
        if (z2) {
            color2 = z ? DEFAULT_DECORATORS_DARK_TOP : DEFAULT_DECORATORS_LIGHT_TOP;
        } else {
            color2 = DEFAULT_DECORATORS_NO_STROKE_TOP;
        }
        return resolveColor(color, color2);
    }

    public static int resolveDecoratorsMixedTopLayerA() {
        return resolveColor(DecoratorsCardMixedColorsA.INSTANCE.getTopLayerLight().get(), DEFAULT_DECORATORS_LIGHT_TOP_A);
    }

    public static int resolveDecoratorsMixedTopLayerB() {
        return resolveColor(DecoratorsCardMixedColorsB.INSTANCE.getTopLayerLight().get(), DEFAULT_DECORATORS_LIGHT_TOP_B);
    }

    public static int resolveExpandHeaderBtnMixedBottomLayer(boolean z) {
        return resolveColor(z ? ExpandHeaderBtnMixedColors.INSTANCE.getBottomLayerDark().get() : ExpandHeaderBtnMixedColors.INSTANCE.getBottomLayerLight().get(), z ? DEFAULT_DARK_BOTTOM : DEFAULT_LIGHT_BOTTOM);
    }

    public static int resolveExpandHeaderBtnMixedBottomLayerA() {
        return resolveColor(ExpandHeaderBtnMixedColorsA.INSTANCE.getBottomLayerLight().get(), DEFAULT_LIGHT_BOTTOM_REGION_A);
    }

    public static int resolveExpandHeaderBtnMixedBottomLayerB() {
        return resolveColor(ExpandHeaderBtnMixedColorsB.INSTANCE.getBottomLayerLight().get(), DEFAULT_LIGHT_BOTTOM_REGION_B);
    }

    public static int resolveExpandHeaderBtnMixedTopLayer(boolean z) {
        return resolveColor(z ? ExpandHeaderBtnMixedColors.INSTANCE.getTopLayerDark().get() : ExpandHeaderBtnMixedColors.INSTANCE.getTopLayerLight().get(), z ? DEFAULT_DARK_TOP : DEFAULT_LIGHT_TOP);
    }

    public static int resolveExpandHeaderBtnMixedTopLayerA() {
        return resolveColor(ExpandHeaderBtnMixedColorsA.INSTANCE.getTopLayerLight().get(), DEFAULT_LIGHT_TOP_REGION_A);
    }

    public static int resolveExpandHeaderBtnMixedTopLayerB() {
        return resolveColor(ExpandHeaderBtnMixedColorsB.INSTANCE.getTopLayerLight().get(), DEFAULT_LIGHT_TOP_REGION_B);
    }

    public final void initOliveLink(Context context) {
        OliveLinkConnector.INSTANCE.start(context, OLIVE_LINK_SERVER_NAME);
    }

    public final void registerChangeListener(Object obj, Function0 function0) {
        DecoratorsCardMixedColors decoratorsCardMixedColors = DecoratorsCardMixedColors.INSTANCE;
        decoratorsCardMixedColors.getTopLayerLight().registerChangeListener(obj, function0);
        decoratorsCardMixedColors.getBottomLayerLight().registerChangeListener(obj, function0);
        decoratorsCardMixedColors.getTopLayerDark().registerChangeListener(obj, function0);
        decoratorsCardMixedColors.getBottomLayerDark().registerChangeListener(obj, function0);
        decoratorsCardMixedColors.getTopLayerLightNoStroke().registerChangeListener(obj, function0);
        decoratorsCardMixedColors.getBottomLayerLightNoStroke().registerChangeListener(obj, function0);
        decoratorsCardMixedColors.getTopLayerDarkNoStroke().registerChangeListener(obj, function0);
        decoratorsCardMixedColors.getBottomLayerDarkNoStroke().registerChangeListener(obj, function0);
        CapsuleCardMixedColors capsuleCardMixedColors = CapsuleCardMixedColors.INSTANCE;
        capsuleCardMixedColors.getTopLayerLight().registerChangeListener(obj, function0);
        capsuleCardMixedColors.getBottomLayerLight().registerChangeListener(obj, function0);
    }

    public final int resolveHeadsUpBottomLayerColor(NotificationBlurMode notificationBlurMode) {
        return (notificationBlurMode == null || !notificationBlurMode.isHeadsUpBlurDark()) ? HeadsUpCardBlurParams.INSTANCE.getBottomLayerLight().get().toArgb() : HeadsUpCardBlurParams.INSTANCE.getBottomLayerDark().get().toArgb();
    }

    public final int resolveHeadsUpMotionMixMode(NotificationBlurMode notificationBlurMode) {
        return (notificationBlurMode == null || !notificationBlurMode.isHeadsUpBlurDark()) ? HeadsUpCardBlurParams.INSTANCE.getBlendModeLight().get().intValue() : HeadsUpCardBlurParams.INSTANCE.getBlendModeDark().get().intValue();
    }

    public final int resolveHeadsUpTopLayerColor(NotificationBlurMode notificationBlurMode) {
        return (notificationBlurMode == null || !notificationBlurMode.isHeadsUpBlurDark()) ? HeadsUpCardBlurParams.INSTANCE.getTopLayerLight().get().toArgb() : HeadsUpCardBlurParams.INSTANCE.getTopLayerDark().get().toArgb();
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class OLiveLinkGroup {
        private final String groupName;
        private final String serverName;

        public OLiveLinkGroup(String str, String str2) {
            this.serverName = str;
            this.groupName = str2;
        }

        public final AndroidColor color(String str, Color color) {
            return OliveLinkConnector.INSTANCE.color(this.serverName, str, this.groupName, color, "");
        }

        public final String getGroupName() {
            return this.groupName;
        }

        public final String getServerName() {
            return this.serverName;
        }

        public final Scalar scalar(String str, int i) {
            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, this.serverName, str, this.groupName, Integer.valueOf(i), 16);
        }

        public final Scalar scalar(String str, float f) {
            return OliveLinkConnector.safeScalar$default(OliveLinkConnector.INSTANCE, this.serverName, str, this.groupName, Float.valueOf(f), 16);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class NormalCardMixedColors extends OLiveLinkGroup {
        private final Lazy bottomLayer$delegate;
        private final Color defaultBottomLayer;
        private final Color defaultTopLayer;
        public final boolean isDebug;
        private final Lazy topLayer$delegate;

        public NormalCardMixedColors(Color color, Color color2, String str, String str2, String str3) {
            super(str2, str3);
            this.defaultTopLayer = color;
            this.defaultBottomLayer = color2;
            this.isDebug = true;
            NotificationBlurMixColorParams$NormalCardMixedColors$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new NotificationBlurMixColorParams$NormalCardMixedColors$.ExternalSyntheticLambda0(0);
            externalSyntheticLambda0.f$0 = this;
            externalSyntheticLambda0.f$1 = str3;
            externalSyntheticLambda0.f$2 = str;
            VarHandle.storeStoreFence();
            this.topLayer$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda0);
            NotificationBlurMixColorParams$NormalCardMixedColors$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new NotificationBlurMixColorParams$NormalCardMixedColors$.ExternalSyntheticLambda0(1);
            externalSyntheticLambda1.f$0 = this;
            externalSyntheticLambda1.f$1 = str3;
            externalSyntheticLambda1.f$2 = str;
            VarHandle.storeStoreFence();
            this.bottomLayer$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda1);
        }

        public final AndroidColor getBottomLayer() {
            return (AndroidColor) this.bottomLayer$delegate.getValue();
        }

        public final int getBottomLayerColor() {
            return (this.isDebug ? getBottomLayer().get() : this.defaultBottomLayer).toArgb();
        }

        public final AndroidColor getTopLayer() {
            return (AndroidColor) this.topLayer$delegate.getValue();
        }

        public final int getTopLayerColor() {
            return (this.isDebug ? getTopLayer().get() : this.defaultTopLayer).toArgb();
        }

        public /* synthetic */ NormalCardMixedColors(Color color, Color color2, String str, int i) {
            this(color, color2, (i & 4) != 0 ? "亮色" : str, NotificationBlurMixColorParams.OLIVE_LINK_SERVER_NAME, "普通通知混色");
        }
    }
}
