package com.oplus.systemui.qs.base.seek;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.compose.runtime.OpaqueKey$;
import androidx.compose.ui.spatial.RectList$;
import androidx.databinding.ObservableField;
import com.airbnb.lottie.LottieAnimationView;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.android.systemui.biometrics.ui.PromptIconState$;
import com.android.systemui.plugins.qs.IQSSpotLightConfig;
import com.android.systemui.plugins.qs.NonNullObservableField;
import com.android.systemui.plugins.qs.NullableObservableField;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.plugins.qs.QSVolume;
import com.coui.appcompat.seekbar.COUIVerticalSeekBar;
import com.oplus.systemui.aod.aodclock.off.AodClockLayout$3$;
import com.oplus.systemui.aod.controller.BaseAodClockLayoutController$bindSceneViewHolder$2$;
import com.oplus.systemui.qs.base.util.PrefUtil;
import com.oplus.systemui.qs.base.util.QsUserContextProvider;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplus.systemui.qs.base.util.ViewController;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.view.ViewExtKt;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQsVolumeSliderController extends ViewController implements COUIVerticalSeekBar.OnSeekBarChangeListener, COUIVerticalSeekBar.OnDeformedListener {
    public static final Companion Companion = new Companion();
    public static final String PREF_KEY_LAST_MUSIC_VOLUME_LEVEL = "lastQsMusicVolumeLevel";
    private static final String PREF_KEY_LAST_SEEKBAR_VOLUME_LEVEL = "lastQsSeekbarVolumeLevel";
    private static final String PREF_KEY_LAST_SLIDER_PROGRESS = "lastQsVolumeSliderProgress";
    private final NonNullObservableField<Boolean> a11yStreamShow;
    private final NonNullObservableField<Unit> a11yStreamShowUpdate;
    public int asyncVolumeProgress;
    private final Handler bgHandler;
    public float bottomDeformed;
    private final NonNullObservableField<Unit> disableShowVolumeDialogUpdate;
    public int distanceFromBottom;
    private CharSequence iconContentDescription;
    public final int initialSeekBarVolumeLevel;
    public final int initialVolumeSliderProgress;
    public boolean isEditMode;
    public boolean isProgressChanged;
    public boolean isQSListeningCache;
    private NonNullObservableField<Boolean> isQsListening;
    public boolean isTrackingTouch;
    private final OplusQsBaseToggleSliderLayout layout;
    private final View.OnLayoutChangeListener layoutChangedListener;
    public int mLastProgress;
    private final View.OnTouchListener mTouchListener;
    private final Handler mainHandler;
    private final NonNullObservableField<Integer> musicMaxVolumeLevel;
    private final NonNullObservableField<Integer> musicMinVolumeLevel;
    private final NonNullObservableField<String> musicStreamLabel;
    private final NonNullObservableField<Unit> musicStreamLabelUpdate;
    private final NonNullObservableField<Boolean> musicStreamZenMuted;
    private final NonNullObservableField<Unit> musicStreamZenMutedUpdate;
    private final NonNullObservableField<Integer> musicVolumeLevel;
    private final NonNullObservableField<QSVolume.VolumeState> musicVolumeState;
    private final NonNullObservableField<Unit> musicVolumeStateUpdate;
    public boolean progressUpdated;
    private final NonNullObservableField<Unit> ringModeUpdate;
    private final NonNullObservableField<Integer> seekBarVolumeLevel;
    private final NonNullObservableField<Unit> seekBarVolumeLevelOnChanged;
    private CharSequence sliderContentDescription;
    private final View.OnTouchListener sliderTouchListener;
    public final String tag;
    public float topDeformed;
    private final QsUserContextProvider userContextProvider;
    private final NullableObservableField<Unit> viewEnableUpdater;
    private final QSVolume volumeController;
    private final View.OnClickListener volumeIconClickListener;
    private final NonNullObservableField<String> volumeIconContentDescription;
    private final NullableObservableField<Unit> volumeIconContentDescriptionUpdater;
    private final NullableObservableField<Unit> volumeIconProgressLevelUpdater;
    private final OplusQsVolumeIconView volumeIconView;
    private final NullableObservableField<Unit> volumeSeekBarMaxProgressUpdater;
    private final NullableObservableField<Unit> volumeSeekBarMinProgressUpdater;
    private final NullableObservableField<Unit> volumeSeekBarProgressUpdater;
    private final NullableObservableField<Unit> volumeSliderContentDescriptionUpdater;
    private final NonNullObservableField<Unit> volumeUnchangeUpdate;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static int convertProgressToVolumeLevel(int i, int i2) {
            int i3 = i / 100;
            int i4 = i3 - 1;
            if (i2 == 0) {
                return 0;
            }
            return i2 == i ? i3 : ((int) ((i2 / i) * i4)) + 1;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.base.seek.OplusQsVolumeSliderController$handleRingModeUpdate$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ OplusQsVolumeSliderController this$0;

        @Override // java.lang.Runnable
        public final void run() {
            OplusQsVerticalSeekBar slider;
            OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) this.this$0.mView;
            if (oplusQsBaseToggleSliderLayout == null || (slider = oplusQsBaseToggleSliderLayout.getSlider()) == null) {
                return;
            }
            slider.stopPhysicsMove();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.base.seek.OplusQsVolumeSliderController$handleUpdateVolumeSeekBarProgress$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC02521 implements Runnable {
        public /* synthetic */ boolean $performAnim;
        public /* synthetic */ int $progress;
        public /* synthetic */ OplusQsVolumeSliderController this$0;

        @Override // java.lang.Runnable
        public final void run() {
            ((OplusQsBaseToggleSliderLayout) this.this$0.mView).setProgress(this.$progress, 1, this.$performAnim);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.base.seek.OplusQsVolumeSliderController$updateVolumeIconState$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC02531 implements Runnable {
        public /* synthetic */ boolean $isUIModeChange;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ QSVolume.VolumeState $state;
        public /* synthetic */ OplusQsVolumeSliderController this$0;

        public /* synthetic */ RunnableC02531(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Float alpha;
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.updateIconState(this.$state, this.$isUIModeChange);
                    break;
                default:
                    OplusQsVolumeIconView oplusQsVolumeIconView = this.this$0.volumeIconView;
                    float fFloatValue = (oplusQsVolumeIconView == null || (alpha = oplusQsVolumeIconView.getAlpha(this.$state)) == null) ? 1.0f : alpha.floatValue();
                    String tag = this.this$0.getTag();
                    boolean z = this.$isUIModeChange;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
                        sbM.append("updateVolumeSeekBarEnable: enable=" + z + " alpha=" + fFloatValue);
                        Log.d("SystemUi--QuickSettings", sbM.toString());
                    }
                    ((OplusQsBaseToggleSliderLayout) this.this$0.mView).setSliderEnabled(this.$isUIModeChange);
                    ((OplusQsBaseToggleSliderLayout) this.this$0.mView).setToggleViewEnabled(fFloatValue, this.$isUIModeChange);
                    OplusQsVolumeIconView oplusQsVolumeIconView2 = this.this$0.volumeIconView;
                    if (oplusQsVolumeIconView2 != null) {
                        oplusQsVolumeIconView2.updateIconState(this.$state);
                    }
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: $r8$lambda$6HxUEDzKTzoVnlpsuClFnm51W-I, reason: not valid java name */
    public static Unit m253$r8$lambda$6HxUEDzKTzoVnlpsuClFnm51WI(OplusQsVolumeSliderController oplusQsVolumeSliderController) {
        boolean z = !((Boolean) oplusQsVolumeSliderController.musicStreamZenMuted.get()).booleanValue();
        QSVolume.VolumeState volumeStateCopy$default = QSVolume.VolumeState.copy$default((QSVolume.VolumeState) oplusQsVolumeSliderController.musicVolumeState.get(), 0, 0, 0, 0, 0, false, 63, (Object) null);
        volumeStateCopy$default.setDistanceFromBottom(volumeStateCopy$default.getDistanceFromBottom());
        volumeStateCopy$default.setZenMuted(true);
        Handler handler = oplusQsVolumeSliderController.mainHandler;
        RunnableC02531 runnableC02531 = new RunnableC02531(1);
        runnableC02531.this$0 = oplusQsVolumeSliderController;
        runnableC02531.$state = volumeStateCopy$default;
        runnableC02531.$isUIModeChange = z;
        VarHandle.storeStoreFence();
        handler.post(runnableC02531);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$72-oGk9fMjNLp7RbvFdvKuiFLcc, reason: not valid java name */
    public static Unit m254$r8$lambda$72oGk9fMjNLp7RbvFdvKuiFLcc(OplusQsVolumeSliderController oplusQsVolumeSliderController) {
        if (!((Boolean) oplusQsVolumeSliderController.isQsListening.get()).booleanValue()) {
            oplusQsVolumeSliderController.isQSListeningCache = false;
            return Unit.INSTANCE;
        }
        QSVolume.VolumeState volumeState = (QSVolume.VolumeState) oplusQsVolumeSliderController.volumeController.getMusicVolumeState().get();
        oplusQsVolumeSliderController.musicMinVolumeLevel.set(Integer.valueOf(volumeState.getMinLevel()));
        oplusQsVolumeSliderController.musicMaxVolumeLevel.set(Integer.valueOf(volumeState.getMaxLevel()));
        oplusQsVolumeSliderController.musicVolumeLevel.set(Integer.valueOf(volumeState.getLevel()));
        oplusQsVolumeSliderController.isQSListeningCache = true;
        oplusQsVolumeSliderController.musicVolumeState.set(volumeState);
        Companion companion = Companion;
        int iIntValue = ((Number) oplusQsVolumeSliderController.musicVolumeLevel.get()).intValue();
        companion.getClass();
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) oplusQsVolumeSliderController.mView;
        OplusQsBaseToggleSliderLayout.Companion companion2 = OplusQsBaseToggleSliderLayout.Companion;
        int distanceFromBottom = oplusQsBaseToggleSliderLayout.getDistanceFromBottom(iIntValue * 100, -1);
        if (oplusQsVolumeSliderController.distanceFromBottom != distanceFromBottom && ((Number) oplusQsVolumeSliderController.seekBarVolumeLevel.get()).intValue() != -1) {
            oplusQsVolumeSliderController.distanceFromBottom = distanceFromBottom;
            oplusQsVolumeSliderController.updateVolumeIconState((QSVolume.VolumeState) oplusQsVolumeSliderController.musicVolumeState.get(), false);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$CTQDzgRn8uvxJ7CNgiv9wk-YXzk, reason: not valid java name */
    public static Unit m255$r8$lambda$CTQDzgRn8uvxJ7CNgiv9wkYXzk(OplusQsVolumeSliderController oplusQsVolumeSliderController) {
        boolean zBooleanValue = ((Boolean) oplusQsVolumeSliderController.volumeController.isAllowedProgressUpdate().get()).booleanValue();
        QSVolume.VolumeState volumeState = (QSVolume.VolumeState) oplusQsVolumeSliderController.musicVolumeState.get();
        int iIntValue = ((Number) oplusQsVolumeSliderController.seekBarVolumeLevel.get()).intValue();
        LogUtil.i$default(LogUtil.TAG_QS, oplusQsVolumeSliderController.tag, "volumeIconProgressLevelUpdater: isAllowUpdate=" + zBooleanValue + ", musicVolumeState=" + volumeState + ", seekBarVolumeLevel=" + iIntValue, null, 8);
        if (iIntValue == volumeState.getLevel() || zBooleanValue) {
            oplusQsVolumeSliderController.updateVolumeIconState((QSVolume.VolumeState) oplusQsVolumeSliderController.musicVolumeState.get(), false);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$Fb-ouehsUVSuWb7J5O-O9Yrlfb4, reason: not valid java name */
    public static Unit m256$r8$lambda$FbouehsUVSuWb7J5OO9Yrlfb4(OplusQsVolumeSliderController oplusQsVolumeSliderController) {
        QSVolume qSVolume = oplusQsVolumeSliderController.volumeController;
        int i = oplusQsVolumeSliderController.initialVolumeSliderProgress;
        boolean zBooleanValue = ((Boolean) qSVolume.isAllowedProgressUpdate().get()).booleanValue();
        int iIntValue = ((Number) oplusQsVolumeSliderController.musicVolumeLevel.get()).intValue();
        Companion companion = Companion;
        int iIntValue2 = ((Number) oplusQsVolumeSliderController.musicMaxVolumeLevel.get()).intValue();
        companion.getClass();
        int iConvertProgressToVolumeLevel = Companion.convertProgressToVolumeLevel(iIntValue2 * 100, oplusQsVolumeSliderController.getSeekBarVolumeProgress());
        boolean z = iIntValue >= 0 && (!oplusQsVolumeSliderController.progressUpdated || (zBooleanValue && iIntValue != iConvertProgressToVolumeLevel));
        String str = oplusQsVolumeSliderController.tag;
        int i2 = oplusQsVolumeSliderController.initialSeekBarVolumeLevel;
        boolean z2 = oplusQsVolumeSliderController.progressUpdated;
        boolean z3 = oplusQsVolumeSliderController.isQSListeningCache;
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("updateVolumeSeekBarProgress: isNeedUpdate=", ", isAllowUpdate=", ", volumeLevel=", z, zBooleanValue);
        RectList$.ExternalSyntheticOutline0.m(iIntValue, iConvertProgressToVolumeLevel, ", seekBarLevel=", ", initialVolumeSliderProgress=", sbM);
        RectList$.ExternalSyntheticOutline0.m(i, i2, ", initialSeekBarVolumeLevel=", ", progressUpdated=", sbM);
        LogUtil.i$default(LogUtil.TAG_QS, str, KeyguardLogger$.ExternalSyntheticOutline0.m(sbM, z2, ", isQSListeningCache=", z3), null, 8);
        if (!z) {
            oplusQsVolumeSliderController.distanceFromBottom = ((OplusQsBaseToggleSliderLayout) oplusQsVolumeSliderController.mView).getDistanceFromBottom(iIntValue * 100, ((Number) oplusQsVolumeSliderController.musicMaxVolumeLevel.get()).intValue() * 100);
        } else if (oplusQsVolumeSliderController.progressUpdated || Companion.convertProgressToVolumeLevel(((Number) oplusQsVolumeSliderController.musicMaxVolumeLevel.get()).intValue() * 100, i) != iIntValue || oplusQsVolumeSliderController.isQSListeningCache) {
            oplusQsVolumeSliderController.handleUpdateVolumeSeekBarProgress(iIntValue * 100, oplusQsVolumeSliderController.progressUpdated && oplusQsVolumeSliderController.isQSListeningCache);
        } else {
            oplusQsVolumeSliderController.handleUpdateVolumeSeekBarProgress(i, false);
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$M7DsjjOkrO8kvUSPjjJuNdlFzpA(OplusQsVolumeSliderController oplusQsVolumeSliderController) {
        if (!((Boolean) oplusQsVolumeSliderController.isQsListening.get()).booleanValue()) {
            return Unit.INSTANCE;
        }
        oplusQsVolumeSliderController.a11yStreamShow.set(oplusQsVolumeSliderController.volumeController.getA11yStreamShow().get());
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$XjOuZ2VCgAYSrZYERug7YY3UUzA(OplusQsVolumeSliderController oplusQsVolumeSliderController) {
        String str = (String) oplusQsVolumeSliderController.volumeIconContentDescription.get();
        if (oplusQsVolumeSliderController.isEditMode) {
            oplusQsVolumeSliderController.iconContentDescription = str;
        } else {
            Handler handler = oplusQsVolumeSliderController.mainHandler;
            OplusQsVolumeSliderController$updateVolumeIconContentDescription$1 oplusQsVolumeSliderController$updateVolumeIconContentDescription$1 = new OplusQsVolumeSliderController$updateVolumeIconContentDescription$1(0);
            oplusQsVolumeSliderController$updateVolumeIconContentDescription$1.this$0 = oplusQsVolumeSliderController;
            oplusQsVolumeSliderController$updateVolumeIconContentDescription$1.$contentDescription = str;
            VarHandle.storeStoreFence();
            handler.post(oplusQsVolumeSliderController$updateVolumeIconContentDescription$1);
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$a0Q4JHBBbqbVSGuma1QJQgSOsww(OplusQsVolumeSliderController oplusQsVolumeSliderController) {
        LogUtil.i$default(LogUtil.TAG_QS, oplusQsVolumeSliderController.tag, ListPopupWindow$.ExternalSyntheticOutline0.m(((Number) oplusQsVolumeSliderController.seekBarVolumeLevel.get()).intValue(), "onSeekBarVolumeLevelChanged: seekBarVolumeLevel="), null, 8);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$aIeWhn9NIAc6lipHiut_SpJYCUU(OplusQsVolumeSliderController oplusQsVolumeSliderController) {
        int iIntValue = ((Number) oplusQsVolumeSliderController.volumeController.getVolumeUnchange().get()).intValue();
        if (iIntValue >= 0 && ((Boolean) oplusQsVolumeSliderController.isQsListening.get()).booleanValue()) {
            OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = oplusQsVolumeSliderController.layout;
            if (oplusQsBaseToggleSliderLayout != null ? oplusQsBaseToggleSliderLayout.isShown() : false) {
                OplusQsSliderTouchAnimationDelegate touchAnimationDelegate = oplusQsVolumeSliderController.layout.getTouchAnimationDelegate();
                if (touchAnimationDelegate != null) {
                    OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout2 = oplusQsVolumeSliderController.layout;
                    volumeUnchangeUpdate.1.1.1 r3 = new volumeUnchangeUpdate.1.1.1();
                    r3.this$0 = oplusQsVolumeSliderController;
                    r3.$it = touchAnimationDelegate;
                    r3.$flags = iIntValue;
                    VarHandle.storeStoreFence();
                    oplusQsBaseToggleSliderLayout2.post(r3);
                }
                return Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$cSdjCv6LVRiWcBap56UxQg8PBZ0(OplusQsVolumeSliderController oplusQsVolumeSliderController) {
        if (!((Boolean) oplusQsVolumeSliderController.isQsListening.get()).booleanValue()) {
            return Unit.INSTANCE;
        }
        oplusQsVolumeSliderController.musicStreamZenMuted.set(oplusQsVolumeSliderController.volumeController.getMusicStreamZenMuted().get());
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$ciXLnT8sHzvla-I9ksTAD_OmoS8, reason: not valid java name */
    public static String m257$r8$lambda$ciXLnT8sHzvlaI9ksTAD_OmoS8(OplusQsVolumeSliderController oplusQsVolumeSliderController) {
        String str = (String) oplusQsVolumeSliderController.musicStreamLabel.get();
        boolean zBooleanValue = ((Boolean) oplusQsVolumeSliderController.musicStreamZenMuted.get()).booleanValue();
        int iIntValue = ((Number) oplusQsVolumeSliderController.musicVolumeLevel.get()).intValue();
        boolean zBooleanValue2 = ((Boolean) oplusQsVolumeSliderController.a11yStreamShow.get()).booleanValue();
        if (zBooleanValue) {
            return str;
        }
        if (iIntValue == 0) {
            return oplusQsVolumeSliderController.getContext().getString(2131957222, str);
        }
        return zBooleanValue2 ? oplusQsVolumeSliderController.getContext().getString(2131957221, str) : oplusQsVolumeSliderController.getContext().getString(2131957220, str);
    }

    public static Unit $r8$lambda$hJwv7A_fvAxQ9LdwdXH6vqo2oqM(OplusQsVolumeSliderController oplusQsVolumeSliderController) {
        if (!((Boolean) oplusQsVolumeSliderController.isQsListening.get()).booleanValue()) {
            return Unit.INSTANCE;
        }
        oplusQsVolumeSliderController.musicStreamLabel.set(oplusQsVolumeSliderController.volumeController.getMusicStreamLabel().get());
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$jCvILZOPEvd_K_LfotdFCvRUNx0(OplusQsVolumeSliderController oplusQsVolumeSliderController, boolean z) {
        Companion companion = Companion;
        int iIntValue = ((Number) oplusQsVolumeSliderController.musicMinVolumeLevel.get()).intValue();
        companion.getClass();
        int i = iIntValue * 100;
        int min = ((OplusQsBaseToggleSliderLayout) oplusQsVolumeSliderController.mView).getMin();
        int i2 = 1;
        boolean z2 = z || i != min;
        String str = oplusQsVolumeSliderController.tag;
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("updateVolumeSeekBarMinProgress: isNeedUpdate=", i, ", minVolumeProgress=", ", seekBarMinProgress=", z2);
        sbM.append(min);
        sbM.append(", isInit=");
        sbM.append(z);
        LogUtil.i$default(LogUtil.TAG_QS, str, sbM.toString(), null, 8);
        if (z2) {
            Handler handler = oplusQsVolumeSliderController.mainHandler;
            OplusQsVolumeSliderController$updateVolumeSeekBarMaxProgress$1 oplusQsVolumeSliderController$updateVolumeSeekBarMaxProgress$1 = new OplusQsVolumeSliderController$updateVolumeSeekBarMaxProgress$1(i2);
            oplusQsVolumeSliderController$updateVolumeSeekBarMaxProgress$1.this$0 = oplusQsVolumeSliderController;
            oplusQsVolumeSliderController$updateVolumeSeekBarMaxProgress$1.$maxProgress = i;
            VarHandle.storeStoreFence();
            handler.post(oplusQsVolumeSliderController$updateVolumeSeekBarMaxProgress$1);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: $r8$lambda$jU7Qgi_eb_ETEQBI_fOplS5C-cg, reason: not valid java name */
    public static Unit m258$r8$lambda$jU7Qgi_eb_ETEQBI_fOplS5Ccg(OplusQsVolumeSliderController oplusQsVolumeSliderController, boolean z) {
        String str = oplusQsVolumeSliderController.tag;
        int iIntValue = ((Number) oplusQsVolumeSliderController.musicMaxVolumeLevel.get()).intValue();
        Companion.getClass();
        int i = iIntValue * 100;
        int max = ((OplusQsBaseToggleSliderLayout) oplusQsVolumeSliderController.mView).getMax();
        int i2 = 0;
        boolean z2 = true;
        if (!z && i == max) {
            z2 = false;
        }
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("updateVolumeSeekBarMaxProgress: isNeedUpdate=", i, ", maxVolumeProgress=", ", seekBarMaxProgress=", z2);
        sbM.append(max);
        sbM.append(", isInit=");
        sbM.append(z);
        LogUtil.i$default(LogUtil.TAG_QS, str, sbM.toString(), null, 8);
        if (z2) {
            Handler handler = oplusQsVolumeSliderController.mainHandler;
            OplusQsVolumeSliderController$updateVolumeSeekBarMaxProgress$1 oplusQsVolumeSliderController$updateVolumeSeekBarMaxProgress$1 = new OplusQsVolumeSliderController$updateVolumeSeekBarMaxProgress$1(i2);
            oplusQsVolumeSliderController$updateVolumeSeekBarMaxProgress$1.this$0 = oplusQsVolumeSliderController;
            oplusQsVolumeSliderController$updateVolumeSeekBarMaxProgress$1.$maxProgress = i;
            VarHandle.storeStoreFence();
            handler.post(oplusQsVolumeSliderController$updateVolumeSeekBarMaxProgress$1);
            int distanceFromBottom = ((OplusQsBaseToggleSliderLayout) oplusQsVolumeSliderController.mView).getDistanceFromBottom(oplusQsVolumeSliderController.asyncVolumeProgress, i);
            oplusQsVolumeSliderController.distanceFromBottom = distanceFromBottom;
            Object obj = oplusQsVolumeSliderController.musicVolumeLevel.get();
            StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("updateVolumeSeekBarMaxProgress: distanceFromBottom=", distanceFromBottom, i, " asyncVolumeProgress=", " seekBarVolumeLevel=");
            sbM2.append(obj);
            LogUtil.i$default(LogUtil.TAG_QS, str, sbM2.toString(), null, 8);
            oplusQsVolumeSliderController.updateVolumeIconState((QSVolume.VolumeState) oplusQsVolumeSliderController.musicVolumeState.get(), false);
        }
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$pAgHISelkoe1TJ_xh3luLKmE3Ss(OplusQsVolumeSliderController oplusQsVolumeSliderController) {
        String str = (String) oplusQsVolumeSliderController.musicStreamLabel.get();
        if (oplusQsVolumeSliderController.isEditMode) {
            oplusQsVolumeSliderController.sliderContentDescription = str;
        } else {
            Handler handler = oplusQsVolumeSliderController.mainHandler;
            OplusQsVolumeSliderController$updateVolumeIconContentDescription$1 oplusQsVolumeSliderController$updateVolumeIconContentDescription$1 = new OplusQsVolumeSliderController$updateVolumeIconContentDescription$1(1);
            oplusQsVolumeSliderController$updateVolumeIconContentDescription$1.this$0 = oplusQsVolumeSliderController;
            oplusQsVolumeSliderController$updateVolumeIconContentDescription$1.$contentDescription = str;
            VarHandle.storeStoreFence();
            handler.post(oplusQsVolumeSliderController$updateVolumeIconContentDescription$1);
        }
        return Unit.INSTANCE;
    }

    public OplusQsVolumeSliderController(OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout, QSVolume qSVolume, Handler handler, Handler handler2, QsUserContextProvider qsUserContextProvider) {
        super(oplusQsBaseToggleSliderLayout);
        this.layout = oplusQsBaseToggleSliderLayout;
        this.volumeController = qSVolume;
        this.mainHandler = handler;
        this.bgHandler = handler2;
        this.userContextProvider = qsUserContextProvider;
        int i = 0;
        int i2 = PrefUtil.getInt(qsUserContextProvider.getCurrentUserContext(), PREF_KEY_LAST_SLIDER_PROGRESS, 0);
        this.initialVolumeSliderProgress = i2;
        int i3 = PrefUtil.getInt(qsUserContextProvider.getCurrentUserContext(), PREF_KEY_LAST_SEEKBAR_VOLUME_LEVEL, -1);
        this.initialSeekBarVolumeLevel = i3;
        Object toggleView = oplusQsBaseToggleSliderLayout.getToggleView();
        this.volumeIconView = toggleView instanceof OplusQsVolumeIconView ? (OplusQsVolumeIconView) toggleView : null;
        this.isQsListening = new NonNullObservableField<>(Boolean.FALSE, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.tag = getClass().getSimpleName();
        ObservableField[] observableFieldArr = {qSVolume.getRingModeChanged()};
        OplusQsVolumeSliderController$$ExternalSyntheticLambda0 oplusQsVolumeSliderController$$ExternalSyntheticLambda0 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda0(i);
        oplusQsVolumeSliderController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.ringModeUpdate = new NonNullObservableField<>(observableFieldArr, false, oplusQsVolumeSliderController$$ExternalSyntheticLambda0, 2, (DefaultConstructorMarker) null);
        ObservableField[] observableFieldArr2 = {qSVolume.getMusicVolumeState(), this.isQsListening};
        OplusQsVolumeSliderController$$ExternalSyntheticLambda0 oplusQsVolumeSliderController$$ExternalSyntheticLambda1 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda0(13);
        oplusQsVolumeSliderController$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.musicVolumeStateUpdate = new NonNullObservableField<>(observableFieldArr2, false, oplusQsVolumeSliderController$$ExternalSyntheticLambda1, 2, (DefaultConstructorMarker) null);
        ObservableField[] observableFieldArr3 = {qSVolume.getMusicStreamLabel(), this.isQsListening};
        OplusQsVolumeSliderController$$ExternalSyntheticLambda0 oplusQsVolumeSliderController$$ExternalSyntheticLambda2 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda0(14);
        oplusQsVolumeSliderController$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.musicStreamLabelUpdate = new NonNullObservableField<>(observableFieldArr3, false, oplusQsVolumeSliderController$$ExternalSyntheticLambda2, 2, (DefaultConstructorMarker) null);
        ObservableField[] observableFieldArr4 = {qSVolume.getA11yStreamShow(), this.isQsListening};
        OplusQsVolumeSliderController$$ExternalSyntheticLambda0 oplusQsVolumeSliderController$$ExternalSyntheticLambda3 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda0(15);
        oplusQsVolumeSliderController$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.a11yStreamShowUpdate = new NonNullObservableField<>(observableFieldArr4, false, oplusQsVolumeSliderController$$ExternalSyntheticLambda3, 2, (DefaultConstructorMarker) null);
        ObservableField[] observableFieldArr5 = {qSVolume.getMusicStreamZenMuted(), this.isQsListening};
        int i4 = 1;
        OplusQsVolumeSliderController$$ExternalSyntheticLambda0 oplusQsVolumeSliderController$$ExternalSyntheticLambda4 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda0(i4);
        oplusQsVolumeSliderController$$ExternalSyntheticLambda4.f$0 = this;
        VarHandle.storeStoreFence();
        this.musicStreamZenMutedUpdate = new NonNullObservableField<>(observableFieldArr5, false, oplusQsVolumeSliderController$$ExternalSyntheticLambda4, 2, (DefaultConstructorMarker) null);
        ObservableField[] observableFieldArr6 = {qSVolume.getVolumeUnchange()};
        OplusQsVolumeSliderController$$ExternalSyntheticLambda0 oplusQsVolumeSliderController$$ExternalSyntheticLambda5 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda0(2);
        oplusQsVolumeSliderController$$ExternalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        this.volumeUnchangeUpdate = new NonNullObservableField<>(observableFieldArr6, false, oplusQsVolumeSliderController$$ExternalSyntheticLambda5, 2, (DefaultConstructorMarker) null);
        ObservableField[] observableFieldArr7 = {this.isQsListening};
        OplusQsVolumeSliderController$$ExternalSyntheticLambda0 oplusQsVolumeSliderController$$ExternalSyntheticLambda6 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda0(3);
        oplusQsVolumeSliderController$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        this.disableShowVolumeDialogUpdate = new NonNullObservableField<>(observableFieldArr7, false, oplusQsVolumeSliderController$$ExternalSyntheticLambda6, 2, (DefaultConstructorMarker) null);
        NonNullObservableField<Integer> nonNullObservableField = new NonNullObservableField<>(Integer.valueOf(((QSVolume.VolumeState) qSVolume.getMusicVolumeState().get()).getLevel()), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.musicVolumeLevel = nonNullObservableField;
        NonNullObservableField<Integer> nonNullObservableField2 = new NonNullObservableField<>(Integer.valueOf(((QSVolume.VolumeState) qSVolume.getMusicVolumeState().get()).getMinLevel()), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.musicMinVolumeLevel = nonNullObservableField2;
        NonNullObservableField<Integer> nonNullObservableField3 = new NonNullObservableField<>(Integer.valueOf(((QSVolume.VolumeState) qSVolume.getMusicVolumeState().get()).getMaxLevel()), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.musicMaxVolumeLevel = nonNullObservableField3;
        NonNullObservableField<String> nonNullObservableField4 = new NonNullObservableField<>(qSVolume.getMusicStreamLabel().get(), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.musicStreamLabel = nonNullObservableField4;
        NonNullObservableField<Boolean> nonNullObservableField5 = new NonNullObservableField<>(qSVolume.getMusicStreamZenMuted().get(), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.musicStreamZenMuted = nonNullObservableField5;
        NonNullObservableField<Boolean> nonNullObservableField6 = new NonNullObservableField<>(qSVolume.getA11yStreamShow().get(), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.a11yStreamShow = nonNullObservableField6;
        NonNullObservableField<QSVolume.VolumeState> nonNullObservableField7 = new NonNullObservableField<>(qSVolume.getMusicVolumeState().get(), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.musicVolumeState = nonNullObservableField7;
        NonNullObservableField<Integer> nonNullObservableField8 = new NonNullObservableField<>(Integer.valueOf(i3), (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
        this.seekBarVolumeLevel = nonNullObservableField8;
        OplusQsVolumeSliderController$$ExternalSyntheticLambda0 oplusQsVolumeSliderController$$ExternalSyntheticLambda7 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda0(4);
        oplusQsVolumeSliderController$$ExternalSyntheticLambda7.f$0 = this;
        VarHandle.storeStoreFence();
        this.seekBarVolumeLevelOnChanged = new NonNullObservableField<>(new ObservableField[]{nonNullObservableField8}, false, oplusQsVolumeSliderController$$ExternalSyntheticLambda7, 2, (DefaultConstructorMarker) null);
        OplusQsVolumeSliderController$$ExternalSyntheticLambda0 oplusQsVolumeSliderController$$ExternalSyntheticLambda8 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda0(5);
        oplusQsVolumeSliderController$$ExternalSyntheticLambda8.f$0 = this;
        VarHandle.storeStoreFence();
        this.viewEnableUpdater = new NullableObservableField<>(new ObservableField[]{nonNullObservableField5}, false, oplusQsVolumeSliderController$$ExternalSyntheticLambda8, 2, (DefaultConstructorMarker) null);
        OplusQsVolumeSliderController$$ExternalSyntheticLambda0 oplusQsVolumeSliderController$$ExternalSyntheticLambda9 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda0(6);
        oplusQsVolumeSliderController$$ExternalSyntheticLambda9.f$0 = this;
        VarHandle.storeStoreFence();
        this.volumeSeekBarMinProgressUpdater = new NullableObservableField<>(new ObservableField[]{nonNullObservableField2}, false, oplusQsVolumeSliderController$$ExternalSyntheticLambda9, 2, (DefaultConstructorMarker) null);
        OplusQsVolumeSliderController$$ExternalSyntheticLambda0 oplusQsVolumeSliderController$$ExternalSyntheticLambda10 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda0(7);
        oplusQsVolumeSliderController$$ExternalSyntheticLambda10.f$0 = this;
        VarHandle.storeStoreFence();
        this.volumeSeekBarMaxProgressUpdater = new NullableObservableField<>(new ObservableField[]{nonNullObservableField3}, false, oplusQsVolumeSliderController$$ExternalSyntheticLambda10, 2, (DefaultConstructorMarker) null);
        OplusQsVolumeSliderController$mTouchListener$1 oplusQsVolumeSliderController$mTouchListener$1 = new OplusQsVolumeSliderController$mTouchListener$1(i4);
        oplusQsVolumeSliderController$mTouchListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.sliderTouchListener = oplusQsVolumeSliderController$mTouchListener$1;
        ObservableField[] observableFieldArr8 = {nonNullObservableField, qSVolume.isAllowedProgressUpdate()};
        OplusQsVolumeSliderController$$ExternalSyntheticLambda0 oplusQsVolumeSliderController$$ExternalSyntheticLambda11 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda0(8);
        oplusQsVolumeSliderController$$ExternalSyntheticLambda11.f$0 = this;
        VarHandle.storeStoreFence();
        this.volumeSeekBarProgressUpdater = new NullableObservableField<>(observableFieldArr8, false, oplusQsVolumeSliderController$$ExternalSyntheticLambda11, 2, (DefaultConstructorMarker) null);
        OplusQsVolumeSliderController$$ExternalSyntheticLambda0 oplusQsVolumeSliderController$$ExternalSyntheticLambda12 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda0(9);
        oplusQsVolumeSliderController$$ExternalSyntheticLambda12.f$0 = this;
        VarHandle.storeStoreFence();
        NonNullObservableField<String> nonNullObservableField9 = new NonNullObservableField<>(new ObservableField[]{nonNullObservableField, nonNullObservableField4, nonNullObservableField5, nonNullObservableField6}, false, oplusQsVolumeSliderController$$ExternalSyntheticLambda12, 2, (DefaultConstructorMarker) null);
        this.volumeIconContentDescription = nonNullObservableField9;
        OplusQsVolumeSliderController$$ExternalSyntheticLambda0 oplusQsVolumeSliderController$$ExternalSyntheticLambda13 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda0(10);
        oplusQsVolumeSliderController$$ExternalSyntheticLambda13.f$0 = this;
        VarHandle.storeStoreFence();
        this.volumeIconContentDescriptionUpdater = new NullableObservableField<>(new ObservableField[]{nonNullObservableField9}, false, oplusQsVolumeSliderController$$ExternalSyntheticLambda13, 2, (DefaultConstructorMarker) null);
        OplusQsVolumeSliderController$$ExternalSyntheticLambda0 oplusQsVolumeSliderController$$ExternalSyntheticLambda14 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda0(11);
        oplusQsVolumeSliderController$$ExternalSyntheticLambda14.f$0 = this;
        VarHandle.storeStoreFence();
        this.volumeSliderContentDescriptionUpdater = new NullableObservableField<>(new ObservableField[]{nonNullObservableField4}, false, oplusQsVolumeSliderController$$ExternalSyntheticLambda14, 2, (DefaultConstructorMarker) null);
        ObservableField[] observableFieldArr9 = {nonNullObservableField7, qSVolume.isAllowedProgressUpdate()};
        OplusQsVolumeSliderController$$ExternalSyntheticLambda0 oplusQsVolumeSliderController$$ExternalSyntheticLambda15 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda0(12);
        oplusQsVolumeSliderController$$ExternalSyntheticLambda15.f$0 = this;
        VarHandle.storeStoreFence();
        this.volumeIconProgressLevelUpdater = new NullableObservableField<>(observableFieldArr9, false, oplusQsVolumeSliderController$$ExternalSyntheticLambda15, 2, (DefaultConstructorMarker) null);
        this.asyncVolumeProgress = i2;
        OplusQsVolumeSliderController$layoutChangedListener$1 oplusQsVolumeSliderController$layoutChangedListener$1 = new OplusQsVolumeSliderController$layoutChangedListener$1();
        oplusQsVolumeSliderController$layoutChangedListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.layoutChangedListener = oplusQsVolumeSliderController$layoutChangedListener$1;
        OplusQsVolumeSliderController$volumeIconClickListener$1 oplusQsVolumeSliderController$volumeIconClickListener$1 = new OplusQsVolumeSliderController$volumeIconClickListener$1();
        oplusQsVolumeSliderController$volumeIconClickListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.volumeIconClickListener = oplusQsVolumeSliderController$volumeIconClickListener$1;
        OplusQsVolumeSliderController$mTouchListener$1 oplusQsVolumeSliderController$mTouchListener$2 = new OplusQsVolumeSliderController$mTouchListener$1(i);
        oplusQsVolumeSliderController$mTouchListener$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mTouchListener = oplusQsVolumeSliderController$mTouchListener$2;
    }

    private final int getSeekBarVolumeProgress() {
        return this.mainHandler.hasMessages(1001) ? this.asyncVolumeProgress : ((OplusQsBaseToggleSliderLayout) this.mView).getProgress();
    }

    private final void setDisAllowProgressUpdate(long j) {
        this.volumeController.setDisAllowProgressUpdate(j);
    }

    public static /* synthetic */ void updateSeekBarColor$default(OplusQsVolumeSliderController oplusQsVolumeSliderController, boolean z, boolean z2, QSSpecialModeUpdateMonitor.ColorType colorType, int i) {
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            colorType = null;
        }
        oplusQsVolumeSliderController.updateSeekBarColor(z, z2, colorType);
    }

    public final View getExpandableView() {
        return this.mView;
    }

    public final String getTag() {
        return this.tag;
    }

    public final QSVolume getVolumeController() {
        return this.volumeController;
    }

    public final NonNullObservableField<Unit> getVolumeUnchangeUpdate() {
        return this.volumeUnchangeUpdate;
    }

    public void handleRingModeUpdate() {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(anonymousClass1);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(this.tag, "-->ringModeUpdate: setAllowProgressUpdate", "SystemUi--QuickSettings");
        }
        setAllowProgressUpdate(0L);
    }

    public void handleUpdateVolumeSeekBarProgress(int i, boolean z) {
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) this.mView;
        Companion companion = Companion;
        int iIntValue = ((Number) this.musicMaxVolumeLevel.get()).intValue();
        companion.getClass();
        int distanceFromBottom = oplusQsBaseToggleSliderLayout.getDistanceFromBottom(i, iIntValue * 100);
        this.distanceFromBottom = distanceFromBottom;
        Object obj = this.seekBarVolumeLevel.get();
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("handleUpdateVolumeSeekBarProgress: distanceFromBottom=", distanceFromBottom, i, " progress:", " seekBarVolumeLevel=");
        sbM.append(obj);
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, sbM.toString(), null, 8);
        this.progressUpdated = true;
        this.asyncVolumeProgress = i;
        Handler handler = this.mainHandler;
        RunnableC02521 runnableC02521 = new RunnableC02521();
        runnableC02521.this$0 = this;
        runnableC02521.$progress = i;
        runnableC02521.$performAnim = z;
        VarHandle.storeStoreFence();
        handler.postDelayed(runnableC02521, 1001, 0L);
    }

    public boolean isNeedtouchFeedback() {
        return ((Boolean) this.isQsListening.get()).booleanValue();
    }

    public final void onHeightDeformedChanged(float f, float f2) {
        if (f == this.topDeformed || f2 == this.bottomDeformed) {
            return;
        }
        this.topDeformed = f;
        this.bottomDeformed = f2;
        setIconTranslationY(Math.abs(f) > Math.abs(this.bottomDeformed) ? f - f2 : f2 - f);
    }

    public void onProgressChanged(COUIVerticalSeekBar cOUIVerticalSeekBar, int i, boolean z) {
        if (this.mLastProgress == i) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                BaseAodClockLayoutController$bindSceneViewHolder$2$.ExternalSyntheticOutline0.m(i, "onProgressChanged progress not changed: ", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(this.tag, LogUtil.SEPARATOR));
            }
            this.isProgressChanged = false;
            return;
        }
        Companion companion = Companion;
        int iIntValue = ((Number) this.musicVolumeLevel.get()).intValue();
        companion.getClass();
        int i2 = iIntValue * 100;
        int iConvertProgressToVolumeLevel = Companion.convertProgressToVolumeLevel(cOUIVerticalSeekBar.getMax(), i);
        int iIntValue2 = ((Number) this.seekBarVolumeLevel.get()).intValue();
        this.seekBarVolumeLevel.set(Integer.valueOf(iConvertProgressToVolumeLevel));
        this.mLastProgress = i;
        this.isProgressChanged = true;
        boolean zBooleanValue = ((Boolean) this.volumeController.isAllowedProgressUpdate().get()).booleanValue();
        View view = this.mView;
        if (zBooleanValue) {
            OplusQsBaseToggleSliderLayout.Companion companion2 = OplusQsBaseToggleSliderLayout.Companion;
            this.distanceFromBottom = ((OplusQsBaseToggleSliderLayout) view).getDistanceFromBottom(i2, -1);
        } else {
            OplusQsBaseToggleSliderLayout.Companion companion3 = OplusQsBaseToggleSliderLayout.Companion;
            this.distanceFromBottom = ((OplusQsBaseToggleSliderLayout) view).getDistanceFromBottom(i, -1);
        }
        if (z && iIntValue2 != iConvertProgressToVolumeLevel) {
            this.volumeController.setMusicVolumeLevel(iConvertProgressToVolumeLevel);
        }
        if (iIntValue2 != iConvertProgressToVolumeLevel) {
            updateVolumeIconState((QSVolume.VolumeState) this.musicVolumeState.get(), false);
        }
    }

    public void onStartTrackingTouch(COUIVerticalSeekBar cOUIVerticalSeekBar) {
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, "onStartTrackingTouch:", null, 8);
        setDisAllowProgressUpdate(0L);
    }

    public void onStopTrackingTouch(COUIVerticalSeekBar cOUIVerticalSeekBar) {
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, "onStopTrackingTouch:", null, 8);
        SeparateQSStatisicUtil.collectQsPanelActionClick(getContext(), SeparateQSStatisicUtil.CLICK_QS_PANEL_TYPE_VOLUME, "move");
        onProgressChanged(cOUIVerticalSeekBar, cOUIVerticalSeekBar.getProgress(), true);
        setAllowProgressUpdate(1000L);
        this.volumeController.forceVolumeControlStream();
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewAttached() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(this.tag, LogUtil.SEPARATOR);
            sbM.append("onViewAttached isQsListening=" + this.isQsListening.get());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        ((OplusQsBaseToggleSliderLayout) this.mView).addOnSeekBarChangeListener(this);
        ((OplusQsBaseToggleSliderLayout) this.mView).setDeformedListener(this);
        ((OplusQsBaseToggleSliderLayout) this.mView).setOnToggleClickListener(this.volumeIconClickListener);
        ((OplusQsBaseToggleSliderLayout) this.mView).setOnTouchListener(this.mTouchListener);
        ((OplusQsBaseToggleSliderLayout) this.mView).setSliderTouchListener(this.sliderTouchListener);
        ((OplusQsBaseToggleSliderLayout) this.mView).addOnLayoutChangeListener(this.layoutChangedListener);
        this.musicVolumeLevel.attach();
        this.musicMaxVolumeLevel.attach();
        this.musicMinVolumeLevel.attach();
        this.musicStreamLabel.attach();
        this.musicStreamZenMuted.attach();
        this.a11yStreamShow.attach();
        this.musicVolumeState.attach();
        this.ringModeUpdate.attach();
        this.musicVolumeStateUpdate.attach();
        this.musicStreamLabelUpdate.attach();
        this.musicStreamZenMutedUpdate.attach();
        this.volumeUnchangeUpdate.attach();
        this.a11yStreamShowUpdate.attach();
        this.disableShowVolumeDialogUpdate.attach();
        this.volumeSeekBarProgressUpdater.attach();
        this.volumeSeekBarMaxProgressUpdater.attach();
        this.volumeSeekBarMinProgressUpdater.attach();
        this.seekBarVolumeLevelOnChanged.attach();
        this.viewEnableUpdater.attach();
        this.volumeIconContentDescriptionUpdater.attach();
        this.volumeSliderContentDescriptionUpdater.attach();
        this.volumeIconProgressLevelUpdater.attach();
        updateDisableShowVolumeDialog();
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = (OplusQsBaseToggleSliderLayout) this.mView;
        OplusQsVolumeSliderController$$ExternalSyntheticLambda16 oplusQsVolumeSliderController$$ExternalSyntheticLambda16 = new OplusQsVolumeSliderController$$ExternalSyntheticLambda16();
        oplusQsVolumeSliderController$$ExternalSyntheticLambda16.f$0 = this;
        VarHandle.storeStoreFence();
        oplusQsBaseToggleSliderLayout.setOnTouchAnimationListener(oplusQsVolumeSliderController$$ExternalSyntheticLambda16);
    }

    @Override // com.oplus.systemui.qs.base.util.ViewController
    public final void onViewDetached() {
        String str = this.tag;
        LogUtil.i$default(LogUtil.TAG_QS, str, "onViewDetached", null, 8);
        ((OplusQsBaseToggleSliderLayout) this.mView).setOnToggleClickListener(null);
        ((OplusQsBaseToggleSliderLayout) this.mView).removeOnSeekBarChangeListener(this);
        ((OplusQsBaseToggleSliderLayout) this.mView).setDeformedListener(null);
        ((OplusQsBaseToggleSliderLayout) this.mView).setOnTouchListener(null);
        ((OplusQsBaseToggleSliderLayout) this.mView).setSliderTouchListener(null);
        ((OplusQsBaseToggleSliderLayout) this.mView).removeOnLayoutChangeListener(this.layoutChangedListener);
        this.musicVolumeStateUpdate.detach();
        this.musicStreamLabelUpdate.detach();
        this.musicStreamZenMutedUpdate.detach();
        this.volumeUnchangeUpdate.detach();
        this.a11yStreamShowUpdate.detach();
        this.disableShowVolumeDialogUpdate.detach();
        this.musicVolumeLevel.detach();
        this.musicMaxVolumeLevel.detach();
        this.musicMinVolumeLevel.detach();
        this.musicStreamLabel.detach();
        this.musicStreamZenMuted.detach();
        this.a11yStreamShow.detach();
        this.musicVolumeState.detach();
        this.ringModeUpdate.detach();
        this.volumeSeekBarProgressUpdater.detach();
        this.volumeSeekBarMaxProgressUpdater.detach();
        this.volumeSeekBarMinProgressUpdater.detach();
        this.seekBarVolumeLevelOnChanged.detach();
        this.viewEnableUpdater.detach();
        this.volumeIconContentDescriptionUpdater.detach();
        this.volumeSliderContentDescriptionUpdater.detach();
        this.volumeIconProgressLevelUpdater.detach();
        int progress = ((OplusQsBaseToggleSliderLayout) this.mView).getProgress();
        int iIntValue = ((Number) this.seekBarVolumeLevel.get()).intValue();
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("saveCurrentSeekBarProgress: isProgressChanged=", progress, " progress=", ", volumeLevel=", this.isProgressChanged);
        sbM.append(iIntValue);
        LogUtil.i$default(LogUtil.TAG_QS, str, sbM.toString(), null, 8);
        if (this.isProgressChanged) {
            PrefUtil.getInt(this.userContextProvider.getCurrentUserContext(), PREF_KEY_LAST_SLIDER_PROGRESS, progress);
            PrefUtil.getInt(this.userContextProvider.getCurrentUserContext(), PREF_KEY_LAST_SEEKBAR_VOLUME_LEVEL, iIntValue);
        }
        ((OplusQsBaseToggleSliderLayout) this.mView).setOnTouchAnimationListener(null);
    }

    public final void setAllowProgressUpdate(long j) {
        this.volumeController.setAllowProgressUpdate(j);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void setIconTranslationY(float f) {
        LottieAnimationView lottieAnimationView = this.volumeIconView;
        if (lottieAnimationView != null) {
            lottieAnimationView.setTranslationY(f);
        }
    }

    public final void setIsEditable(boolean z) {
        CharSequence contentDescription;
        CharSequence contentDescription2;
        this.isEditMode = z;
        ((OplusQsBaseToggleSliderLayout) this.mView).setIsEditable(z);
        LottieAnimationView lottieAnimationView = this.volumeIconView;
        if (lottieAnimationView != null && (contentDescription2 = lottieAnimationView.getContentDescription()) != null) {
            this.iconContentDescription = contentDescription2;
        }
        LottieAnimationView lottieAnimationView2 = this.volumeIconView;
        if (lottieAnimationView2 != null) {
            ViewExtKt.setTalkBackEnabled(lottieAnimationView2, !z, this.iconContentDescription);
        }
        IQSSpotLightConfig slider = ((OplusQsBaseToggleSliderLayout) this.mView).getSlider();
        if (slider != null && (contentDescription = slider.getContentDescription()) != null) {
            this.sliderContentDescription = contentDescription;
        }
        IQSSpotLightConfig slider2 = ((OplusQsBaseToggleSliderLayout) this.mView).getSlider();
        if (slider2 != null) {
            ViewExtKt.setTalkBackEnabled(slider2, !z, this.sliderContentDescription);
        }
    }

    public final void setListening(boolean z) {
        OplusQsSliderTouchAnimationDelegate touchAnimationDelegate;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            AodClockLayout$3$.ExternalSyntheticOutline0.m("setListening listening=", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(this.tag, LogUtil.SEPARATOR), z);
        }
        this.isQsListening.set(Boolean.valueOf(z));
        this.isTrackingTouch = false;
        ((OplusQsBaseToggleSliderLayout) this.mView).setDisallowInterceptTouchEvent(z);
        if (z) {
            return;
        }
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout = this.layout;
        if (oplusQsBaseToggleSliderLayout != null && (touchAnimationDelegate = oplusQsBaseToggleSliderLayout.getTouchAnimationDelegate()) != null) {
            touchAnimationDelegate.dispatchSimulateUp();
        }
        OplusQsBaseToggleSliderLayout oplusQsBaseToggleSliderLayout2 = this.layout;
        if (oplusQsBaseToggleSliderLayout2 != null) {
            oplusQsBaseToggleSliderLayout2.setTranslationY(0.0f);
            oplusQsBaseToggleSliderLayout2.setScaleX(1.0f);
            oplusQsBaseToggleSliderLayout2.setScaleY(1.0f);
        }
    }

    public final void setLongClickListener(View.OnLongClickListener onLongClickListener) {
        ((OplusQsBaseToggleSliderLayout) this.mView).setLongClickListener(onLongClickListener);
    }

    public final void setOnLayoutClickListener(View.OnClickListener onClickListener) {
        ((OplusQsBaseToggleSliderLayout) this.mView).setOnLayoutClickListener(onClickListener);
    }

    public final void updateColor(boolean z, QSSpecialModeUpdateMonitor.ColorType colorType) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(this.tag, LogUtil.SEPARATOR);
            sbM.append("updateColor musicVolumeState=" + this.musicVolumeState.get());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        updateSeekBarColor$default(this, z, false, colorType, 2);
        updateVolumeIconState((QSVolume.VolumeState) this.musicVolumeState.get(), true);
    }

    public void updateDisableShowVolumeDialog() {
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, OpaqueKey$.ExternalSyntheticOutline0.m(this.isQsListening.get(), "updateDisableShowVolumeDialog: isQsListening="), null, 8);
        this.volumeController.disableShowVolumeDialog(((Boolean) this.isQsListening.get()).booleanValue());
    }

    public void updateIconState(QSVolume.VolumeState volumeState, boolean z) {
        OplusQsVolumeIconView oplusQsVolumeIconView = this.volumeIconView;
        if (oplusQsVolumeIconView != null) {
            oplusQsVolumeIconView.updateIconState(volumeState, z);
        }
    }

    public final void updateSeekBarColor(boolean z, boolean z2, QSSpecialModeUpdateMonitor.ColorType colorType) {
        ((OplusQsBaseToggleSliderLayout) this.mView).updateColor(z, z2, colorType);
    }

    public final void updateVolumeIconState(QSVolume.VolumeState volumeState, boolean z) {
        volumeState.setDistanceFromBottom(this.distanceFromBottom);
        volumeState.setZenMuted(false);
        QSVolume.VolumeState volumeStateCopy$default = QSVolume.VolumeState.copy$default(volumeState, 0, 0, 0, 0, 0, false, 63, (Object) null);
        volumeStateCopy$default.setZenMuted(false);
        int i = this.distanceFromBottom;
        Object obj = this.seekBarVolumeLevel.get();
        boolean zenMuted = volumeStateCopy$default.getZenMuted();
        int progress = ((OplusQsBaseToggleSliderLayout) this.mView).getProgress();
        int route = volumeStateCopy$default.getRoute();
        StringBuilder sb = new StringBuilder("updateVolumeIconState: distanceFromBottom=");
        sb.append(i);
        sb.append(" seekBarVolumeLevel=");
        sb.append(obj);
        sb.append(" zenMuted:");
        PromptIconState$.ExternalSyntheticOutline0.m(progress, " progress:", " route:", sb, zenMuted);
        sb.append(route);
        LogUtil.i$default(LogUtil.TAG_QS, this.tag, sb.toString(), null, 8);
        Handler handler = this.mainHandler;
        RunnableC02531 runnableC02531 = new RunnableC02531(0);
        runnableC02531.this$0 = this;
        runnableC02531.$state = volumeStateCopy$default;
        runnableC02531.$isUIModeChange = z;
        VarHandle.storeStoreFence();
        handler.post(runnableC02531);
    }
}
