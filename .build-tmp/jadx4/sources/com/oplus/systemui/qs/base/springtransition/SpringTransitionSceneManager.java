package com.oplus.systemui.qs.base.springtransition;

import androidx.car.app.serialization.Bundler$;
import androidx.room.util.CursorUtil$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SpringTransitionSceneManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SpringTransitionSceneManager";
    private final Map<String, SpringTransitionManager> sceneMap = new HashMap();
    private final Map<String, Pair<Float, Float>> startAndEndValue = new LinkedHashMap();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public final void enterScene(String str) {
        switchScene(str, true);
    }

    public final void exitScene(String str) {
        switchScene(str, false);
    }

    public final boolean isAnyRunning() {
        Collection<SpringTransitionManager> collectionValues = this.sceneMap.values();
        if ((collectionValues instanceof Collection) && collectionValues.isEmpty()) {
            return false;
        }
        Iterator<T> it = collectionValues.iterator();
        while (it.hasNext()) {
            if (((SpringTransitionManager) it.next()).isRunning()) {
                return true;
            }
        }
        return false;
    }

    public final void registerScene(String str, SpringTransitionManager springTransitionManager) {
        this.sceneMap.put(str, springTransitionManager);
    }

    public final void startTransitionStartAndEnd(String str, float f, float f2) {
        this.startAndEndValue.put(str, new Pair<>(Float.valueOf(f), Float.valueOf(f2)));
    }

    public final void switchScene(String str, boolean z) {
        SpringTransitionManager springTransitionManager = this.sceneMap.get(str);
        if (springTransitionManager == null) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, Bundler$.ExternalSyntheticOutline0.m("[switchScene] ", str, " has not registered"), null, 8);
            return;
        }
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, CursorUtil$.ExternalSyntheticOutline0.m("[switchScene] ", z ? "entering" : "exiting", " ", str), null, 8);
        Collection<SpringTransitionManager> collectionValues = this.sceneMap.values();
        ArrayList arrayList = new ArrayList();
        for (Object obj : collectionValues) {
            if (!Intrinsics.areEqual((SpringTransitionManager) obj, springTransitionManager)) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj2 = arrayList.get(i);
                i++;
                if (((SpringTransitionManager) obj2).isRunning()) {
                    Collection<SpringTransitionManager> collectionValues2 = this.sceneMap.values();
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj3 : collectionValues2) {
                        SpringTransitionManager springTransitionManager2 = (SpringTransitionManager) obj3;
                        if (!Intrinsics.areEqual(springTransitionManager2, springTransitionManager) && springTransitionManager2.isRunning()) {
                            arrayList2.add(obj3);
                        }
                    }
                    int size2 = arrayList2.size();
                    int i2 = 0;
                    while (i2 < size2) {
                        Object obj4 = arrayList2.get(i2);
                        i2++;
                        Set<SpringTransition> transitions = ((SpringTransitionManager) obj4).getTransitions();
                        ArrayList arrayList3 = new ArrayList();
                        for (Object obj5 : transitions) {
                            if (((SpringTransition) obj5).isRunning()) {
                                arrayList3.add(obj5);
                            }
                        }
                        int size3 = arrayList3.size();
                        int i3 = 0;
                        while (i3 < size3) {
                            Object obj6 = arrayList3.get(i3);
                            i3++;
                            SpringTransition springTransition = (SpringTransition) obj6;
                            for (SpringTransition springTransition2 : springTransitionManager.getTransitions()) {
                                Iterator<TransitionProperty> allTargetProperties = springTransition2.getAllTargetProperties();
                                while (allTargetProperties.hasNext()) {
                                    springTransition.removeTargetProperty(allTargetProperties.next(), true);
                                }
                                Iterator<T> it = springTransition2.getAllSkipToEndPropertyHash().iterator();
                                while (it.hasNext()) {
                                    springTransition.removeTargetProperty(((Number) it.next()).intValue(), true);
                                }
                            }
                        }
                    }
                    springTransitionManager.getHostViewGroup().suppressLayout(false);
                    break;
                }
            }
        }
        Set<SpringTransition> transitions2 = springTransitionManager.getTransitions();
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(transitions2, 10));
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (Object obj7 : transitions2) {
            Pair<Float, Float> pairCopy = this.startAndEndValue.get(((SpringTransition) obj7).getTransitionName());
            if (pairCopy == null) {
                pairCopy = new Pair<>(Float.valueOf(0.0f), Float.valueOf(1.0f));
            }
            if (!z) {
                pairCopy = pairCopy.copy(pairCopy.getSecond(), pairCopy.getFirst());
            }
            linkedHashMap.put(obj7, pairCopy);
        }
        springTransitionManager.beginDelayedTransition(linkedHashMap);
    }

    public final boolean unRegisterScene(String str) {
        return this.sceneMap.remove(str) != null;
    }
}
