package org.example.groworders.domain.payment.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPaymentEntity is a Querydsl query type for PaymentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentEntity extends EntityPathBase<PaymentEntity> {

    private static final long serialVersionUID = -357841491L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPaymentEntity paymentEntity = new QPaymentEntity("paymentEntity");

    public final StringPath bankCode = createString("bankCode");

    public final StringPath bankName = createString("bankName");

    public final StringPath buyerAddr = createString("buyerAddr");

    public final StringPath buyerEmail = createString("buyerEmail");

    public final org.example.groworders.domain.crops.model.entity.QCrop crop;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath impUid = createString("impUid");

    public final StringPath merchantUid = createString("merchantUid");

    public final org.example.groworders.domain.orders.model.entity.QOrder order;

    public final DateTimePath<java.time.LocalDateTime> paidAt = createDateTime("paidAt", java.time.LocalDateTime.class);

    public final StringPath payMethod = createString("payMethod");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath productName = createString("productName");

    public final StringPath productOption = createString("productOption");

    public final NumberPath<Long> quantity = createNumber("quantity", Long.class);

    public final BooleanPath review = createBoolean("review");

    public final EnumPath<PaymentStatus> statusType = createEnum("statusType", PaymentStatus.class);

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public final org.example.groworders.domain.users.model.entity.QUser user;

    public QPaymentEntity(String variable) {
        this(PaymentEntity.class, forVariable(variable), INITS);
    }

    public QPaymentEntity(Path<? extends PaymentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPaymentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPaymentEntity(PathMetadata metadata, PathInits inits) {
        this(PaymentEntity.class, metadata, inits);
    }

    public QPaymentEntity(Class<? extends PaymentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.crop = inits.isInitialized("crop") ? new org.example.groworders.domain.crops.model.entity.QCrop(forProperty("crop"), inits.get("crop")) : null;
        this.order = inits.isInitialized("order") ? new org.example.groworders.domain.orders.model.entity.QOrder(forProperty("order"), inits.get("order")) : null;
        this.user = inits.isInitialized("user") ? new org.example.groworders.domain.users.model.entity.QUser(forProperty("user")) : null;
    }

}

