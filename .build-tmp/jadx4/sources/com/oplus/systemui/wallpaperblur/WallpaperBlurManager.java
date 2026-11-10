package com.oplus.systemui.wallpaperblur;

import android.app.OplusWallpaperManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.DependencyEx;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplusos.systemui.common.blurability.ext.BitmapExtKt;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WallpaperBlurManager implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "WallpaperBlurManager";
    private final WallpaperBlurManager$colorChangeListener$1 colorChangeListener;
    private final Context context;
    private Job currentJob;
    private Bitmap currentWallpaperBitmap;
    private BitmapDrawable currentWallpaperBlurDrawable;
    private Bitmap lastOriginalBitmap;
    public int overColor;
    private final OplusWallpaperManager oplusWallpaperManager = new OplusWallpaperManager();
    private HashMap<View, WallpaperBlurDrawable> wallpaperBlurViews = new HashMap<>();
    private final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob((Job) null).plus(Dispatchers.getMain()));

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.wallpaperblur.WallpaperBlurManager$refreshCurrentWallpaperBlurDrawable$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.wallpaperblur.WallpaperBlurManager$refreshCurrentWallpaperBlurDrawable$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C01771 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ WallpaperBlurManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01771(WallpaperBlurManager wallpaperBlurManager, Continuation continuation) {
                super(2, continuation);
                this.this$0 = wallpaperBlurManager;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C01771(this.this$0, continuation);
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
                WallpaperBlurManager wallpaperBlurManager = this.this$0;
                wallpaperBlurManager.setOverColor(wallpaperBlurManager.currentWallpaperBitmap);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return WallpaperBlurManager.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Ref.ObjectRef objectRef;
            Ref.BooleanRef booleanRef;
            Ref.ObjectRef objectRef2;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                objectRef = new Ref.ObjectRef();
                CoroutineDispatcher io = Dispatchers.getIO();
                WallpaperBlurManager$refreshCurrentWallpaperBlurDrawable$1$blurBitmap$1 wallpaperBlurManager$refreshCurrentWallpaperBlurDrawable$1$blurBitmap$1 = new WallpaperBlurManager$refreshCurrentWallpaperBlurDrawable$1$blurBitmap$1(objectRef, WallpaperBlurManager.this, booleanRef2, null);
                this.L$0 = booleanRef2;
                this.L$1 = objectRef;
                this.label = 1;
                Object objWithContext = BuildersKt.withContext(io, wallpaperBlurManager$refreshCurrentWallpaperBlurDrawable$1$blurBitmap$1, this);
                if (objWithContext != coroutine_suspended) {
                    booleanRef = booleanRef2;
                    obj = objWithContext;
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                objectRef = (Ref.ObjectRef) this.L$1;
                booleanRef = (Ref.BooleanRef) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                objectRef2 = (Ref.ObjectRef) this.L$1;
                ResultKt.throwOnFailure(obj);
            }
            ((ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class)).setWallpaperBlurBitmap(WallpaperBlurManager.this.currentWallpaperBitmap);
            WallpaperBlurManager.access$updateDrawable(WallpaperBlurManager.this);
            WallpaperBlurManager.this.lastOriginalBitmap = (Bitmap) objectRef2.element;
            return Unit.INSTANCE;
            Bitmap bitmap = (Bitmap) obj;
            if (booleanRef.element) {
                return Unit.INSTANCE;
            }
            if (bitmap == null || bitmap.isRecycled()) {
                LogUtil.e$default(8, LogUtil.TAG_COMMON, WallpaperBlurManager.TAG, "blurBitmap is null or recycled");
                return Unit.INSTANCE;
            }
            WallpaperBlurManager.this.currentWallpaperBitmap = bitmap;
            WallpaperBlurManager wallpaperBlurManager = WallpaperBlurManager.this;
            wallpaperBlurManager.currentWallpaperBlurDrawable = new BitmapDrawable(wallpaperBlurManager.context.getResources(), bitmap);
            CoroutineDispatcher io2 = Dispatchers.getIO();
            C01771 c01771 = new C01771(WallpaperBlurManager.this, null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(booleanRef);
            this.L$1 = objectRef;
            this.L$2 = SpillingKt.nullOutSpilledVariable(bitmap);
            this.label = 2;
            if (BuildersKt.withContext(io2, c01771, this) != coroutine_suspended) {
                objectRef2 = objectRef;
                ((ViewBlurManager) DependencyEx.sDependency.getDependency(ViewBlurManager.class)).setWallpaperBlurBitmap(WallpaperBlurManager.this.currentWallpaperBitmap);
                WallpaperBlurManager.access$updateDrawable(WallpaperBlurManager.this);
                WallpaperBlurManager.this.lastOriginalBitmap = (Bitmap) objectRef2.element;
                return Unit.INSTANCE;
            }
            return coroutine_suspended;
        }
    }

    public WallpaperBlurManager(Context context, DumpManager dumpManager) {
        this.context = context;
        WallpaperBlurManager$colorChangeListener$1 wallpaperBlurManager$colorChangeListener$1 = new WallpaperBlurManager$colorChangeListener$1();
        wallpaperBlurManager$colorChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.colorChangeListener = wallpaperBlurManager$colorChangeListener$1;
        if (!ScrimUtil.isWallpaperBlurDisable()) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "addOnColorsChangedListener", null, 8);
            Handler mainHandler = ThreadManager.Companion.getInstance().getMainHandler();
            if (mainHandler != null) {
                WallpaperManager.getInstance(context).addOnColorsChangedListener(wallpaperBlurManager$colorChangeListener$1, mainHandler, -1);
            }
        }
        dumpManager.registerNormalDumpable(TAG, this);
    }

    public static final boolean access$shouldUpdateDrawable(WallpaperBlurManager wallpaperBlurManager, Bitmap bitmap) {
        return !Intrinsics.areEqual(bitmap, wallpaperBlurManager.lastOriginalBitmap);
    }

    public static final void access$updateDrawable(WallpaperBlurManager wallpaperBlurManager) {
        for (WallpaperBlurDrawable wallpaperBlurDrawable : wallpaperBlurManager.wallpaperBlurViews.values()) {
            wallpaperBlurDrawable.setDrawableConfig(wallpaperBlurManager.currentWallpaperBlurDrawable, wallpaperBlurManager.overColor);
            wallpaperBlurDrawable.invalidateSelf();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setOverColor(Bitmap bitmap) {
        Integer bitmapBrightness;
        if (bitmap == null || bitmap.isRecycled() || (bitmapBrightness = BitmapExtKt.getBitmapBrightness(bitmap)) == null) {
            return;
        }
        int iIntValue = bitmapBrightness.intValue();
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(iIntValue, "getBitmapBrightness = "), null, 8);
        double d = iIntValue;
        Context context = this.context;
        this.overColor = d > 38.25d ? context.getColor(2131103097) : context.getColor(2131103096);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("WallpaperBlurManager:dump start!!!");
        printWriter.println("currentWallpaperBitmap:" + this.currentWallpaperBitmap + ", lastOriginalBitmap : " + this.lastOriginalBitmap + " ");
        printWriter.println("WallpaperBlurManager:dump end!!!");
    }

    public final void refreshCurrentWallpaperBlurDrawable() {
        Job job = this.currentJob;
        if (job != null) {
            Job.Key key = Job.Key;
            job.cancel((CancellationException) null);
        }
        this.currentJob = BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
    }

    public final void releaseWallpaperBlurDrawableForView(View view) {
        this.wallpaperBlurViews.remove(view);
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "releaseWallpaperBlurDrawableForView size: " + this.wallpaperBlurViews.size() + ",view: " + view, null, 8);
    }

    public final WallpaperBlurDrawable requireWallpaperBlurDrawableForView(View view, boolean z) {
        if (this.lastOriginalBitmap == null) {
            refreshCurrentWallpaperBlurDrawable();
        }
        if (this.wallpaperBlurViews.get(view) != null) {
            return this.wallpaperBlurViews.get(view);
        }
        WallpaperBlurDrawable wallpaperBlurDrawable = new WallpaperBlurDrawable();
        wallpaperBlurDrawable.isOverlayEnabled = z;
        VarHandle.storeStoreFence();
        this.wallpaperBlurViews.put(view, wallpaperBlurDrawable);
        wallpaperBlurDrawable.setDrawableConfig(this.currentWallpaperBlurDrawable, this.overColor);
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "requireWallpaperBlurDrawableForView size: " + this.wallpaperBlurViews.size() + ",view: " + view, null, 8);
        return wallpaperBlurDrawable;
    }
}
