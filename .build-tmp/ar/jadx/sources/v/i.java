package v;

import android.R;
import android.content.Context;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5946e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(int i5) {
        super(2);
        this.f5946e = i5;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        int i5;
        ((Number) obj2).intValue();
        f0.v vVar = (f0.v) ((f0.p) obj);
        vVar.V(-1451087197);
        int i6 = this.f5946e;
        if (i6 == 1) {
            i5 = R.string.cut;
        } else if (i6 == 2) {
            i5 = R.string.copy;
        } else if (i6 == 3) {
            i5 = R.string.paste;
        } else {
            if (i6 != 4) {
                throw null;
            }
            i5 = R.string.selectAll;
        }
        vVar.j(AndroidCompositionLocals_androidKt.f203a);
        String string = ((Context) vVar.j(AndroidCompositionLocals_androidKt.f204b)).getResources().getString(i5);
        vVar.p(false);
        return string;
    }
}
