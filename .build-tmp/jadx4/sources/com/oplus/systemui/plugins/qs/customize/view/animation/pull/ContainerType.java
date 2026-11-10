package com.oplus.systemui.plugins.qs.customize.view.animation.pull;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ContainerType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ ContainerType[] $VALUES;
    public static final ContainerType NO_PERSONAL_LAND_LEFT;
    public static final ContainerType NO_PERSONAL_PORT_OR_LAND_RIGHT;
    public static final ContainerType PERSONAL;

    static {
        ContainerType containerType = new ContainerType("PERSONAL", 0);
        PERSONAL = containerType;
        ContainerType containerType2 = new ContainerType("NO_PERSONAL_PORT_OR_LAND_RIGHT", 1);
        NO_PERSONAL_PORT_OR_LAND_RIGHT = containerType2;
        ContainerType containerType3 = new ContainerType("NO_PERSONAL_LAND_LEFT", 2);
        NO_PERSONAL_LAND_LEFT = containerType3;
        ContainerType[] containerTypeArr = {containerType, containerType2, containerType3};
        $VALUES = containerTypeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(containerTypeArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static ContainerType valueOf(String str) {
        return (ContainerType) Enum.valueOf(ContainerType.class, str);
    }

    public static ContainerType[] values() {
        return (ContainerType[]) $VALUES.clone();
    }
}
