package z1;

import android.os.Build;
import android.text.StaticLayout;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p implements u {
    @Override // z1.u
    public StaticLayout a(v vVar) {
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(vVar.f7133a, 0, vVar.f7134b, vVar.f7135c, vVar.f7136d);
        builderObtain.setTextDirection(vVar.f7137e);
        builderObtain.setAlignment(vVar.f7138f);
        builderObtain.setMaxLines(vVar.f7139g);
        builderObtain.setEllipsize(vVar.f7140h);
        builderObtain.setEllipsizedWidth(vVar.f7141i);
        builderObtain.setLineSpacing(0.0f, 1.0f);
        builderObtain.setIncludePad(vVar.f7143k);
        builderObtain.setBreakStrategy(vVar.f7144l);
        builderObtain.setHyphenationFrequency(vVar.f7147o);
        builderObtain.setIndents(null, null);
        int i5 = Build.VERSION.SDK_INT;
        q.a(builderObtain, vVar.f7142j);
        if (i5 >= 28) {
            r.a(builderObtain, true);
        }
        if (i5 >= 33) {
            s.b(builderObtain, vVar.f7145m, vVar.f7146n);
        }
        return builderObtain.build();
    }
}
