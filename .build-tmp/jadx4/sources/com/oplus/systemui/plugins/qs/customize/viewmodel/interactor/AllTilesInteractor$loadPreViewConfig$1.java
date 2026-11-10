package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import com.oplus.systemui.plugins.qs.customize.data.repo.SepMoreTileConfigRepository;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class AllTilesInteractor$loadPreViewConfig$1 extends SuspendLambda implements Function2 {
    Object L$0;
    int label;
    final /* synthetic */ AllTilesInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AllTilesInteractor$loadPreViewConfig$1(AllTilesInteractor allTilesInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = allTilesInteractor;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new AllTilesInteractor$loadPreViewConfig$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) throws Throwable {
        AllTilesInteractor allTilesInteractor;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AllTilesInteractor allTilesInteractor2 = this.this$0;
            SepMoreTileConfigRepository sepMoreTileConfigRepository = allTilesInteractor2.moreTileConfigRepo;
            this.L$0 = allTilesInteractor2;
            this.label = 1;
            Object previewConfig = sepMoreTileConfigRepository.getPreviewConfig(this);
            if (previewConfig == coroutine_suspended) {
                return coroutine_suspended;
            }
            allTilesInteractor = allTilesInteractor2;
            obj = previewConfig;
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            allTilesInteractor = (AllTilesInteractor) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        allTilesInteractor.tileTypeConfig = (List) obj;
        List list = this.this$0.tileTypeConfig;
        if (list == null) {
            list = null;
        }
        LogUtil.i$default(LogUtil.TAG_QS, "AllTilesInteractor", "startLoadPreViewConfig result = " + list, null, 8);
        MutableStateFlow mutableStateFlow = this.this$0._tileTypeConfigFlow;
        List list2 = this.this$0.tileTypeConfig;
        mutableStateFlow.setValue(list2 != null ? list2 : null);
        return Unit.INSTANCE;
    }
}
