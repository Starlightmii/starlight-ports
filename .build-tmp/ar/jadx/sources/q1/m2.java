package q1;

import android.view.ActionMode;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m2 f5265a = new m2();

    public final void a(ActionMode actionMode) {
        actionMode.invalidateContentRect();
    }

    public final ActionMode b(View view, ActionMode.Callback callback, int i5) {
        return view.startActionMode(callback, i5);
    }
}
