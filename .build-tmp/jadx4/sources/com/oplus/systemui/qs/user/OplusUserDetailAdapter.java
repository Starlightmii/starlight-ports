package com.oplus.systemui.qs.user;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.qs.tiles.UserDetailView;
import com.oplus.systemui.qs.user.separate.OplusSeparateQSUserDetailView;
import com.oplus.systemui.separate.OplusSeparateNotificationAndQSState;
import javax.inject.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusUserDetailAdapter implements DetailAdapter {
    public final Intent USER_SETTINGS_INTENT = new Intent("android.settings.USER_SETTINGS");
    public final Context mContext;
    public final OplusSeparateNotificationAndQSState mOplusSeparateNotificationAndQSState;
    public final Provider mUserDetailViewAdapterProvider;

    public OplusUserDetailAdapter(Context context, Provider provider, OplusSeparateNotificationAndQSState oplusSeparateNotificationAndQSState) {
        this.mContext = context;
        this.mUserDetailViewAdapterProvider = provider;
        this.mOplusSeparateNotificationAndQSState = oplusSeparateNotificationAndQSState;
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [android.view.View, android.widget.FrameLayout, com.oplus.systemui.qs.user.OplusUserDetailView] */
    /* JADX WARN: Type inference failed for: r2v4, types: [android.view.View, com.oplus.systemui.qs.user.separate.OplusSeparateQSUserDetailView] */
    public final View createDetailView(Context context, ViewGroup viewGroup, boolean z) {
        boolean zBooleanValue = ((Boolean) this.mOplusSeparateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue();
        Provider provider = this.mUserDetailViewAdapterProvider;
        if (zBooleanValue) {
            ?? Inflate = OplusSeparateQSUserDetailView.inflate(context, viewGroup, z);
            Inflate.setAdapter((UserDetailView.Adapter) provider.get());
            Inflate.refreshAdapter();
            return Inflate;
        }
        int i = OplusUserDetailView.$r8$clinit;
        ?? r2 = (OplusUserDetailView) LayoutInflater.from(context).inflate(2131559532, viewGroup, false);
        r2.mUserDetailView = r2.findViewById(2131365933);
        r2.setAdapter((UserDetailView.Adapter) provider.get());
        r2.mUserDetailView.mAdapter.refresh();
        return r2;
    }

    public DetailAdapter.DetailHeightType getDetailHeightType() {
        return ((Boolean) this.mOplusSeparateNotificationAndQSState.getEnableSeparateNotificationAndQS().getValue()).booleanValue() ? DetailAdapter.DetailHeightType.CHANGE : DetailAdapter.DetailHeightType.IMMUTABLE_ONE;
    }

    public int getMetricsCategory() {
        return 125;
    }

    public Intent getSettingsIntent() {
        return this.USER_SETTINGS_INTENT;
    }

    public String getTag() {
        return "OplusUserDetailAdapter";
    }

    public CharSequence getTitle() {
        return this.mContext.getString(2131955872);
    }
}
