package w2;

import android.view.DisplayCutout;
import android.view.WindowInsets;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class o0 extends n0 {
    public o0(z0 z0Var, WindowInsets windowInsets) {
        super(z0Var, windowInsets);
    }

    @Override // w2.v0
    public z0 a() {
        return z0.b(null, this.f6414c.consumeDisplayCutout());
    }

    @Override // w2.m0, w2.v0
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof o0)) {
            return false;
        }
        o0 o0Var = (o0) obj;
        return Objects.equals(this.f6414c, o0Var.f6414c) && Objects.equals(this.f6418g, o0Var.f6418g) && m0.K(this.f6419h, o0Var.f6419h);
    }

    @Override // w2.v0
    public c g() {
        DisplayCutout displayCutout = this.f6414c.getDisplayCutout();
        if (displayCutout == null) {
            return null;
        }
        return new c(displayCutout);
    }

    @Override // w2.v0
    public int hashCode() {
        return this.f6414c.hashCode();
    }
}
