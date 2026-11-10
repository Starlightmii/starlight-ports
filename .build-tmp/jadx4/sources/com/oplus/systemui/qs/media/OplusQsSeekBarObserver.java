package com.oplus.systemui.qs.media;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;
import com.coui.appcompat.seekbar.COUIIntentSeekBar;
import com.oplus.systemui.utils.Util;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsSeekBarObserver implements Observer {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsSeekBarObserver";
    private final Context context;
    private final OplusMediaViewPagerAdapter.OplusMediaViewHolder holder;
    public int lastDuration;
    public int lastElapsedTime;
    public long lastUserSeekUptimeMs;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQsSeekBarObserver(Context context, OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder) {
        this.context = context;
        this.holder = oplusMediaViewHolder;
    }

    public final int getLastDuration() {
        return this.lastDuration;
    }

    public final int getLastElapsedTime() {
        return this.lastElapsedTime;
    }

    public void onChanged(SeekBarViewModel.Progress progress) {
        Drawable thumb;
        Drawable thumb2;
        boolean enabled = progress.getEnabled();
        OplusMediaViewPagerAdapter.OplusMediaViewHolder oplusMediaViewHolder = this.holder;
        boolean z = false;
        if (!enabled) {
            COUIIntentSeekBar mSeekBar = oplusMediaViewHolder.getMSeekBar();
            if (mSeekBar != null) {
                mSeekBar.setEnabled(false);
            }
            COUIIntentSeekBar mSeekBar2 = this.holder.getMSeekBar();
            if (mSeekBar2 != null && (thumb2 = mSeekBar2.getThumb()) != null) {
                thumb2.setAlpha(0);
            }
            COUIIntentSeekBar mSeekBar3 = this.holder.getMSeekBar();
            if (mSeekBar3 != null) {
                mSeekBar3.setProgress(0);
            }
            TextView mCurrentTime = this.holder.getMCurrentTime();
            if (mCurrentTime != null) {
                mCurrentTime.setText(this.context.getString(2131955666));
            }
            TextView mTotalTime = this.holder.getMTotalTime();
            if (mTotalTime != null) {
                mTotalTime.setText(this.context.getString(2131955666));
            }
            COUIIntentSeekBar mSeekBar4 = this.holder.getMSeekBar();
            if (mSeekBar4 != null) {
                mSeekBar4.setContentDescription("");
                return;
            }
            return;
        }
        COUIIntentSeekBar mSeekBar5 = oplusMediaViewHolder.getMSeekBar();
        if (mSeekBar5 != null && (thumb = mSeekBar5.getThumb()) != null) {
            thumb.setAlpha(progress.isShowThumb ? 255 : 0);
        }
        COUIIntentSeekBar mSeekBar6 = this.holder.getMSeekBar();
        if (mSeekBar6 != null) {
            mSeekBar6.setEnabled(progress.getSeekAvailable());
        }
        COUIIntentSeekBar mSeekBar7 = this.holder.getMSeekBar();
        if (mSeekBar7 != null) {
            mSeekBar7.setMax(progress.getDuration());
        }
        String duration = Util.formatDuration(progress.getDuration());
        TextView mTotalTime2 = this.holder.getMTotalTime();
        if (mTotalTime2 != null) {
            mTotalTime2.setText(duration);
        }
        Integer elapsedTime = progress.getElapsedTime();
        if (elapsedTime != null) {
            int iIntValue = elapsedTime.intValue();
            if (progress.getScrubbing()) {
                return;
            }
            int duration2 = progress.getDuration();
            if (this.lastUserSeekUptimeMs > 0 && SystemClock.uptimeMillis() - this.lastUserSeekUptimeMs < 1000) {
                z = true;
            } else {
                int i = this.lastElapsedTime;
                if (iIntValue > i || duration2 != this.lastDuration) {
                    this.lastElapsedTime = iIntValue;
                    this.lastDuration = duration2;
                } else {
                    int i2 = (int) ((((long) iIntValue) / 1000) - (((long) i) / 1000));
                    if (-1 <= i2 && i2 < 1) {
                        z = true;
                    }
                    if (!z) {
                        this.lastElapsedTime = iIntValue;
                        this.lastDuration = duration2;
                    }
                }
            }
            if (z) {
                return;
            }
            COUIIntentSeekBar mSeekBar8 = this.holder.getMSeekBar();
            if (mSeekBar8 != null) {
                mSeekBar8.setProgress(iIntValue);
            }
            this.holder.updateTextContent(Integer.valueOf(iIntValue));
            String duration3 = Util.formatDuration(iIntValue);
            TextView mCurrentTime2 = this.holder.getMCurrentTime();
            if (mCurrentTime2 != null) {
                mCurrentTime2.setText(duration3);
            }
        }
    }

    public final void setLastDuration(int i) {
        this.lastDuration = i;
    }

    public final void setLastElapsedTime(int i) {
        this.lastElapsedTime = i;
    }
}
