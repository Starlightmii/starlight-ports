package com.oplus.systemui.statusbar.seeding;

import android.graphics.Rect;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.android.systemui.Dumpable;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface HiddenIconDispatcher {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final boolean isInAreas(ArrayList<Rect> arrayList, Pair<Integer, Integer> pair) {
            if (arrayList.isEmpty()) {
                return false;
            }
            for (Rect rect : arrayList) {
                if (!rect.isEmpty()) {
                    int iIntValue = ((Number) pair.getFirst()).intValue();
                    int iIntValue2 = ((Number) pair.getSecond()).intValue();
                    int i = rect.left;
                    if (iIntValue > i && iIntValue < rect.right) {
                        return true;
                    }
                    if (iIntValue2 > i && iIntValue2 < rect.right) {
                        return true;
                    }
                }
            }
            return false;
        }

        public final boolean isValid(ArrayList<Rect> arrayList) {
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Rect rect = arrayList.get(i);
                i++;
                Rect rect2 = rect;
                if (rect2.right > rect2.left) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface HiddenReceiver extends Dumpable {
        void onHiddenChanged(ArrayList<Rect> arrayList, boolean z);

        default void setHiddenIconListChanged(boolean z, ArrayList<String> arrayList) {
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher$observe$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements LifecycleEventObserver {
        public /* synthetic */ HiddenReceiver $listener;
        public /* synthetic */ StatusBarLocation $location;
        public /* synthetic */ HiddenIconDispatcher this$0;

        public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            HiddenReceiver hiddenReceiver = this.$listener;
            StatusBarLocation statusBarLocation = this.$location;
            HiddenIconDispatcher hiddenIconDispatcher = this.this$0;
            if (event == Lifecycle.Event.ON_RESUME) {
                hiddenIconDispatcher.addHiddenReceiver(statusBarLocation, hiddenReceiver);
            } else if (event == Lifecycle.Event.ON_DESTROY) {
                hiddenIconDispatcher.removeHiddenReceiver(statusBarLocation, hiddenReceiver);
            }
        }
    }

    void addHiddenReceiver(StatusBarLocation statusBarLocation, HiddenReceiver hiddenReceiver);

    ArrayList<Rect> getHiddenAreas(StatusBarLocation statusBarLocation);

    boolean getLastShouldScale(StatusBarLocation statusBarLocation);

    List<HiddenReceiver> getReceivers(StatusBarLocation statusBarLocation);

    boolean isRegistered(StatusBarLocation statusBarLocation, HiddenReceiver hiddenReceiver);

    default HiddenReceiver observe(Lifecycle lifecycle, StatusBarLocation statusBarLocation, HiddenReceiver hiddenReceiver) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        anonymousClass1.$location = statusBarLocation;
        anonymousClass1.$listener = hiddenReceiver;
        VarHandle.storeStoreFence();
        lifecycle.addObserver(anonymousClass1);
        return hiddenReceiver;
    }

    void removeHiddenReceiver(StatusBarLocation statusBarLocation, HiddenReceiver hiddenReceiver);

    void setHiddenIconAreas(StatusBarLocation statusBarLocation, List<Rect> list);

    void setHiddenStatusBarIcon(StatusBarLocation statusBarLocation, ArrayList<String> arrayList);

    void setStatusBarPaddingRect(StatusBarLocation statusBarLocation, Rect rect);

    default HiddenReceiver observe(LifecycleOwner lifecycleOwner, StatusBarLocation statusBarLocation, HiddenReceiver hiddenReceiver) {
        return observe(lifecycleOwner.getLifecycle(), statusBarLocation, hiddenReceiver);
    }
}
