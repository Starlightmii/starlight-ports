package com.oplus.systemui.plugins.qs.customize.viewmodel;

import com.oplus.systemui.plugins.qs.customize.viewmodel.model.DialogPreviewConfig;
import com.oplusos.systemui.common.viewholder.GridData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class TileEditViewModel$allSelectable$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        TileEditViewModel$allSelectable$1 tileEditViewModel$allSelectable$1 = new TileEditViewModel$allSelectable$1(3, (Continuation) obj3);
        tileEditViewModel$allSelectable$1.L$0 = obj;
        tileEditViewModel$allSelectable$1.L$1 = (TileEditState) obj2;
        return tileEditViewModel$allSelectable$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2 = this.L$0;
        TileEditState tileEditState = (TileEditState) this.L$1;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (obj2 instanceof AllTilesState.Loading) {
            return obj2;
        }
        ArrayList arrayList = new ArrayList();
        for (DialogPreviewConfig dialogPreviewConfig : (List) obj2) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, CollectionsKt___CollectionsKt.plus(CollectionsKt__CollectionsJVMKt.listOf(dialogPreviewConfig.getTileType()), dialogPreviewConfig.getTiles()));
        }
        if (!(tileEditState instanceof TileEditState.Normal)) {
            return arrayList;
        }
        TileEditState.Normal normal = (TileEditState.Normal) tileEditState;
        List listPlus = CollectionsKt___CollectionsKt.plus(normal.getAllPersonalTiles(), normal.getOtherAddedTiles());
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object objCopy$default = arrayList.get(i);
            i++;
            if (objCopy$default instanceof GridData) {
                List list = listPlus;
                if (!(list instanceof Collection) || !list.isEmpty()) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        GridData gridData = (GridData) objCopy$default;
                        if (((GridData) it.next()).getUniqueId() == gridData.getUniqueId()) {
                            objCopy$default = GridData.copy$default(gridData, null, null, 15);
                            break;
                        }
                    }
                }
            }
            arrayList2.add(objCopy$default);
        }
        return arrayList2;
    }
}
