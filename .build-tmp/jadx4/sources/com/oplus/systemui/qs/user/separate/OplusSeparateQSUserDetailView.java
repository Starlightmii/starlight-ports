package com.oplus.systemui.qs.user.separate;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.core.widget.NestedScrollView;
import com.android.systemui.qs.tiles.UserDetailView;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSUserDetailView extends NestedScrollView {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSUserDetailView";
    private UserDetailView mUserDetailView;

    public /* synthetic */ OplusSeparateQSUserDetailView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public static final OplusSeparateQSUserDetailView inflate(Context context, ViewGroup viewGroup, boolean z) {
        return Companion.inflate(context, viewGroup, z);
    }

    public final void refreshAdapter() {
        UserDetailView userDetailView = this.mUserDetailView;
        if (userDetailView != null) {
            userDetailView.mAdapter.refresh();
        }
    }

    public final void setAdapter(UserDetailView.Adapter adapter) {
        UserDetailView userDetailView = this.mUserDetailView;
        if (userDetailView != null) {
            userDetailView.setAdapter(adapter);
        }
    }

    public OplusSeparateQSUserDetailView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public OplusSeparateQSUserDetailView(Context context) {
        this(context, null, 0, 6, null);
    }

    public OplusSeparateQSUserDetailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
