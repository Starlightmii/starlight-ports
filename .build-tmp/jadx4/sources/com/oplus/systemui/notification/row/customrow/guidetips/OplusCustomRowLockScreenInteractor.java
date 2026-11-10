package com.oplus.systemui.notification.row.customrow.guidetips;

import android.util.Log;
import android.widget.FrameLayout;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowController;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRowControllerManager;
import com.oplus.systemui.statusbar.notification.customcard.guidetips.CustomCardGuideTipsViewController;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomRowLockScreenInteractor {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusCustomRowLockScreenInteractor";
    private final CopyOnWriteArrayList<OplusCustomRowGuideTipsCallback> callbacks = new CopyOnWriteArrayList<>();
    private final Lazy customRowManager;
    private final Lazy lockScreenExtStateController;
    private final CoroutineScope scope;
    private final Lazy statusBarStateController;
    private final OplusCustomRowLockScreenInteractor$statusBarStateListener$1 statusBarStateListener;
    private final Lazy wakefulnessLifecycle;
    private final OplusCustomRowLockScreenInteractor$wakefulnessObserver$1 wakefulnessObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusCustomRowLockScreenInteractor(Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, CoroutineScope coroutineScope) {
        this.customRowManager = lazy;
        this.statusBarStateController = lazy2;
        this.lockScreenExtStateController = lazy3;
        this.wakefulnessLifecycle = lazy4;
        this.scope = coroutineScope;
        OplusCustomRowLockScreenInteractor$wakefulnessObserver$1 oplusCustomRowLockScreenInteractor$wakefulnessObserver$1 = new OplusCustomRowLockScreenInteractor$wakefulnessObserver$1();
        oplusCustomRowLockScreenInteractor$wakefulnessObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.wakefulnessObserver = oplusCustomRowLockScreenInteractor$wakefulnessObserver$1;
        OplusCustomRowLockScreenInteractor$statusBarStateListener$1 oplusCustomRowLockScreenInteractor$statusBarStateListener$1 = new OplusCustomRowLockScreenInteractor$statusBarStateListener$1();
        oplusCustomRowLockScreenInteractor$statusBarStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.statusBarStateListener = oplusCustomRowLockScreenInteractor$statusBarStateListener$1;
    }

    public static final void access$seedlingCardExposedOnLockScreen(OplusCustomRowLockScreenInteractor oplusCustomRowLockScreenInteractor) {
        List<? extends CustomRowEntry> fullyVisibleCustomRowKeys = ((OplusCustomRowControllerManager) oplusCustomRowLockScreenInteractor.customRowManager.get()).getFullyVisibleCustomRowKeys();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sb = new StringBuilder("OplusCustomRowLockScreenInteractor-->");
            List<? extends CustomRowEntry> list = fullyVisibleCustomRowKeys;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((CustomRowEntry) it.next()).getKey());
            }
            sb.append("seedlingCardExposedOnLockScreen, exposedList=" + arrayList + ", reason=screenOn");
            Log.d("SystemUi--Notification", sb.toString());
        }
        if (fullyVisibleCustomRowKeys.isEmpty()) {
            return;
        }
        Iterator<T> it2 = oplusCustomRowLockScreenInteractor.callbacks.iterator();
        while (it2.hasNext()) {
            ((OplusCustomRowGuideTipsCallback) it2.next()).onCardExposedOnLockScreen(fullyVisibleCustomRowKeys);
        }
    }

    public static void requestHideGuideTips$default(OplusCustomRowLockScreenInteractor oplusCustomRowLockScreenInteractor, int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", MobileInputLogger$.ExternalSyntheticOutline0.m("requestHideGuideTips, reason=", i, ", needsAnimation=", "OplusCustomRowLockScreenInteractor-->", true));
        }
        Iterator<T> it = oplusCustomRowLockScreenInteractor.callbacks.iterator();
        while (it.hasNext()) {
            CustomCardGuideTipsViewController customCardGuideTipsViewController = (CustomCardGuideTipsViewController) ((OplusCustomRowGuideTipsCallback) it.next());
            customCardGuideTipsViewController.getClass();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "resetGuide, reason=", "CustomCardGuideTipsVC-->", "SystemUi--Notification");
            }
            customCardGuideTipsViewController.stopCountDown();
            customCardGuideTipsViewController.startEndAnimator();
        }
    }

    public final void addCallback(OplusCustomRowGuideTipsCallback oplusCustomRowGuideTipsCallback) {
        this.callbacks.add(oplusCustomRowGuideTipsCallback);
    }

    public final CustomRowEntry getCustomRowEntry(String str) {
        Object next;
        CustomRowEntry entry;
        Iterator<T> it = ((OplusCustomRowControllerManager) this.customRowManager.get()).getOplusCustomRowControllerList().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            entry = ((OplusCustomRowController) next).getEntry();
        } while (!Intrinsics.areEqual(entry != null ? entry.getKey() : null, str));
        OplusCustomRowController oplusCustomRowController = (OplusCustomRowController) next;
        if (oplusCustomRowController != null) {
            return oplusCustomRowController.getEntry();
        }
        return null;
    }

    public final void hideSeedlingCardFooter(String str, boolean z) {
        OplusCustomRow view;
        OplusCustomRowGuideTipsController guideTipsController;
        for (OplusCustomRowController oplusCustomRowController : ((OplusCustomRowControllerManager) this.customRowManager.get()).getOplusCustomRowControllerList()) {
            CustomRowEntry entry = oplusCustomRowController.getEntry();
            if (Intrinsics.areEqual(entry != null ? entry.getKey() : null, str) && (view = oplusCustomRowController.getView()) != null && (guideTipsController = view.getGuideTipsController()) != null) {
                guideTipsController.hideCustomCardTips();
            }
        }
    }

    public final void removeCallback(OplusCustomRowGuideTipsCallback oplusCustomRowGuideTipsCallback) {
        this.callbacks.remove(oplusCustomRowGuideTipsCallback);
    }

    public final FrameLayout requestGuideTipsViewToShow(String str) {
        OplusCustomRowGuideTipsController guideTipsController;
        CustomCardGuideTipsView orInflateGuideTipsView;
        for (OplusCustomRowController oplusCustomRowController : ((OplusCustomRowControllerManager) this.customRowManager.get()).getOplusCustomRowControllerList()) {
            CustomRowEntry entry = oplusCustomRowController.getEntry();
            if (Intrinsics.areEqual(entry != null ? entry.getKey() : null, str)) {
                OplusCustomRow view = oplusCustomRowController.getView();
                if (view != null && (guideTipsController = view.getGuideTipsController()) != null && (orInflateGuideTipsView = guideTipsController.getOrInflateGuideTipsView()) != null) {
                    return orInflateGuideTipsView.getHighlightArea();
                }
                break;
                break;
                break;
            }
        }
        return null;
    }

    public final void showSeedlingCardFooter(String str, boolean z) {
        Object next;
        OplusCustomRowGuideTipsController guideTipsController;
        CustomRowEntry entry;
        Iterator<T> it = ((OplusCustomRowControllerManager) this.customRowManager.get()).getOplusCustomRowControllerList().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                entry = ((OplusCustomRowController) next).getEntry();
            }
        } while (!Intrinsics.areEqual(entry != null ? entry.getKey() : null, str));
        OplusCustomRowController oplusCustomRowController = (OplusCustomRowController) next;
        OplusCustomRow view = oplusCustomRowController != null ? oplusCustomRowController.getView() : null;
        if (view == null || (guideTipsController = view.getGuideTipsController()) == null) {
            return;
        }
        guideTipsController.showCustomCardTips();
    }
}
