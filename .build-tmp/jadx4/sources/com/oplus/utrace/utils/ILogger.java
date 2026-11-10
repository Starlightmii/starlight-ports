package com.oplus.utrace.utils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ILogger {
    int d(String str, String str2);

    int d(String str, String str2, Throwable th);

    int e(String str, String str2);

    int e(String str, String str2, Throwable th);

    int i(String str, String str2);

    int i(String str, String str2, Throwable th);

    int println(int i, String str, String str2);

    int v(String str, String str2);

    int v(String str, String str2, Throwable th);

    int w(String str, String str2);

    int w(String str, String str2, Throwable th);
}
