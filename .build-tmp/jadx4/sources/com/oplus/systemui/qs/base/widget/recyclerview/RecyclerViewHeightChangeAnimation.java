package com.oplus.systemui.qs.base.widget.recyclerview;

import android.view.View;
import androidx.core.view.OneShotPreDrawListener;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.recyclerview.widget.COUIRecyclerView;
import com.android.keyguard.logging.KeyguardLogger$;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.qs.base.widget.recyclerview.callback.ILayoutCallback;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.viewholder.CellInfo;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RecyclerViewHeightChangeAnimation implements ViewWithSizeChange.OnSizeChangeListener, ILayoutCallback {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusItemShowHideAnimator";
    public boolean curEnable;
    public SpanSize currGridInfo;
    public SpanSize lastGridInfo;
    private final StaggeredPagerLayoutManager layoutManager;
    private final COUIRecyclerView recyclerView;
    private final COUISpringAnimation recyclerViewAnimation;
    private final Lazy springAlphaConfig$delegate = LazyKt__LazyJVMKt.lazy(new RecyclerViewHeightChangeAnimation$$ExternalSyntheticLambda4());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Unit $r8$lambda$9wMGTfBNLgl7vzhHQ48DydUBKpU(int i, int i2, RecyclerViewHeightChangeAnimation recyclerViewHeightChangeAnimation, float f, float f2) {
        int i3 = i * i2;
        int i4 = i2 + i3;
        while (i3 < i4) {
            View viewFindViewByPosition = recyclerViewHeightChangeAnimation.layoutManager.findViewByPosition(i3);
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.setTransitionAlpha(f);
            }
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.setScaleX(f2);
            }
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.setScaleY(f2);
            }
            i3++;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$b5mFsGbO42fWJbDDC6rNtMx-wHg, reason: not valid java name */
    public static Unit m261$r8$lambda$b5mFsGbO42fWJbDDC6rNtMxwHg(int i, int i2, RecyclerViewHeightChangeAnimation recyclerViewHeightChangeAnimation, float f, float f2) {
        int i3 = i * i2;
        int i4 = i2 + i3;
        while (i3 < i4) {
            View viewFindViewByPosition = recyclerViewHeightChangeAnimation.layoutManager.findViewByPosition(i3);
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.setTransitionAlpha(f);
            }
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.setScaleX(f2);
            }
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.setScaleY(f2);
            }
            i3++;
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$kx1icgzpaEa3FLozIp1yi0xHiY4(int i, int i2, RecyclerViewHeightChangeAnimation recyclerViewHeightChangeAnimation) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "showAnimator end", null, 8);
        int i3 = i * i2;
        int i4 = i2 + i3;
        while (i3 < i4) {
            View viewFindViewByPosition = recyclerViewHeightChangeAnimation.layoutManager.findViewByPosition(i3);
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.setScaleX(1.0f);
            }
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.setScaleY(1.0f);
            }
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.setTransitionAlpha(1.0f);
            }
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.setTranslationY(0.0f);
            }
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.setTranslationX(0.0f);
            }
            i3++;
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$p7FZ81Y0wOGaegDnDRp8d5Ctyao(int i, int i2, RecyclerViewHeightChangeAnimation recyclerViewHeightChangeAnimation) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "hideAnimator end", null, 8);
        int i3 = i * i2;
        int i4 = i2 + i3;
        while (i3 < i4) {
            View viewFindViewByPosition = recyclerViewHeightChangeAnimation.layoutManager.findViewByPosition(i3);
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.setScaleX(1.0f);
            }
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.setScaleY(1.0f);
            }
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.setTransitionAlpha(1.0f);
            }
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.setTranslationY(0.0f);
            }
            if (viewFindViewByPosition != null) {
                viewFindViewByPosition.setTranslationX(0.0f);
            }
            i3++;
        }
        return Unit.INSTANCE;
    }

    public RecyclerViewHeightChangeAnimation(COUIRecyclerView cOUIRecyclerView, StaggeredPagerLayoutManager staggeredPagerLayoutManager) {
        this.recyclerView = cOUIRecyclerView;
        this.layoutManager = staggeredPagerLayoutManager;
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(new FloatValueHolder());
        cOUISpringAnimation.setSpring(new COUISpringForce().setDampingRatio(1.0f));
        cOUISpringAnimation.setStartValue(0.0f);
        cOUISpringAnimation.setMinimumVisibleChange(0.001f);
        RecyclerViewHeightChangeAnimation$recyclerViewAnimation$1$1 recyclerViewHeightChangeAnimation$recyclerViewAnimation$1$1 = new RecyclerViewHeightChangeAnimation$recyclerViewAnimation$1$1();
        recyclerViewHeightChangeAnimation$recyclerViewAnimation$1$1.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(recyclerViewHeightChangeAnimation$recyclerViewAnimation$1$1);
        RecyclerViewHeightChangeAnimation$recyclerViewAnimation$1$2 recyclerViewHeightChangeAnimation$recyclerViewAnimation$1$2 = new RecyclerViewHeightChangeAnimation$recyclerViewAnimation$1$2();
        recyclerViewHeightChangeAnimation$recyclerViewAnimation$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(recyclerViewHeightChangeAnimation$recyclerViewAnimation$1$2);
        this.recyclerViewAnimation = cOUISpringAnimation;
    }

    public static final boolean access$isNeedAnimator(RecyclerViewHeightChangeAnimation recyclerViewHeightChangeAnimation) {
        return Math.abs(recyclerViewHeightChangeAnimation.recyclerView.getMeasuredHeight() - (recyclerViewHeightChangeAnimation.recyclerView.getBottom() - recyclerViewHeightChangeAnimation.recyclerView.getTop())) > 0;
    }

    public static final void access$prepareAnimator(RecyclerViewHeightChangeAnimation recyclerViewHeightChangeAnimation) {
        float fIntValue;
        SpanSize spanSize = recyclerViewHeightChangeAnimation.lastGridInfo;
        if (spanSize == null) {
            spanSize = null;
        }
        SpanSize spanSize2 = recyclerViewHeightChangeAnimation.currGridInfo;
        if (spanSize2 == null) {
            spanSize2 = null;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "prepareAnimator, lastGridInfo=" + spanSize + ", currGridInfo=" + spanSize2, null, 8);
        SpanSize spanSize3 = recyclerViewHeightChangeAnimation.lastGridInfo;
        if (spanSize3 == null) {
            spanSize3 = null;
        }
        int rowSpan = spanSize3.getRowSpan();
        SpanSize spanSize4 = recyclerViewHeightChangeAnimation.currGridInfo;
        if (spanSize4 == null) {
            spanSize4 = null;
        }
        if (rowSpan == spanSize4.getRowSpan()) {
            SpanSize spanSize5 = recyclerViewHeightChangeAnimation.lastGridInfo;
            if (spanSize5 == null) {
                spanSize5 = null;
            }
            int colSpan = spanSize5.getColSpan();
            SpanSize spanSize6 = recyclerViewHeightChangeAnimation.currGridInfo;
            if (spanSize6 == null) {
                spanSize6 = null;
            }
            if (colSpan == spanSize6.getColSpan()) {
                return;
            }
        }
        SpanSize spanSize7 = recyclerViewHeightChangeAnimation.currGridInfo;
        if (spanSize7 == null) {
            spanSize7 = null;
        }
        int rowSpan2 = spanSize7.getRowSpan();
        SpanSize spanSize8 = recyclerViewHeightChangeAnimation.lastGridInfo;
        if (spanSize8 == null) {
            spanSize8 = null;
        }
        if (rowSpan2 >= spanSize8.getRowSpan()) {
            SpanSize spanSize9 = recyclerViewHeightChangeAnimation.lastGridInfo;
            if (spanSize9 == null) {
                spanSize9 = null;
            }
            int rowSpan3 = spanSize9.getRowSpan();
            SpanSize spanSize10 = recyclerViewHeightChangeAnimation.lastGridInfo;
            if (spanSize10 == null) {
                spanSize10 = null;
            }
            SpanSize spanSize11 = recyclerViewHeightChangeAnimation.currGridInfo;
            if (spanSize11 == null) {
                spanSize11 = null;
            }
            int rowSpan4 = spanSize11.getRowSpan();
            SpanSize spanSize12 = recyclerViewHeightChangeAnimation.currGridInfo;
            if (spanSize12 == null) {
                spanSize12 = null;
            }
            int colSpan2 = spanSize12.getColSpan() * rowSpan4;
            for (int colSpan3 = spanSize10.getColSpan() * rowSpan3; colSpan3 < colSpan2; colSpan3++) {
                View viewFindViewByPosition = recyclerViewHeightChangeAnimation.layoutManager.findViewByPosition(colSpan3);
                if (viewFindViewByPosition != null) {
                    viewFindViewByPosition.setTranslationX(0.0f);
                }
                if (viewFindViewByPosition != null) {
                    viewFindViewByPosition.setTranslationY(0.0f);
                }
                if (Intrinsics.areEqual(viewFindViewByPosition != null ? Float.valueOf(viewFindViewByPosition.getTransitionAlpha()) : null, 1.0f)) {
                    if (viewFindViewByPosition != null) {
                        viewFindViewByPosition.setTransitionAlpha(0.0f);
                    }
                    if (viewFindViewByPosition != null) {
                        viewFindViewByPosition.setScaleX(0.8f);
                    }
                    if (viewFindViewByPosition != null) {
                        viewFindViewByPosition.setScaleY(0.8f);
                    }
                }
            }
            return;
        }
        SpanSize spanSize13 = recyclerViewHeightChangeAnimation.currGridInfo;
        if (spanSize13 == null) {
            spanSize13 = null;
        }
        int rowSpan5 = spanSize13.getRowSpan();
        SpanSize spanSize14 = recyclerViewHeightChangeAnimation.currGridInfo;
        if (spanSize14 == null) {
            spanSize14 = null;
        }
        SpanSize spanSize15 = recyclerViewHeightChangeAnimation.lastGridInfo;
        if (spanSize15 == null) {
            spanSize15 = null;
        }
        int rowSpan6 = spanSize15.getRowSpan();
        SpanSize spanSize16 = recyclerViewHeightChangeAnimation.lastGridInfo;
        if (spanSize16 == null) {
            spanSize16 = null;
        }
        int colSpan4 = spanSize16.getColSpan() * rowSpan6;
        for (int colSpan5 = spanSize14.getColSpan() * rowSpan5; colSpan5 < colSpan4; colSpan5++) {
            View viewFindViewByPosition2 = recyclerViewHeightChangeAnimation.layoutManager.findViewByPosition(colSpan5);
            if (viewFindViewByPosition2 != null) {
                boolean zIsRtl = DeviceUtil.isRtl();
                COUIRecyclerView cOUIRecyclerView = recyclerViewHeightChangeAnimation.recyclerView;
                if (zIsRtl) {
                    int measuredWidth = cOUIRecyclerView.getMeasuredWidth();
                    Integer itemPageIndex = recyclerViewHeightChangeAnimation.layoutManager.getItemPageIndex(colSpan5);
                    fIntValue = measuredWidth * (itemPageIndex != null ? itemPageIndex.intValue() : 0);
                } else {
                    int measuredWidth2 = cOUIRecyclerView.getMeasuredWidth();
                    Integer itemPageIndex2 = recyclerViewHeightChangeAnimation.layoutManager.getItemPageIndex(colSpan5);
                    fIntValue = -(measuredWidth2 * (itemPageIndex2 != null ? itemPageIndex2.intValue() : 0));
                }
                viewFindViewByPosition2.setTranslationX(fIntValue);
            }
            if (viewFindViewByPosition2 != null) {
                int cellHeight = recyclerViewHeightChangeAnimation.layoutManager.getLayoutConfig().getCellHeight();
                SpanSize spanSize17 = recyclerViewHeightChangeAnimation.currGridInfo;
                if (spanSize17 == null) {
                    spanSize17 = null;
                }
                int rowSpan7 = spanSize17.getRowSpan() * cellHeight;
                Integer itemPageIndex3 = recyclerViewHeightChangeAnimation.layoutManager.getItemPageIndex(colSpan5);
                viewFindViewByPosition2.setTranslationY(rowSpan7 * (itemPageIndex3 != null ? itemPageIndex3.intValue() : 0));
            }
        }
    }

    public static final void access$startRecyclerviewAlphaAnimator(RecyclerViewHeightChangeAnimation recyclerViewHeightChangeAnimation) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "recyclerViewAnimation start", null, 8);
        recyclerViewHeightChangeAnimation.recyclerView.setTransitionAlpha(0.0f);
        recyclerViewHeightChangeAnimation.recyclerViewAnimation.setStartValue(0.0f);
        recyclerViewHeightChangeAnimation.recyclerViewAnimation.getSpring().setResponse(recyclerViewHeightChangeAnimation.getSpringAlphaConfig().response());
        recyclerViewHeightChangeAnimation.recyclerViewAnimation.animateToFinalPosition(1.0f);
    }

    private final Spring getSpringAlphaConfig() {
        return (Spring) this.springAlphaConfig$delegate.getValue();
    }

    public static COUISpringAnimation getSpringAnimation(View view) {
        Object tag = view.getTag(2131365577);
        if (tag instanceof COUISpringAnimation) {
            return (COUISpringAnimation) tag;
        }
        return null;
    }

    public static void resetItemAnimator(View view) {
        COUISpringAnimation springAnimation;
        COUISpringAnimation springAnimation2 = getSpringAnimation(view);
        if (springAnimation2 != null && springAnimation2.canSkipToEnd() && (springAnimation = getSpringAnimation(view)) != null) {
            springAnimation.skipToEnd();
        }
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        view.setTransitionAlpha(1.0f);
    }

    public final void animatorToFinalPosition(View view, boolean z, Function2 function2, Function2 function3) {
        if (view != null) {
            if (z) {
                Object tag = view.getTag(2131365579);
                Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
                if (Boolean.valueOf(bool != null ? bool.booleanValue() : false).equals(Boolean.TRUE)) {
                    return;
                }
            }
            if (!z) {
                Object tag2 = view.getTag(2131365578);
                Boolean bool2 = tag2 instanceof Boolean ? (Boolean) tag2 : null;
                if (Boolean.valueOf(bool2 != null ? bool2.booleanValue() : false).equals(Boolean.TRUE)) {
                    return;
                }
            }
            float f = z ? 1.0f : 0.0f;
            float f2 = z ? 1.0f : 0.8f;
            float f3 = z ? 1.0f : 0.0f;
            float transitionAlpha = (view.getTransitionAlpha() - 0.0f) / 1.0f;
            if (view.getTransitionAlpha() == f && view.getScaleX() == f2) {
                return;
            }
            view.setTag(2131365579, Boolean.valueOf(z));
            view.setTag(2131365578, Boolean.valueOf(!z));
            view.setTag(2131365582, function2);
            view.setTag(2131365576, function3);
            if (getSpringAnimation(view) == null) {
                COUISpringAnimation springAnimation = getSpringAnimation(view);
                if (springAnimation != null) {
                    springAnimation.cancel();
                }
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                COUISpringAnimation springAnimation2 = getSpringAnimation(view);
                objectRef.element = springAnimation2;
                if (springAnimation2 == null) {
                    springAnimation2 = new COUISpringAnimation(new FloatValueHolder());
                    springAnimation2.setSpring(new COUISpringForce().setDampingRatio(1.0f).setBounce(0.0f).setResponse(0.2f));
                    springAnimation2.setMinimumVisibleChange(0.001f);
                    createAnimator.1.1.1 r13 = new createAnimator.1.1.1();
                    r13.this$0 = this;
                    r13.$view = view;
                    VarHandle.storeStoreFence();
                    springAnimation2.addUpdateListener(r13);
                    createAnimator.1.1.2 r14 = new createAnimator.1.1.2();
                    r14.this$0 = this;
                    r14.$view = view;
                    VarHandle.storeStoreFence();
                    springAnimation2.addEndListener(r14);
                    objectRef.element = springAnimation2;
                }
                view.setTag(2131365577, springAnimation2);
            }
            COUISpringAnimation springAnimation3 = getSpringAnimation(view);
            if (springAnimation3 != null) {
                springAnimation3.setStartValue(transitionAlpha);
            }
            COUISpringAnimation springAnimation4 = getSpringAnimation(view);
            if (springAnimation4 != null) {
                springAnimation4.animateToFinalPosition(f3);
            }
        }
    }

    public final void enable(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("enable, enabled=", z), null, 8);
        if (!z) {
            int rowCount = this.layoutManager.getLayoutConfig().getRowCount();
            int colCount = this.layoutManager.getLayoutConfig().getColCount();
            SpanCache spanCache = SpanCache.INSTANCE;
            this.lastGridInfo = spanCache.spanSize(colCount, rowCount);
            this.currGridInfo = spanCache.spanSize(colCount, rowCount);
        }
        if (this.curEnable == z) {
            return;
        }
        this.curEnable = z;
        View view = this.recyclerView;
        if (z) {
            ViewWithSizeChange.Companion.injectOnSizeChange(view, null).addListener(this);
            this.layoutManager.getLayoutCallbackWrapper().addLayoutCallback(this, true);
            return;
        }
        ViewWithSizeChange.Companion.injectOnSizeChange(view, null).removeListener(this);
        this.layoutManager.getLayoutCallbackWrapper().removeLayoutCallback(this);
        int childCount = this.layoutManager.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.layoutManager.getChildAt(i);
            if (childAt != null) {
                resetItemAnimator(childAt);
            }
        }
    }

    public final COUISpringAnimation getRecyclerViewAnimation() {
        return this.recyclerViewAnimation;
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.callback.ILayoutCallback
    public /* bridge */ /* synthetic */ void onCellInfoChanged(CellInfo cellInfo) {
        super.onCellInfoChanged(cellInfo);
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.callback.ILayoutCallback
    public final void onCellNumChanged(int i, int i2) {
        SpanSize spanSize;
        SpanSize spanSize2 = this.lastGridInfo;
        if (spanSize2 == null || (spanSize = this.currGridInfo) == null) {
            SpanCache spanCache = SpanCache.INSTANCE;
            this.lastGridInfo = spanCache.spanSize(i, i2);
            this.currGridInfo = spanCache.spanSize(i, i2);
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onCellNumChanged, lastGridInfo=" + spanSize2 + ", currGridInfo=" + spanSize, null, 8);
        COUIRecyclerView cOUIRecyclerView = this.recyclerView;
        RecyclerViewHeightChangeAnimation$onCellNumChanged$$inlined$doOnPreDraw$1 recyclerViewHeightChangeAnimation$onCellNumChanged$$inlined$doOnPreDraw$1 = new RecyclerViewHeightChangeAnimation$onCellNumChanged$$inlined$doOnPreDraw$1();
        recyclerViewHeightChangeAnimation$onCellNumChanged$$inlined$doOnPreDraw$1.this$0 = this;
        VarHandle.storeStoreFence();
        OneShotPreDrawListener.add(cOUIRecyclerView, recyclerViewHeightChangeAnimation$onCellNumChanged$$inlined$doOnPreDraw$1);
    }

    public final void onSizeChange(int i, int i2, int i3, int i4) {
        SpanSize spanSize = this.currGridInfo;
        if (spanSize == null) {
            spanSize = null;
        }
        int rowSpan = spanSize.getRowSpan();
        SpanSize spanSize2 = this.lastGridInfo;
        if (spanSize2 == null) {
            spanSize2 = null;
        }
        int rowSpan2 = spanSize2.getRowSpan();
        SpanSize spanSize3 = this.currGridInfo;
        int i5 = 0;
        int i6 = 1;
        if (rowSpan > rowSpan2) {
            if (spanSize3 == null) {
                spanSize3 = null;
            }
            int rowSpan3 = spanSize3.getRowSpan();
            SpanSize spanSize4 = this.lastGridInfo;
            if (spanSize4 == null) {
                spanSize4 = null;
            }
            int measuredHeight = this.recyclerView.getMeasuredHeight() - i4;
            int cellHeight = this.layoutManager.getLayoutConfig().getCellHeight();
            int colCount = this.layoutManager.getLayoutConfig().getColCount();
            for (int rowSpan4 = spanSize4.getRowSpan(); rowSpan4 < rowSpan3; rowSpan4++) {
                if (measuredHeight < (rowSpan3 - rowSpan4) * cellHeight) {
                    View viewFindViewByPosition = this.layoutManager.findViewByPosition(rowSpan4 * colCount);
                    RecyclerViewHeightChangeAnimation$$ExternalSyntheticLambda0 recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda0 = new RecyclerViewHeightChangeAnimation$$ExternalSyntheticLambda0(i5);
                    recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda0.f$0 = rowSpan4;
                    recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda0.f$1 = colCount;
                    recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda0.f$2 = this;
                    VarHandle.storeStoreFence();
                    RecyclerViewHeightChangeAnimation$$ExternalSyntheticLambda0 recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda1 = new RecyclerViewHeightChangeAnimation$$ExternalSyntheticLambda0(i6);
                    recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda1.f$0 = rowSpan4;
                    recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda1.f$1 = colCount;
                    recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda1.f$2 = this;
                    VarHandle.storeStoreFence();
                    animatorToFinalPosition(viewFindViewByPosition, true, recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda0, recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda1);
                }
            }
        } else {
            if (spanSize3 == null) {
                spanSize3 = null;
            }
            int rowSpan5 = spanSize3.getRowSpan();
            SpanSize spanSize5 = this.lastGridInfo;
            if (spanSize5 == null) {
                spanSize5 = null;
            }
            if (rowSpan5 < spanSize5.getRowSpan()) {
                SpanSize spanSize6 = this.currGridInfo;
                if (spanSize6 == null) {
                    spanSize6 = null;
                }
                int rowSpan6 = spanSize6.getRowSpan();
                SpanSize spanSize7 = this.lastGridInfo;
                if (spanSize7 == null) {
                    spanSize7 = null;
                }
                int rowSpan7 = spanSize7.getRowSpan();
                int measuredHeight2 = i4 - this.recyclerView.getMeasuredHeight();
                int cellHeight2 = this.layoutManager.getLayoutConfig().getCellHeight();
                int colCount2 = this.layoutManager.getLayoutConfig().getColCount();
                for (int i7 = rowSpan6; i7 < rowSpan7; i7++) {
                    if (measuredHeight2 < ((i7 - rowSpan6) + 1) * cellHeight2) {
                        View viewFindViewByPosition2 = this.layoutManager.findViewByPosition(i7 * colCount2);
                        RecyclerViewHeightChangeAnimation$$ExternalSyntheticLambda0 recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda2 = new RecyclerViewHeightChangeAnimation$$ExternalSyntheticLambda0(2);
                        recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda2.f$0 = i7;
                        recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda2.f$1 = colCount2;
                        recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda2.f$2 = this;
                        VarHandle.storeStoreFence();
                        RecyclerViewHeightChangeAnimation$$ExternalSyntheticLambda0 recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda3 = new RecyclerViewHeightChangeAnimation$$ExternalSyntheticLambda0(3);
                        recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda3.f$0 = i7;
                        recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda3.f$1 = colCount2;
                        recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda3.f$2 = this;
                        VarHandle.storeStoreFence();
                        animatorToFinalPosition(viewFindViewByPosition2, false, recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda2, recyclerViewHeightChangeAnimation$$ExternalSyntheticLambda3);
                    }
                }
            }
        }
        if (i4 == this.recyclerView.getMeasuredHeight() && i3 == this.recyclerView.getMeasuredWidth()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "sizeChange end", null, 8);
            SpanSize spanSize8 = this.currGridInfo;
            this.lastGridInfo = spanSize8 != null ? spanSize8 : null;
        }
    }
}
