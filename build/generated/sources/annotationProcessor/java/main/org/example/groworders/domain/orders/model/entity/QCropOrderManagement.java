package org.example.groworders.domain.orders.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCropOrderManagement is a Querydsl query type for CropOrderManagement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCropOrderManagement extends EntityPathBase<CropOrderManagement> {

    private static final long serialVersionUID = -1940786334L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCropOrderManagement cropOrderManagement = new QCropOrderManagement("cropOrderManagement");

    public final ListPath<org.example.groworders.domain.cart.model.entity.Cart, org.example.groworders.domain.cart.model.entity.QCart> carts = this.<org.example.groworders.domain.cart.model.entity.Cart, org.example.groworders.domain.cart.model.entity.QCart>createList("carts", org.example.groworders.domain.cart.model.entity.Cart.class, org.example.groworders.domain.cart.model.entity.QCart.class, PathInits.DIRECT2);

    public final StringPath color = createString("color");

    public final org.example.groworders.domain.crops.model.entity.QCrop crop;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final StringPath size = createString("size");

    public QCropOrderManagement(String variable) {
        this(CropOrderManagement.class, forVariable(variable), INITS);
    }

    public QCropOrderManagement(Path<? extends CropOrderManagement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCropOrderManagement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCropOrderManagement(PathMetadata metadata, PathInits inits) {
        this(CropOrderManagement.class, metadata, inits);
    }

    public QCropOrderManagement(Class<? extends CropOrderManagement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.crop = inits.isInitialized("crop") ? new org.example.groworders.domain.crops.model.entity.QCrop(forProperty("crop"), inits.get("crop")) : null;
    }

}

