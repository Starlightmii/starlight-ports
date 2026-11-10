package com.oplus.systemui.notification.icdf;

import android.app.NotificationChannel;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.android.settingslib.bluetooth.devicesettings.DeviceSettingItem$;
import com.android.systemui.authentication.shared.model.AuthenticationResultModel$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.domain.interactor.NotificationAlertsInteractor;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.interruption.AlertAppSuspendedSuppressor;
import com.android.systemui.statusbar.notification.interruption.HunGroupAlertBehaviorSuppressor;
import com.android.systemui.statusbar.notification.interruption.HunSilentNotificationSuppressor;
import com.android.systemui.statusbar.notification.interruption.PeekOldWhenSuppressor;
import com.android.systemui.statusbar.notification.interruption.PeekPackageSnoozedSuppressor;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionFilter;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.time.SystemClock;
import com.oplus.systemui.statusbar.notification.helper.HeadsUpHelper;
import com.oplus.systemui.statusbar.observer.GlobalBannerSwitchObserver;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfSynergyHeadsUpDecider implements IcdfHeadsUpDecision {
    public static final Companion Companion = new Companion();
    private static final String TAG = "IcdfSynergyHeadsUp";
    private final Lazy alertsInteractor;
    private final IcdfHeadsUpDecisionCache cache;
    private final GlobalSettings globalSettings;
    private final Lazy headsUpHelper;
    private final Lazy headsUpManager;
    private final KeyguardStateController keyguardStateController;
    private final Handler mainHandler;
    private final Lazy notifPipeline;
    private final Lazy peekEnvelope;
    private final kotlin.Lazy peekFilters$delegate;
    private final StatusBarStateController statusBarStateController;
    private final SystemClock systemClock;
    private final ZenModeController zenModeController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: $r8$lambda$Qeq3xf7_L5EfrwAYClKo3L9g-GA, reason: not valid java name */
    public static List m62$r8$lambda$Qeq3xf7_L5EfrwAYClKo3L9gGA(IcdfSynergyHeadsUpDecider icdfSynergyHeadsUpDecider) {
        PeekOldWhenSuppressor peekOldWhenSuppressor = new PeekOldWhenSuppressor(icdfSynergyHeadsUpDecider.systemClock);
        IcdfSynergyHeadsUpDecider$$ExternalSyntheticLambda0 icdfSynergyHeadsUpDecider$$ExternalSyntheticLambda0 = new IcdfSynergyHeadsUpDecider$$ExternalSyntheticLambda0(1);
        icdfSynergyHeadsUpDecider$$ExternalSyntheticLambda0.f$0 = icdfSynergyHeadsUpDecider;
        VarHandle.storeStoreFence();
        return CollectionsKt__CollectionsKt.listOf(new VisualInterruptionFilter[]{new PeekPackageSnoozedSuppressor((HeadsUpManager) icdfSynergyHeadsUpDecider.headsUpManager.get()), peekOldWhenSuppressor, new HunGroupAlertBehaviorSuppressor(icdfSynergyHeadsUpDecider$$ExternalSyntheticLambda0), new HunSilentNotificationSuppressor(), new AlertAppSuspendedSuppressor(), icdfSynergyHeadsUpDecider.peekEnvelope.get()});
    }

    public IcdfSynergyHeadsUpDecider(Handler handler, IcdfHeadsUpDecisionCache icdfHeadsUpDecisionCache, Lazy lazy, ZenModeController zenModeController, KeyguardStateController keyguardStateController, StatusBarStateController statusBarStateController, GlobalSettings globalSettings, SystemClock systemClock, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5) {
        this.mainHandler = handler;
        this.cache = icdfHeadsUpDecisionCache;
        this.headsUpHelper = lazy;
        this.zenModeController = zenModeController;
        this.keyguardStateController = keyguardStateController;
        this.statusBarStateController = statusBarStateController;
        this.globalSettings = globalSettings;
        this.systemClock = systemClock;
        this.headsUpManager = lazy2;
        this.notifPipeline = lazy3;
        this.alertsInteractor = lazy4;
        this.peekEnvelope = lazy5;
        IcdfSynergyHeadsUpDecider$$ExternalSyntheticLambda0 icdfSynergyHeadsUpDecider$$ExternalSyntheticLambda0 = new IcdfSynergyHeadsUpDecider$$ExternalSyntheticLambda0(0);
        icdfSynergyHeadsUpDecider$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.peekFilters$delegate = LazyKt__LazyJVMKt.lazy(icdfSynergyHeadsUpDecider$$ExternalSyntheticLambda0);
    }

    private final List<VisualInterruptionFilter> getPeekFilters() {
        return (List) this.peekFilters$delegate.getValue();
    }

    public final void logFallback(NotificationEntry notificationEntry, boolean z, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            String logKeyNonNull = NotificationUtilsKt.getLogKeyNonNull(notificationEntry);
            boolean zIsDozing = this.statusBarStateController.isDozing();
            int state = this.statusBarStateController.getState();
            StringBuilder sbM = AuthenticationResultModel$.ExternalSyntheticOutline0.m("fallback=", " by ", str, z, " for ");
            DeviceSettingItem$.ExternalSyntheticOutline0.m(logKeyNonNull, ", isDozing=", ", sbState=", sbM, zIsDozing);
            MobileInputLogger$.ExternalSyntheticOutline0.m(state, "IcdfSynergyHeadsUp-->", "SystemUi--Notification", sbM);
        }
    }

    @Override // com.oplus.systemui.notification.icdf.IcdfHeadsUpDecision
    public boolean shouldHeadsUp(NotificationEntry notificationEntry) {
        if (Intrinsics.areEqual(Looper.myLooper(), this.mainHandler.getLooper())) {
            return shouldHeadsUpOnMain(notificationEntry);
        }
        AtomicReference atomicReference = new AtomicReference(Boolean.FALSE);
        Handler handler = this.mainHandler;
        runOnMain.completed.1 r2 = new runOnMain.completed.1();
        r2.$shouldHeadsUp = atomicReference;
        r2.this$0 = this;
        r2.$entry = notificationEntry;
        VarHandle.storeStoreFence();
        if (handler.runWithScissors(r2, 150L)) {
            return ((Boolean) atomicReference.get()).booleanValue();
        }
        LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "shouldHeadsUp timeout on main thread.");
        return false;
    }

    public final boolean shouldHeadsUpOnMain(NotificationEntry notificationEntry) {
        NotificationChannel channel;
        Object next;
        if (!GlobalBannerSwitchObserver.INSTANCE.getGlobalBannerSwitchEnabled() || (this.zenModeController.getZen() != 0 && notificationEntry.shouldSuppressVisualEffect(16))) {
            return false;
        }
        Boolean boolTake = this.cache.take(notificationEntry.getKey());
        if (this.statusBarStateController.isDozing() || this.statusBarStateController.getState() == 1 || this.keyguardStateController.mShowing || this.statusBarStateController.getState() != 0) {
            if (boolTake != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "IcdfSynergyHeadsUp-->".concat("discard stale phone-cache=" + boolTake + " for " + NotificationUtilsKt.getLogKeyNonNull(notificationEntry) + ", isDozing=" + this.statusBarStateController.isDozing() + ", sbState=" + this.statusBarStateController.getState()));
            }
        } else if (boolTake != null) {
            boolean zBooleanValue = boolTake.booleanValue();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                String logKeyNonNull = NotificationUtilsKt.getLogKeyNonNull(notificationEntry);
                boolean zIsDozing = this.statusBarStateController.isDozing();
                int state = this.statusBarStateController.getState();
                StringBuilder sbM = AuthenticationResultModel$.ExternalSyntheticOutline0.m("consumed phone-cache=", " for ", logKeyNonNull, zBooleanValue, ", isDozing=");
                sbM.append(zIsDozing);
                sbM.append(", sbState=");
                sbM.append(state);
                Log.d("SystemUi--Notification", "IcdfSynergyHeadsUp-->".concat(sbM.toString()));
            }
            return zBooleanValue;
        }
        if (!((HeadsUpHelper) this.headsUpHelper.get()).shouldPeekByOS(notificationEntry.getSbn())) {
            logFallback(notificationEntry, false, "shouldPeekByOS(fromIcdf=true)");
            return false;
        }
        int importance = ((HeadsUpHelper) this.headsUpHelper.get()).getImportance(notificationEntry);
        if (importance < 4) {
            logFallback(notificationEntry, false, "importance=" + importance);
            return false;
        }
        if (this.globalSettings.getInt("heads_up_notifications_enabled", 0) == 0) {
            logFallback(notificationEntry, false, "peek disabled globally");
            return false;
        }
        if (!((NotificationAlertsInteractor) this.alertsInteractor.get()).areNotificationAlertsEnabled()) {
            logFallback(notificationEntry, false, "notification alerts disabled");
            return false;
        }
        if (notificationEntry.getSbn().getNotification().visibility == -1 || ((channel = notificationEntry.getRanking().getChannel()) != null && channel.getLockscreenVisibility() == -1)) {
            logFallback(notificationEntry, false, "secret visibility");
            return false;
        }
        Iterator<T> it = getPeekFilters().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!((VisualInterruptionFilter) next).shouldSuppress(notificationEntry));
        VisualInterruptionFilter visualInterruptionFilter = (VisualInterruptionFilter) next;
        if (visualInterruptionFilter != null) {
            logFallback(notificationEntry, false, visualInterruptionFilter.getReason());
            return false;
        }
        logFallback(notificationEntry, true, "passed");
        return true;
    }

    public final void warmPeekFilters$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic() {
        if (Intrinsics.areEqual(Looper.myLooper(), this.mainHandler.getLooper())) {
            getPeekFilters().size();
        } else {
            Path$.ExternalSyntheticBUOutline0.m$1("warmPeekFilters must run on main thread");
        }
    }
}
