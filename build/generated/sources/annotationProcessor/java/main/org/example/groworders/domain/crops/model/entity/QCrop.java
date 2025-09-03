package org.example.groworders.domain.crops.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCrop is a Querydsl query type for Crop
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCrop extends EntityPathBase<Crop> {

    private static final long serialVersionUID = -1487134199L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCrop crop = new QCrop("crop");

    public final NumberPath<Integer> area = createNumber("area", Integer.class);

    public final StringPath cultivateType = createString("cultivateType");

    public final DatePath<java.time.LocalDate> expectedHarvestDate = createDate("expectedHarvestDate", java.time.LocalDate.class);

    public final NumberPath<Integer> expectedQuantity = createNumber("expectedQuantity", Integer.class);

    public final org.example.groworders.domain.farms.model.entity.QFarm farm;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> maxExpectedQuantity = createNumber("maxExpectedQuantity", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final EnumPath<SaleStatus> saleStatus = createEnum("saleStatus", SaleStatus.class);

    public final DatePath<java.time.LocalDate> sowingStartDate = createDate("sowingStartDate", java.time.LocalDate.class);

    public final EnumPath<CropStatus> status = createEnum("status", CropStatus.class);

    public final StringPath type = createString("type");

    public QCrop(String variable) {
        this(Crop.class, forVariable(variable), INITS);
    }

    public QCrop(Path<? extends Crop> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCrop(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCrop(PathMetadata metadata, PathInits inits) {
        this(Crop.class, metadata, inits);
    }

    public QCrop(Class<? extends Crop> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.farm = inits.isInitialized("farm") ? new org.example.groworders.domain.farms.model.entity.QFarm(forProperty("farm"), inits.get("farm")) : null;
    }

}

