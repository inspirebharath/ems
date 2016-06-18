package com.ems.user.dao.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEventInfo is a Querydsl query type for EventInfo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEventInfo extends EntityPathBase<EventInfo> {

    private static final long serialVersionUID = 675035604L;

    public static final QEventInfo eventInfo = new QEventInfo("eventInfo");

    public final NumberPath<Integer> adminQuotedPrice = createNumber("adminQuotedPrice", Integer.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("createdDate", java.sql.Timestamp.class);

    public final ComparablePath<Character> difficultyGrade = createComparable("difficultyGrade", Character.class);

    public final DateTimePath<java.sql.Timestamp> endDate = createDateTime("endDate", java.sql.Timestamp.class);

    public final NumberPath<Long> eventId = createNumber("eventId", Long.class);

    public final StringPath eventName = createString("eventName");

    public final ListPath<EventOrganizers, QEventOrganizers> eventOrganizers = this.<EventOrganizers, QEventOrganizers>createList("eventOrganizers", EventOrganizers.class, QEventOrganizers.class, PathInits.DIRECT2);

    public final StringPath eventStatus = createString("eventStatus");

    public final StringPath eventType = createString("eventType");

    public final ComparablePath<Character> fitnessLevel = createComparable("fitnessLevel", Character.class);

    public final NumberPath<Integer> maxNoOfParticipants = createNumber("maxNoOfParticipants", Integer.class);

    public final NumberPath<Integer> minNoOfParticipants = createNumber("minNoOfParticipants", Integer.class);

    public final NumberPath<Integer> noOfBatches = createNumber("noOfBatches", Integer.class);

    public final StringPath reason = createString("reason");

    public final StringPath shortDesc = createString("shortDesc");

    public final DateTimePath<java.sql.Timestamp> startDate = createDateTime("startDate", java.sql.Timestamp.class);

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.sql.Timestamp> updatedDate = createDateTime("updatedDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> viewedCount = createNumber("viewedCount", Integer.class);

    public QEventInfo(String variable) {
        super(EventInfo.class, forVariable(variable));
    }

    public QEventInfo(Path<? extends EventInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventInfo(PathMetadata<?> metadata) {
        super(EventInfo.class, metadata);
    }

}

