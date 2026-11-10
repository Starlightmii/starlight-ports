package com.oplus.systemui.media.output.data;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OutputBluetoothScanner$collectBluetoothDeviceIcon$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OutputBluetoothScanner this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OutputBluetoothScanner$collectBluetoothDeviceIcon$1(OutputBluetoothScanner outputBluetoothScanner, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = outputBluetoothScanner;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return OutputBluetoothScanner.access$collectBluetoothDeviceIcon(this.this$0, this);
    }
}
