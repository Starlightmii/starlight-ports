package com.oplusos.systemui.common.blurability.motion;

import android.app.ActivityManager;
import android.gui.EarlyWakeupInfo;
import android.os.Binder;
import android.os.SystemProperties;
import android.os.Trace;
import android.util.Log;
import android.view.CrossWindowBlurListeners;
import android.view.SurfaceControl;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.View;
import android.view.ViewRootImpl;
import com.oplus.graphics.OplusBlurManager;
import com.oplus.graphics.OplusBlurParam;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.io.CloseableKt;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BlurUtils {
    private static final String TRACK_NAME = "BlurUtils";
    public boolean earlyWakeupEnabled;
    private final EarlyWakeupInfo earlyWakeupInfo;
    public int lastAppliedBlur;
    public final int maxBlurRadius;
    public final int minBlurRadius;
    public static final Companion Companion = new Companion();
    private static final String PREPARE_BLUR_TRACE_NAME = BlurUtils.class.getName().concat("::prepareBlur");
    private static final String APPLY_BLUR_TRACE_NAME = BlurUtils.class.getName().concat("::applyBlur");

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static boolean supportsBlursOnWindows() {
            return CrossWindowBlurListeners.CROSS_WINDOW_BLUR_SUPPORTED && ActivityManager.isHighEndGfx() && !SystemProperties.getBoolean("persist.sysui.disableBlur", false);
        }
    }

    public BlurUtils(int i, int i2) {
        this.minBlurRadius = i;
        this.maxBlurRadius = i2;
        EarlyWakeupInfo earlyWakeupInfo = new EarlyWakeupInfo();
        this.earlyWakeupInfo = earlyWakeupInfo;
        earlyWakeupInfo.token = new Binder();
    }

    public static /* synthetic */ void applyBlur$default(BlurUtils blurUtils, ViewRootImpl viewRootImpl, int i, int i2, boolean z, float f, int i3) {
        int i4 = (i3 & 8) != 0 ? 0 : i2;
        boolean z2 = (i3 & 32) == 0;
        if ((i3 & 64) != 0) {
            f = -1.0f;
        }
        blurUtils.applyBlur(viewRootImpl, i, false, i4, z, z2, f);
    }

    public final void applyBlur(ViewRootImpl viewRootImpl, int i, boolean z, int i2, boolean z2, boolean z3, float f) {
        int i3;
        if (viewRootImpl == null || !viewRootImpl.getSurfaceControl().isValid()) {
            LogUtil.w$default(8, LogUtil.TAG_COMMON, TRACK_NAME, "[applyBlur] skip: viewRootImpl=" + viewRootImpl);
            return;
        }
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        try {
            Companion.getClass();
            if (Companion.supportsBlursOnWindows()) {
                if (i2 == 0) {
                    transaction.setBackgroundBlurRadius(viewRootImpl.getSurfaceControl(), i);
                    i3 = 0;
                } else {
                    SurfaceControl surfaceControl = viewRootImpl.getSurfaceControl();
                    OplusBlurParam oplusBlurParam = new OplusBlurParam();
                    oplusBlurParam.setBlurType(2);
                    float f2 = ((i2 >> 16) & 255) / 255.0f;
                    float f3 = ((i2 >> 8) & 255) / 255.0f;
                    float f4 = (i2 & 255) / 255.0f;
                    i3 = 0;
                    float[] fArr = {0.0f, 0.0f, 0.0f, 0.0f};
                    float fCoerceIn = 0.2f;
                    if (f >= 0.0f) {
                        fCoerceIn = 0.2f * RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
                    }
                    oplusBlurParam.setMaterialParams(1, fArr, new float[]{f2, f3, f4, fCoerceIn});
                    OplusBlurManager.setBackgroundBlurRadius(transaction, surfaceControl, i, oplusBlurParam);
                }
                if (!this.earlyWakeupEnabled && this.lastAppliedBlur == 0 && i != 0) {
                    Trace.asyncTraceForTrackBegin(4096L, TRACK_NAME, "eEarlyWakeup (applyBlur)", i3);
                    this.earlyWakeupInfo.trace = APPLY_BLUR_TRACE_NAME;
                    applyEarlyWakeupImmediately(true);
                    this.earlyWakeupEnabled = true;
                }
                if (this.earlyWakeupEnabled && this.lastAppliedBlur != 0 && i == 0) {
                    this.earlyWakeupInfo.trace = APPLY_BLUR_TRACE_NAME;
                    applyEarlyWakeupImmediately(false);
                    Trace.asyncTraceForTrackEnd(4096L, TRACK_NAME, 0);
                    this.earlyWakeupEnabled = false;
                }
                this.lastAppliedBlur = i;
            } else {
                LogUtil.w$default(8, LogUtil.TAG_COMMON, TRACK_NAME, "[applyBlur] Skip for supportsBlursOnWindows() is false");
            }
            transaction.setOpaque(viewRootImpl.getSurfaceControl(), z);
            if (z3) {
                viewRootImpl.applyTransactionOnDraw(transaction);
                View view = viewRootImpl.getView();
                if (view != null) {
                    view.invalidate();
                }
            } else if (z2) {
                SyncRtSurfaceTransactionApplier syncRtSurfaceTransactionApplier = new SyncRtSurfaceTransactionApplier(viewRootImpl.getView());
                SyncRtSurfaceTransactionApplier.SurfaceParams.Builder builder = new SyncRtSurfaceTransactionApplier.SurfaceParams.Builder(viewRootImpl.getSurfaceControl());
                builder.withMergeTransaction(transaction);
                syncRtSurfaceTransactionApplier.scheduleApply(new SyncRtSurfaceTransactionApplier.SurfaceParams[]{builder.build()});
            } else {
                transaction.apply();
            }
            if (i == 0 && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "BlurUtils-->".concat("[applyBlur] radius=" + i));
            }
            CloseableKt.closeFinally(transaction, (Throwable) null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(transaction, th);
                throw th2;
            }
        }
    }

    public final void applyEarlyWakeupImmediately(boolean z) {
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        EarlyWakeupInfo earlyWakeupInfo = this.earlyWakeupInfo;
        try {
            if (z) {
                transaction.setEarlyWakeupStart(earlyWakeupInfo).apply();
            } else {
                transaction.setEarlyWakeupEnd(earlyWakeupInfo).apply();
            }
            CloseableKt.closeFinally(transaction, (Throwable) null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(transaction, th);
                throw th2;
            }
        }
    }

    public final void prepareBlur(ViewRootImpl viewRootImpl, int i) {
        if (viewRootImpl == null || !viewRootImpl.getSurfaceControl().isValid()) {
            return;
        }
        Companion.getClass();
        if (!Companion.supportsBlursOnWindows() || this.earlyWakeupEnabled || this.lastAppliedBlur != 0 || i == 0) {
            return;
        }
        Trace.asyncTraceForTrackBegin(4096L, TRACK_NAME, "eEarlyWakeup (prepareBlur)", 0);
        this.earlyWakeupEnabled = true;
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        try {
            EarlyWakeupInfo earlyWakeupInfo = this.earlyWakeupInfo;
            earlyWakeupInfo.trace = PREPARE_BLUR_TRACE_NAME;
            transaction.setEarlyWakeupStart(earlyWakeupInfo).apply();
            transaction.apply();
            CloseableKt.closeFinally(transaction, (Throwable) null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(transaction, th);
                throw th2;
            }
        }
    }
}
