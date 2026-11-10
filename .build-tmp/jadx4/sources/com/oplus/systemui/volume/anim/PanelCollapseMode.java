package com.oplus.systemui.volume.anim;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PanelCollapseMode {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ PanelCollapseMode[] $VALUES;
    public static final PanelCollapseMode CANCEL_EXPAND;
    public static final PanelCollapseMode HANDOFF;
    public static final PanelCollapseMode NORMAL_PANEL;

    static {
        PanelCollapseMode panelCollapseMode = new PanelCollapseMode("CANCEL_EXPAND", 0);
        CANCEL_EXPAND = panelCollapseMode;
        PanelCollapseMode panelCollapseMode2 = new PanelCollapseMode("HANDOFF", 1);
        HANDOFF = panelCollapseMode2;
        PanelCollapseMode panelCollapseMode3 = new PanelCollapseMode("NORMAL_PANEL", 2);
        NORMAL_PANEL = panelCollapseMode3;
        PanelCollapseMode[] panelCollapseModeArr = {panelCollapseMode, panelCollapseMode2, panelCollapseMode3};
        $VALUES = panelCollapseModeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(panelCollapseModeArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static PanelCollapseMode valueOf(String str) {
        return (PanelCollapseMode) Enum.valueOf(PanelCollapseMode.class, str);
    }

    public static PanelCollapseMode[] values() {
        return (PanelCollapseMode[]) $VALUES.clone();
    }
}
