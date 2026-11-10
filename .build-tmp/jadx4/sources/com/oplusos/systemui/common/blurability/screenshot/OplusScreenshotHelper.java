package com.oplusos.systemui.common.blurability.screenshot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.HardwareBuffer;
import android.os.IBinder;
import android.os.SystemProperties;
import android.os.Trace;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.OplusWindowManager;
import android.view.WindowManager;
import com.oplus.app.OplusScreenShotOptions;
import com.oplus.app.OplusScreenShotResult;
import com.oplus.systemui.aod.utils.NoneLocalHbmAodRusManager$;
import com.oplusos.systemui.common.blurability.ext.BitmapExtKt;
import com.oplusos.systemui.common.blurability.report.ReportManager;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ReflectionUtils;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusScreenshotHelper {
    private static final String TAG = "OplusScreenshotHelper";
    public static volatile boolean needCheckPureBlack;
    private static volatile Job resetPureBlackJob;
    public static final OplusScreenshotHelper INSTANCE = new OplusScreenshotHelper();
    private static final OplusWindowManager oplusWindowManager = new OplusWindowManager();
    private static final DisplayMetrics dm = new DisplayMetrics();
    private static final Lazy scope$delegate = LazyKt__LazyJVMKt.lazy(new OplusScreenshotHelper$$ExternalSyntheticLambda0());

    /* JADX INFO: renamed from: com.oplusos.systemui.common.blurability.screenshot.OplusScreenshotHelper$capture$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OplusScreenshotHelper.this.capture(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.blurability.screenshot.OplusScreenshotHelper$start$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C03151 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C03151(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OplusScreenshotHelper.this.start(null, null, null, null, this);
        }
    }

    public static Object getDefaultBitmap(Function0 function0, Context context, float f, String str) {
        Object obj;
        Bitmap bitmap;
        if (function0 == null) {
            return null;
        }
        try {
            Object objInvoke = function0.invoke();
            Drawable drawable = (Drawable) objInvoke;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "OplusScreenshotHelper-->".concat("[getDefaultBitmap] wallpaperManager.drawable = " + drawable));
            }
            Drawable drawable2 = (Drawable) objInvoke;
            if (drawable2 == null) {
                bitmap = null;
            } else if (drawable2 instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) drawable2).getBitmap();
            } else {
                Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                DisplayMetrics displayMetrics = dm;
                defaultDisplay.getRealMetrics(displayMetrics);
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap((int) (displayMetrics.widthPixels * f), (int) (displayMetrics.heightPixels * f), Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
                drawable2.draw(canvas);
                bitmap = bitmapCreateBitmap;
            }
            obj = Result.constructor-impl(bitmap);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.e$default(8, LogUtil.TAG_COMMON, TAG, "[getDefaultBitmap] " + th2.getMessage());
        }
        if (obj instanceof Result.Failure) {
            obj = null;
        }
        Bitmap bitmap2 = (Bitmap) obj;
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[getDefaultBitmap] return " + bitmap2, null, 8);
        if (bitmap2 == null) {
            Function0 externalSyntheticLambda2 = new OplusScreenshotHelper$.ExternalSyntheticLambda2();
            ((OplusScreenshotHelper$.ExternalSyntheticLambda2) externalSyntheticLambda2).f$0 = str;
            VarHandle.storeStoreFence();
            ReportManager.INSTANCE.report("event_capture_workaround_failed", externalSyntheticLambda2);
        }
        return obj;
    }

    private final CoroutineScope getScope() {
        return (CoroutineScope) scope$delegate.getValue();
    }

    public static Object getScreenshotRect(Context context) {
        Display defaultDisplay;
        boolean z = Settings.Secure.getIntForUser(context.getContentResolver(), "one_handed_mode_activated", 0, -2) == 1;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Object systemService = context.getSystemService("window");
        WindowManager windowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
        if (windowManager != null && (defaultDisplay = windowManager.getDefaultDisplay()) != null) {
            defaultDisplay.getRealMetrics(displayMetrics);
        }
        return new Rect(0, z ? displayMetrics.heightPixels / 2 : 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    /* JADX WARN: Code duplicated, block: B:44:0x015d A[Catch: all -> 0x00e8, TryCatch #4 {all -> 0x00e8, blocks: (B:33:0x00dd, B:42:0x013a, B:44:0x015d, B:48:0x0163, B:50:0x0166, B:51:0x0171, B:53:0x017a, B:55:0x019e, B:57:0x01a5, B:59:0x01b1, B:61:0x01b7, B:93:0x0263, B:94:0x026a), top: B:116:0x00dd }] */
    /* JADX WARN: Code duplicated, block: B:46:0x0160  */
    /* JADX WARN: Code duplicated, block: B:47:0x0162  */
    /* JADX WARN: Code duplicated, block: B:50:0x0166 A[Catch: all -> 0x00e8, TryCatch #4 {all -> 0x00e8, blocks: (B:33:0x00dd, B:42:0x013a, B:44:0x015d, B:48:0x0163, B:50:0x0166, B:51:0x0171, B:53:0x017a, B:55:0x019e, B:57:0x01a5, B:59:0x01b1, B:61:0x01b7, B:93:0x0263, B:94:0x026a), top: B:116:0x00dd }] */
    /* JADX WARN: Code duplicated, block: B:53:0x017a A[Catch: all -> 0x00e8, TryCatch #4 {all -> 0x00e8, blocks: (B:33:0x00dd, B:42:0x013a, B:44:0x015d, B:48:0x0163, B:50:0x0166, B:51:0x0171, B:53:0x017a, B:55:0x019e, B:57:0x01a5, B:59:0x01b1, B:61:0x01b7, B:93:0x0263, B:94:0x026a), top: B:116:0x00dd }] */
    /* JADX WARN: Code duplicated, block: B:58:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:61:0x01b7 A[Catch: all -> 0x00e8, TRY_LEAVE, TryCatch #4 {all -> 0x00e8, blocks: (B:33:0x00dd, B:42:0x013a, B:44:0x015d, B:48:0x0163, B:50:0x0166, B:51:0x0171, B:53:0x017a, B:55:0x019e, B:57:0x01a5, B:59:0x01b1, B:61:0x01b7, B:93:0x0263, B:94:0x026a), top: B:116:0x00dd }] */
    /* JADX WARN: Code duplicated, block: B:64:0x01be  */
    /* JADX WARN: Code duplicated, block: B:67:0x01cb A[Catch: all -> 0x0254, TryCatch #1 {all -> 0x0254, blocks: (B:83:0x024e, B:65:0x01c7, B:67:0x01cb, B:69:0x01d4, B:71:0x01f5, B:73:0x01fb), top: B:110:0x01c7 }] */
    /* JADX WARN: Code duplicated, block: B:79:0x0210 A[Catch: all -> 0x0256, TRY_LEAVE, TryCatch #3 {all -> 0x0256, blocks: (B:91:0x025e, B:77:0x0208, B:79:0x0210), top: B:114:0x0208 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x001b  */
    /* JADX WARN: Code duplicated, block: B:82:0x024d  */
    /* JADX WARN: Code duplicated, block: B:89:0x0259  */
    /* JADX WARN: Code duplicated, block: B:93:0x0263 A[Catch: all -> 0x00e8, TRY_ENTER, TryCatch #4 {all -> 0x00e8, blocks: (B:33:0x00dd, B:42:0x013a, B:44:0x015d, B:48:0x0163, B:50:0x0166, B:51:0x0171, B:53:0x017a, B:55:0x019e, B:57:0x01a5, B:59:0x01b1, B:61:0x01b7, B:93:0x0263, B:94:0x026a), top: B:116:0x00dd }] */
    public final Object capture(Context context, String str, CaptureParams captureParams, ContinuationImpl continuationImpl) {
        AnonymousClass1 anonymousClass1;
        OplusScreenShotOptions oplusScreenShotOptions;
        String str2;
        OplusScreenShotOptions oplusScreenShotOptions2;
        String str3;
        Context context2;
        Object obj;
        int i;
        CaptureParams captureParams2;
        OplusScreenShotOptions oplusScreenShotOptions3;
        IBinder[] excludedWindowTokens;
        OplusScreenShotResult screenshot;
        int i2;
        ColorSpace colorSpace;
        Bitmap bitmapWrapHardwareBuffer;
        OplusScreenShotResult oplusScreenShotResult;
        OplusScreenshotHelper oplusScreenshotHelper;
        int i3;
        Context context3;
        CaptureParams captureParams3;
        boolean z;
        Bitmap bitmap;
        OplusScreenShotOptions oplusScreenShotOptions4;
        Bitmap bitmap2;
        Bitmap bitmapCopy;
        int i4;
        Object obj2;
        OplusScreenshotHelper oplusScreenshotHelper2 = this;
        if (continuationImpl instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuationImpl;
            int i5 = anonymousClass1.label;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i5 - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = oplusScreenshotHelper2.new AnonymousClass1(continuationImpl);
            }
        } else {
            anonymousClass1 = oplusScreenshotHelper2.new AnonymousClass1(continuationImpl);
        }
        Object defaultBitmap = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i6 = anonymousClass1.label;
        if (i6 != 0) {
            if (i6 == 1) {
                int i7 = anonymousClass1.I$0;
                OplusScreenShotOptions oplusScreenShotOptions5 = (OplusScreenShotOptions) anonymousClass1.L$5;
                OplusScreenshotHelper oplusScreenshotHelper3 = (OplusScreenshotHelper) anonymousClass1.L$4;
                oplusScreenShotOptions3 = (OplusScreenShotOptions) anonymousClass1.L$3;
                captureParams2 = (CaptureParams) anonymousClass1.L$2;
                str3 = (String) anonymousClass1.L$1;
                context2 = (Context) anonymousClass1.L$0;
                try {
                    ResultKt.throwOnFailure(defaultBitmap);
                    i = i7;
                    oplusScreenshotHelper2 = oplusScreenshotHelper3;
                    obj = defaultBitmap;
                    oplusScreenShotOptions = oplusScreenShotOptions5;
                    oplusScreenShotOptions.mSourceCrop = (Rect) obj;
                    LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[capture] takeScreen: rect=" + oplusScreenShotOptions3.mSourceCrop + " from " + str3, null, 8);
                    excludedWindowTokens = captureParams2.getExcludedWindowTokens();
                    if (excludedWindowTokens != null) {
                        if (excludedWindowTokens.length == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (!z) {
                            ReflectionUtils.setValue(OplusScreenShotOptions.class, oplusScreenShotOptions3, "mExcludeWindows", captureParams2.getExcludedWindowTokens());
                        }
                    }
                    if (!SystemProperties.getBoolean("debug.systemui.oplus_screenshot_fail", false)) {
                        throw new IllegalStateException("Bitmap is not compatible with protected buffers");
                    }
                    screenshot = oplusWindowManager.getScreenshot(oplusScreenShotOptions3);
                    HardwareBuffer hardwareBuffer = screenshot.mHardwareBuffer;
                    i2 = screenshot.mColorSpaceNamed;
                    anonymousClass1.L$0 = context2;
                    anonymousClass1.L$1 = str3;
                    anonymousClass1.L$2 = captureParams2;
                    anonymousClass1.L$3 = oplusScreenShotOptions3;
                    anonymousClass1.L$4 = oplusScreenshotHelper2;
                    anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(screenshot);
                    anonymousClass1.I$0 = i;
                    anonymousClass1.label = 2;
                    oplusScreenshotHelper2.getClass();
                    if (i2 >= 0 || i2 >= ColorSpace.Named.values().length) {
                        colorSpace = null;
                    } else {
                        colorSpace = ColorSpace.get(ColorSpace.Named.values()[i2]);
                    }
                    bitmapWrapHardwareBuffer = Bitmap.wrapHardwareBuffer(hardwareBuffer, colorSpace);
                    if (bitmapWrapHardwareBuffer != null) {
                        bitmapWrapHardwareBuffer.prepareToDraw();
                    }
                    if (bitmapWrapHardwareBuffer != coroutine_suspended) {
                        oplusScreenShotResult = screenshot;
                        defaultBitmap = bitmapWrapHardwareBuffer;
                        oplusScreenshotHelper = oplusScreenshotHelper2;
                        i3 = i;
                        oplusScreenShotOptions2 = oplusScreenShotOptions3;
                        str2 = str3;
                        context3 = context2;
                        captureParams3 = captureParams2;
                        bitmap = (Bitmap) defaultBitmap;
                        if (bitmap == null) {
                        }
                        oplusScreenShotOptions4 = oplusScreenShotOptions2;
                        bitmap2 = null;
                        obj2 = Result.constructor-impl(bitmap2);
                    }
                    return coroutine_suspended;
                } catch (Throwable th) {
                    th = th;
                    oplusScreenShotOptions2 = oplusScreenShotOptions3;
                    str2 = str3;
                    obj2 = Result.constructor-impl(ResultKt.createFailure(th));
                    oplusScreenShotOptions4 = oplusScreenShotOptions2;
                }
            } else if (i6 != 2) {
                if (i6 == 3) {
                    int i8 = anonymousClass1.I$1;
                    i3 = anonymousClass1.I$0;
                    bitmap2 = (Bitmap) anonymousClass1.L$6;
                    oplusScreenShotResult = (OplusScreenShotResult) anonymousClass1.L$5;
                    oplusScreenshotHelper = (OplusScreenshotHelper) anonymousClass1.L$4;
                    oplusScreenShotOptions4 = (OplusScreenShotOptions) anonymousClass1.L$3;
                    captureParams3 = (CaptureParams) anonymousClass1.L$2;
                    String str4 = (String) anonymousClass1.L$1;
                    context3 = (Context) anonymousClass1.L$0;
                    try {
                        ResultKt.throwOnFailure(defaultBitmap);
                        i4 = i8;
                        str2 = str4;
                        try {
                            if (((Boolean) defaultBitmap).booleanValue()) {
                                Function0 requireDefaultDrawable = captureParams3.getRequireDefaultDrawable();
                                float compressRatioOfScreenSize = captureParams3.getCompressRatioOfScreenSize();
                                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                anonymousClass1.L$1 = str2;
                                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(captureParams3);
                                anonymousClass1.L$3 = oplusScreenShotOptions4;
                                anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(oplusScreenshotHelper);
                                anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(oplusScreenShotResult);
                                anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(bitmap2);
                                anonymousClass1.I$0 = i3;
                                anonymousClass1.I$1 = i4;
                                anonymousClass1.label = 4;
                                oplusScreenshotHelper.getClass();
                                defaultBitmap = getDefaultBitmap(requireDefaultDrawable, context3, compressRatioOfScreenSize, str2);
                                if (defaultBitmap != coroutine_suspended) {
                                    oplusScreenShotOptions2 = oplusScreenShotOptions4;
                                }
                                return coroutine_suspended;
                            }
                            obj2 = Result.constructor-impl(bitmap2);
                        } catch (Throwable th2) {
                            th = th2;
                            oplusScreenShotOptions2 = oplusScreenShotOptions4;
                            obj2 = Result.constructor-impl(ResultKt.createFailure(th));
                            oplusScreenShotOptions4 = oplusScreenShotOptions2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        str2 = str4;
                        oplusScreenShotOptions2 = oplusScreenShotOptions4;
                        obj2 = Result.constructor-impl(ResultKt.createFailure(th));
                        oplusScreenShotOptions4 = oplusScreenShotOptions2;
                    }
                } else {
                    if (i6 != 4) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    oplusScreenShotOptions2 = (OplusScreenShotOptions) anonymousClass1.L$3;
                    String str5 = (String) anonymousClass1.L$1;
                    try {
                        ResultKt.throwOnFailure(defaultBitmap);
                        str2 = str5;
                    } catch (Throwable th4) {
                        th = th4;
                        str2 = str5;
                        obj2 = Result.constructor-impl(ResultKt.createFailure(th));
                        oplusScreenShotOptions4 = oplusScreenShotOptions2;
                    }
                }
                oplusScreenShotOptions4 = oplusScreenShotOptions2;
                bitmap2 = (Bitmap) defaultBitmap;
                obj2 = Result.constructor-impl(bitmap2);
            } else {
                i3 = anonymousClass1.I$0;
                OplusScreenShotResult oplusScreenShotResult2 = (OplusScreenShotResult) anonymousClass1.L$5;
                OplusScreenshotHelper oplusScreenshotHelper4 = (OplusScreenshotHelper) anonymousClass1.L$4;
                OplusScreenShotOptions oplusScreenShotOptions6 = (OplusScreenShotOptions) anonymousClass1.L$3;
                CaptureParams captureParams4 = (CaptureParams) anonymousClass1.L$2;
                str2 = (String) anonymousClass1.L$1;
                Context context4 = (Context) anonymousClass1.L$0;
                try {
                    ResultKt.throwOnFailure(defaultBitmap);
                    oplusScreenShotResult = oplusScreenShotResult2;
                    oplusScreenShotOptions2 = oplusScreenShotOptions6;
                    oplusScreenshotHelper = oplusScreenshotHelper4;
                    captureParams3 = captureParams4;
                    context3 = context4;
                    try {
                        bitmap = (Bitmap) defaultBitmap;
                        if (bitmap == null && (bitmapCopy = bitmap.copy(Bitmap.Config.RGB_565, false)) != null) {
                            anonymousClass1.L$0 = context3;
                            anonymousClass1.L$1 = str2;
                            anonymousClass1.L$2 = captureParams3;
                            anonymousClass1.L$3 = oplusScreenShotOptions2;
                            anonymousClass1.L$4 = oplusScreenshotHelper;
                            anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(oplusScreenShotResult);
                            anonymousClass1.L$6 = bitmapCopy;
                            anonymousClass1.I$0 = i3;
                            i4 = 0;
                            anonymousClass1.I$1 = 0;
                            anonymousClass1.label = 3;
                            oplusScreenshotHelper.getClass();
                            Boolean boolBoxBoolean = Boxing.boxBoolean(needCheckPureBlack ? BitmapExtKt.checkBitmapIsPureBlack(bitmapCopy) : false);
                            if (boolBoxBoolean != coroutine_suspended) {
                                oplusScreenShotOptions4 = oplusScreenShotOptions2;
                                bitmap2 = bitmapCopy;
                                defaultBitmap = boolBoxBoolean;
                                if (((Boolean) defaultBitmap).booleanValue()) {
                                    Function0 requireDefaultDrawable2 = captureParams3.getRequireDefaultDrawable();
                                    float compressRatioOfScreenSize2 = captureParams3.getCompressRatioOfScreenSize();
                                    anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(context3);
                                    anonymousClass1.L$1 = str2;
                                    anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(captureParams3);
                                    anonymousClass1.L$3 = oplusScreenShotOptions4;
                                    anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(oplusScreenshotHelper);
                                    anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(oplusScreenShotResult);
                                    anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(bitmap2);
                                    anonymousClass1.I$0 = i3;
                                    anonymousClass1.I$1 = i4;
                                    anonymousClass1.label = 4;
                                    oplusScreenshotHelper.getClass();
                                    defaultBitmap = getDefaultBitmap(requireDefaultDrawable2, context3, compressRatioOfScreenSize2, str2);
                                    if (defaultBitmap != coroutine_suspended) {
                                        oplusScreenShotOptions2 = oplusScreenShotOptions4;
                                        oplusScreenShotOptions4 = oplusScreenShotOptions2;
                                        bitmap2 = (Bitmap) defaultBitmap;
                                    }
                                }
                            }
                            return coroutine_suspended;
                        }
                        oplusScreenShotOptions4 = oplusScreenShotOptions2;
                        bitmap2 = null;
                        obj2 = Result.constructor-impl(bitmap2);
                    } catch (Throwable th5) {
                        th = th5;
                        obj2 = Result.constructor-impl(ResultKt.createFailure(th));
                        oplusScreenShotOptions4 = oplusScreenShotOptions2;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    oplusScreenShotOptions2 = oplusScreenShotOptions6;
                    obj2 = Result.constructor-impl(ResultKt.createFailure(th));
                    oplusScreenShotOptions4 = oplusScreenShotOptions2;
                }
            }
            obj2 = Result.constructor-impl(ResultKt.createFailure(th));
            oplusScreenShotOptions4 = oplusScreenShotOptions2;
        } else {
            ResultKt.throwOnFailure(defaultBitmap);
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[capture] start from " + str, null, 8);
            Trace.traceBegin(8L, "[capture]");
            oplusScreenShotOptions = new OplusScreenShotOptions();
            try {
                oplusScreenShotOptions.mScaleIndex = captureParams.getCompressRatioOfScreenSize();
                oplusScreenShotOptions.mFullDisplay = true;
                anonymousClass1.L$0 = context;
                anonymousClass1.L$1 = str;
                anonymousClass1.L$2 = captureParams;
                anonymousClass1.L$3 = oplusScreenShotOptions;
                anonymousClass1.L$4 = oplusScreenshotHelper2;
                anonymousClass1.L$5 = oplusScreenShotOptions;
                anonymousClass1.I$0 = 0;
                anonymousClass1.label = 1;
                Object screenshotRect = getScreenshotRect(context);
                if (screenshotRect != coroutine_suspended) {
                    str3 = str;
                    context2 = context;
                    obj = screenshotRect;
                    i = 0;
                    captureParams2 = captureParams;
                    oplusScreenShotOptions3 = oplusScreenShotOptions;
                    oplusScreenShotOptions.mSourceCrop = (Rect) obj;
                    LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[capture] takeScreen: rect=" + oplusScreenShotOptions3.mSourceCrop + " from " + str3, null, 8);
                    excludedWindowTokens = captureParams2.getExcludedWindowTokens();
                    if (excludedWindowTokens != null) {
                        if (excludedWindowTokens.length == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (!z) {
                            ReflectionUtils.setValue(OplusScreenShotOptions.class, oplusScreenShotOptions3, "mExcludeWindows", captureParams2.getExcludedWindowTokens());
                        }
                    }
                    if (!SystemProperties.getBoolean("debug.systemui.oplus_screenshot_fail", false)) {
                        throw new IllegalStateException("Bitmap is not compatible with protected buffers");
                    }
                    screenshot = oplusWindowManager.getScreenshot(oplusScreenShotOptions3);
                    HardwareBuffer hardwareBuffer2 = screenshot.mHardwareBuffer;
                    i2 = screenshot.mColorSpaceNamed;
                    anonymousClass1.L$0 = context2;
                    anonymousClass1.L$1 = str3;
                    anonymousClass1.L$2 = captureParams2;
                    anonymousClass1.L$3 = oplusScreenShotOptions3;
                    anonymousClass1.L$4 = oplusScreenshotHelper2;
                    anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(screenshot);
                    anonymousClass1.I$0 = i;
                    anonymousClass1.label = 2;
                    oplusScreenshotHelper2.getClass();
                    if (i2 >= 0) {
                        colorSpace = null;
                    } else {
                        colorSpace = null;
                    }
                    bitmapWrapHardwareBuffer = Bitmap.wrapHardwareBuffer(hardwareBuffer2, colorSpace);
                    if (bitmapWrapHardwareBuffer != null) {
                        bitmapWrapHardwareBuffer.prepareToDraw();
                    }
                    if (bitmapWrapHardwareBuffer != coroutine_suspended) {
                        oplusScreenShotResult = screenshot;
                        defaultBitmap = bitmapWrapHardwareBuffer;
                        oplusScreenshotHelper = oplusScreenshotHelper2;
                        i3 = i;
                        oplusScreenShotOptions2 = oplusScreenShotOptions3;
                        str2 = str3;
                        context3 = context2;
                        captureParams3 = captureParams2;
                        bitmap = (Bitmap) defaultBitmap;
                        if (bitmap == null) {
                        }
                        oplusScreenShotOptions4 = oplusScreenShotOptions2;
                        bitmap2 = null;
                        obj2 = Result.constructor-impl(bitmap2);
                    }
                }
                return coroutine_suspended;
            } catch (Throwable th7) {
                th = th7;
                str2 = str;
                oplusScreenShotOptions2 = oplusScreenShotOptions;
                obj2 = Result.constructor-impl(ResultKt.createFailure(th));
                oplusScreenShotOptions4 = oplusScreenShotOptions2;
            }
        }
        Throwable th8 = Result.exceptionOrNull-impl(obj2);
        if (th8 != null) {
            LogUtil.e$default(8, LogUtil.TAG_COMMON, TAG, "[capture] " + th8.getMessage() + " from " + str2);
        }
        if (obj2 instanceof Result.Failure) {
            obj2 = null;
        }
        Bitmap bitmap3 = (Bitmap) obj2;
        Trace.traceEnd(8L);
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[capture] end " + bitmap3 + " from " + str2, null, 8);
        if (bitmap3 == null) {
            Function0 externalSyntheticLambda1 = new OplusScreenshotHelper$.ExternalSyntheticLambda1();
            ((OplusScreenshotHelper$.ExternalSyntheticLambda1) externalSyntheticLambda1).f$0 = str2;
            ((OplusScreenshotHelper$.ExternalSyntheticLambda1) externalSyntheticLambda1).f$1 = oplusScreenShotOptions4;
            VarHandle.storeStoreFence();
            ReportManager.INSTANCE.report("event_capture_failed", externalSyntheticLambda1);
        }
        return obj2;
    }

    public final void needCheckPureBlack(long j) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", NoneLocalHbmAodRusManager$.ExternalSyntheticOutline0.m(j, "[needCheckPureBlack] ", " ms", "OplusScreenshotHelper-->"));
        }
        Job job = resetPureBlackJob;
        if (job != null) {
            Job.Key key = Job.Key;
            job.cancel((CancellationException) null);
        }
        resetPureBlackJob = null;
        needCheckPureBlack = true;
        resetPureBlackJob = BuildersKt.launch$default(getScope(), (CoroutineContext) null, (CoroutineStart) null, new OplusScreenshotHelper$scheduleReset$1(j, null), 3);
    }

    /* JADX WARN: Code duplicated, block: B:36:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:40:0x00fb A[PHI: r10 r11 r12 r13 r15
      0x00fb: PHI (r10v15 com.oplusos.systemui.common.blurability.screenshot.ScreenshotsCallback) = 
      (r10v3 com.oplusos.systemui.common.blurability.screenshot.ScreenshotsCallback)
      (r10v16 com.oplusos.systemui.common.blurability.screenshot.ScreenshotsCallback)
     binds: [B:35:0x00d6, B:39:0x00f9] A[DONT_GENERATE, DONT_INLINE]
      0x00fb: PHI (r11v5 com.oplusos.systemui.common.blurability.screenshot.CaptureParams) = 
      (r11v3 com.oplusos.systemui.common.blurability.screenshot.CaptureParams)
      (r11v7 com.oplusos.systemui.common.blurability.screenshot.CaptureParams)
     binds: [B:35:0x00d6, B:39:0x00f9] A[DONT_GENERATE, DONT_INLINE]
      0x00fb: PHI (r12v4 java.lang.String) = (r12v2 java.lang.String), (r12v5 java.lang.String) binds: [B:35:0x00d6, B:39:0x00f9] A[DONT_GENERATE, DONT_INLINE]
      0x00fb: PHI (r13v5 android.content.Context) = (r13v3 android.content.Context), (r13v7 android.content.Context) binds: [B:35:0x00d6, B:39:0x00f9] A[DONT_GENERATE, DONT_INLINE]
      0x00fb: PHI (r15v14 android.graphics.Bitmap) = (r15v10 android.graphics.Bitmap), (r15v16 android.graphics.Bitmap) binds: [B:35:0x00d6, B:39:0x00f9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:43:0x0105  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00f6, code lost:
    
        if (r15 == r1) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x014e, code lost:
    
        if (((com.oplusos.systemui.common.blurability.screenshot.StaticBlurManager.startScreenShotBlur.2.1) r14).onFinished(r15, r0) == r1) goto L48;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object start(android.content.Context r11, java.lang.String r12, com.oplusos.systemui.common.blurability.screenshot.CaptureParams r13, com.oplusos.systemui.common.blurability.screenshot.ScreenshotsCallback r14, kotlin.coroutines.Continuation r15) {
        /*
            Method dump skipped, instruction units count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplusos.systemui.common.blurability.screenshot.OplusScreenshotHelper.start(android.content.Context, java.lang.String, com.oplusos.systemui.common.blurability.screenshot.CaptureParams, com.oplusos.systemui.common.blurability.screenshot.ScreenshotsCallback, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
