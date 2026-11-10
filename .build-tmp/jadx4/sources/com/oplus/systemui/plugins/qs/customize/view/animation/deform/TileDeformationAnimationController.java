package com.oplus.systemui.plugins.qs.customize.view.animation.deform;

import android.graphics.Rect;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.collection.FloatList$;
import androidx.compose.ui.spatial.RectList$;
import androidx.fragment.app.Fragment$5$;
import com.google.gson.Gson$;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.TileBackgroundShareTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.TileIconBackgroundShareTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.TileIconBoundShareTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.TileLabelBoundShareTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.TileLabelFadeShareTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.TileRadiusTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.fixed.TileFixedBackgroundColorShareTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.fixed.TileFixedBoundShareTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.fixed.TileFixedIconBackgroundColorShareTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.fixed.TileFixedIconScaleShareTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.fixed.TileFixedLabelTextFadeShareTransitionProperty;
import com.oplus.systemui.plugins.qs.customize.view.animation.deform.tile.fixed.TileFixedRadiusShareTransitionProperty;
import com.oplus.systemui.qs.base.shareanimation.BoundShareTransitionProperty;
import com.oplus.systemui.qs.base.shareanimation.ShareTransitionProperty;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.MapsKt___MapsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileDeformationAnimationController extends BaseDeformationAnimationController implements BaseDeformationAnimationController.OnAnimateListener {
    public static final Companion Companion = new Companion();
    private static final SpanSize SIZE1X1_SIZE;
    private static final SpanSize SIZE2X2_SIZE;
    private static final String TAG = "TileDeformationAnimationController";
    private BoundShareTransitionProperty bgBoundTransitionProperty;
    private final Lazy sortedSizeList$delegate = LazyKt__LazyJVMKt.lazy(new TileDeformationAnimationController$$ExternalSyntheticLambda2());
    private final Rect rectHost = new Rect();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final SpanSize getSIZE1X1_SIZE() {
            return TileDeformationAnimationController.SIZE1X1_SIZE;
        }

        public final SpanSize getSIZE2X2_SIZE() {
            return TileDeformationAnimationController.SIZE2X2_SIZE;
        }
    }

    static {
        SpanCache spanCache = SpanCache.INSTANCE;
        SIZE2X2_SIZE = spanCache.spanSize(2, 2);
        SIZE1X1_SIZE = spanCache.spanSize(1, 1);
    }

    private final List<Pair<SpanSize, Size>> getSortedSizeList() {
        return (List) this.sortedSizeList$delegate.getValue();
    }

    private final int getTileBgId() {
        return 2131364808;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x01f9 A[Catch: all -> 0x01a9, TryCatch #1 {all -> 0x01a9, blocks: (B:75:0x017d, B:76:0x018a, B:78:0x0190, B:85:0x01ad, B:86:0x01b9, B:88:0x01bf, B:92:0x01de, B:94:0x01e2, B:108:0x0228, B:97:0x01eb, B:98:0x01f3, B:100:0x01f9, B:103:0x0217, B:105:0x021b, B:106:0x021f, B:107:0x0223, B:81:0x01a6), top: B:151:0x017d }] */
    /* JADX WARN: Code duplicated, block: B:105:0x021b A[Catch: all -> 0x01a9, TryCatch #1 {all -> 0x01a9, blocks: (B:75:0x017d, B:76:0x018a, B:78:0x0190, B:85:0x01ad, B:86:0x01b9, B:88:0x01bf, B:92:0x01de, B:94:0x01e2, B:108:0x0228, B:97:0x01eb, B:98:0x01f3, B:100:0x01f9, B:103:0x0217, B:105:0x021b, B:106:0x021f, B:107:0x0223, B:81:0x01a6), top: B:151:0x017d }] */
    /* JADX WARN: Code duplicated, block: B:107:0x0223 A[Catch: all -> 0x01a9, TryCatch #1 {all -> 0x01a9, blocks: (B:75:0x017d, B:76:0x018a, B:78:0x0190, B:85:0x01ad, B:86:0x01b9, B:88:0x01bf, B:92:0x01de, B:94:0x01e2, B:108:0x0228, B:97:0x01eb, B:98:0x01f3, B:100:0x01f9, B:103:0x0217, B:105:0x021b, B:106:0x021f, B:107:0x0223, B:81:0x01a6), top: B:151:0x017d }] */
    /* JADX WARN: Code duplicated, block: B:114:0x023c  */
    /* JADX WARN: Code duplicated, block: B:117:0x0248  */
    /* JADX WARN: Code duplicated, block: B:119:0x024e  */
    /* JADX WARN: Code duplicated, block: B:120:0x0250  */
    /* JADX WARN: Code duplicated, block: B:124:0x025e  */
    /* JADX WARN: Code duplicated, block: B:151:0x017d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:161:0x01ac A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:162:0x01a4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:163:0x01dd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:167:0x0216 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:168:? A[LOOP:5: B:98:0x01f3->B:168:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:0x0190 A[Catch: all -> 0x01a9, TryCatch #1 {all -> 0x01a9, blocks: (B:75:0x017d, B:76:0x018a, B:78:0x0190, B:85:0x01ad, B:86:0x01b9, B:88:0x01bf, B:92:0x01de, B:94:0x01e2, B:108:0x0228, B:97:0x01eb, B:98:0x01f3, B:100:0x01f9, B:103:0x0217, B:105:0x021b, B:106:0x021f, B:107:0x0223, B:81:0x01a6), top: B:151:0x017d }] */
    /* JADX WARN: Code duplicated, block: B:81:0x01a6 A[Catch: all -> 0x01a9, LOOP:3: B:76:0x018a->B:81:0x01a6, LOOP_END, TryCatch #1 {all -> 0x01a9, blocks: (B:75:0x017d, B:76:0x018a, B:78:0x0190, B:85:0x01ad, B:86:0x01b9, B:88:0x01bf, B:92:0x01de, B:94:0x01e2, B:108:0x0228, B:97:0x01eb, B:98:0x01f3, B:100:0x01f9, B:103:0x0217, B:105:0x021b, B:106:0x021f, B:107:0x0223, B:81:0x01a6), top: B:151:0x017d }] */
    /* JADX WARN: Code duplicated, block: B:88:0x01bf A[Catch: all -> 0x01a9, TryCatch #1 {all -> 0x01a9, blocks: (B:75:0x017d, B:76:0x018a, B:78:0x0190, B:85:0x01ad, B:86:0x01b9, B:88:0x01bf, B:92:0x01de, B:94:0x01e2, B:108:0x0228, B:97:0x01eb, B:98:0x01f3, B:100:0x01f9, B:103:0x0217, B:105:0x021b, B:106:0x021f, B:107:0x0223, B:81:0x01a6), top: B:151:0x017d }] */
    /* JADX WARN: Code duplicated, block: B:97:0x01eb A[Catch: all -> 0x01a9, TryCatch #1 {all -> 0x01a9, blocks: (B:75:0x017d, B:76:0x018a, B:78:0x0190, B:85:0x01ad, B:86:0x01b9, B:88:0x01bf, B:92:0x01de, B:94:0x01e2, B:108:0x0228, B:97:0x01eb, B:98:0x01f3, B:100:0x01f9, B:103:0x0217, B:105:0x021b, B:106:0x021f, B:107:0x0223, B:81:0x01a6), top: B:151:0x017d }] */
    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController, com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public IDeformationAnimationController.ComputeResult calculateAnimationStartAndEndView(int i, int i2, int i3, int i4) {
        Object obj;
        SpanSize spanSize;
        TileDeformationAnimationController tileDeformationAnimationController;
        SpanSize spanSize2;
        Iterator<Pair<SpanSize, Size>> it;
        int i5;
        int i6;
        List<Pair<SpanSize, Size>> listSubList;
        ListIterator<Pair<SpanSize, Size>> listIterator;
        Pair<SpanSize, Size> pairPrevious;
        Pair<SpanSize, Size> pair;
        ListIterator<Pair<SpanSize, Size>> listIterator2;
        Pair<SpanSize, Size> pair2;
        Object first;
        SpanSize spanSize3;
        Pair<SpanSize, Size> pairPrevious2;
        Object obj2;
        int i7;
        Pair<SpanSize, Size> pairPrevious3;
        SpanSize spanSize4;
        getComputeResult().reset();
        int i8 = i3 - i;
        int i9 = i4 - i2;
        if (i8 == 0 && i9 == 0) {
            IDeformationAnimationController.ComputeResult computeResult = getComputeResult();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "TileDeformationAnimationController-->".concat("[calculateAnimationStartAndEndView] dx = 0 && dy = 0 , should return default result = " + getComputeResult()));
            }
            return computeResult;
        }
        DeformUtils$Direction deformUtils$DirectionCalculateDirection = DeformUtils.calculateDirection(i8, -i9);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("[calculateAnimationStartAndEndView] oldWidth = ", i, i2, ", oldHeight = ", ", newWidth = ");
            RectList$.ExternalSyntheticOutline0.m(i3, i4, ", newHeight = ", ", direction=", sbM);
            sbM.append(deformUtils$DirectionCalculateDirection);
            Log.d("SystemUi--QuickSettings", "TileDeformationAnimationController-->".concat(sbM.toString()));
        }
        Pair pairLocatedSpanSize = DeformUtils.locatedSpanSize(i3, i4, getSortedSizeList());
        int iOrdinal = deformUtils$DirectionCalculateDirection.ordinal();
        Pair<SpanSize, Size> pair3 = null;
        if (iOrdinal == 0) {
            try {
                List<Pair<SpanSize, Size>> sortedSizeList = getSortedSizeList();
                it = getSortedSizeList().iterator();
                i5 = 0;
                while (true) {
                    if (it.hasNext()) {
                        i6 = -1;
                        break;
                    }
                    if (Intrinsics.areEqual(it.next().getFirst(), pairLocatedSpanSize.getFirst())) {
                        i6 = i5;
                        break;
                    }
                    i5++;
                }
                listSubList = sortedSizeList.subList(0, i6);
                listIterator = listSubList.listIterator(listSubList.size());
                do {
                    if (listIterator.hasPrevious()) {
                        pairPrevious = null;
                        break;
                    }
                    pairPrevious = listIterator.previous();
                } while (((SpanSize) pairPrevious.getFirst()).getRowSpan() != ((SpanSize) pairLocatedSpanSize.getFirst()).getRowSpan());
                pair = pairPrevious;
                if (pair != null || (spanSize3 = (SpanSize) pair.getFirst()) == null) {
                    listIterator2 = listSubList.listIterator(listSubList.size());
                    while (listIterator2.hasPrevious()) {
                        pairPrevious2 = listIterator2.previous();
                        if (((SpanSize) pairPrevious2.getFirst()).getColSpan() < ((SpanSize) pairLocatedSpanSize.getFirst()).getColSpan()) {
                            pair3 = pairPrevious2;
                            break;
                        }
                    }
                    pair2 = pair3;
                    if (pair2 != null) {
                        first = pair2.getFirst();
                    } else {
                        first = pairLocatedSpanSize.getFirst();
                    }
                    spanSize3 = (SpanSize) first;
                }
                obj = Result.constructor-impl(spanSize3);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            if (Result.exceptionOrNull-impl(obj) != null) {
                obj = (SpanSize) pairLocatedSpanSize.getFirst();
            }
            spanSize = (SpanSize) obj;
            if (deformUtils$DirectionCalculateDirection == DeformUtils$Direction.LEFT) {
                tileDeformationAnimationController = this;
                if (!tileDeformationAnimationController.isRTL) {
                    spanSize2 = spanSize;
                }
                if (Intrinsics.areEqual(spanSize2, spanSize)) {
                    spanSize = (SpanSize) pairLocatedSpanSize.getFirst();
                }
            } else {
                tileDeformationAnimationController = this;
            }
            spanSize2 = (SpanSize) pairLocatedSpanSize.getFirst();
            if (Intrinsics.areEqual(spanSize2, spanSize)) {
                spanSize = (SpanSize) pairLocatedSpanSize.getFirst();
            }
        } else {
            if (iOrdinal != 2) {
                if (iOrdinal == 4) {
                    List<Pair<SpanSize, Size>> sortedSizeList2 = getSortedSizeList();
                    it = getSortedSizeList().iterator();
                    i5 = 0;
                    while (true) {
                        if (it.hasNext()) {
                            i6 = -1;
                            break;
                        }
                        if (Intrinsics.areEqual(it.next().getFirst(), pairLocatedSpanSize.getFirst())) {
                            i6 = i5;
                            break;
                        }
                        i5++;
                    }
                    listSubList = sortedSizeList2.subList(0, i6);
                    listIterator = listSubList.listIterator(listSubList.size());
                    do {
                        if (listIterator.hasPrevious()) {
                            pairPrevious = null;
                            break;
                        }
                        pairPrevious = listIterator.previous();
                    } while (((SpanSize) pairPrevious.getFirst()).getRowSpan() != ((SpanSize) pairLocatedSpanSize.getFirst()).getRowSpan());
                    pair = pairPrevious;
                    if (pair != null) {
                        listIterator2 = listSubList.listIterator(listSubList.size());
                        while (listIterator2.hasPrevious()) {
                            pairPrevious2 = listIterator2.previous();
                            if (((SpanSize) pairPrevious2.getFirst()).getColSpan() < ((SpanSize) pairLocatedSpanSize.getFirst()).getColSpan()) {
                                pair3 = pairPrevious2;
                                break;
                            }
                        }
                        pair2 = pair3;
                        if (pair2 != null) {
                            first = pair2.getFirst();
                        } else {
                            first = pairLocatedSpanSize.getFirst();
                        }
                        spanSize3 = (SpanSize) first;
                    } else {
                        listIterator2 = listSubList.listIterator(listSubList.size());
                        while (listIterator2.hasPrevious()) {
                            pairPrevious2 = listIterator2.previous();
                            if (((SpanSize) pairPrevious2.getFirst()).getColSpan() < ((SpanSize) pairLocatedSpanSize.getFirst()).getColSpan()) {
                                pair3 = pairPrevious2;
                                break;
                            }
                        }
                        pair2 = pair3;
                        if (pair2 != null) {
                            first = pair2.getFirst();
                        } else {
                            first = pairLocatedSpanSize.getFirst();
                        }
                        spanSize3 = (SpanSize) first;
                    }
                    obj = Result.constructor-impl(spanSize3);
                    if (Result.exceptionOrNull-impl(obj) != null) {
                        obj = (SpanSize) pairLocatedSpanSize.getFirst();
                    }
                    spanSize = (SpanSize) obj;
                    if (deformUtils$DirectionCalculateDirection == DeformUtils$Direction.LEFT) {
                        tileDeformationAnimationController = this;
                        if (!tileDeformationAnimationController.isRTL) {
                            spanSize2 = spanSize;
                        }
                        if (Intrinsics.areEqual(spanSize2, spanSize)) {
                            spanSize = (SpanSize) pairLocatedSpanSize.getFirst();
                        }
                    } else {
                        tileDeformationAnimationController = this;
                    }
                    spanSize2 = (SpanSize) pairLocatedSpanSize.getFirst();
                    if (Intrinsics.areEqual(spanSize2, spanSize)) {
                        spanSize = (SpanSize) pairLocatedSpanSize.getFirst();
                    }
                } else if (iOrdinal != 6) {
                    Gson$.ExternalSyntheticBUOutline0.m(deformUtils$DirectionCalculateDirection, "illegal direction ");
                    return null;
                }
            }
            try {
                List<Pair<SpanSize, Size>> sortedSizeList3 = getSortedSizeList();
                Iterator<Pair<SpanSize, Size>> it2 = getSortedSizeList().iterator();
                int i10 = 0;
                while (true) {
                    if (!it2.hasNext()) {
                        i7 = -1;
                        break;
                    }
                    if (Intrinsics.areEqual(it2.next().getFirst(), pairLocatedSpanSize.getFirst())) {
                        i7 = i10;
                        break;
                    }
                    i10++;
                }
                List<Pair<SpanSize, Size>> listSubList2 = sortedSizeList3.subList(0, i7);
                ListIterator<Pair<SpanSize, Size>> listIterator3 = listSubList2.listIterator(listSubList2.size());
                do {
                    if (!listIterator3.hasPrevious()) {
                        pairPrevious3 = null;
                        break;
                    }
                    pairPrevious3 = listIterator3.previous();
                } while (((SpanSize) pairPrevious3.getFirst()).getColSpan() != ((SpanSize) pairLocatedSpanSize.getFirst()).getColSpan());
                Pair<SpanSize, Size> pair4 = pairPrevious3;
                if (pair4 == null || (spanSize4 = (SpanSize) pair4.getFirst()) == null) {
                    ListIterator<Pair<SpanSize, Size>> listIterator4 = listSubList2.listIterator(listSubList2.size());
                    while (listIterator4.hasPrevious()) {
                        Pair<SpanSize, Size> pairPrevious4 = listIterator4.previous();
                        if (((SpanSize) pairPrevious4.getFirst()).getRowSpan() < ((SpanSize) pairLocatedSpanSize.getFirst()).getRowSpan()) {
                            pair3 = pairPrevious4;
                            break;
                        }
                    }
                    Pair<SpanSize, Size> pair5 = pair3;
                    spanSize4 = (SpanSize) (pair5 != null ? pair5.getFirst() : pairLocatedSpanSize.getFirst());
                }
                obj2 = Result.constructor-impl(spanSize4);
            } catch (Throwable th2) {
                obj2 = Result.constructor-impl(ResultKt.createFailure(th2));
            }
            if (Result.exceptionOrNull-impl(obj2) != null) {
                obj2 = (SpanSize) pairLocatedSpanSize.getFirst();
            }
            spanSize = (SpanSize) obj2;
            SpanSize spanSize5 = deformUtils$DirectionCalculateDirection == DeformUtils$Direction.UP ? spanSize : (SpanSize) pairLocatedSpanSize.getFirst();
            if (Intrinsics.areEqual(spanSize5, spanSize)) {
                spanSize = (SpanSize) pairLocatedSpanSize.getFirst();
            }
            spanSize2 = spanSize5;
            tileDeformationAnimationController = this;
        }
        Size size = tileDeformationAnimationController.getAllValidSize().get(spanSize);
        Size size2 = tileDeformationAnimationController.getAllValidSize().get(spanSize2);
        IDeformationAnimationController.ComputeResult computeResult2 = tileDeformationAnimationController.getComputeResult();
        computeResult2.setStartHost(tileDeformationAnimationController.getAllSupportHost().get(spanSize));
        computeResult2.setEndHost(tileDeformationAnimationController.getAllSupportHost().get(spanSize2));
        float fComputeProgress = -1.0f;
        computeResult2.setXProgressFromCurr(size2 != null ? DeformUtils.computeProgress(i, size2.getWidth(), i3) : -1.0f);
        computeResult2.setYProgressFromCurr(size2 != null ? DeformUtils.computeProgress(i2, size2.getHeight(), i4) : -1.0f);
        computeResult2.setXProgress((size == null || size2 == null) ? -1.0f : DeformUtils.computeProgress(size.getWidth(), size2.getWidth(), i3));
        if (size != null && size2 != null) {
            fComputeProgress = DeformUtils.computeProgress(size.getHeight(), size2.getHeight(), i4);
        }
        computeResult2.setYProgress(fComputeProgress);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileDeformationAnimationController-->".concat("[calculateAnimationStartAndEndView] 333 " + computeResult2));
        }
        return tileDeformationAnimationController.getComputeResult();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController
    public Map<ShareTransitionProperty, ViewGroup> createShareTransitionProperties(ViewGroup viewGroup, Map<SpanSize, ? extends ViewGroup> map) {
        SpanCache spanCache = SpanCache.INSTANCE;
        ViewGroup viewGroup2 = map.get(spanCache.spanSize(2, 1));
        int tileBgId = getTileBgId();
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("createShareTransitionProperties: tileBgId=", tileBgId, 2131363622, " label_container=", " qs_tile_icon_view=");
        RectList$.ExternalSyntheticOutline0.m(2131364810, 2131363707, "left_view=", " icon=", sbM);
        RectList$.ExternalSyntheticOutline0.m(2131364774, 2131363623, " label_container_two_x_two=", "oplus_separate_qs_resizeable_tile_container=", sbM);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, Fragment$5$.ExternalSyntheticOutline0.m(sbM, 2131364411, 2131364406, "oplus_qs_tile_icon_bg="), null, 8);
        if (this.isForDrag) {
            Map mapCreateMapBuilder = MapsKt__MapsJVMKt.createMapBuilder();
            if (viewGroup2 == null) {
                this.bgBoundTransitionProperty = new BoundShareTransitionProperty(viewGroup, tileBgId, false);
            } else {
                this.bgBoundTransitionProperty = new BoundShareTransitionProperty(viewGroup, tileBgId, false);
                mapCreateMapBuilder.put(new TileLabelFadeShareTransitionProperty(viewGroup, 2131363622), viewGroup2);
                mapCreateMapBuilder.put(new TileBackgroundShareTransitionProperty(viewGroup, tileBgId), viewGroup2);
                mapCreateMapBuilder.put(new TileIconBackgroundShareTransitionProperty(viewGroup, 2131364810), viewGroup2);
                mapCreateMapBuilder.put(new TileIconBoundShareTransitionProperty(viewGroup, 2131363707, false), viewGroup2);
                mapCreateMapBuilder.put(new TileIconBoundShareTransitionProperty(viewGroup, 2131364810, true), viewGroup2);
                mapCreateMapBuilder.put(new TileIconBoundShareTransitionProperty(viewGroup, 2131364774, true), viewGroup2);
                mapCreateMapBuilder.put(new TileRadiusTransitionProperty(viewGroup, 2131364774), viewGroup2);
                ViewGroup viewGroup3 = map.get(spanCache.spanSize(2, 2));
                if (viewGroup3 != null) {
                    mapCreateMapBuilder.put(new TileLabelFadeShareTransitionProperty(viewGroup, 2131363623), viewGroup3);
                    mapCreateMapBuilder.put(new TileLabelBoundShareTransitionProperty(viewGroup, 2131363623), viewGroup3);
                }
            }
            return MapsKt__MapsJVMKt.build(mapCreateMapBuilder);
        }
        if (viewGroup2 == null) {
            return MapsKt__MapsKt.emptyMap();
        }
        Map mapCreateMapBuilder2 = MapsKt__MapsJVMKt.createMapBuilder();
        TileFixedBoundShareTransitionProperty tileFixedBoundShareTransitionProperty = new TileFixedBoundShareTransitionProperty(viewGroup, 2131364411, false);
        tileFixedBoundShareTransitionProperty.setOnAnimateListener(this);
        mapCreateMapBuilder2.put(tileFixedBoundShareTransitionProperty, viewGroup2);
        this.bgBoundTransitionProperty = new BoundShareTransitionProperty(viewGroup, tileBgId, false);
        mapCreateMapBuilder2.put(new TileFixedBackgroundColorShareTransitionProperty(viewGroup, tileBgId), viewGroup2);
        mapCreateMapBuilder2.put(new TileFixedIconBackgroundColorShareTransitionProperty(viewGroup, 2131364406), viewGroup2);
        mapCreateMapBuilder2.put(new TileFixedIconScaleShareTransitionProperty(viewGroup, 2131363707), viewGroup2);
        mapCreateMapBuilder2.put(new TileFixedIconScaleShareTransitionProperty(viewGroup, 2131364810), viewGroup2);
        mapCreateMapBuilder2.put(new TileFixedIconScaleShareTransitionProperty(viewGroup, 2131364406), viewGroup2);
        mapCreateMapBuilder2.put(new TileFixedIconScaleShareTransitionProperty(viewGroup, 2131364774), viewGroup2);
        mapCreateMapBuilder2.put(new TileFixedLabelTextFadeShareTransitionProperty(viewGroup, 2131363622), viewGroup2);
        mapCreateMapBuilder2.put(new TileFixedRadiusShareTransitionProperty(viewGroup, tileBgId), viewGroup2);
        return MapsKt__MapsJVMKt.build(mapCreateMapBuilder2);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController.OnAnimateListener
    public final void onAnimationEnd(boolean z) {
        BaseDeformationAnimationController.OnAnimateListener onAnimateListener = getOnAnimateListener();
        if (onAnimateListener != null) {
            onAnimateListener.onAnimationEnd(z);
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController, com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public void onFlingStarted(ViewGroup viewGroup, SpanSize spanSize) {
        Object next;
        super.onFlingStarted(viewGroup, spanSize);
        Iterator<T> it = getShareTransitionProperties().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            ShareTransitionProperty shareTransitionProperty = (ShareTransitionProperty) next;
            if ((shareTransitionProperty instanceof TileFixedBoundShareTransitionProperty) && ((TileFixedBoundShareTransitionProperty) shareTransitionProperty).getSharedElementId() == 2131364411) {
                break;
            }
        }
        TileFixedBoundShareTransitionProperty tileFixedBoundShareTransitionProperty = next instanceof TileFixedBoundShareTransitionProperty ? (TileFixedBoundShareTransitionProperty) next : null;
        if (tileFixedBoundShareTransitionProperty != null) {
            tileFixedBoundShareTransitionProperty.setOnAnimateListener(this);
        }
        this.bgBoundTransitionProperty = new BoundShareTransitionProperty(viewGroup, getTileBgId(), false);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController.OnAnimateListener
    public final void onViewHostBoundChanged() {
        BaseDeformationAnimationController.OnAnimateListener onAnimateListener = getOnAnimateListener();
        if (onAnimateListener != null) {
            onAnimateListener.onViewHostBoundChanged();
        }
        ViewGroup currTargetViewHost = getCurrTargetViewHost();
        if (currTargetViewHost != null) {
            this.rectHost.set(0, 0, currTargetViewHost.getWidth(), currTargetViewHost.getHeight());
            BoundShareTransitionProperty boundShareTransitionProperty = this.bgBoundTransitionProperty;
            if (boundShareTransitionProperty != null) {
                boundShareTransitionProperty.setValue(this.rectHost);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController, com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public void prepare(ViewGroup viewGroup, Map<SpanSize, ? extends ViewGroup> map, Map<SpanSize, Size> map2, boolean z, boolean z2) {
        super.prepare(viewGroup, map, map2, z, z2);
        getSortedSizeList().clear();
        getSortedSizeList().addAll(CollectionsKt___CollectionsKt.sortedWith(MapsKt___MapsKt.toList(map2), ComparisonsKt__ComparisonsKt.compareBy(new Function1[]{new TileDeformationAnimationController$.ExternalSyntheticLambda0(0), new TileDeformationAnimationController$.ExternalSyntheticLambda0(1)})));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "TileDeformationAnimationController-->".concat("[prepare] sortedSizeList = " + getSortedSizeList() + " "));
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public final void squeezeOrStretch(int i, int i2, int i3) {
        ViewGroup currTargetViewHost = getCurrTargetViewHost();
        if (currTargetViewHost != null) {
            ShareTransitionProperty.Companion companion = ShareTransitionProperty.Companion;
            View viewFindViewByIdUseCache = companion.findViewByIdUseCache(currTargetViewHost, 2131363622, true);
            View viewFindViewByIdUseCache2 = companion.findViewByIdUseCache(currTargetViewHost, 2131363623, true);
            View viewFindViewByIdUseCache3 = companion.findViewByIdUseCache(currTargetViewHost, 2131363707, true);
            int height = currTargetViewHost.getHeight();
            Map<SpanSize, Size> allValidSize = getAllValidSize();
            SpanSize spanSize = SIZE1X1_SIZE;
            Size size = allValidSize.get(spanSize);
            int height2 = height - (size != null ? size.getHeight() : 0);
            Map<SpanSize, Size> allValidSize2 = getAllValidSize();
            SpanSize spanSize2 = SIZE2X2_SIZE;
            Size size2 = allValidSize2.get(spanSize2);
            int height3 = size2 != null ? size2.getHeight() : 0;
            Size size3 = getAllValidSize().get(spanSize);
            boolean z = getAllValidSize().containsKey(spanSize2) && ((float) (height2 / (height3 - (size3 != null ? size3.getHeight() : 0)))) >= 0.5f;
            if (viewFindViewByIdUseCache != null) {
                viewFindViewByIdUseCache.setTranslationX(i);
            }
            if (viewFindViewByIdUseCache != null) {
                viewFindViewByIdUseCache.setTranslationY(i2);
            }
            if (viewFindViewByIdUseCache2 != null) {
                viewFindViewByIdUseCache2.setTranslationX(i);
            }
            if (viewFindViewByIdUseCache2 != null) {
                viewFindViewByIdUseCache2.setTranslationY(i2);
            }
            if (viewFindViewByIdUseCache3 != null) {
                viewFindViewByIdUseCache3.setTranslationX(i);
            }
            if (z) {
                if (viewFindViewByIdUseCache3 != null) {
                    viewFindViewByIdUseCache3.setTranslationY(i2 / 2);
                }
            } else if (viewFindViewByIdUseCache3 != null) {
                viewFindViewByIdUseCache3.setTranslationY(i2);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController, com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public void stop(Function1 function1) {
        ViewGroup currTargetViewHost = getCurrTargetViewHost();
        ViewParent parent = currTargetViewHost != null ? currTargetViewHost.getParent() : null;
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.suppressLayout(false);
        }
        super.stop(function1);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController, com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public void transferFrom(IDeformationAnimationController iDeformationAnimationController, ViewGroup viewGroup, Map<SpanSize, ? extends ViewGroup> map, Map<SpanSize, Size> map2) {
        Object next;
        Size size;
        if (iDeformationAnimationController instanceof TileDeformationAnimationController) {
            TileDeformationAnimationController tileDeformationAnimationController = (TileDeformationAnimationController) iDeformationAnimationController;
            ViewGroup currTargetViewHost = tileDeformationAnimationController.getCurrTargetViewHost();
            setCurrTargetViewHost(viewGroup);
            Iterator<T> it = tileDeformationAnimationController.getShareTransitionProperties().iterator();
            while (it.hasNext()) {
                ((ShareTransitionProperty) it.next()).onTransitionEnd(false);
            }
            if (currTargetViewHost != null) {
                resetChildrenFromOverlay(currTargetViewHost);
            }
            getAllSupportHost().clear();
            getAllSupportHost().putAll(map);
            getAllValidSize().clear();
            getAllValidSize().putAll(map2);
            for (Map.Entry<SpanSize, ? extends ViewGroup> entry : map.entrySet()) {
                if (!Intrinsics.areEqual(entry.getValue(), viewGroup) && (size = map2.get(entry.getKey())) != null) {
                    entry.getValue().measure(View.MeasureSpec.makeMeasureSpec(size.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(size.getHeight(), 1073741824));
                    entry.getValue().layout(0, 0, size.getWidth(), size.getHeight());
                }
            }
            ViewGroup currTargetViewHost2 = getCurrTargetViewHost();
            ViewParent parent = currTargetViewHost2 != null ? currTargetViewHost2.getParent() : null;
            ViewGroup viewGroup2 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup2 != null) {
                viewGroup2.suppressLayout(true);
            }
            Set<ShareTransitionProperty> shareTransitionProperties = tileDeformationAnimationController.getShareTransitionProperties();
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(shareTransitionProperties, 10));
            Iterator<T> it2 = shareTransitionProperties.iterator();
            while (it2.hasNext()) {
                arrayList.add(((ShareTransitionProperty) it2.next()).moveTo(viewGroup));
            }
            getShareTransitionProperties().clear();
            getShareTransitionProperties().addAll(arrayList);
            Iterator<T> it3 = getShareTransitionProperties().iterator();
            while (true) {
                if (!it3.hasNext()) {
                    next = null;
                    break;
                }
                next = it3.next();
                ShareTransitionProperty shareTransitionProperty = (ShareTransitionProperty) next;
                if ((shareTransitionProperty instanceof TileFixedBoundShareTransitionProperty) && ((TileFixedBoundShareTransitionProperty) shareTransitionProperty).getSharedElementId() == 2131364411) {
                    break;
                }
            }
            TileFixedBoundShareTransitionProperty tileFixedBoundShareTransitionProperty = next instanceof TileFixedBoundShareTransitionProperty ? (TileFixedBoundShareTransitionProperty) next : null;
            if (tileFixedBoundShareTransitionProperty != null) {
                tileFixedBoundShareTransitionProperty.setOnAnimateListener(this);
            }
            this.bgBoundTransitionProperty = new BoundShareTransitionProperty(viewGroup, getTileBgId(), false);
            ViewGroup currTargetViewHost3 = getCurrTargetViewHost();
            if (currTargetViewHost3 != null) {
                this.rectHost.set(0, 0, currTargetViewHost3.getWidth(), currTargetViewHost3.getHeight());
                BoundShareTransitionProperty boundShareTransitionProperty = this.bgBoundTransitionProperty;
                if (boundShareTransitionProperty != null) {
                    boundShareTransitionProperty.setValue(this.rectHost);
                }
            }
            if (iDeformationAnimationController != this) {
                tileDeformationAnimationController.onAnimationEnd(true);
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.animation.deform.BaseDeformationAnimationController, com.oplus.systemui.plugins.qs.customize.view.animation.deform.IDeformationAnimationController
    public void updateFrame(IDeformationAnimationController.ComputeResult computeResult) {
        super.updateFrame(computeResult);
        ViewGroup currTargetViewHost = getCurrTargetViewHost();
        if (currTargetViewHost != null) {
            this.rectHost.set(0, 0, currTargetViewHost.getWidth(), currTargetViewHost.getHeight());
            BoundShareTransitionProperty boundShareTransitionProperty = this.bgBoundTransitionProperty;
            if (boundShareTransitionProperty != null) {
                boundShareTransitionProperty.setValue(this.rectHost);
            }
        }
    }
}
