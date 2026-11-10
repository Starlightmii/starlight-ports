package com.oplus.utrace.hlog;

import android.content.Context;
import com.oplus.utrace.sdk.IULogger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ULoggerImpl implements IULogger {
    public static final Companion Companion = new Companion();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final void loadAndQuery(Context context) {
        }
    }

    public ULoggerImpl(Context context, String str) {
    }

    @Override // com.oplus.utrace.sdk.IULogger
    public final boolean available() {
        return true;
    }

    @Override // com.oplus.utrace.sdk.IULogger, com.oplus.utrace.utils.ILogger
    public int d(String str, String str2) {
        return 0;
    }

    @Override // com.oplus.utrace.sdk.IULogger
    public final void deleteLog() {
    }

    @Override // com.oplus.utrace.sdk.IULogger, com.oplus.utrace.utils.ILogger
    public int e(String str, String str2) {
        return 0;
    }

    @Override // com.oplus.utrace.sdk.IULogger, com.oplus.utrace.utils.ILogger
    public int i(String str, String str2) {
        return 0;
    }

    @Override // com.oplus.utrace.sdk.IULogger, com.oplus.utrace.utils.ILogger
    public int println(int i, String str, String str2) {
        return 0;
    }

    @Override // com.oplus.utrace.sdk.IULogger
    public final void release() {
    }

    @Override // com.oplus.utrace.sdk.IULogger
    public void upload(String str, String str2, long j, long j2, boolean z, String str3) {
    }

    @Override // com.oplus.utrace.sdk.IULogger, com.oplus.utrace.utils.ILogger
    public int v(String str, String str2) {
        return 0;
    }

    @Override // com.oplus.utrace.sdk.IULogger, com.oplus.utrace.utils.ILogger
    public int w(String str, String str2) {
        return 0;
    }

    @Override // com.oplus.utrace.sdk.IULogger, com.oplus.utrace.utils.ILogger
    public int d(String str, String str2, Throwable th) {
        return 0;
    }

    @Override // com.oplus.utrace.sdk.IULogger, com.oplus.utrace.utils.ILogger
    public int e(String str, String str2, Throwable th) {
        return 0;
    }

    @Override // com.oplus.utrace.sdk.IULogger, com.oplus.utrace.utils.ILogger
    public int i(String str, String str2, Throwable th) {
        return 0;
    }

    @Override // com.oplus.utrace.sdk.IULogger, com.oplus.utrace.utils.ILogger
    public int v(String str, String str2, Throwable th) {
        return 0;
    }

    @Override // com.oplus.utrace.sdk.IULogger, com.oplus.utrace.utils.ILogger
    public int w(String str, String str2, Throwable th) {
        return 0;
    }
}
