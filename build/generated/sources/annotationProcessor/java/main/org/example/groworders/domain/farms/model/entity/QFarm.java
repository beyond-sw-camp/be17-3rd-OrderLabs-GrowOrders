package org.example.groworders.domain.farms.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFarm is a Querydsl query type for Farm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFarm extends EntityPathBase<Farm> {

    private static final long serialVersionUID = 100009033L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFarm farm = new QFarm("farm");

    public final StringPath address = createString("address");

    public final StringPath contents = createString("contents");

    public final ListPath<org.example.groworders.domain.crops.model.entity.Crop, org.example.groworders.domain.crops.model.entity.QCrop> cropList = this.<org.example.groworders.domain.crops.model.entity.Crop, org.example.groworders.domain.crops.model.entity.QCrop>createList("cropList", org.example.groworders.domain.crops.model.entity.Crop.class, org.example.groworders.domain.crops.model.entity.QCrop.class, PathInits.DIRECT2);

    public final StringPath farmImage = createString("farmImage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath region = createString("region");

    public final NumberPath<Integer> size = createNumber("size", Integer.class);

    public final org.example.groworders.domain.users.model.entity.QUser user;

    public QFarm(String variable) {
        this(Farm.class, forVariable(variable), INITS);
    }

    public QFarm(Path<? extends Farm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFarm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFarm(PathMetadata metadata, PathInits inits) {
        this(Farm.class, metadata, inits);
    }

    public QFarm(Class<? extends Farm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new org.example.groworders.domain.users.model.entity.QUser(forProperty("user")) : null;
    }

}

