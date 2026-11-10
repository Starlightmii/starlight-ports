package com.oplus.systemui.statusbar.seeding;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.os.LocaleList;
import androidx.room.util.CursorUtil$;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel;
import com.android.systemui.statusbar.chips.screenrecord.ui.viewmodel.ScreenRecordChipViewModel;
import com.android.systemui.statusbar.chips.sharetoapp.ui.viewmodel.ShareToAppChipViewModel;
import com.android.systemui.statusbar.chips.ui.model.ChipCardModel;
import com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.statusbar.seeding.livealert.OplusChipCardModelFactory;
import com.oplus.systemui.statusbar.util.OpImageUtils;
import com.oplus.uxicon.ui.util.UxIconLoaderHelper;
import com.oplusos.systemui.common.feature.StatusBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLiveAlertViewModel implements ConfigurationController.ConfigurationListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusLiveAlertViewModel";
    private final MutableStateFlow _locale;
    private final CoroutineDispatcher backgroundDispatcher;
    private final Context context;
    private final StateFlow internalChips;
    private final StateFlow locale;
    private final PackageManager packageManager;
    private final StateFlow statusBarChips;
    private final UserTracker userTracker;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.oplus.systemui.statusbar.seeding.OplusLiveAlertViewModel$OplusChipType[], still in use, count: 1, list:
      (r0v1 com.oplus.systemui.statusbar.seeding.OplusLiveAlertViewModel$OplusChipType[]) from 0x002a: INVOKE (r0v1 com.oplus.systemui.statusbar.seeding.OplusLiveAlertViewModel$OplusChipType[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[WRAPPED]
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
    	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OplusChipType {
        ScreenRecord(10000),
        ShareToApp(10001),
        CastToOtherDevice(10002);

        public static final /* synthetic */ EnumEntries $ENTRIES;
        private final int value;

        static {
            $ENTRIES = EnumEntriesKt.enumEntries(oplusChipTypeArr);
        }

        public OplusChipType(int i) {
            super(str, i);
            this.value = i;
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static OplusChipType valueOf(String str) {
            return (OplusChipType) Enum.valueOf(OplusChipType.class, str);
        }

        public static OplusChipType[] values() {
            return (OplusChipType[]) $VALUES.clone();
        }

        public final int getValue() {
            return this.value;
        }
    }

    public OplusLiveAlertViewModel(Context context, PackageManager packageManager, UserTracker userTracker, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, ScreenRecordChipViewModel screenRecordChipViewModel, ShareToAppChipViewModel shareToAppChipViewModel, CastToOtherDeviceChipViewModel castToOtherDeviceChipViewModel, ConfigurationController configurationController) {
        this.context = context;
        this.packageManager = packageManager;
        this.userTracker = userTracker;
        this.backgroundDispatcher = coroutineDispatcher;
        StatusBarFeatureOption.INSTANCE.getClass();
        if (StatusBarFeatureOption.isStatusBarChipSupport) {
            configurationController.addCallback(this);
        }
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Locale.getDefault());
        this._locale = MutableStateFlow;
        StateFlow stateFlowAsStateFlow = FlowKt.asStateFlow(MutableStateFlow);
        this.locale = stateFlowAsStateFlow;
        Flow flowCombine = FlowKt.combine(screenRecordChipViewModel.getChip(), shareToAppChipViewModel.getChip(), castToOtherDeviceChipViewModel.getChip(), new OplusLiveAlertViewModel$internalChips$1(4, null));
        OplusLiveAlertViewModel$$ExternalSyntheticLambda0 oplusLiveAlertViewModel$$ExternalSyntheticLambda0 = new OplusLiveAlertViewModel$$ExternalSyntheticLambda0();
        oplusLiveAlertViewModel$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(flowCombine, oplusLiveAlertViewModel$$ExternalSyntheticLambda0);
        OplusLiveAlertViewModel$special$$inlined$filter$1 oplusLiveAlertViewModel$special$$inlined$filter$1 = new OplusLiveAlertViewModel$special$$inlined$filter$1();
        oplusLiveAlertViewModel$special$$inlined$filter$1.$this_unsafeTransform$inlined = flowDistinctUntilChanged;
        VarHandle.storeStoreFence();
        SharingStarted.Companion companion = SharingStarted.Companion;
        StateFlow stateFlowStateIn = FlowKt.stateIn(oplusLiveAlertViewModel$special$$inlined$filter$1, coroutineScope, companion.getLazily(), CollectionsKt__CollectionsKt.emptyList());
        this.internalChips = stateFlowStateIn;
        this.statusBarChips = FlowKt.stateIn(FlowKt.dropWhile(FlowKt.flowCombine(stateFlowStateIn, stateFlowAsStateFlow, new OplusLiveAlertViewModel$statusBarChips$1(this, null)), new OplusLiveAlertViewModel$statusBarChips$2(2, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(companion, 0L, 3), CollectionsKt__CollectionsKt.emptyList());
    }

    public static final Icon access$loadAppIcon(OplusLiveAlertViewModel oplusLiveAlertViewModel, String str) {
        oplusLiveAlertViewModel.getClass();
        if (str != null) {
            try {
                return Icon.createWithBitmap(OpImageUtils.drawableToBitmap(UxIconLoaderHelper.getUxDrawableWithMaxSizeIcon(oplusLiveAlertViewModel.context, oplusLiveAlertViewModel.packageManager.getResourcesForApplication(str).getDrawable(oplusLiveAlertViewModel.packageManager.getApplicationInfoAsUser(str, 0, oplusLiveAlertViewModel.userTracker.getUserHandle()).icon, null), DeviceUtil.dp2px(oplusLiveAlertViewModel.context, 20.0f))));
            } catch (PackageManager.NameNotFoundException e) {
                LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, CursorUtil$.ExternalSyntheticOutline0.m("load icon error package = ", str, " msg = ", e.getMessage()));
            } catch (Resources.NotFoundException e2) {
                LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, CursorUtil$.ExternalSyntheticOutline0.m("load icon error package = ", str, " msg = ", e2.getMessage()));
            }
        }
        return null;
    }

    public static String chipCardStopId(LiveAlertChipModel liveAlertChipModel) {
        ChipCardModel chipCardModel;
        Pair positivePair;
        OngoingActivityChipModel.Active model = liveAlertChipModel.getModel();
        OngoingActivityChipModel.Active active = model instanceof OngoingActivityChipModel.Active ? model : null;
        if (active == null || (chipCardModel = active.getChipCardModel()) == null || (positivePair = chipCardModel.getPositivePair()) == null) {
            return null;
        }
        return (String) positivePair.getFirst();
    }

    public final StateFlow getLocale() {
        return this.locale;
    }

    public final StateFlow getStatusBarChips() {
        return this.statusBarChips;
    }

    public final Function1 getStopAction(String str) {
        Function1 function1;
        ChipCardModel chipCardModel;
        Pair positivePair;
        Iterator it = ((Iterable) this.internalChips.getValue()).iterator();
        do {
            function1 = null;
            if (!it.hasNext()) {
                break;
            }
            LiveAlertChipModel liveAlertChipModel = (LiveAlertChipModel) it.next();
            OngoingActivityChipModel.Active model = liveAlertChipModel.getModel();
            OngoingActivityChipModel.Active active = model instanceof OngoingActivityChipModel.Active ? model : null;
            if (active != null && (chipCardModel = active.getChipCardModel()) != null && (positivePair = chipCardModel.getPositivePair()) != null) {
                if (Intrinsics.areEqual(positivePair.getFirst(), str)) {
                    function1 = (Function1) positivePair.getSecond();
                } else if (liveAlertChipModel.getChipType() == OplusChipType.CastToOtherDevice) {
                    OplusChipCardModelFactory.ChipStopAction chipStopAction = OplusChipCardModelFactory.ChipStopAction.END_CAST_SCREEN_TO_OTHER_DEVICE;
                    if (Intrinsics.areEqual(str, chipStopAction.getStopAction()) || Intrinsics.areEqual(str, OplusChipCardModelFactory.ChipStopAction.END_GENERIC_CAST_TO_OTHER_DEVICE.getStopAction())) {
                        String str2 = (String) positivePair.getFirst();
                        if (Intrinsics.areEqual(str2, chipStopAction.getStopAction()) || Intrinsics.areEqual(str2, OplusChipCardModelFactory.ChipStopAction.END_GENERIC_CAST_TO_OTHER_DEVICE.getStopAction())) {
                            function1 = (Function1) positivePair.getSecond();
                        }
                    }
                }
            }
        } while (function1 == null);
        if (function1 == null) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "getStopAction: no action for stopId=", str, LogUtil.TAG_STATUSBAR, TAG);
        }
        return function1;
    }

    public final void onLocaleListChanged() {
        LocaleList locales = this.context.getResources().getConfiguration().getLocales();
        this._locale.setValue(locales.isEmpty() ? Locale.getDefault() : locales.get(0));
    }
}
