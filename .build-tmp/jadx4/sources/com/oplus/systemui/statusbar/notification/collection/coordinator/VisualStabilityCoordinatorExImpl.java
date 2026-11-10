package com.oplus.systemui.statusbar.notification.collection.coordinator;

import android.content.Context;
import android.os.Handler;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.notification.collection.CapsuleAnimatingStateListener;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.VisualStabilityCoordinatorEx;
import com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator$;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifStabilityManager;
import com.android.systemui.statusbar.notification.collection.render.GroupMembershipManager;
import com.android.systemui.statusbar.notification.collection.render.GroupMembershipManagerImpl;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.aod.proxy.AodEnableEnergySavingObserver;
import com.oplus.systemui.notification.aod.OplusPanoramicAodCoreStartable;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopUtils;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class VisualStabilityCoordinatorExImpl extends VisualStabilityCoordinatorEx {
    public static final Companion Companion = new Companion();
    public boolean capsuleAnimating;
    private CapsuleAnimatingStateListener capsuleListener;
    private final Context context;
    private final DelayableExecutor delayableExecutor;
    private final OplusPanoramicAodRepository panoramicAodRepository;
    private final ArrayMap<View, Function0> viewGroupChangesAllowedCallbacks = new ArrayMap<>();
    private final Lazy groupMembershipManager$delegate = LazyKt__LazyJVMKt.lazy(new VisualStabilityCoordinatorExImpl$$ExternalSyntheticLambda1());
    private final Map<String, Runnable> entriesThatCanReOrder = new LinkedHashMap();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinatorExImpl$temporarilyAllowReOrder$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ String $notificationTopKey;
        public /* synthetic */ VisualStabilityCoordinatorExImpl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.entriesThatCanReOrder.remove(this.$notificationTopKey);
        }
    }

    public VisualStabilityCoordinatorExImpl(DelayableExecutor delayableExecutor, OplusPanoramicAodRepository oplusPanoramicAodRepository, Context context) {
        this.delayableExecutor = delayableExecutor;
        this.panoramicAodRepository = oplusPanoramicAodRepository;
        this.context = context;
    }

    private final GroupMembershipManager getGroupMembershipManager() {
        return (GroupMembershipManager) this.groupMembershipManager$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCapsuleAnimating(boolean z) {
        if (z != this.capsuleAnimating) {
            this.capsuleAnimating = z;
            VisualStabilityCoordinator$.ExternalSyntheticLambda1 externalSyntheticLambda1 = this.capsuleListener;
            if (externalSyntheticLambda1 != null) {
                externalSyntheticLambda1.f$0.updateAllowedStates("capsuleAnimating", z, false);
            }
        }
    }

    public void addViewGroupChangeAllowedCallback(View view, Function0 function0) {
        if (this.viewGroupChangesAllowedCallbacks.containsKey(view)) {
            return;
        }
        this.viewGroupChangesAllowedCallbacks.put(view, function0);
    }

    public boolean getStopUpdateUiWhenSleep() {
        OplusPanoramicAodRepository oplusPanoramicAodRepository = this.panoramicAodRepository;
        return (((OplusPanoramicAodCoreStartable) oplusPanoramicAodRepository).isPanoramicAod && oplusPanoramicAodRepository.getShouldUpdateUi()) ? false : true;
    }

    public void hadViewGroupChangeAllowedCallback(View view) {
        this.viewGroupChangesAllowedCallbacks.containsKey(view);
    }

    public final boolean isCapsuleDisallowUpdate(boolean z) {
        return !z && this.capsuleAnimating;
    }

    public boolean isEntrySectionReorderAllowed(ListEntry listEntry) {
        StatusBarNotification sbn;
        String packageName;
        NotificationEntry representativeEntry;
        StatusBarNotification sbn2;
        NotificationEntry representativeEntry2 = listEntry.getRepresentativeEntry();
        if (representativeEntry2 == null || (sbn = representativeEntry2.getSbn()) == null || (packageName = sbn.getPackageName()) == null || (representativeEntry = listEntry.getRepresentativeEntry()) == null || (sbn2 = representativeEntry.getSbn()) == null) {
            return false;
        }
        return this.entriesThatCanReOrder.containsKey(NotificationTopUtils.getNotificationTopKey(packageName, Integer.valueOf(UserHandle.getUserId(sbn2.getUid()))));
    }

    public boolean isOplusViewGroupChangeAllow(boolean z, NotificationEntry notificationEntry, NotifStabilityManager notifStabilityManager) {
        View row = notificationEntry.getRow();
        boolean zIsChildInGroup = ((GroupMembershipManagerImpl) getGroupMembershipManager()).isChildInGroup(row.getEntryAdapter());
        if (!z || !zIsChildInGroup || !((ExpandableNotificationRow) row).mHeadsupDisappearRunning || ((ExpandableNotificationRow) row).mIsHeadsUp) {
            removeViewGroupChangeAllowed(row);
            return true;
        }
        VisualStabilityCoordinatorExImpl$$ExternalSyntheticLambda0 visualStabilityCoordinatorExImpl$$ExternalSyntheticLambda0 = new VisualStabilityCoordinatorExImpl$$ExternalSyntheticLambda0();
        visualStabilityCoordinatorExImpl$$ExternalSyntheticLambda0.f$1 = notifStabilityManager;
        VarHandle.storeStoreFence();
        addViewGroupChangeAllowedCallback(row, visualStabilityCoordinatorExImpl$$ExternalSyntheticLambda0);
        return false;
    }

    public boolean isPanelExpanded(ShadeInteractor shadeInteractor, boolean z, boolean z2) {
        StateFlow stateFlowIsAnyExpanded;
        return (!z2 || shadeInteractor == null || (stateFlowIsAnyExpanded = shadeInteractor.isAnyExpanded()) == null) ? z : ((Boolean) stateFlowIsAnyExpanded.getValue()).booleanValue();
    }

    public final boolean isPanoramicAodShowAllDay() {
        OplusPanoramicAodRepository oplusPanoramicAodRepository = this.panoramicAodRepository;
        return ((OplusPanoramicAodCoreStartable) oplusPanoramicAodRepository).isPanoramicAod && oplusPanoramicAodRepository.getShouldUpdateUi() && SmoothTransitionController.Companion.getInstance(this.context).isSupportPanoramicAllDay && !AodEnableEnergySavingObserver.Companion.getInstance().getAodEnableEnergySavingOpen();
    }

    public void notifyViewGroupChangeAllowed(View view) {
        Function0 function0Remove = this.viewGroupChangesAllowedCallbacks.remove(view);
        if (function0Remove != null) {
            function0Remove.invoke();
        }
    }

    public void removeViewGroupChangeAllowed(View view) {
        this.viewGroupChangesAllowedCallbacks.remove(view);
    }

    public void setCapsuleAnimatingState(boolean z) {
        Log.i("RowAppearanceCoordinatorExImpl", "setCapsuleAnimatingState: old=" + this.capsuleAnimating + " new=" + z);
        setCapsuleAnimating(z);
        if (z) {
            Handler mainHandler = SystemUIThreadUtil.getMainHandler();
            setCapsuleAnimatingState.1 r0 = new setCapsuleAnimatingState.1();
            r0.this$0 = this;
            VarHandle.storeStoreFence();
            mainHandler.postDelayed(r0, 1000L);
        }
    }

    public void setCapsuleAnimatingStateListener(CapsuleAnimatingStateListener capsuleAnimatingStateListener) {
        this.capsuleListener = capsuleAnimatingStateListener;
    }

    public void temporarilyAllowReOrder(String str, long j) {
        Runnable runnable;
        if (this.entriesThatCanReOrder.containsKey(str) && (runnable = this.entriesThatCanReOrder.get(str)) != null) {
            runnable.run();
        }
        Map<String, Runnable> map = this.entriesThatCanReOrder;
        DelayableExecutor delayableExecutor = this.delayableExecutor;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        anonymousClass1.$notificationTopKey = str;
        VarHandle.storeStoreFence();
        map.put(str, delayableExecutor.executeAtTime(anonymousClass1, j + 500));
    }
}
