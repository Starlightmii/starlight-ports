package com.oplus.systemui.notification.blur;

import android.content.Context;
import android.graphics.Color;
import com.oplus.posteffect.params.GradientStrokeLineParams;
import com.oplusos.systemui.common.blurability.StrokeLineGroup;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationGradientStrokeLineAdapter {
    public static final String CAPSULE_OLIVE_LINK_SERVER_NAME = "CapsuleGradientStrokeLine";
    public static final NotificationGradientStrokeLineAdapter INSTANCE = new NotificationGradientStrokeLineAdapter();
    private static final Color DEFAULT_STROKE_LINE_COLOR = Color.valueOf(-1);
    private static final Color DEFAULT_LIGHT_STROKE_LINE_COLOR = Color.valueOf(Color.parseColor("#D8FFFFFF"));
    private static final Color DEFAULT_DARK_STROKE_LINE_COLOR = Color.valueOf(Color.parseColor("#A5FFFFFF"));

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleStrokeLineGroup extends StrokeLineGroup {
        public static final CapsuleStrokeLineGroup INSTANCE;

        static {
            GradientStrokeLineParams gradientStrokeLineParams = new GradientStrokeLineParams();
            gradientStrokeLineParams.setStrokeLineColor(NotificationGradientStrokeLineAdapter.DEFAULT_STROKE_LINE_COLOR);
            gradientStrokeLineParams.setStrokeLineVerticalNearSolid(2.0f);
            gradientStrokeLineParams.setStrokeLineVerticalNearFade(6.0f);
            gradientStrokeLineParams.setStrokeLineVerticalFarSolid(2.0f);
            gradientStrokeLineParams.setStrokeLineVerticalFarFade(3.0f);
            gradientStrokeLineParams.setStrokeLineAlphaNear(0.4f);
            gradientStrokeLineParams.setStrokeLineAlphaFar(0.8f);
            gradientStrokeLineParams.setStrokeLineTransverseNearSolid(0.2f);
            gradientStrokeLineParams.setStrokeLineTransverseNearFade(0.4f);
            gradientStrokeLineParams.setStrokeLineTransverseFarSolid(0.15f);
            gradientStrokeLineParams.setStrokeLineTransverseFarFade(0.4f);
            gradientStrokeLineParams.setRatio(0.0f);
            gradientStrokeLineParams.setStrokeLinePow(4.5f);
            gradientStrokeLineParams.setStrokeLineMix(0.5f);
            INSTANCE = new CapsuleStrokeLineGroup(NotificationGradientStrokeLineAdapter.CAPSULE_OLIVE_LINK_SERVER_NAME, "锁屏胶囊描边", gradientStrokeLineParams, 0.7f);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DecoratorsStrokeLineGroup extends StrokeLineGroup {
        public static final DecoratorsStrokeLineGroup INSTANCE;

        static {
            GradientStrokeLineParams gradientStrokeLineParams = new GradientStrokeLineParams();
            gradientStrokeLineParams.setStrokeLineColor(NotificationGradientStrokeLineAdapter.DEFAULT_STROKE_LINE_COLOR);
            gradientStrokeLineParams.setStrokeLineVerticalNearSolid(2.0f);
            gradientStrokeLineParams.setStrokeLineVerticalNearFade(7.0f);
            gradientStrokeLineParams.setStrokeLineVerticalFarSolid(1.0f);
            gradientStrokeLineParams.setStrokeLineVerticalFarFade(3.0f);
            gradientStrokeLineParams.setStrokeLineAlphaNear(0.5f);
            gradientStrokeLineParams.setStrokeLineAlphaFar(0.6f);
            gradientStrokeLineParams.setStrokeLineTransverseNearSolid(0.1f);
            gradientStrokeLineParams.setStrokeLineTransverseNearFade(0.4f);
            gradientStrokeLineParams.setStrokeLineTransverseFarSolid(0.15f);
            gradientStrokeLineParams.setStrokeLineTransverseFarFade(0.35f);
            gradientStrokeLineParams.setRatio(0.0f);
            gradientStrokeLineParams.setStrokeLinePow(4.88f);
            gradientStrokeLineParams.setStrokeLineMix(0.5f);
            INSTANCE = new DecoratorsStrokeLineGroup(NotificationBlurMixColorParams.OLIVE_LINK_SERVER_NAME, "删除、收起按钮描边", gradientStrokeLineParams, 0.7f);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class NotificationStrokeLineDarkGroup extends NotificationStrokeLineGroup {
        public static final NotificationStrokeLineDarkGroup INSTANCE = new NotificationStrokeLineDarkGroup(NotificationBlurMixColorParams.OLIVE_LINK_SERVER_NAME, "普通通知描边暗色(桌面下拉)", NotificationGradientStrokeLineAdapter.access$createDarkGradientStrokeLineParams(NotificationGradientStrokeLineAdapter.INSTANCE), 0.5f);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class NotificationStrokeLineGroup extends StrokeLineGroup {
        public NotificationStrokeLineGroup(String str, String str2, GradientStrokeLineParams gradientStrokeLineParams, float f) {
            super(str, str2, gradientStrokeLineParams, f);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class NotificationStrokeLineLightGroup extends NotificationStrokeLineGroup {
        public static final NotificationStrokeLineLightGroup INSTANCE = new NotificationStrokeLineLightGroup(NotificationBlurMixColorParams.OLIVE_LINK_SERVER_NAME, "普通通知描边亮色(桌面下拉)", NotificationGradientStrokeLineAdapter.access$createLightGradientStrokeLineParams(NotificationGradientStrokeLineAdapter.INSTANCE), 0.8f);
    }

    public static final GradientStrokeLineParams access$createDarkGradientStrokeLineParams(NotificationGradientStrokeLineAdapter notificationGradientStrokeLineAdapter) {
        notificationGradientStrokeLineAdapter.getClass();
        return new GradientStrokeLineParams(DEFAULT_DARK_STROKE_LINE_COLOR, 2.0f, 6.0f, 2.0f, 4.0f, 0.7f, 1.0f, 0.15f, 0.4f, 0.2f, 0.4f, 0.0f, 4.46f, 1.1f);
    }

    public static final GradientStrokeLineParams access$createKeyguardGradientStrokeLineParams(NotificationGradientStrokeLineAdapter notificationGradientStrokeLineAdapter) {
        notificationGradientStrokeLineAdapter.getClass();
        return new GradientStrokeLineParams(DEFAULT_STROKE_LINE_COLOR, 2.0f, 6.0f, 2.0f, 4.0f, 0.4f, 0.5f, 0.2f, 0.4f, 0.2f, 0.35f, 0.0f, 3.0f, 1.0f);
    }

    public static final GradientStrokeLineParams access$createLightGradientStrokeLineParams(NotificationGradientStrokeLineAdapter notificationGradientStrokeLineAdapter) {
        notificationGradientStrokeLineAdapter.getClass();
        return new GradientStrokeLineParams(DEFAULT_LIGHT_STROKE_LINE_COLOR, 2.0f, 6.0f, 2.0f, 4.0f, 0.5f, 0.7f, 0.2f, 0.4f, 0.2f, 0.35f, 0.0f, 3.0f, 1.0f);
    }

    public final void initCapsuleOliveLink(Context context) {
        OliveLinkConnector.INSTANCE.start(context, CAPSULE_OLIVE_LINK_SERVER_NAME);
        CapsuleStrokeLineGroup.INSTANCE.createStrokeLineParams();
    }

    public final void initOliveLink(Context context) {
        OliveLinkConnector oliveLinkConnector = OliveLinkConnector.INSTANCE;
        oliveLinkConnector.start(context, NotificationBlurMixColorParams.OLIVE_LINK_SERVER_NAME);
        oliveLinkConnector.start(context, NotificationBlurMixColorParams.OLIVE_LINK_SERVER_NAME_KEYGUARD_BLUR_MIX);
        initCapsuleOliveLink(context);
    }

    public final void registerChangeListener(Object obj, Function0 function0) {
        NotificationStrokeLineLightGroup.INSTANCE.registerChangeListener(obj, function0);
        NotificationStrokeLineDarkGroup.INSTANCE.registerChangeListener(obj, function0);
        KeyguardFirstStackStrokeLineGroup.INSTANCE.registerChangeListener(obj, function0);
        KeyguardFirstStackAStrokeLineGroup.INSTANCE.registerChangeListener(obj, function0);
        KeyguardSecondStackStrokeLineGroup.INSTANCE.registerChangeListener(obj, function0);
        KeyguardSecondStackAStrokeLineGroup.INSTANCE.registerChangeListener(obj, function0);
        KeyguardNotificationBStrokeLineGroup.INSTANCE.registerChangeListener(obj, function0);
        KeyguardNotificationAStrokeLineGroup.INSTANCE.registerChangeListener(obj, function0);
        DecoratorsStrokeLineGroup.INSTANCE.registerChangeListener(obj, function0);
        CapsuleStrokeLineGroup.INSTANCE.registerChangeListener(obj, function0);
    }
}
