package com.oplus.systemui.statusbar.phone.policy;

import android.util.Log;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.statusbar.policy.PolicyObserver;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPolicyObserver extends PolicyObserver implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusPolicyObserver";
    private final DumpManager dumpManager;
    private Lazy satellitePolicy;
    private final Set<String> policyBlockSet = new LinkedHashSet();
    private final List<IconBlockPolicy> policies = new ArrayList();
    private final List<PolicyObserver.IconBlockCallback> callbacks = new ArrayList();
    private final CopyOnWriteArrayList<String> blockList = new CopyOnWriteArrayList<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusPolicyObserver(DumpManager dumpManager) {
        this.dumpManager = dumpManager;
    }

    public void addCallback(PolicyObserver.IconBlockCallback iconBlockCallback) {
        if (this.callbacks.contains(iconBlockCallback)) {
            return;
        }
        this.callbacks.add(iconBlockCallback);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("OplusPolicyObserver:");
        printWriter.println("  policyBlockSet: " + this.policyBlockSet);
        printWriter.println("  callbacks: " + this.callbacks);
        Iterator<T> it = this.policies.iterator();
        while (it.hasNext()) {
            ((IconBlockPolicy) it.next()).dump(printWriter, strArr);
        }
    }

    public List<String> getBlockList() {
        return CollectionsKt___CollectionsKt.toList(this.policyBlockSet);
    }

    public final Lazy getSatellitePolicy() {
        return this.satellitePolicy;
    }

    public final void init() {
        Lazy lazy = this.satellitePolicy;
        if (lazy != null) {
            this.policies.add((IconBlockPolicy) lazy.get());
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.policies.size(), "init size: ", "OplusPolicyObserver-->", "SystemUi--Statusbar");
        }
        Iterator<T> it = this.policies.iterator();
        while (it.hasNext()) {
            ((SatellitePolicy) ((IconBlockPolicy) it.next())).init();
        }
        this.dumpManager.registerDumpable(this);
    }

    public final void notifyChanged() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = this.policies.iterator();
        while (it.hasNext()) {
            linkedHashSet.addAll(((IconBlockPolicy) it.next()).getBlockList());
        }
        if (this.policyBlockSet.size() == linkedHashSet.size() && this.policyBlockSet.containsAll(linkedHashSet)) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "OplusPolicyObserver-->".concat("notifyChanged: " + this.policyBlockSet + " -> " + linkedHashSet));
        }
        this.policyBlockSet.clear();
        this.policyBlockSet.addAll(linkedHashSet);
        this.blockList.clear();
        this.blockList.addAll(this.policyBlockSet);
        Iterator it2 = CollectionsKt___CollectionsKt.toList(this.callbacks).iterator();
        while (it2.hasNext()) {
            ((PolicyObserver.IconBlockCallback) it2.next()).onChanged(this.blockList);
        }
    }

    public void removeCallback(PolicyObserver.IconBlockCallback iconBlockCallback) {
        this.callbacks.remove(iconBlockCallback);
    }

    public final void setSatellitePolicy(Lazy lazy) {
        this.satellitePolicy = lazy;
    }
}
