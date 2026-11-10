package s1;

import android.graphics.Rect;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import b4.l;
import v.t;
import x0.d;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends ActionMode.Callback2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f5768a;

    public a(b bVar) {
        this.f5768a = bVar;
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        b bVar = this.f5768a;
        bVar.getClass();
        l.b(menuItem);
        int itemId = menuItem.getItemId();
        if (itemId == 0) {
            t tVar = bVar.f5771c;
            if (tVar != null) {
                tVar.a();
            }
        } else if (itemId == 1) {
            t tVar2 = bVar.f5772d;
            if (tVar2 != null) {
                tVar2.a();
            }
        } else if (itemId == 2) {
            t tVar3 = bVar.f5773e;
            if (tVar3 != null) {
                tVar3.a();
            }
        } else {
            if (itemId != 3) {
                return false;
            }
            t tVar4 = bVar.f5774f;
            if (tVar4 != null) {
                tVar4.a();
            }
        }
        if (actionMode != null) {
            actionMode.finish();
        }
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        b bVar = this.f5768a;
        bVar.getClass();
        if (menu == null) {
            throw new IllegalArgumentException("onCreateActionMode requires a non-null menu");
        }
        if (actionMode == null) {
            throw new IllegalArgumentException("onCreateActionMode requires a non-null mode");
        }
        if (bVar.f5771c != null) {
            b.a(1, menu);
        }
        if (bVar.f5772d != null) {
            b.a(2, menu);
        }
        if (bVar.f5773e != null) {
            b.a(3, menu);
        }
        if (bVar.f5774f != null) {
            b.a(4, menu);
        }
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public final void onDestroyActionMode(ActionMode actionMode) {
        this.f5768a.f5769a.a();
    }

    @Override // android.view.ActionMode.Callback2
    public final void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
        d dVar = this.f5768a.f5770b;
        if (rect != null) {
            rect.set((int) dVar.f6534a, (int) dVar.f6535b, (int) dVar.f6536c, (int) dVar.f6537d);
        }
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        b bVar = this.f5768a;
        bVar.getClass();
        if (actionMode == null || menu == null) {
            return false;
        }
        b.b(menu, 1, bVar.f5771c);
        b.b(menu, 2, bVar.f5772d);
        b.b(menu, 3, bVar.f5773e);
        b.b(menu, 4, bVar.f5774f);
        return true;
    }
}
