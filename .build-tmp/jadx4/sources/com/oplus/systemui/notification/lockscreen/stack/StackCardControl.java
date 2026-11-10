package com.oplus.systemui.notification.lockscreen.stack;

import android.content.Context;
import android.util.Log;
import android.util.PluralsMessageFormatter;
import androidx.collection.FloatList$;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVectorKt$;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import com.android.systemui.animation.LaunchableView;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.ui.controller.MediaPlayerData;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator;
import com.android.systemui.statusbar.notification.animation.RowAnimationStateEx;
import com.android.systemui.statusbar.notification.animation.ScaleState;
import com.android.systemui.statusbar.notification.emptyshade.ui.view.EmptyShadeView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewProxy;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.MediaContainerView;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.notification.stack.SectionHeaderView;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.coe.expr.Expressions$28$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.biometrics.OplusBiometricAuthController;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataPinHandler;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.effect.ExtStateAnimParams;
import com.oplus.systemui.notification.lockscreen.effect.OplusPhysicsPropertyAnimatorExtKt;
import com.oplus.systemui.notification.lockscreen.immersive.ImmersiveCardControl;
import com.oplus.systemui.notification.lockscreen.seedling.ImmersiveViewEntry;
import com.oplus.systemui.notification.lockscreen.seedling.SeedlingImmersiveViewState;
import com.oplus.systemui.notification.lockscreen.stack.algorithm.StackAlgorithmManager;
import com.oplus.systemui.notification.lockscreen.stack.algorithm.StaticStackAlgorithm;
import com.oplus.systemui.notification.lockscreen.stack.updater.CapsuleUpdater;
import com.oplus.systemui.notification.lockscreen.stack.updater.ImmersiveUpdater;
import com.oplus.systemui.notification.lockscreen.stack.updater.SinkingStackUpdater;
import com.oplus.systemui.notification.lockscreen.stack.updater.TopStackUpdater;
import com.oplus.systemui.notification.lockscreen.statemanager.FocusCard;
import com.oplus.systemui.notification.lockscreen.statemanager.ICardControl;
import com.oplus.systemui.notification.lockscreen.statemanager.ICardControlKt;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateManager;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationRectManager;
import com.oplus.systemui.notification.lockscreen.statemanager.NotificationDisplayRectCallbackWrapper;
import com.oplus.systemui.notification.lockscreen.statemanager.SeedlingFocusCard;
import com.oplus.systemui.notification.row.overlay.OplusNotificationOverlayInteractor;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl;
import com.oplus.systemui.statusbar.notification.stack.OplusExpandableViewStateExImpl;
import com.oplus.systemui.statusbar.notification.stack.OplusExpandableViewStateExImplKt;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StackCardControl implements ICardControl {
    private static final String EMPTY_SUMMARY_INFO = "";
    private static final String NOTIFICATION_COUNT_KEY = "count";
    private static final String TAG = "StackCardControl";
    private final Lazy ambientState;
    private final Lazy biometricAuthController;
    private final Lazy capsuleCardControl;
    private final Lazy capsuleContainer;
    private final IStackViewUpdater capsuleUpdater;
    private final Context context;
    private final Lazy extNssl;
    private final Lazy immersiveCardControl;
    private final IStackViewUpdater immersiveUpdater;
    public boolean isExtStateAnimFirstFrame;
    private final Lazy lockScreenExtStateController;
    private final Lazy lockScreenNotificationRectManager;
    private final LogBuffer logBuffer;
    private final Lazy npvc;
    private final Lazy nsslController;
    private final OplusNotificationOverlayInteractor overlayInteractor;
    private final Runnable pointRunnable;
    private final OplusSeparateNotificationAndQSState separateNotificationAndQSState;
    private final OplusLockscreenShadeTransitionControllerEx shadeTransitionControllerEx;
    private final IStackViewUpdater sinkingStackUpdater;
    private final StackAlgorithmManager stackAlgorithmManager;
    private final Lazy statusBarKeyguardViewManagerLazy;
    private final TopStackUpdater topStackUpdater;
    public static final Companion Companion = new Companion();
    private static final List<String> APP_NAME_KEYS = CollectionsKt__CollectionsKt.listOf(new String[]{"APP1", "APP2", "APP3", "APP4"});
    private static final List<Integer> SUMMARY_INFO_STRING_RES = CollectionsKt__CollectionsKt.listOf(new Integer[]{2131954976, 2131954977, 2131954978, 2131954979, 2131954981});

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX WARN: Code duplicated, block: B:7:0x0021  */
        public static int getNotificationCount(List list) {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            Iterator it = list.iterator();
            while (true) {
                int untruncatedChildCount = 0;
                if (!it.hasNext()) {
                    break;
                }
                ExpandableNotificationRow expandableNotificationRow = (ExpandableView) it.next();
                if (expandableNotificationRow instanceof OplusCustomRow) {
                    untruncatedChildCount = 1;
                } else if (expandableNotificationRow instanceof ExpandableNotificationRow) {
                    ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
                    NotificationChildrenContainer childrenContainer = expandableNotificationRow2.getChildrenContainer();
                    if (!expandableNotificationRow2.mIsSummaryWithChildren || childrenContainer == null) {
                        untruncatedChildCount = 1;
                    } else {
                        untruncatedChildCount = childrenContainer.getUntruncatedChildCount();
                    }
                } else if (expandableNotificationRow instanceof MediaContainerView) {
                    untruncatedChildCount = 1;
                }
                arrayList.add(Integer.valueOf(untruncatedChildCount));
            }
            Iterator it2 = arrayList.iterator();
            if (!it2.hasNext()) {
                Expressions$28$.ExternalSyntheticBUOutline0.m("Empty collection can't be reduced.");
                return 0;
            }
            Object next = it2.next();
            while (it2.hasNext()) {
                next = Integer.valueOf(((Number) next).intValue() + ((Number) it2.next()).intValue());
            }
            return ((Number) next).intValue();
        }

        public final float getStackViewScaleInCapsuleState(ExpandableView expandableView, int i) {
            float fFloatValue;
            OplusExpandableViewProxy proxy = expandableView.mExpandableViewEx.getProxy();
            if ((proxy != null ? proxy.getProxyView() : null) != null) {
                fFloatValue = 1.0f;
            } else if (i == 1) {
                fFloatValue = ExtStateAnimParams.StackToCapsule.INSTANCE.getFIREST_ITEM_SCALE().get().floatValue();
            } else {
                ExtStateAnimParams.StackToCapsule stackToCapsule = ExtStateAnimParams.StackToCapsule.INSTANCE;
                fFloatValue = stackToCapsule.getFIREST_ITEM_SCALE().get().floatValue() - (stackToCapsule.getITEM_SCALE_DIFF().get().floatValue() * (i - 1));
            }
            float fFloatValue2 = ExtStateAnimParams.StackToCapsule.INSTANCE.getITEM_SCALE_MINI().get().floatValue();
            return fFloatValue < fFloatValue2 ? fFloatValue2 : fFloatValue;
        }

        public final SummaryInfo updateKgNotificationSummaryInfo(List<? extends ExpandableView> list) {
            Object obj;
            String app;
            MediaPlayerData.MediaSortKey mediaSortKey;
            MediaData data;
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                ExpandableView expandableView = (ExpandableView) next;
                if ((expandableView instanceof ExpandableNotificationRow) || (expandableView instanceof OplusCustomRow) || (expandableView instanceof MediaContainerView)) {
                    OplusExpandableViewStateEx oplusExpandableViewStateEx = expandableView.getViewState().mExpandableViewStateEx;
                    if (Intrinsics.areEqual(oplusExpandableViewStateEx != null ? Float.valueOf(oplusExpandableViewStateEx.getNotificationStackedProgress()) : null, 1.0f)) {
                        arrayList.add(next);
                    }
                }
            }
            if (arrayList.size() >= 2 && !arrayList.isEmpty()) {
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj2 = arrayList.get(i);
                    i++;
                    ExpandableView expandableView2 = (ExpandableView) obj2;
                    if ((expandableView2 instanceof ExpandableNotificationRow) || (expandableView2 instanceof MediaContainerView)) {
                        boolean zIsEmpty = arrayList.isEmpty();
                        String str = StackCardControl.EMPTY_SUMMARY_INFO;
                        if (!zIsEmpty) {
                            ArrayList arrayList2 = new ArrayList();
                            int size2 = arrayList.size();
                            int i2 = 0;
                            while (i2 < size2) {
                                Object obj3 = arrayList.get(i2);
                                i2++;
                                LaunchableView launchableView = (ExpandableView) obj3;
                                if (launchableView instanceof OplusCustomRow) {
                                    app = ((OplusCustomRow) launchableView).getResources().getString(2131954980);
                                } else if (launchableView instanceof ExpandableNotificationRow) {
                                    app = ((ExpandableNotificationRow) launchableView).getAppName();
                                } else {
                                    app = (!(launchableView instanceof MediaContainerView) || (mediaSortKey = (MediaPlayerData.MediaSortKey) CollectionsKt___CollectionsKt.firstOrNull(MediaPlayerData.INSTANCE.visiblePlayerKeys())) == null || (data = mediaSortKey.getData()) == null) ? null : data.getApp();
                                }
                                if (app != null) {
                                    arrayList2.add(app);
                                }
                            }
                            List listDistinct = CollectionsKt___CollectionsKt.distinct(arrayList2);
                            List list2 = StackCardControl.SUMMARY_INFO_STRING_RES;
                            int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(listDistinct);
                            int lastIndex2 = CollectionsKt__CollectionsKt.getLastIndex(StackCardControl.SUMMARY_INFO_STRING_RES);
                            if (lastIndex > lastIndex2) {
                                lastIndex = lastIndex2;
                            }
                            Integer num = (Integer) CollectionsKt___CollectionsKt.getOrNull(list2, lastIndex);
                            if (num != null) {
                                int iIntValue = num.intValue();
                                try {
                                    SnapshotStateMap mutableStateMap = SnapshotStateKt.toMutableStateMap(CollectionsKt___CollectionsKt.zip(StackCardControl.APP_NAME_KEYS, listDistinct));
                                    StackCardControl.Companion.getClass();
                                    mutableStateMap.put(StackCardControl.NOTIFICATION_COUNT_KEY, Integer.valueOf(getNotificationCount(arrayList)));
                                    obj = Result.constructor-impl(PluralsMessageFormatter.format(((ExpandableView) arrayList.get(0)).getResources(), mutableStateMap, iIntValue));
                                } catch (Throwable th) {
                                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                                }
                                Throwable th2 = Result.exceptionOrNull-impl(obj);
                                if (th2 != null) {
                                    LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, StackCardControl.TAG, "getStackedSummeryInfo : " + th2);
                                }
                                String str2 = (String) (obj instanceof Result.Failure ? null : obj);
                                if (str2 != null) {
                                    str = str2;
                                }
                            }
                        }
                        return new SummaryInfo((ExpandableView) CollectionsKt___CollectionsKt.firstOrNull(arrayList), str);
                    }
                }
            }
            return null;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class StackType {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ StackType[] $VALUES;
        public static final StackType DYNAMIC_STACK;
        public static final StackType NOT_STACK;
        public static final StackType STATIC_STACK;

        static {
            StackType stackType = new StackType("STATIC_STACK", 0);
            STATIC_STACK = stackType;
            StackType stackType2 = new StackType("DYNAMIC_STACK", 1);
            DYNAMIC_STACK = stackType2;
            StackType stackType3 = new StackType("NOT_STACK", 2);
            NOT_STACK = stackType3;
            StackType[] stackTypeArr = {stackType, stackType2, stackType3};
            $VALUES = stackTypeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(stackTypeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static StackType valueOf(String str) {
            return (StackType) Enum.valueOf(StackType.class, str);
        }

        public static StackType[] values() {
            return (StackType[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SummaryInfo {
        private final ExpandableView expandableView;
        private final CharSequence info;

        public SummaryInfo(ExpandableView expandableView, CharSequence charSequence) {
            this.expandableView = expandableView;
            this.info = charSequence;
        }

        public final ExpandableView component1() {
            return this.expandableView;
        }

        public final CharSequence component2() {
            return this.info;
        }

        public final SummaryInfo copy(ExpandableView expandableView, CharSequence charSequence) {
            return new SummaryInfo(expandableView, charSequence);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SummaryInfo)) {
                return false;
            }
            SummaryInfo summaryInfo = (SummaryInfo) obj;
            return Intrinsics.areEqual(this.expandableView, summaryInfo.expandableView) && Intrinsics.areEqual(this.info, summaryInfo.info);
        }

        public final ExpandableView getExpandableView() {
            return this.expandableView;
        }

        public final CharSequence getInfo() {
            return this.info;
        }

        public final int hashCode() {
            ExpandableView expandableView = this.expandableView;
            return this.info.hashCode() + ((expandableView == null ? 0 : expandableView.hashCode()) * 31);
        }

        public String toString() {
            return "SummaryInfo(expandableView=" + this.expandableView + ", info=" + ((Object) this.info) + ")";
        }
    }

    public StackCardControl(Context context, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, OplusNotificationOverlayInteractor oplusNotificationOverlayInteractor, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState, Lazy lazy7, Lazy lazy8, OplusLockscreenShadeTransitionControllerEx oplusLockscreenShadeTransitionControllerEx, Lazy lazy9, LogBuffer logBuffer, StackAlgorithmManager stackAlgorithmManager, Lazy lazy10, Lazy lazy11) {
        this.context = context;
        this.npvc = lazy;
        this.nsslController = lazy2;
        this.capsuleCardControl = lazy3;
        this.immersiveCardControl = lazy4;
        this.extNssl = lazy5;
        this.lockScreenExtStateController = lazy6;
        this.overlayInteractor = oplusNotificationOverlayInteractor;
        this.separateNotificationAndQSState = oplusSeparateNotificationAndQSState;
        this.statusBarKeyguardViewManagerLazy = lazy7;
        this.ambientState = lazy8;
        this.shadeTransitionControllerEx = oplusLockscreenShadeTransitionControllerEx;
        this.capsuleContainer = lazy9;
        this.logBuffer = logBuffer;
        this.stackAlgorithmManager = stackAlgorithmManager;
        this.lockScreenNotificationRectManager = lazy10;
        this.biometricAuthController = lazy11;
        this.topStackUpdater = new TopStackUpdater(oplusLockscreenShadeTransitionControllerEx, oplusNotificationOverlayInteractor, stackAlgorithmManager, lazy5, logBuffer);
        this.sinkingStackUpdater = new SinkingStackUpdater(context, lazy, lazy8, oplusLockscreenShadeTransitionControllerEx, oplusNotificationOverlayInteractor, oplusSeparateNotificationAndQSState, stackAlgorithmManager, lazy5, (LockScreenNotificationRectManager) lazy10.get(), logBuffer);
        this.immersiveUpdater = new ImmersiveUpdater(lazy3, oplusSeparateNotificationAndQSState, lazy7, lazy6);
        this.capsuleUpdater = new CapsuleUpdater(lazy3, lazy9);
        StackCardControl$pointRunnable$1 stackCardControl$pointRunnable$1 = new StackCardControl$pointRunnable$1(0);
        stackCardControl$pointRunnable$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.pointRunnable = stackCardControl$pointRunnable$1;
    }

    public static Pair finishDisplayRect(float f, float f2) {
        return (Math.abs(f) > Float.MAX_VALUE || Math.abs(f2) > Float.MAX_VALUE || f2 < f) ? NotificationDisplayRectCallbackWrapper.Companion.getINVALID_DISPLAY_RECT_PAIR() : new Pair(Float.valueOf(f), Float.valueOf(f2));
    }

    private final FocusCard getFocusCard() {
        return ((ImmersiveCardControl) this.immersiveCardControl.get()).getFocusCard();
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0047  */
    public static Pair visualBoundsOrNull(ExpandableView expandableView) {
        float fFloatValue;
        RowAnimationStateEx rowAnimationStateEx;
        ScaleState rowScale$default;
        ExpandableViewState viewState = expandableView.getViewState();
        if (!((ViewState) viewState).hidden && !((ViewState) viewState).gone && !(expandableView instanceof EmptyShadeView)) {
            int i = viewState.height - viewState.clipBottomAmount;
            if (i < 0) {
                i = 0;
            }
            float f = i;
            if (f > 0.0f) {
                OplusExpandableViewStateEx oplusExpandableViewStateEx = viewState.mExpandableViewStateEx;
                if (oplusExpandableViewStateEx == null || (rowAnimationStateEx = oplusExpandableViewStateEx.getRowAnimationStateEx()) == null || (rowScale$default = RowAnimationStateEx.getRowScale$default(rowAnimationStateEx)) == null) {
                    fFloatValue = 1.0f;
                } else {
                    float scaleY = rowScale$default.getScaleY();
                    Float fValueOf = scaleY > 0.0f ? Float.valueOf(scaleY) : null;
                    if (fValueOf != null) {
                        fFloatValue = fValueOf.floatValue();
                    } else {
                        fFloatValue = 1.0f;
                    }
                }
                return new Pair(Float.valueOf((((1.0f - fFloatValue) * f) / 2.0f) + viewState.getYTranslation()), Float.valueOf((((fFloatValue + 1.0f) * f) / 2.0f) + viewState.getYTranslation()));
            }
        }
        return null;
    }

    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    public ExpandableView findSeedingView(String str) {
        Object obj;
        Object next;
        boolean zAreEqual;
        Iterator it = ((NotificationStackScrollLayoutController) this.nsslController.get()).getView().getVisibleChildren().iterator();
        do {
            obj = null;
            if (it.hasNext()) {
                next = it.next();
                OplusCustomRow oplusCustomRow = (ExpandableView) next;
                if (oplusCustomRow instanceof OplusCustomRow) {
                    CustomRowEntry entry = oplusCustomRow.getEntry();
                    zAreEqual = Intrinsics.areEqual(entry != null ? entry.getServiceId() : null, str);
                } else {
                    zAreEqual = false;
                }
            }
            return (ExpandableView) obj;
        } while (!zAreEqual);
        obj = next;
        return (ExpandableView) obj;
    }

    public final StackType getCardStackType() {
        int newState = ((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getExtStateEvent().getValue()).getNewState();
        ILockScreenExtStateController.Companion.getClass();
        if (newState == 0) {
            return this.topStackUpdater.getStackType();
        }
        return newState == 3 ? this.sinkingStackUpdater.getStackType() : StackType.NOT_STACK;
    }

    public final ExpandableView getFirstCardForCapsule() {
        Object obj;
        Iterator it = ((NotificationStackScrollLayoutController) this.nsslController.get()).getView().getVisibleChildren().iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            OplusCustomRow oplusCustomRow = (ExpandableView) next;
            if (!(oplusCustomRow instanceof SectionHeaderView)) {
                FocusCard focusCard = getFocusCard();
                SeedlingFocusCard seedlingFocusCard = focusCard instanceof SeedlingFocusCard ? (SeedlingFocusCard) focusCard : null;
                if (seedlingFocusCard == null || !(oplusCustomRow instanceof OplusCustomRow) || !Intrinsics.areEqual(oplusCustomRow.getEntry(), seedlingFocusCard.getData())) {
                    obj = next;
                    break;
                }
            }
        }
        return (ExpandableView) obj;
    }

    public final int getFirstStackedVisibleHeight() {
        return (int) StaticStackAlgorithm.Companion.getFirstStackedNotificationVisibleHeight();
    }

    public final int getGapBetweenSeedlingCardAndFingerprint() {
        return (int) StaticStackAlgorithm.Companion.getGapBetweenSeedlingCardAndFingerprint();
    }

    public final boolean getHasNotificationStacked() {
        int newState = ((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getExtStateEvent().getValue()).getNewState();
        ILockScreenExtStateController.Companion.getClass();
        if (newState == 0) {
            return this.topStackUpdater.getHasNotificationStacked();
        }
        if (newState == 3) {
            return this.sinkingStackUpdater.getHasNotificationStacked();
        }
        if (newState == 1) {
            return this.capsuleUpdater.getHasNotificationStacked();
        }
        if (newState == 2) {
            return this.immersiveUpdater.getHasNotificationStacked();
        }
        return false;
    }

    public final int getPaddingBetweenElements() {
        return (int) StaticStackAlgorithm.Companion.getPaddingBetweenElements();
    }

    public final int getSecondStackedVisibleHeight() {
        return (int) StaticStackAlgorithm.Companion.getSecondStackedNotificationVisibleHeight();
    }

    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    public int getViewTypeFlag() {
        return 1;
    }

    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    public void hide(int i, int i2, boolean z, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("[hide] ", i, i2, " -> ", " animate:");
            sbM.append(z);
            sbM.append(" from:");
            sbM.append(str);
            Log.d("SystemUi--Notification", "StackCardControl-->".concat(sbM.toString()));
        }
        ((CapsuleCardControl) this.capsuleCardControl.get()).requestUpdateChild("stackHideTo");
        if (!z) {
            ((NotificationStackScrollLayoutController) this.nsslController.get()).getView().requestChildrenUpdate$1();
            return;
        }
        Pair pair = TuplesKt.to(Integer.valueOf(i), Integer.valueOf(i2));
        int i3 = 19;
        if (!Intrinsics.areEqual(pair, TuplesKt.to(0, 1)) && Intrinsics.areEqual(pair, TuplesKt.to(3, 1))) {
            i3 = 21;
        }
        ((NotificationStackScrollLayoutExtImpl) this.extNssl.get()).requestAnimateKgNotificationStateTransform(i3, ICardControlKt.getFilter());
    }

    public final boolean isSinkingStaticStackState() {
        ILockScreenExtStateController.Companion companion = ILockScreenExtStateController.Companion;
        int newState = ((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getExtStateEvent().getValue()).getNewState();
        companion.getClass();
        return newState == 3 && getCardStackType() == StackType.STATIC_STACK;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x005a  */
    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    public void show(int i, int i2, boolean z, String str) {
        CapsuleDataPinHandler capsuleDataPinHandler = CapsuleDataPinHandler.INSTANCE;
        capsuleDataPinHandler.cancelAllTimer("ShowStackList");
        boolean z2 = false;
        capsuleDataPinHandler.startNotifAggregate(false, "ShowStackList");
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("[show] ", i, i2, "->", " animate:");
            sbM.append(z);
            sbM.append(" from:");
            sbM.append(str);
            Log.d("SystemUi--Notification", "StackCardControl-->".concat(sbM.toString()));
        }
        if (((CapsuleCardControl) this.capsuleCardControl.get()).getEventChangeToAnimation()) {
            z2 = true;
        } else {
            CapsuleConfig.INSTANCE.getClass();
            if (CapsuleConfig.isLightOs && (i2 == 0 || i2 == 3)) {
                z2 = true;
            }
        }
        this.isExtStateAnimFirstFrame = z2;
        if (!z) {
            ((NotificationStackScrollLayoutController) this.nsslController.get()).getView().requestChildrenUpdate$1();
            return;
        }
        if (i2 == 0 || i2 == 3 || ((i == 1 && i2 == 2) || (i == 2 && i2 == 2))) {
            ((NotificationStackScrollLayoutExtImpl) this.extNssl.get()).requestAnimateKgNotificationStateTransform(i2 == 3 ? 20 : 18, ICardControlKt.getFilter());
        } else {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "[show] unexpected requestChildrenUpdate case");
            ((NotificationStackScrollLayoutController) this.nsslController.get()).getView().requestChildrenUpdate$1();
        }
    }

    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    /* JADX INFO: renamed from: switch */
    public void mo73switch(int i, int i2, boolean z, String str) {
        int i3;
        NotificationStackScrollLayout view;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("[switch] ", i, i2, "->", " animate:");
            sbM.append(z);
            sbM.append(" from:");
            sbM.append(str);
            Log.d("SystemUi--Notification", "StackCardControl-->".concat(sbM.toString()));
        }
        if (!z) {
            ((NotificationStackScrollLayoutController) this.nsslController.get()).getView().requestChildrenUpdate$1();
            return;
        }
        Pair pair = TuplesKt.to(Integer.valueOf(i), Integer.valueOf(i2));
        if (Intrinsics.areEqual(pair, TuplesKt.to(0, 3))) {
            i3 = 22;
        } else if (Intrinsics.areEqual(pair, TuplesKt.to(3, 0))) {
            i3 = 23;
        } else {
            if (!Intrinsics.areEqual(pair, TuplesKt.to(0, 2)) && !Intrinsics.areEqual(pair, TuplesKt.to(2, 0)) && !Intrinsics.areEqual(pair, TuplesKt.to(2, 2)) && !Intrinsics.areEqual(pair, TuplesKt.to(3, 2)) && !Intrinsics.areEqual(pair, TuplesKt.to(2, 3))) {
                LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, MutableVectorKt$.ExternalSyntheticOutline0.m("[switch] unexpected invoke : ", i, i2, "->", OplusPreventModeData.SEPARATOR));
                ((NotificationStackScrollLayoutController) this.nsslController.get()).getView().requestChildrenUpdate$1();
                return;
            }
            i3 = 18;
        }
        ((NotificationStackScrollLayoutExtImpl) this.extNssl.get()).requestAnimateKgNotificationStateTransform(i3, ICardControlKt.getFilter());
        if (!StringsKt__StringsKt.contains(str, "ExpandClicked:sinking_to_stack", false) || (view = ((NotificationStackScrollLayoutController) this.nsslController.get()).getView()) == null) {
            return;
        }
        view.postOnAnimation(this.pointRunnable);
    }

    /* JADX WARN: Code duplicated, block: B:157:0x02e7  */
    /* JADX WARN: Code duplicated, block: B:77:0x0146  */
    @Override // com.oplus.systemui.notification.lockscreen.statemanager.ICardControl
    public void updateViewStateToExtState(int i, List<? extends ExpandableView> list) {
        float f;
        LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager;
        NotificationDisplayRectCallbackWrapper notificationDisplayRectCallbackWrapper;
        LockScreenNotificationExtStateManager lockScreenNotificationExtStateManagerVisualBoundsOrNull;
        LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager2;
        Pair<Float, Float> invalid_display_rect_pair;
        LockScreenNotificationExtStateManager invalid_display_rect_pair2;
        float stackedBottomBorder;
        LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager3;
        float f2;
        OplusCustomRow oplusCustomRow;
        ImmersiveViewEntry immersiveViewEntry;
        SeedlingImmersiveViewState viewState;
        SeedlingImmersiveViewState viewState2;
        SeedlingImmersiveViewState viewState3;
        List<? extends ExpandableView> list2 = list;
        Iterator<T> it = list2.iterator();
        while (true) {
            f = 1.0f;
            if (!it.hasNext()) {
                break;
            }
            OplusCustomRow oplusCustomRow2 = (ExpandableView) it.next();
            oplusCustomRow2.getViewState().mExpandableViewStateEx.setCapsuleProgress(0.0f);
            oplusCustomRow2.getViewState().mExpandableViewStateEx.setImmersiveProgress(0.0f);
            if ((oplusCustomRow2 instanceof OplusCustomRow) && ((immersiveViewEntry = (oplusCustomRow = oplusCustomRow2).getImmersiveViewEntry()) == null || immersiveViewEntry.getImmersiveState() != 0)) {
                if (i != 2) {
                    ImmersiveViewEntry immersiveViewEntry2 = oplusCustomRow.getImmersiveViewEntry();
                    if (immersiveViewEntry2 != null && (viewState = immersiveViewEntry2.getViewState()) != null) {
                        viewState.setToExtState(i);
                    }
                } else if (((Boolean) this.separateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue() || ((StatusBarKeyguardViewManager) this.statusBarKeyguardViewManagerLazy.get()).getQsExpansion() != 1.0f) {
                    ImmersiveViewEntry immersiveViewEntry3 = oplusCustomRow.getImmersiveViewEntry();
                    if (immersiveViewEntry3 != null && (viewState2 = immersiveViewEntry3.getViewState()) != null) {
                        viewState2.setToExtState(1);
                    }
                } else {
                    ImmersiveViewEntry immersiveViewEntry4 = oplusCustomRow.getImmersiveViewEntry();
                    if (immersiveViewEntry4 != null && (viewState3 = immersiveViewEntry4.getViewState()) != null) {
                        viewState3.setToExtState(0);
                    }
                }
            }
        }
        if (i == 0) {
            this.topStackUpdater.updateToExt(list);
        } else if (i == 1) {
            this.capsuleUpdater.updateToExt(list);
        } else if (i == 2) {
            this.immersiveUpdater.updateToExt(list);
        } else if (i == 3) {
            this.sinkingStackUpdater.updateToExt(list);
        }
        ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType animateType = ((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) this.lockScreenExtStateController.get()).getExtStateEvent().getValue()).getAnimateType();
        if (this.isExtStateAnimFirstFrame) {
            animateType.getClass();
            if (animateType == ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.CAPSULE_TO_SINKING || animateType == ILockScreenExtStateController.ExtStateEvent.ExtStateEventAnimType.CAPSULE_TO_STACK) {
                CapsuleConfig.INSTANCE.getClass();
                if (CapsuleConfig.isLightOs) {
                    for (ExpandableView expandableView : list2) {
                        if (!(expandableView instanceof EmptyShadeView) && !(expandableView instanceof NotificationShelf)) {
                            ExpandableViewState viewState4 = expandableView.getViewState();
                            if (expandableView.getVisibility() != 0 || ((ViewState) viewState4).hidden || ((ViewState) viewState4).gone) {
                                float capsuleProgress = expandableView.mExpandableViewEx.getCapsuleProgress();
                                if (((ViewState) viewState4).hidden || ((ViewState) viewState4).gone) {
                                    viewState4.cancelAnimations(expandableView);
                                }
                                viewState4.applyToView(expandableView);
                                expandableView.mExpandableViewEx.setCapsuleProgress(capsuleProgress);
                            }
                        }
                    }
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "handleStackViewForLightOsLeavingCapsule: currentState=", "StackCardControl-->", "SystemUi--Notification");
                    }
                } else {
                    ExpandableView currentShowingCard = ((CapsuleCardControl) this.capsuleCardControl.get()).getCurrentShowingCard();
                    if (currentShowingCard != null) {
                        int i2 = 0;
                        int i3 = 0;
                        for (Object obj : list2) {
                            int i4 = i2 + 1;
                            if (i2 < 0) {
                                CollectionsKt__CollectionsKt.throwIndexOverflow();
                                throw null;
                            }
                            ExpandableView expandableView2 = (ExpandableView) obj;
                            if (expandableView2.getVisibility() == 0) {
                                f2 = f;
                            } else {
                                ExpandableViewState viewState5 = expandableView2.getViewState();
                                f2 = f;
                                if (!((ViewState) viewState5).hidden && !((ViewState) viewState5).gone && expandableView2.mExpandableViewEx.getProxy() == null) {
                                    i3++;
                                    float stackViewScaleInCapsuleState = Companion.getStackViewScaleInCapsuleState(expandableView2, i3);
                                    OplusPhysicsPropertyAnimatorExtKt.setFinalValue(expandableView2, OplusPhysicsPropertyAnimator.Y_TRANSLATION, (((f2 - stackViewScaleInCapsuleState) * currentShowingCard.getActualHeight()) / 2.0f) + currentShowingCard.getTranslationY());
                                    int width = (int) (expandableView2.getWidth() - (((CapsuleCardControl) this.capsuleCardControl.get()).getItemWidth() * stackViewScaleInCapsuleState));
                                    expandableView2.mExpandableViewEx.setTargetClipWidth(width);
                                    expandableView2.mExpandableViewEx.setTargetClipHeight(expandableView2.getActualHeight() - ((int) (((CapsuleCardControl) this.capsuleCardControl.get()).getItemHeight() * stackViewScaleInCapsuleState)));
                                    OplusExpandableViewStateExImplKt.toPhysicsProperty(OplusExpandableViewStateExImpl.Companion.getCLIP_WIDTH_PROPERTY(), 0.1f).setFinalValue(expandableView2, width);
                                }
                                i2 = i4;
                                f = f2;
                            }
                            i2 = i4;
                            f = f2;
                        }
                        lockScreenNotificationExtStateManager = null;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            Log.d("SystemUi--Notification", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("handleStackViewForCapsuleToStacked: visibleChildren size:", list.size(), i3, ",handle size =", "StackCardControl-->"));
                        }
                    }
                }
                lockScreenNotificationExtStateManager = null;
            } else {
                lockScreenNotificationExtStateManager = null;
            }
        } else {
            lockScreenNotificationExtStateManager = null;
        }
        this.isExtStateAnimFirstFrame = false;
        Object obj2 = this.lockScreenExtStateController.get();
        LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager4 = obj2 instanceof LockScreenNotificationExtStateManager ? (LockScreenNotificationExtStateManager) obj2 : lockScreenNotificationExtStateManager;
        if (lockScreenNotificationExtStateManager4 == null || (notificationDisplayRectCallbackWrapper = lockScreenNotificationExtStateManager4.getNotificationDisplayRectCallbackWrapper()) == null) {
            return;
        }
        if (i != 0 && i != 3) {
            notificationDisplayRectCallbackWrapper.dispatchDisplayRectChanged(-1.0f, -1.0f);
            return;
        }
        if (i == 0) {
            Iterator<? extends ExpandableView> it2 = list.iterator();
            do {
                if (!it2.hasNext()) {
                    lockScreenNotificationExtStateManagerVisualBoundsOrNull = lockScreenNotificationExtStateManager;
                    break;
                }
                lockScreenNotificationExtStateManagerVisualBoundsOrNull = visualBoundsOrNull(it2.next());
            } while (lockScreenNotificationExtStateManagerVisualBoundsOrNull == null);
            if (lockScreenNotificationExtStateManagerVisualBoundsOrNull == null) {
                invalid_display_rect_pair = NotificationDisplayRectCallbackWrapper.Companion.getINVALID_DISPLAY_RECT_PAIR();
            } else {
                int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
                while (true) {
                    if (-1 >= lastIndex) {
                        lockScreenNotificationExtStateManager2 = lockScreenNotificationExtStateManager;
                        break;
                    }
                    LockScreenNotificationExtStateManager lockScreenNotificationExtStateManagerVisualBoundsOrNull2 = visualBoundsOrNull(list.get(lastIndex));
                    if (lockScreenNotificationExtStateManagerVisualBoundsOrNull2 != null) {
                        lockScreenNotificationExtStateManager2 = lockScreenNotificationExtStateManagerVisualBoundsOrNull2;
                        break;
                    }
                    lastIndex--;
                }
                invalid_display_rect_pair = lockScreenNotificationExtStateManager2 == null ? NotificationDisplayRectCallbackWrapper.Companion.getINVALID_DISPLAY_RECT_PAIR() : finishDisplayRect(((Number) lockScreenNotificationExtStateManagerVisualBoundsOrNull.getFirst()).floatValue(), ((Number) lockScreenNotificationExtStateManager2.getSecond()).floatValue());
            }
        } else if (i != 3) {
            invalid_display_rect_pair = NotificationDisplayRectCallbackWrapper.Companion.getINVALID_DISPLAY_RECT_PAIR();
        } else {
            Iterator<? extends ExpandableView> it3 = list.iterator();
            do {
                if (!it3.hasNext()) {
                    invalid_display_rect_pair2 = lockScreenNotificationExtStateManager;
                    break;
                }
                invalid_display_rect_pair2 = visualBoundsOrNull(it3.next());
            } while (invalid_display_rect_pair2 == null);
            if (invalid_display_rect_pair2 == null) {
                invalid_display_rect_pair2 = NotificationDisplayRectCallbackWrapper.Companion.getINVALID_DISPLAY_RECT_PAIR();
            }
            if (((OplusBiometricAuthController) this.biometricAuthController.get()).isNeedNotificationOverlayFingerPrint()) {
                List<? extends ExpandableView> list3 = list;
                if ((list3 instanceof Collection) && list3.isEmpty()) {
                    stackedBottomBorder = ((NotificationStackScrollLayoutExtImpl) this.extNssl.get()).getStackedBottomBorder();
                    invalid_display_rect_pair = finishDisplayRect(((Number) invalid_display_rect_pair2.getFirst()).floatValue(), stackedBottomBorder);
                } else {
                    Iterator<T> it4 = list3.iterator();
                    while (true) {
                        if (it4.hasNext()) {
                            OplusCustomRow oplusCustomRow3 = (ExpandableView) it4.next();
                            if (oplusCustomRow3 instanceof OplusCustomRow) {
                                OplusCustomRow oplusCustomRow4 = oplusCustomRow3;
                                if (!((ViewState) oplusCustomRow4.getViewState()).hidden && !((ViewState) oplusCustomRow4.getViewState()).gone) {
                                    int lastIndex2 = CollectionsKt__CollectionsKt.getLastIndex(list);
                                    while (true) {
                                        if (-1 >= lastIndex2) {
                                            lockScreenNotificationExtStateManager3 = lockScreenNotificationExtStateManager;
                                            break;
                                        }
                                        LockScreenNotificationExtStateManager lockScreenNotificationExtStateManagerVisualBoundsOrNull3 = visualBoundsOrNull(list.get(lastIndex2));
                                        if (lockScreenNotificationExtStateManagerVisualBoundsOrNull3 != null) {
                                            lockScreenNotificationExtStateManager3 = lockScreenNotificationExtStateManagerVisualBoundsOrNull3;
                                            break;
                                        }
                                        lastIndex2--;
                                    }
                                    if (lockScreenNotificationExtStateManager3 != null) {
                                        stackedBottomBorder = ((Number) lockScreenNotificationExtStateManager3.getSecond()).floatValue();
                                        invalid_display_rect_pair = finishDisplayRect(((Number) invalid_display_rect_pair2.getFirst()).floatValue(), stackedBottomBorder);
                                    } else {
                                        invalid_display_rect_pair = NotificationDisplayRectCallbackWrapper.Companion.getINVALID_DISPLAY_RECT_PAIR();
                                    }
                                }
                            }
                        } else {
                            stackedBottomBorder = ((NotificationStackScrollLayoutExtImpl) this.extNssl.get()).getStackedBottomBorder();
                            invalid_display_rect_pair = finishDisplayRect(((Number) invalid_display_rect_pair2.getFirst()).floatValue(), stackedBottomBorder);
                        }
                    }
                }
            } else {
                stackedBottomBorder = ((NotificationStackScrollLayoutExtImpl) this.extNssl.get()).getStackedBottomBorder();
                invalid_display_rect_pair = finishDisplayRect(((Number) invalid_display_rect_pair2.getFirst()).floatValue(), stackedBottomBorder);
            }
        }
        float fFloatValue = ((Number) invalid_display_rect_pair.component1()).floatValue();
        float fFloatValue2 = ((Number) invalid_display_rect_pair.component2()).floatValue();
        if (fFloatValue == -1.0f) {
            notificationDisplayRectCallbackWrapper.dispatchDisplayRectChanged(-1.0f, -1.0f);
        } else {
            notificationDisplayRectCallbackWrapper.dispatchDisplayRectChanged(fFloatValue, fFloatValue2);
        }
    }
}
