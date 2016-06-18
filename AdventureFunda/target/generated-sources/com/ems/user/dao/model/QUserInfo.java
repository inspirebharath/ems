package com.ems.user.dao.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUserInfo is a Querydsl query type for UserInfo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserInfo extends EntityPathBase<UserInfo> {

    private static final long serialVersionUID = 31063597L;

    public static final QUserInfo userInfo = new QUserInfo("userInfo");

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("createdDate", java.sql.Timestamp.class);

    public final StringPath dob = createString("dob");

    public final StringPath emailId = createString("emailId");

    public final StringPath firstName = createString("firstName");

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath isGuestUser = createString("isGuestUser");

    public final DateTimePath<java.sql.Timestamp> lastLoginDateTime = createDateTime("lastLoginDateTime", java.sql.Timestamp.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath mobileNo = createString("mobileNo");

    public final StringPath otp = createString("otp");

    public final StringPath password = createString("password");

    public final NumberPath<Integer> points = createNumber("points", Integer.class);

    public final StringPath reason = createString("reason");

    public final NumberPath<Integer> referalCode = createNumber("referalCode", Integer.class);

    public final NumberPath<Integer> referedCode = createNumber("referedCode", Integer.class);

    public final StringPath state = createString("state");

    public final StringPath status = createString("status");

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.sql.Timestamp> updatedDate = createDateTime("updatedDate", java.sql.Timestamp.class);

    public final StringPath userName = createString("userName");

    public final StringPath verificationStatus = createString("verificationStatus");

    public final StringPath zipCode = createString("zipCode");

    public QUserInfo(String variable) {
        super(UserInfo.class, forVariable(variable));
    }

    public QUserInfo(Path<? extends UserInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserInfo(PathMetadata<?> metadata) {
        super(UserInfo.class, metadata);
    }

}

