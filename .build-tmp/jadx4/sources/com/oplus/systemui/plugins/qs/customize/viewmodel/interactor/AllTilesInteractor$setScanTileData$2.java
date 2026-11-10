package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import com.android.systemui.plugins.qs.SepQSTileQueryHelper;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class AllTilesInteractor$setScanTileData$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ List<SepQSTileQueryHelper.TileInfo> $tiles;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    final /* synthetic */ AllTilesInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AllTilesInteractor$setScanTileData$2(AllTilesInteractor allTilesInteractor, List list, Continuation continuation) {
        super(2, continuation);
        this.$tiles = list;
        this.this$0 = allTilesInteractor;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new AllTilesInteractor$setScanTileData$2(this.this$0, this.$tiles, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:55:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:62:0x0240  */
    /* JADX WARN: Code duplicated, block: B:64:0x0248  */
    /* JADX WARN: Code duplicated, block: B:67:0x0289  */
    /* JADX WARN: Code duplicated, block: B:69:0x02c6  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:67:0x0289 -> B:68:0x029a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:69:0x02c6 -> B:70:0x02de). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) {
        /*
            Method dump skipped, instruction units count: 768
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.AllTilesInteractor$setScanTileData$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
