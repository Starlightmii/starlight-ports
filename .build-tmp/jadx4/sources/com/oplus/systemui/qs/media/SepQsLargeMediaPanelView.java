package com.oplus.systemui.qs.media;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.android.settingslib.Utils;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;
import com.android.systemui.plugins.qs.IHostAbility;
import com.coui.appcompat.seekbar.COUISeekBar;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.separate.OplusSeparateQSManager;
import com.oplusos.systemui.common.model.MediaPanelData;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import java.lang.invoke.VarHandle;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepQsLargeMediaPanelView extends OplusQsBaseMediaPanelView {
    public static final Companion Companion = new Companion();
    private static final SpanSize DEFAULT_SIZE = SpanCache.INSTANCE.spanSize(4, 2);
    private TextView currentTime;
    private LinearLayout llContainer;
    public OplusQsMediaPanelViewSeekBarObserver mediaPanelViewSeekBarObserver;
    private COUISeekBar seekBar;
    private SeekBarViewModel seekBarViewModel;
    private TextView totalTime;

    public /* synthetic */ SepQsLargeMediaPanelView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    private final void setSeekTimeTextSize(int i) {
        TextView textView = this.currentTime;
        if (textView != null && textView != null) {
            textView.setTextSize(0, i);
        }
        TextView textView2 = this.totalTime;
        if (textView2 == null || textView2 == null) {
            return;
        }
        textView2.setTextSize(0, i);
    }

    private final boolean setTextLayout(int i) {
        AppCompatImageView appCompatImageViewM284getCoverImg = m284getCoverImg();
        boolean z = false;
        if ((appCompatImageViewM284getCoverImg != null ? Integer.valueOf(appCompatImageViewM284getCoverImg.getId()) : null) != null) {
            TextView textViewM288getTitle = m288getTitle();
            if (textViewM288getTitle != null) {
                ConstraintLayout.LayoutParams layoutParams = textViewM288getTitle.getLayoutParams();
                ConstraintLayout.LayoutParams layoutParams2 = layoutParams instanceof ConstraintLayout.LayoutParams ? layoutParams : null;
                if (layoutParams2 == null || layoutParams2.getMarginStart() != i) {
                    getTmpConstrainSet().connect(textViewM288getTitle.getId(), 6, 2131364329, 7, i);
                    z = true;
                }
            }
            TextView textViewM287getText = m287getText();
            if (textViewM287getText != null) {
                ConstraintLayout.LayoutParams layoutParams3 = textViewM287getText.getLayoutParams();
                ConstraintLayout.LayoutParams layoutParams4 = layoutParams3 instanceof ConstraintLayout.LayoutParams ? layoutParams3 : null;
                if (layoutParams4 == null || layoutParams4.getMarginStart() != i) {
                    getTmpConstrainSet().connect(textViewM287getText.getId(), 6, 2131364329, 7, i);
                    z = true;
                }
            }
            TextView textViewM285getDefaultTip = m285getDefaultTip();
            if (textViewM285getDefaultTip != null) {
                ConstraintLayout.LayoutParams layoutParams5 = textViewM285getDefaultTip.getLayoutParams();
                ConstraintLayout.LayoutParams layoutParams6 = layoutParams5 instanceof ConstraintLayout.LayoutParams ? layoutParams5 : null;
                if (layoutParams6 != null && layoutParams6.getMarginStart() == i) {
                    return z;
                }
                getTmpConstrainSet().connect(textViewM285getDefaultTip.getId(), 6, 2131364329, 7, i);
                return true;
            }
        }
        return z;
    }

    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public void bindMediaProgress(MediaController mediaController) {
        PlaybackState playbackState = mediaController.getPlaybackState();
        int position = playbackState != null ? (int) playbackState.getPosition() : 0;
        MediaMetadata metadata = mediaController.getMetadata();
        int i = metadata != null ? (int) metadata.getLong("android.media.metadata.DURATION") : 0;
        String elapsedTime = DateUtils.formatElapsedTime(((long) position) / 1000);
        String elapsedTime2 = DateUtils.formatElapsedTime(((long) i) / 1000);
        COUISeekBar cOUISeekBar = this.seekBar;
        if (cOUISeekBar != null) {
            cOUISeekBar.setProgress(position);
        }
        TextView textView = this.currentTime;
        if (textView != null) {
            textView.setText(elapsedTime);
        }
        COUISeekBar cOUISeekBar2 = this.seekBar;
        if (cOUISeekBar2 != null) {
            cOUISeekBar2.setMax(i);
        }
        TextView textView2 = this.totalTime;
        if (textView2 != null) {
            textView2.setText(elapsedTime2);
        }
        SeekBarViewModel seekBarViewModel = this.seekBarViewModel;
        if (seekBarViewModel != null) {
            seekBarViewModel.setListening(true);
        }
        COUISeekBar cOUISeekBar3 = this.seekBar;
        if (cOUISeekBar3 != null) {
            cOUISeekBar3.setClickable(!this.isEditMode);
        }
        COUISeekBar cOUISeekBar4 = this.seekBar;
        if (cOUISeekBar4 != null) {
            cOUISeekBar4.setEnabled(!this.isEditMode);
        }
        COUISeekBar cOUISeekBar5 = this.seekBar;
        if (cOUISeekBar5 != null) {
            cOUISeekBar5.setFocusable(true ^ this.isEditMode);
        }
        MediaPanelData mediaPanelData = getMediaPanelData();
        if (mediaPanelData != null) {
            mediaPanelData.setCurrentTime(elapsedTime);
        }
        MediaPanelData mediaPanelData2 = getMediaPanelData();
        if (mediaPanelData2 != null) {
            mediaPanelData2.setTotalTime(elapsedTime2);
        }
        MediaPanelData mediaPanelData3 = getMediaPanelData();
        if (mediaPanelData3 != null) {
            mediaPanelData3.setPosition(Integer.valueOf(position));
        }
        MediaPanelData mediaPanelData4 = getMediaPanelData();
        if (mediaPanelData4 != null) {
            mediaPanelData4.setLen(Integer.valueOf(i));
        }
        Executor executor = (Executor) Dependency.sDependency.getDependencyInner(Dependency.BACKGROUND_EXECUTOR);
        bindMediaProgress.1 r1 = new bindMediaProgress.1();
        r1.this$0 = this;
        r1.$mediaController = mediaController;
        VarHandle.storeStoreFence();
        executor.execute(r1);
    }

    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public final void editModeChange(boolean z) {
        super.editModeChange(z);
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
        COUISeekBar cOUISeekBar = this.seekBar;
        if (cOUISeekBar != null) {
            cOUISeekBar.setClickable(!z);
        }
        COUISeekBar cOUISeekBar2 = this.seekBar;
        if (cOUISeekBar2 != null) {
            cOUISeekBar2.setEnabled(!z);
        }
        COUISeekBar cOUISeekBar3 = this.seekBar;
        if (cOUISeekBar3 != null) {
            cOUISeekBar3.setFocusable(!z);
        }
        SeekBarViewModel seekBarViewModel = this.seekBarViewModel;
        if (seekBarViewModel != null) {
            seekBarViewModel.setIsEnableTouch(z);
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

    public final TextView getCurrentTime() {
        return this.currentTime;
    }

    public final LinearLayout getLlContainer() {
        return this.llContainer;
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public IHostAbility.LayoutConstraint getQSCapsuleLayoutConstraint() {
        return ((OplusSeparateQSManager) DependencyEx.sDependency.getDependency(OplusSeparateQSManager.class)).getLayoutConstraint(getContext(), DEFAULT_SIZE, this.isEditMode);
    }

    public final COUISeekBar getSeekBar() {
        return this.seekBar;
    }

    public final TextView getTotalTime() {
        return this.totalTime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public void initSeekBarParams(SeekBarViewModel.Factory factory) {
        LiveData progress;
        this.mediaPanelViewSeekBarObserver = new OplusQsMediaPanelViewSeekBarObserver(((ViewGroup) this).mContext, this);
        SeekBarViewModel seekBarViewModelCreate = factory.create();
        this.seekBarViewModel = seekBarViewModelCreate;
        if (seekBarViewModelCreate != null) {
            seekBarViewModelCreate.setInterceptUpdateWhenTrack(true);
        }
        SeekBarViewModel seekBarViewModel = this.seekBarViewModel;
        if (seekBarViewModel != null && (progress = seekBarViewModel.getProgress()) != null) {
            Observer observer = this.mediaPanelViewSeekBarObserver;
            if (observer == null) {
                observer = null;
            }
            progress.observeForever(observer);
        }
        COUISeekBar cOUISeekBar = this.seekBar;
        if (cOUISeekBar != null) {
            cOUISeekBar.setClickable(!this.isEditMode);
            cOUISeekBar.setEnabled(!this.isEditMode);
            cOUISeekBar.setFocusable(true ^ this.isEditMode);
            SeekBarViewModel seekBarViewModel2 = this.seekBarViewModel;
            if (seekBarViewModel2 != null) {
                seekBarViewModel2.setIsEnableTouch(this.isEditMode);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public final void initView() {
        COUISeekBar cOUISeekBarRequireViewById = requireViewById(2131364360);
        this.seekBar = cOUISeekBarRequireViewById;
        if (cOUISeekBarRequireViewById != null) {
            cOUISeekBarRequireViewById.setThumbOffset(0);
        }
        this.currentTime = (TextView) requireViewById(2131364365);
        this.totalTime = (TextView) requireViewById(2131364351);
        this.llContainer = (LinearLayout) requireViewById(2131363752);
    }

    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public final void removeObserver$1() {
        LiveData progress;
        SeekBarViewModel seekBarViewModel = this.seekBarViewModel;
        if (seekBarViewModel == null || (progress = seekBarViewModel.getProgress()) == null) {
            return;
        }
        Observer observer = this.mediaPanelViewSeekBarObserver;
        if (observer == null) {
            observer = null;
        }
        progress.removeObserver(observer);
    }

    public final void setCurrentTime(TextView textView) {
        this.currentTime = textView;
    }

    public final void setLlContainer(LinearLayout linearLayout) {
        this.llContainer = linearLayout;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public void setMediaData(MediaPanelData mediaPanelData) {
        String string;
        Integer len;
        String string2;
        Integer position;
        super.setMediaData(mediaPanelData);
        COUISeekBar cOUISeekBar = this.seekBar;
        int iIntValue = 0;
        if (cOUISeekBar != null) {
            cOUISeekBar.setProgress((mediaPanelData == null || (position = mediaPanelData.getPosition()) == null) ? 0 : position.intValue());
        }
        TextView textView = this.currentTime;
        if (textView != null) {
            if (mediaPanelData == null || (string2 = mediaPanelData.getCurrentTime()) == null) {
                string2 = getContext().getString(2131955666);
            }
            textView.setText(string2);
        }
        COUISeekBar cOUISeekBar2 = this.seekBar;
        if (cOUISeekBar2 != null) {
            if (mediaPanelData != null && (len = mediaPanelData.getLen()) != null) {
                iIntValue = len.intValue();
            }
            cOUISeekBar2.setMax(iIntValue);
        }
        TextView textView2 = this.totalTime;
        if (textView2 != null) {
            if (mediaPanelData == null || (string = mediaPanelData.getTotalTime()) == null) {
                string = getContext().getString(2131955666);
            }
            textView2.setText(string);
        }
    }

    public final void setSeekBar(COUISeekBar cOUISeekBar) {
        this.seekBar = cOUISeekBar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public void setSeekBarColor(ColorStateList colorStateList) {
        int color = QsColorUtil.isNeedDarkThemeColor(getContext(), this.isEditMode) ? getContext().getColor(2131103287) : getContext().getColor(2131100936);
        int colorAttrDefaultColor = Utils.getColorAttrDefaultColor(2130969298, 0, getContext());
        COUISeekBar cOUISeekBar = this.seekBar;
        if (cOUISeekBar != null) {
            cOUISeekBar.setProgressColor(colorStateList);
        }
        COUISeekBar cOUISeekBar2 = this.seekBar;
        if (cOUISeekBar2 != null) {
            cOUISeekBar2.setSeekBarBackgroundColor(ColorStateList.valueOf(colorAttrDefaultColor));
        }
        TextView textView = this.totalTime;
        if (textView != null) {
            textView.setTextColor(color);
        }
        TextView textView2 = this.currentTime;
        if (textView2 != null) {
            textView2.setTextColor(color);
        }
    }

    public final void setTotalTime(TextView textView) {
        this.totalTime = textView;
    }

    /* JADX WARN: Code duplicated, block: B:103:0x0242 A[PHI: r3
      0x0242: PHI (r3v3 boolean) = (r3v2 boolean), (r3v8 boolean) binds: [B:87:0x01f1, B:102:0x0240] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:19:0x00af  */
    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public final void updateCapsuleLayout() {
        boolean z;
        boolean z2;
        IHostAbility.LayoutConstraint qSCapsuleLayoutConstraint = getQSCapsuleLayoutConstraint();
        boolean z3 = false;
        int dimensionPixelSize = getDimensionPixelSize(2131171121, qSCapsuleLayoutConstraint, false);
        int dimensionPixelSize2 = getDimensionPixelSize(2131171120, qSCapsuleLayoutConstraint, false);
        setMediaTextSize(dimensionPixelSize, dimensionPixelSize2);
        setSeekTimeTextSize(dimensionPixelSize2);
        getTmpConstrainSet().clone(getMediaPanelLayout());
        boolean z4 = true;
        int dimensionPixelSize3 = getDimensionPixelSize(2131171114, qSCapsuleLayoutConstraint, true);
        boolean mediaLayout = setMediaLayout(dimensionPixelSize3, getDimensionPixelSize(2131171115, qSCapsuleLayoutConstraint, false), getDimensionPixelSize(2131171116, qSCapsuleLayoutConstraint, false));
        int dimensionPixelSize4 = getDimensionPixelSize(2131171112, qSCapsuleLayoutConstraint, true);
        int dimensionPixelSize5 = getDimensionPixelSize(2131171113, qSCapsuleLayoutConstraint, false);
        int dimensionPixelSize6 = getDimensionPixelSize(2131171991, qSCapsuleLayoutConstraint, false);
        int dimensionPixelSize7 = getDimensionPixelSize(2131171992, qSCapsuleLayoutConstraint, false);
        Float fValueOf = qSCapsuleLayoutConstraint != null ? Float.valueOf(qSCapsuleLayoutConstraint.getInnerMarginRatio()) : null;
        if (fValueOf != null) {
            float fFloatValue = fValueOf.floatValue() * dimensionPixelSize6;
            int iFloatValue = (int) (fValueOf.floatValue() * dimensionPixelSize7);
            COUISeekBar cOUISeekBar = this.seekBar;
            if (cOUISeekBar != null) {
                cOUISeekBar.setProgressEnlargeScale(fValueOf.floatValue() * 1.5f);
                COUISeekBar cOUISeekBar2 = this.seekBar;
                if (cOUISeekBar2 != null) {
                    cOUISeekBar2.setBackgroundEnlargeScale(fValueOf.floatValue() * 1.5f);
                }
                COUISeekBar cOUISeekBar3 = this.seekBar;
                if (cOUISeekBar3 != null) {
                    cOUISeekBar3.setProgressHeight(fFloatValue);
                }
                COUISeekBar cOUISeekBar4 = this.seekBar;
                if (cOUISeekBar4 != null) {
                    cOUISeekBar4.setBackgroundHeight(fFloatValue);
                }
                getTmpConstrainSet().constrainHeight(cOUISeekBar.getId(), iFloatValue);
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        TextView textView = this.currentTime;
        if (textView != null) {
            ConstraintLayout.LayoutParams layoutParams = textView.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams2 = layoutParams instanceof ConstraintLayout.LayoutParams ? layoutParams : null;
            if (layoutParams2 == null || layoutParams2.getMarginStart() != dimensionPixelSize3) {
                getTmpConstrainSet().connect(textView.getId(), 6, 0, 6, dimensionPixelSize3);
                z = true;
            }
        }
        TextView textView2 = this.totalTime;
        if (textView2 != null) {
            ConstraintLayout.LayoutParams layoutParams3 = textView2.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams4 = layoutParams3 instanceof ConstraintLayout.LayoutParams ? layoutParams3 : null;
            if (layoutParams4 == null || layoutParams4.getMarginStart() != dimensionPixelSize3) {
                getTmpConstrainSet().connect(textView2.getId(), 7, 0, 7, dimensionPixelSize3);
                z = true;
            }
        }
        LinearLayout linearLayout = this.llContainer;
        if (linearLayout != null) {
            ConstraintLayout.LayoutParams layoutParams5 = linearLayout.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams6 = layoutParams5 instanceof ConstraintLayout.LayoutParams ? layoutParams5 : null;
            if (layoutParams6 == null || layoutParams6.getMarginStart() != dimensionPixelSize3) {
                getTmpConstrainSet().connect(linearLayout.getId(), 6, 0, 6, dimensionPixelSize3);
                getTmpConstrainSet().connect(linearLayout.getId(), 7, 0, 7, dimensionPixelSize3);
                z = true;
            }
        }
        OplusLottieButton preBtn = getPreBtn();
        if (preBtn != null) {
            ConstraintLayout.LayoutParams layoutParams7 = preBtn.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams8 = layoutParams7 instanceof ConstraintLayout.LayoutParams ? layoutParams7 : null;
            if (layoutParams8 == null || ((ViewGroup.MarginLayoutParams) layoutParams8).bottomMargin != dimensionPixelSize4) {
                getTmpConstrainSet().constrainWidth(preBtn.getId(), dimensionPixelSize5);
                getTmpConstrainSet().constrainHeight(preBtn.getId(), dimensionPixelSize5);
                getTmpConstrainSet().connect(preBtn.getId(), 4, 0, 4, dimensionPixelSize4);
                dimensionPixelSize4 = dimensionPixelSize4;
                z2 = true;
            } else {
                z2 = false;
            }
            ConstraintLayout.LayoutParams layoutParams9 = preBtn.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams10 = layoutParams9 instanceof ConstraintLayout.LayoutParams ? layoutParams9 : null;
            if (layoutParams10 == null || layoutParams10.getMarginStart() != dimensionPixelSize3) {
                getTmpConstrainSet().connect(preBtn.getId(), 6, 0, 6, dimensionPixelSize3);
                z2 = true;
            }
            z3 = z2;
        }
        OplusQsPlayPauseButton playOrPauseBtn = getPlayOrPauseBtn();
        if (playOrPauseBtn != null) {
            ConstraintLayout.LayoutParams layoutParams11 = playOrPauseBtn.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams12 = layoutParams11 instanceof ConstraintLayout.LayoutParams ? layoutParams11 : null;
            if (layoutParams12 == null || ((ViewGroup.MarginLayoutParams) layoutParams12).bottomMargin != dimensionPixelSize4) {
                getTmpConstrainSet().constrainWidth(playOrPauseBtn.getId(), dimensionPixelSize5);
                getTmpConstrainSet().constrainHeight(playOrPauseBtn.getId(), dimensionPixelSize5);
                int i = dimensionPixelSize4;
                getTmpConstrainSet().connect(playOrPauseBtn.getId(), 4, 0, 4, i);
                dimensionPixelSize4 = i;
                z3 = true;
            }
        }
        OplusLottieButton nextBtn = getNextBtn();
        if (nextBtn != null) {
            ConstraintLayout.LayoutParams layoutParams13 = nextBtn.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams14 = layoutParams13 instanceof ConstraintLayout.LayoutParams ? layoutParams13 : null;
            if (layoutParams14 == null || ((ViewGroup.MarginLayoutParams) layoutParams14).bottomMargin != dimensionPixelSize4) {
                getTmpConstrainSet().constrainWidth(nextBtn.getId(), dimensionPixelSize5);
                getTmpConstrainSet().constrainHeight(nextBtn.getId(), dimensionPixelSize5);
                getTmpConstrainSet().connect(nextBtn.getId(), 4, 0, 4, dimensionPixelSize4);
                z3 = true;
            }
            ConstraintLayout.LayoutParams layoutParams15 = nextBtn.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams16 = layoutParams15 instanceof ConstraintLayout.LayoutParams ? layoutParams15 : null;
            if (layoutParams16 == null || layoutParams16.getMarginEnd() != dimensionPixelSize3) {
                getTmpConstrainSet().connect(nextBtn.getId(), 7, 0, 7, dimensionPixelSize3);
            } else {
                z4 = z3;
            }
        } else {
            z4 = z3;
        }
        boolean textLayout = setTextLayout(dimensionPixelSize3);
        if (mediaLayout || z || z4 || textLayout) {
            getTmpConstrainSet().applyTo(getMediaPanelLayout());
        }
    }

    @Override // com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView
    public /* bridge */ /* synthetic */ void updateFinishExpand(String str) {
        super.updateFinishExpand(str);
    }

    public SepQsLargeMediaPanelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public SepQsLargeMediaPanelView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public SepQsLargeMediaPanelView(Context context) {
        this(context, null, 0, 0, 14, null);
    }

    public SepQsLargeMediaPanelView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
