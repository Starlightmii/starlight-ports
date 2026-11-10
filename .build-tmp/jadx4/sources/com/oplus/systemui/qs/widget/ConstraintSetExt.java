package com.oplus.systemui.qs.widget;

import androidx.constraintlayout.widget.ConstraintSet;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ConstraintSetExt {
    public static final ConstraintSetExt INSTANCE = new ConstraintSetExt();

    public static final boolean updateConstrainHeight(ConstraintSet constraintSet, int i, int i2) {
        if (constraintSet.getHeight(i) == i2) {
            return false;
        }
        constraintSet.constrainHeight(i, i2);
        return true;
    }

    public static final boolean updateConstrainWidth(ConstraintSet constraintSet, int i, int i2) {
        if (constraintSet.getWidth(i) == i2) {
            return false;
        }
        constraintSet.constrainWidth(i, i2);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0025  */
    public static final boolean updateEndSideConnect(ConstraintSet constraintSet, int i, int i2, int i3, Integer num) {
        ConstraintSet.Constraint constraint = constraintSet.getConstraint(i);
        boolean z = true;
        if (constraint != null && (num == null || constraint.layout.endMargin == num.intValue())) {
            if (i3 == 7) {
                ConstraintSet.Layout layout = constraint.layout;
                if (layout.endToEnd == i2 && layout.endToStart == -1) {
                    z = false;
                }
            } else if (i3 == 6) {
                ConstraintSet.Layout layout2 = constraint.layout;
                if (layout2.endToStart == i2 && layout2.endToEnd == -1) {
                    z = false;
                }
            } else {
                z = false;
            }
        }
        if (z) {
            if (num != null) {
                constraintSet.connect(i, 7, i2, i3, num.intValue());
                return z;
            }
            constraintSet.connect(i, 7, i2, i3);
        }
        return z;
    }

    public static final boolean updateScaleX(ConstraintSet constraintSet, int i, float f) {
        ConstraintSet.Transform transform;
        ConstraintSet.Constraint constraint = constraintSet.getConstraint(i);
        if (Intrinsics.areEqual((constraint == null || (transform = constraint.transform) == null) ? null : Float.valueOf(transform.scaleX), f)) {
            return false;
        }
        constraintSet.setScaleX(i, f);
        return false;
    }

    public static final boolean updateScaleY(ConstraintSet constraintSet, int i, float f) {
        ConstraintSet.Transform transform;
        ConstraintSet.Constraint constraint = constraintSet.getConstraint(i);
        if (Intrinsics.areEqual((constraint == null || (transform = constraint.transform) == null) ? null : Float.valueOf(transform.scaleY), f)) {
            return false;
        }
        constraintSet.setScaleY(i, f);
        return false;
    }

    public static final boolean updateTranslationX(ConstraintSet constraintSet, int i, float f) {
        ConstraintSet.Transform transform;
        ConstraintSet.Constraint constraint = constraintSet.getConstraint(i);
        if (Intrinsics.areEqual((constraint == null || (transform = constraint.transform) == null) ? null : Float.valueOf(transform.translationX), f)) {
            return false;
        }
        constraintSet.setTranslationX(i, f);
        return true;
    }

    public static final boolean updateTranslationY(ConstraintSet constraintSet, int i, float f) {
        ConstraintSet.Transform transform;
        ConstraintSet.Constraint constraint = constraintSet.getConstraint(i);
        if (Intrinsics.areEqual((constraint == null || (transform = constraint.transform) == null) ? null : Float.valueOf(transform.translationY), f)) {
            return false;
        }
        constraintSet.setTranslationY(i, f);
        return true;
    }

    public static final boolean updateVisibility(ConstraintSet constraintSet, int i, int i2) {
        if (constraintSet.getVisibility(i) == i2) {
            return false;
        }
        constraintSet.setVisibility(i, i2);
        return true;
    }

    public static final boolean updateEndSideConnect(ConstraintSet constraintSet, int i, int i2, int i3) {
        return updateEndSideConnect(constraintSet, i, i2, i3, null);
    }
}
