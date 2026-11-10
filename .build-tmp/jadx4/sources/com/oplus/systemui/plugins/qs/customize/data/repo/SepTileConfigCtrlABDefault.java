package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.android.systemui.plugins.qs.TileSpecOperate;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.MoveTile;
import com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.RemoveTiles;
import com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.ReplaceTiles;
import com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.SwapTile;
import com.oplusos.systemui.common.model.Loc;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.MutableSharedFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepTileConfigCtrlABDefault implements ISepTileConfigCtrlAB {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SepTileConfigCtrlABDefault";
    private final MutableSharedFlow changeEvents;
    private final SepStaticTileConfigRepository staticTileConfigRepo;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigCtrlABDefault$setTiles$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepTileConfigCtrlABDefault.this.setTiles(null, this);
        }
    }

    public SepTileConfigCtrlABDefault(MutableSharedFlow mutableSharedFlow, SepStaticTileConfigRepository sepStaticTileConfigRepository) {
        this.changeEvents = mutableSharedFlow;
        this.staticTileConfigRepo = sepStaticTileConfigRepository;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object moveTile(List<String> list, int i, Loc loc, boolean z, Continuation continuation) {
        Object objEmit = this.changeEvents.emit(new MoveTile(list, i, loc, z), continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object operateTiles(List<? extends TileSpecOperate> list, Continuation continuation) {
        return Unit.INSTANCE;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object prependDefault(Continuation continuation) {
        return Unit.INSTANCE;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object reconcileRestore(String str, boolean z, Set<String> set, Set<String> set2, Continuation continuation) {
        return Unit.INSTANCE;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object removeTiles(Collection<String> collection, Continuation continuation) {
        Object objEmit = this.changeEvents.emit(new RemoveTiles(collection, false), continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object restoreTileConfig(List<TileConfig> list, List<TileConfig> list2, boolean z, Continuation continuation) {
        return Unit.INSTANCE;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object setTileConfigs(List<TileConfig> list, List<TileConfig> list2, Continuation continuation) {
        Object objEmit = this.changeEvents.emit(new ReplaceTiles(list, list2), continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:33:0x0121  */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x0121 -> B:34:0x0127). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public java.lang.Object setTiles(java.util.List<java.lang.String> r18, kotlin.coroutines.Continuation r19) {
        /*
            Method dump skipped, instruction units count: 360
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigCtrlABDefault.setTiles(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    public Object swapTile(String str, String str2, boolean z, Continuation continuation) {
        Object objEmit = this.changeEvents.emit(new SwapTile(str, str2, z), continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }
}
