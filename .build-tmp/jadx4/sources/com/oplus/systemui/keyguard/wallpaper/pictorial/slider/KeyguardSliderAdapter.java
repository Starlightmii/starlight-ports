package com.oplus.systemui.keyguard.wallpaper.pictorial.slider;

import android.view.View;
import android.view.ViewGroup;
import com.android.internal.widget.PagerAdapter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardSliderAdapter extends PagerAdapter {
    public List mPagers;

    public final void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof IKeyguardSliderPager) {
            ((IKeyguardSliderPager) obj).detached();
        }
        viewGroup.removeView((View) obj);
    }

    public int getCount() {
        return this.mPagers.size();
    }

    public final int getItemPosition(Object obj) {
        return -2;
    }

    public final Object instantiateItem(ViewGroup viewGroup, int i) {
        IKeyguardSliderPager iKeyguardSliderPager;
        View view;
        if (this.mPagers.isEmpty() || (iKeyguardSliderPager = (IKeyguardSliderPager) ((ArrayList) this.mPagers).get(i)) == null || (view = iKeyguardSliderPager.getView()) == null) {
            return null;
        }
        iKeyguardSliderPager.attached();
        viewGroup.addView(view, viewGroup.getChildCount());
        return view;
    }

    public final boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void setPageData(List<IKeyguardSliderPager> list) {
        if (list == null || list.isEmpty()) {
            this.mPagers.clear();
            notifyDataSetChanged();
        } else {
            this.mPagers = new ArrayList(list);
            notifyDataSetChanged();
        }
    }
}
