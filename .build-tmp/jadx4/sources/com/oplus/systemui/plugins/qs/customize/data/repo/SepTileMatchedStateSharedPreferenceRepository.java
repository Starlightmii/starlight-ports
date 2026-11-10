package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepTileMatchedStateSharedPreferenceRepository {
    public static final Companion Companion = new Companion();
    private static final String FILE_NAME = "sep_tiles_prefs";
    private static final String KEY_MATCHED = "matched";
    private final OplusSeparateQSPlugin.IStub stub;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SepTileMatchedStateSharedPreferenceRepository(OplusSeparateQSPlugin.IStub iStub) {
        this.stub = iStub;
    }

    public final boolean isMatched(int i) {
        return this.stub.getSharedPreferences(FILE_NAME, 0, i).getBoolean(KEY_MATCHED, false);
    }

    public final void setMatched(int i, boolean z) {
        this.stub.getSharedPreferences(FILE_NAME, 0, i).edit().putBoolean(KEY_MATCHED, z).apply();
    }
}
