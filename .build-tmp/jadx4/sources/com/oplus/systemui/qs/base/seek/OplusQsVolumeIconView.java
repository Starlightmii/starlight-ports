package com.oplus.systemui.qs.base.seek;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import androidx.databinding.ObservableField;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.qs.NonNullObservableField;
import com.android.systemui.plugins.qs.QSVolume;
import com.oplus.systemui.aod.aodclock.off.AodClockLayout$3$;
import com.oplus.systemui.qs.base.tile.QSLottieAnimationView;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQsVolumeIconView extends QSLottieAnimationView {
    private static final String ASSETS_DIR_PREFIX = "volumeui/";
    public static final Companion Companion = new Companion();
    private static final NonNullObservableField<Integer> musicStreamRoute = new NonNullObservableField<>(2, (ObservableField[]) null, false, (Function1) null, 14, (DefaultConstructorMarker) null);
    private final OplusQsVolumeIconView$bluetoothRouteIconState$1 bluetoothRouteIconState;
    private VolumeIconState currentIconState;
    public boolean disableTheme;
    private final OplusQsVolumeIconView$idleIconState$1 idleIconState;
    private final OplusQsVolumeIconView$normalRouteIconState$1 normalRouteIconState;
    private QSVolume.VolumeState qsVolumeState;
    public final String tag;
    private final OplusQsVolumeIconView$wiredRouteIconState$1 wiredRouteIconState;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final NonNullObservableField<Integer> getMusicStreamRoute() {
            return OplusQsVolumeIconView.musicStreamRoute;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface VolumeIconState {
        default Float getAlpha() {
            return null;
        }

        String getDescription();

        Boolean isMuted();

        default void onStateEnter() {
        }

        void onVolumeStateChanged(QSVolume.VolumeState volumeState);
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView$wiredRouteIconState$1] */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView$VolumeIconState, com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView$idleIconState$1] */
    public OplusQsVolumeIconView(Context context) {
        super(context, null, 6, 0);
        ?? r4 = new VolumeIconState() { // from class: com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView$idleIconState$1
            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public /* bridge */ /* synthetic */ Float getAlpha() {
                return super.getAlpha();
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public String getDescription() {
                return "IdleIconState";
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public final Boolean isMuted() {
                return null;
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public final void onVolumeStateChanged(QSVolume.VolumeState volumeState) {
                LogUtil.e$default(8, LogUtil.TAG_QS, this.this$0.tag, "onVolumeStateChanged: occur error");
            }
        };
        this.idleIconState = r4;
        this.tag = getClass().getSimpleName();
        this.normalRouteIconState = new OplusQsVolumeIconView$normalRouteIconState$1(this);
        this.bluetoothRouteIconState = new OplusQsVolumeIconView$bluetoothRouteIconState$1(this);
        this.wiredRouteIconState = new VolumeIconState() { // from class: com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView$wiredRouteIconState$1
            public Boolean aboveThreshold;
            public boolean isGlobalTheme;
            public Boolean isMuted;
            public int lastLevel = -1;

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public /* bridge */ /* synthetic */ Float getAlpha() {
                return super.getAlpha();
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public String getDescription() {
                return "WiredRouteIconState";
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public final Boolean isMuted() {
                return this.isMuted;
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public final void onStateEnter() {
                this.isGlobalTheme = QsColorUtil.isGlobalThemeApplied(this.this$0.getContext());
                this.isMuted = null;
                this.aboveThreshold = null;
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public final void onVolumeStateChanged(QSVolume.VolumeState volumeState) {
                int i;
                boolean z = false;
                boolean z2 = true;
                boolean z3 = volumeState.getLevel() == 0;
                int distanceFromBottom = volumeState.getDistanceFromBottom();
                int level = volumeState.getLevel();
                OplusQsVolumeIconView oplusQsVolumeIconView = this.this$0;
                boolean zIsAboveThreshold = oplusQsVolumeIconView.isAboveThreshold(distanceFromBottom, level);
                if (this.aboveThreshold != null && Boolean.valueOf(zIsAboveThreshold).equals(this.aboveThreshold)) {
                    z2 = false;
                }
                if (this.lastLevel != level || this.aboveThreshold == null || z2) {
                    if (z2) {
                        this.aboveThreshold = Boolean.valueOf(zIsAboveThreshold);
                    }
                    z = z2;
                }
                String str = oplusQsVolumeIconView.tag;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                    Boolean bool = this.isMuted;
                    Boolean bool2 = this.aboveThreshold;
                    int i2 = this.lastLevel;
                    StringBuilder sb = new StringBuilder("wiredRouteIconState onVolumeStateChanged volumeState: ");
                    sb.append(volumeState);
                    sb.append(", isMuted: ");
                    sb.append(bool);
                    sb.append(" needUpdate:");
                    KeyguardLogger$.ExternalSyntheticOutline0.m(" isMute:", " aboveThreshold:", sb, z, z3);
                    sb.append(bool2);
                    sb.append(" lastLevel:");
                    sb.append(i2);
                    sbM.append(sb.toString());
                    Log.d("SystemUi--QuickSettings", sbM.toString());
                }
                this.lastLevel = volumeState.getLevel();
                if (!Boolean.valueOf(z3).equals(this.isMuted) || this.isGlobalTheme || z || volumeState.getZenMuted()) {
                    if (z3) {
                        i = oplusQsVolumeIconView.getDisableTheme() ? 2131236338 : 2131237178;
                    } else {
                        i = oplusQsVolumeIconView.getDisableTheme() ? 2131236339 : 2131237177;
                    }
                    if (z) {
                        oplusQsVolumeIconView.updateIconColor(z3, Intrinsics.areEqual(this.aboveThreshold, Boolean.TRUE));
                    }
                    oplusQsVolumeIconView.setImageResource(i);
                    this.isMuted = Boolean.valueOf(z3);
                }
            }
        };
        this.currentIconState = r4;
    }

    public boolean checkLevelChanged(int i, int i2) {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void disableTheme(boolean z) {
        if (z != getDisableTheme()) {
            setDisableTheme(z);
            QSVolume.VolumeState volumeState = this.qsVolumeState;
            if (volumeState != null) {
                volumeState.setZenMuted(false);
                switchStateTo(getTargetIconState(volumeState), false).onVolumeStateChanged(volumeState);
            }
            Boolean boolIsMuted = this.currentIconState.isMuted();
            int muteIconColor = boolIsMuted != null ? boolIsMuted.booleanValue() : false ? getMuteIconColor() : getUnMuteIconColor();
            setAlpha(1.0f);
            if (muteIconColor != 0) {
                setColorFilter(muteIconColor, PorterDuff.Mode.SRC_IN);
            } else {
                clearColorFilter();
            }
        }
    }

    public final Float getAlpha(QSVolume.VolumeState volumeState) {
        if (volumeState.getLevel() < 0 || volumeState.getRoute() == -1) {
            return null;
        }
        return switchStateTo(getTargetIconState(volumeState), false).getAlpha();
    }

    public boolean getDisableTheme() {
        return this.disableTheme;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int getMuteIconColor() {
        if (!QsColorUtil.isGlobalThemeApplied(getContext()) || getDisableTheme()) {
            return (ScrimUtil.isWallpaperBlurDisable() && QsColorUtil.isLowGaussianLevelColor(getContext(), getDisableTheme())) ? QsColorUtil.BRIGHTNESS_ICON_BG_LIGHT_COLOR : QsColorUtil.ICON_VOLUME_MEDIA_MUTE_WHITE_COLOR;
        }
        return 0;
    }

    public final VolumeIconState getTargetIconState(QSVolume.VolumeState volumeState) {
        int route = volumeState.getRoute();
        if (route == 0) {
            return this.wiredRouteIconState;
        }
        if (route != 1) {
            return route != 2 ? this.idleIconState : this.normalRouteIconState;
        }
        return this.bluetoothRouteIconState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int getUnMuteIconColor() {
        if (!QsColorUtil.isGlobalThemeApplied(getContext()) || getDisableTheme()) {
            return QsColorfulConfigUtil.INSTANCE.getEnableColorfulConfig() ? QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_BLUE_COLOR : QsColorUtil.BRIGHTNESS_ICON_BG_LIGHT_COLOR;
        }
        return 0;
    }

    public boolean isAboveThreshold(int i, int i2) {
        return i >= 20;
    }

    public void setDisableTheme(boolean z) {
        this.disableTheme = z;
    }

    public final synchronized VolumeIconState switchStateTo(VolumeIconState volumeIconState, boolean z) {
        if (!Intrinsics.areEqual(this.currentIconState, volumeIconState) || z) {
            this.currentIconState.getClass();
            this.currentIconState = volumeIconState;
            volumeIconState.onStateEnter();
        }
        return this.currentIconState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void updateIconColor(boolean z, boolean z2) {
        String str = this.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("updateIconColor isMute:" + z + " aboveThreshold:" + z2);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        int unMuteIconColor = z2 ? getUnMuteIconColor() : getMuteIconColor();
        setAlpha(1.0f);
        if (unMuteIconColor != 0) {
            setColorFilter(unMuteIconColor, PorterDuff.Mode.SRC_IN);
        } else {
            clearColorFilter();
        }
    }

    public final void updateIconState(QSVolume.VolumeState volumeState, boolean z) {
        if (volumeState.getLevel() < 0 || volumeState.getRoute() == -1) {
            return;
        }
        this.qsVolumeState = volumeState;
        String str = this.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("updateIconState isValidVolumeState isUIModeChange:" + z + " zenMuted:" + volumeState.getZenMuted());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        switchStateTo(getTargetIconState(volumeState), z).onVolumeStateChanged(volumeState);
        musicStreamRoute.set(Integer.valueOf(volumeState.getRoute()));
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView$VolumeIconState, com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView$idleIconState$1] */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView$wiredRouteIconState$1] */
    public OplusQsVolumeIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 4, 0);
        ?? r3 = new VolumeIconState() { // from class: com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView$idleIconState$1
            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public /* bridge */ /* synthetic */ Float getAlpha() {
                return super.getAlpha();
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public String getDescription() {
                return "IdleIconState";
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public final Boolean isMuted() {
                return null;
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public final void onVolumeStateChanged(QSVolume.VolumeState volumeState) {
                LogUtil.e$default(8, LogUtil.TAG_QS, this.this$0.tag, "onVolumeStateChanged: occur error");
            }
        };
        this.idleIconState = r3;
        this.tag = getClass().getSimpleName();
        this.normalRouteIconState = new OplusQsVolumeIconView$normalRouteIconState$1(this);
        this.bluetoothRouteIconState = new OplusQsVolumeIconView$bluetoothRouteIconState$1(this);
        this.wiredRouteIconState = new VolumeIconState() { // from class: com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView$wiredRouteIconState$1
            public Boolean aboveThreshold;
            public boolean isGlobalTheme;
            public Boolean isMuted;
            public int lastLevel = -1;

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public /* bridge */ /* synthetic */ Float getAlpha() {
                return super.getAlpha();
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public String getDescription() {
                return "WiredRouteIconState";
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public final Boolean isMuted() {
                return this.isMuted;
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public final void onStateEnter() {
                this.isGlobalTheme = QsColorUtil.isGlobalThemeApplied(this.this$0.getContext());
                this.isMuted = null;
                this.aboveThreshold = null;
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public final void onVolumeStateChanged(QSVolume.VolumeState volumeState) {
                int i;
                boolean z = false;
                boolean z2 = true;
                boolean z3 = volumeState.getLevel() == 0;
                int distanceFromBottom = volumeState.getDistanceFromBottom();
                int level = volumeState.getLevel();
                OplusQsVolumeIconView oplusQsVolumeIconView = this.this$0;
                boolean zIsAboveThreshold = oplusQsVolumeIconView.isAboveThreshold(distanceFromBottom, level);
                if (this.aboveThreshold != null && Boolean.valueOf(zIsAboveThreshold).equals(this.aboveThreshold)) {
                    z2 = false;
                }
                if (this.lastLevel != level || this.aboveThreshold == null || z2) {
                    if (z2) {
                        this.aboveThreshold = Boolean.valueOf(zIsAboveThreshold);
                    }
                    z = z2;
                }
                String str = oplusQsVolumeIconView.tag;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                    Boolean bool = this.isMuted;
                    Boolean bool2 = this.aboveThreshold;
                    int i2 = this.lastLevel;
                    StringBuilder sb = new StringBuilder("wiredRouteIconState onVolumeStateChanged volumeState: ");
                    sb.append(volumeState);
                    sb.append(", isMuted: ");
                    sb.append(bool);
                    sb.append(" needUpdate:");
                    KeyguardLogger$.ExternalSyntheticOutline0.m(" isMute:", " aboveThreshold:", sb, z, z3);
                    sb.append(bool2);
                    sb.append(" lastLevel:");
                    sb.append(i2);
                    sbM.append(sb.toString());
                    Log.d("SystemUi--QuickSettings", sbM.toString());
                }
                this.lastLevel = volumeState.getLevel();
                if (!Boolean.valueOf(z3).equals(this.isMuted) || this.isGlobalTheme || z || volumeState.getZenMuted()) {
                    if (z3) {
                        i = oplusQsVolumeIconView.getDisableTheme() ? 2131236338 : 2131237178;
                    } else {
                        i = oplusQsVolumeIconView.getDisableTheme() ? 2131236339 : 2131237177;
                    }
                    if (z) {
                        oplusQsVolumeIconView.updateIconColor(z3, Intrinsics.areEqual(this.aboveThreshold, Boolean.TRUE));
                    }
                    oplusQsVolumeIconView.setImageResource(i);
                    this.isMuted = Boolean.valueOf(z3);
                }
            }
        };
        this.currentIconState = r3;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView$VolumeIconState, com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView$idleIconState$1] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView$wiredRouteIconState$1] */
    public OplusQsVolumeIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ?? r1 = new VolumeIconState() { // from class: com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView$idleIconState$1
            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public /* bridge */ /* synthetic */ Float getAlpha() {
                return super.getAlpha();
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public String getDescription() {
                return "IdleIconState";
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public final Boolean isMuted() {
                return null;
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public final void onVolumeStateChanged(QSVolume.VolumeState volumeState) {
                LogUtil.e$default(8, LogUtil.TAG_QS, this.this$0.tag, "onVolumeStateChanged: occur error");
            }
        };
        this.idleIconState = r1;
        this.tag = getClass().getSimpleName();
        this.normalRouteIconState = new OplusQsVolumeIconView$normalRouteIconState$1(this);
        this.bluetoothRouteIconState = new OplusQsVolumeIconView$bluetoothRouteIconState$1(this);
        this.wiredRouteIconState = new VolumeIconState() { // from class: com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView$wiredRouteIconState$1
            public Boolean aboveThreshold;
            public boolean isGlobalTheme;
            public Boolean isMuted;
            public int lastLevel = -1;

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public /* bridge */ /* synthetic */ Float getAlpha() {
                return super.getAlpha();
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public String getDescription() {
                return "WiredRouteIconState";
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public final Boolean isMuted() {
                return this.isMuted;
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public final void onStateEnter() {
                this.isGlobalTheme = QsColorUtil.isGlobalThemeApplied(this.this$0.getContext());
                this.isMuted = null;
                this.aboveThreshold = null;
            }

            @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
            public final void onVolumeStateChanged(QSVolume.VolumeState volumeState) {
                int i2;
                boolean z = false;
                boolean z2 = true;
                boolean z3 = volumeState.getLevel() == 0;
                int distanceFromBottom = volumeState.getDistanceFromBottom();
                int level = volumeState.getLevel();
                OplusQsVolumeIconView oplusQsVolumeIconView = this.this$0;
                boolean zIsAboveThreshold = oplusQsVolumeIconView.isAboveThreshold(distanceFromBottom, level);
                if (this.aboveThreshold != null && Boolean.valueOf(zIsAboveThreshold).equals(this.aboveThreshold)) {
                    z2 = false;
                }
                if (this.lastLevel != level || this.aboveThreshold == null || z2) {
                    if (z2) {
                        this.aboveThreshold = Boolean.valueOf(zIsAboveThreshold);
                    }
                    z = z2;
                }
                String str = oplusQsVolumeIconView.tag;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                    Boolean bool = this.isMuted;
                    Boolean bool2 = this.aboveThreshold;
                    int i3 = this.lastLevel;
                    StringBuilder sb = new StringBuilder("wiredRouteIconState onVolumeStateChanged volumeState: ");
                    sb.append(volumeState);
                    sb.append(", isMuted: ");
                    sb.append(bool);
                    sb.append(" needUpdate:");
                    KeyguardLogger$.ExternalSyntheticOutline0.m(" isMute:", " aboveThreshold:", sb, z, z3);
                    sb.append(bool2);
                    sb.append(" lastLevel:");
                    sb.append(i3);
                    sbM.append(sb.toString());
                    Log.d("SystemUi--QuickSettings", sbM.toString());
                }
                this.lastLevel = volumeState.getLevel();
                if (!Boolean.valueOf(z3).equals(this.isMuted) || this.isGlobalTheme || z || volumeState.getZenMuted()) {
                    if (z3) {
                        i2 = oplusQsVolumeIconView.getDisableTheme() ? 2131236338 : 2131237178;
                    } else {
                        i2 = oplusQsVolumeIconView.getDisableTheme() ? 2131236339 : 2131237177;
                    }
                    if (z) {
                        oplusQsVolumeIconView.updateIconColor(z3, Intrinsics.areEqual(this.aboveThreshold, Boolean.TRUE));
                    }
                    oplusQsVolumeIconView.setImageResource(i2);
                    this.isMuted = Boolean.valueOf(z3);
                }
            }
        };
        this.currentIconState = r1;
    }

    public final void updateIconState(QSVolume.VolumeState volumeState) {
        if (volumeState.getLevel() < 0 || volumeState.getRoute() == -1) {
            return;
        }
        this.qsVolumeState = volumeState;
        String str = this.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            AodClockLayout$3$.ExternalSyntheticOutline0.m("updateIconState isValidVolumeState zenMuted:", "SystemUi--QuickSettings", Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR), volumeState.getZenMuted());
        }
        switchStateTo(getTargetIconState(volumeState), false).onVolumeStateChanged(volumeState);
        musicStreamRoute.set(Integer.valueOf(volumeState.getRoute()));
    }
}
