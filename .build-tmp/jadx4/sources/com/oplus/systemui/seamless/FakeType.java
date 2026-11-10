package com.oplus.systemui.seamless;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FakeType {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ FakeType[] $VALUES;
    public static final FakeType FAKE_CARRIER;
    public static final FakeType FAKE_CLOCK;
    public static final FakeType FAKE_NOTIFICATION;
    public static final FakeType FAKE_PRIVACY;
    public static final FakeType FAKE_SYSTEM_ICON;
    public static final FakeType KEYGUARD_FAKE_CARRIER;
    public static final FakeType KEYGUARD_FAKE_SYSTEM_ICON;

    static {
        FakeType fakeType = new FakeType("FAKE_CARRIER", 0);
        FAKE_CARRIER = fakeType;
        FakeType fakeType2 = new FakeType("FAKE_CLOCK", 1);
        FAKE_CLOCK = fakeType2;
        FakeType fakeType3 = new FakeType("FAKE_NOTIFICATION", 2);
        FAKE_NOTIFICATION = fakeType3;
        FakeType fakeType4 = new FakeType("FAKE_SYSTEM_ICON", 3);
        FAKE_SYSTEM_ICON = fakeType4;
        FakeType fakeType5 = new FakeType("FAKE_PRIVACY", 4);
        FAKE_PRIVACY = fakeType5;
        FakeType fakeType6 = new FakeType("KEYGUARD_FAKE_CARRIER", 5);
        KEYGUARD_FAKE_CARRIER = fakeType6;
        FakeType fakeType7 = new FakeType("KEYGUARD_FAKE_SYSTEM_ICON", 6);
        KEYGUARD_FAKE_SYSTEM_ICON = fakeType7;
        FakeType[] fakeTypeArr = {fakeType, fakeType2, fakeType3, fakeType4, fakeType5, fakeType6, fakeType7};
        $VALUES = fakeTypeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(fakeTypeArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static FakeType valueOf(String str) {
        return (FakeType) Enum.valueOf(FakeType.class, str);
    }

    public static FakeType[] values() {
        return (FakeType[]) $VALUES.clone();
    }
}
