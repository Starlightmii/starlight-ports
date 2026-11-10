package com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel;

import _COROUTINE.CoroutineDebuggingKt$;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.LocaleList;
import android.support.v4.content.FileProvider$;
import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import com.android.systemui.DualToneHandler;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.aod.controller.BaseAodClockLayoutController$bindSceneViewHolder$2$;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.statusbar.pipeline.battery.data.model.BatteryStyleModel;
import com.oplus.systemui.statusbar.pipeline.battery.data.model.BatteryThemeModel;
import com.oplus.systemui.statusbar.pipeline.battery.data.model.ChargeStateModel;
import com.oplus.systemui.statusbar.pipeline.battery.domain.interactor.ForceShowStyleInteractor;
import com.oplus.systemui.statusbar.pipeline.battery.domain.interactor.StatBatteryIconInteractor;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconStyle;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.ChargeIcon;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.ChargeIconMappings;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.OldHorizontal;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.PercentOutIcon;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon;
import com.oplus.systemui.statusbar.pipeline.battery.utils.BatteryBarUtils;
import com.oplus.systemui.utils.OplusThemeControllerKt;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Deprecated;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LocationBasedBatteryViewModel {
    public static final Companion Companion = new Companion();
    public static final String TAG = "LocationBasedBatteryViewModel";
    private final MutableStateFlow allowLayoutTransition;
    private final MutableStateFlow batteryCharge;
    private final MutableStateFlow batteryColor;
    private BatteryIconColor batteryColorValue;
    private final MutableStateFlow batteryIcon;
    private final MutableStateFlow batteryIconViewStyle;
    public int batteryLevel;
    private final MutableStateFlow batteryPercentOut;
    private BatteryStyleModel batteryStyle;
    public int chargeIconColor;
    private final ChargeIconMappings chargeIconMappings;
    private ChargeStateModel chargeState;
    private final ConfigurationController configurationController;
    private final Context context;
    private final DualToneHandler dualToneHandler;
    private final ForceShowStyleInteractor forceInteractor;
    private final StatBatteryIconInteractor interactor;
    private final MutableSharedFlow isLevelOrChargeUpdate;
    private Boolean isSavingMode;
    private BatteryThemeModel locationThemeStyle;
    private final StateFlow locationThemeStyleFlow;
    private String logTag;
    private final String moduleTag;
    private final MutableStateFlow oldHorizontal;
    private final MutableStateFlow reverseCharge;
    private final CoroutineScope scope;
    private Boolean speedModeTurnedOn;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C01551 extends SuspendLambda implements Function4 {
            /* synthetic */ int I$0;
            /* synthetic */ Object L$0;
            /* synthetic */ Object L$1;
            int label;
            final /* synthetic */ LocationBasedBatteryViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01551(LocationBasedBatteryViewModel locationBasedBatteryViewModel, Continuation continuation) {
                super(4, continuation);
                this.this$0 = locationBasedBatteryViewModel;
            }

            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                int iIntValue = ((Number) obj2).intValue();
                C01551 c01551 = new C01551(this.this$0, (Continuation) obj4);
                c01551.L$0 = (BatteryStyleModel) obj;
                c01551.I$0 = iIntValue;
                c01551.L$1 = (BatteryThemeModel) obj3;
                return c01551.invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                int percentStyle;
                BatteryStyleModel batteryStyleModel = (BatteryStyleModel) this.L$0;
                int i = this.I$0;
                BatteryThemeModel batteryThemeModel = (BatteryThemeModel) this.L$1;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                String str = this.this$0.logTag;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                    sbM.append("bStyle fShowStyle changed: bStyle " + batteryStyleModel + " , fShowStyle: " + i);
                    Log.d("SystemUi--Statusbar", sbM.toString());
                }
                int iconStyle = 1;
                if (i != 1 || batteryStyleModel.getPercentStyle() == 0 || batteryStyleModel.getIconStyle() == 0) {
                    percentStyle = batteryStyleModel.getPercentStyle();
                } else {
                    percentStyle = (batteryStyleModel.getIconStyle() == 3 || batteryStyleModel.getIconStyle() == 4) ? 0 : 1;
                }
                if (i == 2) {
                    iconStyle = 2;
                } else if (batteryStyleModel.getIconStyle() == 1) {
                    if (!BatteryBarUtils.useHorizontalStyleOf15(this.this$0.getContext(), percentStyle == 1, batteryThemeModel.isThirdTheme, batteryThemeModel.getBatteryThemeVersion()) && BatteryBarUtils.useHorizontalStyleOf14(this.this$0.getContext(), batteryThemeModel.isThirdTheme)) {
                        iconStyle = 5;
                    }
                } else {
                    iconStyle = batteryStyleModel.getIconStyle();
                }
                return new LocationBatteryStyleState(BatteryStyleModel.copy$default(batteryStyleModel, percentStyle, iconStyle, null, 0, false, 124), batteryThemeModel);
            }
        }

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass2 implements FlowCollector {
            public /* synthetic */ LocationBasedBatteryViewModel this$0;

            /* JADX WARN: Code duplicated, block: B:30:0x00e0 A[PHI: r6
              0x00e0: PHI (r6v4 com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$LocationBatteryStyleState) = 
              (r6v1 com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$LocationBatteryStyleState)
              (r6v6 com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$LocationBatteryStyleState)
             binds: [B:28:0x00dd, B:17:0x0059] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:33:0x00f0 A[PHI: r6
              0x00f0: PHI (r6v7 com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$LocationBatteryStyleState) = 
              (r6v4 com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$LocationBatteryStyleState)
              (r6v9 com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$LocationBatteryStyleState)
             binds: [B:31:0x00ed, B:16:0x0050] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:36:0x0100 A[PHI: r6
              0x0100: PHI (r6v10 com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$LocationBatteryStyleState) = 
              (r6v7 com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$LocationBatteryStyleState)
              (r6v12 com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$LocationBatteryStyleState)
             binds: [B:34:0x00fd, B:15:0x0047] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:39:0x0110 A[PHI: r6
              0x0110: PHI (r6v13 com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$LocationBatteryStyleState) = 
              (r6v10 com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$LocationBatteryStyleState)
              (r6v15 com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$LocationBatteryStyleState)
             binds: [B:37:0x010d, B:14:0x003e] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:42:0x0120 A[PHI: r6
              0x0120: PHI (r6v16 com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$LocationBatteryStyleState) = 
              (r6v13 com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$LocationBatteryStyleState)
              (r6v18 com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$LocationBatteryStyleState)
             binds: [B:40:0x011d, B:13:0x0035] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:7:0x0015  */
            /* JADX WARN: Code restructure failed: missing block: B:43:0x012d, code lost:
            
                if (com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.updateWirelessChargingReverseIcon$default(r0, r1) == r7) goto L44;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.LocationBatteryStyleState r6, kotlin.coroutines.Continuation r7) {
                /*
                    Method dump skipped, instruction units count: 328
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.AnonymousClass1.AnonymousClass2.emit(com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$LocationBatteryStyleState, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return LocationBasedBatteryViewModel.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0068, code lost:
        
            if (((kotlinx.coroutines.flow.StateFlow) r9).collect(r1, r8) == r0) goto L15;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 0
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L1d
                if (r1 == r4) goto L19
                if (r1 == r3) goto L15
                java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                okio.Path$.ExternalSyntheticBUOutline0.m$1(r8)
                return r2
            L15:
                kotlin.ResultKt.throwOnFailure(r9)
                goto L6b
            L19:
                kotlin.ResultKt.throwOnFailure(r9)
                goto L54
            L1d:
                kotlin.ResultKt.throwOnFailure(r9)
                com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel r9 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.this
                com.oplus.systemui.statusbar.pipeline.battery.domain.interactor.StatBatteryIconInteractor r9 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$getInteractor$p(r9)
                kotlinx.coroutines.flow.StateFlow r9 = r9.getBatteryStyle()
                com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel r1 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.this
                com.oplus.systemui.statusbar.pipeline.battery.domain.interactor.ForceShowStyleInteractor r1 = r1.getForceInteractor()
                kotlinx.coroutines.flow.Flow r1 = r1.getFilteredForceStyle()
                com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel r5 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.this
                kotlinx.coroutines.flow.StateFlow r5 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$getLocationThemeStyleFlow$p(r5)
                com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$1$1 r6 = new com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$1$1
                com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel r7 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.this
                r6.<init>(r7, r2)
                kotlinx.coroutines.flow.Flow r9 = kotlinx.coroutines.flow.FlowKt.combine(r9, r1, r5, r6)
                com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel r1 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.this
                kotlinx.coroutines.CoroutineScope r1 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$getScope$p(r1)
                r8.label = r4
                java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt.stateIn(r9, r1, r8)
                if (r9 != r0) goto L54
                goto L6a
            L54:
                kotlinx.coroutines.flow.StateFlow r9 = (kotlinx.coroutines.flow.StateFlow) r9
                com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$1$2 r1 = new com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$1$2
                com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel r4 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.this
                r1.<init>()
                r1.this$0 = r4
                java.lang.invoke.VarHandle.storeStoreFence()
                r8.label = r3
                java.lang.Object r8 = r9.collect(r1, r8)
                if (r8 != r0) goto L6b
            L6a:
                return r0
            L6b:
                kotlin.text.Regex$.ExternalSyntheticBUOutline0.m$1()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 implements FlowCollector {
            public /* synthetic */ LocationBasedBatteryViewModel this$0;

            public final Object emit(Object obj, Continuation continuation) {
                ((Boolean) obj).booleanValue();
                LocationBasedBatteryViewModel locationBasedBatteryViewModel = this.this$0;
                Object objEmit = locationBasedBatteryViewModel.getAllowLayoutTransition().emit(Boxing.boxBoolean(LocationBasedBatteryViewModel.access$computeAllowLayoutTransition(locationBasedBatteryViewModel)), continuation);
                return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return LocationBasedBatteryViewModel.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlowIsPanelExpanded = LocationBasedBatteryViewModel.this.getForceInteractor().isPanelExpanded();
                LocationBasedBatteryViewModel locationBasedBatteryViewModel = LocationBasedBatteryViewModel.this;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                anonymousClass1.this$0 = locationBasedBatteryViewModel;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (stateFlowIsPanelExpanded.collect(anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            Regex$.ExternalSyntheticBUOutline0.m$1();
            return null;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass4 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$4$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 implements FlowCollector {
            public /* synthetic */ LocationBasedBatteryViewModel this$0;

            /* JADX WARN: Code duplicated, block: B:36:0x00d2 A[PHI: r9
              0x00d2: PHI (r9v3 com.oplus.systemui.statusbar.pipeline.battery.data.model.ChargeStateModel) = 
              (r9v2 com.oplus.systemui.statusbar.pipeline.battery.data.model.ChargeStateModel)
              (r9v10 com.oplus.systemui.statusbar.pipeline.battery.data.model.ChargeStateModel)
             binds: [B:34:0x00cf, B:19:0x004c] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:39:0x00e1 A[PHI: r9
              0x00e1: PHI (r9v4 com.oplus.systemui.statusbar.pipeline.battery.data.model.ChargeStateModel) = 
              (r9v3 com.oplus.systemui.statusbar.pipeline.battery.data.model.ChargeStateModel)
              (r9v12 com.oplus.systemui.statusbar.pipeline.battery.data.model.ChargeStateModel)
             binds: [B:37:0x00de, B:18:0x0043] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:7:0x0015  */
            /* JADX WARN: Code restructure failed: missing block: B:40:0x00ed, code lost:
            
                if (com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$updateOldHorizontalBatteryIconTint(r0, r1) == r10) goto L41;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(com.oplus.systemui.statusbar.pipeline.battery.data.model.ChargeStateModel r9, kotlin.coroutines.Continuation r10) {
                /*
                    Method dump skipped, instruction units count: 243
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.AnonymousClass4.AnonymousClass1.emit(com.oplus.systemui.statusbar.pipeline.battery.data.model.ChargeStateModel, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        public AnonymousClass4(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return LocationBasedBatteryViewModel.this.new AnonymousClass4(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow chargeState = LocationBasedBatteryViewModel.this.interactor.getChargeState();
                LocationBasedBatteryViewModel locationBasedBatteryViewModel = LocationBasedBatteryViewModel.this;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                anonymousClass1.this$0 = locationBasedBatteryViewModel;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (chargeState.collect(anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            Regex$.ExternalSyntheticBUOutline0.m$1();
            return null;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$7, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass7 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$7$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 implements FlowCollector {
            public /* synthetic */ LocationBasedBatteryViewModel this$0;

            /* JADX WARN: Code duplicated, block: B:31:0x0099 A[PHI: r9
              0x0099: PHI (r9v3 com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor) = 
              (r9v2 com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor)
              (r9v10 com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor)
             binds: [B:29:0x0096, B:19:0x004b] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:34:0x00a8 A[PHI: r9
              0x00a8: PHI (r9v4 com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor) = 
              (r9v3 com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor)
              (r9v12 com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor)
             binds: [B:32:0x00a5, B:18:0x0043] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:7:0x0015  */
            /* JADX WARN: Code restructure failed: missing block: B:35:0x00b4, code lost:
            
                if (com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.updateWirelessChargingReverseIcon$default(r0, r1) == r10) goto L36;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor r9, kotlin.coroutines.Continuation r10) {
                /*
                    r8 = this;
                    com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel r0 = r8.this$0
                    boolean r1 = r10 instanceof com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$7$1$emit$1
                    if (r1 == 0) goto L15
                    r1 = r10
                    com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$7$1$emit$1 r1 = (com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$7$1$emit$1) r1
                    int r2 = r1.label
                    r3 = -2147483648(0xffffffff80000000, float:-0.0)
                    r4 = r2 & r3
                    if (r4 == 0) goto L15
                    int r2 = r2 - r3
                    r1.label = r2
                    goto L1a
                L15:
                    com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$7$1$emit$1 r1 = new com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$7$1$emit$1
                    r1.<init>(r8, r10)
                L1a:
                    java.lang.Object r8 = r1.result
                    java.lang.Object r10 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r1.label
                    r3 = 5
                    r4 = 4
                    r5 = 3
                    r6 = 2
                    r7 = 1
                    if (r2 == 0) goto L63
                    if (r2 == r7) goto L5b
                    if (r2 == r6) goto L53
                    if (r2 == r5) goto L4b
                    if (r2 == r4) goto L43
                    if (r2 != r3) goto L3c
                    java.lang.Object r9 = r1.L$0
                    com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor r9 = (com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor) r9
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto Lb7
                L3c:
                    java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                    okio.Path$.ExternalSyntheticBUOutline0.m$1(r8)
                    r8 = 0
                    return r8
                L43:
                    java.lang.Object r9 = r1.L$0
                    com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor r9 = (com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor) r9
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto La8
                L4b:
                    java.lang.Object r9 = r1.L$0
                    com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor r9 = (com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor) r9
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L99
                L53:
                    java.lang.Object r9 = r1.L$0
                    com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor r9 = (com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor) r9
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L8a
                L5b:
                    java.lang.Object r9 = r1.L$0
                    com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor r9 = (com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor) r9
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L7b
                L63:
                    kotlin.ResultKt.throwOnFailure(r8)
                    com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$setBatteryColorValue$p(r0, r9)
                    com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$updateChargeColor(r0)
                    java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
                    r1.L$0 = r8
                    r1.label = r7
                    java.lang.Object r8 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$updateBatteryIcon(r0, r1)
                    if (r8 != r10) goto L7b
                    goto Lb6
                L7b:
                    java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
                    r1.L$0 = r8
                    r1.label = r6
                    java.lang.Object r8 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$updateOldHorizontalBatteryIconTint(r0, r1)
                    if (r8 != r10) goto L8a
                    goto Lb6
                L8a:
                    java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
                    r1.L$0 = r8
                    r1.label = r5
                    java.lang.Object r8 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.updateBatteryPercentOut$default(r0, r1)
                    if (r8 != r10) goto L99
                    goto Lb6
                L99:
                    java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
                    r1.L$0 = r8
                    r1.label = r4
                    java.lang.Object r8 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.updateBatteryChargingIcon$default(r0, r1)
                    if (r8 != r10) goto La8
                    goto Lb6
                La8:
                    java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
                    r1.L$0 = r8
                    r1.label = r3
                    java.lang.Object r8 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.updateWirelessChargingReverseIcon$default(r0, r1)
                    if (r8 != r10) goto Lb7
                Lb6:
                    return r10
                Lb7:
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.AnonymousClass7.AnonymousClass1.emit(com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        public AnonymousClass7(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return LocationBasedBatteryViewModel.this.new AnonymousClass7(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MutableStateFlow batteryColor = LocationBasedBatteryViewModel.this.getBatteryColor();
                LocationBasedBatteryViewModel locationBasedBatteryViewModel = LocationBasedBatteryViewModel.this;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                anonymousClass1.this$0 = locationBasedBatteryViewModel;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (batteryColor.collect(anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            Regex$.ExternalSyntheticBUOutline0.m$1();
            return null;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Factory {
        LocationBasedBatteryViewModel create(Context context, ConfigurationController configurationController, String str, ForceShowStyleInteractor forceShowStyleInteractor, CoroutineScope coroutineScope);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class LocationBatteryStyleState {
        private final BatteryStyleModel batteryStyle;
        private final BatteryThemeModel themeStyle;

        public LocationBatteryStyleState(BatteryStyleModel batteryStyleModel, BatteryThemeModel batteryThemeModel) {
            this.batteryStyle = batteryStyleModel;
            this.themeStyle = batteryThemeModel;
        }

        public final BatteryStyleModel component1() {
            return this.batteryStyle;
        }

        public final BatteryThemeModel component2() {
            return this.themeStyle;
        }

        public final LocationBatteryStyleState copy(BatteryStyleModel batteryStyleModel, BatteryThemeModel batteryThemeModel) {
            return new LocationBatteryStyleState(batteryStyleModel, batteryThemeModel);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LocationBatteryStyleState)) {
                return false;
            }
            LocationBatteryStyleState locationBatteryStyleState = (LocationBatteryStyleState) obj;
            return Intrinsics.areEqual(this.batteryStyle, locationBatteryStyleState.batteryStyle) && Intrinsics.areEqual(this.themeStyle, locationBatteryStyleState.themeStyle);
        }

        public final BatteryStyleModel getBatteryStyle() {
            return this.batteryStyle;
        }

        public final BatteryThemeModel getThemeStyle() {
            return this.themeStyle;
        }

        public final int hashCode() {
            return this.themeStyle.hashCode() + (this.batteryStyle.hashCode() * 31);
        }

        public String toString() {
            return "LocationBatteryStyleState(batteryStyle=" + this.batteryStyle + ", themeStyle=" + this.themeStyle + ")";
        }
    }

    public LocationBasedBatteryViewModel(Context context, ConfigurationController configurationController, String str, ForceShowStyleInteractor forceShowStyleInteractor, CoroutineScope coroutineScope, StatBatteryIconInteractor statBatteryIconInteractor, ChargeIconMappings chargeIconMappings) {
        this.context = context;
        this.configurationController = configurationController;
        this.moduleTag = str;
        this.forceInteractor = forceShowStyleInteractor;
        this.scope = coroutineScope;
        this.interactor = statBatteryIconInteractor;
        this.chargeIconMappings = chargeIconMappings;
        DualToneHandler dualToneHandler = new DualToneHandler(context);
        this.dualToneHandler = dualToneHandler;
        this.batteryColor = StateFlowKt.MutableStateFlow(new BatteryIconColor(dualToneHandler.getSingleColor(0.0f), dualToneHandler.getFillColor(0.0f), dualToneHandler.getBackgroundColor(0.0f)));
        this.reverseCharge = StateFlowKt.MutableStateFlow((Object) null);
        this.batteryPercentOut = StateFlowKt.MutableStateFlow((Object) null);
        this.batteryIconViewStyle = StateFlowKt.MutableStateFlow((Object) null);
        this.batteryCharge = StateFlowKt.MutableStateFlow((Object) null);
        this.batteryIcon = StateFlowKt.MutableStateFlow((Object) null);
        this.isLevelOrChargeUpdate = SharedFlowKt.MutableSharedFlow$default(0, 0, (BufferOverflow) null, 7);
        this.oldHorizontal = StateFlowKt.MutableStateFlow((Object) null);
        this.allowLayoutTransition = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this.logTag = FileProvider$.ExternalSyntheticOutline0.m("LocationBasedBatteryViewModel - ", str);
        this.locationThemeStyleFlow = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowConflatedKt.conflatedCallbackFlow(new LocationBasedBatteryViewModel$locationThemeStyleFlow$1(this, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), readThemeStyleFromContext());
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass5(null), 3);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass6(null), 3);
        BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new AnonymousClass7(null), 2);
    }

    public static final boolean access$computeAllowLayoutTransition(LocationBasedBatteryViewModel locationBasedBatteryViewModel) {
        BatteryStyleModel batteryStyleModel = locationBasedBatteryViewModel.batteryStyle;
        return (Intrinsics.areEqual(locationBasedBatteryViewModel.moduleTag, "home") || Intrinsics.areEqual(locationBasedBatteryViewModel.moduleTag, SeparateQSStatisicUtil.VALUE_KEYGUARD)) && !((batteryStyleModel != null ? batteryStyleModel.isDisableAnimation : false) || ((Boolean) locationBasedBatteryViewModel.forceInteractor.isPanelExpanded().getValue()).booleanValue());
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v5, types: [int] */
    /* JADX WARN: Type inference failed for: r10v7 */
    public static final Object access$updateBatteryIcon(LocationBasedBatteryViewModel locationBasedBatteryViewModel, ContinuationImpl continuationImpl) {
        LocationBasedBatteryViewModel$updateBatteryIcon$1 locationBasedBatteryViewModel$updateBatteryIcon$1;
        ?? r10;
        locationBasedBatteryViewModel.getClass();
        if (continuationImpl instanceof LocationBasedBatteryViewModel$updateBatteryIcon$1) {
            locationBasedBatteryViewModel$updateBatteryIcon$1 = (LocationBasedBatteryViewModel$updateBatteryIcon$1) continuationImpl;
            int i = locationBasedBatteryViewModel$updateBatteryIcon$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                locationBasedBatteryViewModel$updateBatteryIcon$1.label = i - Integer.MIN_VALUE;
            } else {
                locationBasedBatteryViewModel$updateBatteryIcon$1 = new LocationBasedBatteryViewModel$updateBatteryIcon$1(locationBasedBatteryViewModel, continuationImpl);
            }
        } else {
            locationBasedBatteryViewModel$updateBatteryIcon$1 = new LocationBasedBatteryViewModel$updateBatteryIcon$1(locationBasedBatteryViewModel, continuationImpl);
        }
        Object obj = locationBasedBatteryViewModel$updateBatteryIcon$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = locationBasedBatteryViewModel$updateBatteryIcon$1.label;
        Object verticalOrCircle = null;
        if (i2 != 0) {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            StatBatteryIcon statBatteryIcon = (StatBatteryIcon) locationBasedBatteryViewModel$updateBatteryIcon$1.L$5;
            ResultKt.throwOnFailure(obj);
            return statBatteryIcon;
        }
        ResultKt.throwOnFailure(obj);
        BatteryIconColor batteryIconColor = locationBasedBatteryViewModel.batteryColorValue;
        BatteryStyleModel batteryStyleModel = locationBasedBatteryViewModel.batteryStyle;
        ChargeStateModel chargeStateModel = locationBasedBatteryViewModel.chargeState;
        Boolean bool = locationBasedBatteryViewModel.isSavingMode;
        if (batteryStyleModel != null && batteryIconColor != null && chargeStateModel != null && bool != null && batteryStyleModel.getIconStyle() != 2) {
            int progressColor$default = getProgressColor$default(locationBasedBatteryViewModel);
            BatteryThemeModel batteryThemeModel = locationBasedBatteryViewModel.locationThemeStyle;
            boolean z = ((batteryThemeModel != null ? batteryThemeModel.getThemeMode() : 0) > 0 && batteryStyleModel.getIconStyle() == 1) || !(batteryIconColor.getSingleToneColor() == progressColor$default || batteryIconColor.getForegroundColor() == progressColor$default);
            ChargeIconMappings.ChargeIconItem chargeIconId = locationBasedBatteryViewModel.getChargeIconId();
            int chargeIconId2 = chargeIconId != null ? chargeIconId.getChargeIconId() : 0;
            int iconStyle = batteryStyleModel.getIconStyle();
            if (iconStyle != 1) {
                verticalOrCircle = (iconStyle == 3 || iconStyle == 4) ? new StatBatteryIcon.VerticalOrCircle(locationBasedBatteryViewModel.batteryLevel, chargeIconId2, batteryIconColor.getSingleToneColor(), progressColor$default, batteryIconColor.getBackgroundColor(), z, locationBasedBatteryViewModel.getContentDescription()) : null;
                r10 = z;
            } else {
                boolean z2 = z;
                StatBatteryIcon.Horizontal horizontal = new StatBatteryIcon.Horizontal(locationBasedBatteryViewModel.batteryLevel, chargeIconId2, chargeIconId != null ? chargeIconId.getChargeIconBgId() : 0, batteryIconColor.getSingleToneColor(), progressColor$default, batteryIconColor.getBackgroundColor(), z2, locationBasedBatteryViewModel.getContentDescription(), locationBasedBatteryViewModel.isHorizontalBatteryPercentIn(), batteryStyleModel.getLocaleList());
                progressColor$default = progressColor$default;
                r10 = z2;
                verticalOrCircle = horizontal;
            }
            MutableStateFlow mutableStateFlow = locationBasedBatteryViewModel.batteryIcon;
            locationBasedBatteryViewModel$updateBatteryIcon$1.L$0 = SpillingKt.nullOutSpilledVariable(batteryIconColor);
            locationBasedBatteryViewModel$updateBatteryIcon$1.L$1 = SpillingKt.nullOutSpilledVariable(batteryStyleModel);
            locationBasedBatteryViewModel$updateBatteryIcon$1.L$2 = SpillingKt.nullOutSpilledVariable(chargeStateModel);
            locationBasedBatteryViewModel$updateBatteryIcon$1.L$3 = SpillingKt.nullOutSpilledVariable(bool);
            locationBasedBatteryViewModel$updateBatteryIcon$1.L$4 = SpillingKt.nullOutSpilledVariable(chargeIconId);
            locationBasedBatteryViewModel$updateBatteryIcon$1.L$5 = verticalOrCircle;
            locationBasedBatteryViewModel$updateBatteryIcon$1.I$0 = progressColor$default;
            locationBasedBatteryViewModel$updateBatteryIcon$1.I$1 = r10;
            locationBasedBatteryViewModel$updateBatteryIcon$1.I$2 = chargeIconId2;
            locationBasedBatteryViewModel$updateBatteryIcon$1.label = 1;
            if (mutableStateFlow.emit(verticalOrCircle, locationBasedBatteryViewModel$updateBatteryIcon$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return verticalOrCircle;
    }

    public static final void access$updateChargeColor(LocationBasedBatteryViewModel locationBasedBatteryViewModel) {
        int foregroundColor;
        BatteryIconColor batteryIconColor = locationBasedBatteryViewModel.batteryColorValue;
        BatteryStyleModel batteryStyleModel = locationBasedBatteryViewModel.batteryStyle;
        ChargeStateModel chargeStateModel = locationBasedBatteryViewModel.chargeState;
        Boolean bool = locationBasedBatteryViewModel.speedModeTurnedOn;
        Boolean bool2 = locationBasedBatteryViewModel.isSavingMode;
        if (batteryStyleModel == null || batteryIconColor == null || chargeStateModel == null || bool == null || bool2 == null || batteryStyleModel.getIconStyle() == 2) {
            return;
        }
        if (chargeStateModel.getHeatStateActive()) {
            foregroundColor = locationBasedBatteryViewModel.context.getColor(2131099906);
        } else if (chargeStateModel.getSuperVoocCharging() && chargeStateModel.getChargerWattage() >= 100 && chargeStateModel.getVoocSpecialColorShow()) {
            foregroundColor = locationBasedBatteryViewModel.context.getColor(2131099909);
        } else if (chargeStateModel.getSuperVoocCharging() && chargeStateModel.getChargerWattage() >= 33 && chargeStateModel.getVoocSpecialColorShow()) {
            foregroundColor = locationBasedBatteryViewModel.context.getColor(2131099914);
        } else if (chargeStateModel.getWirelessVoocCharging() && chargeStateModel.getVoocSpecialColorShow()) {
            foregroundColor = locationBasedBatteryViewModel.context.getColor(2131099914);
        } else if (chargeStateModel.getCharging()) {
            foregroundColor = locationBasedBatteryViewModel.context.getColor(2131099917);
        } else {
            foregroundColor = (locationBasedBatteryViewModel.isHorizontalBatteryPercentIn() || batteryStyleModel.getIconStyle() == 3) ? batteryIconColor.getForegroundColor() : batteryIconColor.getSingleToneColor();
        }
        locationBasedBatteryViewModel.chargeIconColor = foregroundColor;
    }

    public static final Object access$updateOldHorizontalBatteryIcon(LocationBasedBatteryViewModel locationBasedBatteryViewModel, ContinuationImpl continuationImpl) {
        BatteryStyleModel batteryStyleModel = locationBasedBatteryViewModel.batteryStyle;
        if (batteryStyleModel == null || batteryStyleModel.getIconStyle() != 5) {
            return Unit.INSTANCE;
        }
        Object objEmit = locationBasedBatteryViewModel.oldHorizontal.emit(locationBasedBatteryViewModel.getHorizontalIcon(), continuationImpl);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    public static final Object access$updateOldHorizontalBatteryIconTint(LocationBasedBatteryViewModel locationBasedBatteryViewModel, ContinuationImpl continuationImpl) {
        Drawable foregroundDrawable;
        Drawable progressDrawable;
        BatteryStyleModel batteryStyleModel = locationBasedBatteryViewModel.batteryStyle;
        if (batteryStyleModel == null || batteryStyleModel.getIconStyle() != 5) {
            return Unit.INSTANCE;
        }
        OldHorizontal oldHorizontal = (OldHorizontal) locationBasedBatteryViewModel.oldHorizontal.getValue();
        Drawable drawableMutate = null;
        Drawable drawableMutate2 = (oldHorizontal == null || (progressDrawable = oldHorizontal.getProgressDrawable()) == null) ? null : progressDrawable.mutate();
        if (drawableMutate2 != null) {
            updateProgressDrawable$default(locationBasedBatteryViewModel, drawableMutate2);
        }
        OldHorizontal oldHorizontal2 = (OldHorizontal) locationBasedBatteryViewModel.oldHorizontal.getValue();
        if (oldHorizontal2 != null && (foregroundDrawable = oldHorizontal2.getForegroundDrawable()) != null) {
            drawableMutate = foregroundDrawable.mutate();
        }
        Object objEmit = locationBasedBatteryViewModel.oldHorizontal.emit(new OldHorizontal(locationBasedBatteryViewModel.batteryLevel, drawableMutate2, drawableMutate, ((BatteryIconColor) locationBasedBatteryViewModel.batteryColor.getValue()).getSingleToneColor(), locationBasedBatteryViewModel.getContentDescription()), continuationImpl);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    private final ChargeIconMappings.ChargeIconItem getChargeIconId() {
        BatteryIconColor batteryIconColor = this.batteryColorValue;
        BatteryStyleModel batteryStyleModel = this.batteryStyle;
        ChargeStateModel chargeStateModel = this.chargeState;
        Boolean bool = this.isSavingMode;
        if (batteryStyleModel == null || batteryIconColor == null || chargeStateModel == null || bool == null || !chargeStateModel.getCharging() || isHorizontalBatteryPercentIn()) {
            return null;
        }
        return this.chargeIconMappings.getChargeIconItem(batteryStyleModel.getIconStyle(), chargeStateModel.getPluggedInWireless(), chargeStateModel.getWirelessVoocCharging() || chargeStateModel.getSuperVoocCharging());
    }

    private final String getContentDescription() {
        String string;
        ChargeStateModel chargeStateModel = this.chargeState;
        if (chargeStateModel == null) {
            return "";
        }
        String string2 = this.context.getString(2131951714);
        String string3 = this.context.getString(2131951990);
        boolean charging = chargeStateModel.getCharging();
        Context context = this.context;
        if (charging) {
            string = CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(context.getString(2131951715), ", ", this.context.getString(2131951722, Integer.valueOf(this.batteryLevel)));
        } else {
            string = context.getString(2131951722, Integer.valueOf(this.batteryLevel));
            string.getClass();
        }
        if (Intrinsics.areEqual(this.isSavingMode, Boolean.TRUE)) {
            string2 = Bundler$.ExternalSyntheticOutline0.m(string2, this.context.getString(2131951723));
        }
        return string2 + " " + string3 + " " + string;
    }

    @Deprecated
    private final OldHorizontal getHorizontalIcon() {
        Drawable drawable;
        Drawable drawableMutate;
        BatteryStyleModel batteryStyleModel = this.batteryStyle;
        BatteryThemeModel batteryThemeModel = this.locationThemeStyle;
        if (batteryStyleModel == null || batteryThemeModel == null) {
            drawable = null;
        } else {
            int themeModeUntil14 = batteryThemeModel.getThemeModeUntil14();
            Context context = this.context;
            if (themeModeUntil14 > 0) {
                drawable = context.getDrawable(isHorizontalBatteryPercentIn() ? 2131237069 : 2131237070);
            } else {
                drawable = context.getDrawable((!isHorizontalBatteryPercentIn() || isLowPowerMode()) ? 2131237071 : 2131237068);
            }
            if (drawable instanceof LayerDrawable) {
                ((LayerDrawable) drawable).setLayerInset(0, this.context.getResources().getDimensionPixelSize(2131165629), this.context.getResources().getDimensionPixelSize(2131165630), this.context.getResources().getDimensionPixelSize(2131165628), this.context.getResources().getDimensionPixelSize(2131165627));
            }
        }
        if (drawable == null || (drawableMutate = drawable.mutate()) == null) {
            return null;
        }
        updateProgressDrawable$default(this, drawableMutate);
        Drawable drawable2 = this.context.getDrawable(2131236688);
        Drawable drawableMutate2 = drawable2 != null ? drawable2.mutate() : null;
        if (drawableMutate2 != null) {
            drawableMutate2.setAutoMirrored(true);
        }
        return new OldHorizontal(this.batteryLevel, drawableMutate, drawableMutate2, ((BatteryIconColor) this.batteryColor.getValue()).getSingleToneColor(), getContentDescription());
    }

    @Deprecated
    public static /* synthetic */ void getOldHorizontal$annotations() {
    }

    public static int getProgressColor$default(LocationBasedBatteryViewModel locationBasedBatteryViewModel) {
        BatteryStyleModel batteryStyleModel;
        BatteryStyleModel batteryStyleModel2;
        BatteryStyleModel batteryStyleModel3 = locationBasedBatteryViewModel.batteryStyle;
        BatteryThemeModel batteryThemeModel = locationBasedBatteryViewModel.locationThemeStyle;
        if (batteryStyleModel3 == null) {
            return ((BatteryIconColor) locationBasedBatteryViewModel.batteryColor.getValue()).getSingleToneColor();
        }
        if (locationBasedBatteryViewModel.isHorizontalBatteryPercentIn()) {
            if ((batteryThemeModel != null ? batteryThemeModel.getThemeMode() : 0) > 0) {
                return ((BatteryIconColor) locationBasedBatteryViewModel.batteryColor.getValue()).getSingleToneColor();
            }
        }
        BatteryStyleModel batteryStyleModel4 = locationBasedBatteryViewModel.batteryStyle;
        if (((batteryStyleModel4 != null && batteryStyleModel4.getIconStyle() == 1) || ((batteryStyleModel = locationBasedBatteryViewModel.batteryStyle) != null && batteryStyleModel.getIconStyle() == 5)) && (batteryStyleModel2 = locationBasedBatteryViewModel.batteryStyle) != null && batteryStyleModel2.getPercentStyle() == 2) {
            if ((batteryThemeModel != null ? batteryThemeModel.getThemePercentOutProgressMode() : 0) > 0) {
                return ((BatteryIconColor) locationBasedBatteryViewModel.batteryColor.getValue()).getSingleToneColor();
            }
        }
        if (Intrinsics.areEqual(locationBasedBatteryViewModel.isSavingMode, Boolean.TRUE)) {
            return locationBasedBatteryViewModel.context.getColor(2131099911);
        }
        return locationBasedBatteryViewModel.isLowPowerMode() ? locationBasedBatteryViewModel.context.getColor(2131099907) : locationBasedBatteryViewModel.chargeIconColor;
    }

    public static int getThemeId$default(LocationBasedBatteryViewModel locationBasedBatteryViewModel) {
        BatteryThemeModel batteryThemeModel = locationBasedBatteryViewModel.locationThemeStyle;
        locationBasedBatteryViewModel.getClass();
        if (batteryThemeModel != null && batteryThemeModel.isThirdTheme) {
            BatteryThemeModel batteryThemeModel2 = locationBasedBatteryViewModel.locationThemeStyle;
            if (((batteryThemeModel2 != null ? batteryThemeModel2.getUiMode() : 0) & 48) != 32) {
                return batteryThemeModel.getThemeChanged();
            }
        }
        return 0;
    }

    @Deprecated
    private final void setProgressAlpha(Drawable drawable) {
        BatteryStyleModel batteryStyleModel = this.batteryStyle;
        ChargeStateModel chargeStateModel = this.chargeState;
        Boolean bool = this.isSavingMode;
        BatteryThemeModel batteryThemeModel = this.locationThemeStyle;
        if (batteryStyleModel == null || bool == null || chargeStateModel == null || batteryThemeModel == null || batteryThemeModel.getThemeModeUntil14() > 0 || isLowPowerMode()) {
            return;
        }
        if (isHorizontalBatteryPercentIn()) {
            drawable.setAlpha((bool.booleanValue() || chargeStateModel.getCharging()) ? 128 : 38);
        } else {
            drawable.setAlpha(255);
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0029  */
    public static Object updateBatteryChargingIcon$default(LocationBasedBatteryViewModel locationBasedBatteryViewModel, ContinuationImpl continuationImpl) {
        boolean charging;
        ChargeStateModel chargeStateModel = locationBasedBatteryViewModel.chargeState;
        BatteryStyleModel batteryStyleModel = locationBasedBatteryViewModel.batteryStyle;
        locationBasedBatteryViewModel.getClass();
        if (chargeStateModel == null || batteryStyleModel == null) {
            return Unit.INSTANCE;
        }
        int iconStyle = batteryStyleModel.getIconStyle();
        int i = 0;
        if (iconStyle != 0) {
            charging = true;
            if (iconStyle != 1) {
                if (iconStyle == 5) {
                    charging = chargeStateModel.getCharging();
                }
            } else if (!chargeStateModel.getCharging() || !locationBasedBatteryViewModel.isHorizontalBatteryPercentIn()) {
            }
            charging = false;
        } else {
            charging = chargeStateModel.getCharging();
        }
        if (charging) {
            if (chargeStateModel.getPluggedInWireless() && chargeStateModel.getWirelessVoocCharging()) {
                i = 2131236731;
            } else if (chargeStateModel.getPluggedInWireless()) {
                i = 2131236728;
            } else {
                i = chargeStateModel.getSuperVoocCharging() ? 2131236727 : 2131236726;
            }
        }
        Object objEmit = locationBasedBatteryViewModel.batteryCharge.emit(new ChargeIcon(i, batteryStyleModel.getIconStyle() == 0 ? getProgressColor$default(locationBasedBatteryViewModel) : ((BatteryIconColor) locationBasedBatteryViewModel.batteryColor.getValue()).getSingleToneColor(), getThemeId$default(locationBasedBatteryViewModel), charging), continuationImpl);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    public static Object updateBatteryPercentOut$default(LocationBasedBatteryViewModel locationBasedBatteryViewModel, ContinuationImpl continuationImpl) {
        BatteryStyleModel batteryStyleModel = locationBasedBatteryViewModel.batteryStyle;
        ChargeStateModel chargeStateModel = locationBasedBatteryViewModel.chargeState;
        locationBasedBatteryViewModel.getClass();
        if (batteryStyleModel == null || chargeStateModel == null) {
            return Unit.INSTANCE;
        }
        Object objEmit = locationBasedBatteryViewModel.batteryPercentOut.emit(new PercentOutIcon((batteryStyleModel.getPercentStyle() != 2 || batteryStyleModel.getCapsuleShowing() || batteryStyleModel.getIconStyle() == 2) ? false : true, locationBasedBatteryViewModel.batteryLevel, ((BatteryIconColor) locationBasedBatteryViewModel.batteryColor.getValue()).getSingleToneColor(), batteryStyleModel.getLocaleList()), continuationImpl);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    public static Object updateIconStyle$default(LocationBasedBatteryViewModel locationBasedBatteryViewModel, Continuation continuation) {
        BatteryStyleModel batteryStyleModel = locationBasedBatteryViewModel.batteryStyle;
        BatteryThemeModel batteryThemeModel = locationBasedBatteryViewModel.locationThemeStyle;
        locationBasedBatteryViewModel.getClass();
        if (batteryStyleModel == null || batteryThemeModel == null) {
            return Unit.INSTANCE;
        }
        boolean z = batteryStyleModel.getIconStyle() == 5 && locationBasedBatteryViewModel.isHorizontalBatteryPercentIn();
        int iconStyle = batteryStyleModel.getIconStyle();
        LocaleList localeList = batteryStyleModel.getLocaleList();
        int densityDpi = batteryStyleModel.getDensityDpi();
        float scaleFactor = batteryStyleModel.getScaleFactor();
        int themeId$default = getThemeId$default(locationBasedBatteryViewModel);
        BatteryThemeModel batteryThemeModel2 = locationBasedBatteryViewModel.locationThemeStyle;
        boolean z2 = (batteryThemeModel2 != null ? batteryThemeModel2.getThemeMode() : 0) > 0;
        int frameColorMode = batteryThemeModel.getFrameColorMode();
        BatteryThemeModel batteryThemeModel3 = locationBasedBatteryViewModel.locationThemeStyle;
        boolean z3 = (batteryThemeModel3 != null ? batteryThemeModel3.getThemePercentOutBgMode() : 0) > 0;
        BatteryThemeModel batteryThemeModel4 = locationBasedBatteryViewModel.locationThemeStyle;
        Object objEmit = locationBasedBatteryViewModel.batteryIconViewStyle.emit(new BatteryIconStyle(iconStyle, z, localeList, densityDpi, scaleFactor, themeId$default, z2, frameColorMode, z3, (batteryThemeModel4 != null ? batteryThemeModel4.getThemePercentOutProgressMode() : 0) > 0, batteryThemeModel.getBatteryOutChargeBgMode()), continuation);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    public static void updateProgressDrawable$default(LocationBasedBatteryViewModel locationBasedBatteryViewModel, Drawable drawable) {
        BatteryStyleModel batteryStyleModel = locationBasedBatteryViewModel.batteryStyle;
        ChargeStateModel chargeStateModel = locationBasedBatteryViewModel.chargeState;
        Boolean bool = locationBasedBatteryViewModel.speedModeTurnedOn;
        Boolean bool2 = locationBasedBatteryViewModel.isSavingMode;
        boolean zBooleanValue = bool2 != null ? bool2.booleanValue() : false;
        BatteryThemeModel batteryThemeModel = locationBasedBatteryViewModel.locationThemeStyle;
        locationBasedBatteryViewModel.getClass();
        if (batteryStyleModel == null || chargeStateModel == null || batteryThemeModel == null) {
            return;
        }
        if (batteryThemeModel.getThemeModeUntil14() <= 0) {
            if (zBooleanValue) {
                drawable.setTint(locationBasedBatteryViewModel.context.getColor(2131099911));
            } else if (locationBasedBatteryViewModel.isLowPowerMode()) {
                drawable.setTint(locationBasedBatteryViewModel.context.getColor(2131099907));
            } else {
                drawable.setTint(locationBasedBatteryViewModel.chargeIconColor);
            }
        }
        locationBasedBatteryViewModel.setProgressAlpha(drawable);
    }

    public static Object updateWirelessChargingReverseIcon$default(LocationBasedBatteryViewModel locationBasedBatteryViewModel, ContinuationImpl continuationImpl) {
        ChargeStateModel chargeStateModel = locationBasedBatteryViewModel.chargeState;
        BatteryStyleModel batteryStyleModel = locationBasedBatteryViewModel.batteryStyle;
        locationBasedBatteryViewModel.getClass();
        if (chargeStateModel == null || batteryStyleModel == null) {
            return Unit.INSTANCE;
        }
        Object objEmit = locationBasedBatteryViewModel.reverseCharge.emit(new ChargeIcon(2131236729, ((BatteryIconColor) locationBasedBatteryViewModel.batteryColor.getValue()).getSingleToneColor(), getThemeId$default(locationBasedBatteryViewModel), batteryStyleModel.getIconStyle() == 2 ? false : chargeStateModel.getWirelessChargingReverse()), continuationImpl);
        return objEmit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
    }

    public final MutableStateFlow getAllowLayoutTransition() {
        return this.allowLayoutTransition;
    }

    public final MutableStateFlow getBatteryCharge() {
        return this.batteryCharge;
    }

    public final MutableStateFlow getBatteryColor() {
        return this.batteryColor;
    }

    public final MutableStateFlow getBatteryIcon() {
        return this.batteryIcon;
    }

    public final MutableStateFlow getBatteryIconViewStyle() {
        return this.batteryIconViewStyle;
    }

    public final MutableStateFlow getBatteryPercentOut() {
        return this.batteryPercentOut;
    }

    public final Context getContext() {
        return this.context;
    }

    public final ForceShowStyleInteractor getForceInteractor() {
        return this.forceInteractor;
    }

    public final String getModuleTag() {
        return this.moduleTag;
    }

    public final MutableStateFlow getOldHorizontal() {
        return this.oldHorizontal;
    }

    public final MutableStateFlow getReverseCharge() {
        return this.reverseCharge;
    }

    public final boolean isHorizontalBatteryPercentIn() {
        BatteryStyleModel batteryStyleModel;
        BatteryStyleModel batteryStyleModel2;
        BatteryStyleModel batteryStyleModel3 = this.batteryStyle;
        return ((batteryStyleModel3 != null && batteryStyleModel3.getIconStyle() == 1) || ((batteryStyleModel = this.batteryStyle) != null && batteryStyleModel.getIconStyle() == 5)) && (batteryStyleModel2 = this.batteryStyle) != null && batteryStyleModel2.getPercentStyle() == 1;
    }

    public final MutableSharedFlow isLevelOrChargeUpdate() {
        return this.isLevelOrChargeUpdate;
    }

    public final boolean isLowPowerMode() {
        ChargeStateModel chargeStateModel = this.chargeState;
        return (chargeStateModel == null || chargeStateModel.getCharging() || this.batteryLevel > 20) ? false : true;
    }

    public final BatteryThemeModel readThemeStyleFromContext() {
        Configuration configuration = this.context.getResources().getConfiguration();
        configuration.getClass();
        return new BatteryThemeModel(OplusThemeControllerKt.oplusThirdThemeChange(configuration), configuration.uiMode & 48, OplusThemeControllerKt.isOplusCustomTheme(configuration), this.context.getResources().getInteger(2131427721), this.context.getResources().getInteger(2131427717), this.context.getResources().getInteger(2131427778), this.context.getResources().getInteger(2131427709), this.context.getResources().getInteger(2131427718), this.context.getResources().getInteger(2131427720), this.context.getResources().getInteger(2131427719));
    }

    public final void setForceShowStyle(int i) {
        if (i == ((Number) this.forceInteractor.getForceShowStyle().getValue()).intValue() || !this.forceInteractor.getCanWriteForceStyle()) {
            return;
        }
        String str = this.logTag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(i, "showStateChanged showStyle: ", "SystemUi--Statusbar", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR));
        }
        this.forceInteractor.getForceShowStyle().setValue(Integer.valueOf(i));
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass5 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass5(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return LocationBasedBatteryViewModel.this.new AnonymousClass5(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow speedModeTurnedOn = LocationBasedBatteryViewModel.this.interactor.getSpeedModeTurnedOn();
                LocationBasedBatteryViewModel locationBasedBatteryViewModel = LocationBasedBatteryViewModel.this;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                anonymousClass1.this$0 = locationBasedBatteryViewModel;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (speedModeTurnedOn.collect(anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            Regex$.ExternalSyntheticBUOutline0.m$1();
            return null;
        }

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$5$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 implements FlowCollector {
            public /* synthetic */ LocationBasedBatteryViewModel this$0;

            /* JADX WARN: Code duplicated, block: B:7:0x0015  */
            /* JADX WARN: Code restructure failed: missing block: B:25:0x0071, code lost:
            
                if (com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$updateOldHorizontalBatteryIcon(r0, r1) == r8) goto L26;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(boolean r7, kotlin.coroutines.Continuation r8) {
                /*
                    r6 = this;
                    com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel r0 = r6.this$0
                    boolean r1 = r8 instanceof com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$5$1$emit$1
                    if (r1 == 0) goto L15
                    r1 = r8
                    com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$5$1$emit$1 r1 = (com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$5$1$emit$1) r1
                    int r2 = r1.label
                    r3 = -2147483648(0xffffffff80000000, float:-0.0)
                    r4 = r2 & r3
                    if (r4 == 0) goto L15
                    int r2 = r2 - r3
                    r1.label = r2
                    goto L1a
                L15:
                    com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$5$1$emit$1 r1 = new com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$5$1$emit$1
                    r1.<init>(r6, r8)
                L1a:
                    java.lang.Object r6 = r1.result
                    java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r1.label
                    r3 = 2
                    r4 = 1
                    if (r2 == 0) goto L3b
                    if (r2 == r4) goto L35
                    if (r2 != r3) goto L2e
                    kotlin.ResultKt.throwOnFailure(r6)
                    goto L74
                L2e:
                    java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                    okio.Path$.ExternalSyntheticBUOutline0.m$1(r6)
                    r6 = 0
                    return r6
                L35:
                    boolean r7 = r1.Z$0
                    kotlin.ResultKt.throwOnFailure(r6)
                    goto L69
                L3b:
                    kotlin.ResultKt.throwOnFailure(r6)
                    java.lang.String r6 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$getLogTag$p(r0)
                    boolean r2 = com.oplusos.systemui.common.util.LogUtil.getNormalDebug()
                    if (r2 == 0) goto L57
                    java.lang.String r2 = "Statusbar"
                    boolean r2 = com.oplusos.systemui.common.util.LogUtil.isTagValid(r2)
                    if (r2 == 0) goto L57
                    java.lang.String r2 = "SystemUi--Statusbar"
                    java.lang.String r5 = "-->speedModeTurnedOn changed"
                    com.android.settingslib.volume.MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(r6, r5, r2)
                L57:
                    java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)
                    com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$setSpeedModeTurnedOn$p(r0, r6)
                    r1.Z$0 = r7
                    r1.label = r4
                    java.lang.Object r6 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$updateBatteryIcon(r0, r1)
                    if (r6 != r8) goto L69
                    goto L73
                L69:
                    r1.Z$0 = r7
                    r1.label = r3
                    java.lang.Object r6 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$updateOldHorizontalBatteryIcon(r0, r1)
                    if (r6 != r8) goto L74
                L73:
                    return r8
                L74:
                    kotlin.Unit r6 = kotlin.Unit.INSTANCE
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.AnonymousClass5.AnonymousClass1.emit(boolean, kotlin.coroutines.Continuation):java.lang.Object");
            }

            public final /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return emit(((Boolean) obj).booleanValue(), continuation);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$6, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass6 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass6(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return LocationBasedBatteryViewModel.this.new AnonymousClass6(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlowIsSavingMode = LocationBasedBatteryViewModel.this.interactor.isSavingMode();
                LocationBasedBatteryViewModel locationBasedBatteryViewModel = LocationBasedBatteryViewModel.this;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                anonymousClass1.this$0 = locationBasedBatteryViewModel;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (stateFlowIsSavingMode.collect(anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            Regex$.ExternalSyntheticBUOutline0.m$1();
            return null;
        }

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$6$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 implements FlowCollector {
            public /* synthetic */ LocationBasedBatteryViewModel this$0;

            /* JADX WARN: Code duplicated, block: B:34:0x0098 A[PHI: r9
              0x0098: PHI (r9v3 boolean) = (r9v2 boolean), (r9v6 boolean) binds: [B:32:0x0095, B:17:0x003c] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:7:0x0015  */
            /* JADX WARN: Code restructure failed: missing block: B:35:0x00a0, code lost:
            
                if (com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$updateOldHorizontalBatteryIconTint(r0, r1) == r10) goto L36;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(boolean r9, kotlin.coroutines.Continuation r10) {
                /*
                    r8 = this;
                    com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel r0 = r8.this$0
                    boolean r1 = r10 instanceof com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$6$1$emit$1
                    if (r1 == 0) goto L15
                    r1 = r10
                    com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$6$1$emit$1 r1 = (com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$6$1$emit$1) r1
                    int r2 = r1.label
                    r3 = -2147483648(0xffffffff80000000, float:-0.0)
                    r4 = r2 & r3
                    if (r4 == 0) goto L15
                    int r2 = r2 - r3
                    r1.label = r2
                    goto L1a
                L15:
                    com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$6$1$emit$1 r1 = new com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$6$1$emit$1
                    r1.<init>(r8, r10)
                L1a:
                    java.lang.Object r8 = r1.result
                    java.lang.Object r10 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r1.label
                    r3 = 4
                    r4 = 3
                    r5 = 2
                    r6 = 1
                    if (r2 == 0) goto L4e
                    if (r2 == r6) goto L48
                    if (r2 == r5) goto L42
                    if (r2 == r4) goto L3c
                    if (r2 != r3) goto L35
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto La3
                L35:
                    java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                    okio.Path$.ExternalSyntheticBUOutline0.m$1(r8)
                    r8 = 0
                    return r8
                L3c:
                    boolean r9 = r1.Z$0
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L98
                L42:
                    boolean r9 = r1.Z$0
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L8d
                L48:
                    boolean r9 = r1.Z$0
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L82
                L4e:
                    kotlin.ResultKt.throwOnFailure(r8)
                    java.lang.String r8 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$getLogTag$p(r0)
                    boolean r2 = com.oplusos.systemui.common.util.LogUtil.getNormalDebug()
                    if (r2 == 0) goto L70
                    java.lang.String r2 = "Statusbar"
                    boolean r2 = com.oplusos.systemui.common.util.LogUtil.isTagValid(r2)
                    if (r2 == 0) goto L70
                    java.lang.String r2 = "-->"
                    java.lang.StringBuilder r8 = androidx.car.app.serialization.Bundler$.ExternalSyntheticOutline0.m(r8, r2)
                    java.lang.String r2 = "isSavingMode changed isSavingMode:"
                    java.lang.String r7 = "SystemUi--Statusbar"
                    com.oplus.systemui.aod.aodclock.off.AodClockLayout$3$.ExternalSyntheticOutline0.m(r2, r7, r8, r9)
                L70:
                    java.lang.Boolean r8 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r9)
                    com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$setSavingMode$p(r0, r8)
                    r1.Z$0 = r9
                    r1.label = r6
                    java.lang.Object r8 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$updateBatteryIcon(r0, r1)
                    if (r8 != r10) goto L82
                    goto La2
                L82:
                    r1.Z$0 = r9
                    r1.label = r5
                    java.lang.Object r8 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.updateBatteryChargingIcon$default(r0, r1)
                    if (r8 != r10) goto L8d
                    goto La2
                L8d:
                    r1.Z$0 = r9
                    r1.label = r4
                    java.lang.Object r8 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.updateWirelessChargingReverseIcon$default(r0, r1)
                    if (r8 != r10) goto L98
                    goto La2
                L98:
                    r1.Z$0 = r9
                    r1.label = r3
                    java.lang.Object r8 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$updateOldHorizontalBatteryIconTint(r0, r1)
                    if (r8 != r10) goto La3
                La2:
                    return r10
                La3:
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.AnonymousClass6.AnonymousClass1.emit(boolean, kotlin.coroutines.Continuation):java.lang.Object");
            }

            public final /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return emit(((Boolean) obj).booleanValue(), continuation);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass3(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return LocationBasedBatteryViewModel.this.new AnonymousClass3(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow batteryLevel = LocationBasedBatteryViewModel.this.interactor.getBatteryLevel();
                LocationBasedBatteryViewModel locationBasedBatteryViewModel = LocationBasedBatteryViewModel.this;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                anonymousClass1.this$0 = locationBasedBatteryViewModel;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (batteryLevel.collect(anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            Regex$.ExternalSyntheticBUOutline0.m$1();
            return null;
        }

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$3$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 implements FlowCollector {
            public /* synthetic */ LocationBasedBatteryViewModel this$0;

            /* JADX WARN: Code duplicated, block: B:34:0x009b A[PHI: r8
              0x009b: PHI (r8v3 int) = (r8v2 int), (r8v6 int) binds: [B:32:0x0098, B:17:0x003c] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:7:0x0015  */
            /* JADX WARN: Code restructure failed: missing block: B:35:0x00a3, code lost:
            
                if (com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$updateOldHorizontalBatteryIcon(r0, r1) == r9) goto L36;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(int r8, kotlin.coroutines.Continuation r9) {
                /*
                    r7 = this;
                    com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel r0 = r7.this$0
                    boolean r1 = r9 instanceof com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$3$1$emit$1
                    if (r1 == 0) goto L15
                    r1 = r9
                    com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$3$1$emit$1 r1 = (com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$3$1$emit$1) r1
                    int r2 = r1.label
                    r3 = -2147483648(0xffffffff80000000, float:-0.0)
                    r4 = r2 & r3
                    if (r4 == 0) goto L15
                    int r2 = r2 - r3
                    r1.label = r2
                    goto L1a
                L15:
                    com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$3$1$emit$1 r1 = new com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel$3$1$emit$1
                    r1.<init>(r7, r9)
                L1a:
                    java.lang.Object r7 = r1.result
                    java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r1.label
                    r3 = 4
                    r4 = 3
                    r5 = 2
                    r6 = 1
                    if (r2 == 0) goto L4e
                    if (r2 == r6) goto L48
                    if (r2 == r5) goto L42
                    if (r2 == r4) goto L3c
                    if (r2 != r3) goto L35
                    kotlin.ResultKt.throwOnFailure(r7)
                    goto La6
                L35:
                    java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                    okio.Path$.ExternalSyntheticBUOutline0.m$1(r7)
                    r7 = 0
                    return r7
                L3c:
                    int r8 = r1.I$0
                    kotlin.ResultKt.throwOnFailure(r7)
                    goto L9b
                L42:
                    int r8 = r1.I$0
                    kotlin.ResultKt.throwOnFailure(r7)
                    goto L90
                L48:
                    int r8 = r1.I$0
                    kotlin.ResultKt.throwOnFailure(r7)
                    goto L64
                L4e:
                    kotlin.ResultKt.throwOnFailure(r7)
                    kotlinx.coroutines.flow.MutableSharedFlow r7 = r0.isLevelOrChargeUpdate()
                    java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
                    r1.I$0 = r8
                    r1.label = r6
                    java.lang.Object r7 = r7.emit(r2, r1)
                    if (r7 != r9) goto L64
                    goto La5
                L64:
                    r0.batteryLevel = r8
                    java.lang.String r7 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$getLogTag$p(r0)
                    boolean r2 = com.oplusos.systemui.common.util.LogUtil.getNormalDebug()
                    if (r2 == 0) goto L85
                    java.lang.String r2 = "Statusbar"
                    boolean r2 = com.oplusos.systemui.common.util.LogUtil.isTagValid(r2)
                    if (r2 == 0) goto L85
                    java.lang.String r2 = "-->"
                    java.lang.StringBuilder r7 = androidx.car.app.serialization.Bundler$.ExternalSyntheticOutline0.m(r7, r2)
                    java.lang.String r2 = "batteryLevel changed: "
                    java.lang.String r6 = "SystemUi--Statusbar"
                    com.oplus.systemui.aod.controller.BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(r8, r2, r6, r7)
                L85:
                    r1.I$0 = r8
                    r1.label = r5
                    java.lang.Object r7 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.updateBatteryPercentOut$default(r0, r1)
                    if (r7 != r9) goto L90
                    goto La5
                L90:
                    r1.I$0 = r8
                    r1.label = r4
                    java.lang.Object r7 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$updateBatteryIcon(r0, r1)
                    if (r7 != r9) goto L9b
                    goto La5
                L9b:
                    r1.I$0 = r8
                    r1.label = r3
                    java.lang.Object r7 = com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.access$updateOldHorizontalBatteryIcon(r0, r1)
                    if (r7 != r9) goto La6
                La5:
                    return r9
                La6:
                    kotlin.Unit r7 = kotlin.Unit.INSTANCE
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel.AnonymousClass3.AnonymousClass1.emit(int, kotlin.coroutines.Continuation):java.lang.Object");
            }

            public final /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return emit(((Number) obj).intValue(), continuation);
            }
        }
    }
}
