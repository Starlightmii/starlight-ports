package q1;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Parcel;
import android.text.Annotation;
import android.text.SpannableString;
import android.util.Base64;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h implements f1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ClipboardManager f5215a;

    public h(Context context) {
        Object systemService = context.getSystemService("clipboard");
        b4.l.c("null cannot be cast to non-null type android.content.ClipboardManager", systemService);
        this.f5215a = (ClipboardManager) systemService;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00bb  */
    public final void a(y1.f fVar) {
        List listA = fVar.a();
        CharSequence charSequence = fVar.f6759d;
        if (!listA.isEmpty()) {
            SpannableString spannableString = new SpannableString(charSequence);
            a1.d dVar = new a1.d(18, false);
            dVar.f31e = Parcel.obtain();
            List listA2 = fVar.a();
            int size = listA2.size();
            int i5 = 0;
            while (i5 < size) {
                y1.d dVar2 = (y1.d) listA2.get(i5);
                y1.a0 a0Var = (y1.a0) dVar2.f6739a;
                int i6 = dVar2.f6740b;
                int i7 = dVar2.f6741c;
                ((Parcel) dVar.f31e).recycle();
                dVar.f31e = Parcel.obtain();
                j2.m mVar = a0Var.f6709a;
                long j5 = a0Var.f6720l;
                long j6 = a0Var.f6716h;
                long j7 = a0Var.f6710b;
                int i8 = i5;
                long jB = mVar.b();
                SpannableString spannableString2 = spannableString;
                List list = listA2;
                long j8 = y0.u.f6694g;
                if (!y0.u.c(jB, j8)) {
                    dVar.k((byte) 1);
                    ((Parcel) dVar.f31e).writeLong(a0Var.f6709a.b());
                }
                long j9 = k2.m.f3421c;
                int i9 = size;
                if (!k2.m.a(j7, j9)) {
                    dVar.k((byte) 2);
                    dVar.p(j7);
                }
                d2.k kVar = a0Var.f6711c;
                byte b5 = 3;
                if (kVar != null) {
                    dVar.k((byte) 3);
                    ((Parcel) dVar.f31e).writeInt(kVar.f1615d);
                }
                d2.i iVar = a0Var.f6712d;
                if (iVar != null) {
                    int i10 = iVar.f1610a;
                    dVar.k((byte) 4);
                    dVar.k((i10 != 0 && i10 == 1) ? (byte) 1 : (byte) 0);
                }
                d2.j jVar = a0Var.f6713e;
                if (jVar != null) {
                    int i11 = jVar.f1611a;
                    dVar.k((byte) 5);
                    if (i11 == 0) {
                        b5 = 0;
                    } else if (i11 == 1) {
                        b5 = 1;
                    } else if (i11 == 2) {
                        b5 = 2;
                    } else if (i11 != 3) {
                        b5 = 0;
                    }
                    dVar.k(b5);
                }
                String str = a0Var.f6715g;
                if (str != null) {
                    dVar.k((byte) 6);
                    ((Parcel) dVar.f31e).writeString(str);
                }
                if (!k2.m.a(j6, j9)) {
                    dVar.k((byte) 7);
                    dVar.p(j6);
                }
                j2.a aVar = a0Var.f6717i;
                if (aVar != null) {
                    float f5 = aVar.f3305a;
                    dVar.k((byte) 8);
                    dVar.m(f5);
                }
                j2.n nVar = a0Var.f6718j;
                if (nVar != null) {
                    dVar.k((byte) 9);
                    dVar.m(nVar.f3329a);
                    dVar.m(nVar.f3330b);
                }
                if (!y0.u.c(j5, j8)) {
                    dVar.k((byte) 10);
                    ((Parcel) dVar.f31e).writeLong(j5);
                }
                j2.j jVar2 = a0Var.f6721m;
                if (jVar2 != null) {
                    dVar.k((byte) 11);
                    ((Parcel) dVar.f31e).writeInt(jVar2.f3325a);
                }
                y0.p0 p0Var = a0Var.f6722n;
                if (p0Var != null) {
                    dVar.k((byte) 12);
                    ((Parcel) dVar.f31e).writeLong(p0Var.f6669a);
                    long j10 = p0Var.f6670b;
                    dVar.m(x0.c.d(j10));
                    dVar.m(x0.c.e(j10));
                    dVar.m(p0Var.f6671c);
                }
                spannableString2.setSpan(new Annotation("androidx.compose.text.SpanStyle", Base64.encodeToString(((Parcel) dVar.f31e).marshall(), 0)), i6, i7, 33);
                i5 = i8 + 1;
                spannableString = spannableString2;
                listA2 = list;
                size = i9;
            }
            charSequence = spannableString;
        }
        this.f5215a.setPrimaryClip(ClipData.newPlainText("plain text", charSequence));
    }
}
