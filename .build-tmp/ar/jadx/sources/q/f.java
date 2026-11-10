package q;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Size;
import android.util.SizeF;
import android.view.Display;
import android.view.RoundedCorner;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import f0.i1;
import f0.v;
import io.github.andrea_lyz.assistrestore.R;
import java.io.Serializable;
import java.util.ArrayList;
import n1.v0;
import p1.t;
import q3.s;
import y0.q0;
import y1.e0;
import y1.g0;
import y1.h0;
import z.p;
import z.q;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class f {
    public static y1.a a(String str, h0 h0Var, long j5, k2.b bVar, d2.d dVar, int i5, int i6) {
        s sVar = s.f5477d;
        return new y1.a(new g2.d(str, h0Var, sVar, sVar, dVar, bVar), i5, false, j5);
    }

    public static final z.n b(w0.s sVar, z.j jVar) {
        int iC = sVar.c();
        e2.l lVar = (e2.l) sVar.f6268d;
        boolean z2 = iC == 1;
        return new z.n(f(lVar, z2, true, jVar), f(lVar, z2, false, jVar), z2);
    }

    public static final boolean c(u0.c cVar, long j5) {
        if (!cVar.f5681d.f5693p) {
            return false;
        }
        t tVar = p1.f.s(cVar).f4801y.f4969b;
        if (!tVar.M.f5693p) {
            return false;
        }
        long j6 = tVar.f4132f;
        long jZ = tVar.Z(0L);
        float fD = x0.c.d(jZ);
        float fE = x0.c.e(jZ);
        float f5 = ((int) (j6 >> 32)) + fD;
        float f6 = ((int) (j6 & 4294967295L)) + fE;
        float fD2 = x0.c.d(j5);
        if (fD > fD2 || fD2 > f5) {
            return false;
        }
        float fE2 = x0.c.e(j5);
        return fE <= fE2 && fE2 <= f6;
    }

    public static final String d(Object[] objArr, int i5, int i6, q3.f fVar) {
        StringBuilder sb = new StringBuilder((i6 * 3) + 2);
        sb.append("[");
        for (int i7 = 0; i7 < i6; i7++) {
            if (i7 > 0) {
                sb.append(", ");
            }
            Object obj = objArr[i5 + i7];
            if (obj == fVar) {
                sb.append("(this Collection)");
            } else {
                sb.append(obj);
            }
        }
        sb.append("]");
        String string = sb.toString();
        b4.l.d("toString(...)", string);
        return string;
    }

    public static final z.m e(w0.s sVar, e2.l lVar, z.m mVar) {
        int i5 = lVar.f2034c;
        int i6 = lVar.f2033b;
        boolean z2 = sVar.f6266b;
        int i7 = z2 ? i6 : i5;
        e0 e0Var = (e0) lVar.f2036e;
        int i8 = lVar.f2035d;
        p3.d dVarD = h1.c.D(new q(lVar, i7));
        p3.d dVarD2 = h1.c.D(new p(lVar, i7, z2 ? i5 : i6, sVar, dVarD));
        if (1 != mVar.f6981c) {
            return (z.m) ((p3.o) dVarD2).getValue();
        }
        if (i7 == i8) {
            return mVar;
        }
        if (((Number) ((p3.o) dVarD).getValue()).intValue() != e0Var.e(i8)) {
            return (z.m) ((p3.o) dVarD2).getValue();
        }
        int i9 = mVar.f6980b;
        long jK = e0Var.k(i9);
        if (i8 != -1) {
            if (i7 != i8) {
                if (!((i6 >= i5 && i6 > i5) ^ z2)) {
                }
            }
            return lVar.a(i7);
        }
        int i10 = g0.f6769c;
        return (i9 == ((int) (jK >> 32)) || i9 == ((int) (4294967295L & jK))) ? (z.m) ((p3.o) dVarD2).getValue() : lVar.a(i7);
    }

    public static final z.m f(e2.l lVar, boolean z2, boolean z3, z.j jVar) {
        long j5;
        long jA = jVar.a(lVar, z3 ? lVar.f2033b : lVar.f2034c);
        if (z2 ^ z3) {
            int i5 = g0.f6769c;
            j5 = jA >> 32;
        } else {
            int i6 = g0.f6769c;
            j5 = 4294967295L & jA;
        }
        return lVar.a((int) j5);
    }

    public static final Bundle g(p3.f... fVarArr) {
        Bundle bundle = new Bundle(fVarArr.length);
        for (p3.f fVar : fVarArr) {
            String str = (String) fVar.f5014d;
            Object obj = fVar.f5015e;
            if (obj == null) {
                bundle.putString(str, null);
            } else if (obj instanceof Boolean) {
                bundle.putBoolean(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Byte) {
                bundle.putByte(str, ((Number) obj).byteValue());
            } else if (obj instanceof Character) {
                bundle.putChar(str, ((Character) obj).charValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Number) obj).doubleValue());
            } else if (obj instanceof Float) {
                bundle.putFloat(str, ((Number) obj).floatValue());
            } else if (obj instanceof Integer) {
                bundle.putInt(str, ((Number) obj).intValue());
            } else if (obj instanceof Long) {
                bundle.putLong(str, ((Number) obj).longValue());
            } else if (obj instanceof Short) {
                bundle.putShort(str, ((Number) obj).shortValue());
            } else if (obj instanceof Bundle) {
                bundle.putBundle(str, (Bundle) obj);
            } else if (obj instanceof CharSequence) {
                bundle.putCharSequence(str, (CharSequence) obj);
            } else if (obj instanceof Parcelable) {
                bundle.putParcelable(str, (Parcelable) obj);
            } else if (obj instanceof boolean[]) {
                bundle.putBooleanArray(str, (boolean[]) obj);
            } else if (obj instanceof byte[]) {
                bundle.putByteArray(str, (byte[]) obj);
            } else if (obj instanceof char[]) {
                bundle.putCharArray(str, (char[]) obj);
            } else if (obj instanceof double[]) {
                bundle.putDoubleArray(str, (double[]) obj);
            } else if (obj instanceof float[]) {
                bundle.putFloatArray(str, (float[]) obj);
            } else if (obj instanceof int[]) {
                bundle.putIntArray(str, (int[]) obj);
            } else if (obj instanceof long[]) {
                bundle.putLongArray(str, (long[]) obj);
            } else if (obj instanceof short[]) {
                bundle.putShortArray(str, (short[]) obj);
            } else if (obj instanceof Object[]) {
                Class<?> componentType = obj.getClass().getComponentType();
                b4.l.b(componentType);
                if (Parcelable.class.isAssignableFrom(componentType)) {
                    bundle.putParcelableArray(str, (Parcelable[]) obj);
                } else if (String.class.isAssignableFrom(componentType)) {
                    bundle.putStringArray(str, (String[]) obj);
                } else if (CharSequence.class.isAssignableFrom(componentType)) {
                    bundle.putCharSequenceArray(str, (CharSequence[]) obj);
                } else {
                    if (!Serializable.class.isAssignableFrom(componentType)) {
                        throw new IllegalArgumentException("Illegal value array type " + componentType.getCanonicalName() + " for key \"" + str + '\"');
                    }
                    bundle.putSerializable(str, (Serializable) obj);
                }
            } else if (obj instanceof Serializable) {
                bundle.putSerializable(str, (Serializable) obj);
            } else if (obj instanceof IBinder) {
                bundle.putBinder(str, (IBinder) obj);
            } else if (obj instanceof Size) {
                bundle.putSize(str, (Size) obj);
            } else {
                if (!(obj instanceof SizeF)) {
                    throw new IllegalArgumentException("Illegal value type " + obj.getClass().getCanonicalName() + " for key \"" + str + '\"');
                }
                bundle.putSizeF(str, (SizeF) obj);
            }
        }
        return bundle;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [q3.s] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.Collection, java.util.List] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.ArrayList] */
    public static final boolean h(ArrayList arrayList) {
        ?? arrayList2;
        long j5;
        if (arrayList.size() >= 2) {
            if (arrayList.size() == 0 || arrayList.size() == 1) {
                arrayList2 = s.f5477d;
            } else {
                arrayList2 = new ArrayList();
                Object obj = arrayList.get(0);
                int iZ = p3.j.z(arrayList);
                int i5 = 0;
                while (i5 < iZ) {
                    i5++;
                    Object obj2 = arrayList.get(i5);
                    w1.n nVar = (w1.n) obj2;
                    w1.n nVar2 = (w1.n) obj;
                    arrayList2.add(new x0.c(p3.j.b(Math.abs(x0.c.d(nVar2.e().a()) - x0.c.d(nVar.e().a())), Math.abs(x0.c.e(nVar2.e().a()) - x0.c.e(nVar.e().a())))));
                    obj = obj2;
                }
            }
            if (arrayList2.size() == 1) {
                j5 = ((x0.c) q3.l.W(arrayList2)).f6532a;
            } else {
                if (arrayList2.isEmpty()) {
                    throw new UnsupportedOperationException("Empty collection can't be reduced.");
                }
                Object objW = q3.l.W(arrayList2);
                int iZ2 = p3.j.z(arrayList2);
                if (1 <= iZ2) {
                    int i6 = 1;
                    while (true) {
                        objW = new x0.c(x0.c.h(((x0.c) objW).f6532a, ((x0.c) arrayList2.get(i6)).f6532a));
                        if (i6 == iZ2) {
                            break;
                        }
                        i6++;
                    }
                }
                j5 = ((x0.c) objW).f6532a;
            }
            if (x0.c.e(j5) >= x0.c.d(j5)) {
                return false;
            }
        }
        return true;
    }

    public static final z.m i(z.m mVar, e2.l lVar, int i5) {
        return new z.m(((e0) lVar.f2036e).a(i5), i5, mVar.f6981c);
    }

    public static final r0.p j(r0.p pVar, q0 q0Var) {
        return androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, q0Var, true, 124927);
    }

    public static final r0.p k(r0.p pVar) {
        return androidx.compose.ui.graphics.a.b(pVar, 0.0f, 0.0f, 0.0f, 0.0f, null, true, 126975);
    }

    public static final i1 l(j jVar, f0.p pVar, int i5) {
        v vVar = (v) pVar;
        Object objK = vVar.K();
        f0.h hVar = f0.o.f2281a;
        if (objK == hVar) {
            objK = f0.l.s(Boolean.FALSE);
            vVar.f0(objK);
        }
        i1 i1Var = (i1) objK;
        boolean z2 = (((i5 & 14) ^ 6) > 4 && vVar.f(jVar)) || (i5 & 6) == 4;
        Object objK2 = vVar.K();
        if (z2 || objK2 == hVar) {
            objK2 = new b0.t(jVar, i1Var, null, 12);
            vVar.f0(objK2);
        }
        f0.l.e((a4.e) objK2, vVar, jVar);
        return i1Var;
    }

    public static final boolean m(x0.d dVar, float f5, float f6) {
        float f7 = dVar.f6534a;
        if (f5 > dVar.f6536c || f7 > f5) {
            return false;
        }
        return f6 <= dVar.f6537d && dVar.f6535b <= f6;
    }

    public static final ViewParent n(View view) {
        ViewParent parent = view.getParent();
        if (parent != null) {
            return parent;
        }
        Object tag = view.getTag(R.id.view_tree_disjoint_parent);
        if (tag instanceof ViewParent) {
            return (ViewParent) tag;
        }
        return null;
    }

    public static w2.l o(Display display, int i5) {
        RoundedCorner roundedCorner;
        int i6;
        if (Build.VERSION.SDK_INT < 31 || (roundedCorner = display.getRoundedCorner(i5)) == null) {
            return null;
        }
        int position = roundedCorner.getPosition();
        if (position != 0) {
            i6 = 1;
            if (position != 1) {
                i6 = 2;
                if (position != 2) {
                    i6 = 3;
                    if (position != 3) {
                        throw new IllegalArgumentException("Invalid position: " + position);
                    }
                }
            }
        } else {
            i6 = 0;
        }
        return new w2.l(i6, roundedCorner.getRadius(), roundedCorner.getCenter());
    }

    public static int p(int i5) {
        if (i5 == 1) {
            return 0;
        }
        if (i5 == 2) {
            return 1;
        }
        if (i5 == 4) {
            return 2;
        }
        if (i5 == 8) {
            return 3;
        }
        if (i5 == 16) {
            return 4;
        }
        if (i5 == 32) {
            return 5;
        }
        if (i5 == 64) {
            return 6;
        }
        if (i5 == 128) {
            return 7;
        }
        if (i5 == 256) {
            return 8;
        }
        if (i5 == 512) {
            return 9;
        }
        throw new IllegalArgumentException("type needs to be >= FIRST and <= LAST, type=" + i5);
    }

    public static final boolean q(x0.e eVar) {
        long j5 = eVar.f6542e;
        long j6 = eVar.f6545h;
        long j7 = eVar.f6544g;
        long j8 = eVar.f6543f;
        float fB = x0.a.b(j5);
        long j9 = eVar.f6542e;
        return fB == x0.a.c(j9) && x0.a.b(j9) == x0.a.b(j8) && x0.a.b(j9) == x0.a.c(j8) && x0.a.b(j9) == x0.a.b(j7) && x0.a.b(j9) == x0.a.c(j7) && x0.a.b(j9) == x0.a.b(j6) && x0.a.b(j9) == x0.a.c(j6);
    }

    public static final void r(Object[] objArr, int i5, int i6) {
        b4.l.e("<this>", objArr);
        while (i5 < i6) {
            objArr[i5] = null;
            i5++;
        }
    }

    public static void s(EditorInfo editorInfo, CharSequence charSequence) {
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 30) {
            t2.b.f(editorInfo, charSequence);
            return;
        }
        charSequence.getClass();
        if (i5 >= 30) {
            t2.b.f(editorInfo, charSequence);
            return;
        }
        int i6 = editorInfo.initialSelStart;
        int i7 = editorInfo.initialSelEnd;
        int i8 = i6 > i7 ? i7 : i6;
        if (i6 <= i7) {
            i6 = i7;
        }
        int length = charSequence.length();
        if (i8 < 0 || i6 > length) {
            u(editorInfo, null, 0, 0);
            return;
        }
        int i9 = editorInfo.inputType & 4095;
        if (i9 == 129 || i9 == 225 || i9 == 18) {
            u(editorInfo, null, 0, 0);
            return;
        }
        if (length <= 2048) {
            u(editorInfo, charSequence, i8, i6);
            return;
        }
        int i10 = i6 - i8;
        int i11 = i10 > 1024 ? 0 : i10;
        int i12 = 2048 - i11;
        int iMin = Math.min(charSequence.length() - i6, i12 - Math.min(i8, (int) (((double) i12) * 0.8d)));
        int iMin2 = Math.min(i8, i12 - iMin);
        int i13 = i8 - iMin2;
        if (Character.isLowSurrogate(charSequence.charAt(i13))) {
            i13++;
            iMin2--;
        }
        if (Character.isHighSurrogate(charSequence.charAt((i6 + iMin) - 1))) {
            iMin--;
        }
        int i14 = iMin2 + i11;
        u(editorInfo, i11 != i10 ? TextUtils.concat(charSequence.subSequence(i13, i13 + iMin2), charSequence.subSequence(i6, iMin + i6)) : charSequence.subSequence(i13, i14 + iMin + i13), iMin2, i14);
    }

    public static void t(EditorInfo editorInfo, boolean z2) {
        if (Build.VERSION.SDK_INT >= 35) {
            y2.a.a(editorInfo, z2);
        }
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putBoolean("androidx.core.view.inputmethod.EditorInfoCompat.STYLUS_HANDWRITING_ENABLED", z2);
    }

    public static void u(EditorInfo editorInfo, CharSequence charSequence, int i5, int i6) {
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putCharSequence("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT", charSequence != null ? new SpannableStringBuilder(charSequence) : null);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD", i5);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END", i6);
    }

    public static final String v(float f5) {
        if (Float.isNaN(f5)) {
            return "NaN";
        }
        if (Float.isInfinite(f5)) {
            return f5 < 0.0f ? "-Infinity" : "Infinity";
        }
        int iMax = Math.max(1, 0);
        float fPow = (float) Math.pow(10.0f, iMax);
        float f6 = f5 * fPow;
        int i5 = (int) f6;
        if (f6 - i5 >= 0.5f) {
            i5++;
        }
        float f7 = i5 / fPow;
        return iMax > 0 ? String.valueOf(f7) : String.valueOf((int) f7);
    }

    public static final x0.d w(n1.p pVar) {
        x0.d dVarE = v0.e(pVar);
        long jE = pVar.e(p3.j.b(dVarE.f6534a, dVarE.f6535b));
        long jE2 = pVar.e(p3.j.b(dVarE.f6536c, dVarE.f6537d));
        return new x0.d(x0.c.d(jE), x0.c.e(jE), x0.c.d(jE2), x0.c.e(jE2));
    }
}
