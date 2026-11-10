package b0;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f467a = b4.l.a(Build.DEVICE, "layoutlib");

    public static final o a(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = viewGroup.getChildAt(i5);
            if (childAt instanceof o) {
                return (o) childAt;
            }
        }
        o oVar = new o(viewGroup.getContext());
        viewGroup.addView(oVar);
        return oVar;
    }

    public static final ViewGroup b(View view) {
        Object obj = view;
        while (!(obj instanceof ViewGroup)) {
            ViewParent parent = ((View) obj).getParent();
            if (!(parent instanceof View)) {
                throw new IllegalArgumentException(("Couldn't find a valid parent for " + obj + ". Are you overriding LocalView and providing a View that is not attached to the view hierarchy?").toString());
            }
            obj = parent;
        }
        return (ViewGroup) obj;
    }
}
