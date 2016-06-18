package com.ems.user.dao.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPageHits is a Querydsl query type for PageHits
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPageHits extends EntityPathBase<PageHits> {

    private static final long serialVersionUID = 1156967811L;

    public static final QPageHits pageHits = new QPageHits("pageHits");

    public final NumberPath<Integer> hitCount = createNumber("hitCount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath pageHitDate = createString("pageHitDate");

    public QPageHits(String variable) {
        super(PageHits.class, forVariable(variable));
    }

    public QPageHits(Path<? extends PageHits> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPageHits(PathMetadata<?> metadata) {
        super(PageHits.class, metadata);
    }

}

