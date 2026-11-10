package com.oplus.systemui.statusbar.notification.collection;

import android.content.Context;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.ListAttachState;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilderEx;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.keyguard.clock.WeatherInfoParseHelper$;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.systemui.notification.extend.OplusNotificationEx;
import com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpNotificationInteractor;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.function.Consumer;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ShadeListBuilderExImpl extends ShadeListBuilderEx implements OplusNotificationEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ShadeListBuilderExImpl";
    private final Context context;
    private Consumer<String> dndVisualEffectsInvalidateAction;
    private final Lazy oplusHeadsUpInteractor;
    private final Lazy oplusPanoramicAodRepository;
    private NotificationPanelViewController panelViewController;
    private final OplusPanoramicAodRepository.PanoramicAodProgressChangeListener panoramicAodObserver;
    private final Lazy wakefulnessLifecycle;
    private final Lazy zenModeController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ShadeListBuilderExImpl(Context context, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4) {
        this.context = context;
        this.wakefulnessLifecycle = lazy;
        this.zenModeController = lazy2;
        this.oplusHeadsUpInteractor = lazy3;
        this.oplusPanoramicAodRepository = lazy4;
        ShadeListBuilderExImpl$panoramicAodObserver$1 shadeListBuilderExImpl$panoramicAodObserver$1 = new ShadeListBuilderExImpl$panoramicAodObserver$1();
        shadeListBuilderExImpl$panoramicAodObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.panoramicAodObserver = shadeListBuilderExImpl$panoramicAodObserver$1;
    }

    public void dealLabel(GroupEntry groupEntry) {
        ExpandableNotificationRow row;
        int i = 0;
        String key = null;
        for (NotificationEntry notificationEntry : groupEntry.getChildren()) {
            int i2 = i + 1;
            OplusNotificationEntryEx ex = notificationEntry.getEx();
            if ((ex instanceof OplusNotificationEntryExImpl) && i < 8 && key == null) {
                OplusNotificationEntryExImpl oplusNotificationEntryExImpl = (OplusNotificationEntryExImpl) ex;
                if (oplusNotificationEntryExImpl.getOplusLabel() != null) {
                    NotificationEntry summary = groupEntry.getSummary();
                    OplusNotificationEntryEx ex2 = summary != null ? summary.getEx() : null;
                    if (ex2 instanceof OplusNotificationEntryExImpl) {
                        ((OplusNotificationEntryExImpl) ex2).setOplusLabel(oplusNotificationEntryExImpl.getOplusLabel());
                    }
                    key = notificationEntry.getKey();
                }
            }
            i = i2;
        }
        NotificationEntry summary2 = groupEntry.getSummary();
        if (summary2 != null && (row = summary2.getRow()) != null) {
            row.setLabelKey(key);
        }
        if (key != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("dealLabel:", key, "ShadeListBuilderExImpl-->", "SystemUi--Notification");
        }
    }

    public final boolean isHeadsUpAnimatingAway() {
        ExpandableNotificationRow row;
        NotificationEntry notificationEntryHeadsUpEntry = ((OplusHeadsUpNotificationInteractor) this.oplusHeadsUpInteractor.get()).headsUpEntry();
        return (notificationEntryHeadsUpEntry == null || (row = notificationEntryHeadsUpEntry.getRow()) == null || !row.mHeadsupDisappearRunning) ? false : true;
    }

    public void markSuppressPromotionAnimations(GroupEntry groupEntry) {
        List rawChildren = groupEntry.getRawChildren();
        if (rawChildren.size() == 1) {
            NotificationEntry summary = groupEntry.getSummary();
            if (summary != null) {
                OplusNotificationEntryEx oplusNotificationEntryEx = summary.mNotificationEntryEx;
                boolean suppressPromotionAnimations = oplusNotificationEntryEx != null ? oplusNotificationEntryEx.getSuppressPromotionAnimations() : false;
                ListAttachState attachState = summary.getAttachState();
                if (attachState != null) {
                    attachState.setSuppressPromotionAnimations(suppressPromotionAnimations);
                }
                OplusNotificationEntryEx oplusNotificationEntryEx2 = summary.mNotificationEntryEx;
                if (oplusNotificationEntryEx2 != null) {
                    oplusNotificationEntryEx2.setSuppressPromotionAnimations(false);
                }
            }
            NotificationEntry notificationEntry = (NotificationEntry) CollectionsKt___CollectionsKt.firstOrNull(rawChildren);
            if (notificationEntry != null) {
                OplusNotificationEntryEx oplusNotificationEntryEx3 = notificationEntry.mNotificationEntryEx;
                boolean suppressPromotionAnimations2 = oplusNotificationEntryEx3 != null ? oplusNotificationEntryEx3.getSuppressPromotionAnimations() : false;
                ListAttachState attachState2 = notificationEntry.getAttachState();
                if (attachState2 != null) {
                    attachState2.setSuppressPromotionAnimations(suppressPromotionAnimations2);
                }
                OplusNotificationEntryEx oplusNotificationEntryEx4 = notificationEntry.mNotificationEntryEx;
                if (oplusNotificationEntryEx4 != null) {
                    oplusNotificationEntryEx4.setSuppressPromotionAnimations(false);
                }
            }
        }
    }

    public void setDndVisualEffectsInvalidateAction(Consumer<String> consumer) {
        super.setDndVisualEffectsInvalidateAction(consumer);
        this.dndVisualEffectsInvalidateAction = consumer;
        ((OplusPanoramicAodRepository) this.oplusPanoramicAodRepository.get()).removeCallback(this.panoramicAodObserver);
        ((OplusPanoramicAodRepository) this.oplusPanoramicAodRepository.get()).addCallback(this.panoramicAodObserver);
    }

    public void setNotificationPanelViewController(NotificationPanelViewController notificationPanelViewController) {
        this.panelViewController = notificationPanelViewController;
    }
}
