package m4;

import l4.b0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u extends l4.t implements b0 {
    @Override // l4.b0
    public final Object getValue() {
        Integer numValueOf;
        synchronized (this) {
            Object[] objArr = this.f3581k;
            b4.l.b(objArr);
            numValueOf = Integer.valueOf(((Number) objArr[((int) ((this.f3582l + ((long) ((int) ((o() + ((long) this.f3584n)) - this.f3582l)))) - 1)) & (objArr.length - 1)]).intValue());
        }
        return numValueOf;
    }

    public final void w(int i5) {
        synchronized (this) {
            Object[] objArr = this.f3581k;
            b4.l.b(objArr);
            q(Integer.valueOf(((Number) objArr[((int) ((this.f3582l + ((long) ((int) ((o() + ((long) this.f3584n)) - this.f3582l)))) - 1)) & (objArr.length - 1)]).intValue() + i5));
        }
    }
}
