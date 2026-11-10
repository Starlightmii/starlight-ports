package p2;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import androidx.lifecycle.e0;
import androidx.lifecycle.g0;
import androidx.lifecycle.p;
import androidx.lifecycle.q;
import androidx.lifecycle.v;
import androidx.lifecycle.x;
import b4.l;
import io.github.andrea_lyz.assistrestore.R;
import j.h0;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.WeakHashMap;
import p3.j;
import w2.f;
import w2.t;
import w2.u;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class b extends Activity implements v, f {
    private final h0 extraDataMap = new h0(0);
    private final x lifecycleRegistry = new x(this);

    /* JADX WARN: Code duplicated, block: B:22:0x0069  */
    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean zBooleanValue;
        l.e("event", keyEvent);
        View decorView = getWindow().getDecorView();
        l.d("getDecorView(...)", decorView);
        if (j.u(decorView, keyEvent)) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            return superDispatchKeyEvent(keyEvent);
        }
        onUserInteraction();
        Window window = getWindow();
        boolean z2 = false;
        if (window.hasFeature(8)) {
            ActionBar actionBar = getActionBar();
            if (keyEvent.getKeyCode() == 82 && actionBar != null) {
                if (!j.f5017a) {
                    try {
                        j.f5018b = actionBar.getClass().getMethod("onMenuKeyEvent", KeyEvent.class);
                    } catch (NoSuchMethodException unused) {
                    }
                    j.f5017a = true;
                }
                Method method = j.f5018b;
                if (method != null) {
                    try {
                        Object objInvoke = method.invoke(actionBar, keyEvent);
                        if (objInvoke == null) {
                            zBooleanValue = false;
                        } else {
                            zBooleanValue = ((Boolean) objInvoke).booleanValue();
                        }
                    } catch (IllegalAccessException | InvocationTargetException unused2) {
                    }
                } else {
                    zBooleanValue = false;
                }
                if (zBooleanValue) {
                    return true;
                }
            }
        }
        if (window.superDispatchKeyEvent(keyEvent)) {
            return true;
        }
        View decorView2 = window.getDecorView();
        int i5 = u.f6438a;
        if (Build.VERSION.SDK_INT < 28) {
            ArrayList arrayList = t.f6434d;
            t tVar = (t) decorView2.getTag(R.id.tag_unhandled_key_event_manager);
            if (tVar == null) {
                tVar = new t();
                tVar.f6435a = null;
                tVar.f6436b = null;
                tVar.f6437c = null;
                decorView2.setTag(R.id.tag_unhandled_key_event_manager, tVar);
            }
            if (keyEvent.getAction() == 0) {
                WeakHashMap weakHashMap = tVar.f6435a;
                if (weakHashMap != null) {
                    weakHashMap.clear();
                }
                ArrayList arrayList2 = t.f6434d;
                if (!arrayList2.isEmpty()) {
                    synchronized (arrayList2) {
                        try {
                            if (tVar.f6435a == null) {
                                tVar.f6435a = new WeakHashMap();
                            }
                            for (int size = arrayList2.size() - 1; size >= 0; size--) {
                                ArrayList arrayList3 = t.f6434d;
                                View view = (View) ((WeakReference) arrayList3.get(size)).get();
                                if (view == null) {
                                    arrayList3.remove(size);
                                } else {
                                    tVar.f6435a.put(view, Boolean.TRUE);
                                    for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
                                        tVar.f6435a.put((View) parent, Boolean.TRUE);
                                    }
                                }
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
            }
            View viewA = tVar.a(decorView2);
            if (keyEvent.getAction() == 0) {
                int keyCode = keyEvent.getKeyCode();
                if (viewA != null && !KeyEvent.isModifierKey(keyCode)) {
                    if (tVar.f6436b == null) {
                        tVar.f6436b = new SparseArray();
                    }
                    tVar.f6436b.put(keyCode, new WeakReference(viewA));
                }
            }
            if (viewA != null) {
                z2 = true;
            }
        }
        if (z2) {
            return true;
        }
        return keyEvent.dispatch(this, decorView2 != null ? decorView2.getKeyDispatcherState() : null, this);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        l.e("event", keyEvent);
        View decorView = getWindow().getDecorView();
        l.d("getDecorView(...)", decorView);
        if (j.u(decorView, keyEvent)) {
            return true;
        }
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    @p3.a
    public <T extends a> T getExtraData(Class<T> cls) {
        l.e("extraDataClass", cls);
        if (this.extraDataMap.get(cls) == null) {
            return null;
        }
        throw new ClassCastException();
    }

    public q getLifecycle() {
        return this.lifecycleRegistry;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i5 = g0.f251e;
        e0.b(this);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        l.e("outState", bundle);
        x xVar = this.lifecycleRegistry;
        xVar.d("setCurrentState");
        xVar.f(p.f276f);
        super.onSaveInstanceState(bundle);
    }

    @p3.a
    public void putExtraData(a aVar) {
        l.e("extraData", aVar);
        throw null;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x001b  */
    /* JADX WARN: Code duplicated, block: B:26:0x003f  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final boolean shouldDumpInternalState(String[] strArr) {
        boolean z2 = false;
        if (strArr != null && strArr.length != 0) {
            String str = strArr[0];
            switch (str.hashCode()) {
                case -645125871:
                    if (str.equals("--translation") && Build.VERSION.SDK_INT >= 31) {
                        z2 = true;
                    }
                    break;
                case 100470631:
                    if (str.equals("--dump-dumpable")) {
                        if (Build.VERSION.SDK_INT >= 33) {
                            z2 = true;
                        }
                    }
                    break;
                case 472614934:
                    if (str.equals("--list-dumpables")) {
                        if (Build.VERSION.SDK_INT >= 33) {
                            z2 = true;
                        }
                    }
                    break;
                case 1159329357:
                    if (str.equals("--contentcapture") && Build.VERSION.SDK_INT >= 29) {
                        z2 = true;
                    }
                    break;
                case 1455016274:
                    if (str.equals("--autofill")) {
                        z2 = true;
                    }
                    break;
            }
        }
        return !z2;
    }

    @Override // w2.f
    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        l.e("event", keyEvent);
        return super.dispatchKeyEvent(keyEvent);
    }
}
