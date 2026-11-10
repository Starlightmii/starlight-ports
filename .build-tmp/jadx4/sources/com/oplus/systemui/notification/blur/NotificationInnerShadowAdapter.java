package com.oplus.systemui.notification.blur;

import android.content.Context;
import android.graphics.Color;
import androidx.car.app.serialization.Bundler$;
import com.oplus.posteffect.params.InnerShadowParams;
import com.oplus.systemui.notification.row.material.NotificationBlurMode;
import com.oplusos.systemui.common.blurability.InnerShadowGroup;
import com.oplusos.systemui.common.olivelink.OliveLinkConnector;
import com.oplusos.systemui.common.olivelink.Scalar;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationInnerShadowAdapter {
    private static final String TAG = "NotificationInnerShadowAdapter";
    public static final NotificationInnerShadowAdapter INSTANCE = new NotificationInnerShadowAdapter();
    private static final Color DEFAULT_SHADOW_COLOR = Color.valueOf(-1);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleInnerShadowGroup extends InnerShadowGroup {
        public static final CapsuleInnerShadowGroup INSTANCE;
        private static final Scalar metaBallTouchOffsetScaleX;

        static {
            InnerShadowParams innerShadowParams = new InnerShadowParams();
            innerShadowParams.setShadowColor(Color.valueOf(-1));
            innerShadowParams.setOffsetShadowMaxAlpha(0.1f);
            innerShadowParams.setOffsetShadowMinAlpha(0.0f);
            innerShadowParams.setNoOffsetShadowAlpha(0.1f);
            innerShadowParams.setOffsetShadowRange(80.0f);
            innerShadowParams.setOffsetShadowX(0.0f);
            innerShadowParams.setOffsetShadowY(36.0f);
            innerShadowParams.setOffsetShadowScaleX(1.5f);
            innerShadowParams.setOffsetShadowScaleY(1.5f);
            innerShadowParams.setOffsetShadowPow(20.0f);
            innerShadowParams.setOffsetShadowMix(0.4f);
            innerShadowParams.setNoOffsetShadowRange(0.0f);
            innerShadowParams.setNoOffsetShadowPow(20.0f);
            innerShadowParams.setNoOffsetShadowMix(0.5f);
            CapsuleInnerShadowGroup capsuleInnerShadowGroup = new CapsuleInnerShadowGroup(NotificationGradientStrokeLineAdapter.CAPSULE_OLIVE_LINK_SERVER_NAME, "锁屏胶囊内阴影", innerShadowParams, 1.0f);
            INSTANCE = capsuleInnerShadowGroup;
            metaBallTouchOffsetScaleX = capsuleInnerShadowGroup.scalar(Bundler$.ExternalSyntheticOutline0.m("15-", capsuleInnerShadowGroup.getGroupName(), ":融球接触时对齐的OffsetScaleX"), 1.0f, "开始融球时的左右卡offsetScaleX，如不为1则会出现融球接触点边界显示");
        }

        public final Scalar getMetaBallTouchOffsetScaleX() {
            return metaBallTouchOffsetScaleX;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class DecoratorsInnerShadowGroup extends InnerShadowGroup {
        public static final DecoratorsInnerShadowGroup INSTANCE;

        static {
            InnerShadowParams innerShadowParams = new InnerShadowParams();
            innerShadowParams.setShadowColor(Color.valueOf(-1));
            innerShadowParams.setOffsetShadowMaxAlpha(0.2f);
            innerShadowParams.setOffsetShadowMinAlpha(0.0f);
            innerShadowParams.setNoOffsetShadowAlpha(0.1f);
            innerShadowParams.setOffsetShadowRange(50.0f);
            innerShadowParams.setOffsetShadowX(0.0f);
            innerShadowParams.setOffsetShadowY(36.0f);
            innerShadowParams.setOffsetShadowScaleX(1.34f);
            innerShadowParams.setOffsetShadowScaleY(1.26f);
            innerShadowParams.setOffsetShadowPow(20.0f);
            innerShadowParams.setOffsetShadowMix(0.68f);
            innerShadowParams.setNoOffsetShadowRange(7.08f);
            innerShadowParams.setNoOffsetShadowPow(20.0f);
            innerShadowParams.setNoOffsetShadowMix(0.26f);
            INSTANCE = new DecoratorsInnerShadowGroup(NotificationBlurMixColorParams.OLIVE_LINK_SERVER_NAME, "删除，收起按钮内阴影", innerShadowParams, 0.6f);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class NotificationInnerShadowDarkGroup extends NotificationInnerShadowGroup {
        public static final NotificationInnerShadowDarkGroup INSTANCE = new NotificationInnerShadowDarkGroup(NotificationBlurMixColorParams.OLIVE_LINK_SERVER_NAME, "普通通知内阴影暗色", NotificationInnerShadowAdapter.access$createDarkInnerShadowParams(NotificationInnerShadowAdapter.INSTANCE), 0.05f);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class NotificationInnerShadowGroup extends InnerShadowGroup {
        public NotificationInnerShadowGroup(String str, String str2, InnerShadowParams innerShadowParams, float f) {
            super(str, str2, innerShadowParams, f);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class NotificationInnerShadowLightGroup extends NotificationInnerShadowGroup {
        public static final NotificationInnerShadowLightGroup INSTANCE = new NotificationInnerShadowLightGroup(NotificationBlurMixColorParams.OLIVE_LINK_SERVER_NAME, "普通通知内阴影亮色", NotificationInnerShadowAdapter.access$createLightInnerShadowParams(NotificationInnerShadowAdapter.INSTANCE), 0.8f);
    }

    public static final InnerShadowParams access$createDarkInnerShadowParams(NotificationInnerShadowAdapter notificationInnerShadowAdapter) {
        notificationInnerShadowAdapter.getClass();
        return new InnerShadowParams(DEFAULT_SHADOW_COLOR, 1.0f, 0.0f, 0.06f, 36.0f, 0.0f, 24.0f, 1.22f, 1.22f, 20.0f, 0.47f, 36.0f, 20.0f, 0.47f);
    }

    public static final InnerShadowParams access$createKeyguardInnerShadowParams(NotificationInnerShadowAdapter notificationInnerShadowAdapter) {
        notificationInnerShadowAdapter.getClass();
        return new InnerShadowParams(DEFAULT_SHADOW_COLOR, 0.15f, 0.0f, 0.3f, 70.0f, 0.0f, 90.0f, 1.3f, 1.8f, 10.0f, 0.4f, 0.5f, 8.0f, 0.5f);
    }

    public static final InnerShadowParams access$createLightInnerShadowParams(NotificationInnerShadowAdapter notificationInnerShadowAdapter) {
        notificationInnerShadowAdapter.getClass();
        return new InnerShadowParams(DEFAULT_SHADOW_COLOR, 0.15f, 0.0f, 0.4f, 70.0f, 0.0f, 90.0f, 1.3f, 1.8f, 10.0f, 0.4f, 0.5f, 8.0f, 0.5f);
    }

    public final InnerShadowParams createKeyguardStackingInnerShadowParams(int i, NotificationBlurMode notificationBlurMode) {
        KeyguardNotificationInnerShadowGroup keyguardNotificationInnerShadowGroup;
        if (i == 1) {
            keyguardNotificationInnerShadowGroup = notificationBlurMode == NotificationBlurMode.WALLPAPER_A ? KeyguardNotificationAInnerShadowGroup.INSTANCE : KeyguardFirstStackInnerShadowGroup.INSTANCE;
        } else if (i != 2) {
            keyguardNotificationInnerShadowGroup = KeyguardNotificationInnerShadowGroup.INSTANCE;
        } else {
            keyguardNotificationInnerShadowGroup = notificationBlurMode == NotificationBlurMode.WALLPAPER_A ? KeyguardNotificationAInnerShadowGroup.INSTANCE : KeyguardSecondStackInnerShadowGroup.INSTANCE;
        }
        return keyguardNotificationInnerShadowGroup.createInnerShadowParams();
    }

    public final void initOliveLink(Context context) {
        OliveLinkConnector.INSTANCE.start(context, NotificationBlurMixColorParams.OLIVE_LINK_SERVER_NAME);
        NotificationGradientStrokeLineAdapter.INSTANCE.initCapsuleOliveLink(context);
        CapsuleInnerShadowGroup.INSTANCE.createInnerShadowParams();
    }

    public final void registerChangeListener(Object obj, Function0 function0) {
        NotificationInnerShadowLightGroup.INSTANCE.registerChangeListener(obj, function0);
        NotificationInnerShadowDarkGroup.INSTANCE.registerChangeListener(obj, function0);
        KeyguardFirstStackInnerShadowGroup.INSTANCE.registerChangeListener(obj, function0);
        KeyguardSecondStackInnerShadowGroup.INSTANCE.registerChangeListener(obj, function0);
        KeyguardNotificationInnerShadowGroup.INSTANCE.registerChangeListener(obj, function0);
        DecoratorsInnerShadowGroup.INSTANCE.registerChangeListener(obj, function0);
        CapsuleInnerShadowGroup.INSTANCE.registerChangeListener(obj, function0);
    }
}
