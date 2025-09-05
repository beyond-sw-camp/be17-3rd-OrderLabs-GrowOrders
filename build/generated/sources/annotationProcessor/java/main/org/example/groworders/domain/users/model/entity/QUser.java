package org.example.groworders.domain.users.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1229919703L;

    public static final QUser user = new QUser("user");

    public final StringPath accountId = createString("accountId");

    public final DatePath<java.time.LocalDate> birthDate = createDate("birthDate", java.time.LocalDate.class);

    public final StringPath email = createString("email");

    public final ListPath<org.example.groworders.domain.users.model.dto.EmailVerify, org.example.groworders.domain.users.model.dto.QEmailVerify> emailVerifyList = this.<org.example.groworders.domain.users.model.dto.EmailVerify, org.example.groworders.domain.users.model.dto.QEmailVerify>createList("emailVerifyList", org.example.groworders.domain.users.model.dto.EmailVerify.class, org.example.groworders.domain.users.model.dto.QEmailVerify.class, PathInits.DIRECT2);

    public final BooleanPath enabled = createBoolean("enabled");

    public final ListPath<org.example.groworders.domain.farms.model.entity.Farm, org.example.groworders.domain.farms.model.entity.QFarm> farmList = this.<org.example.groworders.domain.farms.model.entity.Farm, org.example.groworders.domain.farms.model.entity.QFarm>createList("farmList", org.example.groworders.domain.farms.model.entity.Farm.class, org.example.groworders.domain.farms.model.entity.QFarm.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath profileImage = createString("profileImage");

    public final ListPath<org.example.groworders.config.push.model.entity.PushHistory, org.example.groworders.config.push.model.entity.QPushHistory> pushHistoryList = this.<org.example.groworders.config.push.model.entity.PushHistory, org.example.groworders.config.push.model.entity.QPushHistory>createList("pushHistoryList", org.example.groworders.config.push.model.entity.PushHistory.class, org.example.groworders.config.push.model.entity.QPushHistory.class, PathInits.DIRECT2);

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

