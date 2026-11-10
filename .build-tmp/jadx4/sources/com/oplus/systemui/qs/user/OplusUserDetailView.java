package com.oplus.systemui.qs.user;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.widget.NestedScrollView;
import com.android.systemui.qs.tiles.UserDetailView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusUserDetailView extends NestedScrollView {
    public static final /* synthetic */ int $r8$clinit = 0;
    public UserDetailView mUserDetailView;

    public OplusUserDetailView(Context context) {
        this(context, null);
    }

    public void setAdapter(UserDetailView.Adapter adapter) {
        this.mUserDetailView.setAdapter(adapter);
    }

    public OplusUserDetailView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OplusUserDetailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
