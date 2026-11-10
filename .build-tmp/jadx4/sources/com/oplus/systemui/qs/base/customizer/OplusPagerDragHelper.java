package com.oplus.systemui.qs.base.customizer;

import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.DragEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.plugins.qs.QSTileQueryHelper;
import com.oplus.systemui.qs.customize.OplusQSCustomizer;
import com.oplus.systemui.qs.customize.OplusTileAdapter;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.qs.util.QsStatisticUtil;
import java.lang.invoke.VarHandle;
import java.text.MessageFormat;
import java.util.Observable;
import java.util.Observer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPagerDragHelper extends Observable implements View.OnDragListener, Observer {
    public OplusQSBaseDragAdapter mAdapter;
    public final OplusQSCustomizer.AnonymousClass6 mCallback;
    public final String mClassName;
    public QSTileQueryHelper.OplusTileInfo mDragStartTile;
    public final Handler mHandler;
    public OplusPagerGridLayoutManager mLayoutManager;
    public RecyclerView mOriginView;
    public final ShowPageRunnable mShowPageRunnable;
    public final AnonymousClass1 mTransparentShadowBuilder;
    public String mTag = "";
    public int mStartDragPosition = -1;
    public int mInsertPosition = -1;
    public boolean mIsReadyToShowPage = false;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.base.customizer.OplusPagerDragHelper$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 extends View.DragShadowBuilder {
        @Override // android.view.View.DragShadowBuilder
        public final void onDrawShadow(Canvas canvas) {
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ShowPageRunnable implements Runnable {
        public boolean nextPage;
        public RecyclerView rv;
        public /* synthetic */ OplusPagerDragHelper this$0;

        @Override // java.lang.Runnable
        public final void run() {
            OplusPagerDragHelper oplusPagerDragHelper = this.this$0;
            RecyclerView recyclerView = this.rv;
            boolean z = this.nextPage;
            if (recyclerView != null) {
                oplusPagerDragHelper.log("show next page:" + z);
                if (recyclerView.getLayoutManager() instanceof OplusPagerGridLayoutManager) {
                    OplusPagerGridLayoutManager oplusPagerGridLayoutManager = (OplusPagerGridLayoutManager) recyclerView.getLayoutManager();
                    if (oplusPagerGridLayoutManager.mShouldReverseLayout) {
                        if (z) {
                            oplusPagerGridLayoutManager.smoothScrollToPage(oplusPagerGridLayoutManager.getPageIndexByOffset() - 1);
                        } else {
                            oplusPagerGridLayoutManager.smoothScrollToPage(oplusPagerGridLayoutManager.getPageIndexByOffset() + 1);
                        }
                    } else if (z) {
                        oplusPagerGridLayoutManager.smoothScrollToPage(oplusPagerGridLayoutManager.getPageIndexByOffset() + 1);
                    } else {
                        oplusPagerGridLayoutManager.smoothScrollToPage(oplusPagerGridLayoutManager.getPageIndexByOffset() - 1);
                    }
                }
            }
            OplusPagerDragHelper oplusPagerDragHelper2 = this.this$0;
            oplusPagerDragHelper2.mIsReadyToShowPage = false;
            oplusPagerDragHelper2.readyShowPage(this.rv, this.nextPage);
        }
    }

    public OplusPagerDragHelper(OplusQSCustomizer.AnonymousClass6 anonymousClass6) {
        ShowPageRunnable showPageRunnable = new ShowPageRunnable();
        showPageRunnable.this$0 = this;
        VarHandle.storeStoreFence();
        this.mShowPageRunnable = showPageRunnable;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mClassName = "OplusPagerDragHelper";
        this.mTransparentShadowBuilder = new AnonymousClass1();
        this.mCallback = anonymousClass6;
    }

    private int getEmptyTileIndex() {
        for (int i = 0; i < this.mAdapter.getItemCount(); i++) {
            QSTileQueryHelper.OplusTileInfo tile = this.mAdapter.getTile(i);
            if (tile != null && !tile.getVisible() && tile.getSpec() == null) {
                return i;
            }
        }
        return -1;
    }

    public final void checkHighlightTileSize() {
        boolean zM264$$Nest$mhandleCheckHighLightTileSize;
        int i;
        OplusQSCustomizer.AnonymousClass6 anonymousClass6 = this.mCallback;
        if (anonymousClass6 != null) {
            switch (anonymousClass6.$r8$classId) {
                case 1:
                    zM264$$Nest$mhandleCheckHighLightTileSize = OplusQSCustomizer.m264$$Nest$mhandleCheckHighLightTileSize(anonymousClass6.this$0);
                    break;
                case 2:
                    zM264$$Nest$mhandleCheckHighLightTileSize = OplusQSCustomizer.m264$$Nest$mhandleCheckHighLightTileSize(anonymousClass6.this$0);
                    break;
                default:
                    zM264$$Nest$mhandleCheckHighLightTileSize = OplusQSCustomizer.m264$$Nest$mhandleCheckHighLightTileSize(anonymousClass6.this$0);
                    break;
            }
            if (!zM264$$Nest$mhandleCheckHighLightTileSize || this.mAdapter.getEditType() == IQSCustomizer$EditType.BOTTOM || (i = this.mInsertPosition) <= 0) {
                return;
            }
            this.mInsertPosition = i - 1;
        }
    }

    public final void log(String str) {
        if (TextUtils.isEmpty(this.mTag)) {
            return;
        }
        LogUtil.d(LogUtil.TAG_QS, this.mClassName + "-" + this.mTag, str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:124:0x02d7  */
    @Override // android.view.View.OnDragListener
    public final boolean onDrag(View view, DragEvent dragEvent) {
        int currentPageLastPos;
        int i;
        int i2;
        QSTileQueryHelper.OplusTileInfo oplusTileInfo;
        Runnable runnable;
        Runnable runnable2;
        OplusQSCustomizer.AnonymousClass6 anonymousClass6;
        boolean z;
        RecyclerView recyclerView = (RecyclerView) view;
        this.mOriginView = recyclerView;
        this.mAdapter = (OplusQSBaseDragAdapter) recyclerView.getAdapter();
        this.mLayoutManager = (OplusPagerGridLayoutManager) this.mOriginView.getLayoutManager();
        switch (dragEvent.getAction()) {
            case 1:
                QSTileQueryHelper.OplusTileInfo oplusTileInfo2 = (QSTileQueryHelper.OplusTileInfo) dragEvent.getLocalState();
                this.mDragStartTile = oplusTileInfo2;
                int tilePosition = this.mAdapter.getTilePosition(oplusTileInfo2);
                this.mInsertPosition = tilePosition;
                this.mStartDragPosition = tilePosition;
                if (tilePosition != -1) {
                    this.mAdapter.setTileVisible(tilePosition, false);
                }
                OplusQSCustomizer.AnonymousClass6 anonymousClass7 = this.mCallback;
                if (anonymousClass7 != null) {
                    int i3 = this.mStartDragPosition != -1 ? 1 : 0;
                    switch (anonymousClass7.$r8$classId) {
                        case 1:
                            if (i3 != 0) {
                                OplusQSCustomizer oplusQSCustomizer = anonymousClass7.this$0;
                                int i4 = OplusQSCustomizer.$r8$clinit;
                                oplusQSCustomizer.showMinCountHint();
                            }
                            break;
                        case 2:
                            if (i3 != 0) {
                                OplusQSCustomizer oplusQSCustomizer2 = anonymousClass7.this$0;
                                int i5 = OplusQSCustomizer.$r8$clinit;
                                oplusQSCustomizer2.showMinCountHint();
                            }
                            break;
                    }
                }
                log(MessageFormat.format("drag start:{0}|{1} insertPos:{2}", Float.valueOf(dragEvent.getX()), Float.valueOf(dragEvent.getY()), Integer.valueOf(this.mInsertPosition)));
                return true;
            case 2:
                if (dragEvent.getX() < 100.0f) {
                    OplusPagerGridLayoutManager oplusPagerGridLayoutManager = this.mLayoutManager;
                    if ((!oplusPagerGridLayoutManager.mShouldReverseLayout || (oplusPagerGridLayoutManager.getCurrentPageLastPos() + 1) % oplusPagerGridLayoutManager.mOnePageSize == 0) && this.mAdapter.getEditType() != IQSCustomizer$EditType.HIGHLIGHT) {
                        readyShowPage(this.mOriginView, false);
                        checkHighlightTileSize();
                        return true;
                    }
                }
                if (dragEvent.getX() > this.mOriginView.getWidth() - 100) {
                    OplusPagerGridLayoutManager oplusPagerGridLayoutManager2 = this.mLayoutManager;
                    if ((oplusPagerGridLayoutManager2.mShouldReverseLayout || (oplusPagerGridLayoutManager2.getCurrentPageLastPos() + 1) % oplusPagerGridLayoutManager2.mOnePageSize == 0) && this.mAdapter.getEditType() != IQSCustomizer$EditType.HIGHLIGHT) {
                        readyShowPage(this.mOriginView, true);
                        checkHighlightTileSize();
                        return true;
                    }
                }
                this.mIsReadyToShowPage = false;
                this.mHandler.removeCallbacks(this.mShowPageRunnable);
                RecyclerView recyclerView2 = this.mOriginView;
                float x = dragEvent.getX();
                float y = dragEvent.getY();
                View viewFindChildViewUnder = recyclerView2.findChildViewUnder(x, y);
                if (viewFindChildViewUnder != null) {
                    int width = viewFindChildViewUnder.getWidth() / 5;
                    int height = viewFindChildViewUnder.getHeight() / 5;
                    if (x <= viewFindChildViewUnder.getLeft() + width || x >= viewFindChildViewUnder.getRight() - width || y <= viewFindChildViewUnder.getTop() + height || y >= viewFindChildViewUnder.getBottom() - height) {
                        currentPageLastPos = -1;
                    } else {
                        currentPageLastPos = recyclerView2.getChildViewHolder(viewFindChildViewUnder).getAdapterPosition();
                    }
                } else {
                    currentPageLastPos = -1;
                }
                if (currentPageLastPos == -1 && (i2 = this.mInsertPosition) != -1) {
                    OplusPagerGridLayoutManager oplusPagerGridLayoutManager3 = this.mLayoutManager;
                    int i6 = oplusPagerGridLayoutManager3.mOnePageSize;
                    if ((i6 != 0 ? i2 / i6 : 0) != oplusPagerGridLayoutManager3.getPageIndexByOffset()) {
                        currentPageLastPos = this.mLayoutManager.getCurrentPageLastPos();
                    }
                }
                if (currentPageLastPos >= 0 && (i = this.mInsertPosition) != currentPageLastPos && i != -1) {
                    log(MessageFormat.format("drag loc swap tile:{0}|{1} insertPos:{2} fingerUnderPos:{3}", Float.valueOf(dragEvent.getX()), Float.valueOf(dragEvent.getY()), Integer.valueOf(this.mInsertPosition), Integer.valueOf(currentPageLastPos)));
                    this.mAdapter.swapTile(this.mInsertPosition, currentPageLastPos);
                    this.mInsertPosition = currentPageLastPos;
                }
                int i7 = this.mInsertPosition;
                if ((i7 == currentPageLastPos && i7 != -1) || i7 >= this.mLayoutManager.getCurrentPageLastPos()) {
                    checkHighlightTileSize();
                }
                return true;
            case 3:
                log(MessageFormat.format("drag drop:{0}|{1} insertPos:{2}", Float.valueOf(dragEvent.getX()), Float.valueOf(dragEvent.getY()), Integer.valueOf(this.mInsertPosition)));
                int i8 = this.mInsertPosition;
                if (i8 != -1) {
                    this.mAdapter.setTileVisible(i8, true);
                }
                boolean z2 = this.mInsertPosition != this.mStartDragPosition;
                if (!z2) {
                    view.updateDragShadow(this.mTransparentShadowBuilder);
                }
                this.mInsertPosition = -1;
                this.mIsReadyToShowPage = false;
                this.mHandler.removeCallbacks(this.mShowPageRunnable);
                return z2;
            case 4:
                log(MessageFormat.format("drag end:{0}|{1} insertPos:{2}", Float.valueOf(dragEvent.getX()), Float.valueOf(dragEvent.getY()), Integer.valueOf(this.mInsertPosition)));
                OplusQSCustomizer.AnonymousClass6 anonymousClass8 = this.mCallback;
                if (anonymousClass8 != null) {
                    boolean z3 = this.mStartDragPosition != -1;
                    switch (anonymousClass8.$r8$classId) {
                        case 1:
                            OplusQSCustomizer oplusQSCustomizer3 = anonymousClass8.this$0;
                            RecyclerView recyclerView3 = oplusQSCustomizer3.mRecyclerViewHighLight;
                            if (recyclerView3 != null && (runnable = oplusQSCustomizer3.mSaveRunnable) != null) {
                                recyclerView3.postDelayed(runnable, 450L);
                            }
                            if (z3) {
                                oplusQSCustomizer3.showMinCountHint();
                            }
                            QsStatisticUtil.collectEditTitleTypeEvent(oplusQSCustomizer3.getContext(), "edit_tile_drag");
                            break;
                        case 2:
                            OplusQSCustomizer oplusQSCustomizer4 = anonymousClass8.this$0;
                            RecyclerView recyclerView4 = oplusQSCustomizer4.mRecyclerViewTop;
                            if (recyclerView4 != null && (runnable2 = oplusQSCustomizer4.mSaveRunnable) != null) {
                                recyclerView4.postDelayed(runnable2, 450L);
                            }
                            if (z3) {
                                oplusQSCustomizer4.showMinCountHint();
                            }
                            break;
                        default:
                            OplusQSCustomizer oplusQSCustomizer5 = anonymousClass8.this$0;
                            OplusTileAdapter oplusTileAdapter = oplusQSCustomizer5.mTileAdapterBottom;
                            if (oplusTileAdapter != null) {
                                oplusTileAdapter.setTilesChanged(true);
                            }
                            if (z3) {
                                oplusQSCustomizer5.showMinCountHint();
                            }
                            break;
                    }
                }
                int i9 = this.mInsertPosition;
                if (i9 != -1) {
                    QSTileQueryHelper.OplusTileInfo tile = this.mAdapter.getTile(i9);
                    log("Multi finger operation requires position correction, info=" + tile + " mInsertPosition=" + this.mInsertPosition + " dragStartTile=" + this.mDragStartTile);
                    if (tile != null && (oplusTileInfo = this.mDragStartTile) != null && tile != oplusTileInfo) {
                        this.mInsertPosition = this.mAdapter.getTilePosition(oplusTileInfo);
                    } else if (tile == null && this.mInsertPosition == this.mAdapter.getItemCount()) {
                        this.mInsertPosition--;
                    }
                    this.mAdapter.setTileVisible(this.mInsertPosition, true);
                }
                this.mInsertPosition = -1;
                this.mIsReadyToShowPage = false;
                this.mHandler.removeCallbacks(this.mShowPageRunnable);
                return dragEvent.getResult();
            case 5:
                log(MessageFormat.format("drag enter:{0}|{1} insertPos:{2}", Float.valueOf(dragEvent.getX()), Float.valueOf(dragEvent.getY()), Integer.valueOf(this.mInsertPosition)));
                if (this.mStartDragPosition == -1 && (anonymousClass6 = this.mCallback) != null) {
                    switch (anonymousClass6.$r8$classId) {
                        default:
                            OplusTileAdapter oplusTileAdapter2 = anonymousClass6.this$0.mTileAdapterTop;
                            if (oplusTileAdapter2 == null || oplusTileAdapter2.getRealItemCount() <= 8) {
                                z = false;
                                break;
                            }
                        case 1:
                        case 2:
                            z = true;
                            break;
                    }
                    if (!z) {
                        return false;
                    }
                }
                if (this.mInsertPosition == -1) {
                    if (this.mLayoutManager.getItemCount() == 0) {
                        this.mInsertPosition = 0;
                    } else {
                        this.mInsertPosition = this.mLayoutManager.getCurrentPageLastPos();
                        if (this.mLayoutManager.getPageIndexByOffset() == this.mLayoutManager.getTotalPageCount() - 1) {
                            this.mInsertPosition++;
                        }
                        int emptyTileIndex = getEmptyTileIndex();
                        if (emptyTileIndex > -1 && this.mInsertPosition > emptyTileIndex) {
                            this.mInsertPosition = emptyTileIndex;
                        }
                    }
                    QSTileQueryHelper.OplusTileInfo oplusTileInfo3 = (QSTileQueryHelper.OplusTileInfo) dragEvent.getLocalState();
                    if (oplusTileInfo3 != null) {
                        oplusTileInfo3.setVisible(false);
                        this.mAdapter.addTile(oplusTileInfo3, this.mInsertPosition);
                        log(MessageFormat.format("drag enter add tile:{0}|{1} insertPos:{2}", Float.valueOf(dragEvent.getX()), Float.valueOf(dragEvent.getY()), Integer.valueOf(this.mInsertPosition)));
                        setChanged();
                        notifyObservers();
                        return true;
                    }
                }
                return true;
            case 6:
                log(MessageFormat.format("drag exit:{0}|{1} insertPos:{2}", Float.valueOf(dragEvent.getX()), Float.valueOf(dragEvent.getY()), Integer.valueOf(this.mInsertPosition)));
                return true;
            default:
                return true;
        }
    }

    public final void readyShowPage(RecyclerView recyclerView, boolean z) {
        if (this.mIsReadyToShowPage) {
            return;
        }
        this.mIsReadyToShowPage = true;
        ShowPageRunnable showPageRunnable = this.mShowPageRunnable;
        showPageRunnable.rv = recyclerView;
        showPageRunnable.nextPage = z;
        this.mHandler.postDelayed(showPageRunnable, 800L);
    }

    public void setTag(String str) {
        this.mTag = str;
    }

    @Override // java.util.Observer
    public final void update(Observable observable, Object obj) {
        log("receive observable notify , now remove tile:" + this.mInsertPosition);
        int i = this.mInsertPosition;
        if (i != -1) {
            this.mAdapter.removeTile(i);
        }
        this.mInsertPosition = -1;
        this.mIsReadyToShowPage = false;
        this.mHandler.removeCallbacks(this.mShowPageRunnable);
    }
}
