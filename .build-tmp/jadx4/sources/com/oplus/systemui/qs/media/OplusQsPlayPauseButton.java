package com.oplus.systemui.qs.media;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import com.oplus.anim.EffectiveAnimationView;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsPlayPauseButton extends OplusLottieButton {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsPlayPauseButton";
    private Integer colorFilter;
    private OplusQsPlayPauseButton$compositionLoadedListener$1 compositionLoadedListener;
    public int curLottieRes;
    private PlayData curPlayData;
    public boolean isEditMode;
    private PlayData nextPlayData;
    public int state;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class PlayData {
        public final int imageResBeforePlay;
        public final boolean isFromClick;
        public final int lottieResPlay;
        public final int lottieResShow;

        public PlayData(int i, int i2, int i3, boolean z) {
            this.lottieResPlay = i;
            this.lottieResShow = i2;
            this.imageResBeforePlay = i3;
            this.isFromClick = z;
        }

        public final PlayData copy(int i, int i2, int i3, boolean z) {
            return new PlayData(i, i2, i3, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PlayData)) {
                return false;
            }
            PlayData playData = (PlayData) obj;
            return this.lottieResPlay == playData.lottieResPlay && this.lottieResShow == playData.lottieResShow && this.imageResBeforePlay == playData.imageResBeforePlay && this.isFromClick == playData.isFromClick;
        }

        public final int getImageResBeforePlay() {
            return this.imageResBeforePlay;
        }

        public final int getLottieResPlay() {
            return this.lottieResPlay;
        }

        public final int getLottieResShow() {
            return this.lottieResShow;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isFromClick) + MonoSpline$.ExternalSyntheticOutline0.m(this.imageResBeforePlay, MonoSpline$.ExternalSyntheticOutline0.m(this.lottieResShow, Integer.hashCode(this.lottieResPlay) * 31, 31), 31);
        }

        public String toString() {
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("PlayData(lottieResPlay=", this.lottieResPlay, this.lottieResShow, ", lottieResShow=", ", imageResBeforePlay=");
            sbM.append(this.imageResBeforePlay);
            sbM.append(", isFromClick=");
            sbM.append(this.isFromClick);
            sbM.append(")");
            return sbM.toString();
        }
    }

    public OplusQsPlayPauseButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.state = 2;
        this.curPlayData = new PlayData(2131886226, getLottieRes(2), getImageRes(this.state), false);
        OplusQsPlayPauseButton$compositionLoadedListener$1 oplusQsPlayPauseButton$compositionLoadedListener$1 = new OplusQsPlayPauseButton$compositionLoadedListener$1();
        oplusQsPlayPauseButton$compositionLoadedListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.compositionLoadedListener = oplusQsPlayPauseButton$compositionLoadedListener$1;
        EffectiveAnimationView lottieAnimationView = getLottieAnimationView();
        OplusQsPlayPauseButton$compositionLoadedListener$1 oplusQsPlayPauseButton$compositionLoadedListener$2 = this.compositionLoadedListener;
        if (lottieAnimationView.composition != null) {
            oplusQsPlayPauseButton$compositionLoadedListener$2.onCompositionLoaded();
        }
        lottieAnimationView.effectiveOnCompositionLoadedListeners.add(oplusQsPlayPauseButton$compositionLoadedListener$2);
        hideLottieView();
        setImageResourceInternal(getImageRes(this.state));
        setLottieRes(Integer.valueOf(this.curPlayData.getLottieResShow()));
    }

    public static int getLottieRes(int i) {
        return i == 2 ? 2131886225 : 2131886226;
    }

    private final void setImageResourceInternal(int i) {
        getImageView().setImageResource(i);
    }

    public final void addPlayData(PlayData playData) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsPlayPauseButton-->".concat("addPlayData: " + isAnimating() + " " + playData + " " + this.curPlayData));
        }
        boolean zIsAnimating = isAnimating();
        PlayData playData2 = this.curPlayData;
        if (zIsAnimating) {
            if (playData.equals(playData2)) {
                playData = null;
            }
            this.nextPlayData = playData;
            return;
        }
        boolean z = playData2.isFromClick;
        this.curPlayData = playData;
        this.nextPlayData = null;
        if (z && playData.getLottieResPlay() == this.curLottieRes) {
            hideLottieView();
            setImageResourceInternal(getImageRes(this.state));
            setLottieRes(Integer.valueOf(playData.getLottieResShow()));
        } else {
            setImageResourceInternal(playData.getImageResBeforePlay());
            if (playData.getLottieResPlay() == this.curLottieRes) {
                playLottieAnimationInternal();
            } else {
                setLottieRes(Integer.valueOf(playData.getLottieResPlay()));
            }
        }
    }

    public final int getImageRes(int i) {
        boolean z = this.isEditMode;
        if (i == 2) {
            return z ? 2131234393 : 2131234392;
        }
        return z ? 2131234391 : 2131234390;
    }

    @Override // com.oplus.systemui.qs.media.OplusLottieButton
    public final void handleAnimationEnd() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsPlayPauseButton-->".concat("handleAnimationEnd: " + isAnimating() + " " + this.curPlayData + " " + this.nextPlayData));
        }
        if (isAnimating()) {
            return;
        }
        if (this.curPlayData.isFromClick && this.nextPlayData == null) {
            return;
        }
        hideLottieView();
        PlayData playData = this.nextPlayData;
        this.nextPlayData = null;
        if (playData == null) {
            setImageResourceInternal(getImageRes(this.state));
            setLottieRes(Integer.valueOf(this.curPlayData.getLottieResShow()));
            return;
        }
        this.curPlayData = playData;
        if (playData.getLottieResPlay() == this.curLottieRes) {
            setImageResourceInternal(getImageRes(this.state));
            setLottieRes(Integer.valueOf(playData.getLottieResShow()));
        } else {
            setImageResourceInternal(playData.getImageResBeforePlay());
            setLottieRes(Integer.valueOf(playData.getLottieResPlay()));
        }
    }

    public final boolean isAnimating() {
        return getLottieAnimationView().isAnimating();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setImageResourceInternal(getImageRes(this.state));
    }

    @Override // com.oplus.systemui.qs.media.OplusLottieButton
    public final void playLottieAnimation() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsPlayPauseButton-->".concat("playLottieAnimation: " + isAnimating() + " " + this.curPlayData));
        }
        addPlayData(new PlayData(getLottieRes(this.state), getLottieRes(this.state), getImageRes(this.state), true));
    }

    public final void playLottieAnimationInternal() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQsPlayPauseButton-->".concat("playLottieAnimationInternal: " + isAnimating() + " " + this.curPlayData));
        }
        if (isAnimating()) {
            return;
        }
        boolean z = this.curPlayData.isFromClick;
        super.playLottieAnimation();
    }

    public final void setEditMode(boolean z) {
        this.isEditMode = z;
    }

    @Override // com.oplus.systemui.qs.media.OplusLottieButton
    public void setImageResource(int i) {
    }

    @Override // com.oplus.systemui.qs.media.OplusLottieButton
    public void setLottieColorFilter(int i) {
        super.setLottieColorFilter(i);
        this.colorFilter = Integer.valueOf(i);
    }

    @Override // com.oplus.systemui.qs.media.OplusLottieButton
    public void setLottieRes(Integer num) {
        if (num != null) {
            if (num.intValue() == this.curLottieRes) {
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusQsPlayPauseButton-->".concat("setLottieRes: res = " + num));
            }
            this.curLottieRes = num.intValue();
            getLottieAnimationView().setFrame(0);
            getLottieAnimationView().setAnimation(num.intValue());
            Integer num2 = this.colorFilter;
            if (num2 != null) {
                super.setLottieColorFilter(num2.intValue());
            }
        }
    }

    public final void setPlaybackState(int i) {
        if (QsColorUtil.isGlobalThemeApplied(getContext())) {
            this.state = i;
            setImageResourceInternal(getImageRes(i));
        } else if (this.state != i || this.curPlayData.isFromClick) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("setPlaybackState: preState = ", this.state, i, " curState = ", "OplusQsPlayPauseButton-->"));
            }
            this.state = i;
            int i2 = i == 2 ? 3 : 2;
            addPlayData(new PlayData(getLottieRes(i2), getLottieRes(i), getImageRes(i2), false));
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public OplusQsPlayPauseButton(Context context) {
        AttributeSet attributeSet = null;
        this(context, attributeSet, 2, attributeSet);
    }

    public /* synthetic */ OplusQsPlayPauseButton(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }
}
