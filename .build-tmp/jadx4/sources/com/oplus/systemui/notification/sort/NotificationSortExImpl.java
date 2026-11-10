package com.oplus.systemui.notification.sort;

import android.service.notification.NotificationListenerService;
import android.service.notification.NotificationListenerServiceAddon;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator;
import com.android.systemui.statusbar.notification.collection.listbuilder.NotifSection;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifComparator;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;
import com.oplus.systemui.notification.NotificationSortEx;
import com.oplus.systemui.statusbar.notification.collection.coordinator.OplusMediaCoordinator;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.util.Comparator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSortExImpl extends NotificationSortEx {
    private static final Companion Companion = new Companion();
    private static final String TAG = "NotificationSortExImpl";
    private final Lazy commercialBlackList;
    private final Comparator<NotificationEntry> groupChildrenComparator = NotificationSortExImpl$groupChildrenComparator$1.INSTANCE;
    private final Lazy oplusMediaCoordinator;
    private final Lazy oplusPromoteCoordinator;
    private final Lazy pinTopCoordinator;
    private final NotifPipeline pipeLine;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.sort.NotificationSortExImpl$modifyOrderedSection$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 extends NotifComparator {
        public final int compare(PipelineEntry pipelineEntry, PipelineEntry pipelineEntry2) {
            int iCompare;
            NotificationEntry representativeEntry;
            NotificationListenerService.Ranking ranking;
            NotificationEntry representativeEntry2;
            NotificationListenerService.Ranking ranking2;
            ListEntry listEntryAsListEntry = pipelineEntry.asListEntry();
            Float fValueOf = null;
            Float fValueOf2 = (listEntryAsListEntry == null || (representativeEntry2 = listEntryAsListEntry.getRepresentativeEntry()) == null || (ranking2 = representativeEntry2.getRanking()) == null) ? null : Float.valueOf(NotificationListenerServiceAddon.RankingAddOn.getRankingScore(ranking2));
            ListEntry listEntryAsListEntry2 = pipelineEntry2.asListEntry();
            if (listEntryAsListEntry2 != null && (representativeEntry = listEntryAsListEntry2.getRepresentativeEntry()) != null && (ranking = representativeEntry.getRanking()) != null) {
                fValueOf = Float.valueOf(NotificationListenerServiceAddon.RankingAddOn.getRankingScore(ranking));
            }
            return (fValueOf2 == null || fValueOf == null || (iCompare = Float.compare(fValueOf2.floatValue(), fValueOf.floatValue())) == 0) ? Intrinsics.compare(pipelineEntry.getMListEntryEx().getUpdateTime(), pipelineEntry2.getMListEntryEx().getUpdateTime()) * (-1) : iCompare * (-1);
        }
    }

    public NotificationSortExImpl(NotifPipeline notifPipeline, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4) {
        this.pipeLine = notifPipeline;
        this.oplusMediaCoordinator = lazy;
        this.commercialBlackList = lazy2;
        this.pinTopCoordinator = lazy3;
        this.oplusPromoteCoordinator = lazy4;
    }

    @Override // com.oplus.systemui.notification.NotificationSortEx
    public NotifSection createNotifSection(NotifSectioner notifSectioner, int i) {
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isExpRegion() && Intrinsics.areEqual(notifSectioner, this.commercialBlackList.get())) {
            i = 40;
        }
        return super.createNotifSection(notifSectioner, i);
    }

    @Override // com.oplus.systemui.notification.NotificationSortEx
    public Comparator<NotificationEntry> getGroupChildrenComparator(Comparator<NotificationEntry> comparator) {
        FeatureOption.INSTANCE.getClass();
        return FeatureOption.isExpRegion() ? comparator : this.groupChildrenComparator;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.notification.NotificationSortEx
    public void modifyOrderedSection(ConversationCoordinator conversationCoordinator, HeadsUpCoordinator headsUpCoordinator, List<NotifSectioner> list) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            FeatureOption.INSTANCE.getClass();
            OplusLockIconView$.ExternalSyntheticOutline0.m("modifyOrderedSection:", "NotificationSortExImpl-->", "SystemUi--Notification", FeatureOption.isExpRegion());
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            list.add(0, ((PinTopCoordinator) this.pinTopCoordinator.get()).getFixedPinTopNotifSectioner());
            return;
        }
        list.clear();
        list.add(((PinTopCoordinator) this.pinTopCoordinator.get()).getFixedPinTopNotifSectioner());
        list.add(this.commercialBlackList.get());
        if (!FeatureOption.isFluidDevice()) {
            list.add(((OplusPromoteCoordinator) this.oplusPromoteCoordinator.get()).getOplusPromoteNotifSectioner());
        }
        list.add(headsUpCoordinator.getSectioner());
        list.add(((PinTopCoordinator) this.pinTopCoordinator.get()).getUserPinTopNotifSectioner());
        list.add(conversationCoordinator.getPeopleAlertingSectioner());
        list.add(((OplusMediaCoordinator) this.oplusMediaCoordinator.get()).getMediaNotifSectioner());
        this.pipeLine.setComparators(CollectionsKt__CollectionsJVMKt.listOf(new AnonymousClass2("OplusComparator")));
    }
}
