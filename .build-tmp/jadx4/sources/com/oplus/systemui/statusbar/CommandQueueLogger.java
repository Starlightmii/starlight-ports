package com.oplus.systemui.statusbar;

import android.hardware.biometrics.IBiometricContextListener;
import android.hardware.fingerprint.IUdfpsRefreshRateRequestCallback;
import com.android.internal.statusbar.LetterboxDetails;
import com.android.internal.view.AppearanceRegion;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.CommandQueue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CommandQueueLogger implements CommandQueue.Callbacks {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CommandQueueLogger";
    private final LogBuffer buffer;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CommandQueueLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }

    public final void abortTransient(int i, int i2) {
        LogBuffer logBuffer = this.buffer;
        LogLevel logLevel = LogLevel.DEBUG;
        CommandQueueLogger$$ExternalSyntheticLambda0 commandQueueLogger$$ExternalSyntheticLambda0 = new CommandQueueLogger$$ExternalSyntheticLambda0(1);
        LogBuffer.Companion.getClass();
        if (LogBuffer.isLogBufferDisabled) {
            return;
        }
        LogMessage logMessageObtain = logBuffer.obtain(TAG, logLevel, commandQueueLogger$$ExternalSyntheticLambda0, (Throwable) null);
        logMessageObtain.setInt1(i);
        logMessageObtain.setInt2(i2);
        logBuffer.commit(logMessageObtain);
    }

    public final void appTransitionCancelled(int i) {
        LogBuffer logBuffer = this.buffer;
        LogLevel logLevel = LogLevel.DEBUG;
        CommandQueueLogger$$ExternalSyntheticLambda0 commandQueueLogger$$ExternalSyntheticLambda0 = new CommandQueueLogger$$ExternalSyntheticLambda0(5);
        LogBuffer.Companion.getClass();
        if (LogBuffer.isLogBufferDisabled) {
            return;
        }
        LogMessage logMessageObtain = logBuffer.obtain(TAG, logLevel, commandQueueLogger$$ExternalSyntheticLambda0, (Throwable) null);
        logMessageObtain.setInt1(i);
        logBuffer.commit(logMessageObtain);
    }

    public final void appTransitionPending(int i, boolean z) {
        LogBuffer logBuffer = this.buffer;
        LogLevel logLevel = LogLevel.DEBUG;
        CommandQueueLogger$$ExternalSyntheticLambda0 commandQueueLogger$$ExternalSyntheticLambda0 = new CommandQueueLogger$$ExternalSyntheticLambda0(2);
        LogBuffer.Companion.getClass();
        if (LogBuffer.isLogBufferDisabled) {
            return;
        }
        LogMessage logMessageObtain = logBuffer.obtain(TAG, logLevel, commandQueueLogger$$ExternalSyntheticLambda0, (Throwable) null);
        logMessageObtain.setInt1(i);
        logMessageObtain.setBool1(z);
        logBuffer.commit(logMessageObtain);
    }

    public final void appTransitionStarting(int i, long j, long j2, boolean z) {
        LogBuffer logBuffer = this.buffer;
        LogLevel logLevel = LogLevel.DEBUG;
        CommandQueueLogger$$ExternalSyntheticLambda0 commandQueueLogger$$ExternalSyntheticLambda0 = new CommandQueueLogger$$ExternalSyntheticLambda0(3);
        LogBuffer.Companion.getClass();
        if (LogBuffer.isLogBufferDisabled) {
            return;
        }
        LogMessage logMessageObtain = logBuffer.obtain(TAG, logLevel, commandQueueLogger$$ExternalSyntheticLambda0, (Throwable) null);
        logMessageObtain.setInt1(i);
        logMessageObtain.setLong1(j);
        logMessageObtain.setLong2(j2);
        logMessageObtain.setBool1(z);
        logBuffer.commit(logMessageObtain);
    }

    public void onSystemBarAttributesChanged(int i, int i2, AppearanceRegion[] appearanceRegionArr, boolean z, int i3, int i4, String str, LetterboxDetails[] letterboxDetailsArr) {
        LogBuffer logBuffer = this.buffer;
        LogLevel logLevel = LogLevel.DEBUG;
        CommandQueueLogger$$ExternalSyntheticLambda0 commandQueueLogger$$ExternalSyntheticLambda0 = new CommandQueueLogger$$ExternalSyntheticLambda0(0);
        LogBuffer.Companion.getClass();
        if (LogBuffer.isLogBufferDisabled) {
            return;
        }
        LogMessage logMessageObtain = logBuffer.obtain(TAG, logLevel, commandQueueLogger$$ExternalSyntheticLambda0, (Throwable) null);
        logMessageObtain.setInt1(i);
        logMessageObtain.setInt2(i2);
        logMessageObtain.setStr1(str);
        logBuffer.commit(logMessageObtain);
    }

    public /* bridge */ /* synthetic */ void setBiometricContextListener(IBiometricContextListener iBiometricContextListener) {
        super.setBiometricContextListener(iBiometricContextListener);
    }

    public /* bridge */ /* synthetic */ void setQsTiles(String[] strArr) {
        super.setQsTiles(strArr);
    }

    public /* bridge */ /* synthetic */ void setSplitscreenFocus(boolean z) {
        super.setSplitscreenFocus(z);
    }

    public /* bridge */ /* synthetic */ void setTopAppHidesStatusBar(boolean z) {
        super.setTopAppHidesStatusBar(z);
    }

    public /* bridge */ /* synthetic */ void setUdfpsRefreshRateCallback(IUdfpsRefreshRateRequestCallback iUdfpsRefreshRateRequestCallback) {
        super.setUdfpsRefreshRateCallback(iUdfpsRefreshRateRequestCallback);
    }

    public final void showTransient(int i, int i2, boolean z) {
        LogBuffer logBuffer = this.buffer;
        LogLevel logLevel = LogLevel.DEBUG;
        CommandQueueLogger$$ExternalSyntheticLambda0 commandQueueLogger$$ExternalSyntheticLambda0 = new CommandQueueLogger$$ExternalSyntheticLambda0(4);
        LogBuffer.Companion.getClass();
        if (LogBuffer.isLogBufferDisabled) {
            return;
        }
        LogMessage logMessageObtain = logBuffer.obtain(TAG, logLevel, commandQueueLogger$$ExternalSyntheticLambda0, (Throwable) null);
        logMessageObtain.setInt1(i);
        logMessageObtain.setInt2(i2);
        logMessageObtain.setBool1(z);
        logBuffer.commit(logMessageObtain);
    }
}
