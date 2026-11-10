package com.oplus.systemui.plugins.qs.quickentrance.popupwindow;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.coui.appcompat.poplist.COUIPopupListWindow;
import com.coui.appcompat.poplist.PopupListItem;
import com.coui.appcompat.uiutil.AnimLevel;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSMoreButtonPopupWindow extends COUIPopupListWindow {
    public static final Companion Companion = new Companion();
    private static final String FSG_NUM_PKG_MAX_STRING = "99+";
    private static final String TAG = "SeparateQSMoreButtonPopupWindow";
    private final OplusQSMoreButtonPopupWindowController.Callback call;
    private View ccSettingView;
    private final Context context;
    private TextView fgsNumber;
    private View fgsRedDotView;
    private View fgsView;
    private List<PopupListItem> mCustomList;
    private TextView outSwitchView;
    private View redDotView;
    private ImageView userImage;
    private View usersView;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Type {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ Type[] $VALUES;
        public static final Type CC_SETTINGS;
        public static final Type FGS;
        public static final Type MEDIA_SWITCH;
        public static final Type USERS;

        static {
            Type type = new Type("MEDIA_SWITCH", 0);
            MEDIA_SWITCH = type;
            Type type2 = new Type("USERS", 1);
            USERS = type2;
            Type type3 = new Type("FGS", 2);
            FGS = type3;
            Type type4 = new Type("CC_SETTINGS", 3);
            CC_SETTINGS = type4;
            Type[] typeArr = {type, type2, type3, type4};
            $VALUES = typeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(typeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static Type valueOf(String str) {
            return (Type) Enum.valueOf(Type.class, str);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQSMoreButtonPopupWindow(Context context, OplusQSMoreButtonPopupWindowController.Callback callback) {
        super(context);
        this.context = context;
        this.call = callback;
        setInputMethodMode(2);
        setIsAdaptiveFontSize(true);
        setDismissWhenWindowSizeChange(false);
        QSFeatureOption.INSTANCE.getClass();
        if (!QSFeatureOption.isDisableBGBlurNonAnim) {
            setUseBackgroundBlur(true, AnimLevel.MID_END);
        }
        View viewInflate = LayoutInflater.from(context).inflate(2131559350, (ViewGroup) null);
        this.outSwitchView = viewInflate instanceof TextView ? (TextView) viewInflate : null;
        View viewInflate2 = LayoutInflater.from(context).inflate(2131559308, (ViewGroup) null);
        this.usersView = viewInflate2;
        this.userImage = viewInflate2 != null ? (ImageView) viewInflate2.findViewById(2131363503) : null;
        View viewInflate3 = LayoutInflater.from(context).inflate(2131559306, (ViewGroup) null);
        this.fgsView = viewInflate3;
        this.fgsRedDotView = viewInflate3 != null ? viewInflate3.findViewById(2131364758) : null;
        View view = this.fgsView;
        this.fgsNumber = view != null ? (TextView) view.findViewById(2131365811) : null;
        View viewInflate4 = LayoutInflater.from(context).inflate(2131559307, (ViewGroup) null);
        this.ccSettingView = viewInflate4;
        this.redDotView = viewInflate4 != null ? viewInflate4.findViewById(2131364802) : null;
        initList();
        initListener.1 r3 = new initListener.1();
        r3.this$0 = this;
        VarHandle.storeStoreFence();
        setOnItemClickListener(r3);
    }

    public final OplusQSMoreButtonPopupWindowController.Callback getCall() {
        return this.call;
    }

    public final Context getContext() {
        return this.context;
    }

    public final void initList() {
        ArrayList arrayList = new ArrayList();
        this.mCustomList = arrayList;
        PopupListItem.Builder builder = new PopupListItem.Builder();
        TextView textView = this.outSwitchView;
        if (textView != null && textView.getVisibility() == 0) {
            builder.setItemType(2).setCustomItemView(this.outSwitchView);
            arrayList.add(builder.build());
        }
        View view = this.usersView;
        if (view != null && view.getVisibility() == 0) {
            builder.setItemType(2).setCustomItemView(this.usersView);
            arrayList.add(builder.build());
        }
        View view2 = this.fgsView;
        if (view2 != null && view2.getVisibility() == 0) {
            builder.reset();
            builder.setItemType(2).setCustomItemView(this.fgsView);
            arrayList.add(builder.build());
        }
        View view3 = this.ccSettingView;
        if (view3 != null && view3.getVisibility() == 0) {
            builder.reset();
            builder.setItemType(2).setCustomItemView(this.ccSettingView);
            arrayList.add(builder.build());
        }
        setItemList(this.mCustomList);
    }

    public final void updateFsg(int i, boolean z) {
        View view = this.fgsView;
        Integer numValueOf = view != null ? Integer.valueOf(view.getVisibility()) : null;
        int i2 = i > 0 ? 0 : 8;
        View view2 = this.fgsView;
        if (view2 != null) {
            view2.setVisibility(i2);
        }
        View view3 = this.fgsRedDotView;
        if (view3 != null) {
            view3.setVisibility(z ? 0 : 8);
        }
        View view4 = this.fgsView;
        if (view4 != null) {
            view4.setSelected(true);
        }
        String quantityString = this.context.getResources().getQuantityString(2131820571, i, Integer.valueOf(i));
        TextView textView = this.fgsNumber;
        if (textView != null) {
            textView.setText(quantityString);
        }
        TextView textView2 = this.fgsNumber;
        if (textView2 != null) {
            textView2.setContentDescription(quantityString);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SeparateQSMoreButtonPopupWindow-->".concat("updateFsg fgsNum: " + quantityString + ", fgsNumber: " + this.fgsNumber));
        }
        if (numValueOf != null && numValueOf.intValue() == i2) {
            return;
        }
        initList();
    }

    public final void updateMediaSwitch(boolean z, String str) {
        TextView textView = this.outSwitchView;
        Integer numValueOf = textView != null ? Integer.valueOf(textView.getVisibility()) : null;
        int i = z ? 0 : 8;
        TextView textView2 = this.outSwitchView;
        if (textView2 != null) {
            textView2.setVisibility(i);
        }
        TextView textView3 = this.outSwitchView;
        if (textView3 != null) {
            textView3.setText(str);
        }
        if (numValueOf != null && numValueOf.intValue() == i) {
            return;
        }
        initList();
    }

    public final void updateRedDotView(boolean z) {
        View view = this.redDotView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public final void updateUsers(boolean z, Drawable drawable, String str) {
        View view = this.fgsView;
        Integer numValueOf = view != null ? Integer.valueOf(view.getVisibility()) : null;
        int i = z ? 0 : 8;
        View view2 = this.usersView;
        if (view2 != null) {
            view2.setVisibility(i);
        }
        ImageView imageView = this.userImage;
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
            imageView.setContentDescription(str);
        }
        if (numValueOf != null && numValueOf.intValue() == i) {
            return;
        }
        initList();
    }
}
