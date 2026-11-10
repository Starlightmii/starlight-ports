package com.oplus.systemui.notification.row.material;

import android.app.Notification;
import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.android.systemui.statusbar.phone.SysuiDarkIconDispatcher;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.kotlin.Quint;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.wallpaper.OplusLockscreenWallpaperDimmingHelper;
import com.oplus.systemui.keyguard.wallpaper.OplusWallpaperColorManager;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function6;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMaterialColorModeController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MaterialColorController";
    private final MutableStateFlow _blurMode;
    private final MutableStateFlow _currentMaterialColorMode;
    private final MutableStateFlow _darkMode;
    private final MutableStateFlow _isWallpaperDimmingNotSupported;
    private final MutableStateFlow _isWallpaperInRegionB;
    private final MutableStateFlow _statusBarState;
    private final MutableStateFlow _useReverseColor;
    private final Lazy configurationControllerLazy;
    private final NotificationMaterialColorModeController$configurationListener$1 configurationListener;
    private final Context context;
    private final CoroutineScope coroutineScope;
    private MaterialColorMode currentMaterialColorModeValue;
    private final Flow isMaterialDisabled;
    public final boolean isMiddleOrHighAnimLevel;
    private final StateFlow statusBarState;
    private final StatusBarStateController statusBarStateController;
    private final NotificationMaterialColorModeController$statusBarStateListener$1 statusBarStateListener;
    private final SuperPowerSaveRepository superPowerSaveRepository;
    private final Lazy sysuiDarkIconDispatcher;
    private final OplusLockscreenWallpaperDimmingHelper.WallpaperDimmingSnapshotListener wallpaperDimmingSnapshotListener;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.material.NotificationMaterialColorModeController$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        boolean Z$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.row.material.NotificationMaterialColorModeController$start$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00321 extends SuspendLambda implements Function2 {
            final /* synthetic */ boolean $isWallpaperDimmingNotSupported;
            int label;
            final /* synthetic */ NotificationMaterialColorModeController this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00321(boolean z, NotificationMaterialColorModeController notificationMaterialColorModeController, Continuation continuation) {
                super(2, continuation);
                this.$isWallpaperDimmingNotSupported = z;
                this.this$0 = notificationMaterialColorModeController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00321(this.$isWallpaperDimmingNotSupported, this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                if (this.$isWallpaperDimmingNotSupported != ((Boolean) this.this$0._isWallpaperDimmingNotSupported.getValue()).booleanValue()) {
                    this.this$0._isWallpaperDimmingNotSupported.setValue(Boxing.boxBoolean(this.$isWallpaperDimmingNotSupported));
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return NotificationMaterialColorModeController.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                boolean zAccess$isWallpaperDimmingNotSupported = NotificationMaterialColorModeController.access$isWallpaperDimmingNotSupported(NotificationMaterialColorModeController.this);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C00321 c00321 = new C00321(zAccess$isWallpaperDimmingNotSupported, NotificationMaterialColorModeController.this, null);
                this.Z$0 = zAccess$isWallpaperDimmingNotSupported;
                this.label = 1;
                if (BuildersKt.withContext(main, c00321, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.material.NotificationMaterialColorModeController$start$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.row.material.NotificationMaterialColorModeController$start$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function6 {
            /* synthetic */ int I$0;
            /* synthetic */ boolean Z$0;
            /* synthetic */ boolean Z$1;
            /* synthetic */ boolean Z$2;
            /* synthetic */ boolean Z$3;
            int label;

            public AnonymousClass1(Continuation continuation) {
                super(6, continuation);
            }

            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
                int iIntValue = ((Number) obj).intValue();
                boolean zBooleanValue = ((Boolean) obj2).booleanValue();
                boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
                boolean zBooleanValue3 = ((Boolean) obj4).booleanValue();
                boolean zBooleanValue4 = ((Boolean) obj5).booleanValue();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1((Continuation) obj6);
                anonymousClass1.I$0 = iIntValue;
                anonymousClass1.Z$0 = zBooleanValue;
                anonymousClass1.Z$1 = zBooleanValue2;
                anonymousClass1.Z$2 = zBooleanValue3;
                anonymousClass1.Z$3 = zBooleanValue4;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                int i = this.I$0;
                boolean z = this.Z$0;
                boolean z2 = this.Z$1;
                boolean z3 = this.Z$2;
                boolean z4 = this.Z$3;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    return new Quint(Boxing.boxInt(i), Boxing.boxBoolean(z), Boxing.boxBoolean(z2), Boxing.boxBoolean(z3), Boxing.boxBoolean(z4));
                }
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        /* JADX INFO: renamed from: com.oplus.systemui.notification.row.material.NotificationMaterialColorModeController$start$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00332 extends SuspendLambda implements Function2 {
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ NotificationMaterialColorModeController this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00332(NotificationMaterialColorModeController notificationMaterialColorModeController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = notificationMaterialColorModeController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C00332 c00332 = new C00332(this.this$0, continuation);
                c00332.L$0 = obj;
                return c00332;
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((Quint) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Quint quint = (Quint) this.L$0;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                int iIntValue = ((Number) quint.first).intValue();
                boolean zBooleanValue = ((Boolean) quint.second).booleanValue();
                boolean zBooleanValue2 = ((Boolean) quint.third).booleanValue();
                boolean zBooleanValue3 = ((Boolean) quint.fourth).booleanValue();
                boolean zBooleanValue4 = ((Boolean) quint.fifth).booleanValue();
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("state changed: state: ", iIntValue, ", isDark=", ", reverse=", zBooleanValue);
                    KeyguardLogger$.ExternalSyntheticOutline0.m(", isMaterialDisabled=", ",isRegionB=", sbM, zBooleanValue2, zBooleanValue3);
                    SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("MaterialColorController-->", "SystemUi--Notification", sbM, zBooleanValue4);
                }
                NotificationMaterialColorModeController.access$updateBlurMode(this.this$0, iIntValue, zBooleanValue, zBooleanValue3, zBooleanValue2, zBooleanValue4);
                NotificationMaterialColorModeController.access$updateMaterialColorMode(this.this$0, iIntValue, zBooleanValue, zBooleanValue2, zBooleanValue3);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return NotificationMaterialColorModeController.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.combine(NotificationMaterialColorModeController.this.getStatusBarState(), NotificationMaterialColorModeController.this.getDarkMode(), NotificationMaterialColorModeController.this.getUseReverseColor(), NotificationMaterialColorModeController.this.isMaterialDisabled, NotificationMaterialColorModeController.this.isWallpaperInRegionB(), new AnonymousClass1(null)));
                C00332 c00332 = new C00332(NotificationMaterialColorModeController.this, null);
                this.label = 1;
                if (FlowKt.collectLatest(flowDistinctUntilChanged, c00332, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public NotificationMaterialColorModeController(Context context, Lazy lazy, StatusBarStateController statusBarStateController, CoroutineScope coroutineScope, SuperPowerSaveRepository superPowerSaveRepository, Lazy lazy2) {
        this.context = context;
        this.configurationControllerLazy = lazy;
        this.statusBarStateController = statusBarStateController;
        this.coroutineScope = coroutineScope;
        this.superPowerSaveRepository = superPowerSaveRepository;
        this.sysuiDarkIconDispatcher = lazy2;
        MaterialColorMode materialColorMode = MaterialColorMode.UNINITIATED;
        this.currentMaterialColorModeValue = materialColorMode;
        this._currentMaterialColorMode = StateFlowKt.MutableStateFlow(materialColorMode);
        Boolean bool = Boolean.FALSE;
        this._useReverseColor = StateFlowKt.MutableStateFlow(bool);
        this._isWallpaperDimmingNotSupported = StateFlowKt.MutableStateFlow(Boolean.TRUE);
        this._isWallpaperInRegionB = StateFlowKt.MutableStateFlow(bool);
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Integer.valueOf(statusBarStateController.getState()));
        this._statusBarState = MutableStateFlow;
        this.statusBarState = MutableStateFlow;
        this._darkMode = StateFlowKt.MutableStateFlow(Boolean.valueOf(OplusDarkModeUtil.isNightMode(context)));
        this._blurMode = StateFlowKt.MutableStateFlow(NotificationBlurMode.UNINITIATED);
        this.isMiddleOrHighAnimLevel = ScrimUtil.isMiddleOrHighAnimLevel(context);
        this.isMaterialDisabled = FlowKt.combine(superPowerSaveRepository.getSuperPowerSaveMode(), ScrimControllerEx.Companion.isThirdThemeMode(), new NotificationMaterialColorModeController$isMaterialDisabled$1(3, null));
        NotificationMaterialColorModeController$wallpaperDimmingSnapshotListener$1 notificationMaterialColorModeController$wallpaperDimmingSnapshotListener$1 = new NotificationMaterialColorModeController$wallpaperDimmingSnapshotListener$1();
        notificationMaterialColorModeController$wallpaperDimmingSnapshotListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.wallpaperDimmingSnapshotListener = notificationMaterialColorModeController$wallpaperDimmingSnapshotListener$1;
        NotificationMaterialColorModeController$statusBarStateListener$1 notificationMaterialColorModeController$statusBarStateListener$1 = new NotificationMaterialColorModeController$statusBarStateListener$1();
        notificationMaterialColorModeController$statusBarStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.statusBarStateListener = notificationMaterialColorModeController$statusBarStateListener$1;
        NotificationMaterialColorModeController$configurationListener$1 notificationMaterialColorModeController$configurationListener$1 = new NotificationMaterialColorModeController$configurationListener$1();
        notificationMaterialColorModeController$configurationListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.configurationListener = notificationMaterialColorModeController$configurationListener$1;
    }

    public static final boolean access$isWallpaperDimmingNotSupported(NotificationMaterialColorModeController notificationMaterialColorModeController) {
        notificationMaterialColorModeController.getClass();
        return !OplusLockscreenWallpaperDimmingHelper.INSTANCE.isWallpaperDimmingSupported(notificationMaterialColorModeController.context);
    }

    public static final void access$updateBlurMode(NotificationMaterialColorModeController notificationMaterialColorModeController, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        NotificationBlurMode notificationBlurMode;
        notificationMaterialColorModeController.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("calculateBlurMode: state=", i, ", isDark=", ", isMaterialDisabled=", z);
            KeyguardLogger$.ExternalSyntheticOutline0.m(", isRegionA=", ", isRegionB=", sbM, z2, z3);
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("MaterialColorController-->", "SystemUi--Notification", sbM, z4);
        }
        if (z2 || !NotificationMaterialColorExImpl.Companion.isSupported(notificationMaterialColorModeController.context)) {
            notificationBlurMode = z ? NotificationBlurMode.NO_BLUR_DARK : NotificationBlurMode.NO_BLUR_LIGHT;
        } else if (i == 1 || i == 2) {
            notificationBlurMode = z3 ? NotificationBlurMode.WALLPAPER_A : NotificationBlurMode.WALLPAPER_B;
        } else {
            notificationBlurMode = z ? NotificationBlurMode.SHADE_DARK : NotificationBlurMode.SHADE_LIGHT;
        }
        if (notificationMaterialColorModeController.getBlurMode().getValue() != notificationBlurMode) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "MaterialColorController-->".concat("updateBlurMode: blurMode mode=" + notificationBlurMode + ", last=" + notificationMaterialColorModeController.getBlurMode().getValue()));
            }
            notificationMaterialColorModeController._blurMode.setValue(notificationBlurMode);
        }
    }

    /* JADX WARN: Code duplicated, block: B:36:0x0071  */
    /* JADX WARN: Code duplicated, block: B:42:? A[RETURN, SYNTHETIC] */
    public static final void access$updateMaterialColorMode(NotificationMaterialColorModeController notificationMaterialColorModeController, int i, boolean z, boolean z2, boolean z3) {
        MaterialColorMode materialColorMode;
        if (!z3) {
            if (NotificationMaterialColorExImpl.Companion.isSupported(notificationMaterialColorModeController.context)) {
                if (i == 1 || i == 2) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("calculateMaterialColorModeCommon: in lockscreen, darkMode: ", ", useReverseColor=", "MaterialColorController-->", z, z2));
                    }
                    materialColorMode = z2 ? MaterialColorMode.NORMAL_LIGHT : MaterialColorMode.MATERIAL;
                } else {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "MaterialColorController-->calculateMaterialColorModeCommon: in Shade, use material");
                    }
                    materialColorMode = MaterialColorMode.MATERIAL;
                }
            }
            if (notificationMaterialColorModeController.currentMaterialColorModeValue != materialColorMode) {
                notificationMaterialColorModeController.currentMaterialColorModeValue = materialColorMode;
                notificationMaterialColorModeController._currentMaterialColorMode.setValue(materialColorMode);
                if (LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                }
                Log.d("SystemUi--Notification", "MaterialColorController-->".concat("updateMaterialColorMode: mode=" + notificationMaterialColorModeController.currentMaterialColorModeValue));
                return;
            }
        }
        notificationMaterialColorModeController.getClass();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("calculateMaterialColorModeCommon: normal color, darkMode: ", ", isMaterialDisabled=", "MaterialColorController-->", z, z3));
        }
        materialColorMode = z ? MaterialColorMode.NORMAL_DARK : MaterialColorMode.NORMAL_LIGHT;
        if (notificationMaterialColorModeController.currentMaterialColorModeValue != materialColorMode) {
            notificationMaterialColorModeController.currentMaterialColorModeValue = materialColorMode;
            notificationMaterialColorModeController._currentMaterialColorMode.setValue(materialColorMode);
            if (LogUtil.getNormalDebug()) {
            }
        }
    }

    public static final void access$updateReverseColorState(NotificationMaterialColorModeController notificationMaterialColorModeController, OplusLockscreenWallpaperDimmingHelper.WallpaperHsbRegion wallpaperHsbRegion, String str) {
        boolean z = wallpaperHsbRegion == OplusLockscreenWallpaperDimmingHelper.WallpaperHsbRegion.REGION_A;
        boolean z2 = wallpaperHsbRegion == OplusLockscreenWallpaperDimmingHelper.WallpaperHsbRegion.REGION_B;
        OplusWallpaperColorManager.INSTANCE.getClass();
        boolean z3 = OplusWallpaperColorManager.isLightColor;
        boolean z4 = z || z3;
        KeyguardUtils.Companion.getKeyguardUtils(notificationMaterialColorModeController.context).isUseReverseColor = z4;
        if (((Boolean) notificationMaterialColorModeController.getUseReverseColor().getValue()).booleanValue() != z4) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Object value = notificationMaterialColorModeController.getUseReverseColor().getValue();
                StringBuilder sb = new StringBuilder("updateReverseColorState reason=");
                sb.append(str);
                sb.append(", old=");
                sb.append(value);
                sb.append(", region=");
                sb.append(wallpaperHsbRegion);
                sb.append(", useReverse=");
                sb.append(z4);
                sb.append(", lightColor=");
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("MaterialColorController-->", "SystemUi--Notification", sb, z3);
            }
            notificationMaterialColorModeController._useReverseColor.setValue(Boolean.valueOf(z4));
        }
        if (((Boolean) notificationMaterialColorModeController.isWallpaperInRegionB().getValue()).booleanValue() != z2) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "MaterialColorController-->".concat("updateReverseBlurState reason=" + str + ", old=" + notificationMaterialColorModeController.isWallpaperInRegionB().getValue() + ", region=" + wallpaperHsbRegion + ", isRegionAB=" + z2));
            }
            notificationMaterialColorModeController._isWallpaperInRegionB.setValue(Boolean.valueOf(z2));
        }
    }

    public final StateFlow getBlurMode() {
        return this._blurMode;
    }

    public final StateFlow getCurrentMaterialColorMode() {
        return this._currentMaterialColorMode;
    }

    public final StateFlow getDarkMode() {
        return this._darkMode;
    }

    public final NotificationBlurMode getMaterialBlurModeForRow(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        if ((!z && (expandableNotificationRow == null || !expandableNotificationRow.isHeadsUpState())) || !this.isMiddleOrHighAnimLevel) {
            return (NotificationBlurMode) getBlurMode().getValue();
        }
        if (((Boolean) ScrimControllerEx.Companion.isThirdThemeMode().getValue()).booleanValue()) {
            return ((Boolean) getDarkMode().getValue()).booleanValue() ? NotificationBlurMode.NO_BLUR_DARK : NotificationBlurMode.NO_BLUR_LIGHT;
        }
        return ((double) ((SysuiDarkIconDispatcher.DarkChange) FlowKt.asStateFlow(((SysuiDarkIconDispatcher) this.sysuiDarkIconDispatcher.get()).mDarkChangeFlow).getValue()).darkIntensity) >= 0.5d ? NotificationBlurMode.HEADS_UP_MATERIAL_DARK : NotificationBlurMode.HEADS_UP_MATERIAL_LIGHT;
    }

    public final MaterialColorMode getMaterialColorModeForRow(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        NotificationEntry entry;
        StatusBarNotification sbn;
        Notification notification;
        if (expandableNotificationRow != null && (entry = expandableNotificationRow.getEntry()) != null && (sbn = entry.getSbn()) != null && (notification = sbn.getNotification()) != null && notification.isColorized()) {
            return MaterialColorMode.COLOR_NOTIFICATION;
        }
        if (z || (expandableNotificationRow != null && expandableNotificationRow.isHeadsUpState())) {
            return (!this.isMiddleOrHighAnimLevel || ((Boolean) ScrimControllerEx.Companion.isThirdThemeMode().getValue()).booleanValue() || ((double) ((SysuiDarkIconDispatcher.DarkChange) FlowKt.asStateFlow(((SysuiDarkIconDispatcher) this.sysuiDarkIconDispatcher.get()).mDarkChangeFlow).getValue()).darkIntensity) < 0.5d) ? this.currentMaterialColorModeValue : MaterialColorMode.NORMAL_LIGHT;
        }
        return this.currentMaterialColorModeValue;
    }

    public final StateFlow getStatusBarState() {
        return this.statusBarState;
    }

    public final StateFlow getUseReverseColor() {
        return this._useReverseColor;
    }

    public final StateFlow isWallpaperDimmingNotSupported() {
        return this._isWallpaperDimmingNotSupported;
    }

    public final StateFlow isWallpaperInRegionB() {
        return this._isWallpaperInRegionB;
    }

    public final void start() {
        ((ConfigurationController) this.configurationControllerLazy.get()).addCallback(this.configurationListener);
        this.statusBarStateController.addCallback(this.statusBarStateListener);
        OplusLockscreenWallpaperDimmingHelper.INSTANCE.setSnapshotListener(this.wallpaperDimmingSnapshotListener);
        BuildersKt.launch$default(this.coroutineScope, Dispatchers.getIO(), (CoroutineStart) null, new AnonymousClass1(null), 2);
        BuildersKt.launch$default(this.coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new AnonymousClass2(null), 2);
    }
}
