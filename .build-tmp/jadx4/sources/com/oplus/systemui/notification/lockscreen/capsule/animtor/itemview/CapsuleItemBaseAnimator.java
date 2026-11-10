package com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview;

import android.content.Context;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatDelegateImpl$;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleViewState;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.capsule.animtor.ContainerAnimatorViewHelper;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataPinHandler;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.capsule.utils.ViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.effect.AutoRoundRectPathProvider;
import com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationContainerView;
import com.oplusos.systemui.common.rendercache.RenderBooster;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class CapsuleItemBaseAnimator implements ItemAnimatorFinished {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleItemAnimator";
    private final NotificationCapsuleContainer hostLayout;
    public boolean isRunning;
    private final Function0 onAnimationFinished;
    private final List<ItemPendingAnimAction> pendingActionList = new ArrayList();
    private final Set<CapsuleItemView> renderBoosterViews = new LinkedHashSet();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ItemAnimatorUpdater {
        private final Context context;
        private final Function1 innerUpdate;

        public ItemAnimatorUpdater(Context context, Function1 function1) {
            this.context = context;
            this.innerUpdate = function1;
        }

        public final Context getContext() {
            return this.context;
        }

        public final Function1 getInnerUpdate() {
            return this.innerUpdate;
        }

        public final void update(UpdateData updateData) {
            if (CapsuleDataPinHandler.INSTANCE.getNeedKeepAodDisplayDoze().get()) {
                SmoothTransitionController.Companion.getInstance(this.context).requestScreenStateForSysUiComponent("ItemAnimatorUpdater", 1000);
            }
            this.innerUpdate.invoke(updateData);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ItemPendingAnimAction {
        private final CapsuleItemView appearView;
        private final CapsuleItemView disappearView;
        public final boolean isAddNewItem;

        public ItemPendingAnimAction(CapsuleItemView capsuleItemView, CapsuleItemView capsuleItemView2, boolean z) {
            this.appearView = capsuleItemView;
            this.disappearView = capsuleItemView2;
            this.isAddNewItem = z;
        }

        public final CapsuleItemView component1() {
            return this.appearView;
        }

        public final CapsuleItemView component2() {
            return this.disappearView;
        }

        public final ItemPendingAnimAction copy(CapsuleItemView capsuleItemView, CapsuleItemView capsuleItemView2, boolean z) {
            return new ItemPendingAnimAction(capsuleItemView, capsuleItemView2, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ItemPendingAnimAction)) {
                return false;
            }
            ItemPendingAnimAction itemPendingAnimAction = (ItemPendingAnimAction) obj;
            return Intrinsics.areEqual(this.appearView, itemPendingAnimAction.appearView) && Intrinsics.areEqual(this.disappearView, itemPendingAnimAction.disappearView) && this.isAddNewItem == itemPendingAnimAction.isAddNewItem;
        }

        public final CapsuleItemView getAppearView() {
            return this.appearView;
        }

        public final CapsuleItemView getDisappearView() {
            return this.disappearView;
        }

        public final int hashCode() {
            ExpandableView expandableView = this.appearView;
            int iHashCode = (expandableView == null ? 0 : expandableView.hashCode()) * 31;
            ExpandableView expandableView2 = this.disappearView;
            return Boolean.hashCode(this.isAddNewItem) + ((iHashCode + (expandableView2 != null ? expandableView2.hashCode() : 0)) * 31);
        }

        public String toString() {
            CapsuleItemView capsuleItemView = this.appearView;
            CapsuleItemView capsuleItemView2 = this.disappearView;
            StringBuilder sb = new StringBuilder("ItemPendingAnimAction(appearView=");
            sb.append(capsuleItemView);
            sb.append(", disappearView=");
            sb.append(capsuleItemView2);
            sb.append(", isAddNewItem=");
            return AppCompatDelegateImpl$.ExternalSyntheticOutline0.m(")", sb, this.isAddNewItem);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.animtor.itemview.CapsuleItemBaseAnimator$doAddAnimation$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Choreographer.FrameCallback {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ CapsuleItemBaseAnimator this$0;

        public /* synthetic */ AnonymousClass2(int i) {
            this.$r8$classId = i;
        }

        @Override // android.view.Choreographer.FrameCallback
        public final void doFrame(long j) {
            switch (this.$r8$classId) {
                case 0:
                    CapsuleItemBaseAnimator.access$executePendingAction(this.this$0);
                    break;
                default:
                    CapsuleItemBaseAnimator.access$executePendingAction(this.this$0);
                    break;
            }
        }
    }

    public CapsuleItemBaseAnimator(NotificationCapsuleContainer notificationCapsuleContainer, Function0 function0) {
        this.hostLayout = notificationCapsuleContainer;
        this.onAnimationFinished = function0;
    }

    public static final void access$executePendingAction(CapsuleItemBaseAnimator capsuleItemBaseAnimator) {
        Object next;
        Object obj;
        CapsuleItemView capsuleItemView;
        List mutableList = CollectionsKt___CollectionsKt.toMutableList(capsuleItemBaseAnimator.pendingActionList);
        if (mutableList.isEmpty()) {
            return;
        }
        capsuleItemBaseAnimator.pendingActionList.clear();
        List list = mutableList;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((ItemPendingAnimAction) it.next()).getDisappearView());
        }
        int size = arrayList.size();
        int i = 0;
        do {
            next = null;
            if (i >= size) {
                obj = null;
                break;
            } else {
                obj = arrayList.get(i);
                i++;
            }
        } while (!Intrinsics.areEqual((CapsuleItemView) obj, capsuleItemBaseAnimator.getHostLayoutHelper().getAmbientState().getBaseCard()));
        CapsuleItemView capsuleItemView2 = (CapsuleItemView) obj;
        if (capsuleItemView2 == null) {
            capsuleItemView2 = (CapsuleItemView) CollectionsKt___CollectionsKt.firstOrNull(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : list) {
            if (((ItemPendingAnimAction) obj2).isAddNewItem) {
                arrayList2.add(obj2);
            }
        }
        ItemPendingAnimAction itemPendingAnimAction = (ItemPendingAnimAction) CollectionsKt___CollectionsKt.lastOrNull(CollectionsKt___CollectionsKt.toList(arrayList2));
        CapsuleItemView appearView = itemPendingAnimAction != null ? itemPendingAnimAction.getAppearView() : null;
        ArrayList arrayList3 = new ArrayList();
        for (Object obj3 : list) {
            if (!((ItemPendingAnimAction) obj3).isAddNewItem) {
                arrayList3.add(obj3);
            }
        }
        List list2 = CollectionsKt___CollectionsKt.toList(arrayList3);
        ArrayList arrayList4 = new ArrayList();
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            CapsuleItemView appearView2 = ((ItemPendingAnimAction) it2.next()).getAppearView();
            if (appearView2 != null) {
                arrayList4.add(appearView2);
            }
        }
        if (arrayList4.size() <= 1) {
            capsuleItemView = (CapsuleItemView) CollectionsKt___CollectionsKt.firstOrNull(arrayList4);
        } else {
            Iterator it3 = arrayList4.iterator();
            if (it3.hasNext()) {
                next = it3.next();
                if (it3.hasNext()) {
                    int orderPriority = ((CapsuleItemView) next).getEntry().getOrderPriority();
                    do {
                        Object next2 = it3.next();
                        int orderPriority2 = ((CapsuleItemView) next2).getEntry().getOrderPriority();
                        if (orderPriority > orderPriority2) {
                            next = next2;
                            orderPriority = orderPriority2;
                        }
                    } while (it3.hasNext());
                }
            }
            capsuleItemView = (CapsuleItemView) next;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemAnimator-->".concat("executePendingAction disappear:" + capsuleItemView2 + ", oldAppear:" + capsuleItemView + ", newAppear:" + appearView));
        }
        if (appearView != null) {
            capsuleItemBaseAnimator.startAnimation(appearView, capsuleItemView2, true);
        } else {
            capsuleItemBaseAnimator.startAnimation(capsuleItemView, capsuleItemView2, false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void fixedContentWidth(FrameLayout frameLayout, CapsuleItemView capsuleItemView, int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            int width = capsuleItemView.getWidth();
            int width2 = frameLayout.getWidth();
            StringBuilder sb = new StringBuilder("fixedContentWidth(");
            sb.append(capsuleItemView);
            sb.append("): parentViewWidth:");
            sb.append(width);
            sb.append(",  ");
            Log.d("SystemUi--Notification", NotificationPanelViewController$.ExternalSyntheticOutline0.m(width2, i, LogUtil.SEPARATOR, "CapsuleItemAnimator-->", sb));
        }
        capsuleItemView.getLocation().setContentWidth(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void afterAppearAnim(CapsuleItemView capsuleItemView) {
        if (capsuleItemView == 0) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemAnimator-->".concat("InitOrFinallyAnim: afterAppearAnim: " + capsuleItemView));
        }
        capsuleItemView.isAppearAnimating().set(false);
        View contentView = capsuleItemView.getEntry().getContentView();
        CapsuleNotificationContainerView capsuleNotificationContainerView = contentView instanceof CapsuleNotificationContainerView ? (CapsuleNotificationContainerView) contentView : null;
        if (capsuleNotificationContainerView != null) {
            capsuleNotificationContainerView.setHasNewNotificationForCurrentApp(false);
        }
        fixedContentWidth(capsuleItemView.getContentContainer(), capsuleItemView, capsuleItemView.getActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic());
        capsuleItemView.getContentContainer().setTranslationY(0.0f);
        capsuleItemView.getContentContainer().setAlpha(1.0f);
        resetCenterPivotXY(capsuleItemView);
        ((ViewState) capsuleItemView.getViewState()).hidden = false;
        capsuleItemView.getViewState().setZTranslation(2.0f);
        capsuleItemView.getViewState().setWidth(CapsuleViewExpandKtKt.getCurrentCardShowWidth(this.hostLayout));
        capsuleItemView.getViewState().setFinalWidth(CapsuleViewExpandKtKt.getCurrentCardShowWidth(this.hostLayout));
        capsuleItemView.getViewState().setAlpha(1.0f, "CapsuleItemAnimator.afterAppearAnim");
        capsuleItemView.getViewState().setScaleX(1.0f);
        capsuleItemView.getViewState().setScaleY(1.0f);
        capsuleItemView.getViewState().setScrollClipInfo(new CapsuleViewState.ScrollClipInfo(0, 0, 15));
        capsuleItemView.getViewState().applyToView(capsuleItemView);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void afterDisappearAnim(CapsuleItemView capsuleItemView) {
        if (capsuleItemView == 0) {
            return;
        }
        capsuleItemView.isAppearAnimating().set(false);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemAnimator-->".concat("InitOrFinallyAnim: afterDisappearAnim: " + capsuleItemView));
        }
        fixedContentWidth(capsuleItemView.getContentContainer(), capsuleItemView, CapsuleViewExpandKtKt.getCurrentCardShowWidth(this.hostLayout));
        capsuleItemView.getContentContainer().setTranslationY(0.0f);
        capsuleItemView.getContentContainer().setAlpha(1.0f);
        resetCenterPivotXY(capsuleItemView);
        ((ViewState) capsuleItemView.getViewState()).hidden = true;
        capsuleItemView.getViewState().setZTranslation(0.0f);
        capsuleItemView.getViewState().setWidth(CapsuleViewExpandKtKt.getCurrentCardShowWidth(this.hostLayout));
        capsuleItemView.getViewState().setAlpha(1.0f, "CapsuleItemAnimator.afterDisappearAnim");
        capsuleItemView.getViewState().setScaleX(1.0f);
        capsuleItemView.getViewState().setScaleY(1.0f);
        capsuleItemView.getViewState().setScrollClipInfo(new CapsuleViewState.ScrollClipInfo(0, 0, 15));
        capsuleItemView.getViewState().applyToView(capsuleItemView);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void beforeAppearAnim(CapsuleItemView capsuleItemView) {
        if (capsuleItemView == 0) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemAnimator-->".concat("InitOrFinallyAnim: beforeAppearAnim: " + capsuleItemView));
        }
        capsuleItemView.isAppearAnimating().set(true);
        fixedContentWidth(capsuleItemView.getContentContainer(), capsuleItemView, CapsuleViewExpandKtKt.getCurrentCardShowWidth(this.hostLayout));
        resetCenterPivotXY(capsuleItemView);
        capsuleItemView.getContentContainer().setAlpha(0.0f);
        ((ViewState) capsuleItemView.getViewState()).hidden = false;
        capsuleItemView.getViewState().setZTranslation(0.0f);
        capsuleItemView.getViewState().setWidth(CapsuleViewExpandKtKt.getCurrentCardShowWidth(this.hostLayout));
        capsuleItemView.getViewState().setFinalWidth(CapsuleViewExpandKtKt.getCurrentCardShowWidth(this.hostLayout));
        capsuleItemView.getViewState().setAlpha(0.0f, "CapsuleItemAnimator.beforeAppearAnim");
        capsuleItemView.getViewState().setScaleX(1.0f);
        capsuleItemView.getViewState().setScaleY(1.0f);
        capsuleItemView.getViewState().setScrollClipInfo(new CapsuleViewState.ScrollClipInfo(0, 0, 15));
        AutoRoundRectPathProvider.updateMetaData$default(capsuleItemView.getPathProvider(), Float.valueOf(CapsuleConfig.INSTANCE.getDefaultRadius()), null, null, 6);
        capsuleItemView.getViewState().applyToView(capsuleItemView);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void beforeDisappearAnim(CapsuleItemView capsuleItemView) {
        if (capsuleItemView == 0) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemAnimator-->".concat("InitOrFinallyAnim: beforeDisappearAnim: " + capsuleItemView));
        }
        capsuleItemView.isAppearAnimating().set(true);
        fixedContentWidth(capsuleItemView.getContentContainer(), capsuleItemView, capsuleItemView.getActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic());
        capsuleItemView.getContentContainer().setAlpha(1.0f);
        resetCenterPivotXY(capsuleItemView);
        ((ViewState) capsuleItemView.getViewState()).hidden = false;
        capsuleItemView.getViewState().setZTranslation(2.0f);
        capsuleItemView.getViewState().setWidth(capsuleItemView.getActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic());
        capsuleItemView.getViewState().setFinalWidth(capsuleItemView.getActualWidth$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic());
        capsuleItemView.getViewState().setAlpha(1.0f, "CapsuleItemAnimator.beforeDisappearAnim");
        capsuleItemView.getViewState().setScaleX(1.0f);
        capsuleItemView.getViewState().setScaleY(1.0f);
        capsuleItemView.getViewState().setScrollClipInfo(new CapsuleViewState.ScrollClipInfo(0, 0, 15));
        capsuleItemView.getViewState().applyToView(capsuleItemView);
    }

    public final void doAddAnimation(CapsuleItemView capsuleItemView, CapsuleItemView capsuleItemView2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemAnimator-->".concat("doAddAnimation:" + capsuleItemView2 + LogUtil.SEPARATOR + capsuleItemView));
        }
        this.pendingActionList.add(new ItemPendingAnimAction(capsuleItemView, capsuleItemView2, true));
        Choreographer choreographer = Choreographer.getInstance();
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(0);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        choreographer.postFrameCallback(anonymousClass2);
    }

    public final void doRemoveAnimation(CapsuleItemView capsuleItemView, CapsuleItemView capsuleItemView2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemAnimator-->".concat("doRemoveAnimation:" + capsuleItemView + LogUtil.SEPARATOR + capsuleItemView2));
        }
        this.pendingActionList.add(new ItemPendingAnimAction(capsuleItemView2, capsuleItemView, false));
        Choreographer choreographer = Choreographer.getInstance();
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(1);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        choreographer.postFrameCallback(anonymousClass2);
    }

    public final Context getContext() {
        return this.hostLayout.getContext();
    }

    public final NotificationCapsuleContainer getHostLayout() {
        return this.hostLayout;
    }

    public final ContainerAnimatorViewHelper getHostLayoutHelper() {
        return this.hostLayout.getAnimatorHelper();
    }

    public final Function0 getOnAnimationFinished() {
        return this.onAnimationFinished;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void onItemAnimatorFinished(boolean z) {
        if (z) {
            CapsuleDataPinHandler.INSTANCE.startPinTimeout();
        }
        for (ExpandableView expandableView : this.renderBoosterViews) {
            expandableView.getClass();
            RenderBooster renderBooster = RenderBooster.Companion.renderBooster(expandableView);
            if (renderBooster != null) {
                renderBooster.requestStop(0);
            }
        }
        this.renderBoosterViews.clear();
        CapsuleViewExpandKtKt.enableHorizontalScroll(this.hostLayout, true, "onItemAnimationEnd");
        this.hostLayout.getDataInterceptor().unIntercept();
        this.onAnimationFinished.invoke();
        this.isRunning = false;
    }

    public final boolean readyToAnimAddORemove(CapsuleItemView capsuleItemView) {
        if (this.pendingActionList.isEmpty()) {
            return false;
        }
        List<ItemPendingAnimAction> list = this.pendingActionList;
        ArrayList arrayList = new ArrayList();
        for (ItemPendingAnimAction itemPendingAnimAction : list) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, CollectionsKt__CollectionsKt.listOfNotNull(new CapsuleItemView[]{itemPendingAnimAction.getAppearView(), itemPendingAnimAction.getDisappearView()}));
        }
        return arrayList.contains(capsuleItemView);
    }

    public final void resetCenterPivotXY(CapsuleItemView capsuleItemView) {
        FrameLayout contentContainer = capsuleItemView.getContentContainer();
        ViewExpandKtKt.setPivotXY(contentContainer, contentContainer.getWidth() / 2.0f, contentContainer.getHeight() / 2.0f);
        ViewExpandKtKt.setScaleXY(contentContainer, 1.0f, 1.0f);
    }

    public final void setRunning(boolean z) {
        this.isRunning = z;
    }

    public final void startAnimation(CapsuleItemView capsuleItemView, CapsuleItemView capsuleItemView2, boolean z) {
        this.isRunning = true;
        CapsuleViewExpandKtKt.enableHorizontalScroll(this.hostLayout, false, "onItemAnimationStart");
        this.hostLayout.getDataInterceptor().intercept();
        List<ItemPendingAnimAction> list = this.pendingActionList;
        ArrayList arrayList = new ArrayList();
        for (ItemPendingAnimAction itemPendingAnimAction : list) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, CollectionsKt__CollectionsKt.listOfNotNull(new CapsuleItemView[]{itemPendingAnimAction.getAppearView(), itemPendingAnimAction.getDisappearView()}));
        }
        for (ExpandableView expandableView : CollectionsKt___CollectionsKt.distinct(arrayList)) {
            expandableView.getClass();
            RenderBooster.requestBooster$default(RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, expandableView, null, 3), 11, false, 6);
            this.renderBoosterViews.add((CapsuleItemView) expandableView);
        }
        startAnimationImpl(capsuleItemView, capsuleItemView2, z);
    }

    public abstract void startAnimationImpl(CapsuleItemView capsuleItemView, CapsuleItemView capsuleItemView2, boolean z);
}
