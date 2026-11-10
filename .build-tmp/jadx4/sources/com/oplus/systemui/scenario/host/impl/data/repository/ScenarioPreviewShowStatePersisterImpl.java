package com.oplus.systemui.scenario.host.impl.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import com.oplus.systemui.scenario.host.api.data.repository.ScenarioPreviewShowStatePersister;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioPreviewShowStatePersisterImpl implements ScenarioPreviewShowStatePersister {
    public static final Companion Companion = new Companion();
    private static final String FILE_NAME = "scenario_preview_show_state";
    private final SharedPreferences sharedPreferences;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ScenarioPreviewShowStatePersisterImpl(Context context) {
        this.sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
    }

    @Override // com.oplus.systemui.scenario.host.api.data.repository.ScenarioPreviewShowStatePersister
    public Boolean getState(String str) {
        return Boolean.valueOf(this.sharedPreferences.getBoolean(str, false));
    }

    @Override // com.oplus.systemui.scenario.host.api.data.repository.ScenarioPreviewShowStatePersister
    public void removeState(String str) {
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.remove(str);
        editorEdit.apply();
    }

    @Override // com.oplus.systemui.scenario.host.api.data.repository.ScenarioPreviewShowStatePersister
    public void setState(String str, boolean z) {
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putBoolean(str, z);
        editorEdit.apply();
    }
}
