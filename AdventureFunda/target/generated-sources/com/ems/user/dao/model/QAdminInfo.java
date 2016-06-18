package com.ems.user.dao.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAdminInfo is a Querydsl query type for AdminInfo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAdminInfo extends EntityPathBase<AdminInfo> {

    private static final long serialVersionUID = 665479177L;

    public static final QAdminInfo adminInfo = new QAdminInfo("adminInfo");

    public final StringPath adminType = createString("adminType");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("createdDate", java.sql.Timestamp.class);

    public final StringPath emailId = createString("emailId");

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> lastLoginDateTime = createDateTime("lastLoginDateTime", java.sql.Timestamp.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath mobileNo = createString("mobileNo");

    public final StringPath password = createString("password");

    public final StringPath reason = createString("reason");

    public final StringPath status = createString("status");

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.sql.Timestamp> updatedDate = createDateTime("updatedDate", java.sql.Timestamp.class);

    public final StringPath userName = createString("userName");

    public QAdminInfo(String variable) {
        super(AdminInfo.class, forVariable(variable));
    }

    public QAdminInfo(Path<? extends AdminInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdminInfo(PathMetadata<?> metadata) {
        super(AdminInfo.class, metadata);
    }

}

