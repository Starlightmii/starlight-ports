package com.oplus.systemui.qs.scenario.domain.tile;

import com.oplus.systemui.scenario.host.api.data.model.ScenarioTileActionInfo;
import com.oplus.systemui.scenario.host.api.data.model.ScenarioTileInlineActionInfo;
import com.oplus.systemui.scenario.host.api.data.model.ScenarioTileIntentActionInfo;
import com.oplus.systemui.scenario.host.api.data.model.ScenarioTilePluginActionInfo;
import com.oplus.systemui.scenario.host.api.data.model.ScenarioTileProviderActionInfo;
import com.oplus.systemui.scenario.host.api.data.model.ScenarioTileSettingsActionInfo;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioTileActionProviderFactory implements ScenarioTileActionProvider$Factory {
    private final ScenarioTileContentProvider$ScenarioTileContentProviderFactory contentProviderFactory;
    private final ScenarioTileInlineProvider$ScenarioTileInlineProviderFactory inlineProviderFactory;
    private final ScenarioTileIntentProvider$ScenarioTileIntentProviderFactory intentProviderFactory;
    private final ScenarioTilePluginProvider$ScenarioTilePluginProviderFactory pluginProviderFactory;
    private final ScenarioTileSettingsProvider$ScenarioTileSettingsProviderFactory settingsProviderFactory;

    public ScenarioTileActionProviderFactory(ScenarioTileSettingsProvider$ScenarioTileSettingsProviderFactory scenarioTileSettingsProvider$ScenarioTileSettingsProviderFactory, ScenarioTileContentProvider$ScenarioTileContentProviderFactory scenarioTileContentProvider$ScenarioTileContentProviderFactory, ScenarioTileIntentProvider$ScenarioTileIntentProviderFactory scenarioTileIntentProvider$ScenarioTileIntentProviderFactory, ScenarioTilePluginProvider$ScenarioTilePluginProviderFactory scenarioTilePluginProvider$ScenarioTilePluginProviderFactory, ScenarioTileInlineProvider$ScenarioTileInlineProviderFactory scenarioTileInlineProvider$ScenarioTileInlineProviderFactory) {
        this.settingsProviderFactory = scenarioTileSettingsProvider$ScenarioTileSettingsProviderFactory;
        this.contentProviderFactory = scenarioTileContentProvider$ScenarioTileContentProviderFactory;
        this.intentProviderFactory = scenarioTileIntentProvider$ScenarioTileIntentProviderFactory;
        this.pluginProviderFactory = scenarioTilePluginProvider$ScenarioTilePluginProviderFactory;
        this.inlineProviderFactory = scenarioTileInlineProvider$ScenarioTileInlineProviderFactory;
    }

    @Override // com.oplus.systemui.qs.scenario.domain.tile.ScenarioTileActionProvider$Factory
    public ScenarioTileActionProvider create(ScenarioTileActionInfo scenarioTileActionInfo) {
        if (scenarioTileActionInfo instanceof ScenarioTileSettingsActionInfo) {
            return this.settingsProviderFactory.create((ScenarioTileSettingsActionInfo) scenarioTileActionInfo);
        }
        if (scenarioTileActionInfo instanceof ScenarioTileProviderActionInfo) {
            return this.contentProviderFactory.create((ScenarioTileProviderActionInfo) scenarioTileActionInfo);
        }
        if (scenarioTileActionInfo instanceof ScenarioTileIntentActionInfo) {
            return this.intentProviderFactory.create((ScenarioTileIntentActionInfo) scenarioTileActionInfo);
        }
        if (scenarioTileActionInfo instanceof ScenarioTilePluginActionInfo) {
            return this.pluginProviderFactory.create((ScenarioTilePluginActionInfo) scenarioTileActionInfo);
        }
        if (scenarioTileActionInfo instanceof ScenarioTileInlineActionInfo) {
            return this.inlineProviderFactory.create((ScenarioTileInlineActionInfo) scenarioTileActionInfo);
        }
        return null;
    }
}
