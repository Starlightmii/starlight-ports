package com.oplus.systemui.separate.guide;

import android.util.Log;
import com.android.systemui.plugins.qs.GuideEvent;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsGuideEventExternalTransfer {
    private final CopyOnWriteArrayList<GuideExportListener> guideEventListeners = new CopyOnWriteArrayList<>();
    public float qsFraction = -1.0f;
    public float notificationFraction = -1.0f;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface GuideExportListener {
    }

    public final void addListener(GuideExportListener guideExportListener) {
        this.guideEventListeners.add(guideExportListener);
    }

    public final float getNotificationFraction() {
        return this.notificationFraction;
    }

    public final float getQsFraction() {
        return this.qsFraction;
    }

    public final void notifyModeSelectGuideDialogDismiss() {
        Iterator<T> it = this.guideEventListeners.iterator();
        while (it.hasNext()) {
            OplusQsGuideEventController oplusQsGuideEventController = ((OplusQsGuideEventController$guideExportListener$2$1) ((GuideExportListener) it.next())).this$0;
            OplusQsGuideEventController.Companion companion = OplusQsGuideEventController.Companion;
            oplusQsGuideEventController.notifyGuideEvent(GuideEvent.SELECT_DIALOG_DISMISS);
        }
    }

    public final void notifyScreenTurnedOff() {
        Iterator<T> it = this.guideEventListeners.iterator();
        while (it.hasNext()) {
            OplusQsGuideEventController oplusQsGuideEventController = ((OplusQsGuideEventController$guideExportListener$2$1) ((GuideExportListener) it.next())).this$0;
            OplusQsGuideEventController.Companion companion = OplusQsGuideEventController.Companion;
            oplusQsGuideEventController.notifyGuideEvent(GuideEvent.SCREEN_OFF);
        }
    }

    public final void notifySeparateNotificationExpandState(float f, boolean z) {
        this.notificationFraction = f;
        Iterator<T> it = this.guideEventListeners.iterator();
        while (it.hasNext()) {
            OplusQsGuideEventController oplusQsGuideEventController = ((OplusQsGuideEventController$guideExportListener$2$1) ((GuideExportListener) it.next())).this$0;
            OplusQsGuideEventController.Companion companion = OplusQsGuideEventController.Companion;
            float f2 = f < 0.33f ? 0.0f : 1.0f;
            if (f2 != oplusQsGuideEventController.notificationFraction) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    float f3 = oplusQsGuideEventController.notificationFraction;
                    StringBuilder sb = new StringBuilder("notifyNotificationExpandState: old: ");
                    sb.append(f3);
                    sb.append(" ,new: [ ");
                    sb.append(z);
                    sb.append(", ");
                    Log.d("SystemUi--QuickSettings", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sb, f, " ]-> ", f2, "OplusQsGuideEventController-->"));
                }
                oplusQsGuideEventController.notificationFraction = f2;
                if (f2 == 1.0f) {
                    oplusQsGuideEventController.notifyGuideEvent(GuideEvent.NOTIFICATION_PANEL_ENTER);
                } else {
                    oplusQsGuideEventController.notifyGuideEvent(GuideEvent.NOTIFICATION_PANEL_EXIT);
                }
            }
        }
    }

    public final void notifySeparateQSExpandState(float f, boolean z) {
        this.qsFraction = f;
        Iterator<T> it = this.guideEventListeners.iterator();
        while (it.hasNext()) {
            OplusQsGuideEventController oplusQsGuideEventController = ((OplusQsGuideEventController$guideExportListener$2$1) ((GuideExportListener) it.next())).this$0;
            OplusQsGuideEventController.Companion companion = OplusQsGuideEventController.Companion;
            float f2 = f < 0.33f ? 0.0f : 1.0f;
            if (f2 != oplusQsGuideEventController.qsFraction) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    float f3 = oplusQsGuideEventController.qsFraction;
                    StringBuilder sb = new StringBuilder("notifyQSExpandState: old: ");
                    sb.append(f3);
                    sb.append(" ,new: [ ");
                    sb.append(z);
                    sb.append(", ");
                    Log.d("SystemUi--QuickSettings", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sb, f, " ]-> ", f2, "OplusQsGuideEventController-->"));
                }
                oplusQsGuideEventController.qsFraction = f2;
                if (f2 == 1.0f) {
                    oplusQsGuideEventController.notifyGuideEvent(GuideEvent.QS_PANEL_ENTER);
                } else {
                    oplusQsGuideEventController.notifyGuideEvent(GuideEvent.QS_PANEL_EXIT);
                }
            }
        }
    }

    public final void removeListener(GuideExportListener guideExportListener) {
        this.guideEventListeners.remove(guideExportListener);
    }

    public final void setNotificationFraction(float f) {
        this.notificationFraction = f;
    }

    public final void setQsFraction(float f) {
        this.qsFraction = f;
    }
}
