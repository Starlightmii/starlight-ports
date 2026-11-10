package w2;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import io.github.andrea_lyz.assistrestore.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ArrayList f6434d = new ArrayList();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakHashMap f6435a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public SparseArray f6436b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public WeakReference f6437c;

    public final View a(View view) {
        int size;
        WeakHashMap weakHashMap = this.f6435a;
        if (weakHashMap == null || !weakHashMap.containsKey(view)) {
            return null;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View viewA = a(viewGroup.getChildAt(childCount));
                if (viewA != null) {
                    return viewA;
                }
            }
        }
        ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_unhandled_key_listeners);
        if (arrayList == null || (size = arrayList.size() - 1) < 0) {
            return null;
        }
        arrayList.get(size).getClass();
        throw new ClassCastException();
    }
}
