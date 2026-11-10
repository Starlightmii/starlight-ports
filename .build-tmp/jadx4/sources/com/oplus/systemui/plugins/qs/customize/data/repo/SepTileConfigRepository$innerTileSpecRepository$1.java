package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.android.app.tracing.coroutines.flow.FlowExtKt;
import com.android.systemui.plugins.qs.SepTileSpecRepository;
import java.lang.invoke.VarHandle;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepTileConfigRepository$innerTileSpecRepository$1 implements SepTileSpecRepository {
    public /* synthetic */ SepTileConfigRepository this$0;

    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0127, code lost:
    
        if (r13.addTilesNonLoc(r5, r2, true, r1) == r14) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object addTile(int r11, java.lang.String r12, int r13, kotlin.coroutines.Continuation r14) {
        /*
            Method dump skipped, instruction units count: 304
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigRepository$innerTileSpecRepository$1.addTile(int, java.lang.String, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object operateTiles(int i, List list, Continuation continuation) {
        Object objOperateTiles;
        SepTileConfigRepository sepTileConfigRepository = this.this$0;
        if (((Boolean) sepTileConfigRepository.superPowerState.getValue()).booleanValue()) {
            Object objOperateTiles2 = sepTileConfigRepository.superPowerTileConfigRepository.operateTiles(list, continuation);
            return objOperateTiles2 == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objOperateTiles2 : Unit.INSTANCE;
        }
        if (((Boolean) sepTileConfigRepository.willBeingState.getValue()).booleanValue()) {
            Object objOperateTiles3 = sepTileConfigRepository.wellBeingTileConfigRepository.operateTiles(list, continuation);
            return objOperateTiles3 == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objOperateTiles3 : Unit.INSTANCE;
        }
        SepUserTileConfigRepository sepUserTileConfigRepository = (SepUserTileConfigRepository) sepTileConfigRepository.userTileRepositories.get(Boxing.boxInt(i));
        return (sepUserTileConfigRepository == null || (objOperateTiles = sepUserTileConfigRepository.operateTiles(list, continuation)) != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objOperateTiles;
    }

    public final Object reconcileRestore(int i, String str, boolean z, Set set, Set set2, Continuation continuation) {
        Object objReconcileRestore;
        SepTileConfigRepository sepTileConfigRepository = this.this$0;
        sepTileConfigRepository.separateQsTileLogger.logTile("reconcileRestore: " + str + ", autoAdd:" + set + ", restoredAutoAdded:" + set2 + " userId: " + i + ", repository: " + sepTileConfigRepository.userTileRepositories.get(Boxing.boxInt(i)));
        SepUserTileConfigRepository sepUserTileConfigRepository = (SepUserTileConfigRepository) sepTileConfigRepository.userTileRepositories.get(Boxing.boxInt(i));
        return (sepUserTileConfigRepository == null || (objReconcileRestore = sepUserTileConfigRepository.reconcileRestore(str, z, set, set2, continuation)) != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objReconcileRestore;
    }

    public final Object removeTiles(int i, Collection collection, Continuation continuation) {
        Object objRemoveTiles;
        SepTileConfigRepository sepTileConfigRepository = this.this$0;
        sepTileConfigRepository.separateQsTileLogger.logTile("removeTiles: " + collection + ", userId: " + i + ",repository: " + sepTileConfigRepository.userTileRepositories + "[userId]");
        SepUserTileConfigRepository sepUserTileConfigRepository = (SepUserTileConfigRepository) sepTileConfigRepository.userTileRepositories.get(Boxing.boxInt(i));
        return (sepUserTileConfigRepository == null || (objRemoveTiles = sepUserTileConfigRepository.removeTiles(collection, continuation)) != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objRemoveTiles;
    }

    public final Object removeUser(int i, Continuation continuation) {
        Object objAccess$removeUserTileConfigRepository = SepTileConfigRepository.access$removeUserTileConfigRepository(i, this.this$0, continuation);
        return objAccess$removeUserTileConfigRepository == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAccess$removeUserTileConfigRepository : Unit.INSTANCE;
    }

    public void setSuperPowerState(boolean z) {
        this.this$0.superPowerState.setValue(Boolean.valueOf(z));
    }

    public final Object setTiles(int i, List list, Continuation continuation) {
        Object tiles;
        SepUserTileConfigRepository sepUserTileConfigRepository = (SepUserTileConfigRepository) this.this$0.userTileRepositories.get(Boxing.boxInt(i));
        return (sepUserTileConfigRepository == null || (tiles = sepUserTileConfigRepository.setTiles(list, continuation)) != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : tiles;
    }

    public void setWillBeingState(boolean z) {
        this.this$0.willBeingState.setValue(Boolean.valueOf(z));
    }

    public final Object tilesSpecs(int i, Continuation continuation) {
        SepTileConfigRepository sepTileConfigRepository = this.this$0;
        Flow flowTransformLatest = FlowKt.transformLatest(sepTileConfigRepository.specialModeState, new SepTileConfigRepository$innerTileSpecRepository$1$tilesSpecs$$inlined$flatMapLatest$1(i, sepTileConfigRepository, null));
        SepTileConfigRepository$innerTileSpecRepository$1$tilesSpecs$$inlined$map$1 sepTileConfigRepository$innerTileSpecRepository$1$tilesSpecs$$inlined$map$1 = new SepTileConfigRepository$innerTileSpecRepository$1$tilesSpecs$$inlined$map$1();
        sepTileConfigRepository$innerTileSpecRepository$1$tilesSpecs$$inlined$map$1.$this_unsafeTransform$inlined = flowTransformLatest;
        VarHandle.storeStoreFence();
        return FlowExtKt.traceAs(sepTileConfigRepository$innerTileSpecRepository$1$tilesSpecs$$inlined$map$1, "SepTileConfigRepository-tilesSpecs");
    }
}
