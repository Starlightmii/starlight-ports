package j3;

import android.os.Bundle;
import androidx.lifecycle.l0;
import androidx.lifecycle.o;
import androidx.lifecycle.r0;
import androidx.lifecycle.t;
import androidx.lifecycle.v;
import androidx.lifecycle.v0;
import b.r;
import b4.l;
import io.github.andrea_lyz.assistrestore.ui.MainActivity;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b implements t {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3339d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final MainActivity f3340e;

    public /* synthetic */ b(MainActivity mainActivity, int i5) {
        this.f3339d = i5;
        this.f3340e = mainActivity;
    }

    @Override // androidx.lifecycle.t
    public final void b(v vVar, o oVar) {
        switch (this.f3339d) {
            case 0:
                if (oVar != o.ON_CREATE) {
                    throw new AssertionError("Next event must be ON_CREATE");
                }
                vVar.getLifecycle().b(this);
                MainActivity mainActivity = this.f3340e;
                Bundle bundleA = mainActivity.getSavedStateRegistry().a("androidx.savedstate.Restarter");
                if (bundleA == null) {
                    return;
                }
                ArrayList<String> stringArrayList = bundleA.getStringArrayList("classes_to_restore");
                if (stringArrayList == null) {
                    throw new IllegalStateException("SavedState with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
                }
                int size = stringArrayList.size();
                int i5 = 0;
                while (i5 < size) {
                    String str = stringArrayList.get(i5);
                    i5++;
                    String str2 = str;
                    try {
                        Class<? extends U> clsAsSubclass = Class.forName(str2, false, b.class.getClassLoader()).asSubclass(c.class);
                        l.b(clsAsSubclass);
                        try {
                            Constructor declaredConstructor = clsAsSubclass.getDeclaredConstructor(null);
                            declaredConstructor.setAccessible(true);
                            try {
                                Object objNewInstance = declaredConstructor.newInstance(null);
                                l.b(objNewInstance);
                                v0 viewModelStore = mainActivity.getViewModelStore();
                                e savedStateRegistry = mainActivity.getSavedStateRegistry();
                                viewModelStore.getClass();
                                LinkedHashMap linkedHashMap = viewModelStore.f293a;
                                for (String str3 : new HashSet(linkedHashMap.keySet())) {
                                    l.e("key", str3);
                                    r0 r0Var = (r0) linkedHashMap.get(str3);
                                    if (r0Var != null) {
                                        l0.a(r0Var, savedStateRegistry, mainActivity.getLifecycle());
                                    }
                                }
                                if (!new HashSet(linkedHashMap.keySet()).isEmpty()) {
                                    savedStateRegistry.c();
                                }
                            } catch (Exception e5) {
                                throw new RuntimeException("Failed to instantiate " + str2, e5);
                            }
                        } catch (NoSuchMethodException e6) {
                            throw new IllegalStateException("Class " + clsAsSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e6);
                        }
                    } catch (ClassNotFoundException e7) {
                        throw new RuntimeException("Class " + str2 + " wasn't found", e7);
                    }
                }
                return;
            default:
                MainActivity mainActivity2 = this.f3340e;
                r.access$ensureViewModelStore(mainActivity2);
                mainActivity2.getLifecycle().b(this);
                return;
        }
    }
}
