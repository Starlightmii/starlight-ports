package com.oplus.systemui.notification.material;

import android.content.Context;
import android.view.ContextThemeWrapper;
import com.coui.appcompat.spotlight.COUISpotLightEffect;
import com.coui.appcompat.spotlight.COUISpotLightEffectDrawable;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.collections.SetsKt__SetsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSpotLightParams {
    private static final String SETTINGS_KEY_OPLUS_DEBUG_NOTIF_SPOT_LIGHT = "oplus_debug_notif_spot_light";
    public volatile boolean tuningCacheInitialized;
    public static final Companion Companion = new Companion();
    private static final Set<NotificationSpotLightParamsKind> LIGHT_THEME_KINDS = SetsKt__SetsKt.setOf(new NotificationSpotLightParamsKind[]{NotificationSpotLightParamsKind.NOTIFICATION_SEEDLING_CARD, NotificationSpotLightParamsKind.NOTIFICATION_SEEDLING_IMMERSIVE_CARD, NotificationSpotLightParamsKind.NOTIFICATION_MENU, NotificationSpotLightParamsKind.NOTIFICATION_CARD});
    private static final SpotLightEffectParams TYPE_TRANSPARENT_MEDIUM_2 = new SpotLightEffectParams(COUISpotLightEffect.SpotLightType.TYPE_TRANSPARENT_MEDIUM_2);
    private static final SpotLightEffectParams TYPE_TRANSPARENT_SMALL_2 = new SpotLightEffectParams(COUISpotLightEffect.SpotLightType.TYPE_TRANSPARENT_SMALL_2);
    private static final SpotLightEffectParams TYPE_TRANSPARENT_SMALL_1 = new SpotLightEffectParams(COUISpotLightEffect.SpotLightType.TYPE_TRANSPARENT_SMALL_1);
    private static final SpotLightEffectParams TYPE_TRANSPARENT_MEDIUM_1 = new SpotLightEffectParams(COUISpotLightEffect.SpotLightType.TYPE_TRANSPARENT_MEDIUM_1);
    private static final SpotLightEffectParams TYPE_TRANSLUCENT_LARGE_2 = new SpotLightEffectParams(COUISpotLightEffect.SpotLightType.TYPE_TRANSLUCENT_LARGE_2);
    private static final SpotLightEffectParams TYPE_OPAQUE_MEDIUM_1 = new SpotLightEffectParams(COUISpotLightEffect.SpotLightType.TYPE_OPAQUE_MEDIUM_1);
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(new NotificationSpotLightParams$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        private final NotificationSpotLightParams getInstance() {
            return (NotificationSpotLightParams) NotificationSpotLightParams.instance$delegate.getValue();
        }

        public final void init(Context context) {
            NotificationSpotLightParams companion = getInstance();
            if (companion.tuningCacheInitialized) {
                return;
            }
            NotificationSpotLightParams$initTuningCache$1 notificationSpotLightParams$initTuningCache$1 = new NotificationSpotLightParams$initTuningCache$1();
            notificationSpotLightParams$initTuningCache$1.this$0 = companion;
            notificationSpotLightParams$initTuningCache$1.$context = context;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnWorkThread(notificationSpotLightParams$initTuningCache$1);
        }

        public final SpotLightEffectParams resolveSpotLightEffectParams(NotificationSpotLightParamsKind notificationSpotLightParamsKind) {
            return NotificationSpotLightParams.access$resolveSpotLightEffectParams(getInstance(), notificationSpotLightParamsKind);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SpotLightEffectParams {
        private final COUISpotLightEffect.SpotLightType spotLightType;

        public SpotLightEffectParams(COUISpotLightEffect.SpotLightType spotLightType) {
            this.spotLightType = spotLightType;
        }

        public final void applyTo(Context context, COUISpotLightEffectDrawable cOUISpotLightEffectDrawable, NotificationSpotLightParamsKind notificationSpotLightParamsKind) {
            if (NotificationSpotLightParams.LIGHT_THEME_KINDS.contains(notificationSpotLightParamsKind)) {
                context = new ContextThemeWrapper(context, 2132019097);
            }
            cOUISpotLightEffectDrawable.setSpotLightType(context, this.spotLightType);
        }

        public final COUISpotLightEffect.SpotLightType component1() {
            return this.spotLightType;
        }

        public final SpotLightEffectParams copy(COUISpotLightEffect.SpotLightType spotLightType) {
            return new SpotLightEffectParams(spotLightType);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof SpotLightEffectParams) && this.spotLightType == ((SpotLightEffectParams) obj).spotLightType;
        }

        public final COUISpotLightEffect.SpotLightType getSpotLightType() {
            return this.spotLightType;
        }

        public final int hashCode() {
            return this.spotLightType.hashCode();
        }

        public String toString() {
            return "SpotLightEffectParams(spotLightType=" + this.spotLightType + ")";
        }
    }

    public static final SpotLightEffectParams access$resolveSpotLightEffectParams(NotificationSpotLightParams notificationSpotLightParams, NotificationSpotLightParamsKind notificationSpotLightParamsKind) {
        notificationSpotLightParams.getClass();
        switch (notificationSpotLightParamsKind.ordinal()) {
            case 0:
                return TYPE_TRANSPARENT_MEDIUM_1;
            case 1:
                return TYPE_OPAQUE_MEDIUM_1;
            case 2:
                return TYPE_TRANSPARENT_MEDIUM_2;
            case 3:
            case 4:
            case 6:
                return TYPE_TRANSPARENT_SMALL_1;
            case 5:
                return TYPE_TRANSPARENT_SMALL_2;
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
            case 9:
                return TYPE_TRANSLUCENT_LARGE_2;
            case 8:
                return TYPE_TRANSPARENT_SMALL_2;
            case 10:
                return TYPE_TRANSPARENT_MEDIUM_1;
            default:
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return null;
        }
    }
}
