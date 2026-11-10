package com.oplus.systemui.qs.base.dialog;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.FileProvider$;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.COUIRecyclerView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.plugins.qs.QSTile;
import com.coui.appcompat.contextutil.COUIContextUtil;
import com.coui.appcompat.progressbar.COUICompProgressIndicator;
import com.coui.appcompat.theme.COUIThemeOverlay;
import com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSHighlightTileViewLabelTextHandler;
import com.oplus.systemui.qs.base.util.FontSizeUtils;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.tiles.ScreenRecorderTile;
import com.oplus.systemui.qs.tiles.ScreenshotTile;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSDetailItems extends FrameLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public int mActiveIndex;
    public Integer mActiveTitle;
    public Adapter mAdapter;
    public Integer mAvailableRefreshTitle;
    public Integer mAvailableTitle;
    public Callback mCallback;
    public View mEmpty;
    public ImageView mEmptyIcon;
    public TextView mEmptyText;
    public final H mHandler;
    public boolean mIsRefreshing;
    public COUIRecyclerView mItemList;
    public Item[] mItems;
    public boolean mItemsVisible;
    public List mListItems;
    public COUICompProgressIndicator mOplusLoadingView;
    public String mTag;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.base.dialog.OplusQSDetailItems$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 extends COUIRecyclerView.COUIRecyclerViewItemDecoration {
        public final int getDividerInsetEnd(RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof COUIRecyclerView.ICOUIDividerDecorationInterface) {
                View view = viewHolder.itemView;
                int x = 0;
                boolean z = view.getLayoutDirection() == 1;
                View dividerEndAlignView = ((COUIRecyclerView.ICOUIDividerDecorationInterface) viewHolder).getDividerEndAlignView();
                if (dividerEndAlignView != null) {
                    View view2 = dividerEndAlignView;
                    while (true) {
                        x += (int) view2.getX();
                        Object parent = view2.getParent();
                        if (parent == view) {
                            break;
                        }
                        if (!(parent instanceof View)) {
                            x = -1;
                            break;
                        }
                        view2 = (View) parent;
                    }
                    if (x >= 0) {
                        if (z) {
                            return dividerEndAlignView.getPaddingEnd() + x;
                        }
                        return dividerEndAlignView.getPaddingEnd() + ((view.getWidth() - x) - dividerEndAlignView.getWidth());
                    }
                }
            }
            return super.getDividerInsetEnd(viewHolder);
        }

        public final int getDividerInsetStart(RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof COUIRecyclerView.ICOUIDividerDecorationInterface) {
                View view = viewHolder.itemView;
                int x = 0;
                boolean z = view.getLayoutDirection() == 1;
                View dividerStartAlignView = ((COUIRecyclerView.ICOUIDividerDecorationInterface) viewHolder).getDividerStartAlignView();
                if (dividerStartAlignView != null) {
                    View view2 = dividerStartAlignView;
                    while (true) {
                        x += (int) view2.getX();
                        Object parent = view2.getParent();
                        if (parent == view) {
                            break;
                        }
                        if (!(parent instanceof View)) {
                            x = -1;
                            break;
                        }
                        view2 = (View) parent;
                    }
                    if (x >= 0) {
                        if (z) {
                            return dividerStartAlignView.getPaddingStart() + ((view.getWidth() - x) - dividerStartAlignView.getWidth());
                        }
                        return dividerStartAlignView.getPaddingStart() + x;
                    }
                }
            }
            return super.getDividerInsetStart(viewHolder);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Adapter extends OplusOptimizedListAdapter {
        public Adapter() {
            super(new ItemDiffer());
        }

        public final int getItemViewType(int i) {
            Item item = (Item) getItem(i);
            if (item == null) {
                return 0;
            }
            return item.viewType;
        }

        public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            ((BaseHolder) viewHolder).bind((Item) getItem(i), i);
        }

        public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            Context context = viewGroup.getContext();
            OplusQSDetailItems oplusQSDetailItems = OplusQSDetailItems.this;
            Context contextCreateDarkColorContext = QsColorUtil.createDarkColorContext(context, QsColorUtil.isNeedDarkThemeColor(((FrameLayout) oplusQSDetailItems).mContext, false));
            switch (i) {
                case 2:
                    return new TitleHolder(oplusQSDetailItems, viewGroup, oplusQSDetailItems.mActiveTitle, contextCreateDarkColorContext);
                case 3:
                    return new TitleHolder(oplusQSDetailItems, viewGroup, oplusQSDetailItems.mAvailableTitle, contextCreateDarkColorContext);
                case 4:
                    return new TitleWithRefreshHolder(oplusQSDetailItems, viewGroup, oplusQSDetailItems.mAvailableTitle, contextCreateDarkColorContext);
                case 5:
                    return new TitleCheckHolder(oplusQSDetailItems, viewGroup, contextCreateDarkColorContext);
                case 6:
                    return new HotspotHolder(oplusQSDetailItems, viewGroup, contextCreateDarkColorContext);
                case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                    return new RoundContentHolder(oplusQSDetailItems, viewGroup, contextCreateDarkColorContext);
                case 8:
                    return new TitleMediaCheckHolder(oplusQSDetailItems, viewGroup, contextCreateDarkColorContext);
                case 9:
                    TintHolder tintHolder = new TintHolder(LayoutInflater.from(contextCreateDarkColorContext).inflate(2131559512, viewGroup, false));
                    tintHolder.mTitleView = (TextView) ((RecyclerView.ViewHolder) tintHolder).itemView.findViewById(2131364772);
                    return tintHolder;
                case 10:
                    return new ScreenshotHolder(oplusQSDetailItems, viewGroup, contextCreateDarkColorContext);
                default:
                    return new ContentHolder(oplusQSDetailItems, viewGroup, contextCreateDarkColorContext);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Callback {
        void onDetailItemClick(Item item);

        void onDetailItemDisconnect(Item item);

        default void onRefresh$1() {
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class H extends Handler {
        public H() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            OplusQSDetailItems oplusQSDetailItems = OplusQSDetailItems.this;
            if (i == 1) {
                Item[] itemArr = (Item[]) message.obj;
                int i2 = OplusQSDetailItems.$r8$clinit;
                int length = itemArr != null ? itemArr.length : 0;
                MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(length, "handleSetItems: itemCount: ", LogUtil.TAG_QS, "OplusQSDetailItems");
                oplusQSDetailItems.mEmpty.setVisibility(length == 0 ? 0 : 8);
                oplusQSDetailItems.mItemList.setVisibility(length != 0 ? 0 : 8);
                oplusQSDetailItems.mItems = itemArr;
                oplusQSDetailItems.notifyDataSetChanged(false);
                return;
            }
            if (i == 2) {
                oplusQSDetailItems.mCallback = (Callback) message.obj;
                return;
            }
            if (i == 3) {
                boolean z = message.arg1 != 0;
                if (oplusQSDetailItems.mItemsVisible == z) {
                    return;
                }
                oplusQSDetailItems.mItemsVisible = z;
                for (int i3 = 0; i3 < oplusQSDetailItems.mItemList.getChildCount(); i3++) {
                    oplusQSDetailItems.mItemList.getChildAt(i3).setVisibility(oplusQSDetailItems.mItemsVisible ? 0 : 4);
                }
                return;
            }
            if (i != 4) {
                if (i == 5) {
                    boolean zBooleanValue = ((Boolean) message.obj).booleanValue();
                    MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("fireScanStateChanged: ", LogUtil.TAG_QS, "OplusQSDetailItems", zBooleanValue);
                    if (zBooleanValue) {
                        oplusQSDetailItems.mEmptyText.setVisibility(8);
                        oplusQSDetailItems.mEmptyIcon.setVisibility(8);
                        oplusQSDetailItems.mEmpty.setVisibility(0);
                    }
                    oplusQSDetailItems.mOplusLoadingView.setVisibility(zBooleanValue ? 0 : 8);
                    return;
                }
                return;
            }
            List list = (List) message.obj;
            int i4 = message.arg1;
            int i5 = OplusQSDetailItems.$r8$clinit;
            int size = list.size();
            oplusQSDetailItems.mEmptyText.setVisibility(oplusQSDetailItems.mOplusLoadingView.getVisibility() == 8 ? 0 : 8);
            oplusQSDetailItems.mEmptyIcon.setVisibility(oplusQSDetailItems.mOplusLoadingView.getVisibility() == 8 ? 0 : 8);
            oplusQSDetailItems.mEmpty.setVisibility(size == 0 ? 0 : 8);
            oplusQSDetailItems.mItemList.setVisibility(size != 0 ? 0 : 8);
            oplusQSDetailItems.mListItems = list;
            if (oplusQSDetailItems.isItemsList()) {
                oplusQSDetailItems.mActiveIndex = i4;
                if (i4 > 0) {
                    if (oplusQSDetailItems.hasTitle()) {
                        int i6 = oplusQSDetailItems.mActiveIndex;
                        oplusQSDetailItems.mActiveIndex = i6 + 1;
                        Item item = new Item();
                        if (oplusQSDetailItems.isEmpty(oplusQSDetailItems.mAvailableRefreshTitle)) {
                            item.viewType = 3;
                        } else {
                            item.viewType = 4;
                        }
                        oplusQSDetailItems.mListItems.add(i6, item);
                    }
                    if (!oplusQSDetailItems.isEmpty(oplusQSDetailItems.mActiveTitle)) {
                        Item item2 = new Item();
                        item2.viewType = 2;
                        oplusQSDetailItems.mListItems.add(0, item2);
                    }
                } else if (oplusQSDetailItems.hasTitle()) {
                    Item item3 = new Item();
                    if (oplusQSDetailItems.isEmpty(oplusQSDetailItems.mAvailableRefreshTitle)) {
                        item3.viewType = 3;
                    } else {
                        item3.viewType = 4;
                    }
                    oplusQSDetailItems.mListItems.add(0, item3);
                }
            }
            oplusQSDetailItems.notifyDataSetChanged(false);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Item {
        public CharSequence action;
        public boolean active;
        public CharSequence frequency;
        public QSTile.Icon icon;
        public int iconResId;
        public CharSequence line1;
        public CharSequence line2;
        public CharSequence line3;
        public Object tag;
        public boolean tint;
        public String tintText;
        public int viewType = 0;
        public boolean activeLabel = false;
        public boolean isSaved = false;
        public boolean isCategoryFirstItem = false;
        public boolean isCategoryLastItem = false;
        public boolean select = false;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ItemDiffer extends DiffUtil.ItemCallback {
        public final boolean areContentsTheSame(Object obj, Object obj2) {
            Item item = (Item) obj;
            Item item2 = (Item) obj2;
            return item.iconResId == item2.iconResId && item.select == item2.select && item.viewType == item2.viewType && item.active == item2.active && item.tint == item2.tint && item.activeLabel == item2.activeLabel && item.isSaved == item2.isSaved && item.isCategoryFirstItem == item2.isCategoryFirstItem && item.isCategoryLastItem == item2.isCategoryLastItem && Objects.equals(item.icon, item2.icon) && Objects.equals(item.line1, item2.line1) && Objects.equals(item.line2, item2.line2) && Objects.equals(item.line3, item2.line3) && Objects.equals(item.frequency, item2.frequency) && Objects.equals(item.action, item2.action);
        }

        public final boolean areItemsTheSame(Object obj, Object obj2) {
            return Objects.equals(((Item) obj).tag, ((Item) obj2).tag);
        }
    }

    /* JADX INFO: renamed from: -$$Nest$mgetItemCardBackground, reason: not valid java name */
    public static int m243$$Nest$mgetItemCardBackground(OplusQSDetailItems oplusQSDetailItems, Item item) {
        if (item == null) {
            return 0;
        }
        boolean z = item.isCategoryFirstItem;
        if (z && item.isCategoryLastItem) {
            return 4;
        }
        if (z) {
            return 1;
        }
        return item.isCategoryLastItem ? 3 : 2;
    }

    public OplusQSDetailItems(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHandler = new H();
        this.mItemsVisible = true;
        this.mActiveIndex = -1;
        this.mIsRefreshing = false;
        COUIThemeOverlay.getInstance().applyThemeOverlays(((FrameLayout) this).mContext);
        this.mTag = "OplusQSDetailItems";
        getResources().getDimension(2131171661);
    }

    public static OplusQSDetailItems convertOrInflate(Context context, ViewGroup viewGroup, boolean z) {
        return (OplusQSDetailItems) LayoutInflater.from(context).inflate(2131559515, viewGroup, z);
    }

    private List<Item> getListItems() {
        if (isItemsList()) {
            return this.mListItems;
        }
        Item[] itemArr = this.mItems;
        if (itemArr != null) {
            return Arrays.asList(itemArr);
        }
        return null;
    }

    public final void fireScanStateChanged(boolean z) {
        this.mHandler.removeMessages(5);
        H h = this.mHandler;
        if (z) {
            h.sendMessage(h.obtainMessage(5, Boolean.TRUE));
        } else {
            h.obtainMessage(5, Boolean.FALSE).sendToTarget();
        }
    }

    public final boolean hasTitle() {
        if (getTag() != null) {
            return ("cellular".equals(getTag().toString()) || OplusQSHighlightTileViewLabelTextHandler.HOTSPOT.equals(getTag().toString()) || ScreenshotTile.TILE_SPEC.equals(getTag().toString()) || ScreenRecorderTile.TILE_SPEC.equals(getTag().toString())) ? false : true;
        }
        return true;
    }

    public final boolean isEmpty(Integer num) {
        return num == null || TextUtils.isEmpty(((FrameLayout) this).mContext.getString(num.intValue()));
    }

    public final boolean isItemsList() {
        List list = this.mListItems;
        return list != null && list.size() > 0;
    }

    public final void notifyDataSetChanged(boolean z) {
        String string = getTag() != null ? getTag().toString() : "";
        if ("cellular".equals(string)) {
            updateViewTypeInfo(5);
        } else if (OplusQSHighlightTileViewLabelTextHandler.HOTSPOT.equals(string)) {
            updateViewTypeInfo(6);
        } else {
            updateViewTypeInfo(0);
            updateViewTypeInfo(7);
        }
        Adapter adapter = this.mAdapter;
        if (z) {
            adapter.notifyDataSetChanged();
        } else {
            adapter.submitList(getListItems());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtil.d(LogUtil.TAG_QS, this.mTag, "onAttachedToWindow");
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        FontSizeUtils.updateFontSize(this.mEmptyText, 2131171644);
        int childCount = this.mItemList.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.mItemList.getChildAt(i);
            FontSizeUtils.updateFontSize((TextView) childAt.findViewById(R.id.title), 2131171674);
            FontSizeUtils.updateFontSize((TextView) childAt.findViewById(R.id.summary), 2131171675);
            BaseHolder childViewHolder = this.mItemList.getChildViewHolder(childAt);
            if (childViewHolder instanceof BaseHolder) {
                childViewHolder.onConfigurationChanged();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LogUtil.d(LogUtil.TAG_QS, this.mTag, "onDetachedFromWindow");
        this.mCallback = null;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        Drawable drawableMutate;
        super.onFinishInflate();
        boolean zIsNeedDarkThemeColor = QsColorUtil.isNeedDarkThemeColor(((FrameLayout) this).mContext, false);
        Context contextCreateDarkColorContext = QsColorUtil.createDarkColorContext(((FrameLayout) this).mContext, zIsNeedDarkThemeColor);
        COUIRecyclerView cOUIRecyclerViewFindViewById = findViewById(R.id.list);
        this.mItemList = cOUIRecyclerViewFindViewById;
        cOUIRecyclerViewFindViewById.setVisibility(8);
        this.mItemList.setLayoutManager(new LinearLayoutManager(1));
        if (zIsNeedDarkThemeColor && (drawableMutate = this.mItemList.getVerticalScrollbarThumbDrawable().mutate()) != null) {
            drawableMutate.setTintList(ColorStateList.valueOf(COUIContextUtil.getAttrColor(contextCreateDarkColorContext, 2130969314)));
            this.mItemList.setVerticalScrollbarThumbDrawable(drawableMutate);
        }
        Adapter adapter = new Adapter();
        this.mAdapter = adapter;
        this.mItemList.setAdapter(adapter);
        this.mItemList.setItemAnimator((RecyclerView.ItemAnimator) null);
        this.mItemList.setOverScrollEnable(true);
        this.mItemList.setForceDarkAllowed(false);
        this.mItemList.addItemDecoration(new AnonymousClass1(contextCreateDarkColorContext));
        View viewFindViewById = findViewById(R.id.empty);
        this.mEmpty = viewFindViewById;
        viewFindViewById.setVisibility(8);
        this.mEmptyText = (TextView) this.mEmpty.findViewById(R.id.title);
        this.mEmptyIcon = (ImageView) this.mEmpty.findViewById(R.id.icon);
        this.mOplusLoadingView = this.mEmpty.findViewById(2131362843);
        if (QsColorUtil.isNeedDarkThemeColor(((FrameLayout) this).mContext, false)) {
            this.mEmptyText.setTextColor(((FrameLayout) this).mContext.getColor(2131103685));
        }
    }

    public void setCallback(Callback callback) {
        this.mHandler.removeMessages(2);
        this.mHandler.obtainMessage(2, callback).sendToTarget();
    }

    public final void setEmptyState(int i, int i2) {
        ImageView imageView = this.mEmptyIcon;
        OplusQSDetailItems$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new OplusQSDetailItems$.ExternalSyntheticLambda1();
        externalSyntheticLambda1.f$0 = this;
        externalSyntheticLambda1.f$1 = i;
        externalSyntheticLambda1.f$2 = i2;
        VarHandle.storeStoreFence();
        imageView.post(externalSyntheticLambda1);
    }

    public void setItems(Item[] itemArr) {
        this.mHandler.removeMessages(1);
        this.mHandler.obtainMessage(1, itemArr).sendToTarget();
    }

    public void setItemsVisible(boolean z) {
        this.mHandler.removeMessages(3);
        this.mHandler.obtainMessage(3, z ? 1 : 0, 0).sendToTarget();
    }

    public final void setListItems(int i, List list, boolean z) {
        this.mHandler.removeMessages(4);
        Message messageObtainMessage = this.mHandler.obtainMessage(4, i, 0, list);
        if (z || Thread.currentThread() != this.mHandler.getLooper().getThread()) {
            messageObtainMessage.sendToTarget();
        } else {
            this.mHandler.handleMessage(messageObtainMessage);
        }
    }

    public void setRefreshing(boolean z) {
        this.mIsRefreshing = z;
        COUIRecyclerView cOUIRecyclerView = this.mItemList;
        OplusQSDetailItems$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new OplusQSDetailItems$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        cOUIRecyclerView.post(externalSyntheticLambda0);
    }

    public void setTagSuffix(String str) {
        this.mTag = FileProvider$.ExternalSyntheticOutline0.m("OplusQSDetailItems.", str);
    }

    public final void updateViewTypeInfo(int i) {
        if (isItemsList()) {
            int size = this.mListItems.size();
            int i2 = 0;
            int i3 = -1;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                }
                Item item = (Item) this.mListItems.get(i2);
                if (i == item.viewType) {
                    item.isCategoryFirstItem = false;
                    item.isCategoryLastItem = false;
                    if (i3 == -1) {
                        i3 = i2;
                    }
                    int i4 = i2 + 1;
                    if (i4 == size || ((Item) this.mListItems.get(i4)).viewType != i) {
                        break;
                    }
                }
                i2++;
            }
            if (i3 != -1) {
                ((Item) this.mListItems.get(i3)).isCategoryFirstItem = true;
            }
            if (i2 != -1) {
                ((Item) this.mListItems.get(i2)).isCategoryLastItem = true;
                return;
            }
            return;
        }
        Item[] itemArr = this.mItems;
        if (itemArr == null) {
            return;
        }
        int length = itemArr.length;
        int i5 = 0;
        int i6 = -1;
        while (true) {
            if (i5 >= length) {
                i5 = -1;
                break;
            }
            Item[] itemArr2 = this.mItems;
            Item item2 = itemArr2[i5];
            if (i == item2.viewType) {
                item2.isCategoryFirstItem = false;
                item2.isCategoryLastItem = false;
                if (i6 == -1) {
                    i6 = i5;
                }
                int i7 = i5 + 1;
                if (i7 == length || itemArr2[i7].viewType != i) {
                    break;
                }
            }
            i5++;
        }
        if (i6 != -1) {
            ((Item) this.mListItems.get(i6)).isCategoryFirstItem = true;
        }
        if (i5 != -1) {
            ((Item) this.mListItems.get(i5)).isCategoryLastItem = true;
        }
    }
}
