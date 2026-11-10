package com.oplus.systemui.media.seedling.client;

import android.media.session.MediaSession;
import com.android.systemui.media.controls.shared.model.MediaData;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class AbsMediaClient$handleBgMediaDataLoaded$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ MediaData $data;
    final /* synthetic */ long $newDuration;
    final /* synthetic */ MediaSession.Token $newToken;
    int I$0;
    int I$1;
    Object L$0;
    int label;
    final /* synthetic */ AbsMediaClient this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.media.seedling.client.AbsMediaClient$handleBgMediaDataLoaded$2$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Ref.BooleanRef $shouldDispatch;
        int label;
        final /* synthetic */ AbsMediaClient this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(AbsMediaClient absMediaClient, Ref.BooleanRef booleanRef, Continuation continuation) {
            super(2, continuation);
            this.this$0 = absMediaClient;
            this.$shouldDispatch = booleanRef;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, this.$shouldDispatch, continuation);
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
            AbsMediaClient absMediaClient = this.this$0;
            if (absMediaClient.isDestroyed) {
                return Unit.INSTANCE;
            }
            if (this.$shouldDispatch.element) {
                absMediaClient.dispatchMediaDataLoaded(0, "bgHandler onMediaDataLoaded");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbsMediaClient$handleBgMediaDataLoaded$2(AbsMediaClient absMediaClient, MediaSession.Token token, long j, MediaData mediaData, Continuation continuation) {
        super(2, continuation);
        this.this$0 = absMediaClient;
        this.$newToken = token;
        this.$newDuration = j;
        this.$data = mediaData;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new AbsMediaClient$handleBgMediaDataLoaded$2(this.this$0, this.$newToken, this.$newDuration, this.$data, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:68:0x0186 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:69:0x0188  */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0195, code lost:
    
        if (com.oplus.systemui.media.seedling.client.AbsMediaClient.access$bindSeedlingMediaActionButtons(r8, r14, r13) == r0) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01d3, code lost:
    
        if (kotlinx.coroutines.BuildersKt.withContext(r14, r2, r13) == r0) goto L84;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 473
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.media.seedling.client.AbsMediaClient$handleBgMediaDataLoaded$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
