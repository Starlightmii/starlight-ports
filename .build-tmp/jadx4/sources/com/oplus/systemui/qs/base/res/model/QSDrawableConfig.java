package com.oplus.systemui.qs.base.res.model;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface QSDrawableConfig {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Empty implements QSDrawableConfig {
        public static final Empty INSTANCE = new Empty();

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableConfig
        public /* bridge */ /* synthetic */ QSDrawableBuilder getDefaultTypeBuilder() {
            return super.getDefaultTypeBuilder();
        }

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableConfig
        public /* bridge */ /* synthetic */ QSDrawableBuilder getSepHighlightIconBgBuilder() {
            return super.getSepHighlightIconBgBuilder();
        }

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableConfig
        public /* bridge */ /* synthetic */ QSDrawableBuilder getSepHighlightTypeBuilder() {
            return super.getSepHighlightTypeBuilder();
        }

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableConfig
        public /* bridge */ /* synthetic */ QSDrawableBuilder getSepTileTypeBuilder() {
            return super.getSepTileTypeBuilder();
        }

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableConfig
        public /* bridge */ /* synthetic */ QSDrawableBuilder getStaticTypeBgBuilder() {
            return super.getStaticTypeBgBuilder();
        }

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableConfig
        public /* bridge */ /* synthetic */ QSDrawableBuilder getStdHighLightTypeBuilder() {
            return super.getStdHighLightTypeBuilder();
        }

        @Override // com.oplus.systemui.qs.base.res.model.QSDrawableConfig
        public /* bridge */ /* synthetic */ QSDrawableBuilder getStdTileTypeBuilder() {
            return super.getStdTileTypeBuilder();
        }
    }

    default QSDrawableBuilder getDefaultTypeBuilder() {
        return QSDrawableBuilder.Empty.INSTANCE;
    }

    default QSDrawableBuilder getSepHighlightIconBgBuilder() {
        return QSDrawableBuilder.Empty.INSTANCE;
    }

    default QSDrawableBuilder getSepHighlightTypeBuilder() {
        return getSepTileTypeBuilder();
    }

    default QSDrawableBuilder getSepTileTypeBuilder() {
        return getStdTileTypeBuilder();
    }

    default QSDrawableBuilder getStaticTypeBgBuilder() {
        return QSDrawableBuilder.Empty.INSTANCE;
    }

    default QSDrawableBuilder getStdHighLightTypeBuilder() {
        return QSDrawableBuilder.Empty.INSTANCE;
    }

    default QSDrawableBuilder getStdTileTypeBuilder() {
        return QSDrawableBuilder.Empty.INSTANCE;
    }
}
