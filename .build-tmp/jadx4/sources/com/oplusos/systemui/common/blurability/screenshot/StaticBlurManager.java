package com.oplusos.systemui.common.blurability.screenshot;

import android.common.OplusFeatureCache;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.RectF;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.Trace;
import android.util.Log;
import android.util.Pair;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.ImageView;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.animation.ChangeSize$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.shutdown.ShutdownBlurManager$;
import com.oplus.uifirst.IOplusUIFirstManager;
import com.oplusos.systemui.common.blurability.ext.BitmapExtKt;
import com.oplusos.systemui.common.blurability.staticblur.StaticBlurDrawable;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Predicate;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.Job;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StaticBlurManager {
    private static final String TAG = "StaticBlurManager";
    private Job blurJob;
    private final Context context;
    private Pair<Float, Float> scalePair;
    private Bitmap screenBlurredBitmap;
    private final StaticBlurParams staticBlurParams;
    private final Lazy windowManager$delegate;
    public volatile int workThreadId;
    public static final Companion Companion = new Companion();
    private static final Lazy workDispatcher$delegate = LazyKt__LazyJVMKt.lazy(new StaticBlurManager$$ExternalSyntheticLambda0(1));
    private final Lazy blurScope$delegate = LazyKt__LazyJVMKt.lazy(new StaticBlurManager$$ExternalSyntheticLambda0(0));
    private final CopyOnWriteArraySet<BlurStepsObserver> blurStepObservers = new CopyOnWriteArraySet<>();
    private final HashSet<WeakReference<StaticBlurDrawable>> staticBlurDrawables = new HashSet<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface BlurStepsObserver {
        default void onBlurredFinish(Bitmap bitmap, Pair<Float, Float> pair) {
        }

        default void onCompressedFinish(Bitmap bitmap) {
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final ExecutorCoroutineDispatcher getWorkDispatcher$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon() {
            return (ExecutorCoroutineDispatcher) StaticBlurManager.workDispatcher$delegate.getValue();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class StaticBlurParams {
        public int blurRadius;
        public float compressRatioOfScreenSize;

        public StaticBlurParams(float f, int i) {
            this.blurRadius = i;
            this.compressRatioOfScreenSize = f;
        }

        public final StaticBlurParams copy(int i, float f) {
            return new StaticBlurParams(f, i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StaticBlurParams)) {
                return false;
            }
            StaticBlurParams staticBlurParams = (StaticBlurParams) obj;
            return this.blurRadius == staticBlurParams.blurRadius && Float.compare(this.compressRatioOfScreenSize, staticBlurParams.compressRatioOfScreenSize) == 0;
        }

        public final int getBlurRadius() {
            return this.blurRadius;
        }

        public final float getCompressRatioOfScreenSize() {
            return this.compressRatioOfScreenSize;
        }

        public final int hashCode() {
            return Float.hashCode(this.compressRatioOfScreenSize) + (Integer.hashCode(this.blurRadius) * 31);
        }

        public final void setBlurRadius(int i) {
            this.blurRadius = i;
        }

        public final void setCompressRatioOfScreenSize(float f) {
            this.compressRatioOfScreenSize = f;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("StaticBlurParams(blurRadius=");
            sb.append(this.blurRadius);
            sb.append(", compressRatioOfScreenSize=");
            return ChangeSize$.ExternalSyntheticOutline0.m(sb, this.compressRatioOfScreenSize, ')');
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.blurability.screenshot.StaticBlurManager$blurAndSetBackground$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            StaticBlurManager staticBlurManager = StaticBlurManager.this;
            Companion companion = StaticBlurManager.Companion;
            return staticBlurManager.blurAndSetBackground(null, 0.0f, this);
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.blurability.screenshot.StaticBlurManager$blurAndSetBackground$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        final /* synthetic */ Ref.ObjectRef<Bitmap> $blurredBitmap;
        final /* synthetic */ float $compressRatio;
        final /* synthetic */ Ref.ObjectRef<Pair<Float, Float>> $scalePair;
        final /* synthetic */ Bitmap $srcBitmap;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(Bitmap bitmap, Ref.ObjectRef objectRef, float f, Ref.ObjectRef objectRef2, Continuation continuation) {
            super(2, continuation);
            this.$srcBitmap = bitmap;
            this.$blurredBitmap = objectRef;
            this.$compressRatio = f;
            this.$scalePair = objectRef2;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return StaticBlurManager.this.new AnonymousClass3(this.$srcBitmap, this.$blurredBitmap, this.$compressRatio, this.$scalePair, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Bitmap bitmapCompressAsBitmap;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            Bitmap bitmap = this.$srcBitmap;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "StaticBlurManager-->".concat("[blurAndSetBackground] " + bitmap));
            }
            WindowMetrics currentWindowMetrics = StaticBlurManager.this.getWindowManager().getCurrentWindowMetrics();
            if (this.$srcBitmap.getWidth() >= currentWindowMetrics.getBounds().width() || this.$srcBitmap.getHeight() >= currentWindowMetrics.getBounds().width()) {
                Bitmap bitmap2 = this.$srcBitmap;
                float f = this.$compressRatio;
                bitmapCompressAsBitmap = BitmapExtKt.compressAsBitmap(bitmap2, (int) (bitmap2.getWidth() * f), (int) (bitmap2.getHeight() * f));
            } else {
                bitmapCompressAsBitmap = this.$srcBitmap;
            }
            StaticBlurManager.this.onCompressedFinish(bitmapCompressAsBitmap);
            this.$blurredBitmap.element = bitmapCompressAsBitmap != null ? BitmapExtKt.blur(bitmapCompressAsBitmap, (int) (this.$compressRatio * 0.5f * StaticBlurManager.this.getStaticBlurParams().getBlurRadius())) : null;
            this.$scalePair.element = StaticBlurManager.access$convertBitmapSizeToScreenSize(StaticBlurManager.this, (Bitmap) this.$blurredBitmap.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.blurability.screenshot.StaticBlurManager$blurAndSetBackground$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass4 implements Runnable {
        public /* synthetic */ Ref.ObjectRef $blurredBitmap;
        public /* synthetic */ Ref.ObjectRef $scalePair;
        public /* synthetic */ StaticBlurManager this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.scalePair = (Pair) this.$scalePair.element;
            this.this$0.setScreenBlurredBitmap((Bitmap) this.$blurredBitmap.element);
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.blurability.screenshot.StaticBlurManager$onCompressedFinish$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C03161 extends SuspendLambda implements Function2 {
        final /* synthetic */ Bitmap $compressedBitmap;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03161(Bitmap bitmap, Continuation continuation) {
            super(2, continuation);
            this.$compressedBitmap = bitmap;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return StaticBlurManager.this.new C03161(this.$compressedBitmap, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            List<BlurStepsObserver> list = CollectionsKt___CollectionsKt.toList(StaticBlurManager.this.blurStepObservers);
            Bitmap bitmap = this.$compressedBitmap;
            for (BlurStepsObserver blurStepsObserver : list) {
                if (blurStepsObserver != null) {
                    blurStepsObserver.onCompressedFinish(bitmap);
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.blurability.screenshot.StaticBlurManager$removeStaticBlurDrawable$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class C03171 implements Predicate {
        public /* synthetic */ StaticBlurDrawable $drawable;

        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return Intrinsics.areEqual(((WeakReference) obj).get(), this.$drawable);
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.blurability.screenshot.StaticBlurManager$runOnMainImmediately$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ Runnable $action;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Runnable runnable, Continuation continuation) {
            super(2, continuation);
            this.$action = runnable;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.$action, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            this.$action.run();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.blurability.screenshot.StaticBlurManager$startBlurJobWithUx$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C03181 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function2 $block;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03181(Function2 function2, Continuation continuation) {
            super(2, continuation);
            this.$block = function2;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            C03181 c03181 = StaticBlurManager.this.new C03181(this.$block, continuation);
            c03181.L$0 = obj;
            return c03181;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StaticBlurManager.this.setThreadUxFlag(true);
                Function2 function2 = this.$block;
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.label = 1;
                if (function2.invoke(coroutineScope, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public static WindowManager $r8$lambda$fTTJvFEq_wyoEvS0ltw0bwGa7O4(StaticBlurManager staticBlurManager) {
        return (WindowManager) staticBlurManager.context.getSystemService("window");
    }

    public static Unit $r8$lambda$fTVw8FmTkXLprD_4Y7ZTshagdnE(StaticBlurManager staticBlurManager) {
        staticBlurManager.setThreadUxFlag(false);
        return Unit.INSTANCE;
    }

    public StaticBlurManager(Context context, StaticBlurParams staticBlurParams) {
        this.context = context;
        this.staticBlurParams = staticBlurParams;
        StaticBlurManager$$ExternalSyntheticLambda1 staticBlurManager$$ExternalSyntheticLambda1 = new StaticBlurManager$$ExternalSyntheticLambda1();
        staticBlurManager$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.windowManager$delegate = LazyKt__LazyJVMKt.lazy(staticBlurManager$$ExternalSyntheticLambda1);
        Float fValueOf = Float.valueOf(0.0f);
        this.scalePair = new Pair<>(fValueOf, fValueOf);
    }

    public static final Pair access$convertBitmapSizeToScreenSize(StaticBlurManager staticBlurManager, Bitmap bitmap) {
        staticBlurManager.getClass();
        Float fValueOf = Float.valueOf(0.0f);
        if (bitmap == null || bitmap.isRecycled()) {
            return new Pair(fValueOf, fValueOf);
        }
        WindowMetrics currentWindowMetrics = staticBlurManager.getWindowManager().getCurrentWindowMetrics();
        int iWidth = currentWindowMetrics.getBounds().width();
        int iHeight = currentWindowMetrics.getBounds().height();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("[convertBitmapSizeToScreenSize] screenWidth:", iWidth, iHeight, " screenHeight: ", "StaticBlurManager-->"));
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if ((iWidth > iHeight && width < height) || (iWidth < iHeight && width > height)) {
            iWidth = currentWindowMetrics.getBounds().height();
            iHeight = currentWindowMetrics.getBounds().width();
        }
        return new Pair(Float.valueOf((bitmap.getWidth() * 1.0f) / iWidth), Float.valueOf((bitmap.getHeight() * 1.0f) / iHeight));
    }

    private final CoroutineScope getBlurScope() {
        return (CoroutineScope) this.blurScope$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WindowManager getWindowManager() {
        return (WindowManager) this.windowManager$delegate.getValue();
    }

    public static Object runOnMainImmediately(Runnable runnable, ContinuationImpl continuationImpl) {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain().getImmediate(), new AnonymousClass2(runnable, null), continuationImpl);
        return objWithContext == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setScreenBlurredBitmap(Bitmap bitmap) {
        if (Intrinsics.areEqual(this.screenBlurredBitmap, bitmap)) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "StaticBlurManager-->".concat("[setScreenBlurredBitmap] set " + bitmap));
        }
        if (bitmap != null && bitmap.isRecycled()) {
            LogUtil.w$default(8, LogUtil.TAG_COMMON, TAG, "[setScreenBlurredBitmap] set " + bitmap + " is recycled.");
            return;
        }
        this.screenBlurredBitmap = bitmap;
        for (BlurStepsObserver blurStepsObserver : CollectionsKt___CollectionsKt.toList(this.blurStepObservers)) {
            if (blurStepsObserver != null) {
                blurStepsObserver.onBlurredFinish(this.screenBlurredBitmap, this.scalePair);
            }
        }
        Iterator it = CollectionsKt___CollectionsKt.toList(this.staticBlurDrawables).iterator();
        while (it.hasNext()) {
            StaticBlurDrawable staticBlurDrawable = (StaticBlurDrawable) ((WeakReference) it.next()).get();
            if (staticBlurDrawable != null) {
                staticBlurDrawable.setBitmapAndScale(this.screenBlurredBitmap, this.scalePair);
            }
        }
        this.staticBlurDrawables.removeIf(StaticBlurManager$clearNullRef$1.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTempDegree(Integer num) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "StaticBlurManager-->".concat("[rotateDisplay] setTempRotate:" + num));
        }
        Iterator it = CollectionsKt___CollectionsKt.toList(this.staticBlurDrawables).iterator();
        while (it.hasNext()) {
            StaticBlurDrawable staticBlurDrawable = (StaticBlurDrawable) ((WeakReference) it.next()).get();
            if (staticBlurDrawable != null) {
                staticBlurDrawable.setTempDegree(num);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setThreadUxFlag(boolean z) {
        Object obj;
        Trace.traceBegin(8L, "StaticBlurManager-setThreadUxFlag-" + z);
        int i = z ? 130 : 2;
        if (z) {
            this.workThreadId = Process.myTid();
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "StaticBlurManager-->".concat("[setThreadUxFlag] pid=" + Process.myPid() + ", tid=" + this.workThreadId + ", enable=" + z));
        }
        try {
            OplusFeatureCache.getOrCreate(IOplusUIFirstManager.DEFAULT, new Object[0]).setUxThreadValue(Process.myPid(), this.workThreadId, String.valueOf(i));
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "[setThreadUxFlag] to ", " has exception, ");
            sbM.append(th2.getMessage());
            LogUtil.e$default(8, LogUtil.TAG_COMMON, TAG, sbM.toString());
        }
        Trace.traceEnd(8L);
    }

    public static /* synthetic */ void startScreenShotBlur$default(StaticBlurManager staticBlurManager, String str, IBinder[] iBinderArr, Function0 function0, ShutdownBlurManager$.ExternalSyntheticLambda3 externalSyntheticLambda3, int i) {
        boolean z = (i & 2) == 0;
        if ((i & 4) != 0) {
            iBinderArr = null;
        }
        if ((i & 8) != 0) {
            function0 = null;
        }
        staticBlurManager.startScreenShotBlur(str, z, iBinderArr, function0, (i & 16) != 0 ? null : externalSyntheticLambda3);
    }

    public final boolean addBlurredBitmapObserver(BlurStepsObserver blurStepsObserver) {
        if (this.blurStepObservers.contains(blurStepsObserver)) {
            return false;
        }
        this.blurStepObservers.add(blurStepsObserver);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x007c, code lost:
    
        if (runOnMainImmediately(r15, r0) == r1) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00dc, code lost:
    
        if (runOnMainImmediately(r12, r0) == r1) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object blurAndSetBackground(android.graphics.Bitmap r13, float r14, kotlin.coroutines.jvm.internal.ContinuationImpl r15) {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplusos.systemui.common.blurability.screenshot.StaticBlurManager.blurAndSetBackground(android.graphics.Bitmap, float, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:15:0x002d  */
    public final void copyViewAndBlur(ImageView imageView, Function1 function1) {
        Bitmap bitmapCreateBitmap;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "StaticBlurManager-->[copyViewAndBlur]");
        }
        Job job = this.blurJob;
        if (job != null) {
            Job.Key key = Job.Key;
            job.cancel((CancellationException) null);
        }
        ViewParent parent = imageView.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup == null) {
            bitmapCreateBitmap = null;
        } else {
            try {
                float compressRatioOfScreenSize = this.staticBlurParams.getCompressRatioOfScreenSize();
                Matrix matrix = new Matrix();
                matrix.setTranslate(-viewGroup.getScrollX(), -viewGroup.getScrollY());
                imageView.transformMatrixToGlobal(matrix);
                viewGroup.transformMatrixToLocal(matrix);
                matrix.mapRect(new RectF(0.0f, 0.0f, imageView.getWidth(), imageView.getHeight()));
                matrix.postTranslate(0.0f, 0.0f);
                matrix.postScale(compressRatioOfScreenSize, compressRatioOfScreenSize);
                if (imageView.getWidth() <= 0 || imageView.getHeight() <= 0) {
                    bitmapCreateBitmap = null;
                } else {
                    Picture picture = new Picture();
                    Canvas canvasBeginRecording = picture.beginRecording((int) (imageView.getWidth() * compressRatioOfScreenSize), (int) (imageView.getHeight() * compressRatioOfScreenSize));
                    canvasBeginRecording.concat(matrix);
                    imageView.draw(canvasBeginRecording);
                    picture.endRecording();
                    bitmapCreateBitmap = Bitmap.createBitmap(picture);
                }
            } catch (Throwable th) {
                Object obj = Result.constructor-impl(ResultKt.createFailure(th));
                if (obj instanceof Result.Failure) {
                    obj = null;
                }
                bitmapCreateBitmap = (Bitmap) obj;
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "StaticBlurManager-->".concat("[copyViewAndBlur] from view is " + bitmapCreateBitmap));
        }
        this.blurJob = startBlurJobWithUx(new copyViewAndBlur.3(function1, bitmapCreateBitmap, this, (Continuation) null));
    }

    public final Pair<Float, Float> getScalePair() {
        return this.scalePair;
    }

    public final Bitmap getScreenBlurredBitmap() {
        return this.screenBlurredBitmap;
    }

    public final StaticBlurParams getStaticBlurParams() {
        return this.staticBlurParams;
    }

    public final void onCompressedFinish(Bitmap bitmap) {
        if (Intrinsics.areEqual(Looper.getMainLooper(), Looper.myLooper())) {
            BuildersKt.launch$default(getBlurScope(), (CoroutineContext) null, (CoroutineStart) null, new C03161(bitmap, null), 3);
            return;
        }
        for (BlurStepsObserver blurStepsObserver : CollectionsKt___CollectionsKt.toList(this.blurStepObservers)) {
            if (blurStepsObserver != null) {
                blurStepsObserver.onCompressedFinish(bitmap);
            }
        }
    }

    public final boolean registerStaticBlurDrawable(StaticBlurDrawable staticBlurDrawable) {
        this.staticBlurDrawables.removeIf(StaticBlurManager$clearNullRef$1.INSTANCE);
        HashSet<WeakReference<StaticBlurDrawable>> hashSet = this.staticBlurDrawables;
        if (hashSet == null || !hashSet.isEmpty()) {
            Iterator<T> it = hashSet.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(((WeakReference) it.next()).get(), staticBlurDrawable)) {
                    return false;
                }
            }
        }
        this.staticBlurDrawables.add(new WeakReference<>(staticBlurDrawable));
        return true;
    }

    public final boolean removeBlurredBitmapObserver(BlurStepsObserver blurStepsObserver) {
        return this.blurStepObservers.remove(blurStepsObserver);
    }

    public final boolean removeStaticBlurDrawable(StaticBlurDrawable staticBlurDrawable) {
        HashSet<WeakReference<StaticBlurDrawable>> hashSet = this.staticBlurDrawables;
        C03171 c03171 = new C03171();
        c03171.$drawable = staticBlurDrawable;
        VarHandle.storeStoreFence();
        return hashSet.removeIf(c03171);
    }

    public final void rotateBlurBitmap(int i, String str, IBinder[] iBinderArr, Function0 function0) {
        Job job = this.blurJob;
        if (job != null && job.isActive()) {
            startScreenShotBlur$default(this, str, iBinderArr, function0, null, 16);
            return;
        }
        Bitmap bitmap = this.screenBlurredBitmap;
        if (bitmap != null) {
            setTempDegree(Integer.valueOf(i));
            this.blurJob = startBlurJobWithUx(new rotateBlurBitmap.1.1(this, i, bitmap, (Continuation) null));
        }
    }

    public final Job startBlurJobWithUx(Function2 function2) {
        Job jobLaunch$default = BuildersKt.launch$default(getBlurScope(), (CoroutineContext) null, (CoroutineStart) null, new C03181(function2, null), 3);
        StaticBlurManager$$ExternalSyntheticLambda3 staticBlurManager$$ExternalSyntheticLambda3 = new StaticBlurManager$$ExternalSyntheticLambda3();
        staticBlurManager$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        jobLaunch$default.invokeOnCompletion(staticBlurManager$$ExternalSyntheticLambda3);
        return jobLaunch$default;
    }

    public final void startScreenShotBlur(String str, boolean z, IBinder[] iBinderArr, Function0 function0, Function1 function1) {
        Job job;
        if (z || (((job = this.blurJob) == null || !job.isActive()) && this.screenBlurredBitmap == null)) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[startScreenShotBlur] force=", z), null, 8);
            Job job2 = this.blurJob;
            if (job2 != null) {
                Job.Key key = Job.Key;
                job2.cancel((CancellationException) null);
            }
            this.blurJob = startBlurJobWithUx(new startScreenShotBlur.2(this, iBinderArr, function0, str, function1, (Continuation) null));
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            StringBuilder sb = new StringBuilder("[startScreenShotBlur] captureJob?.isActive=");
            Job job3 = this.blurJob;
            sb.append(job3 != null ? Boolean.valueOf(job3.isActive()) : null);
            sb.append(", screenBlurredBitmap=");
            sb.append(this.screenBlurredBitmap);
            Log.d("SystemUi--Common", "StaticBlurManager-->".concat(sb.toString()));
        }
    }

    public final void stopScreenShotBlur() {
        Float fValueOf = Float.valueOf(0.0f);
        Job job = this.blurJob;
        if ((job == null || !job.isActive()) && this.screenBlurredBitmap == null && Intrinsics.areEqual(this.scalePair, new Pair(fValueOf, fValueOf))) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[stopScreenShotBlur] set null", null, 8);
        Job job2 = this.blurJob;
        if (job2 != null) {
            Job.Key key = Job.Key;
            job2.cancel((CancellationException) null);
        }
        this.scalePair = new Pair<>(fValueOf, fValueOf);
        setScreenBlurredBitmap(null);
        onCompressedFinish(null);
    }
}
