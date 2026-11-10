package com.oplus.systemui.statusbar.notification.collection.coordinator;

import android.util.Log;
import com.android.systemui.Dependency;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.media.controls.util.MediaFeatureFlag;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.chips.notification.domain.interactor.StatusBarNotificationChipsInteractor;
import com.android.systemui.statusbar.chips.uievents.StatusBarChipsUiEventLogger;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinatorLogger;
import com.android.systemui.statusbar.notification.collection.coordinator.HunMutator;
import com.android.systemui.statusbar.notification.collection.provider.LaunchFullScreenIntentProvider;
import com.android.systemui.statusbar.notification.collection.render.NodeController;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.headsup.PinnedStatus;
import com.android.systemui.statusbar.notification.interruption.HeadsUpViewBinder;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionLogger;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.notification.row.NotificationActionClickManager;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.time.SystemClock;
import com.oplus.systemui.notification.aonaccess.AonAuthStarter;
import com.oplus.systemui.notification.base.OplusPanelViewInteractor;
import com.oplus.systemui.notification.icdf.IcdfHeadsUpDecisionRecorder;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper;
import com.oplus.systemui.statusbar.notification.collection.OplusNotificationEntryExImpl;
import com.oplus.systemui.statusbar.notification.collection.inflation.OplusHeadsUpAvalancheControl;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnCommon;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplus.wrapper.os.UserHandle;
import com.oplusos.systemui.common.observer.FocusModeSettingsObserver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.OplusMultiAppUtils;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHeadsUpCoordinator extends HeadsUpCoordinator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "HeadsUpCoordinator";
    private final Lazy aonAuthStarter;
    private final CoroutineScope applicationScope;
    public Lazy fullScreenBannerHelper;
    private final Lazy icdfHeadsUpDecisionRecorder;
    private final DelayableExecutor mExecutor;
    private final HeadsUpManager mHeadsUpManager;
    private final HeadsUpViewBinder mHeadsUpViewBinder;
    private final NodeController mIncomingHeaderController;
    private final VisualInterruptionDecisionLogger mInterruptLogger;
    private final LaunchFullScreenIntentProvider mLaunchFullScreenIntentProvider;
    private final HeadsUpCoordinatorLogger mLogger;
    private final NotificationRemoteInputManager mRemoteInputManager;
    private final SystemClock mSystemClock;
    private final VisualInterruptionDecisionProvider mVisualInterruptionDecisionProvider;
    private final MediaFeatureFlag mediaFeatureFlag;
    public final boolean mediaFeatureFlagEnabled;
    private final NotifCollection notifCollection;
    private final NotificationActionClickManager notificationActionClickManager;
    private final NotificationDependOnCommon notificationDependOnCommon;
    private final OplusHeadsUpAvalancheControl oplusAvalanche;
    private final OplusPanelViewInteractor oplusPanelViewInteractor;
    private NotificationEntry preEntry;
    private final StatusBarChipsUiEventLogger statusBarChipsUiEventLogger;
    private final StatusBarNotificationChipsInteractor statusBarNotificationChipsInteractor;
    private final StatusBarStateControllerImpl statusBarStateController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.collection.coordinator.OplusHeadsUpCoordinator$removeHeadsUpWhenNewComing$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Predicate {
        public /* synthetic */ NotificationEntry $entry;

        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return !Intrinsics.areEqual(((NotificationEntry) obj).getKey(), this.$entry.getKey());
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.collection.coordinator.OplusHeadsUpCoordinator$removeHeadsUpWhenNewComing$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Consumer {
        public /* synthetic */ NotificationEntry $entry;
        public /* synthetic */ List $needRemoveList;
        public /* synthetic */ OplusHeadsUpCoordinator this$0;

        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            NotificationEntry notificationEntry = (NotificationEntry) obj;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("removeHeadsUpWhenNewComing, key=", notificationEntry.getEx().getEncryptionKey(), "HeadsUpCoordinator-->", "SystemUi--Notification");
            }
            if (notificationEntry.mRemoteEditImeVisible) {
                return;
            }
            NotificationEntry notificationEntry2 = this.$entry;
            int userId = ((UserTracker) Dependency.sDependency.getDependencyInner(UserTracker.class)).getUserId();
            int userId2 = notificationEntry2.getSbn().getUserId();
            if (userId2 == userId || ((userId == UserHandle.USER_SYSTEM && OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(userId2))) || userId2 == UserHandle.USER_ALL)) {
                this.$needRemoveList.add(notificationEntry.getKey());
            }
        }
    }

    public static boolean $r8$lambda$8uGWlZ4QqxTilioZZi1BT2YTVD0(OplusHeadsUpCoordinator oplusHeadsUpCoordinator) {
        LinkedHashMap mPostedEntries = oplusHeadsUpCoordinator.getMPostedEntries();
        if (!mPostedEntries.isEmpty()) {
            Iterator it = mPostedEntries.entrySet().iterator();
            while (it.hasNext()) {
                if (((HeadsUpCoordinator.PostedEntry) ((Map.Entry) it.next()).getValue()).getShouldHeadsUpEver()) {
                    return false;
                }
            }
        }
        return oplusHeadsUpCoordinator.oplusAvalanche.getNext() == null && oplusHeadsUpCoordinator.getMEntriesBindingUntil().isEmpty();
    }

    public OplusHeadsUpCoordinator(CoroutineScope coroutineScope, HeadsUpCoordinatorLogger headsUpCoordinatorLogger, VisualInterruptionDecisionLogger visualInterruptionDecisionLogger, SystemClock systemClock, NotifCollection notifCollection, HeadsUpManager headsUpManager, HeadsUpViewBinder headsUpViewBinder, VisualInterruptionDecisionProvider visualInterruptionDecisionProvider, NotificationRemoteInputManager notificationRemoteInputManager, NotificationActionClickManager notificationActionClickManager, LaunchFullScreenIntentProvider launchFullScreenIntentProvider, StatusBarNotificationChipsInteractor statusBarNotificationChipsInteractor, StatusBarChipsUiEventLogger statusBarChipsUiEventLogger, NodeController nodeController, DelayableExecutor delayableExecutor, StatusBarStateControllerImpl statusBarStateControllerImpl, NotificationDependOnCommon notificationDependOnCommon, OplusHeadsUpAvalancheControl oplusHeadsUpAvalancheControl, MediaFeatureFlag mediaFeatureFlag, Lazy lazy, OplusPanelViewInteractor oplusPanelViewInteractor, Lazy lazy2) {
        super(coroutineScope, headsUpCoordinatorLogger, visualInterruptionDecisionLogger, systemClock, notifCollection, headsUpManager, headsUpViewBinder, visualInterruptionDecisionProvider, notificationRemoteInputManager, notificationActionClickManager, launchFullScreenIntentProvider, statusBarNotificationChipsInteractor, statusBarChipsUiEventLogger, nodeController, delayableExecutor);
        this.applicationScope = coroutineScope;
        this.mLogger = headsUpCoordinatorLogger;
        this.mInterruptLogger = visualInterruptionDecisionLogger;
        this.mSystemClock = systemClock;
        this.notifCollection = notifCollection;
        this.mHeadsUpManager = headsUpManager;
        this.mHeadsUpViewBinder = headsUpViewBinder;
        this.mVisualInterruptionDecisionProvider = visualInterruptionDecisionProvider;
        this.mRemoteInputManager = notificationRemoteInputManager;
        this.notificationActionClickManager = notificationActionClickManager;
        this.mLaunchFullScreenIntentProvider = launchFullScreenIntentProvider;
        this.statusBarNotificationChipsInteractor = statusBarNotificationChipsInteractor;
        this.statusBarChipsUiEventLogger = statusBarChipsUiEventLogger;
        this.mIncomingHeaderController = nodeController;
        this.mExecutor = delayableExecutor;
        this.statusBarStateController = statusBarStateControllerImpl;
        this.notificationDependOnCommon = notificationDependOnCommon;
        this.oplusAvalanche = oplusHeadsUpAvalancheControl;
        this.mediaFeatureFlag = mediaFeatureFlag;
        this.aonAuthStarter = lazy;
        this.oplusPanelViewInteractor = oplusPanelViewInteractor;
        this.icdfHeadsUpDecisionRecorder = lazy2;
        this.mediaFeatureFlagEnabled = mediaFeatureFlag.getEnabled();
        AonAuthStarter aonAuthStarter = (AonAuthStarter) lazy.get();
        OplusHeadsUpCoordinator$$ExternalSyntheticLambda0 oplusHeadsUpCoordinator$$ExternalSyntheticLambda0 = new OplusHeadsUpCoordinator$$ExternalSyntheticLambda0(0);
        oplusHeadsUpCoordinator$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        aonAuthStarter.setHeadsUpCoordinatorIsEmpty(oplusHeadsUpCoordinator$$ExternalSyntheticLambda0);
        OplusHeadsUpCoordinator$$ExternalSyntheticLambda0 oplusHeadsUpCoordinator$$ExternalSyntheticLambda1 = new OplusHeadsUpCoordinator$$ExternalSyntheticLambda0(1);
        oplusHeadsUpCoordinator$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        oplusHeadsUpAvalancheControl.setNextActionChanged(oplusHeadsUpCoordinator$$ExternalSyntheticLambda1);
    }

    public void bindForAsyncHeadsUp(HeadsUpCoordinator.PostedEntry postedEntry) {
        postedEntry.getEntry().getEx().setShowHeadsUp(true);
        NotificationEntry entry = postedEntry.getEntry();
        if (!FocusModeSettingsObserver.getsInstance().mIsFocusMode) {
            if (((FullScreenBannerHelper) getFullScreenBannerHelper().get()).isSimpleBannerEnable(entry, entry.getSbn().getNotification().extras)) {
                if (!((OplusNotificationEntryExImpl) entry.mNotificationEntryEx).isMcsVoipNotification) {
                    boolean z = entry.getSbn().getNotification().fullScreenIntent != null;
                    if (!this.mHeadsUpManager.isSnoozed(entry.getSbn()) || z) {
                        if (this.mediaFeatureFlagEnabled && MediaDataManager.Companion.isMediaNotification(entry.getSbn())) {
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                Log.d("SystemUi--Notification", "HeadsUpCoordinator-->suppress FullScreenBanner due isMediaNotification");
                            }
                        }
                    } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "HeadsUpCoordinator-->supress FullScreenBanner due isSnoozed");
                    }
                } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "HeadsUpCoordinator-->supress FullScreenBanner due isMcsVoipNotification");
                }
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("showFullScreenBanner:false, isFocusMode=", ",isSimpleBannerEnable=", "HeadsUpCoordinator-->", FocusModeSettingsObserver.getsInstance().mIsFocusMode, ((FullScreenBannerHelper) getFullScreenBannerHelper().get()).isSimpleBannerEnable(entry, entry.getSbn().getNotification().extras)));
            }
            this.preEntry = postedEntry.getEntry();
            boolean z2 = postedEntry.isFromUserAction;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "HeadsUpCoordinator-->bindForAsyncHeadsUp:show heads up");
            }
            OplusHeadsUpCoordinator$bindForAsyncHeadsUp$runnable$1 oplusHeadsUpCoordinator$bindForAsyncHeadsUp$runnable$1 = new OplusHeadsUpCoordinator$bindForAsyncHeadsUp$runnable$1();
            oplusHeadsUpCoordinator$bindForAsyncHeadsUp$runnable$1.this$0 = this;
            oplusHeadsUpCoordinator$bindForAsyncHeadsUp$runnable$1.$posted = postedEntry;
            oplusHeadsUpCoordinator$bindForAsyncHeadsUp$runnable$1.$isPinnedByUser = z2;
            VarHandle.storeStoreFence();
            this.oplusAvalanche.show(postedEntry.getEntry(), oplusHeadsUpCoordinator$bindForAsyncHeadsUp$runnable$1);
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "HeadsUpCoordinator-->showFullScreenBanner:");
        }
        NotificationEntry notificationEntry = this.preEntry;
        if (notificationEntry != null) {
            this.mHeadsUpViewBinder.abortBindCallback(notificationEntry);
        }
        removeHeadsUpWhenNewComing(entry);
        ((FullScreenBannerHelper) getFullScreenBannerHelper().get()).showBanner(entry);
        entry.interruption = true;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "HeadsUpCoordinator-->not bindForAsyncHeadsUp, because showFullScreenBanner");
        }
    }

    public void cancelHeadsUpBind(NotificationEntry notificationEntry) {
        super.cancelHeadsUpBind(notificationEntry);
        tryCloseAonCamera();
    }

    public final Lazy getFullScreenBannerHelper() {
        Lazy lazy = this.fullScreenBannerHelper;
        if (lazy != null) {
            return lazy;
        }
        return null;
    }

    public void handlePostedEntry(HeadsUpCoordinator.PostedEntry postedEntry, HunMutator hunMutator, String str) {
        PinnedStatus pinnedStatus;
        this.mLogger.logPostedEntryWillEvaluate(postedEntry, str);
        if (postedEntry.getWasAdded()) {
            if (postedEntry.getShouldHeadsUpEver()) {
                bindForAsyncHeadsUp(postedEntry);
                return;
            }
            return;
        }
        if (!postedEntry.isHeadsUpAlready()) {
            if (postedEntry.getShouldHeadsUpEver() && postedEntry.getShouldHeadsUpAgain()) {
                bindForAsyncHeadsUp(postedEntry);
                return;
            }
            return;
        }
        boolean shouldHeadsUpEver = postedEntry.getShouldHeadsUpEver();
        boolean z = postedEntry.isHeadsUpEntry;
        if (!shouldHeadsUpEver) {
            if (z) {
                hunMutator.removeNotification(postedEntry.getKey(), false);
                return;
            } else {
                cancelHeadsUpBind(postedEntry.getEntry());
                return;
            }
        }
        if (z) {
            if (postedEntry.getShouldHeadsUpAgain()) {
                pinnedStatus = postedEntry.isFromUserAction ? PinnedStatus.PinnedByUser : PinnedStatus.PinnedBySystem;
            } else {
                pinnedStatus = PinnedStatus.NotPinned;
            }
            hunMutator.updateNotification(postedEntry.getKey(), pinnedStatus);
        }
    }

    public boolean isGoingToShowHunNoRetract(PipelineEntry pipelineEntry) {
        NotificationEntry representativeEntry;
        ListEntry listEntryAsListEntry = pipelineEntry.asListEntry();
        if (listEntryAsListEntry == null || (representativeEntry = listEntryAsListEntry.getRepresentativeEntry()) == null) {
            return false;
        }
        return super.isGoingToShowHunNoRetract(representativeEntry);
    }

    public void onHeadsUpViewBound(NotificationEntry notificationEntry, boolean z) {
        if (!((Boolean) this.oplusPanelViewInteractor.isVisibility().getValue()).booleanValue()) {
            removeHeadsUpWhenNewComing(notificationEntry);
            super.onHeadsUpViewBound(notificationEntry, z);
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("skip onHeadsUpViewBound, panel expanded, key=", notificationEntry.getEx().getEncryptionKey(), "HeadsUpCoordinator-->", "SystemUi--Notification");
        }
        getMEntriesBindingUntil().remove(notificationEntry.getKey());
        this.mHeadsUpViewBinder.unbindHeadsUpView(notificationEntry);
        this.oplusAvalanche.endEntry(notificationEntry);
        notificationEntry.getEx().setShowHeadsUp(false);
    }

    public void postNotificationAdd(boolean z, NotificationEntry notificationEntry) {
        ((IcdfHeadsUpDecisionRecorder) this.icdfHeadsUpDecisionRecorder.get()).recordIfAwake(z, notificationEntry);
        if (!z) {
            NotificationStatisticUtil.onNotificationPosted(this.notificationDependOnCommon.getApplicationContext(), notificationEntry, this.statusBarStateController.getState());
            return;
        }
        ((AonAuthStarter) this.aonAuthStarter.get()).openCamera("postNotificationAdd", null);
        NotificationStatisticUtil.onNotificationPosted(this.notificationDependOnCommon.getApplicationContext(), notificationEntry, 1000);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "HeadsUpCoordinator-->expose: postNotificationAdd ");
        }
        ((OplusNotificationEntryExImpl) notificationEntry.mNotificationEntryEx).onExposure(2);
    }

    public void postNotificationRemove(NotificationEntry notificationEntry) {
        tryCloseAonCamera();
        FullScreenBannerHelper fullScreenBannerHelper = (FullScreenBannerHelper) getFullScreenBannerHelper().get();
        if (fullScreenBannerHelper.mLastNotificationEntry != null && notificationEntry != null && Integer.valueOf(notificationEntry.getSbn().getId()).equals(Integer.valueOf(fullScreenBannerHelper.mLastNotificationEntry.getSbn().getId())) && fullScreenBannerHelper.mIsBannerShowing) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "FullScreenHelper", "FullScreenBannerHelper -> onNotificationRemoved");
            fullScreenBannerHelper.hideBannerImmediately();
        }
        this.oplusAvalanche.endEntry(notificationEntry);
        if (Intrinsics.areEqual(this.preEntry, notificationEntry)) {
            this.preEntry = null;
        }
    }

    public void postNotificationUpdated(Boolean bool, boolean z, boolean z2, boolean z3, NotificationEntry notificationEntry) {
        Integer numValueOf = 1000;
        ((IcdfHeadsUpDecisionRecorder) this.icdfHeadsUpDecisionRecorder.get()).recordIfAwake(z, notificationEntry);
        Boolean bool2 = Boolean.TRUE;
        if (Intrinsics.areEqual(bool, bool2) && !z) {
            tryCloseAonCamera();
        } else if (!Intrinsics.areEqual(bool, bool2) && z && z2) {
            ((AonAuthStarter) this.aonAuthStarter.get()).openCamera("postNotificationUpdated", null);
        }
        if (z3) {
            if (!z) {
                numValueOf = Integer.valueOf(this.statusBarStateController.getState());
            } else if (notificationEntry.getRow() != null) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "HeadsUpCoordinator-->expose: postNotificationUpdated1");
                }
                ((OplusNotificationEntryExImpl) notificationEntry.mNotificationEntryEx).onExposure(2);
            }
        } else if (!z || !z2) {
            numValueOf = null;
        } else if (notificationEntry.getRow() != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "HeadsUpCoordinator-->expose: postNotificationUpdated2");
            }
            ((OplusNotificationEntryExImpl) notificationEntry.mNotificationEntryEx).onExposure(2);
        }
        if (numValueOf != null) {
            NotificationStatisticUtil.onNotificationPosted(this.notificationDependOnCommon.getApplicationContext(), notificationEntry, numValueOf.intValue());
        }
    }

    public final void removeHeadsUpWhenNewComing(NotificationEntry notificationEntry) {
        int i = 0;
        this.mHeadsUpManager.setHeadsUpGoingAwayAnimationsAllowed(false);
        ArrayList arrayList = new ArrayList();
        Stream allEntries = this.mHeadsUpManager.getAllEntries();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$entry = notificationEntry;
        VarHandle.storeStoreFence();
        Stream streamFilter = allEntries.filter(anonymousClass1);
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        anonymousClass2.$entry = notificationEntry;
        anonymousClass2.$needRemoveList = arrayList;
        VarHandle.storeStoreFence();
        streamFilter.forEach(anonymousClass2);
        int size = arrayList.size();
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            this.mHeadsUpManager.removeNotification((String) obj, true, "oplus removeHeadsUpWhenNewComing");
        }
        this.mHeadsUpManager.setHeadsUpGoingAwayAnimationsAllowed(true);
    }

    public final void setFullScreenBannerHelper(Lazy lazy) {
        this.fullScreenBannerHelper = lazy;
    }

    public final void tryCloseAonCamera() {
        LinkedHashMap mPostedEntries = getMPostedEntries();
        if (!mPostedEntries.isEmpty()) {
            Iterator it = mPostedEntries.entrySet().iterator();
            while (it.hasNext()) {
                if (((HeadsUpCoordinator.PostedEntry) ((Map.Entry) it.next()).getValue()).getShouldHeadsUpEver()) {
                    return;
                }
            }
        }
        if (this.oplusAvalanche.getNext() == null && getMEntriesBindingUntil().isEmpty()) {
            ((AonAuthStarter) this.aonAuthStarter.get()).tryCloseAonCamera(TAG);
        }
    }
}
