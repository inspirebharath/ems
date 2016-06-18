package com.ems.user.dao.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEventOrganizers is a Querydsl query type for EventOrganizers
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEventOrganizers extends EntityPathBase<EventOrganizers> {

    private static final long serialVersionUID = 1826657226L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventOrganizers eventOrganizers = new QEventOrganizers("eventOrganizers");

    public final QAdminInfo adminId;

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("createdDate", java.sql.Timestamp.class);

    public final QAdminInfo eventId;

    public final StringPath eventLocation = createString("eventLocation");

    public final NumberPath<Long> eventOrganizerId = createNumber("eventOrganizerId", Long.class);

    public final StringPath medicalCertificateStatus = createString("medicalCertificateStatus");

    public final QPartnerInfo partnerId;

    public final NumberPath<Integer> partnerQuotedPrice = createNumber("partnerQuotedPrice", Integer.class);

    public final DateTimePath<java.sql.Timestamp> paymentLastDate = createDateTime("paymentLastDate", java.sql.Timestamp.class);

    public final StringPath state = createString("state");

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.sql.Timestamp> updatedDate = createDateTime("updatedDate", java.sql.Timestamp.class);

    public final StringPath zipCode = createString("zipCode");

    public QEventOrganizers(String variable) {
        this(EventOrganizers.class, forVariable(variable), INITS);
    }

    public QEventOrganizers(Path<? extends EventOrganizers> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEventOrganizers(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEventOrganizers(PathMetadata<?> metadata, PathInits inits) {
        this(EventOrganizers.class, metadata, inits);
    }

    public QEventOrganizers(Class<? extends EventOrganizers> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.adminId = inits.isInitialized("adminId") ? new QAdminInfo(forProperty("adminId")) : null;
        this.eventId = inits.isInitialized("eventId") ? new QAdminInfo(forProperty("eventId")) : null;
        this.partnerId = inits.isInitialized("partnerId") ? new QPartnerInfo(forProperty("partnerId")) : null;
    }

}

