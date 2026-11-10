package com.oplus.systemui.notification.lockscreen.notification;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleInternalAnimHelper$CapsuleAnimScene {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ CapsuleInternalAnimHelper$CapsuleAnimScene[] $VALUES;
    public static final CapsuleInternalAnimHelper$CapsuleAnimScene AGGREGATE_TO_AGGREGATE;
    public static final CapsuleInternalAnimHelper$CapsuleAnimScene COMMON_TRANSFORMATION;
    public static final CapsuleInternalAnimHelper$CapsuleAnimScene GROUP_TO_GROUP;
    public static final CapsuleInternalAnimHelper$CapsuleAnimScene GROUP_TO_MESSAGE_SINGLE;
    public static final CapsuleInternalAnimHelper$CapsuleAnimScene GROUP_TO_SINGLE;

    static {
        CapsuleInternalAnimHelper$CapsuleAnimScene capsuleInternalAnimHelper$CapsuleAnimScene = new CapsuleInternalAnimHelper$CapsuleAnimScene("GROUP_TO_SINGLE", 0);
        GROUP_TO_SINGLE = capsuleInternalAnimHelper$CapsuleAnimScene;
        CapsuleInternalAnimHelper$CapsuleAnimScene capsuleInternalAnimHelper$CapsuleAnimScene2 = new CapsuleInternalAnimHelper$CapsuleAnimScene("GROUP_TO_MESSAGE_SINGLE", 1);
        GROUP_TO_MESSAGE_SINGLE = capsuleInternalAnimHelper$CapsuleAnimScene2;
        CapsuleInternalAnimHelper$CapsuleAnimScene capsuleInternalAnimHelper$CapsuleAnimScene3 = new CapsuleInternalAnimHelper$CapsuleAnimScene("GROUP_TO_GROUP", 2);
        GROUP_TO_GROUP = capsuleInternalAnimHelper$CapsuleAnimScene3;
        CapsuleInternalAnimHelper$CapsuleAnimScene capsuleInternalAnimHelper$CapsuleAnimScene4 = new CapsuleInternalAnimHelper$CapsuleAnimScene("AGGREGATE_TO_AGGREGATE", 3);
        AGGREGATE_TO_AGGREGATE = capsuleInternalAnimHelper$CapsuleAnimScene4;
        CapsuleInternalAnimHelper$CapsuleAnimScene capsuleInternalAnimHelper$CapsuleAnimScene5 = new CapsuleInternalAnimHelper$CapsuleAnimScene("COMMON_TRANSFORMATION", 4);
        COMMON_TRANSFORMATION = capsuleInternalAnimHelper$CapsuleAnimScene5;
        CapsuleInternalAnimHelper$CapsuleAnimScene[] capsuleInternalAnimHelper$CapsuleAnimSceneArr = {capsuleInternalAnimHelper$CapsuleAnimScene, capsuleInternalAnimHelper$CapsuleAnimScene2, capsuleInternalAnimHelper$CapsuleAnimScene3, capsuleInternalAnimHelper$CapsuleAnimScene4, capsuleInternalAnimHelper$CapsuleAnimScene5};
        $VALUES = capsuleInternalAnimHelper$CapsuleAnimSceneArr;
        $ENTRIES = EnumEntriesKt.enumEntries(capsuleInternalAnimHelper$CapsuleAnimSceneArr);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static CapsuleInternalAnimHelper$CapsuleAnimScene valueOf(String str) {
        return (CapsuleInternalAnimHelper$CapsuleAnimScene) Enum.valueOf(CapsuleInternalAnimHelper$CapsuleAnimScene.class, str);
    }

    public static CapsuleInternalAnimHelper$CapsuleAnimScene[] values() {
        return (CapsuleInternalAnimHelper$CapsuleAnimScene[]) $VALUES.clone();
    }
}
