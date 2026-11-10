package com.oplus.systemui.qs.pipeline.data.event;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepository;
import com.oplus.systemui.qs.pipeline.data.model.TileModifyEvent;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.TuplesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AlexaTileAutoAddEvent implements TileModifyEvent {
    private static final String ALEXA_TILE_DATA_KEY = "alexa_data_key";
    private static final String ALEXA_TILE_PACKAGE = "com.amazon.dee.app";
    private static final String ALEXA_TILE_SP_KEY = "custom_alexa_key";
    public static final Companion Companion = new Companion();
    private static final String TAG = "AlexaTileAutoAddEvent";
    private final CoroutineContext bgCoroutineContext;
    private final InstalledTilesComponentRepository installedTilesComponents;
    private final Lazy sharedPreferences$delegate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public AlexaTileAutoAddEvent(Context context, CoroutineContext coroutineContext, InstalledTilesComponentRepository installedTilesComponentRepository) {
        this.bgCoroutineContext = coroutineContext;
        this.installedTilesComponents = installedTilesComponentRepository;
        AlexaTileAutoAddEvent$$ExternalSyntheticLambda0 alexaTileAutoAddEvent$$ExternalSyntheticLambda0 = new AlexaTileAutoAddEvent$$ExternalSyntheticLambda0();
        alexaTileAutoAddEvent$$ExternalSyntheticLambda0.f$0 = context;
        VarHandle.storeStoreFence();
        this.sharedPreferences$delegate = LazyKt__LazyJVMKt.lazy(alexaTileAutoAddEvent$$ExternalSyntheticLambda0);
    }

    private final SharedPreferences getSharedPreferences() {
        return (SharedPreferences) this.sharedPreferences$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAlexaTileAdded(boolean z) {
        SharedPreferences.Editor editorEdit = getSharedPreferences().edit();
        editorEdit.putBoolean(ALEXA_TILE_DATA_KEY, z);
        editorEdit.commit();
    }

    @Override // com.oplus.systemui.qs.pipeline.data.model.TileModifyEvent
    public Flow modifyTilesSignal(int i) {
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (!FlavorOneFeatureOption.sIsAlexaSupport) {
            return FlowKt.emptyFlow();
        }
        Flow installedTilesComponents = this.installedTilesComponents.getInstalledTilesComponents(i);
        AlexaTileAutoAddEvent$modifyTilesSignal$.inlined.filter.1 r0 = new AlexaTileAutoAddEvent$modifyTilesSignal$.inlined.filter.1(0);
        r0.$this_unsafeTransform$inlined = installedTilesComponents;
        VarHandle.storeStoreFence();
        AlexaTileAutoAddEvent$modifyTilesSignal$.inlined.map.1 r4 = new AlexaTileAutoAddEvent$modifyTilesSignal$.inlined.map.1(0);
        r4.$this_unsafeTransform$inlined = r0;
        VarHandle.storeStoreFence();
        Flow flowScan = FlowKt.scan(FlowKt.distinctUntilChanged(r4), TuplesKt.to(Boolean.valueOf(getSharedPreferences().getBoolean(ALEXA_TILE_DATA_KEY, false)), (Object) null), new modifyTilesSignal.3(this, (Continuation) null));
        AlexaTileAutoAddEvent$modifyTilesSignal$.inlined.filter.1 r1 = new AlexaTileAutoAddEvent$modifyTilesSignal$.inlined.filter.1(1);
        r1.$this_unsafeTransform$inlined = flowScan;
        VarHandle.storeStoreFence();
        AlexaTileAutoAddEvent$modifyTilesSignal$.inlined.map.1 r5 = new AlexaTileAutoAddEvent$modifyTilesSignal$.inlined.map.1(1);
        r5.$this_unsafeTransform$inlined = r1;
        VarHandle.storeStoreFence();
        return FlowKt.flowOn(r5, this.bgCoroutineContext);
    }
}
