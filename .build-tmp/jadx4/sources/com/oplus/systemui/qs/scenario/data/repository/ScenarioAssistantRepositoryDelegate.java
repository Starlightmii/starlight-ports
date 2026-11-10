package com.oplus.systemui.qs.scenario.data.repository;

import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.oplus.systemui.scenario.host.api.data.repository.ScenarioAssistantRepository;
import com.oplus.systemui.scenario.host.impl.data.repository.ScenarioAssistantRepositoryImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioAssistantRepositoryDelegate implements ScenarioAssistantRepository {
    public final /* synthetic */ ScenarioAssistantRepositoryImpl $$delegate_0;
    private final CoroutineScope backgroundScope;
    private final StateFlow isScenarioSupport;
    private final StateFlow scenarioInfo;

    public ScenarioAssistantRepositoryDelegate(ScenarioAssistantRepositoryImpl scenarioAssistantRepositoryImpl, CoroutineScope coroutineScope, KeyguardInteractor keyguardInteractor, SuperPowerSaveRepository superPowerSaveRepository) {
        this.$$delegate_0 = scenarioAssistantRepositoryImpl;
        this.backgroundScope = coroutineScope;
        Flow flowTransformLatest = FlowKt.transformLatest(scenarioAssistantRepositoryImpl.isScenarioSupport(), new ScenarioAssistantRepositoryDelegate$special$$inlined$flatMapLatest$1(null, superPowerSaveRepository, keyguardInteractor));
        SharingStarted.Companion companion = SharingStarted.Companion;
        this.isScenarioSupport = FlowKt.stateIn(flowTransformLatest, coroutineScope, companion.getEagerly(), scenarioAssistantRepositoryImpl.isScenarioSupport().getValue());
        this.scenarioInfo = FlowKt.stateIn(FlowKt.transformLatest(isScenarioSupport(), new ScenarioAssistantRepositoryDelegate$special$$inlined$flatMapLatest$2(scenarioAssistantRepositoryImpl, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), (Object) null);
    }

    @Override // com.oplus.systemui.scenario.host.api.data.repository.ScenarioAssistantRepository
    public StateFlow getScenarioInfo() {
        return this.scenarioInfo;
    }

    @Override // com.oplus.systemui.scenario.host.api.data.repository.ScenarioAssistantRepository
    public StateFlow isMirrorShowing() {
        return this.$$delegate_0.isMirrorShowing();
    }

    @Override // com.oplus.systemui.scenario.host.api.data.repository.ScenarioAssistantRepository
    public StateFlow isScenarioSupport() {
        return this.isScenarioSupport;
    }

    @Override // com.oplus.systemui.scenario.host.api.data.repository.ScenarioAssistantRepository
    public void setMirrorShowing(boolean z) {
        this.$$delegate_0.setMirrorShowing(z);
    }
}
