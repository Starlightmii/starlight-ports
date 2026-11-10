package com.oplus.systemui.statusbar.pipeline.battery;

import android.R;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.statusbar.pipeline.battery.ui.view.StatBatteryMeterView;
import com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel;
import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatBatteryMeterViewController extends ViewController {
    private final LocationBasedBatteryViewModel batteryViewModel;
    public boolean ignoreTunerUpdates;
    public boolean isSubscribedForTunerUpdates;
    private final String slotBattery;
    private final TunerService.Tunable tunable;
    private final TunerService tunerService;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.StatBatteryMeterViewController$onViewAttached$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1 {
        public final Object invoke(Object obj) {
            return Integer.valueOf(((SystemTypefaceHelper) ((CallableReference) this).receiver).mapStatusCommonTextFontWeight(((Number) obj).intValue()));
        }
    }

    public StatBatteryMeterViewController(StatBatteryMeterView statBatteryMeterView, LocationBasedBatteryViewModel locationBasedBatteryViewModel, TunerService tunerService) {
        super(statBatteryMeterView);
        this.batteryViewModel = locationBasedBatteryViewModel;
        this.tunerService = tunerService;
        this.slotBattery = getResources().getString(R.string.wifi_available_sign_in);
        StatBatteryMeterViewController$tunable$1 statBatteryMeterViewController$tunable$1 = new StatBatteryMeterViewController$tunable$1();
        statBatteryMeterViewController$tunable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.tunable = statBatteryMeterViewController$tunable$1;
    }

    public final void ignoreTunerUpdates() {
        this.ignoreTunerUpdates = true;
        if (this.isSubscribedForTunerUpdates) {
            this.tunerService.removeTunable(this.tunable);
            this.isSubscribedForTunerUpdates = false;
        }
    }

    public final void onInit() {
        ((StatBatteryMeterView) ((ViewController) this).mView).bindViewModel(this.batteryViewModel);
    }

    public final void onViewAttached() {
        if (!this.isSubscribedForTunerUpdates && !this.ignoreTunerUpdates) {
            this.tunerService.addTunable(this.tunable, new String[]{"icon_blacklist"});
            this.isSubscribedForTunerUpdates = true;
        }
        SystemTypefaceHelper.Companion companion = SystemTypefaceHelper.Companion;
        companion.getInstance(getContext()).addCallback((SystemTypefaceHelper.SystemUIFontCallback) ((ViewController) this).mView, new AnonymousClass1(1, companion.getInstance(getContext()), SystemTypefaceHelper.class, "mapStatusCommonTextFontWeight", "mapStatusCommonTextFontWeight(I)I", 0));
    }

    public final void onViewDetached() {
        if (this.isSubscribedForTunerUpdates) {
            this.tunerService.removeTunable(this.tunable);
            this.isSubscribedForTunerUpdates = false;
        }
        SystemTypefaceHelper.Companion.getInstance(getContext()).removeCallback((SystemTypefaceHelper.SystemUIFontCallback) ((ViewController) this).mView);
    }
}
