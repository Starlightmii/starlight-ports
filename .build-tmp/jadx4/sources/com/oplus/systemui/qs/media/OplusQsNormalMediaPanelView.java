package com.oplus.systemui.qs.media;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.plugins.qs.IHostAbility;
import com.oplus.systemui.qs.base.spotlight.SharedSpotLightEffect;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.media.multilight.OplusQsMediaBackgroundDrawable;
import com.oplusos.systemui.common.model.MediaPanelData;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQsNormalMediaPanelView extends OplusQsBaseMediaPanelView {
    public static final Companion Companion = new Companion();
    private static final SpanSize DEFAULT_SIZE = SpanCache.INSTANCE.spanSize(2, 2);
    private static final String TAG = "OplusQsNormalMediaPanelView";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.media.OplusQsNormalMediaPanelView$bindMediaProgress$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ MediaController $mediaController;
        public /* synthetic */ OplusQsNormalMediaPanelView this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.media.OplusQsNormalMediaPanelView$bindMediaProgress$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class RunnableC00801 implements Runnable {
            public /* synthetic */ int $duration;
            public /* synthetic */ int $position;
            public /* synthetic */ OplusQsNormalMediaPanelView this$0;

            @Override // java.lang.Runnable
            public final void run() {
                MediaPanelData mediaPanelData = this.this$0.getMediaPanelData();
                if (mediaPanelData != null) {
                    mediaPanelData.setCurrentTime(DateUtils.formatElapsedTime(((long) this.$position) / 1000));
                }
                MediaPanelData mediaPanelData2 = this.this$0.getMediaPanelData();
                if (mediaPanelData2 != null) {
                    mediaPanelData2.setTotalTime(DateUtils.formatElapsedTime(((long) this.$duration) / 1000));
                }
                MediaPanelData mediaPanelData3 = this.this$0.getMediaPanelData();
                if (mediaPanelData3 != null) {
                    mediaPanelData3.setPosition(Integer.valueOf(this.$position));
                }
                MediaPanelData mediaPanelData4 = this.this$0.getMediaPanelData();
                if (mediaPanelData4 != null) {
                    mediaPanelData4.setLen(Integer.valueOf(this.$duration));
                }
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            PlaybackState playbackState = this.$mediaController.getPlaybackState();
            int position = playbackState != null ? (int) playbackState.getPosition() : 0;
            MediaMetadata metadata = this.$mediaController.getMetadata();
            int i = metadata != null ? (int) metadata.getLong("android.media.metadata.DURATION") : 0;
            Handler handler = new Handler(Looper.getMainLooper());
            OplusQsNormalMediaPanelView oplusQsNormalMediaPanelView = this.this$0;
            RunnableC00801 runnableC00801 = new RunnableC00801();
            runnableC00801.this$0 = oplusQsNormalMediaPanelView;
            runnableC00801.$position = position;
            runnableC00801.$duration = i;
            VarHandle.storeStoreFence();
            handler.post(runnableC00801);
        }
    }

    public /* synthetic */ OplusQsNormalMediaPanelView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    private final boolean setTextLayout(int i) {
        TextView textViewM288getTitle = m288getTitle();
        boolean z = false;
        if (textViewM288getTitle != null) {
            ConstraintLayout.LayoutParams layoutParams = textViewM288getTitle.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams2 = layoutParams instanceof ConstraintLayout.LayoutParams ? layoutParams : null;
            if (layoutParams2 == null || layoutParams2.getMarginStart() != i) {
                getTmpConstrainSet().connect(textViewM288getTitle.getId(), 6, 0, 6, i);
                z = true;
            }
        }
        TextView textViewM287getText = m287getText();
        if (textViewM287getText != null) {
            ConstraintLayout.LayoutParams layoutParams3 = textViewM287getText.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams4 = layoutParams3 instanceof ConstraintLayout.LayoutParams ? layoutParams3 : null;
            if (layoutParams4 == null || layoutParams4.getMarginStart() != i) {
                getTmpConstrainSet().connect(textViewM287getText.getId(), 6, 0, 6, i);
                z = true;
            }
        }
        TextView textViewM285getDefaultTip = m285getDefaultTip();
        if (textViewM285getDefaultTip == null) {
            return z;
        }
        ConstraintLayout.LayoutParams layoutParams5 = textViewM285getDefaultTip.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams6 = layoutParams5 instanceof ConstraintLayout.LayoutParams ? layoutParams5 : null;
        if (layoutParams6 != null && layoutParams6.getMarginStart() == i) {
            return z;
        }
        getTmpConstrainSet().connect(textViewM285getDefaultTip.getId(), 6, 0, 6, i);
        return true;
    }

    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public void bindMediaProgress(MediaController mediaController) {
        Handler bgHandler = getBgHandler();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$mediaController = mediaController;
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        bgHandler.post(anonymousClass1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dispatchDraw(Canvas canvas) {
        super/*androidx.constraintlayout.widget.ConstraintLayout*/.dispatchDraw(canvas);
        SharedSpotLightEffect.draw(this, canvas);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (QsColorUtil.getSeparateNotificationAndQSState()) {
            return super/*android.view.ViewGroup*/.dispatchTouchEvent(motionEvent);
        }
        SharedSpotLightEffect.onTouchEvent(this, motionEvent);
        return super/*android.view.ViewGroup*/.dispatchTouchEvent(motionEvent);
    }

    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public final void editModeChange(boolean z) {
        super.editModeChange(z);
        setEditMode(z);
        ImageButton imageButtonM286getMediaOutputBtn = m286getMediaOutputBtn();
        if (imageButtonM286getMediaOutputBtn != null) {
            imageButtonM286getMediaOutputBtn.setClickable(!z);
        }
        OplusQsPlayPauseButton playOrPauseBtn = getPlayOrPauseBtn();
        if (playOrPauseBtn != null) {
            playOrPauseBtn.setClickable(!z);
        }
        OplusLottieButton preBtn = getPreBtn();
        if (preBtn != null) {
            preBtn.setClickable(!z);
        }
        OplusLottieButton nextBtn = getNextBtn();
        if (nextBtn != null) {
            nextBtn.setClickable(!z);
        }
    }

    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public /* bridge */ /* synthetic */ View getCarouselLayout() {
        return super.getCarouselLayout();
    }

    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public /* bridge */ /* synthetic */ List getChildViews() {
        return super.getChildViews();
    }

    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public /* bridge */ /* synthetic */ View getMediaPageIndicator() {
        return super.getMediaPageIndicator();
    }

    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public /* bridge */ /* synthetic */ int getOffsetPixels() {
        return super.getOffsetPixels();
    }

    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public /* bridge */ /* synthetic */ Rect getPaddingForLaunchAnimation() {
        return super/*com.android.systemui.animation.LaunchableView*/.getPaddingForLaunchAnimation();
    }

    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public /* bridge */ /* synthetic */ View getProgressiveBlurOverlay() {
        return super.getProgressiveBlurOverlay();
    }

    public int getSpotLightType() {
        return 2;
    }

    /* JADX WARN: Code duplicated, block: B:51:0x0150 A[PHI: r3
      0x0150: PHI (r3v3 boolean) = (r3v2 boolean), (r3v7 boolean) binds: [B:35:0x00ff, B:50:0x014e] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public final void updateCapsuleLayout() {
        int i;
        IHostAbility.LayoutConstraint qSCapsuleLayoutConstraint = getQSCapsuleLayoutConstraint();
        boolean z = false;
        setMediaTextSize(getDimensionPixelSize(2131171121, qSCapsuleLayoutConstraint, false), getDimensionPixelSize(2131171120, qSCapsuleLayoutConstraint, false));
        getTmpConstrainSet().clone(getMediaPanelLayout());
        boolean z2 = true;
        int dimensionPixelSize = getDimensionPixelSize(2131171114, qSCapsuleLayoutConstraint, true);
        boolean mediaLayout = setMediaLayout(dimensionPixelSize, getDimensionPixelSize(2131171115, qSCapsuleLayoutConstraint, false), getDimensionPixelSize(2131171116, qSCapsuleLayoutConstraint, false));
        int dimensionPixelSize2 = getDimensionPixelSize(2131171112, qSCapsuleLayoutConstraint, true);
        int dimensionPixelSize3 = getDimensionPixelSize(2131171113, qSCapsuleLayoutConstraint, false);
        OplusLottieButton preBtn = getPreBtn();
        if (preBtn != null) {
            ConstraintLayout.LayoutParams layoutParams = preBtn.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams2 = layoutParams instanceof ConstraintLayout.LayoutParams ? layoutParams : null;
            if (layoutParams2 == null || ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin != dimensionPixelSize2) {
                getTmpConstrainSet().constrainWidth(preBtn.getId(), dimensionPixelSize3);
                getTmpConstrainSet().constrainHeight(preBtn.getId(), dimensionPixelSize3);
                getTmpConstrainSet().connect(preBtn.getId(), 4, 0, 4, dimensionPixelSize2);
                i = dimensionPixelSize2;
                z = true;
            } else {
                i = dimensionPixelSize2;
            }
            ConstraintLayout.LayoutParams layoutParams3 = preBtn.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams4 = layoutParams3 instanceof ConstraintLayout.LayoutParams ? layoutParams3 : null;
            if (layoutParams4 == null || layoutParams4.getMarginStart() != dimensionPixelSize) {
                getTmpConstrainSet().connect(preBtn.getId(), 6, 0, 6, dimensionPixelSize);
                z = true;
            }
        } else {
            i = dimensionPixelSize2;
        }
        OplusQsPlayPauseButton playOrPauseBtn = getPlayOrPauseBtn();
        if (playOrPauseBtn != null) {
            ConstraintLayout.LayoutParams layoutParams5 = playOrPauseBtn.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams6 = layoutParams5 instanceof ConstraintLayout.LayoutParams ? layoutParams5 : null;
            if (layoutParams6 == null || ((ViewGroup.MarginLayoutParams) layoutParams6).bottomMargin != i) {
                getTmpConstrainSet().constrainWidth(playOrPauseBtn.getId(), dimensionPixelSize3);
                getTmpConstrainSet().constrainHeight(playOrPauseBtn.getId(), dimensionPixelSize3);
                int i2 = i;
                getTmpConstrainSet().connect(playOrPauseBtn.getId(), 4, 0, 4, i2);
                i = i2;
                z = true;
            }
        }
        OplusLottieButton nextBtn = getNextBtn();
        if (nextBtn != null) {
            ConstraintLayout.LayoutParams layoutParams7 = nextBtn.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams8 = layoutParams7 instanceof ConstraintLayout.LayoutParams ? layoutParams7 : null;
            if (layoutParams8 == null || ((ViewGroup.MarginLayoutParams) layoutParams8).bottomMargin != i) {
                getTmpConstrainSet().constrainWidth(nextBtn.getId(), dimensionPixelSize3);
                getTmpConstrainSet().constrainHeight(nextBtn.getId(), dimensionPixelSize3);
                getTmpConstrainSet().connect(nextBtn.getId(), 4, 0, 4, i);
                z = true;
            }
            ConstraintLayout.LayoutParams layoutParams9 = nextBtn.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams10 = layoutParams9 instanceof ConstraintLayout.LayoutParams ? layoutParams9 : null;
            if (layoutParams10 == null || layoutParams10.getMarginEnd() != dimensionPixelSize) {
                getTmpConstrainSet().connect(nextBtn.getId(), 7, 0, 7, dimensionPixelSize);
            } else {
                z2 = z;
            }
        } else {
            z2 = z;
        }
        boolean textLayout = setTextLayout(dimensionPixelSize);
        if (mediaLayout || z2 || textLayout) {
            getTmpConstrainSet().applyTo(getMediaPanelLayout());
        }
    }

    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public /* bridge */ /* synthetic */ void updateFinishExpand(String str) {
        super.updateFinishExpand(str);
    }

    public void updateSpotLightPath(Path path, Object obj) {
        CornerOutlineProvider pathProvider = getTransitionDrawable().getPathProvider();
        OplusQsMediaBackgroundDrawable transitionDrawable = getTransitionDrawable();
        CornerOutlineProvider.Companion companion = CornerOutlineProvider.Companion;
        pathProvider.updatePath(transitionDrawable, path, null, obj);
    }

    public OplusQsNormalMediaPanelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public OplusQsNormalMediaPanelView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public OplusQsNormalMediaPanelView(Context context) {
        this(context, null, 0, 0, 14, null);
    }

    public OplusQsNormalMediaPanelView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
