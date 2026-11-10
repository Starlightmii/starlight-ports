package com.oplus.utrace.lib;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public enum SpanType {
    CodeSpans(1),
    IntentTrace(2);

    private final int value;

    SpanType(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }
}
