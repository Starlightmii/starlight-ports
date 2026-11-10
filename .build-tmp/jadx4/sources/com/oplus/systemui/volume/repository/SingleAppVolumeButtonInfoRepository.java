package com.oplus.systemui.volume.repository;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SingleAppVolumeButtonInfoRepository {
    public static final Companion Companion = new Companion();
    private static final String KEY_ATTACHED_EDGE = "attached_edge";
    private static final String KEY_POSITION_Y = "position_y";
    private static final String PREF_NAME = "single_app_volume_button_prefs";
    private static final String TAG = "SingleAppVolumeButtonInfoRepository";
    private Context context;
    private SharedPreferences prefs;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Edge {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ Edge[] $VALUES;
        public static final Edge LEFT;
        public static final Edge RIGHT;

        static {
            Edge edge = new Edge("LEFT", 0);
            LEFT = edge;
            Edge edge2 = new Edge("RIGHT", 1);
            RIGHT = edge2;
            Edge[] edgeArr = {edge, edge2};
            $VALUES = edgeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(edgeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static Edge valueOf(String str) {
            return (Edge) Enum.valueOf(Edge.class, str);
        }

        public static Edge[] values() {
            return (Edge[]) $VALUES.clone();
        }
    }

    public SingleAppVolumeButtonInfoRepository(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(PREF_NAME, 0);
    }

    public final Edge loadAttachedEdge() {
        String string = this.prefs.getString(KEY_ATTACHED_EDGE, "LEFT");
        return Edge.valueOf(string != null ? string : "LEFT");
    }

    public final float loadPositionY() {
        return this.prefs.getFloat(KEY_POSITION_Y, 0.2f);
    }

    public final void onUserSwitched(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(PREF_NAME, 0);
    }

    public final void save(float f, Edge edge) {
        this.prefs.edit().putFloat(KEY_POSITION_Y, f).apply();
        this.prefs.edit().putString(KEY_ATTACHED_EDGE, edge.name()).apply();
    }
}
