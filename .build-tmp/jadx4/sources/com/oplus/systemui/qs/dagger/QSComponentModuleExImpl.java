package com.oplus.systemui.qs.dagger;

import android.view.View;
import com.android.systemui.privacy.OngoingPrivacyChip;
import com.android.systemui.qs.IQSContainerImplController;
import com.android.systemui.qs.deprecated.BaseQuickStatusBarHeader;
import com.android.systemui.qs.deprecated.QSPanel;
import com.android.systemui.qs.deprecated.QuickQSPanel;
import com.android.systemui.qs.deprecated.customize.IQSCustomizerController;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.tuner.TunerService;
import com.oplus.systemui.qs.OplusQSBottomImpl;
import com.oplus.systemui.qs.OplusQSContainerImpl;
import com.oplus.systemui.qs.OplusQSContainerImplController;
import com.oplus.systemui.qs.OplusQSFooterImpl;
import com.oplus.systemui.qs.OplusQSPanelContainer;
import com.oplus.systemui.qs.OplusQSTileMediaContainer;
import com.oplus.systemui.qs.OplusSysUiCustomImpl;
import com.oplus.systemui.qs.customize.OplusQSCustomizer;
import com.oplus.systemui.qs.customize.OplusQSCustomizerController;
import com.oplus.systemui.qs.fake.view.QsFakeLayout;
import com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView;
import com.oplus.systemui.statusbar.pipeline.battery.StatBatteryMeterViewController;
import com.oplus.systemui.statusbar.pipeline.battery.ui.view.StatBatteryMeterView;
import com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface QSComponentModuleExImpl {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String QS_USING_ORIGIN_BRIGHTNESS = "qs_using_origin_brightness";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String QS_USING_ORIGIN_BRIGHTNESS = "qs_using_origin_brightness";

        public final QSPanel provideQSPanel(View view) {
            return view.requireViewById(2131364821);
        }

        public final OplusQsBaseMediaPanelView provideQsBaseMediaPanelView(View view) {
            return (OplusQsBaseMediaPanelView) view.requireViewById(2131364398);
        }

        public final StatBatteryMeterViewController provideStatBatteryMeterViewController(StatBatteryMeterView statBatteryMeterView, LocationBasedBatteryViewModel locationBasedBatteryViewModel, TunerService tunerService) {
            return new StatBatteryMeterViewController(statBatteryMeterView, locationBasedBatteryViewModel, tunerService);
        }

        public final BaseQuickStatusBarHeader providesBaseQuickStatusBarHeader(View view) {
            return view.requireViewById(2131363334);
        }

        public final StatBatteryMeterView providesBatteryMeterView(BaseQuickStatusBarHeader baseQuickStatusBarHeader) {
            return (StatBatteryMeterView) baseQuickStatusBarHeader.requireViewById(2131362234);
        }

        public final OplusQSBottomImpl providesOplusQSBottom(View view) {
            return (OplusQSBottomImpl) view.requireViewById(2131364711);
        }

        public final OplusQSCustomizer providesOplusQSCutomizer(View view) {
            return (OplusQSCustomizer) view.requireViewById(2131364724);
        }

        public final OngoingPrivacyChip providesPrivacyChip(BaseQuickStatusBarHeader baseQuickStatusBarHeader) {
            return baseQuickStatusBarHeader.requireViewById(2131364644);
        }

        public final OplusQSContainerImpl providesQSContainerImpl(View view) {
            return (OplusQSContainerImpl) view.requireViewById(2131364820);
        }

        public final IQSContainerImplController providesQSContainerImplController(OplusQSContainerImplController oplusQSContainerImplController) {
            return oplusQSContainerImplController;
        }

        public final IQSCustomizerController providesQSCustomizerController(OplusQSCustomizerController oplusQSCustomizerController) {
            return oplusQSCustomizerController;
        }

        public final OplusQSFooterImpl providesQSFooterView(View view) {
            return (OplusQSFooterImpl) view.requireViewById(2131364763);
        }

        public final boolean providesQSUsingSysUiOrigin(OplusSysUiCustomImpl oplusSysUiCustomImpl) {
            oplusSysUiCustomImpl.getClass();
            return false;
        }

        public final QsFakeLayout providesQsFakeLayout(View view) {
            return (QsFakeLayout) view.requireViewById(2131364753);
        }

        public final OplusQSPanelContainer providesQsPanelContainer(View view) {
            return (OplusQSPanelContainer) view.requireViewById(2131363092);
        }

        public final QuickQSPanel providesQuickQSPanel(OplusQSPanelContainer oplusQSPanelContainer) {
            return oplusQSPanelContainer.requireViewById(2131364817);
        }

        public final StatusIconContainer providesStatusIconContainer(BaseQuickStatusBarHeader baseQuickStatusBarHeader) {
            return baseQuickStatusBarHeader.requireViewById(2131365358);
        }

        public OplusQSTileMediaContainer providesTileMediaContainer(View view) {
            return (OplusQSTileMediaContainer) view.requireViewById(2131364407);
        }
    }

    static QSPanel provideQSPanel(View view) {
        return Companion.provideQSPanel(view);
    }
}
