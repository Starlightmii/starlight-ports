package com.oplus.systemui.media.controls.pipeline;

import android.media.MediaMetadata;
import android.os.UserHandle;
import com.android.systemui.media.controls.shared.model.MediaData;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusMediaDataManagerExImpl$onMetaDataChanged$1$newArtworkIcon$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ MediaData $mediaData;
    final /* synthetic */ MediaMetadata $metaData;
    int label;
    final /* synthetic */ OplusMediaDataManagerExImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusMediaDataManagerExImpl$onMetaDataChanged$1$newArtworkIcon$1(OplusMediaDataManagerExImpl oplusMediaDataManagerExImpl, MediaMetadata mediaMetadata, MediaData mediaData, Continuation continuation) {
        super(2, continuation);
        this.this$0 = oplusMediaDataManagerExImpl;
        this.$metaData = mediaMetadata;
        this.$mediaData = mediaData;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new OplusMediaDataManagerExImpl$onMetaDataChanged$1$newArtworkIcon$1(this.this$0, this.$metaData, this.$mediaData, continuation);
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
        OplusMediaDataManagerExImpl oplusMediaDataManagerExImpl = this.this$0;
        MediaMetadata mediaMetadata = this.$metaData;
        String packageName = this.$mediaData.getPackageName();
        UserHandle userHandleOf = UserHandle.of(this.$mediaData.getUserId());
        this.label = 1;
        Object artWorkIconByMediaMetaData = oplusMediaDataManagerExImpl.getArtWorkIconByMediaMetaData(mediaMetadata, packageName, userHandleOf, this);
        return artWorkIconByMediaMetaData == coroutine_suspended ? coroutine_suspended : artWorkIconByMediaMetaData;
    }
}
