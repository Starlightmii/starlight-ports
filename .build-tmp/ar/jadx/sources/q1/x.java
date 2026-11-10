package q1;

import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import io.github.andrea_lyz.assistrestore.R;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x implements View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f5443d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f5444e;

    public /* synthetic */ x(int i5, Object obj) {
        this.f5443d = i5;
        this.f5444e = obj;
    }

    private final void a(View view) {
    }

    private final void b(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        switch (this.f5443d) {
            case 0:
                f0 f0Var = (f0) this.f5444e;
                AccessibilityManager accessibilityManager = f0Var.f5181g;
                accessibilityManager.addAccessibilityStateChangeListener(f0Var.f5183i);
                accessibilityManager.addTouchExplorationStateChangeListener(f0Var.f5184j);
                break;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        boolean z2;
        switch (this.f5443d) {
            case 0:
                f0 f0Var = (f0) this.f5444e;
                f0Var.f5186l.removeCallbacks(f0Var.K);
                AccessibilityManager accessibilityManager = f0Var.f5181g;
                accessibilityManager.removeAccessibilityStateChangeListener(f0Var.f5183i);
                accessibilityManager.removeTouchExplorationStateChangeListener(f0Var.f5184j);
                break;
            case 1:
                a aVar = (a) this.f5444e;
                ViewParent parent = aVar.getParent();
                Iterator it = (parent == null ? g4.b.f2549a : new g4.d(new f0.d2(1, parent), w2.v.f6439k, 1)).iterator();
                while (true) {
                    z2 = false;
                    if (it.hasNext()) {
                        Object obj = (ViewParent) it.next();
                        if (obj instanceof View) {
                            View view2 = (View) obj;
                            b4.l.e("<this>", view2);
                            Object tag = view2.getTag(R.id.is_pooling_container_tag);
                            Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
                            if (bool != null ? bool.booleanValue() : false) {
                                z2 = true;
                            }
                        }
                    }
                }
                if (!z2) {
                    f3 f3Var = aVar.f5099f;
                    if (f3Var != null) {
                        f3Var.e();
                    }
                    aVar.f5099f = null;
                    aVar.requestLayout();
                }
                break;
            default:
                view.removeOnAttachStateChangeListener(this);
                ((i4.h1) this.f5444e).b(null);
                break;
        }
    }
}
