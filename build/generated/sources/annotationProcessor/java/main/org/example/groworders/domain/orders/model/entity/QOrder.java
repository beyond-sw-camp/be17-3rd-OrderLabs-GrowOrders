package org.example.groworders.domain.orders.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = -43660561L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final StringPath address = createString("address");

    public final StringPath detailAddress = createString("detailAddress");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath merchantUid = createString("merchantUid");

    public final DateTimePath<java.time.LocalDateTime> orderDay = createDateTime("orderDay", java.time.LocalDateTime.class);

    public final StringPath ordererName = createString("ordererName");

    public final StringPath orderId = createString("orderId");

    public final ListPath<OrderItem, QOrderItem> orderItems = this.<OrderItem, QOrderItem>createList("orderItems", OrderItem.class, QOrderItem.class, PathInits.DIRECT2);

    public final EnumPath<org.example.groworders.domain.payment.model.entity.PaymentStatus> paymentStatus = createEnum("paymentStatus", org.example.groworders.domain.payment.model.entity.PaymentStatus.class);

    public final EnumPath<org.example.groworders.domain.payment.model.entity.PayMethod> payMethod = createEnum("payMethod", org.example.groworders.domain.payment.model.entity.PayMethod.class);

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath postCode = createString("postCode");

    public final StringPath productName = createString("productName");

    public final NumberPath<Long> totalPrice = createNumber("totalPrice", Long.class);

    public final org.example.groworders.domain.users.model.entity.QUser user;

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new org.example.groworders.domain.users.model.entity.QUser(forProperty("user")) : null;
    }

}

