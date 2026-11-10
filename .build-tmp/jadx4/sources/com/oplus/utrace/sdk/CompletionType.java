package com.oplus.utrace.sdk;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CompletionType {
    public static final /* synthetic */ CompletionType[] $VALUES;
    public static final CompletionType COMPLETE;
    public static final CompletionType GOAHEAD;
    public static final CompletionType RETURN;

    static {
        CompletionType completionType = new CompletionType("GOAHEAD", 0);
        GOAHEAD = completionType;
        CompletionType completionType2 = new CompletionType("RETURN", 1);
        RETURN = completionType2;
        CompletionType completionType3 = new CompletionType("COMPLETE", 2);
        COMPLETE = completionType3;
        $VALUES = new CompletionType[]{completionType, completionType2, completionType3};
    }

    public static CompletionType valueOf(String str) {
        return (CompletionType) Enum.valueOf(CompletionType.class, str);
    }

    public static CompletionType[] values() {
        return (CompletionType[]) $VALUES.clone();
    }
}
