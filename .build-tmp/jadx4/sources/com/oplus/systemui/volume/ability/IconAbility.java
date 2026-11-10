package com.oplus.systemui.volume.ability;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioSystem;
import android.util.Log;
import android.widget.TextView;
import androidx.collection.FloatList$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.volume.Util;
import com.android.systemui.Dependency;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.coui.appcompat.seekbar.COUIVerticalSeekBar;
import com.oplus.anim.EffectiveAnimationView;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.volume.OplusVolumeDialogImpl;
import com.oplus.systemui.volume.OplusVolumeDialogImpl$;
import com.oplus.systemui.volume.OplusVolumeSeekBar;
import com.oplus.systemui.volume.repository.AudioDataRepository;
import com.oplus.systemui.volume.view.OplusVolumeRow;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.volume.VolumeSettingsValueProxy;
import java.lang.invoke.VarHandle;
import java.util.concurrent.Executor;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IconAbility {
    public static final Companion Companion = new Companion();
    private static final String IC_ANIM_MEDIA_BLUETOOTH = "ic_volume_media_bluetooth";
    private static final String IC_ANIM_MEDIA_BLUETOOTH_MUTE = "ic_volume_media_bluetooth_mute";
    private static final String LIGHT_SUFFIX = "_light.json";
    private static final String SUFFIX = ".json";
    private static final String TAG = "IconAbility";
    private final Context context;
    private final OplusVolumeDialogImpl volumeInteractor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public IconAbility(Context context, OplusVolumeDialogImpl oplusVolumeDialogImpl) {
        this.context = context;
        this.volumeInteractor = oplusVolumeDialogImpl;
    }

    public static boolean isStreamMuted(VolumeDialogController.StreamState streamState) {
        return streamState.level == 0 || streamState.muted;
    }

    public final Context getContext() {
        return this.context;
    }

    public final String getStreamLabelH(VolumeDialogController.StreamState streamState) {
        String string;
        if (streamState == null) {
            return "";
        }
        String str = streamState.remoteLabel;
        if (str != null) {
            LogUtil.i$default(LogUtil.TAG_VOLUME, TAG, "getStreamLabelH, ss.remoteLabel=".concat(str), null, 8);
            string = streamState.remoteLabel;
        } else {
            try {
                string = this.context.getResources().getString(streamState.name);
            } catch (Resources.NotFoundException unused) {
                LogUtil.e$default(8, LogUtil.TAG_VOLUME, TAG, "Can't find translation for stream " + streamState);
                string = "";
            }
        }
        return string == null ? "" : string;
    }

    public final OplusVolumeDialogImpl getVolumeInteractor() {
        return this.volumeInteractor;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0127 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:102:0x012b  */
    /* JADX WARN: Code duplicated, block: B:104:0x012e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:109:0x0137 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:132:0x0165  */
    /* JADX WARN: Code duplicated, block: B:133:0x0167  */
    /* JADX WARN: Code duplicated, block: B:136:0x0175  */
    /* JADX WARN: Code duplicated, block: B:143:0x018d A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:147:0x0196  */
    /* JADX WARN: Code duplicated, block: B:148:0x019a  */
    /* JADX WARN: Code duplicated, block: B:151:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:156:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:164:0x022a  */
    /* JADX WARN: Code duplicated, block: B:167:0x0245  */
    /* JADX WARN: Code duplicated, block: B:169:0x024c A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:190:0x028c  */
    /* JADX WARN: Code duplicated, block: B:192:0x0292  */
    /* JADX WARN: Code duplicated, block: B:193:0x0295  */
    /* JADX WARN: Code duplicated, block: B:196:0x029d  */
    /* JADX WARN: Code duplicated, block: B:198:0x02a1  */
    /* JADX WARN: Code duplicated, block: B:200:0x02a9  */
    /* JADX WARN: Code duplicated, block: B:202:0x02af  */
    /* JADX WARN: Code duplicated, block: B:204:0x02b5 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:209:0x02bd A[PHI: r2
      0x02bd: PHI (r2v42 int) = (r2v1 int), (r2v47 int) binds: [B:216:0x02cf, B:208:0x02bc] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:215:0x02cd  */
    /* JADX WARN: Code duplicated, block: B:218:0x02d2  */
    /* JADX WARN: Code duplicated, block: B:220:0x02d6  */
    /* JADX WARN: Code duplicated, block: B:221:0x02db  */
    /* JADX WARN: Code duplicated, block: B:225:0x02e6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:226:0x02e8  */
    /* JADX WARN: Code duplicated, block: B:228:0x02ee  */
    /* JADX WARN: Code duplicated, block: B:230:0x02f4  */
    /* JADX WARN: Code duplicated, block: B:231:0x02fd  */
    /* JADX WARN: Code duplicated, block: B:232:0x0300 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:233:0x0302  */
    /* JADX WARN: Code duplicated, block: B:235:0x0314  */
    /* JADX WARN: Code duplicated, block: B:237:0x031a  */
    /* JADX WARN: Code duplicated, block: B:238:0x0320  */
    /* JADX WARN: Code duplicated, block: B:241:0x032b  */
    /* JADX WARN: Code duplicated, block: B:243:0x0333  */
    /* JADX WARN: Code duplicated, block: B:245:0x0339  */
    /* JADX WARN: Code duplicated, block: B:247:0x034b  */
    /* JADX WARN: Code duplicated, block: B:248:0x034f  */
    /* JADX WARN: Code duplicated, block: B:253:0x035f  */
    /* JADX WARN: Code duplicated, block: B:256:0x0378  */
    /* JADX WARN: Code duplicated, block: B:258:0x0386 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:259:0x0388  */
    /* JADX WARN: Code duplicated, block: B:260:0x038c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:261:0x038e  */
    /* JADX WARN: Code duplicated, block: B:263:0x0394  */
    /* JADX WARN: Code duplicated, block: B:265:0x039c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:266:0x039e  */
    /* JADX WARN: Code duplicated, block: B:267:0x03a1  */
    /* JADX WARN: Code duplicated, block: B:269:0x03a5  */
    /* JADX WARN: Code duplicated, block: B:270:0x03aa  */
    /* JADX WARN: Code duplicated, block: B:272:0x03b0  */
    /* JADX WARN: Code duplicated, block: B:274:0x03b7  */
    /* JADX WARN: Code duplicated, block: B:275:0x03bd A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:276:0x03bf  */
    /* JADX WARN: Code duplicated, block: B:277:0x03c2  */
    /* JADX WARN: Code duplicated, block: B:279:0x03c6  */
    /* JADX WARN: Code duplicated, block: B:280:0x03cb  */
    /* JADX WARN: Code duplicated, block: B:282:0x03d1  */
    /* JADX WARN: Code duplicated, block: B:283:0x03d7  */
    /* JADX WARN: Code duplicated, block: B:285:0x03df  */
    /* JADX WARN: Code duplicated, block: B:287:0x03e3  */
    /* JADX WARN: Code duplicated, block: B:289:0x03e9  */
    /* JADX WARN: Code duplicated, block: B:291:0x03ef  */
    /* JADX WARN: Code duplicated, block: B:292:0x03f4  */
    /* JADX WARN: Code duplicated, block: B:295:0x0401  */
    /* JADX WARN: Code duplicated, block: B:297:0x040b  */
    /* JADX WARN: Code duplicated, block: B:299:0x0414  */
    /* JADX WARN: Code duplicated, block: B:302:0x041d  */
    /* JADX WARN: Code duplicated, block: B:304:0x042a  */
    /* JADX WARN: Code duplicated, block: B:306:0x0430  */
    /* JADX WARN: Code duplicated, block: B:307:0x043b  */
    /* JADX WARN: Code duplicated, block: B:309:0x0442  */
    /* JADX WARN: Code duplicated, block: B:311:0x0453  */
    /* JADX WARN: Code duplicated, block: B:313:0x046a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:314:0x046c  */
    /* JADX WARN: Code duplicated, block: B:316:0x0472  */
    /* JADX WARN: Code duplicated, block: B:319:0x047a  */
    /* JADX WARN: Code duplicated, block: B:322:0x0482  */
    /* JADX WARN: Code duplicated, block: B:324:0x0487  */
    /* JADX WARN: Code duplicated, block: B:327:0x0490  */
    /* JADX WARN: Code duplicated, block: B:340:0x04e9  */
    /* JADX WARN: Code duplicated, block: B:343:0x04fd  */
    /* JADX WARN: Code duplicated, block: B:368:0x058b  */
    /* JADX WARN: Code duplicated, block: B:370:0x0591  */
    /* JADX WARN: Code duplicated, block: B:371:0x0594  */
    /* JADX WARN: Code duplicated, block: B:374:0x05a7  */
    /* JADX WARN: Code duplicated, block: B:376:0x05b1  */
    /* JADX WARN: Code duplicated, block: B:496:0x07aa  */
    /* JADX WARN: Code duplicated, block: B:500:0x07b8  */
    /* JADX WARN: Code duplicated, block: B:503:0x07c3  */
    /* JADX WARN: Code duplicated, block: B:505:0x07ca  */
    /* JADX WARN: Code duplicated, block: B:506:0x07ce  */
    /* JADX WARN: Code duplicated, block: B:508:0x07d1 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:512:0x07dd A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:513:0x07df A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:517:0x07ed A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:518:0x07ef  */
    /* JADX WARN: Code duplicated, block: B:523:0x07ff  */
    /* JADX WARN: Code duplicated, block: B:525:0x080c  */
    /* JADX WARN: Code duplicated, block: B:527:0x0812  */
    /* JADX WARN: Code duplicated, block: B:531:0x0839  */
    /* JADX WARN: Code duplicated, block: B:535:0x0849  */
    /* JADX WARN: Code duplicated, block: B:538:0x086a  */
    /* JADX WARN: Code duplicated, block: B:540:0x0870  */
    /* JADX WARN: Code duplicated, block: B:541:0x0874  */
    /* JADX WARN: Code duplicated, block: B:543:0x087a  */
    /* JADX WARN: Code duplicated, block: B:544:0x0881  */
    /* JADX WARN: Code duplicated, block: B:546:0x088e  */
    /* JADX WARN: Code duplicated, block: B:556:0x08c8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:557:0x08ca  */
    /* JADX WARN: Code duplicated, block: B:573:0x0911  */
    /* JADX WARN: Code duplicated, block: B:575:0x0916  */
    /* JADX WARN: Code duplicated, block: B:580:0x0924  */
    /* JADX WARN: Code duplicated, block: B:581:0x0926  */
    /* JADX WARN: Code duplicated, block: B:583:0x092b  */
    /* JADX WARN: Code duplicated, block: B:593:0x0942  */
    /* JADX WARN: Code duplicated, block: B:596:0x0949  */
    /* JADX WARN: Code duplicated, block: B:598:0x0954  */
    /* JADX WARN: Code duplicated, block: B:600:0x0962  */
    /* JADX WARN: Code duplicated, block: B:601:0x0964  */
    /* JADX WARN: Code duplicated, block: B:611:0x098f  */
    /* JADX WARN: Code duplicated, block: B:613:0x0995  */
    /* JADX WARN: Code duplicated, block: B:614:0x09a8  */
    /* JADX WARN: Code duplicated, block: B:615:0x09aa  */
    /* JADX WARN: Code duplicated, block: B:624:0x09cf  */
    /* JADX WARN: Code duplicated, block: B:626:0x09d5  */
    /* JADX WARN: Code duplicated, block: B:627:0x09e7  */
    /* JADX WARN: Code duplicated, block: B:629:0x09eb  */
    /* JADX WARN: Code duplicated, block: B:631:0x09f1  */
    /* JADX WARN: Code duplicated, block: B:632:0x09f9  */
    /* JADX WARN: Code duplicated, block: B:634:0x09fd  */
    /* JADX WARN: Code duplicated, block: B:63:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:643:0x0a21  */
    /* JADX WARN: Code duplicated, block: B:645:0x0a27  */
    /* JADX WARN: Code duplicated, block: B:646:0x0a39  */
    /* JADX WARN: Code duplicated, block: B:648:0x0a41  */
    /* JADX WARN: Code duplicated, block: B:651:0x0a4c  */
    /* JADX WARN: Code duplicated, block: B:654:0x0a52  */
    /* JADX WARN: Code duplicated, block: B:656:0x0a57 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:659:0x0a5d  */
    /* JADX WARN: Code duplicated, block: B:665:0x0a6a A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:670:0x0a74  */
    /* JADX WARN: Code duplicated, block: B:673:0x0a7b  */
    /* JADX WARN: Code duplicated, block: B:678:0x0aa2  */
    /* JADX WARN: Code duplicated, block: B:685:0x0abc  */
    /* JADX WARN: Code duplicated, block: B:69:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:72:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:75:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:79:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:80:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:83:0x0102  */
    /* JADX WARN: Code duplicated, block: B:85:0x0106  */
    /* JADX WARN: Code duplicated, block: B:88:0x010c  */
    /* JADX WARN: Code duplicated, block: B:90:0x0110  */
    /* JADX WARN: Code duplicated, block: B:92:0x0115  */
    /* JADX WARN: Code duplicated, block: B:94:0x0119  */
    /* JADX WARN: Code duplicated, block: B:96:0x011e  */
    /* JADX WARN: Code duplicated, block: B:97:0x0121  */
    /* JADX WARN: Instruction removed from duplicated block: B:535:0x0849, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:596:0x0949, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
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
    public final void updateVolumeRowH(OplusVolumeRow oplusVolumeRow, boolean z, VolumeDialogController.State state) {
        VolumeDialogController.StreamState streamState;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        int i;
        int i2;
        int i3;
        int i4;
        boolean z8;
        int i5;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        boolean z14;
        boolean z15;
        int i6;
        OplusVolumeSeekBar slider;
        boolean z16;
        COUIVerticalSeekBar slider2;
        int iconRes;
        String str;
        int i7;
        int iconRes2;
        String str2;
        VolumeDialogController.StreamState ss;
        boolean zIsStreamMuted;
        boolean z17;
        int i8;
        String str3;
        VolumeDialogController.StreamState ss2;
        int i9;
        int i10;
        VolumeDialogController.StreamState ss3;
        int i11;
        String str4;
        VolumeDialogController.StreamState ss4;
        VolumeDialogController.StreamState ss5;
        String str5;
        VolumeDialogController.StreamState ss6;
        boolean z18;
        int i12;
        int i13;
        VolumeDialogController.StreamState ss7;
        int i14;
        int i15;
        int i16;
        EffectiveAnimationView icon;
        EffectiveAnimationView icon2;
        EffectiveAnimationView icon3;
        OplusVolumeRow oplusVolumeRowFindRow;
        String str6;
        int i17;
        boolean z19;
        VolumeDialogController.StreamState ss8;
        VolumeDialogController.StreamState ss9;
        int i18;
        int i19;
        int i20;
        EffectiveAnimationView icon4;
        EffectiveAnimationView icon5;
        EffectiveAnimationView icon6;
        boolean z20;
        int i21;
        boolean z21;
        int i22;
        int i23;
        int i24;
        boolean z22;
        EffectiveAnimationView icon7;
        EffectiveAnimationView icon8;
        VolumeDialogController.StreamState ss10;
        boolean z23;
        int i25;
        EffectiveAnimationView icon9;
        int i26;
        VolumeDialogController.StreamState streamState2;
        EffectiveAnimationView icon10;
        int i27;
        int i28;
        boolean z24;
        boolean z25;
        boolean z26;
        VolumeDialogController.StreamState ss11;
        int i29;
        OplusVolumeDialogImpl oplusVolumeDialogImpl;
        EffectiveAnimationView icon11;
        EffectiveAnimationView icon12;
        int vibrateWhenSilentToggleState;
        boolean z27;
        EffectiveAnimationView icon13;
        EffectiveAnimationView icon14;
        EffectiveAnimationView icon15;
        EffectiveAnimationView icon16;
        int i30;
        int i31;
        TextView nameText;
        IconAbility iconAbility = this;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(oplusVolumeRow.getStream(), "updateVolumeRowH s=", "IconAbility-->", "SystemUi--Volume");
        }
        if (state == null || (streamState = (VolumeDialogController.StreamState) state.states.get(oplusVolumeRow.getStream())) == null) {
            return;
        }
        VolumeDialogController.StreamState ss12 = oplusVolumeRow.getSs();
        boolean z28 = ss12 != null && SetsKt__SetsKt.setOf(new Integer[]{3, 11}).contains(Integer.valueOf(oplusVolumeRow.getStream())) && ((ss12.routedToBluetooth ^ streamState.routedToBluetooth) || (ss12.routedToWired ^ streamState.routedToWired));
        oplusVolumeRow.setSs(streamState);
        if (streamState.level == oplusVolumeRow.getRequestedLevel()) {
            oplusVolumeRow.setRequestedLevel(-1);
        }
        boolean z29 = oplusVolumeRow.getStream() == 11;
        boolean z30 = oplusVolumeRow.getStream() == 10;
        boolean z31 = oplusVolumeRow.getStream() == 2;
        boolean z32 = oplusVolumeRow.getStream() == 1;
        boolean z33 = oplusVolumeRow.getStream() == 4;
        boolean z34 = oplusVolumeRow.getStream() == 3;
        boolean z35 = oplusVolumeRow.getStream() == 5;
        if (z31) {
            z2 = z29;
            boolean z36 = state.ringerModeInternal == 1;
            if (z31 || state.ringerModeInternal != 0) {
                z3 = false;
            } else {
                z3 = true;
            }
            z4 = z36;
            if (z35 || state.ringerModeInternal != 0) {
                z5 = false;
            } else {
                z5 = true;
            }
            z6 = z5;
            if (z35) {
                z7 = z33;
                i = 1;
                boolean z37 = state.ringerModeInternal == 1;
                i2 = state.ringerModeInternal;
                if (i2 == i) {
                    i3 = i;
                } else {
                    i3 = 0;
                }
                boolean z38 = z3;
                if (i2 == 0) {
                    i4 = i;
                } else {
                    i4 = 0;
                }
                z8 = z30;
                i5 = state.zenMode;
                if (i5 == i) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                if (i5 == 3) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                if (i5 == 2) {
                    z11 = true;
                } else {
                    z11 = false;
                }
                z12 = state.disallowRinger;
                if (z12 || i2 != 2) {
                    z13 = false;
                } else {
                    z13 = true;
                }
                z14 = z10 ? !(!z11 ? z9 && ((z7 && state.disallowAlarms) || ((z34 && state.disallowMedia) || ((z31 && z12) || ((z32 && state.disallowSystem) || (z35 && z13))))) : z31 || z32 || z7 || z34) : z31 || z32;
                if (oplusVolumeRow.getStream() == 0) {
                    z15 = true;
                } else {
                    z15 = false;
                }
                i6 = streamState.levelMax * 100;
                slider = oplusVolumeRow.getSlider();
                z16 = z15;
                if (slider != null && i6 != slider.getMax() && !oplusVolumeRow.isMultiMediaVolume) {
                    slider.setMax(i6);
                }
                FeatureOption.INSTANCE.getClass();
                if (FeatureOption.isOplusAlertSlider() && (z31 || z35)) {
                    if (state.ringerModeInternal == 2) {
                        streamState.levelMin = 1;
                    } else {
                        streamState.levelMin = 0;
                    }
                }
                Util.setText(oplusVolumeRow.getNameText(), iconAbility.getStreamLabelH(streamState));
                slider2 = oplusVolumeRow.getSlider();
                if (slider2 != null && (nameText = oplusVolumeRow.getNameText()) != null) {
                    slider2.setContentDescription(nameText.getText());
                }
                EffectiveAnimationView icon17 = oplusVolumeRow.getIcon();
                icon17.getClass();
                icon17.setEnabled(!z14);
                iconRes = oplusVolumeRow.getIconRes();
                if (!LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                    String strStreamToString = AudioSystem.streamToString(oplusVolumeRow.getStream());
                    int i32 = streamState.level;
                    boolean z39 = streamState.routedToWired;
                    boolean z40 = streamState.routedToBluetooth;
                    VolumeDialogController.StreamState ss13 = oplusVolumeRow.getSs();
                    Boolean boolValueOf = ss13 != null ? Boolean.valueOf(ss13.muted) : null;
                    int i33 = state.ringerModeInternal;
                    StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i32, "stream:", strStreamToString, ",level:", ",routedTowired:");
                    KeyguardLogger$.ExternalSyntheticOutline0.m(",routedToBlue:", ",row.ss.muted:", sbM, z39, z40);
                    sbM.append(boolValueOf);
                    sbM.append(",ringerModeInternal:");
                    sbM.append(i33);
                    sbM.append(",isZenMuted:");
                    SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("IconAbility-->", "SystemUi--Volume", sbM, z14);
                }
                String str7 = "ic_volume_media";
                str = "";
                if (z31 != 0) {
                    i30 = state.ringerModeInternal;
                    i31 = 2131237438;
                    if (i30 != 0 || z14) {
                        if (oplusVolumeRow.getLastIconRes() == 2131237442) {
                            str2 = "ic_volume_ringer_remove_vibrate";
                        } else {
                            str2 = "ic_volume_ringer_mute";
                        }
                    } else if (i30 == 1 && (isStreamMuted(streamState) || FeatureOption.isOplusAlertSlider())) {
                        str2 = oplusVolumeRow.getLastIconRes() == 2131237438 ? "ic_volume_ringer_add_vibrate" : "ic_volume_ringer_vibrate_mute";
                        i31 = 2131237442;
                    } else {
                        OplusVolumeDialogImpl oplusVolumeDialogImpl2 = iconAbility.volumeInteractor;
                        int i34 = oplusVolumeDialogImpl2.mVibrateAbility.isVibrateWhenRingingOn(oplusVolumeDialogImpl2.mState) ? 2131237440 : 2131237436;
                        i31 = i34;
                        str2 = i34 == 2131237436 ? "ic_volume_ringer_on" : "ic_volume_ringer_vibrate_on";
                    }
                    if (oplusVolumeRow.getLastIconRes() == i31) {
                        str2 = str;
                    }
                    iconRes2 = i31;
                } else {
                    i7 = 2131237460;
                    if (z34 != 0) {
                        ss6 = oplusVolumeRow.getSs();
                        if (ss6 != null) {
                            if (!isStreamMuted(ss6) || z14) {
                                if (streamState.routedToWired) {
                                    str5 = str;
                                } else if (streamState.routedToBluetooth) {
                                    i7 = 2131237411;
                                    str5 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                                } else {
                                    i7 = 2131237422;
                                    str5 = "ic_volume_media";
                                }
                            } else if (streamState.routedToWired) {
                                i7 = 2131237458;
                                str5 = str;
                            } else if (streamState.routedToBluetooth) {
                                i7 = 2131237410;
                                str5 = IC_ANIM_MEDIA_BLUETOOTH;
                            } else {
                                i7 = 2131237420;
                                str5 = "ic_volume_media";
                            }
                            iconRes = i7;
                            str2 = str5;
                        } else {
                            str2 = str;
                        }
                        iconRes2 = iconRes;
                    } else {
                        if (z35 != 0) {
                            ss5 = oplusVolumeRow.getSs();
                            if (ss5 != null) {
                                if (isStreamMuted(ss5)) {
                                    str5 = "ic_volume_notification_mute";
                                    iconRes = oplusVolumeRow.getIconMuteRes();
                                } else {
                                    str5 = "ic_volume_notification_on";
                                }
                                str2 = str5;
                            }
                            iconRes2 = iconRes;
                        } else if (z16) {
                            if (((AudioDataRepository.AudioState) iconAbility.volumeInteractor.mAudioDataRepository.getAudioState().getValue()).isStreamInBluetoothSco) {
                                if (streamState.level > streamState.levelMin) {
                                    i11 = 2131237404;
                                    str4 = "ic_volume_bt_sco_on";
                                } else {
                                    i11 = 2131237405;
                                    str4 = "ic_volume_bt_sco_mute";
                                }
                                ss4 = oplusVolumeRow.getSs();
                                if (ss4 != null) {
                                    ss4.name = 2131956668;
                                }
                                i9 = i11;
                                str2 = str4;
                            } else {
                                ss2 = oplusVolumeRow.getSs();
                                if (ss2 != null) {
                                    if (((AudioDataRepository.AudioState) iconAbility.volumeInteractor.mAudioDataRepository.getAudioState().getValue()).isSpeakerphoneOn) {
                                        i10 = 2131956681;
                                    } else {
                                        i10 = 2131956680;
                                    }
                                    ss2.name = i10;
                                }
                                i9 = 2131237453;
                                str2 = str;
                            }
                            ss3 = oplusVolumeRow.getSs();
                            if (ss3 != null) {
                                Util.setText(oplusVolumeRow.getNameText(), iconAbility.context.getString(ss3.name));
                            }
                            if (iconAbility.volumeInteractor.mOplusVolumeDialogView.isSliderShowing()) {
                                iconAbility.volumeInteractor.getVolumeListAdapter().updateShowingRow(oplusVolumeRow, 100);
                            }
                            iconRes2 = i9;
                        } else if (z7) {
                            str2 = "ic_volume_alarm_multi_app";
                            iconRes2 = iconRes;
                        } else if (z2) {
                            ss = oplusVolumeRow.getSs();
                            if (ss != null) {
                                zIsStreamMuted = isStreamMuted(ss);
                                z17 = streamState.routedToWired;
                                if (zIsStreamMuted) {
                                    if (z17) {
                                        iconRes2 = 2131237460;
                                        str3 = str;
                                    } else if (streamState.routedToBluetooth) {
                                        str3 = str;
                                        iconRes2 = 2131237415;
                                    } else {
                                        if (FeatureOption.isOplusAssistantVolumeSupport()) {
                                            i8 = 2131237398;
                                            str3 = "ic_volume_assistant_mute";
                                        } else {
                                            i8 = 2131237407;
                                            str3 = "ic_volume_gemini_mute";
                                        }
                                        iconRes2 = i8;
                                    }
                                } else if (z17) {
                                    str3 = str;
                                    iconRes2 = 2131237458;
                                } else if (streamState.routedToBluetooth) {
                                    str3 = str;
                                    iconRes2 = 2131237413;
                                } else {
                                    if (FeatureOption.isOplusAssistantVolumeSupport()) {
                                        i8 = 2131237397;
                                        str3 = "ic_volume_assistant_on";
                                    } else {
                                        i8 = 2131237406;
                                        str3 = "ic_volume_gemini_on";
                                    }
                                    iconRes2 = i8;
                                }
                                str2 = str3;
                            }
                        } else if (streamState.dynamic) {
                            if (isStreamMuted(streamState)) {
                                iconRes2 = oplusVolumeRow.getIconMuteRes();
                            } else {
                                iconRes2 = oplusVolumeRow.getIconRes();
                            }
                            str2 = str;
                        }
                        str2 = str;
                        iconRes2 = iconRes;
                    }
                }
                if (iconAbility.volumeInteractor.mOplusVolumeDialogView.getMIsDefaultVolumeTheme()) {
                    if (str2.length() == 0) {
                        streamState = streamState;
                        z14 = z14;
                        iconRes2 = iconRes2;
                        i12 = 1;
                        i13 = 2;
                    } else {
                        if (z34) {
                            boolean zIsSliderShowing = iconAbility.volumeInteractor.mOplusVolumeDialogView.isSliderShowing();
                            oplusVolumeRowFindRow = iconAbility.volumeInteractor.findRow(3);
                            if (oplusVolumeRowFindRow == null) {
                                LogUtil.e$default(8, LogUtil.TAG_VOLUME, TAG, "updateMusicStreamIcon VolumeRow is null!");
                                streamState = streamState;
                                z14 = z14;
                                iconRes2 = iconRes2;
                            } else {
                                if (z14) {
                                    str6 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                                } else {
                                    ss10 = oplusVolumeRowFindRow.getSs();
                                    if (ss10 != null) {
                                        z23 = ss10.muted;
                                        str6 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                                        if (z23) {
                                        }
                                        if (LogUtil.getNormalDebug() || !LogUtil.isTagValid(r6)) {
                                            z19 = z28;
                                        } else {
                                            int lastLevel = oplusVolumeRowFindRow.getLastLevel();
                                            VolumeDialogController.StreamState ss14 = oplusVolumeRowFindRow.getSs();
                                            Boolean boolValueOf2 = ss14 != null ? Boolean.valueOf(ss14.routedToBluetooth) : null;
                                            VolumeDialogController.StreamState ss15 = oplusVolumeRowFindRow.getSs();
                                            Boolean boolValueOf3 = ss15 != null ? Boolean.valueOf(ss15.routedToWired) : null;
                                            StringBuilder sbM2 = FloatList$.ExternalSyntheticOutline0.m("updateMusicStreamIcon current level: ", i17, lastLevel, ", last level: ", ", routedToBluetooth: ");
                                            sbM2.append(boolValueOf2);
                                            sbM2.append(", routedToWired: ");
                                            sbM2.append(boolValueOf3);
                                            sbM2.append(", isDeviceChanged: ");
                                            z19 = z28;
                                            Log.d("SystemUi--Volume", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(", isShowing: ", "IconAbility-->", sbM2, z19, zIsSliderShowing));
                                        }
                                        ss8 = oplusVolumeRowFindRow.getSs();
                                        float f = 1.0f;
                                        if (ss8 == null && ss8.routedToBluetooth) {
                                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(r6)) {
                                                int lastLevel2 = oplusVolumeRowFindRow.getLastLevel();
                                                EffectiveAnimationView icon18 = oplusVolumeRowFindRow.getIcon();
                                                Integer numValueOf = icon18 != null ? Integer.valueOf(icon18.getFrame()) : null;
                                                String jsonRes = oplusVolumeRowFindRow.getJsonRes();
                                                StringBuilder sbM3 = FloatList$.ExternalSyntheticOutline0.m("updateMusicStreamBluetoothIcon level:", i17, lastLevel2, ",lastlevel:", ",frame:");
                                                sbM3.append(numValueOf);
                                                sbM3.append(",jsonRes:");
                                                sbM3.append(jsonRes);
                                                sbM3.append(",isDeviceChanged:");
                                                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("IconAbility-->", "SystemUi--Volume", sbM3, z19);
                                            }
                                            String str8 = i17 == 0 ? str6 : IC_ANIM_MEDIA_BLUETOOTH;
                                            EffectiveAnimationView icon19 = oplusVolumeRowFindRow.getIcon();
                                            if (icon19 != null) {
                                                if (z19 || !Intrinsics.areEqual(oplusVolumeRowFindRow.getJsonRes(), str8)) {
                                                    icon19.cancelAnimation();
                                                    icon19.setAnimation("volumeui/" + str8 + SUFFIX);
                                                    if (zIsSliderShowing) {
                                                        icon19.setSpeed(1.0f);
                                                        icon19.playAnimation();
                                                    }
                                                }
                                                if (!zIsSliderShowing) {
                                                    icon19.setFrame(35);
                                                }
                                            }
                                            oplusVolumeRowFindRow.setJsonRes(str8);
                                            oplusVolumeRowFindRow.setLastLevel(i17);
                                        } else {
                                            ss9 = oplusVolumeRowFindRow.getSs();
                                            if (ss9 != null) {
                                                i18 = ss9.levelMax;
                                            } else {
                                                i18 = 0;
                                            }
                                            float f2 = i18;
                                            i19 = (int) (0.33f * f2);
                                            i20 = (int) (f2 * 0.66f);
                                            if (!z19 || oplusVolumeRowFindRow.getJsonRes().length() == 0) {
                                                i13 = 2;
                                                icon4 = oplusVolumeRowFindRow.getIcon();
                                                if (icon4 != null) {
                                                    icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                                }
                                                oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                                oplusVolumeRowFindRow.setLastLevel(i17);
                                                if (i17 == 0) {
                                                    icon6 = oplusVolumeRowFindRow.getIcon();
                                                    i12 = 1;
                                                    if (icon6 != null) {
                                                        icon6.setFrame(1);
                                                    }
                                                } else {
                                                    i12 = 1;
                                                    if (i17 <= 0 && i17 < i19) {
                                                        EffectiveAnimationView icon20 = oplusVolumeRowFindRow.getIcon();
                                                        if (icon20 != null) {
                                                            icon20.setFrame(36);
                                                        }
                                                    } else if (i17 < i19 && i17 < i20) {
                                                        EffectiveAnimationView icon21 = oplusVolumeRowFindRow.getIcon();
                                                        if (icon21 != null) {
                                                            icon21.setFrame(60);
                                                        }
                                                    } else if (i17 >= i20 && (icon5 = oplusVolumeRowFindRow.getIcon()) != null) {
                                                        icon5.setFrame(83);
                                                    }
                                                }
                                            } else if (i17 != oplusVolumeRowFindRow.getLastLevel() || oplusVolumeRowFindRow.getJsonRes().length() == 0) {
                                                if (i17 == 0) {
                                                    if (oplusVolumeRowFindRow.getLastLevel() > i20) {
                                                        str = "ic_volume_media_grade_3_to_0";
                                                    } else if (oplusVolumeRowFindRow.getLastLevel() > i19) {
                                                        str = "ic_volume_media_grade_2_to_0";
                                                    } else {
                                                        zIsSliderShowing = zIsSliderShowing;
                                                        i22 = 84;
                                                        f = 1.0f;
                                                        z21 = true;
                                                        z22 = false;
                                                        i23 = 84;
                                                        i24 = 120;
                                                        str = str;
                                                    }
                                                    zIsSliderShowing = zIsSliderShowing;
                                                    f = 1.0f;
                                                    i22 = 0;
                                                    i24 = 83;
                                                    i23 = 0;
                                                    z21 = false;
                                                    z22 = false;
                                                } else if (i17 <= i19) {
                                                    if (oplusVolumeRowFindRow.getLastLevel() == 0) {
                                                        f = 1.0f;
                                                        i24 = 36;
                                                        i22 = 0;
                                                        i23 = 0;
                                                    } else if (oplusVolumeRowFindRow.getLastLevel() > i20) {
                                                        f = -4.0f;
                                                        i23 = 36;
                                                        i22 = 83;
                                                        i24 = 83;
                                                    } else if (oplusVolumeRowFindRow.getLastLevel() > i19) {
                                                        i23 = 36;
                                                        i22 = 60;
                                                        i24 = 60;
                                                        z21 = true;
                                                        f = -1.0f;
                                                        z22 = false;
                                                    } else {
                                                        i22 = 36;
                                                        i24 = 83;
                                                        i23 = 0;
                                                        z21 = false;
                                                        z22 = true;
                                                    }
                                                    z21 = true;
                                                    z22 = false;
                                                } else {
                                                    if (i17 > i20) {
                                                        if (oplusVolumeRowFindRow.getLastLevel() == 0) {
                                                            str = "ic_volume_media_grade_0_to_3";
                                                            f = 1.0f;
                                                        } else {
                                                            if (oplusVolumeRowFindRow.getLastLevel() <= i19) {
                                                                f = 4.0f;
                                                                i21 = 36;
                                                                str = str;
                                                                z20 = true;
                                                            } else {
                                                                f = 1.0f;
                                                                if (oplusVolumeRowFindRow.getLastLevel() <= i20) {
                                                                    str = str;
                                                                    z20 = true;
                                                                    i21 = 60;
                                                                }
                                                            }
                                                            zIsSliderShowing = zIsSliderShowing;
                                                            z21 = z20;
                                                            i22 = i21;
                                                            i23 = i22;
                                                            i24 = 83;
                                                        }
                                                        str = str;
                                                        z20 = false;
                                                        i21 = 0;
                                                        zIsSliderShowing = zIsSliderShowing;
                                                        z21 = z20;
                                                        i22 = i21;
                                                        i23 = i22;
                                                        i24 = 83;
                                                    } else if (oplusVolumeRowFindRow.getLastLevel() == 0) {
                                                        str = "ic_volume_media_grade_0_to_2";
                                                        zIsSliderShowing = zIsSliderShowing;
                                                        f = 1.0f;
                                                        i22 = 0;
                                                        i24 = 83;
                                                        i23 = 0;
                                                        z21 = false;
                                                    } else if (oplusVolumeRowFindRow.getLastLevel() <= i19) {
                                                        f = 1.0f;
                                                        i22 = 36;
                                                        i23 = 36;
                                                        i24 = 60;
                                                        z21 = true;
                                                    } else if (oplusVolumeRowFindRow.getLastLevel() > i20) {
                                                        i22 = 83;
                                                        i24 = 83;
                                                        i23 = 60;
                                                        z21 = true;
                                                        f = -1.0f;
                                                    } else {
                                                        i22 = 60;
                                                        i24 = 83;
                                                        i23 = 0;
                                                        z21 = false;
                                                        z22 = true;
                                                    }
                                                    z22 = false;
                                                }
                                                if (str.length() == 0) {
                                                    if (z21) {
                                                        EffectiveAnimationView icon22 = oplusVolumeRowFindRow.getIcon();
                                                        if (icon22 == null || !icon22.isAnimating() || !Intrinsics.areEqual(oplusVolumeRowFindRow.getJsonRes(), "ic_volume_media") || i17 <= 0 || oplusVolumeRowFindRow.getLastLevel() == 0) {
                                                            i13 = 2;
                                                        } else {
                                                            int i35 = f > 0.0f ? i24 : i23;
                                                            int frame = icon22.getFrame();
                                                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(r6)) {
                                                                Log.d("SystemUi--Volume", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("current frame: ", frame, i35, ", target frame: ", "IconAbility-->"));
                                                            }
                                                            if (i35 > frame) {
                                                                icon22.setMaxFrame(i35);
                                                                i13 = 2;
                                                                icon22.setSpeed(((int) ((i35 - frame) / 30.0f)) + 2);
                                                            } else {
                                                                i13 = 2;
                                                                icon22.setMinFrame(i35);
                                                                icon22.setSpeed(((int) ((i35 - frame) / 30.0f)) - 2);
                                                            }
                                                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(r6)) {
                                                                OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("re-set music icon animation speed: ", icon22.getSpeed(), "IconAbility-->", "SystemUi--Volume");
                                                            }
                                                            z21 = false;
                                                        }
                                                        if (z21) {
                                                            if (!Intrinsics.areEqual(oplusVolumeRowFindRow.getJsonRes(), "ic_volume_media") && (icon8 = oplusVolumeRowFindRow.getIcon()) != null) {
                                                                icon8.setAnimation("volumeui/ic_volume_media_light.json");
                                                            }
                                                            EffectiveAnimationView icon23 = oplusVolumeRowFindRow.getIcon();
                                                            if (icon23 != null) {
                                                                icon23.setMinAndMaxFrame(i23, i24);
                                                            }
                                                            if (zIsSliderShowing) {
                                                                EffectiveAnimationView icon24 = oplusVolumeRowFindRow.getIcon();
                                                                if (icon24 != null) {
                                                                    icon24.setSpeed(f);
                                                                }
                                                                EffectiveAnimationView icon25 = oplusVolumeRowFindRow.getIcon();
                                                                if (icon25 != null) {
                                                                    icon25.playAnimation();
                                                                }
                                                            }
                                                            EffectiveAnimationView icon26 = oplusVolumeRowFindRow.getIcon();
                                                            if (icon26 != null) {
                                                                if (zIsSliderShowing) {
                                                                    i24 = i22;
                                                                }
                                                                icon26.setFrame(i24);
                                                            }
                                                        }
                                                    } else {
                                                        i13 = 2;
                                                    }
                                                    if (!zIsSliderShowing && z22 && (icon7 = oplusVolumeRowFindRow.getIcon()) != null) {
                                                        icon7.setFrame(i22);
                                                    }
                                                    if (str7.length() != 0) {
                                                        oplusVolumeRowFindRow.setJsonRes(str7);
                                                    }
                                                    oplusVolumeRowFindRow.setLastLevel(i17);
                                                    i12 = 1;
                                                } else {
                                                    i13 = 2;
                                                    EffectiveAnimationView icon27 = oplusVolumeRowFindRow.getIcon();
                                                    if (icon27 != null) {
                                                        icon27.cancelAnimation();
                                                        icon27.setAnimation("volumeui/" + ((Object) str) + LIGHT_SUFFIX);
                                                        if (zIsSliderShowing) {
                                                            icon27.setSpeed(1.0f);
                                                            icon27.playAnimation();
                                                        } else {
                                                            icon27.setFrame(35);
                                                        }
                                                    }
                                                }
                                                str7 = str;
                                                if (!zIsSliderShowing) {
                                                    icon7.setFrame(i22);
                                                }
                                                if (str7.length() != 0) {
                                                    oplusVolumeRowFindRow.setJsonRes(str7);
                                                }
                                                oplusVolumeRowFindRow.setLastLevel(i17);
                                                i12 = 1;
                                            }
                                        }
                                    } else {
                                        str6 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                                    }
                                    VolumeDialogController.StreamState ss16 = oplusVolumeRowFindRow.getSs();
                                    i17 = ss16 != null ? ss16.level : 0;
                                    if (LogUtil.getNormalDebug()) {
                                        z19 = z28;
                                    } else {
                                        z19 = z28;
                                    }
                                    ss8 = oplusVolumeRowFindRow.getSs();
                                    float f3 = 1.0f;
                                    if (ss8 == null) {
                                        ss9 = oplusVolumeRowFindRow.getSs();
                                        if (ss9 != null) {
                                            i18 = ss9.levelMax;
                                        } else {
                                            i18 = 0;
                                        }
                                        float f4 = i18;
                                        i19 = (int) (0.33f * f4);
                                        i20 = (int) (f4 * 0.66f);
                                        if (z19) {
                                            i13 = 2;
                                            icon4 = oplusVolumeRowFindRow.getIcon();
                                            if (icon4 != null) {
                                                icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                            }
                                            oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                            oplusVolumeRowFindRow.setLastLevel(i17);
                                            if (i17 == 0) {
                                                icon6 = oplusVolumeRowFindRow.getIcon();
                                                i12 = 1;
                                                if (icon6 != null) {
                                                    icon6.setFrame(1);
                                                }
                                            } else {
                                                i12 = 1;
                                                if (i17 <= 0) {
                                                    if (i17 < i19) {
                                                        if (i17 >= i20) {
                                                            icon5.setFrame(83);
                                                        }
                                                    } else if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            }
                                        } else {
                                            i13 = 2;
                                            icon4 = oplusVolumeRowFindRow.getIcon();
                                            if (icon4 != null) {
                                                icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                            }
                                            oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                            oplusVolumeRowFindRow.setLastLevel(i17);
                                            if (i17 == 0) {
                                                icon6 = oplusVolumeRowFindRow.getIcon();
                                                i12 = 1;
                                                if (icon6 != null) {
                                                    icon6.setFrame(1);
                                                }
                                            } else {
                                                i12 = 1;
                                                if (i17 <= 0) {
                                                    if (i17 < i19) {
                                                        if (i17 >= i20) {
                                                            icon5.setFrame(83);
                                                        }
                                                    } else if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            }
                                        }
                                    } else {
                                        ss9 = oplusVolumeRowFindRow.getSs();
                                        if (ss9 != null) {
                                            i18 = ss9.levelMax;
                                        } else {
                                            i18 = 0;
                                        }
                                        float f5 = i18;
                                        i19 = (int) (0.33f * f5);
                                        i20 = (int) (f5 * 0.66f);
                                        if (z19) {
                                            i13 = 2;
                                            icon4 = oplusVolumeRowFindRow.getIcon();
                                            if (icon4 != null) {
                                                icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                            }
                                            oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                            oplusVolumeRowFindRow.setLastLevel(i17);
                                            if (i17 == 0) {
                                                icon6 = oplusVolumeRowFindRow.getIcon();
                                                i12 = 1;
                                                if (icon6 != null) {
                                                    icon6.setFrame(1);
                                                }
                                            } else {
                                                i12 = 1;
                                                if (i17 <= 0) {
                                                    if (i17 < i19) {
                                                        if (i17 >= i20) {
                                                            icon5.setFrame(83);
                                                        }
                                                    } else if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            }
                                        } else {
                                            i13 = 2;
                                            icon4 = oplusVolumeRowFindRow.getIcon();
                                            if (icon4 != null) {
                                                icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                            }
                                            oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                            oplusVolumeRowFindRow.setLastLevel(i17);
                                            if (i17 == 0) {
                                                icon6 = oplusVolumeRowFindRow.getIcon();
                                                i12 = 1;
                                                if (icon6 != null) {
                                                    icon6.setFrame(1);
                                                }
                                            } else {
                                                i12 = 1;
                                                if (i17 <= 0) {
                                                    if (i17 < i19) {
                                                        if (i17 >= i20) {
                                                            icon5.setFrame(83);
                                                        }
                                                    } else if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            }
                                        }
                                    }
                                }
                                if (LogUtil.getNormalDebug()) {
                                    z19 = z28;
                                } else {
                                    z19 = z28;
                                }
                                ss8 = oplusVolumeRowFindRow.getSs();
                                float f6 = 1.0f;
                                if (ss8 == null) {
                                    ss9 = oplusVolumeRowFindRow.getSs();
                                    if (ss9 != null) {
                                        i18 = ss9.levelMax;
                                    } else {
                                        i18 = 0;
                                    }
                                    float f7 = i18;
                                    i19 = (int) (0.33f * f7);
                                    i20 = (int) (f7 * 0.66f);
                                    if (z19) {
                                        i13 = 2;
                                        icon4 = oplusVolumeRowFindRow.getIcon();
                                        if (icon4 != null) {
                                            icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                        }
                                        oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                        oplusVolumeRowFindRow.setLastLevel(i17);
                                        if (i17 == 0) {
                                            icon6 = oplusVolumeRowFindRow.getIcon();
                                            i12 = 1;
                                            if (icon6 != null) {
                                                icon6.setFrame(1);
                                            }
                                        } else {
                                            i12 = 1;
                                            if (i17 <= 0) {
                                                if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        }
                                    } else {
                                        i13 = 2;
                                        icon4 = oplusVolumeRowFindRow.getIcon();
                                        if (icon4 != null) {
                                            icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                        }
                                        oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                        oplusVolumeRowFindRow.setLastLevel(i17);
                                        if (i17 == 0) {
                                            icon6 = oplusVolumeRowFindRow.getIcon();
                                            i12 = 1;
                                            if (icon6 != null) {
                                                icon6.setFrame(1);
                                            }
                                        } else {
                                            i12 = 1;
                                            if (i17 <= 0) {
                                                if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        }
                                    }
                                } else {
                                    ss9 = oplusVolumeRowFindRow.getSs();
                                    if (ss9 != null) {
                                        i18 = ss9.levelMax;
                                    } else {
                                        i18 = 0;
                                    }
                                    float f8 = i18;
                                    i19 = (int) (0.33f * f8);
                                    i20 = (int) (f8 * 0.66f);
                                    if (z19) {
                                        i13 = 2;
                                        icon4 = oplusVolumeRowFindRow.getIcon();
                                        if (icon4 != null) {
                                            icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                        }
                                        oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                        oplusVolumeRowFindRow.setLastLevel(i17);
                                        if (i17 == 0) {
                                            icon6 = oplusVolumeRowFindRow.getIcon();
                                            i12 = 1;
                                            if (icon6 != null) {
                                                icon6.setFrame(1);
                                            }
                                        } else {
                                            i12 = 1;
                                            if (i17 <= 0) {
                                                if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        }
                                    } else {
                                        i13 = 2;
                                        icon4 = oplusVolumeRowFindRow.getIcon();
                                        if (icon4 != null) {
                                            icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                        }
                                        oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                        oplusVolumeRowFindRow.setLastLevel(i17);
                                        if (i17 == 0) {
                                            icon6 = oplusVolumeRowFindRow.getIcon();
                                            i12 = 1;
                                            if (icon6 != null) {
                                                icon6.setFrame(1);
                                            }
                                        } else {
                                            i12 = 1;
                                            if (i17 <= 0) {
                                                if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        }
                                    }
                                }
                            }
                            i12 = 1;
                            i13 = 2;
                        } else {
                            streamState = streamState;
                            z14 = z14;
                            iconRes2 = iconRes2;
                            z18 = z28;
                            i12 = 1;
                            i13 = 2;
                            if (z2) {
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(r6)) {
                                    String jsonRes2 = oplusVolumeRow.getJsonRes();
                                    StringBuilder sb = new StringBuilder("updateAssistantStreamIcon jsonRes: ");
                                    sb.append((Object) str2);
                                    sb.append(",row.jsonRes: ");
                                    sb.append(jsonRes2);
                                    sb.append(" isDeviceChanged: ");
                                    SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("IconAbility-->", "SystemUi--Volume", sb, z18);
                                }
                                if (!z18 || !str2.equals(oplusVolumeRow.getJsonRes())) {
                                    icon = oplusVolumeRow.getIcon();
                                    if (icon != null) {
                                        icon.setAnimation("volumeui/" + ((Object) str2) + LIGHT_SUFFIX);
                                    }
                                    oplusVolumeRow.setJsonRes(str2);
                                    iconAbility = this;
                                    if (iconAbility.volumeInteractor.mOplusVolumeDialogView.isSliderShowing()) {
                                        icon3 = oplusVolumeRow.getIcon();
                                        if (icon3 != null) {
                                            icon3.playAnimation();
                                        }
                                    } else {
                                        icon2 = oplusVolumeRow.getIcon();
                                        if (icon2 != null) {
                                            icon2.setFrame(35);
                                        }
                                    }
                                }
                            } else {
                                iconAbility = this;
                                FlavorOneFeatureOption.INSTANCE.getClass();
                                if (!FlavorOneFeatureOption.isFlavorOneDevice() || str2.equals(oplusVolumeRow.getJsonRes())) {
                                    if (!z7 && (ss7 = oplusVolumeRow.getSs()) != null) {
                                        if (ss7.level != ss7.levelMin || oplusVolumeRow.getLastAudibleLevel() == ss7.levelMin) {
                                            i14 = 0;
                                        } else {
                                            EffectiveAnimationView icon28 = oplusVolumeRow.getIcon();
                                            if (icon28 != null) {
                                                icon28.setAnimation("volumeui/" + ((Object) str2) + LIGHT_SUFFIX);
                                                oplusVolumeRow.setJsonRes(str2);
                                                icon28.setFrame(0);
                                                icon28.playAnimation();
                                                i16 = 1;
                                            } else {
                                                i16 = 0;
                                            }
                                            i14 = i16;
                                        }
                                        i15 = i14;
                                    }
                                    iconRes2 = iconRes2;
                                    if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                                        oplusVolumeRow.setLastIconRes(iconRes2);
                                        if (i15 == 0 && (icon9 = oplusVolumeRow.getIcon()) != null) {
                                            icon9.setImageResource(iconRes2);
                                        }
                                    }
                                    i25 = 2131237442;
                                } else {
                                    oplusVolumeRow.setJsonRes(str2);
                                    if (iconAbility.volumeInteractor.mOplusVolumeDialogView.isSliderShowing()) {
                                        EffectiveAnimationView icon29 = oplusVolumeRow.getIcon();
                                        if (icon29 != null) {
                                            icon29.setAnimation("volumeui/" + ((Object) str2) + LIGHT_SUFFIX);
                                        }
                                        EffectiveAnimationView icon30 = oplusVolumeRow.getIcon();
                                        if (icon30 != null) {
                                            icon30.playAnimation();
                                        }
                                    }
                                    iconRes2 = iconRes2;
                                    if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                                        oplusVolumeRow.setLastIconRes(iconRes2);
                                        if (i15 == 0) {
                                            icon9.setImageResource(iconRes2);
                                        }
                                    }
                                    i25 = 2131237442;
                                }
                            }
                            i15 = i12;
                            iconRes2 = iconRes2;
                            if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                                oplusVolumeRow.setLastIconRes(iconRes2);
                                if (i15 == 0) {
                                    icon9.setImageResource(iconRes2);
                                }
                            }
                            i25 = 2131237442;
                        }
                        iconAbility = this;
                        i15 = i12;
                        iconRes2 = iconRes2;
                        if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                            oplusVolumeRow.setLastIconRes(iconRes2);
                            if (i15 == 0) {
                                icon9.setImageResource(iconRes2);
                            }
                        }
                        i25 = 2131237442;
                    }
                    i15 = 0;
                    iconRes2 = iconRes2;
                    if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                        oplusVolumeRow.setLastIconRes(iconRes2);
                        if (i15 == 0) {
                            icon9.setImageResource(iconRes2);
                        }
                    }
                    i25 = 2131237442;
                } else {
                    if (oplusVolumeRow.getJsonRes().length() > 0) {
                        oplusVolumeRow.setJsonRes(str);
                        icon16 = oplusVolumeRow.getIcon();
                        if (icon16 != null) {
                            icon16.clearAnimation();
                        }
                    }
                    icon15 = oplusVolumeRow.getIcon();
                    if (icon15 != null) {
                        icon15.setImageResource(iconRes2);
                    }
                    streamState = streamState;
                    z14 = z14;
                    i25 = 2131237442;
                    i12 = 1;
                    i13 = 2;
                }
                if (iconRes2 == i25) {
                    i26 = 3;
                } else if (iconRes2 != 2131237415 || iconRes2 == oplusVolumeRow.getIconMuteRes()) {
                    i26 = i13;
                } else {
                    i26 = (iconRes2 == 2131237413 || iconRes2 == oplusVolumeRow.getIconRes()) ? i12 : 0;
                }
                oplusVolumeRow.setIconState(i26);
                if (z14) {
                    streamState2 = streamState;
                    icon10 = oplusVolumeRow.getIcon();
                    if (icon10 != null) {
                        icon10.setContentDescription(iconAbility.getStreamLabelH(streamState2));
                    }
                } else {
                    if (z31) {
                        vibrateWhenSilentToggleState = VolumeSettingsValueProxy.Companion.getVibrateWhenSilentToggleState(iconAbility.context);
                        streamState2 = streamState;
                        z27 = streamState2.muted;
                        if (vibrateWhenSilentToggleState != 0) {
                            if (!z27 || streamState2.level == 0) {
                                icon14 = oplusVolumeRow.getIcon();
                                if (icon14 != null) {
                                    icon14.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                                }
                            } else {
                                EffectiveAnimationView icon31 = oplusVolumeRow.getIcon();
                                if (icon31 != null) {
                                    icon31.setContentDescription(iconAbility.context.getString(iconAbility.volumeInteractor.mShowA11yStream ? 2131957224 : 2131957223, iconAbility.getStreamLabelH(streamState2)));
                                }
                            }
                        } else if (!z27 || streamState2.level == 0) {
                            icon13 = oplusVolumeRow.getIcon();
                            if (icon13 != null) {
                                icon13.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                            }
                        } else {
                            EffectiveAnimationView icon32 = oplusVolumeRow.getIcon();
                            if (icon32 != null) {
                                icon32.setContentDescription(iconAbility.context.getString(iconAbility.volumeInteractor.mShowA11yStream ? 2131957221 : 2131957220, iconAbility.getStreamLabelH(streamState2)));
                            }
                        }
                    } else {
                        streamState2 = streamState;
                        if (z8) {
                            icon12 = oplusVolumeRow.getIcon();
                            if (icon12 != null) {
                                icon12.setContentDescription(iconAbility.getStreamLabelH(streamState2));
                            }
                        } else if (!streamState2.muted || streamState2.level == 0) {
                            icon11 = oplusVolumeRow.getIcon();
                            if (icon11 != null) {
                                icon11.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                            }
                        } else {
                            EffectiveAnimationView icon33 = oplusVolumeRow.getIcon();
                            if (icon33 != null) {
                                icon33.setContentDescription(iconAbility.context.getString(iconAbility.volumeInteractor.mShowA11yStream ? 2131957221 : 2131957220, iconAbility.getStreamLabelH(streamState2)));
                            }
                        }
                    }
                }
                i27 = streamState2.level;
                if (i27 > 0) {
                    oplusVolumeRow.setLastAudibleLevel(i27);
                }
                i28 = 0;
                if (z14) {
                    oplusVolumeRow.setTracking(false);
                }
                z24 = ((!z38 || z4 || z6 || z37) && FeatureOption.isOplusAlertSlider()) ? i12 : 0;
                z25 = i3;
                if (!z14 || ((z35 && !(z25 == 0 && i4 == 0)) || z24 != 0)) {
                    z26 = 0;
                } else {
                    z26 = i12;
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(r6)) {
                    StringBuilder sbM4 = TextFieldImplKt$.ExternalSyntheticOutline0.m("zenMuted: ", ", isNotificationStream: ", ", isInternalVibrate: ", z14, z35);
                    KeyguardLogger$.ExternalSyntheticOutline0.m(", isInternalSilent: ", ", isOplusThreekeysSilentOrVibrate: ", sbM4, z25, i4);
                    SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("IconAbility-->", "SystemUi--Volume", sbM4, z24);
                }
                ss11 = oplusVolumeRow.getSs();
                if (ss11 != null && !ss11.muted && z24 == 0) {
                    i28 = ss11.level;
                }
                i29 = i28;
                oplusVolumeDialogImpl = iconAbility.volumeInteractor;
                oplusVolumeDialogImpl.getClass();
                VolumeFeatureOption.INSTANCE.getClass();
                if (VolumeFeatureOption.isOplusSuperVolume() || oplusVolumeRow.getStream() != 0 || i29 != oplusVolumeRow.getSs().levelMax || oplusVolumeRow.getSlider().getProgress() != oplusVolumeRow.getSlider().getMax() || oplusVolumeDialogImpl.mAudioManager == null || oplusVolumeDialogImpl.mSuperVolumeRepository == null) {
                    oplusVolumeDialogImpl.updateVolumeRowSliderH(oplusVolumeRow, z26, i29, 0, z);
                }
                Executor executor = (Executor) Dependency.sDependency.getDependencyInner(Dependency.BACKGROUND_EXECUTOR);
                OplusVolumeDialogImpl$.ExternalSyntheticLambda6 externalSyntheticLambda6 = new OplusVolumeDialogImpl$.ExternalSyntheticLambda6();
                externalSyntheticLambda6.f$0 = oplusVolumeDialogImpl;
                externalSyntheticLambda6.f$1 = oplusVolumeRow;
                externalSyntheticLambda6.f$2 = z26;
                externalSyntheticLambda6.f$3 = i29;
                externalSyntheticLambda6.f$4 = z;
                VarHandle.storeStoreFence();
                executor.execute(externalSyntheticLambda6);
                return;
            }
            z7 = z33;
            i = 1;
            i2 = state.ringerModeInternal;
            if (i2 == i) {
                i3 = i;
            } else {
                i3 = 0;
            }
            boolean z310 = z3;
            if (i2 == 0) {
                i4 = i;
            } else {
                i4 = 0;
            }
            z8 = z30;
            i5 = state.zenMode;
            if (i5 == i) {
                z9 = true;
            } else {
                z9 = false;
            }
            if (i5 == 3) {
                z10 = true;
            } else {
                z10 = false;
            }
            if (i5 == 2) {
                z11 = true;
            } else {
                z11 = false;
            }
            z12 = state.disallowRinger;
            if (z12) {
                z13 = false;
            } else {
                z13 = false;
            }
            if (z10) {
            }
            if (oplusVolumeRow.getStream() == 0) {
                z15 = true;
            } else {
                z15 = false;
            }
            i6 = streamState.levelMax * 100;
            slider = oplusVolumeRow.getSlider();
            z16 = z15;
            if (slider != null) {
                slider.setMax(i6);
            }
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isOplusAlertSlider()) {
                if (state.ringerModeInternal == 2) {
                    streamState.levelMin = 1;
                } else {
                    streamState.levelMin = 0;
                }
            }
            Util.setText(oplusVolumeRow.getNameText(), iconAbility.getStreamLabelH(streamState));
            slider2 = oplusVolumeRow.getSlider();
            if (slider2 != null) {
                slider2.setContentDescription(nameText.getText());
            }
            EffectiveAnimationView icon110 = oplusVolumeRow.getIcon();
            icon110.getClass();
            icon110.setEnabled(!z14);
            iconRes = oplusVolumeRow.getIconRes();
            if (!LogUtil.getNormalDebug()) {
            }
            String str9 = "ic_volume_media";
            str = "";
            if (z31 != 0) {
                i30 = state.ringerModeInternal;
                i31 = 2131237438;
                if (i30 != 0) {
                    if (oplusVolumeRow.getLastIconRes() == 2131237442) {
                        str2 = "ic_volume_ringer_remove_vibrate";
                    } else {
                        str2 = "ic_volume_ringer_mute";
                    }
                } else if (oplusVolumeRow.getLastIconRes() == 2131237442) {
                    str2 = "ic_volume_ringer_remove_vibrate";
                } else {
                    str2 = "ic_volume_ringer_mute";
                }
                if (oplusVolumeRow.getLastIconRes() == i31) {
                    str2 = str;
                }
                iconRes2 = i31;
            } else {
                i7 = 2131237460;
                if (z34 != 0) {
                    ss6 = oplusVolumeRow.getSs();
                    if (ss6 != null) {
                        if (isStreamMuted(ss6)) {
                            if (streamState.routedToWired) {
                                str5 = str;
                            } else if (streamState.routedToBluetooth) {
                                i7 = 2131237411;
                                str5 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                            } else {
                                i7 = 2131237422;
                                str5 = "ic_volume_media";
                            }
                        } else if (streamState.routedToWired) {
                            str5 = str;
                        } else if (streamState.routedToBluetooth) {
                            i7 = 2131237411;
                            str5 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                        } else {
                            i7 = 2131237422;
                            str5 = "ic_volume_media";
                        }
                        iconRes = i7;
                        str2 = str5;
                    } else {
                        str2 = str;
                    }
                    iconRes2 = iconRes;
                } else {
                    if (z35 != 0) {
                        ss5 = oplusVolumeRow.getSs();
                        if (ss5 != null) {
                            if (isStreamMuted(ss5)) {
                                str5 = "ic_volume_notification_mute";
                                iconRes = oplusVolumeRow.getIconMuteRes();
                            } else {
                                str5 = "ic_volume_notification_on";
                            }
                            str2 = str5;
                        }
                        iconRes2 = iconRes;
                    } else if (z16) {
                        if (((AudioDataRepository.AudioState) iconAbility.volumeInteractor.mAudioDataRepository.getAudioState().getValue()).isStreamInBluetoothSco) {
                            if (streamState.level > streamState.levelMin) {
                                i11 = 2131237404;
                                str4 = "ic_volume_bt_sco_on";
                            } else {
                                i11 = 2131237405;
                                str4 = "ic_volume_bt_sco_mute";
                            }
                            ss4 = oplusVolumeRow.getSs();
                            if (ss4 != null) {
                                ss4.name = 2131956668;
                            }
                            i9 = i11;
                            str2 = str4;
                        } else {
                            ss2 = oplusVolumeRow.getSs();
                            if (ss2 != null) {
                                if (((AudioDataRepository.AudioState) iconAbility.volumeInteractor.mAudioDataRepository.getAudioState().getValue()).isSpeakerphoneOn) {
                                    i10 = 2131956681;
                                } else {
                                    i10 = 2131956680;
                                }
                                ss2.name = i10;
                            }
                            i9 = 2131237453;
                            str2 = str;
                        }
                        ss3 = oplusVolumeRow.getSs();
                        if (ss3 != null) {
                            Util.setText(oplusVolumeRow.getNameText(), iconAbility.context.getString(ss3.name));
                        }
                        if (iconAbility.volumeInteractor.mOplusVolumeDialogView.isSliderShowing()) {
                            iconAbility.volumeInteractor.getVolumeListAdapter().updateShowingRow(oplusVolumeRow, 100);
                        }
                        iconRes2 = i9;
                    } else if (z7) {
                        str2 = "ic_volume_alarm_multi_app";
                        iconRes2 = iconRes;
                    } else if (z2) {
                        ss = oplusVolumeRow.getSs();
                        if (ss != null) {
                            zIsStreamMuted = isStreamMuted(ss);
                            z17 = streamState.routedToWired;
                            if (zIsStreamMuted) {
                                if (z17) {
                                    iconRes2 = 2131237460;
                                    str3 = str;
                                } else if (streamState.routedToBluetooth) {
                                    str3 = str;
                                    iconRes2 = 2131237415;
                                } else {
                                    if (FeatureOption.isOplusAssistantVolumeSupport()) {
                                        i8 = 2131237398;
                                        str3 = "ic_volume_assistant_mute";
                                    } else {
                                        i8 = 2131237407;
                                        str3 = "ic_volume_gemini_mute";
                                    }
                                    iconRes2 = i8;
                                }
                            } else if (z17) {
                                str3 = str;
                                iconRes2 = 2131237458;
                            } else if (streamState.routedToBluetooth) {
                                str3 = str;
                                iconRes2 = 2131237413;
                            } else {
                                if (FeatureOption.isOplusAssistantVolumeSupport()) {
                                    i8 = 2131237397;
                                    str3 = "ic_volume_assistant_on";
                                } else {
                                    i8 = 2131237406;
                                    str3 = "ic_volume_gemini_on";
                                }
                                iconRes2 = i8;
                            }
                            str2 = str3;
                        }
                    } else if (streamState.dynamic) {
                        if (isStreamMuted(streamState)) {
                            iconRes2 = oplusVolumeRow.getIconMuteRes();
                        } else {
                            iconRes2 = oplusVolumeRow.getIconRes();
                        }
                        str2 = str;
                    }
                    str2 = str;
                    iconRes2 = iconRes;
                }
            }
            if (iconAbility.volumeInteractor.mOplusVolumeDialogView.getMIsDefaultVolumeTheme()) {
                if (oplusVolumeRow.getJsonRes().length() > 0) {
                    oplusVolumeRow.setJsonRes(str);
                    icon16 = oplusVolumeRow.getIcon();
                    if (icon16 != null) {
                        icon16.clearAnimation();
                    }
                }
                icon15 = oplusVolumeRow.getIcon();
                if (icon15 != null) {
                    icon15.setImageResource(iconRes2);
                }
                streamState = streamState;
                z14 = z14;
                i25 = 2131237442;
                i12 = 1;
                i13 = 2;
            } else {
                if (str2.length() == 0) {
                    streamState = streamState;
                    z14 = z14;
                    iconRes2 = iconRes2;
                    i12 = 1;
                    i13 = 2;
                } else {
                    if (z34) {
                        boolean zIsSliderShowing2 = iconAbility.volumeInteractor.mOplusVolumeDialogView.isSliderShowing();
                        oplusVolumeRowFindRow = iconAbility.volumeInteractor.findRow(3);
                        if (oplusVolumeRowFindRow == null) {
                            LogUtil.e$default(8, LogUtil.TAG_VOLUME, TAG, "updateMusicStreamIcon VolumeRow is null!");
                            streamState = streamState;
                            z14 = z14;
                            iconRes2 = iconRes2;
                        } else {
                            if (z14) {
                                ss10 = oplusVolumeRowFindRow.getSs();
                                if (ss10 != null) {
                                    z23 = ss10.muted;
                                    str6 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                                    if (z23) {
                                    }
                                    if (LogUtil.getNormalDebug()) {
                                        z19 = z28;
                                    } else {
                                        z19 = z28;
                                    }
                                    ss8 = oplusVolumeRowFindRow.getSs();
                                    float f9 = 1.0f;
                                    if (ss8 == null) {
                                        ss9 = oplusVolumeRowFindRow.getSs();
                                        if (ss9 != null) {
                                            i18 = ss9.levelMax;
                                        } else {
                                            i18 = 0;
                                        }
                                        float f10 = i18;
                                        i19 = (int) (0.33f * f10);
                                        i20 = (int) (f10 * 0.66f);
                                        if (z19) {
                                            i13 = 2;
                                            icon4 = oplusVolumeRowFindRow.getIcon();
                                            if (icon4 != null) {
                                                icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                            }
                                            oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                            oplusVolumeRowFindRow.setLastLevel(i17);
                                            if (i17 == 0) {
                                                icon6 = oplusVolumeRowFindRow.getIcon();
                                                i12 = 1;
                                                if (icon6 != null) {
                                                    icon6.setFrame(1);
                                                }
                                            } else {
                                                i12 = 1;
                                                if (i17 <= 0) {
                                                    if (i17 < i19) {
                                                        if (i17 >= i20) {
                                                            icon5.setFrame(83);
                                                        }
                                                    } else if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            }
                                        } else {
                                            i13 = 2;
                                            icon4 = oplusVolumeRowFindRow.getIcon();
                                            if (icon4 != null) {
                                                icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                            }
                                            oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                            oplusVolumeRowFindRow.setLastLevel(i17);
                                            if (i17 == 0) {
                                                icon6 = oplusVolumeRowFindRow.getIcon();
                                                i12 = 1;
                                                if (icon6 != null) {
                                                    icon6.setFrame(1);
                                                }
                                            } else {
                                                i12 = 1;
                                                if (i17 <= 0) {
                                                    if (i17 < i19) {
                                                        if (i17 >= i20) {
                                                            icon5.setFrame(83);
                                                        }
                                                    } else if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            }
                                        }
                                    } else {
                                        ss9 = oplusVolumeRowFindRow.getSs();
                                        if (ss9 != null) {
                                            i18 = ss9.levelMax;
                                        } else {
                                            i18 = 0;
                                        }
                                        float f11 = i18;
                                        i19 = (int) (0.33f * f11);
                                        i20 = (int) (f11 * 0.66f);
                                        if (z19) {
                                            i13 = 2;
                                            icon4 = oplusVolumeRowFindRow.getIcon();
                                            if (icon4 != null) {
                                                icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                            }
                                            oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                            oplusVolumeRowFindRow.setLastLevel(i17);
                                            if (i17 == 0) {
                                                icon6 = oplusVolumeRowFindRow.getIcon();
                                                i12 = 1;
                                                if (icon6 != null) {
                                                    icon6.setFrame(1);
                                                }
                                            } else {
                                                i12 = 1;
                                                if (i17 <= 0) {
                                                    if (i17 < i19) {
                                                        if (i17 >= i20) {
                                                            icon5.setFrame(83);
                                                        }
                                                    } else if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            }
                                        } else {
                                            i13 = 2;
                                            icon4 = oplusVolumeRowFindRow.getIcon();
                                            if (icon4 != null) {
                                                icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                            }
                                            oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                            oplusVolumeRowFindRow.setLastLevel(i17);
                                            if (i17 == 0) {
                                                icon6 = oplusVolumeRowFindRow.getIcon();
                                                i12 = 1;
                                                if (icon6 != null) {
                                                    icon6.setFrame(1);
                                                }
                                            } else {
                                                i12 = 1;
                                                if (i17 <= 0) {
                                                    if (i17 < i19) {
                                                        if (i17 >= i20) {
                                                            icon5.setFrame(83);
                                                        }
                                                    } else if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    str6 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                                }
                                VolumeDialogController.StreamState ss17 = oplusVolumeRowFindRow.getSs();
                                if (ss17 != null) {
                                }
                                if (LogUtil.getNormalDebug()) {
                                    z19 = z28;
                                } else {
                                    z19 = z28;
                                }
                                ss8 = oplusVolumeRowFindRow.getSs();
                                float f12 = 1.0f;
                                if (ss8 == null) {
                                    ss9 = oplusVolumeRowFindRow.getSs();
                                    if (ss9 != null) {
                                        i18 = ss9.levelMax;
                                    } else {
                                        i18 = 0;
                                    }
                                    float f13 = i18;
                                    i19 = (int) (0.33f * f13);
                                    i20 = (int) (f13 * 0.66f);
                                    if (z19) {
                                        i13 = 2;
                                        icon4 = oplusVolumeRowFindRow.getIcon();
                                        if (icon4 != null) {
                                            icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                        }
                                        oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                        oplusVolumeRowFindRow.setLastLevel(i17);
                                        if (i17 == 0) {
                                            icon6 = oplusVolumeRowFindRow.getIcon();
                                            i12 = 1;
                                            if (icon6 != null) {
                                                icon6.setFrame(1);
                                            }
                                        } else {
                                            i12 = 1;
                                            if (i17 <= 0) {
                                                if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        }
                                    } else {
                                        i13 = 2;
                                        icon4 = oplusVolumeRowFindRow.getIcon();
                                        if (icon4 != null) {
                                            icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                        }
                                        oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                        oplusVolumeRowFindRow.setLastLevel(i17);
                                        if (i17 == 0) {
                                            icon6 = oplusVolumeRowFindRow.getIcon();
                                            i12 = 1;
                                            if (icon6 != null) {
                                                icon6.setFrame(1);
                                            }
                                        } else {
                                            i12 = 1;
                                            if (i17 <= 0) {
                                                if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        }
                                    }
                                } else {
                                    ss9 = oplusVolumeRowFindRow.getSs();
                                    if (ss9 != null) {
                                        i18 = ss9.levelMax;
                                    } else {
                                        i18 = 0;
                                    }
                                    float f14 = i18;
                                    i19 = (int) (0.33f * f14);
                                    i20 = (int) (f14 * 0.66f);
                                    if (z19) {
                                        i13 = 2;
                                        icon4 = oplusVolumeRowFindRow.getIcon();
                                        if (icon4 != null) {
                                            icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                        }
                                        oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                        oplusVolumeRowFindRow.setLastLevel(i17);
                                        if (i17 == 0) {
                                            icon6 = oplusVolumeRowFindRow.getIcon();
                                            i12 = 1;
                                            if (icon6 != null) {
                                                icon6.setFrame(1);
                                            }
                                        } else {
                                            i12 = 1;
                                            if (i17 <= 0) {
                                                if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        }
                                    } else {
                                        i13 = 2;
                                        icon4 = oplusVolumeRowFindRow.getIcon();
                                        if (icon4 != null) {
                                            icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                        }
                                        oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                        oplusVolumeRowFindRow.setLastLevel(i17);
                                        if (i17 == 0) {
                                            icon6 = oplusVolumeRowFindRow.getIcon();
                                            i12 = 1;
                                            if (icon6 != null) {
                                                icon6.setFrame(1);
                                            }
                                        } else {
                                            i12 = 1;
                                            if (i17 <= 0) {
                                                if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        }
                                    }
                                }
                            } else {
                                str6 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                            }
                            if (LogUtil.getNormalDebug()) {
                                z19 = z28;
                            } else {
                                z19 = z28;
                            }
                            ss8 = oplusVolumeRowFindRow.getSs();
                            float f15 = 1.0f;
                            if (ss8 == null) {
                                ss9 = oplusVolumeRowFindRow.getSs();
                                if (ss9 != null) {
                                    i18 = ss9.levelMax;
                                } else {
                                    i18 = 0;
                                }
                                float f16 = i18;
                                i19 = (int) (0.33f * f16);
                                i20 = (int) (f16 * 0.66f);
                                if (z19) {
                                    i13 = 2;
                                    icon4 = oplusVolumeRowFindRow.getIcon();
                                    if (icon4 != null) {
                                        icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                    }
                                    oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                    oplusVolumeRowFindRow.setLastLevel(i17);
                                    if (i17 == 0) {
                                        icon6 = oplusVolumeRowFindRow.getIcon();
                                        i12 = 1;
                                        if (icon6 != null) {
                                            icon6.setFrame(1);
                                        }
                                    } else {
                                        i12 = 1;
                                        if (i17 <= 0) {
                                            if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 < i19) {
                                            if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    }
                                } else {
                                    i13 = 2;
                                    icon4 = oplusVolumeRowFindRow.getIcon();
                                    if (icon4 != null) {
                                        icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                    }
                                    oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                    oplusVolumeRowFindRow.setLastLevel(i17);
                                    if (i17 == 0) {
                                        icon6 = oplusVolumeRowFindRow.getIcon();
                                        i12 = 1;
                                        if (icon6 != null) {
                                            icon6.setFrame(1);
                                        }
                                    } else {
                                        i12 = 1;
                                        if (i17 <= 0) {
                                            if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 < i19) {
                                            if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    }
                                }
                            } else {
                                ss9 = oplusVolumeRowFindRow.getSs();
                                if (ss9 != null) {
                                    i18 = ss9.levelMax;
                                } else {
                                    i18 = 0;
                                }
                                float f17 = i18;
                                i19 = (int) (0.33f * f17);
                                i20 = (int) (f17 * 0.66f);
                                if (z19) {
                                    i13 = 2;
                                    icon4 = oplusVolumeRowFindRow.getIcon();
                                    if (icon4 != null) {
                                        icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                    }
                                    oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                    oplusVolumeRowFindRow.setLastLevel(i17);
                                    if (i17 == 0) {
                                        icon6 = oplusVolumeRowFindRow.getIcon();
                                        i12 = 1;
                                        if (icon6 != null) {
                                            icon6.setFrame(1);
                                        }
                                    } else {
                                        i12 = 1;
                                        if (i17 <= 0) {
                                            if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 < i19) {
                                            if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    }
                                } else {
                                    i13 = 2;
                                    icon4 = oplusVolumeRowFindRow.getIcon();
                                    if (icon4 != null) {
                                        icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                    }
                                    oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                    oplusVolumeRowFindRow.setLastLevel(i17);
                                    if (i17 == 0) {
                                        icon6 = oplusVolumeRowFindRow.getIcon();
                                        i12 = 1;
                                        if (icon6 != null) {
                                            icon6.setFrame(1);
                                        }
                                    } else {
                                        i12 = 1;
                                        if (i17 <= 0) {
                                            if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 < i19) {
                                            if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    }
                                }
                            }
                        }
                        i12 = 1;
                        i13 = 2;
                    } else {
                        streamState = streamState;
                        z14 = z14;
                        iconRes2 = iconRes2;
                        z18 = z28;
                        i12 = 1;
                        i13 = 2;
                        if (z2) {
                            if (LogUtil.getNormalDebug()) {
                                String jsonRes3 = oplusVolumeRow.getJsonRes();
                                StringBuilder sb2 = new StringBuilder("updateAssistantStreamIcon jsonRes: ");
                                sb2.append((Object) str2);
                                sb2.append(",row.jsonRes: ");
                                sb2.append(jsonRes3);
                                sb2.append(" isDeviceChanged: ");
                                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("IconAbility-->", "SystemUi--Volume", sb2, z18);
                            }
                            if (!z18) {
                            }
                            icon = oplusVolumeRow.getIcon();
                            if (icon != null) {
                                icon.setAnimation("volumeui/" + ((Object) str2) + LIGHT_SUFFIX);
                            }
                            oplusVolumeRow.setJsonRes(str2);
                            iconAbility = this;
                            if (iconAbility.volumeInteractor.mOplusVolumeDialogView.isSliderShowing()) {
                                icon3 = oplusVolumeRow.getIcon();
                                if (icon3 != null) {
                                    icon3.playAnimation();
                                }
                            } else {
                                icon2 = oplusVolumeRow.getIcon();
                                if (icon2 != null) {
                                    icon2.setFrame(35);
                                }
                            }
                        } else {
                            iconAbility = this;
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                                if (!z7) {
                                }
                            } else if (!z7) {
                            }
                            iconRes2 = iconRes2;
                            if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                                oplusVolumeRow.setLastIconRes(iconRes2);
                                if (i15 == 0) {
                                    icon9.setImageResource(iconRes2);
                                }
                            }
                            i25 = 2131237442;
                        }
                        i15 = i12;
                        iconRes2 = iconRes2;
                        if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                            oplusVolumeRow.setLastIconRes(iconRes2);
                            if (i15 == 0) {
                                icon9.setImageResource(iconRes2);
                            }
                        }
                        i25 = 2131237442;
                    }
                    iconAbility = this;
                    i15 = i12;
                    iconRes2 = iconRes2;
                    if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                        oplusVolumeRow.setLastIconRes(iconRes2);
                        if (i15 == 0) {
                            icon9.setImageResource(iconRes2);
                        }
                    }
                    i25 = 2131237442;
                }
                i15 = 0;
                iconRes2 = iconRes2;
                if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                    oplusVolumeRow.setLastIconRes(iconRes2);
                    if (i15 == 0) {
                        icon9.setImageResource(iconRes2);
                    }
                }
                i25 = 2131237442;
            }
            if (iconRes2 == i25) {
                i26 = 3;
            } else if (iconRes2 != 2131237415) {
                i26 = i13;
            } else {
                i26 = i13;
            }
            oplusVolumeRow.setIconState(i26);
            if (z14) {
                if (z31) {
                    vibrateWhenSilentToggleState = VolumeSettingsValueProxy.Companion.getVibrateWhenSilentToggleState(iconAbility.context);
                    streamState2 = streamState;
                    z27 = streamState2.muted;
                    if (vibrateWhenSilentToggleState != 0) {
                        if (z27) {
                            icon14 = oplusVolumeRow.getIcon();
                            if (icon14 != null) {
                                icon14.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                            }
                        } else {
                            icon14 = oplusVolumeRow.getIcon();
                            if (icon14 != null) {
                                icon14.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                            }
                        }
                    } else if (z27) {
                        icon13 = oplusVolumeRow.getIcon();
                        if (icon13 != null) {
                            icon13.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                        }
                    } else {
                        icon13 = oplusVolumeRow.getIcon();
                        if (icon13 != null) {
                            icon13.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                        }
                    }
                } else {
                    streamState2 = streamState;
                    if (z8) {
                        icon12 = oplusVolumeRow.getIcon();
                        if (icon12 != null) {
                            icon12.setContentDescription(iconAbility.getStreamLabelH(streamState2));
                        }
                    } else if (streamState2.muted) {
                        icon11 = oplusVolumeRow.getIcon();
                        if (icon11 != null) {
                            icon11.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                        }
                    } else {
                        icon11 = oplusVolumeRow.getIcon();
                        if (icon11 != null) {
                            icon11.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                        }
                    }
                }
            } else {
                streamState2 = streamState;
                icon10 = oplusVolumeRow.getIcon();
                if (icon10 != null) {
                    icon10.setContentDescription(iconAbility.getStreamLabelH(streamState2));
                }
            }
            i27 = streamState2.level;
            if (i27 > 0) {
                oplusVolumeRow.setLastAudibleLevel(i27);
            }
            i28 = 0;
            if (z14) {
                oplusVolumeRow.setTracking(false);
            }
            if (z310) {
            }
            z25 = i3;
            if (z14) {
                z26 = 0;
            } else {
                z26 = 0;
            }
            if (LogUtil.getNormalDebug()) {
                StringBuilder sbM5 = TextFieldImplKt$.ExternalSyntheticOutline0.m("zenMuted: ", ", isNotificationStream: ", ", isInternalVibrate: ", z14, z35);
                KeyguardLogger$.ExternalSyntheticOutline0.m(", isInternalSilent: ", ", isOplusThreekeysSilentOrVibrate: ", sbM5, z25, i4);
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("IconAbility-->", "SystemUi--Volume", sbM5, z24);
            }
            ss11 = oplusVolumeRow.getSs();
            if (ss11 != null) {
                i28 = ss11.level;
            }
            i29 = i28;
            oplusVolumeDialogImpl = iconAbility.volumeInteractor;
            oplusVolumeDialogImpl.getClass();
            VolumeFeatureOption.INSTANCE.getClass();
            if (VolumeFeatureOption.isOplusSuperVolume()) {
            }
            oplusVolumeDialogImpl.updateVolumeRowSliderH(oplusVolumeRow, z26, i29, 0, z);
        }
        z2 = z29;
        if (z31) {
            z3 = false;
        } else {
            z3 = false;
        }
        z4 = z36;
        if (z35) {
            z5 = false;
        } else {
            z5 = false;
        }
        z6 = z5;
        if (z35) {
            z7 = z33;
            i = 1;
            if (state.ringerModeInternal == 1) {
            }
            i2 = state.ringerModeInternal;
            if (i2 == i) {
                i3 = i;
            } else {
                i3 = 0;
            }
            boolean z311 = z3;
            if (i2 == 0) {
                i4 = i;
            } else {
                i4 = 0;
            }
            z8 = z30;
            i5 = state.zenMode;
            if (i5 == i) {
                z9 = true;
            } else {
                z9 = false;
            }
            if (i5 == 3) {
                z10 = true;
            } else {
                z10 = false;
            }
            if (i5 == 2) {
                z11 = true;
            } else {
                z11 = false;
            }
            z12 = state.disallowRinger;
            if (z12) {
                z13 = false;
            } else {
                z13 = false;
            }
            if (z10) {
            }
            if (oplusVolumeRow.getStream() == 0) {
                z15 = true;
            } else {
                z15 = false;
            }
            i6 = streamState.levelMax * 100;
            slider = oplusVolumeRow.getSlider();
            z16 = z15;
            if (slider != null) {
                slider.setMax(i6);
            }
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isOplusAlertSlider()) {
                if (state.ringerModeInternal == 2) {
                    streamState.levelMin = 1;
                } else {
                    streamState.levelMin = 0;
                }
            }
            Util.setText(oplusVolumeRow.getNameText(), iconAbility.getStreamLabelH(streamState));
            slider2 = oplusVolumeRow.getSlider();
            if (slider2 != null) {
                slider2.setContentDescription(nameText.getText());
            }
            EffectiveAnimationView icon111 = oplusVolumeRow.getIcon();
            icon111.getClass();
            icon111.setEnabled(!z14);
            iconRes = oplusVolumeRow.getIconRes();
            if (!LogUtil.getNormalDebug()) {
            }
            String str10 = "ic_volume_media";
            str = "";
            if (z31 != 0) {
                i30 = state.ringerModeInternal;
                i31 = 2131237438;
                if (i30 != 0) {
                    if (oplusVolumeRow.getLastIconRes() == 2131237442) {
                        str2 = "ic_volume_ringer_remove_vibrate";
                    } else {
                        str2 = "ic_volume_ringer_mute";
                    }
                } else if (oplusVolumeRow.getLastIconRes() == 2131237442) {
                    str2 = "ic_volume_ringer_remove_vibrate";
                } else {
                    str2 = "ic_volume_ringer_mute";
                }
                if (oplusVolumeRow.getLastIconRes() == i31) {
                    str2 = str;
                }
                iconRes2 = i31;
            } else {
                i7 = 2131237460;
                if (z34 != 0) {
                    ss6 = oplusVolumeRow.getSs();
                    if (ss6 != null) {
                        if (isStreamMuted(ss6)) {
                            if (streamState.routedToWired) {
                                str5 = str;
                            } else if (streamState.routedToBluetooth) {
                                i7 = 2131237411;
                                str5 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                            } else {
                                i7 = 2131237422;
                                str5 = "ic_volume_media";
                            }
                        } else if (streamState.routedToWired) {
                            str5 = str;
                        } else if (streamState.routedToBluetooth) {
                            i7 = 2131237411;
                            str5 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                        } else {
                            i7 = 2131237422;
                            str5 = "ic_volume_media";
                        }
                        iconRes = i7;
                        str2 = str5;
                    } else {
                        str2 = str;
                    }
                    iconRes2 = iconRes;
                } else {
                    if (z35 != 0) {
                        ss5 = oplusVolumeRow.getSs();
                        if (ss5 != null) {
                            if (isStreamMuted(ss5)) {
                                str5 = "ic_volume_notification_mute";
                                iconRes = oplusVolumeRow.getIconMuteRes();
                            } else {
                                str5 = "ic_volume_notification_on";
                            }
                            str2 = str5;
                        }
                        iconRes2 = iconRes;
                    } else if (z16) {
                        if (((AudioDataRepository.AudioState) iconAbility.volumeInteractor.mAudioDataRepository.getAudioState().getValue()).isStreamInBluetoothSco) {
                            if (streamState.level > streamState.levelMin) {
                                i11 = 2131237404;
                                str4 = "ic_volume_bt_sco_on";
                            } else {
                                i11 = 2131237405;
                                str4 = "ic_volume_bt_sco_mute";
                            }
                            ss4 = oplusVolumeRow.getSs();
                            if (ss4 != null) {
                                ss4.name = 2131956668;
                            }
                            i9 = i11;
                            str2 = str4;
                        } else {
                            ss2 = oplusVolumeRow.getSs();
                            if (ss2 != null) {
                                if (((AudioDataRepository.AudioState) iconAbility.volumeInteractor.mAudioDataRepository.getAudioState().getValue()).isSpeakerphoneOn) {
                                    i10 = 2131956681;
                                } else {
                                    i10 = 2131956680;
                                }
                                ss2.name = i10;
                            }
                            i9 = 2131237453;
                            str2 = str;
                        }
                        ss3 = oplusVolumeRow.getSs();
                        if (ss3 != null) {
                            Util.setText(oplusVolumeRow.getNameText(), iconAbility.context.getString(ss3.name));
                        }
                        if (iconAbility.volumeInteractor.mOplusVolumeDialogView.isSliderShowing()) {
                            iconAbility.volumeInteractor.getVolumeListAdapter().updateShowingRow(oplusVolumeRow, 100);
                        }
                        iconRes2 = i9;
                    } else if (z7) {
                        str2 = "ic_volume_alarm_multi_app";
                        iconRes2 = iconRes;
                    } else if (z2) {
                        ss = oplusVolumeRow.getSs();
                        if (ss != null) {
                            zIsStreamMuted = isStreamMuted(ss);
                            z17 = streamState.routedToWired;
                            if (zIsStreamMuted) {
                                if (z17) {
                                    iconRes2 = 2131237460;
                                    str3 = str;
                                } else if (streamState.routedToBluetooth) {
                                    str3 = str;
                                    iconRes2 = 2131237415;
                                } else {
                                    if (FeatureOption.isOplusAssistantVolumeSupport()) {
                                        i8 = 2131237398;
                                        str3 = "ic_volume_assistant_mute";
                                    } else {
                                        i8 = 2131237407;
                                        str3 = "ic_volume_gemini_mute";
                                    }
                                    iconRes2 = i8;
                                }
                            } else if (z17) {
                                str3 = str;
                                iconRes2 = 2131237458;
                            } else if (streamState.routedToBluetooth) {
                                str3 = str;
                                iconRes2 = 2131237413;
                            } else {
                                if (FeatureOption.isOplusAssistantVolumeSupport()) {
                                    i8 = 2131237397;
                                    str3 = "ic_volume_assistant_on";
                                } else {
                                    i8 = 2131237406;
                                    str3 = "ic_volume_gemini_on";
                                }
                                iconRes2 = i8;
                            }
                            str2 = str3;
                        }
                    } else if (streamState.dynamic) {
                        if (isStreamMuted(streamState)) {
                            iconRes2 = oplusVolumeRow.getIconMuteRes();
                        } else {
                            iconRes2 = oplusVolumeRow.getIconRes();
                        }
                        str2 = str;
                    }
                    str2 = str;
                    iconRes2 = iconRes;
                }
            }
            if (iconAbility.volumeInteractor.mOplusVolumeDialogView.getMIsDefaultVolumeTheme()) {
                if (oplusVolumeRow.getJsonRes().length() > 0) {
                    oplusVolumeRow.setJsonRes(str);
                    icon16 = oplusVolumeRow.getIcon();
                    if (icon16 != null) {
                        icon16.clearAnimation();
                    }
                }
                icon15 = oplusVolumeRow.getIcon();
                if (icon15 != null) {
                    icon15.setImageResource(iconRes2);
                }
                streamState = streamState;
                z14 = z14;
                i25 = 2131237442;
                i12 = 1;
                i13 = 2;
            } else {
                if (str2.length() == 0) {
                    streamState = streamState;
                    z14 = z14;
                    iconRes2 = iconRes2;
                    i12 = 1;
                    i13 = 2;
                } else {
                    if (z34) {
                        boolean zIsSliderShowing3 = iconAbility.volumeInteractor.mOplusVolumeDialogView.isSliderShowing();
                        oplusVolumeRowFindRow = iconAbility.volumeInteractor.findRow(3);
                        if (oplusVolumeRowFindRow == null) {
                            LogUtil.e$default(8, LogUtil.TAG_VOLUME, TAG, "updateMusicStreamIcon VolumeRow is null!");
                            streamState = streamState;
                            z14 = z14;
                            iconRes2 = iconRes2;
                        } else {
                            if (z14) {
                                ss10 = oplusVolumeRowFindRow.getSs();
                                if (ss10 != null) {
                                    z23 = ss10.muted;
                                    str6 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                                    if (z23) {
                                    }
                                    if (LogUtil.getNormalDebug()) {
                                        z19 = z28;
                                    } else {
                                        z19 = z28;
                                    }
                                    ss8 = oplusVolumeRowFindRow.getSs();
                                    float f18 = 1.0f;
                                    if (ss8 == null) {
                                        ss9 = oplusVolumeRowFindRow.getSs();
                                        if (ss9 != null) {
                                            i18 = ss9.levelMax;
                                        } else {
                                            i18 = 0;
                                        }
                                        float f19 = i18;
                                        i19 = (int) (0.33f * f19);
                                        i20 = (int) (f19 * 0.66f);
                                        if (z19) {
                                            i13 = 2;
                                            icon4 = oplusVolumeRowFindRow.getIcon();
                                            if (icon4 != null) {
                                                icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                            }
                                            oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                            oplusVolumeRowFindRow.setLastLevel(i17);
                                            if (i17 == 0) {
                                                icon6 = oplusVolumeRowFindRow.getIcon();
                                                i12 = 1;
                                                if (icon6 != null) {
                                                    icon6.setFrame(1);
                                                }
                                            } else {
                                                i12 = 1;
                                                if (i17 <= 0) {
                                                    if (i17 < i19) {
                                                        if (i17 >= i20) {
                                                            icon5.setFrame(83);
                                                        }
                                                    } else if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            }
                                        } else {
                                            i13 = 2;
                                            icon4 = oplusVolumeRowFindRow.getIcon();
                                            if (icon4 != null) {
                                                icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                            }
                                            oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                            oplusVolumeRowFindRow.setLastLevel(i17);
                                            if (i17 == 0) {
                                                icon6 = oplusVolumeRowFindRow.getIcon();
                                                i12 = 1;
                                                if (icon6 != null) {
                                                    icon6.setFrame(1);
                                                }
                                            } else {
                                                i12 = 1;
                                                if (i17 <= 0) {
                                                    if (i17 < i19) {
                                                        if (i17 >= i20) {
                                                            icon5.setFrame(83);
                                                        }
                                                    } else if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            }
                                        }
                                    } else {
                                        ss9 = oplusVolumeRowFindRow.getSs();
                                        if (ss9 != null) {
                                            i18 = ss9.levelMax;
                                        } else {
                                            i18 = 0;
                                        }
                                        float f110 = i18;
                                        i19 = (int) (0.33f * f110);
                                        i20 = (int) (f110 * 0.66f);
                                        if (z19) {
                                            i13 = 2;
                                            icon4 = oplusVolumeRowFindRow.getIcon();
                                            if (icon4 != null) {
                                                icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                            }
                                            oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                            oplusVolumeRowFindRow.setLastLevel(i17);
                                            if (i17 == 0) {
                                                icon6 = oplusVolumeRowFindRow.getIcon();
                                                i12 = 1;
                                                if (icon6 != null) {
                                                    icon6.setFrame(1);
                                                }
                                            } else {
                                                i12 = 1;
                                                if (i17 <= 0) {
                                                    if (i17 < i19) {
                                                        if (i17 >= i20) {
                                                            icon5.setFrame(83);
                                                        }
                                                    } else if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            }
                                        } else {
                                            i13 = 2;
                                            icon4 = oplusVolumeRowFindRow.getIcon();
                                            if (icon4 != null) {
                                                icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                            }
                                            oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                            oplusVolumeRowFindRow.setLastLevel(i17);
                                            if (i17 == 0) {
                                                icon6 = oplusVolumeRowFindRow.getIcon();
                                                i12 = 1;
                                                if (icon6 != null) {
                                                    icon6.setFrame(1);
                                                }
                                            } else {
                                                i12 = 1;
                                                if (i17 <= 0) {
                                                    if (i17 < i19) {
                                                        if (i17 >= i20) {
                                                            icon5.setFrame(83);
                                                        }
                                                    } else if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    str6 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                                }
                                VolumeDialogController.StreamState ss18 = oplusVolumeRowFindRow.getSs();
                                if (ss18 != null) {
                                }
                                if (LogUtil.getNormalDebug()) {
                                    z19 = z28;
                                } else {
                                    z19 = z28;
                                }
                                ss8 = oplusVolumeRowFindRow.getSs();
                                float f111 = 1.0f;
                                if (ss8 == null) {
                                    ss9 = oplusVolumeRowFindRow.getSs();
                                    if (ss9 != null) {
                                        i18 = ss9.levelMax;
                                    } else {
                                        i18 = 0;
                                    }
                                    float f112 = i18;
                                    i19 = (int) (0.33f * f112);
                                    i20 = (int) (f112 * 0.66f);
                                    if (z19) {
                                        i13 = 2;
                                        icon4 = oplusVolumeRowFindRow.getIcon();
                                        if (icon4 != null) {
                                            icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                        }
                                        oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                        oplusVolumeRowFindRow.setLastLevel(i17);
                                        if (i17 == 0) {
                                            icon6 = oplusVolumeRowFindRow.getIcon();
                                            i12 = 1;
                                            if (icon6 != null) {
                                                icon6.setFrame(1);
                                            }
                                        } else {
                                            i12 = 1;
                                            if (i17 <= 0) {
                                                if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        }
                                    } else {
                                        i13 = 2;
                                        icon4 = oplusVolumeRowFindRow.getIcon();
                                        if (icon4 != null) {
                                            icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                        }
                                        oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                        oplusVolumeRowFindRow.setLastLevel(i17);
                                        if (i17 == 0) {
                                            icon6 = oplusVolumeRowFindRow.getIcon();
                                            i12 = 1;
                                            if (icon6 != null) {
                                                icon6.setFrame(1);
                                            }
                                        } else {
                                            i12 = 1;
                                            if (i17 <= 0) {
                                                if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        }
                                    }
                                } else {
                                    ss9 = oplusVolumeRowFindRow.getSs();
                                    if (ss9 != null) {
                                        i18 = ss9.levelMax;
                                    } else {
                                        i18 = 0;
                                    }
                                    float f113 = i18;
                                    i19 = (int) (0.33f * f113);
                                    i20 = (int) (f113 * 0.66f);
                                    if (z19) {
                                        i13 = 2;
                                        icon4 = oplusVolumeRowFindRow.getIcon();
                                        if (icon4 != null) {
                                            icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                        }
                                        oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                        oplusVolumeRowFindRow.setLastLevel(i17);
                                        if (i17 == 0) {
                                            icon6 = oplusVolumeRowFindRow.getIcon();
                                            i12 = 1;
                                            if (icon6 != null) {
                                                icon6.setFrame(1);
                                            }
                                        } else {
                                            i12 = 1;
                                            if (i17 <= 0) {
                                                if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        }
                                    } else {
                                        i13 = 2;
                                        icon4 = oplusVolumeRowFindRow.getIcon();
                                        if (icon4 != null) {
                                            icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                        }
                                        oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                        oplusVolumeRowFindRow.setLastLevel(i17);
                                        if (i17 == 0) {
                                            icon6 = oplusVolumeRowFindRow.getIcon();
                                            i12 = 1;
                                            if (icon6 != null) {
                                                icon6.setFrame(1);
                                            }
                                        } else {
                                            i12 = 1;
                                            if (i17 <= 0) {
                                                if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        }
                                    }
                                }
                            } else {
                                str6 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                            }
                            if (LogUtil.getNormalDebug()) {
                                z19 = z28;
                            } else {
                                z19 = z28;
                            }
                            ss8 = oplusVolumeRowFindRow.getSs();
                            float f114 = 1.0f;
                            if (ss8 == null) {
                                ss9 = oplusVolumeRowFindRow.getSs();
                                if (ss9 != null) {
                                    i18 = ss9.levelMax;
                                } else {
                                    i18 = 0;
                                }
                                float f115 = i18;
                                i19 = (int) (0.33f * f115);
                                i20 = (int) (f115 * 0.66f);
                                if (z19) {
                                    i13 = 2;
                                    icon4 = oplusVolumeRowFindRow.getIcon();
                                    if (icon4 != null) {
                                        icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                    }
                                    oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                    oplusVolumeRowFindRow.setLastLevel(i17);
                                    if (i17 == 0) {
                                        icon6 = oplusVolumeRowFindRow.getIcon();
                                        i12 = 1;
                                        if (icon6 != null) {
                                            icon6.setFrame(1);
                                        }
                                    } else {
                                        i12 = 1;
                                        if (i17 <= 0) {
                                            if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 < i19) {
                                            if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    }
                                } else {
                                    i13 = 2;
                                    icon4 = oplusVolumeRowFindRow.getIcon();
                                    if (icon4 != null) {
                                        icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                    }
                                    oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                    oplusVolumeRowFindRow.setLastLevel(i17);
                                    if (i17 == 0) {
                                        icon6 = oplusVolumeRowFindRow.getIcon();
                                        i12 = 1;
                                        if (icon6 != null) {
                                            icon6.setFrame(1);
                                        }
                                    } else {
                                        i12 = 1;
                                        if (i17 <= 0) {
                                            if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 < i19) {
                                            if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    }
                                }
                            } else {
                                ss9 = oplusVolumeRowFindRow.getSs();
                                if (ss9 != null) {
                                    i18 = ss9.levelMax;
                                } else {
                                    i18 = 0;
                                }
                                float f116 = i18;
                                i19 = (int) (0.33f * f116);
                                i20 = (int) (f116 * 0.66f);
                                if (z19) {
                                    i13 = 2;
                                    icon4 = oplusVolumeRowFindRow.getIcon();
                                    if (icon4 != null) {
                                        icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                    }
                                    oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                    oplusVolumeRowFindRow.setLastLevel(i17);
                                    if (i17 == 0) {
                                        icon6 = oplusVolumeRowFindRow.getIcon();
                                        i12 = 1;
                                        if (icon6 != null) {
                                            icon6.setFrame(1);
                                        }
                                    } else {
                                        i12 = 1;
                                        if (i17 <= 0) {
                                            if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 < i19) {
                                            if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    }
                                } else {
                                    i13 = 2;
                                    icon4 = oplusVolumeRowFindRow.getIcon();
                                    if (icon4 != null) {
                                        icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                    }
                                    oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                    oplusVolumeRowFindRow.setLastLevel(i17);
                                    if (i17 == 0) {
                                        icon6 = oplusVolumeRowFindRow.getIcon();
                                        i12 = 1;
                                        if (icon6 != null) {
                                            icon6.setFrame(1);
                                        }
                                    } else {
                                        i12 = 1;
                                        if (i17 <= 0) {
                                            if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 < i19) {
                                            if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    }
                                }
                            }
                        }
                        i12 = 1;
                        i13 = 2;
                    } else {
                        streamState = streamState;
                        z14 = z14;
                        iconRes2 = iconRes2;
                        z18 = z28;
                        i12 = 1;
                        i13 = 2;
                        if (z2) {
                            if (LogUtil.getNormalDebug()) {
                                String jsonRes4 = oplusVolumeRow.getJsonRes();
                                StringBuilder sb3 = new StringBuilder("updateAssistantStreamIcon jsonRes: ");
                                sb3.append((Object) str2);
                                sb3.append(",row.jsonRes: ");
                                sb3.append(jsonRes4);
                                sb3.append(" isDeviceChanged: ");
                                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("IconAbility-->", "SystemUi--Volume", sb3, z18);
                            }
                            if (!z18) {
                            }
                            icon = oplusVolumeRow.getIcon();
                            if (icon != null) {
                                icon.setAnimation("volumeui/" + ((Object) str2) + LIGHT_SUFFIX);
                            }
                            oplusVolumeRow.setJsonRes(str2);
                            iconAbility = this;
                            if (iconAbility.volumeInteractor.mOplusVolumeDialogView.isSliderShowing()) {
                                icon3 = oplusVolumeRow.getIcon();
                                if (icon3 != null) {
                                    icon3.playAnimation();
                                }
                            } else {
                                icon2 = oplusVolumeRow.getIcon();
                                if (icon2 != null) {
                                    icon2.setFrame(35);
                                }
                            }
                        } else {
                            iconAbility = this;
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                                if (!z7) {
                                }
                            } else if (!z7) {
                            }
                            iconRes2 = iconRes2;
                            if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                                oplusVolumeRow.setLastIconRes(iconRes2);
                                if (i15 == 0) {
                                    icon9.setImageResource(iconRes2);
                                }
                            }
                            i25 = 2131237442;
                        }
                        i15 = i12;
                        iconRes2 = iconRes2;
                        if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                            oplusVolumeRow.setLastIconRes(iconRes2);
                            if (i15 == 0) {
                                icon9.setImageResource(iconRes2);
                            }
                        }
                        i25 = 2131237442;
                    }
                    iconAbility = this;
                    i15 = i12;
                    iconRes2 = iconRes2;
                    if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                        oplusVolumeRow.setLastIconRes(iconRes2);
                        if (i15 == 0) {
                            icon9.setImageResource(iconRes2);
                        }
                    }
                    i25 = 2131237442;
                }
                i15 = 0;
                iconRes2 = iconRes2;
                if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                    oplusVolumeRow.setLastIconRes(iconRes2);
                    if (i15 == 0) {
                        icon9.setImageResource(iconRes2);
                    }
                }
                i25 = 2131237442;
            }
            if (iconRes2 == i25) {
                i26 = 3;
            } else if (iconRes2 != 2131237415) {
                i26 = i13;
            } else {
                i26 = i13;
            }
            oplusVolumeRow.setIconState(i26);
            if (z14) {
                if (z31) {
                    vibrateWhenSilentToggleState = VolumeSettingsValueProxy.Companion.getVibrateWhenSilentToggleState(iconAbility.context);
                    streamState2 = streamState;
                    z27 = streamState2.muted;
                    if (vibrateWhenSilentToggleState != 0) {
                        if (z27) {
                            icon14 = oplusVolumeRow.getIcon();
                            if (icon14 != null) {
                                icon14.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                            }
                        } else {
                            icon14 = oplusVolumeRow.getIcon();
                            if (icon14 != null) {
                                icon14.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                            }
                        }
                    } else if (z27) {
                        icon13 = oplusVolumeRow.getIcon();
                        if (icon13 != null) {
                            icon13.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                        }
                    } else {
                        icon13 = oplusVolumeRow.getIcon();
                        if (icon13 != null) {
                            icon13.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                        }
                    }
                } else {
                    streamState2 = streamState;
                    if (z8) {
                        icon12 = oplusVolumeRow.getIcon();
                        if (icon12 != null) {
                            icon12.setContentDescription(iconAbility.getStreamLabelH(streamState2));
                        }
                    } else if (streamState2.muted) {
                        icon11 = oplusVolumeRow.getIcon();
                        if (icon11 != null) {
                            icon11.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                        }
                    } else {
                        icon11 = oplusVolumeRow.getIcon();
                        if (icon11 != null) {
                            icon11.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                        }
                    }
                }
            } else {
                streamState2 = streamState;
                icon10 = oplusVolumeRow.getIcon();
                if (icon10 != null) {
                    icon10.setContentDescription(iconAbility.getStreamLabelH(streamState2));
                }
            }
            i27 = streamState2.level;
            if (i27 > 0) {
                oplusVolumeRow.setLastAudibleLevel(i27);
            }
            i28 = 0;
            if (z14) {
                oplusVolumeRow.setTracking(false);
            }
            if (z311) {
            }
            z25 = i3;
            if (z14) {
                z26 = 0;
            } else {
                z26 = 0;
            }
            if (LogUtil.getNormalDebug()) {
                StringBuilder sbM6 = TextFieldImplKt$.ExternalSyntheticOutline0.m("zenMuted: ", ", isNotificationStream: ", ", isInternalVibrate: ", z14, z35);
                KeyguardLogger$.ExternalSyntheticOutline0.m(", isInternalSilent: ", ", isOplusThreekeysSilentOrVibrate: ", sbM6, z25, i4);
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("IconAbility-->", "SystemUi--Volume", sbM6, z24);
            }
            ss11 = oplusVolumeRow.getSs();
            if (ss11 != null) {
                i28 = ss11.level;
            }
            i29 = i28;
            oplusVolumeDialogImpl = iconAbility.volumeInteractor;
            oplusVolumeDialogImpl.getClass();
            VolumeFeatureOption.INSTANCE.getClass();
            if (VolumeFeatureOption.isOplusSuperVolume()) {
            }
            oplusVolumeDialogImpl.updateVolumeRowSliderH(oplusVolumeRow, z26, i29, 0, z);
        }
        z7 = z33;
        i = 1;
        i2 = state.ringerModeInternal;
        if (i2 == i) {
            i3 = i;
        } else {
            i3 = 0;
        }
        boolean z312 = z3;
        if (i2 == 0) {
            i4 = i;
        } else {
            i4 = 0;
        }
        z8 = z30;
        i5 = state.zenMode;
        if (i5 == i) {
            z9 = true;
        } else {
            z9 = false;
        }
        if (i5 == 3) {
            z10 = true;
        } else {
            z10 = false;
        }
        if (i5 == 2) {
            z11 = true;
        } else {
            z11 = false;
        }
        z12 = state.disallowRinger;
        if (z12) {
            z13 = false;
        } else {
            z13 = false;
        }
        if (z10) {
        }
        if (oplusVolumeRow.getStream() == 0) {
            z15 = true;
        } else {
            z15 = false;
        }
        i6 = streamState.levelMax * 100;
        slider = oplusVolumeRow.getSlider();
        z16 = z15;
        if (slider != null) {
            slider.setMax(i6);
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isOplusAlertSlider()) {
            if (state.ringerModeInternal == 2) {
                streamState.levelMin = 1;
            } else {
                streamState.levelMin = 0;
            }
        }
        Util.setText(oplusVolumeRow.getNameText(), iconAbility.getStreamLabelH(streamState));
        slider2 = oplusVolumeRow.getSlider();
        if (slider2 != null) {
            slider2.setContentDescription(nameText.getText());
        }
        EffectiveAnimationView icon112 = oplusVolumeRow.getIcon();
        icon112.getClass();
        icon112.setEnabled(!z14);
        iconRes = oplusVolumeRow.getIconRes();
        if (!LogUtil.getNormalDebug()) {
        }
        String str11 = "ic_volume_media";
        str = "";
        if (z31 != 0) {
            i30 = state.ringerModeInternal;
            i31 = 2131237438;
            if (i30 != 0) {
                if (oplusVolumeRow.getLastIconRes() == 2131237442) {
                    str2 = "ic_volume_ringer_remove_vibrate";
                } else {
                    str2 = "ic_volume_ringer_mute";
                }
            } else if (oplusVolumeRow.getLastIconRes() == 2131237442) {
                str2 = "ic_volume_ringer_remove_vibrate";
            } else {
                str2 = "ic_volume_ringer_mute";
            }
            if (oplusVolumeRow.getLastIconRes() == i31) {
                str2 = str;
            }
            iconRes2 = i31;
        } else {
            i7 = 2131237460;
            if (z34 != 0) {
                ss6 = oplusVolumeRow.getSs();
                if (ss6 != null) {
                    if (isStreamMuted(ss6)) {
                        if (streamState.routedToWired) {
                            str5 = str;
                        } else if (streamState.routedToBluetooth) {
                            i7 = 2131237411;
                            str5 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                        } else {
                            i7 = 2131237422;
                            str5 = "ic_volume_media";
                        }
                    } else if (streamState.routedToWired) {
                        str5 = str;
                    } else if (streamState.routedToBluetooth) {
                        i7 = 2131237411;
                        str5 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                    } else {
                        i7 = 2131237422;
                        str5 = "ic_volume_media";
                    }
                    iconRes = i7;
                    str2 = str5;
                } else {
                    str2 = str;
                }
                iconRes2 = iconRes;
            } else {
                if (z35 != 0) {
                    ss5 = oplusVolumeRow.getSs();
                    if (ss5 != null) {
                        if (isStreamMuted(ss5)) {
                            str5 = "ic_volume_notification_mute";
                            iconRes = oplusVolumeRow.getIconMuteRes();
                        } else {
                            str5 = "ic_volume_notification_on";
                        }
                        str2 = str5;
                    }
                    iconRes2 = iconRes;
                } else if (z16) {
                    if (((AudioDataRepository.AudioState) iconAbility.volumeInteractor.mAudioDataRepository.getAudioState().getValue()).isStreamInBluetoothSco) {
                        if (streamState.level > streamState.levelMin) {
                            i11 = 2131237404;
                            str4 = "ic_volume_bt_sco_on";
                        } else {
                            i11 = 2131237405;
                            str4 = "ic_volume_bt_sco_mute";
                        }
                        ss4 = oplusVolumeRow.getSs();
                        if (ss4 != null) {
                            ss4.name = 2131956668;
                        }
                        i9 = i11;
                        str2 = str4;
                    } else {
                        ss2 = oplusVolumeRow.getSs();
                        if (ss2 != null) {
                            if (((AudioDataRepository.AudioState) iconAbility.volumeInteractor.mAudioDataRepository.getAudioState().getValue()).isSpeakerphoneOn) {
                                i10 = 2131956681;
                            } else {
                                i10 = 2131956680;
                            }
                            ss2.name = i10;
                        }
                        i9 = 2131237453;
                        str2 = str;
                    }
                    ss3 = oplusVolumeRow.getSs();
                    if (ss3 != null) {
                        Util.setText(oplusVolumeRow.getNameText(), iconAbility.context.getString(ss3.name));
                    }
                    if (iconAbility.volumeInteractor.mOplusVolumeDialogView.isSliderShowing()) {
                        iconAbility.volumeInteractor.getVolumeListAdapter().updateShowingRow(oplusVolumeRow, 100);
                    }
                    iconRes2 = i9;
                } else if (z7) {
                    str2 = "ic_volume_alarm_multi_app";
                    iconRes2 = iconRes;
                } else if (z2) {
                    ss = oplusVolumeRow.getSs();
                    if (ss != null) {
                        zIsStreamMuted = isStreamMuted(ss);
                        z17 = streamState.routedToWired;
                        if (zIsStreamMuted) {
                            if (z17) {
                                iconRes2 = 2131237460;
                                str3 = str;
                            } else if (streamState.routedToBluetooth) {
                                str3 = str;
                                iconRes2 = 2131237415;
                            } else {
                                if (FeatureOption.isOplusAssistantVolumeSupport()) {
                                    i8 = 2131237398;
                                    str3 = "ic_volume_assistant_mute";
                                } else {
                                    i8 = 2131237407;
                                    str3 = "ic_volume_gemini_mute";
                                }
                                iconRes2 = i8;
                            }
                        } else if (z17) {
                            str3 = str;
                            iconRes2 = 2131237458;
                        } else if (streamState.routedToBluetooth) {
                            str3 = str;
                            iconRes2 = 2131237413;
                        } else {
                            if (FeatureOption.isOplusAssistantVolumeSupport()) {
                                i8 = 2131237397;
                                str3 = "ic_volume_assistant_on";
                            } else {
                                i8 = 2131237406;
                                str3 = "ic_volume_gemini_on";
                            }
                            iconRes2 = i8;
                        }
                        str2 = str3;
                    }
                } else if (streamState.dynamic) {
                    if (isStreamMuted(streamState)) {
                        iconRes2 = oplusVolumeRow.getIconMuteRes();
                    } else {
                        iconRes2 = oplusVolumeRow.getIconRes();
                    }
                    str2 = str;
                }
                str2 = str;
                iconRes2 = iconRes;
            }
        }
        if (iconAbility.volumeInteractor.mOplusVolumeDialogView.getMIsDefaultVolumeTheme()) {
            if (oplusVolumeRow.getJsonRes().length() > 0) {
                oplusVolumeRow.setJsonRes(str);
                icon16 = oplusVolumeRow.getIcon();
                if (icon16 != null) {
                    icon16.clearAnimation();
                }
            }
            icon15 = oplusVolumeRow.getIcon();
            if (icon15 != null) {
                icon15.setImageResource(iconRes2);
            }
            streamState = streamState;
            z14 = z14;
            i25 = 2131237442;
            i12 = 1;
            i13 = 2;
        } else {
            if (str2.length() == 0) {
                streamState = streamState;
                z14 = z14;
                iconRes2 = iconRes2;
                i12 = 1;
                i13 = 2;
            } else {
                if (z34) {
                    boolean zIsSliderShowing4 = iconAbility.volumeInteractor.mOplusVolumeDialogView.isSliderShowing();
                    oplusVolumeRowFindRow = iconAbility.volumeInteractor.findRow(3);
                    if (oplusVolumeRowFindRow == null) {
                        LogUtil.e$default(8, LogUtil.TAG_VOLUME, TAG, "updateMusicStreamIcon VolumeRow is null!");
                        streamState = streamState;
                        z14 = z14;
                        iconRes2 = iconRes2;
                    } else {
                        if (z14) {
                            ss10 = oplusVolumeRowFindRow.getSs();
                            if (ss10 != null) {
                                z23 = ss10.muted;
                                str6 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                                if (z23) {
                                }
                                if (LogUtil.getNormalDebug()) {
                                    z19 = z28;
                                } else {
                                    z19 = z28;
                                }
                                ss8 = oplusVolumeRowFindRow.getSs();
                                float f117 = 1.0f;
                                if (ss8 == null) {
                                    ss9 = oplusVolumeRowFindRow.getSs();
                                    if (ss9 != null) {
                                        i18 = ss9.levelMax;
                                    } else {
                                        i18 = 0;
                                    }
                                    float f118 = i18;
                                    i19 = (int) (0.33f * f118);
                                    i20 = (int) (f118 * 0.66f);
                                    if (z19) {
                                        i13 = 2;
                                        icon4 = oplusVolumeRowFindRow.getIcon();
                                        if (icon4 != null) {
                                            icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                        }
                                        oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                        oplusVolumeRowFindRow.setLastLevel(i17);
                                        if (i17 == 0) {
                                            icon6 = oplusVolumeRowFindRow.getIcon();
                                            i12 = 1;
                                            if (icon6 != null) {
                                                icon6.setFrame(1);
                                            }
                                        } else {
                                            i12 = 1;
                                            if (i17 <= 0) {
                                                if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        }
                                    } else {
                                        i13 = 2;
                                        icon4 = oplusVolumeRowFindRow.getIcon();
                                        if (icon4 != null) {
                                            icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                        }
                                        oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                        oplusVolumeRowFindRow.setLastLevel(i17);
                                        if (i17 == 0) {
                                            icon6 = oplusVolumeRowFindRow.getIcon();
                                            i12 = 1;
                                            if (icon6 != null) {
                                                icon6.setFrame(1);
                                            }
                                        } else {
                                            i12 = 1;
                                            if (i17 <= 0) {
                                                if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        }
                                    }
                                } else {
                                    ss9 = oplusVolumeRowFindRow.getSs();
                                    if (ss9 != null) {
                                        i18 = ss9.levelMax;
                                    } else {
                                        i18 = 0;
                                    }
                                    float f119 = i18;
                                    i19 = (int) (0.33f * f119);
                                    i20 = (int) (f119 * 0.66f);
                                    if (z19) {
                                        i13 = 2;
                                        icon4 = oplusVolumeRowFindRow.getIcon();
                                        if (icon4 != null) {
                                            icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                        }
                                        oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                        oplusVolumeRowFindRow.setLastLevel(i17);
                                        if (i17 == 0) {
                                            icon6 = oplusVolumeRowFindRow.getIcon();
                                            i12 = 1;
                                            if (icon6 != null) {
                                                icon6.setFrame(1);
                                            }
                                        } else {
                                            i12 = 1;
                                            if (i17 <= 0) {
                                                if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        }
                                    } else {
                                        i13 = 2;
                                        icon4 = oplusVolumeRowFindRow.getIcon();
                                        if (icon4 != null) {
                                            icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                        }
                                        oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                        oplusVolumeRowFindRow.setLastLevel(i17);
                                        if (i17 == 0) {
                                            icon6 = oplusVolumeRowFindRow.getIcon();
                                            i12 = 1;
                                            if (icon6 != null) {
                                                icon6.setFrame(1);
                                            }
                                        } else {
                                            i12 = 1;
                                            if (i17 <= 0) {
                                                if (i17 < i19) {
                                                    if (i17 >= i20) {
                                                        icon5.setFrame(83);
                                                    }
                                                } else if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        }
                                    }
                                }
                            } else {
                                str6 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                            }
                            VolumeDialogController.StreamState ss19 = oplusVolumeRowFindRow.getSs();
                            if (ss19 != null) {
                            }
                            if (LogUtil.getNormalDebug()) {
                                z19 = z28;
                            } else {
                                z19 = z28;
                            }
                            ss8 = oplusVolumeRowFindRow.getSs();
                            float f1110 = 1.0f;
                            if (ss8 == null) {
                                ss9 = oplusVolumeRowFindRow.getSs();
                                if (ss9 != null) {
                                    i18 = ss9.levelMax;
                                } else {
                                    i18 = 0;
                                }
                                float f1111 = i18;
                                i19 = (int) (0.33f * f1111);
                                i20 = (int) (f1111 * 0.66f);
                                if (z19) {
                                    i13 = 2;
                                    icon4 = oplusVolumeRowFindRow.getIcon();
                                    if (icon4 != null) {
                                        icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                    }
                                    oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                    oplusVolumeRowFindRow.setLastLevel(i17);
                                    if (i17 == 0) {
                                        icon6 = oplusVolumeRowFindRow.getIcon();
                                        i12 = 1;
                                        if (icon6 != null) {
                                            icon6.setFrame(1);
                                        }
                                    } else {
                                        i12 = 1;
                                        if (i17 <= 0) {
                                            if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 < i19) {
                                            if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    }
                                } else {
                                    i13 = 2;
                                    icon4 = oplusVolumeRowFindRow.getIcon();
                                    if (icon4 != null) {
                                        icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                    }
                                    oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                    oplusVolumeRowFindRow.setLastLevel(i17);
                                    if (i17 == 0) {
                                        icon6 = oplusVolumeRowFindRow.getIcon();
                                        i12 = 1;
                                        if (icon6 != null) {
                                            icon6.setFrame(1);
                                        }
                                    } else {
                                        i12 = 1;
                                        if (i17 <= 0) {
                                            if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 < i19) {
                                            if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    }
                                }
                            } else {
                                ss9 = oplusVolumeRowFindRow.getSs();
                                if (ss9 != null) {
                                    i18 = ss9.levelMax;
                                } else {
                                    i18 = 0;
                                }
                                float f1112 = i18;
                                i19 = (int) (0.33f * f1112);
                                i20 = (int) (f1112 * 0.66f);
                                if (z19) {
                                    i13 = 2;
                                    icon4 = oplusVolumeRowFindRow.getIcon();
                                    if (icon4 != null) {
                                        icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                    }
                                    oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                    oplusVolumeRowFindRow.setLastLevel(i17);
                                    if (i17 == 0) {
                                        icon6 = oplusVolumeRowFindRow.getIcon();
                                        i12 = 1;
                                        if (icon6 != null) {
                                            icon6.setFrame(1);
                                        }
                                    } else {
                                        i12 = 1;
                                        if (i17 <= 0) {
                                            if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 < i19) {
                                            if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    }
                                } else {
                                    i13 = 2;
                                    icon4 = oplusVolumeRowFindRow.getIcon();
                                    if (icon4 != null) {
                                        icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                    }
                                    oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                    oplusVolumeRowFindRow.setLastLevel(i17);
                                    if (i17 == 0) {
                                        icon6 = oplusVolumeRowFindRow.getIcon();
                                        i12 = 1;
                                        if (icon6 != null) {
                                            icon6.setFrame(1);
                                        }
                                    } else {
                                        i12 = 1;
                                        if (i17 <= 0) {
                                            if (i17 < i19) {
                                                if (i17 >= i20) {
                                                    icon5.setFrame(83);
                                                }
                                            } else if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 < i19) {
                                            if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    }
                                }
                            }
                        } else {
                            str6 = IC_ANIM_MEDIA_BLUETOOTH_MUTE;
                        }
                        if (LogUtil.getNormalDebug()) {
                            z19 = z28;
                        } else {
                            z19 = z28;
                        }
                        ss8 = oplusVolumeRowFindRow.getSs();
                        float f1113 = 1.0f;
                        if (ss8 == null) {
                            ss9 = oplusVolumeRowFindRow.getSs();
                            if (ss9 != null) {
                                i18 = ss9.levelMax;
                            } else {
                                i18 = 0;
                            }
                            float f1114 = i18;
                            i19 = (int) (0.33f * f1114);
                            i20 = (int) (f1114 * 0.66f);
                            if (z19) {
                                i13 = 2;
                                icon4 = oplusVolumeRowFindRow.getIcon();
                                if (icon4 != null) {
                                    icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                }
                                oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                oplusVolumeRowFindRow.setLastLevel(i17);
                                if (i17 == 0) {
                                    icon6 = oplusVolumeRowFindRow.getIcon();
                                    i12 = 1;
                                    if (icon6 != null) {
                                        icon6.setFrame(1);
                                    }
                                } else {
                                    i12 = 1;
                                    if (i17 <= 0) {
                                        if (i17 < i19) {
                                            if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    } else if (i17 < i19) {
                                        if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    } else if (i17 >= i20) {
                                        icon5.setFrame(83);
                                    }
                                }
                            } else {
                                i13 = 2;
                                icon4 = oplusVolumeRowFindRow.getIcon();
                                if (icon4 != null) {
                                    icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                }
                                oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                oplusVolumeRowFindRow.setLastLevel(i17);
                                if (i17 == 0) {
                                    icon6 = oplusVolumeRowFindRow.getIcon();
                                    i12 = 1;
                                    if (icon6 != null) {
                                        icon6.setFrame(1);
                                    }
                                } else {
                                    i12 = 1;
                                    if (i17 <= 0) {
                                        if (i17 < i19) {
                                            if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    } else if (i17 < i19) {
                                        if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    } else if (i17 >= i20) {
                                        icon5.setFrame(83);
                                    }
                                }
                            }
                        } else {
                            ss9 = oplusVolumeRowFindRow.getSs();
                            if (ss9 != null) {
                                i18 = ss9.levelMax;
                            } else {
                                i18 = 0;
                            }
                            float f1115 = i18;
                            i19 = (int) (0.33f * f1115);
                            i20 = (int) (f1115 * 0.66f);
                            if (z19) {
                                i13 = 2;
                                icon4 = oplusVolumeRowFindRow.getIcon();
                                if (icon4 != null) {
                                    icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                }
                                oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                oplusVolumeRowFindRow.setLastLevel(i17);
                                if (i17 == 0) {
                                    icon6 = oplusVolumeRowFindRow.getIcon();
                                    i12 = 1;
                                    if (icon6 != null) {
                                        icon6.setFrame(1);
                                    }
                                } else {
                                    i12 = 1;
                                    if (i17 <= 0) {
                                        if (i17 < i19) {
                                            if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    } else if (i17 < i19) {
                                        if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    } else if (i17 >= i20) {
                                        icon5.setFrame(83);
                                    }
                                }
                            } else {
                                i13 = 2;
                                icon4 = oplusVolumeRowFindRow.getIcon();
                                if (icon4 != null) {
                                    icon4.setAnimation("volumeui/ic_volume_media_light.json");
                                }
                                oplusVolumeRowFindRow.setJsonRes("ic_volume_media");
                                oplusVolumeRowFindRow.setLastLevel(i17);
                                if (i17 == 0) {
                                    icon6 = oplusVolumeRowFindRow.getIcon();
                                    i12 = 1;
                                    if (icon6 != null) {
                                        icon6.setFrame(1);
                                    }
                                } else {
                                    i12 = 1;
                                    if (i17 <= 0) {
                                        if (i17 < i19) {
                                            if (i17 >= i20) {
                                                icon5.setFrame(83);
                                            }
                                        } else if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    } else if (i17 < i19) {
                                        if (i17 >= i20) {
                                            icon5.setFrame(83);
                                        }
                                    } else if (i17 >= i20) {
                                        icon5.setFrame(83);
                                    }
                                }
                            }
                        }
                    }
                    i12 = 1;
                    i13 = 2;
                } else {
                    streamState = streamState;
                    z14 = z14;
                    iconRes2 = iconRes2;
                    z18 = z28;
                    i12 = 1;
                    i13 = 2;
                    if (z2) {
                        if (LogUtil.getNormalDebug()) {
                            String jsonRes5 = oplusVolumeRow.getJsonRes();
                            StringBuilder sb4 = new StringBuilder("updateAssistantStreamIcon jsonRes: ");
                            sb4.append((Object) str2);
                            sb4.append(",row.jsonRes: ");
                            sb4.append(jsonRes5);
                            sb4.append(" isDeviceChanged: ");
                            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("IconAbility-->", "SystemUi--Volume", sb4, z18);
                        }
                        if (!z18) {
                        }
                        icon = oplusVolumeRow.getIcon();
                        if (icon != null) {
                            icon.setAnimation("volumeui/" + ((Object) str2) + LIGHT_SUFFIX);
                        }
                        oplusVolumeRow.setJsonRes(str2);
                        iconAbility = this;
                        if (iconAbility.volumeInteractor.mOplusVolumeDialogView.isSliderShowing()) {
                            icon3 = oplusVolumeRow.getIcon();
                            if (icon3 != null) {
                                icon3.playAnimation();
                            }
                        } else {
                            icon2 = oplusVolumeRow.getIcon();
                            if (icon2 != null) {
                                icon2.setFrame(35);
                            }
                        }
                    } else {
                        iconAbility = this;
                        FlavorOneFeatureOption.INSTANCE.getClass();
                        if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                            if (!z7) {
                            }
                        } else if (!z7) {
                        }
                        iconRes2 = iconRes2;
                        if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                            oplusVolumeRow.setLastIconRes(iconRes2);
                            if (i15 == 0) {
                                icon9.setImageResource(iconRes2);
                            }
                        }
                        i25 = 2131237442;
                    }
                    i15 = i12;
                    iconRes2 = iconRes2;
                    if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                        oplusVolumeRow.setLastIconRes(iconRes2);
                        if (i15 == 0) {
                            icon9.setImageResource(iconRes2);
                        }
                    }
                    i25 = 2131237442;
                }
                iconAbility = this;
                i15 = i12;
                iconRes2 = iconRes2;
                if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                    oplusVolumeRow.setLastIconRes(iconRes2);
                    if (i15 == 0) {
                        icon9.setImageResource(iconRes2);
                    }
                }
                i25 = 2131237442;
            }
            i15 = 0;
            iconRes2 = iconRes2;
            if (oplusVolumeRow.getLastIconRes() != iconRes2) {
                oplusVolumeRow.setLastIconRes(iconRes2);
                if (i15 == 0) {
                    icon9.setImageResource(iconRes2);
                }
            }
            i25 = 2131237442;
        }
        if (iconRes2 == i25) {
            i26 = 3;
        } else if (iconRes2 != 2131237415) {
            i26 = i13;
        } else {
            i26 = i13;
        }
        oplusVolumeRow.setIconState(i26);
        if (z14) {
            if (z31) {
                vibrateWhenSilentToggleState = VolumeSettingsValueProxy.Companion.getVibrateWhenSilentToggleState(iconAbility.context);
                streamState2 = streamState;
                z27 = streamState2.muted;
                if (vibrateWhenSilentToggleState != 0) {
                    if (z27) {
                        icon14 = oplusVolumeRow.getIcon();
                        if (icon14 != null) {
                            icon14.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                        }
                    } else {
                        icon14 = oplusVolumeRow.getIcon();
                        if (icon14 != null) {
                            icon14.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                        }
                    }
                } else if (z27) {
                    icon13 = oplusVolumeRow.getIcon();
                    if (icon13 != null) {
                        icon13.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                    }
                } else {
                    icon13 = oplusVolumeRow.getIcon();
                    if (icon13 != null) {
                        icon13.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                    }
                }
            } else {
                streamState2 = streamState;
                if (z8) {
                    icon12 = oplusVolumeRow.getIcon();
                    if (icon12 != null) {
                        icon12.setContentDescription(iconAbility.getStreamLabelH(streamState2));
                    }
                } else if (streamState2.muted) {
                    icon11 = oplusVolumeRow.getIcon();
                    if (icon11 != null) {
                        icon11.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                    }
                } else {
                    icon11 = oplusVolumeRow.getIcon();
                    if (icon11 != null) {
                        icon11.setContentDescription(iconAbility.context.getString(2131957222, iconAbility.getStreamLabelH(streamState2)));
                    }
                }
            }
        } else {
            streamState2 = streamState;
            icon10 = oplusVolumeRow.getIcon();
            if (icon10 != null) {
                icon10.setContentDescription(iconAbility.getStreamLabelH(streamState2));
            }
        }
        i27 = streamState2.level;
        if (i27 > 0) {
            oplusVolumeRow.setLastAudibleLevel(i27);
        }
        i28 = 0;
        if (z14) {
            oplusVolumeRow.setTracking(false);
        }
        if (z312) {
        }
        z25 = i3;
        if (z14) {
            z26 = 0;
        } else {
            z26 = 0;
        }
        if (LogUtil.getNormalDebug()) {
            StringBuilder sbM7 = TextFieldImplKt$.ExternalSyntheticOutline0.m("zenMuted: ", ", isNotificationStream: ", ", isInternalVibrate: ", z14, z35);
            KeyguardLogger$.ExternalSyntheticOutline0.m(", isInternalSilent: ", ", isOplusThreekeysSilentOrVibrate: ", sbM7, z25, i4);
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("IconAbility-->", "SystemUi--Volume", sbM7, z24);
        }
        ss11 = oplusVolumeRow.getSs();
        if (ss11 != null) {
            i28 = ss11.level;
        }
        i29 = i28;
        oplusVolumeDialogImpl = iconAbility.volumeInteractor;
        oplusVolumeDialogImpl.getClass();
        VolumeFeatureOption.INSTANCE.getClass();
        if (VolumeFeatureOption.isOplusSuperVolume()) {
        }
        oplusVolumeDialogImpl.updateVolumeRowSliderH(oplusVolumeRow, z26, i29, 0, z);
    }
}
