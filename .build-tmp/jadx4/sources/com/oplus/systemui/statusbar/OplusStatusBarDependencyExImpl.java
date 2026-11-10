package com.oplus.systemui.statusbar;

import android.content.Context;
import com.android.app.displaylib.PerDisplayInstanceRepositoryImpl;
import com.android.app.displaylib.PerDisplayRepository;
import com.android.systemui.OplusScreenDecorHwcLayerEx;
import com.android.systemui.display.OplusConnectingDisplayEx;
import com.android.systemui.display.dagger.ReferenceSysUIDisplaySubcomponent;
import com.android.systemui.locationbutton.OplusLocationButtonStatisticUtil;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.notification.OplusNotificationIconStatusBarHelper;
import com.android.systemui.privacy.OplusPrivacyConfigEx;
import com.android.systemui.sensorprivacy.OplusSensorUseStartedActivityEx;
import com.android.systemui.shade.data.repository.ShadeDisplaysRepository;
import com.android.systemui.statusbar.OplusStatusBarDependencyEx;
import com.android.systemui.statusbar.ScreenDecorationsEx;
import com.android.systemui.statusbar.data.repository.HomeStatusBarComponentsRepository;
import com.android.systemui.statusbar.demomode.OplusDemoStatusIconsEx;
import com.android.systemui.statusbar.event.OplusSystemEventChipAnimationControllerEx;
import com.android.systemui.statusbar.location.OplusLocationControllerEx;
import com.android.systemui.statusbar.phone.KeyguardStatusBarViewEx;
import com.android.systemui.statusbar.phone.OplusPhoneStatusBarPolicyEx;
import com.android.systemui.statusbar.phone.OplusStatusBarInfoControllerEx;
import com.android.systemui.statusbar.phone.StatusBarIconControllerEx;
import com.android.systemui.statusbar.phone.networkspeed.OplusNetworkSpeedControllerEx;
import com.android.systemui.statusbar.pipeline.OplusMobileSignalEx;
import com.android.systemui.statusbar.pipeline.OplusWifiSignalEx;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractorEx;
import com.android.systemui.statusbar.pipeline.shared.ui.composable.OplusStackedMobileIconLayoutStrategyFactory;
import com.android.systemui.statusbar.pipeline.shared.ui.view.OplusSingleBindableStatusBarComposeIconEx;
import com.android.systemui.statusbar.pipeline.ui.viewmodel.OplusStackedMobileIconViewModel;
import com.android.systemui.statusbar.policy.BatteryControllerLogger;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.PolicyObserver;
import com.android.systemui.statusbar.seedling.OplusSeedingCardControllerEx;
import com.android.systemui.statusbar.window.StatusBarWindowController;
import com.android.systemui.statusbar.window.shared.model.StatusBarWindowState;
import com.oplus.systemui.OplusScreenDecorHwcLayerExImpl;
import com.oplus.systemui.locationbutton.OplusLocationButtonStatisticUtilImpl;
import com.oplus.systemui.oplusstatusbar.demomode.OplusDemoStatusIconsExImpl;
import com.oplus.systemui.oplusstatusbar.display.OplusConnectingDisplayExImpl;
import com.oplus.systemui.oplusstatusbar.sensorprivacy.OplusSensorUseStartedActivityExImpl;
import com.oplus.systemui.privacy.OplusPrivacyConfigExImpl;
import com.oplus.systemui.statusbar.event.OplusSystemEventChipAnimationControllerExImpl;
import com.oplus.systemui.statusbar.location.OplusLocationControllerExImpl;
import com.oplus.systemui.statusbar.notification.OplusNotificationIconStatusBarHelperImpl;
import com.oplus.systemui.statusbar.phone.KeyguardStatusBarViewExImpl;
import com.oplus.systemui.statusbar.phone.OplusPhoneStatusBarPolicyExImpl;
import com.oplus.systemui.statusbar.phone.OplusStatusBarInfoControllerExImpl;
import com.oplus.systemui.statusbar.phone.StatusBarIconControllerExImpl;
import com.oplus.systemui.statusbar.phone.netspeed.OplusNetworkSpeedControllerExImpl;
import com.oplus.systemui.statusbar.phone.policy.OplusPolicyObserver;
import com.oplus.systemui.statusbar.pipeline.OplusMobileSignalExImpl;
import com.oplus.systemui.statusbar.pipeline.OplusWifiSignalExImpl;
import com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconsInteractorExImpl;
import com.oplus.systemui.statusbar.pipeline.shared.ui.view.OplusSingleBindableStatusBarComposeIconExImpl;
import com.oplus.systemui.statusbar.seeding.OplusSeedlingCardController;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarDependencyExImpl extends OplusStatusBarDependencyEx {
    private Lazy batteryControllerLogger;
    private final kotlin.Lazy configController$delegate;
    private Lazy configurationController;
    private Lazy connectingDisplayEx;
    private Lazy demoStatusIconsEx;
    private Lazy displaySubComponentRepository;
    private final kotlin.Lazy homeStatusBarComponentsRepository$delegate;
    private Lazy homeStatusBarComponentsRepositoryLazy;
    private Lazy keyguardStatusBarViewEx;
    private Lazy locationButtonStatisticUtilImpl;
    private Lazy locationControllerEx;
    private final kotlin.Lazy oplusBatteryControllerLogger$delegate;
    private final kotlin.Lazy oplusConnectingDisplayEx$delegate;
    private final kotlin.Lazy oplusDemoStatusIconsEx$delegate;
    private final kotlin.Lazy oplusKeyguardStatusBarViewEx$delegate;
    private final kotlin.Lazy oplusLocationButtonStatisticUtil$delegate;
    private final kotlin.Lazy oplusLocationControllerEx$delegate;
    private final kotlin.Lazy oplusMobileIconsInteractorEx$delegate;
    private Lazy oplusMobileIconsInteractorExLazy;
    private final kotlin.Lazy oplusMobileSignalEx$delegate;
    private Lazy oplusMobileSignalExImpl;
    private Lazy oplusNetworkSpeedController;
    private final kotlin.Lazy oplusNetworkSpeedControllerEx$delegate;
    private final kotlin.Lazy oplusNotificationIconStatusBarHelper$delegate;
    private Lazy oplusNotificationIconStatusBarHelperLazy;
    private Lazy oplusPhoneStatusBarPolicy;
    private final kotlin.Lazy oplusPhoneStatusBarPolicyEx$delegate;
    private Lazy oplusPolicyObserver;
    private final kotlin.Lazy oplusScreenDecorationsEx$delegate;
    private final kotlin.Lazy oplusSeedingCardControllerEx$delegate;
    private final kotlin.Lazy oplusSensorUseStartedActivityEx$delegate;
    private final kotlin.Lazy oplusSingleBindableStatusBarComposeIconEx$delegate;
    private Lazy oplusSingleBindableStatusBarComposeIconExLazy;
    private final kotlin.Lazy oplusStackedMobileIconLayoutStrategyFactory$delegate;
    private Lazy oplusStackedMobileIconLayoutStrategyFactoryLazy;
    private final kotlin.Lazy oplusStackedMobileIconViewModelFactory$delegate;
    private Lazy oplusStackedMobileIconViewModelFactoryLazy;
    private Lazy oplusStatusBarIconController;
    private final kotlin.Lazy oplusStatusBarIconControllerEx$delegate;
    private final kotlin.Lazy oplusStatusBarInfoControllerEx$delegate;
    private final kotlin.Lazy oplusWifiSignalEx$delegate;
    private Lazy oplusWifiSignalExImpl;
    private final kotlin.Lazy policyObserver$delegate;
    private Lazy screenDecorationsEx;
    private Lazy seedingCardControllerEx;
    private Lazy sensorUseStartedActivityEx;
    private Lazy shadeDisplaysRepository;
    private Lazy statusBarInfoControllerEx;

    public static ConfigurationController $r8$lambda$28R4JIVlpXKKOYYneHZIcIRjN0k(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        ConfigurationController configurationController;
        Lazy lazy = oplusStatusBarDependencyExImpl.configurationController;
        return (lazy == null || (configurationController = (ConfigurationController) lazy.get()) == null) ? super.getConfigController() : configurationController;
    }

    public static OplusPhoneStatusBarPolicyEx $r8$lambda$2kdP1EG3TO2OnDmKMCRJtDbyUiA(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl;
        Lazy lazy = oplusStatusBarDependencyExImpl.oplusPhoneStatusBarPolicy;
        return (lazy == null || (oplusPhoneStatusBarPolicyExImpl = (OplusPhoneStatusBarPolicyExImpl) lazy.get()) == null) ? super.getOplusPhoneStatusBarPolicyEx() : oplusPhoneStatusBarPolicyExImpl;
    }

    /* JADX INFO: renamed from: $r8$lambda$3GswIk4w-5ihTubOUkzBQrYdogQ, reason: not valid java name */
    public static OplusWifiSignalEx m376$r8$lambda$3GswIk4w5ihTubOUkzBQrYdogQ(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusWifiSignalExImpl oplusWifiSignalExImpl;
        Lazy lazy = oplusStatusBarDependencyExImpl.oplusWifiSignalExImpl;
        return (lazy == null || (oplusWifiSignalExImpl = (OplusWifiSignalExImpl) lazy.get()) == null) ? super.getOplusWifiSignalEx() : oplusWifiSignalExImpl;
    }

    public static OplusLocationControllerEx $r8$lambda$4otxHyMLjvvVR61NajDq392IE5A(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusLocationControllerExImpl oplusLocationControllerExImpl;
        Lazy lazy = oplusStatusBarDependencyExImpl.locationControllerEx;
        return (lazy == null || (oplusLocationControllerExImpl = (OplusLocationControllerExImpl) lazy.get()) == null) ? super.getOplusLocationControllerEx() : oplusLocationControllerExImpl;
    }

    public static PolicyObserver $r8$lambda$7QtIh4R3a2LOc8JMoEiOw2scU2A(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusPolicyObserver oplusPolicyObserver;
        Lazy lazy = oplusStatusBarDependencyExImpl.oplusPolicyObserver;
        return (lazy == null || (oplusPolicyObserver = (OplusPolicyObserver) lazy.get()) == null) ? super.getPolicyObserver() : oplusPolicyObserver;
    }

    public static OplusStackedMobileIconLayoutStrategyFactory $r8$lambda$BHBjBYtMK7H9kRMS5FyAOi6MT90(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusStackedMobileIconLayoutStrategyFactory oplusStackedMobileIconLayoutStrategyFactory;
        Lazy lazy = oplusStatusBarDependencyExImpl.oplusStackedMobileIconLayoutStrategyFactoryLazy;
        return (lazy == null || (oplusStackedMobileIconLayoutStrategyFactory = (OplusStackedMobileIconLayoutStrategyFactory) lazy.get()) == null) ? super.getOplusStackedMobileIconLayoutStrategyFactory() : oplusStackedMobileIconLayoutStrategyFactory;
    }

    public static HomeStatusBarComponentsRepository $r8$lambda$GxgF4eC8EgrnWhosLzLeWJ1vhzQ(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        Lazy lazy = oplusStatusBarDependencyExImpl.homeStatusBarComponentsRepositoryLazy;
        if (lazy != null) {
            return (HomeStatusBarComponentsRepository) lazy.get();
        }
        return null;
    }

    /* JADX INFO: renamed from: $r8$lambda$T7UYg-EQhXl1DruSPBR-rqNNgoU, reason: not valid java name */
    public static OplusStackedMobileIconViewModel.Factory m377$r8$lambda$T7UYgEQhXl1DruSPBRrqNNgoU(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusStackedMobileIconViewModel.Factory factory;
        Lazy lazy = oplusStatusBarDependencyExImpl.oplusStackedMobileIconViewModelFactoryLazy;
        return (lazy == null || (factory = (OplusStackedMobileIconViewModel.Factory) lazy.get()) == null) ? super.getOplusStackedMobileIconViewModelFactory() : factory;
    }

    public static OplusMobileSignalEx $r8$lambda$TsL1exUOvwqalscBFwQYG_04wnI(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusMobileSignalExImpl oplusMobileSignalExImpl;
        Lazy lazy = oplusStatusBarDependencyExImpl.oplusMobileSignalExImpl;
        return (lazy == null || (oplusMobileSignalExImpl = (OplusMobileSignalExImpl) lazy.get()) == null) ? super.getOplusMobileSignalEx() : oplusMobileSignalExImpl;
    }

    public static BatteryControllerLogger $r8$lambda$UJuSl2h0jWnzbue1_di4Xlj4cr0(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        BatteryControllerLogger batteryControllerLogger;
        Lazy lazy = oplusStatusBarDependencyExImpl.batteryControllerLogger;
        return (lazy == null || (batteryControllerLogger = (BatteryControllerLogger) lazy.get()) == null) ? super.getOplusBatteryControllerLogger() : batteryControllerLogger;
    }

    public static KeyguardStatusBarViewEx $r8$lambda$UaJuSTyOd3BZ0nlw_JneQZlW5ns(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        KeyguardStatusBarViewExImpl keyguardStatusBarViewExImpl;
        Lazy lazy = oplusStatusBarDependencyExImpl.keyguardStatusBarViewEx;
        return (lazy == null || (keyguardStatusBarViewExImpl = (KeyguardStatusBarViewExImpl) lazy.get()) == null) ? super.getOplusKeyguardStatusBarViewEx() : keyguardStatusBarViewExImpl;
    }

    public static OplusSeedingCardControllerEx $r8$lambda$WWuLG7v5UlRS031DkXORbBd7dD8(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusSeedlingCardController oplusSeedlingCardController;
        Lazy lazy = oplusStatusBarDependencyExImpl.seedingCardControllerEx;
        return (lazy == null || (oplusSeedlingCardController = (OplusSeedlingCardController) lazy.get()) == null) ? super.getOplusSeedingCardControllerEx() : oplusSeedlingCardController;
    }

    public static OplusSensorUseStartedActivityEx $r8$lambda$WuZNuwNJaljzP6Hm1LNgoD6ZdIk(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusSensorUseStartedActivityExImpl oplusSensorUseStartedActivityExImpl;
        Lazy lazy = oplusStatusBarDependencyExImpl.sensorUseStartedActivityEx;
        return (lazy == null || (oplusSensorUseStartedActivityExImpl = (OplusSensorUseStartedActivityExImpl) lazy.get()) == null) ? super.getOplusSensorUseStartedActivityEx() : oplusSensorUseStartedActivityExImpl;
    }

    public static StatusBarIconControllerEx $r8$lambda$Y1pB6KBYf2x0x4d1HQTOSkR6aO0(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        StatusBarIconControllerExImpl statusBarIconControllerExImpl;
        Lazy lazy = oplusStatusBarDependencyExImpl.oplusStatusBarIconController;
        return (lazy == null || (statusBarIconControllerExImpl = (StatusBarIconControllerExImpl) lazy.get()) == null) ? super.getOplusStatusBarIconControllerEx() : statusBarIconControllerExImpl;
    }

    public static OplusSingleBindableStatusBarComposeIconEx $r8$lambda$aKwIS7BvOumwsaEUxti_b_RuWtI(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusSingleBindableStatusBarComposeIconExImpl oplusSingleBindableStatusBarComposeIconExImpl;
        Lazy lazy = oplusStatusBarDependencyExImpl.oplusSingleBindableStatusBarComposeIconExLazy;
        return (lazy == null || (oplusSingleBindableStatusBarComposeIconExImpl = (OplusSingleBindableStatusBarComposeIconExImpl) lazy.get()) == null) ? super.getOplusSingleBindableStatusBarComposeIconEx() : oplusSingleBindableStatusBarComposeIconExImpl;
    }

    public static OplusStatusBarInfoControllerEx $r8$lambda$aT_FibujTH0ejtgeSu3RW2LVwGM(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusStatusBarInfoControllerExImpl oplusStatusBarInfoControllerExImpl;
        Lazy lazy = oplusStatusBarDependencyExImpl.statusBarInfoControllerEx;
        return (lazy == null || (oplusStatusBarInfoControllerExImpl = (OplusStatusBarInfoControllerExImpl) lazy.get()) == null) ? super.getOplusStatusBarInfoControllerEx() : oplusStatusBarInfoControllerExImpl;
    }

    /* JADX INFO: renamed from: $r8$lambda$d4wJ59UhrfiDs2JAjIW-K1CrzkY, reason: not valid java name */
    public static OplusLocationButtonStatisticUtil m378$r8$lambda$d4wJ59UhrfiDs2JAjIWK1CrzkY(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusLocationButtonStatisticUtilImpl oplusLocationButtonStatisticUtilImpl;
        Lazy lazy = oplusStatusBarDependencyExImpl.locationButtonStatisticUtilImpl;
        return (lazy == null || (oplusLocationButtonStatisticUtilImpl = (OplusLocationButtonStatisticUtilImpl) lazy.get()) == null) ? super.getOplusLocationButtonStatisticUtil() : oplusLocationButtonStatisticUtilImpl;
    }

    /* JADX INFO: renamed from: $r8$lambda$fvOcbql-X9E0G_sFc4a48uO-aOQ, reason: not valid java name */
    public static OplusMobileIconsInteractorEx m379$r8$lambda$fvOcbqlX9E0G_sFc4a48uOaOQ(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusMobileIconsInteractorExImpl oplusMobileIconsInteractorExImpl;
        Lazy lazy = oplusStatusBarDependencyExImpl.oplusMobileIconsInteractorExLazy;
        return (lazy == null || (oplusMobileIconsInteractorExImpl = (OplusMobileIconsInteractorExImpl) lazy.get()) == null) ? super.getOplusMobileIconsInteractorEx() : oplusMobileIconsInteractorExImpl;
    }

    /* JADX INFO: renamed from: $r8$lambda$m-P1zc9eBFVjVwNrW76m8-GWSQM, reason: not valid java name */
    public static OplusConnectingDisplayEx m380$r8$lambda$mP1zc9eBFVjVwNrW76m8GWSQM(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusConnectingDisplayExImpl oplusConnectingDisplayExImpl;
        Lazy lazy = oplusStatusBarDependencyExImpl.connectingDisplayEx;
        return (lazy == null || (oplusConnectingDisplayExImpl = (OplusConnectingDisplayExImpl) lazy.get()) == null) ? super.getOplusConnectingDisplayEx() : oplusConnectingDisplayExImpl;
    }

    public static ScreenDecorationsEx $r8$lambda$mFtWvapTGrD5021yB23DL6taeXA(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusScreenDecorationsExImpl oplusScreenDecorationsExImpl;
        Lazy lazy = oplusStatusBarDependencyExImpl.screenDecorationsEx;
        return (lazy == null || (oplusScreenDecorationsExImpl = (OplusScreenDecorationsExImpl) lazy.get()) == null) ? super.getOplusScreenDecorationsEx() : oplusScreenDecorationsExImpl;
    }

    /* JADX INFO: renamed from: $r8$lambda$mvObXQlAfJNqqOA-iaU48r0CNrs, reason: not valid java name */
    public static OplusNetworkSpeedControllerEx m381$r8$lambda$mvObXQlAfJNqqOAiaU48r0CNrs(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusNetworkSpeedControllerExImpl oplusNetworkSpeedControllerExImpl;
        Lazy lazy = oplusStatusBarDependencyExImpl.oplusNetworkSpeedController;
        return (lazy == null || (oplusNetworkSpeedControllerExImpl = (OplusNetworkSpeedControllerExImpl) lazy.get()) == null) ? super.getOplusNetworkSpeedControllerEx() : oplusNetworkSpeedControllerExImpl;
    }

    public static OplusDemoStatusIconsEx $r8$lambda$rZ8XMQfFDC3Wx0XU1YScvNF60L8(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusDemoStatusIconsExImpl oplusDemoStatusIconsExImpl;
        Lazy lazy = oplusStatusBarDependencyExImpl.demoStatusIconsEx;
        return (lazy == null || (oplusDemoStatusIconsExImpl = (OplusDemoStatusIconsExImpl) lazy.get()) == null) ? super.getOplusDemoStatusIconsEx() : oplusDemoStatusIconsExImpl;
    }

    /* JADX INFO: renamed from: $r8$lambda$yY3ItfR-soRJwvok-3Ca_4CcisE, reason: not valid java name */
    public static OplusNotificationIconStatusBarHelper m382$r8$lambda$yY3ItfRsoRJwvok3Ca_4CcisE(OplusStatusBarDependencyExImpl oplusStatusBarDependencyExImpl) {
        OplusNotificationIconStatusBarHelperImpl oplusNotificationIconStatusBarHelperImpl;
        Lazy lazy = oplusStatusBarDependencyExImpl.oplusNotificationIconStatusBarHelperLazy;
        return (lazy == null || (oplusNotificationIconStatusBarHelperImpl = (OplusNotificationIconStatusBarHelperImpl) lazy.get()) == null) ? super.getOplusNotificationIconStatusBarHelper() : oplusNotificationIconStatusBarHelperImpl;
    }

    public OplusStatusBarDependencyExImpl(Context context, LogBuffer logBuffer) {
        super(context, logBuffer);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(0);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusSeedingCardControllerEx$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda1 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(2);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusStatusBarIconControllerEx$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda1);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda2 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(6);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusPhoneStatusBarPolicyEx$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda2);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda3 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(7);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusWifiSignalEx$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda3);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda4 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(8);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusMobileSignalEx$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda4);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda5 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(9);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        this.policyObserver$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda5);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda6 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(10);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusNetworkSpeedControllerEx$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda6);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda7 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(12);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda7.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusKeyguardStatusBarViewEx$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda7);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda8 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(13);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda8.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusScreenDecorationsEx$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda8);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda9 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(14);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda9.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusLocationControllerEx$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda9);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda10 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(11);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda10.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusStatusBarInfoControllerEx$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda10);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda11 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(15);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda11.f$0 = this;
        VarHandle.storeStoreFence();
        this.configController$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda11);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda12 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(16);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda12.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusSensorUseStartedActivityEx$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda12);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda13 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(17);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda13.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusConnectingDisplayEx$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda13);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda14 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(18);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda14.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusDemoStatusIconsEx$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda14);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda15 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(19);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda15.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusNotificationIconStatusBarHelper$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda15);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda16 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(20);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda16.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusBatteryControllerLogger$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda16);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda17 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(21);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda17.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusMobileIconsInteractorEx$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda17);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda18 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(22);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda18.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusSingleBindableStatusBarComposeIconEx$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda18);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda19 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(1);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda19.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusStackedMobileIconViewModelFactory$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda19);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda20 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(3);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda20.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusStackedMobileIconLayoutStrategyFactory$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda20);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda21 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(4);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda21.f$0 = this;
        VarHandle.storeStoreFence();
        this.homeStatusBarComponentsRepository$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda21);
        OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0 oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda22 = new OplusStatusBarDependencyExImpl$$ExternalSyntheticLambda0(5);
        oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda22.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusLocationButtonStatisticUtil$delegate = LazyKt__LazyJVMKt.lazy(oplusStatusBarDependencyExImpl$$ExternalSyntheticLambda22);
    }

    public OplusPrivacyConfigEx createOplusPrivacyConfigEx() {
        return new OplusPrivacyConfigExImpl();
    }

    public OplusScreenDecorHwcLayerEx createScreenDecorHwcLayerEx() {
        return new OplusScreenDecorHwcLayerExImpl();
    }

    public OplusSystemEventChipAnimationControllerEx createSystemEventChipAnimationControllerEx(Context context, StatusBarWindowController statusBarWindowController) {
        return new OplusSystemEventChipAnimationControllerExImpl(context, statusBarWindowController);
    }

    public final Lazy getBatteryControllerLogger() {
        return this.batteryControllerLogger;
    }

    public ConfigurationController getConfigController() {
        return (ConfigurationController) this.configController$delegate.getValue();
    }

    public final Lazy getConfigurationController() {
        return this.configurationController;
    }

    public final Lazy getConnectingDisplayEx() {
        return this.connectingDisplayEx;
    }

    public final Lazy getDemoStatusIconsEx() {
        return this.demoStatusIconsEx;
    }

    public final Lazy getDisplaySubComponentRepository() {
        return this.displaySubComponentRepository;
    }

    public HomeStatusBarComponentsRepository getHomeStatusBarComponentsRepository() {
        return (HomeStatusBarComponentsRepository) this.homeStatusBarComponentsRepository$delegate.getValue();
    }

    public final Lazy getHomeStatusBarComponentsRepositoryLazy() {
        return this.homeStatusBarComponentsRepositoryLazy;
    }

    public final Lazy getKeyguardStatusBarViewEx() {
        return this.keyguardStatusBarViewEx;
    }

    public final Lazy getLocationButtonStatisticUtilImpl() {
        return this.locationButtonStatisticUtilImpl;
    }

    public final Lazy getLocationControllerEx() {
        return this.locationControllerEx;
    }

    public BatteryControllerLogger getOplusBatteryControllerLogger() {
        return (BatteryControllerLogger) this.oplusBatteryControllerLogger$delegate.getValue();
    }

    public OplusConnectingDisplayEx getOplusConnectingDisplayEx() {
        return (OplusConnectingDisplayEx) this.oplusConnectingDisplayEx$delegate.getValue();
    }

    public OplusDemoStatusIconsEx getOplusDemoStatusIconsEx() {
        return (OplusDemoStatusIconsEx) this.oplusDemoStatusIconsEx$delegate.getValue();
    }

    public KeyguardStatusBarViewEx getOplusKeyguardStatusBarViewEx() {
        return (KeyguardStatusBarViewEx) this.oplusKeyguardStatusBarViewEx$delegate.getValue();
    }

    public OplusLocationButtonStatisticUtil getOplusLocationButtonStatisticUtil() {
        return (OplusLocationButtonStatisticUtil) this.oplusLocationButtonStatisticUtil$delegate.getValue();
    }

    public OplusLocationControllerEx getOplusLocationControllerEx() {
        return (OplusLocationControllerEx) this.oplusLocationControllerEx$delegate.getValue();
    }

    public OplusMobileIconsInteractorEx getOplusMobileIconsInteractorEx() {
        return (OplusMobileIconsInteractorEx) this.oplusMobileIconsInteractorEx$delegate.getValue();
    }

    public final Lazy getOplusMobileIconsInteractorExLazy() {
        return this.oplusMobileIconsInteractorExLazy;
    }

    public OplusMobileSignalEx getOplusMobileSignalEx() {
        return (OplusMobileSignalEx) this.oplusMobileSignalEx$delegate.getValue();
    }

    public final Lazy getOplusMobileSignalExImpl() {
        return this.oplusMobileSignalExImpl;
    }

    public final Lazy getOplusNetworkSpeedController() {
        return this.oplusNetworkSpeedController;
    }

    public OplusNetworkSpeedControllerEx getOplusNetworkSpeedControllerEx() {
        return (OplusNetworkSpeedControllerEx) this.oplusNetworkSpeedControllerEx$delegate.getValue();
    }

    public OplusNotificationIconStatusBarHelper getOplusNotificationIconStatusBarHelper() {
        return (OplusNotificationIconStatusBarHelper) this.oplusNotificationIconStatusBarHelper$delegate.getValue();
    }

    public final Lazy getOplusNotificationIconStatusBarHelperLazy() {
        return this.oplusNotificationIconStatusBarHelperLazy;
    }

    public final Lazy getOplusPhoneStatusBarPolicy() {
        return this.oplusPhoneStatusBarPolicy;
    }

    public OplusPhoneStatusBarPolicyEx getOplusPhoneStatusBarPolicyEx() {
        return (OplusPhoneStatusBarPolicyEx) this.oplusPhoneStatusBarPolicyEx$delegate.getValue();
    }

    public final Lazy getOplusPolicyObserver() {
        return this.oplusPolicyObserver;
    }

    public ScreenDecorationsEx getOplusScreenDecorationsEx() {
        return (ScreenDecorationsEx) this.oplusScreenDecorationsEx$delegate.getValue();
    }

    public OplusSeedingCardControllerEx getOplusSeedingCardControllerEx() {
        return (OplusSeedingCardControllerEx) this.oplusSeedingCardControllerEx$delegate.getValue();
    }

    public OplusSensorUseStartedActivityEx getOplusSensorUseStartedActivityEx() {
        return (OplusSensorUseStartedActivityEx) this.oplusSensorUseStartedActivityEx$delegate.getValue();
    }

    public OplusSingleBindableStatusBarComposeIconEx getOplusSingleBindableStatusBarComposeIconEx() {
        return (OplusSingleBindableStatusBarComposeIconEx) this.oplusSingleBindableStatusBarComposeIconEx$delegate.getValue();
    }

    public final Lazy getOplusSingleBindableStatusBarComposeIconExLazy() {
        return this.oplusSingleBindableStatusBarComposeIconExLazy;
    }

    public OplusStackedMobileIconLayoutStrategyFactory getOplusStackedMobileIconLayoutStrategyFactory() {
        return (OplusStackedMobileIconLayoutStrategyFactory) this.oplusStackedMobileIconLayoutStrategyFactory$delegate.getValue();
    }

    public final Lazy getOplusStackedMobileIconLayoutStrategyFactoryLazy() {
        return this.oplusStackedMobileIconLayoutStrategyFactoryLazy;
    }

    public OplusStackedMobileIconViewModel.Factory getOplusStackedMobileIconViewModelFactory() {
        return (OplusStackedMobileIconViewModel.Factory) this.oplusStackedMobileIconViewModelFactory$delegate.getValue();
    }

    public final Lazy getOplusStackedMobileIconViewModelFactoryLazy() {
        return this.oplusStackedMobileIconViewModelFactoryLazy;
    }

    public final Lazy getOplusStatusBarIconController() {
        return this.oplusStatusBarIconController;
    }

    public StatusBarIconControllerEx getOplusStatusBarIconControllerEx() {
        return (StatusBarIconControllerEx) this.oplusStatusBarIconControllerEx$delegate.getValue();
    }

    public OplusStatusBarInfoControllerEx getOplusStatusBarInfoControllerEx() {
        return (OplusStatusBarInfoControllerEx) this.oplusStatusBarInfoControllerEx$delegate.getValue();
    }

    public OplusWifiSignalEx getOplusWifiSignalEx() {
        return (OplusWifiSignalEx) this.oplusWifiSignalEx$delegate.getValue();
    }

    public final Lazy getOplusWifiSignalExImpl() {
        return this.oplusWifiSignalExImpl;
    }

    public PolicyObserver getPolicyObserver() {
        return (PolicyObserver) this.policyObserver$delegate.getValue();
    }

    public final Lazy getScreenDecorationsEx() {
        return this.screenDecorationsEx;
    }

    public final Lazy getSeedingCardControllerEx() {
        return this.seedingCardControllerEx;
    }

    public final Lazy getSensorUseStartedActivityEx() {
        return this.sensorUseStartedActivityEx;
    }

    public final Lazy getShadeDisplaysRepository() {
        return this.shadeDisplaysRepository;
    }

    public final Lazy getStatusBarInfoControllerEx() {
        return this.statusBarInfoControllerEx;
    }

    public final boolean isFullscreen() {
        ShadeDisplaysRepository shadeDisplaysRepository;
        Lazy lazy;
        PerDisplayInstanceRepositoryImpl perDisplayInstanceRepositoryImpl;
        Lazy lazy2 = this.shadeDisplaysRepository;
        if (lazy2 == null || (shadeDisplaysRepository = (ShadeDisplaysRepository) lazy2.get()) == null || (lazy = this.displaySubComponentRepository) == null || (perDisplayInstanceRepositoryImpl = (PerDisplayRepository) lazy.get()) == null) {
            return false;
        }
        StatusBarWindowState statusBarWindowState = (StatusBarWindowState) ((ReferenceSysUIDisplaySubcomponent) perDisplayInstanceRepositoryImpl.getOrDefault(((Number) shadeDisplaysRepository.getDisplayId().getValue()).intValue())).getStatusBarWindowStateRepository().getWindowState().getValue();
        return statusBarWindowState == StatusBarWindowState.Hidden || statusBarWindowState == StatusBarWindowState.Hiding;
    }

    public final void setBatteryControllerLogger(Lazy lazy) {
        this.batteryControllerLogger = lazy;
    }

    public final void setConfigurationController(Lazy lazy) {
        this.configurationController = lazy;
    }

    public final void setConnectingDisplayEx(Lazy lazy) {
        this.connectingDisplayEx = lazy;
    }

    public final void setDemoStatusIconsEx(Lazy lazy) {
        this.demoStatusIconsEx = lazy;
    }

    public final void setDisplaySubComponentRepository(Lazy lazy) {
        this.displaySubComponentRepository = lazy;
    }

    public final void setHomeStatusBarComponentsRepositoryLazy(Lazy lazy) {
        this.homeStatusBarComponentsRepositoryLazy = lazy;
    }

    public final void setKeyguardStatusBarViewEx(Lazy lazy) {
        this.keyguardStatusBarViewEx = lazy;
    }

    public final void setLocationButtonStatisticUtilImpl(Lazy lazy) {
        this.locationButtonStatisticUtilImpl = lazy;
    }

    public final void setLocationControllerEx(Lazy lazy) {
        this.locationControllerEx = lazy;
    }

    public final void setOplusMobileIconsInteractorExLazy(Lazy lazy) {
        this.oplusMobileIconsInteractorExLazy = lazy;
    }

    public final void setOplusMobileSignalExImpl(Lazy lazy) {
        this.oplusMobileSignalExImpl = lazy;
    }

    public final void setOplusNetworkSpeedController(Lazy lazy) {
        this.oplusNetworkSpeedController = lazy;
    }

    public final void setOplusNotificationIconStatusBarHelperLazy(Lazy lazy) {
        this.oplusNotificationIconStatusBarHelperLazy = lazy;
    }

    public final void setOplusPhoneStatusBarPolicy(Lazy lazy) {
        this.oplusPhoneStatusBarPolicy = lazy;
    }

    public final void setOplusPolicyObserver(Lazy lazy) {
        this.oplusPolicyObserver = lazy;
    }

    public final void setOplusSingleBindableStatusBarComposeIconExLazy(Lazy lazy) {
        this.oplusSingleBindableStatusBarComposeIconExLazy = lazy;
    }

    public final void setOplusStackedMobileIconLayoutStrategyFactoryLazy(Lazy lazy) {
        this.oplusStackedMobileIconLayoutStrategyFactoryLazy = lazy;
    }

    public final void setOplusStackedMobileIconViewModelFactoryLazy(Lazy lazy) {
        this.oplusStackedMobileIconViewModelFactoryLazy = lazy;
    }

    public final void setOplusStatusBarIconController(Lazy lazy) {
        this.oplusStatusBarIconController = lazy;
    }

    public final void setOplusWifiSignalExImpl(Lazy lazy) {
        this.oplusWifiSignalExImpl = lazy;
    }

    public final void setScreenDecorationsEx(Lazy lazy) {
        this.screenDecorationsEx = lazy;
    }

    public final void setSeedingCardControllerEx(Lazy lazy) {
        this.seedingCardControllerEx = lazy;
    }

    public final void setSensorUseStartedActivityEx(Lazy lazy) {
        this.sensorUseStartedActivityEx = lazy;
    }

    public final void setShadeDisplaysRepository(Lazy lazy) {
        this.shadeDisplaysRepository = lazy;
    }

    public final void setStatusBarInfoControllerEx(Lazy lazy) {
        this.statusBarInfoControllerEx = lazy;
    }
}
