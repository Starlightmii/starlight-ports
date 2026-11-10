package com.oplus.systemui.plugins.qs.customize.data.repo.changeaction;

import android.text.TextUtils;
import com.android.systemui.plugins.qs.TileSpecOperate;
import com.oplus.systemui.plugins.qs.customize.data.algorithm.LocationAlgManager;
import com.oplus.systemui.plugins.qs.customize.data.algorithm.RearrangeInputData;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfigKt;
import com.oplus.systemui.plugins.qs.customize.data.model.TileStaticConfig;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OperateTile extends BaseActionForAB {
    public static final Companion Companion = new Companion();
    private final List<TileSpecOperate> operateList;
    public final boolean recalculate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OperateTile(List<? extends TileSpecOperate> list, boolean z) {
        super(true);
        this.operateList = list;
        this.recalculate = z;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.BaseActionForAB
    public Object applyAB(List<TileConfig> list, GridInfoEntity gridInfoEntity, List<TileConfig> list2, Continuation continuation) {
        List listPlus;
        List listProcessOperator = processOperator(list);
        List listProcessOperator2 = processOperator(list2);
        if (!this.recalculate) {
            return new Pair(listProcessOperator, listProcessOperator2);
        }
        int size = listProcessOperator2.size();
        if (size > 0) {
            List list3 = listProcessOperator;
            List listSubList = listProcessOperator2.subList(0, Math.min(3, size));
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listSubList, 10));
            Iterator it = listSubList.iterator();
            while (it.hasNext()) {
                arrayList.add(TileConfigKt.replaceSize((TileConfig) it.next(), TileStaticConfig.Companion.getDEFAULT_SIZE()));
            }
            listPlus = CollectionsKt___CollectionsKt.plus(list3, arrayList);
        } else {
            listPlus = listProcessOperator;
        }
        List<TileConfig> listStartLocationAlgorithm = LocationAlgManager.INSTANCE.startLocationAlgorithm(LocationAlgManager.ACTION_VACANCY_COMPLEMENT, new RearrangeInputData(CollectionsKt___CollectionsKt.toMutableList(listPlus), gridInfoEntity));
        int size2 = listStartLocationAlgorithm.size() - listProcessOperator.size();
        if (size2 > 0) {
            listProcessOperator2 = CollectionsKt___CollectionsKt.drop(listProcessOperator2, size2);
        }
        return new Pair(listStartLocationAlgorithm, listProcessOperator2);
    }

    public final OperateTile copy(List<? extends TileSpecOperate> list, boolean z) {
        return new OperateTile(list, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OperateTile)) {
            return false;
        }
        OperateTile operateTile = (OperateTile) obj;
        return Intrinsics.areEqual(this.operateList, operateTile.operateList) && this.recalculate == operateTile.recalculate;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.recalculate) + (this.operateList.hashCode() * 31);
    }

    public final List processOperator(List list) {
        List mutableList = CollectionsKt___CollectionsKt.toMutableList(list);
        Iterator<T> it = this.operateList.iterator();
        while (it.hasNext()) {
            TileSpecOperate.TileMapper tileMapper = (TileSpecOperate) it.next();
            if (tileMapper instanceof TileSpecOperate.TileFilter) {
                OperateTile$processOperator$1$1 operateTile$processOperator$1$1 = new OperateTile$processOperator$1$1();
                operateTile$processOperator$1$1.$operate = (TileSpecOperate.TileFilter) tileMapper;
                VarHandle.storeStoreFence();
                mutableList.removeIf(operateTile$processOperator$1$1);
            } else if (tileMapper instanceof TileSpecOperate.TileMapper) {
                OperateTile$processOperator$1$2 operateTile$processOperator$1$2 = new OperateTile$processOperator$1$2();
                operateTile$processOperator$1$2.$operate = tileMapper;
                VarHandle.storeStoreFence();
                mutableList.replaceAll(operateTile$processOperator$1$2);
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : mutableList) {
            if (!TextUtils.isEmpty(((TileConfig) obj).getTileSpec())) {
                arrayList.add(obj);
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj2 = arrayList.get(i);
            i++;
            if (hashSet.add(((TileConfig) obj2).getTileSpec())) {
                arrayList2.add(obj2);
            }
        }
        return CollectionsKt___CollectionsKt.toMutableList(arrayList2);
    }

    public String toString() {
        return "OperateTile(operateList=" + this.operateList + ", recalculate=" + this.recalculate + ")";
    }
}
