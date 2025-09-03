package org.example.groworders.domain.users.model.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmailVerify is a Querydsl query type for EmailVerify
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmailVerify extends EntityPathBase<EmailVerify> {

    private static final long serialVersionUID = -1767233767L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmailVerify emailVerify = new QEmailVerify("emailVerify");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final org.example.groworders.domain.users.model.entity.QUser user;

    public final StringPath uuid = createString("uuid");

    public QEmailVerify(String variable) {
        this(EmailVerify.class, forVariable(variable), INITS);
    }

    public QEmailVerify(Path<? extends EmailVerify> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmailVerify(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmailVerify(PathMetadata metadata, PathInits inits) {
        this(EmailVerify.class, metadata, inits);
    }

    public QEmailVerify(Class<? extends EmailVerify> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new org.example.groworders.domain.users.model.entity.QUser(forProperty("user")) : null;
    }

}

