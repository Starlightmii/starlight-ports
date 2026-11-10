package q1;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import io.github.andrea_lyz.assistrestore.R;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class c3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final LinkedHashMap f5147a = new LinkedHashMap();

    public static final l4.b0 a(Context context) {
        l4.b0 b0Var;
        LinkedHashMap linkedHashMap = f5147a;
        synchronized (linkedHashMap) {
            try {
                Object objF = linkedHashMap.get(context);
                if (objF == null) {
                    ContentResolver contentResolver = context.getContentResolver();
                    Uri uriFor = Settings.Global.getUriFor("animator_duration_scale");
                    k4.c cVarA = k4.j.a(-1, 6, null);
                    a1.d dVar = new a1.d(new f0.d3(contentResolver, uriFor, new b3(cVarA, p3.j.p(Looper.getMainLooper())), cVarA, context, null));
                    i4.i1 i1Var = new i4.i1(null);
                    p4.e eVar = i4.e0.f2727a;
                    objF = l4.u.f(dVar, new n4.c(p3.p.A(i1Var, n4.m.f4303a)), new l4.a0(), Float.valueOf(Settings.Global.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f)));
                    linkedHashMap.put(context, objF);
                }
                b0Var = (l4.b0) objF;
            } catch (Throwable th) {
                throw th;
            }
        }
        return b0Var;
    }

    public static final f0.b0 b(View view) {
        Object tag = view.getTag(R.id.androidx_compose_ui_view_composition_context);
        if (tag instanceof f0.b0) {
            return (f0.b0) tag;
        }
        return null;
    }
}
