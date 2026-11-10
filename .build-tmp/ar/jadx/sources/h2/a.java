package h2;

import android.os.LocaleList;
import android.text.style.LocaleSpan;
import g2.f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import q3.m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f2615a = new a();

    public final Object a(f2.b bVar) {
        ArrayList arrayList = new ArrayList(m.S(bVar));
        Iterator it = bVar.f2446d.iterator();
        while (it.hasNext()) {
            arrayList.add(((f2.a) it.next()).f2444a);
        }
        Locale[] localeArr = (Locale[]) arrayList.toArray(new Locale[0]);
        return new LocaleSpan(new LocaleList((Locale[]) Arrays.copyOf(localeArr, localeArr.length)));
    }

    public final void b(f fVar, f2.b bVar) {
        ArrayList arrayList = new ArrayList(m.S(bVar));
        Iterator it = bVar.f2446d.iterator();
        while (it.hasNext()) {
            arrayList.add(((f2.a) it.next()).f2444a);
        }
        Locale[] localeArr = (Locale[]) arrayList.toArray(new Locale[0]);
        fVar.setTextLocales(new LocaleList((Locale[]) Arrays.copyOf(localeArr, localeArr.length)));
    }
}
