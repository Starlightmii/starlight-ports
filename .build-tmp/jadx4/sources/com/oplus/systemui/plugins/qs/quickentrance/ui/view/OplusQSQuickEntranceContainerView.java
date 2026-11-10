package com.oplus.systemui.plugins.qs.quickentrance.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.oplus.systemui.plugins.qs.CellAreaConfig$$ExternalSyntheticOutline0;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSQuickEntranceContainerView extends ConstraintLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SeparateQSQuickEntranceContainerView";
    private final ConstraintSet constraintSet;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public /* synthetic */ OplusQSQuickEntranceContainerView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void setupHorizontalConstraints(ConstraintSet constraintSet) {
        int dimension = (int) getContext().getResources().getDimension(2131172133);
        constraintSet.connect(2131364743, 6, 0, 6, dimension);
        constraintSet.connect(2131364743, 3, 0, 3);
        constraintSet.connect(2131364743, 4, 0, 4);
        constraintSet.clear(2131364743, 7);
        constraintSet.connect(2131364822, 7, 0, 7, dimension);
        constraintSet.connect(2131364822, 3, 0, 3, 0);
        constraintSet.connect(2131364822, 4, 0, 4);
        constraintSet.clear(2131364822, 6);
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131172072);
        constraintSet.clear(2131364108, 3);
        constraintSet.clear(2131364108, 4);
        constraintSet.clear(2131364108, 6);
        constraintSet.clear(2131364108, 7);
        constraintSet.connect(2131364108, 7, 2131364822, 6, dimensionPixelSize);
        constraintSet.connect(2131364108, 3, 0, 3);
        constraintSet.connect(2131364108, 4, 0, 4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void setupVerticalConstraints(ConstraintSet constraintSet) {
        getContext().getResources().getDimension(2131172133);
        constraintSet.connect(2131364822, 3, 0, 3);
        constraintSet.connect(2131364822, 6, 0, 6);
        constraintSet.connect(2131364822, 7, 0, 7, 0);
        constraintSet.clear(2131364822, 4);
        constraintSet.connect(2131364743, 4, 0, 4);
        constraintSet.connect(2131364743, 6, 0, 6);
        constraintSet.connect(2131364743, 7, 0, 7);
        constraintSet.clear(2131364743, 3);
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131172072);
        constraintSet.clear(2131364108, 3);
        constraintSet.clear(2131364108, 4);
        constraintSet.clear(2131364108, 6);
        constraintSet.clear(2131364108, 7);
        constraintSet.connect(2131364108, 3, 2131364822, 4, dimensionPixelSize);
        constraintSet.connect(2131364108, 6, 2131364822, 6);
        constraintSet.connect(2131364108, 7, 2131364822, 7);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super/*android.view.ViewGroup*/.addView(view, i, layoutParams);
        if (view instanceof FrameLayout) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "SeparateQSQuickEntranceContainerView-->".concat("addView p0=" + view + " p1=" + i + " applyViewArrangement"));
            }
            applyViewArrangement(((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(OplusQSSpecialModeProvider.Companion)).booleanValue());
        }
    }

    public final void applyViewArrangement(boolean z) {
        this.constraintSet.clone(this);
        ConstraintSet constraintSet = this.constraintSet;
        if (z) {
            setupVerticalConstraints(constraintSet);
        } else {
            setupHorizontalConstraints(constraintSet);
        }
        this.constraintSet.applyTo(this);
    }

    public final boolean hasOverlappingRendering() {
        return false;
    }

    public OplusQSQuickEntranceContainerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public OplusQSQuickEntranceContainerView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public OplusQSQuickEntranceContainerView(Context context) {
        this(context, null, 0, 0, 14, null);
    }

    public OplusQSQuickEntranceContainerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.constraintSet = new ConstraintSet();
    }
}
