package com.oplus.utrace.sdk;

import com.oplus.utrace.utils.ILogger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IULogger extends ILogger {
    boolean available();

    @Override // com.oplus.utrace.utils.ILogger
    /* synthetic */ int d(String str, String str2);

    @Override // com.oplus.utrace.utils.ILogger
    /* synthetic */ int d(String str, String str2, Throwable th);

    void deleteLog();

    @Override // com.oplus.utrace.utils.ILogger
    /* synthetic */ int e(String str, String str2);

    @Override // com.oplus.utrace.utils.ILogger
    /* synthetic */ int e(String str, String str2, Throwable th);

    @Override // com.oplus.utrace.utils.ILogger
    /* synthetic */ int i(String str, String str2);

    @Override // com.oplus.utrace.utils.ILogger
    /* synthetic */ int i(String str, String str2, Throwable th);

    @Override // com.oplus.utrace.utils.ILogger
    /* synthetic */ int println(int i, String str, String str2);

    void release();

    void upload(String str, String str2, long j, long j2, boolean z, String str3);

    @Override // com.oplus.utrace.utils.ILogger
    /* synthetic */ int v(String str, String str2);

    @Override // com.oplus.utrace.utils.ILogger
    /* synthetic */ int v(String str, String str2, Throwable th);

    @Override // com.oplus.utrace.utils.ILogger
    /* synthetic */ int w(String str, String str2);

    @Override // com.oplus.utrace.utils.ILogger
    /* synthetic */ int w(String str, String str2, Throwable th);
}
