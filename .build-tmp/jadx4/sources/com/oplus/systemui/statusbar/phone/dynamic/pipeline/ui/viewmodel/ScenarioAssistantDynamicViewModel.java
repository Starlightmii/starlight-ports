package com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemProperties;
import com.oplus.systemui.scenario.host.api.data.model.ScenarioInfo;
import com.oplus.systemui.scenario.host.api.data.repository.ScenarioAssistantRepository;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioAssistantDynamicViewModel implements DynamicIconViewModel {
    public static final Companion Companion = new Companion();
    private static final String PREF_FILE_NAME = "scenario_assistant_dynamic_prefs";
    private static final String TAG = "ScenarioAssistantDynamicViewModel";
    private ScenarioInfo current;
    private final StateFlow iconModel;
    public final SharedPreferences prefs;
    private final String slot;
    public final int debugAlertType = SystemProperties.getInt("debug.scenario.show_status_tip_type", 0);
    private final Map<String, Long> alertTimestamp = new LinkedHashMap();
    private final Map<String, Boolean> hasShownRecord = new LinkedHashMap();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ScenarioAssistantDynamicViewModel(Context context, CoroutineScope coroutineScope, ScenarioAssistantRepository scenarioAssistantRepository) {
        this.slot = context.getResources().getString(2131956633);
        this.prefs = context.getSharedPreferences(PREF_FILE_NAME, 0);
        StateFlow scenarioInfo = scenarioAssistantRepository.getScenarioInfo();
        ScenarioAssistantDynamicViewModel$special$$inlined$map$1 scenarioAssistantDynamicViewModel$special$$inlined$map$1 = new ScenarioAssistantDynamicViewModel$special$$inlined$map$1();
        scenarioAssistantDynamicViewModel$special$$inlined$map$1.$this_unsafeTransform$inlined = scenarioInfo;
        scenarioAssistantDynamicViewModel$special$$inlined$map$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.iconModel = FlowKt.stateIn(FlowKt.distinctUntilChanged(scenarioAssistantDynamicViewModel$special$$inlined$map$1), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), (Object) null);
    }

    public static final boolean access$getAndSaveHasShownState(ScenarioAssistantDynamicViewModel scenarioAssistantDynamicViewModel, String str) {
        Map<String, Boolean> map = scenarioAssistantDynamicViewModel.hasShownRecord;
        Boolean boolValueOf = map.get(str);
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(scenarioAssistantDynamicViewModel.prefs.getBoolean(str, false));
            map.put(str, boolValueOf);
        }
        boolean zBooleanValue = boolValueOf.booleanValue();
        if (!zBooleanValue) {
            scenarioAssistantDynamicViewModel.hasShownRecord.put(str, Boolean.TRUE);
            SharedPreferences.Editor editorEdit = scenarioAssistantDynamicViewModel.prefs.edit();
            editorEdit.putBoolean(str, true);
            editorEdit.apply();
        }
        return zBooleanValue;
    }

    @Override // com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel.DynamicIconViewModel
    public StateFlow getIconModel() {
        return this.iconModel;
    }
}
