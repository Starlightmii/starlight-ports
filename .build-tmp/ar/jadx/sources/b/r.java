package b;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Trace;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.lifecycle.m0;
import androidx.lifecycle.n0;
import androidx.lifecycle.r0;
import androidx.lifecycle.s0;
import androidx.lifecycle.t0;
import androidx.lifecycle.v0;
import androidx.lifecycle.w0;
import io.github.andrea_lyz.assistrestore.AssistConfig;
import io.github.andrea_lyz.assistrestore.R;
import io.github.andrea_lyz.assistrestore.ui.MainActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class r extends p2.b implements w0, androidx.lifecycle.k, j3.g, j0 {
    private static final String ACTIVITY_RESULT_TAG = "android:support:activity-result";
    private static final k Companion = new k();
    private v0 _viewModelStore;
    private final e.e activityResultRegistry;
    private int contentLayoutId;
    private final d.a contextAwareHelper = new d.a();
    private final p3.d defaultViewModelProviderFactory$delegate;
    private boolean dispatchingOnMultiWindowModeChanged;
    private boolean dispatchingOnPictureInPictureModeChanged;
    private final p3.d fullyDrawnReporter$delegate;
    private final w2.h menuHostHelper;
    private final AtomicInteger nextLocalRequestCode;
    private final p3.d onBackPressedDispatcher$delegate;
    private final CopyOnWriteArrayList<v2.a> onConfigurationChangedListeners;
    private final CopyOnWriteArrayList<v2.a> onMultiWindowModeChangedListeners;
    private final CopyOnWriteArrayList<v2.a> onNewIntentListeners;
    private final CopyOnWriteArrayList<v2.a> onPictureInPictureModeChangedListeners;
    private final CopyOnWriteArrayList<v2.a> onTrimMemoryListeners;
    private final CopyOnWriteArrayList<Runnable> onUserLeaveHintListeners;
    private final m reportFullyDrawnExecutor;
    private final j3.f savedStateRegistryController;

    public r() {
        j3.d dVar;
        final MainActivity mainActivity = (MainActivity) this;
        this.menuHostHelper = new w2.h(new e(mainActivity, 0));
        k3.b bVar = new k3.b(mainActivity, new m0(mainActivity, 1));
        this.savedStateRegistryController = new j3.f(bVar);
        this.reportFullyDrawnExecutor = new n(mainActivity);
        this.fullyDrawnReporter$delegate = h1.c.E(new p(mainActivity, 2));
        this.nextLocalRequestCode = new AtomicInteger();
        this.activityResultRegistry = new o();
        this.onConfigurationChangedListeners = new CopyOnWriteArrayList<>();
        this.onTrimMemoryListeners = new CopyOnWriteArrayList<>();
        this.onNewIntentListeners = new CopyOnWriteArrayList<>();
        this.onMultiWindowModeChangedListeners = new CopyOnWriteArrayList<>();
        this.onPictureInPictureModeChangedListeners = new CopyOnWriteArrayList<>();
        this.onUserLeaveHintListeners = new CopyOnWriteArrayList<>();
        if (getLifecycle() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        final int i5 = 0;
        getLifecycle().a(new androidx.lifecycle.t() { // from class: b.f
            @Override // androidx.lifecycle.t
            public final void b(androidx.lifecycle.v vVar, androidx.lifecycle.o oVar) {
                Window window;
                View viewPeekDecorView;
                switch (i5) {
                    case 0:
                        if (oVar == androidx.lifecycle.o.ON_STOP && (window = mainActivity.getWindow()) != null && (viewPeekDecorView = window.peekDecorView()) != null) {
                            viewPeekDecorView.cancelPendingInputEvents();
                            break;
                        }
                        break;
                    default:
                        r.b(mainActivity, vVar, oVar);
                        break;
                }
            }
        });
        final int i6 = 1;
        getLifecycle().a(new androidx.lifecycle.t() { // from class: b.f
            @Override // androidx.lifecycle.t
            public final void b(androidx.lifecycle.v vVar, androidx.lifecycle.o oVar) {
                Window window;
                View viewPeekDecorView;
                switch (i6) {
                    case 0:
                        if (oVar == androidx.lifecycle.o.ON_STOP && (window = mainActivity.getWindow()) != null && (viewPeekDecorView = window.peekDecorView()) != null) {
                            viewPeekDecorView.cancelPendingInputEvents();
                            break;
                        }
                        break;
                    default:
                        r.b(mainActivity, vVar, oVar);
                        break;
                }
            }
        });
        getLifecycle().a(new j3.b(mainActivity, i6));
        bVar.d();
        androidx.lifecycle.p pVar = ((androidx.lifecycle.x) getLifecycle()).f298c;
        if (pVar != androidx.lifecycle.p.f275e && pVar != androidx.lifecycle.p.f276f) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        k3.b bVar2 = getSavedStateRegistry().f3341a;
        synchronized (((androidx.lifecycle.k0) bVar2.f3432f)) {
            Iterator it = ((LinkedHashMap) bVar2.f3433g).entrySet().iterator();
            do {
                dVar = null;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                String str = (String) entry.getKey();
                j3.d dVar2 = (j3.d) entry.getValue();
                if (b4.l.a(str, "androidx.lifecycle.internal.SavedStateHandlesProvider")) {
                    dVar = dVar2;
                }
            } while (dVar == null);
        }
        if (dVar == null) {
            n0 n0Var = new n0(getSavedStateRegistry(), mainActivity);
            getSavedStateRegistry().b("androidx.lifecycle.internal.SavedStateHandlesProvider", n0Var);
            getLifecycle().a(new androidx.lifecycle.e(1, n0Var));
        }
        getSavedStateRegistry().b(ACTIVITY_RESULT_TAG, new g(0, mainActivity));
        addOnContextAvailableListener(new h(mainActivity));
        this.defaultViewModelProviderFactory$delegate = h1.c.E(new p(mainActivity, 0));
        this.onBackPressedDispatcher$delegate = h1.c.E(new p(mainActivity, 3));
    }

    public static void a(MainActivity mainActivity, Context context) {
        b4.l.e("it", context);
        Bundle bundleA = mainActivity.getSavedStateRegistry().a(ACTIVITY_RESULT_TAG);
        if (bundleA != null) {
            e.e eVar = ((r) mainActivity).activityResultRegistry;
            LinkedHashMap linkedHashMap = eVar.f1644b;
            LinkedHashMap linkedHashMap2 = eVar.f1643a;
            Bundle bundle = eVar.f1648f;
            ArrayList<Integer> integerArrayList = bundleA.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
            ArrayList<String> stringArrayList = bundleA.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
            if (stringArrayList == null || integerArrayList == null) {
                return;
            }
            ArrayList<String> stringArrayList2 = bundleA.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
            if (stringArrayList2 != null) {
                eVar.f1645c.addAll(stringArrayList2);
            }
            Bundle bundle2 = bundleA.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT");
            if (bundle2 != null) {
                bundle.putAll(bundle2);
            }
            int size = stringArrayList.size();
            for (int i5 = 0; i5 < size; i5++) {
                String str = stringArrayList.get(i5);
                if (linkedHashMap.containsKey(str)) {
                    Integer num = (Integer) linkedHashMap.remove(str);
                    if (!bundle.containsKey(str)) {
                        b4.z.b(linkedHashMap2).remove(num);
                    }
                }
                Integer num2 = integerArrayList.get(i5);
                b4.l.d("rcs[i]", num2);
                int iIntValue = num2.intValue();
                String str2 = stringArrayList.get(i5);
                b4.l.d("keys[i]", str2);
                String str3 = str2;
                linkedHashMap2.put(Integer.valueOf(iIntValue), str3);
                eVar.f1644b.put(str3, Integer.valueOf(iIntValue));
            }
        }
    }

    public static final void access$ensureViewModelStore(r rVar) {
        if (rVar._viewModelStore == null) {
            l lVar = (l) rVar.getLastNonConfigurationInstance();
            if (lVar != null) {
                rVar._viewModelStore = lVar.f352b;
            }
            if (rVar._viewModelStore == null) {
                rVar._viewModelStore = new v0();
            }
        }
    }

    public static void b(MainActivity mainActivity, androidx.lifecycle.v vVar, androidx.lifecycle.o oVar) {
        if (oVar == androidx.lifecycle.o.ON_DESTROY) {
            ((r) mainActivity).contextAwareHelper.f1506b = null;
            if (!mainActivity.isChangingConfigurations()) {
                LinkedHashMap linkedHashMap = mainActivity.getViewModelStore().f293a;
                Iterator it = linkedHashMap.values().iterator();
                while (it.hasNext()) {
                    ((r0) it.next()).a();
                }
                linkedHashMap.clear();
            }
            n nVar = (n) ((r) mainActivity).reportFullyDrawnExecutor;
            MainActivity mainActivity2 = nVar.f358g;
            mainActivity2.getWindow().getDecorView().removeCallbacks(nVar);
            mainActivity2.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(nVar);
        }
    }

    public static Bundle c(MainActivity mainActivity) {
        Bundle bundle = new Bundle();
        e.e eVar = ((r) mainActivity).activityResultRegistry;
        eVar.getClass();
        LinkedHashMap linkedHashMap = eVar.f1644b;
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(linkedHashMap.values()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(linkedHashMap.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(eVar.f1645c));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", new Bundle(eVar.f1648f));
        return bundle;
    }

    public static /* synthetic */ void getOnBackPressedDispatcher$annotations() {
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initializeViewTreeOwners();
        m mVar = this.reportFullyDrawnExecutor;
        View decorView = getWindow().getDecorView();
        b4.l.d("window.decorView", decorView);
        ((n) mVar).a(decorView);
        super.addContentView(view, layoutParams);
    }

    public void addMenuProvider(w2.i iVar) {
        b4.l.e("provider", iVar);
        w2.h hVar = this.menuHostHelper;
        hVar.f6396b.add(null);
        hVar.f6395a.run();
    }

    public void addMenuProvider(w2.i iVar, androidx.lifecycle.v vVar) {
        b4.l.e("provider", iVar);
        b4.l.e("owner", vVar);
        w2.h hVar = this.menuHostHelper;
        hVar.f6396b.add(null);
        hVar.f6395a.run();
        androidx.lifecycle.q lifecycle = vVar.getLifecycle();
        HashMap map = hVar.f6397c;
        w2.g gVar = (w2.g) map.remove(iVar);
        if (gVar != null) {
            gVar.f6393a.b(gVar.f6394b);
            gVar.f6394b = null;
        }
        map.put(iVar, new w2.g(lifecycle, new k3.a(1, hVar)));
    }

    public void addMenuProvider(w2.i iVar, androidx.lifecycle.v vVar, androidx.lifecycle.p pVar) {
        b4.l.e("provider", iVar);
        b4.l.e("owner", vVar);
        b4.l.e("state", pVar);
        w2.h hVar = this.menuHostHelper;
        hVar.getClass();
        androidx.lifecycle.q lifecycle = vVar.getLifecycle();
        HashMap map = hVar.f6397c;
        w2.g gVar = (w2.g) map.remove(iVar);
        if (gVar != null) {
            gVar.f6393a.b(gVar.f6394b);
            gVar.f6394b = null;
        }
        map.put(iVar, new w2.g(lifecycle, new i(hVar, 1, pVar)));
    }

    public final void addOnConfigurationChangedListener(v2.a aVar) {
        b4.l.e("listener", aVar);
        this.onConfigurationChangedListeners.add(aVar);
    }

    public final void addOnContextAvailableListener(d.b bVar) {
        b4.l.e("listener", bVar);
        d.a aVar = this.contextAwareHelper;
        aVar.getClass();
        r rVar = aVar.f1506b;
        if (rVar != null) {
            a(((h) bVar).f335a, rVar);
        }
        aVar.f1505a.add(bVar);
    }

    public final void addOnMultiWindowModeChangedListener(v2.a aVar) {
        b4.l.e("listener", aVar);
        this.onMultiWindowModeChangedListeners.add(aVar);
    }

    public final void addOnNewIntentListener(v2.a aVar) {
        b4.l.e("listener", aVar);
        this.onNewIntentListeners.add(aVar);
    }

    public final void addOnPictureInPictureModeChangedListener(v2.a aVar) {
        b4.l.e("listener", aVar);
        this.onPictureInPictureModeChangedListeners.add(aVar);
    }

    public final void addOnTrimMemoryListener(v2.a aVar) {
        b4.l.e("listener", aVar);
        this.onTrimMemoryListeners.add(aVar);
    }

    public final void addOnUserLeaveHintListener(Runnable runnable) {
        b4.l.e("listener", runnable);
        this.onUserLeaveHintListeners.add(runnable);
    }

    public final e.e getActivityResultRegistry() {
        return this.activityResultRegistry;
    }

    @Override // androidx.lifecycle.k
    public g3.b getDefaultViewModelCreationExtras() {
        g3.a aVar = g3.a.f2546b;
        b4.l.e("initialExtras", aVar);
        LinkedHashMap linkedHashMap = aVar.f2547a;
        b4.l.e("initialExtras", linkedHashMap);
        g3.c cVar = new g3.c();
        cVar.f2547a.putAll(linkedHashMap);
        Application application = getApplication();
        LinkedHashMap linkedHashMap2 = cVar.f2547a;
        if (application != null) {
            Application application2 = getApplication();
            b4.l.d("application", application2);
            linkedHashMap2.put(s0.f290d, application2);
        }
        linkedHashMap2.put(androidx.lifecycle.l0.f262a, this);
        linkedHashMap2.put(androidx.lifecycle.l0.f263b, this);
        Intent intent = getIntent();
        Bundle extras = intent != null ? intent.getExtras() : null;
        if (extras != null) {
            linkedHashMap2.put(androidx.lifecycle.l0.f264c, extras);
        }
        return cVar;
    }

    public t0 getDefaultViewModelProviderFactory() {
        return (t0) this.defaultViewModelProviderFactory$delegate.getValue();
    }

    public y getFullyDrawnReporter() {
        return (y) this.fullyDrawnReporter$delegate.getValue();
    }

    @p3.a
    public Object getLastCustomNonConfigurationInstance() {
        l lVar = (l) getLastNonConfigurationInstance();
        if (lVar != null) {
            return lVar.f351a;
        }
        return null;
    }

    @Override // p2.b, androidx.lifecycle.v
    public androidx.lifecycle.q getLifecycle() {
        return super.getLifecycle();
    }

    @Override // b.j0
    public final i0 getOnBackPressedDispatcher() {
        return (i0) this.onBackPressedDispatcher$delegate.getValue();
    }

    @Override // j3.g
    public final j3.e getSavedStateRegistry() {
        return this.savedStateRegistryController.f3344b;
    }

    @Override // androidx.lifecycle.w0
    public v0 getViewModelStore() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this._viewModelStore == null) {
            l lVar = (l) getLastNonConfigurationInstance();
            if (lVar != null) {
                this._viewModelStore = lVar.f352b;
            }
            if (this._viewModelStore == null) {
                this._viewModelStore = new v0();
            }
        }
        v0 v0Var = this._viewModelStore;
        b4.l.b(v0Var);
        return v0Var;
    }

    public void initializeViewTreeOwners() {
        View decorView = getWindow().getDecorView();
        b4.l.d("window.decorView", decorView);
        decorView.setTag(R.id.view_tree_lifecycle_owner, this);
        View decorView2 = getWindow().getDecorView();
        b4.l.d("window.decorView", decorView2);
        decorView2.setTag(R.id.view_tree_view_model_store_owner, this);
        View decorView3 = getWindow().getDecorView();
        b4.l.d("window.decorView", decorView3);
        decorView3.setTag(R.id.view_tree_saved_state_registry_owner, this);
        View decorView4 = getWindow().getDecorView();
        b4.l.d("window.decorView", decorView4);
        decorView4.setTag(R.id.view_tree_on_back_pressed_dispatcher_owner, this);
        View decorView5 = getWindow().getDecorView();
        b4.l.d("window.decorView", decorView5);
        decorView5.setTag(R.id.report_drawn, this);
    }

    public void invalidateMenu() {
        invalidateOptionsMenu();
    }

    @Override // android.app.Activity
    @p3.a
    public void onActivityResult(int i5, int i6, Intent intent) {
        if (this.activityResultRegistry.a(i5, i6, intent)) {
            return;
        }
        super.onActivityResult(i5, i6, intent);
    }

    @Override // android.app.Activity
    @p3.a
    public void onBackPressed() {
        getOnBackPressedDispatcher().b();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        b4.l.e("newConfig", configuration);
        super.onConfigurationChanged(configuration);
        Iterator<v2.a> it = this.onConfigurationChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(configuration);
        }
    }

    @Override // p2.b, android.app.Activity
    public void onCreate(Bundle bundle) {
        k3.b bVar = this.savedStateRegistryController.f3343a;
        MainActivity mainActivity = (MainActivity) bVar.f3430d;
        if (!bVar.f3427a) {
            bVar.d();
        }
        if (((androidx.lifecycle.x) mainActivity.getLifecycle()).f298c.compareTo(androidx.lifecycle.p.f277g) >= 0) {
            throw new IllegalStateException(("performRestore cannot be called when owner is " + ((androidx.lifecycle.x) mainActivity.getLifecycle()).f298c).toString());
        }
        if (bVar.f3428b) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        Bundle bundleY = null;
        if (bundle != null && bundle.containsKey("androidx.lifecycle.BundlableSavedStateRegistry.key")) {
            bundleY = a.a.y("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle);
        }
        bVar.f3434h = bundleY;
        bVar.f3428b = true;
        d.a aVar = this.contextAwareHelper;
        aVar.getClass();
        aVar.f1506b = this;
        Iterator it = aVar.f1505a.iterator();
        while (it.hasNext()) {
            a(((h) ((d.b) it.next())).f335a, this);
        }
        super.onCreate(bundle);
        int i5 = androidx.lifecycle.g0.f251e;
        androidx.lifecycle.e0.b(this);
        int i6 = this.contentLayoutId;
        if (i6 != 0) {
            setContentView(i6);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i5, Menu menu) {
        b4.l.e("menu", menu);
        if (i5 != 0) {
            return true;
        }
        super.onCreatePanelMenu(i5, menu);
        w2.h hVar = this.menuHostHelper;
        getMenuInflater();
        Iterator it = hVar.f6396b.iterator();
        if (!it.hasNext()) {
            return true;
        }
        it.next().getClass();
        throw new ClassCastException();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i5, MenuItem menuItem) {
        b4.l.e("item", menuItem);
        if (super.onMenuItemSelected(i5, menuItem)) {
            return true;
        }
        if (i5 != 0) {
            return false;
        }
        Iterator it = this.menuHostHelper.f6396b.iterator();
        if (!it.hasNext()) {
            return false;
        }
        it.next().getClass();
        throw new ClassCastException();
    }

    @Override // android.app.Activity
    @p3.a
    public void onMultiWindowModeChanged(boolean z2) {
        if (this.dispatchingOnMultiWindowModeChanged) {
            return;
        }
        Iterator<v2.a> it = this.onMultiWindowModeChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(new androidx.lifecycle.k0(27));
        }
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z2, Configuration configuration) {
        b4.l.e("newConfig", configuration);
        this.dispatchingOnMultiWindowModeChanged = true;
        try {
            super.onMultiWindowModeChanged(z2, configuration);
            this.dispatchingOnMultiWindowModeChanged = false;
            Iterator<v2.a> it = this.onMultiWindowModeChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().accept(new androidx.lifecycle.k0(27));
            }
        } catch (Throwable th) {
            this.dispatchingOnMultiWindowModeChanged = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        b4.l.e(AssistConfig.METHOD_INTENT, intent);
        super.onNewIntent(intent);
        Iterator<v2.a> it = this.onNewIntentListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(intent);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i5, Menu menu) {
        b4.l.e("menu", menu);
        Iterator it = this.menuHostHelper.f6396b.iterator();
        if (it.hasNext()) {
            it.next().getClass();
            throw new ClassCastException();
        }
        super.onPanelClosed(i5, menu);
    }

    @Override // android.app.Activity
    @p3.a
    public void onPictureInPictureModeChanged(boolean z2) {
        if (this.dispatchingOnPictureInPictureModeChanged) {
            return;
        }
        Iterator<v2.a> it = this.onPictureInPictureModeChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(new androidx.lifecycle.k0(28));
        }
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z2, Configuration configuration) {
        b4.l.e("newConfig", configuration);
        this.dispatchingOnPictureInPictureModeChanged = true;
        try {
            super.onPictureInPictureModeChanged(z2, configuration);
            this.dispatchingOnPictureInPictureModeChanged = false;
            Iterator<v2.a> it = this.onPictureInPictureModeChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().accept(new androidx.lifecycle.k0(28));
            }
        } catch (Throwable th) {
            this.dispatchingOnPictureInPictureModeChanged = false;
            throw th;
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i5, View view, Menu menu) {
        b4.l.e("menu", menu);
        if (i5 != 0) {
            return true;
        }
        super.onPreparePanel(i5, view, menu);
        Iterator it = this.menuHostHelper.f6396b.iterator();
        if (!it.hasNext()) {
            return true;
        }
        it.next().getClass();
        throw new ClassCastException();
    }

    @Override // android.app.Activity
    @p3.a
    public void onRequestPermissionsResult(int i5, String[] strArr, int[] iArr) {
        b4.l.e("permissions", strArr);
        b4.l.e("grantResults", iArr);
        if (this.activityResultRegistry.a(i5, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr))) {
            return;
        }
        super.onRequestPermissionsResult(i5, strArr, iArr);
    }

    @p3.a
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        l lVar;
        Object objOnRetainCustomNonConfigurationInstance = onRetainCustomNonConfigurationInstance();
        v0 v0Var = this._viewModelStore;
        if (v0Var == null && (lVar = (l) getLastNonConfigurationInstance()) != null) {
            v0Var = lVar.f352b;
        }
        if (v0Var == null && objOnRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        l lVar2 = new l();
        lVar2.f351a = objOnRetainCustomNonConfigurationInstance;
        lVar2.f352b = v0Var;
        return lVar2;
    }

    @Override // p2.b, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        b4.l.e("outState", bundle);
        if (getLifecycle() instanceof androidx.lifecycle.x) {
            androidx.lifecycle.q lifecycle = getLifecycle();
            b4.l.c("null cannot be cast to non-null type androidx.lifecycle.LifecycleRegistry", lifecycle);
            androidx.lifecycle.x xVar = (androidx.lifecycle.x) lifecycle;
            androidx.lifecycle.p pVar = androidx.lifecycle.p.f276f;
            xVar.d("setCurrentState");
            xVar.f(pVar);
        }
        super.onSaveInstanceState(bundle);
        j3.f fVar = this.savedStateRegistryController;
        fVar.getClass();
        k3.b bVar = fVar.f3343a;
        Bundle bundleG = q.f.g((p3.f[]) Arrays.copyOf(new p3.f[0], 0));
        Bundle bundle2 = (Bundle) bVar.f3434h;
        if (bundle2 != null) {
            bundleG.putAll(bundle2);
        }
        synchronized (((androidx.lifecycle.k0) bVar.f3432f)) {
            for (Map.Entry entry : ((LinkedHashMap) bVar.f3433g).entrySet()) {
                String str = (String) entry.getKey();
                Bundle bundleA = ((j3.d) entry.getValue()).a();
                b4.l.e("key", str);
                bundleG.putBundle(str, bundleA);
            }
        }
        if (bundleG.isEmpty()) {
            return;
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundleG);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i5) {
        super.onTrimMemory(i5);
        Iterator<v2.a> it = this.onTrimMemoryListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(Integer.valueOf(i5));
        }
    }

    @Override // android.app.Activity
    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        Iterator<Runnable> it = this.onUserLeaveHintListeners.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
    }

    public Context peekAvailableContext() {
        return this.contextAwareHelper.f1506b;
    }

    public final <I, O> e.d registerForActivityResult(f.a aVar, e.c cVar) {
        b4.l.e("contract", aVar);
        throw null;
    }

    public final <I, O> e.d registerForActivityResult(f.a aVar, e.e eVar, e.c cVar) {
        b4.l.e("contract", aVar);
        throw null;
    }

    public void removeMenuProvider(w2.i iVar) {
        b4.l.e("provider", iVar);
        this.menuHostHelper.a();
    }

    public final void removeOnConfigurationChangedListener(v2.a aVar) {
        b4.l.e("listener", aVar);
        this.onConfigurationChangedListeners.remove(aVar);
    }

    public final void removeOnContextAvailableListener(d.b bVar) {
        b4.l.e("listener", bVar);
        d.a aVar = this.contextAwareHelper;
        aVar.getClass();
        aVar.f1505a.remove(bVar);
    }

    public final void removeOnMultiWindowModeChangedListener(v2.a aVar) {
        b4.l.e("listener", aVar);
        this.onMultiWindowModeChangedListeners.remove(aVar);
    }

    public final void removeOnNewIntentListener(v2.a aVar) {
        b4.l.e("listener", aVar);
        this.onNewIntentListeners.remove(aVar);
    }

    public final void removeOnPictureInPictureModeChangedListener(v2.a aVar) {
        b4.l.e("listener", aVar);
        this.onPictureInPictureModeChangedListeners.remove(aVar);
    }

    public final void removeOnTrimMemoryListener(v2.a aVar) {
        b4.l.e("listener", aVar);
        this.onTrimMemoryListeners.remove(aVar);
    }

    public final void removeOnUserLeaveHintListener(Runnable runnable) {
        b4.l.e("listener", runnable);
        this.onUserLeaveHintListeners.remove(runnable);
    }

    @Override // android.app.Activity
    public void reportFullyDrawn() {
        try {
            if (d4.a.y()) {
                d4.a.g("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
            y fullyDrawnReporter = getFullyDrawnReporter();
            synchronized (fullyDrawnReporter.f366a) {
                try {
                    fullyDrawnReporter.f367b = true;
                    ArrayList arrayList = fullyDrawnReporter.f368c;
                    int size = arrayList.size();
                    int i5 = 0;
                    while (i5 < size) {
                        Object obj = arrayList.get(i5);
                        i5++;
                        ((a4.a) obj).a();
                    }
                    fullyDrawnReporter.f368c.clear();
                } catch (Throwable th) {
                    throw th;
                }
            }
            Trace.endSection();
        } catch (Throwable th2) {
            Trace.endSection();
            throw th2;
        }
    }

    @Override // android.app.Activity
    public void setContentView(int i5) {
        initializeViewTreeOwners();
        m mVar = this.reportFullyDrawnExecutor;
        View decorView = getWindow().getDecorView();
        b4.l.d("window.decorView", decorView);
        ((n) mVar).a(decorView);
        super.setContentView(i5);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        initializeViewTreeOwners();
        m mVar = this.reportFullyDrawnExecutor;
        View decorView = getWindow().getDecorView();
        b4.l.d("window.decorView", decorView);
        ((n) mVar).a(decorView);
        super.setContentView(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initializeViewTreeOwners();
        m mVar = this.reportFullyDrawnExecutor;
        View decorView = getWindow().getDecorView();
        b4.l.d("window.decorView", decorView);
        ((n) mVar).a(decorView);
        super.setContentView(view, layoutParams);
    }

    @Override // android.app.Activity
    @p3.a
    public void startActivityForResult(Intent intent, int i5) {
        b4.l.e(AssistConfig.METHOD_INTENT, intent);
        super.startActivityForResult(intent, i5);
    }

    @Override // android.app.Activity
    @p3.a
    public void startActivityForResult(Intent intent, int i5, Bundle bundle) {
        b4.l.e(AssistConfig.METHOD_INTENT, intent);
        super.startActivityForResult(intent, i5, bundle);
    }

    @Override // android.app.Activity
    @p3.a
    public void startIntentSenderForResult(IntentSender intentSender, int i5, Intent intent, int i6, int i7, int i8) throws IntentSender.SendIntentException {
        b4.l.e(AssistConfig.METHOD_INTENT, intentSender);
        super.startIntentSenderForResult(intentSender, i5, intent, i6, i7, i8);
    }

    @Override // android.app.Activity
    @p3.a
    public void startIntentSenderForResult(IntentSender intentSender, int i5, Intent intent, int i6, int i7, int i8, Bundle bundle) throws IntentSender.SendIntentException {
        b4.l.e(AssistConfig.METHOD_INTENT, intentSender);
        super.startIntentSenderForResult(intentSender, i5, intent, i6, i7, i8, bundle);
    }
}
