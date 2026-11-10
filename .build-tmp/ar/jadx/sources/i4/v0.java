package i4;

import android.graphics.Rect;
import android.view.FocusFinder;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class v0 extends b4.j implements a4.c {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ int f2778k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v0(int i5, Object obj, Class cls, String str, String str2, int i6, int i7, int i8) {
        super(i5, obj, cls, str, str2, i6, i7);
        this.f2778k = i8;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0076  */
    /* JADX WARN: Code duplicated, block: B:26:0x0086  */
    /* JADX WARN: Code duplicated, block: B:68:0x0139  */
    @Override // a4.c
    public final Object c(Object obj) {
        boolean zD;
        e2.a aVar;
        int iA;
        Integer numValueOf;
        switch (this.f2778k) {
            case 0:
                ((w0) this.f681e).k((Throwable) obj);
                return p3.n.f5026a;
            case 1:
                a4.a aVar2 = (a4.a) obj;
                h0.e eVar = ((q1.u) this.f681e).f5406s0;
                if (!eVar.h(aVar2)) {
                    eVar.b(aVar2);
                }
                return p3.n.f5026a;
            case 2:
                int i5 = ((w0.b) obj).f6225a;
                q1.u uVar = (q1.u) this.f681e;
                uVar.getClass();
                if (i5 == 7 || i5 == 8) {
                    zD = false;
                } else {
                    Integer numH = w0.d.H(i5);
                    if (numH == null) {
                        throw new IllegalStateException("Invalid focus direction");
                    }
                    int iIntValue = numH.intValue();
                    x0.d dVarU = uVar.u();
                    Rect rectU = dVarU != null ? y0.l0.u(dVarU) : null;
                    FocusFinder focusFinder = FocusFinder.getInstance();
                    View viewFindNextFocus = rectU == null ? focusFinder.findNextFocus(uVar, uVar.findFocus(), iIntValue) : focusFinder.findNextFocusFromRect(uVar, rectU, iIntValue);
                    if (viewFindNextFocus != null) {
                        zD = w0.d.D(viewFindNextFocus, Integer.valueOf(iIntValue), rectU);
                    } else {
                        zD = false;
                    }
                }
                return Boolean.valueOf(zD);
            default:
                KeyEvent keyEvent = ((h1.b) obj).f2604a;
                v.x0 x0Var = (v.x0) this.f681e;
                z.j0 j0Var = x0Var.f6136f;
                boolean z2 = x0Var.f6134d;
                boolean z3 = true;
                if (keyEvent.getAction() != 0 || Character.isISOControl(keyEvent.getUnicodeChar())) {
                    aVar = null;
                } else {
                    v.c0 c0Var = x0Var.f6139i;
                    c0Var.getClass();
                    int unicodeChar = keyEvent.getUnicodeChar();
                    if ((Integer.MIN_VALUE & unicodeChar) != 0) {
                        c0Var.f5867a = Integer.valueOf(unicodeChar & Integer.MAX_VALUE);
                        numValueOf = null;
                    } else {
                        Integer num = c0Var.f5867a;
                        if (num != null) {
                            c0Var.f5867a = null;
                            int deadChar = KeyCharacterMap.getDeadChar(num.intValue(), unicodeChar);
                            numValueOf = Integer.valueOf(deadChar);
                            if (deadChar == 0) {
                                numValueOf = null;
                            }
                            if (numValueOf == null) {
                                numValueOf = Integer.valueOf(unicodeChar);
                            }
                        } else {
                            numValueOf = Integer.valueOf(unicodeChar);
                        }
                    }
                    if (numValueOf != null) {
                        aVar = new e2.a(new StringBuilder().appendCodePoint(numValueOf.intValue()).toString(), 1);
                    } else {
                        aVar = null;
                    }
                }
                if (aVar != null) {
                    if (z2) {
                        x0Var.a(p3.j.G(aVar));
                        j0Var.f6971a = null;
                    } else {
                        z3 = false;
                    }
                } else if (h1.c.y(keyEvent) != 2 || (iA = x0Var.f6140j.a(keyEvent)) == 0) {
                    z3 = false;
                } else {
                    boolean z4 = true;
                    switch (iA) {
                        case 1:
                        case 2:
                        case 3:
                        case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 38:
                        case 39:
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                            z4 = false;
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 44:
                        case 45:
                        case 46:
                        case 47:
                        case 48:
                            if (z4 && !z2) {
                                z3 = false;
                            } else {
                                b4.s sVar = new b4.s();
                                sVar.f693d = true;
                                n.i1 i1Var = new n.i1(iA, x0Var, sVar);
                                e2.x xVar = x0Var.f6133c;
                                z.b0 b0Var = new z.b0(xVar, x0Var.f6137g, x0Var.f6131a.d(), j0Var);
                                i1Var.c(b0Var);
                                if (!y1.g0.a(b0Var.f6904f, xVar.f2063b) || !b4.l.a(b0Var.f6905g, xVar.f2062a)) {
                                    x0Var.f6141k.c(e2.x.a(xVar, b0Var.f6905g, b0Var.f6904f, 4));
                                }
                                v.f1 f1Var = x0Var.f6138h;
                                if (f1Var != null) {
                                    f1Var.f5925e = true;
                                }
                                z3 = sVar.f693d;
                            }
                            break;
                        default:
                            throw null;
                    }
                }
                return Boolean.valueOf(z3);
        }
    }
}
