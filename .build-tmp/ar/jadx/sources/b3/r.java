package b3;

import android.text.TextUtils;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r implements p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f633d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f634e;

    public /* synthetic */ r(String str, int i5) {
        this.f633d = i5;
        this.f634e = str;
    }

    @Override // b3.p
    public Object a() {
        return this;
    }

    @Override // b3.p
    public boolean j(CharSequence charSequence, int i5, int i6, x xVar) {
        if (!TextUtils.equals(charSequence.subSequence(i5, i6), this.f634e)) {
            return true;
        }
        xVar.f663c = (xVar.f663c & 3) | 4;
        return false;
    }

    public String toString() {
        switch (this.f633d) {
            case 1:
                return "<" + this.f634e + '>';
            default:
                return super.toString();
        }
    }
}
