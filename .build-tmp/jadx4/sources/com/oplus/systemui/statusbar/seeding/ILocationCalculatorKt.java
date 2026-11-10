package com.oplus.systemui.statusbar.seeding;

import android.view.View;
import android.view.ViewGroup;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ILocationCalculatorKt {
    public static final Pair<Integer, Integer> computeSeedlingHiddenLocation(View view, boolean z) {
        IScaledAncestorView scaledAncestorViewOrNull = getScaledAncestorViewOrNull(view);
        float scaleFraction = scaledAncestorViewOrNull != null ? scaledAncestorViewOrNull.getScaleFraction(z) : 1.0f;
        Pair<Integer, Integer> locationByScaledAncestorView = scaledAncestorViewOrNull != null ? getLocationByScaledAncestorView(view, scaledAncestorViewOrNull.getScaledAncestorView(), scaleFraction, scaledAncestorViewOrNull.getPivotXDirection()) : getLocationByDefault(view);
        return new Pair<>(locationByScaledAncestorView.getFirst(), Integer.valueOf((int) ((view.getWidth() * scaleFraction) + ((Number) locationByScaledAncestorView.getFirst()).floatValue())));
    }

    public static final Pair<Integer, Integer> getLocationByDefault(View view) {
        int i = view.getLocationOnScreen()[0];
        return new Pair<>(Integer.valueOf(i), Integer.valueOf(view.getWidth() + i));
    }

    public static final Pair<Integer, Integer> getLocationByScaledAncestorView(View view, ViewGroup viewGroup, float f, DIRECTION direction) {
        if (direction != DIRECTION.LEFT) {
            float scaleX = viewGroup.getScaleX();
            if (scaleX <= 0.0f) {
                return new Pair<>(0, 0);
            }
            if (scaleX == f) {
                int i = view.getLocationOnScreen()[0];
                return new Pair<>(Integer.valueOf(i), Integer.valueOf((int) ((view.getWidth() * f) + i)));
            }
            float width = (viewGroup.getWidth() * scaleX) + viewGroup.getLocationOnScreen()[0];
            float width2 = width - (((width - ((view.getWidth() * scaleX) + view.getLocationOnScreen()[0])) / scaleX) * f);
            return new Pair<>(Integer.valueOf((int) (width2 - (view.getWidth() * f))), Integer.valueOf((int) width2));
        }
        float scaleX2 = viewGroup.getScaleX();
        if (scaleX2 <= 0.0f) {
            return new Pair<>(0, 0);
        }
        if (scaleX2 == f) {
            int i2 = view.getLocationOnScreen()[0];
            return new Pair<>(Integer.valueOf(i2), Integer.valueOf((int) ((view.getWidth() * f) + i2)));
        }
        int i3 = viewGroup.getLocationOnScreen()[0];
        float f2 = (((view.getLocationOnScreen()[0] - i3) / scaleX2) * f) + i3;
        return new Pair<>(Integer.valueOf((int) f2), Integer.valueOf((int) ((view.getWidth() * f) + f2)));
    }

    public static final IScaledAncestorView getScaledAncestorViewOrNull(View view) {
        for (Object parent = view.getParent(); parent instanceof View; parent = ((View) parent).getParent()) {
            if (parent instanceof IScaledAncestorView) {
                return (IScaledAncestorView) parent;
            }
        }
        return null;
    }
}
