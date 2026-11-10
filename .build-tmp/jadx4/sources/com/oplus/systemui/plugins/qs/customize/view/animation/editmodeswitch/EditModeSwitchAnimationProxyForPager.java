package com.oplus.systemui.plugins.qs.customize.view.animation.editmodeswitch;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.core.view.ViewGroupKt;
import androidx.recyclerview.widget.COUIRecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import com.oplus.systemui.plugins.qs.editmodeswitch.EditModeSwitchAnimationManager;
import com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher;
import com.oplus.systemui.plugins.qs.editmodeswitch.TransitionPropertiesCache;
import com.oplus.systemui.qs.base.springtransition.ITransitionDesc;
import com.oplus.systemui.qs.base.springtransition.SpringTransition;
import com.oplus.systemui.qs.base.springtransition.TransitionProperty;
import com.oplus.systemui.qs.base.widget.recyclerview.StaggeredPagerLayoutManager;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt___SequencesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditModeSwitchAnimationProxyForPager implements IEditModeSwitcher {
    public static final Companion Companion = new Companion();
    private static final String TAG = "EditModeSwitchAnimationProxyForPager";
    private final List<ITransitionDesc> extraTransitionDesc;
    private final Function0 isAnyTransitionRunning;
    private final COUIRecyclerView recyclerView;
    private final Lazy transitionProperties$delegate = LazyKt__LazyJVMKt.lazy(new EditModeSwitchAnimationProxyForPager$$ExternalSyntheticLambda0(0));
    private final Set<ITransitionDesc> currPageTransitionDesc = new LinkedHashSet();
    private final Set<ITransitionDesc> invisibleTransitionDesc = new LinkedHashSet();
    private SparseArray<TransitionProperty> lastTransitionProperties = new SparseArray<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public EditModeSwitchAnimationProxyForPager(COUIRecyclerView cOUIRecyclerView, List<? extends ITransitionDesc> list, Function0 function0) {
        this.recyclerView = cOUIRecyclerView;
        this.extraTransitionDesc = list;
        this.isAnyTransitionRunning = function0;
    }

    public static void collectAllTransitionDesc(View view, Set set) {
        if (view instanceof ITransitionDesc) {
            set.add(view);
        }
        if (view instanceof ViewGroup) {
            Iterator it = ViewGroupKt.getChildren((ViewGroup) view).iterator();
            while (it.hasNext()) {
                collectAllTransitionDesc((View) it.next(), set);
            }
        }
    }

    private final TransitionPropertiesCache getTransitionProperties() {
        return (TransitionPropertiesCache) this.transitionProperties$delegate.getValue();
    }

    @Override // com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public void bindTargetProperties(Set<? extends SpringTransition> set) {
        List<View> listFindVisibleChildren;
        boolean zBooleanValue = ((Boolean) this.isAnyTransitionRunning.invoke()).booleanValue();
        this.currPageTransitionDesc.clear();
        RecyclerView.LayoutManager layoutManager = this.recyclerView.getLayoutManager();
        StaggeredPagerLayoutManager staggeredPagerLayoutManager = layoutManager instanceof StaggeredPagerLayoutManager ? (StaggeredPagerLayoutManager) layoutManager : null;
        if (staggeredPagerLayoutManager == null) {
            listFindVisibleChildren = CollectionsKt__CollectionsKt.emptyList();
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "[findChildInCurrPage] layoutManager isn't StaggeredPagerLayoutManager", null, 8);
        } else {
            listFindVisibleChildren = staggeredPagerLayoutManager.findVisibleChildren();
        }
        List<View> list = listFindVisibleChildren;
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            collectAllTransitionDesc((View) it.next(), this.currPageTransitionDesc);
        }
        this.currPageTransitionDesc.addAll(this.extraTransitionDesc);
        if (zBooleanValue) {
            this.invisibleTransitionDesc.clear();
            Iterator it2 = CollectionsKt___CollectionsKt.minus(SequencesKt___SequencesKt.toList(ViewGroupKt.getChildren(this.recyclerView)), list).iterator();
            while (it2.hasNext()) {
                collectAllTransitionDesc((View) it2.next(), this.invisibleTransitionDesc);
            }
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(this.currPageTransitionDesc.size(), "[refreshAllTransitionDesc] currPageTransitionDesc.size="), null, 8);
        getTransitionProperties().refreshCacheWith(this.lastTransitionProperties);
        this.lastTransitionProperties = new SparseArray<>();
        SparseArray sparseArray = new SparseArray();
        SparseArray sparseArray2 = new SparseArray();
        ArrayList arrayList = new ArrayList();
        for (SpringTransition springTransition : set) {
            arrayList.clear();
            Iterator<T> it3 = this.currPageTransitionDesc.iterator();
            while (it3.hasNext()) {
                ((ITransitionDesc) it3.next()).collectProperties(springTransition.getTransitionName(), arrayList);
            }
            int size = arrayList.size();
            int i = 0;
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayList.get(i2);
                i2++;
                Pair pair = (Pair) obj;
                Object second = pair.getSecond();
                Class cls = second instanceof Class ? (Class) second : null;
                if (cls != null) {
                    TransitionProperty transitionPropertyFetchTransitionProperty = getTransitionProperties().fetchTransitionProperty(pair.getFirst(), cls);
                    int iHashCode = transitionPropertyFetchTransitionProperty.hashCode();
                    sparseArray.put(iHashCode, transitionPropertyFetchTransitionProperty);
                    this.lastTransitionProperties.put(iHashCode, transitionPropertyFetchTransitionProperty);
                    springTransition.addTargetProperty(transitionPropertyFetchTransitionProperty);
                }
            }
            Iterator<TransitionProperty> properties = getTransitionProperties().getProperties();
            while (properties.hasNext()) {
                TransitionProperty next = properties.next();
                int iHashCode2 = next.hashCode();
                if (sparseArray.indexOfKey(iHashCode2) < 0) {
                    sparseArray2.put(iHashCode2, next);
                }
            }
            List list2 = SpringTransition.Companion.toList(springTransition.getAllTargetProperties());
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : list2) {
                if (sparseArray2.indexOfKey(((TransitionProperty) obj2).hashCode()) >= 0) {
                    arrayList2.add(obj2);
                }
            }
            int size2 = arrayList2.size();
            int i3 = 0;
            while (i3 < size2) {
                Object obj3 = arrayList2.get(i3);
                i3++;
                springTransition.removeTargetProperty((TransitionProperty) obj3, false);
            }
            sparseArray2.clear();
            sparseArray.clear();
            if (zBooleanValue) {
                arrayList.clear();
                Iterator<T> it4 = this.invisibleTransitionDesc.iterator();
                while (it4.hasNext()) {
                    ((ITransitionDesc) it4.next()).collectProperties(springTransition.getTransitionName(), arrayList);
                }
                int size3 = arrayList.size();
                while (i < size3) {
                    Object obj4 = arrayList.get(i);
                    i++;
                    Pair pair2 = (Pair) obj4;
                    Object second2 = pair2.getSecond();
                    Class<?> cls2 = second2 instanceof Class ? (Class) second2 : null;
                    if (cls2 != null) {
                        springTransition.addSkipToEndPropertyHash(TransitionProperty.Companion.hashCode(cls2, pair2.getFirst()));
                    }
                }
            }
        }
    }

    @Override // com.oplus.systemui.plugins.qs.editmodeswitch.IEditModeSwitcher
    public SpringTransition[] createSpringTransition(String str) {
        if (Intrinsics.areEqual(str, EditModeSwitchAnimationManager.SCENE_DRAG)) {
            return (SpringTransition[]) CollectionsKt__CollectionsKt.listOfNotNull(IEditModeSwitcher.Companion.createTransition("drag_label_transition")).toArray(new SpringTransition[0]);
        }
        return Intrinsics.areEqual(str, EditModeSwitchAnimationManager.SCENE_ADDING) ? (SpringTransition[]) CollectionsKt__CollectionsKt.listOfNotNull(IEditModeSwitcher.Companion.createTransition("adding_transition_tile_stroke_alpha")).toArray(new SpringTransition[0]) : new SpringTransition[0];
    }
}
