package com.oplus.systemui.plugins.qs.customize.data.repo;

import android.text.TextUtils;
import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.customize.SeparateQsTileListLogBuffer;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepRestoreTileConfig {
    private static final String DELIMITER = ",";
    public static final String HIDE_DEVICE_SPACE = "false";
    public static final String SHOW_DEVICE_SPACE = "true";
    public static final String SHOW_SEPARATE_DEVICE_ROOM_KEY = "qs_separate_device_space";
    private static final String TAG = "SepRestoreTileConfig";
    private final ContextInstance contextInstance;
    private final SeparateQsTileListLogBuffer separateQsTileLogger;
    private final SepStaticTileConfigRepository staticTileConfigRepository;
    public static final Companion Companion = new Companion();
    private static final SpanSize LARGE_DEVICE_SPACE_SIZE = SpanCache.INSTANCE.spanSize(4, 1);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final SpanSize getLARGE_DEVICE_SPACE_SIZE() {
            return SepRestoreTileConfig.LARGE_DEVICE_SPACE_SIZE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepRestoreTileConfig$checkLocationValidate$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            SepRestoreTileConfig sepRestoreTileConfig = SepRestoreTileConfig.this;
            Companion companion = SepRestoreTileConfig.Companion;
            return sepRestoreTileConfig.checkLocationValidate(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepRestoreTileConfig$loadTilesFromQsSettings$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02101 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C02101(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SepRestoreTileConfig.this.loadTilesFromQsSettings(null, null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepRestoreTileConfig$restoreFromDb$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02111 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$17;
        Object L$18;
        Object L$19;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C02111(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            SepRestoreTileConfig sepRestoreTileConfig = SepRestoreTileConfig.this;
            Companion companion = SepRestoreTileConfig.Companion;
            return sepRestoreTileConfig.restoreFromDb(null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.customize.data.repo.SepRestoreTileConfig$restoreFromQsSettings$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C02121 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C02121(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            SepRestoreTileConfig sepRestoreTileConfig = SepRestoreTileConfig.this;
            Companion companion = SepRestoreTileConfig.Companion;
            return sepRestoreTileConfig.restoreFromQsSettings(null, null, null, null, null, null, this);
        }
    }

    public SepRestoreTileConfig(ContextInstance contextInstance, SepStaticTileConfigRepository sepStaticTileConfigRepository, SeparateQsTileListLogBuffer separateQsTileListLogBuffer) {
        this.contextInstance = contextInstance;
        this.staticTileConfigRepository = sepStaticTileConfigRepository;
        this.separateQsTileLogger = separateQsTileListLogBuffer;
    }

    public final Object buildFinalBZoneTileConfigs(List list, List list2, List list3, List list4, Set set, Set set2, ContinuationImpl continuationImpl) {
        List list5 = list3;
        Set set3 = CollectionsKt___CollectionsKt.toSet(CollectionsKt___CollectionsKt.plus(list5, list));
        Set set4 = set;
        ArrayList arrayList = new ArrayList();
        for (Object obj : set4) {
            if (!set2.contains((String) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj2 = arrayList.get(i);
            i++;
            String str = (String) obj2;
            if (list4.contains(str) && !set3.contains(str)) {
                arrayList2.add(obj2);
            }
        }
        Set set5 = CollectionsKt___CollectionsKt.toSet(arrayList2);
        List mutableList = CollectionsKt___CollectionsKt.toMutableList(CollectionsKt___CollectionsKt.distinct(CollectionsKt___CollectionsKt.plus(list, list2)));
        ArrayList arrayList3 = new ArrayList();
        for (Object obj3 : set4) {
            if (!set2.contains((String) obj3)) {
                arrayList3.add(obj3);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        int size2 = arrayList3.size();
        int i2 = 0;
        while (i2 < size2) {
            Object obj4 = arrayList3.get(i2);
            i2++;
            String str2 = (String) obj4;
            if (list2.contains(str2) && !set3.contains(str2) && !set5.contains(str2)) {
                arrayList4.add(obj4);
            }
        }
        ArrayList arrayList5 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList4, 10));
        int size3 = arrayList4.size();
        int i3 = 0;
        while (i3 < size3) {
            Object obj5 = arrayList4.get(i3);
            i3++;
            String str3 = (String) obj5;
            arrayList5.add(TuplesKt.to(str3, Integer.valueOf(list2.indexOf(str3))));
        }
        for (Pair pair : CollectionsKt___CollectionsKt.sortedWith(arrayList5, new SepRestoreTileConfig$reconcileBZoneSpecs$.inlined.sortedBy.1())) {
            String str4 = (String) pair.component1();
            int iIntValue = ((Number) pair.component2()).intValue();
            mutableList.remove(str4);
            if (iIntValue > mutableList.size()) {
                mutableList.add(str4);
            } else {
                mutableList.add(iIntValue, str4);
            }
        }
        this.separateQsTileLogger.logTile(TAG, "reconcileBZoneSpecs: restored=" + list + ", current=" + list2 + ", restoredTiles=" + set3 + ", aZoneAutoAdd(excluded)=" + set5 + ", result=" + mutableList);
        List mutableList2 = CollectionsKt___CollectionsKt.toMutableList(mutableList);
        List listDistinct = CollectionsKt___CollectionsKt.distinct(CollectionsKt___CollectionsKt.plus(list5, list4));
        ArrayList arrayList6 = new ArrayList();
        for (Object obj6 : listDistinct) {
            if (!set5.contains((String) obj6)) {
                arrayList6.add(obj6);
            }
        }
        ArrayList arrayList7 = new ArrayList();
        int size4 = arrayList6.size();
        int i4 = 0;
        while (i4 < size4) {
            Object obj7 = arrayList6.get(i4);
            i4++;
            if (!StringsKt__StringsJVMKt.startsWith((String) obj7, "innerFake_", false)) {
                arrayList7.add(obj7);
            }
        }
        int size5 = arrayList7.size();
        int i5 = 0;
        while (i5 < size5) {
            Object obj8 = arrayList7.get(i5);
            i5++;
            String str5 = (String) obj8;
            if (!mutableList2.contains(str5)) {
                mutableList2.add(str5);
            }
        }
        for (String str6 : CollectionsKt___CollectionsKt.reversed(set5)) {
            mutableList2.remove(str6);
            mutableList2.add(0, str6);
        }
        this.separateQsTileLogger.logTile(TAG, "applyAZoneContributionsToBZone: afterBRestore=" + mutableList + ", aZoneAutoAdd=" + set5 + ", mergedA=" + arrayList7 + ", result=" + mutableList2);
        return toBZoneTileConfigs(mutableList2, continuationImpl);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0085  */
    /* JADX WARN: Code duplicated, block: B:24:0x00bc A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:28:0x00de  */
    /* JADX WARN: Code duplicated, block: B:30:0x0118  */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00bd -> B:26:0x00c4). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object checkLocationValidate(com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity r20, com.oplus.systemui.plugins.qs.customize.data.model.BackupData r21, kotlin.coroutines.jvm.internal.ContinuationImpl r22) {
        /*
            Method dump skipped, instruction units count: 332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.repo.SepRestoreTileConfig.checkLocationValidate(com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity, com.oplus.systemui.plugins.qs.customize.data.model.BackupData, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0134  */
    /* JADX WARN: Code duplicated, block: B:35:0x013e  */
    /* JADX WARN: Code duplicated, block: B:37:0x0186 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:38:0x0187  */
    /* JADX WARN: Code duplicated, block: B:41:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:43:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:45:0x01b6  */
    /* JADX WARN: Code duplicated, block: B:47:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:49:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:51:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:52:0x01da  */
    /* JADX WARN: Code duplicated, block: B:56:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:59:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:60:0x0208  */
    /* JADX WARN: Code duplicated, block: B:7:0x001b  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0187 -> B:39:0x0196). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object loadTilesFromQsSettings(java.util.List<java.lang.String> r25, com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity r26, boolean r27, kotlin.coroutines.Continuation r28) {
        /*
            Method dump skipped, instruction units count: 771
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.repo.SepRestoreTileConfig.loadTilesFromQsSettings(java.util.List, com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object reconcileRestore(String str, boolean z, Set<String> set, Set<String> set2, List<String> list, List<String> list2, GridInfoEntity gridInfoEntity, Continuation continuation) {
        if (!z) {
            return restoreFromDb(str, gridInfoEntity, set, set2, list, list2, continuation);
        }
        Object objRestoreFromQsSettings = restoreFromQsSettings(str, gridInfoEntity, set, set2, list, list2, continuation);
        return objRestoreFromQsSettings == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRestoreFromQsSettings : (Pair) objRestoreFromQsSettings;
    }

    /* JADX WARN: Code duplicated, block: B:153:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:50:0x0338  */
    /* JADX WARN: Code duplicated, block: B:53:0x03c8  */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x03c8 -> B:54:0x03df). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:99:0x06d8 -> B:100:0x06f5). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object restoreFromDb(java.lang.String r24, com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity r25, java.util.Set r26, java.util.Set r27, java.util.List r28, java.util.List r29, kotlin.coroutines.Continuation r30) {
        /*
            Method dump skipped, instruction units count: 2150
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.repo.SepRestoreTileConfig.restoreFromDb(java.lang.String, com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity, java.util.Set, java.util.Set, java.util.List, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:40:0x01d0 A[LOOP:0: B:38:0x01ca->B:40:0x01d0, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:44:0x01f5 A[LOOP:1: B:42:0x01ef->B:44:0x01f5, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:48:0x0246  */
    /* JADX WARN: Code duplicated, block: B:8:0x001e  */
    public final Object restoreFromQsSettings(String str, GridInfoEntity gridInfoEntity, Set set, Set set2, List list, List list2, Continuation continuation) {
        C02121 c02121;
        List<String> list3;
        Set set3;
        Object objLoadTilesFromQsSettings;
        GridInfoEntity gridInfoEntity2;
        String str2;
        List list4;
        List list5;
        ArrayList arrayList;
        Iterator it;
        ArrayList arrayList2;
        Iterator it2;
        List list6;
        Set set4 = set;
        List list7 = list;
        List list8 = list2;
        if (continuation instanceof C02121) {
            c02121 = (C02121) continuation;
            int i = c02121.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c02121.label = i - Integer.MIN_VALUE;
            } else {
                c02121 = new C02121(continuation);
            }
        } else {
            c02121 = new C02121(continuation);
        }
        C02121 c02122 = c02121;
        Object objBuildFinalBZoneTileConfigs = c02122.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c02122.label;
        if (i2 != 0) {
            if (i2 == 1) {
                list4 = (List) c02122.L$7;
                ResultKt.throwOnFailure(objBuildFinalBZoneTileConfigs);
                return new Pair(list4, objBuildFinalBZoneTileConfigs);
            }
            if (i2 == 2) {
                List<String> list9 = (List) c02122.L$6;
                List list10 = (List) c02122.L$5;
                list7 = (List) c02122.L$4;
                Set set5 = (Set) c02122.L$3;
                Set set6 = (Set) c02122.L$2;
                gridInfoEntity2 = (GridInfoEntity) c02122.L$1;
                str2 = (String) c02122.L$0;
                ResultKt.throwOnFailure(objBuildFinalBZoneTileConfigs);
                objLoadTilesFromQsSettings = objBuildFinalBZoneTileConfigs;
                set3 = set5;
                set4 = set6;
                list3 = list9;
                list8 = list10;
                Pair pair = (Pair) objLoadTilesFromQsSettings;
                list5 = (List) pair.component1();
                List list11 = (List) pair.component2();
                List list12 = list5;
                arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list12, 10));
                it = list12.iterator();
                while (it.hasNext()) {
                    arrayList.add(((TileConfig) it.next()).getTileSpec());
                }
                List list13 = list11;
                Set set7 = set3;
                arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list13, 10));
                it2 = list13.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(((TileConfig) it2.next()).getTileSpec());
                }
                c02122.L$0 = SpillingKt.nullOutSpilledVariable(str2);
                c02122.L$1 = SpillingKt.nullOutSpilledVariable(gridInfoEntity2);
                c02122.L$2 = SpillingKt.nullOutSpilledVariable(set4);
                c02122.L$3 = SpillingKt.nullOutSpilledVariable(set7);
                c02122.L$4 = SpillingKt.nullOutSpilledVariable(list7);
                c02122.L$5 = SpillingKt.nullOutSpilledVariable(list8);
                c02122.L$6 = SpillingKt.nullOutSpilledVariable(list3);
                c02122.L$7 = list5;
                c02122.L$8 = SpillingKt.nullOutSpilledVariable(list11);
                c02122.L$9 = SpillingKt.nullOutSpilledVariable(arrayList);
                c02122.label = 3;
                objBuildFinalBZoneTileConfigs = buildFinalBZoneTileConfigs(arrayList2, list8, arrayList, list7, set4, set7, c02122);
                if (objBuildFinalBZoneTileConfigs != coroutine_suspended) {
                    list6 = list5;
                }
                return coroutine_suspended;
            }
            if (i2 != 3) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            list6 = (List) c02122.L$7;
            ResultKt.throwOnFailure(objBuildFinalBZoneTileConfigs);
            return new Pair(list6, (List) objBuildFinalBZoneTileConfigs);
        }
        ResultKt.throwOnFailure(objBuildFinalBZoneTileConfigs);
        List listSplit$default = StringsKt__StringsKt.split$default(str, new String[]{","}, 0, 6);
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : listSplit$default) {
            if (!TextUtils.isEmpty((String) obj)) {
                arrayList3.add(obj);
            }
        }
        List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList(arrayList3);
        this.separateQsTileLogger.logTile(TAG, "restoreFromQsSettings: " + mutableList.size() + ", autoAddedTiles=" + set4 + ", currentATiles=" + list7 + ", currentBTiles=" + list8);
        if (mutableList.isEmpty()) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "restoreFromQsSettings: restoreTiles is empty!");
            List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            List listEmptyList2 = CollectionsKt__CollectionsKt.emptyList();
            List listEmptyList3 = CollectionsKt__CollectionsKt.emptyList();
            c02122.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c02122.L$1 = SpillingKt.nullOutSpilledVariable(gridInfoEntity);
            c02122.L$2 = SpillingKt.nullOutSpilledVariable(set4);
            c02122.L$3 = SpillingKt.nullOutSpilledVariable(set2);
            c02122.L$4 = SpillingKt.nullOutSpilledVariable(list7);
            c02122.L$5 = SpillingKt.nullOutSpilledVariable(list8);
            c02122.L$6 = SpillingKt.nullOutSpilledVariable(mutableList);
            c02122.L$7 = listEmptyList;
            c02122.label = 1;
            objBuildFinalBZoneTileConfigs = buildFinalBZoneTileConfigs(listEmptyList2, list8, listEmptyList3, list7, set4, set2, c02122);
            if (objBuildFinalBZoneTileConfigs != coroutine_suspended) {
                list4 = listEmptyList;
                return new Pair(list4, objBuildFinalBZoneTileConfigs);
            }
        } else {
            list3 = mutableList;
            c02122.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c02122.L$1 = SpillingKt.nullOutSpilledVariable(gridInfoEntity);
            c02122.L$2 = set4;
            set3 = set2;
            c02122.L$3 = set3;
            c02122.L$4 = list7;
            c02122.L$5 = list8;
            c02122.L$6 = SpillingKt.nullOutSpilledVariable(list3);
            c02122.label = 2;
            objLoadTilesFromQsSettings = loadTilesFromQsSettings(list3, gridInfoEntity, false, c02122);
            if (objLoadTilesFromQsSettings != coroutine_suspended) {
                gridInfoEntity2 = gridInfoEntity;
                str2 = str;
                Pair pair2 = (Pair) objLoadTilesFromQsSettings;
                list5 = (List) pair2.component1();
                List list14 = (List) pair2.component2();
                List list15 = list5;
                arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list15, 10));
                it = list15.iterator();
                while (it.hasNext()) {
                    arrayList.add(((TileConfig) it.next()).getTileSpec());
                }
                List list16 = list14;
                Set set8 = set3;
                arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list16, 10));
                it2 = list16.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(((TileConfig) it2.next()).getTileSpec());
                }
                c02122.L$0 = SpillingKt.nullOutSpilledVariable(str2);
                c02122.L$1 = SpillingKt.nullOutSpilledVariable(gridInfoEntity2);
                c02122.L$2 = SpillingKt.nullOutSpilledVariable(set4);
                c02122.L$3 = SpillingKt.nullOutSpilledVariable(set8);
                c02122.L$4 = SpillingKt.nullOutSpilledVariable(list7);
                c02122.L$5 = SpillingKt.nullOutSpilledVariable(list8);
                c02122.L$6 = SpillingKt.nullOutSpilledVariable(list3);
                c02122.L$7 = list5;
                c02122.L$8 = SpillingKt.nullOutSpilledVariable(list14);
                c02122.L$9 = SpillingKt.nullOutSpilledVariable(arrayList);
                c02122.label = 3;
                objBuildFinalBZoneTileConfigs = buildFinalBZoneTileConfigs(arrayList2, list8, arrayList, list7, set4, set8, c02122);
                if (objBuildFinalBZoneTileConfigs != coroutine_suspended) {
                    list6 = list5;
                    return new Pair(list6, (List) objBuildFinalBZoneTileConfigs);
                }
            }
        }
        return coroutine_suspended;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x007a  */
    /* JADX WARN: Code duplicated, block: B:19:0x00b1 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00b2 -> B:21:0x00b6). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object toBZoneTileConfigs(java.util.List r17, kotlin.coroutines.jvm.internal.ContinuationImpl r18) {
        /*
            Method dump skipped, instruction units count: 211
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.repo.SepRestoreTileConfig.toBZoneTileConfigs(java.util.List, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
