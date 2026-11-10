package com.oplus.systemui.notification.interruption;

import android.app.Notification;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionFilter;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionType;
import com.oplus.systemui.notification.envelope.EnvelopeHelper;
import com.oplus.systemui.qs.tiles.EnvelopeTile;
import com.oplus.systemui.statusbar.notification.helper.HeadsUpHelper;
import com.oplusos.systemui.common.feature.FeatureOption;
import kotlin.collections.SetsKt__SetsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PeekEnvelope extends VisualInterruptionFilter {
    private final EnvelopeHelper envelopeHelper;
    private final HeadsUpHelper headsUpHelper;
    private String reason;

    public PeekEnvelope(HeadsUpHelper headsUpHelper, EnvelopeHelper envelopeHelper) {
        super(SetsKt__SetsJVMKt.setOf(VisualInterruptionType.PEEK), "suppress by envelope assistant");
        this.headsUpHelper = headsUpHelper;
        this.envelopeHelper = envelopeHelper;
        this.reason = "suppress by envelope assistant";
    }

    public String getReason() {
        return this.reason;
    }

    public boolean shouldSuppress(NotificationEntry notificationEntry) {
        if (this.envelopeHelper.getEnvelopeAssistantStatus() == 1) {
            EnvelopeHelper envelopeHelper = this.envelopeHelper;
            Notification notification = notificationEntry.getSbn().getNotification();
            envelopeHelper.getClass();
            FeatureOption.INSTANCE.getClass();
            if (!FeatureOption.isLightOs() && (notification.extras.getBoolean(EnvelopeTile.TILE_SPEC, false) || notification.extras.getString("notification_source") != null)) {
                this.reason = "suppress by envelope assistant";
                return true;
            }
        }
        if (!this.headsUpHelper.getEnvelopeShow()) {
            return false;
        }
        this.reason = "suppress by os policy--envelopeShow";
        return true;
    }
}
