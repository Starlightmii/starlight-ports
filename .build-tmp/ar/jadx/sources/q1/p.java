package q1;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5300e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ u f5301f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p(u uVar, int i5) {
        super(1);
        this.f5300e = i5;
        this.f5301f = uVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        w0.b bVar;
        switch (this.f5300e) {
            case 0:
                KeyEvent keyEvent = ((h1.b) obj).f2604a;
                long jU = h1.c.u(keyEvent);
                if (h1.a.a(jU, h1.a.f2596h)) {
                    bVar = new w0.b(keyEvent.isShiftPressed() ? 2 : 1);
                } else if (h1.a.a(jU, h1.a.f2594f)) {
                    bVar = new w0.b(4);
                } else if (h1.a.a(jU, h1.a.f2593e)) {
                    bVar = new w0.b(3);
                } else {
                    if (h1.a.a(jU, h1.a.f2591c) ? true : h1.a.a(jU, h1.a.f2599k)) {
                        bVar = new w0.b(5);
                    } else {
                        if (h1.a.a(jU, h1.a.f2592d) ? true : h1.a.a(jU, h1.a.f2600l)) {
                            bVar = new w0.b(6);
                        } else {
                            if (h1.a.a(jU, h1.a.f2595g) ? true : h1.a.a(jU, h1.a.f2597i) ? true : h1.a.a(jU, h1.a.f2601m)) {
                                bVar = new w0.b(7);
                            } else {
                                bVar = h1.a.a(jU, h1.a.f2590b) ? true : h1.a.a(jU, h1.a.f2598j) ? new w0.b(8) : null;
                            }
                        }
                    }
                }
                if (bVar != null) {
                    int i5 = bVar.f6225a;
                    if (h1.c.y(keyEvent) == 2) {
                        u uVar = this.f5301f;
                        x0.d dVarU = uVar.u();
                        Boolean boolC = ((androidx.compose.ui.focus.b) uVar.getFocusOwner()).c(i5, dVarU, new n(bVar, 4));
                        if (boolC != null ? boolC.booleanValue() : true) {
                            return Boolean.TRUE;
                        }
                        if (!(i5 == 1 || i5 == 2)) {
                            return Boolean.FALSE;
                        }
                        Integer numH = w0.d.H(i5);
                        if (numH == null) {
                            throw new IllegalStateException("Invalid focus direction");
                        }
                        int iIntValue = numH.intValue();
                        Rect rectU = dVarU != null ? y0.l0.u(dVarU) : null;
                        if (rectU == null) {
                            throw new IllegalStateException("Invalid rect");
                        }
                        View viewFindNextFocus = uVar;
                        while (true) {
                            if (viewFindNextFocus != null) {
                                FocusFinder focusFinder = FocusFinder.getInstance();
                                View rootView = uVar.getRootView();
                                b4.l.c("null cannot be cast to non-null type android.view.ViewGroup", rootView);
                                viewFindNextFocus = focusFinder.findNextFocus((ViewGroup) rootView, viewFindNextFocus, iIntValue);
                                if (viewFindNextFocus != null) {
                                    if (!viewFindNextFocus.equals(uVar)) {
                                        ViewParent parent = viewFindNextFocus.getParent();
                                        while (true) {
                                            if (parent != null) {
                                                if (parent != uVar) {
                                                    parent = parent.getParent();
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                viewFindNextFocus = null;
                            }
                        }
                        if (b4.l.a(viewFindNextFocus, uVar)) {
                            viewFindNextFocus = null;
                        }
                        if ((viewFindNextFocus == null || !w0.d.D(viewFindNextFocus, Integer.valueOf(iIntValue), rectU)) && ((androidx.compose.ui.focus.b) uVar.getFocusOwner()).a(i5, false, false)) {
                            Boolean boolC2 = ((androidx.compose.ui.focus.b) uVar.getFocusOwner()).c(i5, null, new n(bVar, 3));
                            return Boolean.valueOf(boolC2 != null ? boolC2.booleanValue() : true);
                        }
                        return Boolean.TRUE;
                    }
                }
                return Boolean.FALSE;
            case 1:
                a4.a aVar = (a4.a) obj;
                u uVar2 = this.f5301f;
                Handler handler = uVar2.getHandler();
                if ((handler != null ? handler.getLooper() : null) == Looper.myLooper()) {
                    aVar.a();
                } else {
                    Handler handler2 = uVar2.getHandler();
                    if (handler2 != null) {
                        handler2.post(new n2.n(aVar, 1));
                    }
                }
                return p3.n.f5026a;
            default:
                u uVar3 = this.f5301f;
                return new r0(uVar3, uVar3.getTextInputService(), (i4.v) obj);
        }
    }
}
