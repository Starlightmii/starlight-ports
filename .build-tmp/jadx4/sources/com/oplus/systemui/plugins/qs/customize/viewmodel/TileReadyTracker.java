package com.oplus.systemui.plugins.qs.customize.viewmodel;

import android.support.v4.content.FileProvider$;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.systemui.plugins.qs.QSTile;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileReadyTracker implements QSTile.Callback {
    public static final Companion Companion = new Companion();
    private static final String TAG = "TileTracker";
    private final CoroutineExceptionHandler exceptionHandler;
    private final List<TilePair> trackingPairs;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.TileReadyTracker$startTracking$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ long $outTime;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.viewmodel.TileReadyTracker$startTracking$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ TileReadyTracker this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(TileReadyTracker tileReadyTracker, Continuation continuation) {
                super(2, continuation);
                this.this$0 = tileReadyTracker;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i != 0) {
                    if (i == 1) {
                        ResultKt.throwOnFailure(obj);
                        return obj;
                    }
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                List list = this.this$0.trackingPairs;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((TilePair) it.next()).getDeferred());
                }
                this.label = 1;
                Object objAwaitAll = AwaitKt.awaitAll(arrayList, this);
                return objAwaitAll == coroutine_suspended ? coroutine_suspended : objAwaitAll;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(long j, Continuation continuation) {
            super(2, continuation);
            this.$outTime = j;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return TileReadyTracker.this.new AnonymousClass1(this.$outTime, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    List<TilePair> list = TileReadyTracker.this.trackingPairs;
                    TileReadyTracker tileReadyTracker = TileReadyTracker.this;
                    for (TilePair tilePair : list) {
                        tilePair.getQsTile().addCallback(tileReadyTracker);
                        if (tilePair.getQsTile().isTileReady()) {
                            tileReadyTracker.handleTileReady(tilePair);
                        }
                    }
                    long j = this.$outTime;
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(TileReadyTracker.this, null);
                    this.label = 1;
                    if (TimeoutKt.withTimeout(j, anonymousClass2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
            } catch (CancellationException e) {
                TileReadyTracker.access$cancelTracking(TileReadyTracker.this, e, "cancel");
            } catch (TimeoutCancellationException e2) {
                TileReadyTracker.access$cancelTracking(TileReadyTracker.this, e2, "timeout");
            } finally {
                TileReadyTracker.access$cleanupCallbacks(TileReadyTracker.this);
            }
            return Unit.INSTANCE;
        }
    }

    public TileReadyTracker(List<? extends QSTile> list) {
        List<? extends QSTile> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(new TilePair((QSTile) it.next(), false, CompletableDeferredKt.CompletableDeferred((Job) null)));
        }
        this.trackingPairs = arrayList;
        this.exceptionHandler = new TileReadyTracker$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key, this);
    }

    public static final void access$cancelTracking(TileReadyTracker tileReadyTracker, Throwable th, String str) {
        tileReadyTracker.getClass();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "cancelTracking: ".concat(str), null, 8);
        for (TilePair tilePair : tileReadyTracker.trackingPairs) {
            if (!tilePair.getDeferred().isCompleted()) {
                tilePair.getDeferred().completeExceptionally(th);
            }
        }
    }

    public static final void access$cleanupCallbacks(TileReadyTracker tileReadyTracker) {
        tileReadyTracker.getClass();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "cleanupCallbacks", null, 8);
        for (TilePair tilePair : tileReadyTracker.trackingPairs) {
            if (!tilePair.getReady()) {
                tilePair.getQsTile().removeCallback(tileReadyTracker);
            }
        }
    }

    public final void handleTileReady(TilePair tilePair) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, FileProvider$.ExternalSyntheticOutline0.m("handleTileReady: ", tilePair.getQsTile().getTileSpec()), null, 8);
        if (tilePair.getReady() || tilePair.getDeferred().isCompleted()) {
            return;
        }
        tilePair.setReady(true);
        tilePair.getDeferred().makeCompleting$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Unit.INSTANCE);
        tilePair.getQsTile().removeCallback(this);
    }

    public void onStateChanged(QSTile.State state) {
        for (TilePair tilePair : this.trackingPairs) {
            if (!tilePair.getReady() && tilePair.getQsTile().isTileReady()) {
                handleTileReady(tilePair);
            }
        }
    }

    public final Job startTracking(CoroutineScope coroutineScope, long j) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "startTracking: " + j + " " + this.trackingPairs.size(), null, 8);
        return CoroutineTracingKt.launchTraced$default(coroutineScope, "TileTracker-launch-startTracking", this.exceptionHandler, (CoroutineStart) null, new AnonymousClass1(j, null), 4);
    }
}
