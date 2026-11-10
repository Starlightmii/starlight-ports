package com.oplus.systemui.notification.stackednotification.ruler;

import android.content.Context;
import android.content.res.Configuration;
import android.util.MathUtils;
import android.view.View;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.DependencyEx;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.domain.interactor.ShadeModeInteractor;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.notification.stack.SectionHeaderView;
import com.android.systemui.statusbar.notification.stack.SectionHeaderViewEx;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.kotlin.FlowDumperImpl;
import com.android.systemui.util.kotlin.UtilsKt;
import com.oplus.systemui.notification.lockscreen.stack.algorithm.StackAlgorithmManager;
import com.oplus.systemui.notification.stackednotification.NotificationStackedRuler;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.statusbar.notification.OplusCloseAllController;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ShadeNotificationStackedRuler extends FlowDumperImpl implements NotificationStackedRuler {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ShadeNotificationStackedRuler";
    private final MutableStateFlow _isStackedNotification;
    private final OplusCloseAllController closeController;
    private final Context context;
    public boolean currentIsStackedNotification;
    private final LogBuffer logBuffer;
    private final Lazy npvc;
    public int screenHeight;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;
    private final Lazy shadeModeInteractor;
    private final StackAlgorithmManager stackAlgorithmManager;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.stackednotification.ruler.ShadeNotificationStackedRuler$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements ConfigurationController.ConfigurationListener {
        public /* synthetic */ ShadeNotificationStackedRuler this$0;

        public final void onConfigChanged(Configuration configuration) {
            ShadeNotificationStackedRuler.access$onConfigChanged(this.this$0);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public ShadeNotificationStackedRuler(ConfigurationController configurationController, Context context, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, OplusCloseAllController oplusCloseAllController, Lazy lazy, LogBuffer logBuffer, Lazy lazy2, StackAlgorithmManager stackAlgorithmManager, DumpManager dumpManager) {
        super(dumpManager, (String) null);
        this.context = context;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.closeController = oplusCloseAllController;
        this.npvc = lazy;
        this.logBuffer = logBuffer;
        this.shadeModeInteractor = lazy2;
        this.stackAlgorithmManager = stackAlgorithmManager;
        this.screenHeight = UtilsKt.toPx(context.getResources().getConfiguration().screenHeightDp, context);
        this.currentIsStackedNotification = m102isStackedNotification();
        this._isStackedNotification = dumpValue(StateFlowKt.MutableStateFlow(Boolean.valueOf(getCurrentIsStackedNotification())), "ShadeNotificationStackedRuler-isStackedNotification");
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        configurationController.addCallback(anonymousClass1);
    }

    public static final void access$onConfigChanged(ShadeNotificationStackedRuler shadeNotificationStackedRuler) {
        shadeNotificationStackedRuler.screenHeight = UtilsKt.toPx(shadeNotificationStackedRuler.context.getResources().getConfiguration().screenHeightDp, shadeNotificationStackedRuler.context);
        shadeNotificationStackedRuler.setCurrentIsStackedNotification(shadeNotificationStackedRuler.m102isStackedNotification());
        shadeNotificationStackedRuler._isStackedNotification.setValue(Boolean.valueOf(shadeNotificationStackedRuler.getCurrentIsStackedNotification()));
    }

    @Override // com.oplus.systemui.notification.stackednotification.NotificationStackedRuler
    public boolean getCurrentIsStackedNotification() {
        return this.currentIsStackedNotification;
    }

    /* JADX INFO: renamed from: isStackedNotification, reason: collision with other method in class */
    public final boolean m102isStackedNotification() {
        boolean z = this.screenHeight > this.context.getResources().getDimensionPixelOffset(2131172950);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isStackedNotificationForShade: ", z, ", screenHeight=", this.screenHeight), null, 8);
        return z;
    }

    @Override // com.oplus.systemui.notification.stackednotification.NotificationStackedRuler
    public /* bridge */ /* synthetic */ void prepareAddOrRemoveAnimationForOverlay(View view, View view2, boolean z) {
        super.prepareAddOrRemoveAnimationForOverlay(view, view2, z);
    }

    public void setCurrentIsStackedNotification(boolean z) {
        this.currentIsStackedNotification = z;
    }

    @Override // com.oplus.systemui.notification.stackednotification.NotificationStackedRuler
    public float shelfPosition(AmbientState ambientState) {
        if (ambientState.mStatusBarState != 0 || ((Boolean) this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue() || ((ShadeModeInteractor) this.shadeModeInteractor.get()).isSplitShade()) {
            return ambientState.mStatusBarState == 0 ? ambientState.getLayoutMaxHeight() : this.closeController.viewTop();
        }
        return (ambientState.getStackY() - ((NotificationPanelViewController) this.npvc.get()).getMinExpansionHeight()) + ambientState.getLayoutMaxHeight();
    }

    @Override // com.oplus.systemui.notification.stackednotification.NotificationStackedRuler
    public void updateStackedState(List<? extends ExpandableView> list, AmbientState ambientState, float f) {
        List visibleChildren;
        if (list.isEmpty()) {
            return;
        }
        if (!getCurrentIsStackedNotification()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableView) it.next();
                if (expandableNotificationRow instanceof ExpandableNotificationRow) {
                    ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
                    if (expandableNotificationRow2.mIsSummaryWithChildren && expandableNotificationRow2.isGroupExpanded() && expandableNotificationRow2.getChildrenContainer().getAttachedChildren().size() == 1) {
                        ExpandableViewState viewState = expandableNotificationRow2.getViewState();
                        View viewFindViewById = expandableNotificationRow.findViewById(2131364384);
                        if (viewFindViewById == null) {
                            return;
                        }
                        float notificationChildrenContainerDivider = this.stackAlgorithmManager.currentDynamicStackDimens().getNotificationChildrenContainerDivider() + viewFindViewById.getHeight();
                        ExpandableNotificationRow expandableNotificationRow3 = (ExpandableNotificationRow) CollectionsKt___CollectionsKt.first(expandableNotificationRow2.getChildrenContainer().getAttachedChildren());
                        if (expandableNotificationRow3.getTranslationY() <= notificationChildrenContainerDivider) {
                            float translationY = notificationChildrenContainerDivider - expandableNotificationRow3.getTranslationY();
                            OplusExpandableViewStateEx oplusExpandableViewStateEx = viewState.mExpandableViewStateEx;
                            StackAlgorithmManager.Companion.getClass();
                            oplusExpandableViewStateEx.setGroupExpandHeaderAlpha(RangesKt___RangesKt.coerceIn(MathUtils.lerp(1.0f, 0.0f, translationY / notificationChildrenContainerDivider), 0.0f, 1.0f));
                        }
                    }
                }
            }
            return;
        }
        this.stackAlgorithmManager.applyDynamic(list, shelfPosition(ambientState));
        if (list.isEmpty() || getCurrentIsStackedNotification()) {
            return;
        }
        SectionHeaderViewEx sectionHeaderViewEx = (SectionHeaderViewEx) DependencyEx.sDependency.getDependency(SectionHeaderViewEx.class);
        NotificationStackScrollLayout view = ((NotificationStackScrollLayoutExt) DependencyEx.sDependency.getDependency(NotificationStackScrollLayoutExt.class)).getView();
        if (view == null || (visibleChildren = view.getVisibleChildren()) == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : visibleChildren) {
            if (obj instanceof SectionHeaderView) {
                arrayList.add(obj);
            }
        }
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj2 = arrayList.get(i);
            i++;
            sectionHeaderViewEx.checkIfNeedUpdateSectionHeaderBlurRadius((SectionHeaderView) obj2, 0.0f);
        }
    }

    @Override // com.oplus.systemui.notification.stackednotification.NotificationStackedRuler
    public StateFlow isStackedNotification() {
        return this._isStackedNotification;
    }
}
