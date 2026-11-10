package com.oplus.systemui.qs.base.seek;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import androidx.car.app.serialization.Bundler$;
import androidx.compose.ui.spatial.RectList$;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.room.util.CursorUtil$;
import com.android.keyguard.OplusLockIconView$;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.android.systemui.plugins.qs.QSVolume;
import com.oplus.systemui.qs.base.tile.QSLottieAnimationView;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsVolumeIconView$normalRouteIconState$1 implements OplusQsVolumeIconView.VolumeIconState {
    public Boolean aboveThreshold;
    public QSLottieAnimationView.ColorfulConfig colorfulConfig;
    public String currentAsset;
    public int currentEndFrame;
    public int currentStartFrame;
    public boolean isGlobalTheme;
    public Boolean lastAboveThreshold;
    public final /* synthetic */ OplusQsVolumeIconView this$0;
    public int lastLevel = -1;
    public String lastJsonRes = "";
    public int lastDistanceFromBottom = -1;
    public float currentSpeed = 1.0f;

    public OplusQsVolumeIconView$normalRouteIconState$1(OplusQsVolumeIconView oplusQsVolumeIconView) {
        this.this$0 = oplusQsVolumeIconView;
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
    public Float getAlpha() {
        QSLottieAnimationView.ColorfulConfig colorfulConfig = this.colorfulConfig;
        if (colorfulConfig != null) {
            return Float.valueOf(colorfulConfig.getEndAlpha());
        }
        return null;
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
    public String getDescription() {
        return "NormalRouteIconState";
    }

    @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
    public final Boolean isMuted() {
        int i = this.lastLevel;
        if (i == -1) {
            return null;
        }
        return Boolean.valueOf(i == 0);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [android.widget.ImageView, com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView] */
    @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
    public final void onStateEnter() {
        ?? r0 = this.this$0;
        String str = r0.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(str, "-->normalRouteIconState onStateEnter", "SystemUi--QuickSettings");
        }
        this.isGlobalTheme = QsColorUtil.isGlobalThemeApplied(r0.getContext());
        this.lastLevel = -1;
        this.lastJsonRes = "";
        this.currentAsset = null;
        this.lastDistanceFromBottom = -1;
        this.aboveThreshold = null;
    }

    /* JADX WARN: Code duplicated, block: B:138:0x0267  */
    /* JADX WARN: Code duplicated, block: B:86:0x01ac  */
    @Override // com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView.VolumeIconState
    public final void onVolumeStateChanged(QSVolume.VolumeState volumeState) {
        QSVolume.VolumeState volumeState2;
        int i;
        int i2;
        float f;
        boolean z;
        int i3;
        int i4;
        int i5;
        float f2;
        int i6;
        int i7;
        String str;
        int i8;
        String str2;
        OplusQsVolumeIconView oplusQsVolumeIconView = this.this$0;
        String str3 = oplusQsVolumeIconView.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str3, LogUtil.SEPARATOR);
            String str4 = this.lastJsonRes;
            int i9 = this.lastLevel;
            StringBuilder sb = new StringBuilder("normalRouteIconState onVolumeStateChanged mIsDefaultVolumeTheme: volumeState: ");
            volumeState2 = volumeState;
            sb.append(volumeState2);
            sb.append(", lastJsonRes: ");
            sb.append(str4);
            sb.append(", lastLevel: ");
            sb.append(i9);
            sbM.append(sb.toString());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        } else {
            volumeState2 = volumeState;
        }
        int maxLevel = volumeState2.getMaxLevel();
        int level = volumeState2.getLevel();
        float f3 = maxLevel;
        int i10 = (int) (0.33f * f3);
        int i11 = (int) (f3 * 0.66f);
        boolean zenMuted = volumeState2.getZenMuted();
        int i12 = this.lastLevel;
        if (i12 < 0 || this.isGlobalTheme) {
            if (level == 0) {
                i = oplusQsVolumeIconView.getDisableTheme() ? 2131236336 : 2131237175;
            } else if (level <= i10) {
                i = oplusQsVolumeIconView.getDisableTheme() ? 2131236335 : 2131237174;
            } else if (level <= i11) {
                i = oplusQsVolumeIconView.getDisableTheme() ? 2131236337 : 2131237170;
            } else {
                i = oplusQsVolumeIconView.getDisableTheme() ? 2131236334 : 2131237173;
            }
            oplusQsVolumeIconView.setImageResource(i);
            Boolean boolValueOf = Boolean.valueOf(oplusQsVolumeIconView.isAboveThreshold(volumeState.getDistanceFromBottom(), volumeState.getLevel()));
            this.aboveThreshold = boolValueOf;
            this.lastAboveThreshold = boolValueOf;
            oplusQsVolumeIconView.updateIconColor(level == 0, boolValueOf.equals(Boolean.TRUE));
            this.lastLevel = level;
            this.lastDistanceFromBottom = volumeState.getDistanceFromBottom();
            return;
        }
        if (oplusQsVolumeIconView.checkLevelChanged(level, i12)) {
            String str5 = "";
            String str6 = (QsColorUtil.isIconNeedUseLightColor(((ImageView) oplusQsVolumeIconView).mContext, oplusQsVolumeIconView.getDisableTheme()) || QsColorUtil.isIconNeedUseLightColorWhenDarkMode(((ImageView) oplusQsVolumeIconView).mContext, oplusQsVolumeIconView.getDisableTheme()) || ScrimUtil.isWallpaperBlurDisable()) ? "_gauss" : "";
            int distanceFromBottom = volumeState2.getDistanceFromBottom();
            int level2 = volumeState2.getLevel();
            boolean zIsAboveThreshold = oplusQsVolumeIconView.isAboveThreshold(distanceFromBottom, level2);
            boolean z2 = this.aboveThreshold == null || !Boolean.valueOf(zIsAboveThreshold).equals(this.aboveThreshold);
            if (this.lastLevel == level2 && this.aboveThreshold != null && !z2) {
                z2 = false;
            } else if (z2) {
                this.aboveThreshold = Boolean.valueOf(zIsAboveThreshold);
            }
            String str7 = oplusQsVolumeIconView.tag;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM2 = Bundler$.ExternalSyntheticOutline0.m(str7, LogUtil.SEPARATOR);
                int i13 = this.lastLevel;
                Boolean bool = this.aboveThreshold;
                Boolean bool2 = this.lastAboveThreshold;
                int i14 = this.lastDistanceFromBottom;
                int distanceFromBottom2 = volumeState2.getDistanceFromBottom();
                StringBuilder sbM3 = OplusLockIconView$.ExternalSyntheticOutline0.m("needUpdate = ", i10, " grade1 = ", " grade2 = ", z2);
                RectList$.ExternalSyntheticOutline0.m(i11, level, " currentLevel:", " lastLevel = ", sbM3);
                sbM3.append(i13);
                sbM3.append(" aboveThreshold = ");
                sbM3.append(bool);
                sbM3.append(" lastAboveThreshold = ");
                sbM3.append(bool2);
                sbM3.append(" lastDistanceFromBottom:");
                sbM3.append(i14);
                sbM3.append(" distanceFromBottom:");
                sbM3.append(distanceFromBottom2);
                sbM2.append(sbM3.toString());
                Log.d("SystemUi--QuickSettings", sbM2.toString());
            }
            boolean z3 = level < i10 && (level != this.lastLevel || this.lastAboveThreshold == null);
            boolean z4 = zenMuted != 0 && level > 0;
            boolean z5 = z2 || zenMuted;
            boolean z6 = z3 || z4 || this.lastDistanceFromBottom != volumeState.getDistanceFromBottom();
            Boolean bool3 = this.aboveThreshold;
            Boolean bool4 = Boolean.TRUE;
            boolean z7 = (Intrinsics.areEqual(bool3, bool4) && Intrinsics.areEqual(this.lastAboveThreshold, bool4)) ? false : true;
            int i15 = 93;
            if (z6 && z5 && z7) {
                boolean zAreEqual = Intrinsics.areEqual(this.aboveThreshold, bool4);
                int i16 = this.lastLevel;
                if (zAreEqual) {
                    if (level >= i16) {
                        i2 = 46;
                        i3 = 36;
                        i4 = 36;
                        f = 1.0f;
                        z = true;
                    } else {
                        i2 = 93;
                        f = 1.0f;
                        z = false;
                        i3 = 0;
                        i4 = 0;
                    }
                } else if (level <= i16) {
                    i2 = 46;
                    i4 = 46;
                    i3 = 36;
                    z = true;
                    f = -1.0f;
                } else {
                    i2 = 93;
                    f = 1.0f;
                    z = false;
                    i3 = 0;
                    i4 = 0;
                }
            } else {
                i2 = 93;
                f = 1.0f;
                z = false;
                i3 = 0;
                i4 = 0;
            }
            if (level == 0) {
                int i17 = this.lastLevel;
                if (i17 > i11) {
                    str2 = "ic_qs_volume_media_grade_3_to_0";
                } else if (i17 > i10) {
                    str2 = "ic_qs_volume_media_grade_2_to_0";
                } else if (i17 > 0) {
                    if (Intrinsics.areEqual(this.lastAboveThreshold, bool4)) {
                        i5 = 94;
                        i15 = 129;
                        i6 = i15;
                        i7 = i5;
                        i8 = i7;
                        f2 = 1.0f;
                    } else {
                        i7 = 130;
                        i8 = 130;
                        f2 = 1.0f;
                        i6 = 166;
                    }
                    z = true;
                } else {
                    i7 = i3;
                    i6 = i2;
                    i8 = i4;
                    f2 = f;
                    str5 = str5;
                }
                str5 = str2;
                i6 = 36;
                f2 = 1.0f;
                z = true;
                i7 = 0;
                i8 = 0;
            } else {
                int i18 = this.lastLevel;
                if (level <= i10) {
                    if (i18 == 0) {
                        if (Intrinsics.areEqual(this.aboveThreshold, bool4)) {
                            f2 = 3.0f;
                            i6 = 212;
                            i7 = 166;
                            i8 = i7;
                        } else {
                            i6 = 36;
                            f2 = f;
                            str5 = str5;
                            z = true;
                            i7 = 0;
                            i8 = 0;
                        }
                    } else if (i18 > i11) {
                        f2 = -4.0f;
                        i6 = 93;
                        i8 = 93;
                        i7 = Intrinsics.areEqual(this.aboveThreshold, bool4) ? 46 : 36;
                    } else if (i18 > i10) {
                        if (Intrinsics.areEqual(this.aboveThreshold, bool4)) {
                            i7 = 46;
                            i6 = 70;
                            i8 = 70;
                        } else {
                            i6 = 46;
                            i8 = 46;
                            i7 = 36;
                        }
                        z = true;
                        f2 = -1.0f;
                    } else {
                        i7 = i3;
                        i6 = i2;
                        i8 = i4;
                        f2 = f;
                        str5 = str5;
                    }
                } else if (level <= i11) {
                    if (i18 == 0) {
                        str = "ic_qs_volume_media_grade_0_to_2";
                        str5 = str;
                        f2 = 1.0f;
                        i6 = 37;
                        z = true;
                        i7 = 0;
                        i8 = 0;
                    } else if (i18 <= i10) {
                        i7 = 46;
                        i8 = 46;
                        f2 = 1.0f;
                        i6 = 70;
                    } else if (i18 > i11) {
                        i6 = 93;
                        i8 = 93;
                        i7 = 70;
                        z = true;
                        f2 = -1.0f;
                    } else {
                        i7 = i3;
                        i6 = i2;
                        i8 = i4;
                        f2 = f;
                        str5 = str5;
                    }
                } else if (i18 == 0) {
                    str = "ic_qs_volume_media_grade_0_to_3";
                    str5 = str;
                    f2 = 1.0f;
                    i6 = 37;
                    z = true;
                    i7 = 0;
                    i8 = 0;
                } else if (i18 <= i10) {
                    f2 = 4.0f;
                    i6 = 93;
                    i7 = 36;
                    i8 = i7;
                } else if (i18 <= i11) {
                    i5 = 60;
                    i6 = i15;
                    i7 = i5;
                    i8 = i7;
                    f2 = 1.0f;
                } else {
                    i7 = i3;
                    i6 = i2;
                    i8 = i4;
                    f2 = f;
                    str5 = str5;
                }
                z = true;
            }
            if (str5.length() > 0) {
                String strM = CursorUtil$.ExternalSyntheticOutline0.m("volumeui/", str5, str6, "_night.json");
                this.colorfulConfig = QSLottieAnimationView.ColorfulConfig.Companion.makeColorfulConfigForQSVolumeIcon(((ImageView) oplusQsVolumeIconView).mContext, oplusQsVolumeIconView.getMuteIconColor(), oplusQsVolumeIconView.getUnMuteIconColor(), this.lastLevel, level, Intrinsics.areEqual(this.aboveThreshold, bool4), Intrinsics.areEqual(this.lastAboveThreshold, bool4));
                updateAnimation$1(strM, i7, i6, i8, f2);
            } else if (z) {
                String strM2 = Bundler$.ExternalSyntheticOutline0.m("volumeui/ic_qs_volume_media", str6, "_night.json");
                this.colorfulConfig = QSLottieAnimationView.ColorfulConfig.Companion.makeColorfulConfigForQSVolumeIcon(((ImageView) oplusQsVolumeIconView).mContext, oplusQsVolumeIconView.getMuteIconColor(), oplusQsVolumeIconView.getUnMuteIconColor(), this.lastLevel, level, Intrinsics.areEqual(this.aboveThreshold, bool4), Intrinsics.areEqual(this.lastAboveThreshold, bool4));
                if (z) {
                    updateAnimation$1(strM2, i7, i6, i8, f2);
                }
                str5 = "ic_qs_volume_media";
            }
            if (str5.length() > 0) {
                this.lastJsonRes = str5;
            }
            this.lastLevel = level;
            this.lastAboveThreshold = this.aboveThreshold;
            this.lastDistanceFromBottom = volumeState.getDistanceFromBottom();
        }
    }

    public final void updateAnimation$1(String str, int i, int i2, int i3, float f) {
        OplusQsVolumeIconView oplusQsVolumeIconView = this.this$0;
        String str2 = oplusQsVolumeIconView.tag;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str2, LogUtil.SEPARATOR);
            String str3 = this.currentAsset;
            Boolean boolIsMuted = isMuted();
            Boolean bool = this.aboveThreshold;
            Boolean bool2 = this.lastAboveThreshold;
            StringBuilder sbM2 = ConstraintSet$.ExternalSyntheticOutline0.m(i, "updateAnimation currentAsset:", str3, " startFrame:", ", endFrame:");
            RectList$.ExternalSyntheticOutline0.m(i2, i3, " frame:", " speed:", sbM2);
            sbM2.append(f);
            sbM2.append(" isMuted:");
            sbM2.append(boolIsMuted);
            sbM2.append(" assetName:");
            sbM2.append(str);
            sbM2.append(" aboveThreshold:");
            sbM2.append(bool);
            sbM2.append(" lastAboveThreshold:");
            sbM2.append(bool2);
            sbM2.append(" ");
            sbM.append(sbM2.toString());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        this.currentStartFrame = i;
        this.currentEndFrame = i2;
        this.currentSpeed = f;
        if (str.equals(this.currentAsset)) {
            oplusQsVolumeIconView.setTintColorfulConfig(str, this.colorfulConfig);
            oplusQsVolumeIconView.setMinAndMaxFrame(this.currentStartFrame, this.currentEndFrame);
            oplusQsVolumeIconView.setSpeed(this.currentSpeed);
            oplusQsVolumeIconView.setFrame(i3);
            oplusQsVolumeIconView.playAnimation();
            return;
        }
        oplusQsVolumeIconView.removeAllLottieOnCompositionLoadedListener();
        oplusQsVolumeIconView.setImageDrawable((Drawable) null);
        OplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2 oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2 = new OplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2(0);
        oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2.this$0 = oplusQsVolumeIconView;
        oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2.$assetName = str;
        oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2.this$1 = this;
        oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2.$frame = i3;
        oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2.$startFrame = i;
        oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2.$endFrame = i2;
        oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2.$speed = f;
        VarHandle.storeStoreFence();
        oplusQsVolumeIconView.addLottieOnCompositionLoadedListener(oplusQsVolumeIconView$normalRouteIconState$1$updateAnimation$2);
        oplusQsVolumeIconView.setAnimation(str);
        this.currentAsset = str;
    }
}
