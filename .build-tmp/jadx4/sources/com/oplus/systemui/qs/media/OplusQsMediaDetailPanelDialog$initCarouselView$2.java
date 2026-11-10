package com.oplus.systemui.qs.media;

import androidx.viewpager2.widget.ViewPager2;
import com.coui.appcompat.indicator.COUIPageIndicator;
import com.oplus.systemui.qs.base.widget.OplusQSPageIndicator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaDetailPanelDialog$initCarouselView$2 extends ViewPager2.OnPageChangeCallback {
    public /* synthetic */ OplusQsMediaDetailPanelDialog this$0;

    public final void onPageScrollStateChanged(int i) {
        OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog = this.this$0;
        OplusQSPageIndicator oplusQSPageIndicator = oplusQsMediaDetailPanelDialog.mediaPageIndicator;
        if (oplusQSPageIndicator == null) {
            oplusQSPageIndicator = null;
        }
        oplusQSPageIndicator.getClass();
        if (i == 0) {
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter = oplusQsMediaDetailPanelDialog.adapter;
            (oplusMediaViewPagerAdapter != null ? oplusMediaViewPagerAdapter : null).setViewPagerScrolling(false);
            OplusQsMediaDetailPanelDialog.access$refreshCarouselClipOnScroll(oplusQsMediaDetailPanelDialog);
        } else if (i == 1) {
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter2 = oplusQsMediaDetailPanelDialog.adapter;
            (oplusMediaViewPagerAdapter2 != null ? oplusMediaViewPagerAdapter2 : null).setViewPagerScrolling(true);
            OplusQsMediaDetailPanelDialog.access$finishLaunchAnimationOnViewPagerDrag(oplusQsMediaDetailPanelDialog);
        } else {
            if (i != 2) {
                return;
            }
            OplusMediaViewPagerAdapter oplusMediaViewPagerAdapter3 = oplusQsMediaDetailPanelDialog.adapter;
            (oplusMediaViewPagerAdapter3 != null ? oplusMediaViewPagerAdapter3 : null).setViewPagerScrolling(true);
        }
    }

    public final void onPageScrolled(int i, float f, int i2) {
        OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog = this.this$0;
        oplusQsMediaDetailPanelDialog.positionOffsetPixels = i2;
        OplusQSPageIndicator oplusQSPageIndicator = oplusQsMediaDetailPanelDialog.mediaPageIndicator;
        if (oplusQSPageIndicator == null) {
            oplusQSPageIndicator = null;
        }
        oplusQSPageIndicator.setCurrentPosition(f, i);
        if (f > 0.0f) {
            OplusQsMediaDetailPanelDialog.access$refreshCarouselClipOnScroll(oplusQsMediaDetailPanelDialog);
        }
    }

    public final void onPageSelected(int i) {
        OplusQsMediaDetailPanelDialog oplusQsMediaDetailPanelDialog = this.this$0;
        oplusQsMediaDetailPanelDialog.mCurrentPosition = i;
        OplusQSPageIndicator oplusQSPageIndicator = oplusQsMediaDetailPanelDialog.mediaPageIndicator;
        if (oplusQSPageIndicator == null) {
            oplusQSPageIndicator = null;
        }
        ((COUIPageIndicator) oplusQSPageIndicator).mModel.mAccessCurrentIndex = i;
    }
}
