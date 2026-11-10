package com.oplus.systemui.statusbar.notification.collection.render;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.notification.NotificationSectionsFeatureManager;
import com.android.systemui.statusbar.notification.collection.BundleEntry;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.render.BundleBarn;
import com.android.systemui.statusbar.notification.collection.render.MediaContainerController;
import com.android.systemui.statusbar.notification.collection.render.NodeSpecImpl;
import com.android.systemui.statusbar.notification.collection.render.NotifViewBarn;
import com.android.systemui.statusbar.notification.collection.render.OplusNodeSpecBuilderEx;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowController;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowControllerManager;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNodeSpecBuilderExImpl extends OplusNodeSpecBuilderEx {
    private OplusCustomRowControllerManager oplusCustomContainerManager;

    public OplusNodeSpecBuilderExImpl(Context context) {
    }

    public static NodeSpecImpl buildNotifNode(NodeSpecImpl nodeSpecImpl, PipelineEntry pipelineEntry, BundleBarn bundleBarn, NotifViewBarn notifViewBarn) throws Exception {
        if (pipelineEntry instanceof NotificationEntry) {
            return new NodeSpecImpl(nodeSpecImpl, notifViewBarn.requireNodeController(pipelineEntry));
        }
        if (!(pipelineEntry instanceof GroupEntry)) {
            if (!(pipelineEntry instanceof BundleEntry)) {
                throw new Exception("Unexpected entry: " + pipelineEntry);
            }
            BundleEntry bundleEntry = (BundleEntry) pipelineEntry;
            NodeSpecImpl nodeSpecImpl2 = new NodeSpecImpl(nodeSpecImpl, bundleBarn.requireNodeController(bundleEntry));
            Iterator it = bundleEntry.getChildren().iterator();
            while (it.hasNext()) {
                nodeSpecImpl2.getChildren().add(buildNotifNode(nodeSpecImpl2, (ListEntry) it.next(), bundleBarn, notifViewBarn));
            }
            return nodeSpecImpl2;
        }
        GroupEntry groupEntry = (GroupEntry) pipelineEntry;
        NotificationEntry summary = groupEntry.getSummary();
        if (summary == null) {
            Path$.ExternalSyntheticBUOutline0.m$1("Required value was null.");
            return null;
        }
        NodeSpecImpl nodeSpecImpl3 = new NodeSpecImpl(nodeSpecImpl, notifViewBarn.requireNodeController(summary));
        for (NotificationEntry notificationEntry : groupEntry.getChildren()) {
            List children = nodeSpecImpl3.getChildren();
            notificationEntry.getClass();
            children.add(buildNotifNode(nodeSpecImpl3, notificationEntry, bundleBarn, notifViewBarn));
        }
        return nodeSpecImpl3;
    }

    public boolean buildNodeSpec(NodeSpecImpl nodeSpecImpl, List<? extends PipelineEntry> list, Set<? extends PipelineEntry> set, MediaContainerController mediaContainerController, NotificationSectionsFeatureManager notificationSectionsFeatureManager, BundleBarn bundleBarn, NotifViewBarn notifViewBarn) throws Exception {
        if (this.oplusCustomContainerManager == null) {
            this.oplusCustomContainerManager = (OplusCustomRowControllerManager) DependencyEx.sDependency.getDependency(OplusCustomRowControllerManager.class);
        }
        OplusCustomRowControllerManager oplusCustomRowControllerManager = this.oplusCustomContainerManager;
        List<OplusCustomRowController> oplusCustomRowControllerList = oplusCustomRowControllerManager != null ? oplusCustomRowControllerManager.getOplusCustomRowControllerList() : null;
        if (oplusCustomRowControllerList != null) {
            Iterator<T> it = oplusCustomRowControllerList.iterator();
            while (it.hasNext()) {
                nodeSpecImpl.getChildren().add(new NodeSpecImpl(nodeSpecImpl, (OplusCustomRowController) it.next()));
            }
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            return false;
        }
        if (notificationSectionsFeatureManager.isMediaControlsEnabled()) {
            nodeSpecImpl.getChildren().add(new NodeSpecImpl(nodeSpecImpl, mediaContainerController));
        }
        Iterator<? extends PipelineEntry> it2 = list.iterator();
        while (it2.hasNext()) {
            GroupEntry groupEntry = (PipelineEntry) it2.next();
            if (!(groupEntry instanceof GroupEntry) || groupEntry.getSummary() != null) {
                NodeSpecImpl nodeSpecImplBuildNotifNode = buildNotifNode(nodeSpecImpl, groupEntry, bundleBarn, notifViewBarn);
                if (set.contains(groupEntry)) {
                    nodeSpecImplBuildNotifNode.setKeepGone(true);
                }
                nodeSpecImpl.getChildren().add(nodeSpecImplBuildNotifNode);
            }
        }
        return true;
    }

    public void printNotifList(List<? extends PipelineEntry> list) {
        NotificationEntry representativeEntry;
        for (PipelineEntry pipelineEntry : list) {
            ListEntry listEntryAsListEntry = pipelineEntry.asListEntry();
            StatusBarNotification sbn = (listEntryAsListEntry == null || (representativeEntry = listEntryAsListEntry.getRepresentativeEntry()) == null) ? null : representativeEntry.getSbn();
            if (sbn != null) {
                LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, "NodeSpecBuilder", " section:" + pipelineEntry.getSection() + " packageName:" + sbn.getPackageName() + " id:" + sbn.getId());
            }
        }
    }
}
