package com.ems.user.dao.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPartnerInfo is a Querydsl query type for PartnerInfo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPartnerInfo extends EntityPathBase<PartnerInfo> {

    private static final long serialVersionUID = 1328591362L;

    public static final QPartnerInfo partnerInfo = new QPartnerInfo("partnerInfo");

    public final StringPath alternativeNo = createString("alternativeNo");

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("createdDate", java.sql.Timestamp.class);

    public final StringPath dob = createString("dob");

    public final StringPath emailId = createString("emailId");

    public final StringPath firstName = createString("firstName");

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> lastLoginDateTime = createDateTime("lastLoginDateTime", java.sql.Timestamp.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath merchantDesc = createString("merchantDesc");

    public final StringPath mobileNo = createString("mobileNo");

    public final StringPath organizationName = createString("organizationName");

    public final StringPath otp = createString("otp");

    public final StringPath partnerType = createString("partnerType");

    public final StringPath password = createString("password");

    public final StringPath reason = createString("reason");

    public final StringPath state = createString("state");

    public final StringPath status = createString("status");

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.sql.Timestamp> updatedDate = createDateTime("updatedDate", java.sql.Timestamp.class);

    public final StringPath userName = createString("userName");

    public final StringPath verificationStatus = createString("verificationStatus");

    public final StringPath websiteAddress = createString("websiteAddress");

    public final StringPath zipCode = createString("zipCode");

    public QPartnerInfo(String variable) {
        super(PartnerInfo.class, forVariable(variable));
    }

    public QPartnerInfo(Path<? extends PartnerInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPartnerInfo(PathMetadata<?> metadata) {
        super(PartnerInfo.class, metadata);
    }

}

