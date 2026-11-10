package com.oplus.systemui.qs.pipeline.data.event;

import android.content.ComponentName;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepository;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SettingsProxyExt;
import com.oplus.systemui.qs.pipeline.data.model.TileModifyEvent;
import java.lang.invoke.VarHandle;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VoWifiTileAutoAddEvent implements TileModifyEvent {
    public static final Companion Companion = new Companion();
    private static final String SETTINGS_KEY_STRING = "custom_vowifi_tileservice_enable";
    private static final String SETTINGS_KEY_STRING_SUPPORT = "custom_vowifi_tileservice_support";
    private static final String TAG = "VoWifiTileAutoAddEvent";
    private final CoroutineContext bgCoroutineContext;
    private final InstalledTilesComponentRepository installedTilesComponents;
    private final SecureSettings secureSettings;
    private final Pair<TileSpec, ComponentName> tileVoWifeOneInfo;
    private final Pair<TileSpec, ComponentName> tileVoWifeTwoInfo;
    private final Pair<TileSpec, ComponentName> tileVoWifiInfo;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public VoWifiTileAutoAddEvent(SecureSettings secureSettings, CoroutineContext coroutineContext, InstalledTilesComponentRepository installedTilesComponentRepository) {
        this.secureSettings = secureSettings;
        this.bgCoroutineContext = coroutineContext;
        this.installedTilesComponents = installedTilesComponentRepository;
        TileSpec.Companion companion = TileSpec.Companion;
        this.tileVoWifiInfo = TuplesKt.to(companion.create("custom(com.android.phone/com.android.simsettings.demands.tileservice.WifiCallingTileService)"), CustomTile.getComponentFromSpec("custom(com.android.phone/com.android.simsettings.demands.tileservice.WifiCallingTileService)"));
        this.tileVoWifeOneInfo = TuplesKt.to(companion.create("custom(com.android.phone/com.android.simsettings.demands.tileservice.WifiCallingTileServiceSlotOne)"), CustomTile.getComponentFromSpec("custom(com.android.phone/com.android.simsettings.demands.tileservice.WifiCallingTileServiceSlotOne)"));
        this.tileVoWifeTwoInfo = TuplesKt.to(companion.create("custom(com.android.phone/com.android.simsettings.demands.tileservice.WifiCallingTileServiceSlotTwo)"), CustomTile.getComponentFromSpec("custom(com.android.phone/com.android.simsettings.demands.tileservice.WifiCallingTileServiceSlotTwo)"));
    }

    public static final boolean access$isVoWifiEnabled(VoWifiTileAutoAddEvent voWifiTileAutoAddEvent, int i) {
        return voWifiTileAutoAddEvent.secureSettings.getIntForUser(SETTINGS_KEY_STRING, 0, i) == 1;
    }

    @Override // com.oplus.systemui.qs.pipeline.data.model.TileModifyEvent
    public Flow modifyTilesSignal(int i) {
        SettingsProxyExt settingsProxyExt = SettingsProxyExt.INSTANCE;
        Flow flowObserverFlow = settingsProxyExt.observerFlow(this.secureSettings, i, new String[]{SETTINGS_KEY_STRING_SUPPORT});
        VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$1 voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$1 = new VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$1(1);
        voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$1.$this_unsafeTransform$inlined = flowObserverFlow;
        voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$1.this$0 = this;
        voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$1.$userId$inlined = i;
        VarHandle.storeStoreFence();
        Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.onEach(voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$1, new VoWifiTileAutoAddEvent$observeFlowForVoWifiSupport$2(i, null)));
        VoWifiTileAutoAddEvent$observeFlowForVoWifiSupport$$inlined$map$2 voWifiTileAutoAddEvent$observeFlowForVoWifiSupport$$inlined$map$2 = new VoWifiTileAutoAddEvent$observeFlowForVoWifiSupport$$inlined$map$2();
        voWifiTileAutoAddEvent$observeFlowForVoWifiSupport$$inlined$map$2.$this_unsafeTransform$inlined = flowDistinctUntilChanged;
        voWifiTileAutoAddEvent$observeFlowForVoWifiSupport$$inlined$map$2.this$0 = this;
        VarHandle.storeStoreFence();
        Flow flowFlowOn = FlowKt.flowOn(FlowKt.filterNotNull(FlowKt.flatMapConcat(FlowKt.onEach(voWifiTileAutoAddEvent$observeFlowForVoWifiSupport$$inlined$map$2, new VoWifiTileAutoAddEvent$observeFlowForVoWifiSupport$4(2, null)), new VoWifiTileAutoAddEvent$observeFlowForVoWifiSupport$5(2, null))), this.bgCoroutineContext);
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
        Ref.BooleanRef booleanRef3 = new Ref.BooleanRef();
        Flow flowObserverFlow2 = settingsProxyExt.observerFlow(this.secureSettings, i, new String[]{SETTINGS_KEY_STRING});
        VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$1 voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$2 = new VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$1(0);
        voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$2.$this_unsafeTransform$inlined = flowObserverFlow2;
        voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$2.this$0 = this;
        voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$2.$userId$inlined = i;
        VarHandle.storeStoreFence();
        Flow flowTransformLatest = FlowKt.transformLatest(FlowKt.distinctUntilChanged(FlowKt.onEach(voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$2, new VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$2(i, null))), new VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$flatMapLatest$1(null, this, i, booleanRef, booleanRef2, booleanRef3));
        VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$2 voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$3 = new VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$2();
        voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$3.$this_unsafeTransform$inlined = flowTransformLatest;
        voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$3.$isAddVoWifi$inlined = booleanRef;
        voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$3.this$0 = this;
        voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$3.$isAddVoWifiTwo$inlined = booleanRef2;
        voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$3.$isAddVoWifiOne$inlined = booleanRef3;
        VarHandle.storeStoreFence();
        return FlowKt.merge(new Flow[]{flowFlowOn, FlowKt.flowOn(FlowKt.filterNotNull(FlowKt.flatMapConcat(FlowKt.onEach(voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$3, new VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$5(2, null)), new VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$6(2, null))), this.bgCoroutineContext)});
    }
}
