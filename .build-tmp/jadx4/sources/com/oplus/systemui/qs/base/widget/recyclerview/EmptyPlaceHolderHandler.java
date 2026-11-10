package com.oplus.systemui.qs.base.widget.recyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.foundation.shape.DpCornerSize$;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.qs.base.springtransition.DrawableAlphaProperty;
import com.oplus.systemui.qs.base.springtransition.DrawableAlphaPropertyKt;
import com.oplusos.systemui.common.adapter.BaseRecyclerAdapter;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.LocCache;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewholder.GridData;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EmptyPlaceHolderHandler implements Drawable.Callback {
    private static final String TAG = "EmptyPlaceHolderHandler";
    private final Rect cellRectDiff;
    private final HashSet<Loc> cellsFillLoc;
    private final Rect currFirstCellRect;
    private final Lazy decoration$delegate;
    private final Rect emptyCellDrawRect;
    private final Map<Loc, EmptyCell> emptyCells;
    private final Function2 getItemRectAction;
    private final RecyclerView hostRecyclerView;
    private final StaggeredPagerLayoutManager.LayoutConfig layoutConfig;
    private final StaggeredPagerLayoutManager.LayoutState layoutState;
    private final Lazy locsNeedTogetherAnimation$delegate;
    private final PlaceHolder placeHolder;
    private final EmptyPlaceHolderHandler$progressProperty$1 progressProperty;
    private final Lazy springAnimationTogether$delegate;
    private final EmptyPlaceHolderHandler$updateCurrFirstCellRect$1 updateCurrFirstCellRect;
    public static final Companion Companion = new Companion();
    private static final Lazy springTogetherConfig$delegate = LazyKt__LazyJVMKt.lazy(new EmptyPlaceHolderHandler$$ExternalSyntheticLambda0(0));
    private static final Lazy springConfigOfSingleCell$delegate = LazyKt__LazyJVMKt.lazy(new EmptyPlaceHolderHandler$$ExternalSyntheticLambda0(1));

    /* JADX INFO: renamed from: com.oplus.systemui.qs.base.widget.recyclerview.EmptyPlaceHolderHandler$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements View.OnAttachStateChangeListener {
        public /* synthetic */ EmptyPlaceHolderHandler this$0;

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            this.this$0.getHostRecyclerView().getViewTreeObserver().removeOnPreDrawListener(this.this$0.updateCurrFirstCellRect);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final Spring getSpringConfigOfSingleCell() {
            return (Spring) EmptyPlaceHolderHandler.springConfigOfSingleCell$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Spring getSpringTogetherConfig() {
            return (Spring) EmptyPlaceHolderHandler.springTogetherConfig$delegate.getValue();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class PlaceHolderDecoration extends RecyclerView.ItemDecoration {
        public /* synthetic */ EmptyPlaceHolderHandler this$0;

        public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
            Rect placeHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib;
            Drawable placeHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib;
            super.onDraw(canvas, recyclerView, state);
            EmptyPlaceHolderHandler emptyPlaceHolderHandler = this.this$0;
            if (emptyPlaceHolderHandler.isEmptyPlaceHolderHideFully() || (placeHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib = emptyPlaceHolderHandler.getPlaceHolder().getPlaceHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib()) == null || !placeHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib.isValid() || (placeHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib = emptyPlaceHolderHandler.getPlaceHolder().getPlaceHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib()) == null) {
                return;
            }
            for (Map.Entry entry : emptyPlaceHolderHandler.emptyCells.entrySet()) {
                Rect emptyCellRect = emptyPlaceHolderHandler.getEmptyCellRect((EmptyCell) entry.getValue());
                if (Rect.intersects(emptyCellRect, emptyPlaceHolderHandler.layoutState.getDisplayRect())) {
                    Drawable drawableCreateDrawableForEmptyCell = emptyPlaceHolderHandler.createDrawableForEmptyCell((EmptyCell) entry.getValue(), -1.0f);
                    if (drawableCreateDrawableForEmptyCell == null) {
                        drawableCreateDrawableForEmptyCell = placeHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib;
                    }
                    float currValue = DrawableAlphaPropertyKt.getDRAWABLE_ALPHA().getValue(drawableCreateDrawableForEmptyCell) == 1.0f ? 1.0f : emptyPlaceHolderHandler.progressProperty.getProgressMapper().getCurrValue();
                    if (emptyPlaceHolderHandler.progressProperty.getProgressMapper().getEndValue() == 0.0f) {
                        currValue = 1.0f - currValue;
                    }
                    float f = 1.0f - currValue;
                    emptyPlaceHolderHandler.emptyCellDrawRect.set((((emptyCellRect.left - emptyPlaceHolderHandler.layoutState.getScrollX()) + placeHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib.left) - ((int) (emptyPlaceHolderHandler.getPlaceHolder().getBoundsDiff$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib().left * f))) - ((int) (emptyPlaceHolderHandler.cellRectDiff.left * f)), (((emptyCellRect.top - emptyPlaceHolderHandler.layoutState.getScrollY()) + placeHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib.top) - ((int) (emptyPlaceHolderHandler.getPlaceHolder().getBoundsDiff$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib().top * f))) - ((int) (emptyPlaceHolderHandler.cellRectDiff.top * f)), (((emptyCellRect.left - emptyPlaceHolderHandler.layoutState.getScrollX()) + placeHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib.right) - ((int) (emptyPlaceHolderHandler.getPlaceHolder().getBoundsDiff$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib().left * f))) - ((int) (emptyPlaceHolderHandler.cellRectDiff.right * f)), (((emptyCellRect.top - emptyPlaceHolderHandler.layoutState.getScrollY()) + placeHolderBoundsToCell$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib.bottom) - ((int) (emptyPlaceHolderHandler.getPlaceHolder().getBoundsDiff$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib().top * f))) - ((int) (emptyPlaceHolderHandler.cellRectDiff.bottom * f)));
                    drawableCreateDrawableForEmptyCell.setBounds(emptyPlaceHolderHandler.emptyCellDrawRect);
                    drawableCreateDrawableForEmptyCell.draw(canvas);
                }
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ProgressMapper {
        public float currValue;
        public float endValue;
        public float startValue;

        public ProgressMapper(float f, float f2, float f3) {
            this.startValue = f;
            this.endValue = f2;
            this.currValue = f3;
        }

        public final ProgressMapper copy(float f, float f2, float f3) {
            return new ProgressMapper(f, f2, f3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ProgressMapper)) {
                return false;
            }
            ProgressMapper progressMapper = (ProgressMapper) obj;
            return Float.compare(this.startValue, progressMapper.startValue) == 0 && Float.compare(this.endValue, progressMapper.endValue) == 0 && Float.compare(this.currValue, progressMapper.currValue) == 0;
        }

        public final float getCurrValue() {
            return this.currValue;
        }

        public final float getEndValue() {
            return this.endValue;
        }

        public final float getStartValue() {
            return this.startValue;
        }

        public final int hashCode() {
            return Float.hashCode(this.currValue) + ChangeSize$.ExternalSyntheticOutline0.m(this.endValue, Float.hashCode(this.startValue) * 31, 31);
        }

        public final void mapTo(DrawableAlphaProperty drawableAlphaProperty) {
            if (drawableAlphaProperty.isValid()) {
                float f = this.currValue;
                float f2 = this.startValue;
                drawableAlphaProperty.setProgress((f - f2) / (this.endValue - f2));
            }
        }

        public final void setCurrValue(float f) {
            this.currValue = f;
        }

        public final void setEndValue(float f) {
            this.endValue = f;
        }

        public final void setStartValue(float f) {
            this.startValue = f;
        }

        public String toString() {
            return DpCornerSize$.ExternalSyntheticOutline0.m(this.currValue, ")", MonoSpline$.ExternalSyntheticOutline0.m("ProgressMapper(startValue=", this.startValue, ", endValue=", this.endValue, ", currValue="));
        }
    }

    public static COUISpringAnimation $r8$lambda$ZWFmqMKrLK430ph9jzkMqruYdBA(EmptyPlaceHolderHandler emptyPlaceHolderHandler) {
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(emptyPlaceHolderHandler.progressProperty, emptyPlaceHolderHandler);
        COUISpringForce cOUISpringForce = new COUISpringForce();
        Companion companion = Companion;
        cOUISpringForce.setBounce(companion.getSpringTogetherConfig().bounce());
        cOUISpringForce.setResponse(companion.getSpringTogetherConfig().response());
        cOUISpringAnimation.setMinimumVisibleChange(0.00390625f);
        cOUISpringAnimation.setSpring(cOUISpringForce);
        cOUISpringAnimation.setStartVelocity(companion.getSpringTogetherConfig().velocity());
        cOUISpringAnimation.setStartValue(0.0f);
        return cOUISpringAnimation;
    }

    public EmptyPlaceHolderHandler(RecyclerView recyclerView, StaggeredPagerLayoutManager.LayoutState layoutState, StaggeredPagerLayoutManager.LayoutConfig layoutConfig, Function2 function2, PlaceHolder placeHolder) {
        this.hostRecyclerView = recyclerView;
        this.layoutState = layoutState;
        this.layoutConfig = layoutConfig;
        this.getItemRectAction = function2;
        this.placeHolder = placeHolder;
        EmptyPlaceHolderHandler$$ExternalSyntheticLambda2 emptyPlaceHolderHandler$$ExternalSyntheticLambda2 = new EmptyPlaceHolderHandler$$ExternalSyntheticLambda2(0);
        emptyPlaceHolderHandler$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.decoration$delegate = LazyKt__LazyJVMKt.lazy(emptyPlaceHolderHandler$$ExternalSyntheticLambda2);
        this.emptyCells = new LinkedHashMap();
        this.emptyCellDrawRect = new Rect();
        this.currFirstCellRect = new Rect();
        this.cellRectDiff = new Rect();
        this.progressProperty = new EmptyPlaceHolderHandler$progressProperty$1(this);
        EmptyPlaceHolderHandler$$ExternalSyntheticLambda2 emptyPlaceHolderHandler$$ExternalSyntheticLambda3 = new EmptyPlaceHolderHandler$$ExternalSyntheticLambda2(1);
        emptyPlaceHolderHandler$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.springAnimationTogether$delegate = LazyKt__LazyJVMKt.lazy(emptyPlaceHolderHandler$$ExternalSyntheticLambda3);
        this.locsNeedTogetherAnimation$delegate = LazyKt__LazyJVMKt.lazy(new EmptyPlaceHolderHandler$$ExternalSyntheticLambda0(2));
        this.cellsFillLoc = new HashSet<>();
        placeHolder.setCallback$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(this);
        placeHolder.setEmptyPlaceHolderHandler$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(this);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        recyclerView.addOnAttachStateChangeListener(anonymousClass1);
        EmptyPlaceHolderHandler$updateCurrFirstCellRect$1 emptyPlaceHolderHandler$updateCurrFirstCellRect$1 = new EmptyPlaceHolderHandler$updateCurrFirstCellRect$1();
        emptyPlaceHolderHandler$updateCurrFirstCellRect$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.updateCurrFirstCellRect = emptyPlaceHolderHandler$updateCurrFirstCellRect$1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Loc> getLocsNeedTogetherAnimation() {
        return (List) this.locsNeedTogetherAnimation$delegate.getValue();
    }

    private final COUISpringAnimation getSpringAnimationTogether() {
        return (COUISpringAnimation) this.springAnimationTogether$delegate.getValue();
    }

    public final void addOneEmptyPage(int i) {
        Set<Loc> setKeySet = this.emptyCells.keySet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : setKeySet) {
            if (((Loc) obj).getPageIndex() >= i) {
                arrayList.add(obj);
            }
        }
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i3 < size) {
            Object obj2 = arrayList.get(i3);
            i3++;
            Loc loc = (Loc) obj2;
            EmptyCell emptyCellRemove = this.emptyCells.remove(loc);
            if (emptyCellRemove != null) {
                Loc loc2 = LocCache.INSTANCE.loc(loc.getPageIndex() + 1, loc.getStartCol(), loc.getStartRow());
                this.emptyCells.put(loc2, emptyCellRemove.copy(loc2, null, emptyCellRemove.drawable, emptyCellRemove.springAnimation, emptyCellRemove.drawableAlphaProperty, emptyCellRemove.temporary));
            }
        }
        int rowCount = this.layoutConfig.getRowCount();
        for (int i4 = 0; i4 < rowCount; i4++) {
            int colCount = this.layoutConfig.getColCount();
            for (int i5 = 0; i5 < colCount; i5++) {
                Loc loc3 = LocCache.INSTANCE.loc(i, i5, i4);
                this.emptyCells.put(loc3, new EmptyCell(loc3));
            }
        }
        HashSet<Loc> hashSet = this.cellsFillLoc;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj3 : hashSet) {
            if (((Loc) obj3).getPageIndex() >= i) {
                arrayList2.add(obj3);
            }
        }
        int size2 = arrayList2.size();
        while (i2 < size2) {
            Object obj4 = arrayList2.get(i2);
            i2++;
            Loc loc4 = (Loc) obj4;
            this.cellsFillLoc.remove(loc4);
            this.cellsFillLoc.add(LocCache.INSTANCE.loc(loc4.getPageIndex() + 1, loc4.getStartCol(), loc4.getStartRow()));
        }
    }

    public final Drawable createDrawableForEmptyCell(EmptyCell emptyCell, float f) {
        Drawable drawable;
        Drawable placeHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib = this.placeHolder.getPlaceHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib();
        if (placeHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "EmptyPlaceHolderHandler-->[setPlaceHolderVisible] placeHolder.placeHolderDrawable is null");
            }
            return null;
        }
        Drawable drawable2 = emptyCell.getDrawable();
        if (drawable2 != null) {
            return drawable2;
        }
        Function2 copyOrSetAction$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib = this.placeHolder.getCopyOrSetAction$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib();
        if (copyOrSetAction$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib == null || (drawable = (Drawable) copyOrSetAction$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib.invoke(placeHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib, Boolean.TRUE)) == null) {
            return null;
        }
        if (!drawable.equals(placeHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib)) {
            if (-1.0f == f) {
                boolean zContains = getLocsNeedTogetherAnimation().contains(emptyCell.getLoc());
                EmptyPlaceHolderHandler$progressProperty$1 emptyPlaceHolderHandler$progressProperty$1 = this.progressProperty;
                f = !zContains ? emptyPlaceHolderHandler$progressProperty$1.getProgressMapper().getEndValue() : emptyPlaceHolderHandler$progressProperty$1.getProgressMapper().getCurrValue();
            }
            emptyCell.setDrawable(drawable);
            DrawableAlphaPropertyKt.getDRAWABLE_ALPHA().setValue(drawable, f);
            drawable.setCallback(placeHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib.getCallback());
        }
        return drawable;
    }

    public final void enable(boolean z, boolean z2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("[enable] ", " withAnimationInDisplayRect=", "EmptyPlaceHolderHandler-->", z, z2));
        }
        Collection<EmptyCell> collectionValues = this.emptyCells.values();
        ArrayList arrayList = new ArrayList();
        for (Object obj : collectionValues) {
            EmptyCell emptyCell = (EmptyCell) obj;
            if (emptyCell.getSpringAnimation() != null || emptyCell.getDrawableAlphaProperty() != null) {
                arrayList.add(obj);
            }
        }
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj2 = arrayList.get(i);
            i++;
            EmptyCell emptyCell2 = (EmptyCell) obj2;
            COUISpringAnimation springAnimation = emptyCell2.getSpringAnimation();
            if (springAnimation != null) {
                springAnimation.cancel();
            }
            emptyCell2.setSpringAnimation(null);
            emptyCell2.setDrawableAlphaProperty(null);
        }
        float f = z ? 1.0f : 0.0f;
        if (!z2) {
            for (EmptyCell emptyCell3 : this.emptyCells.values()) {
                emptyCell3.setSpringAnimation(null);
                emptyCell3.setDrawableAlphaProperty(null);
                DrawableAlphaPropertyKt.getDRAWABLE_ALPHA().setValue(emptyCell3.getDrawable(), f);
            }
            getSpringAnimationTogether().cancel();
            ProgressMapper progressMapper = this.progressProperty.getProgressMapper();
            progressMapper.startValue = progressMapper.currValue;
            progressMapper.endValue = f;
            this.progressProperty.setValue(f);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "EmptyPlaceHolderHandler-->".concat("[enable] " + z + " setToFinalDirectly " + f));
                return;
            }
            return;
        }
        getLocsNeedTogetherAnimation().clear();
        List<Loc> locsNeedTogetherAnimation = getLocsNeedTogetherAnimation();
        Map<Loc, EmptyCell> map = this.emptyCells;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<Loc, EmptyCell> entry : map.entrySet()) {
            if (Rect.intersects(getEmptyCellRect(entry.getValue()), this.layoutState.getDisplayRect())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        ArrayList arrayList2 = new ArrayList(linkedHashMap.size());
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            arrayList2.add((Loc) ((Map.Entry) it.next()).getKey());
        }
        locsNeedTogetherAnimation.addAll(arrayList2);
        Map<Loc, EmptyCell> map2 = this.emptyCells;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry<Loc, EmptyCell> entry2 : map2.entrySet()) {
            if (!getLocsNeedTogetherAnimation().contains(entry2.getKey())) {
                linkedHashMap2.put(entry2.getKey(), entry2.getValue());
            }
        }
        Iterator it2 = linkedHashMap2.entrySet().iterator();
        while (it2.hasNext()) {
            DrawableAlphaPropertyKt.getDRAWABLE_ALPHA().setValue(((EmptyCell) ((Map.Entry) it2.next()).getValue()).getDrawable(), f);
        }
        List<Loc> locsNeedTogetherAnimation2 = getLocsNeedTogetherAnimation();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj3 : locsNeedTogetherAnimation2) {
            FloatPropertyCompat drawable_alpha = DrawableAlphaPropertyKt.getDRAWABLE_ALPHA();
            EmptyCell emptyCell4 = this.emptyCells.get((Loc) obj3);
            float value = drawable_alpha.getValue(emptyCell4 != null ? emptyCell4.getDrawable() : null);
            if (value == 0.0f || value == 1.0f) {
                if (value != this.progressProperty.getProgressMapper().getCurrValue()) {
                    arrayList3.add(obj3);
                }
            }
        }
        ArrayList arrayList4 = new ArrayList();
        int size2 = arrayList3.size();
        int i2 = 0;
        while (i2 < size2) {
            Object obj4 = arrayList3.get(i2);
            i2++;
            EmptyCell emptyCell5 = this.emptyCells.get((Loc) obj4);
            if (emptyCell5 != null) {
                arrayList4.add(emptyCell5);
            }
        }
        updateSpringConfig(getSpringAnimationTogether());
        ProgressMapper progressMapper2 = this.progressProperty.getProgressMapper();
        progressMapper2.startValue = progressMapper2.currValue;
        progressMapper2.endValue = f;
        getSpringAnimationTogether().animateToFinalPosition(f);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EmptyPlaceHolderHandler-->".concat("[enable] " + z + " animateToFinalPosition " + f));
        }
        ArrayList arrayList5 = new ArrayList();
        int size3 = arrayList.size();
        int i3 = 0;
        while (i3 < size3) {
            Object obj5 = arrayList.get(i3);
            i3++;
            if (getLocsNeedTogetherAnimation().contains(((EmptyCell) obj5).getLoc())) {
                arrayList5.add(obj5);
            }
        }
        for (EmptyCell emptyCell6 : CollectionsKt___CollectionsKt.plus(arrayList5, arrayList4)) {
            Drawable drawable = emptyCell6.getDrawable();
            if (drawable != null) {
                DrawableAlphaProperty drawableAlphaProperty = emptyCell6.getDrawableAlphaProperty();
                if (drawableAlphaProperty == null) {
                    drawableAlphaProperty = new DrawableAlphaProperty(drawable);
                }
                drawableAlphaProperty.captureStartValue();
                drawableAlphaProperty.setEndValue(Integer.valueOf((!z || emptyCell6.getTemporary()) ? 0 : 255));
                if (drawableAlphaProperty.isValid()) {
                    emptyCell6.setDrawableAlphaProperty(drawableAlphaProperty);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "EmptyPlaceHolderHandler-->".concat("[enable] " + z + " " + emptyCell6.getLoc() + " create drawableAlphaProperty " + drawableAlphaProperty.getStartValue() + "-" + drawableAlphaProperty.getValue() + "-" + drawableAlphaProperty.getEndValue()));
                    }
                } else {
                    emptyCell6.setDrawableAlphaProperty(null);
                }
            }
        }
        Collection<EmptyCell> collectionValues2 = this.emptyCells.values();
        ArrayList arrayList6 = new ArrayList();
        for (Object obj6 : collectionValues2) {
            if (!getLocsNeedTogetherAnimation().contains(((EmptyCell) obj6).getLoc())) {
                arrayList6.add(obj6);
            }
        }
        int size4 = arrayList6.size();
        int i4 = 0;
        while (i4 < size4) {
            Object obj7 = arrayList6.get(i4);
            i4++;
            ((EmptyCell) obj7).setDrawableAlphaProperty(null);
        }
    }

    public final List<Drawable> getAllDrawable() {
        ArrayList arrayList = new ArrayList();
        Drawable placeHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib = this.placeHolder.getPlaceHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib();
        if (placeHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib != null) {
            arrayList.add(placeHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib);
        }
        Iterator<Map.Entry<Loc, EmptyCell>> it = this.emptyCells.entrySet().iterator();
        while (it.hasNext()) {
            Drawable drawable = it.next().getValue().getDrawable();
            if (drawable != null) {
                arrayList.add(drawable);
            }
        }
        return arrayList;
    }

    public final List<Drawable> getAllDrawableByLoc() {
        ArrayList arrayList = new ArrayList();
        Drawable placeHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib = this.placeHolder.getPlaceHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib();
        if (placeHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib != null) {
            arrayList.add(placeHolderDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib);
        }
        for (Map.Entry<Loc, EmptyCell> entry : this.emptyCells.entrySet()) {
            Loc key = entry.getKey();
            Drawable drawable = entry.getValue().getDrawable();
            if (drawable != null && isEmptyCellByLoc(key)) {
                arrayList.add(drawable);
            }
        }
        return arrayList;
    }

    public final PlaceHolderDecoration getDecoration() {
        return (PlaceHolderDecoration) this.decoration$delegate.getValue();
    }

    public final Rect getEmptyCellRect(EmptyCell emptyCell) {
        Rect rect = emptyCell.getRect();
        if (rect != null) {
            return rect;
        }
        Rect rect2 = (Rect) this.getItemRectAction.invoke(emptyCell.getLoc(), SpanCache.INSTANCE.spanSize(1, 1));
        emptyCell.setRect(rect2);
        return rect2;
    }

    public final List<Loc> getEmptyLocs() {
        Map<Loc, EmptyCell> map = this.emptyCells;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<Loc, EmptyCell> entry : map.entrySet()) {
            if (!entry.getValue().getTemporary()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return CollectionsKt___CollectionsKt.toList(linkedHashMap.keySet());
    }

    public final RecyclerView getHostRecyclerView() {
        return this.hostRecyclerView;
    }

    public final PlaceHolder getPlaceHolder() {
        return this.placeHolder;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        this.hostRecyclerView.invalidate();
    }

    public final boolean isEmptyCellByLoc(Loc loc) {
        return !this.cellsFillLoc.contains(loc);
    }

    public final boolean isEmptyPlaceHolderHideFully() {
        return this.progressProperty.getProgressMapper().getCurrValue() == 0.0f;
    }

    public final void refreshAllEmptyCell(int i) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(this.emptyCells);
        this.emptyCells.clear();
        this.cellsFillLoc.clear();
        boolean[][][] zArr = new boolean[i][][];
        for (int i2 = 0; i2 < i; i2++) {
            int rowCount = this.layoutConfig.getRowCount();
            boolean[][] zArr2 = new boolean[rowCount][];
            for (int i3 = 0; i3 < rowCount; i3++) {
                zArr2[i3] = new boolean[this.layoutConfig.getColCount()];
            }
            zArr[i2] = zArr2;
        }
        RecyclerView.Adapter adapter = this.hostRecyclerView.getAdapter();
        BaseRecyclerAdapter baseRecyclerAdapter = adapter instanceof BaseRecyclerAdapter ? (BaseRecyclerAdapter) adapter : null;
        List<Object> dataList = baseRecyclerAdapter != null ? baseRecyclerAdapter.getDataList() : null;
        if (!(dataList instanceof List)) {
            dataList = null;
        }
        if (dataList == null) {
            return;
        }
        int i4 = 0;
        for (Object obj : this.layoutConfig.getSpecifyLoc()) {
            int i5 = i4 + 1;
            if (i4 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            Loc loc = (Loc) obj;
            SpanSize size = ((GridData) dataList.get(i4)).getSize();
            int iMin = Math.min(size.getRowSpan() + loc.getStartRow(), this.layoutConfig.getRowCount());
            for (int startRow = loc.getStartRow(); startRow < iMin; startRow++) {
                int iMin2 = Math.min(size.getColSpan() + loc.getStartCol(), this.layoutConfig.getColCount());
                for (int startCol = loc.getStartCol(); startCol < iMin2; startCol++) {
                    int pageIndex = loc.getPageIndex();
                    if (pageIndex >= 0 && pageIndex < i) {
                        zArr[loc.getPageIndex()][startRow][startCol] = true;
                        this.cellsFillLoc.add(LocCache.INSTANCE.loc(loc.getPageIndex(), startCol, startRow));
                    }
                }
            }
            i4 = i5;
        }
        int i6 = 0;
        while (true) {
            Rect rect = null;
            if (i6 >= i) {
                break;
            }
            int rowCount2 = this.layoutConfig.getRowCount();
            int i7 = 0;
            while (i7 < rowCount2) {
                int colCount = this.layoutConfig.getColCount();
                int i8 = 0;
                while (i8 < colCount) {
                    if (!zArr[i6][i7][i8]) {
                        Loc loc2 = LocCache.INSTANCE.loc(i6, i8, i7);
                        Map<Loc, EmptyCell> map = this.emptyCells;
                        EmptyCell emptyCell = (EmptyCell) linkedHashMap.get(loc2);
                        if (emptyCell != null) {
                            emptyCell.setRect(rect);
                            if (emptyCell.getTemporary()) {
                                linkedHashMap.remove(loc2);
                                emptyCell.setTemporary(false);
                            }
                        } else {
                            emptyCell = new EmptyCell(loc2);
                        }
                        map.put(loc2, emptyCell);
                    }
                    i8++;
                    rect = null;
                }
                i7++;
                rect = null;
            }
            i6++;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("[refreshAllEmptyCell] oldCount=", linkedHashMap.size(), this.emptyCells.size(), ", newCount=", "EmptyPlaceHolderHandler-->"));
        }
        if (((COUIDynamicAnimation) getSpringAnimationTogether()).mRunning || this.progressProperty.getProgressMapper().getCurrValue() != 1.0f) {
            return;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (Rect.intersects(getEmptyCellRect((EmptyCell) entry.getValue()), this.layoutState.getDisplayRect())) {
                linkedHashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        Set setKeySet = linkedHashMap2.keySet();
        Map<Loc, EmptyCell> map2 = this.emptyCells;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        for (Map.Entry<Loc, EmptyCell> entry2 : map2.entrySet()) {
            if (Rect.intersects(getEmptyCellRect(entry2.getValue()), this.layoutState.getDisplayRect())) {
                linkedHashMap3.put(entry2.getKey(), entry2.getValue());
            }
        }
        Set setKeySet2 = linkedHashMap3.keySet();
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : setKeySet2) {
            if (!setKeySet.contains((Loc) obj2)) {
                arrayList.add(obj2);
            }
        }
        int size2 = arrayList.size();
        int i9 = 0;
        while (i9 < size2) {
            Object obj3 = arrayList.get(i9);
            i9++;
            setPlaceHolderVisible((Loc) obj3, true, true, false);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj4 : setKeySet) {
            if (!setKeySet2.contains((Loc) obj4)) {
                arrayList2.add(obj4);
            }
        }
        int size3 = arrayList2.size();
        int i10 = 0;
        while (i10 < size3) {
            Object obj5 = arrayList2.get(i10);
            i10++;
            Loc loc3 = (Loc) obj5;
            if (this.emptyCells.get(loc3) == null) {
                EmptyCell emptyCell2 = (EmptyCell) linkedHashMap.get(loc3);
                if (emptyCell2 != null) {
                    emptyCell2.setRect(null);
                    this.emptyCells.put(loc3, emptyCell2);
                } else {
                    emptyCell2 = null;
                }
                if (emptyCell2 == null) {
                }
            }
            setPlaceHolderVisible(loc3, false, true, false);
        }
    }

    public final void removeOneEmptyPage(int i) {
        Set<Loc> setKeySet = this.emptyCells.keySet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : setKeySet) {
            if (((Loc) obj).getPageIndex() == i) {
                arrayList.add(obj);
            }
        }
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i3 < size) {
            Object obj2 = arrayList.get(i3);
            i3++;
            this.emptyCells.remove((Loc) obj2);
        }
        Set<Loc> setKeySet2 = this.emptyCells.keySet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj3 : setKeySet2) {
            if (((Loc) obj3).getPageIndex() > i) {
                arrayList2.add(obj3);
            }
        }
        int size2 = arrayList2.size();
        int i4 = 0;
        while (i4 < size2) {
            Object obj4 = arrayList2.get(i4);
            i4++;
            Loc loc = (Loc) obj4;
            EmptyCell emptyCellRemove = this.emptyCells.remove(loc);
            if (emptyCellRemove != null) {
                Loc loc2 = LocCache.INSTANCE.loc(loc.getPageIndex() - 1, loc.getStartCol(), loc.getStartRow());
                this.emptyCells.put(loc2, emptyCellRemove.copy(loc2, null, emptyCellRemove.drawable, emptyCellRemove.springAnimation, emptyCellRemove.drawableAlphaProperty, emptyCellRemove.temporary));
            }
        }
        HashSet<Loc> hashSet = this.cellsFillLoc;
        ArrayList arrayList3 = new ArrayList();
        for (Object obj5 : hashSet) {
            if (((Loc) obj5).getPageIndex() > i) {
                arrayList3.add(obj5);
            }
        }
        int size3 = arrayList3.size();
        while (i2 < size3) {
            Object obj6 = arrayList3.get(i2);
            i2++;
            Loc loc3 = (Loc) obj6;
            this.cellsFillLoc.remove(loc3);
            this.cellsFillLoc.add(LocCache.INSTANCE.loc(loc3.getPageIndex() - 1, loc3.getStartCol(), loc3.getStartRow()));
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        this.hostRecyclerView.getContext().getMainThreadHandler().postAtTime(runnable, drawable, j);
    }

    public final void setPlaceHolderVisible(Loc loc, boolean z, boolean z2, boolean z3) {
        if (!z3 && isEmptyPlaceHolderHideFully()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "EmptyPlaceHolderHandler-->".concat("[setPlaceHolderVisible] cannot call this method when empty placeHolder is fully hide visible=" + z + " loc=" + loc));
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sb = new StringBuilder("[setPlaceHolderVisible] ");
            sb.append(loc);
            sb.append(" visible=");
            sb.append(z);
            sb.append(", withAnimation=");
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("EmptyPlaceHolderHandler-->", "SystemUi--QuickSettings", sb, z2);
        }
        EmptyCell emptyCell = this.emptyCells.get(loc);
        if (emptyCell == null) {
            emptyCell = new EmptyCell(loc);
        }
        emptyCell.setTemporary(!z);
        this.emptyCells.put(loc, emptyCell);
        Drawable drawableCreateDrawableForEmptyCell = createDrawableForEmptyCell(emptyCell, 0.0f);
        if (drawableCreateDrawableForEmptyCell == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "EmptyPlaceHolderHandler-->[setPlaceHolderVisible] placeHolder.copyAction return null");
                return;
            }
            return;
        }
        float f = z ? 1.0f : 0.0f;
        if (!z2) {
            DrawableAlphaPropertyKt.getDRAWABLE_ALPHA().setValue(drawableCreateDrawableForEmptyCell, f);
            if (!z) {
                this.emptyCells.remove(loc);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "EmptyPlaceHolderHandler-->".concat("[setPlaceHolderVisible] " + loc + " setToFinalDirectly " + f));
                return;
            }
            return;
        }
        COUISpringAnimation springAnimation = emptyCell.getSpringAnimation();
        if (springAnimation == null) {
            springAnimation = new COUISpringAnimation(DrawableAlphaPropertyKt.getDRAWABLE_ALPHA(), drawableCreateDrawableForEmptyCell);
            COUISpringForce cOUISpringForce = new COUISpringForce();
            Companion companion = Companion;
            cOUISpringForce.setBounce(companion.getSpringConfigOfSingleCell().bounce());
            cOUISpringForce.setResponse(companion.getSpringConfigOfSingleCell().response());
            springAnimation.setMinimumVisibleChange(0.00390625f);
            springAnimation.setSpring(cOUISpringForce);
            springAnimation.setStartVelocity(companion.getSpringConfigOfSingleCell().velocity());
            springAnimation.setStartValue(DrawableAlphaPropertyKt.getDRAWABLE_ALPHA().getValue(drawableCreateDrawableForEmptyCell));
            emptyCell.setSpringAnimation(springAnimation);
            setPlaceHolderVisible.springAnimation.1.1 r11 = new setPlaceHolderVisible.springAnimation.1.1();
            r11.this$0 = this;
            VarHandle.storeStoreFence();
            springAnimation.addEndListener(r11);
        }
        updateSpringConfig(springAnimation);
        springAnimation.animateToFinalPosition(f);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EmptyPlaceHolderHandler-->".concat("[setPlaceHolderVisible] " + loc + " animationToFinal " + f));
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        this.hostRecyclerView.getContext().getMainThreadHandler().removeCallbacks(runnable, drawable);
    }

    public final void updateCellRect() {
        Rect rect = (Rect) this.getItemRectAction.invoke(LocCache.loc$default(LocCache.INSTANCE, 0, 0, 7), SpanCache.INSTANCE.spanSize(1, 1));
        Rect rect2 = this.cellRectDiff;
        int i = rect.left;
        Rect rect3 = this.currFirstCellRect;
        rect2.set(i - rect3.left, rect.top - rect3.top, rect.right - rect3.right, rect.bottom - rect3.bottom);
        this.hostRecyclerView.getViewTreeObserver().removeOnPreDrawListener(this.updateCurrFirstCellRect);
        this.hostRecyclerView.getViewTreeObserver().addOnPreDrawListener(this.updateCurrFirstCellRect);
        Rect rect4 = this.cellRectDiff;
        if (rect4.left == 0 && rect4.right == 0 && rect4.top == 0 && rect4.bottom == 0) {
            return;
        }
        Iterator<T> it = this.emptyCells.values().iterator();
        while (it.hasNext()) {
            ((EmptyCell) it.next()).setRect(null);
        }
    }

    public final void updateDrawable$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(Function2 function2) {
        for (EmptyCell emptyCell : this.emptyCells.values()) {
            Drawable drawable = emptyCell.getDrawable();
            if (drawable != null) {
                emptyCell.setDrawable((Drawable) function2.invoke(drawable, Boolean.FALSE));
            }
        }
    }

    public final void updateSpringConfig(COUISpringAnimation cOUISpringAnimation) {
        Spring springTogetherConfig = Intrinsics.areEqual(cOUISpringAnimation, getSpringAnimationTogether()) ? Companion.getSpringTogetherConfig() : Companion.getSpringConfigOfSingleCell();
        COUISpringForce spring = cOUISpringAnimation.getSpring();
        spring.setBounce(springTogetherConfig.bounce());
        spring.setResponse(springTogetherConfig.response());
        cOUISpringAnimation.setStartVelocity(springTogetherConfig.velocity());
    }
}
