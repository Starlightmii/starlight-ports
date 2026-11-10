package com.oplus.systemui.separate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.app.displaylib.PerDisplayRepository;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.qs.deprecated.BaseQuickStatusBarHeader;
import com.android.systemui.shade.ShadeExpansionStateManager;
import com.android.systemui.shade.carrier.ShadeCarrierGroupController;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.TintedIconManager;
import com.android.systemui.tuner.TunerService;
import com.oplus.systemui.qs.OplusQuickStatusBarHeaderController;
import com.oplus.systemui.qs.privacy.HeaderPrivacyIconsControllerFactory;
import com.oplus.systemui.statusbar.phone.policy.OplusPolicyObserver;
import com.oplus.systemui.statusbar.pipeline.battery.StatBatteryMeterViewController;
import com.oplus.systemui.statusbar.pipeline.battery.ui.view.StatBatteryMeterView;
import com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSHeaderFactory {
    private final LocationBasedBatteryViewModel batteryViewModel;
    private final Context context;
    private final FeatureFlags featureFlags;
    private final HeaderPrivacyIconsControllerFactory headerPrivacyIconsControllerFactory;
    private final PerDisplayRepository mPerDisplaySubcomponentRepo;
    private final OplusMobileIconsInteractor oplusMobileIconsInteractor;
    private final OplusPolicyObserver oplusPolicyObserver;
    private final ShadeCarrierGroupController.Builder qsCarrierGroupControllerBuilder;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;
    private final ShadeExpansionStateManager shadeExpansionStateManager;
    private final StatusBarIconController statusBarIconController;
    private final TintedIconManager.Factory tintedIconManagerFactory;
    private final TunerService tunerService;

    public QSHeaderFactory(Context context, HeaderPrivacyIconsControllerFactory headerPrivacyIconsControllerFactory, StatusBarIconController statusBarIconController, ShadeCarrierGroupController.Builder builder, FeatureFlags featureFlags, PerDisplayRepository perDisplayRepository, ShadeExpansionStateManager shadeExpansionStateManager, TintedIconManager.Factory factory, LocationBasedBatteryViewModel locationBasedBatteryViewModel, TunerService tunerService, OplusPolicyObserver oplusPolicyObserver, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, OplusMobileIconsInteractor oplusMobileIconsInteractor) {
        this.context = context;
        this.headerPrivacyIconsControllerFactory = headerPrivacyIconsControllerFactory;
        this.statusBarIconController = statusBarIconController;
        this.qsCarrierGroupControllerBuilder = builder;
        this.featureFlags = featureFlags;
        this.mPerDisplaySubcomponentRepo = perDisplayRepository;
        this.shadeExpansionStateManager = shadeExpansionStateManager;
        this.tintedIconManagerFactory = factory;
        this.batteryViewModel = locationBasedBatteryViewModel;
        this.tunerService = tunerService;
        this.oplusPolicyObserver = oplusPolicyObserver;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.oplusMobileIconsInteractor = oplusMobileIconsInteractor;
    }

    public final OplusQuickStatusBarHeaderController create(BaseQuickStatusBarHeader baseQuickStatusBarHeader) {
        if (baseQuickStatusBarHeader == null) {
            baseQuickStatusBarHeader = (BaseQuickStatusBarHeader) LayoutInflater.from(this.context).inflate(2131559543, (ViewGroup) null, false);
        }
        BaseQuickStatusBarHeader baseQuickStatusBarHeader2 = baseQuickStatusBarHeader;
        return new OplusQuickStatusBarHeaderController(baseQuickStatusBarHeader2, this.headerPrivacyIconsControllerFactory, baseQuickStatusBarHeader2.requireViewById(2131364644), this.statusBarIconController, this.qsCarrierGroupControllerBuilder, this.featureFlags, new StatBatteryMeterViewController((StatBatteryMeterView) baseQuickStatusBarHeader2.requireViewById(2131362234), this.batteryViewModel, this.tunerService), this.mPerDisplaySubcomponentRepo, this.shadeExpansionStateManager, this.tintedIconManagerFactory, this.separateNotificationAndQSState, this.oplusMobileIconsInteractor);
    }
}
