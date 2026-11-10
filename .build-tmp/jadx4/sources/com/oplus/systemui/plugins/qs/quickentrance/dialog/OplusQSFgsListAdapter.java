package com.oplus.systemui.plugins.qs.quickentrance.dialog;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.plugins.qs.QSFgs;
import com.coui.appcompat.button.COUIButton;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.util.OplusContextUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSFgsListAdapter extends RecyclerView.Adapter {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SeparateQSFgsListAdapter";
    private final View.OnClickListener clickListener;
    private Context mContext;
    private final Object lock = new Object();
    private List<QSFgs.QSRunningApp> data = CollectionsKt__CollectionsKt.listOfNotNull(new QSFgs.QSRunningApp[0]);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AppItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView appLabelView;
        private final View dividerView;
        private final TextView durationView;
        private final ImageView iconView;
        private final COUIButton stopButton;

        public AppItemViewHolder(View view) {
            super(view);
            this.appLabelView = (TextView) view.requireViewById(2131365679);
            this.durationView = (TextView) view.requireViewById(2131365614);
            this.iconView = (ImageView) view.requireViewById(2131363381);
            this.stopButton = view.requireViewById(2131361918);
            this.dividerView = view.requireViewById(2131362909);
        }

        public final TextView getAppLabelView() {
            return this.appLabelView;
        }

        public final View getDividerView() {
            return this.dividerView;
        }

        public final TextView getDurationView() {
            return this.durationView;
        }

        public final ImageView getIconView() {
            return this.iconView;
        }

        public final COUIButton getStopButton() {
            return this.stopButton;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class HeaderViewHolder extends RecyclerView.ViewHolder {
        private final TextView headView;

        public HeaderViewHolder(View view) {
            super(view);
            this.headView = (TextView) view.requireViewById(2131365614);
        }

        public final TextView getHeadView() {
            return this.headView;
        }
    }

    public OplusQSFgsListAdapter(OplusQSFgsDialogController.Callback callback) {
        OplusQSFgsListAdapter$clickListener$1 oplusQSFgsListAdapter$clickListener$1 = new OplusQSFgsListAdapter$clickListener$1();
        oplusQSFgsListAdapter$clickListener$1.$call = callback;
        VarHandle.storeStoreFence();
        this.clickListener = oplusQSFgsListAdapter$clickListener$1;
    }

    public int getItemCount() {
        return this.data.size() + 1;
    }

    public final int getItemViewType(int i) {
        return i == 0 ? 0 : 1;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        QSFgs.QSRunningApp qSRunningApp;
        Context context;
        Resources resources;
        if (i == 0) {
            Context context2 = this.mContext;
            if (context2 == null || !QsColorUtil.isNeedDarkThemeColor(context2, false)) {
                return;
            }
            HeaderViewHolder headerViewHolder = viewHolder instanceof HeaderViewHolder ? (HeaderViewHolder) viewHolder : null;
            if (headerViewHolder == null || (context = this.mContext) == null || (resources = context.getResources()) == null) {
                return;
            }
            headerViewHolder.getHeadView().setTextColor(resources.getColor(2131103102));
            return;
        }
        synchronized (this.lock) {
            qSRunningApp = this.data.get(i - 1);
        }
        AppItemViewHolder appItemViewHolder = viewHolder instanceof AppItemViewHolder ? (AppItemViewHolder) viewHolder : null;
        if (appItemViewHolder != null) {
            QSFgs.QSRunningApp qSRunningApp2 = qSRunningApp;
            appItemViewHolder.getIconView().setImageDrawable(qSRunningApp2.getIcon());
            appItemViewHolder.getAppLabelView().setText(qSRunningApp2.getAppLabel());
            appItemViewHolder.getAppLabelView().setContentDescription(qSRunningApp2.getAppLabel());
            CharSequence duration = DateUtils.formatDuration(Math.max(SystemClock.elapsedRealtime() - qSRunningApp2.getTimeStarted(), 60000L), 20);
            appItemViewHolder.getDurationView().setText(duration);
            appItemViewHolder.getDurationView().setContentDescription(duration);
            if (qSRunningApp2.getUiControl() == QSFgs.QSUIControl.HIDE_BUTTON) {
                appItemViewHolder.getStopButton().setVisibility(8);
                appItemViewHolder.getStopButton().setOnClickListener(null);
            } else {
                COUIButton stopButton = appItemViewHolder.getStopButton();
                stopButton.setVisibility(0);
                stopButton.setTag(qSRunningApp);
                stopButton.setOnClickListener(this.clickListener);
            }
            if (i == this.data.size()) {
                appItemViewHolder.getDividerView().setVisibility(8);
            } else {
                appItemViewHolder.getDividerView().setVisibility(0);
            }
            if (QsColorUtil.isGlobalThemeApplied(viewHolder.itemView.getContext())) {
                appItemViewHolder.getAppLabelView().setTextColor(OplusContextUtil.getAttrColor(viewHolder.itemView.getContext(), 2130969296));
                appItemViewHolder.getDurationView().setTextColor(OplusContextUtil.getAttrColor(viewHolder.itemView.getContext(), 2130969299));
            }
            Context context3 = this.mContext;
            if (context3 == null || !QsColorUtil.isNeedDarkThemeColor(context3, false)) {
                return;
            }
            Context context4 = this.mContext;
            if (context4 != null) {
                appItemViewHolder.getAppLabelView().setTextColor(context4.getColor(2131103102));
            }
            Context context5 = this.mContext;
            if (context5 != null) {
                appItemViewHolder.getDurationView().setTextColor(context5.getColor(2131103102));
            }
            Context context6 = this.mContext;
            if (context6 != null) {
                appItemViewHolder.getDividerView().setBackgroundColor(context6.getColor(2131103102));
            }
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.mContext = viewGroup.getContext();
        return i == 0 ? new HeaderViewHolder(OplusLockIconView$.ExternalSyntheticOutline0.m(viewGroup, 2131559344, viewGroup, false)) : new AppItemViewHolder(LayoutInflater.from(this.mContext).inflate(2131559343, viewGroup, false));
    }

    public final void setData(List<QSFgs.QSRunningApp> list) {
        List<QSFgs.QSRunningApp> list2 = this.data;
        this.data = list;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SeparateQSFgsListAdapter-->".concat("setData: oldData: " + list2 + ", newData: " + list));
        }
        notifyDataSetChanged();
    }
}
